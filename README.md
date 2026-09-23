# ☕ FinMitra Backend — Enterprise Java Spring Boot 3 REST API

<p align="center">
  <img src="https://raw.githubusercontent.com/iyumtush/finmitra_ai/main/public/logo-transparent.png" alt="FinMitra Logo" width="100" style="border-radius: 16px; margin-bottom: 8px;" />
</p>

<p align="center">
  <strong>Production-grade, layered REST API backend for the FinMitra Personal Wealth & AI Advisory Platform.</strong>
</p>

<p align="center">
  <img src="https://img.shields.io/badge/Java-17-orange?style=for-the-badge&logo=openjdk" alt="Java 17">
  <img src="https://img.shields.io/badge/Spring%20Boot-3.3.2-brightgreen?style=for-the-badge&logo=springboot" alt="Spring Boot">
  <img src="https://img.shields.io/badge/Spring%20Security-6.0-green?style=for-the-badge&logo=springsecurity" alt="Spring Security">
  <img src="https://img.shields.io/badge/JWT-JJWT%200.12.6-blue?style=for-the-badge&logo=jsonwebtokens" alt="JWT">
  <img src="https://img.shields.io/badge/Database-MySQL%20%7C%20PostgreSQL-blue?style=for-the-badge&logo=mysql" alt="Database">
  <img src="https://img.shields.io/badge/Documentation-Swagger%20OpenAPI%203-yellow?style=for-the-badge&logo=swagger" alt="Swagger">
</p>

<p align="center">
  🌐 <strong>Looking for the live React Web App?</strong> Check out <a href="https://github.com/iyumtush/finmitra_ai"><strong>finmitra_ai (Frontend)</strong></a>.
</p>

---

## 📌 Table of Contents

- [Overview](#-overview)
- [System Architecture](#-system-architecture)
- [Key Features & Capabilities](#-key-features--capabilities)
- [REST API Endpoints & Swagger UI](#-rest-api-endpoints--swagger-ui)
- [Security & JWT Authentication Flow](#-security--jwt-authentication-flow)
- [Agentic AI & Telemetry Engine](#-agentic-ai--telemetry-engine)
- [Multi-Database Support (MySQL & PostgreSQL)](#-multi-database-support)
- [Getting Started / Local Setup](#-getting-started--local-setup)
- [Docker Containerization](#-docker-containerization)
- [Project Directory Structure](#-project-directory-structure)
- [Author & Contact](#-author--contact)

---

## 🌟 Overview

**FinMitra Backend** is an enterprise-grade RESTful API built with **Java 17**, **Spring Boot 3.3.2**, and **Spring Security 6**. It provides the core financial ledger, category budgeting, user persona persistence, and **context-aware Agentic AI financial telemetry** for the FinMitra platform.

Built with clean architecture principles, this backend demonstrates:
- **Layered Architecture**: Strict separation of Controller, Service, Repository, Entity, and DTO layers.
- **Stateless JWT Security**: Custom `JwtAuthenticationFilter` with BCrypt password hashing.
- **Smart Data Access**: Spring Data JPA with auto-generating schema updates and custom JPQL queries.
- **Multi-Database Agnostic Engine**: Dynamic auto-switching between local MySQL and cloud PostgreSQL.
- **Interactive Documentation**: Embedded **Swagger UI / OpenAPI 3.0** documentation with 1-click Bearer token authorization.

---

## 🏗️ System Architecture

```text
┌─────────────────────────────────────────────────────────────┐
│                    HTTP Clients / React SPA                 │
└──────────────────────────────┬──────────────────────────────┘
                               │ (Bearer Token / JSON)
                               ▼
┌─────────────────────────────────────────────────────────────┐
│                  Spring Security 6 Filter Chain             │
│   ├── CorsConfigurationSource (Cross-Origin Policy)         │
│   ├── JwtAuthenticationFilter (Stateless Bearer Interceptor)│
│   └── JwtAuthenticationEntryPoint (401 Unauthorized Handler)│
└──────────────────────────────┬──────────────────────────────┘
                               │
                               ▼
┌─────────────────────────────────────────────────────────────┐
│                       Controller Layer                      │
│   AuthController | TransactionController | BudgetController │
│   ProfileController | CategoryController | AIController     │
└──────────────────────────────┬──────────────────────────────┘
                               │ (DTO Ingestion & Validation)
                               ▼
┌─────────────────────────────────────────────────────────────┐
│                    Service & Business Logic                 │
│   AuthService | TransactionService | BudgetService          │
│   ProfileService | AIService (Gemini Telemetry Injector)    │
└──────────────────────────────┬──────────────────────────────┘
                               │ (Spring Data JPA)
                               ▼
┌─────────────────────────────────────────────────────────────┐
│                     Repository & ORM Layer                  │
│   UserRepository | TransactionRepository | BudgetRepository │
└──────────────────────────────┬──────────────────────────────┘
                               │ (Hibernate SQL Dialect)
                               ▼
┌─────────────────────────────────────────────────────────────┐
│                  Relational Database Storage                │
│            MySQL 8+ / PostgreSQL 15+ (Dual Engine)          │
└─────────────────────────────────────────────────────────────┘
```

---

## ✨ Key Features & Capabilities

### 1. 🔐 Stateless Authentication & Authorization
- **Signup (`POST /api/auth/signup`)**: Validates email format, verifies uniqueness in the database, and securely hashes passwords using **BCrypt** with salt.
- **Login (`POST /api/auth/login`)**: Authenticates credentials and returns a signed **JWT Bearer Token** (valid for 24 hours).
- **Stateless Filter**: Every request is intercepted by `JwtAuthenticationFilter`, which parses the bearer token, validates the HMAC-SHA256 signature, and injects user identity into the `SecurityContextHolder`.

### 2. 💸 Financial Transactions Ledger (CRUD)
- Full CRUD operations for income and expenses linked to the authenticated user ID.
- Categorization across standard and custom categories (*Food*, *Rent*, *Travel*, *Utilities*, *Shopping*, *Health*, etc.).
- Auto-chronological sorting (`OrderByDateDescIdDesc`).

### 3. 🎯 Goal-Oriented Category Budgeting
- Set and manage monthly spend limits per category.
- Real-time spent calculations dynamically aggregating all user expenses for the current period against budget thresholds.

### 4. 👤 Financial Persona & Profile Engine
- Stores user age, monthly income, fixed costs, active EMIs, risk appetite (*Conservative*, *Moderate*, *Aggressive*), and retirement target age.
- Direct synchronization with the AI engine for customized financial planning.

### 5. 🤖 Context-Aware AI Telemetry & Receipt Vision OCR
- **Live Cash Flow Telemetry**: Queries database records to aggregate net savings, savings rate, and top category expenditures before consulting Google Gemini.
- **Deterministic Offline Rules Engine**: When offline or if external AI limits occur, a built-in mathematical engine answers loan/EMI affordability questions using the **35% Safe EMI threshold**:
  $$\text{Max Safe EMI} = \text{Net Monthly Savings} \times 0.35$$
- **Multimodal Receipt OCR**: `POST /api/ai/parse-receipt` accepts base64 receipt images, sends them to Gemini Vision OCR, and extracts store name, total, category, and date into structured JSON.

---

## 📖 REST API Endpoints & Swagger UI

### 🖥️ Interactive Swagger UI
When running locally, open:
👉 **`http://localhost:8085/swagger-ui/index.html`**

Click the green **"Authorize"** button and paste your Bearer token to test any protected endpoint directly from your browser!

### Endpoint Summary Table

| Category | Method | Endpoint | Access | Description |
| :--- | :---: | :--- | :---: | :--- |
| **Auth** | `POST` | `/api/auth/signup` | Public | Register new user account |
| **Auth** | `POST` | `/api/auth/login` | Public | Authenticate user & return JWT Bearer token |
| **Profile** | `GET` | `/api/profile` | Protected | Get financial persona (age, income, goals, EMIs) |
| **Profile** | `PUT` | `/api/profile` | Protected | Update financial persona & risk parameters |
| **Transactions**| `GET` | `/api/transactions` | Protected | Get all transactions for current user |
| **Transactions**| `POST` | `/api/transactions` | Protected | Create new income or expense transaction |
| **Transactions**| `PUT` | `/api/transactions/{id}` | Protected | Update existing transaction by ID |
| **Transactions**| `DELETE`| `/api/transactions/{id}` | Protected | Delete transaction by ID |
| **Budgets** | `GET` | `/api/budgets` | Protected | Get all category budgets with spent amounts |
| **Budgets** | `POST` | `/api/budgets` | Protected | Set or update category budget spending cap |
| **Categories** | `GET` | `/api/categories` | Protected | List all available expense categories |
| **Categories** | `POST` | `/api/categories` | Protected | Create a custom user category |
| **AI Advisor** | `GET` | `/api/ai/insights` | Protected | Generate AI financial health summary |
| **AI Advisor** | `POST` | `/api/ai/chat` | Protected | Chat with context-aware AI financial adviser |
| **AI Vision** | `POST` | `/api/ai/parse-receipt` | Protected | Parse receipt image via Gemini Vision OCR |
| **System** | `GET` | `/health` | Public | Healthcheck monitor endpoint |

---

## 🗄️ Multi-Database Support

FinMitra features a smart **`DataSourceConfig.java`** that automatically inspects connection strings at runtime and loads the appropriate JDBC driver and dialect:

- **MySQL 8+**: Default for local development (`jdbc:mysql://localhost:3306/finmitra_db`).
- **PostgreSQL / NeonDB / Supabase**: Automatically recognized when a `jdbc:postgresql://` or cloud connection string is provided.

---

## ⚡ Getting Started / Local Setup

### Prerequisites
- **Java**: JDK 17 or higher
- **Maven**: 3.8+ (or use the included `./mvnw`)
- **MySQL Server** (running locally on port 3306)

### 1. Database Setup (MySQL)
Log in to MySQL and create the database:
```sql
CREATE DATABASE finmitra_db;
```

### 2. Configure Environment Variables
Copy `.env.example` to `.env` or set environment variables:
```bash
cp .env.example .env
```
Edit `.env` (or configure in `src/main/resources/application.yml`):
```properties
DATABASE_URL=jdbc:mysql://localhost:3306/finmitra_db
SPRING_DATASOURCE_USERNAME=root
SPRING_DATASOURCE_PASSWORD=your_mysql_password
GEMINI_API_KEY=your_gemini_api_key
```

### 3. Build & Run
Run with the Maven wrapper:
```bash
./mvnw clean spring-boot:run
```
Or with Maven installed:
```bash
mvn clean spring-boot:run
```

- **Backend Base URL**: `http://localhost:8085`
- **Swagger Documentation**: `http://localhost:8085/swagger-ui/index.html`

---

## 🐳 Docker Containerization

To run FinMitra Backend inside a lightweight Docker container:

```bash
# 1. Build the Docker Image
docker build -t finmitra-backend .

# 2. Run the Container
docker run -p 8085:8085 \
  -e DATABASE_URL="jdbc:mysql://host.docker.internal:3306/finmitra_db" \
  -e SPRING_DATASOURCE_USERNAME="root" \
  -e SPRING_DATASOURCE_PASSWORD="password" \
  -e GEMINI_API_KEY="your-gemini-key" \
  finmitra-backend
```

---

## 📁 Project Directory Structure

```text
finmitra-backend/
├── pom.xml                               # Maven project dependencies & build plugins
├── Dockerfile                            # Multi-stage production container build
├── mvnw & mvnw.cmd                       # Maven command wrapper scripts
├── .env.example                          # Environment variable configuration template
├── .gitignore                            # Standard Java, Maven, and OS ignore rules
│
└── src/
    ├── main/
    │   ├── resources/
    │   │   └── application.yml           # Database, JWT, Port, and Gemini configuration
    │   └── java/com/finmitra/
    │       ├── FinMitraApplication.java  # Main application entry point
    │       │
    │       ├── config/                   # Security, Multi-DataSource, and Seed Data
    │       │   ├── SecurityConfig.java
    │       │   ├── DataSourceConfig.java
    │       │   └── DataInitializer.java
    │       │
    │       ├── controller/               # REST API Controllers
    │       │   ├── AuthController.java
    │       │   ├── TransactionController.java
    │       │   ├── BudgetController.java
    │       │   ├── CategoryController.java
    │       │   ├── ProfileController.java
    │       │   ├── AIController.java
    │       │   └── HealthController.java
    │       │
    │       ├── entity/                   # JPA Database Entities
    │       │   ├── User.java
    │       │   ├── Transaction.java
    │       │   ├── Budget.java
    │       │   └── Category.java
    │       │
    │       ├── repository/               # Spring Data JPA Repositories
    │       │   ├── UserRepository.java
    │       │   ├── TransactionRepository.java
    │       │   ├── BudgetRepository.java
    │       │   └── CategoryRepository.java
    │       │
    │       ├── security/                 # Stateless JWT Implementation
    │       │   ├── JwtTokenProvider.java
    │       │   ├── JwtAuthenticationFilter.java
    │       │   ├── JwtAuthenticationEntryPoint.java
    │       │   └── CustomUserDetailsService.java
    │       │
    │       ├── service/ & impl/          # Service Layer Business Logic
    │       │   ├── AuthService.java & AuthServiceImpl.java
    │       │   ├── TransactionService.java & TransactionServiceImpl.java
    │       │   ├── BudgetService.java & BudgetServiceImpl.java
    │       │   ├── ProfileService.java & ProfileServiceImpl.java
    │       │   ├── CategoryService.java & CategoryServiceImpl.java
    │       │   └── AIService.java & AIServiceImpl.java
    │       │
    │       ├── dto/                      # Data Transfer Objects
    │       │   ├── AuthResponse.java, LoginRequest.java, SignupRequest.java
    │       │   ├── TransactionRequest.java, TransactionResponse.java
    │       │   ├── BudgetRequest.java, BudgetResponse.java
    │       │   ├── UserProfileDto.java
    │       │   ├── ReceiptParseRequest.java, ReceiptParseResponse.java
    │       │   └── ChatRequest.java, ChatResponse.java, AIInsightResponse.java
    │       │
    │       └── exception/                # Global Exception Handling
    │           ├── GlobalExceptionHandler.java
    │           ├── APIException.java
    │           └── ErrorDetails.java
```

---

## 👨‍💻 Author & Contact

- **Developer**: Tushar ([@iyumtush](https://github.com/iyumtush))
- **Role Focus**: Full-Stack Software Engineer / Java Backend Developer
- **GitHub**: [github.com/iyumtush](https://github.com/iyumtush)
- **Live Frontend App**: [github.com/iyumtush/finmitra_ai](https://github.com/iyumtush/finmitra_ai)

---

<p align="center">
  <sub>Built with ❤️ using Java 17, Spring Boot 3, and Spring Security.</sub>
</p>
