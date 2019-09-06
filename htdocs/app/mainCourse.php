<?php
 
$con = mysqli_connect('127.0.0.1', 'root', '', 'shinple');

// array를 받을 변수
$N = $_POST["userNum"];

// Query 구현
//최신순 3개
$query = <<<_SQL
SELECT c.course_title, c.course_level, c.course_tch, c.course_text,  c.course_num , COALESCE(l.learn_state,0), c.course_like,  l.course_like
FROM course c LEFT JOIN learn_course l ON (c.course_num = l.course_num) AND l.mem_num = $N
WHERE c.course_nec = 0
ORDER BY course_date DESC
LIMIT 3
_SQL;
//좋아요 순 3개 
$query2 = "
SELECT c.course_title, c.course_level, c.course_tch, c.course_text,  c.course_num , COALESCE(l.learn_state,0),c.course_like, l.course_like
FROM course c LEFT JOIN learn_course l ON (c.course_num = l.course_num) AND l.mem_num = $N
WHERE c.course_nec = 0
ORDER BY c.course_like DESC
LIMIT 3";

$result = mysqli_query($con, $query/*"SELECT * FROM course WHERE courseLevel = $L[1];"*/);

$result2 = mysqli_query($con, $query2/*"SELECT * FROM course WHERE courseLevel = $L[1];"*/);


$response = array();//배열 선언
 
$response2 = array();//배열 선언

while($row = mysqli_fetch_array($result)){
 //response["userID"]=$row[0] ....이런식으로 됨.
 
    array_push($response, array("course_title"=>$row[0], "course_level"=>$row[1], "course_tch"=>$row[2],"course_text"=>$row[3],"course_num" =>$row[4],"learn_state"=>$row[5],"Like"=>$row[6],"mem_like"=>$row[7]));
    }
 //response라는 변수명으로 JSON 타입으로 $response 내용을 출력
 
while($row2 = mysqli_fetch_array($result2)){
   //response["userID"]=$row[0] ....이런식으로 됨.
   
      array_push($response2, array("course_title"=>$row2[0], "course_level"=>$row2[1], "course_tch"=>$row2[2],"course_text"=>$row2[3],"course_num" =>$row2[4],"learn_state"=>$row[5],"Like"=>$row[6],"mem_like"=>$row[7]));
      }
   //response라는 변수명으로 JSON 타입으로 $response 내용을 출력

echo json_encode(array("response"=>$response, "response2"=>$response2 ));

mysqli_close($con);
 ?>