"use strict";(self["webpackChunkvue"]=self["webpackChunkvue"]||[]).push([[306],{8306:function(a,e,t){t.r(e),t.d(e,{default:function(){return L}});var l=t(6252),n=t(9963),o=t(3577);const d=a=>((0,l.dD)("data-v-c8013a10"),a=a(),(0,l.Cn)(),a),i={class:"wrapper"},s={class:"content-page"},c={class:"content"},r={class:"container-fluid"},u=(0,l.uE)('<div class="row" data-v-c8013a10><div class="col-12" data-v-c8013a10><div class="page-title-box" data-v-c8013a10><div class="page-title-right" data-v-c8013a10><ol class="breadcrumb m-0" data-v-c8013a10></ol></div><h4 class="page-title" data-v-c8013a10>教师账户管理</h4></div></div></div>',1),m={class:"row"},p={class:"col-5"},f={class:"card"},h={class:"card-body"},b={class:"row mb-3"},I={class:"col-sm-5"},g=d((()=>(0,l._)("i",{class:"mdi mdi-plus-circle me-2"},null,-1))),w=d((()=>(0,l._)("label",{class:"form-label"},"姓名",-1))),v=d((()=>(0,l._)("label",{class:"form-label"},"密码",-1))),y=d((()=>(0,l._)("label",{class:"form-label"},"邮箱",-1))),C={class:"dialog-footer"},V={class:"dialog-footer"};function _(a,e,t,d,_,D){const Z=(0,l.up)("TopNav"),k=(0,l.up)("LeftNav"),x=(0,l.up)("el-input"),W=(0,l.up)("el-button"),T=(0,l.up)("el-dialog"),U=(0,l.up)("el-table-column"),B=(0,l.up)("el-button-group"),L=(0,l.up)("el-table"),E=(0,l.Q2)("loading");return(0,l.wg)(),(0,l.iD)("body",null,[(0,l._)("div",i,[(0,l.Wm)(Z),(0,l.Wm)(k),(0,l._)("div",s,[(0,l._)("div",c,[(0,l._)("div",r,[u,(0,l._)("div",m,[(0,l._)("div",p,[(0,l._)("div",f,[(0,l._)("div",h,[(0,l._)("div",b,[(0,l._)("div",I,[(0,l._)("span",{style:{width:"150px"},onClick:e[0]||(e[0]=a=>_.addInfoDialogVisible=!0),class:"btn btn-danger mb-2"},[g,(0,l.Uk)(" 添加教师账号")]),(0,l.Wm)(T,{modelValue:_.addInfoDialogVisible,"onUpdate:modelValue":e[8]||(e[8]=a=>_.addInfoDialogVisible=a),title:"教师账号信息添加",width:"30%",onClose:D.addInputInfoClose},{footer:(0,l.w5)((()=>[(0,l._)("span",C,[(0,l.Wm)(W,{onClick:e[4]||(e[4]=a=>_.addInfoDialogVisible=!1)},{default:(0,l.w5)((()=>[(0,l.Uk)("关闭")])),_:1}),(0,l.Wm)(W,{type:"primary",onClick:D.addInput},{default:(0,l.w5)((()=>[(0,l.Uk)(" 确认 ")])),_:1},8,["onClick"]),(0,l.wy)((0,l.Wm)(W,{text:"",onClick:e[5]||(e[5]=a=>_.addInfoConfirmVisible=!0)},{default:(0,l.w5)((()=>[(0,l.Uk)(" Click to open the Dialog ")])),_:1},512),[[n.F8,!1]]),(0,l.Wm)(T,{modelValue:_.addInfoConfirmVisible,"onUpdate:modelValue":e[7]||(e[7]=a=>_.addInfoConfirmVisible=a),title:"Warning",width:"30%",center:""},{footer:(0,l.w5)((()=>[(0,l._)("span",V,[(0,l.Wm)(W,{onClick:e[6]||(e[6]=a=>_.addInfoConfirmVisible=!1)},{default:(0,l.w5)((()=>[(0,l.Uk)("取消")])),_:1}),(0,l.Wm)(W,{type:"primary",onClick:D.addInfoConfirm},{default:(0,l.w5)((()=>[(0,l.Uk)(" 确认 ")])),_:1},8,["onClick"])])])),default:(0,l.w5)((()=>[(0,l._)("span",null,(0,o.zw)(_.confirmMessage),1)])),_:1},8,["modelValue"])])])),default:(0,l.w5)((()=>[(0,l._)("div",null,[w,(0,l.Wm)(x,{style:{"margin-bottom":"10px"},modelValue:_.addInputInfo.name,"onUpdate:modelValue":e[1]||(e[1]=a=>_.addInputInfo.name=a),placeholder:"输入姓名"},null,8,["modelValue"]),v,(0,l.Wm)(x,{style:{"margin-bottom":"10px"},modelValue:_.addInputInfo.password,"onUpdate:modelValue":e[2]||(e[2]=a=>_.addInputInfo.password=a),placeholder:"输入密码"},null,8,["modelValue"]),y,(0,l.Wm)(x,{style:{"margin-bottom":"10px"},modelValue:_.addInputInfo.email,"onUpdate:modelValue":e[3]||(e[3]=a=>_.addInputInfo.email=a),placeholder:"输入邮箱"},null,8,["modelValue"])])])),_:1},8,["modelValue","onClose"])]),(0,l.wy)(((0,l.wg)(),(0,l.j4)(L,{fit:"",stripe:"true",id:"table_studentList",border:"",ref:"multipleTableRef",data:_.tableData,style:{width:"100%"},onSelectionChange:a.handleSelectionChange},{default:(0,l.w5)((()=>[(0,l.Wm)(U,{type:"selection",width:"55px"}),(0,l.Wm)(U,{property:"name",label:"姓名"}),(0,l.Wm)(U,{label:"操作"},{default:(0,l.w5)((a=>[(0,l.Wm)(B,{class:"ml-4"},{default:(0,l.w5)((()=>[(0,l.Wm)(W,{onClick:e=>D.handleDelete(a.$index),type:"primary",icon:_.Delete},null,8,["onClick","icon"])])),_:2},1024)])),_:1})])),_:1},8,["data","onSelectionChange"])),[[E,_.tableLoading]])])])])])])])])])])])}var D=t(9308),Z=t(3274),k=t(5781),x=t(6133),W=t(594),T={name:"teacherMagager.vue",components:{TopNav:D.Z,LeftNav:Z.Z},data(){return{Search:k.olm,Delete:k.HG3,Edit:k.I8b,addInfoConfirmVisible:!1,addInfoDialogVisible:!1,confirmMessage:"",addInputInfo:{name:"",password:"",email:""},isConfirm:!1,confirmType:1,tableData:[],totalDataNum:0,currentPage:1,pageSize:5,tableLoading:!1,selection:[],bulkDeletionVisible:!1}},methods:{async singleDelete(a){await(0,W.Z)({headers:{"content-Type":"application/json"},method:"post",url:"/teacher/admin/remove",data:a}).then((()=>{x.Z.messageBox("删除成功","success"),this.getInitTableData(this.currentPage,this.pageSize)})).catch((()=>{x.Z.messageBox("删除失败","success")}))},async addInfoConfirm(){let a=x.Z.loadingWait("正在导入中......");if(await x.Z.delay(100),1===this.confirmType){let e={name:this.addInputInfo.name,password:this.addInputInfo.password,email:this.addInputInfo.email};await(0,W.Z)({headers:{"content-Type":"application/json"},method:"post",url:"/teacher/add/teacher",data:e}).then((async()=>{a.close(),x.Z.messageBox("添加成功","success"),await this.getInitTableData(),this.addInfoDialogVisible=!1})).catch((()=>{a.close(),x.Z.messageBox("添加失败","error")}))}this.addInfoConfirmVisible=!1,this.addInputInfoClose()},addInput(){if(""===this.addInputInfo.name||""===this.addInputInfo.password||""===this.addInputInfo.email)return x.Z.messageBox("请将信息填写完整","warning"),void(this.addInfoDialogVisible=!0);this.addInfoDialogVisible=!0,this.confirmMessage="是否确认添加信息",this.addInfoConfirmVisible=!0,this.confirmType=1},addInputInfoClose(){this.addInputInfo.name="",this.addInputInfo.password="",this.addInputInfo.email="",this.addInfoConfirmVisible=!1},async handleDelete(a){let e=this.tableData[a].id;await(0,W.Z)({headers:{"content-type":"application/x-www-form-urlencoded;charset=UTF-8"},method:"post",url:`teacher/delete/teacher/${e}`,data:[e]}).then((a=>{403===a.data.code?x.Z.messageBox("系统至少需要保留一名老师","warning"):(x.Z.messageBox("删除成功","success"),this.getInitTableData(),this.addInfoDialogVisible=!1)})).catch((()=>{x.Z.messageBox("删除失败","success")}))},async getInitTableData(){let a="teacher/query/list",e=x.Z.loadingWait("获取教师信息。。。","table_studentList");await x.Z.delay(50),await W.Z.get(a).then((async a=>{if(await x.Z.delay(100),e.close(),0===a.data.data.length)return x.Z.messageBox("暂无有更多信息","warning"),this.tableData=[],void(this.totalDataNum=0);this.tableData=a.data.data,x.Z.messageBox("获取教师信息成功","success")})).catch((async()=>{await x.Z.delay(100),e.close(),x.Z.messageBox("获取教师信息失败","error")}))}},mounted(){this.getInitTableData(1,5),window.addEventListener("beforeunload",(a=>x.Z.beforeunloadHandle())),window.addEventListener("unload",(a=>x.Z.unloadHandle()))},unmounted(){window.removeEventListener("beforeunload",(a=>x.Z.beforeunloadHandle())),window.removeEventListener("unload",(a=>x.Z.unloadHandle()))}},U=t(3744);const B=(0,U.Z)(T,[["render",_],["__scopeId","data-v-c8013a10"]]);var L=B}}]);
//# sourceMappingURL=306.88dbfc7b.js.map