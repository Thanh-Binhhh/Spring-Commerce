let searchInput, priceRange, categoryRadios, sizeRadios, characteristicRadios;

document.addEventListener("DOMContentLoaded", function () {
    let currentPage = 0;
    searchInput = document.getElementById('searchInput');
    priceRange = document.getElementById('priceRange');
    categoryRadios = document.querySelectorAll('input[name="category"]');
    sizeRadios = document.querySelectorAll('input[name="tree-size"]');
    characteristicRadios = document.querySelectorAll('input[name="characteristics"]');

    fetchProducts(currentPage);
    fetchPricesForFilter();
    getCartCount();
    setupFilterListeners();
});


function fetchProducts(currentPage) {
    fetch(`http://localhost:8080/plants?currentPage=${currentPage}`)
        .then(async response => {
            if (!response.ok) {
                const errorText = await response.json();
                throw new Error(errorText || `HTTP error! status: ${response.status}`);
            }
            return response.json();
        })
        .then(products => {
            pagination(products.totalPages, products.pageable.pageNumber)
            showProducts(products.content);
            document.getElementById("products").scrollIntoView({ behavior: "smooth" });
        })
        .catch(error => {
            console.error("Error:", error);
        });
}


function showProducts(products) {
    const container = document.querySelector(".product__container");
    container.innerHTML = "";

    products.forEach(product => {
        const price = new Intl.NumberFormat(
            'vi-VN', {
            style: 'currency',
            currency: 'VND'
        }).format(product.price);

        const card = document.createElement("article");
        card.className = "product__card";
        card.innerHTML = `
            <div class="product__circle"></div>
            <img src="../assets/images/${product.image}" alt="${product.title}" class="product__img">
            <h3 class="product__title">${product.plant_name}</h3>
            <span class="product__price">${price}</span>
            <button class="button--flex product__button">
                <i class="ri-add-line"></i>
            </button>
        `;

        card.addEventListener('click', () => {
            window.location.href = `details.html?id=${product.id}`;
        });

        card.querySelector('.product__button')
            .addEventListener('click', (event) => {
                event.stopPropagation();
                addToCart(product.id, 1);
            });

        container.appendChild(card);
    });
}

function fetchPricesForFilter() {
    fetch(`http://localhost:8080/plants/price`)
        .then(async response => {
            if (!response.ok) {
                const errorText = await response.json();
                throw new Error(errorText || `HTTP error! status: ${response.status}`);
            }
            return response.json();
        })
        .then(prices => {
            showPrices(prices);
        })
        .catch(error => {
            console.error("Error:", error);
        });
}

function showPrices(prices) {
    const priceSlider = document.getElementById('priceRange');
    const priceValue = document.querySelector('.price__value');

    const MIN_PRICE = prices[0];
    const MAX_PRICE = prices[1];

    function updatePriceValue(value) {
        priceValue.textContent = `${MIN_PRICE.toLocaleString()}đ - ${parseInt(value).toLocaleString()}đ`;
    }

    priceSlider.min = MIN_PRICE;
    priceSlider.max = MAX_PRICE;
    priceSlider.value = MAX_PRICE;
    updatePriceValue(MAX_PRICE);

    priceSlider.addEventListener('input', function () {
        updatePriceValue(this.value);
    });
}

function pagination(totalPages, currentPage) {
    const paginationContainer = document.getElementById('pagination');
    paginationContainer.innerHTML = '';

    for (let i = 1; i <= totalPages; i++) {
        const btn = document.createElement('button');
        btn.textContent = i;
        btn.className = 'pagination__btn';
        if (i === currentPage + 1) btn.classList.add('active');

        btn.addEventListener('click', () => fetchProducts(i - 1));
        paginationContainer.appendChild(btn);
    }
}


function setupFilterListeners() {
    searchInput.addEventListener('input', debounce(filterPlants, 600));
    priceRange.addEventListener('input', filterPlants);
    categoryRadios.forEach(r => r.addEventListener('change', filterPlants));
    sizeRadios.forEach(r => r.addEventListener('change', filterPlants));
    characteristicRadios.forEach(r => r.addEventListener('change', filterPlants));
}

function debounce(func, delay) {
    let timeout;
    return function () {
        clearTimeout(timeout);
        timeout = setTimeout(func, delay);
    };
}

function getCheckedValue(radios) {
    const checked = [...radios].find(radio => radio.checked);
    return checked ? checked.labels[0].textContent.trim() : null;
}

function filterPlants() {
    const keyword = searchInput.value;
    const maxPrice = priceRange.value;
    const category = getCheckedValue(categoryRadios);
    const size = getCheckedValue(sizeRadios);
    const characteristic = getCheckedValue(characteristicRadios);

    const params = new URLSearchParams();

    if (keyword) params.append('name', keyword);
    if (category) params.append('category', category);
    if (size) params.append('size', size);
    if (characteristic) params.append('characteristic', characteristic);
    if (maxPrice) params.append('maxPrice', maxPrice);

    fetch(`http://localhost:8080/plants/filter?${params.toString()}`)
        .then(async response => {
            if (!response.ok) {
                const errorText = await response.text();
                throw new Error(errorText || `HTTP error! status: ${response.status}`);
            }
            if (response.status === 204) {
                return null;
            }
            return response.json();
        })
        .then(products => {
            if (!products || products.length === 0) {
                const container = document.querySelector(".product__container");
                container.innerHTML = `<h3 class="product__title">No products found</h3>`;
                return;
            }
            showProducts(products);
            document.getElementById("products").scrollIntoView({ behavior: "smooth" });
        })
        .catch(error => {
            console.error('Error when filtering: ', error);
        });
}

window.addEventListener('beforeunload', function (event) {
    saveCart();
});
