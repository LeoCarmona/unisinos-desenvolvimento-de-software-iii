(function ($, document, window) {

	$(document).ready(function () {

		// Cloning main navigation for mobile menu
		$(".mobile-navigation").append($(".main-navigation .menu").clone());

		// Mobile menu toggle 
		$(".toggle-menu").click(function () {
			$(".mobile-navigation").slideToggle();
		});

		$(".home-slider").flexslider({
			controlNav: true,
			directionNav: false
		});

		$(".login-button").on("click", function () {
			$(".overlay").fadeIn();
			$(".auth-popup").toggleClass("active");
		});

		$(".close, .overlay").on("click", function () {
			$(".overlay").fadeOut();
			$(".popup").toggleClass("active");
		});

		initLightbox({
			selector: '.product-images a',
			overlay: true,
			closeButton: true,
			arrow: true
		});


		$(document).keyup(function (e) {
			if ($(".popup").hasClass("active")) {
				if (e.keyCode === 27) {
					$(".overlay").fadeOut();
					$(".popup").toggleClass("active");
				}
			}
		});

		start();

	});

	$(window).load(function () {

	});

})(jQuery, document, window);

function start() {
	switch (app.getPage()) {
		case 'home':
			productService.createProductCardList(document.getElementById('product-list'));
			break;
	}
}

// ==================================================
// Constants
// ==================================================

var API_URL = "http://localhost:8080";

// ==================================================
// Http request utils
// ==================================================

var http = {
	createRequest: function (method, url, data, successCallback, errorCallback) {
		$.ajax({
			url: url,
			method: method,
			data: data,
			beforeSend: function (xhr) {
				xhr.setRequestHeader('Access-Control-Allow-Origin', '*');
				xhr.setRequestHeader('Content-Type', 'application/json');
			},
			success: function (data) {
				if (typeof successCallback == "function") {
					successCallback(data);
				}
			},
			error: function (error) {
				if (typeof errorCallback == "function") {
					errorCallback(error);
				}
			}
		});
	}
}

// ==================================================
// Clients
// ==================================================

var productClient = {

	findAll: function (successCallback, errorCallback) {
		http.createRequest('GET', API_URL + '/products', {}, successCallback, errorCallback);
	}

};

// ==================================================
// Services
// ==================================================

var productService = {

	createProductCardList: function (parentElement) {
		productClient.findAll(function (data) {
			for (var i = 0; i < data.length; i++) {
				parentElement.append(htmlService.htmlToElement(
					'<div class="product">' +
					'	<div class="inner-product">' +
					'		<div class="figure-image">' +
					'			<a href="single.html"><img src="' + data[i].image + '" alt="Game 1"></a>' +
					'		</div>' +
					'		<h3 class="product-title"><a href="#">' + data[i].title + '</a></h3>' +
					'		<small class="price">R$ ' + data[i].price.toFixed(2) + '</small>' +
					'		<p>' + data[i].description + '</p>' +
					'		<center><a href="cart.html" class="button">Adicionar ao Carrinho</a></center>' +
					'	</div>' +
					'</div>'
				))
			};
		});
	}

};

var htmlService = {

	htmlToElement: function (html) {
		let template = document.createElement('template');

		html = html.trim();
		template.innerHTML = html;

		return template.content.firstChild;
	}

};

var loginService = {

	isLogged: function() {

	}

}

var app = {

	getPage: function () {
		let page = document.head.querySelector("meta[app=page]");

		return page ? page.content : '';
	}

};

// ==================================================
// Session
// ==================================================

var session = {

	token: null,
	expiresIn: null,

	isLogged: function() {
		return false;
	}

}