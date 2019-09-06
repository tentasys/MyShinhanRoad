<?php
 
 $con = mysqli_connect('127.0.0.1', 'root', '', 'shinple');

 // array를 받을 변수
//$L = $_POST["level"];
//$Fil = $_POST["filter"];

// 변수가 비었을 때 NULL로 채운다 
$in_list = empty($L)?'NULL':"'".join("','", $L)."'";
$in_list2 = empty($Fil)?'NULL':"'".join("','", $Fil)."'";

// Query 구현
$query = <<<_SQL
    SELECT `tag_name`
    FROM `tag`
    ORDER BY `tag_num`;
_SQL;


$result = mysqli_query($con, $query/*"SELECT * FROM course WHERE courseLevel = $L[1];"*/);
 
$response = array();//배열 선언
 
while($row = mysqli_fetch_array($result)){
 //response["userID"]=$row[0] ....이런식으로 됨.
 
array_push($response, array("tag_name"=>$row[0]));
 }
 //response라는 변수명으로 JSON 타입으로 $response 내용을 출력s
 
echo json_encode(array("response"=>$response));
mysqli_close($con);
 ?>