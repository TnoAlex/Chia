"use strict";(self["webpackChunkvue"]=self["webpackChunkvue"]||[]).push([[783],{4783:function(e,a,t){t.r(a),t.d(a,{default:function(){return ie}});var l=t(6252),i=t(9963),n=t(3577);const s=e=>((0,l.dD)("data-v-f6faa3c0"),e=e(),(0,l.Cn)(),e),o={class:"wrapper"},r={class:"content-page"},d={class:"content"},c={class:"container-fluid"},h=(0,l.uE)('<div class="row" data-v-f6faa3c0><div class="col-12" data-v-f6faa3c0><div class="page-title-box" data-v-f6faa3c0><div class="page-title-right" data-v-f6faa3c0><ol class="breadcrumb m-0" data-v-f6faa3c0></ol></div><h4 class="page-title" data-v-f6faa3c0>学生管理</h4></div></div></div>',1),u={class:"row"},m={class:"col-12"},p={class:"card"},f={class:"card-body"},g={style:{"margin-bottom":"10px"}},b={style:{display:"inline-block","justify-content":"center","align-items":"center"}},I=s((()=>(0,l._)("span",{style:{"margin-right":"5px",display:"inline-block"}}," 学号/姓名 ",-1))),y={style:{display:"inline-block","text-align":"center","align-items":"center","margin-left":"0!important"}},w={style:{"margin-bottom":"10px"}},x={style:{"margin-bottom":"0!important",display:"inline-block","justify-content":"center","align-items":"center"}},Z=s((()=>(0,l._)("span",{style:{"margin-right":"5px"}}," 年级 ",-1))),v=s((()=>(0,l._)("span",{style:{"margin-right":"5px!important"}}," 免费次数 ",-1))),_={style:{"margin-bottom":"10px"}},k={style:{"margin-top":"0!important",display:"inline-block","justify-content":"center","align-items":"center"}},D=s((()=>(0,l._)("span",{style:{"margin-right":"5px!important"}}," 分数条件 ",-1))),V=s((()=>(0,l._)("span",{style:{"margin-right":"5px"}}," 分数 ",-1))),C={style:{display:"inline-block","text-align":"center","align-items":"center"}},S={style:{display:"inline-block","margin-left":"20px","text-align":"center","align-items":"center"}},T={style:{"margin-bottom":"10px"}},W=s((()=>(0,l._)("span",null," 若没有选中表格任何数据则将删除当下搜索到的所有数据。 若没有任何搜索字段将删除所有学生信息 ",-1))),N={class:"dialog-footer"},U={style:{"margin-top":"0!important",display:"inline-block","justify-content":"center","align-items":"center"}},B={class:"row mb-2"},z={class:"col-sm-5"},F=s((()=>(0,l._)("i",{class:"mdi mdi-plus-circle me-2"},null,-1))),j=s((()=>(0,l._)("label",{class:"form-label"},"姓名",-1))),E=s((()=>(0,l._)("label",{class:"form-label"},"学号",-1))),P=s((()=>(0,l._)("label",{class:"form-label"},"年级",-1))),O=s((()=>(0,l._)("label",{class:"form-label"},"免费次数",-1))),$=s((()=>(0,l._)("label",{class:"form-label"},"身份证号",-1))),L={class:"dialog-footer"},G={class:"dialog-footer"},H={class:"col-sm-7"},M={class:"text-sm-end"},A={href:"static/学生信息导入模板.xlsx",download:"学生信息导入模板"},K={style:{float:"right"}},Y={style:{float:"right"}};function Q(e,a,t,s,Q,R){const q=(0,l.up)("TopNav"),J=(0,l.up)("LeftNav"),X=(0,l.up)("el-input"),ee=(0,l.up)("el-button"),ae=(0,l.up)("el-option"),te=(0,l.up)("el-select"),le=(0,l.up)("el-dialog"),ie=(0,l.up)("el-upload"),ne=(0,l.up)("el-table-column"),se=(0,l.up)("el-button-group"),oe=(0,l.up)("el-table"),re=(0,l.up)("el-pagination"),de=(0,l.Q2)("loading");return(0,l.wg)(),(0,l.iD)("body",null,[(0,l._)("div",o,[(0,l.Wm)(q),(0,l.Wm)(J),(0,l._)("div",r,[(0,l._)("div",d,[(0,l._)("div",c,[h,(0,l._)("div",u,[(0,l._)("div",m,[(0,l._)("div",p,[(0,l._)("div",f,[(0,l._)("div",g,[(0,l._)("span",b,[I,(0,l.Wm)(X,{modelValue:Q.searchInfo.nameOrStudentNumber,"onUpdate:modelValue":a[0]||(a[0]=e=>Q.searchInfo.nameOrStudentNumber=e),placeholder:"输入姓名/学号",style:{display:"inline-block",width:"200px","margin-right":"0!important"}},null,8,["modelValue"]),(0,l._)("div",y,[(0,l.Wm)(ee,{icon:Q.Search,circle:"",onClick:R.nameOrStudentNumberSearch},null,8,["icon","onClick"])])])]),(0,l._)("div",w,[(0,l._)("span",x,[Z,(0,l.Wm)(X,{modelValue:Q.searchInfo.grade,"onUpdate:modelValue":a[1]||(a[1]=e=>Q.searchInfo.grade=e),placeholder:"输入年级如：2020",style:{display:"inline-block",width:"200px"}},null,8,["modelValue"]),v,(0,l.Wm)(te,{clearable:"",modelValue:Q.searchInfo.freeTime,"onUpdate:modelValue":a[2]||(a[2]=e=>Q.searchInfo.freeTime=e),class:"m-2",placeholder:"选择免费次数",style:{"margin-bottom":"0!important","margin-top":"0!important","margin-left":"0!important",display:"inline-block"}},{default:(0,l.w5)((()=>[((0,l.wg)(!0),(0,l.iD)(l.HY,null,(0,l.Ko)(Q.optionsFreeTime,(e=>((0,l.wg)(),(0,l.j4)(ae,{key:e.value,label:e.label,value:e.value},null,8,["label","value"])))),128))])),_:1},8,["modelValue"])])]),(0,l._)("div",_,[(0,l._)("span",k,[D,(0,l.Wm)(te,{clearable:"",modelValue:Q.searchInfo.scoreFilter,"onUpdate:modelValue":a[3]||(a[3]=e=>Q.searchInfo.scoreFilter=e),class:"m-2",placeholder:"选择分数条件",style:{"margin-bottom":"0!important","margin-top":"0!important","margin-left":"0!important",display:"inline-block"}},{default:(0,l.w5)((()=>[((0,l.wg)(!0),(0,l.iD)(l.HY,null,(0,l.Ko)(Q.optionsScore,(e=>((0,l.wg)(),(0,l.j4)(ae,{key:e.value,label:e.label,value:e.value},null,8,["label","value"])))),128))])),_:1},8,["modelValue"]),V,(0,l.Wm)(X,{modelValue:Q.searchInfo.score,"onUpdate:modelValue":a[4]||(a[4]=e=>Q.searchInfo.score=e),placeholder:"输入分数如：300",style:{display:"inline-block",width:"200px"}},null,8,["modelValue"]),(0,l._)("div",C,[(0,l.Wm)(ee,{icon:Q.Search,circle:"",onClick:a[5]||(a[5]=e=>R.otherSearch(this.currentPage,this.pageSize))},null,8,["icon"])]),(0,l._)("div",S,[(0,l.Wm)(ee,{type:"danger",icon:Q.Delete,circle:"",onClick:R.resetAll},null,8,["icon","onClick"])])])]),(0,l._)("div",T,[(0,l.Wm)(le,{"append-to-body":"",modelValue:Q.bulkDeletionVisible,"onUpdate:modelValue":a[7]||(a[7]=e=>Q.bulkDeletionVisible=e),title:"警告",width:"30%",center:""},{footer:(0,l.w5)((()=>[(0,l._)("span",N,[(0,l.Wm)(ee,{onClick:a[6]||(a[6]=e=>Q.bulkDeletionVisible=!1)},{default:(0,l.w5)((()=>[(0,l.Uk)("取消")])),_:1}),(0,l.Wm)(ee,{type:"primary",onClick:R.bulkDeletion},{default:(0,l.w5)((()=>[(0,l.Uk)(" 确认删除 ")])),_:1},8,["onClick"])])])),default:(0,l.w5)((()=>[W])),_:1},8,["modelValue"]),(0,l._)("span",U,[(0,l.Wm)(ee,{size:"small",type:"primary",onClick:R.bulkDeletionConfirm},{default:(0,l.w5)((()=>[(0,l.Uk)("批量删除")])),_:1},8,["onClick"])])]),(0,l._)("div",B,[(0,l._)("div",z,[(0,l._)("span",{onClick:a[8]||(a[8]=e=>Q.addInfoDialogVisible=!0),class:"btn btn-danger mb-2"},[F,(0,l.Uk)(" 添加学生信息")]),(0,l.Wm)(le,{modelValue:Q.addInfoDialogVisible,"onUpdate:modelValue":a[18]||(a[18]=e=>Q.addInfoDialogVisible=e),title:"学生信息添加",width:"30%",onClose:R.addInputInfoClose},{footer:(0,l.w5)((()=>[(0,l._)("span",L,[(0,l.Wm)(ee,{onClick:a[14]||(a[14]=e=>Q.addInfoDialogVisible=!1)},{default:(0,l.w5)((()=>[(0,l.Uk)("关闭")])),_:1}),(0,l.Wm)(ee,{type:"primary",onClick:R.addInput},{default:(0,l.w5)((()=>[(0,l.Uk)(" 确认 ")])),_:1},8,["onClick"]),(0,l.wy)((0,l.Wm)(ee,{text:"",onClick:a[15]||(a[15]=e=>Q.addInfoConfirmVisible=!0)},{default:(0,l.w5)((()=>[(0,l.Uk)(" Click to open the Dialog ")])),_:1},512),[[i.F8,!1]]),(0,l.Wm)(le,{modelValue:Q.addInfoConfirmVisible,"onUpdate:modelValue":a[17]||(a[17]=e=>Q.addInfoConfirmVisible=e),title:"消息确认",width:"30%",center:""},{footer:(0,l.w5)((()=>[(0,l._)("span",G,[(0,l.Wm)(ee,{onClick:a[16]||(a[16]=e=>Q.addInfoConfirmVisible=!1)},{default:(0,l.w5)((()=>[(0,l.Uk)("取消")])),_:1}),(0,l.Wm)(ee,{type:"primary",onClick:R.addInfoConfirm},{default:(0,l.w5)((()=>[(0,l.Uk)(" 确认 ")])),_:1},8,["onClick"])])])),default:(0,l.w5)((()=>[(0,l._)("span",null,(0,n.zw)(Q.confirmMessage),1)])),_:1},8,["modelValue"])])])),default:(0,l.w5)((()=>[(0,l._)("div",null,[j,(0,l.Wm)(X,{style:{"margin-bottom":"10px"},modelValue:Q.addInputInfo.name,"onUpdate:modelValue":a[9]||(a[9]=e=>Q.addInputInfo.name=e),placeholder:"输入姓名"},null,8,["modelValue"]),E,(0,l.Wm)(X,{style:{"margin-bottom":"10px"},modelValue:Q.addInputInfo.studentNum,"onUpdate:modelValue":a[10]||(a[10]=e=>Q.addInputInfo.studentNum=e),placeholder:"输入学号"},null,8,["modelValue"]),P,(0,l.Wm)(X,{style:{"margin-bottom":"10px"},modelValue:Q.addInputInfo.grade,"onUpdate:modelValue":a[11]||(a[11]=e=>Q.addInputInfo.grade=e),placeholder:"输入年级如：2020"},null,8,["modelValue"]),O,(0,l.Wm)(X,{style:{"margin-bottom":"10px"},modelValue:Q.addInputInfo.freeTime,"onUpdate:modelValue":a[12]||(a[12]=e=>Q.addInputInfo.freeTime=e),placeholder:"输入免费次数如：1"},null,8,["modelValue"]),$,(0,l.Wm)(X,{style:{"margin-bottom":"10px"},modelValue:Q.addInputInfo.studentId,"onUpdate:modelValue":a[13]||(a[13]=e=>Q.addInputInfo.studentId=e),placeholder:"身份证号（18位）"},null,8,["modelValue"])])])),_:1},8,["modelValue","onClose"])]),(0,l._)("div",H,[(0,l._)("div",M,[(0,l._)("a",A,[(0,l.Wm)(ee,{style:{"margin-right":"20px"},type:"primary",size:"large"},{default:(0,l.w5)((()=>[(0,l.Uk)("导入模板下载")])),_:1})]),(0,l.Wm)(ie,{ref:"upload",style:{display:"inline-block","margin-right":"20px"},action:"##","auto-upload":!1,"show-file-list":!1,"on-change":R.handleExcel},{default:(0,l.w5)((()=>[(0,l.Wm)(ee,{type:"primary",size:"large"},{default:(0,l.w5)((()=>[(0,l.Uk)("导入")])),_:1})])),_:1},8,["on-change"]),(0,l.Wm)(ee,{onClick:R.exportExcel,type:"success",size:"large"},{default:(0,l.w5)((()=>[(0,l.Uk)("导出")])),_:1},8,["onClick"])])]),(0,l.wy)(((0,l.wg)(),(0,l.j4)(oe,{fit:"",stripe:"true",id:"table_studentList",border:"",ref:"multipleTableRef",data:Q.tableData,style:{width:"100%"},onSelectionChange:R.handleSelectionChange},{default:(0,l.w5)((()=>[(0,l.Wm)(ne,{type:"selection",width:"55px"}),(0,l.Wm)(ne,{property:"studentNumber",label:"学号",width:"200px","show-overflow-tooltip":""}),(0,l.Wm)(ne,{property:"name",label:"姓名",width:"150px","show-overflow-tooltip":""}),(0,l.Wm)(ne,{property:"grade",label:"年级",width:"80px","show-overflow-tooltip":""}),(0,l.Wm)(ne,{property:"maxScore",label:"最高分",width:"80px","show-overflow-tooltip":""}),(0,l.Wm)(ne,{property:"freeTime",label:"免费次数","show-overflow-tooltip":""}),(0,l.Wm)(ne,{label:"操作",width:"120px"},{default:(0,l.w5)((e=>[(0,l.Wm)(se,{class:"ml-4"},{default:(0,l.w5)((()=>[(0,l.Wm)(ee,{onClick:a=>R.handleDelete(e.$index),type:"primary",icon:Q.Delete},null,8,["onClick","icon"])])),_:2},1024)])),_:1})])),_:1},8,["data","onSelectionChange"])),[[de,Q.tableLoading]]),(0,l._)("div",K,[(0,l._)("div",Y,[(0,l.Wm)(re,{style:{"margin-top":"20px"},"current-page":Q.currentPage,"onUpdate:current-page":a[19]||(a[19]=e=>Q.currentPage=e),"page-size":Q.pageSize,"onUpdate:page-size":a[20]||(a[20]=e=>Q.pageSize=e),"page-sizes":[5,10,15,20],small:"small",layout:"total, sizes, prev, pager, next, jumper,",total:Q.totalDataNum,onSizeChange:R.handleSizeChange,onCurrentChange:R.handleCurrentChange},null,8,["current-page","page-size","total","onSizeChange","onCurrentChange"])])])])])])])])])])])])])}t(7658);var R=t(9308),q=t(3274),J=t(5781),X=t(6133),ee=t(594),ae={name:"studentList.vue",components:{TopNav:R.Z,LeftNav:q.Z},data(){return{Search:J.olm,Delete:J.HG3,Edit:J.I8b,searchInfo:{nameOrStudentNumber:"",grade:"",freeTime:"",scoreFilter:"",score:""},addInfoConfirmVisible:!1,addInfoDialogVisible:!1,confirmMessage:"",addInputInfo:{studentNum:"",studentId:"",name:"",grade:"",freeTime:""},isConfirm:!1,confirmType:1,optionsFreeTime:[{value:1,label:"0"},{value:2,label:"1"}],optionsScore:[{value:1,label:"大于"},{value:2,label:"小于"},{value:3,label:"等于"}],tableData:[],totalDataNum:0,currentPage:1,pageSize:5,tableLoading:!1,selection:[],bulkDeletionVisible:!1,currentExportUrl:""}},methods:{async exportExcel(){if(0===this.tableData.length)return void X.Z.messageBox("没有数据可以导出","success");let e="";""===this.searchInfo.nameOrStudentNumber||""===this.searchInfo.freeTime||""===this.searchInfo.grade||""===this.searchInfo.score||""===this.searchInfo.scoreFilter?e=`teacher/list/0/${this.tableData[0].totalSize+1}`:""===this.searchInfo.grade&&""===this.searchInfo.freeTime&&""===this.searchInfo.scoreFilter&&""===this.searchInfo.score||(e=this.getSearchUrl(),e+=`0/${this.tableData[0].totalSize+1}`),console.log(e);let a=X.Z.loadingWait("下载导出数据中","table_studentList");await X.Z.delay(100),await(0,ee.Z)({method:"GET",url:e,headers:{"content-type":"application/x-www-form-urlencoded;charset=UTF-8"}}).then((async e=>{await X.Z.delay(100),a.close(),X.Z.messageBox("拉取数据成功","success");let t=[];for(let a=0;a<e.data.data.length;a++)t.push({"学号":e.data.data[a]["studentNumber"],"姓名":e.data.data[a]["name"],"年级":e.data.data[a]["grade"],"最高分":e.data.data[a]["maxScore"],"免费次数":e.data.data[a]["freeTime"]});X.Z.exportExcel(t),X.Z.messageBox("导出成功","success")})).catch((async()=>{await X.Z.delay(100),a.close(),X.Z.messageBox("拉取数据失败，导出失败","error")}))},async singleDelete(e){await(0,ee.Z)({headers:{"content-Type":"application/json"},method:"post",url:"/teacher/admin/remove",data:e}).then((()=>{X.Z.messageBox("删除成功","success"),this.getInitTableData(this.currentPage,this.pageSize)})).catch((()=>{X.Z.messageBox("删除失败","success")}))},async getSearchUrl(){if(""===this.searchInfo.grade.toString()&&""===this.searchInfo.score&&""===this.searchInfo.scoreFilter&&""===this.searchInfo.freeTime)return"";if(this.searchInfo.grade.length>0&&(4!==this.searchInfo.grade.length||!1===X.Z.judgeInputIsNumber(this.searchInfo.grade)))return X.Z.messageBox("年级格式错误","error"),"";if(this.searchInfo.score.length>0&&!1===X.Z.judgeInputIsNumber(this.searchInfo.score))return X.Z.messageBox("分数格式输入错误","error"),"";if(""===this.searchInfo.scoreFilter&&""!==this.searchInfo.score||""!==this.searchInfo.scoreFilter&&""===this.searchInfo.score)return X.Z.messageBox("分数条件与分数必须同时选择一个值","error"),"";let e=""===this.searchInfo.score?null:this.searchInfo.score,a=""===this.searchInfo.scoreFilter?null:this.searchInfo.scoreFilter,t=""===this.searchInfo.grade?null:this.searchInfo.grade+"-09-01",l=""===this.searchInfo.freeTime?null:this.searchInfo.freeTime-1;return`teacher/filter/${e}/${a}/${t}/${l}/`},async bulkDeletion(){if(0===this.selection.length){let e=await this.getSearchUrl();if(""===e)X.Z.messageBox("批量删除需要选择字段","warning");else{e=e.slice(0,e.length-1);let a=X.Z.loadingWait("删除中");await X.Z.delay(100),await(0,ee.Z)({url:e,headers:{"content-type":"application/x-www-form-urlencoded;charset=UTF-8"},method:"POST"}).then((()=>{a.close(),X.Z.delay(100),X.Z.messageBox("删除成功","success"),this.bulkDeletionVisible=!1,this.getInitTableData(1,this.pageSize)})).catch((()=>{a.close(),X.Z.delay(100),X.Z.messageBox("删除失败","error")}))}}else{let e=[];this.selection.forEach((a=>{e.push(a.id)})),await(0,ee.Z)({headers:{"content-Type":"application/json"},method:"post",url:"/teacher/admin/remove",data:e}).then((()=>{X.Z.messageBox("删除成功","success"),this.bulkDeletionVisible=!1,this.getInitTableData(this.currentPage,this.pageSize)})).catch((()=>{X.Z.messageBox("删除失败","success")}))}},bulkDeletionConfirm(){this.bulkDeletionVisible=!0},resetAll(){this.searchInfo.freeTime="",this.searchInfo.grade="",this.searchInfo.nameOrStudentNumber="",this.searchInfo.scoreFilter=""},async otherSearch(e,a){let t=await this.getSearchUrl();if(""===t)return void await this.getInitTableData(1,this.pageSize);t+=`${e}/${a}`;let l=X.Z.loadingWait("搜索中","table_studentList");await X.Z.delay(100),this.currentExportUrl=t,await(0,ee.Z)({url:t,method:"GET",headers:{"content-type":"application/x-www-form-urlencoded;charset=UTF-8"}}).then((e=>{X.Z.delay(100),l.close(),this.tableData=e.data.data,0!==this.tableData.length?this.totalDataNum=e.data.data[0].totalSize:(this.totalDataNum=0,this.currentPage=1),X.Z.messageBox("查询成功","success")})).catch((()=>{X.Z.delay(100),l.close(),X.Z.messageBox("搜索失败","error")}))},async nameOrStudentNumberSearch(){if(X.Z.print(this.searchInfo.nameOrStudentNumber),0===this.searchInfo.nameOrStudentNumber.length)return void await this.getInitTableData(1,this.pageSize);let e=X.Z.loadingWait("搜索中。。。","table_studentList");await X.Z.delay(50);let a=this.searchInfo.nameOrStudentNumber;if(!1===X.Z.judgeInputIsNumber(a)){let t=`teacher/filter/${a}/null`;await(0,ee.Z)({method:"GET",headers:{"content-type":"application/x-www-form-urlencoded;charset=UTF-8"},url:t}).then((async a=>{await X.Z.delay(50),e.close(),X.Z.messageBox("查询成功","success"),this.tableData=a.data.data,this.currentPage=1,this.totalDataNum=a.data.data.length})).catch((async a=>{X.Z.print(a),await X.Z.delay(50),e.close(),X.Z.messageBox("查询失败","error")}))}else{let t=`teacher/filter/null/${a}`;await ee.Z.get(t).then((async a=>{await X.Z.delay(50),X.Z.print(a),e.close(),X.Z.messageBox("查询成功","success"),this.tableData=a.data.data,this.currentPage=1,this.totalDataNum=1})).catch((async()=>{await X.Z.delay(50),e.close(),X.Z.messageBox("查询失败","error")}))}this.searchInfo.nameOrStudentNumber=""},async addInfoConfirm(){let e=X.Z.loadingWait("正在导入中......");if(await X.Z.delay(100),1===this.confirmType){let a={name:this.addInputInfo.name,studentNumber:this.addInputInfo.studentNum,enrollmentTime:this.addInputInfo.grade+"-9-1",idNumber:this.addInputInfo.studentId,freeTime:this.addInputInfo.freeTime};await(0,ee.Z)({headers:{"content-Type":"application/json"},method:"post",url:"/teacher/import/freshman",data:[a]}).then((()=>{e.close(),X.Z.messageBox("导入成功","success"),this.addInfoDialogVisible=!1})).catch((()=>{e.close(),X.Z.messageBox("导入失败","error")}))}this.addInfoConfirmVisible=!1,this.addInputInfoClose()},addInput(){return""===this.addInputInfo.studentNum||""===this.addInputInfo.name||""===this.addInputInfo.studentNum||""===this.addInputInfo.studentId||""===this.addInputInfo.freeTime?(X.Z.messageBox("请将信息填写完整","warning"),void(this.addInfoDialogVisible=!0)):this.addInputInfo.studentId.length<18?(X.Z.messageBox("身份证号输入过短","error"),void(this.addInfoDialogVisible=!0)):this.addInputInfo.studentId.length>18?(X.Z.messageBox("身份证号输入过长","error"),X.Z.messageBox("请将信息填写完整","warning"),void(this.addInfoDialogVisible=!0)):(this.addInfoDialogVisible=!0,this.confirmMessage="是否确认添加信息",this.addInfoConfirmVisible=!0,void(this.confirmType=1))},addInputInfoClose(){this.addInputInfo.name="",this.addInputInfo.studentNum="",this.addInputInfo.studentId="",this.addInputInfo.grade="",this.addInputInfo.freeTime="",this.addInfoConfirmVisible=!1},async handleDelete(e){let a=this.tableData[e].id;await(0,ee.Z)({headers:{"content-Type":"application/json"},method:"post",url:"/teacher/admin/remove",data:[a]}).then((()=>{X.Z.messageBox("删除成功","success"),this.getInitTableData(this.currentPage,this.pageSize)})).catch((()=>{X.Z.messageBox("删除失败","success")}))},handleSelectionChange(e){this.selection=e},handleEdit(){},async getInitTableData(e,a){let t=`teacher/list/${e}/${a}`;this.currentExportUrl=t;let l=X.Z.loadingWait("获取学生信息中。。。","table_studentList");await X.Z.delay(50),await ee.Z.get(t).then((async a=>{if(await X.Z.delay(100),l.close(),0===a.data.data.length)return X.Z.messageBox("暂无有更多信息","warning"),this.tableData=[],void(this.totalDataNum=0);this.currentPage=e,this.tableData=a.data.data,this.totalDataNum=a.data.data[0].totalSize,X.Z.messageBox("获取学生信息成功","success")})).catch((async()=>{await X.Z.delay(100),l.close(),X.Z.messageBox("获取学生信息失败","error")}))},async handleSizeChange(e){this.pageSize=e,this.currentPage=1;let a=await this.getSearchUrl();""===a?await this.getInitTableData(this.currentPage,e):await this.otherSearch(this.currentPage,e)},async handleCurrentChange(e){this.currentPage=e;let a=await this.getSearchUrl();""===a?await this.getInitTableData(e,this.pageSize):await this.otherSearch(e,this.pageSize)},async handleExcel(e){let a=t(4105),l=e.raw;if(!l)return;let i=X.Z.loadingWait("解析表格中......");await X.Z.delay(100);let n=await X.Z.readFile(l),s=a.read(n,{type:"binary"}),o=s.Sheets[s.SheetNames[0]],r=a.utils.sheet_to_json(o),d=Object.keys(r[0]);if("学号"!==d[0]||"姓名"!==d[1]||"年级"!==d[2]||"身份证号"!==d[3]||"免费次数"!==d[4])return i.close(),void X.Z.messageBox("解析错误：导入表格列明不正确","error");let c=[],h=1;if(r.forEach((function(e){let a=Object.values(e);18===a[3].length&&4===a[2].length||(h=0),c.push({studentNumber:a[0],name:a[1],enrollmentTime:X.Z.dateTranslate(a[2]),idNumber:a[3],freeTime:a[4]})})),0===h)return i.close(),void X.Z.messageBox("导入错误：数据格式不正确","error");await X.Z.delay(100),i.close(),X.Z.messageBox("解析成功","success"),this.$refs.upload.clearFiles(),await this.uploadExcelInfo(c),this.tableData=c,this.totalDataNum=c.length},async uploadExcelInfo(e){let a=X.Z.loadingWait("上传信息中。。。");await X.Z.delay(50);let t=1;return await(0,ee.Z)({headers:{"content-type":"application/json"},method:"post",url:"/teacher/import/freshman",data:e}).then((()=>{})).catch((()=>{t=0})),await X.Z.delay(100),a.close(),0===t?(X.Z.messageBox("上传学生信息失败","error"),0):(X.Z.messageBox("上传学生信息成功","success"),1)}},mounted(){this.getInitTableData(1,5)}},te=t(3744);const le=(0,te.Z)(ae,[["render",Q],["__scopeId","data-v-f6faa3c0"]]);var ie=le}}]);
//# sourceMappingURL=783.1124745d.js.map