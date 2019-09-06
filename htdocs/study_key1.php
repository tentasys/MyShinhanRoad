<?php
include("db_connection.php"); 
// mysqli_query("set names utf8");
// $query =" select * from tag";
// $result = mysqli_query($query);
// echo $result;



$sql="SELECT*FROM tag";
$rs= mysqli_query($con,$sql);
while($info = mysqli_fetch_array($rs)){
    echo'Col1:'.$info['tag_num']."/";
    echo'Col2:'.$info['tag_name']."<br/>";
}
mysqli_close($con);
?>