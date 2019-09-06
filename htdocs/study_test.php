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
  $connect = mysqli_connect("192.168.1.156", "root", "",'shinple') or die (mysql_error());
  $query ="select distinct course_name from problem order by course_name desc";
  $result = $connect->query($query);
  $total = mysqli_num_rows($result);
  

?>
<!-- 규진오빠 어디감 -->

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
                <h3>테스트 관리<small>&nbsp;&nbsp;신한DS 직원들을 위한 LMS, Shinple</small></h3>
              </div>

            </div>

            <div class="clearfix"></div>

            <div class="row">
            
              
              <div class="col-md-12 col-sm-12 col-xs-12">
                <div class="x_panel">
                  <div class="x_title">
                    <h2>강좌별 테스트 리스트</h2>
                    <div class="clearfix"></div>
                  </div>

                  <div class="x_content">

                    
                    <div class="table-responsive">
                      <table class="table table-striped jambo_table bulk_action">
                        <thead>
                          <tr class="headings" >
                            <th style="text-align:center;">
                              <!-- <input type="checkbox" id="check-all" class="flat" > -->
                            </th>
                            <th class="column-title" style="text-align:center;">강좌 명 </th>
                            <!-- <th class="column-title" style="text-align:center;">레벨</th> -->
                            <!-- <th class="column-title">Bill to Name </th> -->
                            <!-- <th class="column-title">태그</th> -->
                            <!-- <th class="column-title">Amount </th> -->
                            <th class="column-title no-link last" style="text-align:center;"><span class="nobr">보기</span>
                            </th>
                            <th class="bulk-actions" colspan="7">
                              <a class="antoo" style="color:#fff; font-weight:500;">Bulk Actions ( <span class="action-cnt"> </span> ) <i class="fa fa-chevron-down"></i></a>
                            </th>
                          </tr>
                        </thead>

                        <tbody>
                        <form action="test_delete.php" method="POST">
                        <?php
                        
                                while($rows = mysqli_fetch_assoc($result)){
                                  $number = $total; //DB에 저장된 데이터 수 (열 기준)
                                        if($total%2==0){
                        ?>                      <tr class="even pointer" style="text-align:center;">
                                        <?php   }
                                        else{
                        ?>                      <tr class="odd pointer" style="text-align:center;">
                                        <?php } ?>
                            
                            <td class="a-center ">   
                              
                              <input type="checkbox" class="flat" name="table_records[]" value="<?php echo $rows['course_name']?>">                           
                            </td>
                            
                            <td class=" "><?php echo $rows['course_name']?></td>
                            <!-- <td class=" ">Lv.3</td>
                            <td class=" ">파이썬</td> -->
                            
                            <td class=" last"><a href=<?php echo "test_edit.php?view_list=".htmlentities(urlencode($rows['course_name'])) ?>>View</a>
                                          
                            </td>
                            
                          </tr>
                          <?php
                                    $total--;
                                    }
                            ?>



                         
                        </tbody>
                      </table>
                      <button type="submit" class="btn btn-round btn-danger" onclick="alert('삭제하시겠습니까?')">선택 삭제</button>
                      <button type="button" class="btn btn-round btn-success" onclick="location.href='test_write.php'" style="float: right;">테스트 등록</button>
                    </div>
							
						
                  </div>
                </div>
              </div>
            </div>
          </div>
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
    <!-- FastClick -->
    <script src="vendors/fastclick/lib/fastclick.js"></script>
    <!-- NProgress -->
    <script src="vendors/nprogress/nprogress.js"></script>
    <!-- iCheck -->
    <script src="vendors/iCheck/icheck.min.js"></script>

    <!-- Custom Theme Scripts -->
    <script src="build/js/custom.min.js"></script>

    <!-- Custom script Add -->
    <script src="build/js/script.js"></script>
  </body>
</html>