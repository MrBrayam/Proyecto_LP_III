-- Base de datos: bellarista
-- Generado para MySQL
-- Fecha: 2026-05-24

SET FOREIGN_KEY_CHECKS = 0;

SET FOREIGN_KEY_CHECKS = 0;

-- Table structure for table `planes_suscripcion`
DROP TABLE IF EXISTS `planes_suscripcion`;
CREATE TABLE `planes_suscripcion` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `nombre` varchar(100) NOT NULL,
  `descripcion` text DEFAULT NULL,
  `precio_mensual` decimal(10,2) DEFAULT NULL,
  `precio_trimestral` decimal(10,2) DEFAULT NULL,
  `precio_anual` decimal(10,2) DEFAULT NULL,
  `periodicidad` enum('mensual','trimestral','anual') DEFAULT NULL,
  `limites_operativos` longtext CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL CHECK (json_valid(`limites_operativos`)),
  `funcionalidades_habilitadas` longtext CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL CHECK (json_valid(`funcionalidades_habilitadas`)),
  `version` int(11) DEFAULT 1,
  `estado` enum('activo','inactivo') DEFAULT 'activo',
  `fecha_creacion` timestamp NULL DEFAULT current_timestamp(),
  `fecha_desactivacion` timestamp NULL DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `idx_estado` (`estado`)
);

-- Table structure for table `tenants`
DROP TABLE IF EXISTS `tenants`;
CREATE TABLE `tenants` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `razon_social` varchar(255) NOT NULL,
  `ruc` varchar(20) NOT NULL,
  `direccion_fiscal` varchar(255) DEFAULT NULL,
  `correo` varchar(100) NOT NULL,
  `telefono` varchar(15) DEFAULT NULL,
  `nombre_comercial` varchar(255) DEFAULT NULL,
  `tipo_negocio` varchar(100) DEFAULT NULL,
  `estado` enum('activo','suspendido','cancelado') DEFAULT 'activo',
  `fecha_registro` timestamp NULL DEFAULT current_timestamp(),
  `fecha_actualizacion` timestamp NULL DEFAULT current_timestamp() ON UPDATE current_timestamp(),
  PRIMARY KEY (`id`),
  UNIQUE KEY `ruc` (`ruc`),
  UNIQUE KEY `correo` (`correo`),
  KEY `idx_ruc` (`ruc`),
  KEY `idx_estado` (`estado`)
);

-- Table structure for table `branding_negocio`
DROP TABLE IF EXISTS `branding_negocio`;
CREATE TABLE `branding_negocio` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `tenant_id` int(11) NOT NULL,
  `logo_url` varchar(255) DEFAULT NULL,
  `color_primario` varchar(7) DEFAULT NULL,
  `color_secundario` varchar(7) DEFAULT NULL,
  `nombre_visible` varchar(255) DEFAULT NULL,
  `redes_sociales` longtext CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL CHECK (json_valid(`redes_sociales`)),
  `fecha_actualizacion` timestamp NULL DEFAULT current_timestamp() ON UPDATE current_timestamp(),
  PRIMARY KEY (`id`),
  UNIQUE KEY `unique_tenant` (`tenant_id`),
  KEY `idx_tenant_id` (`tenant_id`),
  CONSTRAINT `fk_branding_negocio_1` FOREIGN KEY (`tenant_id`) REFERENCES `tenants` (`id`) ON DELETE CASCADE
);

-- Table structure for table `categorias_productos`
DROP TABLE IF EXISTS `categorias_productos`;
CREATE TABLE `categorias_productos` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `tenant_id` int(11) NOT NULL,
  `nombre` varchar(100) NOT NULL,
  `descripcion` text DEFAULT NULL,
  `imagen_url` varchar(255) DEFAULT NULL,
  `orden` int(11) DEFAULT NULL,
  `estado` enum('activo','inactivo') DEFAULT 'activo',
  `fecha_creacion` timestamp NULL DEFAULT current_timestamp(),
  PRIMARY KEY (`id`),
  KEY `idx_tenant_id` (`tenant_id`),
  KEY `idx_estado` (`estado`),
  CONSTRAINT `fk_categorias_productos_1` FOREIGN KEY (`tenant_id`) REFERENCES `tenants` (`id`) ON DELETE CASCADE
);

-- Table structure for table `clientes`
DROP TABLE IF EXISTS `clientes`;
CREATE TABLE `clientes` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `tenant_id` int(11) NOT NULL,
  `nombre_completo` varchar(255) NOT NULL,
  `tipo_documento` enum('DNI','RUC','Pasaporte') DEFAULT 'DNI',
  `numero_documento` varchar(20) DEFAULT NULL,
  `telefono` varchar(15) DEFAULT NULL,
  `correo` varchar(100) DEFAULT NULL,
  `direccion` varchar(255) DEFAULT NULL,
  `distrito` varchar(100) DEFAULT NULL,
  `tipo_cliente` enum('regular','frecuente','vip') DEFAULT 'regular',
  `estado` enum('activo','inactivo') DEFAULT 'activo',
  `fecha_registro` timestamp NULL DEFAULT current_timestamp(),
  PRIMARY KEY (`id`),
  KEY `idx_tenant_id` (`tenant_id`),
  KEY `idx_numero_documento` (`numero_documento`),
  KEY `idx_estado` (`estado`),
  CONSTRAINT `fk_clientes_1` FOREIGN KEY (`tenant_id`) REFERENCES `tenants` (`id`) ON DELETE CASCADE
);

-- Table structure for table `combos_promocionales`
DROP TABLE IF EXISTS `combos_promocionales`;
CREATE TABLE `combos_promocionales` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `tenant_id` int(11) NOT NULL,
  `nombre` varchar(255) NOT NULL,
  `descripcion` text DEFAULT NULL,
  `precio_combo` decimal(10,2) DEFAULT NULL,
  `precio_original` decimal(10,2) DEFAULT NULL,
  `fecha_inicio` date DEFAULT NULL,
  `fecha_fin` date DEFAULT NULL,
  `visible_storefront` tinyint(1) DEFAULT 1,
  `estado` enum('activo','inactivo') DEFAULT 'activo',
  `fecha_creacion` timestamp NULL DEFAULT current_timestamp(),
  PRIMARY KEY (`id`),
  KEY `idx_tenant_id` (`tenant_id`),
  KEY `idx_estado` (`estado`),
  CONSTRAINT `fk_combos_promocionales_1` FOREIGN KEY (`tenant_id`) REFERENCES `tenants` (`id`) ON DELETE CASCADE
);

-- Table structure for table `configuracion_global`
DROP TABLE IF EXISTS `configuracion_global`;
CREATE TABLE `configuracion_global` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `tenant_id` int(11) NOT NULL,
  `clave` varchar(100) NOT NULL,
  `valor` text DEFAULT NULL,
  `tipo_valor` enum('string','numerico','booleano','json') DEFAULT 'string',
  `descripcion` varchar(255) DEFAULT NULL,
  `fecha_actualizacion` timestamp NULL DEFAULT current_timestamp() ON UPDATE current_timestamp(),
  PRIMARY KEY (`id`),
  UNIQUE KEY `unique_tenant_clave` (`tenant_id`,`clave`),
  KEY `idx_tenant_id` (`tenant_id`),
  CONSTRAINT `fk_configuracion_global_1` FOREIGN KEY (`tenant_id`) REFERENCES `tenants` (`id`) ON DELETE CASCADE
);

-- Table structure for table `configuracion_tienda_online`
DROP TABLE IF EXISTS `configuracion_tienda_online`;
CREATE TABLE `configuracion_tienda_online` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `tenant_id` int(11) NOT NULL,
  `titulo_tienda` varchar(255) DEFAULT NULL,
  `descripcion_tienda` text DEFAULT NULL,
  `politica_compra` text DEFAULT NULL,
  `condiciones_compra` text DEFAULT NULL,
  `informacion_contacto` longtext CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL CHECK (json_valid(`informacion_contacto`)),
  `horarios_compra_activos` tinyint(1) DEFAULT 1,
  `estado_tienda` enum('activa','mantenimiento','desactivada') DEFAULT 'activa',
  `mensaje_estado` varchar(500) DEFAULT NULL,
  `fecha_actualizacion` timestamp NULL DEFAULT current_timestamp() ON UPDATE current_timestamp(),
  PRIMARY KEY (`id`),
  UNIQUE KEY `unique_tenant` (`tenant_id`),
  KEY `idx_tenant_id` (`tenant_id`),
  CONSTRAINT `fk_configuracion_tienda_online_1` FOREIGN KEY (`tenant_id`) REFERENCES `tenants` (`id`) ON DELETE CASCADE
);

-- Table structure for table `marcas`
DROP TABLE IF EXISTS `marcas`;
CREATE TABLE `marcas` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `tenant_id` int(11) NOT NULL,
  `nombre` varchar(100) NOT NULL,
  `pais_origen` varchar(100) DEFAULT NULL,
  `logo_url` varchar(255) DEFAULT NULL,
  `descripcion` text DEFAULT NULL,
  `estado` enum('activo','inactivo') DEFAULT 'activo',
  `fecha_creacion` timestamp NULL DEFAULT current_timestamp(),
  PRIMARY KEY (`id`),
  KEY `idx_tenant_id` (`tenant_id`),
  KEY `idx_estado` (`estado`),
  CONSTRAINT `fk_marcas_1` FOREIGN KEY (`tenant_id`) REFERENCES `tenants` (`id`) ON DELETE CASCADE
);

-- Table structure for table `metodos_pago`
DROP TABLE IF EXISTS `metodos_pago`;
CREATE TABLE `metodos_pago` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `tenant_id` int(11) NOT NULL,
  `nombre` varchar(100) NOT NULL,
  `tipo` enum('efectivo','tarjeta_credito','tarjeta_debito','transferencia','billetera_digital','otro') DEFAULT NULL,
  `descripcion` text DEFAULT NULL,
  `estado` enum('activo','inactivo') DEFAULT 'activo',
  `fecha_creacion` timestamp NULL DEFAULT current_timestamp(),
  PRIMARY KEY (`id`),
  KEY `idx_tenant_id` (`tenant_id`),
  CONSTRAINT `fk_metodos_pago_1` FOREIGN KEY (`tenant_id`) REFERENCES `tenants` (`id`) ON DELETE CASCADE
);

-- Table structure for table `proveedores`
DROP TABLE IF EXISTS `proveedores`;
CREATE TABLE `proveedores` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `tenant_id` int(11) NOT NULL,
  `razon_social` varchar(255) NOT NULL,
  `ruc` varchar(20) DEFAULT NULL,
  `direccion` varchar(255) DEFAULT NULL,
  `telefono` varchar(15) DEFAULT NULL,
  `correo` varchar(100) DEFAULT NULL,
  `persona_contacto` varchar(255) DEFAULT NULL,
  `terminos_comerciales` text DEFAULT NULL,
  `categorias_productos` varchar(255) DEFAULT NULL,
  `estado` enum('activo','inactivo') DEFAULT 'activo',
  `fecha_creacion` timestamp NULL DEFAULT current_timestamp(),
  PRIMARY KEY (`id`),
  KEY `idx_tenant_id` (`tenant_id`),
  KEY `idx_estado` (`estado`),
  CONSTRAINT `fk_proveedores_1` FOREIGN KEY (`tenant_id`) REFERENCES `tenants` (`id`) ON DELETE CASCADE
);

-- Table structure for table `roles_personalizados`
DROP TABLE IF EXISTS `roles_personalizados`;
CREATE TABLE `roles_personalizados` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `tenant_id` int(11) NOT NULL,
  `nombre` varchar(100) NOT NULL,
  `descripcion` text DEFAULT NULL,
  `estado` enum('activo','inactivo') DEFAULT 'activo',
  `fecha_creacion` timestamp NULL DEFAULT current_timestamp(),
  PRIMARY KEY (`id`),
  KEY `idx_tenant_id` (`tenant_id`),
  CONSTRAINT `fk_roles_personalizados_1` FOREIGN KEY (`tenant_id`) REFERENCES `tenants` (`id`) ON DELETE CASCADE
);

-- Table structure for table `sedes`
DROP TABLE IF EXISTS `sedes`;
CREATE TABLE `sedes` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `tenant_id` int(11) NOT NULL,
  `nombre` varchar(255) NOT NULL,
  `direccion` varchar(255) DEFAULT NULL,
  `distrito` varchar(100) DEFAULT NULL,
  `telefono` varchar(15) DEFAULT NULL,
  `horario_apertura` time DEFAULT NULL,
  `horario_cierre` time DEFAULT NULL,
  `responsable` varchar(255) DEFAULT NULL,
  `estado` enum('activa','suspendida','desactivada') DEFAULT 'activa',
  `fecha_creacion` timestamp NULL DEFAULT current_timestamp(),
  `fecha_actualizacion` timestamp NULL DEFAULT current_timestamp() ON UPDATE current_timestamp(),
  PRIMARY KEY (`id`),
  KEY `idx_tenant_id` (`tenant_id`),
  KEY `idx_estado` (`estado`),
  CONSTRAINT `fk_sedes_1` FOREIGN KEY (`tenant_id`) REFERENCES `tenants` (`id`) ON DELETE CASCADE
);

-- Table structure for table `series_comprobantes`
DROP TABLE IF EXISTS `series_comprobantes`;
CREATE TABLE `series_comprobantes` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `tenant_id` int(11) NOT NULL,
  `tipo_comprobante` enum('boleta','factura','nota_credito','nota_debito') DEFAULT NULL,
  `punto_emision` int(11) DEFAULT NULL,
  `numero_serie` varchar(10) DEFAULT NULL,
  `numero_correlativo` int(11) DEFAULT 0,
  `numero_proximo` int(11) DEFAULT NULL,
  `fecha_autorizacion` date DEFAULT NULL,
  `fecha_vencimiento` date DEFAULT NULL,
  `estado` enum('activa','inactiva') DEFAULT 'activa',
  PRIMARY KEY (`id`),
  UNIQUE KEY `unique_serie` (`tenant_id`,`tipo_comprobante`,`punto_emision`,`numero_serie`),
  KEY `idx_tenant_id` (`tenant_id`),
  CONSTRAINT `fk_series_comprobantes_1` FOREIGN KEY (`tenant_id`) REFERENCES `tenants` (`id`) ON DELETE CASCADE
);

-- Table structure for table `servicios_belleza`
DROP TABLE IF EXISTS `servicios_belleza`;
CREATE TABLE `servicios_belleza` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `tenant_id` int(11) NOT NULL,
  `nombre` varchar(255) NOT NULL,
  `descripcion` text DEFAULT NULL,
  `categoria` varchar(100) DEFAULT NULL,
  `duracion_minima` int(11) DEFAULT NULL,
  `duracion_maxima` int(11) DEFAULT NULL,
  `precio_base` decimal(10,2) DEFAULT NULL,
  `precio_editable` tinyint(1) DEFAULT 0,
  `estado` enum('activo','inactivo') DEFAULT 'activo',
  `fecha_creacion` timestamp NULL DEFAULT current_timestamp(),
  PRIMARY KEY (`id`),
  KEY `idx_tenant_id` (`tenant_id`),
  KEY `idx_estado` (`estado`),
  CONSTRAINT `fk_servicios_belleza_1` FOREIGN KEY (`tenant_id`) REFERENCES `tenants` (`id`) ON DELETE CASCADE
);

-- Table structure for table `suscripciones`
DROP TABLE IF EXISTS `suscripciones`;
CREATE TABLE `suscripciones` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `tenant_id` int(11) NOT NULL,
  `plan_id` int(11) NOT NULL,
  `fecha_inicio` date NOT NULL,
  `fecha_proximo_pago` date NOT NULL,
  `estado` enum('activa','suspendida','cancelada') DEFAULT 'activa',
  `precio_contratado` decimal(10,2) DEFAULT NULL,
  `fecha_creacion` timestamp NULL DEFAULT current_timestamp(),
  `fecha_actualizacion` timestamp NULL DEFAULT current_timestamp() ON UPDATE current_timestamp(),
  PRIMARY KEY (`id`),
  KEY `plan_id` (`plan_id`),
  KEY `idx_tenant_id` (`tenant_id`),
  KEY `idx_estado` (`estado`),
  KEY `idx_fecha_proximo_pago` (`fecha_proximo_pago`),
  CONSTRAINT `fk_suscripciones_1` FOREIGN KEY (`tenant_id`) REFERENCES `tenants` (`id`) ON DELETE CASCADE,
  CONSTRAINT `fk_suscripciones_2` FOREIGN KEY (`plan_id`) REFERENCES `planes_suscripcion` (`id`)
);

-- Table structure for table `usuarios`
DROP TABLE IF EXISTS `usuarios`;
CREATE TABLE `usuarios` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `tenant_id` int(11) NOT NULL,
  `nombre_completo` varchar(255) NOT NULL,
  `correo` varchar(100) NOT NULL,
  `numero_documento` varchar(20) DEFAULT NULL,
  `contrasena_hash` varchar(255) DEFAULT NULL,
  `tipo_usuario` enum('superadmin','admin','cajero','recepcionista','especialista','estilista','gerente','otro') DEFAULT 'otro',
  `estado` enum('activo','inactivo') DEFAULT 'activo',
  `fecha_ultimo_acceso` timestamp NULL DEFAULT NULL,
  `fecha_creacion` timestamp NULL DEFAULT current_timestamp(),
  `fecha_actualizacion` timestamp NULL DEFAULT current_timestamp() ON UPDATE current_timestamp(),
  PRIMARY KEY (`id`),
  UNIQUE KEY `unique_correo_tenant` (`tenant_id`,`correo`),
  KEY `idx_tenant_id` (`tenant_id`),
  KEY `idx_correo` (`correo`),
  KEY `idx_estado` (`estado`),
  KEY `idx_usuario_tenant` (`tenant_id`),
  CONSTRAINT `fk_usuarios_1` FOREIGN KEY (`tenant_id`) REFERENCES `tenants` (`id`) ON DELETE CASCADE
);

-- Table structure for table `promociones`
DROP TABLE IF EXISTS `promociones`;
CREATE TABLE `promociones` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `tenant_id` int(11) NOT NULL,
  `nombre` varchar(255) NOT NULL,
  `tipo_descuento` enum('fijo','porcentual','2x1','3x2','escalonado','regalo') DEFAULT NULL,
  `valor_descuento` decimal(10,2) DEFAULT NULL,
  `compra_minima` decimal(10,2) DEFAULT NULL,
  `fecha_inicio` date DEFAULT NULL,
  `fecha_fin` date DEFAULT NULL,
  `categoria_id` int(11) DEFAULT NULL,
  `estado` enum('activa','inactiva') DEFAULT 'activa',
  `fecha_creacion` timestamp NULL DEFAULT current_timestamp(),
  PRIMARY KEY (`id`),
  KEY `categoria_id` (`categoria_id`),
  KEY `idx_tenant_id` (`tenant_id`),
  KEY `idx_estado` (`estado`),
  CONSTRAINT `fk_promociones_1` FOREIGN KEY (`tenant_id`) REFERENCES `tenants` (`id`) ON DELETE CASCADE,
  CONSTRAINT `fk_promociones_2` FOREIGN KEY (`categoria_id`) REFERENCES `categorias_productos` (`id`)
);

-- Table structure for table `productos`
DROP TABLE IF EXISTS `productos`;
CREATE TABLE `productos` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `tenant_id` int(11) NOT NULL,
  `categoria_id` int(11) NOT NULL,
  `marca_id` int(11) DEFAULT NULL,
  `codigo_interno` varchar(50) DEFAULT NULL,
  `codigo_barras` varchar(100) DEFAULT NULL,
  `nombre` varchar(255) NOT NULL,
  `descripcion` text DEFAULT NULL,
  `tipo_producto` varchar(100) DEFAULT NULL,
  `presentacion` varchar(100) DEFAULT NULL,
  `contenido_neto` varchar(50) DEFAULT NULL,
  `precio_costo` decimal(10,2) DEFAULT NULL,
  `precio_venta` decimal(10,2) DEFAULT NULL,
  `margen_ganancia` decimal(5,2) DEFAULT NULL,
  `stock_minimo` int(11) DEFAULT 10,
  `stock_critico` int(11) DEFAULT 5,
  `visible_storefront` tinyint(1) DEFAULT 1,
  `etiqueta_especial` varchar(100) DEFAULT NULL,
  `estado` enum('activo','inactivo') DEFAULT 'activo',
  `fecha_creacion` timestamp NULL DEFAULT current_timestamp(),
  `fecha_actualizacion` timestamp NULL DEFAULT current_timestamp() ON UPDATE current_timestamp(),
  PRIMARY KEY (`id`),
  UNIQUE KEY `codigo_barras` (`codigo_barras`),
  KEY `idx_tenant_id` (`tenant_id`),
  KEY `idx_categoria_id` (`categoria_id`),
  KEY `idx_marca_id` (`marca_id`),
  KEY `idx_codigo_barras` (`codigo_barras`),
  KEY `idx_estado` (`estado`),
  KEY `idx_producto_tenant` (`tenant_id`),
  CONSTRAINT `fk_productos_1` FOREIGN KEY (`tenant_id`) REFERENCES `tenants` (`id`) ON DELETE CASCADE,
  CONSTRAINT `fk_productos_2` FOREIGN KEY (`categoria_id`) REFERENCES `categorias_productos` (`id`),
  CONSTRAINT `fk_productos_3` FOREIGN KEY (`marca_id`) REFERENCES `marcas` (`id`)
);

-- Table structure for table `cuentas_por_pagar`
DROP TABLE IF EXISTS `cuentas_por_pagar`;
CREATE TABLE `cuentas_por_pagar` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `tenant_id` int(11) NOT NULL,
  `proveedor_id` int(11) NOT NULL,
  `documento_numero` varchar(50) DEFAULT NULL,
  `tipo_documento` enum('factura','boleta','orden_compra') DEFAULT 'factura',
  `monto` decimal(10,2) DEFAULT NULL,
  `fecha_documento` date DEFAULT NULL,
  `fecha_vencimiento` date DEFAULT NULL,
  `estado_pago` enum('pendiente','parcial','pagada') DEFAULT 'pendiente',
  `fecha_creacion` timestamp NULL DEFAULT current_timestamp(),
  PRIMARY KEY (`id`),
  KEY `proveedor_id` (`proveedor_id`),
  KEY `idx_tenant_id` (`tenant_id`),
  KEY `idx_estado_pago` (`estado_pago`),
  KEY `idx_fecha_vencimiento` (`fecha_vencimiento`),
  CONSTRAINT `fk_cuentas_por_pagar_1` FOREIGN KEY (`tenant_id`) REFERENCES `tenants` (`id`) ON DELETE CASCADE,
  CONSTRAINT `fk_cuentas_por_pagar_2` FOREIGN KEY (`proveedor_id`) REFERENCES `proveedores` (`id`)
);

-- Table structure for table `devoluciones_proveedor`
DROP TABLE IF EXISTS `devoluciones_proveedor`;
CREATE TABLE `devoluciones_proveedor` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `tenant_id` int(11) NOT NULL,
  `proveedor_id` int(11) NOT NULL,
  `numero_devolucion` varchar(50) DEFAULT NULL,
  `fecha_devolucion` date DEFAULT NULL,
  `motivo` varchar(255) DEFAULT NULL,
  `observaciones` text DEFAULT NULL,
  `estado` enum('pendiente','procesada','rechazada') DEFAULT 'pendiente',
  `fecha_creacion` timestamp NULL DEFAULT current_timestamp(),
  PRIMARY KEY (`id`),
  UNIQUE KEY `numero_devolucion` (`numero_devolucion`),
  KEY `proveedor_id` (`proveedor_id`),
  KEY `idx_tenant_id` (`tenant_id`),
  KEY `idx_estado` (`estado`),
  CONSTRAINT `fk_devoluciones_proveedor_1` FOREIGN KEY (`tenant_id`) REFERENCES `tenants` (`id`) ON DELETE CASCADE,
  CONSTRAINT `fk_devoluciones_proveedor_2` FOREIGN KEY (`proveedor_id`) REFERENCES `proveedores` (`id`)
);

-- Table structure for table `ordenes_compra`
DROP TABLE IF EXISTS `ordenes_compra`;
CREATE TABLE `ordenes_compra` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `tenant_id` int(11) NOT NULL,
  `proveedor_id` int(11) NOT NULL,
  `numero_orden` varchar(50) DEFAULT NULL,
  `fecha_orden` date DEFAULT NULL,
  `fecha_entrega_estimada` date DEFAULT NULL,
  `estado` enum('borrador','pendiente','aprobada','rechazada','parcial','completa','cancelada') DEFAULT 'borrador',
  `monto_total` decimal(10,2) DEFAULT NULL,
  `condiciones_entrega` text DEFAULT NULL,
  `forma_pago` varchar(100) DEFAULT NULL,
  `notas` text DEFAULT NULL,
  `fecha_creacion` timestamp NULL DEFAULT current_timestamp(),
  `fecha_actualizacion` timestamp NULL DEFAULT current_timestamp() ON UPDATE current_timestamp(),
  PRIMARY KEY (`id`),
  UNIQUE KEY `numero_orden` (`numero_orden`),
  KEY `idx_tenant_id` (`tenant_id`),
  KEY `idx_proveedor_id` (`proveedor_id`),
  KEY `idx_estado` (`estado`),
  CONSTRAINT `fk_ordenes_compra_1` FOREIGN KEY (`tenant_id`) REFERENCES `tenants` (`id`) ON DELETE CASCADE,
  CONSTRAINT `fk_ordenes_compra_2` FOREIGN KEY (`proveedor_id`) REFERENCES `proveedores` (`id`)
);

-- Table structure for table `permisos_rol`
DROP TABLE IF EXISTS `permisos_rol`;
CREATE TABLE `permisos_rol` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `rol_id` int(11) NOT NULL,
  `modulo` varchar(100) DEFAULT NULL,
  `accion` enum('crear','editar','eliminar','visualizar') NOT NULL,
  `recurso` varchar(100) DEFAULT NULL,
  `estado` enum('activo','inactivo') DEFAULT 'activo',
  PRIMARY KEY (`id`),
  KEY `idx_rol_id` (`rol_id`),
  CONSTRAINT `fk_permisos_rol_1` FOREIGN KEY (`rol_id`) REFERENCES `roles_personalizados` (`id`) ON DELETE CASCADE
);

-- Table structure for table `almacenes`
DROP TABLE IF EXISTS `almacenes`;
CREATE TABLE `almacenes` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `sede_id` int(11) NOT NULL,
  `nombre` varchar(100) NOT NULL,
  `ubicacion` varchar(255) DEFAULT NULL,
  `capacidad` int(11) DEFAULT NULL,
  `estado` enum('activo','inactivo') DEFAULT 'activo',
  `fecha_creacion` timestamp NULL DEFAULT current_timestamp(),
  PRIMARY KEY (`id`),
  KEY `idx_sede_id` (`sede_id`),
  CONSTRAINT `fk_almacenes_1` FOREIGN KEY (`sede_id`) REFERENCES `sedes` (`id`) ON DELETE CASCADE
);

-- Table structure for table `gastos_recurrentes`
DROP TABLE IF EXISTS `gastos_recurrentes`;
CREATE TABLE `gastos_recurrentes` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `tenant_id` int(11) NOT NULL,
  `sede_id` int(11) DEFAULT NULL,
  `concepto` varchar(255) DEFAULT NULL,
  `monto` decimal(10,2) DEFAULT NULL,
  `frecuencia` enum('diaria','semanal','quincenal','mensual','trimestral','anual') DEFAULT 'mensual',
  `fecha_proximo_registro` date DEFAULT NULL,
  `estado` enum('activo','pausado','cancelado') DEFAULT 'activo',
  `fecha_creacion` timestamp NULL DEFAULT current_timestamp(),
  PRIMARY KEY (`id`),
  KEY `sede_id` (`sede_id`),
  KEY `idx_tenant_id` (`tenant_id`),
  CONSTRAINT `fk_gastos_recurrentes_1` FOREIGN KEY (`tenant_id`) REFERENCES `tenants` (`id`) ON DELETE CASCADE,
  CONSTRAINT `fk_gastos_recurrentes_2` FOREIGN KEY (`sede_id`) REFERENCES `sedes` (`id`)
);

-- Table structure for table `horarios_operacion`
DROP TABLE IF EXISTS `horarios_operacion`;
CREATE TABLE `horarios_operacion` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `sede_id` int(11) NOT NULL,
  `dia_semana` enum('lunes','martes','miercoles','jueves','viernes','sabado','domingo') DEFAULT NULL,
  `hora_apertura` time DEFAULT NULL,
  `hora_cierre` time DEFAULT NULL,
  `estado` enum('activo','inactivo') DEFAULT 'activo',
  PRIMARY KEY (`id`),
  UNIQUE KEY `unique_sede_dia` (`sede_id`,`dia_semana`),
  KEY `idx_sede_id` (`sede_id`),
  CONSTRAINT `fk_horarios_operacion_1` FOREIGN KEY (`sede_id`) REFERENCES `sedes` (`id`) ON DELETE CASCADE
);

-- Table structure for table `zonas_delivery`
DROP TABLE IF EXISTS `zonas_delivery`;
CREATE TABLE `zonas_delivery` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `sede_id` int(11) NOT NULL,
  `nombre` varchar(100) DEFAULT NULL,
  `distritos` varchar(500) DEFAULT NULL,
  `costo_fijo` decimal(10,2) DEFAULT NULL,
  `monto_minimo_compra` decimal(10,2) DEFAULT NULL,
  `tiempo_estimado_minutos` int(11) DEFAULT NULL,
  `estado` enum('activo','inactivo') DEFAULT 'activo',
  PRIMARY KEY (`id`),
  KEY `idx_sede_id` (`sede_id`),
  CONSTRAINT `fk_zonas_delivery_1` FOREIGN KEY (`sede_id`) REFERENCES `sedes` (`id`)
);

-- Table structure for table `facturas_suscripcion`
DROP TABLE IF EXISTS `facturas_suscripcion`;
CREATE TABLE `facturas_suscripcion` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `suscripcion_id` int(11) NOT NULL,
  `numero_factura` varchar(50) DEFAULT NULL,
  `monto` decimal(10,2) DEFAULT NULL,
  `fecha_emision` date DEFAULT NULL,
  `fecha_vencimiento` date DEFAULT NULL,
  `estado_pago` enum('pendiente','pagada','vencida','cancelada') DEFAULT 'pendiente',
  `metodo_pago` varchar(50) DEFAULT NULL,
  `fecha_pago` timestamp NULL DEFAULT NULL,
  `fecha_creacion` timestamp NULL DEFAULT current_timestamp(),
  PRIMARY KEY (`id`),
  UNIQUE KEY `numero_factura` (`numero_factura`),
  KEY `suscripcion_id` (`suscripcion_id`),
  KEY `idx_estado_pago` (`estado_pago`),
  KEY `idx_fecha_vencimiento` (`fecha_vencimiento`),
  CONSTRAINT `fk_facturas_suscripcion_1` FOREIGN KEY (`suscripcion_id`) REFERENCES `suscripciones` (`id`) ON DELETE CASCADE
);

-- Table structure for table `auditoria`
DROP TABLE IF EXISTS `auditoria`;
CREATE TABLE `auditoria` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `tenant_id` int(11) NOT NULL,
  `usuario_id` int(11) DEFAULT NULL,
  `accion` varchar(255) DEFAULT NULL,
  `tabla_afectada` varchar(100) DEFAULT NULL,
  `registro_id` int(11) DEFAULT NULL,
  `datos_anteriores` longtext CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL CHECK (json_valid(`datos_anteriores`)),
  `datos_nuevos` longtext CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL CHECK (json_valid(`datos_nuevos`)),
  `ip_address` varchar(45) DEFAULT NULL,
  `fecha_hora` timestamp NULL DEFAULT current_timestamp(),
  PRIMARY KEY (`id`),
  KEY `idx_tenant_id` (`tenant_id`),
  KEY `idx_usuario_id` (`usuario_id`),
  KEY `idx_fecha_hora` (`fecha_hora`),
  KEY `idx_accion` (`accion`),
  CONSTRAINT `fk_auditoria_1` FOREIGN KEY (`tenant_id`) REFERENCES `tenants` (`id`) ON DELETE CASCADE,
  CONSTRAINT `fk_auditoria_2` FOREIGN KEY (`usuario_id`) REFERENCES `usuarios` (`id`) ON DELETE SET NULL
);

-- Table structure for table `citas`
DROP TABLE IF EXISTS `citas`;
CREATE TABLE `citas` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `tenant_id` int(11) NOT NULL,
  `sede_id` int(11) NOT NULL,
  `cliente_id` int(11) NOT NULL,
  `especialista_id` int(11) DEFAULT NULL,
  `servicio_id` int(11) NOT NULL,
  `fecha_cita` date DEFAULT NULL,
  `hora_inicio` time DEFAULT NULL,
  `hora_fin` time DEFAULT NULL,
  `duracion_minutos` int(11) DEFAULT NULL,
  `estado` enum('programada','confirmada','en_proceso','completada','cancelada','no_asistio') DEFAULT 'programada',
  `observaciones` text DEFAULT NULL,
  `usuario_creacion_id` int(11) DEFAULT NULL,
  `fecha_creacion` timestamp NULL DEFAULT current_timestamp(),
  `fecha_actualizacion` timestamp NULL DEFAULT current_timestamp() ON UPDATE current_timestamp(),
  PRIMARY KEY (`id`),
  KEY `sede_id` (`sede_id`),
  KEY `servicio_id` (`servicio_id`),
  KEY `usuario_creacion_id` (`usuario_creacion_id`),
  KEY `idx_tenant_id` (`tenant_id`),
  KEY `idx_fecha_cita` (`fecha_cita`),
  KEY `idx_estado` (`estado`),
  KEY `idx_cita_cliente` (`cliente_id`),
  KEY `idx_cita_especialista` (`especialista_id`),
  CONSTRAINT `fk_citas_1` FOREIGN KEY (`tenant_id`) REFERENCES `tenants` (`id`) ON DELETE CASCADE,
  CONSTRAINT `fk_citas_2` FOREIGN KEY (`sede_id`) REFERENCES `sedes` (`id`),
  CONSTRAINT `fk_citas_3` FOREIGN KEY (`cliente_id`) REFERENCES `clientes` (`id`),
  CONSTRAINT `fk_citas_4` FOREIGN KEY (`especialista_id`) REFERENCES `usuarios` (`id`),
  CONSTRAINT `fk_citas_5` FOREIGN KEY (`servicio_id`) REFERENCES `servicios_belleza` (`id`),
  CONSTRAINT `fk_citas_6` FOREIGN KEY (`usuario_creacion_id`) REFERENCES `usuarios` (`id`)
);

-- Table structure for table `gastos_operativos`
DROP TABLE IF EXISTS `gastos_operativos`;
CREATE TABLE `gastos_operativos` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `tenant_id` int(11) NOT NULL,
  `sede_id` int(11) DEFAULT NULL,
  `concepto` varchar(255) DEFAULT NULL,
  `categoria` enum('insumos','cosmeticos','publicidad','servicios_basicos','mantenimiento','transporte','personal','otro') DEFAULT 'otro',
  `monto` decimal(10,2) DEFAULT NULL,
  `descripcion` text DEFAULT NULL,
  `proveedor_id` int(11) DEFAULT NULL,
  `comprobante_numero` varchar(50) DEFAULT NULL,
  `archivo_evidencia` varchar(255) DEFAULT NULL,
  `estado` enum('registrado','aprobado','rechazado') DEFAULT 'registrado',
  `usuario_creacion_id` int(11) DEFAULT NULL,
  `usuario_aprobacion_id` int(11) DEFAULT NULL,
  `fecha_gasto` date DEFAULT NULL,
  `fecha_creacion` timestamp NULL DEFAULT current_timestamp(),
  PRIMARY KEY (`id`),
  KEY `sede_id` (`sede_id`),
  KEY `proveedor_id` (`proveedor_id`),
  KEY `usuario_creacion_id` (`usuario_creacion_id`),
  KEY `usuario_aprobacion_id` (`usuario_aprobacion_id`),
  KEY `idx_tenant_id` (`tenant_id`),
  KEY `idx_estado` (`estado`),
  KEY `idx_fecha_gasto` (`fecha_gasto`),
  CONSTRAINT `fk_gastos_operativos_1` FOREIGN KEY (`tenant_id`) REFERENCES `tenants` (`id`) ON DELETE CASCADE,
  CONSTRAINT `fk_gastos_operativos_2` FOREIGN KEY (`sede_id`) REFERENCES `sedes` (`id`),
  CONSTRAINT `fk_gastos_operativos_3` FOREIGN KEY (`proveedor_id`) REFERENCES `proveedores` (`id`),
  CONSTRAINT `fk_gastos_operativos_4` FOREIGN KEY (`usuario_creacion_id`) REFERENCES `usuarios` (`id`),
  CONSTRAINT `fk_gastos_operativos_5` FOREIGN KEY (`usuario_aprobacion_id`) REFERENCES `usuarios` (`id`)
);

-- Table structure for table `notificaciones`
DROP TABLE IF EXISTS `notificaciones`;
CREATE TABLE `notificaciones` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `tenant_id` int(11) NOT NULL,
  `usuario_id` int(11) DEFAULT NULL,
  `tipo` enum('stock_bajo','producto_vencer','cita_pendiente','cancelacion','acceso_sospechoso','pago_vencido','otro') DEFAULT 'otro',
  `titulo` varchar(255) DEFAULT NULL,
  `mensaje` text DEFAULT NULL,
  `canal_envio` enum('correo','sms','sistema','whatsapp') DEFAULT 'sistema',
  `estado_lectura` enum('no_leido','leido') DEFAULT 'no_leido',
  `fecha_creacion` timestamp NULL DEFAULT current_timestamp(),
  `fecha_lectura` timestamp NULL DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `usuario_id` (`usuario_id`),
  KEY `idx_tenant_id` (`tenant_id`),
  KEY `idx_estado_lectura` (`estado_lectura`),
  CONSTRAINT `fk_notificaciones_1` FOREIGN KEY (`tenant_id`) REFERENCES `tenants` (`id`) ON DELETE CASCADE,
  CONSTRAINT `fk_notificaciones_2` FOREIGN KEY (`usuario_id`) REFERENCES `usuarios` (`id`)
);

-- Table structure for table `pedidos`
DROP TABLE IF EXISTS `pedidos`;
CREATE TABLE `pedidos` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `tenant_id` int(11) NOT NULL,
  `cliente_id` int(11) NOT NULL,
  `numero_pedido` varchar(50) DEFAULT NULL,
  `modalidad` enum('delivery','pickup','presencial') DEFAULT 'delivery',
  `estado` enum('pendiente_pago','confirmado','preparacion','listo','en_camino','entregado','cancelado') DEFAULT 'pendiente_pago',
  `subtotal` decimal(10,2) DEFAULT NULL,
  `costo_envio` decimal(10,2) DEFAULT 0.00,
  `impuesto` decimal(10,2) DEFAULT NULL,
  `total` decimal(10,2) DEFAULT NULL,
  `direccion_entrega` varchar(255) DEFAULT NULL,
  `zona_delivery_id` int(11) DEFAULT NULL,
  `repartidor_id` int(11) DEFAULT NULL,
  `fecha_pedido` timestamp NULL DEFAULT current_timestamp(),
  `fecha_entrega_estimada` datetime DEFAULT NULL,
  `fecha_entrega_real` datetime DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `numero_pedido` (`numero_pedido`),
  KEY `repartidor_id` (`repartidor_id`),
  KEY `idx_tenant_id` (`tenant_id`),
  KEY `idx_estado` (`estado`),
  KEY `idx_fecha_pedido` (`fecha_pedido`),
  KEY `idx_pedido_cliente` (`cliente_id`),
  CONSTRAINT `fk_pedidos_1` FOREIGN KEY (`tenant_id`) REFERENCES `tenants` (`id`) ON DELETE CASCADE,
  CONSTRAINT `fk_pedidos_2` FOREIGN KEY (`cliente_id`) REFERENCES `clientes` (`id`),
  CONSTRAINT `fk_pedidos_3` FOREIGN KEY (`repartidor_id`) REFERENCES `usuarios` (`id`)
);

-- Table structure for table `preferencias_usuario`
DROP TABLE IF EXISTS `preferencias_usuario`;
CREATE TABLE `preferencias_usuario` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `usuario_id` int(11) NOT NULL,
  `idioma` varchar(10) DEFAULT 'es',
  `formato_fecha` varchar(20) DEFAULT 'DD/MM/YYYY',
  `zona_horaria` varchar(50) DEFAULT NULL,
  `notificaciones_activas` tinyint(1) DEFAULT 1,
  `vista_inicial` varchar(100) DEFAULT NULL,
  `tema_interfaz` enum('claro','oscuro') DEFAULT 'claro',
  PRIMARY KEY (`id`),
  UNIQUE KEY `unique_usuario` (`usuario_id`),
  KEY `idx_usuario_id` (`usuario_id`),
  CONSTRAINT `fk_preferencias_usuario_1` FOREIGN KEY (`usuario_id`) REFERENCES `usuarios` (`id`) ON DELETE CASCADE
);

-- Table structure for table `repartidores`
DROP TABLE IF EXISTS `repartidores`;
CREATE TABLE `repartidores` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `usuario_id` int(11) NOT NULL,
  `nombres` varchar(255) DEFAULT NULL,
  `apellidos` varchar(255) DEFAULT NULL,
  `numero_documento` varchar(20) DEFAULT NULL,
  `tipo_vehiculo` varchar(100) DEFAULT NULL,
  `placa_vehiculo` varchar(20) DEFAULT NULL,
  `numero_licencia` varchar(50) DEFAULT NULL,
  `estado` enum('activo','inactivo','en_ruta') DEFAULT 'activo',
  `fecha_registro` timestamp NULL DEFAULT current_timestamp(),
  PRIMARY KEY (`id`),
  KEY `idx_usuario_id` (`usuario_id`),
  CONSTRAINT `fk_repartidores_1` FOREIGN KEY (`usuario_id`) REFERENCES `usuarios` (`id`)
);

-- Table structure for table `sesiones_caja`
DROP TABLE IF EXISTS `sesiones_caja`;
CREATE TABLE `sesiones_caja` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `sede_id` int(11) NOT NULL,
  `usuario_id` int(11) NOT NULL,
  `fecha_apertura` timestamp NULL DEFAULT current_timestamp(),
  `fecha_cierre` timestamp NULL DEFAULT NULL,
  `monto_inicial` decimal(10,2) DEFAULT NULL,
  `monto_final` decimal(10,2) DEFAULT NULL,
  `diferencia` decimal(10,2) DEFAULT NULL,
  `estado` enum('abierta','cerrada') DEFAULT 'abierta',
  `observaciones` text DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `idx_sede_id` (`sede_id`),
  KEY `idx_usuario_id` (`usuario_id`),
  KEY `idx_estado` (`estado`),
  CONSTRAINT `fk_sesiones_caja_1` FOREIGN KEY (`sede_id`) REFERENCES `sedes` (`id`),
  CONSTRAINT `fk_sesiones_caja_2` FOREIGN KEY (`usuario_id`) REFERENCES `usuarios` (`id`)
);

-- Table structure for table `usuario_sedes`
DROP TABLE IF EXISTS `usuario_sedes`;
CREATE TABLE `usuario_sedes` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `usuario_id` int(11) NOT NULL,
  `sede_id` int(11) NOT NULL,
  `fecha_asignacion` timestamp NULL DEFAULT current_timestamp(),
  PRIMARY KEY (`id`),
  UNIQUE KEY `unique_usuario_sede` (`usuario_id`,`sede_id`),
  KEY `idx_usuario_id` (`usuario_id`),
  KEY `idx_sede_id` (`sede_id`),
  CONSTRAINT `fk_usuario_sedes_1` FOREIGN KEY (`usuario_id`) REFERENCES `usuarios` (`id`) ON DELETE CASCADE,
  CONSTRAINT `fk_usuario_sedes_2` FOREIGN KEY (`sede_id`) REFERENCES `sedes` (`id`) ON DELETE CASCADE
);

-- Table structure for table `composicion_combo`
DROP TABLE IF EXISTS `composicion_combo`;
CREATE TABLE `composicion_combo` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `combo_id` int(11) NOT NULL,
  `producto_id` int(11) NOT NULL,
  `cantidad` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `producto_id` (`producto_id`),
  KEY `idx_combo_id` (`combo_id`),
  CONSTRAINT `fk_composicion_combo_1` FOREIGN KEY (`combo_id`) REFERENCES `combos_promocionales` (`id`) ON DELETE CASCADE,
  CONSTRAINT `fk_composicion_combo_2` FOREIGN KEY (`producto_id`) REFERENCES `productos` (`id`)
);

-- Table structure for table `imagenes_producto`
DROP TABLE IF EXISTS `imagenes_producto`;
CREATE TABLE `imagenes_producto` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `producto_id` int(11) NOT NULL,
  `url_imagen` varchar(255) DEFAULT NULL,
  `texto_alternativo` varchar(255) DEFAULT NULL,
  `es_principal` tinyint(1) DEFAULT 0,
  `orden` int(11) DEFAULT NULL,
  `fecha_creacion` timestamp NULL DEFAULT current_timestamp(),
  PRIMARY KEY (`id`),
  KEY `idx_producto_id` (`producto_id`),
  CONSTRAINT `fk_imagenes_producto_1` FOREIGN KEY (`producto_id`) REFERENCES `productos` (`id`) ON DELETE CASCADE
);

-- Table structure for table `pagos_proveedor`
DROP TABLE IF EXISTS `pagos_proveedor`;
CREATE TABLE `pagos_proveedor` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `cuenta_por_pagar_id` int(11) NOT NULL,
  `monto_pagado` decimal(10,2) DEFAULT NULL,
  `forma_pago` enum('efectivo','transferencia','tarjeta','otro') DEFAULT 'efectivo',
  `numero_comprobante` varchar(50) DEFAULT NULL,
  `fecha_pago` date DEFAULT NULL,
  `usuario_responsable_id` int(11) DEFAULT NULL,
  `observaciones` text DEFAULT NULL,
  `fecha_creacion` timestamp NULL DEFAULT current_timestamp(),
  PRIMARY KEY (`id`),
  KEY `usuario_responsable_id` (`usuario_responsable_id`),
  KEY `idx_cuenta_id` (`cuenta_por_pagar_id`),
  KEY `idx_fecha_pago` (`fecha_pago`),
  CONSTRAINT `fk_pagos_proveedor_1` FOREIGN KEY (`cuenta_por_pagar_id`) REFERENCES `cuentas_por_pagar` (`id`),
  CONSTRAINT `fk_pagos_proveedor_2` FOREIGN KEY (`usuario_responsable_id`) REFERENCES `usuarios` (`id`)
);

-- Table structure for table `detalle_orden_compra`
DROP TABLE IF EXISTS `detalle_orden_compra`;
CREATE TABLE `detalle_orden_compra` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `orden_compra_id` int(11) NOT NULL,
  `producto_id` int(11) NOT NULL,
  `cantidad_solicitada` int(11) DEFAULT NULL,
  `cantidad_recibida` int(11) DEFAULT 0,
  `precio_unitario` decimal(10,2) DEFAULT NULL,
  `subtotal` decimal(10,2) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `producto_id` (`producto_id`),
  KEY `idx_orden_compra_id` (`orden_compra_id`),
  CONSTRAINT `fk_detalle_orden_compra_1` FOREIGN KEY (`orden_compra_id`) REFERENCES `ordenes_compra` (`id`) ON DELETE CASCADE,
  CONSTRAINT `fk_detalle_orden_compra_2` FOREIGN KEY (`producto_id`) REFERENCES `productos` (`id`)
);

-- Table structure for table `lotes_inventario`
DROP TABLE IF EXISTS `lotes_inventario`;
CREATE TABLE `lotes_inventario` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `producto_id` int(11) NOT NULL,
  `almacen_id` int(11) NOT NULL,
  `numero_lote` varchar(100) DEFAULT NULL,
  `cantidad` int(11) DEFAULT NULL,
  `cantidad_disponible` int(11) DEFAULT NULL,
  `precio_unitario` decimal(10,2) DEFAULT NULL,
  `fecha_vencimiento` date DEFAULT NULL,
  `proveedor_id` int(11) DEFAULT NULL,
  `fecha_ingreso` date DEFAULT NULL,
  `observaciones` text DEFAULT NULL,
  `estado` enum('disponible','reservado','agotado','vencido') DEFAULT 'disponible',
  `fecha_creacion` timestamp NULL DEFAULT current_timestamp(),
  PRIMARY KEY (`id`),
  KEY `idx_producto_id` (`producto_id`),
  KEY `idx_almacen_id` (`almacen_id`),
  KEY `idx_fecha_vencimiento` (`fecha_vencimiento`),
  KEY `idx_estado` (`estado`),
  KEY `idx_lote_producto` (`producto_id`),
  CONSTRAINT `fk_lotes_inventario_1` FOREIGN KEY (`producto_id`) REFERENCES `productos` (`id`),
  CONSTRAINT `fk_lotes_inventario_2` FOREIGN KEY (`almacen_id`) REFERENCES `almacenes` (`id`)
);

-- Table structure for table `servicios_cita`
DROP TABLE IF EXISTS `servicios_cita`;
CREATE TABLE `servicios_cita` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `cita_id` int(11) NOT NULL,
  `servicio_id` int(11) NOT NULL,
  `precio` decimal(10,2) DEFAULT NULL,
  `observaciones` text DEFAULT NULL,
  `fecha_registro` timestamp NULL DEFAULT current_timestamp(),
  PRIMARY KEY (`id`),
  KEY `servicio_id` (`servicio_id`),
  KEY `idx_cita_id` (`cita_id`),
  CONSTRAINT `fk_servicios_cita_1` FOREIGN KEY (`cita_id`) REFERENCES `citas` (`id`) ON DELETE CASCADE,
  CONSTRAINT `fk_servicios_cita_2` FOREIGN KEY (`servicio_id`) REFERENCES `servicios_belleza` (`id`)
);

-- Table structure for table `detalle_pedido`
DROP TABLE IF EXISTS `detalle_pedido`;
CREATE TABLE `detalle_pedido` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `pedido_id` int(11) NOT NULL,
  `producto_id` int(11) NOT NULL,
  `cantidad` int(11) DEFAULT NULL,
  `precio_unitario` decimal(10,2) DEFAULT NULL,
  `descuento` decimal(10,2) DEFAULT 0.00,
  `subtotal` decimal(10,2) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `producto_id` (`producto_id`),
  KEY `idx_pedido_id` (`pedido_id`),
  CONSTRAINT `fk_detalle_pedido_1` FOREIGN KEY (`pedido_id`) REFERENCES `pedidos` (`id`) ON DELETE CASCADE,
  CONSTRAINT `fk_detalle_pedido_2` FOREIGN KEY (`producto_id`) REFERENCES `productos` (`id`)
);

-- Table structure for table `caja_chica`
DROP TABLE IF EXISTS `caja_chica`;
CREATE TABLE `caja_chica` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `sesion_caja_id` int(11) NOT NULL,
  `concepto` varchar(255) DEFAULT NULL,
  `monto` decimal(10,2) DEFAULT NULL,
  `descripcion` text DEFAULT NULL,
  `comprobante_url` varchar(255) DEFAULT NULL,
  `usuario_id` int(11) DEFAULT NULL,
  `fecha_registro` timestamp NULL DEFAULT current_timestamp(),
  PRIMARY KEY (`id`),
  KEY `usuario_id` (`usuario_id`),
  KEY `idx_sesion_caja_id` (`sesion_caja_id`),
  CONSTRAINT `fk_caja_chica_1` FOREIGN KEY (`sesion_caja_id`) REFERENCES `sesiones_caja` (`id`),
  CONSTRAINT `fk_caja_chica_2` FOREIGN KEY (`usuario_id`) REFERENCES `usuarios` (`id`)
);

-- Table structure for table `ventas`
DROP TABLE IF EXISTS `ventas`;
CREATE TABLE `ventas` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `tenant_id` int(11) NOT NULL,
  `sede_id` int(11) NOT NULL,
  `sesion_caja_id` int(11) NOT NULL,
  `cliente_id` int(11) DEFAULT NULL,
  `numero_ticket` varchar(50) DEFAULT NULL,
  `comprobante_numero` varchar(50) DEFAULT NULL,
  `tipo_comprobante` enum('boleta','factura','nota_credito','nota_debito') DEFAULT 'boleta',
  `subtotal` decimal(10,2) DEFAULT NULL,
  `descuento` decimal(10,2) DEFAULT 0.00,
  `impuesto` decimal(10,2) DEFAULT NULL,
  `total` decimal(10,2) DEFAULT NULL,
  `estado` enum('completada','anulada','devuelta') DEFAULT 'completada',
  `estado_sunat` enum('aceptada','observada','rechazada','pendiente') DEFAULT 'pendiente',
  `usuario_id` int(11) DEFAULT NULL,
  `fecha_venta` timestamp NULL DEFAULT current_timestamp(),
  PRIMARY KEY (`id`),
  UNIQUE KEY `comprobante_numero` (`comprobante_numero`),
  KEY `sesion_caja_id` (`sesion_caja_id`),
  KEY `idx_tenant_id` (`tenant_id`),
  KEY `idx_sede_id` (`sede_id`),
  KEY `idx_fecha_venta` (`fecha_venta`),
  KEY `idx_estado` (`estado`),
  KEY `idx_venta_cliente` (`cliente_id`),
  KEY `idx_venta_usuario` (`usuario_id`),
  CONSTRAINT `fk_ventas_1` FOREIGN KEY (`tenant_id`) REFERENCES `tenants` (`id`) ON DELETE CASCADE,
  CONSTRAINT `fk_ventas_2` FOREIGN KEY (`sede_id`) REFERENCES `sedes` (`id`),
  CONSTRAINT `fk_ventas_3` FOREIGN KEY (`sesion_caja_id`) REFERENCES `sesiones_caja` (`id`),
  CONSTRAINT `fk_ventas_4` FOREIGN KEY (`cliente_id`) REFERENCES `clientes` (`id`),
  CONSTRAINT `fk_ventas_5` FOREIGN KEY (`usuario_id`) REFERENCES `usuarios` (`id`)
);

-- Table structure for table `detalle_devolucion_proveedor`
DROP TABLE IF EXISTS `detalle_devolucion_proveedor`;
CREATE TABLE `detalle_devolucion_proveedor` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `devolucion_id` int(11) NOT NULL,
  `producto_id` int(11) NOT NULL,
  `lote_id` int(11) DEFAULT NULL,
  `cantidad` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `producto_id` (`producto_id`),
  KEY `lote_id` (`lote_id`),
  KEY `idx_devolucion_id` (`devolucion_id`),
  CONSTRAINT `fk_detalle_devolucion_proveedor_1` FOREIGN KEY (`devolucion_id`) REFERENCES `devoluciones_proveedor` (`id`) ON DELETE CASCADE,
  CONSTRAINT `fk_detalle_devolucion_proveedor_2` FOREIGN KEY (`producto_id`) REFERENCES `productos` (`id`),
  CONSTRAINT `fk_detalle_devolucion_proveedor_3` FOREIGN KEY (`lote_id`) REFERENCES `lotes_inventario` (`id`)
);

-- Table structure for table `movimientos_inventario`
DROP TABLE IF EXISTS `movimientos_inventario`;
CREATE TABLE `movimientos_inventario` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `lote_id` int(11) NOT NULL,
  `tipo_movimiento` enum('entrada','salida','ajuste','transferencia','merma','devolucion') NOT NULL,
  `cantidad` int(11) DEFAULT NULL,
  `usuario_id` int(11) DEFAULT NULL,
  `motivo` varchar(255) DEFAULT NULL,
  `referencia_documento` varchar(100) DEFAULT NULL,
  `fecha_movimiento` timestamp NULL DEFAULT current_timestamp(),
  PRIMARY KEY (`id`),
  KEY `usuario_id` (`usuario_id`),
  KEY `idx_lote_id` (`lote_id`),
  KEY `idx_tipo_movimiento` (`tipo_movimiento`),
  KEY `idx_fecha_movimiento` (`fecha_movimiento`),
  CONSTRAINT `fk_movimientos_inventario_1` FOREIGN KEY (`lote_id`) REFERENCES `lotes_inventario` (`id`),
  CONSTRAINT `fk_movimientos_inventario_2` FOREIGN KEY (`usuario_id`) REFERENCES `usuarios` (`id`)
);

-- Table structure for table `comprobantes_electronicos`
DROP TABLE IF EXISTS `comprobantes_electronicos`;
CREATE TABLE `comprobantes_electronicos` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `tenant_id` int(11) NOT NULL,
  `tipo_comprobante` enum('boleta','factura','nota_credito','nota_debito') NOT NULL,
  `numero_serie` varchar(10) DEFAULT NULL,
  `numero_comprobante` varchar(10) DEFAULT NULL,
  `venta_id` int(11) DEFAULT NULL,
  `estado_sunat` enum('aceptada','observada','rechazada','pendiente_envio') DEFAULT 'pendiente_envio',
  `respuesta_sunat` text DEFAULT NULL,
  `fecha_envio_sunat` timestamp NULL DEFAULT NULL,
  `xml_generado` longtext DEFAULT NULL,
  `cdr_recibida` longtext DEFAULT NULL,
  `fecha_creacion` timestamp NULL DEFAULT current_timestamp(),
  PRIMARY KEY (`id`),
  UNIQUE KEY `unique_comprobante` (`tenant_id`,`numero_serie`,`numero_comprobante`),
  KEY `venta_id` (`venta_id`),
  KEY `idx_tenant_id` (`tenant_id`),
  KEY `idx_estado_sunat` (`estado_sunat`),
  CONSTRAINT `fk_comprobantes_electronicos_1` FOREIGN KEY (`tenant_id`) REFERENCES `tenants` (`id`) ON DELETE CASCADE,
  CONSTRAINT `fk_comprobantes_electronicos_2` FOREIGN KEY (`venta_id`) REFERENCES `ventas` (`id`)
);

-- Table structure for table `detalle_venta`
DROP TABLE IF EXISTS `detalle_venta`;
CREATE TABLE `detalle_venta` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `venta_id` int(11) NOT NULL,
  `producto_id` int(11) DEFAULT NULL,
  `cantidad` int(11) DEFAULT NULL,
  `precio_unitario` decimal(10,2) DEFAULT NULL,
  `descuento` decimal(10,2) DEFAULT 0.00,
  `subtotal` decimal(10,2) DEFAULT NULL,
  `lote_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `producto_id` (`producto_id`),
  KEY `lote_id` (`lote_id`),
  KEY `idx_venta_id` (`venta_id`),
  CONSTRAINT `fk_detalle_venta_1` FOREIGN KEY (`venta_id`) REFERENCES `ventas` (`id`) ON DELETE CASCADE,
  CONSTRAINT `fk_detalle_venta_2` FOREIGN KEY (`producto_id`) REFERENCES `productos` (`id`),
  CONSTRAINT `fk_detalle_venta_3` FOREIGN KEY (`lote_id`) REFERENCES `lotes_inventario` (`id`)
);

-- Table structure for table `devoluciones_venta`
DROP TABLE IF EXISTS `devoluciones_venta`;
CREATE TABLE `devoluciones_venta` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `tenant_id` int(11) NOT NULL,
  `venta_id` int(11) NOT NULL,
  `numero_devolucion` varchar(50) DEFAULT NULL,
  `motivo` varchar(255) DEFAULT NULL,
  `tipo_resolucion` enum('reembolso','cambio_producto','nota_credito') DEFAULT 'reembolso',
  `estado_devolucion` enum('pendiente','aprobada','rechazada','completada') DEFAULT 'pendiente',
  `monto_reembolso` decimal(10,2) DEFAULT NULL,
  `usuario_responsable_id` int(11) DEFAULT NULL,
  `fecha_devolucion` timestamp NULL DEFAULT current_timestamp(),
  PRIMARY KEY (`id`),
  UNIQUE KEY `numero_devolucion` (`numero_devolucion`),
  KEY `venta_id` (`venta_id`),
  KEY `usuario_responsable_id` (`usuario_responsable_id`),
  KEY `idx_tenant_id` (`tenant_id`),
  KEY `idx_estado_devolucion` (`estado_devolucion`),
  CONSTRAINT `fk_devoluciones_venta_1` FOREIGN KEY (`tenant_id`) REFERENCES `tenants` (`id`) ON DELETE CASCADE,
  CONSTRAINT `fk_devoluciones_venta_2` FOREIGN KEY (`venta_id`) REFERENCES `ventas` (`id`),
  CONSTRAINT `fk_devoluciones_venta_3` FOREIGN KEY (`usuario_responsable_id`) REFERENCES `usuarios` (`id`)
);

-- Table structure for table `formas_pago_venta`
DROP TABLE IF EXISTS `formas_pago_venta`;
CREATE TABLE `formas_pago_venta` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `venta_id` int(11) NOT NULL,
  `tipo_pago` enum('efectivo','tarjeta_credito','tarjeta_debito','transferencia','billetera_digital','credito_tienda') DEFAULT 'efectivo',
  `monto` decimal(10,2) DEFAULT NULL,
  `referencia_transaccion` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `idx_venta_id` (`venta_id`),
  CONSTRAINT `fk_formas_pago_venta_1` FOREIGN KEY (`venta_id`) REFERENCES `ventas` (`id`) ON DELETE CASCADE
);

-- Table structure for table `reclamos`
DROP TABLE IF EXISTS `reclamos`;
CREATE TABLE `reclamos` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `tenant_id` int(11) NOT NULL,
  `cliente_id` int(11) NOT NULL,
  `venta_id` int(11) DEFAULT NULL,
  `numero_reclamo` varchar(50) DEFAULT NULL,
  `canal_ingreso` enum('presencial','web','telefono','whatsapp') DEFAULT 'presencial',
  `tipo_incidencia` varchar(100) DEFAULT NULL,
  `descripcion` text DEFAULT NULL,
  `estado` enum('abierto','en_proceso','resuelto','cerrado') DEFAULT 'abierto',
  `responsable_id` int(11) DEFAULT NULL,
  `sla_horas` int(11) DEFAULT NULL,
  `fecha_vencimiento_sla` datetime DEFAULT NULL,
  `solucion_aplicada` text DEFAULT NULL,
  `usuario_creacion_id` int(11) DEFAULT NULL,
  `fecha_creacion` timestamp NULL DEFAULT current_timestamp(),
  `fecha_cierre` timestamp NULL DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `numero_reclamo` (`numero_reclamo`),
  KEY `venta_id` (`venta_id`),
  KEY `responsable_id` (`responsable_id`),
  KEY `usuario_creacion_id` (`usuario_creacion_id`),
  KEY `idx_tenant_id` (`tenant_id`),
  KEY `idx_estado` (`estado`),
  KEY `idx_reclamo_cliente` (`cliente_id`),
  CONSTRAINT `fk_reclamos_1` FOREIGN KEY (`tenant_id`) REFERENCES `tenants` (`id`) ON DELETE CASCADE,
  CONSTRAINT `fk_reclamos_2` FOREIGN KEY (`cliente_id`) REFERENCES `clientes` (`id`),
  CONSTRAINT `fk_reclamos_3` FOREIGN KEY (`venta_id`) REFERENCES `ventas` (`id`),
  CONSTRAINT `fk_reclamos_4` FOREIGN KEY (`responsable_id`) REFERENCES `usuarios` (`id`),
  CONSTRAINT `fk_reclamos_5` FOREIGN KEY (`usuario_creacion_id`) REFERENCES `usuarios` (`id`)
);

-- Table structure for table `detalle_devolucion_venta`
DROP TABLE IF EXISTS `detalle_devolucion_venta`;
CREATE TABLE `detalle_devolucion_venta` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `devolucion_id` int(11) NOT NULL,
  `producto_id` int(11) NOT NULL,
  `cantidad` int(11) DEFAULT NULL,
  `precio_unitario` decimal(10,2) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `producto_id` (`producto_id`),
  KEY `idx_devolucion_id` (`devolucion_id`),
  CONSTRAINT `fk_detalle_devolucion_venta_1` FOREIGN KEY (`devolucion_id`) REFERENCES `devoluciones_venta` (`id`) ON DELETE CASCADE,
  CONSTRAINT `fk_detalle_devolucion_venta_2` FOREIGN KEY (`producto_id`) REFERENCES `productos` (`id`)
);

SET FOREIGN_KEY_CHECKS = 1;
