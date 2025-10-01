# Lakgamana Train Booking Backend System

A complete Spring Boot backend application for train reservation management system built with Java 17.

## Features

✅ **Passenger Management**
- Create, update, view, and delete passenger records
- Email validation and unique constraints

✅ **Train Schedule Management**
- View train schedules by date
- Manage train information (routes, timings)

✅ **Booking Management**
- Create new bookings
- View bookings by passenger or train
- Reschedule existing bookings
- Cancel bookings
- Seat availability validation

✅ **Payment Processing**
- Create and process payments
- Multiple payment methods support
- Payment status tracking

## Database Schema

The system uses the following tables:
- **Passenger**: Store passenger information
- **Train**: Store train details and schedules
- **Booking**: Manage booking records with status tracking
- **Payment**: Handle payment transactions

## Technology Stack

- **Java 17**
- **Spring Boot 3.5.5**
- **Spring Data JPA**
- **MySQL Database**
- **Maven**

## API Endpoints

### Passenger Management
- `GET /api/passengers` - Get all passengers
- `GET /api/passengers/{id}` - Get passenger by ID
- `GET /api/passengers/email/{email}` - Get passenger by email
- `POST /api/passengers` - Create new passenger
- `PUT /api/passengers/{id}` - Update passenger
- `DELETE /api/passengers/{id}` - Delete passenger

### Train Schedule
- `GET /api/trains` - Get all trains
- `GET /api/trains/{id}` - Get train by ID
- `GET /api/trains/schedule` - Get current train schedule
- `GET /api/trains/schedule/{date}` - Get train schedule for specific date
- `POST /api/trains` - Add new train
- `PUT /api/trains/{id}` - Update train
- `DELETE /api/trains/{id}` - Delete train

### Booking Operations
- `GET /api/bookings` - Get all bookings
- `GET /api/bookings/{id}` - Get booking by ID
- `GET /api/bookings/passenger/{passengerId}` - Get bookings by passenger
- `GET /api/bookings/train/{trainId}` - Get bookings by train
- `POST /api/bookings` - **Create new booking**
- `PUT /api/bookings/{id}/confirm` - Confirm booking
- `PUT /api/bookings/{id}/reschedule` - **Reschedule booking**
- `PUT /api/bookings/{id}/cancel` - **Cancel booking**
- `DELETE /api/bookings/{id}` - Delete booking

### Payment Management
- `GET /api/payments` - Get all payments
- `GET /api/payments/{id}` - Get payment by ID
- `GET /api/payments/booking/{bookingId}` - Get payment by booking
- `POST /api/payments` - Create payment
- `PUT /api/payments/{id}/process` - Process payment
- `PUT /api/payments/{id}/fail` - Mark payment as failed

## Request Examples

### Create Booking
```json
POST /api/bookings
{
    "passengerId": 1,
    "trainId": 1,
    "journeyDate": "2025-10-15",
    "classType": "FIRST",
    "seatNum": "A1"
}
```

### Reschedule Booking
```json
PUT /api/bookings/1/reschedule
{
    "newTrainId": 2,
    "newJourneyDate": "2025-10-20"
}
```

### Create Payment
```json
POST /api/payments
{
    "bookingId": 1,
    "amount": 1500.00,
    "paymentMethod": "Card"
}
```

## Database Configuration

Update `application.properties` with your MySQL database settings:

```properties
spring.datasource.url=jdbc:mysql://localhost:3307/se
spring.datasource.username=root
spring.datasource.password=root
```

## Business Logic Features

### Booking Validation
- Prevents booking past dates
- Checks seat availability
- Validates passenger and train existence
- Manages booking status (pending, confirmed, cancelled)

### Rescheduling Logic
- Validates new train and date
- Checks seat availability on new schedule
- Prevents rescheduling cancelled bookings
- Resets status to pending for rescheduled bookings

### Payment Integration
- Links payments to bookings
- Supports multiple payment methods (Cash, Card, UPI, NetBanking)
- Tracks payment status (Paid, Pending, Failed)

## Running the Application

1. Ensure MySQL is running on port 3307
2. Create database named 'se'
3. Run the application:
```bash
mvn spring-boot:run
```

The application will start on `http://localhost:8080`

## Project Structure

```
src/main/java/com/seproject/lakgamana_backend/
├── entity/          # JPA entities (Passenger, Train, Booking, Payment)
├── repository/      # Data access layer
├── service/         # Business logic layer
├── controller/      # REST API controllers
└── LakgamanaBackendApplication.java
```

## Status

✅ **Completed Features:**
- Add booking records
- View train schedules and bookings  
- Reschedule bookings
- Cancel bookings
- Complete CRUD operations for all entities
- Payment processing system
- Data validation and error handling

The backend system is fully functional and ready for integration with a frontend application.