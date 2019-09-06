<?php
 
$con = mysqli_connect('127.0.0.1', 'root', '', 'shinple');

// array를 받을 변수
$L = intval($_POST["courseNUM"]);
$score = intval($_POST["score"]);
$pass = intval($_POST["pass"]);
$userNum = intval($_POST["userNum"]);
$state = intval($_POST["state"]);
// Query 구현

$query = <<<_SQL
SELECT problem_num,problem_txt,problem_no1,problem_no2,problem_no3,problem_no4,problem_answer
FROM problem
WHERE course_num = $L;
_SQL;


$result = mysqli_query($con, $query/*"SELECT * FROM course WHERE courseLevel = $L[1];"*/);


$response = array();//배열 선언
 
while($row = mysqli_fetch_array($result)){
 //response["userID"]=$row[0] ....이런식으로 됨.
 
 array_push($response, array("quiz_num"=>$row[0], "question"=>$row[1], "quiz1"=>$row[2],"quiz2"=>$row[3],"quiz3" =>$row[4],"quiz4" =>$row[5],"answer" =>$row[6]));
    }
 //response라는 변수명으로 JSON 타입으로 $response 내용을 출력

 echo json_encode(array("response"=>$response));
 
 
 $query1 = <<<_SQL
 UPDATE learn_course SET learn_pass = $pass , learn_score = $score , learn_state = $state WHERE course_num = $L AND mem_num= $userNum;
 _SQL;

 $rssult1 = mysqli_query($con,$query1);


 $query2 = <<<_SQL
 UPDATE member SET mem_point = mem_point +$score Where mem_num= $userNum;
 _SQL;

 $rssult2 = mysqli_query($con,$query2);
 


mysqli_close($con);
 ?>