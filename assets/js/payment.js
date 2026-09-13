document.addEventListener("DOMContentLoaded", function () {
    console.log("DOM fully loaded and parsed");
    const total = new URLSearchParams(window.location.search).get('total');

    const steps = document.querySelectorAll(".checkout-step");
    const nextButtons = document.querySelectorAll(".next-step");
    let currentStep = 0;

    nextButtons.forEach(btn => {
        btn.addEventListener("click", function () {
            if (currentStep == 1) {
                console.log("Calling sendOrderEmail...");
                fetchProducts();
            }

            steps[currentStep].classList.remove("active");
            currentStep = Math.min(currentStep + 1, steps.length - 1);
            steps[currentStep].classList.add("active");
        });
    });

    // --- QR Code logic ---
    const qrCodeImage = document.getElementById("qr-code");
    qrCodeImage.src = `https://img.vietqr.io/image/VCB-0123456789-compact2.png?amount=${total}&addInfo=Spring+Commerce+Order`;
});

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
            sendOrderEmail(products, quantities);
        })
        .catch(error => {
            console.error("Error:", error);
        });
}

function sendOrderEmail(products, quantities) {
    const fullName = document.getElementById("fullName").value;
    const email = document.getElementById("email").value;
    const address = document.getElementById("address").value;
    const phone = document.getElementById("phone").value;
    console.log("EMAIL: ", email);
    console.log(fullName, email, address, phone);

    let orders = [];
    let subtotal = 0;

    for (let i = 0; i < products.length; i++) {
        const product = products[i];
        const quantity = quantities[i];
        const price = product.price * quantity;

        orders.push({
            name: product.plant_name,
            units: quantity,
            price: price.toLocaleString(),
        });
        subtotal += price;
    }

    const templateParams = {
        email: email,
        to_name: fullName,
        address: address,
        phone: phone,
        message: "Thank you for your order!",
        orders: orders,
        cost: {
            total: subtotal.toLocaleString()
        }
    };

    emailjs.send("service_2ym5x6m", "template_0bcpdmj", templateParams)
        .then(function (response) {
            console.log("SUCCESS!", response.status, response.text);
        }, function (error) {
            console.log("FAILED...", error);
            alert("Failed to send confirmation email.");
        });
}