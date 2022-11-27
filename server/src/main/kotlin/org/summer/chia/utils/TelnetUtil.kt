package org.summer.chia.utils

import org.apache.commons.net.telnet.TelnetClient
import org.springframework.stereotype.Component
import org.springframework.util.Base64Utils
import org.summer.chia.exception.ServiceConfigException
import java.io.InputStream
import java.io.OutputStream


@Component
object TelnetUtil : Runnable {

    private var responseStr = StringBuilder()
    private val telnetClient = TelnetClient()
    private lateinit var inputStream: InputStream
    private lateinit var outputStream: OutputStream

    fun testMailConnection(host: String, username: String, password: String) {
        try {
            val temp = host.split(".")
            var serverName = ""
            temp.slice(1 until temp.size).forEach {
                serverName += "$it."
            }
            serverName = serverName.substring(0, serverName.length - 1)
            val commandResponseMap = mapOf(
                "helo $serverName" to "250 OK", "auth login" to "334",
                String(Base64Utils.encode(username.toByteArray())) to "334",
                String(Base64Utils.encode(password.toByteArray())) to "235 Authentication successful"
            )
            val welcome = connect(host)
            if (!welcome.contains("220"))
                throw ServiceConfigException("The remote server connection failed", this::testMailConnection.name)
            for (i in commandResponseMap.keys) {
                val res = senCommand(i)
                if(!res.contains(commandResponseMap[i]!!)){
                    throw ServiceConfigException("System mailbox configuration failed with incorrect user name or password", this::testMailConnection.name)
                }
            }
        } catch (e: Exception) {
            throw ServiceConfigException("An unexpected occurred during system mailbox configuration",this::testMailConnection.name)
        }
    }

    override fun run() {
        try {
            val buff = ByteArray(1024)
            var retRead = 0
            do {
                retRead = inputStream.read(buff)
                if (retRead > 0) {
                    responseStr.append(String(buff, 0, retRead))
                }
            } while (retRead >= 0)
        } catch (e: Exception) {
            throw e
        }
    }

    private fun connect(host: String): String {
        telnetClient.connect(host, 25)
        outputStream = telnetClient.outputStream
        inputStream = telnetClient.inputStream
        val reader = Thread(this)
        reader.start()
        return getResponse()
    }

    private fun getResponse(): String {
        Thread.sleep(500)
        return responseStr.toString()
    }

    private fun senCommand(command: String): String {
        responseStr.clear()
        outputStream.write(command.toByteArray())
        outputStream.write(13)
        outputStream.write(10)
        outputStream.flush()
        return getResponse()
    }
}