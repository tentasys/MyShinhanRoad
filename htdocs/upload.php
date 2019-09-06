<?php
   function GetUniqFileName($FN, $PN)
   {
    $FileExt = substr(strrchr($FN, "."), 1); // 확장자 추출
    $FileName = substr($FN, 0, strlen($FN) - strlen($FileExt) - 1); // 파일명 추출

    $ret = "$FileName.$FileExt";

    while(file_exists($PN.$ret)) // 파일명이 중복되지 않을때 까지 반복
    {
      $FileCnt++;
      echo $FileCnt;
      $ret = $FileName."_".$FileCnt.".".$FileExt; // 파일명뒤에 (_1 ~ n)의 값을 붙여서....
    }

    return($ret); // 중복되지 않는 파일명 리턴
  }

  function alert($msg)
  {
     echo "<script> alert('$msg'); location.replace('study_write_html.php'); </script>";
  }

  $allowed_ext = array('jpg','jpeg','png','gif');
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
  
     $FileName = GetUniqFileName($name, $upload_dir);
     $file_success = move_uploaded_file($tmp_file, $upload_dir.$FileName);
  }
?>