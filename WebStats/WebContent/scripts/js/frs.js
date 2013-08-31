function sendEvent(params)
{

 var imageURL = "http://w3.metrics-dna.com/metrics/foresee";
 imageURL += ("?" + params);

 var imgTag = document.createElement("img");
 imgTag.src=imageURL;

 var imgURLElement = document.getElementById("frs_img");

 imgURLElement.innerHTML="";
 imgURLElement.appendChild(imgTag);

}

function initLoad() {

 var params="url=" + escape(window.location) + "&type=url";

 sendEvent(params);

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

 if(targetElem.getAttribute("class") == null || (targetElem.getAttribute("class").search("track") == (-1))) {
 return;
 }


 var params = new String();

 params = "type=click";

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
 params += ("&elemType=" + targetElem.type);
 }
	
	
 // if (targetElem.innerHTML) {
 // params += ("&innerHTML=" + targetElem.innerHTML);
 // }
	
 params += ("&url=" + escape(window.location));
	
 sendEvent(params);
	
 }
	
// Copyright ©2009, SOA-DNA Inc. All rights reserved.
// No part of this script may be used or reproduced in any form or by any means, or stored in a database or retrieval system without prior written permission of SOA-DNA Incorporated.
// Making copies of any part of this script for any purpose is a violation of United States copyright laws.
	