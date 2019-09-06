<?php
 
$con = mysqli_connect('127.0.0.1', 'root', '', 'shinple');

// array를 받을 변수
$courseNum = intval($_POST["courseNum"]);
$L = intval($_POST["userNum"]);
$lectureNum = intval($_POST["lecNum"]);
$date = $_POST["date"];

// Query 구현
 
 $query2 = <<<_SQL
 UPDATE learn_lec SET recent_learn = '$date' WHERE mem_num=$L AND course_num = $courseNum AND lec_num =$lectureNum;
 _SQL;

 $result1 = mysqli_query($con, $query2/*"SELECT * FROM course WHERE courseLevel = $L[1];"*/);

 if ($result1){
     
    $query = <<<_SQL
    INSERT INTO learn_lec(`mem_num`,`lec_num`,`recent_learn`,`course_num`) values ($L,$lectureNum,$date,$courseNum);
    _SQL;

    $result = mysqli_query($con, $query/*"SELECT * FROM course WHERE courseLevel = $L[1];"*/);
 }

mysqli_close($con);
 ?>