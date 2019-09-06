<?php
 
$con = mysqli_connect('127.0.0.1', 'root', '', 'shinple');

// // UserNum과 token 받아옴
$L = intval($_POST["userNum"]);
$token = $_POST["userToken"];
// $token = "24566";

if (!$con)
{
   echo "MySQL 접속 에러 : ";
   echo mysqli_connect_error();
   exit();
}

//유저의 토큰 가져오는 쿼리 작성
$query = <<<_SQL
SELECT mem_token FROM member WHERE mem_num = $L
_SQL;

//쿼리 결과
$result = mysqli_query($con, $query);
$row = mysqli_fetch_array($result);
$mem_token = $row['mem_token'];

if(strcmp($mem_token, $token) || $mem_token == null){  //현재 가지고 있는 token과 저장되어 잇는 token이 다를 때

  //토큰 업데이트 쿼리 작성
  $query = <<<_SQL
  UPDATE member SET mem_token = "$token" WHERE mem_num = $L
  _SQL;

  $result = mysqli_query($con, $query);       //값 업데이트
}

echo json_encode(array("response"=>$result));

mysqli_close($con);
 ?>