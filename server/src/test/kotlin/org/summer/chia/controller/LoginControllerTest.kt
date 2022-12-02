package org.summer.chia.controller

import com.google.gson.Gson
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.http.MediaType
import org.springframework.security.test.context.support.WithAnonymousUser
import org.springframework.security.test.context.support.WithMockUser
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post
import org.springframework.test.web.servlet.result.MockMvcResultHandlers.print
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.content
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status
import org.summer.chia.pojo.ao.Result
import org.summer.chia.pojo.ao.ResultStatus


@SpringBootTest
@AutoConfigureMockMvc
class LoginControllerTest {

    @Autowired
    private lateinit var mvc: MockMvc

    @Test
    fun testLogin() {
        mvc.perform(
            post("/login").contentType(MediaType.APPLICATION_JSON).content(
                "{\n" +
                        "    \"username\":\"test@123.com\",\n" +
                        "    \"password\":\"123456\",\n" +
                        "    \"type\":\"1\"\n" +
                        "}"
            )
        )
            .andExpect(content().json(Gson().toJson(Result.success("登录成功"))))
            .andDo(print())

        mvc.perform(
            post("/login").contentType(MediaType.APPLICATION_JSON).content(
                "{\n" +
                        "    \"username\":\"test@123.com\",\n" +
                        "    \"password\":\"123436\",\n" +
                        "    \"type\":\"1\"\n" +
                        "}"
            )
        )
            .andExpect(content().json(Gson().toJson(Result.error(ResultStatus.BAD_AUTHENTICATION))))
            .andDo(print())

        mvc.perform(
            post("/login").contentType(MediaType.APPLICATION_JSON).content(
                "{\n" +
                        "    \"username\":\"test@123.com\",\n" +
                        "    \"password\":\"123436\",\n" +
                        "    \"type\":\"7\"\n" +
                        "}"
            )
        )
            .andExpect(status().`is`(401))
            .andDo(print())
    }

    @Test
    @WithMockUser
    fun testTestLogin() {
        mvc.perform(post("/test/login").contentType(MediaType.APPLICATION_JSON))
            .andExpect(content().json(Gson().toJson(Result.success("登录成功"))))
            .andDo(print())
    }

    @Test
    @WithAnonymousUser
    fun testTestLoginFail() {
        mvc.perform(post("/test/login").contentType(MediaType.APPLICATION_JSON))
            .andExpect(content().json(Gson().toJson(Result.error(ResultStatus.NONE_AUTHENTICATION))))
            .andDo(print())
    }

    @Test
    @WithAnonymousUser
    fun testLoginPage() {
        mvc.perform(post("/login/page").contentType(MediaType.APPLICATION_JSON))
            .andExpect(content().json(Gson().toJson(Result.success("登录页面"))))
            .andDo(print())
    }

}