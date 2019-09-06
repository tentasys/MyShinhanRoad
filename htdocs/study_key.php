<!DOCTYPE html>
<html lang="en">
  <head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <!-- Meta, title, CSS, favicons, etc. -->
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">

    <title>Shinple Admin</title>
    <link rel="shortcut icon" href="images/Shinhanlogo.ico">

    <link rel="shortcut icon" href="images/Shinhanlogo.ico">
    
    <!-- Bootstrap -->
    <link href="vendors/bootstrap/dist/css/bootstrap.min.css" rel="stylesheet">
    <!-- Font Awesome -->
    <link href="vendors/font-awesome/css/font-awesome.min.css" rel="stylesheet">
    <!-- NProgress -->
    <link href="vendors/nprogress/nprogress.css" rel="stylesheet">
    
    <!-- <link href="vendors/jquery.tagsinput/src/jquery.tagsinput.css" rel="stylesheet"> -->
    <!-- iCheck -->
    <!-- <link href="vendors/iCheck/skins/flat/green.css" rel="stylesheet"> -->
    <!-- bootstrap-wysiwyg -->
    <!-- <link href="vendors/google-code-prettify/bin/prettify.min.css" rel="stylesheet"> -->
    <!-- Select2 -->
    <!-- <link href="vendors/select2/dist/css/select2.min.css" rel="stylesheet"> -->
    <!-- Switchery -->
    <!-- <link href="vendors/switchery/dist/switchery.min.css" rel="stylesheet"> -->
    <!-- starrr -->
    <!-- <link href="vendors/starrr/dist/starrr.css" rel="stylesheet"> -->
    <!-- bootstrap-daterangepicker -->
    <!-- <link href="vendors/bootstrap-daterangepicker/daterangepicker.css" rel="stylesheet"> -->



    <!-- bootstrap-progressbar -->
    <link href="vendors/bootstrap-progressbar/css/bootstrap-progressbar-3.3.4.min.css" rel="stylesheet">
    <!-- bootstrap-daterangepicker -->
    <link href="vendors/bootstrap-daterangepicker/daterangepicker.css" rel="stylesheet">

    <!-- Custom Theme Style -->
    <link href="build/css/custom.min.css" rel="stylesheet">


    <script src="vendors/jquery.tagsinput/src/jquery.tagsinput.js"></script>

    <style>
      /* .tagsinput {
        border: none;
        box-shadow: none;
      } */
      /* .tagsinput input {
        display: none;
      } */
      </style>
  </head>

  <body class="nav-md">
    <div class="container body">
      <div class="main_container">
        <div class="col-md-3 left_col">
          <div class="left_col scroll-view">
            <div class="navbar nav_title" style="border: 0;">
              <a href="index.php" class="site_title"><img src="images/logo.png" alt="" style="width: 40px; height: 40px;"> <span> &nbsp;Shinple</span></a>
            </div>

            <div class="clearfix"></div>

            <!-- menu profile quick info -->
            <div class="profile clearfix">
              <div class="profile_info">
                <span>안녕하세요,</span>
                <h2>관리자님</h2>
              </div>
            </div>
            <!-- /menu profile quick info -->

            <br />

             <!-- sidebar menu -->
             <div id="sidebar-menu" class="main_menu_side hidden-print main_menu">
                <div class="menu_section">
                  <!-- <h3>강좌 관리</h3> -->
                  <ul class="nav side-menu">
                    <li><a href="index.php"><i class="fa fa-home"></i> &nbsp;&nbsp;Home</a></li>
                    <li><a><i class="fa fa-play-circle"></i> &nbsp;&nbsp;강&nbsp;&nbsp;&nbsp;&nbsp;좌 <span class="fa fa-chevron-down"></span></a>
                      <ul class="nav child_menu">
                        <li><a href="study_list.php">강좌 관리</a></li>
                        <li><a href="study_test.php">테스트 관리</a></li>
                      </ul>
                    </li>
  
                    <li><a><i class="fa fa-hashtag"></i> &nbsp;&nbsp;태&nbsp;&nbsp;&nbsp;&nbsp;그 <span class="fa fa-chevron-down"></span></a>
                      <ul class="nav child_menu">
                        <li><a href="study_key.php">태그 관리</a></li>
                      </ul>
                    </li>
  
                    <li><a><i class="fa fa-users"></i> &nbsp;&nbsp;회&nbsp;&nbsp;&nbsp;&nbsp;원 <span class="fa fa-chevron-down"></span></a>
                      <ul class="nav child_menu">
                        <li><a href="member_study.php">강좌별 관리</a></li>
                        <li><a href="member_test.php">테스트별 관리</a></li>
                        <li><a href="member_company.php">회사별 관리</a></li>
                      </ul>
                    </li>
  
                    <li><a><i class="fa fa-file"></i> &nbsp;&nbsp;공&nbsp;지&nbsp;사&nbsp;항 <span class="fa fa-chevron-down"></span></a>
                      <ul class="nav child_menu">
                        <li><a href="log_view.php">공지 관리</a></li>
                      </ul>
                    </li>
                      </ul>
                    </li>
                  </ul>
                </div>
              </div>
              <!-- /sidebar menu -->


            <!-- /menu footer buttons -->
            <div class="sidebar-footer hidden-small">
              <a data-toggle="tooltip" data-placement="top" title="Logout" href="login.php">
                <span class="glyphicon glyphicon-off" aria-hidden="true"></span>
              </a>
            </div>
            <!-- /menu footer buttons -->
          </div>
        </div>

        <!-- top navigation -->
        <div class="top_nav">
          <div class="nav_menu">
            <nav>
              <div class="nav toggle">
                <a id="menu_toggle"><i class="fa fa-bars"></i></a>
              </div>
            </nav>
          </div>
        </div>
        <!-- /top navigation -->

<!-- page content -->
<div class="right_col" role="main">
  <div class="">
    <div class="page-title">
      <div class="title_left">
        <h3>강좌 태그 <small>강좌별 태그 등록 및 목록 확인</small></h3>
      </div>
    </div>


    <div class="clearfix"></div>

    <div class="row">
    
    <div class="col-md-12 col-sm-12 col-xs-12">
        <div class="x_content">
            <div class="x_panel">
              <div class="x_title">
                <h2>강좌 태그 List</h2>
                <div class="clearfix"></div>
              </div>
    
              <div class="control-group" style="width: 100%; padding: 10px" >
                <div class="row">
                <?php
                    include 'db_connection.php'; 

                    $query = <<<_SQL
                    SELECT tag_name FROM tag;
                    _SQL;
                    $result = mysqli_query($con, $query);
                    while($row = mysqli_fetch_assoc($result)){
                      echo '<span class="tag">';
                      echo $row['tag_name'];
                      echo '</span>';
                    }

                    mysqli_close($con);
                ?>
                  <div id="suggestions-container" style="position: relative; float: left; margin: 10px;">
                </div>
              </div>
              
            </div>

            <!-- 삭제 넣을 수 있음 -->
            <!-- <div class="form-group">
              <div class="col-md-9 col-sm-9 col-xs-12 col-md-offset-3">
                <button type="submit" class="btn btn-success" onclick="location.href='study_key.php'" style="float:right;">저장</button>
              </div>
            </div> -->
          <!-- </form> -->

            </div>
        </div>
    </div>

      <div class="col-md-12 col-sm-12 col-xs-12">
          <div class="x_content">
            <div class="x_panel">
              <div class="x_title">
                <h2>강좌 태그 등록</h2>
                <div class="clearfix"></div>
              </div>
    
	            <form action="study_key_write.php" method="POST" class="form-horizontal form-label-left">
              <div class="control-group" style="width: 100%;">
                <div class="row">
                  <input id="tags_1" type="text" name="tag123" class="tags form-control">
                  <div id="suggestions-container" style="position: relative; float: left; margin: 10px;">
                </div>
              </div>
              
            </div>

            <div class="form-group">
              <div class="col-md-9 col-sm-9 col-xs-12 col-md-offset-3">
                <button type="submit" class="btn btn-success" onclick="location.href='study_key.php'" style="float:right;">저장</button>
              </div>
            </div>
          </form>

            </div>
          </div>
      </div>
    </div>
  </div>
</div>

        <!-- footer content -->
        <footer>
            <div style="text-align: center;">
                Copyright ⓒ 2019. Shinhan DS. All right reserved. <a href="https://colorlib.com"></a>
            </div>
          <div class="clearfix"></div>
        </footer>
        <!-- /footer content -->
      </div>
    </div>
    <!-- jQuery -->
    <script src="vendors/jquery/dist/jquery.min.js"></script>
    <!-- Bootstrap -->
    <script src="vendors/bootstrap/dist/js/bootstrap.min.js"></script>
    <!-- FastClick -->
    <script src="vendors/fastclick/lib/fastclick.js"></script>
    <!-- NProgress -->
    <script src="vendors/nprogress/nprogress.js"></script>
    <!-- iCheck --> 
    <script src="vendors/iCheck/icheck.min.js"></script>

    <script src="vendors/jquery.tagsinput/src/jquery.tagsinput.js"></script>

    <!-- Custom Theme Scripts -->
    <script src="build/js/custom.min.js"></script>

    <!-- Custom script Add -->
    <script src="build/js/script.js"></script>
  </body>
</html>
