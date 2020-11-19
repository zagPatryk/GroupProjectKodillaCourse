# 0. Start

Simple team project to learn Kanban board. Review other people's codes using a collaborative project on github.

This project is an implementation of a simple BackEnd logic for an ecommerce store. It is implemented in a REST architecture, and it is made of 5 entities (product, group, user, cart, order) coupled together with SQL-style relationships and business logic.

Main: Java, Spring, SpringBoot, Hibernate | 
Additional: SQL | 
For testing: JUnit4 | 
App builder: Gradle | 
Version control: Git | 
DataBases: MySQL, additionally H2 in group project | 
Additionally used: lombok, swagger | 
Knowledge about: Kanban board, team work, project lifetime cycle | 

My part **Patryk**

| Lp |      Id      |                                                         Nazwa                                                        |   AddedBy  |   DoneBy   |
|:--:|:------------:|:--------------------------------------------------------------------------------------------------------------------:|:----------:|:----------:|
| 1  | JDP200801-10 | UserController - testy endpointów                                                                                    |   kodilla  | **Patryk** |
| 2  | JDP200801-14 | Encja Cart - implementacja                                                                                           |   kodilla  | **Patryk** |
| 3  | JDP200801-19 | Encja Cart - testy                                                                                                   |   kodilla  | **Patryk** |
| 4  | JDP200801-21 | ProductController - rzeczywista implementacja metod                                                                  |   kodilla  | **Patryk** |
| 5  | JDP200801-30 | Encja cart - brakujące cascade, fetch oraz poprawa konstruktorów                                                     | **Patryk** | **Patryk** |
| 6  | JDP200801-31 | ProductDaoTestSuite - nie wszystkie wpisy do bazy danych są usuwane po zakończeniu testów                            | **Patryk** | **Patryk** |
| 7  | JDP200801-33 | Encja User - niepoprawne mappedBy                                                                                    | **Patryk** | **Patryk** |
| 8  | JDP200801-34 | Zmiana Cart - usunięcie @NotNull w połączeniu z User                                                                 | **Patryk** | **Patryk** |
| 9  | JDP200801-35 | Zmiana Product - dodanie isActive                                                                                    | **Patryk** | **Patryk** |
| 10 | JDP200801-37 | Dodanie swaggera                                                                                                     | **Patryk** | **Patryk** |
| 11 | JDP200801-36 | Encja Order - dodanie order status                                                                                   | **Patryk** |      -     |
| 12 | JDP200801-2  | GroupController - wstępna implementacja endpointów                                                                   |   kodilla  |   Andrzej  |
| 13 | JDP200801-4  | OrderController - wstępna implementacja endpointów                                                                   |   kodilla  |   Andrzej  |
| 14 | JDP200801-18 | Encja Group - testy                                                                                                  |   kodilla  |   Andrzej  |
| 15 | JDP200801-3  | CartController - wstępna implementacja endpointów                                                                    |   kodilla  |   Joanna   |
| 16 | JDP200801-6  | ProductController - testy endpointów                                                                                 |   kodilla  |   Joanna   |
| 17 | JDP200801-11 | Encja Order - implementacja                                                                                          |   kodilla  |   Joanna   |
| 18 | JDP200801-16 | Encja Order - testy                                                                                                  |   kodilla  |   Joanna   |
| 19 | JDP200801-22 | GroupController - rzeczywista implementacja metod                                                                    |   kodilla  |   Joanna   |
| 20 | JDP200801-32 | Upgrade Encji Order                                                                                                  |   Joanna   |   Joanna   |
| 21 | JDP200801-1  | ProductController - wstępna implementacja endpointów                                                                 |   kodilla  |   Maciej   |
| 22 | JDP200801-8  | CartController - testy endpointów                                                                                    |   kodilla  |   Maciej   |
| 23 | JDP200801-13 | Encja Group - implementacja                                                                                          |   kodilla  |   Maciej   |
| 24 | JDP200801-17 | Encja Product - testy                                                                                                |   kodilla  |   Maciej   |
| 25 | JDP200801-24 | OrderController - rzeczywista implementacja metod                                                                    |   kodilla  |   Maciej   |
| 26 | JDP200801-28 | Encja Product - usunięcie istniejącej encji Product nie uaktualnia list encji Group oraz Cart                        |   Maciej   |   Maciej   |
| 27 | JDP200801-29 | Błąd "tzw dwuznacznej nazwy Encji" w klasie Group, którą Hibernaer nie czyta lub tworzy konflikt w utworzeniu sql'a. |   Maciej   |   Maciej   |
| 28 | JDP200801-38 | Dodanie pliku Readme.md                                                                                              |   Maciej   |   Maciej   |
| 29 | JDP200801-9  | OrderController - testy endpointów                                                                                   |   kodilla  |   Marcin   |
| 30 | JDP200801-12 | Encja Product - implementacja                                                                                        |   kodilla  |   Marcin   |
| 31 | JDP200801-15 | Encja User - implementacja                                                                                           |   kodilla  |   Marcin   |
| 32 | JDP200801-20 | Encja User - testy                                                                                                   |   kodilla  |   Marcin   |
| 33 | JDP200801-23 | CartController - rzeczywista implementacja metod                                                                     |   kodilla  |   Marcin   |
| 34 | JDP200801-5  | UserController - wstępna implementacja endpointów                                                                    |   kodilla  | Paweł      |
| 35 | JDP200801-7  | GroupController - testy endpointów                                                                                   |   kodilla  | Paweł      |
| 36 | JDP200801-25 | UserController - rzeczywista implementacja metod                                                                     |   kodilla  | Paweł      |

# 1. Description

This project is an implementation of a simple BackEnd logic for an ecommerce store. 
It is implemented in a REST architecture, and it is made of 5 entities (product, group, user, cart, order) 
coupled together with SQL-style relationships and business logic.

# 2. Demo

Project is not publicly available at the moment, and exists as this GitHub repo.

# 3. Requirements

- Java 8
- MySQL 8 server
- Gradle 4.10
- Spring Boot 2.1.1
- Lombok 1.18
- Swagger 2

# 4. Startup

Project can be launched locally by executing EcommerceeApplication class, which starts up the Spring ApplicationContext.
While running, it can be accessed under [local address](http://localhost:8080).
![alt Landing Page](./docs/landing_page.png)

# 5. Endpoints description

Full endpoint documentation is available on a webpage, dynamically generated by the running project under `{URL}/swagger-ui.html`.

|  |  |
:---:|:---:
![alt CartController API](./docs/cart_controller_api_documentation.png "CartController API") | ![alt GroupController API](./docs/group_controller_api_documentation.png "GroupController API")
![alt OrderController API](./docs/order_controller_api_documentation.png "OrderController API") | ![alt ProductController API](./docs/product_controller_api_documentation.png "ProductController API")
![alt UserController API](./docs/user_controller_api_documentation.png "UserController API") | 
# 6. Application

At this stage, project simulates a simple operation of an ecommerce store, which houses the following:
- User & Cart (decoupled, a Cart can exist without a User, if not logged in)
- Product & Group (decoupled Products can belong to certain product Groups (1 group per product type), 
and Groups can exist without any products attached to them)
- Order (build based on a passed Cart)

Further development of the application can include the following:
- User logging in and out, with User's details being stored in the DB for easier Order completion
- Order class expansion to include user details required to issue an invoice

Other...

# 7. Troubleshooting

TBA
