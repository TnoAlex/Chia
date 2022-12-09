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
  
                  <h4 class="page-title">消息</h4>
  
                </div>
              </div>
            </div>
            <div class="row">
              <div class="col-12">
                <div class="card">
                  <div class="card-body">
                
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
                              stripe="true"
                              id = "table_studentList"
                              v-loading="tableLoading"
                              border
                              ref="multipleTableRef"
                              :data="messagesTableList"
                              style="width: 100%"
                              @selection-change="handleSelectionChange"
                    >
                      <el-table-column type="selection" width="55" />
                      <el-table-column property="status" label="状态" width="100" show-overflow-tooltip />
                      <el-table-column property="sendFrom" label="发送者" width="200" show-overflow-tooltip />
                      <el-table-column property="content" label="内容" width="500" show-overflow-tooltip />
                      <el-table-column  label="操作" >
                        <template #default="scope" >
                          <el-button-group class="ml-4">
  <!--                          <el-button @click="handleEdit(scope.$index, scope.row)" type="primary" :icon="Edit" />-->
                            <el-button @click="handleDelete(scope.$index)" type="primary" :icon="Delete" />
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
    name: "message.vue",
    components:{
      TopNav:topNav,
      LeftNav:leftNav
    },
    data(){
      return{
        Search,
        Delete,
        Edit,
        messagesTableList:[],
        currentPage:1,
        pageSize:5,
        totalDataNum:0,
        selection:[]
      }
    },
    methods:{
      async getInitMessages(pageNum,pageSize)
      {
        let loading = util.loadingWait('消息获取中。。。')
        util.delay(100)
        axios({
          url:`message/query/list/${pageNum}/${pageSize}`,
          method:'GET',
          headers:{'content-type':'application/x-www-form-urlencoded;charset=UTF-8'},
        }).then(async (res)=>{
          console.log(res)
          util.delay(100)
          loading.close()
          this.messagesTableList = res.data.data
          util.messageBox('获取成功','success')
        }).catch(()=>{
          util.delay(100)
          loading.close()
          util.messageBox('获取失败','error')
        })
      },
      handleSizeChange()
      {

      },
      handleCurrentChange()
      {

      },
      handleDelete()
      {

      },
      handleSelectionChange()
      {

      }
    },
    mounted()
    {
      this.getInitMessages(1,5)
    }
  }
  </script>
  <style scoped>
  @import "../assets/css/index_css/icons.min.css";
  @import "../assets/css/index_css/app-saas.min.css";
  /*@import "../../assets/css/index_css/jquery-jvectormap-1.2.2.css";*/
  </style>