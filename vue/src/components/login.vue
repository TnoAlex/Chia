<template>
  <head>
    <meta charset="UTF-8">
    <title>登录界面</title>
  </head>

  <body>
  <div class="container">
    <div class="forms-container">
      <div class="signin-signup">
        <form class="sign-in-form">
          <h2 class="title">学生登录</h2>
          <div class="input-field">
            <i class="fas fa-user">
            </i>
            <input id="student_name" v-model="loginObject.username" placeholder="用户名" type="text">
          </div>
          <div class="input-field">
            <i class="fas fa-lock">
            </i>
            <input id="student_passwd" v-model="loginObject.password" placeholder="密码" type="password">
          </div>
          <router-link class="atag" style="display: block;text-align: right" to="forget_password">忘记密码</router-link>
          <input class="btn solid" type="button" value="登录" @click="submitForm">
        </form>
        <form class="sign-up-form">
          <h2 class="title">教师登录</h2>
          <div class="input-field">
            <i class="fas fa-user"></i>
            <input id="teacher_name" v-model="loginObject.username" placeholder="用户名" type="text">
          </div>
          <div class="input-field">
            <i class="fas fa-lock"></i>
            <input id="teacher_passwd" v-model="loginObject.password" placeholder="密码" type="password">
          </div>
          <router-link class="atag" style="display: block;text-align: right" to="forget_password">忘记密码</router-link>
          <input class="btn" type="button" value="登录" @click="submitForm">
        </form>
      </div>
    </div>
    <div class="panels-container">
      <div class="panel left-panel">
        <div class="content" style="margin-right: 200px">
          <h3>您是教师 ?</h3>
          <p>请 点 击 此 处 进 行 登 录!</p>
          <button id="sign-up-btn" class="btn transparent">教师登录</button>
        </div>
      </div>
      <div class="panel right-panel">
        <div class="content">
          <h3>您是学生 ?</h3>
          <p>请 点 击 此 处 进 行 登 录!</p>
          <button id="sign-in-btn" class="btn transparent">学生登录</button>
        </div>
      </div>
    </div>
  </div>
  </body>
</template>

<script>
import messageBox from "@/utils/commonUtil";

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
    async submitForm() {
      await this.$axios.post(
          "/login",
          JSON.stringify(this.loginObject)
      ).then(() => {
        messageBox('success', '登录成功')
      })
          .catch(err => {
            messageBox('error', err.data.msg)
          })
    }
  }
}
</script>

<style scoped src="../assets/css/login.css"></style>
<style scoped src="../assets/css/login_style.css"></style>

