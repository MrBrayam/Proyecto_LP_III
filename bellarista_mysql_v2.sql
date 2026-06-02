-- ============================================================
-- Base de datos: bellarista (v3 - refactorizada e íntegra)
-- Generado: 2026-06-01
-- Cambios respecto a v2:
--   - Eliminada tabla imagenes_producto
--   - Agregada columna img_url en productos
--   - repartidores vinculado a usuarios (id_usuarios FK) y a tenants (id_tenants)
--   - tipo_usuario ENUM incluye 'repartidor'
--   - Corregidas comillas simples en proveedores y repartidores
--   - FK faltante en pedidos (id_zonas_delivery)
--   - FK faltante en lotes_inventario (id_proveedores)
--   - Trailing comma corregida en repartidores
--   - Punto y coma faltante en registros
-- ============================================================

SET FOREIGN_KEY_CHECKS = 0;


DROP TABLE IF EXISTS `planes_suscripcion`;
CREATE TABLE `planes_suscripcion` (
  `id_planes_suscripcion` int(11) NOT NULL AUTO_INCREMENT,
  `nombre_plan_suscripcion` varchar(100) NOT NULL,
  `descripcion` text DEFAULT NULL,
  `estado` tinyint(1) DEFAULT 1,
  PRIMARY KEY (`id_planes_suscripcion`),
  KEY `idx_estado` (`estado`)
);


DROP TABLE IF EXISTS `precios_plan`;
CREATE TABLE `precios_plan` (
  `id_precios_plan` int(11) NOT NULL AUTO_INCREMENT,
  `id_planes_suscripcion` int(11) NOT NULL,
  `periodo` enum('mensual','trimestral','anual') NOT NULL,
  `precio` decimal(10,2) NOT NULL,
  `estado` tinyint(1) DEFAULT 1,
  PRIMARY KEY (`id_precios_plan`),
  UNIQUE KEY `unique_plan_periodo` (`id_planes_suscripcion`,`periodo`),
  KEY `idx_plan_id` (`id_planes_suscripcion`),
  CONSTRAINT `fk_precios_plan_1` FOREIGN KEY (`id_planes_suscripcion`) REFERENCES `planes_suscripcion` (`id_planes_suscripcion`) ON DELETE CASCADE
);

DROP TABLE IF EXISTS `tenants`;
CREATE TABLE `tenants` (
  `id_tenants` int(11) NOT NULL AUTO_INCREMENT,
  `razon_social` varchar(255) NOT NULL,
  `ruc` varchar(20) NOT NULL,
  `direccion_fiscal` varchar(255) DEFAULT NULL,
  `correo` varchar(100) NOT NULL,
  `telefono` varchar(15) DEFAULT NULL,
  `nombre_comercial` varchar(255) DEFAULT NULL,
  `tipo_negocio` varchar(100) DEFAULT NULL,
  `estado` tinyint(1) DEFAULT 1,
  `fecha_registro` timestamp NULL DEFAULT current_timestamp(),
  PRIMARY KEY (`id_tenants`),
  UNIQUE KEY `ruc` (`ruc`),
  UNIQUE KEY `correo` (`correo`),
  KEY `idx_ruc` (`ruc`),
  KEY `idx_estado` (`estado`)
);

DROP TABLE IF EXISTS `branding_negocio`;
CREATE TABLE `branding_negocio` (
  `id_branding_negocio` int(11) NOT NULL AUTO_INCREMENT,
  `id_tenants` int(11) NOT NULL,
  `logo_url` varchar(255) DEFAULT NULL,
  `color_primario` varchar(7) DEFAULT NULL,
  `color_secundario` varchar(7) DEFAULT NULL,
  `nombre_visible` varchar(255) DEFAULT NULL,
  `redes_sociales` longtext CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL CHECK (json_valid(`redes_sociales`)),
  PRIMARY KEY (`id_branding_negocio`),
  UNIQUE KEY `unique_tenant` (`id_tenants`),
  KEY `idx_tenant_id` (`id_tenants`),
  CONSTRAINT `fk_branding_negocio_1` FOREIGN KEY (`id_tenants`) REFERENCES `tenants` (`id_tenants`) ON DELETE CASCADE
);

DROP TABLE IF EXISTS `categorias_productos`;
CREATE TABLE `categorias_productos` (
  `id_categorias_productos` int(11) NOT NULL AUTO_INCREMENT,
  `id_tenants` int(11) NOT NULL,
  `nombre_categoria_producto` varchar(100) NOT NULL,
  `descripcion` text DEFAULT NULL,
  `imagen_url` varchar(255) DEFAULT NULL,
  `orden` int(11) DEFAULT NULL,
  `estado` tinyint(1) DEFAULT 1,
  PRIMARY KEY (`id_categorias_productos`),
  KEY `idx_tenant_id` (`id_tenants`),
  KEY `idx_estado` (`estado`),
  CONSTRAINT `fk_categorias_productos_1` FOREIGN KEY (`id_tenants`) REFERENCES `tenants` (`id_tenants`) ON DELETE CASCADE
);

DROP TABLE IF EXISTS `categorias_servicios`;
CREATE TABLE `categorias_servicios` (
  `id_categorias_servicios` int(11) NOT NULL AUTO_INCREMENT,
  `id_tenants` int(11) NOT NULL,
  `nombre_categoria_servicio` varchar(100) NOT NULL,
  `descripcion` text DEFAULT NULL,
  `estado` tinyint(1) DEFAULT 1,
  PRIMARY KEY (`id_categorias_servicios`),
  KEY `idx_tenant_id` (`id_tenants`),
  KEY `idx_estado` (`estado`),
  CONSTRAINT `fk_categorias_servicios_1` FOREIGN KEY (`id_tenants`) REFERENCES `tenants` (`id_tenants`) ON DELETE CASCADE
);


DROP TABLE IF EXISTS `clientes`;
CREATE TABLE `clientes` (
  `id_clientes` int(11) NOT NULL AUTO_INCREMENT,
  `id_tenants` int(11) NOT NULL,
  `nombre_cliente` varchar(255) NOT NULL,
  `apellidos_clientes` varchar(255) NOT NULL,
  `tipo_documento` enum('DNI','RUC','Pasaporte') DEFAULT 'DNI',
  `numero_documento` varchar(20) DEFAULT NULL,
  `telefono` varchar(15) DEFAULT NULL,
  `correo` varchar(100) DEFAULT NULL,
  `direccion` varchar(255) DEFAULT NULL,
  `distrito` varchar(100) DEFAULT NULL,
  `tipo_cliente` enum('regular','frecuente','vip') DEFAULT 'regular',
  `estado` tinyint(1) DEFAULT 1,
  `fecha_registro` timestamp NULL DEFAULT current_timestamp(),
  PRIMARY KEY (`id_clientes`),
  KEY `idx_tenant_id` (`id_tenants`),
  KEY `idx_numero_documento` (`numero_documento`),
  KEY `idx_estado` (`estado`),
  CONSTRAINT `fk_clientes_1` FOREIGN KEY (`id_tenants`) REFERENCES `tenants` (`id_tenants`) ON DELETE CASCADE
);


DROP TABLE IF EXISTS `combos_promocionales`;
CREATE TABLE `combos_promocionales` (
  `id_combos_promocionales` int(11) NOT NULL AUTO_INCREMENT,
  `id_tenants` int(11) NOT NULL,
  `nombre_promocion` varchar(255) NOT NULL,
  `descripcion` text DEFAULT NULL,
  `precio_combo` decimal(10,2) DEFAULT NULL,
  `precio_original` decimal(10,2) DEFAULT NULL,
  `fecha_inicio` date DEFAULT NULL,
  `fecha_fin` date DEFAULT NULL,
  `visible_storefront` tinyint(1) DEFAULT 1,
  `estado` tinyint(1) DEFAULT 1,
  PRIMARY KEY (`id_combos_promocionales`),
  KEY `idx_tenant_id` (`id_tenants`),
  KEY `idx_estado` (`estado`),
  CONSTRAINT `fk_combos_promocionales_1` FOREIGN KEY (`id_tenants`) REFERENCES `tenants` (`id_tenants`) ON DELETE CASCADE
);


DROP TABLE IF EXISTS `configuracion_global`;
CREATE TABLE `configuracion_global` (
  `id_configuracion_global` int(11) NOT NULL AUTO_INCREMENT,
  `id_tenants` int(11) NOT NULL,
  `clave` varchar(100) NOT NULL,
  `valor` text DEFAULT NULL,
  `tipo_valor` enum('string','numerico','booleano','json') DEFAULT 'string',
  `descripcion` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id_configuracion_global`),
  UNIQUE KEY `unique_tenant_clave` (`id_tenants`,`clave`),
  KEY `idx_tenant_id` (`id_tenants`),
  CONSTRAINT `fk_configuracion_global_1` FOREIGN KEY (`id_tenants`) REFERENCES `tenants` (`id_tenants`) ON DELETE CASCADE
);

DROP TABLE IF EXISTS `marcas`;
CREATE TABLE `marcas` (
  `id_marcas` int(11) NOT NULL AUTO_INCREMENT,
  `id_tenants` int(11) NOT NULL,
  `nombre_marca` varchar(100) NOT NULL,
  `pais_origen` varchar(100) DEFAULT NULL,
  `logo_url` varchar(255) DEFAULT NULL,
  `descripcion` text DEFAULT NULL,
  `estado` tinyint(1) DEFAULT 1,
  PRIMARY KEY (`id_marcas`),
  KEY `idx_tenant_id` (`id_tenants`),
  KEY `idx_estado` (`estado`),
  CONSTRAINT `fk_marcas_1` FOREIGN KEY (`id_tenants`) REFERENCES `tenants` (`id_tenants`) ON DELETE CASCADE
);


DROP TABLE IF EXISTS `metodos_pago`;
CREATE TABLE `metodos_pago` (
  `id_metodos_pago` int(11) NOT NULL AUTO_INCREMENT,
  `id_tenants` int(11) NOT NULL,
  `nombre_metodo_de_pago` varchar(100) NOT NULL,
  `tipo` enum('efectivo','tarjeta_credito','tarjeta_debito','transferencia','billetera_digital','otro') DEFAULT NULL,
  `descripcion` text DEFAULT NULL,
  `estado` tinyint(1) DEFAULT 1,
  PRIMARY KEY (`id_metodos_pago`),
  KEY `idx_tenant_id` (`id_tenants`),
  CONSTRAINT `fk_metodos_pago_1` FOREIGN KEY (`id_tenants`) REFERENCES `tenants` (`id_tenants`) ON DELETE CASCADE
);


DROP TABLE IF EXISTS `proveedores`;
CREATE TABLE `proveedores` (
  `id_proveedores` int(11) NOT NULL AUTO_INCREMENT,
  `id_tenants` int(11) NOT NULL,
  `nombre_proveedor` varchar(255) NOT NULL,
  `apellido_proveedor` varchar(255) NOT NULL,
  `razon_social` varchar(255) NOT NULL,
  `ruc` varchar(20) DEFAULT NULL,
  `direccion` varchar(255) DEFAULT NULL,
  `telefono` varchar(15) DEFAULT NULL,
  `correo` varchar(100) DEFAULT NULL,
  `estado` tinyint(1) DEFAULT 1,
  PRIMARY KEY (`id_proveedores`),
  KEY `idx_tenant_id` (`id_tenants`),
  KEY `idx_estado` (`estado`),
  CONSTRAINT `fk_proveedores_1` FOREIGN KEY (`id_tenants`) REFERENCES `tenants` (`id_tenants`) ON DELETE CASCADE
);


DROP TABLE IF EXISTS `roles_personalizados`;
CREATE TABLE `roles_personalizados` (
  `id_roles_personalizados` int(11) NOT NULL AUTO_INCREMENT,
  `id_tenants` int(11) NOT NULL,
  `nombre_rol_personalizado` varchar(100) NOT NULL,
  `descripcion` text DEFAULT NULL,
  `estado` tinyint(1) DEFAULT 1,
  PRIMARY KEY (`id_roles_personalizados`),
  KEY `idx_tenant_id` (`id_tenants`),
  CONSTRAINT `fk_roles_personalizados_1` FOREIGN KEY (`id_tenants`) REFERENCES `tenants` (`id_tenants`) ON DELETE CASCADE
);


DROP TABLE IF EXISTS `sedes`;
CREATE TABLE `sedes` (
  `id_sedes` int(11) NOT NULL AUTO_INCREMENT,
  `id_tenants` int(11) NOT NULL,
  `nombre_sede` varchar(255) NOT NULL,
  `direccion` varchar(255) DEFAULT NULL,
  `distrito` varchar(100) DEFAULT NULL,
  `telefono` varchar(15) DEFAULT NULL,
  `responsable` varchar(255) DEFAULT NULL,
  `estado` tinyint(1) DEFAULT 1,
  PRIMARY KEY (`id_sedes`),
  KEY `idx_tenant_id` (`id_tenants`),
  KEY `idx_estado` (`estado`),
  CONSTRAINT `fk_sedes_1` FOREIGN KEY (`id_tenants`) REFERENCES `tenants` (`id_tenants`) ON DELETE CASCADE
);


DROP TABLE IF EXISTS `series_comprobantes`;
CREATE TABLE `series_comprobantes` (
  `id_series_comprobantes` int(11) NOT NULL AUTO_INCREMENT,
  `id_tenants` int(11) NOT NULL,
  `tipo_comprobante` enum('boleta','factura','nota_credito','nota_debito') DEFAULT NULL,
  `punto_emision` int(11) DEFAULT NULL,
  `numero_serie` varchar(10) DEFAULT NULL,
  `numero_proximo` int(11) DEFAULT 1,
  `fecha_autorizacion` date DEFAULT NULL,
  `fecha_vencimiento` date DEFAULT NULL,
  `estado` tinyint(1) DEFAULT 1,
  PRIMARY KEY (`id_series_comprobantes`),
  UNIQUE KEY `unique_serie` (`id_tenants`,`tipo_comprobante`,`punto_emision`,`numero_serie`),
  KEY `idx_tenant_id` (`id_tenants`),
  CONSTRAINT `fk_series_comprobantes_1` FOREIGN KEY (`id_tenants`) REFERENCES `tenants` (`id_tenants`) ON DELETE CASCADE
);

DROP TABLE IF EXISTS `servicios_belleza`;
CREATE TABLE `servicios_belleza` (
  `id_servicios_belleza` int(11) NOT NULL AUTO_INCREMENT,
  `id_tenants` int(11) NOT NULL,
  `id_categorias_servicios` int(11) DEFAULT NULL,
  `nombre_servicio_belleza` varchar(255) NOT NULL,
  `descripcion` text DEFAULT NULL,
  `duracion_minima` int(11) DEFAULT NULL,
  `duracion_maxima` int(11) DEFAULT NULL,
  `precio_base` decimal(10,2) DEFAULT NULL,
  `precio_editable` tinyint(1) DEFAULT 0,
  `estado` tinyint(1) DEFAULT 1,
  PRIMARY KEY (`id_servicios_belleza`),
  KEY `idx_tenant_id` (`id_tenants`),
  KEY `idx_categoria_id` (`id_categorias_servicios`),
  KEY `idx_estado` (`estado`),
  CONSTRAINT `fk_servicios_belleza_1` FOREIGN KEY (`id_tenants`) REFERENCES `tenants` (`id_tenants`) ON DELETE CASCADE,
  CONSTRAINT `fk_servicios_belleza_2` FOREIGN KEY (`id_categorias_servicios`) REFERENCES `categorias_servicios` (`id_categorias_servicios`)
);

DROP TABLE IF EXISTS `suscripciones`;
CREATE TABLE `suscripciones` (
  `id_suscripciones` int(11) NOT NULL AUTO_INCREMENT,
  `id_tenants` int(11) NOT NULL,
  `id_planes_suscripcion` int(11) NOT NULL,
  `fecha_inicio` date NOT NULL,
  `fecha_proximo_pago` date NOT NULL,
  `estado` tinyint(1) DEFAULT 1,
  `precio_contratado` decimal(10,2) DEFAULT NULL,
  PRIMARY KEY (`id_suscripciones`),
  KEY `id_planes_suscripcion` (`id_planes_suscripcion`),
  KEY `idx_tenant_id` (`id_tenants`),
  KEY `idx_estado` (`estado`),
  KEY `idx_fecha_proximo_pago` (`fecha_proximo_pago`),
  CONSTRAINT `fk_suscripciones_1` FOREIGN KEY (`id_tenants`) REFERENCES `tenants` (`id_tenants`) ON DELETE CASCADE,
  CONSTRAINT `fk_suscripciones_2` FOREIGN KEY (`id_planes_suscripcion`) REFERENCES `planes_suscripcion` (`id_planes_suscripcion`)
);

-- ============================================================
-- NOTA: tipo_usuario incluye 'repartidor' para que los repartidores
-- puedan ser creados primero como usuarios del sistema y luego
-- registrados en la tabla repartidores con sus datos de vehículo.
-- ============================================================
DROP TABLE IF EXISTS `usuarios`;
CREATE TABLE `usuarios` (
  `id_usuarios` int(11) NOT NULL AUTO_INCREMENT,
  `id_tenants` int(11) NOT NULL,
  `nombre_usuario` varchar(255) NOT NULL,
  `apellidos_usuario` varchar(255) NOT NULL,
  `correo` varchar(100) NOT NULL,
  `numero_documento` varchar(20) DEFAULT NULL,
  `contraseña` varchar(255) NOT NULL,
  `tipo_usuario` enum('superadmin','admin','cajero','recepcionista','especialista','estilista','gerente','repartidor','otro') DEFAULT 'otro',
  `estado` tinyint(1) DEFAULT 1,
  PRIMARY KEY (`id_usuarios`),
  UNIQUE KEY `unique_correo_tenant` (`id_tenants`,`correo`),
  KEY `idx_tenant_id` (`id_tenants`),
  KEY `idx_correo` (`correo`),
  KEY `idx_estado` (`estado`),
  CONSTRAINT `fk_usuarios_1` FOREIGN KEY (`id_tenants`) REFERENCES `tenants` (`id_tenants`) ON DELETE CASCADE
);


DROP TABLE IF EXISTS `promociones`;
CREATE TABLE `promociones` (
  `id_promociones` int(11) NOT NULL AUTO_INCREMENT,
  `id_tenants` int(11) NOT NULL,
  `nombre_promocion` varchar(255) NOT NULL,
  `tipo_descuento` enum('fijo','porcentual','2x1','3x2','escalonado','regalo') DEFAULT NULL,
  `valor_descuento` decimal(10,2) DEFAULT NULL,
  `compra_minima` decimal(10,2) DEFAULT NULL,
  `fecha_inicio` date DEFAULT NULL,
  `fecha_fin` date DEFAULT NULL,
  `id_categorias_productos` int(11) DEFAULT NULL,
  `estado` tinyint(1) DEFAULT 1,
  PRIMARY KEY (`id_promociones`),
  KEY `id_categorias_productos` (`id_categorias_productos`),
  KEY `idx_tenant_id` (`id_tenants`),
  KEY `idx_estado` (`estado`),
  CONSTRAINT `fk_promociones_1` FOREIGN KEY (`id_tenants`) REFERENCES `tenants` (`id_tenants`) ON DELETE CASCADE,
  CONSTRAINT `fk_promociones_2` FOREIGN KEY (`id_categorias_productos`) REFERENCES `categorias_productos` (`id_categorias_productos`)
);


-- ============================================================
-- CAMBIO: Se eliminó la tabla imagenes_producto y se agregó
-- la columna img_url directamente en productos para la imagen principal.
-- ============================================================
DROP TABLE IF EXISTS `productos`;
CREATE TABLE `productos` (
  `id_productos` int(11) NOT NULL AUTO_INCREMENT,
  `id_tenants` int(11) NOT NULL,
  `id_categorias_productos` int(11) NOT NULL,
  `id_marcas` int(11) DEFAULT NULL,
  `codigo_interno` varchar(50) DEFAULT NULL,
  `codigo_barras` varchar(100) DEFAULT NULL,
  `nombre_producto` varchar(255) NOT NULL,
  `descripcion` text DEFAULT NULL,
  `tipo_producto` varchar(100) DEFAULT NULL,
  `presentacion` varchar(100) DEFAULT NULL,
  `contenido_neto` varchar(50) DEFAULT NULL,
  `img_url` varchar(255) DEFAULT NULL,
  `precio_costo` decimal(10,2) DEFAULT NULL,
  `precio_venta` decimal(10,2) DEFAULT NULL,
  `margen_ganancia` decimal(5,2) GENERATED ALWAYS AS (
    CASE
      WHEN `precio_costo` > 0
      THEN ROUND((`precio_venta` - `precio_costo`) / `precio_costo` * 100, 2)
      ELSE NULL
    END
  ) VIRTUAL,
  `stock_minimo` int(11) DEFAULT 10,
  `stock_critico` int(11) DEFAULT 5,
  `visible_storefront` tinyint(1) DEFAULT 1,
  `etiqueta_especial` varchar(100) DEFAULT NULL,
  `estado` tinyint(1) DEFAULT 1,
  PRIMARY KEY (`id_productos`),
  UNIQUE KEY `codigo_barras` (`codigo_barras`),
  KEY `idx_tenant_id` (`id_tenants`),
  KEY `idx_categoria_id` (`id_categorias_productos`),
  KEY `idx_marca_id` (`id_marcas`),
  KEY `idx_codigo_barras` (`codigo_barras`),
  KEY `idx_estado` (`estado`),
  CONSTRAINT `fk_productos_1` FOREIGN KEY (`id_tenants`) REFERENCES `tenants` (`id_tenants`) ON DELETE CASCADE,
  CONSTRAINT `fk_productos_2` FOREIGN KEY (`id_categorias_productos`) REFERENCES `categorias_productos` (`id_categorias_productos`),
  CONSTRAINT `fk_productos_3` FOREIGN KEY (`id_marcas`) REFERENCES `marcas` (`id_marcas`)
);

-- No tocar esta tabla. ya que esta sirve para poder acceder a los datos
DROP TABLE IF EXISTS `registros`;
CREATE TABLE `registros` (
  `idregistro` int(11) NOT NULL,
  `nombres` varchar(255) NOT NULL,
  `apellidos` varchar(255) NOT NULL,
  `email` varchar(255) NOT NULL,
  `cliente_id` varchar(255) NOT NULL,
  `llave_secreta` varchar(255) NOT NULL,
  `access_token` varchar(255) NOT NULL,
  `estado` tinyint(1) NOT NULL DEFAULT 1,
  PRIMARY KEY (`idregistro`)
);

DROP TABLE IF EXISTS `cuentas_por_pagar`;
CREATE TABLE `cuentas_por_pagar` (
  `id_cuentas_por_pagar` int(11) NOT NULL AUTO_INCREMENT,
  `id_tenants` int(11) NOT NULL,
  `id_proveedores` int(11) NOT NULL,
  `documento_numero` varchar(50) DEFAULT NULL,
  `tipo_documento` enum('factura','boleta','orden_compra') DEFAULT 'factura',
  `monto` decimal(10,2) DEFAULT NULL,
  `fecha_documento` date DEFAULT NULL,
  `fecha_vencimiento` date DEFAULT NULL,
  `estado_pago` enum('pendiente','pagada') DEFAULT 'pendiente',
  PRIMARY KEY (`id_cuentas_por_pagar`),
  KEY `id_proveedores` (`id_proveedores`),
  KEY `idx_tenant_id` (`id_tenants`),
  KEY `idx_estado_pago` (`estado_pago`),
  KEY `idx_fecha_vencimiento` (`fecha_vencimiento`),
  CONSTRAINT `fk_cuentas_por_pagar_1` FOREIGN KEY (`id_tenants`) REFERENCES `tenants` (`id_tenants`) ON DELETE CASCADE,
  CONSTRAINT `fk_cuentas_por_pagar_2` FOREIGN KEY (`id_proveedores`) REFERENCES `proveedores` (`id_proveedores`)
);


DROP TABLE IF EXISTS `devoluciones_proveedor`;
CREATE TABLE `devoluciones_proveedor` (
  `id_devoluciones_proveedor` int(11) NOT NULL AUTO_INCREMENT,
  `id_tenants` int(11) NOT NULL,
  `id_proveedores` int(11) NOT NULL,
  `numero_devolucion` varchar(50) DEFAULT NULL,
  `fecha_devolucion` date DEFAULT NULL,
  `motivo` varchar(255) DEFAULT NULL,
  `observaciones` text DEFAULT NULL,
  `estado` tinyint(1) DEFAULT 1,
  PRIMARY KEY (`id_devoluciones_proveedor`),
  UNIQUE KEY `numero_devolucion` (`numero_devolucion`),
  KEY `id_proveedores` (`id_proveedores`),
  KEY `idx_tenant_id` (`id_tenants`),
  KEY `idx_estado` (`estado`),
  CONSTRAINT `fk_devoluciones_proveedor_1` FOREIGN KEY (`id_tenants`) REFERENCES `tenants` (`id_tenants`) ON DELETE CASCADE,
  CONSTRAINT `fk_devoluciones_proveedor_2` FOREIGN KEY (`id_proveedores`) REFERENCES `proveedores` (`id_proveedores`)
);


DROP TABLE IF EXISTS `ordenes_compra`;
CREATE TABLE `ordenes_compra` (
  `id_ordenes_compra` int(11) NOT NULL AUTO_INCREMENT,
  `id_tenants` int(11) NOT NULL,
  `id_proveedores` int(11) NOT NULL,
  `numero_orden` varchar(50) DEFAULT NULL,
  `fecha_orden` date DEFAULT NULL,
  `fecha_entrega_estimada` date DEFAULT NULL,
  `estado` tinyint(1) DEFAULT 1,
  `monto_total` decimal(10,2) DEFAULT NULL,
  `condiciones_entrega` text DEFAULT NULL,
  `forma_pago` varchar(100) DEFAULT NULL,
  `notas` text DEFAULT NULL,
  PRIMARY KEY (`id_ordenes_compra`),
  UNIQUE KEY `numero_orden` (`numero_orden`),
  KEY `idx_tenant_id` (`id_tenants`),
  KEY `idx_proveedor_id` (`id_proveedores`),
  KEY `idx_estado` (`estado`),
  CONSTRAINT `fk_ordenes_compra_1` FOREIGN KEY (`id_tenants`) REFERENCES `tenants` (`id_tenants`) ON DELETE CASCADE,
  CONSTRAINT `fk_ordenes_compra_2` FOREIGN KEY (`id_proveedores`) REFERENCES `proveedores` (`id_proveedores`)
);


DROP TABLE IF EXISTS `permisos_rol`;
CREATE TABLE `permisos_rol` (
  `id_permisos_rol` int(11) NOT NULL AUTO_INCREMENT,
  `id_roles_personalizados` int(11) NOT NULL,
  `modulo` varchar(100) DEFAULT NULL,
  `accion` enum('crear','editar','eliminar','visualizar') NOT NULL,
  `recurso` varchar(100) DEFAULT NULL,
  `estado` tinyint(1) DEFAULT 1,
  PRIMARY KEY (`id_permisos_rol`),
  KEY `idx_rol_id` (`id_roles_personalizados`),
  CONSTRAINT `fk_permisos_rol_1` FOREIGN KEY (`id_roles_personalizados`) REFERENCES `roles_personalizados` (`id_roles_personalizados`) ON DELETE CASCADE
);


DROP TABLE IF EXISTS `almacenes`;
CREATE TABLE `almacenes` (
  `id_almacenes` int(11) NOT NULL AUTO_INCREMENT,
  `id_sedes` int(11) NOT NULL,
  `nombre_almacen` varchar(100) NOT NULL,
  `ubicacion` varchar(255) DEFAULT NULL,
  `capacidad` int(11) DEFAULT NULL,
  `estado` tinyint(1) DEFAULT 1,
  PRIMARY KEY (`id_almacenes`),
  KEY `idx_sede_id` (`id_sedes`),
  CONSTRAINT `fk_almacenes_1` FOREIGN KEY (`id_sedes`) REFERENCES `sedes` (`id_sedes`) ON DELETE CASCADE
);


DROP TABLE IF EXISTS `gastos_recurrentes`;
CREATE TABLE `gastos_recurrentes` (
  `id_gastos_recurrentes` int(11) NOT NULL AUTO_INCREMENT,
  `id_tenants` int(11) NOT NULL,
  `id_sedes` int(11) DEFAULT NULL,
  `concepto` varchar(255) DEFAULT NULL,
  `monto` decimal(10,2) DEFAULT NULL,
  `frecuencia` enum('diaria','semanal','quincenal','mensual','trimestral','anual') DEFAULT 'mensual',
  `fecha_proximo_registro` date DEFAULT NULL,
  `estado` tinyint(1) DEFAULT 1,
  PRIMARY KEY (`id_gastos_recurrentes`),
  KEY `id_sedes` (`id_sedes`),
  KEY `idx_tenant_id` (`id_tenants`),
  CONSTRAINT `fk_gastos_recurrentes_1` FOREIGN KEY (`id_tenants`) REFERENCES `tenants` (`id_tenants`) ON DELETE CASCADE,
  CONSTRAINT `fk_gastos_recurrentes_2` FOREIGN KEY (`id_sedes`) REFERENCES `sedes` (`id_sedes`)
);


DROP TABLE IF EXISTS `horarios_operacion`;
CREATE TABLE `horarios_operacion` (
  `id_horarios_operacion` int(11) NOT NULL AUTO_INCREMENT,
  `id_sedes` int(11) NOT NULL,
  `dia_semana` enum('lunes','martes','miercoles','jueves','viernes','sabado','domingo') DEFAULT NULL,
  `hora_apertura` time DEFAULT NULL,
  `hora_cierre` time DEFAULT NULL,
  `estado` tinyint(1) DEFAULT 1,
  PRIMARY KEY (`id_horarios_operacion`),
  UNIQUE KEY `unique_sede_dia` (`id_sedes`,`dia_semana`),
  KEY `idx_sede_id` (`id_sedes`),
  CONSTRAINT `fk_horarios_operacion_1` FOREIGN KEY (`id_sedes`) REFERENCES `sedes` (`id_sedes`) ON DELETE CASCADE
);


DROP TABLE IF EXISTS `zonas_delivery`;
CREATE TABLE `zonas_delivery` (
  `id_zonas_delivery` int(11) NOT NULL AUTO_INCREMENT,
  `id_sedes` int(11) NOT NULL,
  `nombre_zona` varchar(100) DEFAULT NULL,
  `distritos` varchar(500) DEFAULT NULL,
  `costo_fijo` decimal(10,2) DEFAULT NULL,
  `monto_minimo_compra` decimal(10,2) DEFAULT NULL,
  `tiempo_estimado_minutos` int(11) DEFAULT NULL,
  `estado` tinyint(1) DEFAULT 1,
  PRIMARY KEY (`id_zonas_delivery`),
  KEY `idx_sede_id` (`id_sedes`),
  CONSTRAINT `fk_zonas_delivery_1` FOREIGN KEY (`id_sedes`) REFERENCES `sedes` (`id_sedes`)
);


DROP TABLE IF EXISTS `facturas_suscripcion`;
CREATE TABLE `facturas_suscripcion` (
  `id_facturas_suscripcion` int(11) NOT NULL AUTO_INCREMENT,
  `id_suscripciones` int(11) NOT NULL,
  `numero_factura` varchar(50) DEFAULT NULL,
  `monto` decimal(10,2) DEFAULT NULL,
  `fecha_emision` date DEFAULT NULL,
  `fecha_vencimiento` date DEFAULT NULL,
  `estado_pago` enum('pendiente','pagada','vencida','cancelada') DEFAULT 'pendiente',
  `metodo_pago` varchar(50) DEFAULT NULL,
  `fecha_pago` timestamp NULL DEFAULT NULL,
  PRIMARY KEY (`id_facturas_suscripcion`),
  UNIQUE KEY `numero_factura` (`numero_factura`),
  KEY `id_suscripciones` (`id_suscripciones`),
  KEY `idx_estado_pago` (`estado_pago`),
  KEY `idx_fecha_vencimiento` (`fecha_vencimiento`),
  CONSTRAINT `fk_facturas_suscripcion_1` FOREIGN KEY (`id_suscripciones`) REFERENCES `suscripciones` (`id_suscripciones`) ON DELETE CASCADE
);


DROP TABLE IF EXISTS `auditoria`;
CREATE TABLE `auditoria` (
  `id_auditoria` int(11) NOT NULL AUTO_INCREMENT,
  `id_tenants` int(11) NOT NULL,
  `id_usuarios` int(11) DEFAULT NULL,
  `accion` varchar(255) DEFAULT NULL,
  `tabla_afectada` varchar(100) DEFAULT NULL,
  `id_registro` int(11) DEFAULT NULL,
  `datos_anteriores` longtext CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL CHECK (json_valid(`datos_anteriores`)),
  `datos_nuevos` longtext CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL CHECK (json_valid(`datos_nuevos`)),
  `ip_address` varchar(45) DEFAULT NULL,
  `fecha_hora` timestamp NULL DEFAULT current_timestamp(),
  PRIMARY KEY (`id_auditoria`),
  KEY `idx_tenant_id` (`id_tenants`),
  KEY `idx_usuario_id` (`id_usuarios`),
  KEY `idx_fecha_hora` (`fecha_hora`),
  KEY `idx_accion` (`accion`),
  CONSTRAINT `fk_auditoria_1` FOREIGN KEY (`id_tenants`) REFERENCES `tenants` (`id_tenants`) ON DELETE CASCADE,
  CONSTRAINT `fk_auditoria_2` FOREIGN KEY (`id_usuarios`) REFERENCES `usuarios` (`id_usuarios`) ON DELETE SET NULL
);


DROP TABLE IF EXISTS `citas`;
CREATE TABLE `citas` (
  `id_citas` int(11) NOT NULL AUTO_INCREMENT,
  `id_tenants` int(11) NOT NULL,
  `id_sedes` int(11) NOT NULL,
  `id_clientes` int(11) NOT NULL,
  `id_usuarios_especialista` int(11) DEFAULT NULL,
  `fecha_cita` date DEFAULT NULL,
  `hora_inicio` time DEFAULT NULL,
  `hora_fin` time DEFAULT NULL,
  `duracion_minutos` int(11) DEFAULT NULL,
  `estado` tinyint(1) DEFAULT 1,
  `observaciones` text DEFAULT NULL,
  `id_usuarios_usuario_creacion` int(11) DEFAULT NULL,
  PRIMARY KEY (`id_citas`),
  KEY `id_sedes` (`id_sedes`),
  KEY `id_usuarios_usuario_creacion` (`id_usuarios_usuario_creacion`),
  KEY `idx_tenant_id` (`id_tenants`),
  KEY `idx_fecha_cita` (`fecha_cita`),
  KEY `idx_estado` (`estado`),
  KEY `idx_cita_cliente` (`id_clientes`),
  KEY `idx_cita_especialista` (`id_usuarios_especialista`),
  CONSTRAINT `fk_citas_1` FOREIGN KEY (`id_tenants`) REFERENCES `tenants` (`id_tenants`) ON DELETE CASCADE,
  CONSTRAINT `fk_citas_2` FOREIGN KEY (`id_sedes`) REFERENCES `sedes` (`id_sedes`),
  CONSTRAINT `fk_citas_3` FOREIGN KEY (`id_clientes`) REFERENCES `clientes` (`id_clientes`),
  CONSTRAINT `fk_citas_4` FOREIGN KEY (`id_usuarios_especialista`) REFERENCES `usuarios` (`id_usuarios`),
  CONSTRAINT `fk_citas_5` FOREIGN KEY (`id_usuarios_usuario_creacion`) REFERENCES `usuarios` (`id_usuarios`)
);


DROP TABLE IF EXISTS `gastos_operativos`;
CREATE TABLE `gastos_operativos` (
  `id_gastos_operativos` int(11) NOT NULL AUTO_INCREMENT,
  `id_tenants` int(11) NOT NULL,
  `id_sedes` int(11) DEFAULT NULL,
  `concepto` varchar(255) DEFAULT NULL,
  `categoria` enum('insumos','cosmeticos','publicidad','servicios_basicos','mantenimiento','transporte','personal','otro') DEFAULT 'otro',
  `monto` decimal(10,2) DEFAULT NULL,
  `descripcion` text DEFAULT NULL,
  `id_proveedores` int(11) DEFAULT NULL,
  `comprobante_numero` varchar(50) DEFAULT NULL,
  `archivo_evidencia` varchar(255) DEFAULT NULL,
  `estado` tinyint(1) DEFAULT 1,
  `id_usuarios_usuario_creacion` int(11) DEFAULT NULL,
  `id_usuarios_usuario_aprobacion` int(11) DEFAULT NULL,
  `fecha_gasto` date DEFAULT NULL,
  PRIMARY KEY (`id_gastos_operativos`),
  KEY `id_sedes` (`id_sedes`),
  KEY `id_proveedores` (`id_proveedores`),
  KEY `id_usuarios_usuario_creacion` (`id_usuarios_usuario_creacion`),
  KEY `id_usuarios_usuario_aprobacion` (`id_usuarios_usuario_aprobacion`),
  KEY `idx_tenant_id` (`id_tenants`),
  KEY `idx_estado` (`estado`),
  KEY `idx_fecha_gasto` (`fecha_gasto`),
  CONSTRAINT `fk_gastos_operativos_1` FOREIGN KEY (`id_tenants`) REFERENCES `tenants` (`id_tenants`) ON DELETE CASCADE,
  CONSTRAINT `fk_gastos_operativos_2` FOREIGN KEY (`id_sedes`) REFERENCES `sedes` (`id_sedes`),
  CONSTRAINT `fk_gastos_operativos_3` FOREIGN KEY (`id_proveedores`) REFERENCES `proveedores` (`id_proveedores`),
  CONSTRAINT `fk_gastos_operativos_4` FOREIGN KEY (`id_usuarios_usuario_creacion`) REFERENCES `usuarios` (`id_usuarios`),
  CONSTRAINT `fk_gastos_operativos_5` FOREIGN KEY (`id_usuarios_usuario_aprobacion`) REFERENCES `usuarios` (`id_usuarios`)
);


DROP TABLE IF EXISTS `notificaciones`;
CREATE TABLE `notificaciones` (
  `id_notificaciones` int(11) NOT NULL AUTO_INCREMENT,
  `id_tenants` int(11) NOT NULL,
  `id_usuarios` int(11) DEFAULT NULL,
  `tipo` enum('stock_bajo','producto_vencer','cita_pendiente','cancelacion','acceso_sospechoso','pago_vencido','otro') DEFAULT 'otro',
  `titulo` varchar(255) DEFAULT NULL,
  `mensaje` text DEFAULT NULL,
  `canal_envio` enum('correo','sms','sistema','whatsapp') DEFAULT 'sistema',
  `estado_lectura` enum('no_leido','leido') DEFAULT 'no_leido',
  `fecha_lectura` timestamp NULL DEFAULT NULL,
  PRIMARY KEY (`id_notificaciones`),
  KEY `id_usuarios` (`id_usuarios`),
  KEY `idx_tenant_id` (`id_tenants`),
  KEY `idx_estado_lectura` (`estado_lectura`),
  CONSTRAINT `fk_notificaciones_1` FOREIGN KEY (`id_tenants`) REFERENCES `tenants` (`id_tenants`) ON DELETE CASCADE,
  CONSTRAINT `fk_notificaciones_2` FOREIGN KEY (`id_usuarios`) REFERENCES `usuarios` (`id_usuarios`)
);


-- ============================================================
-- CORRECCIÓN: Se agrega FK a zonas_delivery que faltaba en v2
-- ============================================================
DROP TABLE IF EXISTS `pedidos`;
CREATE TABLE `pedidos` (
  `id_pedidos` int(11) NOT NULL AUTO_INCREMENT,
  `id_tenants` int(11) NOT NULL,
  `id_clientes` int(11) NOT NULL,
  `numero_pedido` varchar(50) DEFAULT NULL,
  `modalidad` enum('delivery','pickup','presencial') DEFAULT 'delivery',
  `estado` tinyint(1) DEFAULT 1,
  `subtotal` decimal(10,2) DEFAULT NULL,
  `costo_envio` decimal(10,2) DEFAULT 0.00,
  `impuesto` decimal(10,2) DEFAULT NULL,
  `total` decimal(10,2) DEFAULT NULL,
  `direccion_entrega` varchar(255) DEFAULT NULL,
  `id_zonas_delivery` int(11) DEFAULT NULL,
  `id_usuarios` int(11) DEFAULT NULL,
  `fecha_pedido` timestamp NULL DEFAULT current_timestamp(),
  `fecha_entrega_estimada` datetime DEFAULT NULL,
  `fecha_entrega_real` datetime DEFAULT NULL,
  PRIMARY KEY (`id_pedidos`),
  UNIQUE KEY `numero_pedido` (`numero_pedido`),
  KEY `id_usuarios` (`id_usuarios`),
  KEY `id_zonas_delivery` (`id_zonas_delivery`),
  KEY `idx_tenant_id` (`id_tenants`),
  KEY `idx_estado` (`estado`),
  KEY `idx_fecha_pedido` (`fecha_pedido`),
  KEY `idx_pedido_cliente` (`id_clientes`),
  CONSTRAINT `fk_pedidos_1` FOREIGN KEY (`id_tenants`) REFERENCES `tenants` (`id_tenants`) ON DELETE CASCADE,
  CONSTRAINT `fk_pedidos_2` FOREIGN KEY (`id_clientes`) REFERENCES `clientes` (`id_clientes`),
  CONSTRAINT `fk_pedidos_3` FOREIGN KEY (`id_usuarios`) REFERENCES `usuarios` (`id_usuarios`),
  CONSTRAINT `fk_pedidos_4` FOREIGN KEY (`id_zonas_delivery`) REFERENCES `zonas_delivery` (`id_zonas_delivery`)
);


DROP TABLE IF EXISTS `preferencias_usuario`;
CREATE TABLE `preferencias_usuario` (
  `id_preferencias_usuario` int(11) NOT NULL AUTO_INCREMENT,
  `id_usuarios` int(11) NOT NULL,
  `idioma` varchar(10) DEFAULT 'es',
  `formato_fecha` varchar(20) DEFAULT 'DD/MM/YYYY',
  `zona_horaria` varchar(50) DEFAULT NULL,
  `notificaciones_activas` tinyint(1) DEFAULT 1,
  `vista_inicial` varchar(100) DEFAULT NULL,
  `tema_interfaz` enum('claro','oscuro') DEFAULT 'claro',
  PRIMARY KEY (`id_preferencias_usuario`),
  UNIQUE KEY `unique_usuario` (`id_usuarios`),
  KEY `idx_usuario_id` (`id_usuarios`),
  CONSTRAINT `fk_preferencias_usuario_1` FOREIGN KEY (`id_usuarios`) REFERENCES `usuarios` (`id_usuarios`) ON DELETE CASCADE
);


-- ============================================================
-- CAMBIO: repartidores ahora vinculado a usuarios (id_usuarios)
-- y a tenants (id_tenants). El flujo correcto es:
--   1. Crear el usuario con tipo_usuario = 'repartidor'
--   2. Registrar en repartidores referenciando ese id_usuarios
-- ============================================================
DROP TABLE IF EXISTS `repartidores`;
CREATE TABLE `repartidores` (
  `id_repartidores` int(11) NOT NULL AUTO_INCREMENT,
  `id_tenants` int(11) NOT NULL,
  `id_usuarios` int(11) NOT NULL,
  `tipo_vehiculo` varchar(100) DEFAULT NULL,
  `placa_vehiculo` varchar(20) DEFAULT NULL,
  `numero_licencia` varchar(50) DEFAULT NULL,
  `estado` tinyint(1) DEFAULT 1,
  PRIMARY KEY (`id_repartidores`),
  UNIQUE KEY `unique_usuario_repartidor` (`id_usuarios`),
  KEY `idx_tenant_id` (`id_tenants`),
  KEY `idx_usuario_id` (`id_usuarios`),
  KEY `idx_estado` (`estado`),
  CONSTRAINT `fk_repartidores_1` FOREIGN KEY (`id_tenants`) REFERENCES `tenants` (`id_tenants`) ON DELETE CASCADE,
  CONSTRAINT `fk_repartidores_2` FOREIGN KEY (`id_usuarios`) REFERENCES `usuarios` (`id_usuarios`) ON DELETE CASCADE
);


DROP TABLE IF EXISTS `sesiones_caja`;
CREATE TABLE `sesiones_caja` (
  `id_sesiones_caja` int(11) NOT NULL AUTO_INCREMENT,
  `id_sedes` int(11) NOT NULL,
  `id_usuarios` int(11) NOT NULL,
  `fecha_apertura` timestamp NULL DEFAULT current_timestamp(),
  `fecha_cierre` timestamp NULL DEFAULT NULL,
  `monto_inicial` decimal(10,2) DEFAULT NULL,
  `monto_final` decimal(10,2) DEFAULT NULL,
  `diferencia` decimal(10,2) DEFAULT NULL,
  `estado` tinyint(1) DEFAULT 1,
  `observaciones` text DEFAULT NULL,
  PRIMARY KEY (`id_sesiones_caja`),
  KEY `idx_sede_id` (`id_sedes`),
  KEY `idx_usuario_id` (`id_usuarios`),
  KEY `idx_estado` (`estado`),
  CONSTRAINT `fk_sesiones_caja_1` FOREIGN KEY (`id_sedes`) REFERENCES `sedes` (`id_sedes`),
  CONSTRAINT `fk_sesiones_caja_2` FOREIGN KEY (`id_usuarios`) REFERENCES `usuarios` (`id_usuarios`)
);


DROP TABLE IF EXISTS `usuario_sedes`;
CREATE TABLE `usuario_sedes` (
  `id_usuario_sedes` int(11) NOT NULL AUTO_INCREMENT,
  `id_usuarios` int(11) NOT NULL,
  `id_sedes` int(11) NOT NULL,
  `fecha_asignacion` timestamp NULL DEFAULT current_timestamp(),
  PRIMARY KEY (`id_usuario_sedes`),
  UNIQUE KEY `unique_usuario_sede` (`id_usuarios`,`id_sedes`),
  KEY `idx_usuario_id` (`id_usuarios`),
  KEY `idx_sede_id` (`id_sedes`),
  CONSTRAINT `fk_usuario_sedes_1` FOREIGN KEY (`id_usuarios`) REFERENCES `usuarios` (`id_usuarios`) ON DELETE CASCADE,
  CONSTRAINT `fk_usuario_sedes_2` FOREIGN KEY (`id_sedes`) REFERENCES `sedes` (`id_sedes`) ON DELETE CASCADE
);


DROP TABLE IF EXISTS `composicion_combo`;
CREATE TABLE `composicion_combo` (
  `id_composicion_combo` int(11) NOT NULL AUTO_INCREMENT,
  `id_combos_promocionales` int(11) NOT NULL,
  `id_productos` int(11) NOT NULL,
  `cantidad` int(11) DEFAULT NULL,
  PRIMARY KEY (`id_composicion_combo`),
  KEY `id_productos` (`id_productos`),
  KEY `idx_combo_id` (`id_combos_promocionales`),
  CONSTRAINT `fk_composicion_combo_1` FOREIGN KEY (`id_combos_promocionales`) REFERENCES `combos_promocionales` (`id_combos_promocionales`) ON DELETE CASCADE,
  CONSTRAINT `fk_composicion_combo_2` FOREIGN KEY (`id_productos`) REFERENCES `productos` (`id_productos`)
);

-- imagenes_producto eliminada (reemplazada por img_url en productos)

DROP TABLE IF EXISTS `pagos_proveedor`;
CREATE TABLE `pagos_proveedor` (
  `id_pagos_proveedor` int(11) NOT NULL AUTO_INCREMENT,
  `id_cuentas_por_pagar` int(11) NOT NULL,
  `monto_pagado` decimal(10,2) DEFAULT NULL,
  `forma_pago` enum('efectivo','transferencia','tarjeta','otro') DEFAULT 'efectivo',
  `numero_comprobante` varchar(50) DEFAULT NULL,
  `fecha_pago` date DEFAULT NULL,
  `id_usuarios` int(11) DEFAULT NULL,
  `observaciones` text DEFAULT NULL,
  PRIMARY KEY (`id_pagos_proveedor`),
  KEY `id_usuarios` (`id_usuarios`),
  KEY `idx_cuenta_id` (`id_cuentas_por_pagar`),
  KEY `idx_fecha_pago` (`fecha_pago`),
  CONSTRAINT `fk_pagos_proveedor_1` FOREIGN KEY (`id_cuentas_por_pagar`) REFERENCES `cuentas_por_pagar` (`id_cuentas_por_pagar`),
  CONSTRAINT `fk_pagos_proveedor_2` FOREIGN KEY (`id_usuarios`) REFERENCES `usuarios` (`id_usuarios`)
);


DROP TABLE IF EXISTS `detalle_orden_compra`;
CREATE TABLE `detalle_orden_compra` (
  `id_detalle_orden_compra` int(11) NOT NULL AUTO_INCREMENT,
  `id_ordenes_compra` int(11) NOT NULL,
  `id_productos` int(11) NOT NULL,
  `cantidad_solicitada` int(11) DEFAULT NULL,
  `cantidad_recibida` int(11) DEFAULT 0,
  `precio_unitario` decimal(10,2) DEFAULT NULL,
  `subtotal` decimal(10,2) DEFAULT NULL,
  PRIMARY KEY (`id_detalle_orden_compra`),
  KEY `id_productos` (`id_productos`),
  KEY `idx_orden_compra_id` (`id_ordenes_compra`),
  CONSTRAINT `fk_detalle_orden_compra_1` FOREIGN KEY (`id_ordenes_compra`) REFERENCES `ordenes_compra` (`id_ordenes_compra`) ON DELETE CASCADE,
  CONSTRAINT `fk_detalle_orden_compra_2` FOREIGN KEY (`id_productos`) REFERENCES `productos` (`id_productos`)
);


-- ============================================================
-- CORRECCIÓN: Se agrega FK a proveedores que faltaba en v2
-- ============================================================
DROP TABLE IF EXISTS `lotes_inventario`;
CREATE TABLE `lotes_inventario` (
  `id_lotes_inventario` int(11) NOT NULL AUTO_INCREMENT,
  `id_productos` int(11) NOT NULL,
  `id_almacenes` int(11) NOT NULL,
  `numero_lote` varchar(100) DEFAULT NULL,
  `cantidad` int(11) DEFAULT NULL,
  `cantidad_disponible` int(11) DEFAULT NULL,
  `precio_unitario` decimal(10,2) DEFAULT NULL,
  `fecha_vencimiento` date DEFAULT NULL,
  `id_proveedores` int(11) DEFAULT NULL,
  `fecha_ingreso` date DEFAULT NULL,
  `observaciones` text DEFAULT NULL,
  `estado` tinyint(1) DEFAULT 1,
  PRIMARY KEY (`id_lotes_inventario`),
  KEY `idx_producto_id` (`id_productos`),
  KEY `idx_almacen_id` (`id_almacenes`),
  KEY `idx_proveedor_id` (`id_proveedores`),
  KEY `idx_fecha_vencimiento` (`fecha_vencimiento`),
  KEY `idx_estado` (`estado`),
  CONSTRAINT `fk_lotes_inventario_1` FOREIGN KEY (`id_productos`) REFERENCES `productos` (`id_productos`),
  CONSTRAINT `fk_lotes_inventario_2` FOREIGN KEY (`id_almacenes`) REFERENCES `almacenes` (`id_almacenes`),
  CONSTRAINT `fk_lotes_inventario_3` FOREIGN KEY (`id_proveedores`) REFERENCES `proveedores` (`id_proveedores`)
);

DROP TABLE IF EXISTS `servicios_cita`;
CREATE TABLE `servicios_cita` (
  `id_servicios_cita` int(11) NOT NULL AUTO_INCREMENT,
  `id_citas` int(11) NOT NULL,
  `id_servicios_belleza` int(11) NOT NULL,
  `precio` decimal(10,2) DEFAULT NULL,
  `observaciones` text DEFAULT NULL,
  `fecha_registro` timestamp NULL DEFAULT current_timestamp(),
  PRIMARY KEY (`id_servicios_cita`),
  KEY `id_servicios_belleza` (`id_servicios_belleza`),
  KEY `idx_cita_id` (`id_citas`),
  CONSTRAINT `fk_servicios_cita_1` FOREIGN KEY (`id_citas`) REFERENCES `citas` (`id_citas`) ON DELETE CASCADE,
  CONSTRAINT `fk_servicios_cita_2` FOREIGN KEY (`id_servicios_belleza`) REFERENCES `servicios_belleza` (`id_servicios_belleza`)
);


DROP TABLE IF EXISTS `detalle_pedido`;
CREATE TABLE `detalle_pedido` (
  `id_detalle_pedido` int(11) NOT NULL AUTO_INCREMENT,
  `id_pedidos` int(11) NOT NULL,
  `id_productos` int(11) NOT NULL,
  `cantidad` int(11) DEFAULT NULL,
  `precio_unitario` decimal(10,2) DEFAULT NULL,
  `descuento` decimal(10,2) DEFAULT 0.00,
  `subtotal` decimal(10,2) DEFAULT NULL,
  PRIMARY KEY (`id_detalle_pedido`),
  KEY `id_productos` (`id_productos`),
  KEY `idx_pedido_id` (`id_pedidos`),
  CONSTRAINT `fk_detalle_pedido_1` FOREIGN KEY (`id_pedidos`) REFERENCES `pedidos` (`id_pedidos`) ON DELETE CASCADE,
  CONSTRAINT `fk_detalle_pedido_2` FOREIGN KEY (`id_productos`) REFERENCES `productos` (`id_productos`)
);


DROP TABLE IF EXISTS `caja_chica`;
CREATE TABLE `caja_chica` (
  `id_caja_chica` int(11) NOT NULL AUTO_INCREMENT,
  `id_sesiones_caja` int(11) NOT NULL,
  `concepto` varchar(255) DEFAULT NULL,
  `monto` decimal(10,2) DEFAULT NULL,
  `descripcion` text DEFAULT NULL,
  `comprobante_url` varchar(255) DEFAULT NULL,
  `id_usuarios` int(11) DEFAULT NULL,
  `fecha_registro` timestamp NULL DEFAULT current_timestamp(),
  PRIMARY KEY (`id_caja_chica`),
  KEY `id_usuarios` (`id_usuarios`),
  KEY `idx_sesion_caja_id` (`id_sesiones_caja`),
  CONSTRAINT `fk_caja_chica_1` FOREIGN KEY (`id_sesiones_caja`) REFERENCES `sesiones_caja` (`id_sesiones_caja`),
  CONSTRAINT `fk_caja_chica_2` FOREIGN KEY (`id_usuarios`) REFERENCES `usuarios` (`id_usuarios`)
);


DROP TABLE IF EXISTS `ventas`;
CREATE TABLE `ventas` (
  `id_ventas` int(11) NOT NULL AUTO_INCREMENT,
  `id_tenants` int(11) NOT NULL,
  `id_sedes` int(11) NOT NULL,
  `id_sesiones_caja` int(11) NOT NULL,
  `id_clientes` int(11) DEFAULT NULL,
  `numero_ticket` varchar(50) DEFAULT NULL,
  `comprobante_numero` varchar(50) DEFAULT NULL,
  `tipo_comprobante` enum('boleta','factura','nota_credito','nota_debito') DEFAULT 'boleta',
  `subtotal` decimal(10,2) DEFAULT NULL,
  `descuento` decimal(10,2) DEFAULT 0.00,
  `impuesto` decimal(10,2) DEFAULT NULL,
  `total` decimal(10,2) DEFAULT NULL,
  `estado` tinyint(1) DEFAULT 1,
  `estado_sunat` enum('aceptada','observada','rechazada','pendiente') DEFAULT 'pendiente',
  `id_usuarios` int(11) DEFAULT NULL,
  `fecha_venta` timestamp NULL DEFAULT current_timestamp(),
  PRIMARY KEY (`id_ventas`),
  UNIQUE KEY `comprobante_numero` (`comprobante_numero`),
  KEY `id_sesiones_caja` (`id_sesiones_caja`),
  KEY `idx_tenant_id` (`id_tenants`),
  KEY `idx_sede_id` (`id_sedes`),
  KEY `idx_fecha_venta` (`fecha_venta`),
  KEY `idx_estado` (`estado`),
  KEY `idx_venta_cliente` (`id_clientes`),
  KEY `idx_venta_usuario` (`id_usuarios`),
  CONSTRAINT `fk_ventas_1` FOREIGN KEY (`id_tenants`) REFERENCES `tenants` (`id_tenants`) ON DELETE CASCADE,
  CONSTRAINT `fk_ventas_2` FOREIGN KEY (`id_sedes`) REFERENCES `sedes` (`id_sedes`),
  CONSTRAINT `fk_ventas_3` FOREIGN KEY (`id_sesiones_caja`) REFERENCES `sesiones_caja` (`id_sesiones_caja`),
  CONSTRAINT `fk_ventas_4` FOREIGN KEY (`id_clientes`) REFERENCES `clientes` (`id_clientes`),
  CONSTRAINT `fk_ventas_5` FOREIGN KEY (`id_usuarios`) REFERENCES `usuarios` (`id_usuarios`)
);


DROP TABLE IF EXISTS `detalle_devolucion_proveedor`;
CREATE TABLE `detalle_devolucion_proveedor` (
  `id_detalle_devolucion_proveedor` int(11) NOT NULL AUTO_INCREMENT,
  `id_devoluciones_proveedor` int(11) NOT NULL,
  `id_productos` int(11) NOT NULL,
  `id_lotes_inventario` int(11) DEFAULT NULL,
  `cantidad` int(11) DEFAULT NULL,
  PRIMARY KEY (`id_detalle_devolucion_proveedor`),
  KEY `id_productos` (`id_productos`),
  KEY `id_lotes_inventario` (`id_lotes_inventario`),
  KEY `idx_devolucion_id` (`id_devoluciones_proveedor`),
  CONSTRAINT `fk_detalle_devolucion_proveedor_1` FOREIGN KEY (`id_devoluciones_proveedor`) REFERENCES `devoluciones_proveedor` (`id_devoluciones_proveedor`) ON DELETE CASCADE,
  CONSTRAINT `fk_detalle_devolucion_proveedor_2` FOREIGN KEY (`id_productos`) REFERENCES `productos` (`id_productos`),
  CONSTRAINT `fk_detalle_devolucion_proveedor_3` FOREIGN KEY (`id_lotes_inventario`) REFERENCES `lotes_inventario` (`id_lotes_inventario`)
);


DROP TABLE IF EXISTS `movimientos_inventario`;
CREATE TABLE `movimientos_inventario` (
  `id_movimientos_inventario` int(11) NOT NULL AUTO_INCREMENT,
  `id_lotes_inventario` int(11) NOT NULL,
  `tipo_movimiento` enum('entrada','salida','ajuste','transferencia','merma','devolucion') NOT NULL,
  `cantidad` int(11) DEFAULT NULL,
  `id_usuarios` int(11) DEFAULT NULL,
  `motivo` varchar(255) DEFAULT NULL,
  `referencia_documento` varchar(100) DEFAULT NULL,
  `fecha_movimiento` timestamp NULL DEFAULT current_timestamp(),
  PRIMARY KEY (`id_movimientos_inventario`),
  KEY `id_usuarios` (`id_usuarios`),
  KEY `idx_lote_id` (`id_lotes_inventario`),
  KEY `idx_tipo_movimiento` (`tipo_movimiento`),
  KEY `idx_fecha_movimiento` (`fecha_movimiento`),
  CONSTRAINT `fk_movimientos_inventario_1` FOREIGN KEY (`id_lotes_inventario`) REFERENCES `lotes_inventario` (`id_lotes_inventario`),
  CONSTRAINT `fk_movimientos_inventario_2` FOREIGN KEY (`id_usuarios`) REFERENCES `usuarios` (`id_usuarios`)
);

DROP TABLE IF EXISTS `comprobantes_electronicos`;
CREATE TABLE `comprobantes_electronicos` (
  `id_comprobantes_electronicos` int(11) NOT NULL AUTO_INCREMENT,
  `id_tenants` int(11) NOT NULL,
  `tipo_comprobante` enum('boleta','factura','nota_credito','nota_debito') NOT NULL,
  `numero_serie` varchar(10) DEFAULT NULL,
  `numero_comprobante` varchar(10) DEFAULT NULL,
  `id_ventas` int(11) DEFAULT NULL,
  `estado_sunat` enum('aceptada','observada','rechazada','pendiente_envio') DEFAULT 'pendiente_envio',
  `respuesta_sunat` text DEFAULT NULL,
  `fecha_envio_sunat` timestamp NULL DEFAULT NULL,
  `xml_generado` longtext DEFAULT NULL,
  `cdr_recibida` longtext DEFAULT NULL,
  PRIMARY KEY (`id_comprobantes_electronicos`),
  UNIQUE KEY `unique_comprobante` (`id_tenants`,`numero_serie`,`numero_comprobante`),
  KEY `id_ventas` (`id_ventas`),
  KEY `idx_tenant_id` (`id_tenants`),
  KEY `idx_estado_sunat` (`estado_sunat`),
  CONSTRAINT `fk_comprobantes_electronicos_1` FOREIGN KEY (`id_tenants`) REFERENCES `tenants` (`id_tenants`) ON DELETE CASCADE,
  CONSTRAINT `fk_comprobantes_electronicos_2` FOREIGN KEY (`id_ventas`) REFERENCES `ventas` (`id_ventas`)
);


DROP TABLE IF EXISTS `detalle_venta`;
CREATE TABLE `detalle_venta` (
  `id_detalle_venta` int(11) NOT NULL AUTO_INCREMENT,
  `id_ventas` int(11) NOT NULL,
  `id_productos` int(11) DEFAULT NULL,
  `cantidad` int(11) DEFAULT NULL,
  `precio_unitario` decimal(10,2) DEFAULT NULL,
  `descuento` decimal(10,2) DEFAULT 0.00,
  `subtotal` decimal(10,2) DEFAULT NULL,
  `id_lotes_inventario` int(11) DEFAULT NULL,
  PRIMARY KEY (`id_detalle_venta`),
  KEY `id_productos` (`id_productos`),
  KEY `id_lotes_inventario` (`id_lotes_inventario`),
  KEY `idx_venta_id` (`id_ventas`),
  CONSTRAINT `fk_detalle_venta_1` FOREIGN KEY (`id_ventas`) REFERENCES `ventas` (`id_ventas`) ON DELETE CASCADE,
  CONSTRAINT `fk_detalle_venta_2` FOREIGN KEY (`id_productos`) REFERENCES `productos` (`id_productos`),
  CONSTRAINT `fk_detalle_venta_3` FOREIGN KEY (`id_lotes_inventario`) REFERENCES `lotes_inventario` (`id_lotes_inventario`)
);


DROP TABLE IF EXISTS `devoluciones_venta`;
CREATE TABLE `devoluciones_venta` (
  `id_devoluciones_venta` int(11) NOT NULL AUTO_INCREMENT,
  `id_tenants` int(11) NOT NULL,
  `id_ventas` int(11) NOT NULL,
  `numero_devolucion` varchar(50) DEFAULT NULL,
  `motivo` varchar(255) DEFAULT NULL,
  `tipo_resolucion` enum('reembolso','cambio_producto','nota_credito') DEFAULT 'reembolso',
  `estado_devolucion` enum('pendiente','aprobada','rechazada','completada') DEFAULT 'pendiente',
  `monto_reembolso` decimal(10,2) DEFAULT NULL,
  `id_usuarios` int(11) DEFAULT NULL,
  `fecha_devolucion` timestamp NULL DEFAULT current_timestamp(),
  PRIMARY KEY (`id_devoluciones_venta`),
  UNIQUE KEY `numero_devolucion` (`numero_devolucion`),
  KEY `id_ventas` (`id_ventas`),
  KEY `id_usuarios` (`id_usuarios`),
  KEY `idx_tenant_id` (`id_tenants`),
  KEY `idx_estado_devolucion` (`estado_devolucion`),
  CONSTRAINT `fk_devoluciones_venta_1` FOREIGN KEY (`id_tenants`) REFERENCES `tenants` (`id_tenants`) ON DELETE CASCADE,
  CONSTRAINT `fk_devoluciones_venta_2` FOREIGN KEY (`id_ventas`) REFERENCES `ventas` (`id_ventas`),
  CONSTRAINT `fk_devoluciones_venta_3` FOREIGN KEY (`id_usuarios`) REFERENCES `usuarios` (`id_usuarios`)
);


DROP TABLE IF EXISTS `formas_pago_venta`;
CREATE TABLE `formas_pago_venta` (
  `id_formas_pago_venta` int(11) NOT NULL AUTO_INCREMENT,
  `id_ventas` int(11) NOT NULL,
  `tipo_pago` enum('efectivo','tarjeta_credito','tarjeta_debito','transferencia','billetera_digital','credito_tienda') DEFAULT 'efectivo',
  `monto` decimal(10,2) DEFAULT NULL,
  `referencia_transaccion` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`id_formas_pago_venta`),
  KEY `idx_venta_id` (`id_ventas`),
  CONSTRAINT `fk_formas_pago_venta_1` FOREIGN KEY (`id_ventas`) REFERENCES `ventas` (`id_ventas`) ON DELETE CASCADE
);


DROP TABLE IF EXISTS `reclamos`;
CREATE TABLE `reclamos` (
  `id_reclamos` int(11) NOT NULL AUTO_INCREMENT,
  `id_tenants` int(11) NOT NULL,
  `id_clientes` int(11) NOT NULL,
  `id_ventas` int(11) DEFAULT NULL,
  `numero_reclamo` varchar(50) DEFAULT NULL,
  `canal_ingreso` enum('presencial','web','telefono','whatsapp') DEFAULT 'presencial',
  `tipo_incidencia` varchar(100) DEFAULT NULL,
  `descripcion` text DEFAULT NULL,
  `estado` tinyint(1) DEFAULT 1,
  `id_usuarios_responsable` int(11) DEFAULT NULL,
  `sla_horas` int(11) DEFAULT NULL,
  `fecha_vencimiento_sla` datetime DEFAULT NULL,
  `solucion_aplicada` text DEFAULT NULL,
  `id_usuarios_usuario_creacion` int(11) DEFAULT NULL,
  `fecha_cierre` timestamp NULL DEFAULT NULL,
  PRIMARY KEY (`id_reclamos`),
  UNIQUE KEY `numero_reclamo` (`numero_reclamo`),
  KEY `id_ventas` (`id_ventas`),
  KEY `id_usuarios_responsable` (`id_usuarios_responsable`),
  KEY `id_usuarios_usuario_creacion` (`id_usuarios_usuario_creacion`),
  KEY `idx_tenant_id` (`id_tenants`),
  KEY `idx_estado` (`estado`),
  KEY `idx_reclamo_cliente` (`id_clientes`),
  CONSTRAINT `fk_reclamos_1` FOREIGN KEY (`id_tenants`) REFERENCES `tenants` (`id_tenants`) ON DELETE CASCADE,
  CONSTRAINT `fk_reclamos_2` FOREIGN KEY (`id_clientes`) REFERENCES `clientes` (`id_clientes`),
  CONSTRAINT `fk_reclamos_3` FOREIGN KEY (`id_ventas`) REFERENCES `ventas` (`id_ventas`),
  CONSTRAINT `fk_reclamos_4` FOREIGN KEY (`id_usuarios_responsable`) REFERENCES `usuarios` (`id_usuarios`),
  CONSTRAINT `fk_reclamos_5` FOREIGN KEY (`id_usuarios_usuario_creacion`) REFERENCES `usuarios` (`id_usuarios`)
);


DROP TABLE IF EXISTS `detalle_devolucion_venta`;
CREATE TABLE `detalle_devolucion_venta` (
  `id_detalle_devolucion_venta` int(11) NOT NULL AUTO_INCREMENT,
  `id_devoluciones_venta` int(11) NOT NULL,
  `id_productos` int(11) NOT NULL,
  `cantidad` int(11) DEFAULT NULL,
  `precio_unitario` decimal(10,2) DEFAULT NULL,
  PRIMARY KEY (`id_detalle_devolucion_venta`),
  KEY `id_productos` (`id_productos`),
  KEY `idx_devolucion_id` (`id_devoluciones_venta`),
  CONSTRAINT `fk_detalle_devolucion_venta_1` FOREIGN KEY (`id_devoluciones_venta`) REFERENCES `devoluciones_venta` (`id_devoluciones_venta`) ON DELETE CASCADE,
  CONSTRAINT `fk_detalle_devolucion_venta_2` FOREIGN KEY (`id_productos`) REFERENCES `productos` (`id_productos`)
);


DROP TABLE IF EXISTS `proveedor_categorias`;
CREATE TABLE `proveedor_categorias` (
  `id_proveedor_categorias` int(11) NOT NULL AUTO_INCREMENT,
  `id_proveedores` int(11) NOT NULL,
  `id_categorias_productos` int(11) NOT NULL,
  PRIMARY KEY (`id_proveedor_categorias`),
  UNIQUE KEY `unique_proveedor_categoria` (`id_proveedores`,`id_categorias_productos`),
  KEY `idx_proveedor_id` (`id_proveedores`),
  KEY `idx_categoria_id` (`id_categorias_productos`),
  CONSTRAINT `fk_proveedor_categorias_1` FOREIGN KEY (`id_proveedores`) REFERENCES `proveedores` (`id_proveedores`) ON DELETE CASCADE,
  CONSTRAINT `fk_proveedor_categorias_2` FOREIGN KEY (`id_categorias_productos`) REFERENCES `categorias_productos` (`id_categorias_productos`)
);

SET FOREIGN_KEY_CHECKS = 1;