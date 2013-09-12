Ext.define('mobile-client-sencha.controller.ChoosePointController', {
	extend: 'Ext.app.Controller',

	views: [
		'mobile-client-sencha.view.Home',
		'mobile-client-sencha.view.Routes',
		'mobile-client-sencha.view.ChoosePoint'
	],

	config: {
		control: {
			'button[id=homeBtn1]': {
				tap: 'onTapHomeBtn'
			},

			'list[id=choosePointMenu]': {
				itemtap: function (list, idx, target, record, evt) {
					this.onTapChoosePointListMenuItem(idx);
				}
			}
		},

		refs: {
			homeView: {
				autoCreate: true,
				selector: '#homeView',
				xtype: 'Home'
			},

			routesView: {
				autoCreate: true,
				selector: '#routesView',
				xtype: 'Routes'
			},

			choosePointView: {
				autoCreate: true,
				selector: '#choosePointView',
				xtype: 'ChoosePoint'
			}
		}
	},

	onTapHomeBtn: function (button, e, eOpts) {
		Ext.Viewport.getLayout().setAnimation({
			type: 'slide',
			direction: 'right'
		});

		Ext.Viewport.setActiveItem(this.getHomeView());
	},

	onTapChoosePointListMenuItem: function (idx) {

		switch (idx) {
			case 0 :
				this.getCurrentPosition();
				break;
			default :
				break;
		}
	},

	getCurrentPosition: function () {

		document.addEventListener("deviceready", onDeviceReady, false);

		// Take picture using device camera and retrieve image as base64-encoded string
		navigator.geolocation.getCurrentPosition(

			function (position) {
				alert('Latitude: ' + position.coords.latitude + '<br />' +
					'Longitude: ' + position.coords.longitude + '<br />' +
					'Altitude: ' + position.coords.altitude + '<br />' +
					'Accuracy: ' + position.coords.accuracy + '<br />' +
					'Altitude Accuracy: ' + position.coords.altitudeAccuracy + '<br />' +
					'Heading: ' + position.coords.heading + '<br />' +
					'Speed: ' + position.coords.speed + '<br />' +
					'Timestamp: ' + position.timestamp + '<br />');

			}, function (error) {
				alert('code: ' + error.code + '\n' +
					'message: ' + error.message + '\n');
			});

		/*-------------- Helper Functions -------------- */
		function onDeviceReady () {
			alert('ready');
		}


	}
});