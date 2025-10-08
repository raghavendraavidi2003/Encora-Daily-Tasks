# Retail Management System

A microservices-based retail management system implementing event-driven architecture using Apache Kafka for asynchronous communication between services.

##  Project Overview

This project demonstrates a modern retail management system split into microservices that communicate through Apache Kafka message broker. The system includes Product Service for managing product catalog and Inventory Service for stock management.

##  Architecture

The system follows a microservices architecture pattern with event-driven communication:

- **Product Service**: Manages product information, pricing, and catalog
- **Inventory Service**: Handles stock levels, inventory tracking, and availability
- **Apache Kafka**: Message broker for asynchronous event communication

##  Features

- **Product Management**: CRU operations for products
- **Inventory Tracking**: Real-time stock level monitoring
- **Event-Driven Communication**: Services communicate via Kafka topics
- **Asynchronous Processing**: Non-blocking operations between services
- **Scalable Architecture**: Independent service scaling capability

##  Technology Stack

- **Message Broker**: Apache Kafka
- **Backend Framework**: [Spring Boot/Apache Kafka]
- **Database**: [H2 DB]
- **Build Tool**: [Maven/Spring Boot Starter]

##  Prerequisites

- Java 21+
- Apache Kafka 2.8+
- H2 DB installed and running

##  Setup Instructions

### 1. Start Kafka 

bash
# Start Zookeeper
bin/windows/server-start.sh config/zookeeper.properties

# Start Kafka Server
bin/windows/kafka-server-start.sh config/server.properties


### 2. Create Kafka Topics

bash
# Create product events topic
bin/windows/kafka-topics.sh --create --topic product-events --bootstrap-server localhost:9092 --partitions 3 --replication-factor 1

# Create inventory events topic
bin/kafka-topics.sh --create --topic inventory-events --bootstrap-server localhost:9092 --partitions 3 --replication-factor 1


### 3. Configure Services

Update application configuration files with your Kafka broker details:
properties
kafka.bootstrap.servers=localhost:9092
kafka.group.id=retail-consumer-group


### 4. Run Services

bash
# Start Order Service
cd order-service
[mvn spring-boot:run / npm start / python app.py]

# Start Inventory Service
cd inventory-service
[mvn spring-boot:run / npm start / python app.py]


##  API Endpoints

### Order Service
- `POST /api/orders/create` - Create new product
- `GET /api/orders/all` - Get all products

### Inventory Service
- `GET /api/inventory/items/{productId}` - Get inventory for product
- `POST /api/inventory/items` - Add inventory
-`GET /api/inventory/items/all` - Get all products

##  Event Flow

1. Order Service publishes events to `product-events` topic
2. Inventory Service consumes events from `product-events` topic
3. Inventory Service automatically syncs stock data based on product changes
4. Services remain loosely coupled through Kafka messaging

##  Future Enhancements

- Add authentication and authorization
- Implement API Gateway
- Add service discovery (Eureka/Consul)
- Implement distributed tracing
- Add monitoring and logging (ELK Stack)
- Containerize with Docker
- Orchestrate with Kubernetes

##  Contributors

- Avidi Raghavendra

##  License

This project is created for learning purposes.

##  Contact

For questions or feedback, please reach out to [avidi.raghavendra@encora.com]



**Date Created**: October 7, 2025  
**Last Updated**: October 8, 2025
