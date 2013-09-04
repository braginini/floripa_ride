function Page() {};

Page.prototype.bindEvents = function() {};

function RoutesPage() {};

RoutesPage.prototype = new Page;

RoutesPage.prototype.bindEvents = function() {

	Page.prototype.bindEvents.call(this);

	$( "#textinput-a" ).add("#textinput-b").bind( "tap", function(event, ui) {
		$.mobile.navigate( "#choosePointPage" );
	});
};

function ChoosePointPage() {};

ChoosePointPage.prototype = new Page;

ChoosePointPage.prototype.bindEvents = function() {

	Page.prototype.bindEvents.call(this);

	$("#myPosition" ).bind( "tap", function(event, ui) {
		navigator.geolocation.getCurrentPosition(function geolocationSuccess(position) {
				alert('Latitude: '          + position.coords.latitude          + '\n' +
					'Longitude: '         + position.coords.longitude         + '\n' +
					'Altitude: '          + position.coords.altitude          + '\n' +
					'Accuracy: '          + position.coords.accuracy          + '\n' +
					'Altitude Accuracy: ' + position.coords.altitudeAccuracy  + '\n' +
					'Heading: '           + position.coords.heading           + '\n' +
					'Speed: '             + position.coords.speed             + '\n' +
					'Timestamp: '         + position.timestamp                + '\n');
			},
			function(error) {
				alert(error);
			});
	});
};
