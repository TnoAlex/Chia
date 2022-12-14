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
                            <p class="text-muted mt-2 mb-0 pb-3">{{userInfo.userName}}</p>
                          </div>
                        </div>

                        <div class="timeline-item">
                          <i class="mdi mdi-circle bg-primary-lighten text-primary timeline-icon"></i>
                          <div class="timeline-item-info">
                            <h5 class="mt-0 mb-1">学号</h5>
                            <p class="text-muted mt-2 mb-0 pb-3">{{ userInfo.studentNum}}</p>
                          </div>
                        </div>

                        <div class="timeline-item">
                          <i class="mdi mdi-circle bg-info-lighten text-info timeline-icon"></i>
                          <div class="timeline-item-info">
                            <h5 class="mt-0 mb-1">身份证号</h5>
                            <p class="text-muted mt-2 mb-0 pb-2">{{userInfo.id}}</p>
                          </div>
                        </div>

                        <div class="timeline-item">
                          <i class="mdi mdi-circle bg-info-lighten text-primary timeline-icon"></i>
                          <div class="timeline-item-info">
                            <h5 class="mt-0 mb-1">邮箱</h5>
                            <p class="text-muted mt-2 mb-0 pb-2">{{userInfo.email}}</p>
                          </div>
                        </div>

                        <div class="timeline-item">
                          <i class="mdi mdi-circle bg-info-lighten text-info timeline-icon"></i>
                          <div class="timeline-item-info">
                            <h5 class="mt-0 mb-1">最高分</h5>
                            <p class="text-muted mt-2 mb-0 pb-2">{{ userInfo.maxScore }}</p>
                          </div>
                        </div>

                        <div class="timeline-item">
                          <i class="mdi mdi-circle bg-info-lighten text-primary timeline-icon"></i>
                          <div class="timeline-item-info">
                            <h5 class="mt-0 mb-1">免费资格次数</h5>
                            <p class="text-muted mt-2 mb-0 pb-2">{{userInfo.freeTime}}</p>
                          </div>
                        </div>

                      </div>
                      <!-- end timeline -->
                    </div> <!-- end tab-pane -->
                    <!-- end about me section content -->
                    <!-- end timeline content-->

                    <div class="tab-pane" id="settings">
                      <form>

                        <div class="row">
                          <div class="col-md-6">
                            <div class="mb-3">
                              <label for="password" class="form-label">密码</label>
                              <input type="text" class="form-control" id="password" v-model="passwordChangeInfo.newPassword" placeholder="输入密码">
                            </div>
                          </div> <!-- end col -->

                        </div> <!-- end row -->
                        <div class="row">
                          <div class="col-md-6" style="margin-left: 0!important; ">
                            <div  class="mb-3" style="width: 100%; margin-left: 0!important; padding-left: 0!important;">
                              <label style="width: 100%; display: block" for="code" class="form-label">验证码</label>
                              <div style="margin: 0!important;padding: 0!important;  display: flex; align-items: center; ">
                                <input type="text"  style="display: inline-block; width: 200px" class="form-control" id="code" v-model="passwordChangeInfo.code" placeholder="输入验证码">
                                <el-button :disabled="getCodeFlag"  style="display: inline-block; height:100%; margin-left: 20px; width: 120px;  margin-top: 0!important;"  type="submit" class="btn btn-success mt-3" @click="getCode()">
                                  <i  class="mdi mdi-email"></i>&nbsp;{{getCodeFlag===false?'获取验证码':`${timeCount}后重发`}}
                                </el-button>
                              </div>

                            </div>
                          </div> <!-- end col -->





                        </div> <!-- end row -->






                        <div class="text-end">



                        </div>

                        <div class="text-end">
                          <span type="submit" class="btn btn-success mt-2" @click="checkCode()"><i class="mdi mdi-content-save"></i>保存并提交
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
import '../assets/js/jquery.mask.min'

export default {

  name: "personal_info",
  components: {
    TopNav: topNav,
    LeftNav: leftNav
  },
  beforeMount() {
    this.userInfo = cookies.get('userInfo')
  },
  data(){
    return {
      userInfo:{},
      passwordChangeInfo:{
        newPassword: '',
        code: '',
        type: ''
      },
      getCodeFlag:false,
      timeCount:'',
      timerCount:null
    }
  },
  methods:{
    async checkCode(){
      if(this.passwordChangeInfo.code==='')
      {
        util.messageBox('请填写验证码','warning')
        return
      }
      let loading = util.loadingWait('验证码验证中。。。','success')
      await util.delay(100)
      const _this = this
      this.$axios.post('/verify/reset_code/validate/'+this.passwordChangeInfo.code)
          .then(async (res) => {
            await util.delay(100)
            loading.close()
            let deepLoading = util.loadingWait('验证码正确，正在修改中。。。')
            await util.delay(100)
            this.$axios.post(
                '/user/reset/password',
                {
                  "code": _this.passwordChangeInfo.code,
                  "type": this.userInfo.type,
                  "newPassword": _this.passwordChangeInfo.newPassword
                })
                .then(async (res) =>
                {
                  await util.delay(100)
                  deepLoading.close()
                  util.messageBox('密码修改成功','success')
                  _this.passwordChangeInfo.newPassword=''
                  _this.passwordChangeInfo.code=''
                  if(_this.timeCount>0&&_this.timeCount<60)
                  {
                    _this.timeCount=''
                    clearInterval(this.timerCount)
                    _this.timerCount = null
                    _this.getCodeFlag = false
                  }

                }).catch(async ()=>{
                  await util.delay(100)
                  deepLoading.close()
                  util.messageBox('密码修改失败','error')
            })

          }).catch(async (err)=>{
              await util.delay(100)
              loading.close()
              util.messageBox('验证失败','error')
      })

    },
    async getCode(){
      if(this.passwordChangeInfo.newPassword==='')
      {
        util.messageBox('请输入新密码','warning')
        return
      }
      this.timeReverse()
      let sending = util.loadingWait('发送验证码中')
      await util.delay(100)
      await this.$axios.get('/verify/reset_code')
          .then(async (res) => {
            await util.delay(100)
            sending.close()
            util.messageBox('发送成功','success')
          }).catch(async (err)=>{
            await util.delay(100)
            sending.close()
            util.messageBox('发送失败','error')
          })
    },
    timeReverse()
    {

      let TIME_COUNT = 60;
      if(!this.timerCount)
      {
        this.timeCount = TIME_COUNT
        this.getCodeFlag = true
        this.timerCount = setInterval(()=>{
          if(this.timeCount>0 && this.timeCount<=TIME_COUNT)
          {
            this.timeCount--
          }else
          {
            this.getCodeFlag = false
            clearInterval(this.timerCount)
            this.timerCount = null
          }


        },1000)

      }

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
