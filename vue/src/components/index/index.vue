<template>
  <body>
  <div class="wrapper">
    <TopNav ref="childTopNav">
    </TopNav>
    <LeftNav  >
    </LeftNav>
    <div class="content-page">
      <div class="content">

        <!-- Start Content-->
        <div class="container-fluid">

          <!-- start page title -->
          <div class="row">
            <div class="col-12">
              <div class="page-title-box">
                <h4 class="page-title">历次CSP列表</h4>
              </div>
            </div>
          </div>
          <!-- end page title -->

          <div class="row mb-2">
            <div class="col-sm-4" v-if="util.userInfo.type===1">
              <router-link :to="{path:'/create_csp'}" class="btn btn-danger rounded-pill mb-3"><i class="mdi mdi-plus"></i> 发布CSP预报名</router-link>
            </div>

          </div>
          <!-- end row-->

          <div class="row">
            <div class="col-md-6 col-xxl-3" style="width: 300px;height: 300px;" v-for="(item,index) in cspInfoList" :key="index">
              <!-- project card -->
              <div class="card d-block">
                <div class="card-body">
                  <div class="dropdown card-widgets" v-if="util.userInfo.type===1">
                    <a href="#" class="dropdown-toggle arrow-none" data-bs-toggle="dropdown" aria-expanded="false">
                      <i class="ri-more-fill"></i>
                    </a>
                    <div class="dropdown-menu dropdown-menu-end" >
                      <a href="javascript:void(0);" class="dropdown-item"><i class="mdi mdi-pencil me-1"></i>编辑</a>
                      <a href="javascript:void(0);" class="dropdown-item"><i class="mdi mdi-delete me-1"></i>删除</a>
                    </div>
                  </div>
                  <!-- project title-->
                  <h4 class="mt-0">
                    <a href="apps-projects-details.html" class="text-title">第{{item.name}}次CSP</a>
                  </h4>
                  <div class="badge bg-success">已完成</div>

                  <!-- project detail-->
                  <p class="mb-1">
                                            <span class="pe-2 text-nowrap mb-2 d-inline-block">
                                                <i class="mdi mdi-human-queue text-muted"></i>
                                                <b>{{item.personNumber}}</b> 报名人数
                                            </span>
                    <span class="text-nowrap mb-2 d-inline-block">
                                                <i class="mdi mdi-timeline-clock-outline text-muted"></i>
                                                <b>{{ item.startTime }}开始时间</b>
                                            </span>
                    <span class="text-nowrap mb-2 d-inline-block">
                                                <i class="mdi mdi-timeline-clock-outline text-muted"></i>
                                                <b>{{item.endTime}}截止时间</b>
                                            </span>
                  </p>

                </div> <!-- end card-body-->
                <ul class="list-group list-group-flush">
                  <li class="list-group-item p-3">
                    <!-- project progress-->
                    <p class="mb-2 fw-bold">进度<span class="float-end">{{getProcession(item.startTime,item.endTime)}}%</span></p>
                    <el-progress :percentage="getProcession(item.startTime,item.endTime)" :show-text="false" />
                  </li>
                </ul>
              </div> <!-- end card-->
            </div> <!-- end col -->
          </div>

        </div> <!-- container -->

      </div> <!-- content -->

      <!-- Footer Start -->
      <!--      <footer class="footer">-->
      <!--        <div class="container-fluid">-->
      <!--          <div class="row">-->
      <!--            <div class="col-md-6">-->
      <!--              <script>document.write(new Date().getFullYear())</script> © Hyper - Coderthemes.com-->
      <!--            </div>-->
      <!--            <div class="col-md-6">-->
      <!--              <div class="text-md-end footer-links d-none d-md-block">-->
      <!--                <a href="javascript: void(0);">About</a>-->
      <!--                <a href="javascript: void(0);">Support</a>-->
      <!--                <a href="javascript: void(0);">Contact Us</a>-->
      <!--              </div>-->
      <!--            </div>-->
      <!--          </div>-->
      <!--        </div>-->
      <!--      </footer>-->
      <!-- end Footer -->

    </div>
  </div>
  </body>

</template>

<script>
import topNav from './topNav'
import leftNav from './leftNav'
import util from '../../utils/commonUtil'
export default {
  components: {
    TopNav: topNav,
    LeftNav: leftNav
  },
  data(){
    return{
      util,
      cspInfoList:[]
    }
  },
  name: "index.vue",
  created() {
    this.getUserInfo()
    this.getCspInfo()
    // this.getCspInfo2()
  },
  methods: {
    getProcession(startTime,endTime)
    {
      let startValue = (new Date(startTime)).valueOf()
      let endValue = (new Date(endTime)).valueOf()
      let nowValue = (new Date()).valueOf()
      let pro = (nowValue-startValue)/(endValue-startValue)
      let proStr = (Number(pro).toFixed(2)*100)
      return Number(proStr)
    },
    // getCspInfo2()
    // {
    //
    //
    // },

    async getCspInfo(){
      await this.$axios.get('csp_info/pre')
          .then((res)=>{
            console.log(res)
            this.cspInfoList = res.data.data
          })
          .catch(reason => {
            console.log(reason)
          })
    },
    async getUserInfo() {
      if(util.userInfo.type===1)
      {
        await this.$axios.get('/teacher/brief_info')
            .then((res)=>{
              console.log(res)
              util.userInfo.userName = res.data.msg
              this.$refs.childTopNav.getUserData(util.userInfo)
            })
            .catch(reason => {
              console.log(reason)
              if(reason.data.data===null)
              {
                util.messageBox(reason.data.msg,'error')
              }
              else {
                this.$router.push({path:'/error',
                  errorCode:reason.data.code,
                  errorMsg:reason.data.msg,
                  errorCaused:reason.data
                  })
              }
            })
      }
      else {
        await this.$axios.get('student/brief_info')
            .then((res)=>{
              console.log(res)
              util.userInfo.userName = res.data.data.name
              util.userInfo.studentNum = res.data.data.studentNum
              console.log(util.userInfo)
              this.$refs.childTopNav.getUserData(util.userInfo)
            })
            .catch(reason => {
              console.log(reason)
              if(reason.data.data===null)
              {
                util.messageBox(reason.data.msg,'error')
              }
              else {
                this.$router.push({path:'/error',
                  errorCode:reason.data.code,
                  errorMsg:reason.data.msg,
                  errorCaused:reason.data
                })
              }

            })
      }



    }
  }
}

</script>
<style scoped>
@import "bootstrap-daterangepicker/daterangepicker.css";
@import "../../assets/css/index_css/icons.min.css";
@import "../../assets/css/index_css/app-saas.min.css";
@import "../../assets/css/index_css/jquery-jvectormap-1.2.2.css";
</style>