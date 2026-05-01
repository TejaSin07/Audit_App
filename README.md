# AuditPro - Audit Application

A Spring Boot-based audit application designed to provide comprehensive audit tracking, management, and security features for enterprise applications.

## 📋 Project Overview

**AuditPro** is a robust audit management system built with Spring Boot 3.2.12 and Java 17. It provides RESTful APIs for audit logging, tracking, and management with integrated security features including JWT-based authentication.

**Repository:** [TejaSin07/Audit_App](https://github.com/TejaSin07/Audit_App)

---

## 🛠️ Tech Stack

### Core Technologies
- **Java:** 17
- **Framework:** Spring Boot 3.2.12
- **Build Tool:** Maven
- **Database:** MySQL

### Key Dependencies

#### Data & ORM
- Spring Data JPA
- MySQL Connector/J

#### Security & Authentication
- Spring Security
- JWT (JSON Web Tokens)
  - jjwt-api v0.11.5
  - jjwt-impl v0.11.5
  - jjwt-jackson v0.11.5

#### Web & API
- Spring Boot Web Starter
- SpringDoc OpenAPI (Swagger UI) v2.5.0
- Spring Boot Validation

#### Development Tools
- Lombok (reduces boilerplate code)
- Spring Boot Test (for unit and integration testing)

---

## 📦 Project Structure

```
Audit_App/
├── src/                          # Source code directory
├── AuditPro/                     # Application modules
├── pom.xml                       # Maven configuration
├── mvnw & mvnw.cmd              # Maven wrapper scripts
└── .mvn/                         # Maven configuration directory
```

---

## 🚀 Getting Started

### Prerequisites
- **Java 17** or higher
- **Maven 3.6+** (or use Maven wrapper)
- **MySQL 8.0+**

### Installation

1. **Clone the repository:**
   ```bash
   git clone https://github.com/TejaSin07/Audit_App.git
   cd Audit_App
   ```

2. **Configure Database:**
   Update `application.properties` or `application.yml` with your MySQL credentials:
   ```properties
   spring.datasource.url=jdbc:mysql://localhost:3306/audit_db
   spring.datasource.username=root
   spring.datasource.password=your_password
   spring.jpa.hibernate.ddl-auto=update
   ```

3. **Build the Application:**
   ```bash
   ./mvnw clean install
   ```
   Or on Windows:
   ```cmd
   mvnw.cmd clean install
   ```

4. **Run the Application:**
   ```bash
   ./mvnw spring-boot:run
   ```

The application will start on `http://localhost:8080`

---

## 📚 API Documentation

### Swagger UI
Interactive API documentation is available at:
```
http://localhost:8080/swagger-ui.html
```

This provides a complete view of all available endpoints and allows you to test API calls directly.

---

## 🔐 Security Features

### Authentication
- **JWT-based Authentication:** Secure token-based authentication for API endpoints
- **Spring Security:** Integrated security framework for request validation and authorization

### Features
- User authentication and authorization
- JWT token generation and validation
- Secure password handling
- Role-based access control (RBAC) support

---

## 📋 Core Features

### Audit Management
- Track and log audit events
- Maintain audit trail for compliance
- Query audit logs with filtering capabilities
- RESTful API endpoints for audit operations

### Data Validation
- Input validation using Spring Validation
- Custom validation annotations
- Error handling and response management

### Database
- MySQL integration via JPA
- Optimized query performance
- Support for complex data relationships

---

## 🏗️ Development

### Directory Structure
```
src/
├── main/
│   ├── java/com/tys/
│   │   ├── controller/         # REST endpoints
│   │   ├── service/            # Business logic
│   │   ├── repository/         # Data access
│   │   ├── model/              # Entity classes
│   │   ├── dto/                # Data Transfer Objects
│   │   ├── security/           # Security configuration
│   │   ├── config/             # Application configuration
│   │   └── AuditProApplication # Main Spring Boot class
│   └── resources/
│       └── application.yml      # Configuration file
└── test/                        # Unit and integration tests
```

### Building & Packaging

**Development Build:**
```bash
./mvnw clean install -DskipTests
```

**Production Build:**
```bash
./mvnw clean install -Pproduction
```

**JAR Execution:**
```bash
java -jar target/AuditPro-0.0.1-SNAPSHOT.jar
```

---

## 🧪 Testing

Run unit and integration tests:
```bash
./mvnw test
```

Run specific test class:
```bash
./mvnw test -Dtest=YourTestClassName
```

---

## 📝 Configuration

### Application Properties
Configure the application through `application.yml` or `application.properties`:

```yaml
spring:
  datasource:
    url: jdbc:mysql://localhost:3306/audit_db
    username: root
    password: password
  jpa:
    hibernate:
      ddl-auto: update
    show-sql: false
  application:
    name: AuditPro

server:
  port: 8080
```

### JWT Configuration
Configure JWT properties in your security configuration for token expiration, secret key, etc.

---

## 🔄 API Endpoints

The application exposes RESTful endpoints for audit operations. Common endpoint patterns:

```
GET    /api/audit/logs           - Retrieve all audit logs
POST   /api/audit/logs           - Create new audit entry
GET    /api/audit/logs/{id}      - Get specific audit log
PUT    /api/audit/logs/{id}      - Update audit entry
DELETE /api/audit/logs/{id}      - Delete audit entry
```

Refer to Swagger UI for complete API documentation.

---

## 🐛 Troubleshooting

### Common Issues

**Database Connection Error:**
- Verify MySQL is running
- Check database credentials in application.properties
- Ensure database exists or auto-create is enabled

**Port Already in Use:**
```bash
./mvnw spring-boot:run -Dspring-boot.run.arguments="--server.port=8081"
```

**Build Failures:**
```bash
./mvnw clean install -U  # Force update dependencies
```

---

## 📄 Dependencies Summary

| Dependency | Version | Purpose |
|-----------|---------|---------|
| Spring Boot | 3.2.12 | Core framework |
| Java | 17 | Runtime |
| MySQL Connector | Latest | Database driver |
| JJWT | 0.11.5 | JWT token handling |
| Lombok | 1.18.30 | Code generation |
| SpringDoc OpenAPI | 2.5.0 | API documentation |

---

## 👨‍💻 Contributing

1. Fork the repository
2. Create a feature branch (`git checkout -b feature/AmazingFeature`)
3. Commit your changes (`git commit -m 'Add some AmazingFeature'`)
4. Push to the branch (`git push origin feature/AmazingFeature`)
5. Open a Pull Request

---

## 📄 License

This project is unlicensed. See the repository for more details.

---

## 👤 Author

**TejaSin07**
- GitHub: [@TejaSin07](https://github.com/TejaSin07)
- Repository: [Audit_App](https://github.com/TejaSin07/Audit_App)

---

## 📞 Support

For issues, questions, or suggestions:
- Open an [Issue](https://github.com/TejaSin07/Audit_App/issues)
- Check existing documentation and Swagger UI

---

## 🗺️ Roadmap

- [ ] Enhanced audit filtering and search capabilities
- [ ] Audit report generation
- [ ] Advanced analytics dashboard
- [ ] Multi-tenancy support
- [ ] Audit event notifications
- [ ] Batch audit operations

---

## 📈 Version

**Current Version:** 0.0.1-SNAPSHOT

**Release Date:** December 26, 2025

---

*Last Updated: May 1, 2026*
