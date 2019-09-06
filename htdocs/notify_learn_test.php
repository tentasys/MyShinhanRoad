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

    //-------------------수료처리 하기
    $that_guy = $_POST["this_guy"];
    $that_course = $_POST["this_course"];

    echo "회원 번호 : ", $that_guy, "<br>";
    echo "강좌 번호 : ", $that_course, "<br>";

    $query = 
    <<<_SQL
    UPDATE learn_course SET learn_state = '3' WHERE mem_num = $that_guy AND course_num = $that_course;
    _SQL;

    $result = mysqli_query($con, $query);


    //---------------------알림 보내기
    $sql = <<<_SQL
    SELECT mem_token FROM member WHERE mem_num = $that_guy;
    _SQL;

    $result = mysqli_query($con, $sql);

    echo $sql;
    $tokens = array();              //넘길 토큰들
    $message = array("message"=>"테스트를 완료한 강좌 수료 처리가 완료되었습니다.");

    while($row = mysqli_fetch_assoc($result)){
      $tokens[] = $row["mem_token"];
    }

    $result = send_notification($tokens, $message);
    

    mysqli_close($con);

    echo("<script>location.href='../member_test.php';</script>");

?>
