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
                    <div style="margin-bottom: 10px" >
                       <span style="margin-bottom: 0!important; display: inline-block;justify-content: center;align-items: center ">
                          <span style="margin-right: 5px!important;">
                            导出选项
                          </span>
                       <el-select clearable v-model="nowExportSelect" class="m-2" placeholder="选择导入的CSP届数" style="margin-bottom: 0!important; margin-top: 0!important; margin-left: 0!important; display: inline-block">
                          <el-option
                              v-for="item in exportList"
                              :key="item.value"
                              :label="item.label"
                              :value="item.value"
                          /></el-select>

                      </span>
                      <el-button :icon="Search" circle @click="searchShow(1,5)" />
                      <el-button @click="exportExcel" type="success" size="large">导出</el-button>
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
                        <el-button @click="sendMessageToAllAbsence" type="primary"
                                   size="small">提醒学生参与正式报名</el-button>
                       </span>
                    </div>




                    <div class="row mb-2">
                      <div class="col-sm-5">
                        <span   class="btn btn-danger mb-2"><i class="mdi mdi-plus-circle me-2"></i> 导出选项展示表</span>
                      </div>
                      <el-table
                              fit
                              stripe="true"
                              id = "table_studentList"
                              v-loading="tableLoading"
                              border
                              ref="multipleTableRef"
                              :data="tableData"
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
                                     :total="tableDataNum"
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
        exportList:[{value: 0,label: '未有正式报名'},
                    {value: 1,label: '缺席考试名单'},
                    {value: 2,label: '正式报名错误'},
                    {value: 3,label: '正式报名正确'}
        ],
        currentExportUrl:'',
        nowExportSelect:'',
        searchInfo:{
          nameOrStudentNumber:'',
          grade:'',
          freeTime:'',
          scoreFilter:'',
          score:''
        },
        addInfoConfirmVisible:false,
        addInfoDialogVisible:false,
        confirmMessage:'',
        isConfirm:false,
        confirmType:1,
        currentPage:1,
        pageSize:5,
        tableLoading:false,
        selection:[],
        cspInfoList:[],
        cspNameSelectList:[],
        nowCspSelect:'',
        tableData:[],
        tableDataNum:0
      }
    },
    methods:{
      async exportExcel()
      {
        if(this.nowCspSelect===''||this.nowExportSelect==='')
        {
          util.messageBox('选择届次与导出选项才能导出','warning')
          return
        }
        if(this.currentExportUrl==='')
        {
          util.messageBox('请先点击搜索按钮，获取部分数据','warning')
          return
        }
        if(this.tableDataNum===0)
        {
          util.messageBox('暂无可导出的数据','warning')
          return
        }


        let loadint = util.loadingWait('获取导出数据中',"table_studentList")
        await util.delay(100)
        await axios({
          url:this.currentExportUrl,
          method:'GET'
        }).then(async (res)=>{
          await util.delay(100)
          loadint.close()
          util.messageBox('获取数据成功，即将导出','success')
          let exportArray = []
          for(let i = 0;i<res.data.data.length;i++)
          {
            exportArray.push({
              '学号':res.data.data[i]['studentNumber'],
              '姓名':res.data.data[i]['name'],
              '年级':res.data.data[i]['grade'],
              '最高分':res.data.data[i]['maxScore'],
              '免费次数':res.data.data[i]['freeTime']
            })
          }
          util.exportExcel(exportArray)
        }).catch(async (err)=>{
          await util.delay(100)
          loadint.close()
          util.messageBox('获取数据失败，导出失败','error')
        })
        this.currentExportUrl=''
      },
      async searchShow(pageNum,pageSize)
      {
        if(this.nowCspSelect===''||this.nowExportSelect==='')
        {
          util.messageBox('选择届次与导出选项才能展示','warning')
          return
        }
        let url = ''
        let cid = this.cspNameSelectList[this.nowCspSelect].cspId
        if(this.nowExportSelect===0)
        {
          url =`teacher/query/absent/official/${cid}/${pageNum}/${pageSize}`
          await this.getTableData(url)
          this.currentExportUrl =`teacher/query/absent/official/${cid}/${0}/${this.tableDataNum}`
        }else if(this.nowExportSelect===1)
        {
          url = `teacher/query/absent/exam/${cid}/${pageNum}/${pageSize}`
          await this.getTableData(url)
          this.currentExportUrl =`teacher/query/absent/exam/${cid}/${0}/${this.tableDataNum}`
        }else if(this.nowExportSelect===2)
        {
          url = `teacher/query/wrong/${cid}/${pageNum}/${pageSize}`
          await this.getTableData(url)
          this.currentExportUrl =`teacher/query/wrong/${cid}/${0}/${this.tableDataNum}`
        }else if(this.nowExportSelect===3)
        {
          url = `teacher/query/official/${cid}/${pageNum}/${pageSize}`
          await this.getTableData(url)
          this.currentExportUrl =`teacher/query/official/${cid}/${0}/${this.tableDataNum}`
        }
      },
      async getTableData(url)
      {
        let loading = util.loadingWait('获取数据中',"table_studentList")
        await util.delay(100)
        await axios({
          url:url,
          method:'GET',
        }).then(async (res)=>{
          await util.delay(100)
          loading.close()
          console.log(res)
          if(res.data.data===null)
          {
            util.messageBox(res.data.msg,'warning')
            this.tableDataNum=0
            return
          }
          if(res.data.data.length===0)
          {
            this.tableDataNum=0
            util.messageBox('暂无数据','success')
          }else
          {
            util.messageBox('获取数据成功','success')
            this.tableDataNum = res.data.data.totalSize
            this.tableData = res.data.data
          }
        }).catch(async (err)=>{
          console.log(err)
          await util.delay(100)
          loading.close()
          util.messageBox('获取数据失败','error')
        })
      },
    async handleSizeChange(size)
    {
      this.pageSize = size
      await this.searchShow(this.currentPage, this.pageSize)
    },
    async handleCurrentChange(index)
    {
      this.currentPage = index
      await this.searchShow(this.currentPage,this.pageSize)
    },
      handleSelectionChange(select)
      {
        this.selection = select
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
        let loading = util.loadingWait('上传信息中。。。')
        await util.delay(50)
        let upFlag = 1
        await axios({
          headers: {'content-type': 'application/json'},
          method: 'post',
          url: 'teacher/upload/regList',
          data: dataArray
        }).catch(() => {
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
        }).catch(async (err)=>{
          loading.close()
          await util.delay(100)
          util.messageBox('发送失败','error')
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
