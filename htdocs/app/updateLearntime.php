<?php
 
$con = mysqli_connect('127.0.0.1', 'root', '', 'shinple');

// array를 받을 변수
$courseNum = intval($_POST["courseNum"]);
$L = intval($_POST["userNum"]);
$time = $_POST["time"];
$lec_num =$_POST["lec_num"];
// Query 구현
 
 $query2 = <<<_SQL
 UPDATE learn_lec SET learn_time = '$time' WHERE mem_num=$L AND course_num = $courseNum AND lec_num =$lec_num;
 _SQL;

 $result1 = mysqli_query($con, $query2);

 
$query = <<<_SQL
INSERT INTO learn_lec(`mem_num`,`lec_num`,`learn_time`,`course_num`) values ($L,$lec_num,'$time',$courseNum);
_SQL;

$result = mysqli_query($con, $query/*"SELECT * FROM course WHERE courseLevel = $L[1];"*/);


 echo $result;

mysqli_close($con);
 ?>