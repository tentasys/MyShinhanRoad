<?php
$connect = mysqli_connect('192.168.1.156', 'root', '', 'shinple') or die (mysql_error());
   $L = $_POST["key_1"];
    
   echo $L;
  $query =" SELECT c.mem_num, c.mem_name, l.learn_state FROM member c JOIN learn_course l ON (c.mem_num = l.mem_num) WHERE $L = l.course_num;"
  $result = $connect->query($query);
  echo $result;
  mysqli_close($connect);
/* $query ="select distinct course_num, course_title, course_level, course_tag01, course_tag02, course_tag03 from course order by course_num desc;";
    $result = $connect->query($query);
    $total = mysqli_num_rows($result); 
  ?> */
  ?>