Estrucutura correcta:
en Post: En branding_negocio
{
  "id_tenants": {
    "id_tenants": 1
  },
  "logo_url": "https://cdn.bellarista.pe/logos/bellarista.png",
  "color_primario": "#FF6B9D",
  "color_secundario": "#FFFFFF",
  "nombre_visible": "Bellarista",
  "redes_sociales": "{\"instagram\":\"@bellarista\",\"facebook\":\"BellaristaPe\"}"
}

Estructura PUT correcta: esto es en branding_negocio
{
  "id_branding_negocio": 1,
  "id_tenants": {
    "id_tenants": 1
  },
  "logo_url": "https://cdn.bellarista.pe/logos/bellarista-new.png",
  "color_primario": "#E91E63",
  "color_secundario": "#F8F9FA",
  "nombre_visible": "Bellarista Premium",
  "redes_sociales": "{\"instagram\":\"@bellarista_oficial\",\"tiktok\":\"@bellarista\"}"
}


ya sigue los lineamientos de la base de datos:

corregir los de abajo
# POST y PUT - Controllers de la imagen

Base URL sugerida: `https://belleza.spring.informaticapp.com:2451`

## 1) GastoRecurrenteController
Endpoint: `/api/gastos_recurrentes`

POST
```json
{
	"id_tenants": { "id_tenants": 1 },
	"id_sedes": { "id_sedes": 1 },
	"concepto": "Alquiler local sede central",
	"monto": 3500.00,
	"frecuencia": "mensual",
	"id_proveedores": { "id_proveedores": 1 },
	"estado": 1
}
```

PUT
```json
{
	"id_gastos_recurrentes": 1,
	"id_tenants": { "id_tenants": 1 },
	"id_sedes": { "id_sedes": 1 },
	"concepto": "Alquiler local actualizado",
	"monto": 3600.00,
	"frecuencia": "mensual",
	"fecha_proximo_registro": "2026-08-01",
	"estado": 1
}
```

## 2) HorarioOperacionController
Endpoint: `/api/horarios_operacion`

POST
```json
{
	"id_sedes": { "id_sedes": 1 },
	"dia_semana": "lunes",
	"hora_apertura": "09:00:00",
	"hora_cierre": "20:00:00",
	"estado": 1
}
	"id_proveedores": { "id_proveedores": 1 },

PUT
```json
{
	"id_horarios_operacion": 1,
	"id_sedes": { "id_sedes": 1 },
	"dia_semana": "lunes",
	"hora_apertura": "08:30:00",
	"hora_cierre": "20:30:00",
	"estado": 1
}
```

## 3) LoteInventarioController
Endpoint: `/api/lotes_inventario`

POST
	"id_usuarios": { "id_usuarios": 1 },
{
	"id_productos": { "id_productos": 1 },
	"id_almacenes": { "id_almacenes": 1 },
	"numero_lote": "LOT-2026-001",
	"cantidad": 100,
	"cantidad_disponible": 100,
	"precio_unitario": 18.50,
	"fecha_vencimiento": "2027-12-31",
	"id_proveedores": { "id_proveedores": 1 },
	"fecha_ingreso": "2026-06-02",
	"observaciones": "Ingreso inicial",
	"estado": 1
}
```

PUT
```json
{
	"id_lotes_inventario": 1,
	"id_productos": { "id_productos": 1 },
	"id_usuarios": { "id_usuarios": 1 },
	"numero_lote": "LOT-2026-001-A",
	"cantidad": 120,
	"cantidad_disponible": 110,
	"precio_unitario": 19.00,
	"fecha_vencimiento": "2027-12-31",
	"id_proveedores": { "id_proveedores": 1 },
	"fecha_ingreso": "2026-06-02",
	"observaciones": "Ajuste de lote",
	"estado": 1
}
```

## 4) MarcaController
Endpoint: `/api/marcas`

POST
```json
{
	"id_tenants": { "id_tenants": 1 },
	"nombre_marca": "Marca Demo",
	"pais_origen": "Peru",
	"logo_url": "https://cdn.demo.com/marca.png",
	"descripcion": "Marca de prueba",
	"estado": 1
}
```

PUT
```json
{
	"id_marcas": 1,
	"id_tenants": { "id_tenants": 1 },
	"nombre_marca": "Marca Demo Actualizada",
	"pais_origen": "Peru",
	"logo_url": "https://cdn.demo.com/marca-new.png",
	"descripcion": "Descripcion actualizada",
	"estado": 1
}
```

## 5) MetodoPagoController
Endpoint: `/api/metodos_pago`

POST
```json
{
	"id_tenants": { "id_tenants": 1 },
	"nombre_metodo_de_pago": "Transferencia bancaria",
	"tipo": "transferencia",
	"descripcion": "Transferencia interbancaria",
	"estado": 1
}
```

PUT
```json
{
	"id_metodos_pago": 1,
	"id_tenants": { "id_tenants": 1 },
	"nombre_metodo_de_pago": "Transferencia inmediata",
	"tipo": "transferencia",
	"descripcion": "Transferencia con confirmacion instantanea",
	"estado": 1
}
```

## 6) MovimientoInventarioController
Endpoint: `/api/movimientos_inventario`

POST
```json
{
	"id_lotes_inventario": { "id_lotes_inventario": 1 },
	"tipo_movimiento": "entrada",
	"cantidad": 50,
	"id_usuarios": { "id_usuarios": 1 },
	"motivo": "Compra a proveedor",
	"referencia_documento": "OC-2026-001",
	"fecha_movimiento": "2026-06-02T10:30:00"
}
```

PUT
```json
{
	"id_movimientos_inventario": 1,
	"id_lotes_inventario": { "id_lotes_inventario": 1 },
	"tipo_movimiento": "ajuste",
	"cantidad": 45,
	"id_usuarios": { "id_usuarios": 1 },
	"motivo": "Correccion de inventario",
	"referencia_documento": "AJ-2026-001",
	"fecha_movimiento": "2026-06-02T11:00:00"
}
```

## 7) NotificacionController
Endpoint: `/api/notificaciones`

POST
```json
{
	"id_tenants": { "id_tenants": 1 },
	"id_usuarios": { "id_usuarios": 1 },
	"tipo": "alerta",
	"titulo": "Stock bajo",
	"mensaje": "El producto TIN-001 esta por debajo del stock minimo",
	"canal_envio": "app",
	"estado_lectura": "no_leido",
	"fecha_lectura": null
}
```

PUT
```json
{
	"id_notificaciones": 1,
	"id_tenants": { "id_tenants": 1 },
	"id_usuarios": { "id_usuarios": 1 },
	"tipo": "alerta",
	"titulo": "Stock bajo - actualizado",
	"mensaje": "Se actualizo la alerta de stock",
	"canal_envio": "email",
	"estado_lectura": "leido",
	"fecha_lectura": "2026-06-02T12:00:00"
}
```

## 8) OrdenCompraController
Endpoint: `/api/ordenes_compra`

POST
```json
{
	"id_tenants": { "id_tenants": 1 },
	"id_proveedores": { "id_proveedores": 1 },
	"numero_orden": "OC-2026-010",
	"fecha_orden": "2026-06-02",
	"fecha_entrega_estimada": "2026-06-10",
	"estado": 1,
	"monto_total": 540.00,
	"condiciones_entrega": "Entrega en almacen principal",
	"forma_pago": "transferencia",
	"notas": "Pedido urgente"
}
```

PUT
```json
{
	"id_ordenes_compra": 1,
	"id_tenants": { "id_tenants": 1 },
	"id_proveedores": { "id_proveedores": 1 },
	"numero_orden": "OC-2026-010",
	"fecha_orden": "2026-06-02",
	"fecha_entrega_estimada": "2026-06-12",
	"estado": 1,
	"monto_total": 560.00,
	"condiciones_entrega": "Entrega en almacen principal - puerta 2",
	"forma_pago": "transferencia",
	"notas": "Se agrego un item"
}
```

## 9) PagoProveedorController
Endpoint: `/api/pagos_proveedor`

POST
```json
{
	"id_cuentas_por_pagar": { "id_cuentas_por_pagar": 1 },
	"monto_pagado": 300.00,
	"forma_pago": "transferencia",
	"numero_comprobante": "TRX-000123",
	"fecha_pago": "2026-06-02",
	"id_usuarios": { "id_usuarios": 1 },
	"observaciones": "Pago parcial"
}
```

PUT
```json
{
	"id_pagos_proveedor": 1,
	"id_cuentas_por_pagar": { "id_cuentas_por_pagar": 1 },
	"monto_pagado": 320.00,
	"forma_pago": "transferencia",
	"numero_comprobante": "TRX-000123-A",
	"fecha_pago": "2026-06-02",
	"id_usuarios": { "id_usuarios": 1 },
	"observaciones": "Pago parcial ajustado"
}
```

## 10) PedidoController
Endpoint: `/api/pedidos`

POST
```json
{
	"id_tenants": { "id_tenants": 1 },
	"id_clientes": { "id_clientes": 1 },
	"numero_pedido": "PED-2026-001",
	"modalidad": "delivery",
	"estado": 1,
	"subtotal": 100.00,
	"costo_envio": 10.00,
	"impuesto": 18.00,
	"total": 128.00,
	"direccion_entrega": "Av. Brasil 123",
	"id_zonas_delivery": { "id_zonas_delivery": 1 },
	"id_usuarios": { "id_usuarios": 2 },
	"fecha_pedido": "2026-06-02T14:00:00",
	"fecha_entrega_estimada": "2026-06-02T15:00:00",
	"fecha_entrega_real": null
}
```

PUT
```json
{
	"id_pedidos": 1,
	"id_tenants": { "id_tenants": 1 },
	"id_clientes": { "id_clientes": 1 },
	"numero_pedido": "PED-2026-001",
	"modalidad": "delivery",
	"estado": 1,
	"subtotal": 110.00,
	"costo_envio": 10.00,
	"impuesto": 19.80,
	"total": 139.80,
	"direccion_entrega": "Av. Brasil 123 - Dpto 4",
	"id_zonas_delivery": { "id_zonas_delivery": 1 },
	"id_usuarios": { "id_usuarios": 2 },
	"fecha_pedido": "2026-06-02T14:00:00",
	"fecha_entrega_estimada": "2026-06-02T15:30:00",
	"fecha_entrega_real": null
}
```

## 11) PermisoRolController
Endpoint: `/api/permisos_rol`

POST
```json
{
	"id_roles_personalizados": { "id_roles_personalizados": 1 },
	"modulo": "ventas",
	"accion": "visualizar",
	"recurso": "sesiones_caja",
	"estado": 1
}
```

PUT
```json
{
	"id_permisos_rol": 1,
	"id_roles_personalizados": { "id_roles_personalizados": 1 },
	"modulo": "ventas",
	"accion": "editar",
	"recurso": "sesiones_caja",
	"estado": 1
}
```

## Notas rapidas

- En relaciones (`id_tenants`, `id_sedes`, etc.) enviar objeto con su ID interno.
- En PUT incluye siempre el ID principal del registro.
- Para fecha: `LocalDate` usa `YYYY-MM-DD` y `LocalDateTime` usa `YYYY-MM-DDTHH:mm:ss`.
