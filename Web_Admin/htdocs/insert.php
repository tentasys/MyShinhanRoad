<?php

header("Content-Type: text/html; charset=UTF-8");   //UTF-8 설정

?>

 

<?php

 

    mysqli_connect("localhost", "root", "") or die (mysqli_error()); 

    mysqli_select_db(""); 

 

    $problem_num = 1;     // ComName(회사명)을 POST 방식으로 받습니다. 

    $course_name = $_POST["course_name"];                   // Name(이름)

    $problem_answer = $_POST["problem_answer"];                        // Mail(이메일)

    $problem_no1 = $_POST["problem_no1"];             //  Telnum(전화번호)

    $problem_no2 = $_POST["problem_no2"];                   //  Radio(라디오버튼의 값)

    $problem_no3 = $_POST["problem_no3"];              //  Combo(콤보박스의 값)

    $problem_no4 = $_POST["problem_no4"];                      //  Text(문의내용)

 

    //쿼리를 통해 넘어온 값을 DB에 INSERT 합니다.

    $sql = "insert into problem (problem_num, course_name, problem_answer, problem_no1, problem_no2, problem_no3, problem_no4) 

                values ('1', '$course_name', '$problem_answer', '$problem_no1', '$problem_no2', '$problem_no3', '$problem_no4')";

    mysql_query($sql) or die (mysql_error());

 

 //링크된 페이지로 이동시키며 경고창을 띄웁니다.

    echo("

    <html>

        <head>

        <script name=javascript>

            location.href='http://www.innergy.co.kr/EXER/input_form.php'; 

            self.window.alert('빠른 시간 안에 연락드리겠습니다!');

        </script>

        </head>

       </html>

 

");
