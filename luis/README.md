# Prueba Técnica - Inditex

## Cómo ejecutar el proyecto

El proyecto incluye Maven Wrapper, por lo que no es necesario tener Maven instalado. Desde la terminal en la raíz del proyecto ejecuta:

**Para arrancar la aplicación:**
```bash
./mvnw spring-boot:run
```
*(O usando `mvnw.cmd spring-boot:run` si estás en consola de Windows pura)*

**Para ejecutar los tests (incluyen los 5 casos solicitados):**
```bash
./mvnw test
```

## Documentación API

Una vez arrancado el proyecto en el puerto 8080:
- Swagger UI: http://localhost:8080/swagger-ui/index.html
- OpenAPI YAML: http://localhost:8080/v3/api-docs.yaml

## Base de Datos (H2)

La aplicación utiliza una base de datos en memoria H2 que se inicializa automáticamente al arrancar. Puedes inspeccionarla directamente desde el navegador:

- **Consola Web:** http://localhost:8080/h2-console
- **JDBC URL:** `jdbc:h2:mem:testdb`
- **Usuario:** `sa`
- **Contraseña:** *(dejar en blanco)*

## Arquitectura

La arquitectura de la API es una arquitectura hexagonal simplificada, ya que no hay carpetas de puertos como tal, pero sí una separación de responsabilidades:

- Controller: En esta carpeta se encuentra todo lo relacionado con la entrada y salida de datos.
- Domain: En esta carpeta se encuentra todo lo relacionado con la lógica de negocio, así como los puertos de entrada y salida de datos.
- Infrastructure: En esta carpeta se encuentra todo lo relacionado con la infraestructura, como la base de datos, servicios externos, etc.