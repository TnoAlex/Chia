<template>
  <body>
  <div class="wrapper">
    <TopNav>

    </TopNav>
    <LeftNav >

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

                <h4 class="page-title">学生报名信息管理</h4>

              </div>
            </div>
          </div>
          <!-- end page title -->

          <div class="row">
            <div class="col-12">
              <div class="card">
                <div class="card-body">
                  <div style="margin-bottom: 20px;">


                    <el-select-v2 style="margin-right: 20px;"
                                  v-model="value"
                                  :options="options"
                                  clearable
                                  placeholder="请选择..."

                    />
                    <el-select-v2 style="margin-right: 20px;"
                                  v-model="value"
                                  :options="options"
                                  placeholder="请选择..."
                                  clearable
                    />
                    <el-select-v2 style="margin-right: 20px;"
                                  v-model="value"
                                  :options="options"
                                  placeholder="请选择..."
                                  clearable
                    />
                    <el-select-v2 style="margin-right: 20px;"
                                  v-model="value"
                                  :options="options"
                                  placeholder="请选择..."
                                  clearable
                    />
                  </div>
                  <div style="margin-bottom: 20px;">
                    <el-select-v2 style="margin-right: 20px;"
                                  v-model="value"
                                  :options="options"
                                  placeholder="请选择..."
                                  clearable
                    />
                    <el-select-v2 style="margin-right: 20px;"
                                  v-model="value"
                                  :options="options"
                                  placeholder="请选择..."
                                  clearable
                    />
                    <el-select-v2 style="margin-right: 20px;"
                                  v-model="value"
                                  :options="options"
                                  placeholder="请选择..."
                                  clearable
                    />
                    <el-select-v2 style="margin-right: 20px;"
                                  v-model="value"
                                  :options="options"
                                  placeholder="请选择..."
                                  clearable
                    />
                  </div>
                  <div style="margin-bottom: 20px">
                    <div style="display: inline-block; text-align: center; align-items: center">
                      <el-button :icon="Search" circle /> <span style="line-height: 18px;height: 18px">点击搜索...</span>
                    </div>
                    <div style="display: inline-block; margin-left: 20px;text-align: center; align-items: center" >
                      <el-button type="danger" :icon="Delete" circle /> <span style="line-height: 18px;height: 18px">重置搜索</span>
                    </div>

                  </div>
                  <div class="row mb-2">

                    <div class="col-sm-5">
                      <span @click="addInfoDialogVisible = true"  class="btn btn-danger mb-2"><i class="mdi mdi-plus-circle me-2"></i> 添加学生信息</span>
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
                        </div>
                        <template #footer>
                      <span class="dialog-footer">
                      <el-button @click="addInfoDialogVisible = false">关闭</el-button>
                      <el-button type="primary" @click="addInfoDialogVisible = false">
                      确认
                      </el-button>
                      </span>
                        </template>
                      </el-dialog>
                    </div>
                    <div class="col-sm-7">
                      <div class="text-sm-end">
                        <a href="static/学生信息导入模板.xlsx" download="学生信息导入模板">
                          <el-button style="margin-right: 20px" type="primary" size="large">导入模板下载</el-button>
                        </a>
                        <el-upload ref="upload" style="display: inline-block;margin-right: 20px"
                                   action="##"
                                   :auto-upload="false"
                                   :show-file-list="false"
                                   :on-change="handleExcel"
                        >
                          <el-button type="primary" size="large">导入</el-button>

                        </el-upload >
                        <el-button type="success" size="large">导出</el-button>

                      </div>

                    </div><!-- end col-->
                  </div>

                  <el-table border
                            ref="multipleTableRef"
                            :data="tableData.slice((currentPage-1)*pageSize,currentPage*pageSize)"
                            style="width: 100%"
                            @selection-change="handleSelectionChange"
                  >
                    <el-table-column type="selection" width="55" />
                    <el-table-column property="studentNumber" label="学号" width="120" />
                    <el-table-column property="name" label="姓名" width="120" />
                    <el-table-column property="enrollmentTime" label="入学日期" show-overflow-tooltip />
                    <el-table-column  label="操作" width="120px">
                      <template #default="scope" >
                        <el-button-group class="ml-4">
                          <el-button @click="handleEdit(scope.$index, scope.row)" type="primary" :icon="Edit" />
                          <el-button @click="handleEdit(scope.$index, scope.row)" type="primary" :icon="Delete" />
                        </el-button-group>
                      </template>
                    </el-table-column>
                  </el-table>
                  <div style="float: right">
                    <el-pagination style="margin-top: 20px;"
                                   v-model:current-page="currentPage"
                                   v-model:page-size="pageSize"
                                   :page-sizes="[5, 10, 15, 20]"
                                   :small="small"
                                   :disabled="disabled"
                                   :background="background"
                                   layout="total, sizes, prev, pager, next, jumper,"
                                   :total="totalDataNum"
                                   @size-change="handleSizeChange"
                                   @current-change="handleCurrentChange"
                    />
                  </div>
                </div> <!-- end card-body-->
              </div> <!-- end card-->
            </div> <!-- end col -->
          </div>
          <!-- end row -->

        </div> <!-- container -->

      </div> <!-- content -->


    </div>
  </div>
  </body>
</template>

<script>
import topNav from "@/components/index/topNav";
import leftNav from "@/components/index/leftNav";
import {Search,Delete,Edit } from '@element-plus/icons-vue';
import util from '@/utils/commonUtil'
import axios from "axios";
export default {
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
      addInfoDialogVisible:false
      ,addInputInfo:{
        studentNum:'',
        name:'',
      }
      ,
      value:'请选择...',
      options:[{value:1,label:'a'},{value:2,label:'b'},{value:3,label:'c'}],
      tableData:[],
      totalDataNum :0,
      currentPage:1,
      pageSize:5
    }
  },
  methods:{
    addInputInfoClose(){
      this.addInputInfo.name=''
      this.addInputInfo.studentNum=''
    },
    handleEdit()
    {
    },
    getInitTableData()
    {
      for (let i = 0; i < 50; i++) {
        let temp = {
          studentId:((i+1)+2).toString(),
          name: 'Tom',
          address: '南京理工大学',
        }
        this.tableData =  this.tableData.concat(temp)
      }
      this.totalDataNum = this.tableData.length
    },
    handleSizeChange(size)
    {
      this.pageSize = size
      this.currentPage = 1
    },
    handleCurrentChange(index)
    {
      this.currentPage = index
    },
    async handleExcel(ev)
    {
      let xlsx = require("xlsx")
      let file = ev.raw
      if(!file) return
      let loadingInstance = util.loadingWait('解析表格中......')
      // 异步提示
      await util.delay(100)
      let dataBinary = await util.readFile(file)
      let workBook = xlsx.read(dataBinary, { type: 'binary',cellDates: true})
      let workSheet = workBook.Sheets[workBook.SheetNames[0]]
      let data = xlsx.utils.sheet_to_json(workSheet)
      let dataArray = []
      let tempArray = []
      data.forEach(function (value) {
        let valueArray = Object.values(value)
        dataArray.push({
          name:valueArray[0],
          studentNumber:valueArray[1],
          enrollmentTime:util.dateTranslate(valueArray[2]),
          idNumber:valueArray[3]
        })
        tempArray.push({
          name:valueArray[0],
          studentNumber:valueArray[1],
          enrollmentTime:util.dateTranslate(valueArray[2]),
        })
      })
      await util.delay(100)
      loadingInstance.close()
      util.messageBox('解析成功','success')
      this.tableData = tempArray
      this.totalDataNum = tempArray.length
      this.$refs.upload.clearFiles()
    },
  },created() {
    // axios.get('/teacher/brief_info')
    //     .then(res =>{
    //       console.log(res)
    //     })
    //this.getInitTableData()
  }
}
</script>
<style scoped>
@import "../../assets/css/index_css/icons.min.css";
@import "../../assets/css/index_css/app-saas.min.css";
@import "../../assets/css/index_css/jquery-jvectormap-1.2.2.css";
</style>
<!--<link rel="shortcut icon" href="assets/images/favicon.ico">-->
<!--<link href="assets/css/dataTables.bootstrap5.min.css" rel="stylesheet" type="text/css">-->
<!--<link href="assets/css/responsive.bootstrap5.min.css" rel="stylesheet" type="text/css">-->
<!--<script src="assets/js/hyper-config.js"></script>-->
<!--<link href="assets/css/icons.min.css" rel="stylesheet" type="text/css">-->
<!--<link href="assets/css/app-modern.min.css" rel="stylesheet" type="text/css" id="app-style">-->