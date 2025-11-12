# 🛒 Productos API
Alumno Facundo Richard
Legajo 51055
## 📝 Descripción del proyecto
Este proyecto consiste en una API REST para la gestión de productos. Permite crear, listar, actualizar y eliminar productos, cada uno con nombre, descripción, precio, stock y categoría.  
El proyecto está desarrollado en **Java con Spring Boot**, utilizando **H2** como base de datos en memoria y **Swagger UI** para la documentación y prueba de endpoints.  

---

## ⚙️ Tecnologías utilizadas
- **Java 21**
- **Spring Boot 3.5.7**
- **Spring Data JPA**
- **H2 Database**
- **Swagger/OpenAPI**
- **Maven** como gestor de dependencias
- **IntelliJ IDEA** como IDE

---

## Endpoints:
| Método | Ruta              | Descripción                      |
| ------ | ----------------- | -------------------------------- |
| GET    | `/productos`      | Listar todos los productos       |
| GET    | `/productos/{id}` | Obtener un producto por ID       |
| POST   | `/productos`      | Crear un nuevo producto          |
| PUT    | `/productos/{id}` | Actualizar un producto existente |
| DELETE | `/productos/{id}` | Eliminar un producto por ID      |
## 🚀 Instrucciones para clonar y ejecutar

1. Clonar el repositorio:
```bash
git clone https://github.com/TU_USUARIO/productos-api.git


