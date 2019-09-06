<?php
 
 $con = mysqli_connect('127.0.0.1', 'root', '', 'shinple');

//유저넘버 받음
$L = intval($_POST["userNum"]);

// Query 구현

$query = <<<_SQL
SELECT c.cop_name, ci.cop_ranking , c.cop_intro ,c.cop_num 
FROM member m JOIN my_cop mc ON(m.mem_num=mc.mem_num) JOIN cop c ON(mc.cop_num =c.cop_num) JOIN copinfo ci ON( c.cop_num = ci.cop_num)
WHERE m.mem_num = $L
_SQL;


$result = mysqli_query($con, $query/*"SELECT * FROM course WHERE courseLevel = $L[1];"*/);


$response = array();//배열 선언
 
while($row = mysqli_fetch_array($result)){
 //response["userID"]=$row[0] ....이런식으로 됨.
 
    array_push($response, array("cop_name"=>$row[0],"cop_rank"=>$row[1],"cop_intro"=>$row[2],"cop_num"=>$row[3]));
    }
 //response라는 변수명으로 JSON 타입으로 $response 내용을 출력
 

echo json_encode(array("response"=>$response));

mysqli_close($con);
 ?>