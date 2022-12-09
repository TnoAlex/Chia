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
                    <li class="breadcrumb-item"><a href="javascript: void(0);">主页</a></li>
                    <li class="breadcrumb-item"><a href="javascript: void(0);">页面</a></li>
                    <li class="breadcrumb-item active">个人信息页</li>
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
                            <p class="text-muted mt-2 mb-0 pb-3">{{getinfoObject.name}}</p>
                          </div>
                        </div>

                        <div class="timeline-item">
                          <i class="mdi mdi-circle bg-primary-lighten text-primary timeline-icon"></i>
                          <div class="timeline-item-info">
                            <h5 class="mt-0 mb-1">学号</h5>
                            <p class="text-muted mt-2 mb-0 pb-3">{{ getinfoObject.studentID }}</p>
                          </div>
                        </div>

                        <div class="timeline-item">
                          <i class="mdi mdi-circle bg-info-lighten text-info timeline-icon"></i>
                          <div class="timeline-item-info">
                            <h5 class="mt-0 mb-1">身份证号</h5>
                            <p class="text-muted mt-2 mb-0 pb-2">{{ getinfoObject.idNumber }}</p>
                          </div>
                        </div>

                        <div class="timeline-item">
                          <i class="mdi mdi-circle bg-info-lighten text-primary timeline-icon"></i>
                          <div class="timeline-item-info">
                            <h5 class="mt-0 mb-1">邮箱</h5>
                            <p class="text-muted mt-2 mb-0 pb-2">{{ getinfoObject.email }}</p>
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
                              <input type="text" class="form-control" id="password" placeholder="输入密码">
                              <span class="form-text text-muted"><small>如果需要修改请<a
                                  href="javascript: void(0);">点击</a>这里</small></span>
                            </div>
                          </div> <!-- end col -->
                        </div> <!-- end row -->

                        <div class="text-end">
                          <span type="submit" class="btn btn-success mt-2"><i class="mdi mdi-content-save"></i>保存并提交
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
      <!-- content -->

      <!-- Footer Start -->
      <footer class="footer">
        <div class="container-fluid">
          <div class="row">
            <div class="col-md-6">
              <div class="text-md-end footer-links d-none d-md-block">
                <a href="javascript: void(0);">关于我们</a>
                <a href="javascript: void(0);">支持我们</a>
                <a href="javascript: void(0);">联系我们</a>
              </div>
            </div>
          </div>
        </div>
      </footer>
      <!-- end Footer -->

    </div>


    <!-- ============================================================== -->
    <!-- End Page content -->
    <!-- ============================================================== -->

  </div>
  <!-- END wrapper -->

  <!-- Theme Settings -->


  </body>


</template>

<script>
import topNav from "@/components/index/topNav";
import leftNav from "@/components/index/leftNav";

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
    this.getinfo()
    console.log(1)
  },
  data(){
    return {
      getinfoObject:{
        name: '',
        studentID: '',
        idNumber: '',
        maxScore: '',
        freeTime: '',
        email: '',
      },
      pwdchangeObject:{
        newPwd: ''
      }
    }
  },
  methods:{
    async getinfo(){
      await this.$axios.get('/student/query/details')
          .then((res) => {
            this.getinfoObject = res.data.data
            console.log(res.data)
          })
    },
    async changePwd(){

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