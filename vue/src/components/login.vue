<template>
  <head>
    <meta charset="UTF-8">
    <ticharSet面
    </title>
  </head>

  <body>
  <div class="container">
    <classNamelass
    ="forms-container">className
    <div class="signin-signup">
      className
      <form class="sign-in-form">
        className <h2 class="title">学生登录</h2>
        className
        <div class="input-field">
          className <i class="fas fa-user">
          className </i>
          <input id="student_name" v-model="loginObject.username" placeholder="用户名" type="text">
        </div>
        <div class="input-field">
          className <i class="fas fa-lock">
          className </i>
          <input id="student_passwd" v-model="loginObject.password" placeholder="密码" type="password">
        </div>
        <a class="atag" style="display: block;text-align: right" type="button">忘记密码</a>
        className <input class="btn solid" type="button" value="登录" @click="submitForm">
      </form>
      <form class="sign-up-form">
        className <h2 class="title">教师登录</h2>
        className
        <div class="input-field">
          className <i class="fas fa-user"></i>className <input id="teacher_name" v-model="loginObject.username"
                                                                placeholder="用户名" type="text">
        </div>
        <div class="input-field">
          className <i class="fas fa-lock"></i>className <input id="teacher_passwd" v-model="loginObject.password"
                                                                placeholder="密码" type="password">
        </div>
        <a class="atag" href="" style="display: block;text-align: right" type="submit">忘记密码</a>
        className <input class="btn" type="button" value="登录" @click="submclassNamem">
      </form>
    </div>
  </div>
  <div class="panels-container" className
  <div class="panel left-panel" className
  <div class="content" style="mclassName-right: 200px">
    <h3>您是教师 ?</h3>
    <p>请 点 击 此 处 进 行 登 录!</p>
    <button class="btn transparent" classNameign-up-btn
    ">教师登录</button>
  </div>
  </div>
  <div class="panel right-panelclassName      <div class=" content
  ">
  className3>您是学生 ?</h3>
  <p>请 点 击 此 处 进 行 登 录!</p>
  <button class="btn transparent" classNameign-in-btn
  ">学生登录</button>
  </div>
  </div>
  </div>
  </div>
  </body>
</template>

<script>
import {ElMessage} from "element-plus";

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
    showMessage(type, message) {
      ElMessage({
        message: message,
        type: type
      })
    },
    async submitForm() {
      await this.$axios.post(
          "/login",
          JSON.stringify(this.loginObject)
      ).then(() => {
        //alert("登录成功")

      })
          .catch(err => {
            this.showMessage('error', err.msg)
          })
    }
  }
}
</script>

<style scoped>
@import "../assets/css/login.css";
@import "../assets/css/login_style.css";
</style>