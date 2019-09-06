<?php
 
 $con = mysqli_connect('127.0.0.1', 'root', '', 'shinple');
 
 $cop_num = $_POST["cop_num"]=10;

// Query 구현
$sql ="SELECT cop_name,cop_intro,cop_ranking
FROM `copinfo`  
WHERE cop_num = $cop_num";




$result = mysqli_query($con, $sql);


$response = array();//배열 선언
 
while($row = mysqli_fetch_array($result)){
 //response["userID"]=$row[0] ....이런식으로 됨.
 
 array_push($response, array("cop_name"=>$row[0], "course_intro"=>$row[1], "cop_ranking"=>$row[2]));}
 //response라는 변수명으로 JSON 타입으로 $response 내용을 출력
 
echo json_encode(array("response"=>$response));

mysqli_close($con);
 ?>