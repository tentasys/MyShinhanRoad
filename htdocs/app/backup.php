<?php
 
 $con = mysqli_connect('127.0.0.1', 'root', '', 'shinple');

// array를 받을 변수
$L = $_POST["level"];
$Fil = $_POST["filter"];  //태그

// 변수가 비었을 때 NULL로 채운다 
$in_list = empty($L)?'NULL':"'".join("','", $L)."'";
$in_list2 = empty($Fil)?'NULL':"'".join("','", $Fil)."'";


// Query 구현

$query = <<<_SQL
SELECT course_title,course_level,course_tch,course_text, course_num
FROM `course` 
WHERE `course_level` IN ({$in_list}) AND (`course_tag01` IN ({$in_list2}) or `course_tag02` IN ({$in_list2}) or `course_tag03` IN ({$in_list2}))
ORDER BY course_level;
_SQL;


$result = mysqli_query($con, $query/*"SELECT * FROM course WHERE courseLevel = $L[1];"*/);


$response = array();//배열 선언
 
while($row = mysqli_fetch_array($result)){
 //response["userID"]=$row[0] ....이런식으로 됨.
 
    array_push($response, array("course_title"=>$row[0], "course_level"=>$row[1], "course_tch"=>$row[2],"course_text"=>$row[3],"course_num" =>$row[4]));
    }
 //response라는 변수명으로 JSON 타입으로 $response 내용을 출력
 

echo json_encode(array("response"=>$response));

mysqli_close($con);
 ?>