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
		case 'cart':
			basketService.createBasketList();
			break;
	}
}

function loadHeader() {
	let session = this.session.data();

	if (session.token == null) {
		return;
	}
	
	console.log(app.getPage());

	switch (app.getPage()) {
		case 'home':
			let loginElement = document.getElementById('header.login');
			let logoutElement = htmlService.htmlToElement('<a href="index.html" onclick="session.logout()">Logout <small>(' + session.customer.email + ')</small></a>');

			loginElement.parentNode.insertBefore(logoutElement, loginElement.nextSibling);
			loginElement.remove();

			break;
		case 'cart':
			document.getElementById('logout.email').textContent = '(' + session.customer.email + ')';
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

var htmlService = {

	htmlToElement: function (html) {
		let template = document.createElement('template');

		html = html.trim();
		template.innerHTML = html;

		return template.content.firstChild;
	}

};

var customerService = {

	register: function () {
		$.ajax({
			url: API_URL + '/customers',
			method: 'POST',
			data: JSON.stringify({
				email: document.getElementById('customer.register.email').value,
				name: document.getElementById('customer.register.name').value,
				password: document.getElementById('customer.register.password').value
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

var basketService = {

	add: function (id, quantity) {
		let _session = session.data();

		$.ajax({
			url: API_URL + "/basket/product/",
			method: 'POST',
			data: JSON.stringify({
				customerId: _session.customer.id,
				productId: id,
				quantity: quantity
			}),
			beforeSend: function (xhr) {
				xhr.setRequestHeader('Access-Control-Allow-Origin', '*');
				xhr.setRequestHeader('Content-Type', 'application/json');
				xhr.setRequestHeader('Authorization', _session.token);
			},
			success: function (data) {
				toastr.success('Produto adicionado com sucesso!');
			},
			error: function (error) {
				toastr.error('Ocorreu um erro ao adicionar o produto!');
			}
		});
	},

	products: function () {
		let _session = session.data();

		$.ajax({
			url: API_URL + "/basket/",
			method: 'GET',
			beforeSend: function (xhr) {
				xhr.setRequestHeader('Access-Control-Allow-Origin', '*');
				xhr.setRequestHeader('Content-Type', 'application/json');
				xhr.setRequestHeader('Authorization', _session.token);
			},
			success: function (data) {
				console.log(data);
			},
			error: function (error) {
				toastr.error('Ocorreu um erro ao adicionar o produto!');
			}
		});
	},

	createBasketList: function () {
		let _session = session.data();

		$.ajax({
			url: API_URL + '/basket',
			method: 'GET',
			beforeSend: function (xhr) {
				xhr.setRequestHeader('Access-Control-Allow-Origin', '*');
				xhr.setRequestHeader('Content-Type', 'application/json');
				xhr.setRequestHeader('Authorization', _session.token);
			},
			success: function (data) {
				let parentElement = document.getElementById('product-list');
				let products = data.products;

				for (var i = 0; i < products.length; i++) {
					parentElement.append(htmlService.htmlToElement(
						'<tr>' +
						'	<td class="product-name">' +
						'		<div class="product-thumbnail">' +
						'			<img src="' + products[i].image + '" height="134" width="118" alt="">' +
						'		</div>' +
						'		<div class="product-detail">' +
						'			<h3 class="product-title">' + products[i].title + '</h3>' +
						'			<p>' + products[i].description + '</p>' +
						'		</div>' +
						'	</td>' +
						'	<td class="product-price">R$ ' + products[i].price.toFixed(2) + '</td>' +
						'	<td class="product-qty">' +
						'		<select name="#" onchange="basketService.add(' + products[i].id +', this.selectedIndex + 1);location.reload();">' +
						'			<option value="1"' + (products[i].quantity == 1 ? ' selected' : '') + '>1</option>' +
						'			<option value="2"' + (products[i].quantity == 2 ? ' selected' : '') + '>2</option>' +
						'			<option value="3"' + (products[i].quantity == 3 ? ' selected' : '') + '>3</option>' +
						'		</select>' +
						'	</td>' +
						'	<td class="product-total">R$ ' + products[i].total.toFixed(2) + '</td>' +
						'	<td class="action">' +
						'		<a href="#" onclick="basketService.add(' + products[i].id +', 0);location.reload();">' +
						'			<i class="fa fa-times"></i>' +
						'		</a>' +
						'	</td>' +
						'</tr>'
					))
				};

				document.getElementById('basket.total').textContent = 'R$ ' + data.total.toFixed(2);
			},
			error: function (error) {}
		});
	}

}

var app = {

	getPage: function () {
		let page = document.getElementById('app.page').getAttribute('content');

		return page ? page : '';
	}

};

// ==================================================
// Session
// ==================================================

var session = {

	data: function () {
		let _session = localStorage.getItem('session');

		if (_session == null) {
			return session.clear();
		}

		return JSON.parse(_session);
	},

	login: function () {
		$.ajax({
			url: API_URL + '/login',
			method: 'POST',
			data: JSON.stringify({
				username: document.getElementById('customer.login.email').value,
				password: document.getElementById('customer.login.password').value
			}),
			beforeSend: function (xhr) {
				xhr.setRequestHeader('Access-Control-Allow-Origin', '*');
				xhr.setRequestHeader('Content-Type', 'application/json');
			},
			success: function (data) {
				localStorage.setItem('session', JSON.stringify({
					token: data.token,
					customer: data.customer,
					itensQuantity: data.itensQuantity
				}));
				location.reload();
				loadHeader();
				// $(".popup").toggleClass("active");
				// toastr.success('Login realizado com sucesso!');
			},
			error: function (error) {
				// if (error.status == 401) {
				// 	toastr.error('O login ou senha estão incorretos!');
				// } else {
				// 	toastr.error('Ocorreu um erro ao ao se logar!');
				// }
			}
		});
	},

	logout: function () {
		let _session = {
			token: null,
			customer: null,
			itensQuantity: 0
		};

		localStorage.setItem('session', JSON.stringify(_session));

		return _session;
	}

}