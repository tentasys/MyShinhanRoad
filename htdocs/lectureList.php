<?php
 
 $con = mysqli_connect('127.0.0.1', 'root', '123456', 'proj_manager');

 //$lectureNUM =intval($_POST["lectureNUM"]);

 
$result = mysqli_query($con, "SELECT * FROM lecture ORDER BY lectureNUM");
                                                        //WHERE lectureNUM = $lectureNUM;
$response = array();//배열 선언
 
while($row = mysqli_fetch_array($result)){
 //response["userID"]=$row[0] ....이런식으로 됨.
 
array_push($response, array("lectureNum"=>$row[0], "lectureName"=>$row[1], "lectureTag"=>$row[2]));
 }
 //response라는 변수명으로 JSON 타입으로 $response 내용을 출력
 
echo json_encode(array("response"=>$response));
 
mysqli_close($con);
 ?>