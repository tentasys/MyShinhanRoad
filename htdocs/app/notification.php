<?php
$con = mysqli_connect('127.0.0.1', 'root', '', 'shinple');

$query = <<<_SQL
SELECT noti_name, noti_text,noti_date FROM notification
ORDER BY noti_num DESC;
_SQL;

$result = mysqli_query($con, $query/*"SELECT * FROM course WHERE courseLevel = $L[1];"*/);

$response = array();//배열 선언
//제이슨 인코드로
while($row = mysqli_fetch_array($result)){

     array_push($response, array("noti_name"=>$row[0],"noti_text"=>$row[1],"noti_date"=>$row[2]));

    }
    //response라는 변수명으로 JSON 타입으로 $response 내용을 출력S
    echo json_encode(array("response"=>$response)); 
   
mysqli_close($con);
 ?>