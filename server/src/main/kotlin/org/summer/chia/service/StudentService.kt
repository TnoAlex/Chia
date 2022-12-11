package org.summer.chia.service

import com.baomidou.mybatisplus.extension.service.IService
import org.springframework.security.core.userdetails.UserDetails
import org.summer.chia.pojo.ao.FreshmanInfo
import org.summer.chia.pojo.ao.Result
import org.summer.chia.pojo.dto.Student
import java.util.concurrent.CompletableFuture

interface StudentService : IService<Student> {
    fun loadUserByUsername(username: String): UserDetails?
    fun getBriefInfo(user: UserDetails): Result
    fun enableAccount(mailAddress: String, user: UserDetails): CompletableFuture<Result>
    fun importStudent(infos: List<FreshmanInfo>): Result
    fun queryStudentList(pageNum: String, pageSize: String): Result
    fun queryStudentTotalNumber(): Result
    fun removeStudent(list: List<String>): Result
    fun doFilterStudentList(
        score: String, score_filter: String, grade: String,
        free_time: String,
        pageNum: String,
        pageSize: String
    ): Result

    fun queryStudentByNameOrNumber(name: String, number: String): Result
    fun doQueryDetails(user: UserDetails): Result
    fun doFilterStudentDelete(
        score: String, score_filter: String, grade: String,
        free_time: String
    ): Result
}