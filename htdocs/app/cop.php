<?php
 
 $con = mysqli_connect('127.0.0.1', 'root', '', 'shinple');


//$L = intval($_POST["userNum"]);
//$state = $_POST["state"];  //태그

// Query 구현

$query = <<<_SQL
SELECT cop_name, cop_point, cop_intro, cop_num, cop_chief
FROM `cop`;
_SQL;


$result = mysqli_query($con, $query);


$response = array();//배열 선언
 
while($row = mysqli_fetch_array($result)){
 //response["userID"]=$row[0] ....이런식으로 됨.
 
    array_push($response, array("cop_name"=>$row[0],"cop_point"=>$row[1],"cop_intro"=>$row[2],"cop_num"=>$row[3], "cop_chief"=>$row[4]));
    }
 //response라는 변수명으로 JSON 타입으로 $response 내용을 출력
 

echo json_encode(array("response"=>$response));

mysqli_close($con);
 ?>