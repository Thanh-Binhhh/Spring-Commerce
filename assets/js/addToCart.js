let numOfPlantsInCart = document.getElementById('cart-count');;
let cartCount;
let cartItems;

document.addEventListener("DOMContentLoaded", function () {
    getCartCount(numOfPlantsInCart);
});

function getCartCount() {
    const storedCount = localStorage.getItem('cartCount');
    const storedItems = localStorage.getItem('cartItems');

    cartCount = storedCount ? Number(storedCount) : 0;
    cartItems = storedItems ? JSON.parse(storedItems) : {};
    numOfPlantsInCart.textContent = cartCount;
}

function addToCart(id, count) {
    cartCount += count;
    numOfPlantsInCart.textContent = cartCount;
    cartItems[id] = cartItems.hasOwnProperty(id) ? cartItems[id] + count : count;
}

function saveCart() {
    localStorage.setItem('cartCount', cartCount.toString());
    localStorage.setItem('cartItems', JSON.stringify(cartItems));
}