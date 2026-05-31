-- ============================================================
-- Datos de ejemplo: 3 filas por tabla
-- Generado desde bellarista_mysql_v2.sql
-- ============================================================

SET FOREIGN_KEY_CHECKS = 0;

-- planes_suscripcion
INSERT INTO planes_suscripcion (
  id_planes_suscripcion,
  nombre_plan_suscripcion,
  descripcion,
  estado
) VALUES
  (1, 'planes_suscripcion_nombre_plan_suscripcion_1', 'planes_suscripcion_descripcion_1', 1),
  (2, 'planes_suscripcion_nombre_plan_suscripcion_2', 'planes_suscripcion_descripcion_2', 1),
  (3, 'planes_suscripcion_nombre_plan_suscripcion_3', 'planes_suscripcion_descripcion_3', 1);

-- precios_plan
INSERT INTO precios_plan (
  id_precios_plan,
  id_planes_suscripcion,
  periodo,
  precio,
  estado
) VALUES
  (1, 1, 'mensual', 10.00, 1),
  (2, 2, 'trimestral', 20.00, 1),
  (3, 3, 'anual', 30.00, 1);

-- tenants
INSERT INTO tenants (
  id_tenants,
  razon_social,
  ruc,
  direccion_fiscal,
  correo,
  telefono,
  nombre_comercial,
  tipo_negocio,
  estado,
  fecha_registro
) VALUES
  (1, 'tenants_razon_social_1', '20100000001', 'tenants_direccion_fiscal_1', 'tenants_correo_1@example.com', 'tenants_telefono_1', 'tenants_nombre_comercial_1', 'tenants_tipo_negocio_1', 1, '2026-05-01 09:01:01'),
  (2, 'tenants_razon_social_2', '20200000002', 'tenants_direccion_fiscal_2', 'tenants_correo_2@example.com', 'tenants_telefono_2', 'tenants_nombre_comercial_2', 'tenants_tipo_negocio_2', 1, '2026-05-02 09:02:02'),
  (3, 'tenants_razon_social_3', '20300000003', 'tenants_direccion_fiscal_3', 'tenants_correo_3@example.com', 'tenants_telefono_3', 'tenants_nombre_comercial_3', 'tenants_tipo_negocio_3', 1, '2026-05-03 09:03:03');

-- branding_negocio
INSERT INTO branding_negocio (
  id_branding_negocio,
  id_tenants,
  logo_url,
  color_primario,
  color_secundario,
  nombre_visible,
  redes_sociales
) VALUES
  (1, 1, 'branding_negocio_logo_url_1', 'branding_negocio_color_primario_1', 'branding_negocio_color_secundario_1', 'branding_negocio_nombre_visible_1', '{"table":"branding_negocio","row":1}'),
  (2, 2, 'branding_negocio_logo_url_2', 'branding_negocio_color_primario_2', 'branding_negocio_color_secundario_2', 'branding_negocio_nombre_visible_2', '{"table":"branding_negocio","row":2}'),
  (3, 3, 'branding_negocio_logo_url_3', 'branding_negocio_color_primario_3', 'branding_negocio_color_secundario_3', 'branding_negocio_nombre_visible_3', '{"table":"branding_negocio","row":3}');

-- categorias_productos
INSERT INTO categorias_productos (
  id_categorias_productos,
  id_tenants,
  nombre_categoria_producto,
  descripcion,
  imagen_url,
  orden,
  estado
) VALUES
  (1, 1, 'categorias_productos_nombre_categoria_producto_1', 'categorias_productos_descripcion_1', 'categorias_productos_imagen_url_1', 10.00, 1),
  (2, 2, 'categorias_productos_nombre_categoria_producto_2', 'categorias_productos_descripcion_2', 'categorias_productos_imagen_url_2', 20.00, 1),
  (3, 3, 'categorias_productos_nombre_categoria_producto_3', 'categorias_productos_descripcion_3', 'categorias_productos_imagen_url_3', 30.00, 1);

-- categorias_servicios
INSERT INTO categorias_servicios (
  id_categorias_servicios,
  id_tenants,
  nombre_categoria_servicio,
  descripcion,
  estado
) VALUES
  (1, 1, 'categorias_servicios_nombre_categoria_servicio_1', 'categorias_servicios_descripcion_1', 1),
  (2, 2, 'categorias_servicios_nombre_categoria_servicio_2', 'categorias_servicios_descripcion_2', 1),
  (3, 3, 'categorias_servicios_nombre_categoria_servicio_3', 'categorias_servicios_descripcion_3', 1);

-- clientes
INSERT INTO clientes (
  id_clientes,
  id_tenants,
  nombre_cliente,
  apellidos_clientes,
  tipo_documento,
  numero_documento,
  telefono,
  correo,
  direccion,
  distrito,
  tipo_cliente,
  estado,
  fecha_registro
) VALUES
  (1, 1, 'clientes_nombre_cliente_1', 'clientes_apellidos_clientes_1', 'DNI', 'DOC10001', 'clientes_telefono_1', 'clientes_correo_1@example.com', 'clientes_direccion_1', 'clientes_distrito_1', 'regular', 1, '2026-05-01 09:01:01'),
  (2, 2, 'clientes_nombre_cliente_2', 'clientes_apellidos_clientes_2', 'RUC', 'DOC20002', 'clientes_telefono_2', 'clientes_correo_2@example.com', 'clientes_direccion_2', 'clientes_distrito_2', 'frecuente', 1, '2026-05-02 09:02:02'),
  (3, 3, 'clientes_nombre_cliente_3', 'clientes_apellidos_clientes_3', 'Pasaporte', 'DOC30003', 'clientes_telefono_3', 'clientes_correo_3@example.com', 'clientes_direccion_3', 'clientes_distrito_3', 'vip', 1, '2026-05-03 09:03:03');

-- combos_promocionales
INSERT INTO combos_promocionales (
  id_combos_promocionales,
  id_tenants,
  nombre_promocion,
  descripcion,
  precio_combo,
  precio_original,
  fecha_inicio,
  fecha_fin,
  visible_storefront,
  estado
) VALUES
  (1, 1, 'combos_promocionales_nombre_promocion_1', 'combos_promocionales_descripcion_1', 10.00, 10.00, '2026-05-01', '2026-05-01', 1, 1),
  (2, 2, 'combos_promocionales_nombre_promocion_2', 'combos_promocionales_descripcion_2', 20.00, 20.00, '2026-05-02', '2026-05-02', 2, 1),
  (3, 3, 'combos_promocionales_nombre_promocion_3', 'combos_promocionales_descripcion_3', 30.00, 30.00, '2026-05-03', '2026-05-03', 3, 1);

-- configuracion_global
INSERT INTO configuracion_global (
  id_configuracion_global,
  id_tenants,
  clave,
  valor,
  tipo_valor,
  descripcion
) VALUES
  (1, 1, 'configuracion_global_clave_1', 'configuracion_global_valor_1', 'string', 'configuracion_global_descripcion_1'),
  (2, 2, 'configuracion_global_clave_2', 'configuracion_global_valor_2', 'numerico', 'configuracion_global_descripcion_2'),
  (3, 3, 'configuracion_global_clave_3', 'configuracion_global_valor_3', 'booleano', 'configuracion_global_descripcion_3');

-- marcas
INSERT INTO marcas (
  id_marcas,
  id_tenants,
  nombre_marca,
  pais_origen,
  logo_url,
  descripcion,
  estado
) VALUES
  (1, 1, 'marcas_nombre_marca_1', 'marcas_pais_origen_1', 'marcas_logo_url_1', 'marcas_descripcion_1', 1),
  (2, 2, 'marcas_nombre_marca_2', 'marcas_pais_origen_2', 'marcas_logo_url_2', 'marcas_descripcion_2', 1),
  (3, 3, 'marcas_nombre_marca_3', 'marcas_pais_origen_3', 'marcas_logo_url_3', 'marcas_descripcion_3', 1);

-- metodos_pago
INSERT INTO metodos_pago (
  id_metodos_pago,
  id_tenants,
  nombre_metodo_de_pago,
  tipo,
  descripcion,
  estado
) VALUES
  (1, 1, 'metodos_pago_nombre_metodo_de_pago_1', 'efectivo', 'metodos_pago_descripcion_1', 1),
  (2, 2, 'metodos_pago_nombre_metodo_de_pago_2', 'tarjeta_credito', 'metodos_pago_descripcion_2', 1),
  (3, 3, 'metodos_pago_nombre_metodo_de_pago_3', 'tarjeta_debito', 'metodos_pago_descripcion_3', 1);

-- proveedores
INSERT INTO proveedores (
  id_proveedores,
  id_tenants,
  razon_social,
  ruc,
  direccion,
  telefono,
  correo,
  persona_contacto,
  terminos_comerciales,
  estado
) VALUES
  (1, 1, 'proveedores_razon_social_1', '20100000001', 'proveedores_direccion_1', 'proveedores_telefono_1', 'proveedores_correo_1@example.com', 'proveedores_persona_contacto_1', 'proveedores_terminos_comerciales_1', 1),
  (2, 2, 'proveedores_razon_social_2', '20200000002', 'proveedores_direccion_2', 'proveedores_telefono_2', 'proveedores_correo_2@example.com', 'proveedores_persona_contacto_2', 'proveedores_terminos_comerciales_2', 1),
  (3, 3, 'proveedores_razon_social_3', '20300000003', 'proveedores_direccion_3', 'proveedores_telefono_3', 'proveedores_correo_3@example.com', 'proveedores_persona_contacto_3', 'proveedores_terminos_comerciales_3', 1);

-- roles_personalizados
INSERT INTO roles_personalizados (
  id_roles_personalizados,
  id_tenants,
  nombre_rol_personalizado,
  descripcion,
  estado
) VALUES
  (1, 1, 'roles_personalizados_nombre_rol_personalizado_1', 'roles_personalizados_descripcion_1', 1),
  (2, 2, 'roles_personalizados_nombre_rol_personalizado_2', 'roles_personalizados_descripcion_2', 1),
  (3, 3, 'roles_personalizados_nombre_rol_personalizado_3', 'roles_personalizados_descripcion_3', 1);

-- sedes
INSERT INTO sedes (
  id_sedes,
  id_tenants,
  nombre_sede,
  direccion,
  distrito,
  telefono,
  responsable,
  estado
) VALUES
  (1, 1, 'sedes_nombre_sede_1', 'sedes_direccion_1', 'sedes_distrito_1', 'sedes_telefono_1', 'sedes_responsable_1', 1),
  (2, 2, 'sedes_nombre_sede_2', 'sedes_direccion_2', 'sedes_distrito_2', 'sedes_telefono_2', 'sedes_responsable_2', 1),
  (3, 3, 'sedes_nombre_sede_3', 'sedes_direccion_3', 'sedes_distrito_3', 'sedes_telefono_3', 'sedes_responsable_3', 1);

-- series_comprobantes
INSERT INTO series_comprobantes (
  id_series_comprobantes,
  id_tenants,
  tipo_comprobante,
  punto_emision,
  numero_serie,
  numero_proximo,
  fecha_autorizacion,
  fecha_vencimiento,
  estado
) VALUES
  (1, 1, 'boleta', 1, 'SERIES_COMPROBANTES-1-0001', 10.00, '2026-05-01', '2026-05-01', 1),
  (2, 2, 'factura', 2, 'SERIES_COMPROBANTES-2-0002', 20.00, '2026-05-02', '2026-05-02', 1),
  (3, 3, 'nota_credito', 3, 'SERIES_COMPROBANTES-3-0003', 30.00, '2026-05-03', '2026-05-03', 1);

-- servicios_belleza
INSERT INTO servicios_belleza (
  id_servicios_belleza,
  id_tenants,
  id_categorias_servicios,
  nombre_servicio_belleza,
  descripcion,
  duracion_minima,
  duracion_maxima,
  precio_base,
  precio_editable,
  estado
) VALUES
  (1, 1, 1, 'servicios_belleza_nombre_servicio_belleza_1', 'servicios_belleza_descripcion_1', 10.00, 10.00, 10.00, 10.00, 1),
  (2, 2, 2, 'servicios_belleza_nombre_servicio_belleza_2', 'servicios_belleza_descripcion_2', 20.00, 20.00, 20.00, 20.00, 1),
  (3, 3, 3, 'servicios_belleza_nombre_servicio_belleza_3', 'servicios_belleza_descripcion_3', 30.00, 30.00, 30.00, 30.00, 1);

-- suscripciones
INSERT INTO suscripciones (
  id_suscripciones,
  id_tenants,
  id_planes_suscripcion,
  fecha_inicio,
  fecha_proximo_pago,
  estado,
  precio_contratado
) VALUES
  (1, 1, 1, '2026-05-01', '2026-05-01', 1, 10.00),
  (2, 2, 2, '2026-05-02', '2026-05-02', 1, 20.00),
  (3, 3, 3, '2026-05-03', '2026-05-03', 1, 30.00);

-- usuarios
INSERT INTO usuarios (
  id_usuarios,
  id_tenants,
  nombre_usuario,
  apellidos_usuario,
  correo,
  numero_documento,
  contraseña,
  tipo_usuario,
  estado
) VALUES
  (1, 1, 'usuarios_nombre_usuario_1', 'usuarios_apellidos_usuario_1', 'usuarios_correo_1@example.com', 'DOC10001', 'usuarios_contraseña_1', 'superadmin', 1),
  (2, 2, 'usuarios_nombre_usuario_2', 'usuarios_apellidos_usuario_2', 'usuarios_correo_2@example.com', 'DOC20002', 'usuarios_contraseña_2', 'admin', 1),
  (3, 3, 'usuarios_nombre_usuario_3', 'usuarios_apellidos_usuario_3', 'usuarios_correo_3@example.com', 'DOC30003', 'usuarios_contraseña_3', 'cajero', 1);

-- promociones
INSERT INTO promociones (
  id_promociones,
  id_tenants,
  nombre_promocion,
  tipo_descuento,
  valor_descuento,
  compra_minima,
  fecha_inicio,
  fecha_fin,
  id_categorias_productos,
  estado
) VALUES
  (1, 1, 'promociones_nombre_promocion_1', 'fijo', 10.00, 1, '2026-05-01', '2026-05-01', 1, 1),
  (2, 2, 'promociones_nombre_promocion_2', 'porcentual', 20.00, 2, '2026-05-02', '2026-05-02', 2, 1),
  (3, 3, 'promociones_nombre_promocion_3', '2x1', 30.00, 3, '2026-05-03', '2026-05-03', 3, 1);

-- productos
INSERT INTO productos (
  id_productos,
  id_tenants,
  id_categorias_productos,
  id_marcas,
  codigo_interno,
  codigo_barras,
  nombre_producto,
  descripcion,
  tipo_producto,
  presentacion,
  contenido_neto,
  precio_costo,
  precio_venta,
  stock_minimo,
  stock_critico,
  visible_storefront,
  etiqueta_especial,
  estado
) VALUES
  (1, 1, 1, 1, 'PRODUCTOS-1-CODE1', 'PRODUCTOS-1-CODE1', 'productos_nombre_producto_1', 'productos_descripcion_1', 'productos_tipo_producto_1', 'productos_presentacion_1', 'productos_contenido_neto_1', 10.00, 10.00, 10.00, 1, 1, 'productos_etiqueta_especial_1', 1),
  (2, 2, 2, 2, 'PRODUCTOS-2-CODE2', 'PRODUCTOS-2-CODE2', 'productos_nombre_producto_2', 'productos_descripcion_2', 'productos_tipo_producto_2', 'productos_presentacion_2', 'productos_contenido_neto_2', 20.00, 20.00, 20.00, 2, 2, 'productos_etiqueta_especial_2', 1),
  (3, 3, 3, 3, 'PRODUCTOS-3-CODE3', 'PRODUCTOS-3-CODE3', 'productos_nombre_producto_3', 'productos_descripcion_3', 'productos_tipo_producto_3', 'productos_presentacion_3', 'productos_contenido_neto_3', 30.00, 30.00, 30.00, 3, 3, 'productos_etiqueta_especial_3', 1);

-- registros
INSERT INTO registros (
  idregistro,
  nombres,
  apellidos,
  email,
  cliente_id,
  llave_secreta,
  access_token,
  estado
) VALUES
  (1, 'registros_nombres_1', 'registros_apellidos_1', 'registros_email_1', 'registros_cliente_id_1', 'registros_llave_secreta_1', 'registros_access_token_1', 1),
  (2, 'registros_nombres_2', 'registros_apellidos_2', 'registros_email_2', 'registros_cliente_id_2', 'registros_llave_secreta_2', 'registros_access_token_2', 1),
  (3, 'registros_nombres_3', 'registros_apellidos_3', 'registros_email_3', 'registros_cliente_id_3', 'registros_llave_secreta_3', 'registros_access_token_3', 1);

-- cuentas_por_pagar
INSERT INTO cuentas_por_pagar (
  id_cuentas_por_pagar,
  id_tenants,
  id_proveedores,
  documento_numero,
  tipo_documento,
  monto,
  fecha_documento,
  fecha_vencimiento,
  estado_pago
) VALUES
  (1, 1, 1, 'cuentas_por_pagar_documento_numero_1', 'factura', 10.00, '2026-05-01', '2026-05-01', 'pendiente'),
  (2, 2, 2, 'cuentas_por_pagar_documento_numero_2', 'boleta', 20.00, '2026-05-02', '2026-05-02', 'pagada'),
  (3, 3, 3, 'cuentas_por_pagar_documento_numero_3', 'orden_compra', 30.00, '2026-05-03', '2026-05-03', 'pagada');

-- devoluciones_proveedor
INSERT INTO devoluciones_proveedor (
  id_devoluciones_proveedor,
  id_tenants,
  id_proveedores,
  numero_devolucion,
  fecha_devolucion,
  motivo,
  observaciones,
  estado
) VALUES
  (1, 1, 1, 'DEVOLUCIONES_PROVEEDOR-1-0001', '2026-05-01', 'devoluciones_proveedor_motivo_1', 'devoluciones_proveedor_observaciones_1', 1),
  (2, 2, 2, 'DEVOLUCIONES_PROVEEDOR-2-0002', '2026-05-02', 'devoluciones_proveedor_motivo_2', 'devoluciones_proveedor_observaciones_2', 1),
  (3, 3, 3, 'DEVOLUCIONES_PROVEEDOR-3-0003', '2026-05-03', 'devoluciones_proveedor_motivo_3', 'devoluciones_proveedor_observaciones_3', 1);

-- ordenes_compra
INSERT INTO ordenes_compra (
  id_ordenes_compra,
  id_tenants,
  id_proveedores,
  numero_orden,
  fecha_orden,
  fecha_entrega_estimada,
  estado,
  monto_total,
  condiciones_entrega,
  forma_pago,
  notas
) VALUES
  (1, 1, 1, 'ORDENES_COMPRA-1-0001', '2026-05-01', '2026-05-01', 1, 10.00, 'ordenes_compra_condiciones_entrega_1', 'ordenes_compra_forma_pago_1', 'ordenes_compra_notas_1'),
  (2, 2, 2, 'ORDENES_COMPRA-2-0002', '2026-05-02', '2026-05-02', 1, 20.00, 'ordenes_compra_condiciones_entrega_2', 'ordenes_compra_forma_pago_2', 'ordenes_compra_notas_2'),
  (3, 3, 3, 'ORDENES_COMPRA-3-0003', '2026-05-03', '2026-05-03', 1, 30.00, 'ordenes_compra_condiciones_entrega_3', 'ordenes_compra_forma_pago_3', 'ordenes_compra_notas_3');

-- permisos_rol
INSERT INTO permisos_rol (
  id_permisos_rol,
  id_roles_personalizados,
  modulo,
  accion,
  recurso,
  estado
) VALUES
  (1, 1, 'permisos_rol_modulo_1', 'crear', 'permisos_rol_recurso_1', 1),
  (2, 2, 'permisos_rol_modulo_2', 'editar', 'permisos_rol_recurso_2', 1),
  (3, 3, 'permisos_rol_modulo_3', 'eliminar', 'permisos_rol_recurso_3', 1);

-- almacenes
INSERT INTO almacenes (
  id_almacenes,
  id_sedes,
  nombre_almacen,
  ubicacion,
  capacidad,
  estado
) VALUES
  (1, 1, 'almacenes_nombre_almacen_1', 'almacenes_ubicacion_1', 10.00, 1),
  (2, 2, 'almacenes_nombre_almacen_2', 'almacenes_ubicacion_2', 20.00, 1),
  (3, 3, 'almacenes_nombre_almacen_3', 'almacenes_ubicacion_3', 30.00, 1);

-- gastos_recurrentes
INSERT INTO gastos_recurrentes (
  id_gastos_recurrentes,
  id_tenants,
  id_sedes,
  concepto,
  monto,
  frecuencia,
  fecha_proximo_registro,
  estado
) VALUES
  (1, 1, 1, 'gastos_recurrentes_concepto_1', 10.00, 'diaria', '2026-05-01', 1),
  (2, 2, 2, 'gastos_recurrentes_concepto_2', 20.00, 'semanal', '2026-05-02', 1),
  (3, 3, 3, 'gastos_recurrentes_concepto_3', 30.00, 'quincenal', '2026-05-03', 1);

-- horarios_operacion
INSERT INTO horarios_operacion (
  id_horarios_operacion,
  id_sedes,
  dia_semana,
  hora_apertura,
  hora_cierre,
  estado
) VALUES
  (1, 1, 'lunes', '09:00:00', '09:00:00', 1),
  (2, 2, 'martes', '10:00:00', '10:00:00', 1),
  (3, 3, 'miercoles', '11:00:00', '11:00:00', 1);

-- zonas_delivery
INSERT INTO zonas_delivery (
  id_zonas_delivery,
  id_sedes,
  nombre_zona,
  distritos,
  costo_fijo,
  monto_minimo_compra,
  tiempo_estimado_minutos,
  estado
) VALUES
  (1, 1, 'zonas_delivery_nombre_zona_1', 'zonas_delivery_distritos_1', 10.00, 10.00, 1, 1),
  (2, 2, 'zonas_delivery_nombre_zona_2', 'zonas_delivery_distritos_2', 20.00, 20.00, 2, 1),
  (3, 3, 'zonas_delivery_nombre_zona_3', 'zonas_delivery_distritos_3', 30.00, 30.00, 3, 1);

-- facturas_suscripcion
INSERT INTO facturas_suscripcion (
  id_facturas_suscripcion,
  id_suscripciones,
  numero_factura,
  monto,
  fecha_emision,
  fecha_vencimiento,
  estado_pago,
  metodo_pago,
  fecha_pago
) VALUES
  (1, 1, 'FACTURAS_SUSCRIPCION-1-0001', 10.00, '2026-05-01', '2026-05-01', 'pendiente', 'facturas_suscripcion_metodo_pago_1', '2026-05-01 09:01:01'),
  (2, 2, 'FACTURAS_SUSCRIPCION-2-0002', 20.00, '2026-05-02', '2026-05-02', 'pagada', 'facturas_suscripcion_metodo_pago_2', '2026-05-02 09:02:02'),
  (3, 3, 'FACTURAS_SUSCRIPCION-3-0003', 30.00, '2026-05-03', '2026-05-03', 'vencida', 'facturas_suscripcion_metodo_pago_3', '2026-05-03 09:03:03');

-- auditoria
INSERT INTO auditoria (
  id_auditoria,
  id_tenants,
  id_usuarios,
  accion,
  tabla_afectada,
  id_registro,
  datos_anteriores,
  datos_nuevos,
  ip_address,
  fecha_hora
) VALUES
  (1, 1, 1, 'auditoria_accion_1', 'auditoria_tabla_afectada_1', 1, '{"table":"auditoria","row":1}', '{"table":"auditoria","row":1}', 'auditoria_ip_address_1', '2026-05-01 09:01:01'),
  (2, 2, 2, 'auditoria_accion_2', 'auditoria_tabla_afectada_2', 2, '{"table":"auditoria","row":2}', '{"table":"auditoria","row":2}', 'auditoria_ip_address_2', '2026-05-02 09:02:02'),
  (3, 3, 3, 'auditoria_accion_3', 'auditoria_tabla_afectada_3', 3, '{"table":"auditoria","row":3}', '{"table":"auditoria","row":3}', 'auditoria_ip_address_3', '2026-05-03 09:03:03');

-- citas
INSERT INTO citas (
  id_citas,
  id_tenants,
  id_sedes,
  id_clientes,
  id_usuarios_especialista,
  fecha_cita,
  hora_inicio,
  hora_fin,
  duracion_minutos,
  estado,
  observaciones,
  id_usuarios_usuario_creacion
) VALUES
  (1, 1, 1, 1, 1, '2026-05-01', '09:00:00', '09:00:00', 10.00, 1, 'citas_observaciones_1', 1),
  (2, 2, 2, 2, 2, '2026-05-02', '10:00:00', '10:00:00', 20.00, 1, 'citas_observaciones_2', 2),
  (3, 3, 3, 3, 3, '2026-05-03', '11:00:00', '11:00:00', 30.00, 1, 'citas_observaciones_3', 3);

-- gastos_operativos
INSERT INTO gastos_operativos (
  id_gastos_operativos,
  id_tenants,
  id_sedes,
  concepto,
  categoria,
  monto,
  descripcion,
  id_proveedores,
  comprobante_numero,
  archivo_evidencia,
  estado,
  id_usuarios_usuario_creacion,
  id_usuarios_usuario_aprobacion,
  fecha_gasto
) VALUES
  (1, 1, 1, 'gastos_operativos_concepto_1', 'insumos', 10.00, 'gastos_operativos_descripcion_1', 1, 'gastos_operativos_comprobante_numero_1', 'gastos_operativos_archivo_evidencia_1', 1, 1, 1, '2026-05-01'),
  (2, 2, 2, 'gastos_operativos_concepto_2', 'cosmeticos', 20.00, 'gastos_operativos_descripcion_2', 2, 'gastos_operativos_comprobante_numero_2', 'gastos_operativos_archivo_evidencia_2', 1, 2, 2, '2026-05-02'),
  (3, 3, 3, 'gastos_operativos_concepto_3', 'publicidad', 30.00, 'gastos_operativos_descripcion_3', 3, 'gastos_operativos_comprobante_numero_3', 'gastos_operativos_archivo_evidencia_3', 1, 3, 3, '2026-05-03');

-- notificaciones
INSERT INTO notificaciones (
  id_notificaciones,
  id_tenants,
  id_usuarios,
  tipo,
  titulo,
  mensaje,
  canal_envio,
  estado_lectura,
  fecha_lectura
) VALUES
  (1, 1, 1, 'stock_bajo', 'notificaciones_titulo_1', 'notificaciones_mensaje_1', 'correo', 'no_leido', '2026-05-01 09:01:01'),
  (2, 2, 2, 'producto_vencer', 'notificaciones_titulo_2', 'notificaciones_mensaje_2', 'sms', 'leido', '2026-05-02 09:02:02'),
  (3, 3, 3, 'cita_pendiente', 'notificaciones_titulo_3', 'notificaciones_mensaje_3', 'sistema', 'leido', '2026-05-03 09:03:03');

-- pedidos
INSERT INTO pedidos (
  id_pedidos,
  id_tenants,
  id_clientes,
  numero_pedido,
  modalidad,
  estado,
  subtotal,
  costo_envio,
  impuesto,
  total,
  direccion_entrega,
  id_zonas_delivery,
  id_usuarios,
  fecha_pedido,
  fecha_entrega_estimada,
  fecha_entrega_real
) VALUES
  (1, 1, 1, 'PEDIDOS-1-0001', 'delivery', 1, 10.00, 10.00, 10.00, 10.00, 'pedidos_direccion_entrega_1', 1, 1, '2026-05-01 09:01:01', '2026-05-01 09:01:01', '2026-05-01 09:01:01'),
  (2, 2, 2, 'PEDIDOS-2-0002', 'pickup', 1, 20.00, 20.00, 20.00, 20.00, 'pedidos_direccion_entrega_2', 2, 2, '2026-05-02 09:02:02', '2026-05-02 09:02:02', '2026-05-02 09:02:02'),
  (3, 3, 3, 'PEDIDOS-3-0003', 'presencial', 1, 30.00, 30.00, 30.00, 30.00, 'pedidos_direccion_entrega_3', 3, 3, '2026-05-03 09:03:03', '2026-05-03 09:03:03', '2026-05-03 09:03:03');

-- preferencias_usuario
INSERT INTO preferencias_usuario (
  id_preferencias_usuario,
  id_usuarios,
  idioma,
  formato_fecha,
  zona_horaria,
  notificaciones_activas,
  vista_inicial,
  tema_interfaz
) VALUES
  (1, 1, 'preferencias_usuario_idioma_1', 'preferencias_usuario_formato_fecha_1', 'preferencias_usuario_zona_horaria_1', 1, 'preferencias_usuario_vista_inicial_1', 'claro'),
  (2, 2, 'preferencias_usuario_idioma_2', 'preferencias_usuario_formato_fecha_2', 'preferencias_usuario_zona_horaria_2', 2, 'preferencias_usuario_vista_inicial_2', 'oscuro'),
  (3, 3, 'preferencias_usuario_idioma_3', 'preferencias_usuario_formato_fecha_3', 'preferencias_usuario_zona_horaria_3', 3, 'preferencias_usuario_vista_inicial_3', 'oscuro');

-- repartidores
INSERT INTO repartidores (
  id_repartidores,
  id_usuarios,
  tipo_vehiculo,
  placa_vehiculo,
  numero_licencia,
  estado
) VALUES
  (1, 1, 'repartidores_tipo_vehiculo_1', 'repartidores_placa_vehiculo_1', 'REPARTIDORES-1-0001', 1),
  (2, 2, 'repartidores_tipo_vehiculo_2', 'repartidores_placa_vehiculo_2', 'REPARTIDORES-2-0002', 1),
  (3, 3, 'repartidores_tipo_vehiculo_3', 'repartidores_placa_vehiculo_3', 'REPARTIDORES-3-0003', 1);

-- sesiones_caja
INSERT INTO sesiones_caja (
  id_sesiones_caja,
  id_sedes,
  id_usuarios,
  fecha_apertura,
  fecha_cierre,
  monto_inicial,
  monto_final,
  diferencia,
  estado,
  observaciones
) VALUES
  (1, 1, 1, '2026-05-01 09:01:01', '2026-05-01 09:01:01', 10.00, 10.00, 10.00, 1, 'sesiones_caja_observaciones_1'),
  (2, 2, 2, '2026-05-02 09:02:02', '2026-05-02 09:02:02', 20.00, 20.00, 20.00, 1, 'sesiones_caja_observaciones_2'),
  (3, 3, 3, '2026-05-03 09:03:03', '2026-05-03 09:03:03', 30.00, 30.00, 30.00, 1, 'sesiones_caja_observaciones_3');

-- usuario_sedes
INSERT INTO usuario_sedes (
  id_usuario_sedes,
  id_usuarios,
  id_sedes,
  fecha_asignacion
) VALUES
  (1, 1, 1, '2026-05-01 09:01:01'),
  (2, 2, 2, '2026-05-02 09:02:02'),
  (3, 3, 3, '2026-05-03 09:03:03');

-- composicion_combo
INSERT INTO composicion_combo (
  id_composicion_combo,
  id_combos_promocionales,
  id_productos,
  cantidad
) VALUES
  (1, 1, 1, 10.00),
  (2, 2, 2, 20.00),
  (3, 3, 3, 30.00);

-- imagenes_producto
INSERT INTO imagenes_producto (
  id_imagenes_producto,
  id_productos,
  url_imagen,
  texto_alternativo,
  es_principal,
  orden
) VALUES
  (1, 1, 'imagenes_producto_url_imagen_1', 'imagenes_producto_texto_alternativo_1', 1, 10.00),
  (2, 2, 'imagenes_producto_url_imagen_2', 'imagenes_producto_texto_alternativo_2', 2, 20.00),
  (3, 3, 'imagenes_producto_url_imagen_3', 'imagenes_producto_texto_alternativo_3', 3, 30.00);

-- pagos_proveedor
INSERT INTO pagos_proveedor (
  id_pagos_proveedor,
  id_cuentas_por_pagar,
  monto_pagado,
  forma_pago,
  numero_comprobante,
  fecha_pago,
  id_usuarios,
  observaciones
) VALUES
  (1, 1, 10.00, 'efectivo', 'PAGOS_PROVEEDOR-1-0001', '2026-05-01', 1, 'pagos_proveedor_observaciones_1'),
  (2, 2, 20.00, 'transferencia', 'PAGOS_PROVEEDOR-2-0002', '2026-05-02', 2, 'pagos_proveedor_observaciones_2'),
  (3, 3, 30.00, 'tarjeta', 'PAGOS_PROVEEDOR-3-0003', '2026-05-03', 3, 'pagos_proveedor_observaciones_3');

-- detalle_orden_compra
INSERT INTO detalle_orden_compra (
  id_detalle_orden_compra,
  id_ordenes_compra,
  id_productos,
  cantidad_solicitada,
  cantidad_recibida,
  precio_unitario,
  subtotal
) VALUES
  (1, 1, 1, 10.00, 10.00, 10.00, 10.00),
  (2, 2, 2, 20.00, 20.00, 20.00, 20.00),
  (3, 3, 3, 30.00, 30.00, 30.00, 30.00);

-- lotes_inventario
INSERT INTO lotes_inventario (
  id_lotes_inventario,
  id_productos,
  id_almacenes,
  numero_lote,
  cantidad,
  cantidad_disponible,
  precio_unitario,
  fecha_vencimiento,
  id_proveedores,
  fecha_ingreso,
  observaciones,
  estado
) VALUES
  (1, 1, 1, 'lotes_inventario_numero_lote_1', 10.00, 10.00, 10.00, '2026-05-01', 1, '2026-05-01', 'lotes_inventario_observaciones_1', 1),
  (2, 2, 2, 'lotes_inventario_numero_lote_2', 20.00, 20.00, 20.00, '2026-05-02', 2, '2026-05-02', 'lotes_inventario_observaciones_2', 1),
  (3, 3, 3, 'lotes_inventario_numero_lote_3', 30.00, 30.00, 30.00, '2026-05-03', 3, '2026-05-03', 'lotes_inventario_observaciones_3', 1);

-- servicios_cita
INSERT INTO servicios_cita (
  id_servicios_cita,
  id_citas,
  id_servicios_belleza,
  precio,
  observaciones,
  fecha_registro
) VALUES
  (1, 1, 1, 10.00, 'servicios_cita_observaciones_1', '2026-05-01 09:01:01'),
  (2, 2, 2, 20.00, 'servicios_cita_observaciones_2', '2026-05-02 09:02:02'),
  (3, 3, 3, 30.00, 'servicios_cita_observaciones_3', '2026-05-03 09:03:03');

-- detalle_pedido
INSERT INTO detalle_pedido (
  id_detalle_pedido,
  id_pedidos,
  id_productos,
  cantidad,
  precio_unitario,
  descuento,
  subtotal
) VALUES
  (1, 1, 1, 10.00, 10.00, 1, 10.00),
  (2, 2, 2, 20.00, 20.00, 2, 20.00),
  (3, 3, 3, 30.00, 30.00, 3, 30.00);

-- caja_chica
INSERT INTO caja_chica (
  id_caja_chica,
  id_sesiones_caja,
  concepto,
  monto,
  descripcion,
  comprobante_url,
  id_usuarios,
  fecha_registro
) VALUES
  (1, 1, 'caja_chica_concepto_1', 10.00, 'caja_chica_descripcion_1', 'caja_chica_comprobante_url_1', 1, '2026-05-01 09:01:01'),
  (2, 2, 'caja_chica_concepto_2', 20.00, 'caja_chica_descripcion_2', 'caja_chica_comprobante_url_2', 2, '2026-05-02 09:02:02'),
  (3, 3, 'caja_chica_concepto_3', 30.00, 'caja_chica_descripcion_3', 'caja_chica_comprobante_url_3', 3, '2026-05-03 09:03:03');

-- ventas
INSERT INTO ventas (
  id_ventas,
  id_tenants,
  id_sedes,
  id_sesiones_caja,
  id_clientes,
  numero_ticket,
  comprobante_numero,
  tipo_comprobante,
  subtotal,
  descuento,
  impuesto,
  total,
  estado,
  estado_sunat,
  id_usuarios,
  fecha_venta
) VALUES
  (1, 1, 1, 1, 1, 'VENTAS-1-0001', 'ventas_comprobante_numero_1', 'boleta', 10.00, 1, 10.00, 10.00, 1, 'aceptada', 1, '2026-05-01 09:01:01'),
  (2, 2, 2, 2, 2, 'VENTAS-2-0002', 'ventas_comprobante_numero_2', 'factura', 20.00, 2, 20.00, 20.00, 1, 'observada', 2, '2026-05-02 09:02:02'),
  (3, 3, 3, 3, 3, 'VENTAS-3-0003', 'ventas_comprobante_numero_3', 'nota_credito', 30.00, 3, 30.00, 30.00, 1, 'rechazada', 3, '2026-05-03 09:03:03');

-- detalle_devolucion_proveedor
INSERT INTO detalle_devolucion_proveedor (
  id_detalle_devolucion_proveedor,
  id_devoluciones_proveedor,
  id_productos,
  id_lotes_inventario,
  cantidad
) VALUES
  (1, 1, 1, 1, 10.00),
  (2, 2, 2, 2, 20.00),
  (3, 3, 3, 3, 30.00);

-- movimientos_inventario
INSERT INTO movimientos_inventario (
  id_movimientos_inventario,
  id_lotes_inventario,
  tipo_movimiento,
  cantidad,
  id_usuarios,
  motivo,
  referencia_documento,
  fecha_movimiento
) VALUES
  (1, 1, 'entrada', 10.00, 1, 'movimientos_inventario_motivo_1', 'movimientos_inventario_referencia_documento_1', '2026-05-01 09:01:01'),
  (2, 2, 'salida', 20.00, 2, 'movimientos_inventario_motivo_2', 'movimientos_inventario_referencia_documento_2', '2026-05-02 09:02:02'),
  (3, 3, 'ajuste', 30.00, 3, 'movimientos_inventario_motivo_3', 'movimientos_inventario_referencia_documento_3', '2026-05-03 09:03:03');

-- comprobantes_electronicos
INSERT INTO comprobantes_electronicos (
  id_comprobantes_electronicos,
  id_tenants,
  tipo_comprobante,
  numero_serie,
  numero_comprobante,
  id_ventas,
  estado_sunat,
  respuesta_sunat,
  fecha_envio_sunat,
  xml_generado,
  cdr_recibida
) VALUES
  (1, 1, 'boleta', 'COMPROBANTES_ELECTRONICOS-1-0001', 'COMPROBANTES_ELECTRONICOS-1-0001', 1, 'aceptada', 'comprobantes_electronicos_respuesta_sunat_1', '2026-05-01 09:01:01', 'comprobantes_electronicos_xml_generado_1', 'comprobantes_electronicos_cdr_recibida_1'),
  (2, 2, 'factura', 'COMPROBANTES_ELECTRONICOS-2-0002', 'COMPROBANTES_ELECTRONICOS-2-0002', 2, 'observada', 'comprobantes_electronicos_respuesta_sunat_2', '2026-05-02 09:02:02', 'comprobantes_electronicos_xml_generado_2', 'comprobantes_electronicos_cdr_recibida_2'),
  (3, 3, 'nota_credito', 'COMPROBANTES_ELECTRONICOS-3-0003', 'COMPROBANTES_ELECTRONICOS-3-0003', 3, 'rechazada', 'comprobantes_electronicos_respuesta_sunat_3', '2026-05-03 09:03:03', 'comprobantes_electronicos_xml_generado_3', 'comprobantes_electronicos_cdr_recibida_3');

-- detalle_venta
INSERT INTO detalle_venta (
  id_detalle_venta,
  id_ventas,
  id_productos,
  cantidad,
  precio_unitario,
  descuento,
  subtotal,
  id_lotes_inventario
) VALUES
  (1, 1, 1, 10.00, 10.00, 1, 10.00, 1),
  (2, 2, 2, 20.00, 20.00, 2, 20.00, 2),
  (3, 3, 3, 30.00, 30.00, 3, 30.00, 3);

-- devoluciones_venta
INSERT INTO devoluciones_venta (
  id_devoluciones_venta,
  id_tenants,
  id_ventas,
  numero_devolucion,
  motivo,
  tipo_resolucion,
  estado_devolucion,
  monto_reembolso,
  id_usuarios,
  fecha_devolucion
) VALUES
  (1, 1, 1, 'DEVOLUCIONES_VENTA-1-0001', 'devoluciones_venta_motivo_1', 'reembolso', 'pendiente', 10.00, 1, '2026-05-01 09:01:01'),
  (2, 2, 2, 'DEVOLUCIONES_VENTA-2-0002', 'devoluciones_venta_motivo_2', 'cambio_producto', 'aprobada', 20.00, 2, '2026-05-02 09:02:02'),
  (3, 3, 3, 'DEVOLUCIONES_VENTA-3-0003', 'devoluciones_venta_motivo_3', 'nota_credito', 'rechazada', 30.00, 3, '2026-05-03 09:03:03');

-- formas_pago_venta
INSERT INTO formas_pago_venta (
  id_formas_pago_venta,
  id_ventas,
  tipo_pago,
  monto,
  referencia_transaccion
) VALUES
  (1, 1, 'efectivo', 10.00, 'formas_pago_venta_referencia_transaccion_1'),
  (2, 2, 'tarjeta_credito', 20.00, 'formas_pago_venta_referencia_transaccion_2'),
  (3, 3, 'tarjeta_debito', 30.00, 'formas_pago_venta_referencia_transaccion_3');

-- reclamos
INSERT INTO reclamos (
  id_reclamos,
  id_tenants,
  id_clientes,
  id_ventas,
  numero_reclamo,
  canal_ingreso,
  tipo_incidencia,
  descripcion,
  estado,
  id_usuarios_responsable,
  sla_horas,
  fecha_vencimiento_sla,
  solucion_aplicada,
  id_usuarios_usuario_creacion,
  fecha_cierre
) VALUES
  (1, 1, 1, 1, 'RECLAMOS-1-0001', 'presencial', 'reclamos_tipo_incidencia_1', 'reclamos_descripcion_1', 1, 1, 1, '2026-05-01 09:01:01', 'reclamos_solucion_aplicada_1', 1, '2026-05-01 09:01:01'),
  (2, 2, 2, 2, 'RECLAMOS-2-0002', 'web', 'reclamos_tipo_incidencia_2', 'reclamos_descripcion_2', 1, 2, 2, '2026-05-02 09:02:02', 'reclamos_solucion_aplicada_2', 2, '2026-05-02 09:02:02'),
  (3, 3, 3, 3, 'RECLAMOS-3-0003', 'telefono', 'reclamos_tipo_incidencia_3', 'reclamos_descripcion_3', 1, 3, 3, '2026-05-03 09:03:03', 'reclamos_solucion_aplicada_3', 3, '2026-05-03 09:03:03');

-- detalle_devolucion_venta
INSERT INTO detalle_devolucion_venta (
  id_detalle_devolucion_venta,
  id_devoluciones_venta,
  id_productos,
  cantidad,
  precio_unitario
) VALUES
  (1, 1, 1, 10.00, 10.00),
  (2, 2, 2, 20.00, 20.00),
  (3, 3, 3, 30.00, 30.00);

-- proveedor_categorias
INSERT INTO proveedor_categorias (
  id_proveedor_categorias,
  id_proveedores,
  id_categorias_productos
) VALUES
  (1, 1, 1),
  (2, 2, 2),
  (3, 3, 3);

SET FOREIGN_KEY_CHECKS = 1;
