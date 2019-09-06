<!doctype html>
<html lang="ko">
  <head>
    <meta charset="utf-8">
    <title>TEST</title>
  </head>
  <body>
  <table width=600;>
   <tr>
     <td>No
     <td>이름
     <td>메모
     <td>시간
<?php

$connect = mysqli_connect ( 'localhost', 'test1', 'test123', 'sample' );
$result = mysqli_query( $connect);

?>


 </table>
  </body>
</html>
