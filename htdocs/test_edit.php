
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
    <!-- JQVMap -->
    <link href="vendors/jqvmap/dist/jqvmap.min.css" rel="stylesheet"/>
    <!-- bootstrap-daterangepicker -->
    <link href="vendors/bootstrap-daterangepicker/daterangepicker.css" rel="stylesheet">

    <!-- Custom Theme Style -->
    <link href="build/css/custom.min.css" rel="stylesheet">
    <style>
    /* #tags_1{
      height:10px;
    } */
    #tags_1_tagsinput{
      height:10px;
    }
    </style>
  </head>
  <?php
  // 배열 만들어버리기 쿼리짜기귀찮다

  


  $tot = $_GET['view_list'];

  $connect = mysqli_connect("192.168.1.156", "root", "",'shinple') or die (mysql_error());
  $query ="select * from problem where course_name = '$tot'";
  $result = $connect->query($query);
  $course_name = mysqli_fetch_assoc($result);


  $query ="select problem_answer from problem where course_name = '$tot'";
  $result = $connect->query($query);
  $problem_answer = array();
  $i = 0;

  while($row = mysqli_fetch_row($result)){

    $problem_answer[$i] = $row[0];

    $i = $i + 1;

  }

  $query ="select problem_txt from problem where course_name = '$tot'";
  $result = $connect->query($query);
  $problem_txt = array();
  $i = 0;

  while($row = mysqli_fetch_row($result)){

    $problem_txt[$i] = $row[0];

    $i = $i + 1;

  }

  $query ="select problem_no1 from problem where course_name = '$tot'";
  $result = $connect->query($query);
  $problem_no1 = array();
  $i = 0;

  while($row = mysqli_fetch_row($result)){

    $problem_no1[$i] = $row[0];

    $i = $i + 1;

  }

  $query ="select problem_no2 from problem where course_name = '$tot'";
  $result = $connect->query($query);
  $problem_no2 = array();
  $i = 0;
  
  while($row = mysqli_fetch_row($result)){

    $problem_no2[$i] = $row[0];

    $i = $i + 1;

  }

  $query ="select problem_no3 from problem where course_name = '$tot'";
  $result = $connect->query($query);
  $problem_no3 = array();
  $i = 0;

  while($row = mysqli_fetch_row($result)){

    $problem_no3[$i] = $row[0];

    $i = $i + 1;

  }

  $query ="select problem_no4 from problem where course_name = '$tot'";
  $result = $connect->query($query);
  $problem_no4 = array();
  $i = 0;

  while($row = mysqli_fetch_row($result)){

    $problem_no4[$i] = $row[0];

    $i = $i + 1;

  }

    
    

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
                <h3>테스트 관리<small>&nbsp;&nbsp;신한DS 직원들을 위한 LMS, Shinple</small></h3>
              </div>
            </div>

            <div class="clearfix"></div>

            <div class="row">
              <div class="col-md-12 col-sm-12 col-xs-12">
                <div class="x_panel">
                  <div class="x_title">
                    <h2>테스트 편집</h2>
                    
                    <div class="clearfix"></div>
                  </div>
                  <div class="x_content" style="margin-top: 0px;">
                    <br />
                    
                    <form action="modify.php" method="POST">
                    
                      <div class="form-group">
                        <label class="control-label col-md-3 col-sm-3 col-xs-12" style="text-align:right">강좌명</label>
                        <div>
                          <span style="display: table; vertical-align:middle; margin-top: 8px; padding-left: 10px;"><?php echo $course_name['course_name']?></span>
                        </div>
                      </div>
                      <!-- 문제1 -->
                      
                      <div class="form-group">
                        <label class="control-label col-md-3 col-sm-3 col-xs-12" style="padding-top: 9px; text-align:right;">문제 1</label> 
                        <div class="col-md-9 col-sm-9 col-xs-12" style="padding-top: 8px; margin-bottom: 15px;">
                        <textarea name = problem_txt[] class="form-control" rows="3"  style="resize: none;"><?php echo $problem_txt[0]?></textarea>
                        </div>
                      </div>

                      <div class="form-group">
                        <label class="control-label col-md-3 col-sm-3 col-xs-12" style="padding-top: 9px; text-align:right;">보기 1번</label>
                        <div class="col-md-9 col-sm-9 col-xs-12" style="margin-bottom: 15px;">
                          <textarea name = problem_no1[] class="form-control" ><?php echo $problem_no1[0]?></textarea>
                        </div>
                      </div>

                      <div class="form-group">
                        <label class="control-label col-md-3 col-sm-3 col-xs-12" style="padding-top: 9px; text-align:right;">보기 2번</label>
                        <div class="col-md-9 col-sm-9 col-xs-12" style="margin-bottom: 15px;">
                            <textarea name = problem_no2[] class="form-control" ><?php echo $problem_no2[0]?></textarea>
                        </div>
                      </div>
  
                      <div class="form-group">
                        <label class="control-label col-md-3 col-sm-3 col-xs-12" style="padding-top: 9px; text-align:right;">보기 3번</label>
                          <div class="col-md-9 col-sm-9 col-xs-12" style="margin-bottom: 15px;">
                            <textarea name = problem_no3[] class="form-control" ><?php echo $problem_no3[0]?></textarea> 
                          </div>
                      </div>
  
                      <div class="form-group">
                        <label class="control-label col-md-3 col-sm-3 col-xs-12" style="padding-top: 9px; text-align:right;">보기 4번</label>
                          <div class="col-md-9 col-sm-9 col-xs-12" style="margin-bottom: 15px;">
                            <textarea name = problem_no4[] class="form-control" ><?php echo $problem_no4[0]?></textarea> 
                          </div>
                      </div>

                      <div class="form-group1">
                        <label class="col-md-3 col-sm-3 col-xs-12 control-label" style="padding-top: 9px; text-align:right;">정답</small></label>
                        <div class="col-md-9 col-sm-9 col-xs-12">
                          <div class="radio">
                            <label>
                              <input type="radio" class="flat1" checked name="problem_answer[0]" value="1">1번
                              &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input type="radio" class="flat1" name="problem_answer[0]" value="2">2번
                              &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input type="radio" class="flat1" name="problem_answer[0]" value="3">3번
                              &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input type="radio" class="flat1" name="problem_answer[0]" value="4">4번
                            </label>
                          </div>
                        </div>
                      </div>          
                    <div class="tags_clear"></div>    
                    <div class="ln_solid"></div>


                      <div class="form-group">
                        <label class="control-label col-md-3 col-sm-3 col-xs-12" style="padding-top: 9px; text-align:right;">문제 2</label> 
                        <div class="col-md-9 col-sm-9 col-xs-12" style="padding-top: 8px; margin-bottom: 15px;">
                        <textarea name = problem_txt[] class="form-control" rows="3"  style="resize: none;"><?php echo $problem_txt[1]?></textarea>
                        </div>
                      </div>

                      <div class="form-group">
                        <label class="control-label col-md-3 col-sm-3 col-xs-12" style="padding-top: 9px; text-align:right;">보기 1번</label>
                        <div class="col-md-9 col-sm-9 col-xs-12" style="margin-bottom: 15px;">
                          <textarea name = problem_no1[] class="form-control" ><?php echo $problem_no1[1]?></textarea>
                        </div>
                      </div>

                      <div class="form-group">
                        <label class="control-label col-md-3 col-sm-3 col-xs-12" style="padding-top: 9px; text-align:right;">보기 2번</label>
                        <div class="col-md-9 col-sm-9 col-xs-12" style="margin-bottom: 15px;">
                            <textarea name = problem_no2[] class="form-control" ><?php echo $problem_no2[1]?></textarea>
                        </div>
                      </div>
  
                      <div class="form-group">
                        <label class="control-label col-md-3 col-sm-3 col-xs-12" style="padding-top: 9px; text-align:right;">보기 3번</label>
                          <div class="col-md-9 col-sm-9 col-xs-12" style="margin-bottom: 15px;">
                            <textarea name = problem_no3[] class="form-control" ><?php echo $problem_no3[1]?></textarea> 
                          </div>
                      </div>
  
                      <div class="form-group">
                        <label class="control-label col-md-3 col-sm-3 col-xs-12" style="padding-top: 9px; text-align:right;">보기 4번</label>
                          <div class="col-md-9 col-sm-9 col-xs-12" style="margin-bottom: 15px;">
                            <textarea name = problem_no4[] class="form-control" ><?php echo $problem_no4[1]?></textarea> 
                          </div>
                      </div>

                      <div class="form-group1">
                        <label class="col-md-3 col-sm-3 col-xs-12 control-label" style="padding-top: 9px; text-align:right;">정답</small></label>
                        <div class="col-md-9 col-sm-9 col-xs-12">
                          <div class="radio">
                            <label>
                              <input type="radio" class="flat2" checked name="problem_answer[1]" value="1">1번
                              &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input type="radio" class="flat2" name="problem_answer[1]" value="2">2번
                              &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input type="radio" class="flat2" name="problem_answer[1]" value="3">3번
                              &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input type="radio" class="flat2" name="problem_answer[1]" value="4">4번
                            </label>
                          </div>
                        </div>
                      </div>          
                    <div class="tags_clear"></div>    
                    <div class="ln_solid"></div>

                <!-- 문제 3번 -->
                      <div class="form-group">
                        <label class="control-label col-md-3 col-sm-3 col-xs-12" style="padding-top: 9px; text-align:right;">문제 3</label> 
                        <div class="col-md-9 col-sm-9 col-xs-12" style="padding-top: 8px; margin-bottom: 15px;">
                        <textarea name = problem_txt[] class="form-control" rows="3"  style="resize: none;"><?php echo $problem_txt[2]?></textarea>
                        </div>
                      </div>

                      <div class="form-group">
                        <label class="control-label col-md-3 col-sm-3 col-xs-12" style="padding-top: 9px; text-align:right;">보기 1번</label>
                        <div class="col-md-9 col-sm-9 col-xs-12" style="margin-bottom: 15px;">
                          <textarea name = problem_no1[] class="form-control" ><?php echo $problem_no1[2]?></textarea>
                        </div>
                      </div>

                      <div class="form-group">
                        <label class="control-label col-md-3 col-sm-3 col-xs-12" style="padding-top: 9px; text-align:right;">보기 2번</label>
                        <div class="col-md-9 col-sm-9 col-xs-12" style="margin-bottom: 15px;">
                            <textarea name = problem_no2[] class="form-control" ><?php echo $problem_no2[2]?></textarea>
                        </div>
                      </div>
  
                      <div class="form-group">
                        <label class="control-label col-md-3 col-sm-3 col-xs-12" style="padding-top: 9px; text-align:right;">보기 3번</label>
                          <div class="col-md-9 col-sm-9 col-xs-12" style="margin-bottom: 15px;">
                            <textarea name = problem_no3[] class="form-control" ><?php echo $problem_no3[2]?></textarea> 
                          </div>
                      </div>
  
                      <div class="form-group">
                        <label class="control-label col-md-3 col-sm-3 col-xs-12" style="padding-top: 9px; text-align:right;">보기 4번</label>
                          <div class="col-md-9 col-sm-9 col-xs-12" style="margin-bottom: 15px;">
                            <textarea name = problem_no4[] class="form-control" ><?php echo $problem_no4[2]?></textarea> 
                          </div>
                      </div>

                      <div class="form-group1">
                        <label class="col-md-3 col-sm-3 col-xs-12 control-label" style="padding-top: 9px; text-align:right;">정답</small></label>
                        <div class="col-md-9 col-sm-9 col-xs-12">
                          <div class="radio">
                            <label>
                              <input type="radio" class="flat3" checked name="problem_answer[2]" value="1">1번
                              &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input type="radio" class="flat3" name="problem_answer[2]" value="2">2번
                              &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input type="radio" class="flat3" name="problem_answer[2]" value="3">3번
                              &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input type="radio" class="flat3" name="problem_answer[2]" value="4">4번
                            </label>
                          </div>
                        </div>
                      </div>          
                    <div class="tags_clear"></div>    
                    <div class="ln_solid"></div>
                                <!-- 문제 4번 -->
                                <div class="form-group">
                        <label class="control-label col-md-3 col-sm-3 col-xs-12" style="padding-top: 9px; text-align:right;">문제 4</label> 
                        <div class="col-md-9 col-sm-9 col-xs-12" style="padding-top: 8px; margin-bottom: 15px;">
                        <textarea name = problem_txt[] class="form-control" rows="3"  style="resize: none;"><?php echo $problem_txt[3]?></textarea>
                        </div>
                      </div>

                      <div class="form-group">
                        <label class="control-label col-md-3 col-sm-3 col-xs-12" style="padding-top: 9px; text-align:right;">보기 1번</label>
                        <div class="col-md-9 col-sm-9 col-xs-12" style="margin-bottom: 15px;">
                          <textarea name = problem_no1[] class="form-control" ><?php echo $problem_no1[3]?></textarea>
                        </div>
                      </div>

                      <div class="form-group">
                        <label class="control-label col-md-3 col-sm-3 col-xs-12" style="padding-top: 9px; text-align:right;">보기 2번</label>
                        <div class="col-md-9 col-sm-9 col-xs-12" style="margin-bottom: 15px;">
                            <textarea name = problem_no2[] class="form-control" ><?php echo $problem_no2[3]?></textarea>
                        </div>
                      </div>
  
                      <div class="form-group">
                        <label class="control-label col-md-3 col-sm-3 col-xs-12" style="padding-top: 9px; text-align:right;">보기 3번</label>
                          <div class="col-md-9 col-sm-9 col-xs-12" style="margin-bottom: 15px;">
                            <textarea name = problem_no3[] class="form-control" ><?php echo $problem_no3[3]?></textarea> 
                          </div>
                      </div>
  
                      <div class="form-group">
                        <label class="control-label col-md-3 col-sm-3 col-xs-12" style="padding-top: 9px; text-align:right;">보기 4번</label>
                          <div class="col-md-9 col-sm-9 col-xs-12" style="margin-bottom: 15px;">
                            <textarea name = problem_no4[] class="form-control" ><?php echo $problem_no4[3]?></textarea> 
                          </div>
                      </div>

                      <div class="form-group1">
                        <label class="col-md-3 col-sm-3 col-xs-12 control-label" style="padding-top: 9px; text-align:right;">정답</small></label>
                        <div class="col-md-9 col-sm-9 col-xs-12">
                          <div class="radio">
                            <label>
                              <input type="radio" class="flat4" checked name="problem_answer[3]" value="1">1번
                              &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input type="radio" class="flat4" name="problem_answer[3]" value="2">2번
                              &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input type="radio" class="flat4" name="problem_answer[3]" value="3">3번
                              &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input type="radio" class="flat4" name="problem_answer[3]" value="4">4번
                            </label>
                          </div>
                        </div>
                      </div>          
                    <div class="tags_clear"></div>    
                    <div class="ln_solid"></div>
                                <!-- 문제 5번 -->
                        <div class="form-group">
                        <label class="control-label col-md-3 col-sm-3 col-xs-12" style="padding-top: 9px; text-align:right;">문제 5</label> 
                        <div class="col-md-9 col-sm-9 col-xs-12" style="padding-top: 8px; margin-bottom: 15px;">
                        <textarea name = problem_txt[] class="form-control" rows="3"  style="resize: none;"><?php echo $problem_txt[0]?></textarea>
                        </div>
                      </div>

                      <div class="form-group">
                        <label class="control-label col-md-3 col-sm-3 col-xs-12" style="padding-top: 9px; text-align:right;">보기 1번</label>
                        <div class="col-md-9 col-sm-9 col-xs-12" style="margin-bottom: 15px;">
                          <textarea name = problem_no1[] class="form-control" ><?php echo $problem_no1[4]?></textarea>
                        </div>
                      </div>

                      <div class="form-group">
                        <label class="control-label col-md-3 col-sm-3 col-xs-12" style="padding-top: 9px; text-align:right;">보기 2번</label>
                        <div class="col-md-9 col-sm-9 col-xs-12" style="margin-bottom: 15px;">
                            <textarea name = problem_no2[] class="form-control" ><?php echo $problem_no2[4]?></textarea>
                        </div>
                      </div>
  
                      <div class="form-group">
                        <label class="control-label col-md-3 col-sm-3 col-xs-12" style="padding-top: 9px; text-align:right;">보기 3번</label>
                          <div class="col-md-9 col-sm-9 col-xs-12" style="margin-bottom: 15px;">
                            <textarea name = problem_no3[] class="form-control" ><?php echo $problem_no3[4]?></textarea> 
                          </div>
                      </div>
  
                      <div class="form-group">
                        <label class="control-label col-md-3 col-sm-3 col-xs-12" style="padding-top: 9px; text-align:right;">보기 4번</label>
                          <div class="col-md-9 col-sm-9 col-xs-12" style="margin-bottom: 15px;">
                            <textarea name = problem_no4[] class="form-control" ><?php echo $problem_no4[4]?></textarea> 
                          </div>
                      </div>

                      <div class="form-group1">
                        <label class="col-md-3 col-sm-3 col-xs-12 control-label" style="padding-top: 9px; text-align:right;">정답</small></label>
                        <div class="col-md-9 col-sm-9 col-xs-12">
                          <div class="radio">
                            <label>
                              <input type="radio" class="flat5" checked name="problem_answer[4]" value="1">1번
                              &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input type="radio" class="flat5" name="problem_answer[4]" value="2">2번
                              &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input type="radio" class="flat5" name="problem_answer[4]" value="3">3번
                              &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input type="radio" class="flat5" name="problem_answer[4]" value="4">4번
                            </label>
                          </div>
                        </div>
                      </div>          
                    <div class="tags_clear"></div>    
                    <div class="ln_solid"></div>
                      <div class="form-group">
                        <div class="col-md-9 col-sm-9 col-xs-12 col-md-offset-3">
                          <button type="submit" class="btn btn-success" style="float:right;">저장</button>
                         
                          <button type="button" class="btn btn-primary" onclick="location.href='study_test.php'" style="float:right;">취소</button>
                          
                          <!-- <button type="reset" class="btn btn-primary">Reset</button> -->
                          
                        </div>
                      </div>
                      
                       
                      </div>

                    </form>
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
    <style>
        .tagsinput{
          height: 20px !important;
        }
        #tags_1_tagsinput{
          height: 20px !important;
        }
      </style>
  </body>
</html>