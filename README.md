# Cosmetics Platform Microservices

[![Build Status](https://img.shields.io/badge/build-passing-brightgreen)](https://jenkins.yourcompany.com)
[![Quality Gate Status](https://img.shields.io/badge/quality%20gate-passed-brightgreen)](https://sonarqube.yourcompany.com)
[![Coverage](https://img.shields.io/badge/coverage-85%25-brightgreen)](https://sonarqube.yourcompany.com)

A comprehensive microservices architecture implementing cosmetics product and brand management with full DevOps integration, monitoring, and security.

## 🏗️ Architecture Overview

This project demonstrates a production-ready microservices architecture with the following key components:

```
┌─────────────────┐    ┌─────────────────┐    ┌─────────────────┐
│ Gateway Service │────│   Keycloak      │────│   Eureka        │
│   (Port: 8888)  │    │ (Port: 9098)    │    │ (Port: 8761)    │
└─────────┬───────┘    └─────────────────┘    └─────────────────┘
          │
          ▼
┌─────────────────┐    ┌─────────────────┐    ┌─────────────────┐
│Products Service │────│ Brands Service  │────│ Config Server   │
│ (Port: 8085)    │    │ (Port: 8082)    │    │ (Port: 8088)    │
│   (MongoDB)     │    │   (MySQL)       │    └─────────────────┘
└─────────┬───────┘    └─────────┬───────┘
          │                      │
          └──────────┬───────────┘
                     ▼
      ┌─────────────────┐    ┌─────────────────┐
      │     Kafka       │────│    Kafdrop      │
      │  (Port: 9092)   │    │  (Port: 9009)   │
      └─────────────────┘    └─────────────────┘
```

## 🚀 Features

### Core Services
- **Products Service**: Complete cosmetics product lifecycle management with MongoDB persistence
- **Brands Service**: Cosmetics brand management and relationships with MySQL database
- **Service Discovery**: Eureka server for automatic service registration and discovery
- **Gateway Service**: Centralized routing, load balancing, and cross-cutting concerns

### Communication Patterns
- **Synchronous**: OpenFeign with Circuit Breaker pattern for resilient service-to-service calls
- **Asynchronous**: Apache Kafka for event-driven architecture and loose coupling
- **Kafka UI**: Kafdrop for Kafka cluster monitoring and management

### DevOps & Infrastructure
- **Configuration Management**: Centralized configuration with Spring Cloud Config Server
- **Security**: OAuth2/OIDC authentication and authorization via Keycloak
- **Monitoring**: Comprehensive observability with Prometheus and Grafana
- **Quality Assurance**: Multi-layer testing with SonarQube analysis
- **Artifact Management**: Nexus repository for dependency and artifact management
- **CI/CD**: Jenkins pipeline with automated deployment

## 📋 Prerequisites

- Docker & Docker Compose (v20.10+)
- Java 17+
- Maven 3.8+
- Git
- Minimum 8GB RAM
- 20GB free disk space

## 🛠️ Technology Stack

| Component | Technology | Version |
|-----------|------------|---------|
| **Backend Framework** | Spring Boot | 3.2.x |
| **Service Discovery** | Netflix Eureka | 4.0.x |
| **API Gateway** | Spring Cloud Gateway | 4.0.x |
| **Configuration** | Spring Cloud Config | 4.0.x |
| **Circuit Breaker** | Resilience4j | 2.1.x |
| **Message Broker** | Apache Kafka, Zookeeper | 3.6, 3.8 |
| **Kafka UI** | Kafdrop | Latest |
| **Databases** | MongoDB, MySQL | Latest, 8.0 |
| **Authentication** | Keycloak | 24.0.2 |
| **Monitoring** | Prometheus, Grafana | Latest |
| **Code Quality** | SonarQube | 9.9 LTS |
| **Repository** | Nexus | 3.x |
| **CI/CD** | Jenkins | 2.4.x |
| **Containerization** | Docker, Docker Compose | Latest |

## ⚡ Quick Start

### 1. Clone the Repository
```bash
git clone https://github.com/Zaoui-Douaa/microservices-project.git
cd microservices-project
```

### 2. Start All Services
```bash
# Start the complete cosmetics platform
docker-compose up -d

# Verify all services are running
docker-compose ps
```

### 3. Verify Deployment
```bash
# Check service registration in Eureka
curl http://localhost:8761/eureka/apps

# Test API endpoints through gateway
curl http://localhost:8888/api/products
curl http://localhost:8888/api/brands

# Check Kafka topics in Kafdrop
open http://localhost:9009
```

## 📁 Project Structure

```
cosmetics-platform-microservices/
├── products-service/        # Products microservice (MongoDB)
├── brands-service/          # Brands microservice (MySQL)
├── config-server/           # Configuration server
├── eureka-server/           # Service discovery
├── gateway-service/         # API Gateway
├── keycloak/
│   └── realms/                  # Keycloak realm configurations
├── monitoring/
│   ├── prometheus/              # Prometheus configuration
│   │   └── prometheus.yml
│   ├── grafana-datasources/     # Grafana data sources
│   └── grafana-dashboards/      # Pre-built dashboards
├── docker-compose.yml          # Main compose file
├── jenkins/
│   └── Jenkinsfile             # CI/CD pipeline
│   └── scripts
└── README.md
```

## 🔧 Configuration

### Service Ports
| Service | Port | Description |
|---------|------|-------------|
| Gateway Service | 8888 | Main entry point |
| Eureka Server | 8761 | Service registry UI |
| Products Service | 8085 | Products APIs |
| Brands Service | 8082 | Brands APIs |
| Config Server | 8088 | Configuration management |
| Keycloak | 9098 | Authentication server |
| MongoDB | 27017 | Products database |
| MySQL | 3306 | Brands database |
| Kafka | 9092 | Message broker |
| Kafdrop | 9009 | Kafka UI |
| Zookeeper | 2181 | Kafka coordination |
| Grafana | 3000 | Monitoring dashboard |
| Prometheus | 9090 | Metrics collection |
| SonarQube | 9000 | Code quality |
| Nexus | 8081 | Artifact repository |

## 🔒 Security

### Authentication Flow
1. Client requests authentication from Keycloak
2. Keycloak validates credentials and issues JWT token
3. Client includes JWT in API requests
4. API Gateway validates token with Keycloak
5. Valid requests are forwarded to microservices

### Default Users
| Username | Password | Role |
|----------|----------|------|
| admin | admin | Administrator |

## 📊 Monitoring & Observability

### Grafana Dashboards
- **Service Health**: Overall system health and availability
- **Performance Metrics**: Response times, throughput, error rates
- **Infrastructure**: CPU, memory, disk usage
- **Business Metrics**: Product/brand creation rates, user activity

### Key Metrics
- Service response times (P50, P95, P99)
- Error rates and circuit breaker status
- Database connection pools
- Kafka consumer lag
- JVM memory and garbage collection

## 🧪 Testing Strategy

### Test Pyramid
1. **Unit Tests** : Fast, isolated component testing
2. **Integration Tests** : Service integration and database testing
3. **Functional Tests** : End-to-end workflow validation

### Quality Gates
- **Code Coverage**: Minimum 80%
- **Duplicated Lines**: <3%
- **Maintainability Rating**: A
- **Reliability Rating**: A
- **Security Rating**: A

### Running Tests
```bash
mvn clean package
# Unit tests
mvn test
```

## 🚀 CI/CD Pipeline

### Jenkins Pipeline Stages

## 🔧 Development

### Local Development Setup
```bash
# Start dependencies only (databases, kafka, keycloak)
docker-compose up -d --build

```

### API Documentation
- **Swagger UI**: http://localhost:8888/swagger-ui.html
- **OpenAPI Spec**: http://localhost:8888/v3/api-docs
- **Kafdrop (Kafka UI)**: http://localhost:9009

### Adding New Services
1. Create service directory (e.g., `/api/products`)
2. Add Dockerfile and service configuration
3. Update docker-compose.yml with new service
4. Configure service discovery and dependencies
5. Add monitoring and health checks

### Code Standards
- Follow Java coding conventions
- Maintain test coverage >80%
- Update documentation for new features
- Add appropriate logging and monitoring


## 📞 Support

- **Email**: zaouidoa9@gmail.com

---