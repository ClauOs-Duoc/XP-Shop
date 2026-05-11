XP-Shop
Integrantes del equipo
-Matias Meza
-Claudio Osorio
-Tomas Gallegos

 Tecnologías utilizadas
Java 21
Spring Boot 4.0.6
Spring Data JPA + Hibernate
MySQL
Lombok
Bean Validation (JSR 380)
Maven

Prerrequisitos
Java 21 instalado
MySQL corriendo en localhost:3306
Maven instalado (o usar el wrapper ./mvnw incluido)



git clone https://github.com/ClauOs-Duoc/XP-Shop.git
cd XP-Shop/XP-Shop

La base de datos se crea automaticamente al ejecutar el createDatabaseIfNotExist=true de application.properties.
La aplicación quedará disponible en: http://localhost:8080

Funcionalidades implementadas

CRUD completo para todas las entidades del dominio
Patrón de diseño CSR (Controller → Service → Repository)
Persistencia real con JPA + Hibernate
Validaciones con Bean Validation (@NotBlank, @NotNull, @Email, @Min, @Max, @Size)
Separación entre entidades y DTOs
Manejo centralizado de excepciones con @ControllerAdvice
Respuestas REST con ResponseEntity y códigos HTTP adecuados
Relaciones entre entidades (@OneToMany, @ManyToOne)

ENDPOINTS
Base URL: http://localhost:8080/api/v1
Productos /producto
Grupos de productos /productos
Marcas /marca
Grupos de marcas /marcas
Categorías /categoria
Grupos de categorías /categorias 
Imágenes /imagen
Boletas /boleta
Detalle de boleta /detalleBoleta
Métodos de pago /metodoPago
Métodos de envío /metodoEnvio
Usuarios /usuario
Comunas /comuna
Regiones /region

Para formatear recuento de ids(ejecutar en consulta de Laragon) 
SET FOREIGN_KEY_CHECKS = 0;

TRUNCATE TABLE imagen;
TRUNCATE TABLE detalle_boleta;
TRUNCATE TABLE boleta;
TRUNCATE TABLE usuario;
TRUNCATE TABLE producto;
TRUNCATE TABLE productos;
TRUNCATE TABLE categoria;
TRUNCATE TABLE categorias;
TRUNCATE TABLE marca;
TRUNCATE TABLE marcas;
TRUNCATE TABLE metodo_envio;
TRUNCATE TABLE metodo_pago;
TRUNCATE TABLE comuna;
TRUNCATE TABLE region;

SET FOREIGN_KEY_CHECKS = 1;

Orden recomendado para poblar la base de datos: Región - Comuna - Usuario - MetodoEnvio - MetodoPago - Marcas - Marca - Categorias - Categoria - Productos - Producto - DetalleBoleta - Boleta

Crear región
POST http://localhost:8080/api/v1/region
json
{
  "nombreRegion": "Región Metropolitana"
}
Listar todas
GET http://localhost:8080/api/v1/region

Buscar por ID
GET http://localhost:8080/api/v1/region/1

Actualizar
PUT http://localhost:8080/api/v1/region/1
{
  "nombreRegion": "Región de Valparaíso"
}

Eliminar
DELETE http://localhost:8080/api/v1/region/1

Comuna
Crear comuna (requiere region existente)
POST http://localhost:8080/api/v1/comuna
{
  "nombreComuna": "Santiago",
  "region": { "idRegion": 1 }
}
Listar todas
GET http://localhost:8080/api/v1/comuna

Usuario
Crear usuario (requiere comuna existente)
POST http://localhost:8080/api/v1/usuario
{
  "nombreUsuario": "Juan Pérez",
  "correo": "juan.perez@gmail.com",
  "fechaNacimiento": "2000-05-15",
  "comuna": { "idComuna": 1 }
}
Listar todos
GET http://localhost:8080/api/v1/usuario

Método de Envío
Crear
POST http://localhost:8080/api/v1/metodoEnvio
json{
  "nombreMetodoEnvio": "Despacho a domicilio"
}

Método de Pago
Crear
POST http://localhost:8080/api/v1/metodoPago
json{
  "nombreMetodoPago": "Tarjeta de crédito"
}

 Marcas (grupo)
Crear
POST http://localhost:8080/api/v1/marcas
json{
  "nombreMarcas": "Tecnología"
}

 Marca
Crear (requiere marcas existente)
POST http://localhost:8080/api/v1/marca
json{
  "nombreMarca": "Samsung",
  "marcas": { "idMarcas": 1 }
}

Categorías (grupo)
Crear
POST http://localhost:8080/api/v1/categorias
json{
  "nombreCategorias": "Electrónica"
}

Categoría
Crear (requiere categorias existente)
POST http://localhost:8080/api/v1/categoria
json{
  "nombreCategoria": "Celulares",
  "categorias": { "idCategorias": 1 }
}

Productos (grupo)
Crear
POST http://localhost:8080/api/v1/productos
json{
  "nombreProductos": "Celulares Samsung"
}

 Producto
Crear (requiere marcas, categorias y productos existentes)
POST http://localhost:8080/api/v1/producto
json{
  "nombreProducto": "Samsung Galaxy S24",
  "descripcionProducto": "Smartphone de última generación",
  "precio": 899990,
  "stock": 50,
  "marcas": { "idMarcas": 1 },
  "categorias": { "idCategorias": 1 },
  "productos": { "idProductos": 1 }
}
Listar todos
GET http://localhost:8080/api/v1/producto
Actualizar parcial (PATCH)
PATCH http://localhost:8080/api/v1/producto/1
json{
  "precio": 799990,
  "stock": 45
}
Eliminar
DELETE http://localhost:8080/api/v1/producto/1

Detalle Boleta
Crear (requiere productos existente)
POST http://localhost:8080/api/v1/detalleBoleta
json{
  "cantidad": 2,
  "subtotal": 1799980,
  "productos": { "idProductos": 1 }
}

 Boleta
Crear (requiere usuario, metodoEnvio, metodoPago y detalleBoleta existentes)
POST http://localhost:8080/api/v1/boleta
json{
  "fechaCompra": "2025-05-10",
  "totalCompra": 1799980,
  "usuario": { "idUsuario": 1 },
  "metodoEnvio": { "idMetodoEnvio": 1 },
  "metodoPago": { "idMetodoPago": 1 },
  "detalleBoleta": { "idDetalleBoleta": 1 }
}
Listar todas
GET http://localhost:8080/api/v1/boleta








