<?php
    include('video_upload.php');

    $con = mysqli_connect('192.168.1.156', 'root', '', 'shinple');

    echo "Lecture_belong : ", $Lecture_belong, "<br>";

    // 해당 PHP 파일의 쿼리는 Course 테이블에서 Lecture_belong과 이름이 동일한 course_title의 course_num을 반환하는 쿼리입니다
    $query = "SELECT course_num FROM course WHERE course_title = '$Lecture_belong'";

    $result = $con->query($query);
    $rows = mysqli_fetch_assoc($result);

    $Lecture_course = (int)$rows['course_num'];

    mysqli_close($con);
    echo("<script>location.href='../study_write.php';</script>");
?>