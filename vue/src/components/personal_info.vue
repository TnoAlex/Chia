<template>
  <body>
  <!-- Begin page -->
  <div class="wrapper">
    <TopNav ref="childTopNav">
    </TopNav>
    <LeftNav>
    </LeftNav>

    <div class="content-page">
      <div class="content">

        <!-- Start Content-->
        <div class="container-fluid">

          <!-- start page title -->
          <div class="row">
            <div class="col-12">
              <div class="page-title-box">
                <div class="page-title-right">
                  <ol class="breadcrumb m-0">
                  
                  </ol>
                </div>
                <h4 class="page-title">个人信息页</h4>
              </div>
            </div>
          </div>
          <!-- end page title -->

          <div class="row">

            <div class="col-xl-8 col-lg-7">
              <div class="card">
                <div class="card-body">
                  <ul class="nav nav-pills bg-nav-pills nav-justified mb-3">
                    <li class="nav-item">
                      <a href="#aboutme" data-bs-toggle="tab" aria-expanded="false" class="nav-link rounded-0 active">
                        个人信息
                      </a>
                    </li>
                    <li class="nav-item">
                      <a href="#settings" data-bs-toggle="tab" aria-expanded="false" class="nav-link rounded-0">
                        修改信息
                      </a>
                    </li>
                  </ul>
                  <div class="tab-content">
                    <div class="tab-pane show active" id="aboutme">

                      <h5 class="text-uppercase"><i class="mdi mdi-briefcase me-1"></i>
                        主要信息</h5>
                      <div class="timeline-alt pb-0">
                        <div class="timeline-item">
                          <i class="mdi mdi-circle bg-info-lighten text-info timeline-icon"></i>
                          <div class="timeline-item-info">
                            <h5 class="mt-0 mb-1">姓名</h5>
                            <p class="text-muted mt-2 mb-0 pb-3">{{getinfoObject.userName}}</p>
                          </div>
                        </div>

                        <div class="timeline-item">
                          <i class="mdi mdi-circle bg-primary-lighten text-primary timeline-icon"></i>
                          <div class="timeline-item-info">
                            <h5 class="mt-0 mb-1">学号</h5>
                            <p class="text-muted mt-2 mb-0 pb-3">{{ getinfoObject.studentNum }}</p>
                          </div>
                        </div>

                        <div class="timeline-item">
                          <i class="mdi mdi-circle bg-info-lighten text-info timeline-icon"></i>
                          <div class="timeline-item-info">
                            <h5 class="mt-0 mb-1">身份证号</h5>
                            <p class="text-muted mt-2 mb-0 pb-2">等待身份证号被cookie存入</p>
                          </div>
                        </div>

                        <div class="timeline-item">
                          <i class="mdi mdi-circle bg-info-lighten text-primary timeline-icon"></i>
                          <div class="timeline-item-info">
                            <h5 class="mt-0 mb-1">邮箱</h5>
                            <p class="text-muted mt-2 mb-0 pb-2">等待邮箱在cookie中被存入</p>
                          </div>
                        </div>

                        <div class="timeline-item">
                          <i class="mdi mdi-circle bg-info-lighten text-info timeline-icon"></i>
                          <div class="timeline-item-info">
                            <h5 class="mt-0 mb-1">最高分</h5>
                            <p class="text-muted mt-2 mb-0 pb-2">{{ getinfoObject.maxScore }}</p>
                          </div>
                        </div>

                        <div class="timeline-item">
                          <i class="mdi mdi-circle bg-info-lighten text-primary timeline-icon"></i>
                          <div class="timeline-item-info">
                            <h5 class="mt-0 mb-1">免费资格次数</h5>
                            <p class="text-muted mt-2 mb-0 pb-2">{{getinfoObject.freeTime}}</p>
                          </div>
                        </div>

                      </div>
                      <!-- end timeline -->
                    </div> <!-- end tab-pane -->
                    <!-- end about me section content -->
                    <!-- end timeline content-->

                    <div class="tab-pane" id="settings">
                      <form>
                        <h5 class="mb-4 text-uppercase"><i class="mdi mdi-account-circle me-1"></i> 个人信息</h5>
                        <div class="row">
                          <div class="col-md-6">
                            <div class="mb-3">
                              <label for="password" class="form-label">密码</label>
                              <input type="text" class="form-control" id="password" v-model="pwdchangeObject.newPassword" placeholder="输入密码">
                            </div>
                          </div> <!-- end col -->
                          <div class="col-md-6">
                            <div class="mb-3">
                              <label for="code" class="form-label">验证码</label>
                              <input type="text" class="form-control" id="code" v-model="pwdchangeObject.code" placeholder="输入验证码">
                            </div>
                          </div> <!-- end col -->
                        </div> <!-- end row -->

                        <div class="text-end">
                          <span type="submit" class="btn btn-success mt-2" @click="getCode()"><i class="mdi mdi-content-save"></i>获取验证码
                          </span>
                        </div>

                        <div class="text-end">
                          <span type="submit" class="btn btn-success mt-2" @click="changePwd()"><i class="mdi mdi-content-save"></i>保存并提交
                          </span>
                        </div>
                      </form>
                    </div>
                    <!-- end settings content-->

                  </div> <!-- end tab-content -->
                </div> <!-- end card body -->
              </div> <!-- end card -->
            </div> <!-- end col -->
          </div>
          <!-- end row-->

        </div>
        <!-- container -->

      </div>
    </div>
  </div>
  </body>

  <el-dialog title="弹窗" v-model="resObject.ifOut" width="35%">
    {{this.resObject.msg}}
  </el-dialog>

</template>

<script>
import topNav from "@/components/index/topNav";
import leftNav from "@/components/index/leftNav";

import util from '../utils/commonUtil'
import cookies from "vue-cookies";

import '../utils/commonUtil'
import '../assets/js/hyper-config'
import '../assets/js/moment.min'
import '../assets/js/vendor.min'
import '../assets/js/apexcharts.min'
import 'bootstrap/dist/js/bootstrap.min.js'
import '../assets/js/vendor.min'
import '../assets/js/jquery.mask.min'
import '../assets/js/moment.min'

export default {

  name: "personal_info",
  components: {
    TopNav: topNav,
    LeftNav: leftNav
  },
  beforeMount() {

    console.log(1)
  },
  data(){
    return {
      getinfoObject:{},
      pwdchangeObject:{
        newPassword: '',
        code: '',
        type: ''
      },
      resObject:{
        code: '',
        msg: '',
        ifOut: false,
        codeIsTrue: false
      }
    }
  },
  mounted() {
    let userInfo = cookies.get('userInfo')
    this.getinfoObject = userInfo
  },
  methods:{

    async checkCode(){
      this.$axios.post('/verify/reset_code/validate/'+this.pwdchangeObject.code)
          .then((res)=>{
            console.log(res)
            this.resObject.codeIsTrue = res.data.code === 200;
            console.log(this.resObject.codeIsTrue)
          })
    },
    async changePwd(){
      console.log(this.pwdchangeObject)
      const _this=this
      await this.checkCode()
      if(this.resObject.codeIsTrue === true)
      {
        console.log(this.getinfoObject.type)
        await this.$axios.post(
            '/user/reset/password',
            {
              "code": _this.pwdchangeObject.code,
              "type": this.getinfoObject.type,
              "newPassword": _this.pwdchangeObject.newPassword
            })
            .then((res)=>{
              if(res.data.msg === 'ok')
                this.resObject.msg = '修改成功！'
              else
                this.resObject.msg = res.data.msg
              this.resObject.code = res.data.code
              this.resObject.ifOut = true
            })
      }
      else{
        this.resObject.msg = '验证码错误'
        this.resObject.ifOut = true
      }
    },
    async getCode(){
      let sending = util.loadingWait('发送验证码中')
      await util.delay(100)
      await this.$axios.get('/verify/reset_code')
          .then(async (res) => {
            await util.delay(100)
            sending.close()
            if (res.data.code !== 200) {
              this.resObject.msg = '验证码发送失败！'
            } else {
              this.resObject.msg = '验证码已经发送'
            }
            this.resObject.ifOut = true
          })
    }
  }

}
</script>

<!--<style scoped>-->
<!--@import "../assets/css/index_css/icons.min.css";-->
<!--@import "../assets/css/setting_css/app-saas.min.css";-->
<!--@import "../assets/css/index_css/jquery-jvectormap-1.2.2.css";-->
<!--</style>-->
<style scoped src="../assets/css/index_css/icons.min.css"></style>
<!--<style scoped src="../assets/css/setting_css/app-saas.min.css"></style>-->
<style scoped src="../assets/css/index_css/jquery-jvectormap-1.2.2.css"></style>