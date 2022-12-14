<template>
  <div  class="leftside-menu">
    <!-- Logo Light -->
    <a href="" class="logo logo-light">
                    <span class="logo-lg" style="height: 100%;width: 100%;">
                        <img src="../../assets/picture/csp_icon.png" style="width: 85%;height: 85%;"  alt="logo" height="22">
                    </span>
    </a>
    <!-- Logo Dark -->
    <a href="" class="logo logo-dark">
                    <span class="logo-lg">
                        <img src="../../assets/picture/csp_icon.png" style="width: 85%;height: 85%;"  alt="dark logo" height="22">
                    </span>
    </a>
    <!-- Sidebar Hover Menu Toggle Button -->
    <button type="button" class="btn button-sm-hover p-0" data-bs-toggle="tooltip" data-bs-placement="right" title="Show Full Sidebar">
      <i class="ri-checkbox-blank-circle-line align-middle"></i>
    </button>
    <!-- Sidebar -left -->
    <div class="h-100" id="leftside-menu-container" data-simplebar="">
      <ul class="side-nav">
        <li class="side-nav-title side-nav-item">导航栏</li>
        <li class="side-nav-item">
          <a data-bs-toggle="collapse" href="#sidebarDashboards" aria-expanded="false" aria-controls="sidebarDashboards" class="side-nav-link">
            <i class="uil-home-alt"></i>
            <span class="badge bg-success float-end">2</span>
            <span> 主页 </span>
          </a>
          <div class="collapse" id="sidebarDashboards">
            <ul class="side-nav-second-level">
              <li>
                <router-link :to="{path:'/index'}">回到主页</router-link>
              </li>

              <li>
                <router-link :to="{path:'/message'}">通知</router-link>
              </li>
            </ul>
          </div>
        </li>
        <li v-show="userInfo.type===1"
            class="side-nav-title side-nav-item">学生教师信息管理</li>
        <li class="side-nav-item" v-if="userInfo.type===1">
          <router-link :to="{path:'/teacherManager'}" class="side-nav-link">
            <i class="uil-table">
            </i>
            <span> 教师账号管理 </span>
          </router-link>

          <router-link :to="{path:'/studentList'}" class="side-nav-link">
            <i class="uil-table">
            </i>
            <span> 学生信息表 </span>
          </router-link>

          <router-link :to="{path:'/officialEnroll'}" class="side-nav-link">
            <i class="uil-table">
            </i>
            <span> 正式报名比对</span>
          </router-link>
        </li>
      </ul>
      <div class="clearfix"></div>
    </div>
  </div>
</template>
<script>
import util from '../../utils/commonUtil'
import {js1} from '../../assets/js/hyper-config'
import {js8} from '../../assets/js/moment.min'
import {js9} from '../../assets/js/vendor.min'
import {js3} from '../../assets/js/apexcharts.min'
import cookies from "vue-cookies";

export default {
  name: "leftBar.vue",
  data(){
    return{
      util,
      js1,
      js8,
      js9,
      js3,
      userInfo: {}
    }
  },
  created() {
    this.userInfo = cookies.get('userInfo')
  }
}
</script>
<style scoped src="../../assets/css/index_css/icons.min.css"></style>
<style scoped src="../../assets/css/index_css/app-saas.min.css"></style>
<style scoped src="../../assets/css/index_css/jquery-jvectormap-1.2.2.css"></style>