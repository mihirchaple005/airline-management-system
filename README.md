# SpringBootHonorsAssignment
This is my first Spring boot project using microservice architecture . This project consists of User management , Ticket Management and Flight management.

---

```
# ✈️ Airline Management System (Microservices-based)

This is a **Spring Boot Microservices** project designed as part of Assignment 1. The system simulates core airline functionalities such as booking, ticketing, and flight management using separate services.

## 📦 Microservices Structure

```

airline-management-system/
├── FlightMicroservice/      # Handles flight data
├── TicketMicroservice/      # Manages ticket booking and cancellation
├── UserMicroservice/        # User information and login
├── ApiGatewayService/       # Central gateway to route requests
├── docker-compose.yml       # Docker orchestration
├── README.md
└── Jenkinsfile              # Jenkins pipeline configuration

````

---

## 🛠️ Tech Stack

- **Java 21 + Spring Boot**
- **Maven** for dependency management
- **Docker & Docker Compose**
- **Jenkins** for CI/CD pipeline
- **REST APIs** for communication
- **Feign Clients** for internal service-to-service calls

---

## 🚀 How to Run (Locally)

### 1. Clone the repository
```bash
git clone https://github.com/mihirchaple005/springbootassignment.git
cd airline-management-system
````

### 2. Build and Run with Docker Compose

```bash
docker-compose build
docker-compose up
```

Services will be available at:

* 🧑 User Service → `http://localhost:8083`
* 🎫 Ticket Service → `http://localhost:8082`
* 🛩️ Flight Service → `http://localhost:8081`
* 🌐 API Gateway → `http://localhost:8080`

---

## ⚙️ Jenkins CI/CD (Deployment Flow)

### 📄 Jenkins Pipeline Stages

1. **Clone Code** from GitHub
2. **Build Microservices** using Docker
3. **Run Tests** (if present)
4. **Deploy to EC2** using Docker Compose

### 🔧 Jenkinsfile Overview

```groovy
pipeline {
    agent any
    stages {
        stage('Clone') {
            steps {
                git 'https://github.com/mihirchaple005/springbootassignment.git'
            }
        }
        stage('Build') {
            steps {
                sh 'docker-compose build'
            }
        }
        stage('Test') {
            steps {
                echo 'Run unit/integration tests here'
            }
        }
        stage('Deploy') {
            steps {
                sh 'docker-compose up -d'
            }
        }
    }
}
```

---

## 📌 Notes

* Ensure **ports (8080–8084)** are free on your system before running.
* Feign clients are used for internal communication. Make sure service names in `application.properties` match Docker Compose `service names`.
* This project is deployable on **AWS EC2**, and suitable for demonstration of real-world microservice architecture.

---

## 👨‍💻 Author

> Created by \[Mihir Chaple], as part of Spring Boot Microservices Assignment 1.

```
