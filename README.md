# 🚧 Checklist Vehicular - Backend

API REST para la gestión de checklists pre-operacionales de vehículos en entornos industriales (minería, logística, transporte).

---

## 🚀 Funcionalidades

* Registro de vehículos
* Creación de checklist por vehículo
* Evaluación automática:

  * 🟢 APROBADO (sin fallas críticas)
  * 🔴 RECHAZADO (con fallas críticas)
* Identificación de ítems críticos
* Registro de observaciones por ítem
* Actualización automática del estado del vehículo (habilitado / bloqueado)

---

## 🛠️ Tecnologías

* Java 21
* Spring Boot
* Spring Data JPA / Hibernate
* PostgreSQL
* Maven

---

## 📦 Estructura del proyecto

* `controller` → Endpoints REST
* `service` → Lógica de negocio
* `repository` → Acceso a datos
* `model` → Entidades JPA
* `dto` → Transferencia de datos

---

## 🔌 Endpoints principales

### Vehículos

* `GET /vehiculos` → Listar vehículos
* `POST /vehiculos` → Crear vehículo
* `GET /vehiculos/{id}/estado` → Estado del vehículo (habilitado/bloqueado)

### Checklists

* `POST /checklists` → Crear checklist
* `GET /checklists/{id}` → Obtener checklist

---

## 📥 Ejemplo de request

```json
{
  "vehiculoId": 1,
  "kilometraje": 120000,
  "items": [
    {
      "nombreItem": "Frenos",
      "estado": "FAIL",
      "esCritico": true
    }
  ]
}
```

---

## 📤 Ejemplo de respuesta

```json
{
  "estadoFinal": "RECHAZADO",
  "vehiculo": {
    "habilitado": false
  }
}
```

---

## 🌐 Frontend

Interfaz web disponible en:

👉 https://github.com/GoCuevas/checklist-frontend

---

## 🎯 Estado del proyecto

MVP funcional con lógica de negocio completa, listo para integración y despliegue en la nube.

---

## 🚀 Próximas mejoras

* Subida de imágenes (evidencia)
* Historial de checklists
* Autenticación de usuarios
* Dashboard de monitoreo
* Integración con sistemas externos

---
