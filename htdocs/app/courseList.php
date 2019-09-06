<?php
 
$con = mysqli_connect('127.0.0.1', 'root', '', 'shinple');

// array를 받을 변수
$N = $_POST["userNum"];
/*$L = $_POST["level"];
$Fil = $_POST["filter"];

// 변수가 비었을 때 NULL로 채운다 
$in_list = empty($L)?'NULL':"'".join("','", $L)."'";
$in_list2 = empty($Fil)?'NULL':"'".join("','", $Fil)."'";*/
if(empty($_POST["level"])&&!empty($_POST["filter"])){
   $Fil = $_POST["filter"];
   $in_list2 = empty($Fil)?'NULL':"'".join("','", $Fil)."'";
   $query = <<<_SQL
   SELECT c.course_title, c.course_level, c.course_tch, c.course_text,  c.course_num , COALESCE(l.learn_state,0), COALESCE(c.course_like,0), l.course_like
   FROM course c LEFT JOIN learn_course l ON (c.course_num = l.course_num) AND l.mem_num = $N
   WHERE (`course_tag01` IN ({$in_list2}) or `course_tag02` IN ({$in_list2}) or `course_tag03` IN ({$in_list2})) AND c.course_nec = 0;
   _SQL;

} else if (!empty($_POST["level"])&&empty($_POST["filter"])){
   $L = $_POST["level"];
   $in_list = empty($L)?'NULL':"'".join("','", $L)."'";

   $query = <<<_SQL
   SELECT c.course_title, c.course_level, c.course_tch, c.course_text,  c.course_num , COALESCE(l.learn_state,0), COALESCE(c.course_like,0), l.course_like
   FROM course c LEFT JOIN learn_course l ON (c.course_num = l.course_num) AND l.mem_num = $N
   WHERE `course_level` IN ({$in_list}) AND c.course_nec = 0;
   _SQL;

} else if (!empty($_POST["level"])&&!empty($_POST["filter"])) {
   $L = $_POST["level"];
   $Fil = $_POST["filter"];
   $in_list = empty($L)?'NULL':"'".join("','", $L)."'";
   $in_list2 = empty($Fil)?'NULL':"'".join("','", $Fil)."'";
   $query = <<<_SQL
   SELECT c.course_title, c.course_level, c.course_tch, c.course_text,  c.course_num , COALESCE(l.learn_state,0), COALESCE(c.course_like,0), l.course_like
   FROM course c LEFT JOIN learn_course l ON (c.course_num = l.course_num) AND l.mem_num = $N
   WHERE `course_level` IN ({$in_list}) AND (`course_tag01` IN ({$in_list2}) or `course_tag02` IN ({$in_list2}) or `course_tag03` IN ({$in_list2})) AND c.course_nec = 0;
   _SQL;
} else {
   $query = <<<_SQL
   SELECT c.course_title, c.course_level, c.course_tch, c.course_text,  c.course_num , COALESCE(l.learn_state,0),COALESCE(c.course_like,0), l.course_like
   FROM course c LEFT JOIN learn_course l ON (c.course_num = l.course_num) AND l.mem_num = $N
   WHERE c.course_nec = 0;
   _SQL;
}
// Query 구현
//전체좋아요 추가 SUM
/*$query = <<<_SQL
SELECT c.course_title, c.course_level, c.course_tch, c.course_text,  c.course_num , COALESCE(l.learn_state,0),c.course_like
FROM course c LEFT JOIN learn_course l ON (c.course_num = l.course_num) AND l.mem_num = $N
WHERE `course_level` IN ({$in_list}) AND (`course_tag01` IN ({$in_list2}) or `course_tag02` IN ({$in_list2}) or `course_tag03` IN ({$in_list2}))
_SQL;*/

$result = mysqli_query($con, $query/*"SELECT * FROM course WHERE courseLevel = $L[1];"*/);

$response = array();//배열 선언
 
while($row = mysqli_fetch_array($result)){
 //response["userID"]=$row[0] ....이런식으로 됨.
 
    array_push($response, array("course_title"=>$row[0], "course_level"=>$row[1], "course_tch"=>$row[2],"course_text"=>$row[3],"course_num" =>$row[4],"learn_state"=>$row[5],"Like"=>$row[6], "mem_like"=>$row[7]));
    }
 //response라는 변수명으로 JSON 타입으로 $response 내용을 출력

 

echo json_encode(array("response"=>$response));

mysqli_close($con);
 ?>