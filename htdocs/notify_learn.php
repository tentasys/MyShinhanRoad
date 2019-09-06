<!DOCTYPE html>
<head>
    <script src="build/js/script.js" type="text/javascript"></script>
</head>
<body>
    <script>
        notification();
    </script>

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

    $num = $_POST["row_num"];       //반복문 횟수
    $message = $_POST["msg"];
    $tokens = array();              //넘길 토큰들
    $message = array("message"=>$message);

    for($i=1; $i<$num; $i++){
        $tokens[] = $_POST["row".$i];
    }

    $result = send_notification($tokens, $message);
    echo $result;
?>
</body>
</html>