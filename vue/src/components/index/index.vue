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
              <div class="col-sm-4" v-show="userInfo.type===1">
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

                    <label class="form-label">大于某分数奖励次数</label>
                    <el-input style="margin-bottom: 10px" v-model="createCspInfo.gtScore"
                              placeholder="输入分数" />


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
                      <el-button type="primary" @click="createCsp">
                      发布
                      </el-button>
                      </span>
                  </template>
                </el-dialog>
                <el-dialog
                    append-to-body="true"
                    v-model="enrollVisible"
                    :title="`第${currentCspInfo.name}次CSP报名`"
                    width="30%"
                >
                  <div>
                    <el-select style="margin-left: 0!important;" v-model="enrollInfo.freeOrOwn" class="m-2" placeholder="选择自费或者免费">
                      <el-option
                          v-for="item1 in enrollInfo.options"
                          :key="item1.value"
                          :label="item1.label"
                          :value="item1.value"
                      />
                    </el-select>
                    <el-input v-model="enrollInfo.extra" placeholder="输入补充信息" />
                  </div>
                  <template #footer>
                              <span class="dialog-footer">
                                <el-button @click="enrollCancel(enrollVisible = false)">取消</el-button>
                                <el-button type="primary" @click="uploadEnrollInfo(currentCspInfo.id)">
                                  确认
                                </el-button>
                              </span>
                  </template>
                    </el-dialog>
                <el-dialog
                    v-model="editCspVisible"
                    title="CSP预报名信息修改"
                    width="30%"
                    @close="editCspClose"
                  >
                  <div>
                    <label class="form-label">届次</label>
                    <el-input style="margin-bottom: 10px" v-model="editCspInfo.number" placeholder="输入届次" />

                    <label class="form-label">大于某分数奖励次数</label>
                    <el-input style="margin-bottom: 10px" v-model="editCspInfo.gtScore"
                              placeholder="输入分数" />

                    <div class="mb-3 position-relative" id="datepicker1">
                      <label class="form-label">开始时间</label>
                      <div class="block">
                        <el-date-picker
                            v-model="editCspInfo.startTime"
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
                            v-model="editCspInfo.endTime"
                            type="datetime"
                            placeholder="选择开始时间"
                            format="YYYY/MM/DD HH:mm:ss"
                        />
                      </div>
                    </div>
                  </div>
                  <template #footer>
                      <span class="dialog-footer">
                      <el-button @click="editCspVisible = false">关闭</el-button>
                      <el-button type="primary" @click="editCsp">
                      更新
                      </el-button>
                      </span>
                  </template>
                    </el-dialog>
              </div>
            </div>
            <!-- end row-->
            <div class="row">
              <div class="col-md-6 col-xxl-3" style="width: 300px;height: 300px; margin-bottom: 80px;" v-for="(item,index) in cspInfoList" :key="index">
                <div class="card d-block">
                  <div class="card-body">
                    <div class="dropdown card-widgets" >
                      <a href="#" class="dropdown-toggle arrow-none" data-bs-toggle="dropdown" aria-expanded="false">
                        <i class="ri-more-fill"></i>
                      </a>
                      <div class="dropdown-menu dropdown-menu-end" >
                        <span @click ="editCspOpen(index)" v-show="userInfo.type===1" href="javascript:void(0);" class="dropdown-item"><i class="mdi mdi-pencil me-1"></i>编辑</span>
                        <span @click ="deleteCspInfo(item.id)" v-show="userInfo.type===1" href="javascript:void(0);" class="dropdown-item"><i class="mdi mdi-delete me-1"></i>删除</span>
                        <span @click="enroll(index)" v-show="userInfo.type===0" href="javascript:void(0);" class="dropdown-item">
                          <i class="mdi mdi-star-box me-1"></i>报名
                        </span>
                        <span @click="deleteEnroll(item.id)" v-show="userInfo.type===0" href="javascript:void(0);" class="dropdown-item"><i class="mdi mdi-cancel me-1"></i>取消报名</span>
                      </div>

                    </div>
                    <!-- project title-->
                    <h4 class="mt-0">
                      <a href="" class="text-title">第{{item.name}}次CSP</a>
                    </h4>
                    <div class="badge bg-success">{{this.getProcessionStr(item.startTime,item.endTime)}}</div>



                    <!-- project detail-->
                    <p class="mb-1">
                                            <span class="pe-2 text-nowrap mb-2 d-inline-block">
                                                <i class="mdi mdi-human-queue text-muted"></i>
                                                <b>{{item.personNumber}}</b> 人报名

                                            </span>

                      <label style="display: block" class="form-label">开始时间</label>
                      <span class="text-nowrap mb-2 d-inline-block">
                                                <i class="mdi mdi-timeline-clock-outline text-muted"></i>
                                                <b>&nbsp;{{ item.startTime }}</b>

                                            </span>
                      <label style="display: block" class="form-label">截止时间</label>
                      <span class="text-nowrap mb-2 d-inline-block">
                                                <i class="mdi mdi-timeline-clock-outline text-muted"></i>
                                                <b>&nbsp;{{item.endTime}}</b>
                      </span>
                      <label style="display: block" class="form-label">当分数大于等于{{item.freeThreshold}}时可以追加免费次数</label>
                    </p>

                  </div> <!-- end card-body-->
                  <ul class="list-group list-group-flush">
                    <li class="list-group-item p-3">
                      <!-- project progress-->
                      <p class="mb-2 fw-bold">进度<span class="float-end">{{getProcession(item.startTime,item.endTime)}}%</span></p>
                      <el-progress :percentage="getProcession(item.startTime,item.endTime)" :show-text="false" />
                    </li>
                  </ul> <!-- end card-->
                </div>
              </div>
            </div>
          </div>
        </div>
      </div>
  </div>
  </body>
</template>
<script>
import topNav from './topNav'
import leftNav from './leftNav'
import util from '../../utils/commonUtil'

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
        gtScore:''

      },
      enrollVisible:false,
      createCspVisible:false,
      userInfo:{},
      cspInfoList:[],
      currentCspInfo:{},
      editCspVisible:false,
      editCspInfo:{
        number:'',
        startTime:'',
        endTime:'',
        id:'',
        gtScore:''
      },
      enrollInfo:{
        options:[{value: 0, label: '自费'},{value: 1, label: '免费'}],
        freeOrOwn:'',
        extra: ''
      }
    }
  },
  name: "index.vue",
  mounted() {
    window.addEventListener('beforeunload', e => util.destroyCookie(e))
    this.userInfo = this.$cookies.get('userInfo')
    this.getCspInfo()
  },
  unmounted() {
    window.removeEventListener('beforeunload', e => util.destroyCookie(e))
  },
  methods: {

    async createCsp() {
      if (this.createCspInfo.endTime === '' || this.createCspInfo.startTime === ''
          || this.createCspInfo.number === '' || this.createCspInfo.gtScore === '') {
        util.messageBox('请将信息填写完整', 'warning')
        return
      }
      let loading = util.loadingWait('正在发布中...')
      await util.delay(100)
      axios({
        url: 'teacher/publish/csp',
        method: 'POST',
        headers: {'content-type': 'application/json'},
        data: {
          name: this.createCspInfo.number,
          startTime: util.timeStampToTime(new Date(this.createCspInfo.startTime).getTime()),
          endTime: util.timeStampToTime(new Date(this.createCspInfo.endTime).getTime()),
          freeThreshold: this.createCspInfo.gtScore
        }
      }).then(async () => {
        await util.delay(100)
        loading.close()
        util.messageBox('发布成功', 'success')
        await this.getCspInfo()
        this.createCspVisible = false
      }).catch(async()=>{
        await util.delay(100)
        loading.close()
        util.messageBox('发布失败','error')

      })
    },
    async deleteCspInfo(pid){
      let loading = util.loadingWait('删除中...')
      await util.delay(100)
      axios({
        url:`teacher/publish/delete/${pid}`,
        method:'POST',
        headers:{'content-type': 'application/json;charset=UTF-8'},
      }).then(async (res)=>{
        await util.delay(100)
        loading.close()
        if(res.data.code===403)
        {
          util.messageBox(res.data.msg,'warning')
        }
        else
        {
          util.messageBox('删除成功','success')
          await this.getCspInfo()
        }

      }).catch(async ()=>{
        await util.delay(100)
        loading.close()
        util.messageBox('删除失败','error')
      })
    },
   async editCsp()
    {
      if(this.editCspInfo.endTime===''||this.editCspInfo.startTime===''
          ||this.editCspInfo.number===''||this.editCspInfo.gtScore==='')
      {
        util.messageBox('请将信息填写完整','warning')
        return
      }
      let loading = util.loadingWait('修改中...')
      await util.delay(100)
      axios({
        method: 'POST',
        url: `teacher/publish/modify`,
        headers: {'content-type': 'application/json'},
        data: {
          id: this.editCspInfo.id,
          name: this.editCspInfo.number,
          startTime: util.timeStampToTime(new Date(this.editCspInfo.startTime).getTime()),
          endTime: util.timeStampToTime(new Date(this.editCspInfo.endTime).getTime()),
          freeThreshold: this.editCspInfo.gtScore
        }
      }).then(async () => {
        await util.delay(100)
        loading.close()
        util.messageBox('修改成功', 'success')
        await this.getCspInfo()
        this.editCspVisible = false
      }).catch(async ()=>{
        await util.delay(100)
        loading.close()
        util.messageBox('修改失败','error')
      })

    },
    editCspOpen(index)
    {
      this.editCspInfo.id = this.cspInfoList[index].id
      this.editCspInfo.endTime = this.cspInfoList[index].endTime
      this.editCspInfo.startTime = this.cspInfoList[index].startTime
      this.editCspInfo.gtScore = this.cspInfoList[index].freeThreshold
      this.editCspInfo.number = this.cspInfoList[index].name
      this.editCspVisible = true
    },
    editCspClose()
    {
      this.editCspInfo.endTime=''
      this.editCspInfo.startTime=''
      this.editCspInfo.number=''
      this.editCspInfo.gtScore=''

    },
    createCspClose(){
      this.createCspInfo.endTime=''
      this.createCspInfo.startTime=''
      this.createCspInfo.number=''
      this.createCspInfo.gtScore=''
    },
    getProcessionStr(startTime,endTime)
    {
      let res = this.getProcessionNum(startTime,endTime)
      if(res<0)
      {
        return '未开始'
      }
      else if(res>100) {
        return '已结束'
      }
      else{
        return '报名中'
      }
    },
    getProcession(startTime,endTime)
    {
      let res= this.getProcessionNum(startTime,endTime)
      if (res<0)
      {
        return 0
      }else if(res>100)
      {
        return 100
      }else
      {
        return res
      }
    },
    getProcessionNum(startTime,endTime)
    {
      let startValue = (new Date(startTime)).valueOf()
      let endValue = (new Date(endTime)).valueOf()
      let nowValue = (new Date()).valueOf()
      let pro = (nowValue-startValue)/(endValue-startValue)
      let proStr = (Number(pro).toFixed(2)*100)
      if (Number(proStr)>=100)
      {
        return 101
      }
      else if(Number(proStr)<0)
      {
        return -1
      }
      else {
        return Math.ceil(Number(proStr))
      }
    },
    async uploadEnrollInfo(pid){
      if(this.userInfo.freeOrOwn===0 && this.enrollInfo.freeOrOwn===1)
      {
        util.messageBox('您的免费次数不足只能选择自费')
        return
      }
      let loading = util.loadingWait('报名中...')
      await util.delay(50)
      await axios({
        method:'POST',
        url:'pre/reg',
        headers:{'content-type': 'application/json;charset=UTF-8'},
        data:{
          cspId:pid,
          type:this.enrollInfo.freeOrOwn,
          extra: this.enrollInfo.extra
        }
      }).then(async (res) => {
        await util.delay(100)
        loading.close()
        if(res.data.code===403)
        {
          util.messageBox('您已经报名成功，请勿多次报名，','warning')
        } else {
          util.messageBox('报名成功', 'success')
          this.enrollInfo.extra = ''
          this.enrollInfo.freeOrOwn = ''
          this.enrollVisible = false
          await this.getCspInfo()
        }
        this.enrollInfo.extra = ''
        this.enrollInfo.freeOrOwn = ''
      }).catch(async () => {
        await util.delay(100)
        loading.close()
        util.messageBox('报名失败', 'error')
      })
    },
    async getCspInfo(){
      let loading = util.loadingWait('拉取CSP信息中...')
      await util.delay(200)
      await this.$axios.get('csp_info/pre')
          .then(async (res) => {
            await util.delay(100)
            loading.close()
            util.messageBox('CSP信息拉取成功','success')
            this.cspInfoList = res.data.data
          })
          .catch(async () => {
            await util.delay(100)
            loading.close()
            util.messageBox('拉取CSP信息失败','error')
          })
    },
    enroll(index){
      this.enrollVisible = true
      this.currentCspInfo =this.cspInfoList[index]
    },
    enrollCancel()
    {
      this.enrollInfo.extra=''
      this.enrollInfo.freeOrOwn=''
    },
    async deleteEnroll(pid)
    {
      let loading = util.loadingWait('取消报名中。。。')
      await util.delay(50)
      axios({
        method:'POST',
        headers:{'content-type':'application/x-www-form-urlencoded;charset=UTF-8'},
        url: `pre/reg/cancel/${pid}`
      }).then(async (res)=>{
        await util.delay(100)
        loading.close()
        if(res.data.code===403)
        {
          util.messageBox('您没有报名，请勿随意操作','warning')
        }
        else
        {
          util.messageBox('取消报名成功','success')
          await this.getCspInfo()
        }


      }).catch(async ()=>{
        await util.delay(100)
        loading.close()
        util.messageBox('取消报名失败','error')
      })


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
