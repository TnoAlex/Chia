package org.summer.chia.controller

import com.google.gson.Gson
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.http.MediaType
import org.springframework.test.annotation.Rollback
import org.springframework.test.context.ContextConfiguration
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post
import org.springframework.test.web.servlet.result.MockMvcResultHandlers.print
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.content
import org.springframework.transaction.annotation.Transactional
import org.summer.chia.annotation.WithMockStudent
import org.summer.chia.annotation.WithMockTeacher
import org.summer.chia.pojo.ao.Result
import org.summer.chia.pojo.ao.StudentBriefInfo
import org.summer.chia.pojo.ao.StudentListItem


@SpringBootTest
@AutoConfigureMockMvc
@ContextConfiguration
internal class UserControllerTest {

    @Autowired
    private lateinit var mvc: MockMvc

    @Test
    @WithMockTeacher
    fun teacherInfo() {
        mvc.perform(get("/teacher/brief_info"))
            .andExpect(content().json(Gson().toJson(Result.success("test"))))
            .andDo(print())
    }

    @Test
    @WithMockStudent
    fun studentBriefInfo() {
        mvc.perform(get("/student/brief_info"))
            .andExpect(
                content().json(
                    Gson().toJson(
                        Result.success(
                            StudentBriefInfo(
                                "test",
                                "123456",
                                300,
                                1,
                                1
                            )
                        )
                    )
                )
            ).andDo(print())
    }


    @Test
    @Rollback(true)
    @Transactional
    @WithMockTeacher
    fun importFreshManInfo() {
        mvc.perform(
            post("/teacher/import/freshman").contentType(MediaType.APPLICATION_JSON).content(
                "[\n" +
                        "    {\n" +
                        "        \"name\": \"阿庆1\",\n" +
                        "        \"studentNumber\": 1,\n" +
                        "        \"enrollmentTime\": \"2020-9-3\",\n" +
                        "        \"idNumber\":\"123456123456\"\n" +
                        "    },\n" +
                        "    {\n" +
                        "        \"name\": \"阿庆2\",\n" +
                        "        \"studentNumber\": 2,\n" +
                        "        \"enrollmentTime\": \"2020-10-4\",\n" +
                        "        \"idNumber\":\"123457123456\"\n" +
                        "    },\n" +
                        "    {\n" +
                        "        \"name\": \"阿庆3\",\n" +
                        "        \"studentNumber\": 3,\n" +
                        "        \"enrollmentTime\": \"2020-10-5\",\n" +
                        "        \"idNumber\":\"123458123456\"\n" +
                        "    },\n" +
                        "    {\n" +
                        "        \"name\": \"阿庆4\",\n" +
                        "        \"studentNumber\": 4,\n" +
                        "        \"enrollmentTime\": \"2020-10-6\",\n" +
                        "        \"idNumber\":\"123459123456\"\n" +
                        "    },\n" +
                        "    {\n" +
                        "        \"name\": \"阿庆5\",\n" +
                        "        \"studentNumber\": 5,\n" +
                        "        \"enrollmentTime\": \"2020-10-0\",\n" +
                        "        \"idNumber\":\"123460123456\"\n" +
                        "    },\n" +
                        "    {\n" +
                        "        \"name\": \"阿庆6\",\n" +
                        "        \"studentNumber\": 6,\n" +
                        "        \"enrollmentTime\": \"2020-10-1\",\n" +
                        "        \"idNumber\":\"123461123456\"\n" +
                        "    }\n" +
                        "]"
            )
        ).andExpect(content().json(Gson().toJson(Result.success())))
            .andDo(print())
    }

    @Test
    @WithMockTeacher
    fun queryStudentList() {
        val test = ArrayList<StudentListItem>()
        test.add(StudentListItem("1593439666370023425", "test", "123456", 300, 1, "2020级", 7))
        test.add(StudentListItem("1598636048336887810", "阿庆1", "1", 0, 1, "2020级", 7))
        test.add(StudentListItem("1598636048697597953", "阿庆2", "2", 0, 1, "2020级", 7))
        test.add(StudentListItem("1598636048961839106", "阿庆3", "3", 0, 1, "2020级", 7))
        test.add(StudentListItem("1598636049230274561", "阿庆4", "4", 0, 1, "2020级", 7))
        mvc.perform(get("/student/list/0/5"))
            .andExpect(content().json(Gson().toJson(Result.success(test))))
            .andDo(print())
    }

    @Test
    @WithMockTeacher
    fun queryStudentTotalNum() {
        mvc.perform(get("/student/list/total"))
            .andExpect(content().json(Gson().toJson(Result.success(7))))
            .andDo(print())
    }


    @Test
    @WithMockTeacher
    fun filterStudentList() {
        val test = ArrayList<StudentListItem>()
        test.add(StudentListItem("1598636048336887810", "阿庆1", "1", 0, 1, "2020级", 4))
        test.add(StudentListItem("1598636048697597953", "阿庆2", "2", 0, 1, "2020级", 4))
        test.add(StudentListItem("1598636048961839106", "阿庆3", "3", 0, 1, "2020级", 4))
        test.add(StudentListItem("1598636049230274561", "阿庆4", "4", 0, 1, "2020级", 4))
        mvc.perform(get("/student/filter/0/4/null/null/0/4"))
            .andExpect(content().json(Gson().toJson(Result.success(test))))
    }

    @Test
    @WithMockTeacher
    fun queryStudentByNameOrNumber() {
        mvc.perform(get("/student/filter/test/null"))
            .andExpect(
                content().json(
                    Gson().toJson(
                        Result.success(
                            StudentListItem(
                                "1593439666370023425",
                                "test",
                                "123456",
                                300,
                                1,
                                "2020级",
                                1
                            )
                        )
                    )
                )
            )
    }
}