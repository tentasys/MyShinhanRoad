<?php
 
$con = mysqli_connect('127.0.0.1', 'root', '', 'shinple');

// array를 받을 변수
$L = intval($_POST["userNum"]);
$courseNum = intval($_POST["courseNum"]);
$lectureNum = intval($_POST["lectureNum"]);
$date = $_POST["date"];

// Query 구현

$query = <<<_SQL
UPDATE learn_lec SET recent_learn = '$date' WHERE mem_num=$L AND course_num = $courseNum AND lec_num =$lectureNum;
_SQL;


$result = mysqli_query($con, $query/*"SELECT * FROM course WHERE courseLevel = $L[1];"*/);


mysqli_close($con);
 ?>