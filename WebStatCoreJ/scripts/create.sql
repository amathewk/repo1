select ID,xmlquery('$c/FORESEE/REMOTE_ADDR' passing STATS.EVENT as "c"),xmlquery('$c/FORESEE/REQUEST_TIME' passing STATS.EVENT as "c")  from STATS
where xmlexists('$c/FORESEE[REMOTE_ADDR!="209.64.87.68"]' passing STATS.EVENT as "c");


create view EVENTVIEW(ID, CLIENT_ID, HTTP_HOST, REMOTE_ADDR, REMOTE_PORT, SERVER_ADDR, SERVER_NAME, SERVER_PORT, REQUEST_TIME) as
select s.id,t.* from  STATS s,
xmltable('$c/FORESEE' passing s.EVENT as "c"
columns CLIENT_ID varchar(100) path 'CLIENT_ID',
	HTTP_HOST varchar(100) path 'HTTP_HOST',
	REMOTE_ADDR varchar(100) path 'REMOTE_ADDR',
	REMOTE_PORT varchar(100) path 'REMOTE_PORT',
	SERVER_ADDR varchar(100) path 'SERVER_ADDR',
	SERVER_NAME varchar(100) path 'SERVER_NAME',
	SERVER_PORT varchar(100) path 'SERVER_PORT',
	REQUEST_TIME varchar(100) path 'REQUEST_TIME'
	) as t;

	
create view EVENTVIEW(ID, URL, ELEM_TYPE, ELEM_ID, ELEM_NAME, ELEM_VALUE, REFERRER, HOST, UA_CPU) as
select s.id,t.* from  STATS s,
xmltable('$c/REQUEST' passing s.EVENT as "c"
columns URL varchar(100) path 'PARAMETERS/url',
 	ELEM_TYPE varchar(100) path 'PARAMETERS/type',
	ELEM_ID varchar(100) path 'PARAMETERS/id', 	
	ELEM_NAME varchar(100) path 'PARAMETERS/name', 	
	ELEM_VALUE varchar(100) path 'PARAMETERS/value', 	
	REFERRER varchar(100) path 'HEADERS/Referer',
	HOST varchar(100) path 'HEADERS/Host',
	UA_CPU varchar(20) path 'HEADERS/UA-CPU'
	) as t;		