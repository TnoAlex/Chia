<template>
  <head>
    <meta charset="utf-8">
    <meta content="width=device-width, initial-scale=1.0" name="viewport">

    <title>激活账号</title>
  </head>
  <body>

  <main>
    <div class="container">

      <section class="section register min-vh-100 d-flex flex-column align-items-center justify-content-center py-4">
        <div class="container">
          <div class="row justify-content-center">
            <div class="col-lg-4 col-md-6 d-flex flex-column align-items-center justify-content-center">

              <div class="d-flex justify-content-center">
                <router-link class="logo d-flex align-items-center w-auto"
                             style="position: relative;margin-bottom: 40px" to="/">
                  <img alt="" src="@/assets/img/logo.png">
                  <span class="d-none d-lg-block">NiceAdmin</span>
                </router-link>
              </div><!-- End Logo -->

              <div class="card mb-3">

                <div class="card-body">

                  <div class="pt-4 pb-2">
                    <h5 class="card-title text-center pb-0 fs-4">激活账号</h5>
                    <p class="text-center small" style="color: #aaaaaa">验证码将会发送至你的邮箱</p>
                  </div>

                  <form class="row g-3 needs-validation" novalidate>

                    <div ref="mail_address" class="col-12">
                      <input ref="mail_address_input" v-model="emailAddress" class="form-control" name="password"
                             placeholder="邮箱地址"
                             type="text">
                    </div>

                    <div ref="code_input" class="col-12" style="visibility: hidden;display: none">
                      <input v-model="code" class="form-control" name="code" placeholder="请输入验证码" type="text">
                    </div>

                    <div ref="next" class="col-12">
                      <button ref="next_button" class="btn btn-primary w-100" type="button" @click="submitMailAddress">
                        下一步
                      </button>
                    </div>
                    <div ref="submit_code" class="col-12" style="visibility: hidden;display: none">
                      <button class="btn btn-primary w-100" type="button" @click="submitCode">确认</button>
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
import {toBase64} from "js-base64";
import util from "@/utils/commonUtil";
import 'bootstrap/dist/css/bootstrap.min.css'
import 'bootstrap/dist/js/bootstrap.min.js'

export default {
  name: "enable_account",
  data() {
    return {
      emailAddress: "",
      code: ""
    }
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
          button.innerText = "下一步"
          button.disabled = false
        }
      }, 1000)
      let reg = /^([a-zA-Z\d][\w-]{2,})@(\w{2,})\.([a-z]{2,})(\.[a-z]{2,})?$/
      if (!reg.test(this.emailAddress)) {
        clearInterval(button.timer)
        button.duration = 60
        button.innerText = "下一步"
        button.disabled = false
        util.messageBox("邮箱格式错误", "error")
        return
      }
      await this.$axios.post("/student/enable/" + toBase64(this.emailAddress))
          .then((res) => {
            clearInterval(button.timer)
            button.duration = 60
            button.innerText = "下一步"
            button.disabled = false
            this.$refs.next.style.visibility = "hidden"
            this.$refs.next.style.display = "none"
            this.$refs.submit_code.style.visibility = "visible"
            this.$refs.submit_code.style.display = "flex"
            this.$refs.mail_address_input.disabled = true
            this.$refs.code_input.style.visibility = "visible"
            this.$refs.code_input.style.display = "flex"
            if (res.data.code === 403) {
              util.messageBox(res.data.msg, "error")
            }
          }).catch(err => {
            util.messageBox(err.data.msg, "error")
          })
    },
    async submitCode() {
      await this.$axios.post("/student/verify/activate_account/" + this.code)
          .then((res) => {
            if (res.data.code === 403) {
              util.messageBox(res.data.msg, "error")
            } else {
              util.messageBox("激活成功", "success")
              this.$router.push('/index')
            }
          }).catch(err => {
            util.messageBox(err.data.msg, "error")
          })
    },
  }
}
</script>

<style scoped src="../assets/css/forget_password_style.css">
</style>