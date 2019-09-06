<!DOCTYPE html>
<html lang="en">
  <head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <!-- Meta, title, CSS, favicons, etc. -->
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
	<link rel="icon" href="images/favicon.ico" type="image/ico" />

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
   
   <?php
    $connect = mysqli_connect('192.168.1.156', 'root', '', 'shinple') or die (mysql_error()); 
    
    //요약
    $query ="SELECT count(course_num) from course;";
    $course_num = $connect->query($query);

    $query ="SELECT count(tag_num) from tag;";
    $tag_num = $connect->query($query);

    $query ="SELECT count(mem_num) from member;";
    $mem_num = $connect->query($query);

    //최근등록된강좌
    $query ="SELECT course_date, course_title, course_level from course where course_date >= DATE_ADD(NOW(), INTERVAL -1 WEEK) AND NOW() order by course_date DESC LIMIT 5;";
    $result = $connect->query($query);
    $total = mysqli_num_rows($result);

    //수강완료처리요망
    $query = "SELECT c.course_title,c.course_num, count(*) AS total,l.learn_state
              FROM course c JOIN learn_course l ON (c.course_num = l.course_num)
              WHERE l.learn_state=2 GROUP BY course_title DESC LIMIT 5;";
    $result2 = $connect->query($query);
    $total2 = mysqli_num_rows($result2);


    //인기태그별 강좌수
    $query ="SELECT t.tag_name , count(*) AS 'num_per_tag'
             FROM course c JOIN tag t ON( c.course_tag01 = t.tag_name or c.course_tag02= t.tag_name or c.course_tag03 = t.tag_name)
             GROUP BY t.tag_name order by num_per_tag DESC LIMIT 5";
    $result3 = $connect->query($query);
    $total3 = mysqli_num_rows($result3);

    ?>


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
        <div class="right_col" role="main" style="min-height: 754px;margin-left: 230px;">
            <br />
            <!-- 첫번째 로우 시작 -->
            <div class="row">
                <div class="col-md-8 col-sm-8 col-xs-12">
                    <div class="x_panel tile fixed_height_320">
                        <div class="x_title">
                            <h2><b>요약<b></h2>
                            <div class="clearfix"></div>
                        </div>
                    
                        <div class="x_content">
                            <ul class="stats-overview">
                            <br/><br/><br/>
                            
                            
                            <li>
                                <img src="images/play.png" width="58px;" height="53px;"><br/><br/>
                                <span class="name"> <b>총 강좌수<b> </span>
                                <span class="value text-success"><?php echo mysqli_fetch_array($course_num)[0] ?></span>
                            </li>
                            <li>
                                <img src="images/tag.png" width="60px;" height="55px;"><br/><br/>
                                <span class="name"> <b>총 태그수<b> </span>
                                <span class="value text-success"><?php echo mysqli_fetch_array($tag_num)[0] ?></span>
                            </li>
                            <li class="hidden-phone">
                                <img src="images/people.png" width="65px;" height="40px;"><br/><br/>
                                <span class="name"> <b>총 회원수<b> </span>
                                <span class="value text-success"><?php echo mysqli_fetch_array($mem_num)[0] ?></span>
                            </li>
                            </ul>
                        </div>
                    </div>
                </div>
         
                <div class="col-md-4 col-sm-4 col-xs-12">
                    <div class="x_panel tile fixed_height_320">
                    <div class="x_title">
                        <img src="images/new.png" alt="" style="width: 30px; height: 15px; margin-left: 10px; margin-top: 5px;"><h2> <b>수강 완료 처리 요망<b></h2>
                        <div class="clearfix"></div>
                    </div>
        
                    <div class="col-md-12 col-sm-12 col-xs-6">
                        <table class="table table-striped jambo_table bulk_action">
                        <thead>
                            <tr class="headings">
                                <th class="column-title" style="text-align:center;">강좌명</th>
                                <th class="column-title" style="text-align:center;">인원</th>
                            </tr>
                        </thead>
                        <tbody>
                        <?php

                            $i=0;

                            while($rows = mysqli_fetch_assoc($result2)){
                                $i++;
                            $number2 = $total2; //DB에 저장된 데이터 수 (열 기준)
                                    if($total2%2==0){
                            ?>                      <tr class="even pointer" style="text-align:center;">
                                    <?php   }
                                    else{
                            ?>                      <tr class="odd pointer" style="text-align:center;">
                                    <?php } ?>



                            
                            <td>
                                <form action="member_test_detail.php" method="POST">
                                <input id="keys_1" type="hidden" name="test_1" value =<?php echo $rows['course_num'] ?> />
                               <button type="submit" class="btn btn-success" style="float:center; background-color:rgba(255,255,255,0); color:#73879C; border:none; padding:0px; margin:0px;" hover="rgba(255,255,255,0)";><b><?php echo $rows['course_title'];?><b></button>
                                </form>
                            </td>
                            <td class=" "><?php echo $rows['total']?></td>


                            </tr>
                            </tr>
                            <?php
                                $total2 --;
                                }
                            ?>
                         
                        </tbody>
                        </table>
                    </div>
                    </div>

                </div>
            </div> 
            <!-- 첫번째 로우 끝남 -->

            <!-- 두번째 로우 시작 -->
            <div class="row">
                <div class="col-md-6 col-sm-6 col-xs-12">
                    <div class="x_panel tile fixed_height_320" style="height: 350px;">
                        <div class="x_title">
                            <h2><b>최근 등록된 강좌 리스트</b></h2>
                            <div class="clearfix"></div>
                        </div>
                    
                        <div class="x_content">
                            <table class="table table-striped jambo_table bulk_action">
                            <thead>
                                <tr class="headings">
                                    <th class="column-title" style="text-align:center;">등록 날짜</th>
                                    <th class="column-title" style="text-align:center;">강좌명</th>
                                    <th class="column-title" style="text-align:center;">레벨</th>
                                </tr>
                            </thead>
                            <tbody>

                            <?php
                                // $number = $total;
                            //  
                                for ($i = 1; $i <= $total; $i++){
                                $rows = mysqli_fetch_assoc($result);                              //DB에 저장된 데이터 수 (열 기준)
                                    if($i%2==0){
                            ?>                      
                            <tr class="even pointer" style="text-align:center;">
                                            <?php   }
                                            else{
                            ?>                      <tr class="odd pointer" style="text-align:center;">
                                                                
                                            <?php } ?>

                            <tr class="even pointer" style="text-align:center;">
                                <th class="column-title" style="text-align:center;"><?php echo $rows['course_date']; ?></th>
                                <th class="column-title" style="text-align:center;"><?php echo $rows['course_title']; ?></th>
                                <th class="column-title" style="text-align:center;"><?php echo $rows['course_level']; ?></th>
                            </tr>
                            <?php
                                }
                            ?>
                            </tbody>
                            </table>
                        </div>
                    </div>
                </div>
    
                <div class="col-md-6 col-sm-6 col-xs-12">
                    <div class="x_panel tile fixed_height_320 overflow_hidden" style="height: 350px;">
                        <div class="x_title">
                            <h2><b>인기 태그별 강좌 수 Top5</b></h2>
                        <div class="clearfix"></div>
                        </div>
                        <div class="x_content" style="margin-top: 30px;">
                          <!-- while문 시작 -->
                          <?php
                            while($rows=mysqli_fetch_assoc($result3))
                            {

                            ?>
                            <div class="w_left w_25" style="float:left;">
                                <b><span><?php echo $rows['tag_name']; ?></span></b>
                            </div>

                            <div class="w_center w_55" style="float:left;">
                                <div class="progress">
                                  <div class="progress-bar bg-green" role="progressbar" aria-valuenow="60" aria-valuemin="0" aria-valuemax="100" style="width: calc(15px * <?php echo $rows['num_per_tag'] ?>);">
                                  
                                  </div>
                                </div>
                            </div>

                            <div class="w_right w_200" style="float:left;">
                            <b><span style="margin-left: 30px;"><?php echo $rows['num_per_tag']; ?>개</span></b>
                                </div>
                            <?php } ?>
                              <!-- while문 끝 -->
                               
                            </div>
                            
                            <div class="clearfix"></div>
                        </div>
                    </div>
                </div>
            </div>
            <!-- 두번째 로우 끝남 -->
        </div>
        <!-- /page content -->
       
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

    <!-- NProgress -->
    <script src="vendors/nprogress/nprogress.js"></script>
    <!-- gauge.js -->
    <script src="vendors/gauge.js/dist/gauge.min.js"></script>
    <!-- bootstrap-progressbar -->
    <script src="vendors/bootstrap-progressbar/bootstrap-progressbar.min.js"></script>
  <!-- bootstrap-daterangepicker -->
    <script src="vendors/moment/min/moment.min.js"></script>
    <script src="vendors/bootstrap-daterangepicker/daterangepicker.js"></script>

    <!-- Custom Theme Scripts -->
    <script src="build/js/custom.min.js"></script>

    <!-- Chart.js -->
    <script src="vendors/Chart.js/dist/Chart.min.js"></script>
	
  </body>
</html>