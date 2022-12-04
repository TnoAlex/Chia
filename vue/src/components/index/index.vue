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
                <span @click="createCspVisible = true" class="btn btn-danger rounded-pill mb-3"><i class="mdi mdi-plus"></i> 发布CSP预报名</span>
                <el-dialog
                    v-model="createCspVisible"
                    title="CSP预报名信息"
                    width="30%"
                    @close="createCspClose"
                >
                  <div>
                    <label class="form-label">届次</label>
                    <el-input style="margin-bottom: 10px" v-model="createCspInfo.number" placeholder="输入届次" />
                    <div class="mb-3 position-relative" id="datepicker1">
                      <label class="form-label">开始时间</label>
                      <div class="block">
                        <el-date-picker
                            v-model="createCspInfo.startTime"
                            type="datetime"
                            placeholder="选择开始时间"
                            format="YYYY/MM/DD HH:mm:ss"
                        />
                      </div>
                    </div>

                    <div class="mb-3 position-relative" id="datepicker1">
                      <label class="form-label">截止时间</label>
                      <div class="block">
                        <el-date-picker
                            v-model="createCspInfo.endTime"
                            type="datetime"
                            placeholder="选择开始时间"
                            format="YYYY/MM/DD HH:mm:ss"
                        />
                      </div>
                    </div>
                  </div>
                  <template #footer>
                      <span class="dialog-footer">
                      <el-button @click="createCspVisible = false">关闭</el-button>
                      <el-button type="primary" @click="createCspVisible = false">
                      发布
                      </el-button>
                      </span>
                  </template>
                </el-dialog>
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
                    <div class="badge bg-success">{{getProcession(item.startTime,item.endTime)<100?'报名中':'已过期'}}</div>

                    <!-- project detail-->
                    <p class="mb-1">
                                            <span class="pe-2 text-nowrap mb-2 d-inline-block">
                                                <i class="mdi mdi-human-queue text-muted"></i>
                                                <b>{{item.personNumber}}</b> 人报名

                                            </span>

                      <label style="display: block" class="form-label">开始时间</label>
                      <span class="text-nowrap mb-2 d-inline-block">
                                                <i class="mdi mdi-timeline-clock-outline text-muted"></i>
                                                <b>{{ item.startTime }}</b>

                                            </span>
                      <label style="display: block" class="form-label">截止时间</label>
                      <span class="text-nowrap mb-2 d-inline-block">
                                                <i class="mdi mdi-timeline-clock-outline text-muted"></i>
                                                <b>{{item.endTime}}</b>

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
      createCspInfo:{
        number:'',
        startTime:'',
        endTime:''
      },
      createCspVisible:false
      ,
      cspInfoList:[]
    }
  },
  name: "index.vue",
  created() {
    this.getCspInfo()
    // this.getCspInfo2()
  },
  methods: {
    createCspClose(){
      this.createCspInfo.endTime=''
      this.createCspInfo.startTime=''
      this.createCspInfo.number=''
      console.log("关闭回调")
    },
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
  }
}
</script>
<style scoped>
@import "bootstrap-daterangepicker/daterangepicker.css";
@import "../../assets/css/index_css/icons.min.css";
@import "../../assets/css/index_css/app-saas.min.css";
@import "../../assets/css/index_css/jquery-jvectormap-1.2.2.css";
</style>