<?php

$params = $_SERVER["QUERY_STRING"];

$params = $params."&ip=".$_SERVER['REMOTE_ADDR'];

$handle = fopen("urlInfo.txt", "a");

fwrite($handle, $params."\n");
fclose($handle);
?>