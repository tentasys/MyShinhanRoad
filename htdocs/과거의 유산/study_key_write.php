<?php
   include("db_connection.php"); 
  // array를 받을 변수
  $L = $_POST["tag123"];
  $temp = $_POST["tag123"];


  echo $L;

  $result = mysqli_query($con, $query);
  
  mysqli_close($con);

  

  echo ("<script language=javascript> history.back(); </script>");

  ?>


