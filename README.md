# 🎓 Sistema de Gestión de Estudiantes - Spring Boot

API REST para la gestión de estudiantes desarrollada con Spring Boot 3.5.6 y Java 17.

## 📋 Descripción

Este proyecto implementa un sistema CRUD completo para la gestión de estudiantes, incluyendo validaciones, manejo de excepciones global, testing y funcionalidades adicionales comentadas para uso en exámenes.

## 🚀 Tecnologías

- **Java 17**
- **Spring Boot 3.5.6**
- **Spring Data JPA**
- **MySQL** (producción)
- **H2** (testing)
- **Gradle**
- **JUnit 5** + AssertJ

## 📦 Estructura del Proyecto

```
src/
├── main/
│   ├── java/edu/espe/springlab/
│   │   ├── SpringLabApplication.java          # Clase principal
│   │   ├── config/
│   │   │   └── WebConfig.java                 # Configuración de interceptores
│   │   ├── domain/
│   │   │   └── Student.java                   # Entidad JPA
│   │   ├── dto/
│   │   │   ├── StudentRequestData.java        # DTO de entrada
│   │   │   └── StudentResponse.java           # DTO de salida
│   │   ├── interceptor/
│   │   │   └── RequestLoggingInterceptor.java # Logging HTTP
│   │   ├── repository/
│   │   │   └── StudentRepository.java         # Repositorio JPA
│   │   ├── service/
│   │   │   ├── StudentService.java            # Interfaz de servicio
│   │   │   └── impl/
│   │   │       └── StudentServiceImpl.java    # Implementación
│   │   ├── validation/                        # Validaciones personalizadas (comentadas)
│   │   └── web/
│   │       ├── advice/
│   │       │   ├── GlobalExceptionHandler.java
│   │       │   ├── NotFoundException.java
│   │       │   └── ConflictException.java
│   │       └── controller/
│   │           └── StudentController.java     # REST Controller
│   └── resources/
│       └── application.yml                    # Configuración
└── test/                                      # Tests unitarios e integración
```

## 🗄️ Modelo de Datos

### Student (Estudiante)

| Campo | Tipo | Restricciones |
|-------|------|---------------|
| id | Long | PK, Auto-generado |
| fullName | String | NOT NULL, max 120 chars |
| email | String | NOT NULL, UNIQUE, max 120 chars |
| birthDate | LocalDate | Opcional |
| active | Boolean | Default: true |

## 🔌 Endpoints Implementados

### Base URL: `/api/students`

| Método | Endpoint | Descripción | Body |
|--------|----------|-------------|------|
| POST | `/` | Crear estudiante | StudentRequestData |
| GET | `/{id}` | Obtener por ID | - |
| GET | `/` | Listar todos | - |
| PATCH | `/{id}/deactivate` | Desactivar estudiante | - |

### 📝 Ejemplos de Uso

**Crear Estudiante:**
```bash
POST http://localhost:8080/api/students
Content-Type: application/json

{
  "fullName": "Juan Pérez",
  "email": "juan.perez@espe.edu.ec",
  "birthDate": "2000-05-15"
}
```

**Obtener por ID:**
```bash
GET http://localhost:8080/api/students/1
```

**Listar Todos:**
```bash
GET http://localhost:8080/api/students
```

**Desactivar:**
```bash
PATCH http://localhost:8080/api/students/1/deactivate
```

## ✅ Validaciones

- **fullName**: Requerido, mínimo 3 caracteres, máximo 120
- **email**: Requerido, formato email válido, máximo 120, único
- **birthDate**: Opcional, formato ISO (yyyy-MM-dd)

## 🛡️ Manejo de Errores

La API retorna respuestas estandarizadas para todos los errores:

| Código | Error | Descripción |
|--------|-------|-------------|
| 400 | Bad Request | Validaciones fallidas |
| 404 | Not Found | Recurso no encontrado |
| 409 | Conflict | Email duplicado |
| 500 | Internal Server Error | Error del servidor |

**Ejemplo de respuesta de error:**
```json
{
  "timestamp": "2025-11-14T10:30:00",
  "status": 409,
  "error": "Conflict",
  "message": "El email ya esta registrado"
}
```

## 🧪 Testing

El proyecto incluye tests de:
- **Repository**: Pruebas de persistencia con H2
- **Service**: Lógica de negocio
- **Integration**: Tests de integración

**Ejecutar tests:**
```bash
./gradlew test
```

## ⚙️ Configuración

### Base de Datos

**Desarrollo (MySQL):**
```yaml
spring:
  datasource:
    url: jdbc:mysql://localhost:3306/spring_lab
    username: root
    password: 
```

**Testing (H2):**
Se configura automáticamente en `src/test/resources/application.yml`

### Puerto del Servidor

Por defecto: `8080`

Cambiar en `application.yml`:
```yaml
server:
  port: 8080
```

## 🚀 Instalación y Ejecución

### Prerrequisitos

- Java 17 o superior
- MySQL 8.0 o superior
- Gradle (incluido wrapper)

### Pasos

1. **Clonar el repositorio:**
```bash
git clone <repository-url>
cd HerreraAlan_Examen
```

2. **Crear base de datos:**
```sql
CREATE DATABASE spring_lab;
```

3. **Configurar credenciales** en `src/main/resources/application.yml`

4. **Ejecutar la aplicación:**
```bash
./gradlew bootRun
```

5. **Acceder a la API:**
```
http://localhost:8080/api/students
```

## 🎯 Funcionalidades Adicionales (Comentadas para Examen)

El proyecto incluye **8 funcionalidades adicionales completamente implementadas pero comentadas**, listas para activar durante un examen:

### Lista de Funcionalidades Preparadas:

| # | Funcionalidad | Tiempo Estimado | Dificultad | Archivos Afectados |
|---|---------------|-----------------|------------|-------------------|
| 1 | **UPDATE (PUT)** - Actualizar estudiante completo | ~15 min | ⭐ Fácil | Controller, Service, ServiceImpl |
| 2 | **Buscar por EMAIL** - Endpoint de búsqueda | ~5 min | ⭐ Muy Fácil | Controller, Service, ServiceImpl |
| 3 | **Listar ACTIVOS** - Filtrar estudiantes activos | ~8 min | ⭐ Fácil | Controller, Service, ServiceImpl, Repository |
| 4 | **Buscar por NOMBRE** - Búsqueda con LIKE | ~10 min | ⭐ Fácil | Controller, Service, ServiceImpl, Repository |
| 5 | **PAGINACIÓN** - Listar con páginas | ~20 min | ⭐⭐ Medio | Controller, Service, ServiceImpl |
| 6 | **DELETE** - Eliminar físicamente | ~5 min | ⭐ Muy Fácil | Controller, Service, ServiceImpl |
| 7 | **CONTAR** - Total de estudiantes | ~3 min | ⭐ Muy Fácil | Controller, Service, ServiceImpl |
| 8 | **ACTIVAR** - Reactivar estudiante | ~5 min | ⭐ Muy Fácil | Controller, Service, ServiceImpl |

**Tiempo total de implementación**: 71 minutos (ideal para examen de 1.5 horas con tiempo para pruebas)

### 📋 Detalles de Cada Funcionalidad:

#### 1️⃣ UPDATE (PUT) - Actualizar Estudiante Completo
**Endpoint**: `PUT /api/students/{id}`

**Request Body**:
```json
{
  "fullName": "Juan Pérez Actualizado",
  "email": "nuevo.email@espe.edu.ec",
  "birthDate": "2000-05-15"
}
```

**Características**:
- Validación de email duplicado (solo si cambió)
- Actualización de todos los campos
- Retorna el estudiante actualizado

---

#### 2️⃣ Buscar por EMAIL
**Endpoint**: `GET /api/students/email/{email}`

**Ejemplo**: `GET /api/students/email/juan.perez@espe.edu.ec`

**Características**:
- Búsqueda exacta por email
- Retorna 404 si no existe

---

#### 3️⃣ Listar ACTIVOS
**Endpoint**: `GET /api/students/active`

**Características**:
- Filtra solo estudiantes con `active=true`
- Usa Query Method de Spring Data JPA: `findByActiveTrue()`

---

#### 4️⃣ Buscar por NOMBRE (LIKE)
**Endpoint**: `GET /api/students/search?name={texto}`

**Ejemplo**: `GET /api/students/search?name=Juan`

**Características**:
- Búsqueda parcial (LIKE)
- Case-insensitive
- Usa Query Method: `findByFullNameContainingIgnoreCase()`

---

#### 5️⃣ PAGINACIÓN
**Endpoint**: `GET /api/students/page?page={num}&size={size}`

**Ejemplo**: `GET /api/students/page?page=0&size=10`

**Características**:
- Paginación con Spring Data
- Parámetros opcionales (defaults: page=0, size=10)
- Retorna objeto `Page<StudentResponse>` con metadata

**Respuesta**:
```json
{
  "content": [...],
  "pageable": {...},
  "totalPages": 5,
  "totalElements": 50,
  "last": false,
  "size": 10,
  "number": 0
}
```

---

#### 6️⃣ DELETE - Eliminar Físicamente
**Endpoint**: `DELETE /api/students/{id}`

**Características**:
- Eliminación física de la base de datos
- Retorna 204 No Content
- Valida existencia antes de eliminar

---

#### 7️⃣ CONTAR Estudiantes
**Endpoint**: `GET /api/students/count`

**Características**:
- Retorna el total de estudiantes
- Usa método `count()` de JpaRepository

**Respuesta**:
```json
42
```

---

#### 8️⃣ ACTIVAR Estudiante
**Endpoint**: `PATCH /api/students/{id}/activate`

**Características**:
- Cambia `active` de `false` a `true`
- Complementa la funcionalidad de desactivar
- Retorna el estudiante actualizado

---

### 🚀 Cómo Activar Durante un Examen:

**Proceso en 4 pasos** (5-20 min según funcionalidad):

1. **Repository** (`StudentRepository.java`):
   - Descomenta los Query Methods necesarios (si aplica)
   - Ejemplo: `List<Student> findByActiveTrue();`

2. **Service Interface** (`StudentService.java`):
   - Descomenta la firma del método
   - Ejemplo: `List<StudentResponse> listActive();`

3. **Service Implementation** (`StudentServiceImpl.java`):
   - Descomenta la implementación completa
   - Ya incluye manejo de excepciones y validaciones

4. **Controller** (`StudentController.java`):
   - Descomenta el endpoint
   - Ya incluye las anotaciones correctas

5. **Agrega Imports** (si es necesario):
   - Tu IDE te ayudará automáticamente
   - Para paginación: `Page`, `Pageable`, `PageRequest`

6. **Prueba** con Postman, Thunder Client o curl

### 💡 Estrategias Recomendadas:

**Si tienes 1.5 horas y el profesor pide implementar funcionalidades:**

**Opción A - Máxima Cantidad** (6-7 funcionalidades fáciles):
- #2 Email (5 min)
- #6 Delete (5 min) 
- #7 Count (3 min)
- #8 Activate (5 min)
- #3 Activos (8 min)
- #4 Búsqueda (10 min)
- #1 Update (15 min)
- **Total**: ~51 min + 30 min tests = **~80 min**

**Opción B - Calidad** (3-4 funcionalidades completas con tests exhaustivos):
- #1 Update (15 min)
- #5 Paginación (20 min)
- #3 Activos (8 min)
- Tests completos (40 min)
- **Total**: ~83 min

**Opción C - Balanceada** (recomendada):
- #1 Update (15 min)
- #3 Activos (8 min)
- #4 Búsqueda (10 min)
- #5 Paginación (20 min)
- Tests básicos (25 min)
- **Total**: ~78 min

### ⚠️ Tips Importantes:

✅ **Lee bien el enunciado** antes de empezar
✅ **Empieza por las fáciles** (#2, #6, #7, #8) para ganar confianza
✅ **Descomenta en orden**: Repository → Service → ServiceImpl → Controller
✅ **Compila frecuentemente** para detectar errores temprano
✅ **Prueba cada endpoint** antes de pasar al siguiente
✅ **Haz commits** después de cada funcionalidad implementada
✅ **Deja tests para el final** si vas justo de tiempo

## 📚 Recursos Adicionales para Funcionalidades Avanzadas

El proyecto incluye **clases auxiliares completamente implementadas pero comentadas** para funcionalidades más complejas:

### 🎯 Validación Personalizada (Dificultad: ⭐⭐⭐)
**Archivos**: `validation/Adult.java` + `validation/AdultValidator.java`

**Qué hace**: Valida que la fecha de nacimiento corresponda a una persona mayor de 18 años

**Implementación**:
```java
@Adult  // Anotación personalizada
private LocalDate birthDate;
```

**Tiempo**: ~25 minutos

**Pasos**:
1. Descomenta `Adult.java` (anotación)
2. Descomenta `AdultValidator.java` (lógica de validación)
3. Aplica `@Adult` en `StudentRequestData.java`
4. Prueba con fechas menores a 18 años (debe fallar)

---

### 🔍 Filtros Dinámicos con Specifications (Dificultad: ⭐⭐⭐)
**Archivo**: `specification/StudentSpecification.java`

**Qué hace**: Permite búsquedas complejas combinando múltiples criterios opcionales

**Endpoint sugerido**: `GET /api/students/filter?name=Juan&active=true&minAge=18`

**Características**:
- Búsqueda por nombre (LIKE)
- Filtro por email
- Filtro por estado activo
- Filtro por edad mínima
- Combinación dinámica de filtros

**Tiempo**: ~30 minutos

**Pasos**:
1. Modificar `StudentRepository` para extender `JpaSpecificationExecutor<Student>`
2. Descomenta `StudentSpecification.java`
3. Implementa método `filterStudents()` en Service
4. Agrega endpoint en Controller

---

### 📦 DTOs Adicionales
**Archivos**: `dto/StudentUpdateRequest.java`, `dto/StudentFilterDTO.java`

**StudentUpdateRequest**: Para actualizaciones parciales (PATCH flexible)
- Solo actualiza campos no nulos
- Útil para endpoints como: `PATCH /api/students/{id}`

**StudentFilterDTO**: Encapsula criterios de búsqueda
- Usado con Specifications
- Hace el código más limpio

**Tiempo**: ~10 minutos (si usas con funcionalidades ya implementadas)

---

### 🗺️ Mapper Pattern
**Archivo**: `mapper/StudentMapper.java`

**Qué hace**: Centraliza la conversión entre Entidades y DTOs

**Beneficios**:
- Código más limpio
- Fácil mantenimiento
- Evita duplicación

**Tiempo**: ~15 minutos

**Pasos**:
1. Descomenta `StudentMapper.java`
2. Inyéctala en `StudentServiceImpl`
3. Reemplaza llamadas a `toResponse()` por `mapper.toResponse()`

---

### 📝 Archivos de Referencia

**GUIA_EXAMEN_FUNCIONALIDADES.md**:
- Guía completa paso a paso
- Errores comunes a evitar
- Checklist antes de entregar
- Tips y estrategias

**IMPLEMENTACIONES_COMENTADAS_SERVICE.java**:
- Todas las implementaciones en un solo archivo
- Útil para copiar rápidamente

**IMPLEMENTACIONES_COMENTADAS_REPOSITORY.java**:
- Todos los Query Methods comentados
- Ejemplos de @Query avanzados

## 🔍 Características Técnicas

### ✅ Implementadas y Activas:
- ✅ Arquitectura en capas (Controller → Service → Repository)
- ✅ Separación de DTOs y Entidades
- ✅ Validación con Jakarta Bean Validation
- ✅ Manejo global de excepciones con @ControllerAdvice
- ✅ Inyección de dependencias por constructor
- ✅ Logging de peticiones HTTP con Interceptor
- ✅ Tests unitarios e integración
- ✅ Configuración externalizada
- ✅ Repository con Query Methods personalizados
- ✅ Manejo de errores estandarizado (JSON)

### 💡 Preparadas para Activar (Comentadas):
- 💡 8 endpoints REST adicionales listos para usar
- 💡 Paginación con Spring Data
- 💡 Búsquedas avanzadas (LIKE, filtros)
- 💡 Validación personalizada de edad
- 💡 Specifications para filtros dinámicos
- 💡 Mapper pattern para DTOs
- 💡 Query Methods de Spring Data JPA
- 💡 Actualización completa y parcial

### 🎓 Ventajas para Exámenes:
- ⚡ Implementación en minutos (solo descomentar)
- 📚 Código documentado y comentado
- ✅ Todo compilado y testeado
- 🎯 Funcionalidades ordenadas por dificultad
- ⏱️ Tiempo estimado por funcionalidad
- 📖 Guías de implementación incluidas

## 👨‍💻 Autor

**Alan Herrera**

## 📄 Licencia

Este proyecto es de uso educativo para la ESPE (Escuela Politécnica del Ejército).

---

**Nota**: Este proyecto está preparado para ser usado en exámenes. Todas las funcionalidades adicionales están comentadas y listas para descomentar según sea necesario.
