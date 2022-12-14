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
                  <div style="margin-bottom: 10px">
                    <span style="display: inline-block;justify-content: center;align-items: center">
                      <span style="margin-right: 5px;display: inline-block">
                        学号/姓名
                      </span>
                      <el-input v-model="searchInfo.nameOrStudentNumber" placeholder="输入姓名/学号"
                                style="display: inline-block; width: 200px; margin-right: 0!important;" />
                      <div style="display: inline-block; text-align: center; align-items: center;margin-left: 0!important;">
                        <el-button :icon="Search" circle @click="nameOrStudentNumberSearch" />
                      </div>
                    </span>
                  </div>


                  <div style="margin-bottom: 10px" >
                     <span style="margin-bottom: 0!important; display: inline-block;justify-content: center;align-items: center ">
                        <span style="margin-right: 5px">
                        年级
                        </span>
                      <el-input v-model="searchInfo.grade" placeholder="输入年级如：2020"
                                style="display: inline-block; width: 200px;" />
                        <span style="margin-right: 5px!important;">
                          免费次数
                        </span>
                     <el-select clearable v-model="searchInfo.freeTime" class="m-2" placeholder="选择免费次数" style="margin-bottom: 0!important; margin-top: 0!important; margin-left: 0!important; display: inline-block">
                        <el-option
                            v-for="item in optionsFreeTime"
                            :key="item.value"
                            :label="item.label"
                            :value="item.value"
                        /></el-select>
                    </span>
                  </div>

                  <div style="margin-bottom: 10px">
                     <span style="margin-top: 0!important; display: inline-block;justify-content: center;align-items: center;">
                       <span style="margin-right: 5px!important;">
                          分数条件
                        </span>
                      <el-select clearable v-model="searchInfo.scoreFilter" class="m-2" placeholder="选择分数条件" style="margin-bottom: 0!important; margin-top: 0!important; margin-left: 0!important; display: inline-block">
                        <el-option
                            v-for="item in optionsScore"
                            :key="item.value"
                            :label="item.label"
                            :value="item.value"
                        /></el-select>
                       <span style="margin-right: 5px">
                        分数
                        </span>
                      <el-input v-model="searchInfo.score" placeholder="输入分数如：300"
                                style="display: inline-block; width: 200px;" />
                      <div style="display: inline-block; text-align: center; align-items: center">
                        <el-button :icon="Search" circle @click="otherSearch(this.currentPage,this.pageSize)" />
                      </div>
                       <div style="display: inline-block; margin-left: 20px;text-align: center; align-items: center" >
                      <el-button type="danger" :icon="Delete" circle @click="resetAll" />
                    </div>
                     </span>
                  </div>
                  <div style="margin-bottom: 10px">
                    <el-dialog append-to-body v-model="bulkDeletionVisible" title="警告" width="30%" center>
                      <span>
                        若没有选中表格任何数据则将删除当下搜索到的所有数据。
                        若没有任何搜索字段将删除所有学生信息
                      </span>
                      <template #footer>
                        <span class="dialog-footer">
                          <el-button @click="bulkDeletionVisible = false">取消</el-button>
                          <el-button type="primary" @click="bulkDeletion">
                            确认删除
                          </el-button>
                        </span>
                      </template>
                    </el-dialog>
                    <span style="margin-top: 0!important; display: inline-block;justify-content: center;align-items: center;">
                        <el-button size="small" type="primary" @click="bulkDeletionConfirm">批量删除</el-button>
                     </span>
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
                        <el-button  type="success" size="large">导出</el-button>

                      </div>

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
                    <el-table-column  label="操作" width="120px">
                      <template #default="scope" >
                        <el-button-group class="ml-4">
<!--                          <el-button @click="handleEdit(scope.$index, scope.row)" type="primary" :icon="Edit" />-->
                          <el-button @click="handleDelete(scope.$index)" type="primary" :icon="Delete" />
                        </el-button-group>
                      </template>
                    </el-table-column>
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
                                       :total="totalDataNum"
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
import {Search,Delete,Edit } from '@element-plus/icons-vue';
import util from '@/utils/commonUtil'
import axios from "axios";
import { forEach } from "lodash";
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
      optionsFreeTime:[{value:1,label:'0'},{value:2,label:'1'}],
      optionsScore:[{value: 1,label: '大于'},{value: 2,label: '小于',},{value: 3,label: '等于'}],
      tableData:[],
      totalDataNum :0,
      currentPage:1,
      pageSize:5,
      tableLoading:false,
      selection:[],
      bulkDeletionVisible:false,
    }
  },
  methods:{
    async singleDelete(uidList)
    {
      await axios({
        headers: {'content-Type': 'application/json'},
        method: 'post',
        url: '/teacher/admin/remove',
        data: uidList
      }).then(()=>{
        util.messageBox('删除成功','success')
        this.getInitTableData(this.currentPage,this.pageSize)
      }).catch(()=>{
        util.messageBox('删除失败','success')
      })
    },
    async getSearchUrl()
    {
      if(this.searchInfo.grade.toString()===''&&this.searchInfo.score===''
          &&this.searchInfo.scoreFilter===''&&this.searchInfo.freeTime==='')
      {
        return ''
      }
      if((this.searchInfo.grade.length>0)&&(this.searchInfo.grade.length!==4||
          util.judgeInputIsNumber(this.searchInfo.grade)===false))
      {
        util.messageBox('年级格式错误','error')
        return ''
      }
      if(this.searchInfo.score.length>0&&util.judgeInputIsNumber(this.searchInfo.score)===false)
      {
        util.messageBox('分数格式输入错误','error')
        return ''
      }
      if(this.searchInfo.scoreFilter===''&&this.searchInfo.score!==''||
          this.searchInfo.scoreFilter!==''&&this.searchInfo.score==='')
      {
        util.messageBox('分数条件与分数必须同时选择一个值','error')
        return ''
      }
      let score = this.searchInfo.score===''?null:this.searchInfo.score
      let filter = this.searchInfo.scoreFilter===''?null:this.searchInfo.scoreFilter
      let grade = (this.searchInfo.grade===''?null:this.searchInfo.grade+'-09-01')
      let freeTime= this.searchInfo.freeTime===''?null:this.searchInfo.freeTime-1
      let url  =`teacher/filter/${score}/${filter}/${grade}/${freeTime}/`
      //${1}/${this.pageSize}`
      return url
    },
    async bulkDeletion()
    {
      if(this.selection.length===0)
      {
        //如果没有选中某些内容删除该搜索全部信息
        let url = await this.getSearchUrl()
        if(url==='')
        {
          util.messageBox('批量删除需要选择字段','warning')
          // let loading = util.loadingWait('删除中')
          // url = `teacher/filter/null/null/null/null`
          // await axios({
          //   url:url,
          //   headers:{'content-type':'application/x-www-form-urlencoded;charset=UTF-8'},
          //   method:'GET'
          // }).then((res)=>{

          // }).catch(()=>{

          // })
        }
        else
        {

          url =url.slice(0,url.length-1)
          let loading = util.loadingWait('删除中')
          await util.delay(100)
          await axios({
            url:url,
            headers:{'content-type':'application/x-www-form-urlencoded;charset=UTF-8'},
            method:'POST'
          }).then((res)=>{
            loading.close()
            util.delay(100)
            util.messageBox('删除成功','success')
            this.bulkDeletionVisible = false
            this.getInitTableData(1,this.pageSize)
          }).catch((err)=>{
            loading.close()
            util.delay(100)
            console.log(err)
            util.messageBox('删除失败','error')
          })
        }
      }
      else
      {
        let uidList = []
        this.selection.forEach((element)=>{
          uidList.push(element.id)
        })
        await axios({
        headers: {'content-Type': 'application/json'},
        method: 'post',
        url: '/teacher/admin/remove',
        data: uidList
        }).then(()=>{
          util.messageBox('删除成功','success')
          this.bulkDeletionVisible = false
          this.getInitTableData(this.currentPage,this.pageSize)
        }).catch(()=>{
          util.messageBox('删除失败','success')
        })


      }
    },
    bulkDeletionConfirm()
    {
      this.bulkDeletionVisible = true
    },
    resetAll(){
      this.searchInfo.freeTime=''
      this.searchInfo.grade=''
      this.searchInfo.nameOrStudentNumber=''
      this.searchInfo.scoreFilter=''
    },
    async otherSearch(pageNum,pageSize)
    {
      let url = await this.getSearchUrl()
      if(url==='')
      {
        await this.getInitTableData(1,this.pageSize)
        console.log("ddd")
        return
      }
      else
        url = url + `${pageNum}/${pageSize}`
      let loading = util.loadingWait('搜索中','table_studentList')
      await util.delay(100)
      await axios({
        url: url,
        method: 'GET',
        headers:{'content-type': 'application/x-www-form-urlencoded;charset=UTF-8'},
      }).then(res=>{
        util.delay(100)
        loading.close()
        this.tableData = res.data.data
        if(this.tableData.length!==0)
        {
          this.totalDataNum = res.data.data[0].totalSize
        }else {
          this.totalDataNum=0
          this.currentPage =1
        }
        util.messageBox('查询成功','success')
      }).catch(()=>{
        util.delay(100)
        loading.close()
        util.messageBox('搜索失败','error')
      })

    },
    async nameOrStudentNumberSearch()
    {
      util.print(this.searchInfo.nameOrStudentNumber)
      if(this.searchInfo.nameOrStudentNumber.length===0)
      {
        await this.getInitTableData(1, this.pageSize)
        return
      }
      let loading = util.loadingWait('搜索中。。。','table_studentList')
      await util.delay(50)
      let searchText = this.searchInfo.nameOrStudentNumber
      if(util.judgeInputIsNumber(searchText)===false)
      {
        //输入为中文，搜索名字
          let url = `teacher/filter/${searchText}/null`
          await axios({
            method:'GET',
            headers:{'content-type':'application/x-www-form-urlencoded;charset=UTF-8'},
            url:url
          }).then(async res => {

            await util.delay(50)
            loading.close()
            util.messageBox('查询成功', 'success')
            this.tableData = res.data.data
            this.currentPage = 1
            this.totalDataNum = res.data.data.length
          }).catch(async (err) => {
            util.print(err)
            await util.delay(50)
            loading.close()
            util.messageBox('查询失败', 'error')
          })
      }
      else
      {
        //输入数字，搜索学号
        let url = `teacher/filter/null/${searchText}`
        await axios.get(url).then(async (res) => {
          await util.delay(50)
          util.print(res)
          loading.close()
          util.messageBox('查询成功', 'success')
          this.tableData = res.data.data
          this.currentPage = 1
          this.totalDataNum = 1

        }).catch(async () => {
          await util.delay(50)
          loading.close()
          util.messageBox('查询失败', 'error')
        })
      }
      this.searchInfo.nameOrStudentNumber=''
    },
    async addInfoConfirm(){
      let loading = util.loadingWait('正在导入中......')
      await util.delay(100)
      if (this.confirmType === 1)
      {
        let submitData = {
            name: this.addInputInfo.name,
            studentNumber: this.addInputInfo.studentNum,
            enrollmentTime: this.addInputInfo.grade+ '-' + '9-1',
            idNumber: this.addInputInfo.studentId
        }
        await axios({
          headers: {'content-Type': 'application/json'},
          method: 'post',
          url: '/teacher/import/freshman',
          data: [submitData]
        }).then(() => {
          loading.close()
          util.messageBox('导入成功','success')
        }).catch(() => {
          loading.close()
          util.messageBox('导入失败','error')
        })
      }
      this.addInfoConfirmVisible = false
      this.addInputInfoClose()
    },
    addInput()
    {
      if (this.addInputInfo.studentNum==='' ||
          this.addInputInfo.name===''       ||
          this.addInputInfo.studentNum==='' ||
          this.addInputInfo.studentId==='')
      {
        util.messageBox('请将信息填写完整','warning')
        this.addInfoDialogVisible = true
        return
      }
      if (this.addInputInfo.studentId.length<18)
      {
        util.messageBox('身份证号输入过短','error')
        this.addInfoDialogVisible = true
        return
      }
      else if (this.addInputInfo.studentId.length>18)
      {
        util.messageBox('身份证号输入过长','error')
        util.messageBox('请将信息填写完整','warning')
        this.addInfoDialogVisible = true
        return
      }

      this.addInfoDialogVisible = true
      this.confirmMessage = '是否确认添加信息'
      this.addInfoConfirmVisible = true
      this.confirmType = 1

    },
    addInputInfoClose(){
      this.addInputInfo.name=''
      this.addInputInfo.studentNum=''
      this.addInputInfo.studentId=''
      this.addInputInfo.grade=''
      this.addInfoConfirmVisible = false
    },
    async handleDelete(index)
    {
      let id = this.tableData[index].id
      await axios({
        headers: {'content-Type': 'application/json'},
        method: 'post',
        url: '/teacher/admin/remove',
        data: [id]
      }).then(()=>{
        util.messageBox('删除成功','success')
        this.getInitTableData(this.currentPage,this.pageSize)
      }).catch(()=>{
        util.messageBox('删除失败','success')
      })
    },handleSelectionChange(select)
    {
      this.selection = select
    },
    handleEdit()
    {

    },
    async getInitTableData(pageNum,pageSize)
    {
      let url = `teacher/list/${pageNum}/${pageSize}`
      let loading = util.loadingWait('获取学生信息中。。。','table_studentList')
      await util.delay(50)
      await axios.get(url
      ).then(async (res) => {
        await util.delay(100)
        loading.close()
        if(res.data.data.length===0)
        {
          util.messageBox('暂无有更多信息', 'warning')
          this.tableData = []
          this.totalDataNum = 0
          return
        }
        this.currentPage = pageNum
        this.tableData = res.data.data
        this.totalDataNum = res.data.data[0].totalSize
        util.messageBox('获取学生信息成功', 'success')
      }).catch(async () => {
        await util.delay(100)
        loading.close()
        util.messageBox('获取学生信息失败', 'error')
      })

    },
    async handleSizeChange(size)
    {
      this.pageSize = size
      this.currentPage = 1
      let url = await this.getSearchUrl()
      if(url==='')
      {
        await this.getInitTableData(this.currentPage,size)
      }
      else
      {
        await this.otherSearch(this.currentPage,size)
        console.log('size更新')
      }
    },
     async handleCurrentChange(index)
    {
      this.currentPage = index
      let url = await this.getSearchUrl()
      if(url==='')
      {
        await this.getInitTableData(index, this.pageSize)
      }
      else
      {
        await this.otherSearch(index,this.pageSize)
      }
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
      let workBook = xlsx.read(dataBinary, { type: 'binary'})
      let workSheet = workBook.Sheets[workBook.SheetNames[0]]
      let data = xlsx.utils.sheet_to_json(workSheet)
      let keyArray = Object.keys(data[0])
      if (keyArray[0]!=='学号' || keyArray[1]!=='姓名'||keyArray[2]!=='年级'|| keyArray[3]
      !=='身份证号')
      {
        loadingInstance.close()
        util.messageBox('解析错误：导入表格列明不正确','error')
        return
      }
      let dataArray = []
      let jiexiFlag = 1
      data.forEach(function (value) {
        let valueArray = Object.values(value)
        if (valueArray[3].length!==18 || valueArray[2].length!==4)
        {
          jiexiFlag = 0
        }
        dataArray.push({
          studentNumber:valueArray[0],
          name:valueArray[1],
          enrollmentTime:util.dateTranslate(valueArray[2]),
          idNumber:valueArray[3]
        })

      })
      if (jiexiFlag===0)
      {
        loadingInstance.close()
        util.messageBox('导入错误：数据格式不正确','error')
        return
      }
      await util.delay(100)
      loadingInstance.close()
      util.messageBox('解析成功','success')
      this.$refs.upload.clearFiles()
      await this.uploadExcelInfo(dataArray)
      this.tableData = dataArray
      this.totalDataNum = dataArray.length

    },
    async uploadExcelInfo(dataArray)
    {
      let loading = util.loadingWait('上传信息中。。。')
      await util.delay(50)
      let upFlag = 1
      await axios({
        headers: {'content-type':'application/json'},
        method:'post',
        url:'/teacher/import/freshman',
        data:dataArray
      }).then(()=>{
      }).catch(()=>{
        upFlag = 0
      })
      await util.delay(100)
      loading.close()
      if(upFlag===0)
      {
        util.messageBox('上传学生信息失败','error')
        return 0
      }
      else
      {
        util.messageBox('上传学生信息成功','success')
        return 1
      }
    }
  },
 mounted() {
    util.print('haha')
    this.getInitTableData(1,5)

  }
}
</script>
<style scoped>
@import "../../assets/css/index_css/icons.min.css";
@import "../../assets/css/index_css/app-saas.min.css";
/*@import "../../assets/css/index_css/jquery-jvectormap-1.2.2.css";*/
</style>
