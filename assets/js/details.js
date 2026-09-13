document.addEventListener("DOMContentLoaded", function () {
    const id = new URLSearchParams(window.location.search).get('id');

    if (id) {
        fetchProduct(id)
        getCartCount()

        document.querySelector('.add-to-cart').addEventListener('click', () => {
            addDetailsToCart(id);
        });
    }
});


function fetchProduct(id) {
    fetch(`http://localhost:8080/plants?id=${id}`)
        .then(async response => {
            if (!response.ok) {
                const errorText = await response.json();
                throw new Error(errorText || `HTTP error! status: ${response.status}`);
            }
            return response.json();
        })
        .then(product => {
            showProducts(product);
            controlQuantity();
        })
        .catch(error => {
            console.error("Error:", error);
        })
}


function showProducts(product) {
    const productImage = document.querySelector('.product-detail__main-img');
    const productTitle = document.querySelector('.product-detail__title');
    const productPrice = document.querySelector('.product-detail__price');
    const productDesc = document.querySelectorAll('.product-detail__desc');
    const items = document.querySelectorAll('.product-detail__param .product-detail__meta');

    const price = new Intl.NumberFormat(
                    'vi-VN', {
                            style: 'currency',
                            currency: 'VND'
                        }).format(product.price);

    productImage.src = `../assets/images/${product.image}`;
    productTitle.textContent = product.plant_name;
    productPrice.textContent = price;
    productDesc[0].textContent = product.description;
    productDesc[1].textContent = product.description + ' ' + product.description + ' ' + product.description;
    items[0].innerHTML = `<strong>Size:</strong> ${product.plant_size}`;
    items[1].innerHTML = `<strong>Category:</strong> ${product.category}`;
    items[2].innerHTML = `<strong>Characteristic:</strong> ${product.characteristic}`;
}


function controlQuantity() {
    const quantityControl = {
        value: document.querySelector('.qty-value'),
        decreaseBtn: document.querySelector('.qty-btn:first-child'),
        increaseBtn: document.querySelector('.qty-btn:last-child'),
        quantity: 1
    };

    quantityControl.decreaseBtn.addEventListener('click', () => {
        if (quantityControl.quantity > 1) {
            quantityControl.quantity--;
            quantityControl.value.textContent = quantityControl.quantity;
        }
    });

    quantityControl.increaseBtn.addEventListener('click', () => {
        quantityControl.quantity++;
        quantityControl.value.textContent = quantityControl.quantity;
    });
}


function addDetailsToCart(id) {
    const quantityValue = document.querySelector('.qty-value');
    addToCart(id, Number(quantityValue.textContent))
}


window.addEventListener('beforeunload', function (event) {
    event.preventDefault();
    saveCart();
});