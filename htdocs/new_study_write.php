<?php
  $con = mysqli_connect('192.168.1.134', 'root', '', 'shinple');

  //Course 파트
  //변수 대입
  $Course_title = $_POST["course_title"];
  $Course_teacher = $_POST["course_teacher"];
  $Course_level = $_POST["iCheck"]; // 라디오 받아오기
  $Course_text = $_POST["course_text"];
  $Course_date = date("Y-m-d H:i:s"); // Date 함수로 받아오기
  //$Course_Picture = $_Post["..."];  // 사진 받아오기
  $Course_tag1 = $_POST["course_tag1"]; // Tag1
  $Course_tag2 = $_POST["course_tag2"]; // Tag2
  $Course_tag3 = $_POST["course_tag3"]; // Tag3
  //$Course_like = $_POST["course_like"]; // 변수 선언은 하였지만, 필요 없는 기능

  // Tag에 0이면 NULL값을 넣는다.
  //$Course_tag1 = empty($Course_tag1)?'NULL':"'".join("','", $Course_tag1)."'";
  //$Course_tag2 = empty($Course_tag2)?'NULL':"'".join("','", $Course_tag2)."'";
  //$Course_tag3 = empty($Course_tag3)?'NULL':"'".join("','", $Course_tag3)."'";

  //삽입값 확인
  echo $Course_title, "<br>";
  echo $Course_teacher, "<br>";
  echo $Course_text, "<br>";
  echo $Course_level, "<br>";
  echo $Course_date, "<br>";

  //DB 삽입
  $query = "INSERT INTO `course`(`course_title`, `course_tch`, `course_level`, `course_text`, `course_date`) VALUES ('$Course_title', '$Course_level', '$Course_text', '$Course_date')";
  $result = mysqli_query($con, $query);

  //결과값 확인
  if ($result) {
    echo "OOO<br>";
  } else {
    echo "XXX<br>";
  } echo "--------------------------------------------------<br>";

  //Lecture 1 파트
  //Course_num 가져오기
  $course_num = "SELET course_num FROM course WHERE course_title = $Course_title";
  //변수 대입
  $Lec_title = $_POST["lec_title"];
  //$Lec_text = $_POST[""]; 웹에 없다. Ssul 부분
  $Lec_course = $course_num;
  $Lec_path = $_POST["upload_file"];
  //$Lec_Time = $_POST[""]; 웹에 없다. 시간 부분... 얘도 그냥 긇어오면 될거 같긴한대

  //DB 삽입
  $query = "INSERT INTO `lecture` (`lec_title`, `lec_text`, `lec_path`, `lec_course`) VALUES ($Lec_title', 'Ssul', '$Lec_path', '$Lec_course')";
  $result = mysqli_query($con, $query);

  //결과값 확인
  if ($result) {
    echo "OOO<br>";
  } else {
    echo "XXX<br>";
  } echo "--------------------------------------------------<br>";

  //Lecture 2 파트
  //변수 대입
  $Lec_title = $_POST["lec_title"];
  //$Lec_text = $_POST[""]; 웹에 없다. Ssul 부분
  $Lec_course = $course_num;
  $Lec_path = $_POST["upload_file"];
  //$Lec_Time = $_POST[""]; 웹에 없다. 시간 부분... 얘도 그냥 긇어오면 될거 같긴한대

  //DB 삽입
  $query = "INSERT INTO `lecture` (`lec_title`, `lec_text`, `lec_path`, `lec_course`) VALUES ($Lec_title', 'Ssul', '$Lec_path', '$Lec_course')";
  $result = mysqli_query($con, $query);

  //결과값 확인
  if ($result) {
    echo "OOO<br>";
  } else {
    echo "XXX<br>";
  } echo "--------------------------------------------------<br>";

  //Lecture 3 파트
  //변수 대입
  $Lec_title = $_POST["lec_title"];
  //$Lec_text = $_POST[""]; 웹에 없다. Ssul 부분
  $Lec_course = $course_num;
  $Lec_path = $_POST["upload_file"];
  //$Lec_Time = $_POST[""]; 웹에 없다. 시간 부분... 얘도 그냥 긇어오면 될거 같긴한대

  //DB 삽입
  $query = "INSERT INTO `lecture` (`lec_title`, `lec_text`, `lec_path`, `lec_course`) VALUES ($Lec_title', 'Ssul', '$Lec_path', '$Lec_course')";
  $result = mysqli_query($con, $query);

  //결과값 확인
  if ($result) {
    echo "OOO<br>";
  } else {
    echo "XXX<br>";
  } echo "--------------------------------------------------<br>";

  mysqli_close($con);

  ?>
