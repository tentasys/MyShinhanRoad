<?php

  include_once 'db_connection.php';

    //알림 보내는 php 함수
  function send_notification ($tokens, $message)
  {
    $url = 'https://fcm.googleapis.com/fcm/send';

    $fields = array(
        'registration_ids'		=> $tokens,         //토큰
        'data'	=> $message                         //보낼 메시지
      );

    $headers = array(
      'Authorization:key =' . GOOGLE_API_KEY,
      'Content-Type: application/json'
      );

    $ch = curl_init();
      curl_setopt($ch, CURLOPT_URL, $url);
      curl_setopt($ch, CURLOPT_POST, true);
      curl_setopt($ch, CURLOPT_HTTPHEADER, $headers);
      curl_setopt($ch, CURLOPT_RETURNTRANSFER, true);
      curl_setopt($ch, CURLOPT_SSL_VERIFYHOST, 0);  
      curl_setopt($ch, CURLOPT_SSL_VERIFYPEER, false);
      curl_setopt($ch, CURLOPT_POSTFIELDS, json_encode($fields));
      $result = curl_exec($ch);           
      if ($result === FALSE) {
          die('Curl failed: ' . curl_error($ch));
      }
      curl_close($ch);
      return $result;          //통신 결과
    }

  $Noti_name = $_POST["noti_name"]; 
  $Noti_text = $_POST["noti_text"]; 
  $Noti_date = date("Y-m-d H:i:s"); // 등록 일시 - Date 함수로 받아오기


    $sql = "INSERT INTO `notification`(`noti_name`, `noti_text`, `noti_date`)
            VALUES ('$Noti_name', '$Noti_text', '$Noti_date' )";

    $result =  mysqli_query($con, $sql);

    //알림 보내는 부분
    $sql = <<<_SQL
    SELECT mem_token FROM member;
    _SQL;

    $result = mysqli_query($con, $sql);

    $tokens = array();              //넘길 토큰들
    $message = array("message"=>"새 공지사항이 등록되었습니다.");

    while($row = mysqli_fetch_assoc($result)){
      $tokens[] = $row["mem_token"];
    }

    $result = send_notification($tokens, $message);
    echo $result;

    mysqli_close($con);
    echo("<script>location.href='../log_view.php';</script>");
    
    ?>
    
    
    
 