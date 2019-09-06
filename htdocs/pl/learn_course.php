<?php

$con = mysqli_connect('192.168.1.156', 'root', '', 'shinple') or die (mysql_error());
$L = $_POST['course_num'];
$state = $_POST['state'];

if($state == "complete")
{
    $query = <<<_SQL
    SELECT company.company_name com_name, joined.num mem_num, joined.name mem_name, mem_token, joined.learn_state mem_learn_state from 
    (SELECT c.company_num com_num, c.mem_num num, c.mem_name name, mem_token, l.learn_state learn_state FROM member c JOIN learn_course l ON (c.mem_num = l.mem_num) WHERE l.course_num = $L AND l.learn_state = 3) joined
    JOIN company company ON (company.company_num = joined.com_num);
    _SQL;
}
else if($state == "total")
{
    $query = <<<_SQL
    SELECT company.company_name com_name, joined.num mem_num, joined.name mem_name, mem_token, joined.learn_state mem_learn_state from 
    (SELECT c.company_num com_num, c.mem_num num, c.mem_name name, mem_token, l.learn_state learn_state FROM member c JOIN learn_course l ON (c.mem_num = l.mem_num) WHERE l.course_num = $L) joined
    JOIN company company ON (company.company_num = joined.com_num);
    _SQL;
}
else
{
    $query = <<<_SQL
    SELECT company.company_name com_name, joined.num mem_num, joined.name mem_name, mem_token, joined.learn_state mem_learn_state from 
    (SELECT c.company_num com_num, c.mem_num num, c.mem_name name, mem_token, l.learn_state learn_state FROM member c JOIN learn_course l ON (c.mem_num = l.mem_num) WHERE l.course_num = $L AND NOT l.learn_state = 3) joined
    JOIN company company ON (company.company_num = joined.com_num);
    _SQL;
}

$result = mysqli_query($con, $query);
$i = 0;
while($row = mysqli_fetch_assoc($result)){
  $rows[$i++] = $row;
}

mysqli_close($con);

echo json_encode($rows);

?>