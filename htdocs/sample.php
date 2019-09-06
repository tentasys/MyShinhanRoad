<?php
 
 $con = mysqli_connect('127.0.0.1', 'root', '', 'shinple');
 $date = date("Y-m-d");
 echo $date;
   $query = <<<_SQL
   INSERT INTO  learn_course(`mem_num`,`course_num`,`learn_state`,`learn_date`) values (1370000302,5,1,'$date');
   _SQL;
   $result1 = mysqli_query($con, $query/*"SELECT * FROM course WHERE courseLevel = $L[1];"*/);
   

mysqli_close($con);
 ?>