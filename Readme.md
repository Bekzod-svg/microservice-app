# Microservice Application

This is a microservice-based application developed using Java, Maven, and Spring Boot. The application is designed to demonstrate the use of microservices in a real-world scenario. It consists of several services, each performing a specific function, and communicating with each other through APIs.

## Project Structure

The project is structured as a collection of microservices. Each service is a separate application, running in its own Docker container. The services communicate with each other using REST APIs and Kafka messaging.

## Services

The application consists of the following services:

- Zookeeper: Used for service discovery and configuration management.
- Kafka: Used for asynchronous messaging between services.
- MySQL (Order and Inventory): Used for storing order and inventory data.
- MongoDB: Used for storing product data.
- Keycloak with MySQL database: Used for user authentication and authorization.
- Zipkin: Used for distributed tracing.
- Eureka Server: Used for service discovery.
- API Gateway: Acts as a single entry point into the system.
- Product Service: Manages product-related operations.
- Order Service: Manages order-related operations.
- Inventory Service: Manages inventory-related operations.
- Notification Service: Sends notifications to users.
- Prometheus: Used for monitoring.
- Grafana: Used for visualizing metrics.

## Setup and Installation

1. Clone the repository from GitHub.

```bash
git clone https://github.com/Bekzod-svg/microservice-app.git
```

2. Navigate to the project directory.

```bash
cd microservice-app
```

3. Run the Docker Compose file.

```bash
docker-compose up
```

## Usage

To use the application, you can send HTTP requests to the API Gateway, which will route the requests to the appropriate services. The services will process the requests and respond with the requested data.
