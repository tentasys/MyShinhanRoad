<?php
 
 $con = mysqli_connect('127.0.0.1', 'root', '', 'shinple');


//최근 등록된 강좌 2개

$query = <<<_SQL
SELECT course_num
FROM course
ORDER BY course_date DESC
LIMIT 2;
_SQL;


$result = mysqli_query($con, $query/*"SELECT * FROM course WHERE courseLevel = $L[1];"*/);


$response = array();//배열 선언
 
while($row = mysqli_fetch_array($result)){
 //response["userID"]=$row[0] ....이런식으로 됨.
 
        array_push($response, array("course_num"=>$row[0]));
    }
 //response라는 변수명으로 JSON 타입으로 $response 내용을 출력S
 

echo json_encode(array("response"=>$response));

mysqli_close($con);
 ?>