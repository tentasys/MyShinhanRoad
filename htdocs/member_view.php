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
    <link href="/vendors/bootstrap/dist/css/bootstrap.min.css" rel="stylesheet">
    <!-- Font Awesome -->
    <link href="/vendors/font-awesome/css/font-awesome.min.css" rel="stylesheet">
    <!-- NProgress -->
    <link href="/vendors/nprogress/nprogress.css" rel="stylesheet">

    
    <!-- bootstrap-wysiwyg -->
    <link href="/vendors/google-code-prettify/bin/prettify.min.css" rel="stylesheet">
    <!-- Select2 -->
    <link href="/vendors/select2/dist/css/select2.min.css" rel="stylesheet">
    <!-- Switchery -->
    <link href="/vendors/switchery/dist/switchery.min.css" rel="stylesheet">
    <!-- starrr -->
    <link href="/vendors/starrr/dist/starrr.css" rel="stylesheet">
    <!-- bootstrap-daterangepicker -->
    <link href="/vendors/bootstrap-daterangepicker/daterangepicker.css" rel="stylesheet">

    <!-- Custom Theme Style -->
    <link href="/build/css/custom.min.css" rel="stylesheet">
  </head>

  <?php
        $connect = mysqli_connect('192.168.1.156', 'root', '', 'shinple') or die (mysql_error()); 
        $L = $_POST['mem_1'];
        
        $query = <<<_SQL
        SELECT mem_point FROM member WHERE mem_num = $L;
        _SQL;

        $result = $connect->query($query); 

        $query_1 = <<<_SQL
        SELECT* FROM (SELECT l.mem_num,c.course_title,l.learn_state
        FROM course c JOIN learn_course l ON (c.course_num = l.course_num)
        ORDER BY l.mem_num)c WHERE c.mem_num = $L;
        _SQL;

        $result_1 = $connect->query($query_1); 

        $query_2 = <<<_SQL
        SELECT mem_num, mem_name FROM member WHERE mem_num = $L ;
        _SQL;

        $result_2 = $connect->query($query_2); 
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
                <h3>회사별 회원 관리<small>&nbsp;&nbsp;신한DS 직원들을 위한 LMS, Shinple</small></h3>
              </div>

              <div class="title_right">
                
              </div>
            </div>

            <div class="clearfix"></div>

            <div class="row">
              <div class="col-md-12 col-sm-12 col-xs-12">
                <div class="x_panel" >
                            <div class="x_title" >
                                <h2><b>사&nbsp;&nbsp;번</b>&nbsp;&nbsp;<?php 
                                     $rows = mysqli_fetch_assoc($result_2);
                                     echo $rows['mem_num'] ?>&nbsp;&nbsp;&nbsp;&nbsp;
                                     <b>성&nbsp;&nbsp;명</b>&nbsp;&nbsp;<?php 
                                      // $rows = mysqli_fetch_assoc($result_2);
                                     echo $rows['mem_name'] ?></h2>
                                <ul class="nav navbar-right panel_toolbox">
                                </ul>
                            <div class="clearfix"></div>
                            </div>
                    <div class="x_content"style="width: 510px";>
                        <br/>
                        <form class="form-horizontal form-label-left">
                            <div class="table-responsive">
                                <table class="table table-striped jambo_table bulk_action" >
                                    <thead>
                                        <tr class="headings" >
                                        
                                        <th class="column-title" style="text-align:center;">Learnig Point </th>
            
                                        <th class="bulk-actions" colspan="7">
                                            <a class="antoo" style="color:#fff; font-weight:500;">Bulk Actions ( <span class="action-cnt"> </span> ) <i class="fa fa-chevron-down"></i></a>
                                        </th>
                                        </tr>
                                    </thead>
        
                                    <tbody>
                                    <?php 
                                $num = mysqli_num_rows($result);
                            
                                while($rows =  mysqli_fetch_assoc($result)){
                                    if($num%2==0){
                            ?>
                            
                                    <tr class="even pointer" style="text-align: center;">
                            <?php 
                                }
                                else{
                            ?>
                                    <tr class="odd pointer" style="text-align: center;">
                            <?php
                                }
                            ?>
                            
                                    <tr class="even pointer" style="text-align: center;">
                                        <td class=" "style="height: 117px; padding-top: 50; font-size: 3em; padding-bottom: 0; padding-top: 25px;"><?php echo $rows['mem_point']?></td> 
                                        </tr>
                                        <?php
                                }
                            ?>
                                    </tbody>
                                </table>
                            </div>
                        </form>
                    </div> <!--x_content 끝-->
                    
                    <div class="x_content" style="width: 710px; text-align:center;">
                            <br/>
                        <form class="form-horizontal form-label-left">
                            <div class="table-responsive">
                                <table class="table table-striped jambo_table bulk_action">
                                    <thead>
                                        <tr class="headings" >
                                        <th class="column-title" style="text-align:center;">수강중인 강좌 </th>
                                        <th class="column-title" style="text-align:center;">수료 여부 </th>
            
                                        <th class="bulk-actions" colspan="7">
                                            <a class="antoo" style="color:#fff; font-weight:500;">Bulk Actions ( <span class="action-cnt"> </span> ) <i class="fa fa-chevron-down"></i></a>
                                        </th>
                                        </tr>
                                    </thead>
        
                                    <tbody>

                                    <?php 
                                $num = mysqli_num_rows($result_1);
                            
                                while($rows =  mysqli_fetch_assoc($result_1)){
                                    if($num%2==0){
                            ?>
                            
                                    <tr class="even pointer" style="text-align: center;">
                            <?php 
                                }
                                else{
                            ?>
                                    <tr class="odd pointer" style="text-align: center;">
                            <?php
                                }
                            ?>
                            
                                        <td class=" "><?php echo $rows['course_title']?></td>
                                        <td class=" "><?php if ($rows['learn_state']==3){
                                          ?>
                                          수료 완료
                                          <?php 
                                            }else if ($rows['learn_state']==2){
                                        ?>
                                          수료 대기
                                              <?php
                                            } else {
                                            ?>
                                              수강 중
                                    <?php 
                                        }?>
                                          </td>
                                        <?php
                                }
                            ?>





                                  
                                    </tbody>
                                </table>
                            </div>
                        </form>
                     </div>

                </div>
            </div>
         </div>
        <!-- /page content -->

                    <!-- footer content -->
                    <footer style="margin-left: 0px;">
                    <div style="text-align: center;">
                    Copyright ⓒ 2019. Shinhan DS. All right reserved. <a href="https://colorlib.com"></a>
                    </div>
                    <div class="clearfix"></div>
                    </footer>
                    <!-- /footer content -->
      </div>
    </div>

    <!-- jQuery -->
    <script src="../vendors/jquery/dist/jquery.min.js"></script>
    <!-- Bootstrap -->
    <script src="../vendors/bootstrap/dist/js/bootstrap.min.js"></script>
    <!-- FastClick -->
    <script src="../vendors/fastclick/lib/fastclick.js"></script>
    <!-- NProgress -->
    <script src="../vendors/nprogress/nprogress.js"></script>
    
    <!-- bootstrap-progressbar -->
    <script src="../vendors/bootstrap-progressbar/bootstrap-progressbar.min.js"></script>
    <!-- iCheck -->
    <script src="../vendors/iCheck/icheck.min.js"></script>
    <!-- bootstrap-daterangepicker -->
    <script src="../vendors/moment/min/moment.min.js"></script>
    <script src="../vendors/bootstrap-daterangepicker/daterangepicker.js"></script>
    <!-- bootstrap-wysiwyg -->
    <script src="../vendors/bootstrap-wysiwyg/js/bootstrap-wysiwyg.min.js"></script>
    <script src="../vendors/jquery.hotkeys/jquery.hotkeys.js"></script>
    <script src="../vendors/google-code-prettify/src/prettify.js"></script>
    <!-- jQuery Tags Input -->
    <script src="../vendors/jquery.tagsinput/src/jquery.tagsinput.js"></script>
    <!-- Switchery -->
    <script src="../vendors/switchery/dist/switchery.min.js"></script>
    <!-- Select2 -->
    <script src="../vendors/select2/dist/js/select2.full.min.js"></script>
    <!-- Parsley -->
    <script src="../vendors/parsleyjs/dist/parsley.min.js"></script>
    <!-- Autosize -->
    <script src="../vendors/autosize/dist/autosize.min.js"></script>
    <!-- jQuery autocomplete -->
    <script src="../vendors/devbridge-autocomplete/dist/jquery.autocomplete.min.js"></script>
    <!-- starrr -->
    <script src="../vendors/starrr/dist/starrr.js"></script>

    <!-- Custom Theme Scripts -->
    <script src="../build/js/custom.min.js"></script>

    <!-- Custom script Add -->
    <script src="../build/js/script.js"></script>
  </body>
</html>
