# Lakgamana - Train Reservation System Backend

A comprehensive backend system for managing train reservations, built with Spring Boot and MySQL.

## Overview

This is the backend API for Lakgamana, a train reservation management platform that handles all aspects of railway ticketing operations, including reservations, train schedules, routes, user management, payments, and customer feedback.

## Features

### Core Modules

- **Reservation Management** - Handle ticket bookings, cancellations, and reservation queries
- **Train Schedule Management** - Manage train schedules, timings, and availability
- **Route Management** - Define and manage railway routes and station connections
- **User Management** - User registration, authentication, and profile management
- **Payment Management** - Process payments and maintain transaction records
- **Feedback Management** - Collect and manage customer feedback and ratings

## Technology Stack

- **Framework:** Spring Boot
- **Build Tool:** Maven
- **Database:** MySQL
- **Language:** Java

## Prerequisites

- Java JDK 17 or higher
- Maven 3.6+
- MySQL 8.0+
- IDE (IntelliJ IDEA, Eclipse, or VS Code)

## Installation

### 1. Clone the Repository

```bash
git clone <repository-url>
cd lakgamana-backend
```

### 2. Database Setup

Create a MySQL database:

```sql
CREATE DATABASE lakgamana_db;
```

### 3. Configure Application Properties

Update `src/main/resources/application.properties` with your database credentials:

```properties
spring.datasource.url=jdbc:mysql://localhost:3306/lakgamana_db
spring.datasource.username=your_username
spring.datasource.password=your_password
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
```

### 4. Build the Project

```bash
mvn clean install
```

### 5. Run the Application

```bash
mvn spring-boot:run
```

The application will start on `http://localhost:8080`

## API Documentation

### Base URL
```
http://localhost:8080/api
```

### Main Endpoints

- `/reservations` - Reservation operations
- `/trains` - Train schedule management
- `/routes` - Route management
- `/users` - User operations
- `/payments` - Payment processing
- `/feedback` - Feedback management

## Project Structure

```
src/
├── main/
│   ├── java/
│   │   └── com/trainreservation/
│   │       ├── controller/
│   │       ├── service/
│   │       ├── repository/
│   │       ├── model/
│   │       ├── dto/
│   │       └── config/
│   └── resources/
│       ├── application.properties
│       └── static/
└── test/
```

## Development

### Running Tests

```bash
mvn test
```

### Building for Production

```bash
mvn clean package
```

The generated JAR file will be in the `target/` directory.

## Configuration

Key configuration files:
- `application.properties` - Main configuration
- `pom.xml` - Maven dependencies and build configuration

## Database Schema

The application uses JPA/Hibernate for ORM. Entity relationships include:
- Users and Reservations (One-to-Many)
- Trains and Schedules (One-to-Many)
- Routes and Trains (Many-to-Many)
- Reservations and Payments (One-to-One)

## Contributing

1. Fork the repository
2. Create a feature branch (`git checkout -b feature/YourFeature`)
3. Commit your changes (`git commit -m 'Add some feature'`)
4. Push to the branch (`git push origin feature/YourFeature`)
5. Open a Pull Request
