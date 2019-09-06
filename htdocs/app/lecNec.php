<?php
 
$con = mysqli_connect('127.0.0.1', 'root', '', 'shinple');

// UserNum 입력 받음
$L = intval($_POST["userNum"]);


$query2 = <<<_SQL
SELECT lec_num FROM lecture WHERE lec_nec=1;
_SQL;
$result2 = mysqli_query($con, $query2/*"SELECT * FROM course WHERE courseLevel = $L[1];"*/);

while($row2 = mysqli_fetch_array($result2)){
   //response["userID"]=$row[0] ....이런식으로 됨.
   $query3 = <<<_SQL
   INSERT INTO learn_lec(`mem_num`,`lec_num`) VALUES($L,$row2[0]);
   _SQL;
   
   mysqli_query($con, $query3/*"SELECT * FROM course WHERE courseLevel = $L[1];"*/);
}


//최근 들은 강의 정보 출력
$query = <<<_SQL
SELECT lec.lec_title, lec.lec_order, lec.lec_text , ll.learn_time, ll.recent_learn ,ll.lec_num , ll.course_num, lec.lec_time
FROM member m JOIN learn_lec ll ON(m.mem_num=ll.mem_num) JOIN lecture lec ON (lec.lec_num=ll.lec_num)
WHERE m.mem_num = $L AND lec.lec_nec = 1;
_SQL;

//쿼리 결과
$result = mysqli_query($con, $query/*"SELECT * FROM course WHERE courseLevel = $L[1];"*/);

//배열 선언
$response = array();

//저장
while($row = mysqli_fetch_array($result)){
 //response["userID"]=$row[0] ....이런식으로 됨.
 
    array_push($response, array("lec_title"=>$row[0],"lec_order"=>$row[1],"lec_text"=>$row[2],"learn_time"=>$row[3],"recent_time"=>$row[4],"lec_num"=>$row[5],"course_num"=>$row[6],"lec_time"=>$row[7]));
    }
 //response라는 변수명으로 JSON 타입으로 $response 내용을 출력
 
//제이슨 인코드로
echo json_encode(array("response"=>$response));

mysqli_close($con);
 ?>