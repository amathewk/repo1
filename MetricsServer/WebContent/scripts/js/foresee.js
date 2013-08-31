var xmlhttp;
var debug = false;
var clientId="10001";
var phpTrackFileLocation="WebStats";
var imageURL = "http://w3.soa-dna.com/metrics/foresee";
var urlStatURL = "http://w3.soa-dna.com/metrics/foresee";
var clickStatURL = "http://w3.soa-dna.com/metrics/foresee";

function sendTrackEvent(url)
{
	if(debug)
		alert("sending url: " + url);	
	xmlhttp = null;
	if (window.XMLHttpRequest)
	{// code for all new browsers
		xmlhttp = new XMLHttpRequest();
	}
	else if (window.ActiveXObject)
	{// code for IE5 and IE6
		xmlhttp = new ActiveXObject("Microsoft.XMLHTTP");
	}
	if (xmlhttp!=null)
	{
		xmlhttp.onreadystatechange = state_Change;

		xmlhttp.open("GET",url,true);

		xmlhttp.send(null);
	}
	else
	{
	// TODO mask with error handling. write to remote file on client

	//  alert("Your browser does not support XMLHTTP.");
	}
}


function state_Change()
{
	if(debug)
	alert("state change");

	if (xmlhttp.readyState == 4)
	{// 4 = "loaded"
		if (xmlhttp.status == 200)
		{// 200 = OK
			// ...our code here...
			//    alert("worked");
		}
		else
		{
			// TODO mask with error handling. write to remote file on client
			// alert("Problem retrieving XML data");
		}
	}
}

function initLoad() 
{
	if(debug)
		alert("URL:" + window.location);
	// TODO: send the above URL in an ajax call
	var params = "clientId=" + clientId + "&url=" + escape(window.location) + "&time=" + new Date().getTime() + "&type=url" + "&id=" + escape(window.location);

	if (typeof targetHit!="undefined") 
	{
		params += "&targetHit=" + targetHit;
	}
		
	imageURL += ("?" + params);
	
	var imgTag = document.createElement("img"); 
	
	imgTag.src=imageURL;
	
	document.body.appendChild(imgTag); 
	
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
	if(debug)
		alert("window - attach Event");
	window.attachEvent("onload", initLoad);
}

// FF

else if (window.addEventListener){

	window.addEventListener("load", initLoad, false);

} 

function winTracker(clickEvent) {
	if(debug)
		alert("winTracker");
	
	var targetElem;

	if (clickEvent.srcElement) {
		targetElem = clickEvent.srcElement;
	}
	else if (clickEvent.target) {
		targetElem = clickEvent.target;
	}

	// Filter types of interest
	if(targetElem.getAttribute("class") != null && (targetElem.getAttribute("class").search("track") == (-1))) {
		return;
	}

	var params = new String();

	params += "clientId=" + clientId;

	if (targetElem.id) {
		params += ("&id=" + targetElem.id);
	}

	if (targetElem.name) {
		params += ("&name=" + escape(targetElem.name));
	}

	if (targetElem.value) {
		params += ("&value=" + escape(targetElem.value));
	}

	if (targetElem.type) {
		params += ("&type=" + targetElem.type);
	}

	params += ("&time=" + (new Date()).getTime());

	params += ("&url=" + escape(window.location));

	imageURL += ("?" + params);
	
	var imgTag = document.createElement("img"); 
	
	imgTag.src=imageURL;
	
    var imgURL = document.getElementById("frs_img");

	imgURL.innerHTML = "";
	imgURL.appendChild(imgTag); 

}





