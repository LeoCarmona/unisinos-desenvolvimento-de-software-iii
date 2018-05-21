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
	loadHeader();

	switch (app.getPage()) {
		case 'home':
			productService.createProductCardList(document.getElementById('product-list'));
			break;
	}
}

function loadHeader() {
	let session = this.session.data();

	if (session.token == null) {
		return;
	}

	let loginElement = document.getElementById('header.login');
	let logoutElement = htmlService.htmlToElement('<a href="index.html" onclick="session.logout()">Logout <small>(' + session.customer.email + ')</small></a>');

	loginElement.parentNode.insertBefore(logoutElement, loginElement.nextSibling);
	loginElement.remove();
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

var customerService = {

	register: function() {
		$.ajax({
			url: API_URL + '/customers',
			method: 'POST',
			data: JSON.stringify({
				email : document.getElementById('customer.register.email').value,
				name : document.getElementById('customer.register.name').value,
				password : document.getElementById('customer.register.password').value
			}),
			beforeSend: function (xhr) {
				xhr.setRequestHeader('Access-Control-Allow-Origin', '*');
				xhr.setRequestHeader('Content-Type', 'application/json');
			},
			success: function (data) {
				toastr.success('Cliente cadastrado com sucesso!');
			},
			error: function (error) {
				toastr.error('Ocorreu um erro ao cadastrar o cliente!');
			}
		});
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

	data: function() {
		let _session = localStorage.getItem('session');

		if (_session == null) {
			return session.clear();
		}

		return JSON.parse(_session);
	},

	login: function() {
		$.ajax({
			url: API_URL + '/login',
			method: 'POST',
			data: JSON.stringify({
				username : document.getElementById('customer.login.email').value,
				password : document.getElementById('customer.login.password').value
			}),
			beforeSend: function (xhr) {
				xhr.setRequestHeader('Access-Control-Allow-Origin', '*');
				xhr.setRequestHeader('Content-Type', 'application/json');
			},
			success: function (data) {
				localStorage.setItem('session', JSON.stringify({
					token : data.token,
					customer : data.customer
				}));

				$(".popup").toggleClass("active");
				loadHeader();

				toastr.success('Login realizado com sucesso!');
			},
			error: function (error) {
				if (error.status == 401) {
					toastr.error('O login ou senha estão incorretos!');
				} else {
					toastr.error('Ocorreu um erro ao ao se logar!');
				}
			}
		});
	},

	logout: function() {
		let _session = {
			token : null,
			customer : null
		};

		localStorage.setItem('session', JSON.stringify(_session));

		return _session;
	}

}