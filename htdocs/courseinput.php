<?php
 
 $con = mysqli_connect('127.0.0.1', 'root', '', 'shinple');

// array를 받을 변수
$coursetitle = $_POST["coursetitle"];
$coursetext = $_POST["coursetext"];
$courselevel = $_POST["courselevel"]


$query = "INSERT INTO `course`('course_num','course_title','course_text','courselevel') VALUES ('','$coursetitle','$coursetext','$courselevel')";

$result = mysqli_query($con, $query);

mysqli_close($con);

?>