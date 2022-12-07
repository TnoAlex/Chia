<template>
  <head>
    <meta charSet="UTF-8">
    <title>登录界面</title>
  </head>
  <body>
  <div className="container">
    <div className="forms-container">
      <div className="signin-signup">
        <form className="sign-in-form">
          <h2 className="title">学生登录</h2>
          <div className="input-field">
            <i className="fas fa-user">
            </i>
            <input id="student_name" v-model="loginObject.username" placeholder="用户名" type="text">
          </div>
          <div className="input-field">
            <i className="fas fa-lock">
            </i>
            <input id="student_passwd" v-model="loginObject.password" placeholder="密码" type="password">
          </div>
          <router-link className="atag" style="display: block;text-align: right" to="forget_password">忘记密码
          </router-link>
          <input className="btn solid" type="button" value="登录" @click="submitForm">
        </form>
        <form className="sign-up-form">
          <h2 className="title">教师登录</h2>
          <div className="input-field">
            <i className="fas fa-user"></i>
            <input id="teacher_name" v-model="loginObject.username" placeholder="用户名" type="text">
          </div>
          <div className="input-field">
            <i className="fas fa-lock"></i>
            <input id="teacher_passwd" v-model="loginObject.password" placeholder="密码" type="password">
          </div>
          <router-link className="atag" style="display: block;text-align: right" to="forget_password">忘记密码
          </router-link>
          <input className="btn" type="button" value="登录" @click="submitForm">
        </form>
      </div>
    </div>
    <div className="panels-container">
      <div className="panel left-panel">
        <div className="content" style="margin-right: 200px">
          <h3>您是教师 ?</h3>
          <p>请 点 击 此 处 进 行 登 录!</p>
          <button id="sign-up-btn" className="btn transparent">教师登录</button>
        </div>
      </div>
      <div className="panel right-panel">
        <div className="content">
          <h3>您是学生 ?</h3>
          <p>请 点 击 此 处 进 行 登 录!</p>
          <button id="sign-in-btn" className="btn transparent">学生登录</button>
        </div>
      </div>
    </div>
  </div>
  </body>
</template>
<script>
import util from '../utils/commonUtil'
import cookies from "vue-cookies";
import axios from "axios";

export default {
  name: "login",
  data() {
    return {
      loginObject: {
        type: 0,
        username: '',
        password: ''
      }
    }
  },
  mounted() {
    const sign_in_btn = document.querySelector("#sign-in-btn");
    const sign_up_btn = document.querySelector("#sign-up-btn");
    const container = document.querySelector(".container");
    sign_up_btn.addEventListener("click", () => {
      container.classList.add("sign-up-mode");
      this.loginObject.type = 1
    });
    sign_in_btn.addEventListener("click", () => {
      container.classList.remove("sign-up-mode");
      this.loginObject.type = 0
    });
  },
  methods: {
    async getUserInfo() {
      if (util.userInfo.type === 1) {
        await this.$axios.get('/teacher/brief_info')
            .then((res) => {
              util.userInfo.userName = res.data.msg
            })
            .catch(reason => {

              if (reason.data.data === null) {
                util.messageBox(reason.data.msg, 'error')
              } else {
                this.$router.push({
                  path: '/error',
                  errorCode: reason.data.code,
                  errorMsg: reason.data.msg,
                  errorCaused: reason.data
                })
              }
            })
      } else {
        await this.$axios.get('student/brief_info')
            .then((res) => {
              util.userInfo.userName = res.data.data.name
              util.userInfo.studentNum = res.data.data.studentNum
              util.userInfo.maxScore = res.data.data.maxScore
              util.userInfo.freeTime = res.data.data.freeTime
              util.userInfo.status = res.data.data.status
            })
            .catch(err => {
              this.$router.push({
                path:'/error',
                query:{
                  errorCode: err.response.status,
                  errorMsg: err.msg,
                  errorCaused: err.response.data
                }
              })
            })
      }
    },
    async submitForm() {
      let loading = util.loadingWait('登录中...')
      let loginStatus = 0
      await util.delay(100)
      await this.$axios.post(
          "/login",
          JSON.stringify(this.loginObject)
      ).then(() => {
        util.userInfo.type = this.loginObject.type
        loginStatus = 1
      })
          .catch(err => {
            loading.close()
            if (err.response.status===500)
            {
              util.messageBox('登录失败', 'error')
              this.$router.push({
                path:'/error',
                query:{
                  errorCode: err.response.status,
                  errorMsg: err.msg,
                  errorCaused: err.response.data
                }
              })
            }
            else
            {
              util.messageBox('身份验证错误', 'error')
            }


          })
      if (loginStatus === 0)
      {
        return
      }

      await this.getUserInfo()
      await util.delay(100)
      loading.close()
      if (loginStatus === 0) {
        util.messageBox('登录失败', 'error')

      } else {
        util.messageBox('登录成功', 'success')
        cookies.set('userInfo',util.userInfo,{ expires: 7 })
        this.$router.push('/index')
      }
    }
  }
}
</script>
<style scoped src="../assets/css/login.css"></style>
<style scoped src="../assets/css/login_style.css"></style>