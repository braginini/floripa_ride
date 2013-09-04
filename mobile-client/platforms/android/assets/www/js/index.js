$('#routesPage').bind('pageinit', function(event) {
	new RoutesPage().bindEvents();
	new ChoosePointPage().bindEvents();
});
