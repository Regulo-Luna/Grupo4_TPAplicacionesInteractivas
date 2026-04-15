# Módulo: Gestión de Eventos y Dashboard

Este módulo permite la gestión de eventos dentro del sistema, incluyendo la creación, consulta y eliminación de los mismos. Además, provee un dashboard con estadísticas que resumen la actividad registrada.

El sistema también incorpora autenticación mediante JWT para proteger los endpoints.

Estructura:

backend/
└── src/main/java/com/uade/dashboard/
    ├── Main.java
    │
    ├── controller/
    │   ├── AuthController.java
    │   ├── EventoController.java
    │   └── DashboardController.java
    │
    ├── model/
    │   ├── Evento.java
    │   ├── Usuario.java
    │   └── Rol.java
    │
    ├── repository/
    │   ├── EventoRepository.java
    │   └── UsuarioRepository.java
    │
    ├── service/
    │   └── DashboardService.java
    │
    ├── security/
    │   ├── JwtAuthFilter.java
    │   ├── JwtUtil.java
    │   └── UserDetailsServiceImpl.java
    │
    ├── config/
    │   └── SecurityConfig.java
    │
    ├── request/
    │   ├── AuthRequest.java
    │   └── RegisterRequest.java
    │
    └── response/
        └── AuthResponse.java
	└── DashboardResponse.java


# Modelo de datos

### Usuario

- id: Long
- nombre: String
- email: String
- password: String
- rol: Enum

### Evento

- id: Long
- tipo: String
- descripcion: String
- fecha: LocalDateTime
- valor: Double
- usuario: relación ManyToOne con Usuario



# Endpoints implementados

### Autenticación

- POST /auth/register  
  Registra un nuevo usuario

- POST /auth/login  
  Autentica un usuario y devuelve un token JWT

### Eventos

- POST /eventos  
  Crea un nuevo evento

- GET /eventos  
  Lista todos los eventos

- GET /eventos/{id}  
  Obtiene un evento por ID

- DELETE /eventos/{id}  
  Elimina un evento

### Dashboard

- GET /dashboard  
  Devuelve estadísticas del sistema:
    - total de usuarios
    - total de eventos
    - eventos del día
    - promedio de eventos por usuario  