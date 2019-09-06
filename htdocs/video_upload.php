<?php

  // 경로 설정
  $con = mysqli_connect('192.168.1.156', 'root', '', 'shinple');

  function GetUniqFileName($FN, $PN)
  {
    $FileExt = substr(strrchr($FN, "."), 1); // 확장자 추출
    $FileName = substr($FN, 0, strlen($FN) - strlen($FileExt) - 1); // 파일명 추출

    
    while(file_exists($PN.$ret)) // 파일명이 중복되지 않을때 까지 반복
    {
      $FileCnt++;
      $ret = "$FileName" + $FileCnt.".".$FileExt; // Lecture num의 숫자가 겹치지 않도록
    }

    return($ret); // 중복되지 않는 파일명 리턴
  }

  function alert($msg)
  {
     echo "<script> alert('$msg'); location.replace('study_write_html.php'); </script>";
  }

  $allowed_ext = array('mp4','avi','wmv','mov');
  $upload_dir = "./video/course/";

  $name = $_FILES['upload']['name'];

  //$ext == isset($_POST['$ext = array_pop(explode('.', $name))']) ? $_POST['$ext = array_pop(explode('.', $name))'] : '';

  $exts = explode('.', $name);
  $ext = array_pop($exts);

  if(preg_match('/php|jsp|asp/i',$ext))
  {
     alert("해킹하지마세요!");
  } 
  else 
  {
    $tmp_file = $_FILES['upload']['tmp_name'];

    // 해당 PHP 파일의 쿼리는 Lecture 테이블에서 lec_num의 최대값을 받아오는 쿼리입니다.
    $query = "SELECT MAX(lec_num) FROM lecture";
    $getname = mysqli_query($con, $query);

    $rows = mysqli_fetch_assoc($getname);

    //$FileName = GetUniqFileName($name, $upload_dir);  //그냥 안 없앤거

    $FileExt = substr(strrchr($name, "."), 1); // 확장자 추출
    $Out = substr($name, 0, strlen($name) - strlen($FileExt) - 1); // 파일명 추출
    $realName = (int)$rows['MAX(lec_num)'];
    $realName = $realName + 1;

    echo $realName, "<br>";

    $FileName = $realName.".".$FileExt;

    $file_success = move_uploaded_file($tmp_file, $upload_dir.$FileName);

    $Lecture_path = "video/course/$FileName"; // 강의 경로

    $Lecture_title = $_POST["lecture_title"]; //강의 제목
    $Lecture_text = $_POST["lecture_text"]; //강의 설명
    $Lecture_order = $_POST["lecture_order"]; // 강의 순서
    $Lecture_belong = $_POST["lecture_course"];

    mysqli_close($con);
  }
  echo("<script>location.href='../study_write.php';</script>");
?>