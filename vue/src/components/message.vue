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
                      <div style="margin-bottom: 10px">
                        <span style="margin-top: 0!important; display: inline-block;justify-content: center;align-items: center;">
                          <el-button size="normal" type="success" @click="getInitMessages(1,this.pageSize)">学生信息</el-button>
                          <el-button v-show="userInfo.type===1" size="normal" type="success" @click="getSystemMessages(1,this.pageSize)">系统信息</el-button>
                          <el-button v-show="userInfo.type===0" size="normal" type="success" @click="sendToTeacherInfo.sendToTeacherVisible = true" >给老师发送消息</el-button>
                               <el-dialog
                                   v-model="sendToTeacherInfo.sendToTeacherVisible"
                                   title="消息发送"
                                   width="30%"
                                   :before-close="handleClose"
                               >
                                 <label class="form-label" style="display: block">选择老师</label>
                                 <el-select clearable v-model="sendToTeacherInfo.teacherSelectIndex" class="m-2" placeholder="选择老师" style="margin-bottom: 0!important; margin-top: 0!important; margin-left: 0!important; display: inline-block">
                                  <el-option
                                  v-for="item in sendToTeacherInfo.teacherList"
                                  :key="item.value"
                                  :label="item.label"
                                  :value="item.value"/>
                                 </el-select>
                                 <label class="form-label" style="display: block">发送内容</label>
                                 <el-input  v-model="sendToTeacherInfo.sendText" placeholder="待发送的消息" />
                                 <template #footer>
                                   <span class="dialog-footer">
                                     <el-button @click="sendToTeacherInfo.sendToTeacherVisible = false">取消</el-button>
                                     <el-button type="primary" @click="sendMessageToTeacher(sendToTeacherInfo.sendToTeacherVisible= false)">
                                       确认发送
                                     </el-button>
                                   </span>
                                 </template>
                               </el-dialog>
                          </span>
                      </div>

                      <el-table
                              fit
                              stripe="true"
                              id = "table_studentList"
                              v-loading="tableLoading"
                              hight
                              highlight-current-row
                              border
                              ref="multipleTableRef"
                              @row-click="clickTabRow"
                              @row-contextmenu="rightClick"
                              :data="messagesTableList"
                              style="width: 100%"
                              @selection-change="handleSelectionChange"
                      >
                      <el-table-column type="selection" width="55" />
                      <el-table-column property="status" label="状态" width="60px" show-overflow-tooltip />
                      <el-table-column property="sendFrom" label="发送者" width="150px" show-overflow-tooltip />
                      <el-table-column property="content" label="内容"  show-overflow-tooltip />
                      <!-- <el-table-column  label="操作" width="120">
                        <template #default="scope" >
                          <el-button-group class="ml-4">

                            <el-button @click="handleDelete(scope.$index)" type="primary" :icon="Delete" />
                          </el-button-group>
                        </template>
                      </el-table-column> -->


                    </el-table>
                      <!-- 右键菜单 -->
                      <el-dialog
                         v-model="deliMessageVisible"
                        title="消息发送"
                        width="30%"
                        :before-close="handleClose">
                        <div>
                          <label class="form-label">输入待发送的消息</label>
                          <el-input style="margin-bottom: 10px" v-model="deliContent" placeholder="待发送的消息" />
                        </div>
                        <template #footer>
                          <span class="dialog-footer">
                            <el-button @click="deliMessageVisible = false">取消</el-button>
                            <el-button type="primary" @click="deliMessageConfirm(deliMessageVisible = false)">
                              确认发送
                            </el-button>
                          </span>
                        </template>
                      </el-dialog>
                      <div id = "menu" class="menuDiv">
                        <ul class="menuUl">
                          <li class="liUi" v-for="(item, index) in menus"
                            :key="index"
                            @click="infoClick(index)">
                            <span class="changeBackgrounColor" style="color: rgb(109, 128, 116); height: 35px;
                              line-height: 35px; margin-left: 10px; ">
                              {{item.name}}
                            </span>

                          </li>
                        </ul>
                      </div>
                      <el-dropdown tabindex="1001" placement="top" ref="dropdown1" trigger="click" style="margin-right: 30px">
                        <template #dropdown>
                        <el-dropdown-menu>
                          <el-dropdown-item>Action 1</el-dropdown-item>
                          <el-dropdown-item>Action 2</el-dropdown-item>
                          <el-dropdown-item>Action 3</el-dropdown-item>
                        </el-dropdown-menu>
                        </template>
                     </el-dropdown>


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
  import cookies from 'vue-cookies'
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
        selection:[],
        currentClickRow:{},
        messageType:0,
        menus:[
          { name: '标记已读', operType: 1 },
          { name: '删除消息', operType: 2 },
          { name: '发送消息',operType: 3 },
          { name: '全部已读', operType: 4 },
          { name: '全部删除', operType: 5 }
        ],
        deliMessageVisible:false,
        deliContent:'',
        userInfo:{},
        sendToTeacherInfo:{
          teacherList:[],
          sendToTeacherVisible:false,
          teacherSelectIndex:'',
          sendText:''
        },
      }
    },
    created()
    {
      this.userInfo = cookies.get('userInfo')
    },
    methods:{
      rightClick(row,column,event)
      {
        let menu = document.querySelector("#menu")
        event.preventDefault()
        menu.style.left = event.clientX-290+"px"
        menu.style.top = event.clientY-150+"px"
        menu.style.display = "block"
        menu.style.zIndex = 1000
        this.currentClickRow = row
      },
      clickTabRow(row,column,event)
      {
        let menu = document.querySelector('#menu')
        menu.style.display = "none"
      },
      async infoClick(index)
      {
        if(index ===0)
        {
          await this.markdownARead([this.currentClickRow.id])
        }
        else if (index===1)
        {
          await this.deleteMessage([this.currentClickRow.id])
        }
        else if (index===2)
        {
          if(this.messageType===1)
          {
            util.messageBox('不能对系统发消息','warning')
            return
          }
          else
          {
            this.deliMessageVisible = true
          }

        }
        else if (index===3)
        {
          let messageIdList = []
          for (let i = 0; i <this.messagesTableList.length; i++)
          {
            messageIdList.push(this.messagesTableList[i].id)
          }
          await this.markdownARead(messageIdList)
        }else if(index === 4)
        {
          let messageIdList = []
          for (let i = 0; i <this.messagesTableList.length; i++)
          {
            messageIdList.push(this.messagesTableList[i].id)
          }
          await this.deleteMessage(messageIdList)
        }

        let menu = document.querySelector("#menu")
        menu.style.display = "none"

      },
      async deliMessageConfirm()
      {

        let loading = util.loadingWait('消息发送中。。。',"table_studentList")
          await util.delay(50)
          axios({
            method:'POST',
            url:`message/post`,
            data:{
              reciId:this.currentClickRow.sendFromId,
              content:this.deliContent
            }
          }).then(async (res)=>{
            await util.delay(100)
            loading.close()
            util.messageBox('发送成功','success')
          }).catch(async (err)=>{
            await util.delay(100)
            loading.close()
            util.print(err)
            util.messageBox('发送失败','error')
          })
      },
      async deleteMessage(messageIdList)
      {
        let loading = util.loadingWait('删除中。。。',"table_studentList")
        await util.delay(50)
        await axios({
          url:`message/delete`,
          headers:{'content-type':'application/json'},
          method:'POST',
          data:messageIdList
        }).then(async (res)=>{
          util.messageBox('删除成功','success')
          await util.delay(50)
          loading.close()
          await this.getInitMessages(this.currentPage,this.pageSize)
        }).catch(async ()=>{
          await util.delay(50)
          loading.close()
          util.messageBox('删除失败','error')
        })
      },
      async markdownARead(messageIdList)
      {
        //标记为已读
        let loading = util.loadingWait('标记中。。。',"table_studentList")
        await util.delay(100)
        await axios({
          url:`message/read`,
          method:'POST',
          headers:{'content-type':'application/json'},
          data:messageIdList
        }).then(async (res)=>{
          await util.delay(100)
          loading.close()
          util.messageBox('标记成功','success')
          await this.getInitMessages(this.currentPage, this.pageSize)
        }).catch(async (err)=>{
          await util.delay(100)
          util.messageBox('标记失败','error')
        })
      },
      async getInitMessages(pageNum,pageSize)
      {
        this.messageType=0
        let loading = util.loadingWait('消息获取中。。。',"table_studentList")
        await util.delay(100)
        await axios({
          url:`message/query/list/${pageNum}/${pageSize}`,
          method:'GET',
          headers:{'content-type':'application/x-www-form-urlencoded;charset=UTF-8'},
        }).then(async (res)=>{
          await util.delay(100)
          loading.close()
          if(res.data.data.length===0)
          {
            util.messageBox('暂无消息','success')
            return
          }
          this.totalDataNum = res.data.data[0].totalSize
          let messages = []
          res.data.data.forEach((element,index)=>{
            messages.push({
              status:   element.status===0?'未读':'已读',
              content:  element.content,
              sendFrom: element.sendFrom,
              sendFromId:element.sendFromId,
              sendTo:element.sendTo,
              sendToId:element.sendToId,
              id:element.id,
              index:index
            })
          })
          this.messagesTableList = messages
          util.messageBox('获取消息成功','success')
        }).catch(async (err)=>{
          await util.delay(100)
          loading.close()
          util.messageBox('获取失败','error')
        })
      },
      async handleSizeChange(size)
      {
        this.pageSize = size
        if(this.messageType===0)
        {
          await this.getInitMessages(this.currentPage,size)
        }
        else
        {
          await this.getSystemMessages(this.currentPage,size)
        }



      },
      async handleCurrentChange(index)
      {
        this.currentPage = index
        if(this.messageType===0)
        {
          await this.getInitMessages(index,this.pageSize)
        }
        else
        {
          await  this.getSystemMessages(index,this.pageSize)
        }

      },
      handleDelete()
      {

      },
      handleSelectionChange(select)
      {
        this.selection = select
      },
      async getInitTeacherList()
      {
        let url = `message/query/communicative`
        let loading = util.loadingWait('获取教师信息。。。','table_studentList')
        await util.delay(50)
        await axios.get(url
        ).then(async (res) => {
          console.log(res)
          await util.delay(100)
          loading.close()
          if(res.data.data.length===0)
          {
            util.messageBox('暂无有更多信息', 'warning')
            this.tableData = []
            this.totalDataNum = 0
            return
          }
          util.messageBox('获取教师信息成功', 'success')
          for (let i = 0;i<res.data.data.length;i++)
          {
            this.sendToTeacherInfo.teacherList.push({
              value:i,
              label:res.data.data[i].name,
              id:res.data.data[i].id
            })
          }
        }).catch(async () => {
          await util.delay(100)
          loading.close()
          util.messageBox('获取教师信息失败', 'error')
        })

      },
      async sendMessageToTeacher()
      {
        if(this.sendToTeacherInfo.teacherSelectIndex==='')
        {
          util.messageBox('请选择老师','warning')
          return
        }
        if(this.sendToTeacherInfo.sendText==='')
        {
          util.messageBox('请输入要发送的内容','success')
          return
        }
        let loading = util.loadingWait('发送中。。。')
        await util.delay(100)
        await axios({
          url:`message/post`,
          method:'post',
          headers:{'content-type':'application/json'},
          data:{
            reciId: this.sendToTeacherInfo.
                teacherList[this.sendToTeacherInfo.teacherSelectIndex].id,
            content:this.sendToTeacherInfo.sendText
          }
        }).then(async (res) => {
          await util.delay(100)
          loading.close()
          util.messageBox('发送成功', 'success')
          this.sendToTeacherInfo.sendText = ''
          this.sendToTeacherInfo.teacherSelectIndex = ''
        }).catch(async (err) => {
          await util.delay(100)
          loading.close()
          util.messageBox('发送失败', 'error')
        })
      },
      async getSystemMessages(pageNum,pageSize)
      {
        this.messageType=1
        let loading = util.loadingWait('获取系统消息中','success')
        await util.delay(100)
        await axios({
          method:'GET',
          headers:{'content-type':'application/x-www-form-urlencoded;charset=UTF-8'},
          url:`teacher/message/list/${pageNum}/${pageSize}`
        }).then(async (res)=>{
          await util.delay(100)
          loading.close()
          if(res.data.data.length===0)
          {
            util.messageBox('暂无系统消息','success')
          }
          else
          {
            this.messagesTableList = res.data.data
            this.totalDataNum = res.data.data[0].totalSize
            util.messageBox('获取系统消息成功','success')
          }
        }).catch(async (err)=>{
          console.log(err)
          await util.delay(100)
          loading.close()
          util.messageBox('获取系统消息失败','error')
        })
      }

    },
    mounted()
    {
      this.getInitMessages(1, 5)
      this.getInitTeacherList()
    }
  }
  </script>
  <style scoped>
  .menuDiv {
    display: none;
    position: absolute;
    width: 120px;
  }
  .menuDiv .menuUl {

    height: auto;
    width: auto;
    font-size: 14px;
    text-align: left;
    border-radius: 3px;
    background-color: #FFFFFF;
    color: #000000;
    list-style: none;
    padding: 0 0px;
  }
.liUi:hover{
  cursor:pointer;
  background-color: rgb(246, 246, 246);
}
  @import "../assets/css/index_css/icons.min.css";
  @import "../assets/css/index_css/app-saas.min.css";
  /*@import "../../assets/css/index_css/jquery-jvectormap-1.2.2.css";*/
  </style>
