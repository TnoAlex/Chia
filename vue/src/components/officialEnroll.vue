<template>
    <body>
    <div class="wrapper">
      <TopNav>

      </TopNav>
      <LeftNav >

      </LeftNav>
      <div class="content-page">
        <div class="content">
          <div class="container-fluid">
            <div class="row">

              <div class="col-12">
                <div class="page-title-box">
                  <div class="page-title-right">
                    <ol class="breadcrumb m-0">

                    </ol>
                  </div>

                  <h4 class="page-title">学生报名信息管理</h4>

                </div>
              </div>
            </div>
            <div class="row">
              <div class="col-12">
                <div class="card">
                  <div class="card-body">


                    <div style="margin-bottom: 10px" >
                       <span style="margin-bottom: 0!important; display: inline-block;justify-content: center;align-items: center ">
                          <span style="margin-right: 5px!important;">
                            届数选择
                          </span>
                       <el-select clearable v-model="nowCspSelect" class="m-2" placeholder="选择导入的CSP届数" style="margin-bottom: 0!important; margin-top: 0!important; margin-left: 0!important; display: inline-block">
                          <el-option
                              v-for="item in cspNameSelectList"
                              :key="item.value"
                              :label="item.label"
                              :value="item.value"
                          /></el-select>

                      </span>
                    </div>
                    <div style="margin-bottom: 10px">
                      <span style="margin-top: 0!important; display: inline-block;justify-content: center;align-items: center;">
                        <el-upload ref="upload" style="display: inline-block;margin-right: 20px"
                                     action="##"
                                     :auto-upload="false"
                                     :show-file-list="false"
                                     :on-change="handleExcel"
                          >
                            <el-button type="primary" size="small">导入正式报名表</el-button>

                          </el-upload >

                          <el-upload ref="uploadScore" style="display: inline-block;margin-right: 20px"
                                     action="##"
                                     :auto-upload="false"
                                     :show-file-list="false"
                                     :on-change="handleScoreExcel"
                          >
                            <el-button type="primary" size="small">成绩单上传</el-button>

                          </el-upload >

                          <el-button type="primary" @click="getAbsenceTable(
                            1,5)"
                                     size="small">查看未正式报名学生</el-button>
                        <el-button @click="sendMessageToAllAbsence" type="primary"
                                   size="small">提醒学生参与正式报名</el-button>
                       </span>
                    </div>




                    <div class="row mb-2">
                      <div class="col-sm-5">
                        <span   class="btn btn-danger mb-2"><i class="mdi mdi-plus-circle me-2"></i> 未正式报名表</span>
                        <el-dialog
                            v-model="addInfoDialogVisible"
                            title="学生信息添加"
                            width="30%"
                            @close="addInputInfoClose"
                        >
                          <div>
                            <label class="form-label">姓名</label>
                            <el-input style="margin-bottom: 10px" v-model="addInputInfo.name" placeholder="输入姓名" />
                            <label class="form-label">学号</label>
                            <el-input style="margin-bottom: 10px" v-model="addInputInfo.studentNum" placeholder="输入学号" />
                            <label class="form-label">年级</label>
                            <el-input style="margin-bottom: 10px" v-model="addInputInfo.grade" placeholder="输入年级如：2020" />
                            <label class="form-label">身份证号</label>
                            <el-input style="margin-bottom: 10px" v-model="addInputInfo.studentId" placeholder="身份证号（18位）" />
                          </div>
                          <template #footer>
                        <span class="dialog-footer">
                        <el-button @click="addInfoDialogVisible = false">关闭</el-button>
                        <el-button type="primary" @click="addInput">
                        确认
                        </el-button>

                          <el-button text @click="addInfoConfirmVisible = true" v-show="false">
                            Click to open the Dialog
                          </el-button>
                           <el-dialog v-model="addInfoConfirmVisible" title="Warning" width="30%" center>
                            <span>
                              {{confirmMessage}}
                            </span>
                            <template #footer >
                              <span class="dialog-footer" >
                                <el-button @click="addInfoConfirmVisible = false">取消</el-button>
                                <el-button type="primary" @click="addInfoConfirm">
                                  确认
                                </el-button>
                              </span>
                            </template>
                          </el-dialog>





                        </span>
                          </template>
                        </el-dialog>
                      </div>
                      <el-table
                              fit
                              stripe="true"
                              id = "table_studentList"
                              v-loading="tableLoading"
                              border
                              ref="multipleTableRef"
                              :data="absenceTableList"
                              style="width: 100%"
                              @selection-change="handleSelectionChange"
                    >
                      <el-table-column type="selection" width="55px" />
                      <el-table-column property="studentNumber" label="学号" width="200px" show-overflow-tooltip />
                      <el-table-column property="name" label="姓名" width="150px" show-overflow-tooltip />
                      <el-table-column property="grade" label="年级" width="80px" show-overflow-tooltip />
                      <el-table-column property="maxScore" label="最高分" width="80px"  show-overflow-tooltip/>

                      <el-table-column property="freeTime" label="免费次数" show-overflow-tooltip />
                    </el-table>
                    <div style="float: right">
                      <div style="float: right">
                      <el-pagination style="margin-top: 20px;"
                                     v-model:current-page="currentPage"
                                     v-model:page-size="pageSize"
                                     :page-sizes="[5, 10, 15, 20]"
                                     :small="small"
                                     :disabled="disabled"
                                     :background="background"
                                     layout="total, sizes, prev, pager, next, jumper,"
                                     :total="absenceTotalNum"
                                     @size-change="handleSizeChange"
                                     @current-change="handleCurrentChange"
                      />
                    </div>
                    </div>


                    </div>

                  </div> <!-- end card-body-->
                </div> <!-- end card-->
              </div> <!-- end col -->
            </div>
          </div>
        </div>
      </div>
    </div>
    </body>
  </template>

  <script>
  import topNav from "@/components/index/topNav";
  import leftNav from "@/components/index/leftNav";
  import {Delete, Edit, Search} from '@element-plus/icons-vue';
  import util from '@/utils/commonUtil'
  import axios from "axios";

  export default
  {
    name: "studentList.vue",
    components:{
      TopNav:topNav,
      LeftNav:leftNav
    },
    data(){
      return{
        Search,
        Delete,
        Edit,
        searchInfo:{
          nameOrStudentNumber:'',
          grade:'',
          freeTime:'',
          scoreFilter:'',
          score:''
        },
        addInfoConfirmVisible:false,
        addInfoDialogVisible:false,
        confirmMessage:''
        ,addInputInfo:{
          studentNum:'',
          studentId: '',
          name:'',
          grade:''
        },
        isConfirm:false,
        confirmType:1,
        currentPage:1,
        pageSize:5,
        tableLoading:false,
        selection:[],
        cspInfoList:[],
        cspNameSelectList:[],
        nowCspSelect:'',
        absenceTableList:[],
        absenceTotalNum:0
      }
    },
    methods:{
      handleSelectionChange(select)
      {
        this.selection = select
      },
      async handleSizeChange(size)
      {
        this.pageSize = size
        await this.getAbsenceTable(this.currentPage,size)

      },
       async handleCurrentChange(index)
      {
        this.currentPage = index
        await this.absenceTableList(index,this.pageSize)
      },
      async handleExcel(ev)
      {
        if(this.nowCspSelect==='')
        {
          util.messageBox('请先选择届次','warning')
          return
        }

        let xlsx = require("xlsx")
        let file = ev.raw
        if(!file) return
        let loadingInstance = util.loadingWait('解析表格中......')
        // 异步提示
        await util.delay(100)
        let dataBinary = await util.readFile(file)
        let workBook = xlsx.read(dataBinary, { type: 'binary'})
        let workSheet = workBook.Sheets[workBook.SheetNames[0]]
        let data = xlsx.utils.sheet_to_json(workSheet,{range:2})
        let keyArray = Object.keys(data[0])
        let studentIdNumberIndex =0
        let enrollTypeIndex = 0
        let enrollStatusIndex = 0
        for(let i = 0 ;i<keyArray.length;i++)
        {
          if(keyArray[i]==='学号')
          {
            studentIdNumberIndex = i
          }else if(keyArray[i]==='团报单位')
          {
            enrollTypeIndex = i
          }else if(keyArray[i]==='报名状态')
          {
            enrollStatusIndex = i
          }
        }
        console.log(studentIdNumberIndex,enrollTypeIndex,enrollStatusIndex)
        let dataArray  = []
        for(let i = 0 ;i<data.length;i++)
        {
          let valueArray = Object.values(data[i])
          if(valueArray[enrollStatusIndex]!=='未提交报名')
          {
            let enrollTypeZh = valueArray[enrollTypeIndex]
            let enrollType = 0
            if(enrollTypeZh==='南京理工大学')
            {
              //免费
              enrollType = 1
            }
            else
            {
              enrollType = 0
            }
            dataArray.push({
            studentIdNumber:valueArray[studentIdNumberIndex].toString(),
            cspId:this.cspNameSelectList[this.nowCspSelect].cspId,
            type:enrollType
            })
          }
        }

        await util.delay(100)
        loadingInstance.close()
        util.messageBox('解析成功','success')
        this.$refs.upload.clearFiles()
        await this.uploadExcelInfo(dataArray)
      },
      async handleScoreExcel(ev)
      {
        if(this.nowCspSelect==='')
        {
          util.messageBox('请先选择届次','warning')
          return
        }
        let xlsx = require("xlsx")
        let file = ev.raw
        if(!file) return
        let loadingInstance = util.loadingWait('解析表格中......')
        // 异步提示
        await util.delay(100)
        let dataBinary = await util.readFile(file)
        let workBook = xlsx.read(dataBinary, { type: 'binary'})
        let workSheet = workBook.Sheets[workBook.SheetNames[0]]
        let data = xlsx.utils.sheet_to_json(workSheet,{range:2})
        let dataArray = []
        let keyArray = Object.keys(data[0])
        let studentIdNumberIndex =0
        let scoreIndex = 0
        for(let i = 0 ;i<keyArray.length;i++)
        {
          if(keyArray[i]==='学号')
          {
            studentIdNumberIndex = i
          }else if (keyArray[i]==='认证总分')
          {
            scoreIndex = i
          }
        }
        for(let i = 0 ;i<data.length;i++)
        {
          let valueArray = Object.values(data[i])
          dataArray.push({
          studentIdNumber:valueArray[studentIdNumberIndex].toString(),
          cspId:this.cspNameSelectList[this.nowCspSelect].cspId,
          score:valueArray[scoreIndex]
          })
        }

        await util.delay(100)
        loadingInstance.close()
        util.messageBox('解析成功','success')
        this.$refs.uploadScore.clearFiles()
        console.log(dataArray)
        await this.uploadScoreExcelInfo(dataArray)
      },
      async uploadScoreExcelInfo(dataArray)
      {
        let loading = util.loadingWait('上传中。。。','table_studentList')
        await util.delay(100)
        await axios({
          method:'POST',
          headers:{'content-type':'application/json'},
          url:`teacher/upload/transcripts`,
          data:dataArray
        }).then(async(res)=>{
          await util.delay(100)
          loading.close()
          util.messageBox('上传成功','success')
        }).catch(async (err)=>{
          await util.delay(100)
          loading.close()
          util.messageBox('成绩更新失败','error')
        })
      },
      async uploadExcelInfo(dataArray)
      {
        console.log(dataArray)
        let loading = util.loadingWait('上传信息中。。。')
        await util.delay(50)
        let upFlag = 1
        await axios({
          headers: {'content-type':'application/json'},
          method:'post',
          url:'teacher/upload/regList',
          data:dataArray
        }).then((res)=>{
          console.log(res)
        }).catch((err)=>{
          console.log(err)
          upFlag = 0
        })
        await util.delay(100)
        loading.close()
        if(upFlag===0)
        {
          util.messageBox('上传正式报名表格失败','error')
          return 0
        }
        else
        {
          util.messageBox('上传正式报名表格成功','success')
          return 1
        }
      },
      async sendMessageToAllAbsence()
      {
        if(this.nowCspSelect==='')
        {
          util.messageBox('请先选择届次','warning')
          return
        }
        console.log(this.cspNameSelectList[this.nowCspSelect].id)
        let loading = util.loadingWait('发送中')
        await util.delay(100)
        await axios({
          method:'POST',
          headers:{'content-type':'application/x-www-form-urlencoded;charset=UTF-8'},
          url:`teacher/notice/official/${this.cspNameSelectList[this.nowCspSelect].cspId}/`
        }).then(async (res)=>{
          loading.close()
          await util.delay(100)
          util.messageBox('发送成功','success')
          console.log(res)
        }).catch(async (err)=>{
          loading.close()
          await util.delay(100)
          util.messageBox('发送失败','error')
          console.log(err)
        })

      },
      async getAbsenceTable(pageNum,pageSize)
      {
        if(this.nowCspSelect==='')
        {
          util.messageBox('请先选择届次','warning')
          return
        }
        let loading = util.loadingWait('获取中','table_studentList')
        await util.delay(100)
        axios({
          method:'GET',
          headers:{'content-type':'application/x-www-form-urlencoded;charset=UTF-8'},
          url:`teacher/query/absent/official/${this.cspNameSelectList[this.nowCspSelect].cspId}/${pageNum}/${pageSize}`,
        }).then(async (res)=>{
          loading.close()
          await util.delay(100)
          util.messageBox('查询成功','success')
          if(res.data.data.length>0)
          {
            this.absenceTableList = res.data.data
            this.absenceTotalNum = res.data.data[0].totalSize

          }
        }).catch(async (err)=>{
          loading.close()
          await util.delay(100)
          util.messageBox('查询失败','error')
        })
      },
      async getCspInfo(){
      let loading = util.loadingWait('拉取CSP届次中。。。')
      await util.delay(200)
      await this.$axios.get('csp_info/pre')
          .then(async (res) => {
            await util.delay(100)
            loading.close()
            util.messageBox('CSP届次拉取成功','success')
            if(res.data.data.length>0)
            {

              for(let i = 0 ;i<res.data.data.length;i++)
              {
                this.cspNameSelectList.push({
                  value:i,
                  label:res.data.data[i].name,
                  cspId:res.data.data[i].id
                })
              }
              this.cspInfoList = res.data.data
            }
            console.log(this.cspInfoList)
          })
          .catch(async () => {
            await util.delay(100)
            loading.close()
            util.messageBox('拉取CSP届次失败', 'error')
          })
      },
    },
    created() {
      this.getCspInfo()
    },
    mounted() {
      window.addEventListener('beforeunload', e => util.destroyCookie(e))
    },
    unmounted() {
      window.removeEventListener('beforeunload', e => util.destroyCookie(e))
    }
  }
  </script>
  <style scoped>
  @import "../assets/css/index_css/icons.min.css";
  @import "../assets/css/index_css/app-saas.min.css";
  /*@import "../../assets/css/index_css/jquery-jvectormap-1.2.2.css";*/
  </style>
