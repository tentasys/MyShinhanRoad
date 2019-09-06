<?php
 
 $con = mysqli_connect('127.0.0.1', 'root', '', 'shinple');

// array를 받을 변수
$userNum = $_POST["userNum"];
$L = $_POST["courseNum"];
$state = $_POST["state"];


// Query 구현

$query = <<<_SQL
INSERT INTO  learn_course values ($userNum,$L,'','','','',$state)
_SQL;

$query2 = <<<_SQL
SELECT lec_title ,lec_order, lec_text, lec_time
FROM `lecture`
WHERE `lec_course` = $L
ORDER BY lec_order;
_SQL;

$result = mysqli_query($con, $query/*"SELECT * FROM course WHERE courseLevel = $L[1];"*/);
$result2 = mysqli_query($con, $query2/*"SELECT * FROM course WHERE courseLevel = $L[1];"*/);

$response = array();//배열 선언
 
while($row = mysqli_fetch_array($result2)){
 //response["userID"]=$row[0] ....이런식으로 됨.
 
    array_push($response, array("lec_title"=>$row[0],"lec_order"=>$row[1],"lec_text"=>$row[2],"lec_time"=>$row[3]));
    }
 //response라는 변수명으로 JSON 타입으로 $response 내용을 출력
 

echo json_encode(array("response"=>$response));


mysqli_close($con);
 ?>