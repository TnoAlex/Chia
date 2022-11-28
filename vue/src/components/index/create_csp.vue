<template>
  <body>
    <div class="wrapper">
      <TopNav></TopNav>
      <LeftNav></LeftNav>
      <div class="content-page">

        <div class="content" style="width: 500px;height: 500px;">

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
                  <h4 class="page-title">发布CSP预报名</h4>
                </div>
              </div>
            </div>
            <!-- end page title -->

            <div class="row">
              <div class="col-12">
                <div class="card">
                  <div class="card-body">
                    <div  >
                      <div class="col-xl-5" style="width: 100%;height: 100%">
                        <div class="mb-3" >
                          <label for="projectname" class="form-label">{{cspName}}</label>
                          <input type="text" id="projectname" class="form-control" placeholder="输入预报名信息">
                        </div>
                        <div class="mb-3 position-relative" id="datepicker1">
                          <label class="form-label">开始时间</label>
                          <div class="block">
                            <el-date-picker
                                v-model="valueStart"
                                type="datetime"
                                placeholder="选择开始时间"
                                format="YYYY/MM/DD HH:mm:ss"
                            />
                          </div>
                        </div>
                        <div class="mb-3 position-relative" id="datepicker1">
                          <label class="form-label">截止时间</label>
                          <div class="block">
                            <el-date-picker
                                v-model="valueEnd"
                                type="datetime"
                                placeholder="选择截止时间"
                                format="YYYY/MM/DD HH:mm:ss"
                            />
                          </div>
                        </div>

                        <el-button  @click="createCsp"  type="primary" round>发布</el-button>
                      </div>


                    </div>
                    <!-- end row -->

                  </div> <!-- end card-body -->
                </div> <!-- end card-->
              </div> <!-- end col-->
            </div>
            <!-- end row-->

          </div> <!-- container -->

        </div>

      </div>
    </div>
  </body>
</template>

<script>
import topNav from './topNav'
import leftNav from './leftNav'
import messageBox from "@/utils/commonUtil";
export default {
  components:{
    TopNav: topNav,
    LeftNav:leftNav
  },
  data()
  {
    return{
      valueStart:'',
      valueEnd:'',
      cspName:"",
      value1:"",

    }


  },methods:{
    createCsp()
    {
      console.log(this.valueStart)
      console.log(this.valueEnd)
      console.log(this.valueEnd-this.valueStart)
      if(this.cspName==='' || this.valueStart===''||this.valueEnd==='')
      {
        messageBox('请将信息填写完整·', 'warning')
        return
      }
      if(this.valueEnd-this.valueStart<=0)
      {
        messageBox('时间选择错误', 'warning')
      }


    }

  },

  name: "create_csp"

}
</script>

<style scoped src="../../assets/css/index_css/icons.min.css"></style>
<style scoped src="../../assets/css/index_css/app-saas.min.css"></style>
<style scoped src="../../assets/css/index_css/jquery-jvectormap-1.2.2.css"></style>
<style scoped src="bootstrap-daterangepicker/daterangepicker.css"></style>