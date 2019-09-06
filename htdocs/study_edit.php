<?php 
  $con = mysqli_connect('192.168.1.156', 'root', '', 'shinple');

  $query = "select tag_name from tag";
  $result = $con->query($query);
  $total = array();
  $i=0;
  while($row = mysqli_fetch_row($result))
  {
    $total[$i] = $row[0];
    $i = $i +1;
  }   // echo json_encode($total, JSON_FORCE_OBJECT);
?>

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
   
	
    <!-- bootstrap-progressbar -->
    <link href="vendors/bootstrap-progressbar/css/bootstrap-progressbar-3.3.4.min.css" rel="stylesheet">
    <!-- bootstrap-daterangepicker -->
    <link href="vendors/bootstrap-daterangepicker/daterangepicker.css" rel="stylesheet">

    <!-- Custom Theme Style -->
    <link href="build/css/custom.min.css" rel="stylesheet">

    <script src="vendors/jquery.tagsinput/src/jquery.tagsinput.js"></script>

    <style>
    /* #tags_1{
      height:10px;
    } */
    #tags_1_tagsinput{
      height:10px;
    }
    </style>
    
    <!-- 태그 수정중 -->
   
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/select2/4.0.10/js/select2.min.js"></script>
    <link href="https://cdnjs.cloudflare.com/ajax/libs/select2/4.0.10/css/select2.min.css" rel="stylesheet">
    <!-- <link href="select2.min.css" rel="stylesheet" />
    <script src="select2.min.js"></script> -->



    <?php
      $js_array = json_encode($total);
      echo '<script>
          
      $(document).ready(function()
      {
          var mSelect2= '. $js_array . ';
          $("#select2_example").select2({
            data:mSelect2, maximumSelectionLength:3
          });
      });

      </script>';
    ?>

  <script></script>

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

            <br/>
            
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
  
                    <li><a><i class="fa fa-file"></i> &nbsp;&nbsp;로&nbsp;&nbsp;&nbsp;&nbsp;그 <span class="fa fa-chevron-down"></span></a>
                      <ul class="nav child_menu">
                        <li><a href="log_view.php">로그 관리</a></li>
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
            </div>

            <div class="row">
              <div class="col-md-12 col-sm-12 col-xs-12">
                <div class="x_panel">
                  <div class="x_title">
                    <h2>강좌 등록 <small>강좌 등록 시 반드시 <code>태그</code>를 등록해 주세요.</small></h2>
                    
                    <div class="clearfix"></div>
                  </div>

                  <div class="x_content">     
                    <form action = "study_write1.php" method = "post">
                      <div class = "form-group" style="text-align:right;">
                        <label class = "control-label col-md-3 col-sm-3 col-xs-12" style="padding-top: 7px;">강좌명</label>
                        <div class = "col-md-9 col-sm-9 col-xs-12" style="margin-bottom:15px;">
                          <input type = "text" class = "form-control" name = "course_title" placeholder = "강좌명을 입력하세요."/>
                        </div>
                      </div>

                      <div class="form-group" style="text-align:right;">
                        <label class="control-label col-md-3 col-sm-3 col-xs-12" style="padding-top: 7px;">강사명</label>
                        <div class="col-md-9 col-sm-9 col-xs-12" style="margin-bottom:15px;">
                          <input type="text" class="form-control" name = "course_teacher" placeholder="강사명을 입력하세요."/>
                        </div>
                      </div>

                      <div class="form-group" style="text-align:right;">
                        <label class="control-label col-md-3 col-sm-3 col-xs-12" style="padding-top: 7px;">강좌설명</label>
                        <div class="col-md-9 col-sm-9 col-xs-12" style="margin-bottom:15px;">
                          <textarea class="form-control" rows="3" name = "course_text" placeholder="강좌에 대한 설명을 입력하세요." style="resize: none;"></textarea>
                        </div>
                      </div>
                      
                      <div class="form-group" >
                        <label class="control-label col-md-3 col-sm-3 col-xs-12" style="text-align:right; padding-top: 7px;">태그 선택</label>
                        <div class="col-md-9 col-sm-9 col-xs-12" style="margin-bottom:15px;">
                          <select id="select2_example" multiple size="3" style="width: 300px;" name = "course_tag[]">
                          <!-- <option value="" selected hiddenlected hidden> -- Select tags -- </option> -->
                          <!-- <option>test</option> -->
                        </select>
                        </div>
                      </div>

                      <div class="form-group style=">
                        <label class="col-md-3 col-sm-3 col-xs-12 control-label" name = "course_necessary" style="text-align:right; padding-top:11px;">필수 여부</small></label> <!-- name 필요 없는거-->
                        <div class="col-md-9 col-sm-9 col-xs-12">
                          <div class="radio">
                          &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input type="radio" class="flat" checked name="iCheck2" value = '1'/>필수
                          &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input type="radio" class="flat" name="iCheck2" value = '2'/>선택
                          </div>
                        </div>
                      </div>
                        
                      <div class="form-group">
                        <label class="col-md-3 col-sm-3 col-xs-12 control-label" name = "course_level" style="text-align:right; padding-top:11px;">강좌 레벨</small></label> <!-- name 필요 없는거-->
                        <div class="col-md-9 col-sm-9 col-xs-12" style="margin-bottom:15px;">
                          <div class="radio">
                            <label>
                              <input type="radio" class="flat" checked name="iCheck" value = '1'/>레벨1
                              &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input type="radio" class="flat" name="iCheck" value = '2'/>레벨2
                              &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input type="radio" class="flat" name="iCheck" value = '3'/>레벨3
                              &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input type="radio" class="flat" name="iCheck" value = '4'/>레벨4
                              &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input type="radio" class="flat" name="iCheck" value = '5'/>레벨5
                              &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input type="radio" class="flat" name="iCheck" value = '6'/>레벨6
                              &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input type="radio" class="flat" name="iCheck" value = '7'/>레벨7
                            </label>
                          </div>
                        </div>
                      </div>

                      

                      <!--칸 맞추를 위한 광기의 br-->
                      <br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br>

                     
                      <div class="tags_clear"></div>

                      <div class="form-group">
                        <div class="col-md-9 col-sm-9 col-xs-12 col-md-offset-3"  >
                          <button type="submit" class="btn btn-success" style="float:right">저장</button>
                          <button type="button" class="btn btn-primary" onclick="study_list()" style="float:right">취소</button>
                        </div>
                      </div>
                    </form>

                    

                    <!--대표 사진 업로드-->
                    <form action = "picture_upload.php" method = "post" enctype = "multipart/form-data">

                      <div class="ln_solid"></div>
                      <div class="tags_clear"></div>

                      <div class="form-group">
                        <label class="control-label col-md-3 col-sm-3 col-xs-12" style="text-align:right; padding-top:7px;">강좌 대표 사진</label>
                        <div class="col-md-9 col-sm-9 col-xs-12" style="margin-bottom:15px;">
                          <input type="text" class="form-control" placeholder="강좌명을 정확히 입력하세요." name = "Course_picture_title"/>
                        </div>
                      </div>
                      
                      <br><br>

                      <div class="form-group">
                        <label class="control-label col-md-3 col-sm-3 col-xs-12" style="text-align:right;">대표 사진</label>
                        <!--파일 업로드-->
                        <div class="filebox">
                              <input type="file" name = "upload" id = "fileUpload" style="padding-left: 10px;">
                               &nbsp;&nbsp;&nbsp;강좌의 대표 사진을 첨부해주세요.
                              <!-- <img id="=upload_file"> -->
                        </div>
                      </div>

                      
                      <div class="tags_clear"></div>

                      <div class="form-group">
                        <div class="col-md-9 col-sm-9 col-xs-12 col-md-offset-3">
                          <button type="submit" class="btn btn-success" style="float:right">저장</button>
                          <button type="button" class="btn btn-primary" onclick="study_list()" style="float:right">취소</button>
                        </div>
                      </div>
                    </form>



                    <!-- 강의 업로드 -->
                    <form action = "video_upload4.php" method = "post" enctype = "multipart/form-data">

                      <div class="ln_solid"></div>
                      <div class="tags_clear"></div>

                      <div class="form-group">
                        <label class="control-label col-md-3 col-sm-3 col-xs-12" style="text-align:right; padding-top:7px;">강의명</label>
                        <div class="col-md-9 col-sm-9 col-xs-12" style="margin-bottom:15px;">
                          <input type="text" class="form-control" name = "lecture_title" placeholder="강의명을 입력하세요."/>
                        </div>
                      </div>

                      <div class="form-group">
                        <label class="control-label col-md-3 col-sm-3 col-xs-12" style="text-align:right; padding-top:7px;">강의설명</label>
                        <div class="col-md-9 col-sm-9 col-xs-12" style="margin-bottom:15px;">
                          <textarea class="form-control" rows="3" name = "lecture_text" placeholder="강의에 대한 설명을 입력하세요." style="resize: none;"></textarea>
                        </div>
                      </div>

                      <div class="form-group">
                        <label class="control-label col-md-3 col-sm-3 col-xs-12" style="text-align:right; padding-top:7px;">강의가 속해있는 강좌명</label>
                        <div class="col-md-9 col-sm-9 col-xs-12" style="margin-bottom:15px;">
                        <input type="text" class="form-control" name = "lecture_course" placeholder="강의가 속해있는 강좌를 입력하세요."/>
                        </div>
                      </div>

                      <div class="form-group">
                        <label class="control-label col-md-3 col-sm-3 col-xs-12" style="text-align:right; padding-top:7px;">강의 순서</label>
                        <div class="col-md-9 col-sm-9 col-xs-12" style="margin-bottom:15px;">
                          <input type="text" class="form-control" name = "lecture_order" placeholder="강의 순서를 입력하세요."/>
                        </div>
                      </div>

                      <!--칸 맞추를 위한 광기의 br-->
                      <br><br><br><br><br><br><br><br><br><br>

                      <div class="form-group">
                        <label class="control-label col-md-3 col-sm-3 col-xs-12" style="text-align:right;">강의 영상</label>
                        <!--파일 업로드-->
                        <div class="filebox">
                              <input type = "file" name = "upload" id = 'fileupload' style = "padding-left: 10px;">
                              &nbsp;&nbsp;&nbsp;강의 영상을 첨부해 주세요.(1GB 이하)
                        </div>
                      </div>

                      
                      <div class="tags_clear"></div>

                      <div class="form-group">
                        <div class="col-md-9 col-sm-9 col-xs-12 col-md-offset-3">
                         
                          <button type="submit" class="btn btn-success" style="float:right">저장</button>
                          <button type="button" class="btn btn-primary" onclick="study_list()" style="float:right">취소</button>
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
    <!-- <script src="vendors/jquery/dist/jquery.min.js"></script> -->
    

    <!-- Bootstrap -->
    <script src="vendors/bootstrap/dist/js/bootstrap.min.js"></script>
    <!-- FastClick -->
    <script src="vendors/fastclick/lib/fastclick.js"></script>
    <!-- iCheck -->
    <script src="vendors/iCheck/icheck.min.js"></script>

    <!-- NProgress -->
    <!-- <script src="vendors/nprogress/nprogress.js"></script> -->


    <script src="vendors/jquery.tagsinput/src/jquery.tagsinput.js"></script>

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
