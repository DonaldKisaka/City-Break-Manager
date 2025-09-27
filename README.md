# 🌍 CityBreak - Travel Management API

[![Java](https://img.shields.io/badge/Java-25-orange.svg)](https://openjdk.java.net/)
[![Spring Boot](https://img.shields.io/badge/Spring%20Boot-3.5.6-brightgreen.svg)](https://spring.io/projects/spring-boot)
[![PostgreSQL](https://img.shields.io/badge/PostgreSQL-Database-blue.svg)](https://www.postgresql.org/)
[![JWT](https://img.shields.io/badge/JWT-Authentication-red.svg)](https://jwt.io/)
[![Maven](https://img.shields.io/badge/Maven-Build%20Tool-yellow.svg)](https://maven.apache.org/)

A robust RESTful API for managing travel experiences, built with modern Java and Spring Boot. CityBreak allows users to track cities they've visited, plan future trips, and maintain personal travel records with secure authentication.

## 🚀 Features

### 🔐 **Secure Authentication**
- JWT-based authentication system
- User registration and login
- Protected endpoints with role-based access
- Password encryption and validation

### 🏙️ **City Management**
- Add cities you've visited or want to visit
- Store detailed information about each destination
- Personal city collections per user
- Country and location tracking

### ✈️ **Trip Planning & Tracking**
- Create and manage trip records
- Date-based trip planning (start/end dates)
- Personal rating system (1-10 scale)
- Private notes for each trip
- Link trips to specific cities

### 🛡️ **Security & Data Protection**
- Spring Security integration
- JWT token-based authentication
- User-specific data isolation
- Input validation and sanitization
- Secure password storage

## 🛠️ Tech Stack

### **Backend Framework**
- **Spring Boot 3.5.6** - Modern Java framework
- **Spring Security** - Authentication & authorization
- **Spring Data JPA** - Database abstraction layer
- **Spring Validation** - Input validation

### **Database**
- **PostgreSQL** - Production-ready relational database
- **Supabase** - Database hosting and management
- **Hibernate** - ORM for database operations

### **Security & Authentication**
- **JWT (JSON Web Tokens)** - Stateless authentication
- **BCrypt** - Password hashing
- **JJWT** - JWT implementation for Java

### **Development Tools**
- **Lombok** - Reduces boilerplate code
- **Maven** - Dependency management
- **Spring Boot DevTools** - Development productivity

### **Testing**
- **JUnit 5** - Unit testing framework
- **Spring Boot Test** - Integration testing
- **Spring Security Test** - Security testing

## 📋 Prerequisites

Before running this application, make sure you have:

- **Java 25** or higher
- **Maven 3.6+**
- **PostgreSQL** database (or Supabase account)
- **Git** for version control

## 🚀 Quick Start

### 1. Clone the Repository
```bash
git clone https://github.com/yourusername/citybreak-api.git
cd citybreak-api
```

### 2. Database Setup
Configure your database connection in `src/main/resources/application.properties`:

```properties
# Database Configuration
spring.datasource.url=jdbc:postgresql://your-db-host:5432/your-database
spring.datasource.username=your-username
spring.datasource.password=your-password
spring.datasource.driver-class-name=org.postgresql.Driver

# JWT Configuration
security.jwt.secret-key=your-secret-key-here
security.jwt.expiration-time=900000

# JPA Configuration
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
```

### 3. Install Dependencies
```bash
mvn clean install
```

### 4. Run the Application
```bash
mvn spring-boot:run
```

The API will be available at `http://localhost:8080`

## 📡 API Documentation

### Authentication Endpoints

#### Register New User
```http
POST /api/v1/auth/signup
Content-Type: application/json

{
  "email": "user@example.com",
  "username": "john_doe",
  "password": "securePassword123"
}
```

#### User Login
```http
POST /api/v1/auth/login
Content-Type: application/json

{
  "email": "user@example.com",
  "password": "securePassword123"
}
```

**Response:**
```json
{
  "token": "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9...",
  "expiresIn": 900000
}
```

### City Management Endpoints

#### Get All Cities
```http
GET /api/v1/cities
Authorization: Bearer {jwt-token}
```

#### Create New City
```http
POST /api/v1/cities
Authorization: Bearer {jwt-token}
Content-Type: application/json

{
  "name": "Paris",
  "country": "France",
  "details": "The city of lights, famous for the Eiffel Tower and rich culture."
}
```

#### Update City
```http
PUT /api/v1/cities/{id}
Authorization: Bearer {jwt-token}
Content-Type: application/json

{
  "name": "Paris",
  "country": "France",
  "details": "Updated description of Paris..."
}
```

#### Delete City
```http
DELETE /api/v1/cities/{id}
Authorization: Bearer {jwt-token}
```

### Trip Management Endpoints

#### Get All Trips
```http
GET /api/v1/trips
Authorization: Bearer {jwt-token}
```

#### Create New Trip
```http
POST /api/v1/trips
Authorization: Bearer {jwt-token}
Content-Type: application/json

{
  "city": {
    "id": 1
  },
  "startDate": "2024-06-01",
  "endDate": "2024-06-07",
  "rating": 9,
  "personalNotes": "Amazing trip! The food was incredible and the weather was perfect."
}
```

#### Update Trip
```http
PUT /api/v1/trips/{id}
Authorization: Bearer {jwt-token}
Content-Type: application/json

{
  "city": {
    "id": 1
  },
  "startDate": "2024-06-01",
  "endDate": "2024-06-10",
  "rating": 10,
  "personalNotes": "Extended the trip - even better than expected!"
}
```

#### Delete Trip
```http
DELETE /api/v1/trips/{id}
Authorization: Bearer {jwt-token}
```

## 🏗️ Project Structure

```
src/
├── main/
│   ├── java/com/example/citybreak/city/
│   │   ├── config/           # Security and JWT configuration
│   │   │   ├── ApplicationConfig.java
│   │   │   ├── JwtAuthenticationFilter.java
│   │   │   ├── JwtService.java
│   │   │   └── SecurityConfig.java
│   │   ├── controller/       # REST API controllers
│   │   │   ├── AuthenticationController.java
│   │   │   ├── CityController.java
│   │   │   └── TripController.java
│   │   ├── dto/             # Data Transfer Objects
│   │   │   ├── AuthenticationResponseDto.java
│   │   │   ├── CreateUserDto.java
│   │   │   └── LoginUserDto.java
│   │   ├── model/           # JPA Entity classes
│   │   │   ├── City.java
│   │   │   ├── Trip.java
│   │   │   └── User.java
│   │   ├── repository/      # Data access layer
│   │   │   ├── CityRepository.java
│   │   │   ├── TripRepository.java
│   │   │   └── UserRepository.java
│   │   ├── service/         # Business logic layer
│   │   │   ├── AuthenticationService.java
│   │   │   ├── CityService.java
│   │   │   └── TripsService.java
│   │   └── CityApplication.java
│   └── resources/
│       ├── application.properties
│       ├── static/
│       └── templates/
└── test/                    # Test classes
    └── java/com/example/citybreak/city/
        └── CityApplicationTests.java
```

## 🔧 Configuration

### Environment Variables
For production deployment, consider using environment variables:

```bash
export DB_URL=jdbc:postgresql://your-production-db:5432/citybreak
export DB_USERNAME=your-username
export DB_PASSWORD=your-password
export JWT_SECRET=your-production-secret-key
export JWT_EXPIRATION=3600000
```

### Docker Deployment (Optional)
```dockerfile
FROM openjdk:25-jdk-slim
COPY target/city-0.0.1-SNAPSHOT.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "/app.jar"]
```

## 🧪 Testing

Run the test suite:
```bash
mvn test
```

Run with coverage:
```bash
mvn test jacoco:report
```

## 🚀 Deployment

### Local Development
```bash
mvn spring-boot:run
```

### Production Build
```bash
mvn clean package -Dmaven.test.skip=true
java -jar target/city-0.0.1-SNAPSHOT.jar
```



## 📈 Performance Features

- **Connection Pooling** - Efficient database connections
- **Lazy Loading** - Optimized JPA relationships
- **JWT Stateless Authentication** - Scalable security
- **Input Validation** - Prevents invalid data
- **Error Handling** - Comprehensive exception management




