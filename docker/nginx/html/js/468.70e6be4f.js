"use strict";(self["webpackChunkvue"]=self["webpackChunkvue"]||[]).push([[468],{1468:function(e,a,t){t.r(a),t.d(a,{default:function(){return L}});var l=t(6252),o=t(9963),d=t(3577);const n=e=>((0,l.dD)("data-v-d982e446"),e=e(),(0,l.Cn)(),e),i={class:"wrapper"},s={class:"content-page"},c={class:"content"},r={class:"container-fluid"},m=(0,l.uE)('<div class="row" data-v-d982e446><div class="col-12" data-v-d982e446><div class="page-title-box" data-v-d982e446><div class="page-title-right" data-v-d982e446><ol class="breadcrumb m-0" data-v-d982e446></ol></div><h4 class="page-title" data-v-d982e446>教师账户管理</h4></div></div></div>',1),u={class:"row"},p={class:"col-5"},f={class:"card"},h={class:"card-body"},I={class:"row mb-3"},g={class:"col-sm-5"},b=n((()=>(0,l._)("i",{class:"mdi mdi-plus-circle me-2"},null,-1))),w=n((()=>(0,l._)("label",{class:"form-label"},"姓名",-1))),v=n((()=>(0,l._)("label",{class:"form-label"},"密码",-1))),y=n((()=>(0,l._)("label",{class:"form-label"},"邮箱",-1))),C={class:"dialog-footer"},V={class:"dialog-footer"};function _(e,a,t,n,_,D){const Z=(0,l.up)("TopNav"),k=(0,l.up)("LeftNav"),x=(0,l.up)("el-input"),W=(0,l.up)("el-button"),T=(0,l.up)("el-dialog"),U=(0,l.up)("el-table-column"),B=(0,l.up)("el-button-group"),L=(0,l.up)("el-table"),N=(0,l.Q2)("loading");return(0,l.wg)(),(0,l.iD)("body",null,[(0,l._)("div",i,[(0,l.Wm)(Z),(0,l.Wm)(k),(0,l._)("div",s,[(0,l._)("div",c,[(0,l._)("div",r,[m,(0,l._)("div",u,[(0,l._)("div",p,[(0,l._)("div",f,[(0,l._)("div",h,[(0,l._)("div",I,[(0,l._)("div",g,[(0,l._)("span",{style:{width:"150px"},onClick:a[0]||(a[0]=e=>_.addInfoDialogVisible=!0),class:"btn btn-danger mb-2"},[b,(0,l.Uk)(" 添加教师账号")]),(0,l.Wm)(T,{modelValue:_.addInfoDialogVisible,"onUpdate:modelValue":a[8]||(a[8]=e=>_.addInfoDialogVisible=e),title:"教师账号信息添加",width:"30%",onClose:D.addInputInfoClose},{footer:(0,l.w5)((()=>[(0,l._)("span",C,[(0,l.Wm)(W,{onClick:a[4]||(a[4]=e=>_.addInfoDialogVisible=!1)},{default:(0,l.w5)((()=>[(0,l.Uk)("关闭")])),_:1}),(0,l.Wm)(W,{type:"primary",onClick:D.addInput},{default:(0,l.w5)((()=>[(0,l.Uk)(" 确认 ")])),_:1},8,["onClick"]),(0,l.wy)((0,l.Wm)(W,{text:"",onClick:a[5]||(a[5]=e=>_.addInfoConfirmVisible=!0)},{default:(0,l.w5)((()=>[(0,l.Uk)(" Click to open the Dialog ")])),_:1},512),[[o.F8,!1]]),(0,l.Wm)(T,{modelValue:_.addInfoConfirmVisible,"onUpdate:modelValue":a[7]||(a[7]=e=>_.addInfoConfirmVisible=e),title:"Warning",width:"30%",center:""},{footer:(0,l.w5)((()=>[(0,l._)("span",V,[(0,l.Wm)(W,{onClick:a[6]||(a[6]=e=>_.addInfoConfirmVisible=!1)},{default:(0,l.w5)((()=>[(0,l.Uk)("取消")])),_:1}),(0,l.Wm)(W,{type:"primary",onClick:D.addInfoConfirm},{default:(0,l.w5)((()=>[(0,l.Uk)(" 确认 ")])),_:1},8,["onClick"])])])),default:(0,l.w5)((()=>[(0,l._)("span",null,(0,d.zw)(_.confirmMessage),1)])),_:1},8,["modelValue"])])])),default:(0,l.w5)((()=>[(0,l._)("div",null,[w,(0,l.Wm)(x,{style:{"margin-bottom":"10px"},modelValue:_.addInputInfo.name,"onUpdate:modelValue":a[1]||(a[1]=e=>_.addInputInfo.name=e),placeholder:"输入姓名"},null,8,["modelValue"]),v,(0,l.Wm)(x,{style:{"margin-bottom":"10px"},modelValue:_.addInputInfo.password,"onUpdate:modelValue":a[2]||(a[2]=e=>_.addInputInfo.password=e),placeholder:"输入密码"},null,8,["modelValue"]),y,(0,l.Wm)(x,{style:{"margin-bottom":"10px"},modelValue:_.addInputInfo.email,"onUpdate:modelValue":a[3]||(a[3]=e=>_.addInputInfo.email=e),placeholder:"输入邮箱"},null,8,["modelValue"])])])),_:1},8,["modelValue","onClose"])]),(0,l.wy)(((0,l.wg)(),(0,l.j4)(L,{fit:"",stripe:"true",id:"table_studentList",border:"",ref:"multipleTableRef",data:_.tableData,style:{width:"100%"},onSelectionChange:e.handleSelectionChange},{default:(0,l.w5)((()=>[(0,l.Wm)(U,{type:"selection",width:"55px"}),(0,l.Wm)(U,{property:"name",label:"姓名"}),(0,l.Wm)(U,{label:"操作"},{default:(0,l.w5)((e=>[(0,l.Wm)(B,{class:"ml-4"},{default:(0,l.w5)((()=>[(0,l.Wm)(W,{onClick:a=>D.handleDelete(e.$index),type:"primary",icon:_.Delete},null,8,["onClick","icon"])])),_:2},1024)])),_:1})])),_:1},8,["data","onSelectionChange"])),[[N,_.tableLoading]])])])])])])])])])])])}var D=t(9308),Z=t(6317),k=t(5781),x=t(6133),W=t(594),T={name:"teacherMagager.vue",components:{TopNav:D.Z,LeftNav:Z.Z},data(){return{Search:k.olm,Delete:k.HG3,Edit:k.I8b,addInfoConfirmVisible:!1,addInfoDialogVisible:!1,confirmMessage:"",addInputInfo:{name:"",password:"",email:""},isConfirm:!1,confirmType:1,tableData:[],totalDataNum:0,currentPage:1,pageSize:5,tableLoading:!1,selection:[],bulkDeletionVisible:!1}},methods:{async singleDelete(e){await(0,W.Z)({headers:{"content-Type":"application/json"},method:"post",url:"/teacher/admin/remove",data:e}).then((()=>{x.Z.messageBox("删除成功","success"),this.getInitTableData(this.currentPage,this.pageSize)})).catch((()=>{x.Z.messageBox("删除失败","success")}))},async addInfoConfirm(){let e=x.Z.loadingWait("正在导入中......");if(await x.Z.delay(100),1===this.confirmType){let a={name:this.addInputInfo.name,password:this.addInputInfo.password,email:this.addInputInfo.email};await(0,W.Z)({headers:{"content-Type":"application/json"},method:"post",url:"/teacher/add/teacher",data:a}).then((async()=>{e.close(),x.Z.messageBox("添加成功","success"),await this.getInitTableData(),this.addInfoDialogVisible=!1})).catch((()=>{e.close(),x.Z.messageBox("添加失败","error")}))}this.addInfoConfirmVisible=!1,this.addInputInfoClose()},addInput(){if(""===this.addInputInfo.name||""===this.addInputInfo.password||""===this.addInputInfo.email)return x.Z.messageBox("请将信息填写完整","warning"),void(this.addInfoDialogVisible=!0);this.addInfoDialogVisible=!0,this.confirmMessage="是否确认添加信息",this.addInfoConfirmVisible=!0,this.confirmType=1},addInputInfoClose(){this.addInputInfo.name="",this.addInputInfo.password="",this.addInputInfo.email="",this.addInfoConfirmVisible=!1},async handleDelete(e){let a=this.tableData[e].id;await(0,W.Z)({headers:{"content-type":"application/x-www-form-urlencoded;charset=UTF-8"},method:"post",url:`teacher/delete/teacher/${a}`,data:[a]}).then((e=>{403===e.data.code?x.Z.messageBox("系统至少需要保留一名老师","warning"):(x.Z.messageBox("删除成功","success"),this.getInitTableData(),this.addInfoDialogVisible=!1)})).catch((()=>{x.Z.messageBox("删除失败","success")}))},async getInitTableData(){let e="teacher/query/list",a=x.Z.loadingWait("获取教师信息。。。","table_studentList");await x.Z.delay(50),await W.Z.get(e).then((async e=>{if(await x.Z.delay(100),a.close(),0===e.data.data.length)return x.Z.messageBox("暂无有更多信息","warning"),this.tableData=[],void(this.totalDataNum=0);this.tableData=e.data.data,x.Z.messageBox("获取教师信息成功","success")})).catch((async()=>{await x.Z.delay(100),a.close(),x.Z.messageBox("获取教师信息失败","error")}))}},mounted(){this.getInitTableData(1,5),window.addEventListener("beforeunload",(e=>x.Z.destroyCookie(e)))},unmounted(){window.removeEventListener("beforeunload",(e=>x.Z.destroyCookie(e)))}},U=t(3744);const B=(0,U.Z)(T,[["render",_],["__scopeId","data-v-d982e446"]]);var L=B}}]);
//# sourceMappingURL=468.70e6be4f.js.map