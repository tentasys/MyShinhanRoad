<?php
   include("db_connection.php"); 
   $sql = "DELETE FROM tag WHERE tag_num ='35'";
   $result = mysqli_query($con,$sql);
   ?>