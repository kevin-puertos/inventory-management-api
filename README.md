# inventory-management-api

##  📝 Descripción
API RESTful básica desarrollada con Spring Boot para gestión de inventario



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
- ✅ Tests unitarios con JUnit y Mockito






