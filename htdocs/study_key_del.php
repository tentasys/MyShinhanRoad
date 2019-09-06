<?php
   include("db_connection.php"); 
  // array를 받을 변수

  //db delete
   $del_tag = $_POST["del_tag"];
		
  if ( isset( $del_tag ) ) {
        $sql_delete = "DELETE FROM tag WHERE 'tag_name' = '$del_tag'";
        mysqli_query( $con, $sql_delete );
        echo '<p style="color: red;">tag '. $del_tag .'  is deleted.</p>';
      }
      $sql = "SELECT * FROM tag LIMIT 25;";
      $result = mysqli_query( $con, $sql );
  while( $row = mysqli_fetch_array( $result ) ) {
   echo '<tr><td>' . $row[ 'tag_num' ] . '</td><td>'. $row[ 'tag_name' ] . '</td><td>' ;
      }
   //db delete

  mysqli_close($con);

//   echo ("<script language=javascript> history.back(); </script>");


  ?>