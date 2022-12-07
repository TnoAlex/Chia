<template>
  <head>
    <meta charset="UTF-8">
    <title>修改信息</title>
  </head>
  <body>
  <!-- Begin page -->
  <div class="wrapper">
    <!-- ========== Topbar Start ========== -->
    <div class="navbar-custom topnav-navbar">
      <div class="container-fluid detached-nav">

        <!-- Topbar Logo -->
        <div class="logo-topbar">
          <!-- Logo light -->
          <a class="logo-light">
                            <span class="logo-lg">
                                <img src="../assets/picture/logo.png" alt="logo" height="22">
                            </span>
            <span class="logo-sm">
                                <img src="../assets/picture/logo-sm.png" alt="small logo" height="22">
                            </span>
          </a>

          <!-- Logo Dark -->
          <a class="logo-dark">
                            <span class="logo-lg">
                                <img src="../assets/picture/logo-dark.png" alt="dark logo" height="22">
                            </span>
            <span class="logo-sm">
                                <img src="../assets/picture/logo-dark-sm.png" alt="small logo" height="22">
                            </span>
          </a>
        </div>

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
            <a class="nav-link dropdown-toggle arrow-none" data-bs-toggle="dropdown" href="#" role="button" aria-haspopup="false" aria-expanded="false">
              <i class="ri-search-line noti-icon"></i>
            </a>
            <div class="dropdown-menu dropdown-menu-animated dropdown-lg p-0">
              <form class="p-3">
                <input type="search" class="form-control" placeholder="Search ..." aria-label="Recipient's username">
              </form>
            </div>
          </li>

          <li class="dropdown notification-list topbar-dropdown">
            <a class="nav-link dropdown-toggle arrow-none" data-bs-toggle="dropdown" href="#" role="button" aria-haspopup="false" aria-expanded="false">
              <img src="../assets/picture/us.jpg" alt="user-image" class="me-0 me-sm-1" height="12">
              <span class="align-middle d-none d-lg-inline-block">English</span> <i class="mdi mdi-chevron-down d-none d-sm-inline-block align-middle"></i>
            </a>
            <div class="dropdown-menu dropdown-menu-end dropdown-menu-animated topbar-dropdown-menu">

              <!-- item-->
              <a href="javascript:void(0);" class="dropdown-item notify-item">
                <img src="../assets/picture/germany.jpg" alt="user-image" class="me-1" height="12"> <span class="align-middle">German</span>
              </a>

              <!-- item-->
              <a href="javascript:void(0);" class="dropdown-item notify-item">
                <img src="../assets/picture/italy.jpg" alt="user-image" class="me-1" height="12"> <span class="align-middle">Italian</span>
              </a>

              <!-- item-->
              <a href="javascript:void(0);" class="dropdown-item notify-item">
                <img src="../assets/picture/spain.jpg" alt="user-image" class="me-1" height="12"> <span class="align-middle">Spanish</span>
              </a>

              <!-- item-->
              <a href="javascript:void(0);" class="dropdown-item notify-item">
                <img src="../assets/picture/russia.jpg" alt="user-image" class="me-1" height="12"> <span class="align-middle">Russian</span>
              </a>

            </div>
          </li>

          <li class="dropdown notification-list">
            <a class="nav-link dropdown-toggle arrow-none" data-bs-toggle="dropdown" href="#" role="button" aria-haspopup="false" aria-expanded="false">
              <i class="ri-notification-3-line noti-icon"></i>
              <span class="noti-icon-badge"></span>
            </a>
            <div class="dropdown-menu dropdown-menu-end dropdown-menu-animated dropdown-lg py-0">
              <div class="p-2 border-top-0 border-start-0 border-end-0 border-dashed border">
                <div class="row align-items-center">
                  <div class="col">
                    <h6 class="m-0 font-16 fw-semibold"> Notification</h6>
                  </div>
                  <div class="col-auto">
                    <a href="javascript: void(0);" class="text-dark text-decoration-underline">
                      <small>Clear All</small>
                    </a>
                  </div>
                </div>
              </div>

              <div class="px-3" style="max-height: 300px;" data-simplebar="">

                <h5 class="text-muted font-13 fw-normal mt-2">Today</h5>
                <!-- item-->

                <a href="javascript:void(0);" class="dropdown-item p-0 notify-item card unread-noti shadow-none mb-2">
                  <div class="card-body">
                    <span class="float-end noti-close-btn text-muted"><i class="mdi mdi-close"></i></span>
                    <div class="d-flex align-items-center">
                      <div class="flex-shrink-0">
                        <div class="notify-icon bg-primary">
                          <i class="mdi mdi-comment-account-outline"></i>
                        </div>
                      </div>
                      <div class="flex-grow-1 text-truncate ms-2">
                        <h5 class="noti-item-title fw-semibold font-14">Datacorp <small class="fw-normal text-muted ms-1">1 min ago</small></h5>
                        <small class="noti-item-subtitle text-muted">Caleb Flakelar commented on Admin</small>
                      </div>
                    </div>
                  </div>
                </a>

                <!-- item-->
                <a href="javascript:void(0);" class="dropdown-item p-0 notify-item card read-noti shadow-none mb-2">
                  <div class="card-body">
                    <span class="float-end noti-close-btn text-muted"><i class="mdi mdi-close"></i></span>
                    <div class="d-flex align-items-center">
                      <div class="flex-shrink-0">
                        <div class="notify-icon bg-info">
                          <i class="mdi mdi-account-plus"></i>
                        </div>
                      </div>
                      <div class="flex-grow-1 text-truncate ms-2">
                        <h5 class="noti-item-title fw-semibold font-14">Admin <small class="fw-normal text-muted ms-1">1 hours ago</small></h5>
                        <small class="noti-item-subtitle text-muted">New user registered</small>
                      </div>
                    </div>
                  </div>
                </a>

                <h5 class="text-muted font-13 fw-normal mt-0">Yesterday</h5>

                <!-- item-->
                <a href="javascript:void(0);" class="dropdown-item p-0 notify-item card read-noti shadow-none mb-2">
                  <div class="card-body">
                    <span class="float-end noti-close-btn text-muted"><i class="mdi mdi-close"></i></span>
                    <div class="d-flex align-items-center">
                      <div class="flex-shrink-0">
                        <div class="notify-icon">
                          <img src="../assets/picture/avatar-2.jpg" class="img-fluid rounded-circle" alt="">
                        </div>
                      </div>
                      <div class="flex-grow-1 text-truncate ms-2">
                        <h5 class="noti-item-title fw-semibold font-14">Cristina Pride <small class="fw-normal text-muted ms-1">1 day ago</small></h5>
                        <small class="noti-item-subtitle text-muted">Hi, How are you? What about our next meeting</small>
                      </div>
                    </div>
                  </div>
                </a>

                <h5 class="text-muted font-13 fw-normal mt-0">30 Dec 2021</h5>

                <!-- item-->
                <a href="javascript:void(0);" class="dropdown-item p-0 notify-item card read-noti shadow-none mb-2">
                  <div class="card-body">
                    <span class="float-end noti-close-btn text-muted"><i class="mdi mdi-close"></i></span>
                    <div class="d-flex align-items-center">
                      <div class="flex-shrink-0">
                        <div class="notify-icon bg-primary">
                          <i class="mdi mdi-comment-account-outline"></i>
                        </div>
                      </div>
                      <div class="flex-grow-1 text-truncate ms-2">
                        <h5 class="noti-item-title fw-semibold font-14">Datacorp</h5>
                        <small class="noti-item-subtitle text-muted">Caleb Flakelar commented on Admin</small>
                      </div>
                    </div>
                  </div>
                </a>

                <!-- item-->
                <a href="javascript:void(0);" class="dropdown-item p-0 notify-item card read-noti shadow-none mb-2">
                  <div class="card-body">
                    <span class="float-end noti-close-btn text-muted"><i class="mdi mdi-close"></i></span>
                    <div class="d-flex align-items-center">
                      <div class="flex-shrink-0">
                        <div class="notify-icon">
                          <img src="../assets/picture/avatar-4.jpg" class="img-fluid rounded-circle" alt="">
                        </div>
                      </div>
                      <div class="flex-grow-1 text-truncate ms-2">
                        <h5 class="noti-item-title fw-semibold font-14">Karen Robinson</h5>
                        <small class="noti-item-subtitle text-muted">Wow ! this admin looks good and awesome design</small>
                      </div>
                    </div>
                  </div>
                </a>

                <div class="text-center">
                  <i class="mdi mdi-dots-circle mdi-spin text-muted h3 mt-0"></i>
                </div>
              </div>

              <!-- All-->
              <a href="javascript:void(0);" class="dropdown-item text-center text-primary notify-item border-top border-light py-2">
                View All
              </a>

            </div>
          </li>

          <li class="dropdown notification-list d-none d-sm-inline-block">
            <a class="nav-link dropdown-toggle arrow-none" data-bs-toggle="dropdown" href="#" role="button" aria-haspopup="false" aria-expanded="false">
              <i class="ri-apps-2-line noti-icon"></i>
            </a>
            <div class="dropdown-menu dropdown-menu-end dropdown-menu-animated dropdown-lg p-0">

              <div class="p-2">
                <div class="row g-0">
                  <div class="col">
                    <a class="dropdown-icon-item" href="#">
                      <img src="../assets/picture/slack.png" alt="slack">
                      <span>Slack</span>
                    </a>
                  </div>
                  <div class="col">
                    <a class="dropdown-icon-item" href="#">
                      <img src="../assets/picture/github.png" alt="Github">
                      <span>GitHub</span>
                    </a>
                  </div>
                  <div class="col">
                    <a class="dropdown-icon-item" href="#">
                      <img src="../assets/picture/dribbble.png" alt="dribbble">
                      <span>Dribbble</span>
                    </a>
                  </div>
                </div>

                <div class="row g-0">
                  <div class="col">
                    <a class="dropdown-icon-item" href="#">
                      <img src="../assets/picture/bitbucket.png" alt="bitbucket">
                      <span>Bitbucket</span>
                    </a>
                  </div>
                  <div class="col">
                    <a class="dropdown-icon-item" href="#">
                      <img src="../assets/picture/dropbox.png" alt="dropbox">
                      <span>Dropbox</span>
                    </a>
                  </div>
                  <div class="col">
                    <a class="dropdown-icon-item" href="#">
                      <img src="../assets/picture/g-suite.png" alt="G Suite">
                      <span>G Suite</span>
                    </a>
                  </div>
                </div> <!-- end row-->
              </div>

            </div>
          </li>

          <li class="notification-list d-none d-sm-inline-block">
            <a class="nav-link" data-bs-toggle="offcanvas" href="#theme-settings-offcanvas">
              <i class="ri-settings-3-line noti-icon"></i>
            </a>
          </li>

          <li class="notification-list d-none d-sm-inline-block">
            <a class="nav-link" href="javascript:void(0)" id="light-dark-mode">
              <i class="ri-moon-line noti-icon"></i>
            </a>
          </li>

          <li class="notification-list d-none d-md-inline-block">
            <a class="nav-link" href="" data-toggle="fullscreen">
              <i class="ri-fullscreen-line noti-icon"></i>
            </a>
          </li>

          <li class="dropdown notification-list">
            <a class="nav-link dropdown-toggle nav-user arrow-none me-0" data-bs-toggle="dropdown" href="#" role="button" aria-haspopup="false" aria-expanded="false">
                                <span class="account-user-avatar">
                                    <img src="../assets/picture/avatar-1.jpg" alt="user-image" class="rounded-circle">
                                </span>
              <span>
                                    <span class="account-user-name">Dominic Keller</span>
                                    <span class="account-position">Founder</span>
                                </span>
            </a>
            <div class="dropdown-menu dropdown-menu-end dropdown-menu-animated topbar-dropdown-menu profile-dropdown">
              <!-- item-->
              <div class=" dropdown-header noti-title">
                <h6 class="text-overflow m-0">Welcome !</h6>
              </div>

              <!-- item-->
              <a href="javascript:void(0);" class="dropdown-item notify-item">
                <i class="mdi mdi-account-circle me-1"></i>
                <span>My Account</span>
              </a>

              <!-- item-->
              <a href="javascript:void(0);" class="dropdown-item notify-item">
                <i class="mdi mdi-account-edit me-1"></i>
                <span>Settings</span>
              </a>

              <!-- item-->
              <a href="javascript:void(0);" class="dropdown-item notify-item">
                <i class="mdi mdi-lifebuoy me-1"></i>
                <span>Support</span>
              </a>

              <!-- item-->
              <a href="javascript:void(0);" class="dropdown-item notify-item">
                <i class="mdi mdi-lock-outline me-1"></i>
                <span>Lock Screen</span>
              </a>

              <!-- item-->
              <a href="javascript:void(0);" class="dropdown-item notify-item">
                <i class="mdi mdi-logout me-1"></i>
                <span>Logout</span>
              </a>
            </div>
          </li>
        </ul>

        <!-- Topbar Search Form -->
        <div class="app-search dropdown">
          <form>
            <div class="input-group">
              <input type="search" class="form-control dropdown-toggle" placeholder="Search..." id="top-search">
              <span class="mdi mdi-magnify search-icon"></span>
              <button class="input-group-text btn btn-primary" type="submit">Search</button>
            </div>
          </form>

          <div class="dropdown-menu dropdown-menu-animated dropdown-lg" id="search-dropdown">
            <!-- item-->
            <div class="dropdown-header noti-title">
              <h5 class="text-overflow mb-2">Found <span class="text-danger">17</span> results</h5>
            </div>

            <!-- item-->
            <a href="javascript:void(0);" class="dropdown-item notify-item">
              <i class="uil-notes font-16 me-1"></i>
              <span>Analytics Report</span>
            </a>

            <!-- item-->
            <a href="javascript:void(0);" class="dropdown-item notify-item">
              <i class="uil-life-ring font-16 me-1"></i>
              <span>How can I help you?</span>
            </a>

            <!-- item-->
            <a href="javascript:void(0);" class="dropdown-item notify-item">
              <i class="uil-cog font-16 me-1"></i>
              <span>User profile settings</span>
            </a>

            <!-- item-->
            <div class="dropdown-header noti-title">
              <h6 class="text-overflow mb-2 text-uppercase">Users</h6>
            </div>

            <div class="notification-list">
              <!-- item-->
              <a href="javascript:void(0);" class="dropdown-item notify-item">
                <div class="d-flex">
                  <img class="d-flex me-2 rounded-circle" src="../assets/picture/avatar-2.jpg" alt="Generic placeholder image" height="32">
                  <div class="w-100">
                    <h5 class="m-0 font-14">Erwin Brown</h5>
                    <span class="font-12 mb-0">UI Designer</span>
                  </div>
                </div>
              </a>

              <!-- item-->
              <a href="javascript:void(0);" class="dropdown-item notify-item">
                <div class="d-flex">
                  <img class="d-flex me-2 rounded-circle" src="../assets/picture/avatar-5.jpg" alt="Generic placeholder image" height="32">
                  <div class="w-100">
                    <h5 class="m-0 font-14">Jacob Deo</h5>
                    <span class="font-12 mb-0">Developer</span>
                  </div>
                </div>
              </a>
            </div>
          </div>
        </div>
      </div>
    </div>
    <!-- ========== Topbar End ========== -->

    <!-- ========== Left Sidebar Start ========== -->
    <div class="leftside-menu">

      <!-- Logo Light -->
      <a class="logo logo-light">
                    <span class="logo-lg">
                        <img src="../assets/picture/logo.png" alt="logo" height="22">
                    </span>
        <span class="logo-sm">
                        <img src="../assets/picture/logo-sm.png" alt="small logo" height="22">
                    </span>
      </a>

      <!-- Logo Dark -->
      <a class="logo logo-dark">
                    <span class="logo-lg">
                        <img src="../assets/picture/logo-dark.png" alt="dark logo" height="22">
                    </span>
        <span class="logo-sm">
                        <img src="../assets/picture/logo-dark-sm.png" alt="small logo" height="22">
                    </span>
      </a>

      <!-- Sidebar Hover Menu Toggle Button -->
      <button type="button" class="btn button-sm-hover p-0" data-bs-toggle="tooltip" data-bs-placement="right" title="Show Full Sidebar">
        <i class="ri-checkbox-blank-circle-line align-middle"></i>
      </button>

      <!-- Sidebar -left -->
      <div class="h-100" id="leftside-menu-container" data-simplebar="">
        <!-- Leftbar User -->

        <!--- Sidemenu -->
        <ul class="side-nav">

          <li class="side-nav-title side-nav-item">Navigation</li>

          <li class="side-nav-item">
            <a data-bs-toggle="collapse" href="#sidebarDashboards" aria-expanded="false" aria-controls="sidebarDashboards" class="side-nav-link">
              <i class="uil-home-alt"></i>
              <span class="badge bg-success float-end">5</span>
              <span>主页</span>
            </a>
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

          <li class="side-nav-item">
            <a href="landing.html" target="_blank" class="side-nav-link">
              <i class="uil-globe"></i>
              <span class="badge bg-secondary text-light float-end">New</span>
              <span> Landing </span>
            </a>
          </li>

          <li class="side-nav-item">
            <a data-bs-toggle="collapse" href="#sidebarLayouts" aria-expanded="false" aria-controls="sidebarLayouts" class="side-nav-link">
              <i class="uil-window"></i>
              <span> Layouts </span>
              <span class="menu-arrow"></span>
            </a>
            <div class="collapse" id="sidebarLayouts">
              <ul class="side-nav-second-level">
                <li>
                  <a href="layouts-horizontal.html" target="_blank">Horizontal</a>
                </li>
                <li>
                  <a href="layouts-detached.html" target="_blank">Detached</a>
                </li>
                <li>
                  <a href="layouts-full.html" target="_blank">Full View</a>
                </li>
                <li>
                  <a href="layouts-hover.html" target="_blank">Hover Menu</a>
                </li>
                <li>
                  <a href="layouts-compact.html" target="_blank">Compact</a>
                </li>
                <li>
                  <a href="layouts-icon-view.html" target="_blank">Icon View</a>
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

          <li class="side-nav-item">
            <a data-bs-toggle="collapse" href="#sidebarExtendedUI" aria-expanded="false" aria-controls="sidebarExtendedUI" class="side-nav-link">
              <i class="uil-package"></i>
              <span> Extended UI </span>
              <span class="menu-arrow"></span>
            </a>
            <div class="collapse" id="sidebarExtendedUI">
              <ul class="side-nav-second-level">
                <li>
                  <a href="extended-dragula.html">Dragula</a>
                </li>
                <li>
                  <a href="extended-range-slider.html">Range Slider</a>
                </li>
                <li>
                  <a href="extended-ratings.html">Ratings</a>
                </li>
                <li>
                  <a href="extended-scrollbar.html">Scrollbar</a>
                </li>
                <li>
                  <a href="extended-scrollspy.html">Scrollspy</a>
                </li>
                <li>
                  <a href="extended-treeview.html">Treeview</a>
                </li>
              </ul>
            </div>
          </li>

          <li class="side-nav-item">
            <a href="widgets.html" class="side-nav-link">
              <i class="uil-layer-group"></i>
              <span> Widgets </span>
            </a>
          </li>

          <li class="side-nav-item">
            <a data-bs-toggle="collapse" href="#sidebarIcons" aria-expanded="false" aria-controls="sidebarIcons" class="side-nav-link">
              <i class="uil-streering"></i>
              <span> Icons </span>
              <span class="menu-arrow"></span>
            </a>
            <div class="collapse" id="sidebarIcons">
              <ul class="side-nav-second-level">
                <li>
                  <a href="icons-remixicons.html">Remix Icons</a>
                </li>
                <li>
                  <a href="icons-mdi.html">Material Design</a>
                </li>
                <li>
                  <a href="icons-unicons.html">Unicons</a>
                </li>
              </ul>
            </div>
          </li>

          <li class="side-nav-item">
            <a data-bs-toggle="collapse" href="#sidebarCharts" aria-expanded="false" aria-controls="sidebarCharts" class="side-nav-link">
              <i class="uil-chart"></i>
              <span> Charts </span>
              <span class="menu-arrow"></span>
            </a>
            <div class="collapse" id="sidebarCharts">
              <ul class="side-nav-second-level">
                <li class="side-nav-item">
                  <a data-bs-toggle="collapse" href="#sidebarApexCharts" aria-expanded="false" aria-controls="sidebarApexCharts">
                    <span> Apex Charts </span>
                    <span class="menu-arrow"></span>
                  </a>
                  <div class="collapse" id="sidebarApexCharts">
                    <ul class="side-nav-third-level">
                      <li>
                        <a href="charts-apex-area.html">Area</a>
                      </li>
                      <li>
                        <a href="charts-apex-bar.html">Bar</a>
                      </li>
                      <li>
                        <a href="charts-apex-bubble.html">Bubble</a>
                      </li>
                      <li>
                        <a href="charts-apex-candlestick.html">Candlestick</a>
                      </li>
                      <li>
                        <a href="charts-apex-column.html">Column</a>
                      </li>
                      <li>
                        <a href="charts-apex-heatmap.html">Heatmap</a>
                      </li>
                      <li>
                        <a href="charts-apex-line.html">Line</a>
                      </li>
                      <li>
                        <a href="charts-apex-mixed.html">Mixed</a>
                      </li>
                      <li>
                        <a href="charts-apex-timeline.html">Timeline</a>
                      </li>
                      <li>
                        <a href="charts-apex-boxplot.html">Boxplot</a>
                      </li>
                      <li>
                        <a href="charts-apex-treemap.html">Treemap</a>
                      </li>
                      <li>
                        <a href="charts-apex-pie.html">Pie</a>
                      </li>
                      <li>
                        <a href="charts-apex-radar.html">Radar</a>
                      </li>
                      <li>
                        <a href="charts-apex-radialbar.html">RadialBar</a>
                      </li>
                      <li>
                        <a href="charts-apex-scatter.html">Scatter</a>
                      </li>
                      <li>
                        <a href="charts-apex-polar-area.html">Polar Area</a>
                      </li>
                      <li>
                        <a href="charts-apex-sparklines.html">Sparklines</a>
                      </li>
                    </ul>
                  </div>
                </li>
                <li class="side-nav-item">
                  <a data-bs-toggle="collapse" href="#sidebarChartJSCharts" aria-expanded="false" aria-controls="sidebarChartJSCharts">
                    <span> ChartJS </span>
                    <span class="menu-arrow"></span>
                  </a>
                  <div class="collapse" id="sidebarChartJSCharts">
                    <ul class="side-nav-third-level">
                      <li>
                        <a href="charts-chartjs-area.html">Area</a>
                      </li>
                      <li>
                        <a href="charts-chartjs-bar.html">Bar</a>
                      </li>
                      <li>
                        <a href="charts-chartjs-line.html">Line</a>
                      </li>
                      <li>
                        <a href="charts-chartjs-other.html">Other</a>
                      </li>

                    </ul>
                  </div>
                </li>
                <li>
                  <a href="charts-brite.html">Britecharts</a>
                </li>
                <li>
                  <a href="charts-sparkline.html">Sparklines</a>
                </li>
              </ul>
            </div>
          </li>

          <li class="side-nav-item">
            <a data-bs-toggle="collapse" href="#sidebarForms" aria-expanded="false" aria-controls="sidebarForms" class="side-nav-link">
              <i class="uil-document-layout-center"></i>
              <span> Forms </span>
              <span class="menu-arrow"></span>
            </a>
            <div class="collapse" id="sidebarForms">
              <ul class="side-nav-second-level">
                <li>
                  <a href="form-elements.html">Basic Elements</a>
                </li>
                <li>
                  <a href="form-advanced.html">Form Advanced</a>
                </li>
                <li>
                  <a href="form-validation.html">Validation</a>
                </li>
                <li>
                  <a href="form-wizard.html">Wizard</a>
                </li>
                <li>
                  <a href="form-fileuploads.html">File Uploads</a>
                </li>
                <li>
                  <a href="form-editors.html">Editors</a>
                </li>
              </ul>
            </div>
          </li>

          <li class="side-nav-item">
            <a data-bs-toggle="collapse" href="#sidebarTables" aria-expanded="false" aria-controls="sidebarTables" class="side-nav-link">
              <i class="uil-table"></i>
              <span> Tables </span>
              <span class="menu-arrow"></span>
            </a>
            <div class="collapse" id="sidebarTables">
              <ul class="side-nav-second-level">
                <li>
                  <a href="tables-basic.html">Basic Tables</a>
                </li>
                <li>
                  <a href="tables-datatable.html">Data Tables</a>
                </li>
              </ul>
            </div>
          </li>

          <li class="side-nav-item">
            <a data-bs-toggle="collapse" href="#sidebarMaps" aria-expanded="false" aria-controls="sidebarMaps" class="side-nav-link">
              <i class="uil-location-point"></i>
              <span> Maps </span>
              <span class="menu-arrow"></span>
            </a>
            <div class="collapse" id="sidebarMaps">
              <ul class="side-nav-second-level">
                <li>
                  <a href="maps-google.html">Google Maps</a>
                </li>
                <li>
                  <a href="maps-vector.html">Vector Maps</a>
                </li>
              </ul>
            </div>
          </li>

          <li class="side-nav-item">
            <a data-bs-toggle="collapse" href="#sidebarMultiLevel" aria-expanded="false" aria-controls="sidebarMultiLevel" class="side-nav-link">
              <i class="uil-folder-plus"></i>
              <span> Multi Level </span>
              <span class="menu-arrow"></span>
            </a>
            <div class="collapse" id="sidebarMultiLevel">
              <ul class="side-nav-second-level">
                <li class="side-nav-item">
                  <a data-bs-toggle="collapse" href="#sidebarSecondLevel" aria-expanded="false" aria-controls="sidebarSecondLevel">
                    <span> Second Level </span>
                    <span class="menu-arrow"></span>
                  </a>
                  <div class="collapse" id="sidebarSecondLevel">
                    <ul class="side-nav-third-level">
                      <li>
                        <a href="javascript: void(0);">Item 1</a>
                      </li>
                      <li>
                        <a href="javascript: void(0);">Item 2</a>
                      </li>
                    </ul>
                  </div>
                </li>
                <li class="side-nav-item">
                  <a data-bs-toggle="collapse" href="#sidebarThirdLevel" aria-expanded="false" aria-controls="sidebarThirdLevel">
                    <span> Third Level </span>
                    <span class="menu-arrow"></span>
                  </a>
                  <div class="collapse" id="sidebarThirdLevel">
                    <ul class="side-nav-third-level">
                      <li>
                        <a href="javascript: void(0);">Item 1</a>
                      </li>
                      <li class="side-nav-item">
                        <a data-bs-toggle="collapse" href="#sidebarFourthLevel" aria-expanded="false" aria-controls="sidebarFourthLevel">
                          <span> Item 2 </span>
                          <span class="menu-arrow"></span>
                        </a>
                        <div class="collapse" id="sidebarFourthLevel">
                          <ul class="side-nav-forth-level">
                            <li>
                              <a href="javascript: void(0);">Item 2.1</a>
                            </li>
                            <li>
                              <a href="javascript: void(0);">Item 2.2</a>
                            </li>
                          </ul>
                        </div>
                      </li>
                    </ul>
                  </div>
                </li>
              </ul>
            </div>
          </li>
        </ul>
        <!--- End Sidemenu -->

        <!-- Help Box -->
        <div class="help-box text-white text-center">
          <a href="javascript: void(0);" class="float-end close-btn text-white">
            <i class="mdi mdi-close"></i>
          </a>
          <img src="assets/picture/help-icon.svg" height="90" alt="Helper Icon Image">
          <h5 class="mt-3">Unlimited Access</h5>
          <p class="mb-3">Upgrade to plan to get access to unlimited reports</p>
          <a href="javascript: void(0);" class="btn btn-secondary btn-sm">Upgrade</a>
        </div>
        <!-- end Help Box -->

        <div class="clearfix"></div>
      </div>
    </div>
    <!-- ========== Left Sidebar End ========== -->

    <!-- ============================================================== -->
    <!-- Start Page Content here -->
    <!-- ============================================================== -->

    <div class="content-page">
      <div class="content">

        <!-- Start Content-->
        <div class="container-fluid">

          <div class="row">
            <div class="col-12">
              <div class="card">
                <div class="card-body">

                  <!-- Checkout Steps -->


                  <!-- Steps Information -->
                  <div class="tab-content">

                    <!-- Billing Content-->
                    <div class="tab-pane show active" id="billing-information">
                      <div class="row">
                        <div class="col-lg-8">
                          <h4 class="mt-2">个人信息修改</h4>

                          <p class="text-muted mb-4">将新的个人信息填入其中</p>

                          <form>
                            <div class="row">
                              <div class="col-md-6">
                                <div class="mb-3">
                                  <label for="name" class="form-label" >姓名</label>
                                  <p class="text" v-html="requestText"></p>
                                  <input type="text" v-model="infoObject.username" placeholder="填入你的姓名" id="name">
                                </div>
                              </div>
                              <div class="col-md-6">
                                <div class="mb-3">
                                  <label for="pwd" class="form-label">密码</label>
                                  <p class="text" v-html="requestText"></p>
                                  <input type="text" v-model="infoObject.password" placeholder="修改你的密码" id="pwd">
                                </div>
                              </div>
                            </div>
                            <div class="row">
                              <div class="col-md-6">
                                <div class="mb-3">
                                  <label for="id" class="form-label">身份证号</label>
                                  <p class="text" v-html="requestText"></p>
                                  <input type="text" placeholder="填入你的身份证号" id="id">
                                </div>
                              </div>
                            </div> <!-- end row -->
                            <div class="row">
                              <div class="row">
                                <div class="mb-3">
                                  <input type="button" value="提交" @click="submitForm">
                                </div>
                              </div>
                            </div> <!-- end row -->
                          </form>
                        </div>

                      </div> <!-- end row-->
                    </div>
                    <!-- End Billing Information Content-->

                  </div> <!-- end tab content-->

                </div> <!-- end card-body-->
              </div> <!-- end card-->
            </div> <!-- end col -->
          </div>
          <!-- end row-->

        </div> <!-- container -->

      </div> <!-- content -->

      <!-- Footer Start -->
      <footer class="footer">
        <div class="container-fluid">
          <div class="row">
            <div class="col-md-6">
              <div class="text-md-end footer-links d-none d-md-block">
                <a href="javascript: void(0);">关于</a>
                <a href="javascript: void(0);">支持</a>
                <a href="javascript: void(0);">联系我们</a>
              </div>
            </div>
          </div>
        </div>
      </footer>
      <!-- end Footer -->

    </div>

    <!-- ============================================================== -->
    <!-- End Page content -->
    <!-- ============================================================== -->

  </div>
  <!-- END wrapper -->

  <!-- Theme Settings -->


  </body>


</template>
<script>
import util from "@/utils/commonUtil";
export default {
  name: "setting",
  data(){
    return{
      infoObject:{
        username:'',
        password:'',
        id:'',
        email:''
      }
    }
  },
  async submitForm() {
    let loading = util.loadingWait('提交中...')
    let submitStatus = 0
    await util.delay(100)
    await this.$axios.post(
        "/setting",
        JSON.stringify(this.infoObject)
    ).then(() => {

    })
        .catch(err => {
          console.log(err)
        })
    await util.delay(100)
    console.log("跳出")
    loading.close()
    if (submitStatus === 0) {
      util.messageBox('提交失败', 'error')
    } else {
      util.messageBox('提交成功', 'success')
      this.$router.push('/index')
    }
  }
}
</script>

<style scoped src="../assets/css/index_css/app-saas.min.css"></style>
<style scoped src="../assets/css/index_css/icons.min.css"></style>
<style scoped src="../assets/css/setting_css/select2.min.css"></style>