<?php

header("Content-Type: text/html; charset=UTF-8");   //UTF-8 설정

?>

 

<?php


 
    
$con = mysqli_connect("192.168.1.156", "root", "",'shinple') or die (mysql_error());



for($i=0; $i<=10; $i++){
    $table_records = $_POST["table_records"][$i];

    $sql = "DELETE FROM learn_course WHERE course_num = '$table_records';";

    mysqli_query($con, $sql);
    $sql = "DELETE FROM course WHERE course_num = '$table_records';";
    mysqli_query($con, $sql);

}

?>




<script type="text/javascript">alert("삭제되었습니다.");</script>
<meta http-equiv="refresh" content="0 url=study_list.php" /> 



