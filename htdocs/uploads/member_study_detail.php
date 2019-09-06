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
    <style>
    #select_box{
      background-color: rgba(74, 74, 204, 0);
      border: none;
    }

    </style>
    <script src="./build/js/jquery.js"></script>
    <script>
      function study_detail_selectbox(e, coursenum){
        var state = e.value;
        $.ajax({
            url: './learn_course.php',
            type: 'post',
            dataType: 'json',
            data: {"course_num": coursenum, "state": state},
            success: function(data){
                var i = 0;
                var res = "";

                while(data[i]){

                    //짝수행과 홀수행의 속성 지정
                    if(i%2 == 0)
                    {
                        res += '<tr class="even pointer" style="text-align: center;"></tr>';
                    }                    
                    else
                    {
                        res += '<tr class="odd pointer" style="text-align: center;"></tr>';
                    }
                    res += '<td class="center" style="text-align: center;">';
                    res += '<input type="checkbox" class="chk" name="row'+(i+1)+'" value="'+data[i]['mem_token']+'">';
                    res += '</td>';
                    res += '<td class=" " style="text-align: center;">'+data[i]['com_name']+'</td>';
                    res += '<td class=" " style="text-align: center;">'+data[i]['mem_num']+'</td>';
                    res += '<td class=" " style="text-align: center;">'+data[i]['mem_name']+'</td>';
                    res += '<td class=" " style="text-align: center;">';
                    if(data[i]['mem_learn_state'] == 3)
                    {
                        res += "수료 완료";
                    }
                    else
                    {
                        res += "미수료";
                    }
                    res += "</td></tr>";

                    i++;
                }

                $("#learn_course_table").html(res);
            },
            error: function(){
                var res = "";
                res += '<tr style="text-align: center;"><td colspan=5>';
                res += "해당 데이터가 존재하지 않습니다.";
                res += '</td></tr>';
                $("#learn_course_table").html(res);
            }
        });
    }
  </script>
  </head>

    <?php
        $connect = mysqli_connect('192.168.1.156', 'root', '', 'shinple') or die (mysql_error()); 
        $L = $_POST['key_1'];

        $query = <<<_SQL
        SELECT company.company_name com_name, joined.num mem_num, joined.name mem_name, mem_token, joined.learn_state mem_learn_state from 
        (SELECT c.company_num com_num, c.mem_num num, c.mem_name name, mem_token, l.learn_state learn_state FROM member c JOIN learn_course l ON (c.mem_num = l.mem_num) WHERE l.course_num = $L) joined
        JOIN company company ON (company.company_num = joined.com_num);
        _SQL;

        $result = $connect->query($query);

        $query_1 = <<<_SQL
        SELECT c.course_title,c.course_num,  count(*) AS total,l.learn_state
        FROM course c JOIN learn_course l ON (c.course_num = l.course_num)
        WHERE l.course_num=$L GROUP BY course_title;
        _SQL;

        $result_1 = $connect->query($query_1);

        // $total = mysqli_num_rows($result); 
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
                <h3>강좌별 회원 관리<small>&nbsp;&nbsp;신한DS 직원들을 위한 LMS, Shinple</small></h3>
              </div>

              
            </div>

            <div class="clearfix"></div>

            <div class="row">
            
               
              <div class="col-md-12 col-sm-12 col-xs-12">
                <div class="x_panel">
                  <div class="x_title">
                    <h2><span style="color: rgb(115,135,156)"><?php 
                                     $rows = mysqli_fetch_assoc($result_1);
                                     echo $rows['course_title'] ?></span></h2>
                    <div class="clearfix"></div>
                  </div>

                  <div class="x_content">

                    
                    <div class="table-responsive">
                    
                      <table class="table table-striped jambo_table bulk_action" style="width: 100%;">
                        <thead>
                          <tr class="headings">
                            <!-- <th>
                              <input type="checkbox" id="check-all" class="flat">
                            </th> -->
                            <th style="text-align:center;">
                                <input type="checkbox"name="checkAll" id="th_checkAll" onclick="checkAll();">
                              </th>
                            <th class="column-title" style="text-align: center; width: 150px;">회사명</th>
                            <th class="column-title" style="text-align: center;">사번</th>
                            <th class="column-title" style="text-align: center;">이름</th>
                            <th class="column-title" style="text-align: center;">
                              <select id="select_box" onchange="study_detail_selectbox(this, <?php echo $L?>);">
                                <option value = "total">전체</option>
                                <option value = "complete" >수료</option>
                                <option value = "not_complete">미수료</option>
                              </select>
                            </th>
                            <th class="bulk-actions" colspan="7">
                              <a class="antoo" style="color:#fff; font-weight:500;">Bulk Actions ( <span class="action-cnt"> </span> ) <i class="fa fa-chevron-down"></i></a>
                            </th>
                          </tr>
                        </thead>
                  <form action="notify_learn.php" method="POST">

                  <!--///////////////////////////////////////////////////////////////////////-->
                        <tbody id = "learn_course_table">
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

                                    <td class="center">
                                        <input type="checkbox" class="chk" name= "<?php echo "row".$num ?>" value="<?php echo $rows['mem_token'] ?>"> 
                                    </td>
                                    <td class=" "><?php echo $rows['com_name'] ?></td>
                                    <td class=" "><?php echo $rows['mem_num'] ?></td>
                                    <td class=" "><?php echo $rows['mem_name'] ?></td>
                                    <td class=" "><?php if($rows['mem_learn_state'] == 3) {
                                        ?>
                                            수료 완료
                                        <?php 
                                            }else{
                                        ?>
                                                미수료
                                    <?php 
                                        }?>
                                    </td>
                                </tr>
                            <?php $num++;} ?>
                        </tbody>
                      </table>

                      <!--///////////////////////////////////////////////////////////////////////-->

                      <input type="hidden" name="row_num" value= <?php echo "$num"?> />
                      <input type="hidden" name="msg" value="강좌 수료가 되지 않았습니다. 어서 테스트를 응시하세요."/>
                      <input type="submit" class="btn btn-round btn-success" style="float: right;" value="알림 전송"/> 

                  </form>
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
     <!-- checkbox -->
     <script src="build/js/checknew.js"></script>
  </body>
</html>
