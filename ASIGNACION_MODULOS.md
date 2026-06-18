# 📋 ASIGNACIÓN DE MÓDULOS - PROYECTO LP_III

## Fecha: 2026-06-18
---

## 🎯 MÓDULO 1: Gestión de Usuarios y Seguridad
**Asignado a:** 👤 **LEONARDO**

### Entidades:
- `Usuarios.java` 
- `RolPersonalizado.java`
- `PermisoRol.java`
- `UsuarioSede.java`
- `PreferenciaUsuario.java`
- `Tenants.java`
- `Auditoria.java`
- `Registros.java`

### Descripción:
Módulo base de autenticación, control de acceso y auditoría. Todas las entidades tienen relación FK con Tenants y Usuarios.

### Dependencias:
- Base para todos los módulos

### Prioridad: 🔴 **ALTA** (debe estar listo primero)

---

## 📦 MÓDULO 2: Productos e Inventario
**Asignado a:** 🧑‍💼 **BRAYAM**

### Entidades:
- `Producto.java` (core)
- `CategoriaProducto.java`
- `Marca.java`
- `Almacen.java`
- `LoteInventario.java` 
- `MovimientoInventario.java`
- `ComboPromocional.java`
- `ComposicionCombo.java`

### Descripción:
Gestión completa de catálogo de productos, categorías, inventario y promociones combinadas.

### Relaciones principales:
- `Producto` → `CategoriaProducto` + `Marca`
- `LoteInventario` → `Producto`
- `MovimientoInventario` → `Producto` + `Almacen`
- `ComboPromocional` → `ComposicionCombo` → `Producto`

### Dependencias:
- Módulo 1 (Usuarios)
- Necesario para: Módulos 4 y 5

### Prioridad: 🟠 **ALTA**

---

## 👥 MÓDULO 3: Clientes y Contacto
**Asignado a:** 🎯 **DYOGO**

### Entidades:
- `Cliente.java` (core)
- `Repartidor.java`
- `ZonaDelivery.java`
- `Cita.java`
- `Notificacion.java`
- `Sede.java`
- `HorarioOperacion.java`

### Descripción:
Gestión de relaciones con clientes, distribuidores, zonas de entrega y programación de citas.

### Relaciones principales:
- `Cliente` → múltiples `Venta` + `Pedido`
- `Cita` → `Cliente` + `ServicioBelleza`
- `Repartidor` → `Pedido` + `ZonaDelivery`
- `Notificacion` → `Cliente`

### Dependencias:
- Módulo 1 (Usuarios)
- Necesario para: Módulos 4 y 5

### Prioridad: 🟠 **ALTA**

---

## 💰 MÓDULO 4: Ventas y Pedidos
**Asignado a:** 💳 **MARCO**

### Entidades:
- `Venta.java` (core)
- `DetalleVenta.java`
- `Pedido.java` (core)
- `DetallePedido.java`
- `DevolucionVenta.java`
- `DetalleDevolucionVenta.java`
- `FormaPagoVenta.java`
- `ComprobanteElectronico.java`
- `SerieComprobante.java`

### Descripción:
Procesamiento completo de ventas, pedidos, devoluciones y comprobantes electrónicos.

### Relaciones principales:
- `Venta` → `DetalleVenta` → `Producto` (Módulo 2)
- `Venta` → `Cliente` (Módulo 3) + `SesionCaja` (Módulo 5) + `Usuarios` (Módulo 1)
- `Pedido` → `DetallePedido` → `Producto` (Módulo 2)
- `Pedido` → `Cliente` (Módulo 3) + `Repartidor` (Módulo 3)
- `DevolucionVenta` → `DetalleDevolucionVenta`
- `ComprobanteElectronico` → `SerieComprobante`

### Dependencias:
- Módulo 1 (Usuarios)
- Módulo 2 (Productos)
- Módulo 3 (Clientes)
- Módulo 5 (Finanzas - SesionCaja)

### Prioridad: 🟡 **MEDIA-ALTA**

---

## 📊 MÓDULO 5: Compras y Finanzas
**Asignado a:** 🏦 **YOANLU**

### Entidades:
- `OrdenCompra.java` (core)
- `DetalleOrdenCompra.java`
- `Proveedor.java`
- `ProveedorCategoria.java`
- `DevolucionProveedor.java`
- `DetalleDevolucionProveedor.java`
- `PagoProveedor.java`
- `CuentaPorPagar.java`
- `GastoOperativo.java`
- `GastoRecurrente.java`
- `CajaChica.java`
- `SesionCaja.java`
- `MetodoPago.java`

### Descripción:
Gestión completa de compras, proveedores, pagos, gastos y tesorería.

### Relaciones principales:
- `OrdenCompra` → `DetalleOrdenCompra` → `Producto` (Módulo 2)
- `OrdenCompra` → `Proveedor` + `ProveedorCategoria`
- `DevolucionProveedor` → `DetalleDevolucionProveedor`
- `PagoProveedor` → `Proveedor` + `CuentaPorPagar`
- `SesionCaja` → `CajaChica` + `MetodoPago`
- `GastoOperativo` + `GastoRecurrente` → `SesionCaja`
- `SesionCaja` ← `Venta` (Módulo 4)

### Dependencias:
- Módulo 1 (Usuarios)
- Módulo 2 (Productos)
- Necesario para: Módulo 4

### Prioridad: 🟠 **ALTA**

---

## 🔄 ENTIDADES TRANSVERSALES (Compartidas)

Estas entidades se usan en múltiples módulos. Los dueños deben coordinar:

| Entidad | Dueño Principal | Usuarios | Razón |
|---------|-----------------|----------|-------|
| `Tenants` | LEONARDO | Todos | Multi-tenancy |
| `Usuarios` | LEONARDO | Todos | Auditoría |
| `Sede` | DYOGO | MARCO, YOANLU | Ubicación |
| `Producto` | BRAYAM | MARCO, YOANLU | Catálogo |
| `SesionCaja` | YOANLU | MARCO | Transacciones |
| `Promocion` | BRAYAM | MARCO | Marketing |
| `CategoriaServicio` | DYOGO | (servicio) | Servicios |
| `ServicioBelleza` | DYOGO | (servicio) | Detalles |
| `ServicioCita` | DYOGO | MARCO | Citas |
| `BrandingNegocio` | LEONARDO | (config) | Settings |
| `ConfiguracionGlobal` | LEONARDO | (config) | Settings |
| `FacturaSuscripcion` | YOANLU | MARCO | Facturación |
| `Suscripcion` | LEONARDO | MARCO | Planes |
| `PlanSuscripcion` | LEONARDO | (config) | Planes |
| `PreciosPlan` | LEONARDO | (config) | Precios |

---

## 📈 Orden de Implementación Recomendado

### **Fase 1: Bases (Semana 1)**
1. LEONARDO - Módulo 1 (Usuarios y Seguridad) ⏱️
2. BRAYAM - Módulo 2 (Productos) ⏱️

### **Fase 2: Operaciones (Semana 2)**
3. DYOGO - Módulo 3 (Clientes) ⏱️
4. YOANLU - Módulo 5 (Compras y Finanzas) ⏱️

### **Fase 3: Integración (Semana 3)**
5. MARCO - Módulo 4 (Ventas y Pedidos) ⏱️

---

## 🔗 Comunicación entre equipos

### **LEONARDO** se coordina con:
- ✅ Todos (proporciona Usuarios, Tenants, Auditoria)

### **BRAYAM** se coordina con:
- ✅ MARCO (Producto → DetalleVenta/DetallePedido)
- ✅ YOANLU (Producto → DetalleOrdenCompra)

### **DYOGO** se coordina con:
- ✅ MARCO (Cliente → Venta/Pedido, Repartidor → Pedido)
- ✅ YOANLU (ZonaDelivery)

### **MARCO** se coordina con:
- ✅ BRAYAM (Productos)
- ✅ DYOGO (Clientes, Repartidor)
- ✅ YOANLU (SesionCaja)

### **YOANLU** se coordina con:
- ✅ BRAYAM (Productos)
- ✅ MARCO (SesionCaja → Venta)

---

## 📌 Notas Importantes

- Crear branches siguiendo el patrón: `feature/modulo-<nombre>` (ej: `feature/modulo-usuarios-leonardo`)
- Los PRs deben incluir todas las entidades del módulo
- Mantener la nomenclatura de bases de datos consistente (snake_case)
- Los getters/setters deben generarse automáticamente (Lombok recomendado)
- Coordinar con Leonardo para las anotaciones JPA y validaciones globales

---

## ✅ Checklist por Módulo

### Cada persona debe:
- [ ] Crear rama feature del módulo
- [ ] Implementar todas las entidades
- [ ] Agregar validaciones necesarias
- [ ] Incluir getters/setters completos
- [ ] Documentar relaciones FK
- [ ] Crear PR con descripción del módulo
- [ ] Coordinar con equipos dependientes
- [ ] Realizar code review

---

**Generado:** 2026-06-18
**Por:** Sistema de Asignación Automática
