<?php

header("Content-Type: text/html; charset=UTF-8");   //UTF-8 설정

?>

 

<?php



 
    
$con = mysqli_connect('192.168.1.156', 'root', '', 'shinple') or die (mysql_error()); 

 



    //쿼리를 통해 넘어온 값을 DB에 INSERT 합니다.
    for($i=0; $i<=4; $i++){
        
        $problem_num = [1,2,3,4,5][$i];     // ComName(회사명)을 POST 방식으로 받습니다. 

        $course_name = $_POST["course_name"][0];                   // Name(이름)

        $problem_answer = $_POST["problem_answer"][$i];                        // Mail(이메일)

        $problem_txt = $_POST["problem_txt"][$i];  

        $problem_no1 = $_POST["problem_no1"][$i];             //  Telnum(전화번호)ij

        $problem_no2 = $_POST["problem_no2"][$i];                   //  Radio(라디오버튼의 값)

        $problem_no3 = $_POST["problem_no3"][$i];              //  Combo(콤보박스의 값)

        $problem_no4 = $_POST["problem_no4"][$i];                      //  Text(문의내용)
        
        
      
		$sql = "INSERT INTO problem VALUES ('' ,'$problem_num', '$course_name', '$problem_answer','$problem_txt' ,'$problem_no1', '$problem_no2', '$problem_no3', '$problem_no4');";

        mysqli_query($con, $sql);
     
       

	}
mysqli_close($con);



  

 //링크된 페이지로 이동시키며 경고창을 띄웁니다.
 echo("

 <html>

     <head>

     <script name=javascript>

         location.href='study_test.php'; 

         self.window.alert('등록이 완료되었습니다.');

     </script>

     </head>

    </html>



");
?>
<center>
<font size=2>정상적으로 저장되었습니다.</font>

