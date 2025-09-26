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
