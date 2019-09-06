<?php
 
 $con = mysqli_connect('127.0.0.1', 'root', '123456', 'proj_manager');
 
$result = mysqli_query($con, "SELECT * FROM USER;");
 
$response = array();//배열 선언
 
while($row = mysqli_fetch_array($result)){
 //response["userID"]=$row[0] ....이런식으로 됨.
 
array_push($response, array("userID"=>$row[0], "userPassword"=>$row[1], "userName"=>$row[2], "userAge"=>$row[3]));
 }
 //response라는 변수명으로 JSON 타입으로 $response 내용을 출력
 
echo json_encode(array("response"=>$response));
 
mysqli_close($con);
 ?>