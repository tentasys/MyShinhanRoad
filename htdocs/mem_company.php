<?php

$con = mysqli_connect('192.168.1.156', 'root', '', 'shinple') or die (mysql_error());
$company = $_POST['company'];

if($company == "bank")
{
    $query = <<<_SQL
        SELECT c.company_name, m.mem_num, m.mem_name
        FROM company c JOIN member m ON (c.company_num = m.company_num)
        WHERE company_name = "신한은행";
    _SQL;
}
else if($company == "card")
{
    $query = <<<_SQL
        SELECT c.company_name, m.mem_num, m.mem_name
        FROM company c JOIN member m ON (c.company_num = m.company_num)
        WHERE company_name = "신한카드";
    _SQL;
}
else if($company == "invest")
{
    $query = <<<_SQL
        SELECT c.company_name, m.mem_num, m.mem_name
        FROM company c JOIN member m ON (c.company_num = m.company_num)
        WHERE company_name = "신한금융투자";
    _SQL;
}
else if($company == "life")
{
    $query = <<<_SQL
        SELECT c.company_name, m.mem_num, m.mem_name
        FROM company c JOIN member m ON (c.company_num = m.company_num)
        WHERE company_name = "신한생명";
    _SQL;
}
else if($company == "sys")
{
    $query = <<<_SQL
        SELECT c.company_name, m.mem_num, m.mem_name
        FROM company c JOIN member m ON (c.company_num = m.company_num)
        WHERE company_name = "신한DS";
    _SQL;
}
else{
    $query = <<<_SQL
    SELECT c.company_name, m.mem_num, m.mem_name
    FROM company c JOIN member m ON (c.company_num = m.company_num);
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