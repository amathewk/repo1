<!DOCTYPE html>
<html>
<head>
<meta http-equiv="content-type" content="text/html; charset=UTF-8" />
</head>
<body style="font: 75% Lucida Grande, Trebuchet MS; margin: 0">
<div id="content" style="height: 450px"></div>
<script>
    if (navigator.geolocation) {
        navigator.geolocation.getCurrentPosition(showPosition, onError);
        // also monitor position as it changes
      // navigator.geolocation.watchPosition(showPosition);
      } else {
        //onError();
      }

      function showPosition(position) {
          var lat = position.coords.latitude;
          var lng = position.coords.longitude;
          console.log(lat)
          console.log(lng)
          window.location = "${grailsApplication.config.grails.serverURL}/entry/searchM?lat=" + lat + "&lng=" + lng + "&max=3";	  	      
          return;
        }
	
      function onError() {
        if (navigator.geolocation) {
          initialLocation = newyork;
          contentString = "Error: The Geolocation service failed.";
        } else {
          initialLocation = siberia;
          contentString = "Error: Your browser doesn't support geolocation.";
        }
        mapOptions.zoom = 4;
        map = new google.maps.Map(document.getElementById("content"), mapOptions);
        map.setCenter(initialLocation);
        infowindow.setContent(contentString);
        infowindow.setPosition(initialLocation);
        infowindow.open(map);
      }
    </script>
</html>