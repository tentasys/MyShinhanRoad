<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
</head>
 
<?php
session_start (); 
// 세션을 사용하기 위해 초기화
 
/* email, password가 post로 안넘어오면 exit*/
if(!isset($_POST['id']) || !isset($_POST['password'])) exit;
 
$id = $_POST['id'];
$pw = $_POST['password'];
 
/* email, password가 공백이면 exit*/
if ( ($id=='') || ($pw=='') ) {
  echo "<script>alert('아이디 또는 패스워드를 입력하여 주세요.');history.back();</script>";
  exit;
}
 
/* db연결 */
    $con = mysqli_connect("192.168.1.156", "root", "",'shinple') or die (mysql_error());
 
/* db 조회, post로 받은 id, pw 일치하면 로그인 성공*/
    $query="select mem_num, mem_password, mem_name, mem_auth from member where mem_num = '$id' and mem_password = '$pw' "; 
    
    $result=mysqli_query($con, $query);
 
 
    $row=mysqli_fetch_array($result);
 
    $nickname = $row["mem_name"];
    $auth = $row["mem_auth"];
 
    // 한글사용
    $nickname = iconv("EUC-KR","UTF-8", $nickname);
 
 
    $recordcount =mysqli_num_rows($result);
 
/* id, pw 일치하면 board.php호출 */
    if ( $auth >=2){
      if ( $recordcount == 1 ) 
      {
          $_SESSION['mem_name'] = $mem_name;
    
        echo "<script>alert('환영합니다!'); location.href='index.php';</script>"; 
      }
      else{ /* id, pw 실패하면 이전화면 */
        echo "<script>alert('아이디 또는 비밀번호를 확인해주세요.'); history.back(); </script>"; 
        }
      }
        else{
      echo "<script>alert('Admin 권한 등급이 낮습니다.'); history.back(); </script>"; 
        }
    mysqli_close($conn);
 
 
?>
 
</html>
