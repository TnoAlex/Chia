<template>
  <body>
  <div class="wrapper">
    <TopNav>

    </TopNav>
    <LeftNav>

    </LeftNav>
    <div class="content-page">
      <div class="content">

        <!-- Start Content-->
        <div class="container-fluid">

          <!-- start page title -->
          <div class="row">
            <div class="col-12">
              <div class="page-title-box">
                <h4 class="page-title">历次CSP列表</h4>
              </div>
            </div>
          </div>
          <!-- end page title -->

          <div class="row mb-2">
            <div class="col-sm-4">
              <router-link :to="{path:'/create_csp'}" class="btn btn-danger rounded-pill mb-3"><i class="mdi mdi-plus"></i> 发布CSP预报名</router-link>
            </div>

          </div>
          <!-- end row-->

          <div class="row">
            <div class="col-md-6 col-xxl-3" style="width: 300px;height: 300px;">
              <!-- project card -->
              <div class="card d-block">
                <div class="card-body">
                  <div class="dropdown card-widgets">
                    <a href="#" class="dropdown-toggle arrow-none" data-bs-toggle="dropdown" aria-expanded="false">
                      <i class="ri-more-fill"></i>
                    </a>
                    <div class="dropdown-menu dropdown-menu-end">
                      <a href="javascript:void(0);" class="dropdown-item"><i class="mdi mdi-pencil me-1"></i>编辑</a>
                      <a href="javascript:void(0);" class="dropdown-item"><i class="mdi mdi-delete me-1"></i>删除</a>
                    </div>
                  </div>
                  <!-- project title-->
                  <h4 class="mt-0">
                    <a href="apps-projects-details.html" class="text-title">第28次CSP</a>
                  </h4>
                  <div class="badge bg-success">已完成</div>

                  <!-- project detail-->
                  <p class="mb-1">
                                            <span class="pe-2 text-nowrap mb-2 d-inline-block">
                                                <i class="mdi mdi-human-queue text-muted"></i>
                                                <b>21</b> 报名人数
                                            </span>
                    <span class="text-nowrap mb-2 d-inline-block">
                                                <i class="mdi mdi-timeline-clock-outline text-muted"></i>
                                                <b>2022/11/21 00:00:00开始时间</b>
                                            </span>
                    <span class="text-nowrap mb-2 d-inline-block">
                                                <i class="mdi mdi-timeline-clock-outline text-muted"></i>
                                                <b>2022/11/21 00:00:00截止时间</b>
                                            </span>
                  </p>

                </div> <!-- end card-body-->
                <ul class="list-group list-group-flush">
                  <li class="list-group-item p-3">
                    <!-- project progress-->
                    <p class="mb-2 fw-bold">进度<span class="float-end">100%</span></p>
                    <div class="progress progress-sm">
                      <div class="progress-bar" role="progressbar" aria-valuenow="100" aria-valuemin="0" aria-valuemax="100" style="width: 100%;">
                      </div><!-- /.progress-bar -->
                    </div><!-- /.progress -->
                  </li>
                </ul>
              </div> <!-- end card-->
            </div> <!-- end col -->
          </div>

        </div> <!-- container -->

      </div> <!-- content -->

      <!-- Footer Start -->
      <!--      <footer class="footer">-->
      <!--        <div class="container-fluid">-->
      <!--          <div class="row">-->
      <!--            <div class="col-md-6">-->
      <!--              <script>document.write(new Date().getFullYear())</script> © Hyper - Coderthemes.com-->
      <!--            </div>-->
      <!--            <div class="col-md-6">-->
      <!--              <div class="text-md-end footer-links d-none d-md-block">-->
      <!--                <a href="javascript: void(0);">About</a>-->
      <!--                <a href="javascript: void(0);">Support</a>-->
      <!--                <a href="javascript: void(0);">Contact Us</a>-->
      <!--              </div>-->
      <!--            </div>-->
      <!--          </div>-->
      <!--        </div>-->
      <!--      </footer>-->
      <!-- end Footer -->

    </div>
  </div>
  </body>

</template>

<script>
import topNav from './topNav'
import leftNav from './leftNav'
export default {
  components: {
    TopNav: topNav,
    LeftNav: leftNav
  },
  name: "index.vue"
}

</script>

<style scoped src="../../assets/css/index_css/icons.min.css"></style>
<style scoped src="../../assets/css/index_css/app-saas.min.css"></style>
<style scoped src="../../assets/css/index_css/jquery-jvectormap-1.2.2.css"></style>
<style scoped src="bootstrap-daterangepicker/daterangepicker.css"></style>