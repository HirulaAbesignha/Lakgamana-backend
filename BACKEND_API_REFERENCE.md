# Train Booking System - Complete API Reference with Raw Bodies

## Base URL
```
http://localhost:8080
```

---

## 1. PASSENGER MANAGEMENT APIs

### Create Passenger
**POST** `/api/passengers`
```json
{
    "name": "John Doe",
    "gender": "Male",
    "age": 30,
    "contactNum": "0771234567",
    "email": "john.doe@email.com"
}
```

### Update Passenger
**PUT** `/api/passengers/{id}`
```json
{
    "name": "John Smith",
    "gender": "Male",
    "age": 31,
    "contactNum": "0779876543",
    "email": "john.smith@email.com"
}
```

### Get All Passengers
**GET** `/api/passengers`
*No body required*

### Get Passenger by ID
**GET** `/api/passengers/1`
*No body required*

### Get Passenger by Email
**GET** `/api/passengers/email/john.doe@email.com`
*No body required*

### Delete Passenger
**DELETE** `/api/passengers/1`
*No body required*

---

## 2. TRAIN MANAGEMENT APIs

### Create Train
**POST** `/api/trains`
```json
{
    "trainNumber": "TR001",
    "trainName": "Express Train",
    "departureTime": "2025-09-30T08:00:00",
    "arrivalTime": "2025-09-30T14:00:00"
}
```

### Update Train
**PUT** `/api/trains/{id}`
```json
{
    "trainNumber": "TR001-UPDATED",
    "trainName": "Super Express Train",
    "departureTime": "2025-09-30T09:00:00",
    "arrivalTime": "2025-09-30T15:00:00"
}
```

### Get All Trains
**GET** `/api/trains`
*No body required*

### Get Train by ID
**GET** `/api/trains/1`
*No body required*

### Get Train by Number
**GET** `/api/trains/number/TR001`
*No body required*

### Get Train Schedule (All upcoming trains)
**GET** `/api/trains/schedule`
*No body required*

### Get Train Schedule by Date
**GET** `/api/trains/schedule/2025-09-30`
*No body required*

### Delete Train
**DELETE** `/api/trains/1`
*No body required*

---

## 3. BOOKING MANAGEMENT APIs (CORE FEATURES)

### Create Booking ⭐
**POST** `/api/bookings`
```json
{
    "passengerId": 1,
    "trainId": 1,
    "journeyDate": "2025-10-15",
    "classType": "FIRST",
    "seatNum": "A1"
}
```

**Alternative Class Types:**
```json
{
    "passengerId": 1,
    "trainId": 1,
    "journeyDate": "2025-10-15",
    "classType": "SECOND",
    "seatNum": "B15"
}
```

```json
{
    "passengerId": 1,
    "trainId": 1,
    "journeyDate": "2025-10-15",
    "classType": "THIRD",
    "seatNum": "C25"
}
```

### Reschedule Booking ⭐
**PUT** `/api/bookings/{bookingId}/reschedule`
```json
{
    "newTrainId": 2,
    "newJourneyDate": "2025-10-20"
}
```

### Cancel Booking ⭐
**PUT** `/api/bookings/{bookingId}/cancel`
*No body required*

### Confirm Booking
**PUT** `/api/bookings/{bookingId}/confirm`
*No body required*

### Get All Bookings ⭐
**GET** `/api/bookings`
*No body required*

### Get Booking by ID
**GET** `/api/bookings/1`
*No body required*

### Get Bookings by Passenger ⭐
**GET** `/api/bookings/passenger/1`
*No body required*

### Get Bookings by Train ⭐
**GET** `/api/bookings/train/1`
*No body required*

### Get Bookings by Status
**GET** `/api/bookings/passenger/1/status/confirmed`
**GET** `/api/bookings/passenger/1/status/pending`
**GET** `/api/bookings/passenger/1/status/cancelled`
*No body required*

### Delete Booking
**DELETE** `/api/bookings/1`
*No body required*

---

## 4. PAYMENT MANAGEMENT APIs

### Create Payment
**POST** `/api/payments`
```json
{
    "bookingId": 1,
    "amount": 1500.50,
    "paymentMethod": "Card"
}
```

**Alternative Payment Methods:**
```json
{
    "bookingId": 1,
    "amount": 2000.00,
    "paymentMethod": "Cash"
}
```

```json
{
    "bookingId": 1,
    "amount": 1750.25,
    "paymentMethod": "UPI"
}
```

```json
{
    "bookingId": 1,
    "amount": 1800.00,
    "paymentMethod": "NetBanking"
}
```

### Process Payment
**PUT** `/api/payments/{paymentId}/process`
*No body required*

### Fail Payment
**PUT** `/api/payments/{paymentId}/fail`
*No body required*

### Get All Payments
**GET** `/api/payments`
*No body required*

### Get Payment by ID
**GET** `/api/payments/1`
*No body required*

### Get Payment by Booking
**GET** `/api/payments/booking/1`
*No body required*

### Get Payments by Status
**GET** `/api/payments/status/Paid`
**GET** `/api/payments/status/Pending`
**GET** `/api/payments/status/Failed`
*No body required*

---

## 5. COMPLETE WORKFLOW EXAMPLES

### Complete Booking Flow
1. **Create Passenger**
```json
POST /api/passengers
{
    "name": "Alice Johnson",
    "gender": "Female",
    "age": 28,
    "contactNum": "0776543210",
    "email": "alice.johnson@email.com"
}
```

2. **Create Train**
```json
POST /api/trains
{
    "trainNumber": "EXP100",
    "trainName": "Morning Express",
    "departureTime": "2025-10-01T06:00:00",
    "arrivalTime": "2025-10-01T12:00:00"
}
```

3. **Create Booking**
```json
POST /api/bookings
{
    "passengerId": 1,
    "trainId": 1,
    "journeyDate": "2025-10-01",
    "classType": "FIRST",
    "seatNum": "A5"
}
```

4. **Create Payment**
```json
POST /api/payments
{
    "bookingId": 1,
    "amount": 2500.00,
    "paymentMethod": "Card"
}
```

5. **Process Payment**
```
PUT /api/payments/1/process
```

6. **Confirm Booking**
```
PUT /api/bookings/1/confirm
```

### Rescheduling Flow
1. **Check New Train Schedule**
```
GET /api/trains/schedule/2025-10-05
```

2. **Reschedule Booking**
```json
PUT /api/bookings/1/reschedule
{
    "newTrainId": 2,
    "newJourneyDate": "2025-10-05"
}
```

---

## 6. ERROR RESPONSES

### Common Error Response Format
```json
{
    "error": "Error message description"
}
```

### Example Error Scenarios
- **Past Date Booking**: `"Journey date cannot be in the past"`
- **Seat Taken**: `"Seat A1 is already booked for this train on 2025-10-01"`
- **Invalid Passenger**: `"Passenger not found with id: 999"`
- **Invalid Train**: `"Train not found with id: 999"`
- **Cancelled Booking Reschedule**: `"Cannot reschedule a cancelled booking"`

---

## 7. TESTING SEQUENCE

### Quick Test Sequence (Copy-paste ready)

1. **Create a passenger**
```bash
curl -X POST http://localhost:8080/api/passengers \
-H "Content-Type: application/json" \
-d '{"name":"Test User","gender":"Male","age":25,"contactNum":"0771234567","email":"test@email.com"}'
```

2. **Create a train**
```bash
curl -X POST http://localhost:8080/api/trains \
-H "Content-Type: application/json" \
-d '{"trainNumber":"TEST01","trainName":"Test Train","departureTime":"2025-10-01T10:00:00","arrivalTime":"2025-10-01T16:00:00"}'
```

3. **Create a booking**
```bash
curl -X POST http://localhost:8080/api/bookings \
-H "Content-Type: application/json" \
-d '{"passengerId":1,"trainId":1,"journeyDate":"2025-10-01","classType":"FIRST","seatNum":"A1"}'
```

4. **View all bookings**
```bash
curl -X GET http://localhost:8080/api/bookings
```

5. **Reschedule booking**
```bash
curl -X PUT http://localhost:8080/api/bookings/1/reschedule \
-H "Content-Type: application/json" \
-d '{"newTrainId":1,"newJourneyDate":"2025-10-02"}'
```

---

## 8. ENUM VALUES REFERENCE

### Gender Options
- `"Male"`
- `"Female"`
- `"Other"`

### Class Types
- `"FIRST"` (maps to "1st" in database)
- `"SECOND"` (maps to "2nd" in database)
- `"THIRD"` (maps to "3rd" in database)

### Booking Status
- `"pending"`
- `"confirmed"`
- `"cancelled"`

### Payment Methods
- `"Cash"`
- `"Card"`
- `"UPI"`
- `"NetBanking"`

### Payment Status
- `"Paid"`
- `"Pending"`
- `"Failed"`

---

## Start Your Application
```bash
mvn spring-boot:run
```

The API will be available at `http://localhost:8080`