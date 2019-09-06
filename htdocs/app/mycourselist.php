<?php
 
$con = mysqli_connect('127.0.0.1', 'root', '', 'shinple');

// array를 받을 변수
$L = $_POST["userNum"];

// Query 구현

$query = <<<_SQL
SELECT c.course_title, c.course_level, c.course_tch, c.course_text,  c.course_num , COALESCE(lc.learn_state,0), c.course_like,  lc.course_like
FROM member m JOIN learn_course lc ON(m.mem_num=lc.mem_num) JOIN course c ON(c.course_num=lc.course_num)
WHERE m.mem_num = $L AND c.course_nec = 0;
_SQL;


$result = mysqli_query($con, $query/*"SELECT * FROM course WHERE courseLevel = $L[1];"*/);


$response = array();//배열 선언
 
while($row = mysqli_fetch_array($result)){
 //response["userID"]=$row[0] ....이런식으로 됨.
 
    array_push($response, array("course_title"=>$row[0], "course_level"=>$row[1], "course_tch"=>$row[2],"course_text"=>$row[3],"course_num" =>$row[4],"learn_state"=>$row[5],"Like"=>$row[6],"mem_like"=>$row[7]));
    }
 //response라는 변수명으로 JSON 타입으로 $response 내용을 출력
 

echo json_encode(array("response"=>$response));

mysqli_close($con);
 ?>