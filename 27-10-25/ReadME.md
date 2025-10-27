# 🏦 Bank Microservices Suite

This repository contains a production-style microservices-based banking system built with **Spring Boot**, **Spring Cloud Gateway**, and **Eureka Server**.  
The architecture demonstrates robust service registration, API routing, modular design, and modern UI with JSP+Bootstrap. Both customer and admin features are included, each as a standalone service.

---

## 📦 Architecture Overview

This ecosystem consists of the following main microservices:

1. **Eureka Server**
    - Service registry and discovery for all services.
    - Access: [`http://localhost:8080/eureka`](http://localhost:8080/eureka)

2. **API Gateway**
    - Unified entry point for all inbound client requests.
    - Handles dynamic routing, path predicates, service discovery, and load balancing through Eureka.
    - Access: [`http://localhost:8084`](http://localhost:8084)

3. **Bank Application**
    - End-user service for customers.
    - Features: registration, login (with OTP), dashboard, deposit/withdraw, and more.
    - Built with Spring Boot + JSP.

4. **Admin Application**
    - Admin panel for managing bank customers.
    - Features: add, edit, delete, and list users with an interactive dashboard.
    - Built with Spring Boot + JSP.

---

## 🗂️ Repository Structure

.
├── api-gateway/ # Spring Cloud Gateway (8084)
├── eureka-server/ # Eureka registry server (8080)
├── bank-app/ # Bank customer microservice (e.g. 8082)
├── admin-app/ # Admin microservice (e.g. 8081)

text

---

## 🛠️ Technologies

- Java 17+
- Spring Boot 3.x
- Spring Cloud Gateway
- Eureka Discovery Server
- JSP (for UI)
- Bootstrap 5 (UI styling)
- MySQL (JDBC)
- Maven

---

## 🚦 How To Run

### Prerequisites

- Java JDK 17 or later
- Maven
- MySQL running and accessible (update config in `application.properties` for username/password/db)

### Run Microservices

1. **Start Eureka Server (on port 8080)**
    ```
    cd eureka-server
    mvn spring-boot:run
    ```
2. **Start API Gateway (on port 8084)**
    ```
    cd api-gateway
    mvn spring-boot:run
    ```
3. **Start Admin Microservice (e.g. on port 8081)**
    ```
    cd admin-app
    mvn spring-boot:run
    ```
4. **Start Bank Microservice (e.g. on port 8082)**
    ```
    cd bank-app
    mvn spring-boot:run
    ```

_(Be sure all port numbers and `spring.application.name` values are unique for each service.)_

---

### Important URLs

- **Eureka Dashboard:** [`http://localhost:8080/eureka`](http://localhost:8080/eureka)
- **API Gateway:** [`http://localhost:8084`](http://localhost:8084)
- **Bank App (via Gateway):** [`http://localhost:8084/bankapp/...`](http://localhost:8084/bankapp/...)
- **Admin App (via Gateway):** [`http://localhost:8084/admin/...`](http://localhost:8084/admin/...)

---

## ✨ Features

- **Microservices with Central Discovery:** Each service registers itself with Eureka upon startup.
- **API Gateway:** Central routing, dynamic service lookup, and external API grouping.
- **Bank Application:**
    - User registration and login (with OTP verification)
    - Account dashboard with modern UI
    - Deposit and Withdraw transactions
    - Cheque book request (sample feature)
- **Admin Application:**
    - Secure admin login
    - Dashboard with user statistics
    - List/Add/Edit/Delete any customer
- **Modern, Responsive UI:** JSP pages styled with Bootstrap 5 and subtle animations.
- **MySQL Database:** All user data and account operations persist in a MySQL database.

---

## 🧑‍💻 Customization

- **Database:** Update each service’s `application.properties` with your MySQL credentials.
- **Ports and Names:** Set `server.port` and `spring.application.name` uniquely per microservice.
- **API Gateway Mappings:** Control path routing and service discovery via `application.properties` in `api-gateway`.

---

## 👥 Contributing

Have an idea, bugfix, or feature?  
Fork this repo, make a PR, or open an issue!  
Code for learning and personal or educational use—commercial use at your own risk.

---

## 📃 License

This source code is provided under the MIT License for educational and demo use only.

---

## 🙏 Acknowledgements

Special thanks to the Spring, Netflix, and Bootstrap communities!

---

**Enjoy Modern Banking with Microservices 🚀**
