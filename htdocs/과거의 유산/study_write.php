<?php
  $con = mysqli_connect('192.168.1.134', 'root', '', 'shinple');

  //연결 상태 확인
  if (mysqli_connect_errno())
  {
    echo "DB와 연결에 실패하였습니다. : ". mysqli_connect_error();
  }

  //Course 파트
  //변수 대입
  $Course_title = $_POST["course_title"]; //강좌 제목
  $Course_teacher = $_POST["course_teacher"]; //강사명
  $Course_level = $_POST["iCheck"]; //강좌 래벨 - 라디오 받아오기
  $Course_text = $_POST["course_text"]; //강좌 설명
  $Course_point = $_POST["course_point"]; //강좌 점수
  $Course_date = date("Y-m-d H:i:s"); // 등록 일시 - Date 함수로 받아오기
  $Course_necessary = $_POST["iCheck2"];
  //$Course_Picture = $_Post["..."];  // 강사 사진 - 사진 받아오기
  //tag01

  //////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    //////////////////////////////////////////////////////////////////////////////////////////////////////////////////
      //////////////////////////////////////////////////////////////////////////////////////////////////////////////////
      //변경된 태그
  /*
  foreach ($_POST["course_tag1"] as $  $Course_tag1)
  {
    print "You are selected $Course_tag1<br>";
  }*/
    //////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    //////////////////////////////////////////////////////////////////////////////////////////////////////////////////
      //////////////////////////////////////////////////////////////////////////////////////////////////////////////////

  //$Course_tag2 = $_POST["course_tag2"]; // Tag2
  //$Course_tag3 = $_POST["course_tag3"]; // Tag3
  //$Course_like = $_POST["course_like"]; // 변수 선언은 하였지만, 필요 없는 기능

  // Tag에 0이면 NULL값을 넣는다.
  //$Course_tag1 = empty($Course_tag1)?'NULL':"'".join("','", $Course_tag1)."'";
  //$Course_tag2 = empty($Course_tag2)?'NULL':"'".join("','", $Course_tag2)."'";
  //$Course_tag3 = empty($Course_tag3)?'NULL':"'".join("','", $Course_tag3)."'";

  //삽입값 확인
  echo "강좌명 : ", $Course_title, "<br>";
  echo "강사명 : ", $Course_teacher, "<br>";
  echo "강좌 설명 : ", $Course_text, "<br>";
  echo "강좌 래벨 : ", $Course_level, "<br>";
  echo "강좌 점수 : ", $Course_point, "<br>";
  echo "등록 일시 : ", $Course_date, "<br>";
  echo "필수 강좌 여부 : ", $Course_necessary. "<br>";

  //DB 삽입
  //$query = "INSERT INTO `course`(`course_title`, `course_tch`, `course_level`, `course_text`, `course_date`, `course_point`, `course_tag01`) VALUES ('$Course_title', '$Course_teacher', '$Course_level', '$Course_text', '$Course_date', '$Course_point', '$Course_tag1')";
  //$sql = "INSERT INTO `course`(`course_title`, `course_tch`, `course_level`, `course_text`, `course_date`, `course_point`, `course_necessary`) VALUES ('$Course_title', '$Course_teacher', '$Course_level', '$Course_text', '$Course_date', '$Course_point', '$Course_necessary')";

/*
  //사진 업로드 파트
  $target_dir = "uploads/";
  $target_file = $target_dir . basename($_FILES["fileUpload"]["fileUpload"]); // name은 뭐지?
  $uploadOk = 1;
  $imageFileType = strtolower(pathinfo($target_file, PATHINFO_EXTENSION));
  //이미지 파일이 진짜인지 확인
  if (isset($_POST["submit"]))
  {
    $check = getimagesize($_FILES["fileUpload"]);
    if ($check !== false)
    {
      echo "파일 이미지 : " . $check["mime"]."."; //////////////////////여기 . 맞아?
      $uploadOk = 1;
    }
    else{
      echo "파일 이미지가 존재하지 않습니다.<br>";
      $uploadOk = 0;
    }
  }

  //이미지 파일이 이미 존재한다면, 업로드 거부
  if (file_exists($target_file))
  {
    echo "죄송합니다. 파일이 이미 존재합니다.<br>";
    $uploadOk = 0;
  }
  
  //파일 사이즈 확인 후 용량 초과시, 업로드 거부
  if ($_FILES["fileUpload"]["size"] > 5000000)
  {
    echo "죄송합니다. 파일의 사이즈가 너무 큽니다.<br>";
    $uploadOk = 0;
  }

  //파일 형식을 확인
  if ($imageFileType != "jpg" && $imageFileType != "png" && $imageFileType != "jpeg" && $imageFileType != "gif")
  {
    echo "죄송합니다. .jpg, .jpeg, .png, .gif 형식의 파일만 업로드 할 수 있습니다.<br>";
    $uploadOk = 0;
  }

  //$uploadOk의 값에 따라 업로드 당락
  if ($uploadOk == 0)
  {
    echo "위의 사유로 파일 업로드에 실패하였습니다.<br>";
  }
  else
  {
    if (move_uploaded_file($_FILES["fileUpload"], $target_file))
    {
      echo "<p>The file ".basename($_FILES["fileUpload"]["name"]). "가 업로드 되었습니다.</p>";
      echo "<br><img src =/ uploads/".basename($_FILES["fileUpload"]["name"]). " > ";
      echo "<br><button type='button' onclick='history.back()'>돌아가기</button>"; /////////////////??
    }
    else
    {
      echo "<p>Sorry, there was an error uploading your file.</p>";
      echo "<br><button type='button' onclick='history.back()'>돌아가기</button>";
    }
  }
  */

  //Lecture 1 파트
  //Course_num 가져오기
  //$course_num = "SELET course_num FROM course WHERE course_title = $Course_title";
  //변수 대입
  $Lec_title1 = $_POST["lec_title1"];
  //$Lec_text = $_POST[""]; 웹에 없다. Ssul 부분
  //$Lec_course = $course_num;
  //$Lec_path = $_POST["upload_file"];
  //$Lec_Time = $_POST[""]; 웹에 없다. 시간 부분... 얘도 그냥 긇어오면 될거 같긴한대

  echo "강의 1 제목 : ", $Lec_title1, "<br>";

  //DB 삽입
  //$sql .= "INSERT INTO `lecture`(`lec_title`) VALUES ('$Lec_title1')";

  //Lecture 2 파트
  //변수 대입
  $Lec_title2 = $_POST["lec_title2"];
  //$Lec_text = $_POST[""]; 웹에 없다. Ssul 부분
  //$Lec_course = $course_num;
  //$Lec_path = $_POST["upload_file"];
  //$Lec_Time = $_POST[""]; 웹에 없다. 시간 부분... 얘도 그냥 긇어오면 될거 같긴한대

  echo "강의 2 제목 : ", $Lec_title2, "<br>";

  //DB 삽입

  //$sql .= "INSERT INTO `lecture` (`lec_title`) VALUES ('$Lec_title2')";

  //Lecture 3 파트
  //변수 대입
  $Lec_title3 = $_POST["lec_title3"];
  //$Lec_text = $_POST[""]; 웹에 없다. Ssul 부분
  //$Lec_course = $course_num;
  //$Lec_path = $_POST["upload_file"];
  //$Lec_Time = $_POST[""]; 웹에 없다. 시간 부분... 얘도 그냥 긇어오면 될거 같긴한대

  echo "강의 3 제목 : ", $Lec_title3, "<br>";

  $sql = array("INSERT INTO `course`(`course_title`, `course_tch`, `course_level`, `course_text`, `course_point`, `course_date`) VALUES ('$Course_title', '$Course_teacher', '$Course_level', '$Course_text', '$Course_point', '$Course_date')",
   "INSERT INTO `lecture`(`lec_title`) VALUES ('$Lec_title1')",
    "INSERT INTO `lecture` (`lec_title`) VALUES ('$Lec_title2')",
     "INSERT INTO `lecture` (`lec_title`) VALUES ('$Lec_title3')");
  
  for ($i = 0; $i <= 3; $i++)
  {
    mysqli_query($con, $sql[$i]);
  }

  mysqli_close($con);

    //$query = "INSERT INTO `lecture` (`lec_title`, `lec_text`, `lec_path`, `lec_course`) VALUES ($Lec_title', 'Ssul', '$Lec_path', '$Lec_course')";
  ?>