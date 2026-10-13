# inventory-management-api

##  📝 Descripción
API RESTful básica desarrollada con Spring Boot para gestión de inventario


## 🚀 Características
- ✅ CRUD de productos
- ✅ Gestión de categorías
- ✅ Registro de usuarios
- ✅ Inicio de sesión
- ✅ Autenticación mediante JWT
- ✅ Autorización basada en roles
- ✅ Roles de `ADMIN` y `EMPLOYEE`
- ✅ Validación de datos
- ✅ DTOs para las respuestas y solicitudes
- ✅ Mapeo de entidades mediante MapStruct
- ✅ Manejo global de excepciones



## 🛠️ Tecnologías utilizadas

| Tecnología      | Uso                            |
| --------------- | ------------------------------ |
| Java 21         | Lenguaje de programación       |
| Spring Boot     | Framework backend              |
| Spring Web      | Desarrollo de API REST         |
| Spring Data JPA | Persistencia y acceso a datos  |
| Spring Security | Autenticación y autorización   |
| JWT             | Autenticación basada en tokens |
| PostgreSQL      | Base de datos                  |
| MapStruct       | Mapeo entre entidades y DTOs   |
| Lombok          | Reducción de código repetitivo |
| Maven           | Gestión de dependencias        |

---

## 🛠️ Requisitos Previos
* **JAVA JDK 21** o superior.
* **Maven 3.9+**.
* **PostgreSQL**.
* **Git**.




## 🏗️ Arquitectura

El proyecto utiliza una arquitectura por capas:

```text
Controller
    ↓
Service
    ↓
Repository
    ↓
PostgreSQL
```

### Capas principales

- **Controller:** Recibe las solicitudes HTTP y devuelve las respuestas de la API.
- **Service:** Contiene la lógica de negocio de la aplicación.
- **Repository:** Se encarga de las operaciones con la base de datos mediante Spring Data JPA.
- **Entity:** Representa las entidades almacenadas en la base de datos.
- **DTO:** Define los objetos utilizados para recibir y devolver información mediante la API.
- **Mapper:** Convierte entidades a DTOs y viceversa utilizando MapStruct.
- **Security:** Contiene la configuración de Spring Security, JWT y autorización.

---

## 📂 Estructura General del proyecto

```text
src/
├── main/
│   ├── java/
│   │   └── io.github.macebot_inventory_maneagment/
│   │       ├── auth/
│   │       ├── category/
│   │       ├── config/
│   │       ├── exceptions/
│   │       ├── product/
│   │       ├── security/
│   │       └── user/
│   │
│   └── resources/
│       └── application.properties
│
└── test/
    └── java/
```

---


---

## 📋 Endpoints

### 📚 Documentación de la API

La API cuenta con documentación interactiva mediante **Swagger UI**, que permite consultar los endpoints disponibles y realizar peticiones directamente desde el navegador.

Una vez iniciada la aplicación, puedes acceder a Swagger UI en:

```text
http://localhost:8080/swagger-ui/index.html
```




### 🔑 Autenticación

| Método | Endpoint         | Descripción          | Autenticación |
| ------ | ---------------- | -------------------- | ------------- |
| `POST` | `/api/auth/register` | Registrar un usuario | ❌             |
| `POST` | `/api/auth/login`    | Iniciar sesión       | ❌             |

### 📦 Productos

| Método   | Endpoint         | Descripción                 | Rol                 |
| -------- | ---------------- | --------------------------- | ------------------- |
| `GET`    | `/api/products`      | Obtener todos los productos | Usuario autenticado |
| `GET`    | `/api/products/{id}` | Obtener un producto         | Usuario autenticado |
| `POST`   | `/api/products`      | Crear un producto           | Usuario autenticado |
| `PUT`    | `/api/products/{id}` | Actualizar un producto      | `ADMIN`             |
| `DELETE` | `/api/products/{id}` | Eliminar un producto        | `ADMIN`             |
| `POST` | `/api/products/sell/{id}/{cantProducts}` | Restar stock por venta       | Usuario autenticado             |

### 🏷️ Categorías

| Método   | Endpoint           | Descripción                  | Rol                 |
| -------- | ------------------ | ---------------------------- | ------------------- |
| `GET`    | `/api/categories`      | Obtener todas las categorías | Usuario autenticado |
| `GET`    | `/api/categories/{id}` | Obtener una categoría        | Usuario autenticado |
| `POST`   | `/api/categories`      | Crear una categoría          | Usuario autenticado |
| `PUT`    | `/api/categories/{id}` | Actualizar una categoría     | `ADMIN`             |
| `DELETE` | `/api/categories/{id}` | Eliminar una categoría       | `ADMIN`             |

---



## ⚙️ Configuración

La aplicación utiliza variables de entorno para configurar la conexión
a PostgreSQL , la clave y tiempo de expiración utilizada para JWT.

### Variables de entorno

| Variable | Descripción | Ejemplo |
|----------|-------------|---------|
| `DB_URL` | URL de conexión a PostgreSQL | `jdbc:postgresql://localhost:5432/inventory` |
| `DB_USERNAME` | Usuario de PostgreSQL | `postgres` |
| `DB_PASSWORD` | Contraseña de PostgreSQL | `tu_contraseña` |
| `SECRET_KEY` | Clave utilizada para firmar los tokens JWT | `tu_clave_secreta` |
| `JWT_EXPIRATION` | Tiempo de expiración de los tokens JWT | 3600000 |

### Configuración en IntelliJ IDEA

En IntelliJ IDEA puedes configurar las variables de entorno desde:

`Run → Edit Configurations → Environment variables`

Agrega las variables necesarias:

```text
DB_URL=jdbc:postgresql://localhost:5432/inventory
DB_USERNAME=postgres
DB_PASSWORD=tu_contraseña
SECRET_KEY=tu_clave_secreta
JWT_EXPIRATION = 3600000
```

### `application.properties`

La aplicación obtiene estos valores desde variables de entorno:

```properties
spring.datasource.url=${DB_URL}
spring.datasource.username=${DB_USERNAME}
spring.datasource.password=${DB_PASSWORD}

jwt.secret=${SECRET_KEY}
jwt.expiration=${JWT_EXPIRATION}
```



## 👨‍💻 Autor

**Kevin Puertos**

Desarrollador novato interesado en backend, Java y desarrollo de aplicaciones con Spring Boot.

- GitHub: [@kevin-puertos](https://github.com/kevin-puertos)
- LinkedIn: [Kevin Puertos](https://linkedin.com/in/kevin-puertos-a09559428/)













