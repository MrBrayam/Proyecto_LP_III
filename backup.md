# Inserts de ejemplo (5 filas por tabla)

> Cada tabla tiene un INSERT con 5 filas, siguiendo el esquema de bellarista_mysql.sql.

## planes_suscripcion
```sql
INSERT INTO planes_suscripcion (
  id_planes_suscripcion, nombre_plan_suscripcion, descripcion,
  precio_mensual, precio_trimestral, precio_anual, periodo, estado
) VALUES
  (1, 'Plan Basico', 'Acceso basico', 49.90, 139.90, 499.90, 'mensual', 1),
  (2, 'Plan Estudio', 'Para emprendimientos', 79.90, 219.90, 799.90, 'trimestral', 1),
  (3, 'Plan Pro', 'Para equipos', 119.90, 329.90, 1199.90, 'mensual', 1),
  (4, 'Plan Premium', 'Cobertura completa', 159.90, 449.90, 1599.90, 'anual', 1),
  (5, 'Plan Enterprise', 'Alto volumen', 199.90, 569.90, 1999.90, 'mensual', 1);
```

## tenants
```sql
INSERT INTO tenants (
  id_tenants, razon_social, ruc, direccion_fiscal, correo, telefono,
  nombre_comercial, tipo_negocio, estado, fecha_registro
) VALUES
  (1, 'Bellarista Centro SAC', '10000000001', 'Av. Central 123', 'centro@bellarista.pe', '900111111', 'Bellarista Centro', 'salon', 1, '2026-05-01 09:00:00'),
  (2, 'Bellarista Norte SAC', '10000000002', 'Jr. Norte 456', 'norte@bellarista.pe', '900222222', 'Bellarista Norte', 'spa', 1, '2026-05-02 09:00:00'),
  (3, 'Bellarista Sur SAC', '10000000003', 'Av. Sur 789', 'sur@bellarista.pe', '900333333', 'Bellarista Sur', 'barberia', 1, '2026-05-03 09:00:00'),
  (4, 'Bellarista Este SAC', '10000000004', 'Calle Este 101', 'este@bellarista.pe', '900444444', 'Bellarista Este', 'salon', 1, '2026-05-04 09:00:00'),
  (5, 'Bellarista Oeste SAC', '10000000005', 'Av. Oeste 202', 'oeste@bellarista.pe', '900555555', 'Bellarista Oeste', 'spa', 1, '2026-05-05 09:00:00');
```

## branding_negocio
```sql
INSERT INTO branding_negocio (
  id_branding_negocio, id_tenants, logo_url, color_primario,
  color_secundario, nombre_visible, redes_sociales
) VALUES
  (1, 1, 'https://img.example.com/logo1.png', '#111111', '#EEEEEE', 'Bellarista Centro', '{"ig":"@bellarista_centro"}'),
  (2, 2, 'https://img.example.com/logo2.png', '#222222', '#DDDDDD', 'Bellarista Norte', '{"fb":"/bellarista.norte"}'),
  (3, 3, 'https://img.example.com/logo3.png', '#333333', '#CCCCCC', 'Bellarista Sur', '{"tt":"@bellarista_sur"}'),
  (4, 4, 'https://img.example.com/logo4.png', '#444444', '#BBBBBB', 'Bellarista Este', '{"yt":"BellaristaEste"}'),
  (5, 5, 'https://img.example.com/logo5.png', '#555555', '#AAAAAA', 'Bellarista Oeste', '{"web":"https://bellarista.pe"}');
```

## categorias_productos
```sql
INSERT INTO categorias_productos (
  id_categorias_productos, id_tenants, nombre_categoria_producto, descripcion,
  imagen_url, orden, estado
) VALUES
  (1, 1, 'Cuidado Capilar', 'Shampoo y tratamientos', 'https://img.example.com/capilar.png', 1, 1),
  (2, 2, 'Coloracion', 'Tinturas y oxidantes', 'https://img.example.com/color.png', 2, 1),
  (3, 3, 'Barberia', 'Cuidado masculino', 'https://img.example.com/barber.png', 3, 1),
  (4, 4, 'Manicura', 'Uñas y esmaltes', 'https://img.example.com/manicura.png', 4, 1),
  (5, 5, 'Skincare', 'Cuidado facial', 'https://img.example.com/skin.png', 5, 1);
```

## clientes
```sql
INSERT INTO clientes (
  id_clientes, id_tenants, nombre_cliente, apellidos_clientes,
  tipo_documento, numero_documento, telefono, correo, direccion,
  distrito, tipo_cliente, estado
) VALUES
  (1, 1, 'Ana', 'Lopez', 'DNI', '70111111', '911111111', 'ana@correo.com', 'Av. Uno 101', 'Lima', 'regular', 1),
  (2, 2, 'Bruno', 'Ramos', 'DNI', '70222222', '922222222', 'bruno@correo.com', 'Jr. Dos 202', 'Lima', 'frecuente', 1),
  (3, 3, 'Carla', 'Diaz', 'RUC', '20333333333', '933333333', 'carla@correo.com', 'Calle Tres 303', 'Lima', 'vip', 1),
  (4, 4, 'Diego', 'Vega', 'Pasaporte', 'P4444444', '944444444', 'diego@correo.com', 'Av. Cuatro 404', 'Lima', 'regular', 1),
  (5, 5, 'Elena', 'Suarez', 'DNI', '70555555', '955555555', 'elena@correo.com', 'Jr. Cinco 505', 'Lima', 'frecuente', 1);
```

## combos_promocionales
```sql
INSERT INTO combos_promocionales (
  id_combos_promocionales, id_tenants, nombre_promocion, descripcion,
  precio_combo, precio_original, fecha_inicio, fecha_fin,
  visible_storefront, estado
) VALUES
  (1, 1, 'Combo Capilar', 'Shampoo + tratamiento', 59.90, 79.90, '2026-05-01', '2026-06-01', 1, 1),
  (2, 2, 'Combo Color', 'Tinte + oxidante', 89.90, 109.90, '2026-05-02', '2026-06-02', 1, 1),
  (3, 3, 'Combo Barber', 'Cera + aceite', 49.90, 69.90, '2026-05-03', '2026-06-03', 1, 1),
  (4, 4, 'Combo Manicura', 'Esmalte + removedor', 39.90, 49.90, '2026-05-04', '2026-06-04', 1, 1),
  (5, 5, 'Combo Facial', 'Limpiador + hidratante', 79.90, 99.90, '2026-05-05', '2026-06-05', 1, 1);
```

## configuracion_global
```sql
INSERT INTO configuracion_global (
  id_configuracion_global, id_tenants, clave, valor, tipo_valor, descripcion
) VALUES
  (1, 1, 'moneda', 'PEN', 'string', 'Moneda por defecto'),
  (2, 2, 'igv', '0.18', 'numerico', 'Impuesto general'),
  (3, 3, 'notificaciones', 'true', 'booleano', 'Notificaciones activas'),
  (4, 4, 'terminos', '{"version":1}', 'json', 'Version de terminos'),
  (5, 5, 'zona_horaria', 'America/Lima', 'string', 'Zona horaria');
```

## configuracion_tienda_online
```sql
INSERT INTO configuracion_tienda_online (
  id_configuracion_tienda_online, id_tenants, titulo_tienda, descripcion_tienda,
  politica_compra, condiciones_compra, informacion_contacto,
  horarios_compra_activos, estado_tienda, mensaje_estado
) VALUES
  (1, 1, 'Tienda Centro', 'Productos capilares', 'Sin devoluciones', 'Pago con tarjeta', '{"telefono":"900111111"}', 1, 'activa', NULL),
  (2, 2, 'Tienda Norte', 'Coloracion profesional', 'Devolucion 7 dias', 'Entrega 48h', '{"telefono":"900222222"}', 1, 'activa', NULL),
  (3, 3, 'Tienda Sur', 'Productos barberia', 'Sin devoluciones', 'Recojo en tienda', '{"telefono":"900333333"}', 1, 'mantenimiento', 'Actualizando catalogo'),
  (4, 4, 'Tienda Este', 'Manicura', 'Devolucion 3 dias', 'Entrega 24h', '{"telefono":"900444444"}', 1, 'activa', NULL),
  (5, 5, 'Tienda Oeste', 'Skincare', 'Sin devoluciones', 'Pago al recibir', '{"telefono":"900555555"}', 0, 'desactivada', 'Temporalmente cerrada');
```

## marcas
```sql
INSERT INTO marcas (
  id_marcas, id_tenants, nombre_marca, pais_origen, logo_url, descripcion, estado
) VALUES
  (1, 1, 'Marca A', 'Peru', 'https://img.example.com/marca1.png', 'Linea profesional', 1),
  (2, 2, 'Marca B', 'Chile', 'https://img.example.com/marca2.png', 'Coloracion premium', 1),
  (3, 3, 'Marca C', 'Mexico', 'https://img.example.com/marca3.png', 'Barberia clasica', 1),
  (4, 4, 'Marca D', 'Colombia', 'https://img.example.com/marca4.png', 'Uñas y belleza', 1),
  (5, 5, 'Marca E', 'Brasil', 'https://img.example.com/marca5.png', 'Skincare natural', 1);
```

## metodos_pago
```sql
INSERT INTO metodos_pago (
  id_metodos_pago, id_tenants, nombre_metodo_de_pago, tipo, descripcion, estado
) VALUES
  (1, 1, 'Efectivo', 'efectivo', 'Pago en caja', 1),
  (2, 2, 'Visa', 'tarjeta_credito', 'Tarjeta credito', 1),
  (3, 3, 'Mastercard', 'tarjeta_debito', 'Tarjeta debito', 1),
  (4, 4, 'Transferencia', 'transferencia', 'Transferencia bancaria', 1),
  (5, 5, 'Yape', 'billetera_digital', 'Pago por app', 1);
```

## proveedores
```sql
INSERT INTO proveedores (
  id_proveedores, id_tenants, razon_social, ruc, direccion, telefono, correo,
  persona_contacto, terminos_comerciales, categorias_productos, estado
) VALUES
  (1, 1, 'Distribuidora Uno', '20111111111', 'Av. Proveedor 101', '910111111', 'prov1@correo.com', 'Luis Perez', '30 dias', 'capilar', 1),
  (2, 2, 'Distribuidora Dos', '20222222222', 'Jr. Proveedor 202', '910222222', 'prov2@correo.com', 'Maria Ruiz', '15 dias', 'coloracion', 1),
  (3, 3, 'Distribuidora Tres', '20333333333', 'Calle Proveedor 303', '910333333', 'prov3@correo.com', 'Carlos Diaz', 'contado', 'barberia', 1),
  (4, 4, 'Distribuidora Cuatro', '20444444444', 'Av. Proveedor 404', '910444444', 'prov4@correo.com', 'Rosa Soto', '30 dias', 'manicura', 1),
  (5, 5, 'Distribuidora Cinco', '20555555555', 'Jr. Proveedor 505', '910555555', 'prov5@correo.com', 'Jorge Lima', '15 dias', 'skincare', 1);
```

## roles_personalizados
```sql
INSERT INTO roles_personalizados (
  id_roles_personalizados, id_tenants, nombre_rol_personalizado, descripcion, estado
) VALUES
  (1, 1, 'Administrador', 'Acceso total', 1),
  (2, 2, 'Cajero', 'Gestion de caja', 1),
  (3, 3, 'Recepcion', 'Agenda y clientes', 1),
  (4, 4, 'Especialista', 'Servicios especializados', 1),
  (5, 5, 'Gerente', 'Reportes y control', 1);
```

## sedes
```sql
INSERT INTO sedes (
  id_sedes, id_tenants, nombre_sede, direccion, distrito, telefono,
  horario_apertura, horario_cierre, responsable, estado
) VALUES
  (1, 1, 'Sede Centro', 'Av. Central 123', 'Lima', '900111111', '09:00:00', '19:00:00', 'Ana Lopez', 1),
  (2, 2, 'Sede Norte', 'Jr. Norte 456', 'Lima', '900222222', '09:00:00', '19:00:00', 'Bruno Ramos', 1),
  (3, 3, 'Sede Sur', 'Av. Sur 789', 'Lima', '900333333', '09:00:00', '19:00:00', 'Carla Diaz', 1),
  (4, 4, 'Sede Este', 'Calle Este 101', 'Lima', '900444444', '09:00:00', '19:00:00', 'Diego Vega', 1),
  (5, 5, 'Sede Oeste', 'Av. Oeste 202', 'Lima', '900555555', '09:00:00', '19:00:00', 'Elena Suarez', 1);
```

## series_comprobantes
```sql
    INSERT INTO series_comprobantes (
    id_series_comprobantes, id_tenants, tipo_comprobante, punto_emision, numero_serie,
    numero_correlativo, numero_proximo, fecha_autorizacion, fecha_vencimiento, estado
    ) VALUES
    (1, 1, 'boleta', 1, 'B001', 100, 101, '2026-05-01', '2027-05-01', 1),
    (2, 2, 'factura', 1, 'F001', 200, 201, '2026-05-01', '2027-05-01', 1),
    (3, 3, 'nota_credito', 1, 'NC01', 10, 11, '2026-05-01', '2027-05-01', 1),
    (4, 4, 'nota_debito', 1, 'ND01', 20, 21, '2026-05-01', '2027-05-01', 1),
    (5, 5, 'boleta', 2, 'B002', 300, 301, '2026-05-01', '2027-05-01', 1);
```

## servicios_belleza
```sql
INSERT INTO servicios_belleza (
  id_servicios_belleza, id_tenants, nombre_servicio_belleza, descripcion,
  categoria, duracion_minima, duracion_maxima, precio_base, precio_editable, estado
) VALUES
  (1, 1, 'Corte Dama', 'Corte y peinado', 'cabello', 30, 60, 40.00, 1, 1),
  (2, 2, 'Coloracion', 'Tinte completo', 'color', 60, 120, 120.00, 1, 1),
  (3, 3, 'Barba', 'Perfilado de barba', 'barberia', 20, 40, 30.00, 1, 1),
  (4, 4, 'Manicure', 'Uñas clasicas', 'unas', 30, 60, 35.00, 1, 1),
  (5, 5, 'Limpieza Facial', 'Limpieza profunda', 'facial', 45, 90, 80.00, 1, 1);
```

## suscripciones
```sql
INSERT INTO suscripciones (
  id_suscripciones, id_tenants, id_planes_suscripcion, fecha_inicio,
  fecha_proximo_pago, estado, precio_contratado
) VALUES
  (1, 1, 1, '2026-05-01', '2026-06-01', 1, 49.90),
  (2, 2, 2, '2026-05-02', '2026-08-02', 1, 219.90),
  (3, 3, 3, '2026-05-03', '2026-06-03', 1, 119.90),
  (4, 4, 4, '2026-05-04', '2027-05-04', 1, 1599.90),
  (5, 5, 5, '2026-05-05', '2026-06-05', 1, 199.90);
```

## usuarios
```sql
INSERT INTO usuarios (
  id_usuarios, id_tenants, nombre_usuario, apellidos_usuario, correo,
  numero_documento, contrasenia, tipo_usuario, estado
) VALUES
  (1, 1, 'Ana', 'Lopez', 'ana@bellarista.pe', '70111111', 'hash1', 'admin', 1),
  (2, 2, 'Bruno', 'Ramos', 'bruno@bellarista.pe', '70222222', 'hash2', 'cajero', 1),
  (3, 3, 'Carla', 'Diaz', 'carla@bellarista.pe', '70333333', 'hash3', 'especialista', 1),
  (4, 4, 'Diego', 'Vega', 'diego@bellarista.pe', '70444444', 'hash4', 'recepcionista', 1),
  (5, 5, 'Elena', 'Suarez', 'elena@bellarista.pe', '70555555', 'hash5', 'gerente', 1);
```

## promociones
```sql
INSERT INTO promociones (
  id_promociones, id_tenants, nombre_promocion, tipo_descuento, valor_descuento,
  compra_minima, fecha_inicio, fecha_fin, id_categorias_productos, estado
) VALUES
  (1, 1, 'Promo Capilar', 'porcentual', 10.00, 50.00, '2026-05-01', '2026-05-31', 1, 1),
  (2, 2, 'Promo Color', 'fijo', 15.00, 60.00, '2026-05-02', '2026-05-31', 2, 1),
  (3, 3, 'Promo Barber', '2x1', 0.00, 30.00, '2026-05-03', '2026-05-31', 3, 1),
  (4, 4, 'Promo Manicure', '3x2', 0.00, 40.00, '2026-05-04', '2026-05-31', 4, 1),
  (5, 5, 'Promo Facial', 'escalonado', 20.00, 80.00, '2026-05-05', '2026-05-31', 5, 1);
```

## productos
```sql
INSERT INTO productos (
  id_productos, id_tenants, id_categorias_productos, id_marcas, codigo_interno,
  codigo_barras, nombre_producto, descripcion, tipo_producto, presentacion,
  contenido_neto, precio_costo, precio_venta, margen_ganancia, stock_minimo,
  stock_critico, visible_storefront, etiqueta_especial, estado
) VALUES
  (1, 1, 1, 1, 'CAP-001', '1000000000001', 'Shampoo Hidratante', 'Para uso diario', 'shampoo', 'botella', '300ml', 15.00, 29.90, 49.50, 10, 5, 1, 'nuevo', 1),
  (2, 2, 2, 2, 'COL-002', '1000000000002', 'Tinte Rubio', 'Coloracion profesional', 'tinte', 'caja', '120ml', 25.00, 49.90, 50.00, 10, 5, 1, 'top', 1),
  (3, 3, 3, 3, 'BAR-003', '1000000000003', 'Cera Mate', 'Fijacion media', 'cera', 'tarro', '100ml', 12.00, 24.90, 48.00, 10, 5, 1, NULL, 1),
  (4, 4, 4, 4, 'MAN-004', '1000000000004', 'Esmalte Rojo', 'Larga duracion', 'esmalte', 'frasco', '12ml', 8.00, 19.90, 60.00, 10, 5, 1, 'destacado', 1),
  (5, 5, 5, 5, 'SKN-005', '1000000000005', 'Crema Facial', 'Hidratacion profunda', 'crema', 'tarro', '50ml', 20.00, 39.90, 50.00, 10, 5, 1, NULL, 1);
```

## cuentas_por_pagar
```sql
INSERT INTO cuentas_por_pagar (
  id_cuentas_por_pagar, id_tenants, id_proveedores, documento_numero,
  tipo_documento, monto, fecha_documento, fecha_vencimiento, estado_pago
) VALUES
  (1, 1, 1, 'FAC-1001', 'factura', 500.00, '2026-05-01', '2026-05-15', 'pendiente'),
  (2, 2, 2, 'BOL-2001', 'boleta', 300.00, '2026-05-02', '2026-05-16', 'pagada'),
  (3, 3, 3, 'OC-3001', 'orden_compra', 800.00, '2026-05-03', '2026-05-17', 'pendiente'),
  (4, 4, 4, 'FAC-4001', 'factura', 450.00, '2026-05-04', '2026-05-18', 'pagada'),
  (5, 5, 5, 'BOL-5001', 'boleta', 250.00, '2026-05-05', '2026-05-19', 'pendiente');
```

## devoluciones_proveedor
```sql
INSERT INTO devoluciones_proveedor (
  id_devoluciones_proveedor, id_tenants, id_proveedores, numero_devolucion,
  fecha_devolucion, motivo, observaciones, estado
) VALUES
  (1, 1, 1, 'DEV-P-001', '2026-05-06', 'Producto defectuoso', 'Lote con falla', 1),
  (2, 2, 2, 'DEV-P-002', '2026-05-07', 'Vencimiento cercano', 'Rotacion lenta', 1),
  (3, 3, 3, 'DEV-P-003', '2026-05-08', 'Error de despacho', 'Cantidad incorrecta', 1),
  (4, 4, 4, 'DEV-P-004', '2026-05-09', 'Empaque dañado', 'Cajas rotas', 1),
  (5, 5, 5, 'DEV-P-005', '2026-05-10', 'Solicitud cliente', 'Cambio por modelo', 1);
```

## ordenes_compra
```sql
INSERT INTO ordenes_compra (
  id_ordenes_compra, id_tenants, id_proveedores, numero_orden, fecha_orden,
  fecha_entrega_estimada, estado, monto_total, condiciones_entrega,
  forma_pago, notas
) VALUES
  (1, 1, 1, 'OC-1001', '2026-05-01', '2026-05-05', 1, 1000.00, 'Entrega en sede', 'transferencia', 'Urgente'),
  (2, 2, 2, 'OC-1002', '2026-05-02', '2026-05-06', 1, 1200.00, 'Entrega en sede', 'tarjeta', NULL),
  (3, 3, 3, 'OC-1003', '2026-05-03', '2026-05-07', 1, 800.00, 'Entrega en sede', 'efectivo', NULL),
  (4, 4, 4, 'OC-1004', '2026-05-04', '2026-05-08', 1, 900.00, 'Entrega en sede', 'transferencia', 'Confirmar stock'),
  (5, 5, 5, 'OC-1005', '2026-05-05', '2026-05-09', 1, 700.00, 'Entrega en sede', 'efectivo', NULL);
```

## permisos_rol
```sql
INSERT INTO permisos_rol (
  id_permisos_rol, id_roles_personalizados, modulo, accion, recurso, estado
) VALUES
  (1, 1, 'usuarios', 'crear', 'usuarios', 1),
  (2, 2, 'ventas', 'visualizar', 'ventas', 1),
  (3, 3, 'citas', 'editar', 'citas', 1),
  (4, 4, 'servicios', 'visualizar', 'servicios', 1),
  (5, 5, 'reportes', 'eliminar', 'reportes', 1);
```

## almacenes
```sql
INSERT INTO almacenes (
  id_almacenes, id_sedes, nombre_almacen, ubicacion, capacidad, estado
) VALUES
  (1, 1, 'Almacen Centro', 'Primer piso', 100, 1),
  (2, 2, 'Almacen Norte', 'Segundo piso', 120, 1),
  (3, 3, 'Almacen Sur', 'Primer piso', 90, 1),
  (4, 4, 'Almacen Este', 'Sotano', 80, 1),
  (5, 5, 'Almacen Oeste', 'Primer piso', 110, 1);
```

## gastos_recurrentes
```sql
INSERT INTO gastos_recurrentes (
  id_gastos_recurrentes, id_tenants, id_sedes, concepto, monto,
  frecuencia, fecha_proximo_registro, estado
) VALUES
  (1, 1, 1, 'Internet', 120.00, 'mensual', '2026-06-01', 1),
  (2, 2, 2, 'Luz', 200.00, 'mensual', '2026-06-02', 1),
  (3, 3, 3, 'Agua', 90.00, 'mensual', '2026-06-03', 1),
  (4, 4, 4, 'Alquiler', 1500.00, 'mensual', '2026-06-04', 1),
  (5, 5, 5, 'Limpieza', 300.00, 'mensual', '2026-06-05', 1);
```

## horarios_operacion
```sql
INSERT INTO horarios_operacion (
  id_horarios_operacion, id_sedes, dia_semana, hora_apertura, hora_cierre, estado
) VALUES
  (1, 1, 'lunes', '09:00:00', '19:00:00', 1),
  (2, 2, 'martes', '09:00:00', '19:00:00', 1),
  (3, 3, 'miercoles', '09:00:00', '19:00:00', 1),
  (4, 4, 'jueves', '09:00:00', '19:00:00', 1),
  (5, 5, 'viernes', '09:00:00', '19:00:00', 1);
```

## zonas_delivery
```sql
INSERT INTO zonas_delivery (
  id_zonas_delivery, id_sedes, nombre_zona, distritos, costo_fijo,
  monto_minimo_compra, tiempo_estimado_minutos, estado
) VALUES
  (1, 1, 'Zona Centro', 'Cercado,Brena', 8.00, 50.00, 40, 1),
  (2, 2, 'Zona Norte', 'Independencia,Los Olivos', 10.00, 60.00, 45, 1),
  (3, 3, 'Zona Sur', 'Surco,Chorrillos', 12.00, 70.00, 50, 1),
  (4, 4, 'Zona Este', 'Ate,Santa Anita', 9.00, 55.00, 42, 1),
  (5, 5, 'Zona Oeste', 'Magdalena,San Miguel', 11.00, 65.00, 48, 1);
```

## facturas_suscripcion
```sql
INSERT INTO facturas_suscripcion (
  id_facturas_suscripcion, id_suscripciones, numero_factura, monto,
  fecha_emision, fecha_vencimiento, estado_pago, metodo_pago, fecha_pago
) VALUES
  (1, 1, 'FS-1001', 49.90, '2026-05-01', '2026-05-10', 'pagada', 'tarjeta', '2026-05-02 10:00:00'),
  (2, 2, 'FS-1002', 219.90, '2026-05-02', '2026-05-12', 'pendiente', 'transferencia', NULL),
  (3, 3, 'FS-1003', 119.90, '2026-05-03', '2026-05-13', 'vencida', 'tarjeta', NULL),
  (4, 4, 'FS-1004', 1599.90, '2026-05-04', '2026-05-14', 'pagada', 'transferencia', '2026-05-05 11:00:00'),
  (5, 5, 'FS-1005', 199.90, '2026-05-05', '2026-05-15', 'cancelada', 'tarjeta', NULL);
```

## auditoria
```sql
INSERT INTO auditoria (
  id_auditoria, id_tenants, id_usuarios, accion, tabla_afectada,
  id_registro, datos_anteriores, datos_nuevos, ip_address, fecha_hora
) VALUES
  (1, 1, 1, 'crear', 'usuarios', 1, '{"antes":null}', '{"despues":"usuario"}', '192.168.1.10', '2026-05-01 10:00:00'),
  (2, 2, 2, 'editar', 'productos', 2, '{"precio":29.90}', '{"precio":31.90}', '192.168.1.11', '2026-05-02 10:00:00'),
  (3, 3, 3, 'eliminar', 'clientes', 3, '{"estado":1}', '{"estado":0}', '192.168.1.12', '2026-05-03 10:00:00'),
  (4, 4, 4, 'crear', 'ventas', 4, '{"antes":null}', '{"total":120.00}', '192.168.1.13', '2026-05-04 10:00:00'),
  (5, 5, 5, 'editar', 'sedes', 5, '{"nombre":"Sede X"}', '{"nombre":"Sede Oeste"}', '192.168.1.14', '2026-05-05 10:00:00');
```

## citas
```sql
INSERT INTO citas (
  id_citas, id_tenants, id_sedes, id_clientes, id_usuarios_especialista,
  id_servicios_belleza, fecha_cita, hora_inicio, hora_fin, duracion_minutos,
  estado, observaciones, id_usuarios_usuario_creacion
) VALUES
  (1, 1, 1, 1, 1, 1, '2026-05-10', '10:00:00', '10:30:00', 30, 1, 'Primera visita', 1),
  (2, 2, 2, 2, 2, 2, '2026-05-11', '11:00:00', '12:00:00', 60, 1, NULL, 2),
  (3, 3, 3, 3, 3, 3, '2026-05-12', '12:00:00', '12:30:00', 30, 1, NULL, 3),
  (4, 4, 4, 4, 4, 4, '2026-05-13', '13:00:00', '14:00:00', 60, 1, 'Preferencia gel', 4),
  (5, 5, 5, 5, 5, 5, '2026-05-14', '14:00:00', '15:30:00', 90, 1, NULL, 5);
```

## gastos_operativos
```sql
INSERT INTO gastos_operativos (
  id_gastos_operativos, id_tenants, id_sedes, concepto, categoria, monto,
  descripcion, id_proveedores, comprobante_numero, archivo_evidencia,
  estado, id_usuarios_usuario_creacion, id_usuarios_usuario_aprobacion, fecha_gasto
) VALUES
  (1, 1, 1, 'Insumos', 'insumos', 200.00, 'Compra mensual', 1, 'COMP-1001', NULL, 1, 1, 1, '2026-05-01'),
  (2, 2, 2, 'Publicidad', 'publicidad', 150.00, 'Redes sociales', 2, 'COMP-1002', NULL, 1, 2, 2, '2026-05-02'),
  (3, 3, 3, 'Mantenimiento', 'mantenimiento', 300.00, 'Mantenimiento equipos', 3, 'COMP-1003', NULL, 1, 3, 3, '2026-05-03'),
  (4, 4, 4, 'Transporte', 'transporte', 120.00, 'Envios', 4, 'COMP-1004', NULL, 1, 4, 4, '2026-05-04'),
  (5, 5, 5, 'Personal', 'personal', 500.00, 'Servicios externos', 5, 'COMP-1005', NULL, 1, 5, 5, '2026-05-05');
```

## notificaciones
```sql
INSERT INTO notificaciones (
  id_notificaciones, id_tenants, id_usuarios, tipo, titulo, mensaje,
  canal_envio, estado_lectura, fecha_lectura
) VALUES
  (1, 1, 1, 'stock_bajo', 'Stock bajo', 'Producto por agotarse', 'sistema', 'no_leido', NULL),
  (2, 2, 2, 'cita_pendiente', 'Cita pendiente', 'Recordatorio de cita', 'correo', 'no_leido', NULL),
  (3, 3, 3, 'cancelacion', 'Cita cancelada', 'Cliente cancelo cita', 'sistema', 'leido', '2026-05-03 09:00:00'),
  (4, 4, 4, 'pago_vencido', 'Pago vencido', 'Pago pendiente', 'sms', 'no_leido', NULL),
  (5, 5, 5, 'otro', 'Aviso', 'Mensaje informativo', 'whatsapp', 'leido', '2026-05-05 09:00:00');
```

## pedidos
```sql
INSERT INTO pedidos (
  id_pedidos, id_tenants, id_clientes, numero_pedido, modalidad, estado,
  subtotal, costo_envio, impuesto, total, direccion_entrega, id_zonas_delivery,
  id_usuarios, fecha_pedido, fecha_entrega_estimada, fecha_entrega_real
) VALUES
  (1, 1, 1, 'PED-1001', 'delivery', 1, 100.00, 8.00, 18.00, 126.00, 'Av. Uno 101', 1, 1, '2026-05-01 10:00:00', '2026-05-01 12:00:00', NULL),
  (2, 2, 2, 'PED-1002', 'pickup', 1, 80.00, 0.00, 14.40, 94.40, 'Jr. Dos 202', 2, 2, '2026-05-02 10:00:00', '2026-05-02 12:00:00', NULL),
  (3, 3, 3, 'PED-1003', 'presencial', 1, 60.00, 0.00, 10.80, 70.80, 'Calle Tres 303', 3, 3, '2026-05-03 10:00:00', '2026-05-03 12:00:00', NULL),
  (4, 4, 4, 'PED-1004', 'delivery', 1, 90.00, 9.00, 16.20, 115.20, 'Av. Cuatro 404', 4, 4, '2026-05-04 10:00:00', '2026-05-04 12:00:00', NULL),
  (5, 5, 5, 'PED-1005', 'delivery', 1, 110.00, 11.00, 19.80, 140.80, 'Jr. Cinco 505', 5, 5, '2026-05-05 10:00:00', '2026-05-05 12:00:00', NULL);
```

## preferencias_usuario
```sql
INSERT INTO preferencias_usuario (
  id_preferencias_usuario, id_usuarios, idioma, formato_fecha, zona_horaria,
  notificaciones_activas, vista_inicial, tema_interfaz
) VALUES
  (1, 1, 'es', 'DD/MM/YYYY', 'America/Lima', 1, 'dashboard', 'claro'),
  (2, 2, 'es', 'DD/MM/YYYY', 'America/Lima', 1, 'ventas', 'claro'),
  (3, 3, 'es', 'DD/MM/YYYY', 'America/Lima', 1, 'citas', 'oscuro'),
  (4, 4, 'es', 'DD/MM/YYYY', 'America/Lima', 1, 'clientes', 'claro'),
  (5, 5, 'es', 'DD/MM/YYYY', 'America/Lima', 1, 'inventario', 'oscuro');
```

## repartidores
```sql
INSERT INTO repartidores (
  id_repartidores, id_usuarios, nombre_repartidor, apellidos_repartidor,
  numero_documento, tipo_vehiculo, placa_vehiculo, numero_licencia, estado
) VALUES
  (1, 1, 'Luis', 'Paredes', '70111111', 'moto', 'MOT-111', 'LIC-111', 1),
  (2, 2, 'Mario', 'Soto', '70222222', 'moto', 'MOT-222', 'LIC-222', 1),
  (3, 3, 'Jose', 'Quispe', '70333333', 'auto', 'AUT-333', 'LIC-333', 1),
  (4, 4, 'Rene', 'Diaz', '70444444', 'moto', 'MOT-444', 'LIC-444', 1),
  (5, 5, 'Paola', 'Vega', '70555555', 'auto', 'AUT-555', 'LIC-555', 1);
```

## sesiones_caja
```sql
INSERT INTO sesiones_caja (
  id_sesiones_caja, id_sedes, id_usuarios, fecha_apertura, fecha_cierre,
  monto_inicial, monto_final, diferencia, estado, observaciones
) VALUES
  (1, 1, 1, '2026-05-01 09:00:00', '2026-05-01 19:00:00', 200.00, 450.00, 0.00, 1, 'Turno completo'),
  (2, 2, 2, '2026-05-02 09:00:00', '2026-05-02 19:00:00', 250.00, 430.00, 0.00, 1, NULL),
  (3, 3, 3, '2026-05-03 09:00:00', '2026-05-03 19:00:00', 180.00, 390.00, 0.00, 1, NULL),
  (4, 4, 4, '2026-05-04 09:00:00', '2026-05-04 19:00:00', 220.00, 410.00, 0.00, 1, NULL),
  (5, 5, 5, '2026-05-05 09:00:00', '2026-05-05 19:00:00', 210.00, 420.00, 0.00, 1, NULL);
```

## usuario_sedes
```sql
INSERT INTO usuario_sedes (
  id_usuario_sedes, id_usuarios, id_sedes, fecha_asignacion
) VALUES
  (1, 1, 1, '2026-05-01 09:00:00'),
  (2, 2, 2, '2026-05-02 09:00:00'),
  (3, 3, 3, '2026-05-03 09:00:00'),
  (4, 4, 4, '2026-05-04 09:00:00'),
  (5, 5, 5, '2026-05-05 09:00:00');
```

## composicion_combo
```sql
INSERT INTO composicion_combo (
  id_composicion_combo, id_combos_promocionales, id_productos, cantidad
) VALUES
  (1, 1, 1, 1),
  (2, 2, 2, 1),
  (3, 3, 3, 2),
  (4, 4, 4, 2),
  (5, 5, 5, 1);
```

## imagenes_producto
```sql
INSERT INTO imagenes_producto (
  id_imagenes_producto, id_productos, url_imagen, texto_alternativo, es_principal, orden
) VALUES
  (1, 1, 'https://img.example.com/prod1.png', 'Shampoo', 1, 1),
  (2, 2, 'https://img.example.com/prod2.png', 'Tinte', 1, 1),
  (3, 3, 'https://img.example.com/prod3.png', 'Cera', 1, 1),
  (4, 4, 'https://img.example.com/prod4.png', 'Esmalte', 1, 1),
  (5, 5, 'https://img.example.com/prod5.png', 'Crema', 1, 1);
```

## pagos_proveedor
```sql
INSERT INTO pagos_proveedor (
  id_pagos_proveedor, id_cuentas_por_pagar, monto_pagado, forma_pago,
  numero_comprobante, fecha_pago, id_usuarios, observaciones
) VALUES
  (1, 1, 200.00, 'transferencia', 'PP-1001', '2026-05-06', 1, NULL),
  (2, 2, 300.00, 'efectivo', 'PP-1002', '2026-05-07', 2, NULL),
  (3, 3, 150.00, 'tarjeta', 'PP-1003', '2026-05-08', 3, NULL),
  (4, 4, 450.00, 'efectivo', 'PP-1004', '2026-05-09', 4, NULL),
  (5, 5, 120.00, 'otro', 'PP-1005', '2026-05-10', 5, NULL);
```

## detalle_orden_compra
```sql
INSERT INTO detalle_orden_compra (
  id_detalle_orden_compra, id_ordenes_compra, id_productos, cantidad_solicitada,
  cantidad_recibida, precio_unitario, subtotal
) VALUES
  (1, 1, 1, 10, 10, 15.00, 150.00),
  (2, 2, 2, 8, 8, 25.00, 200.00),
  (3, 3, 3, 12, 12, 12.00, 144.00),
  (4, 4, 4, 6, 6, 8.00, 48.00),
  (5, 5, 5, 9, 9, 20.00, 180.00);
```

## lotes_inventario
```sql
INSERT INTO lotes_inventario (
  id_lotes_inventario, id_productos, id_almacenes, numero_lote, cantidad,
  cantidad_disponible, precio_unitario, fecha_vencimiento, id_proveedores,
  fecha_ingreso, observaciones, estado
) VALUES
  (1, 1, 1, 'L-1001', 50, 50, 15.00, '2027-05-01', 1, '2026-05-01', NULL, 1),
  (2, 2, 2, 'L-1002', 40, 40, 25.00, '2027-05-02', 2, '2026-05-02', NULL, 1),
  (3, 3, 3, 'L-1003', 60, 60, 12.00, '2027-05-03', 3, '2026-05-03', NULL, 1),
  (4, 4, 4, 'L-1004', 30, 30, 8.00, '2027-05-04', 4, '2026-05-04', NULL, 1),
  (5, 5, 5, 'L-1005', 35, 35, 20.00, '2027-05-05', 5, '2026-05-05', NULL, 1);
```

## servicios_cita
```sql
INSERT INTO servicios_cita (
  id_servicios_cita, id_citas, id_servicios_belleza, precio, observaciones, fecha_registro
) VALUES
  (1, 1, 1, 40.00, NULL, '2026-05-10 10:00:00'),
  (2, 2, 2, 120.00, NULL, '2026-05-11 11:00:00'),
  (3, 3, 3, 30.00, NULL, '2026-05-12 12:00:00'),
  (4, 4, 4, 35.00, NULL, '2026-05-13 13:00:00'),
  (5, 5, 5, 80.00, NULL, '2026-05-14 14:00:00');
```

## detalle_pedido
```sql
INSERT INTO detalle_pedido (
  id_detalle_pedido, id_pedidos, id_productos, cantidad, precio_unitario,
  descuento, subtotal
) VALUES
  (1, 1, 1, 2, 29.90, 0.00, 59.80),
  (2, 2, 2, 1, 49.90, 0.00, 49.90),
  (3, 3, 3, 3, 24.90, 0.00, 74.70),
  (4, 4, 4, 2, 19.90, 0.00, 39.80),
  (5, 5, 5, 1, 39.90, 0.00, 39.90);
```

## caja_chica
```sql
INSERT INTO caja_chica (
  id_caja_chica, id_sesiones_caja, concepto, monto, descripcion,
  comprobante_url, id_usuarios, fecha_registro
) VALUES
  (1, 1, 'Compra insumos', 20.00, 'Botellas', NULL, 1, '2026-05-01 12:00:00'),
  (2, 2, 'Taxi', 15.00, 'Traslado', NULL, 2, '2026-05-02 12:00:00'),
  (3, 3, 'Impresiones', 10.00, 'Boletas', NULL, 3, '2026-05-03 12:00:00'),
  (4, 4, 'Snacks', 12.00, 'Personal', NULL, 4, '2026-05-04 12:00:00'),
  (5, 5, 'Limpieza', 18.00, 'Suministros', NULL, 5, '2026-05-05 12:00:00');
```

## ventas
```sql
INSERT INTO ventas (
  id_ventas, id_tenants, id_sedes, id_sesiones_caja, id_clientes, numero_ticket,
  comprobante_numero, tipo_comprobante, subtotal, descuento, impuesto, total,
  estado, estado_sunat, id_usuarios, fecha_venta
) VALUES
  (1, 1, 1, 1, 1, 'TCK-001', 'B001-0001', 'boleta', 80.00, 0.00, 14.40, 94.40, 1, 'aceptada', 1, '2026-05-01 13:00:00'),
  (2, 2, 2, 2, 2, 'TCK-002', 'F001-0002', 'factura', 120.00, 10.00, 19.80, 129.80, 1, 'pendiente', 2, '2026-05-02 13:00:00'),
  (3, 3, 3, 3, 3, 'TCK-003', 'B001-0003', 'boleta', 60.00, 0.00, 10.80, 70.80, 1, 'observada', 3, '2026-05-03 13:00:00'),
  (4, 4, 4, 4, 4, 'TCK-004', 'B002-0004', 'boleta', 90.00, 5.00, 15.30, 100.30, 1, 'aceptada', 4, '2026-05-04 13:00:00'),
  (5, 5, 5, 5, 5, 'TCK-005', 'B002-0005', 'boleta', 110.00, 0.00, 19.80, 129.80, 1, 'rechazada', 5, '2026-05-05 13:00:00');
```

## detalle_devolucion_proveedor
```sql
INSERT INTO detalle_devolucion_proveedor (
  id_detalle_devolucion_proveedor, id_devoluciones_proveedor, id_productos,
  id_lotes_inventario, cantidad
) VALUES
  (1, 1, 1, 1, 2),
  (2, 2, 2, 2, 1),
  (3, 3, 3, 3, 3),
  (4, 4, 4, 4, 2),
  (5, 5, 5, 5, 1);
```

## movimientos_inventario
```sql
INSERT INTO movimientos_inventario (
  id_movimientos_inventario, id_lotes_inventario, tipo_movimiento, cantidad,
  id_usuarios, motivo, referencia_documento, fecha_movimiento
) VALUES
  (1, 1, 'entrada', 10, 1, 'Ingreso compra', 'OC-1001', '2026-05-01 15:00:00'),
  (2, 2, 'salida', 5, 2, 'Venta', 'B001-0001', '2026-05-02 15:00:00'),
  (3, 3, 'ajuste', 2, 3, 'Inventario', 'AJ-0001', '2026-05-03 15:00:00'),
  (4, 4, 'transferencia', 3, 4, 'Traslado', 'TR-0001', '2026-05-04 15:00:00'),
  (5, 5, 'merma', 1, 5, 'Producto dañado', 'MR-0001', '2026-05-05 15:00:00');
```

## comprobantes_electronicos
```sql
INSERT INTO comprobantes_electronicos (
  id_comprobantes_electronicos, id_tenants, tipo_comprobante, numero_serie,
  numero_comprobante, id_ventas, estado_sunat, respuesta_sunat, fecha_envio_sunat,
  xml_generado, cdr_recibida
) VALUES
  (1, 1, 'boleta', 'B001', '0001', 1, 'aceptada', 'OK', '2026-05-01 13:05:00', '<xml>1</xml>', '<cdr>1</cdr>'),
  (2, 2, 'factura', 'F001', '0002', 2, 'pendiente_envio', NULL, NULL, '<xml>2</xml>', NULL),
  (3, 3, 'boleta', 'B001', '0003', 3, 'observada', 'Error', '2026-05-03 13:05:00', '<xml>3</xml>', '<cdr>3</cdr>'),
  (4, 4, 'boleta', 'B002', '0004', 4, 'aceptada', 'OK', '2026-05-04 13:05:00', '<xml>4</xml>', '<cdr>4</cdr>'),
  (5, 5, 'boleta', 'B002', '0005', 5, 'rechazada', 'Rechazo', '2026-05-05 13:05:00', '<xml>5</xml>', '<cdr>5</cdr>');
```

## detalle_venta
```sql
INSERT INTO detalle_venta (
  id_detalle_venta, id_ventas, id_productos, cantidad, precio_unitario,
  descuento, subtotal, id_lotes_inventario
) VALUES
  (1, 1, 1, 2, 29.90, 0.00, 59.80, 1),
  (2, 2, 2, 1, 49.90, 0.00, 49.90, 2),
  (3, 3, 3, 3, 24.90, 0.00, 74.70, 3),
  (4, 4, 4, 2, 19.90, 0.00, 39.80, 4),
  (5, 5, 5, 1, 39.90, 0.00, 39.90, 5);
```

## devoluciones_venta
```sql
INSERT INTO devoluciones_venta (
  id_devoluciones_venta, id_tenants, id_ventas, numero_devolucion, motivo,
  tipo_resolucion, estado_devolucion, monto_reembolso, id_usuarios, fecha_devolucion
) VALUES
  (1, 1, 1, 'DEV-V-001', 'Producto defectuoso', 'reembolso', 'pendiente', 29.90, 1, '2026-05-06 10:00:00'),
  (2, 2, 2, 'DEV-V-002', 'Error en talla', 'cambio_producto', 'aprobada', 0.00, 2, '2026-05-07 10:00:00'),
  (3, 3, 3, 'DEV-V-003', 'No satisfaccion', 'nota_credito', 'rechazada', 0.00, 3, '2026-05-08 10:00:00'),
  (4, 4, 4, 'DEV-V-004', 'Demora', 'reembolso', 'completada', 19.90, 4, '2026-05-09 10:00:00'),
  (5, 5, 5, 'DEV-V-005', 'Producto equivocado', 'cambio_producto', 'pendiente', 0.00, 5, '2026-05-10 10:00:00');
```

## formas_pago_venta
```sql
INSERT INTO formas_pago_venta (
  id_formas_pago_venta, id_ventas, tipo_pago, monto, referencia_transaccion
) VALUES
  (1, 1, 'efectivo', 94.40, 'REF-1001'),
  (2, 2, 'tarjeta_credito', 129.80, 'REF-1002'),
  (3, 3, 'transferencia', 70.80, 'REF-1003'),
  (4, 4, 'tarjeta_debito', 100.30, 'REF-1004'),
  (5, 5, 'billetera_digital', 129.80, 'REF-1005');
```

## reclamos
```sql
INSERT INTO reclamos (
  id_reclamos, id_tenants, id_clientes, id_ventas, numero_reclamo,
  canal_ingreso, tipo_incidencia, descripcion, estado, id_usuarios_responsable,
  sla_horas, fecha_vencimiento_sla, solucion_aplicada, id_usuarios_usuario_creacion, fecha_cierre
) VALUES
  (1, 1, 1, 1, 'REC-1001', 'presencial', 'atencion', 'Demora en atencion', 1, 1, 24, '2026-05-06 10:00:00', NULL, 1, NULL),
  (2, 2, 2, 2, 'REC-1002', 'web', 'producto', 'Producto dañado', 1, 2, 24, '2026-05-07 10:00:00', NULL, 2, NULL),
  (3, 3, 3, 3, 'REC-1003', 'telefono', 'entrega', 'Entrega tardia', 1, 3, 24, '2026-05-08 10:00:00', NULL, 3, NULL),
  (4, 4, 4, 4, 'REC-1004', 'whatsapp', 'cita', 'Cita reprogramada', 1, 4, 24, '2026-05-09 10:00:00', NULL, 4, NULL),
  (5, 5, 5, 5, 'REC-1005', 'presencial', 'cobro', 'Cobro indebido', 1, 5, 24, '2026-05-10 10:00:00', NULL, 5, NULL);
```

## detalle_devolucion_venta
```sql
INSERT INTO detalle_devolucion_venta (
  id_detalle_devolucion_venta, id_devoluciones_venta, id_productos, cantidad, precio_unitario
) VALUES
  (1, 1, 1, 1, 29.90),
  (2, 2, 2, 1, 49.90),
  (3, 3, 3, 1, 24.90),
  (4, 4, 4, 1, 19.90),
  (5, 5, 5, 1, 39.90);
```
