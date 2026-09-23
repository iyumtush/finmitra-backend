# 🪙 FinMitra — Full-Stack AI Financial Adviser & Wealth Platform

<p align="center">
  <img src="https://raw.githubusercontent.com/iyumtush/finmitra_ai/main/public/logo-transparent.png" alt="FinMitra Logo" width="100" style="border-radius: 16px; margin-bottom: 8px;" />
</p>

<p align="center">
  <strong>Production-ready monorepo featuring a high-performance React 18 frontend paired with an enterprise Java Spring Boot 3 REST API backend.</strong>
</p>

<p align="center">
  <img src="https://img.shields.io/badge/Frontend-React%2018%20%7C%20Vite-blue?style=for-the-badge&logo=react" alt="React 18">
  <img src="https://img.shields.io/badge/Backend-Spring%20Boot%203.3.2-brightgreen?style=for-the-badge&logo=springboot" alt="Spring Boot 3">
  <img src="https://img.shields.io/badge/Java-17%20%7C%2021-orange?style=for-the-badge&logo=openjdk" alt="Java">
  <img src="https://img.shields.io/badge/Database-MySQL%20%7C%20PostgreSQL-blue?style=for-the-badge&logo=mysql" alt="Database">
  <img src="https://img.shields.io/badge/AI-Google%20Gemini%203.6%20Flash-purple?style=for-the-badge&logo=google" alt="Gemini">
  <img src="https://img.shields.io/badge/Security-Stateless%20JWT-red?style=for-the-badge&logo=jsonwebtokens" alt="JWT">
</p>

---

## 📁 Repository Structure

This repository is organized into distinct, decoupled `frontend` and `backend` workspaces:

```text
finmitra-backend/
├── frontend/                     # ⚛️ React 18 Single-Page Application
│   ├── src/                      # Components, Views, Hooks, Context, APIs
│   │   ├── api/                  # Backend REST connectors (Auth, Profile, Transactions, AI)
│   │   ├── components/           # ChatBot, Dashboard, Onboarding, Analytics
│   │   └── views/                # AIInsightView, TransactionsView, ProfileView
│   ├── public/                   # Static assets, branding & icons
│   ├── vite.config.js            # Port 3000 config with /api proxy to Spring Boot
│   ├── package.json              # Frontend scripts & dependencies
│   └── .env.example              # Frontend environment template
│
├── backend/                      # ☕ Java Spring Boot 3 Enterprise REST API
│   ├── src/main/java/com/finmitra/
│   │   ├── config/               # SecurityFilterChain, CORS, DataSourceConfig
│   │   ├── controller/           # REST endpoints (Auth, Profile, Transactions, Budgets, AI)
│   │   ├── service/              # Core business logic & Gemini Multi-Key Rotation
│   │   ├── repository/           # Spring Data JPA repositories (MySQL / PostgreSQL)
│   │   ├── entity/               # JPA Entities (User, Transaction, Budget, Category)
│   │   └── security/             # JWT token provider & authentication filter
│   ├── src/main/resources/       # application.yml configuration
│   ├── pom.xml                   # Maven dependencies & build plugins
│   ├── mvnw & mvnw.cmd           # Maven wrapper binaries
│   └── .env.example              # Backend environment template
│
├── package.json                  # Root monorepo runner scripts
├── .gitignore                    # Monorepo ignore rules (strictly protects .env & secrets)
└── README.md                     # Monorepo documentation
```

---

## ⚡ Quick Start (Running Both Concurrently)

### Prerequisites
* **Java 17+** (`java -version`)
* **Node.js 18+** & **npm** (`node -v`, `npm -v`)
* **MySQL 8+** running on `localhost:3306` with database `finmitra_db`

---

### Step 1: Start the Java Spring Boot Backend

```bash
cd backend

# Option A: Run directly using Maven Wrapper
./mvnw spring-boot:run

# Option B: Run with your Gemini API Keys (comma-separated for auto-rotation)
GEMINI_API_KEY="key1,key2,key3,key4" ./mvnw spring-boot:run
```

* 🚀 **Server URL**: [`http://localhost:8085`](http://localhost:8085)
* 💚 **Health Check**: [`http://localhost:8085/health`](http://localhost:8085/health)
* 📖 **Interactive Swagger UI**: [`http://localhost:8085/swagger-ui/index.html`](http://localhost:8085/swagger-ui/index.html)

---

### Step 2: Start the React Frontend

Open a second terminal window:

```bash
cd frontend

# Install dependencies (if first time)
npm install

# Start Vite development server
npm run dev
```

* 🌐 **Web App URL**: [`http://localhost:3000`](http://localhost:3000)
* Any call to `/api/*` is automatically proxied from port `3000` to the Spring Boot backend on port `8085`.

---

## 🛡️ Enterprise Security & Architecture Highlights

### 1. Server-Side AI Key Protection (Zero Client Leakage)
* In standard client-side SPAs, calling external AI APIs from the browser exposes private API keys in the **Chrome DevTools Network Tab**.
* **FinMitra Architecture**: The frontend routes all AI conversations and receipt image OCR via `POST /api/ai/chat` and `POST /api/ai/parse-receipt`.
* The **Spring Boot server** communicates server-to-server with Google Gemini. **Zero API keys are exposed to the browser or network inspector.**

### 2. Multi-Key Auto-Rotation & Fallback
* `AIServiceImpl.java` accepts comma-separated Gemini keys (`GEMINI_API_KEY="key1,key2,key3,key4"`).
* If Google Gemini rate-limits Key 1 (HTTP 429) or exhausts its free quota, the backend **automatically fails over to Key 2, Key 3, and Key 4** in real time without dropping user requests.
* Default model configured to Google's high-speed **`gemini-3.6-flash`**.

### 3. Layered Stateless Architecture
* **Spring Security 6 + JJWT**: Stateless token validation on every protected endpoint.
* **Auto-Schema Migration**: Hibernate dynamically manages schema updates (`users`, `transactions`, `budgets`, `categories`).
* **Multi-DB Agnostic Engine**: `DataSourceConfig.java` detects whether your connection string is MySQL or cloud PostgreSQL (NeonDB, Supabase, AWS RDS) and configures connection pools automatically.

---

## 📡 REST API Reference

| Method | Endpoint | Description | Auth Required |
| :--- | :--- | :--- | :--- |
| `GET` | `/health` | Server health check (`{"status":"UP"}`) | Public |
| `POST` | `/api/auth/signup` | Register a new user account | Public |
| `POST` | `/api/auth/login` | Authenticate and obtain JWT Bearer token | Public |
| `POST` | `/api/ai/chat` | Context-aware AI financial advisory | Public / Optional JWT |
| `POST` | `/api/ai/parse-receipt` | OCR Bill / Receipt parsing via Gemini Vision | Public / Optional JWT |
| `GET` | `/api/profile` | Fetch user profile, salary, EMIs, risk tolerance | `Bearer <JWT>` |
| `PUT` | `/api/profile` | Update financial profile & retirement targets | `Bearer <JWT>` |
| `GET` | `/api/transactions` | Retrieve all user transactions | `Bearer <JWT>` |
| `POST` | `/api/transactions` | Log new income or expense transaction | `Bearer <JWT>` |
| `GET` | `/api/transactions/summary` | Income, expense, and net savings totals | `Bearer <JWT>` |
| `GET` | `/api/budgets` | Retrieve category budget limits & status | `Bearer <JWT>` |
| `POST` | `/api/budgets` | Set or update monthly category budget | `Bearer <JWT>` |

---

## 👨‍💻 Author & Attribution

Developed with passion by **Priyanshu Verma**:
* **GitHub**: [@iyumtush](https://github.com/iyumtush)
* **Live Web App**: [finmitra.vercel.app](https://finmitra.vercel.app)
* **Companion Frontend Repo**: [iyumtush/finmitra_ai](https://github.com/iyumtush/finmitra_ai)
