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

                <h4 class="page-title">教师账户管理</h4>

              </div>
            </div>
          </div>
          <div class="row" >
            <div class="col-5">
              <div class="card">
                <div class="card-body">










                  <div class="row mb-3">
                    <div class="col-sm-5">
                      <span style="width: 150px" @click="addInfoDialogVisible = true"  class="btn btn-danger mb-2"><i class="mdi mdi-plus-circle me-2"></i> 添加教师账号</span>
                      <el-dialog
                          v-model="addInfoDialogVisible"
                          title="教师账号信息添加"
                          width="30%"
                          @close="addInputInfoClose"
                      >
                        <div>
                          <label class="form-label">姓名</label>
                          <el-input style="margin-bottom: 10px" v-model="addInputInfo.name" placeholder="输入姓名" />
                          <label class="form-label">密码</label>
                          <el-input style="margin-bottom: 10px" v-model="addInputInfo.password" placeholder="输入密码" />
                          <label class="form-label">邮箱</label>
                          <el-input style="margin-bottom: 10px" v-model="addInputInfo.email" placeholder="输入邮箱" />
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
                        :data="tableData"
                        style="width: 100%"
                        @selection-change="handleSelectionChange"
                    >
                      <el-table-column type="selection" width="55px" />
                      <el-table-column property="name" label="姓名"   />
                      <el-table-column  label="操作">
                        <template #default="scope" >
                          <el-button-group class="ml-4">
                            <!--                          <el-button @click="handleEdit(scope.$index, scope.row)" type="primary" :icon="Edit" />-->
                            <el-button @click="handleDelete(scope.$index)" type="primary" :icon="Delete" />
                          </el-button-group>
                        </template>
                      </el-table-column>
                    </el-table>
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
export default {
  name: "teacherMagager.vue",
  components:{
    TopNav:topNav,
    LeftNav:leftNav
  },
  data(){
    return{
      Search,
      Delete,
      Edit,
      addInfoConfirmVisible:false,
      addInfoDialogVisible:false,
      confirmMessage:''
      ,addInputInfo:{
        name:'',
        password:'',
        email:''
      },
      isConfirm:false,
      confirmType:1,
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
    async addInfoConfirm(){
      let loading = util.loadingWait('正在导入中......')
      await util.delay(100)
      if (this.confirmType === 1)
      {
        let submitData = {
          name: this.addInputInfo.name,
          password: this.addInputInfo.password,
          email:this.addInputInfo.email
        }
        await axios({
          headers: {'content-Type': 'application/json'},
          method: 'post',
          url: '/teacher/add/teacher',
          data: submitData
        }).then(async () => {
          loading.close()
          util.messageBox('添加成功','success')
          await this.getInitTableData()
          this.addInfoDialogVisible =false
        }).catch(() => {
          loading.close()
          util.messageBox('添加失败','error')
        })
      }
      this.addInfoConfirmVisible = false
      this.addInputInfoClose()
    },
    addInput()
    {
      if (this.addInputInfo.name===''       ||
          this.addInputInfo.password==='' ||
          this.addInputInfo.email==='')
      {
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
      this.addInputInfo.password=''
      this.addInputInfo.email=''
      this.addInfoConfirmVisible = false
    },
    async handleDelete(index)
    {
      let id = this.tableData[index].id
      await axios({
        headers:{'content-type':'application/x-www-form-urlencoded;charset=UTF-8'},
        method: 'post',
        url: `teacher/delete/teacher/${id}`,
        data: [id]
      }).then((res)=>{
        if(res.data.code===403)
        {
          util.messageBox('系统至少需要保留一名老师','warning')
        }
        else
        {
          util.messageBox('删除成功','success')
          this.getInitTableData()
          this.addInfoDialogVisible = false
        }


      }).catch(()=>{
        util.messageBox('删除失败','success')
      })
    },
    async getInitTableData()
    {
      let url = `teacher/query/list`
      let loading = util.loadingWait('获取教师信息。。。','table_studentList')
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
        this.tableData = res.data.data
        util.messageBox('获取教师信息成功', 'success')
      }).catch(async () => {
        await util.delay(100)
        loading.close()
        util.messageBox('获取教师信息失败', 'error')
      })

    },
  },
  mounted() {
    this.getInitTableData(1,5)
  }
}
</script>
<style scoped>
@import "../assets/css/index_css/icons.min.css";
@import "../assets/css/index_css/app-saas.min.css";
/*@import "../../assets/css/index_css/jquery-jvectormap-1.2.2.css";*/
</style>
