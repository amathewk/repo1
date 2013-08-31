var xmlhttp;

var clientId="10001";

var phpTrackFileLocation="WebStats";
var imageFileLocation="WebStats";


function sendTrackEvent(url)
{

	//netscape.security.PrivilegeManager.enablePrivilege("UniversalPreferencesRead UniversalPreferencesWrite");

// alert("sending url: " + url);	
	
xmlhttp=null;
if (window.XMLHttpRequest)
  {// code for all new browsers
  xmlhttp=new XMLHttpRequest();
  }
else if (window.ActiveXObject)
  {// code for IE5 and IE6
  xmlhttp=new ActiveXObject("Microsoft.XMLHTTP");
  }
if (xmlhttp!=null)
  {
  xmlhttp.onreadystatechange=state_Change;
  xmlhttp.open("GET",url,true);
  xmlhttp.send(null);
  }
else
  {
	// TODO mask with error handling. write to remote file on client
  alert("Your browser does not support XMLHTTP.");
  }
}

function state_Change()
{

if (xmlhttp.readyState==4)
  {// 4 = "loaded"
  if (xmlhttp.status==200)
    {// 200 = OK
    // ...our code here...
//	    alert("worked");
    }
  else
    {
	  // TODO mask with error handling. write to remote file on client
//    alert("Problem retrieving XML data");
    }
  }
}


function initLoad() {

	//	alert("URL" + window.location);
	// TODO: send the above URL in an ajax call
	var params="clientId="+ clientId + "&url=" + window.location + "&time=" + new Date().getTime();
	if (typeof targetHit!="undefined") {
		params += "&targetHit=" + targetHit;
	}
	
	// var urlStatURL = location.protocol + "//" + location.hostname + "/" +  phpTrackFileLocation + "/urlStat.php";
	
	//	var urlStatURL = "http://lab.soa-dna.net/MetricsDNA/ForeSeeServlet";
	
	//	sendTrackEvent(urlStatURL + "?" + params);
	
	var imageURL = "http://localhost:8080/WebStatsServer/images/track/frs.gif";
	imageURL += ("?" + params);
	
	var imgTag = document.createElement("img"); 
	imgTag.src=imageURL;
	
	document.body.appendChild(imgTag); 
	//document.body.innerHTML += (unescape("%3Cimg src='" + imageURL + "' %3E%3C/img%3E"));
	
	
	//IE
	if (document.attachEvent){
		document.attachEvent("onclick", winTracker);
	}
	// FF
	else if (window.addEventListener){
		window.addEventListener("click", winTracker, false);
	} 

}


//IE
if (window.attachEvent){
	window.attachEvent("onload", initLoad);
}
// FF
else if (window.addEventListener){
	window.addEventListener("load", initLoad, false);
} 

function winTracker(clickEvent) {
	var targetElem;

	// IE
	if (clickEvent.srcElement) {
		targetElem = clickEvent.srcElement;
	}
	// FF
	else if (clickEvent.target) {
		targetElem = clickEvent.target;
	}

	// Filter types of interest
	if(targetElem.getAttribute("class") != null && (targetElem.getAttribute("class").search("track") == (-1))) {
		return;
	}
	
//	alert("info: id=" + targetElem.getAttribute("id") + ":   name=" + targetElem.name + ":  value=" + targetElem.value);	
//	alert("href=" + targetElem.href+ ":  innerhtml=" + targetElem.innerHTML); 	

	var params = new String();
	params += "clientId=" + clientId;
	
	if (targetElem.id) {
		params += ("&id=" + targetElem.id);
	}

	if (targetElem.name) {
		params += ("&name=" + targetElem.name);
	}

	if (targetElem.value) {
		params += ("&value=" + targetElem.value);
	}

	if (targetElem.type) {
		params += ("&type=" + targetElem.type);
	}
	
	if (targetElem.innerHTML) {
		params += ("&innerHTML=" + targetElem.innerHTML);
	}

	params += ("&time=" + (new Date()).getTime());

	var imageURL = "http://localhost:8080/WebStatsServer/images/track/frs.gif";
	imageURL += ("?" + params);
	
	var imgTag = document.createElement("img"); 
	imgTag.src=imageURL;
	
	document.body.appendChild(imgTag); 

	
	
	// var clickStatURL = location.protocol + "//" + location.hostname + "/" +  phpTrackFileLocation + "/clickStat.php";
//	var clickStatURL = "http://lab.soa-dna.net/MetricsDNA/ForeSeeServlet";
	
	//sendTrackEvent( clickStatURL +  "?" + params);
}

function frsTrack() {
	var imageURL = "http://localhost:8080/WebStatsServer/images/track/frs.gif";
	document.write(unescape("%3Cimg src='" + imageURL + "' %3E%3C/img%3E"));
}


