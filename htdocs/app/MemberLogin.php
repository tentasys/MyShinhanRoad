<?php
$con = mysqli_connect('127.0.0.1', 'root', '', 'shinple');

$mem_num = $_POST["mem_num"];
$mem_password = $_POST["mem_password"];

$query = <<<_SQL
SELECT mem_name, company_num, mem_point,mem_noti FROM member WHERE mem_num = $mem_num AND mem_password = $mem_password;
_SQL;

$result = mysqli_query($con, $query/*"SELECT * FROM course WHERE courseLevel = $L[1];"*/);

$response = array();//배열 선언
//제이슨 인코드로

if ( $result ) {
     if(mysqli_num_rows($result) == 1){
        $row = mysqli_fetch_array($result);
        array_push($response, array("mem_name"=>$row[0],"company_num"=>$row[1],"mem_point"=>$row[2],"mem_noti"=>$row[3]));
        echo json_encode(array("success"=>$response));
     }else{
        echo json_encode(array("failure"=>$response)); 
    }
}
else{        
    echo json_encode(array("failure"=>$response)); 
}   
mysqli_close($con);
 ?>