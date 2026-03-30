# 🚗 Dealer & Vehicle Inventory Module (Multi-Tenant)

## 📌 Overview

This project is a **Multi-Tenant Inventory Module** built using **Spring Boot** following **Clean Architecture principles** inside a **Modular Monolith** architecture.

It manages:

* Dealers
* Vehicles
* Tenant-based data isolation
* Admin-level analytics

---

## 🧠 Key Features

* Multi-Tenant Support using `X-Tenant-Id`
* Clean Architecture (Controller → Service → Repository → Domain)
* Dealer & Vehicle CRUD Operations
* Advanced Vehicle Filtering
* Pagination & Sorting
* Role-Based Security (`GLOBAL_ADMIN`)
* Admin Analytics Endpoint

---

## 🏗️ Project Structure

```
com.inventory
│
├── common        # Tenant, security, exceptions
├── dealer        # Dealer module
├── vehicle       # Vehicle module
├── admin         # Admin module
```

---

## 🧱 Technologies Used

* Java 17+
* Spring Boot
* Spring Data JPA
* Spring Security
* Hibernate
* MySQL / PostgreSQL
* Lombok

---

## 🔐 Multi-Tenancy Design

Each request must include:

```
X-Tenant-Id: <tenant-id>
```

### Rules:

* Missing header → **400 Bad Request**
* Cross-tenant access → **403 Forbidden**
* All data is strictly tenant-scoped

---

## 🗄️ Data Model

### Dealer

* id (UUID)
* tenant_id
* name
* email
* subscriptionType → BASIC / PREMIUM

### Vehicle

* id (UUID)
* tenant_id
* dealer_id
* model
* price
* status → AVAILABLE / SOLD

---

## 🔗 API Endpoints

### 🏢 Dealers

| Method | Endpoint      | Description               |
| ------ | ------------- | ------------------------- |
| POST   | /dealers      | Create dealer             |
| GET    | /dealers/{id} | Get dealer                |
| GET    | /dealers      | List dealers (pagination) |
| PATCH  | /dealers/{id} | Update dealer             |
| DELETE | /dealers/{id} | Delete dealer             |

---

### 🚗 Vehicles

| Method | Endpoint       | Description                          |
| ------ | -------------- | ------------------------------------ |
| POST   | /vehicles      | Create vehicle                       |
| GET    | /vehicles/{id} | Get vehicle                          |
| GET    | /vehicles      | List vehicles (filters + pagination) |
| PATCH  | /vehicles/{id} | Update vehicle                       |
| DELETE | /vehicles/{id} | Delete vehicle                       |

---

## 🔍 Vehicle Filtering

Example:

```
GET /vehicles?model=Civic&status=AVAILABLE&priceMin=1000&priceMax=5000
```

Filter by dealer subscription:

```
GET /vehicles?subscription=PREMIUM
```

✔ Returns vehicles whose dealer has `PREMIUM` subscription within the same tenant

---

## 👑 Admin Endpoint

```
GET /admin/dealers/countBySubscription
```

### Response

```json
{
  "BASIC": 10,
  "PREMIUM": 5
}
```

📌 Note:
This count is **GLOBAL (across all tenants)**.

---

## 🔐 Security

* Role-based access using Spring Security
* Admin endpoint requires:

```
ROLE_GLOBAL_ADMIN
```

---

## ⚙️ Pagination & Sorting

Example:

```
GET /vehicles?page=0&size=10&sort=price,desc
```

---

## 🧪 Sample Requests

### Create Dealer

```json
{
  "name": "ABC Motors",
  "email": "abc@gmail.com",
  "subscriptionType": "PREMIUM"
}
```

---

### Create Vehicle

```json
{
  "model": "Civic",
  "price": 5000000,
  "status": "AVAILABLE",
  "dealerId": "UUID_HERE"
}
```

---

## ⚠️ Error Handling

| Scenario              | Response |
| --------------------- | -------- |
| Missing Tenant Header | 400      |
| Cross Tenant Access   | 403      |
| Resource Not Found    | 404      |

---

## 🧠 Design Decisions

* UUID used for global uniqueness
* Tenant enforced at service and query level
* Specification API used for dynamic filtering
* Clean separation of concerns
* Modular monolith design for scalability

---

## 🚀 How to Run

1. Clone the repository
2. Configure database in `application.properties`
3. Run the application

```
mvn spring-boot:run
```

---

## 👨‍💻 Author

Developed as part of a technical assessment for a Java Developer role.

---

## ✅ Conclusion

This project demonstrates:

* Multi-tenant backend design
* Clean architecture implementation
* Secure and scalable REST APIs
* Real-world filtering and query handling

---
