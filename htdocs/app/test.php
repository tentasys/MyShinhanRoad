<?php
 
$con = mysqli_connect('127.0.0.1', 'root', '', 'shinple');

// array를 받을 변수
$courseNum = intval($_POST["courseNum"]);
$L = intval($_POST["userNum"]);
// Query 구현

$query = <<<_SQL
SELECT problem_txt,problem_no1,problem_no2,problem_no3,problem_no4,problem_answer
FROM problem
WHERE course_num = $courseNum
ORDER BY problem_num;
_SQL;


$result = mysqli_query($con, $query/*"SELECT * FROM course WHERE courseLevel = $L[1];"*/);


$response = array();//배열 선언
 
while($row = mysqli_fetch_array($result)){
 //response["userID"]=$row[0] ....이런식으로 됨.
 
 array_push($response, array("question"=>$row[0], "quiz1"=>$row[1], "quiz2"=>$row[2],"quiz3"=>$row[3],"quiz4" =>$row[4],"answer" =>$row[5]));
    }
 //response라는 변수명으로 JSON 타입으로 $response 내용을 출력
 
echo json_encode(array("response"=>$response));

mysqli_close($con);
 ?>