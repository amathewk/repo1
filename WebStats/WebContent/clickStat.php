<?php

$params = $_SERVER["QUERY_STRING"];

# var_dump($_REQUEST);
# var_dump($_SERVER);
# $urlParts = parse_url("http://www.con.com?param1=3132k&dasdsa=1212&oiuo=9w0");
# print_r($urlParts['query']);

$params = $params."&ip=".$_SERVER['REMOTE_ADDR'];

$handle = fopen("clickInfo.txt", "a");

fwrite($handle, $params."\n");
fclose($handle);
?>