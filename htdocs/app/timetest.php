<?php
 function timedd($time){

    
    $hour = $time[0].$time[1];

    $minute = $time[3].$time[4];

    $second = $time[6].$time[7];

    $mili = ( $hour * 3600 ) + ($minute * 60) + $second;

    return $mili;
 }

 $con = mysqli_connect('127.0.0.1', 'root', '', 'shinple');

 $coursNum =$_POST["course_num"];
 $memNum = $_POST["mem_num"];
 $courseTime = 0;
 $time =<<<_SQL
 SELECT lec_time
 FROM `lecture` 
 WHERE lec_course =$coursNum;
 _SQL;

 $result = mysqli_query($con, $time);

 while($row = mysqli_fetch_array($result)){
           
    $courseTime += timedd($row[0]);
 }

 $learnTime = 0;

 $query =<<<_SQL
 SELECT learn_time
 FROM `learn_lec` ll JOIN `lecture` l ON(ll.lec_num = l.lec_num)
 WHERE ll.mem_num =$memNum AND l.lec_course = $coursNum;
 _SQL;

 $result1 = mysqli_query($con, $query);

 while($row1 = mysqli_fetch_array($result1)){
    $learnTime += $row1[0];
    
 }
$learnTime = round($learnTime/1000);
            
$progress = round(($learnTime/$courseTime)*100); 

$response = array();
array_push($response, array("progress"=>$progress));
echo json_encode(array("response"=>$response));

mysqli_close($con);
 ?>