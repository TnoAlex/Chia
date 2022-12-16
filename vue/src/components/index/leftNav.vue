<template>
  <div class="leftside-menu">
    <!-- Logo Light -->
    <div class="d-flex justify-content-center">
      <router-link class="logo d-flex align-items-center w-auto" style="position: relative;margin-bottom: 40px" to="/">
        <img alt="" src="@/assets/img/logo.png">
        <span class="d-none d-lg-block">NiceCSP</span>
      </router-link>
    </div>
    <!-- Sidebar Hover Menu Toggle Button -->
    <button type="button" class="btn button-sm-hover p-0" data-bs-toggle="tooltip" data-bs-placement="right"
            title="Show Full Sidebar">
      <i class="ri-checkbox-blank-circle-line align-middle"></i>
    </button>
    <!-- Sidebar -left -->
    <div class="h-100" id="leftside-menu-container" data-simplebar="">
      <ul class="side-nav">
        <li class="side-nav-title side-nav-item">导航栏</li>
        <li class="side-nav-item">
          <router-link :to="{path:'/index'}" class="side-nav-link">
            <i class="uil-home"></i>
            <span> 主页 </span>
          </router-link>
          <router-link :to="{path:'/message'}" class="side-nav-link">
            <i class="uil-message">
            </i>
            <span> 通知 </span>
          </router-link>
        </li>
        <li v-show="userInfo.type===1"
            class="side-nav-title side-nav-item">学生教师信息管理</li>
        <li class="side-nav-item" v-if="userInfo.type===1">
          <router-link :to="{path:'/teacherManager'}" class="side-nav-link">
            <i class="bi bi-person-badge"></i>
            <span> 教师账号管理 </span>
          </router-link>

          <router-link :to="{path:'/studentList'}" class="side-nav-link">
            <i class="bi bi-person-video2"></i>
            <span> 学生信息表 </span>
          </router-link>

          <router-link :to="{path:'/officialEnroll'}" class="side-nav-link">
            <i class="bi bi-calendar3-week"></i>
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
import "bootstrap-icons/font/bootstrap-icons.css"

export default {
  name: "leftBar.vue",
  data() {
    return {
      util,
      js1,
      js8,
      js9,
      js3,
      userInfo: {}
    }
  },
  beforeMount() {
    this.userInfo = this.$cookies.get('userInfo')
  }
}
</script>
<style scoped src="../../assets/css/index_css/icons.min.css"></style>
<style scoped src="../../assets/css/index_css/app-saas.min.css"></style>
<style scoped src="../../assets/css/forget_password_style.css">
</style>