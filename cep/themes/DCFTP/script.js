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
  if (document.getElementById('latitude')) {
    document.getElementById('latitude').value = latLng.lat();
    document.getElementById('longitude').value = latLng.lng();
  }
}

function findAddress() {
  if (document.getElementById('addressline1')) {
    document.getElementById('addressline1').value = address.streetNo + " " + address.streetName;
    document.getElementById('city').value = address.city;
    document.getElementById('county').value = address.county;
    document.getElementById('postcode').value = address.postcode;
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
    if (document.getElementById('location')) {
      document.getElementById('location').value = info.formatted_address;
    }
  }
}

function addClickListener(markers, marker, id) {
  google.maps.event.addListener(marker, 'click', function(pos) {
    for (var i = markers.length - 1; i >= 0; i--) {
      if (markers[i] == marker) {
        markers[i].setAnimation(google.maps.Animation.BOUNCE);
      } else {
        markers[i].setAnimation(null);
      }
    };
    var entries = document.getElementById('entries').childNodes;
    for (var i = entries.length - 1; i >= 0; i--) {
      var entry = entries.item(i);
      if (!entry.id) continue;
      if (entry.id.substr(5) == id) {
        entry.className = 'entry';
      } else {
        entry.className = 'entry-hidden';
      }
    }
  });
}

function findDistance(locA, locB)
{
  var rad = Math.PI / 180;
  var R = 6371009;
  var dLat = (locB.lat()-locA.lat()) * rad;
  var dLon = (locB.lng()-locA.lng()) * rad;
  var lat1 = locA.lat() * rad;
  var lat2 = locB.lat() * rad;

  var a = Math.sin(dLat/2) * Math.sin(dLat/2) +
          Math.sin(dLon/2) * Math.sin(dLon/2) * Math.cos(lat1) * Math.cos(lat2); 
  var c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1-a)); 
  return R * c;
}

function moveToLocation() {
  var locName = document.getElementById('location').value;
  geocoder.geocode({
    address: locName
  }, function(responses) {
    if (responses && responses.length > 0) {
      var loc = responses[0].geometry.location;
      _map.setCenter(loc);
      if (document.getElementById('entries')) {
        var entries = document.getElementById('entries').childNodes;
        for (var i = entries.length - 1; i >= 0; i--) {
          var entry = entries.item(i);
          if (!entry.id) continue;
          var locNode = document.getElementById('loc' + entry.id.substr(5));
          var distDiv = document.getElementById('dist' + entry.id.substr(5));
          var locParts = locNode.value.split(",");
          var otherLoc = new google.maps.LatLng(parseFloat(locParts[0]), parseFloat(locParts[1]));
          distDiv.className = 'div';
          var dist = Math.round(findDistance(loc, otherLoc));
          distDiv.innerHTML = 'Distance from ' + locName + ': ' + dist + 'm';
        }
      }
    } else {
      // some error here
    }
  });
}

var _map;
function initialize() {
  var latLng = new google.maps.LatLng(54.776842,-1.575454);
  _map = new google.maps.Map(document.getElementById('mapCanvas'), {
    zoom: 16,
    center: latLng,
    mapTypeId: google.maps.MapTypeId.ROADMAP
  });

  if (document.getElementById('hidden_locations')) {
    var entries = document.getElementById('entries').childNodes;
    for (var i = entries.length - 1; i >= 0; i--) {
      var entry = entries.item(i);
      if (!entry.id) continue;
      entry.className = 'entry-hidden';
    }
    var locations = document.getElementById('hidden_locations').childNodes;
    var markers = new Array();
    for (var i = locations.length - 1; i >= 0; i--) {
      var item = locations.item(i);
      if (!item.id) continue;
      var id = item.id.substr(3);
      var loc = item.value.split(",");
      var marker = new google.maps.Marker({
        position: new google.maps.LatLng(parseFloat(loc[0]), parseFloat(loc[1])),
        title: loc[2],
        map: _map,
        draggable: false
      });
      markers.push(marker);
      addClickListener(markers, marker, id);
    };
  } else {
    var marker = new google.maps.Marker({
      position: latLng,
      title: 'New Retailer',
      map: _map,
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
}

// Onload handler to fire off the app.
google.maps.event.addDomListener(window, 'load', initialize);
