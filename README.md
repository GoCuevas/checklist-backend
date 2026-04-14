# 🚢 MAD Checklist Backend

Backend del sistema de checklists de vehículos para el proyecto **MAD (Maritime Agent Dashboard)**.

## 🧠 Descripción

Este servicio permite gestionar inspecciones de vehículos, evaluando su estado operativo en base a checklist estructurado.

El sistema determina automáticamente el estado final del vehículo según reglas de negocio:

* ✅ APROBADO → Todos los ítems OK
* 🟡 OBSERVADO → Existen observaciones
* 🔴 NO_HABILITADO → Ítems no críticos fallan
* ⛔ BLOQUEADO → Falla crítica detectada

## ⚙️ Tecnologías

* Java 17
* Spring Boot
* Hibernate / JPA
* PostgreSQL

## 🔥 Lógica principal

El estado final se calcula en base a:

* Estado de cada ítem (`OK`, `OBSERVACION`, `NO_CUMPLE`)
* Identificación de ítems críticos

```java
boolean fallaCritica = items.stream()
    .anyMatch(item -> item.isEsCritico() && item.getEstado().equalsIgnoreCase("NO_CUMPLE"));
```

## 📡 Endpoint principal

### Crear checklist

POST `/checklists`

#### Body ejemplo:

```json
{
  "vehiculoId": 1,
  "kilometraje": 100000,
  "items": [
    {
      "nombreItem": "Frenos",
      "estado": "NO_CUMPLE",
      "esCritico": true
    }
  ]
}
```

## 📊 Resultado

El sistema:

* Guarda el checklist
* Calcula el estado final
* Actualiza el estado del vehículo
* Habilita o bloquea su uso

## 🚀 Futuras mejoras

* Autenticación de usuarios
* Historial de checklists por vehículo
* Alertas automáticas
* Integración con sistemas externos (clima, tracking, etc.)

---

Desarrollado como parte del ecosistema **MAD**
