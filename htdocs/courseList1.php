<?php
 
 $con = mysqli_connect('127.0.0.1', 'root', '', 'shinple');

 // array를 받을 변수
//$L = $_POST["level"];
//$Fil = $_POST["filter"];

// 변수가 비었을 때 NULL로 채운다 
//$in_list = empty($L)?'NULL':"'".join("','", $L)."'";
//$in_list2 = empty($Fil)?'NULL':"'".join("','", $Fil)."'";

// Query 구현
$sql ="SELECT course_title,course_level,course_tch,course_text
FROM `course` ORDER BY course_date 
DESC LIMIT 2";



$result = mysqli_query($con, $sql/*"SELECT * FROM course WHERE courseLevel = $L[1];"*/);
/*if (!$result) {
    die('Invalid query: ' . mysql_error());
}

$row = mysqli_fetch_array($result);
echo '<h1>'.$row['course_title'].'<h1>';
echo $row['course_level'];*/

$response = array();//배열 선언
 
while($row = mysqli_fetch_array($result)){
 //response["userID"]=$row[0] ....이런식으로 됨.
 
 array_push($response, array("course_title"=>$row[0], "course_level"=>$row[1], "course_tch"=>$row[2],"course_text"=>$row[3]));}
 //response라는 변수명으로 JSON 타입으로 $response 내용을 출력
 
echo json_encode(array("response"=>$response));

mysqli_close($con);
 ?>