<?php

  $con = mysqli_connect('192.168.1.156', 'root', '', 'shinple');

  //연결 상태 확인
  if (mysqli_connect_errno())
  {
    echo "DB와 연결에 실패하였습니다. : ". mysqli_connect_error();
  }

  //Course 파트
  //변수 대입
  $Course_title = $_POST["course_title"]; //강좌 제목
  $Course_teacher = $_POST["course_teacher"]; //강사명
  $Course_level = $_POST["iCheck"]; //강좌 래벨 - 라디오 받아오기
  $Course_text = $_POST["course_text"]; //강좌 설명
  $Course_point = $_POST["course_point"]; //강좌 점수
  $Course_date = date("Y-m-d H:i:s"); // 등록 일시 - Date 함수로 받아오기

  $Course_tag1 = $_POST["course_tag"][0]; // 강좌 테그 01
  $Course_tag2 = $_POST["course_tag"][1]; // 강좌 테그 02
  $Course_tag3 = $_POST["course_tag"][2]; // 강좌 테그 03


  // Tag에 0이면 NULL값을 넣는다.
  if ($Course_tag1 == '')
  {
    $Course_tag1 = NULL;
  }
  if ($Course_tag2 == '')
  {
    $Course_tag2 = NULL;
  }
  if ($Course_tag3 == '')
  {
    $Course_tag3 = NULL;
  }


  if ($Course_level == '1')
  {
    $Course_point = '10';
  }
  else if ($Course_level == '2')
  {
    $Course_point = '12';
  }
  else if ($Course_level == '3')
  {
    $Course_point = '14';
  }
  else if ($Course_level == '4')
  {
    $Course_point = '16';
  }
  else if ($Course_level == '5')
  {
    $Course_point = '18';
  }
  else if ($Course_level == '6')
  {
    $Course_point = '20';
  }
  else if ($Course_level == '7')
  {
    $Course_point = '22';
  }

  //삽입값 확인
  echo "강좌명 : ", $Course_title, "<br>";
  echo "강사명 : ", $Course_teacher, "<br>";
  echo "강좌 설명 : ", $Course_text, "<br>";
  echo "강좌 래벨 : ", $Course_level, "<br>";
  echo "강좌 점수 : ", $Course_point, "<br>사실 점수 부분을 넣었지만 쓰지 않는다 ㅠㅠㅠㅠ<br>";
  echo "등록 일시 : ", $Course_date, "<br>";
  echo "테그 1 : ", $Course_tag1, "<br>";
  echo "테그 2 : ", $Course_tag2, "<br>";
  echo "테그 3 : ", $Course_tag3, "<br>";
  //echo "필수 강좌 여부 : ", $Course_necessary, "<br>";


  /*

  //Lecture 1 파트
  //Course_num 가져오기
  //$course_num = "SELET course_num FROM course WHERE course_title = $Course_title";
  //변수 대입
  $First_Lec_title = $_POST["first_lec_title"];
  //$Lec_text = $_POST[""]; 웹에 없다. Ssul 부분
  //$Lec_course = $course_num;
  //$Lec_path = $_POST["upload_file"];
  //$Lec_Time = $_POST[""]; 웹에 없다. 시간 부분... 얘도 그냥 긇어오면 될거 같긴한대

  echo "강의 1 제목 : ", $First_Lec_title, "<br>";

  //DB 삽입
  //$sql .= "INSERT INTO `lecture`(`lec_title`) VALUES ('$Lec_title1')";

  //Lecture 2 파트
  //변수 대입
  $Second_Lec_title = $_POST["second_lec_title"];
  //$Lec_text = $_POST[""]; 웹에 없다. Ssul 부분
  //$Lec_course = $course_num;
  //$Lec_path = $_POST["upload_file"];
  //$Lec_Time = $_POST[""]; 웹에 없다. 시간 부분... 얘도 그냥 긇어오면 될거 같긴한대

  echo "강의 2 제목 : ", $Second_Lec_title, "<br>";

  //DB 삽입

  //$sql .= "INSERT INTO `lecture` (`lec_title`) VALUES ('$Lec_title2')";

  //Lecture 3 파트
  //변수 대입
  $Third_Lec_title = $_POST["third_lec_title"];
  //$Lec_text = $_POST[""]; 웹에 없다. Ssul 부분
  //$Lec_course = $course_num;
  //$Lec_path = $_POST["upload_file"];
  //$Lec_Time = $_POST[""]; 웹에 없다. 시간 부분... 얘도 그냥 긇어오면 될거 같긴한대

  echo "강의 3 제목 : ", $Third_Lec_title, "<br>";


*/

  $sql = "INSERT INTO `course`(`course_title`, `course_tch`, `course_level`, `course_text`, `course_point`, `course_date`, `course_tag01`, `course_tag02`, `course_tag03`)
   VALUES ('$Course_title', '$Course_teacher', '$Course_level', '$Course_text', '$Course_point', '$Course_date', '$Course_tag1', '$Course_tag2', '$Course_tag3')";
  
  mysqli_query($con, $sql);
  mysqli_close($con);

  echo("<script>location.href='../study_write.php';</script>");
    //$query = "INSERT INTO `lecture` (`lec_title`, `lec_text`, `lec_path`, `lec_course`) VALUES ($Lec_title', 'Ssul', '$Lec_path', '$Lec_course')";
  ?>