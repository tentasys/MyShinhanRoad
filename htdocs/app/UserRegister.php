<?php

   $con = mysqli_connect('127.0.0.1', 'root', '', 'Shinple');
   if (!$con)
  {
     echo "MySQL 접속 에러 : ";
     echo mysqli_connect_error();
     exit();
  }

   //post방식으로 데이터를 받는다.
    $mem_num = $_POST["mem_num"];
    $mem_password = $_POST["mem_password"];
    $mem_point = 0;
    $mem_name = $_POST["mem_name"];
    $mem_code = $_POST["mem_code"];

    $query = <<<_SQL
      INSERT INTO member(`mem_num`, `mem_point`, `mem_password`,`mem_name`,`company_num`) VALUES($mem_num, $mem_point ,'$mem_password','$mem_name', $mem_code)
    _SQL;
   
$result = mysqli_query($con, $query/*"SELECT * FROM course WHERE courseLevel = $L[1];"*/);

if($result){  //결과에 따라서 메세지 에코
  echo json_encode(array("response"=>"success"));  
}  
else{  
  echo '0';  
}  

mysqli_close($con);
   
  ?>