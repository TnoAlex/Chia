<template>
  <div  class="leftside-menu">

    <!-- Logo Light -->
    <a href="" class="logo logo-light">
                    <span class="logo-lg" style="height: 100%;width: 100%;">
                        <img src="../../assets/picture/csp_icon.png" style="width: 85%;height: 85%;"  alt="logo" height="22">
                    </span>
      <span class="logo-sm">
                        <img src="../../assets/picture/logo-sm.png" alt="small logo" height="22">
                    </span>
    </a>

    <!-- Logo Dark -->
    <a href="" class="logo logo-dark">
                    <span class="logo-lg">
                        <img src="../../assets/picture/csp_icon.png" style="width: 85%;height: 85%;"  alt="dark logo" height="22">
                    </span>
      <span class="logo-sm">
                        <img src="../../assets/picture/logo-dark-sm.png" alt="small logo" height="22">
                    </span>
    </a>

    <!-- Sidebar Hover Menu Toggle Button -->
    <button type="button" class="btn button-sm-hover p-0" data-bs-toggle="tooltip" data-bs-placement="right" title="Show Full Sidebar">
      <i class="ri-checkbox-blank-circle-line align-middle"></i>
    </button>
    <!-- Sidebar -left -->
    <div class="h-100" id="leftside-menu-container" data-simplebar="">
      <!-- Leftbar User -->
      <div class="leftbar-user">
        <a href="pages-profile.html">
          <img src="../../assets/picture/avatar-1.jpg" alt="user-image" height="42" class="rounded-circle shadow-sm">
          <span class="leftbar-user-name">Dominic Keller</span>
        </a>
      </div>

      <!--- Sidemenu -->
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
                <router-link :to="{path:'/create_csp'}">发布CSP预报名</router-link>
              </li>

            </ul>
          </div>
        </li>

        <li class="side-nav-title side-nav-item">学生信息管理</li>

        <li class="side-nav-item">
          <router-link :to="{path:'/studentList'}" class="side-nav-link">
            <i class="uil-table">
            </i>
            <span> 学生报名表 </span>
          </router-link>
        </li>

        <li class="side-nav-title side-nav-item">Custom</li>

        <li class="side-nav-item">
          <a data-bs-toggle="collapse" href="#sidebarPages" aria-expanded="false" aria-controls="sidebarPages" class="side-nav-link">
            <i class="uil-copy-alt"></i>
            <span> Pages </span>
            <span class="menu-arrow"></span>
          </a>
          <div class="collapse" id="sidebarPages">
            <ul class="side-nav-second-level">
              <li>
                <a href="pages-profile.html">Profile</a>
              </li>
              <li>
                <a href="pages-profile-2.html">Profile 2</a>
              </li>
              <li>
                <a href="pages-invoice.html">Invoice</a>
              </li>
              <li>
                <a href="pages-faq.html">FAQ</a>
              </li>
              <li>
                <a href="pages-pricing.html">Pricing</a>
              </li>
              <li>
                <a href="pages-maintenance.html">Maintenance</a>
              </li>
              <li class="side-nav-item">
                <a data-bs-toggle="collapse" href="#sidebarPagesAuth" aria-expanded="false" aria-controls="sidebarPagesAuth">
                  <span> Authentication </span>
                  <span class="menu-arrow"></span>
                </a>
                <div class="collapse" id="sidebarPagesAuth">
                  <ul class="side-nav-third-level">
                    <li>
                      <a href="pages-login.html">Login</a>
                    </li>
                    <li>
                      <a href="pages-login-2.html">Login 2</a>
                    </li>
                    <li>
                      <a href="pages-register.html">Register</a>
                    </li>
                    <li>
                      <a href="pages-register-2.html">Register 2</a>
                    </li>
                    <li>
                      <a href="pages-logout.html">Logout</a>
                    </li>
                    <li>
                      <a href="pages-logout-2.html">Logout 2</a>
                    </li>
                    <li>
                      <a href="pages-recoverpw.html">Recover Password</a>
                    </li>
                    <li>
                      <a href="pages-recoverpw-2.html">Recover Password 2</a>
                    </li>
                    <li>
                      <a href="pages-lock-screen.html">Lock Screen</a>
                    </li>
                    <li>
                      <a href="pages-lock-screen-2.html">Lock Screen 2</a>
                    </li>
                    <li>
                      <a href="pages-confirm-mail.html">Confirm Mail</a>
                    </li>
                    <li>
                      <a href="pages-confirm-mail-2.html">Confirm Mail 2</a>
                    </li>
                  </ul>
                </div>
              </li>
              <li class="side-nav-item">
                <a data-bs-toggle="collapse" href="#sidebarPagesError" aria-expanded="false" aria-controls="sidebarPagesError">
                  <span> Error </span>
                  <span class="menu-arrow"></span>
                </a>
                <div class="collapse" id="sidebarPagesError">
                  <ul class="side-nav-third-level">
                    <li>
                      <a href="pages-404.html">Error 404</a>
                    </li>
                    <li>
                      <a href="pages-404-alt.html">Error 404-alt</a>
                    </li>
                    <li>
                      <a href="pages-500.html">Error 500</a>
                    </li>
                  </ul>
                </div>
              </li>
              <li>
                <a href="pages-starter.html">Starter Page</a>
              </li>
              <li>
                <a href="pages-preloader.html">With Preloader</a>
              </li>
              <li>
                <a href="pages-timeline.html">Timeline</a>
              </li>
            </ul>
          </div>
        </li>
        <li class="side-nav-title side-nav-item mt-1">Components</li>
        <li class="side-nav-item">
          <a data-bs-toggle="collapse" href="#sidebarBaseUI" aria-expanded="false" aria-controls="sidebarBaseUI" class="side-nav-link">
            <i class="uil-box"></i>
            <span> Base UI </span>
            <span class="menu-arrow"></span>
          </a>
          <div class="collapse" id="sidebarBaseUI">
            <ul class="side-nav-second-level">
              <li>
                <a href="ui-accordions.html">Accordions & Collapse</a>
              </li>
              <li>
                <a href="ui-alerts.html">Alerts</a>
              </li>
              <li>
                <a href="ui-avatars.html">Avatars</a>
              </li>
              <li>
                <a href="ui-badges.html">Badges</a>
              </li>
              <li>
                <a href="ui-breadcrumb.html">Breadcrumb</a>
              </li>
              <li>
                <a href="ui-buttons.html">Buttons</a>
              </li>
              <li>
                <a href="ui-cards.html">Cards</a>
              </li>
              <li>
                <a href="ui-carousel.html">Carousel</a>
              </li>
              <li>
                <a href="ui-dropdowns.html">Dropdowns</a>
              </li>
              <li>
                <a href="ui-embed-video.html">Embed Video</a>
              </li>
              <li>
                <a href="ui-grid.html">Grid</a>
              </li>
              <li>
                <a href="ui-list-group.html">List Group</a>
              </li>
              <li>
                <a href="ui-modals.html">Modals</a>
              </li>
              <li>
                <a href="ui-notifications.html">Notifications</a>
              </li>
              <li>
                <a href="ui-offcanvas.html">Offcanvas</a>
              </li>
              <li>
                <a href="ui-placeholders.html">Placeholders</a>
              </li>
              <li>
                <a href="ui-pagination.html">Pagination</a>
              </li>
              <li>
                <a href="ui-popovers.html">Popovers</a>
              </li>
              <li>
                <a href="ui-progress.html">Progress</a>
              </li>
              <li>
                <a href="ui-ribbons.html">Ribbons</a>
              </li>
              <li>
                <a href="ui-spinners.html">Spinners</a>
              </li>
              <li>
                <a href="ui-tabs.html">Tabs</a>
              </li>
              <li>
                <a href="ui-tooltips.html">Tooltips</a>
              </li>
              <li>
                <a href="ui-typography.html">Typography</a>
              </li>
              <li>
                <a href="ui-utilities.html">Utilities</a>
              </li>
            </ul>
          </div>
        </li>
      </ul>

      <div class="clearfix"></div>
    </div>
  </div>
</template>

<script>

export default {
  name: "leftBar.vue"
}
</script>
<style scoped src="../../assets/css/index_css/icons.min.css"></style>
<style scoped src="../../assets/css/index_css/app-saas.min.css"></style>
<style scoped src="../../assets/css/index_css/jquery-jvectormap-1.2.2.css"></style>
<style scoped src="bootstrap-daterangepicker/daterangepicker.css"></style>