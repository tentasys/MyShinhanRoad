<?php
 
 $con = mysqli_connect('127.0.0.1', 'root', '', 'shinple');

//array를 받을 변수
$userNum = intval($_POST["userNum"]);
$L = intval($_POST["courseNum"]);
$state = intval($_POST["state"]); //1
$date = date("Y-m-d");

// Query 구현
if($state == 0){
   $query = <<<_SQL
   INSERT INTO  learn_course(`mem_num`,`course_num`,`learn_state`,`learn_date`) values ($userNum,$L,1,'$date');
   _SQL;
   $result1 = mysqli_query($con, $query/*"SELECT * FROM course WHERE courseLevel = $L[1];"*/);
   
   $query2 = <<<_SQL
   INSERT INTO  learn_lec(`mem_num`,`lec_num`,`recent_learn`,`course_num`) values ($userNum,1,'$date',$L);
   _SQL;
   $result3 = mysqli_query($con, $query2/*"SELECT * FROM course WHERE courseLevel = $L[1];"*/);
}

$query2 = <<<_SQL
SELECT l.lec_title ,l.lec_order, l.lec_text, ll.learn_time ,ll.recent_learn ,l.lec_num , ll.course_num,l.lec_time
FROM `lecture` l LEFT JOIN learn_lec ll ON( l.lec_num = ll.lec_num) AND ll.mem_num = $userNum
WHERE l.lec_course = $L
ORDER BY lec_order
_SQL;

$result2 = mysqli_query($con, $query2/*"SELECT * FROM course WHERE courseLevel = $L[1];"*/);
$response = array();//배열 선언
 
while($row = mysqli_fetch_array($result2)){
 //response["userID"]=$row[0] ....이런식으로 됨.
 
    array_push($response, array("lec_title"=>$row[0],"lec_order"=>$row[1],"lec_text"=>$row[2],"learn_time"=>$row[3],"recent_time"=>$row[4],"lec_num"=>$row[5],"course_num"=>$row[6],"lec_time"=>$row[7]));
    }
 //response라는 변수명으로 JSON 타입으로 $response 내용을 출력

 echo json_encode(array("response"=>$response));

mysqli_close($con);
 ?>