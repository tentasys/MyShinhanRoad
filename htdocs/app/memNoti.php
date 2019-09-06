<?php
$con = mysqli_connect('127.0.0.1', 'root', '', 'shinple');

$userNum = $_POST["mem_num"];
//
$query = <<<_SQL
UPDATE member SET mem_noti = 0 WHERE mem_num = $userNum;
_SQL;

$result = mysqli_query($con, $query/*"SELECT * FROM course WHERE courseLevel = $L[1];"*/);
   
mysqli_close($con);
 ?>