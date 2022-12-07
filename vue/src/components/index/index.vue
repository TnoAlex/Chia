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
                <el-dialog
                    v-model="enrollVisible"
                    :title="`第${item.name}次CSP报名`"
                    width="30%"
                >
                  <div>
                    <el-select style="margin-left: 0!important;" v-model="enrollInfo.freeOrOwn" class="m-2" placeholder="选择自费或者免费">
                      <el-option
                          v-for="item in enrollInfo.options"
                          :key="item.value"
                          :label="item.label"
                          :value="item.value"
                      />
                    </el-select>
                    <el-input v-model="enrollInfo.extra" placeholder="输入补充信息" />
                  </div>

                  <template #footer>
                              <span class="dialog-footer">
                                <el-button @click="enrollVisible = false">取消</el-button>
                                <el-button type="primary" @click="uploadEnrollInfo(index)">
                                  确认
                                </el-button>
                              </span>
                  </template>
                </el-dialog>
                <!-- project card -->
                <div class="card d-block">
                  <div class="card-body">
                    <div class="dropdown card-widgets" >
                      <a href="#" class="dropdown-toggle arrow-none" data-bs-toggle="dropdown" aria-expanded="false">
                        <i class="ri-more-fill"></i>
                      </a>
                      <div class="dropdown-menu dropdown-menu-end" >
                        <span @click ="editCspInfo(index)" v-if="userInfo.type" href="javascript:void(0);" class="dropdown-item"><i class="mdi mdi-pencil me-1"></i>编辑</span>
                        <span @click ="deleteCspInfo(index)" v-if="userInfo.type===1" href="javascript:void(0);" class="dropdown-item"><i class="mdi mdi-delete me-1"></i>删除</span>
                        <span @click="enroll" v-if="userInfo.type===0" href="javascript:void(0);" class="dropdown-item">
                          <i class="mdi mdi-star-box me-1"></i>报名
                        </span>
                        <span @click="enrollCancel" v-if="userInfo.type===0" href="javascript:void(0);" class="dropdown-item"><i class="mdi mdi-cancel me-1"></i>取消报名</span>
                      </div>

                    </div>
                    <!-- project title-->
                    <h4 class="mt-0">
                      <a href="" class="text-title">第{{item.name}}次CSP</a>
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
import cookies from "vue-cookies";
import axios from "axios";
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
        endTime:'',

      },
      enrollVisible:false,
      createCspVisible:false
      ,userInfo:{},
      cspInfoList:[],
      enrollInfo:{
        options:[{value: 0, label: '自费'},{value: 1, label: '免费'}],
        freeOrOwn:'',
        extra:''
      }
    }
  },
  name: "index.vue",
  mounted() {
    this.userInfo = cookies.get('userInfo')
    this.getCspInfo()
  },
  methods: {
    deleteCspInfo(){

    },
    editCspInfo()
    {

    },
    createCspClose(){
      this.createCspInfo.endTime=''
      this.createCspInfo.startTime=''
      this.createCspInfo.number=''
    },
    getProcession(startTime,endTime)
    {
      let startValue = (new Date(startTime)).valueOf()
      let endValue = (new Date(endTime)).valueOf()
      let nowValue = (new Date()).valueOf()
      let pro = (nowValue-startValue)/(endValue-startValue)
      let proStr = (Number(pro).toFixed(2)*100)
      if (Number(proStr)>=100)
      {
        return 100
      }
      else {
        return Math.ceil(Number(proStr))
      }
    },
    async uploadEnrollInfo(index){
      util.print(this.enrollInfo)
      let loading = util.loadingWait('报名中。。。')
      await util.delay(50)
      await axios({
        method:'POST',
        url:'pre/reg',
        headers:{'content-type': 'application/json;charset=UTF-8'},
        data:{
          cspId:this.cspInfoList[index].id,
          type:this.enrollInfo.freeOrOwn,
          extra: this.enrollInfo.extra
        }
      }).then(async (res) => {
        await util.delay(100)
        loading.close()
        util.messageBox('报名成功','success')
        this.enrollInfo.extra=''
        this.enrollInfo.freeOrOwn=''
        this.enrollVisible = false
        await this.getCspInfo()
        util.print(res)
      }).catch(async (err) => {
        await util.delay(100)
        loading.close()
        util.messageBox('报名失败','error')
        util.print(err)
      })
    },
    async getCspInfo(){
      let loading = util.loadingWait('拉取CSP信息中。。。')
      await util.delay(50)
      await this.$axios.get('csp_info/pre')
          .then(async (res) => {
            await util.delay(100)
            loading.close()
            this.cspInfoList = res.data.data
          })
          .catch(async () => {
            await util.delay(100)
            loading.close()
            util.messageBox('拉取CSP信息失败','error')
          })


    },

    async enroll(){
      this.enrollVisible = true
    },
    enrollCancel()
    {

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