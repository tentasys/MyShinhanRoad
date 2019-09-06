<?php
   include("db_connection.php"); 
   $L = $_POST["tag123"];

  // echo $L;
  if($L == "")    //입력된 태그가 없을 때
  {
    echo "<script>alert('태그를 입력해 주세요');history.back();</script>";
  }
  else
  {
    $temp = strtok($L, ",");

    $query = "INSERT INTO tag VALUES ('','$temp');";
    // echo $query;
    mysqli_query($con, $query);

    while ($temp = strtok(","))
    {
      $query = "INSERT INTO tag VALUES ('','$temp');";
      // echo $query;
      mysqli_query($con, $query);
    }

    echo "<script>history.back();</script>";
  }

  mysqli_close($con);

  ?>