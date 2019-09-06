<?php
    include('video_upload2.php');

    $con = mysqli_connect('192.168.1.156', 'root', '', 'shinple');

    $Lecture_title = $_POST["lecture_title"]; //강의 제목
    $Lecture_text = $_POST["lecture_text"]; //강의 설명
    $Lecture_order = $_POST["lecture_order"]; // 강의 순서

    echo "강의명 : ", $Lecture_title, "<br>";
    echo "강의 설명 : ", $Lecture_text, "<br>";
    echo "강의가 속한 강좌명 : ", $Lecture_belong, "<br>";
    echo "강의가 속한 강좌 번호 : ", $Lecture_course, "<br>";
    echo "강의 경로 : ", $Lecture_path, "<br>";
    echo "강의 순서 : ", $Lecture_order, "<br>";

    $query = "INSERT INTO `lecture` (`lec_title`, `lec_text`, `lec_course`, `lec_path`, `lec_order`)
     VALUES ('$Lecture_title', '$Lecture_text', '$Lecture_course', '$Lecture_path', '$Lecture_order')";

    $result = mysqli_query($con, $query);

    if ($result)
    {
        echo "경***** 성 공 ******축";
    }
    else
    {
        echo "남자는 긴 말 하지 않는다.";
    }
    
    mysqli_close($con);
    echo("<script>location.href='../study_write.php';</script>");
?>