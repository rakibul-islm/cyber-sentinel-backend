# 🔐 Cyber Sentinel (Web Security Toolkit)

An interactive web-based security toolkit built with **Spring Boot 3**, designed to help developers and testers explore essential security concepts:

- 🛡️ **SQL Injection Detection**
- 🔑 **Password Strength Checker**
- 🔐 **Password Encryption & Decryption**

---

## 📌 Features

### 🛡️ SQL Injection Detection

Scan URLs with query parameters using common SQL injection payloads to identify potential vulnerabilities.

**Features:**
- GET parameter scanning
- Built-in payload list
- Clear vulnerability indicators

**Usage:**
```http
GET /cyber-sentinel/vulnerability/scan?url=http://example.com/products?id=1
```

## ⚙️ Build, Run & Deployment (Spring Boot 3 + Gradle)

### 🧰 Prerequisites

- Java 17 or higher
- Gradle 7+
- (Optional) Docker for deployment

---

### 🛠️ Build the Project

```bash
./gradlew clean build


