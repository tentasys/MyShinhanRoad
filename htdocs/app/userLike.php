<?php
 
 $con = mysqli_connect('127.0.0.1', 'root', '', 'shinple');

// array를 받을 변수
$userNum = intval($_POST["userNum"]);
$L = intval($_POST["courseNum"]);
$Like = intval($_POST["Like"]);
echo $Like;
// Query 구현

$query = <<<_SQL
UPDATE learn_course SET course_like = $Like WHERE mem_num = $userNum AND course_num = $L; 
_SQL;

$result = mysqli_query($con, $query/*"SELECT * FROM course WHERE courseLevel = $L[1];"*/);

mysqli_close($con);
 ?>