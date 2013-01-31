var geocoder = new google.maps.Geocoder();
var address = {
  streetNo : "",
  streetName : "",
  city : "",
  county : "",
  postcode : ""
};

function geocodePosition(pos) {
  geocoder.geocode({
    latLng: pos
  }, function(responses) {
    if (responses && responses.length > 0) {
      updateMarkerAddress(responses[0]);
    } else {
      updateMarkerAddress('Cannot determine address at this location.');
    }
  });
}

function updateMarkerStatus(str) {
  //document.getElementById('markerStatus').innerHTML = str;
}

function updateMarkerPosition(latLng) {
  document.getElementById('latitude').value = latLng.lat();
  document.getElementById('longitude').value = latLng.lng();
}

function findAddress() {
  if (document.getElementById('addressline1')) {
    document.getElementById('addressline1').value = address.streetNo + " " + address.streetName;
    document.getElementById('city').value = address.city;
    document.getElementById('county').value = address.county;
    document.getElementById('postcode').value = address.postcode;
  } else {
    document.getElementById('location').value = address.
  }
}

function updateMarkerAddress(info) {
  if (info.address_components) {
    for (var i = info.address_components.length - 1; i >= 0; i--) {
      var comp = info.address_components[i];
      if (comp.types.indexOf("street_number") > -1) {
        address.streetNo = comp.long_name;
      } else if (comp.types.indexOf("route") > -1) {
        address.streetName = comp.long_name;
      } else if (comp.types.indexOf("postal_town") > -1) {
        address.city = comp.long_name;
      } else if (comp.types.indexOf("administrative_area_level_2") > -1) {
        address.county = comp.long_name;
      } else if (comp.types.indexOf("postal_code") > -1) {
        address.postcode = comp.long_name;
      }
    };
    //document.getElementById('address').innerHTML = str;
  }
}

function initialize() {
  var latLng = new google.maps.LatLng(54.776842,-1.575454);
  var map = new google.maps.Map(document.getElementById('mapCanvas'), {
    zoom: 16,
    center: latLng,
    mapTypeId: google.maps.MapTypeId.ROADMAP
  });
  var marker = new google.maps.Marker({
    position: latLng,
    title: 'New Retailer',
    map: map,
    draggable: true
  });
  
  // Update current position info.
  updateMarkerPosition(latLng);
  geocodePosition(latLng);
  
  // Add dragging event listeners.
  google.maps.event.addListener(marker, 'dragstart', function() {
    updateMarkerAddress('Dragging...');
  });
  
  google.maps.event.addListener(marker, 'drag', function() {
    updateMarkerStatus('Dragging...');
    updateMarkerPosition(marker.getPosition());
  });
  
  google.maps.event.addListener(marker, 'dragend', function() {
    updateMarkerStatus('Drag ended');
    geocodePosition(marker.getPosition());
  });
}

// Onload handler to fire off the app.
google.maps.event.addDomListener(window, 'load', initialize);
