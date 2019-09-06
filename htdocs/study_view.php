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
    <!-- Bootstrap -->
    <link href="vendors/bootstrap/dist/css/bootstrap.min.css" rel="stylesheet">
    <!-- Font Awesome -->
    <link href="vendors/font-awesome/css/font-awesome.min.css" rel="stylesheet">
    <!-- NProgress -->
    <link href="vendors/nprogress/nprogress.css" rel="stylesheet">
    <!-- iCheck -->
    <link href="vendors/iCheck/skins/flat/green.css" rel="stylesheet">
	
    <!-- bootstrap-progressbar -->
    <link href="vendors/bootstrap-progressbar/css/bootstrap-progressbar-3.3.4.min.css" rel="stylesheet">
    <!-- bootstrap-daterangepicker -->
    <link href="vendors/bootstrap-daterangepicker/daterangepicker.css" rel="stylesheet">

    <!-- Custom Theme Style -->
    <link href="build/css/custom.min.css" rel="stylesheet"> 
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
                <h3>강좌 관리<small>&nbsp;&nbsp;신한DS 직원들을 위한 LMS, Shinple</small></h3>
              </div>

              <div class="title_right"></div>
            </div>

            <div class="clearfix"></div>

            <div class="row">
              <div class="col-md-12 col-sm-12 col-xs-12">
                <div class="x_panel">
                  <div class="x_title">
                    <h2>강좌 Info</h2>
                    <ul class="nav navbar-right panel_toolbox"></ul>
                  <div class="clearfix"></div>
                  </div>
                  
                  <div class="x_content" style="padding-left: 10px; padding-right: 10px;">
                    <!-- <br /> -->
                    <form class="form-horizontal form-label-left">
                      <div style="padding: 10px;width: 480px;float: left;margin-right:10px;border : 3px solid rgb(204, 217, 255);margin-top: 38px;margin-left: 20px;">                          <div class="form-group">
                        <?php
                          $view_list = $_GET["view_list"]; 
                          $connect = mysqli_connect("192.168.1.156", "root", "",'shinple') or die (mysql_error());
                          $query ="select * from course where course_num = '$view_list';";
                          $result = $connect->query($query);
                          $rows = mysqli_fetch_assoc($result)

                          
                        ?>
                          
                        
                          <label class="control-label col-md-3 col-sm-3 col-xs-12">강좌명</label>
                          <div class="col-md-9 col-sm-9 col-xs-12" style="padding-top: 8px;"><span><?php echo $rows['course_title']?></span></div>
                        </div>
                        <div class="form-group">
                          <label class="control-label col-md-3 col-sm-3 col-xs-12">강사명</label>
                          <div class="col-md-9 col-sm-9 col-xs-12" style="padding-top: 8px;"><span><?php echo $rows['course_tch']?></span></div>
                        </div>
                        <div class="form-group">
                          <label class="control-label col-md-3 col-sm-3 col-xs-12">강좌설명 </span>
                          </label>


                          <div class="col-md-9 col-sm-9 col-xs-12" style="padding-top: 8px;">
                            <span><?php echo $rows['course_text']?></span>
                          </div>
                        </div>
                        <div class="form-group">
                          <label class="control-label col-md-3 col-sm-3 col-xs-12">강좌 태그</label>
                          <div class="col-md-9 col-sm-9 col-xs-12" style="padding-top: 8px;"><span><?php echo $rows['course_tag01']?>, <?php echo $rows['course_tag02']?>, <?php echo $rows['course_tag03']?></span></div>
                        </div>
                        <div class="form-group">
                          <label class="control-label col-md-3 col-sm-3 col-xs-12">레벨</label>
                          <div class="col-md-9 col-sm-9 col-xs-12" style="padding-top: 8px;"><span><?php echo $rows['course_level']?></span></div>
                        </div>


                        <div class="form-group">
                          <label class="col-md-3 col-sm-3 col-xs-12 control-label" >등록자</label>

                          <div class="col-md-9 col-sm-9 col-xs-12" style="padding-top: 8px;"><span><?php echo $rows['course_tch']?></span></div>
                        </div>  
                      </div> 


                      <div style="padding: 10px; width: 300px; text-align:center; float: left; margin-right:10px;">
                        <div class="form-group" style="padding-left: 100px;width: 550px;">
                          
                         <div><label class="control-label col-md-3 col-sm-3 col-xs-12"style="width: 80px;padding-top: 0px;">대표 사진</label><br></div>
                          <div class="col-md-9 col-sm-9 col-xs-12" style="padding-top: 7px;">
                             <td><input type="image" src="/video/course/<?php echo $rows['course_num']?>.png" width="350" height="350" ></td>
                          </div>  
                        </div>                    
                      </div> 
                      
                     
                      <div class="form-group"></div>
    
                      <!-- <div class="ln_solid" style="margin-bottom: 0px;"></div>  -->
                      <br/><br/>
                      <div class="x_title">
                        <h2>강의 List</h2>
                        <ul class="nav navbar-right panel_toolbox"></ul>
                      <div class="clearfix"></div>
                      </div>
                      <div class="x_content" style="padding-left: 10px; padding-right: 10px;">
                        <!-- <br /> -->
                        <form class="form-horizontal form-label-left">
                          <div style="padding: 10px; width: 100%; float: left; margin-right:10px; ">
                          
                          <?php
                            $view_list = $_GET["view_list"]; 
                            $query = "SELECT lec_title, lec_course FROM lecture WHERE lec_course = $view_list";
                            $result = $connect->query($query);
                            

                            
                            $i=1;
                            $MAX = 0;
                            while($rows = mysqli_fetch_assoc($result)){
                                $items[]=$rows;
                                $MAX++;
                            }
                                
                            //$items = array_reverse($items ,true); //DB에 저장된 데이터 수 (열 기준)
                            
                            ?>     
                            <?php foreach($items as $item){?>
                                    <div class="form-group">
                                        <label class="control-label col-md-3 col-sm-3 col-xs-12">강의 <?php echo $i?></label>
                                        <div class="col-md-9 col-sm-9 col-xs-12" style="padding-top: 8px;"><span><?php echo $item['lec_title']?></span></div>
                                            
                                    </div>
                        
                                            
                            
                            <?php
                                        $i++;
                                        $MAX--;
                                        
                                        }
                                    
                                ?>

                                    
                        </div> 
                          <div class="form-group"></div>






                           
                       
                        <button type="button" class="btn btn-round btn-danger" onclick="location.href='study_list.php'" style="float: right;">목록</button> 
                    </form>
                  </div> 
                </div> 
              </div>
            </div>
          </div>
        </div>
        <!-- /page content -->

        <!-- footer content -->
        <footer style="margin-left:0px;">
          <div style="text-align: center; width: 100%;">
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

          <!-- NProgress -->
          <script src="vendors/nprogress/nprogress.js"></script>
         
          <!-- Custom Theme Scripts -->
          <script src="build/js/custom.min.js"></script>

          <!-- Custom script Add -->
          <script src="build/js/script.js"></script>
  </body>
</html>
