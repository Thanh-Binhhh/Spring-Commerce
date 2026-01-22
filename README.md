# 🌷 Spring Commerce

## Bonsai Shop

This is a simple online plant shopping application designed to showcase and sell plant products such as indoor plants, outdoor plants, etc. Here, users can browse the displayed products, add them to the cart, and place an order if they want.

The application is based on a Minimum Viable Product (MVP) version with very few functions.

## Features

- The application displays all the products that the company sells.
- The website suggests products related to the keywords entered by the user.
- Customers can filter products by different criteria such as name, price, category, size, features.
- If customers find a product they like, they can view detailed information about that product and add it to the cart.
- Customers can order and pay online or pay COD. They are required to provide shipping information and pay cash when the product is delivered to their home.

## Software Development Principles

- The application follows SOLID principles to ensure a robust and maintainable design, even in its MVP version.
- Consistent RESTful architecture is applied to build a clean, scalable, and easy-to-integrate backend system.

## Software Development Patterns

The application is built with two distinct components:
- Front end: .html, .css, and .js files to display product information and handle user requests.
- Back end: Controllers, services, repositories, and models folders
   - _Controller_: Contains classes that handle incoming requests and direct them to the appropriate processing methods, and return the corresponding responses.
   - _Model_: Contains classes that map to the database, representing the data structure.
   - _Repository_: Contains classes that query the database directly to fetch or modify data.
   - _Service_: Contains the core logic of the application, handling essential operations and business logic.

## Running The Application Locally
First, run by command line in the Midterm/SpringCommerce root directory:
```sh
docker-compose up -d
```

Second, open the _pom.xml_ file, select _Reload Project_ to make sure all dependencies are installed properly.

Third, you can launch the application in two ways:
- Run directly from IDE: If you are using IDE, you can run the Spring Boot application by clicking the run button in the IDE.

- By command line:
```sh
mvn spring-boot:run
```

Finally, run the front end directly from the WebStorm IDE by clicking the run button in the IDE.

## Security

The application uses Spring Security to ensure the security of RESTful Web Service, while still allowing public access to some necessary resources.

- Security Configuration
    Use SecurityFilterChain (modern Spring Security) instead of the old WebSecurityConfigurerAdapter.
    Enable CORS to allow frontend access from a specific domain.
    Specify public endpoints that do not require authentication:
        /plants/**
        /carts/**
        /categories/**, 
        /sizes/**, 
        /characteristics/**

        ```sh
        .authorizeHttpRequests(auth -> auth
                    .requestMatchers(   // Allow free access without login.
                            "/plants/**",
                            "/carts/**",
                            "/categories",
                            "/sizes",
                            "/characteristics")
                    .permitAll()
        ```

    Other endpoints are completely denied during the MVP phase to limit the access area.

    ```sh
    .anyRequest().denyAll()
    ```

- CORS Configuration
    Allow the frontend running at http://localhost:63343 to call the backend.
    Support the main HTTP methods: GET, POST, PUT, DELETE.
    Allow sending cookies (if any) between the frontend and backend.

    ```
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOrigins("http://localhost:63343")
                .allowedMethods("GET", "POST", "PUT", "DELETE")
                .allowedHeaders("*")
                .allowCredentials(true);
    }
    ```

## Postman APIs Testing

Base URL: http://localhost:8080/

| Method | Endpoind |  |
|------|------|------|
| GET    | `/plants?currentPage={$currentPage}` | Get all products (with pagination) |
| GET    | `/plants?id={$id}`    | Get a product details    |
| GET    | `plants/ids`    | Get products by id list    |
| GET    | `plants/price`    | Get the minimum and maximum price of the product    |
| GET    | `plants/filter`    | Filter product(s) by criteria(s)    |
| GET    | `/carts`    | Get cart information    |

| POST    | `/carts` | Create a cart if none exists; otherwise add the product or increase its quantity. |
| DELETE    | `/carts`    | Reduce product quantity or remove product from cart    |
| GET    | `/categories`    | Get list of categories    |
| GET    | `/sizes`    | Get list of sizes of plant    |
| GET    | `/characteristics`    | Get list of characteristics    |

## Video for demonstration

[Watch the video on Drive](https://drive.google.com/file/d/1F1NLHo_VKGfGcyEk_5sjracsgvTuwefV/view?usp=sharing)
