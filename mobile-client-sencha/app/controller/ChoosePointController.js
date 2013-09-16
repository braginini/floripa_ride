Ext.define('mobile-client-sencha.controller.ChoosePointController', {
	extend: 'Ext.app.Controller',

	config: {

		views: [
			'mobile-client-sencha.view.HomeView',
			'mobile-client-sencha.view.RoutesView',
			'mobile-client-sencha.view.ChoosePointView'
		],

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
				xtype: 'HomeView'
			},

			routesView: {
				autoCreate: true,
				selector: '#routesView',
				xtype: 'RoutesView'
			},

			choosePointView: {
				autoCreate: true,
				selector: '#choosePointView',
				xtype: 'ChoosePointView'
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


		/*navigator.camera.getPicture( function (imageData) {
		   alert(imageData);

		}, function (error) {
			alert('code: ' + error.code + '\n' +
				'message: ' + error.message + '\n');
		});*/
		// Take picture using device camera and retrieve image as base64-encoded string


		/*-------------- Helper Functions -------------- */
		function onDeviceReady () {
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
		}


	}
});