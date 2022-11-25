<template>
  <body>
    <div class="wrapper">
      <TopNav></TopNav>
      <LeftNav></LeftNav>

    </div>
  </body>
</template>

<script>
import topNav from './topNav'
import leftNav from './leftNav'
export default {
  components:{
    TopNav: topNav,
    LeftNav:leftNav
  },
  name: "create_csp"

}
</script>

<style scoped>
@import "bootstrap-daterangepicker/daterangepicker.css";
@import "../../assets/css/index_css/icons.min.css";
@import "../../assets/css/index_css/app-saas.min.css";
@import "../../assets/css/index_css/jquery-jvectormap-1.2.2.css";
</style>