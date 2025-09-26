# Flight Reservation System

This project is a **full-stack flight reservation system** built with:

-   **Backend:** Spring Boot (Java 17)
-   **Frontend:** Angular 20
-   **Database:** PostgreSQL

It provides user authentication (login/register with JWT), role-based
access (user/admin), and a CRUD interface for managing flights.

------------------------------------------------------------------------

## 🚀 Prerequisites

Before running the project, ensure you have installed:

-   [Java 17+](https://adoptium.net/)
-   [Node.js 20+](https://nodejs.org/)
-   [Angular CLI 20+](https://angular.io/cli)
-   [PostgreSQL 15+](https://www.postgresql.org/)
-   [Git](https://git-scm.com/)

------------------------------------------------------------------------

## 🗄️ Database Setup

1.  Start PostgreSQL and create a database:

``` sql
CREATE DATABASE flight_reservation;
```

2.  Update the database credentials in:

```{=html}
<!-- -->
```
    backend/src/main/resources/application.properties

Example:

``` properties
spring.datasource.url=jdbc:postgresql://localhost:5432/flight_reservation
spring.datasource.username=postgres
spring.datasource.password=yourpassword
spring.jpa.hibernate.ddl-auto=update
```

------------------------------------------------------------------------

## ⚙️ Backend Setup (Spring Boot)

1.  Navigate to the backend folder:

``` bash
cd backend
```

2.  Build and run the backend:

``` bash
./mvnw spring-boot:run
```

The backend will be available at:\
👉 <http://localhost:8080>

3.  API Documentation is available with Swagger UI:\
    👉 <http://localhost:8080/swagger-ui.html>

------------------------------------------------------------------------

## 🎨 Frontend Setup (Angular)

1.  Navigate to the frontend folder:

``` bash
cd frontend
```

2.  Install dependencies:

``` bash
npm install
```

3.  Run the development server:

``` bash
ng serve -o
```

The frontend will be available at:\
👉 <http://localhost:4200>

------------------------------------------------------------------------

## 🔐 Authentication

-   Register a new user via `/api/auth/register` or Swagger UI.
-   Login via `/api/auth/login` to obtain a **JWT token**.
-   The token is stored in `localStorage` and automatically attached to
    requests.

------------------------------------------------------------------------

## ✈️ Features

-   User registration and login with JWT authentication
-   Role-based access control (User / Admin)
-   CRUD operations for flights (Admin only)
-   View available flights (User)
-   Integrated Swagger UI for API testing

------------------------------------------------------------------------

## 📂 Project Structure

    flight-reservation-system/
    │── backend/        # Spring Boot application
    │── frontend/       # Angular application
    │── README.md       # Project documentation

------------------------------------------------------------------------

## 🛠️ Tech Stack

-   **Spring Boot 3.5.6**
-   **Angular 20**
-   **PostgreSQL 15**
-   **Swagger (OpenAPI 3.0)**
-   **JWT Authentication**

------------------------------------------------------------------------

## 🤝 Contributing

1.  Fork the repository
2.  Create a feature branch (`git checkout -b feature/my-feature`)
3.  Commit your changes (`git commit -m "Add my feature"`)
4.  Push to the branch (`git push origin feature/my-feature`)
5.  Open a Pull Request

------------------------------------------------------------------------

## 📜 License

This project is licensed under the MIT License.


# ✈️ Flight Reservation System

Este proyecto es una aplicación **Fullstack** para reservar vuelos.  
Incluye:

- **Backend:** Spring Boot 3 + Spring Security + JWT + Swagger  
- **Frontend:** Angular 17 + Signals + HttpClient  
- **Base de Datos:** PostgreSQL  

---

## 📦 Requisitos previos

Asegúrate de tener instalados en tu máquina:

- [Java 17](https://adoptium.net/)  
- [Node.js 20+](https://nodejs.org/) y [Angular CLI](https://angular.dev)  
- [PostgreSQL 15+](https://www.postgresql.org/)  
- [Git](https://git-scm.com/)  

---

## 🗄️ Base de Datos (PostgreSQL)

1. Inicia sesión en PostgreSQL:
   ```bash
   psql -U postgres
   ```

2. Crea la base de datos:
   ```sql
   CREATE DATABASE flights_db;
   ```

3. (Opcional) Crear usuario dedicado:
   ```sql
   CREATE USER flights_user WITH PASSWORD 'flights_pass';
   GRANT ALL PRIVILEGES ON DATABASE flights_db TO flights_user;
   ```

4. Ajusta `application.properties` en el backend:
   ```properties
   spring.datasource.url=jdbc:postgresql://localhost:5432/flights_db
   spring.datasource.username=flights_user
   spring.datasource.password=flights_pass
   spring.jpa.hibernate.ddl-auto=update
   spring.jpa.show-sql=true
   ```

---

## ⚙️ Backend (Spring Boot)

1. Ir a la carpeta:
   ```bash
   cd backend
   ```

2. Compilar y ejecutar:
   ```bash
   ./mvnw spring-boot:run
   ```

3. El backend quedará disponible en:
   ```
   http://localhost:8080
   ```

4. Endpoints principales:
   - **Auth**: `POST /api/auth/register`, `POST /api/auth/login`
   - **Flights CRUD**: `GET/POST/PUT/DELETE /api/flights`

5. Documentación con Swagger:
   ```
   http://localhost:8080/swagger-ui/index.html
   ```

---

## 💻 Frontend (Angular)

1. Ir a la carpeta:
   ```bash
   cd frontend
   ```

2. Instalar dependencias:
   ```bash
   npm install
   ```

3. Levantar servidor de desarrollo:
   ```bash
   ng serve -o
   ```

4. El frontend quedará disponible en:
   ```
   http://localhost:4200
   ```

---

## 🔐 Usuarios de prueba

El backend incluye usuarios iniciales para pruebas:

| Usuario | Contraseña | Rol    |
|---------|------------|--------|
| juan    | 1234       | USER   |
| admin   | admin123   | ADMIN  |

- **USER**: puede ver vuelos.  
- **ADMIN**: puede crear, actualizar y eliminar vuelos.  

---

## 🚀 Flujo de la aplicación

1. El usuario se registra o inicia sesión.  
2. El backend responde con un **JWT Token**.  
3. El frontend guarda el token en `localStorage`.  
4. El usuario autenticado puede acceder a `/flights`.  
5. Según el rol, se habilitan más o menos opciones en el CRUD.  

---

## 📌 Notas

- Si aparece error **CORS**, asegúrate de que la configuración en `CorsConfig.java` permita:  
  ```java
  registry.addMapping("/**")
          .allowedOrigins("http://localhost:4200")
          .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS")
          .allowedHeaders("*")
          .allowCredentials(true);
  ```

- Para reiniciar la BD:  
  ```sql
  DROP DATABASE flights_db;
  CREATE DATABASE flights_db;
  ```

---

## 🤝 Contribuir

1. Haz un fork del proyecto.  
2. Crea una rama: `git checkout -b feature/nueva-funcionalidad`  
3. Haz commit: `git commit -m "Agregada nueva funcionalidad"`  
4. Sube la rama: `git push origin feature/nueva-funcionalidad`  
5. Abre un Pull Request 🚀  

---

## 📜 Licencia

Este proyecto es open-source bajo licencia MIT.
