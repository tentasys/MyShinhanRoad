<?php
 
 $con = mysqli_connect('127.0.0.1', 'root', '', 'shinple');

// array를 받을 변수
$L = $_POST["tag123"];

echo $L;

$query = "INSERT INTO `tag` VALUES ('','$L')";

$result = mysqli_query($con, $query);

mysqli_close($con);
?>