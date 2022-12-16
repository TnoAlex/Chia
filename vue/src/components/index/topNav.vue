<template>
  <div  class="navbar-custom topnav-navbar">
      <!-- Sidebar Menu Toggle Button -->
      <button class="button-toggle-menu">
        <i class="mdi mdi-menu"></i>
      </button>
      <!-- Horizontal Menu Toggle Button -->
      <button class="navbar-toggle" data-bs-toggle="collapse" data-bs-target="#topnav-menu-content">
        <div class="lines">
          <span></span>
          <span></span>
          <span></span>
        </div>
      </button>
      <ul class="list-unstyled topbar-menu float-end mb-0">
        <li class="dropdown notification-list d-lg-none">

          <div class="dropdown-menu dropdown-menu-animated dropdown-lg p-0">
            <form class="p-3">
              <input type="search" class="form-control" placeholder="搜索 ..." aria-label="Recipient's username">
            </form>
          </div>
        </li>
        <li class="dropdown notification-list">
          
          <el-dropdown placement="bottom-start" >
            <span  style="display: inline-block;width: 300px" class="nav-link  nav-user ">
              <div class="account-user-avatar" style="display: flex; justify-content: center; align-items: center;margin-left: 0;margin-right: 0">
                <img class="rounded-circle" src="../../assets/img/avatar.jpg">
                <span style="margin-left: 5px" >{{userInfo.userName}}</span>
              </div>
            </span>
            <template #dropdown>
              <el-dropdown-menu>
                <el-dropdown-item>
                  <router-link :to="{path:'/personal_info'}" class="dropdown-item notify-item">
                    <i class="mdi mdi-account-circle me-1"></i>
                    <span>我的账号</span>
                  </router-link>
                </el-dropdown-item>
                <el-dropdown-item >
                  <span  @click="loginOut" class="dropdown-item notify-item">
                    <i class="mdi mdi-logout me-1"></i>
                    <span>登出</span>
                  </span>
                </el-dropdown-item>
              </el-dropdown-menu>
            </template>
          </el-dropdown>
        </li>
      </ul>
  </div>
</template>
<script>
import util from '../../utils/commonUtil'
import axios from "axios";

export default {
  name: "topNav.vue",
  data(){
    return{
      util,
      userInfo:{}
    }
  },
  methods:{
    async loginOut()
    {
      let loading = util.loadingWait('退出登录中。。。')
      await util.delay(100)
      await axios({
        method: 'POST',
        url: '/logout'
      }).then(async () => {
        loading.close()
        await util.delay(100)
        util.messageBox('退出登录成功，回到登录页面', 'success')
        this.$router.push('/')
      }).catch(async () => {
        loading.close()
        await util.delay(100)
        util.messageBox('退出登录失败', 'error')
      })

    }
  },
  beforeMount() {
    this.userInfo = this.$cookies.get('userInfo')
  }
}
</script>
<style scoped src="../../assets/css/index_css/icons.min.css"></style>
<!--<style scoped src="../../assets/css/index_css/app-saas.min.css"></style>-->
<!--<style scoped src="../../assets/css/index_css/jquery-jvectormap-1.2.2.css"></style>-->
<!--<style scoped src="bootstrap-daterangepicker/daterangepicker.css"></style>-->