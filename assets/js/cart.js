let total = 0;

document.addEventListener("DOMContentLoaded", function () {
    fetchProducts();
})

function fetchCart() {
    const storedCount = localStorage.getItem('cartCount');

    if (Number(storedCount) > 0) {
        const storedItems = JSON.parse(localStorage.getItem('cartItems'));
        const ids = Object.keys(storedItems).map(Number);
        const quantities = Object.values(storedItems).map(Number);
        return {ids, quantities};
    }
}

function fetchProducts() {
    const {ids, quantities} = fetchCart();

    if (!ids || ids.length === 0) {
        console.warn("No products in cart.");
        return;
    }

    const queryParams = ids.map(id => `ids=${id}`).join('&');

    fetch(`http://localhost:8080/plants/ids?${queryParams}`)
        .then(async response => {
            if (!response.ok) {
                const errorText = await response.json();
                throw new Error(errorText || `HTTP error! status: ${response.status}`);
            }
            return response.json();
        })
        .then(products => {
            showProducts(products, quantities);
        })
        .catch(error => {
            console.error("Error:", error);
        });
}

function showProducts(products, quantities) {
    const tbody = document.getElementById("cart-items-body");
    tbody.innerHTML = "";
    total = 0;

    const price = new Intl.NumberFormat(
        'vi-VN', {
            style: 'currency',
            currency: 'VND'
        });

    products.forEach((product, index) => {
        const tr = document.createElement("tr");
        tr.className = "cart__item";

        let totalPriceAPlant = product.price * quantities[index];

        tr.innerHTML = `
            <td class="cart__product">
                <img class="cart__img" src="../assets/images/${product.image}" alt="${product.plant_name}">
                <span>${product.plant_name}</span>
            </td>
            <td class="cart__quantity">
                <button class="qty-btn" onclick="updateQty(${product.id}, -1)">−</button>
                <span class="qty-value">${quantities[index]}</span>
                <button class="qty-btn" onclick="updateQty(${product.id}, 1)">+</button>
            </td>
            <td>${price.format(product.price)}</td>
            <td>${price.format(totalPriceAPlant)}</td>
            <td><button class="cart__remove" data-id="${product.id}"><i class="ri-close-line"></i></button></td>
        `;

        tbody.appendChild(tr);
        total += totalPriceAPlant;
    });

    document.getElementById('cart__total').querySelector('h3').innerText = price.format(total);


}

// Delete a product
let deleteTargetId = null;

document.addEventListener("click", function (e) {
    if (e.target.closest(".cart__remove")) {
        deleteTargetId = e.target.closest(".cart__remove").getAttribute("data-id");
        document.getElementById("delete-confirm-modal").style.display = "flex";
    }

    if (e.target.id === "confirm-delete-btn") {
        if (deleteTargetId !== null) {
            removeFromCart(Number(deleteTargetId));
            deleteTargetId = null;
            document.getElementById("delete-confirm-modal").style.display = "none";
        }
    }

    if (e.target.id === "cancel-delete-btn") {
        deleteTargetId = null;
        document.getElementById("delete-confirm-modal").style.display = "none";
    }
});

function removeFromCart(id) {
    const storedItems = JSON.parse(localStorage.getItem('cartItems') || '{}');
    delete storedItems[id];
    localStorage.setItem('cartItems', JSON.stringify(storedItems));
    localStorage.setItem('cartCount', Object.keys(storedItems).length);
    fetchProducts();
}

function updateQty(id, change) {
    const cartItems = JSON.parse(localStorage.getItem('cartItems') || '{}');
    if (cartItems[id] !== undefined) {
        cartItems[id] += change;
        if (cartItems[id] <= 0) {
            delete cartItems[id];
        }
        localStorage.setItem('cartItems', JSON.stringify(cartItems));
        localStorage.setItem('cartCount', Object.keys(cartItems).length);
        fetchProducts();
    }
}

document.querySelector(".checkout-td").addEventListener("click", function (e) {
    window.location.href = `payment.html?total=${total}`;
});