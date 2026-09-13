# 🌷 Bonsai Shop Frontend

Frontend application for the Bonsai Shop project. The application is built using HTML, CSS, and JavaScript and communicates with the Spring Boot backend through REST APIs.

## Running the frontend

This project is a static HTML/CSS/JavaScript application, so the easiest way to run it is using the **Live Server** extension in Visual Studio Code.

Base URL: `http://localhost:5500`

| Page          | URL                                  | Description                   |
| ------------- | ------------------------------------ | ----------------------------- |
| index.html    | Base URL                             | Home page                     |
| products.html | `/pages/products.html`               | Product listing and filtering |
| details.html  | `/pages/details.html?id={$id}`       | Product details               |
| cart.html     | `/pages/cart.html`                   | Shopping cart                 |
| checkout.html | `/pages/payment.html?total={$total}` | Checkout page                 |
| payment.html  | `/pages/payment.html?total={$total}` | Payment page                  |

## Project Structure

```
front-end/
│
├── assets/
│   ├── css/
│   │   ├── cart.css
│   │   ├── checkout.css
│   │   ├── details.css
│   │   ├── payment.css
│   │   ├── products.css
│   │   └── styles.css
│   │
│   ├── images/
│   └── js/
│       ├── addToCart.js
│       ├── cart.js
│       ├── details.js
│       ├── main.js
│       ├── payment.js
│       ├── products.js
│       └── scrollreveal.min.js
├── pages/
│   ├── cart.html
│   ├── checkout.html
│   ├── details.html
│   ├── payment.html
│   └── products.html
└── index.html
```
