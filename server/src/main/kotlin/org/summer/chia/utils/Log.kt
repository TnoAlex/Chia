package org.summer.chia.utils

import org.slf4j.LoggerFactory

object Log {

    fun debug(clazz: Class<*>, msg: String, params: Any?) {
        val logger = LoggerFactory.getLogger(clazz.name)
        logger.debug(msg, params)
    }

    fun trace(clazz: Class<*>, msg: String, params: Any?) {
        val logger = LoggerFactory.getLogger(clazz.name)
        logger.trace(msg, params)
    }

    fun info(clazz: Class<*>, msg: String, params: Any?) {
        val logger = LoggerFactory.getLogger(clazz.name)
        logger.info(msg, params)
    }

    fun warn(clazz: Class<*>, msg: String, params: Any?) {
        val logger = LoggerFactory.getLogger(clazz.name)
        logger.warn(msg, params)
    }

    fun error(clazz: Class<*>, msg: String, params: Any?) {
        val logger = LoggerFactory.getLogger(clazz.name)
        logger.error(msg, params)
    }
}