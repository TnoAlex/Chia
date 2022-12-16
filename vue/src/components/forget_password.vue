<template>
  <head>
    <meta charset="utf-8">
    <meta content="width=device-width, initial-scale=1.0" name="viewport">

    <title>忘记密码</title>
  </head>
  <body>

  <main>
    <div class="container">

      <section class="section register min-vh-100 d-flex flex-column align-items-center justify-content-center py-4">
        <div class="container">
          <div class="row justify-content-center">
            <div class="col-lg-4 col-md-6 d-flex flex-column align-items-center justify-content-center">

              <div class="d-flex justify-content-center py-4">
                <router-link class="logo d-flex align-items-center w-auto" to="/">
                  <img src="@/assets/img/logo.png" alt="">
                  <span class="d-none d-lg-block">NiceCSP</span>
                </router-link>
              </div><!-- End Logo -->

              <div class="card mb-3">

                <div class="card-body">

                  <div class="pt-4 pb-2">
                    <h5 class="card-title text-center pb-0 fs-4">找回密码</h5>
                    <p class="text-center small" style="color: #aaaaaa">验证码将会发送至你的注册邮箱</p>
                  </div>

                  <form class="row g-3 needs-validation" novalidate>

                    <div class="col-12" ref="mail_address">
                      <input ref="mail_address_input" class="form-control" name="password" placeholder="邮箱地址"
                             type="text"
                             v-model="emailAddress">
                    </div>

                    <div class="col-12" ref="code_input" style="visibility: hidden;display: none">
                      <input type="text" name="code" class="form-control" placeholder="请输入验证码" v-model="code">
                    </div>

                    <div class="col-12" ref="password" style="visibility: hidden;display: none">
                      <input type="password" name="password" class="form-control" placeholder="请输入新密码"
                             v-model="newPassword">
                    </div>
                    <div class="col-12" ref="repeat_password" style="visibility: hidden;display: none">
                      <input type="password" name="password" class="form-control" placeholder="请输再次密码"
                             v-model="repeatPassword">
                    </div>
                    <div class="col-12" ref="next">
                      <button class="btn btn-primary w-100" type="button" ref="next_button" @click="submitMailAddress">下一步</button>
                    </div>
                    <div class="col-12" style="visibility: hidden;display: none" ref="submit_code">
                      <button class="btn btn-primary w-100" type="button" @click="submitCode">确认</button>
                    </div>
                    <div class="col-12" style="visibility: hidden;display: none" ref="submit_password">
                      <button class="btn btn-primary w-100" type="button" @click="submitPassword">确认</button>
                    </div>
                    <div class="col-12">
                      <p class="small mb-0">
                        <router-link to="/" style="color: #aaaaaa">返回登录</router-link>
                      </p>
                    </div>
                  </form>

                </div>
              </div>

            </div>
          </div>
        </div>

      </section>

    </div>
  </main>
  </body>
</template>

<script>
import 'bootstrap/dist/css/bootstrap.min.css'
import 'bootstrap/dist/js/bootstrap.min.js'
import {toBase64} from "js-base64";
import util from '../utils/commonUtil'

export default {
  name: "forget_password",
  data() {
    return {
      emailAddress: "",
      code: "",
      newPassword: "",
      repeatPassword: "",
      type: ""
    }
  },
  mounted() {
    this.type = this.$route.query.type
  },
  methods: {
    async submitMailAddress() {
      let button = this.$refs.next_button
      button.disabled = true
      button.duration = 60
      button.timer = setInterval(() => {
        const tmp = button.duration--
        button.innerText = tmp + "秒后重新获取"
        if (tmp <= 0) {
          clearInterval(button.timer)
          button.duration = 60
          button.text = "下一步"
          button.disabled = false
        }
      },1000)
      await this.$axios.post("/forget/password/email/" + toBase64(this.emailAddress))
          .then((res) => {
            clearInterval(button.timer)
            button.duration = 60
            button.text = "下一步"
            button.disabled = false
            if (res.data.code === 403) {
              util.messageBox(res.data.msg, "error")
            } else {
              this.$refs.next.style.visibility = "hidden"
              this.$refs.next.style.display = "none"
              this.$refs.submit_code.style.visibility = "visible"
              this.$refs.submit_code.style.display = "flex"
              this.$refs.mail_address_input.disabled = true
              this.$refs.code_input.style.visibility = "visible"
              this.$refs.code_input.style.display = "flex"
            }
          }).catch(err=>{
            util.messageBox(err.data.msg, "error")
          })
    },
    async submitCode() {
      await this.$axios.post("/verify/reset_code/validate/" + this.code)
          .then((res) => {
            if (res.data.code === 403) {
              util.messageBox(res.data.msg, "error")
            } else {
              this.$refs.mail_address.style.visibility = "hidden"
              this.$refs.mail_address.style.display = "none"
              this.$refs.code_input.style.visibility = "hidden"
              this.$refs.code_input.style.display = "none"
              this.$refs.submit_code.style.visibility = "hidden"
              this.$refs.submit_code.style.display = "none"
              this.$refs.submit_password.style.visibility = "visible"
              this.$refs.submit_password.style.display = "flex"
              this.$refs.password.style.visibility = "visible"
              this.$refs.password.style.display = "flex"
              this.$refs.repeat_password.style.visibility = "visible"
              this.$refs.repeat_password.style.display = "flex"
            }
          }).catch(err => {
            util.messageBox(err.data.msg, "error")
          })
    },
    async submitPassword() {
      if (this.newPassword !== this.repeatPassword)
        util.messageBox("两次输入不匹配！", "error")
      await this.$axios.post(
          "/forget/password/rest",
          {
            newPassword: this.newPassword,
            code: this.code,
            type: this.type
          }
      ).then((res) => {
        if (res.data.code === 403) {
          util.messageBox(res.data.msg, "error")
        }
        util.messageBox("修改成功", "success")
        this.$router.push('/')
      }).catch(err => {
        util.messageBox(err.data.msg, "error")
      })
    },
  },
}
</script>

<style src="../assets/css/forget_password_style.css" scoped>
</style>