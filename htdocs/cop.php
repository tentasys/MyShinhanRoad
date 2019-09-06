<?php
 
 $con = mysqli_connect('192.168.1.134', 'root', '', 'shinple') or die (mysql_error()); 
 
$result = mysqli_query($con, "SELECT * FROM cop;");
 
$response = array();//배열 선언
 
while($row = mysqli_fetch_array($result)){
 //response["userID"]=$row[0] ....이런식으로 됨.
 
array_push($response, array("courseName"=>$row[0], "courseLevel"=>$row[1], "tagName"=>$row[2]));
 }
 //response라는 변수명으로 JSON 타입으로 $response 내용을 출력
 
echo json_encode(array("response"=>$response));
 
mysqli_close($con);
 ?>