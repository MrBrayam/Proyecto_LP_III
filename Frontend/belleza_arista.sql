-- phpMyAdmin SQL Dump
-- version 5.2.2
-- https://www.phpmyadmin.net/
--
-- Servidor: localhost:3306
-- Tiempo de generación: 10-07-2026 a las 07:57:08
-- Versión del servidor: 10.11.18-MariaDB
-- Versión de PHP: 8.4.22

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de datos: `belleza_arista`
--

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `almacenes`
--

CREATE TABLE `almacenes` (
  `id_almacenes` int(11) NOT NULL,
  `id_tenants` int(11) NOT NULL,
  `id_sedes` int(11) NOT NULL,
  `nombre_almacen` varchar(100) NOT NULL,
  `ubicacion` varchar(255) DEFAULT NULL,
  `capacidad` int(11) DEFAULT NULL,
  `estado` tinyint(1) DEFAULT 1
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `almacenes`
--

INSERT INTO `almacenes` (`id_almacenes`, `id_tenants`, `id_sedes`, `nombre_almacen`, `ubicacion`, `capacidad`, `estado`) VALUES
(1, 1, 1, 'Almacén Principal', 'Sótano - Sede Central', 600, 1),
(2, 1, 2, 'Almacén Miraflores', 'Trastienda - Miraflores', 350, 1),
(3, 1, 3, 'Almacén San Borja', 'Trastienda - San Borja', 200, 1),
(28, 2, 28, 'Almacén Principal Sede 1', 'Piso 1', 1000, 1),
(29, 2, 29, 'Almacén Principal Sede 2', 'Piso 1', 1000, 1),
(30, 2, 30, 'Almacén Principal Sede 3', 'Piso 1', 1000, 1),
(31, 3, 31, 'Almacén Principal Sede 1', 'Piso 1', 1000, 1),
(32, 3, 32, 'Almacén Principal Sede 2', 'Piso 1', 1000, 1),
(33, 3, 33, 'Almacén Principal Sede 3', 'Piso 1', 1000, 1),
(34, 4, 34, 'Almacén Principal Sede 1', 'Piso 1', 1000, 1),
(35, 4, 35, 'Almacén Principal Sede 2', 'Piso 1', 1000, 1),
(36, 4, 36, 'Almacén Principal Sede 3', 'Piso 1', 1000, 1),
(37, 5, 37, 'Almacén Principal Sede 1', 'Piso 1', 1000, 1),
(38, 5, 38, 'Almacén Principal Sede 2', 'Piso 1', 1000, 1),
(39, 5, 39, 'Almacén Principal Sede 3', 'Piso 1', 1000, 1);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `auditoria`
--

CREATE TABLE `auditoria` (
  `id_auditoria` int(11) NOT NULL,
  `id_tenants` int(11) NOT NULL,
  `id_usuarios` int(11) DEFAULT NULL,
  `accion` varchar(255) DEFAULT NULL,
  `tabla_afectada` varchar(100) DEFAULT NULL,
  `id_registro` int(11) DEFAULT NULL,
  `datos_anteriores` longtext CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL CHECK (json_valid(`datos_anteriores`)),
  `datos_nuevos` longtext CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL CHECK (json_valid(`datos_nuevos`)),
  `ip_address` varchar(45) DEFAULT NULL,
  `fecha_hora` timestamp NULL DEFAULT current_timestamp()
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `auditoria`
--

INSERT INTO `auditoria` (`id_auditoria`, `id_tenants`, `id_usuarios`, `accion`, `tabla_afectada`, `id_registro`, `datos_anteriores`, `datos_nuevos`, `ip_address`, `fecha_hora`) VALUES
(1, 1, 1, 'INSERT', 'productos', 1, NULL, '{\"nombre_producto\":\"Tinte Majirel N°7\"}', '192.168.1.10', '2026-06-02 02:52:12'),
(2, 1, 2, 'INSERT', 'ventas', 1, NULL, '{\"total\":35.00,\"tipo_comprobante\":\"boleta\"}', '192.168.1.11', '2026-06-02 02:52:12'),
(3, 1, 1, 'UPDATE', 'suscripciones', 1, '{\"estado\":1}', '{\"estado\":1}', '192.168.1.10', '2026-06-02 02:52:12'),
(5, 1, NULL, 'DELETE', 'combo_promocionals', 1, NULL, NULL, '0:0:0:0:0:0:0:1', '2026-07-10 01:50:54'),
(6, 1, NULL, 'DELETE', 'combo_promocionals', 2, NULL, NULL, '0:0:0:0:0:0:0:1', '2026-07-10 01:50:55'),
(7, 1, NULL, 'DELETE', 'combo_promocionals', 3, NULL, NULL, '0:0:0:0:0:0:0:1', '2026-07-10 01:50:56'),
(8, 1, NULL, 'DELETE', 'promocions', 1, NULL, NULL, '0:0:0:0:0:0:0:1', '2026-07-10 01:51:03'),
(9, 1, NULL, 'DELETE', 'promocions', 2, NULL, NULL, '0:0:0:0:0:0:0:1', '2026-07-10 01:51:05'),
(10, 1, NULL, 'DELETE', 'promocions', 3, NULL, NULL, '0:0:0:0:0:0:0:1', '2026-07-10 01:51:06'),
(11, 1, NULL, 'INSERT', 'combo_promocionals', NULL, NULL, '{\"descripcion\":\"Hola \",\"estado\":\"1\",\"visible_storefront\":\"1\",\"fecha_inicio\":\"2026-07-08\",\"fecha_fin\":\"2026-07-15\",\"precio_combo\":\"30\",\"precio_original\":\"45.00\",\"id_tenants\":1,\"nombre_promocion\":\"Test combo\",\"id_combos_promocionales\":null}', '0:0:0:0:0:0:0:1', '2026-07-10 01:51:35'),
(12, 1, NULL, 'INSERT', 'composicion_combos', NULL, NULL, '{\"cantidad\":\"1\",\"id_productos\":1,\"id_composicion_combo\":null,\"id_combos_promocionales\":41}', '0:0:0:0:0:0:0:1', '2026-07-10 01:51:35'),
(13, 1, NULL, 'INSERT', 'composicion_combos', NULL, NULL, '{\"cantidad\":\"1\",\"id_productos\":4,\"id_composicion_combo\":null,\"id_combos_promocionales\":41}', '0:0:0:0:0:0:0:1', '2026-07-10 01:51:35'),
(14, 1, NULL, 'INSERT', 'ventas', NULL, NULL, '{\"estado\":\"1\",\"comprobante_numero\":\"C-1783648351113\",\"estado_sunat\":\"aceptada\",\"descuento\":\"0\",\"impuesto\":\"26.694915254237287\",\"numero_ticket\":\"TK-1783648351113\",\"fecha_venta\":\"2026-07-09T20:52:31.113639\",\"total\":\"175.0\",\"id_usuarios\":null,\"id_tenants\":1,\"id_clientes\":4,\"id_ventas\":null,\"id_sedes\":1,\"subtotal\":\"148.3050847457627\",\"id_sesiones_caja\":1,\"tipo_comprobante\":\"boleta\"}', '0:0:0:0:0:0:0:1', '2026-07-10 01:52:31'),
(15, 1, NULL, 'INSERT', 'detalle_ventas', NULL, NULL, '{\"id_detalle_venta\":null,\"descuento\":\"0\",\"cantidad\":\"5\",\"id_productos\":1,\"id_ventas\":52,\"subtotal\":\"175.0\",\"precio_unitario\":\"35.0\",\"id_lotes_inventario\":null}', '0:0:0:0:0:0:0:1', '2026-07-10 01:52:31'),
(16, 1, NULL, 'UPDATE', 'productos', 1, '{\"codigo_interno\":\"TIN-001\",\"descripcion\":\"Tinte permanente rubio medio con cobertura 100%\",\"stock_critico\":\"5\",\"codigo_barras\":\"7501234560001\",\"tipo_producto\":\"tinte\",\"contenido_neto\":\"50ml\",\"stock_minimo\":\"10\",\"presentacion\":\"tubo\",\"id_marcas\":1,\"precio_costo\":\"20.00\",\"estado\":\"1\",\"visible_storefront\":\"1\",\"etiqueta_especial\":null,\"id_categorias_productos\":1,\"img_url\":\"/uploads/383bcfd951ce46ceb2d53f851a336527.jpeg\",\"margen_ganancia\":\"75.00\",\"id_tenants\":1,\"precio_venta\":\"35.00\",\"id_productos\":\"1\",\"stock_actual\":\"24\",\"nombre_producto\":\"Tinte L\'Oréal Majirel N°7\"}', '{\"codigo_interno\":\"TIN-001\",\"descripcion\":\"Tinte permanente rubio medio con cobertura 100%\",\"stock_critico\":\"5\",\"codigo_barras\":\"7501234560001\",\"tipo_producto\":\"tinte\",\"contenido_neto\":\"50ml\",\"stock_minimo\":\"10\",\"presentacion\":\"tubo\",\"id_marcas\":1,\"precio_costo\":\"20.00\",\"estado\":\"1\",\"visible_storefront\":\"1\",\"etiqueta_especial\":null,\"id_categorias_productos\":1,\"img_url\":\"/uploads/383bcfd951ce46ceb2d53f851a336527.jpeg\",\"margen_ganancia\":\"75.00\",\"id_tenants\":1,\"precio_venta\":\"35.00\",\"id_productos\":\"1\",\"stock_actual\":\"24\",\"nombre_producto\":\"Tinte L\'Oréal Majirel N°7\"}', '0:0:0:0:0:0:0:1', '2026-07-10 01:52:31'),
(17, 1, NULL, 'INSERT', 'promocions', NULL, NULL, '{\"estado\":\"1\",\"id_categorias_productos\":1,\"fecha_inicio\":\"2026-07-08\",\"fecha_fin\":\"2026-07-15\",\"valor_descuento\":\"20\",\"id_promociones\":null,\"compra_minima\":\"10\",\"tipo_descuento\":\"porcentual\",\"id_tenants\":1,\"nombre_promocion\":\"TEST\"}', '0:0:0:0:0:0:0:1', '2026-07-10 01:55:36'),
(18, 1, NULL, 'INSERT', 'ventas', NULL, NULL, '{\"impuesto\":\"13.728813559322035\",\"total\":\"90.0\",\"id_sedes\":1,\"id_clientes\":4,\"subtotal\":\"76.27118644067797\",\"fecha_venta\":\"2026-07-09T22:38:24.508982900\",\"numero_ticket\":\"TK-1783654704508\",\"id_tenants\":1,\"id_usuarios\":null,\"id_ventas\":null,\"estado_sunat\":\"aceptada\",\"descuento\":\"0\",\"tipo_comprobante\":\"boleta\",\"id_sesiones_caja\":1,\"comprobante_numero\":\"C-1783654704508\",\"estado\":\"1\"}', '0:0:0:0:0:0:0:1', '2026-07-10 03:38:24'),
(19, 1, NULL, 'INSERT', 'detalle_ventas', NULL, NULL, '{\"subtotal\":\"70.0\",\"precio_unitario\":\"23.333333333333332\",\"cantidad\":\"3\",\"id_ventas\":53,\"id_productos\":1,\"descuento\":\"0\",\"id_lotes_inventario\":null,\"id_detalle_venta\":null}', '0:0:0:0:0:0:0:1', '2026-07-10 03:38:24'),
(20, 1, NULL, 'INSERT', 'detalle_ventas', NULL, NULL, '{\"subtotal\":\"20.0\",\"precio_unitario\":\"6.666666666666667\",\"cantidad\":\"3\",\"id_ventas\":53,\"id_productos\":4,\"descuento\":\"0\",\"id_lotes_inventario\":null,\"id_detalle_venta\":null}', '0:0:0:0:0:0:0:1', '2026-07-10 03:38:24'),
(21, 1, NULL, 'UPDATE', 'productos', 1, '{\"etiqueta_especial\":null,\"id_categorias_productos\":1,\"margen_ganancia\":\"75.00\",\"nombre_producto\":\"Tinte L\'Oréal Majirel N°7\",\"stock_critico\":\"5\",\"presentacion\":\"tubo\",\"codigo_barras\":\"7501234560001\",\"descripcion\":\"Tinte permanente rubio medio con cobertura 100%\",\"stock_minimo\":\"10\",\"id_marcas\":1,\"contenido_neto\":\"50ml\",\"codigo_interno\":\"TIN-001\",\"precio_costo\":\"20.00\",\"tipo_producto\":\"tinte\",\"id_tenants\":1,\"precio_venta\":\"35.00\",\"id_productos\":\"1\",\"stock_actual\":\"21\",\"estado\":\"1\",\"img_url\":\"/uploads/383bcfd951ce46ceb2d53f851a336527.jpeg\",\"visible_storefront\":\"1\",\"hibernateLazyInitializer\":\"org.hibernate.proxy.pojo.bytebuddy.ByteBuddyInterceptor@1aa18bf4\"}', '{\"etiqueta_especial\":null,\"id_categorias_productos\":1,\"margen_ganancia\":\"75.00\",\"nombre_producto\":\"Tinte L\'Oréal Majirel N°7\",\"stock_critico\":\"5\",\"presentacion\":\"tubo\",\"codigo_barras\":\"7501234560001\",\"descripcion\":\"Tinte permanente rubio medio con cobertura 100%\",\"stock_minimo\":\"10\",\"id_marcas\":1,\"contenido_neto\":\"50ml\",\"codigo_interno\":\"TIN-001\",\"precio_costo\":\"20.00\",\"tipo_producto\":\"tinte\",\"id_tenants\":1,\"precio_venta\":\"35.00\",\"id_productos\":\"1\",\"stock_actual\":\"21\",\"estado\":\"1\",\"img_url\":\"/uploads/383bcfd951ce46ceb2d53f851a336527.jpeg\",\"visible_storefront\":\"1\",\"hibernateLazyInitializer\":\"org.hibernate.proxy.pojo.bytebuddy.ByteBuddyInterceptor@1aa18bf4\"}', '0:0:0:0:0:0:0:1', '2026-07-10 03:38:24'),
(22, 1, NULL, 'UPDATE', 'productos', 4, '{\"etiqueta_especial\":null,\"id_categorias_productos\":4,\"margen_ganancia\":\"100.00\",\"nombre_producto\":\"Test\",\"stock_critico\":\"5\",\"presentacion\":\"Caja\",\"codigo_barras\":\"7501234560007\",\"descripcion\":\"holis\",\"stock_minimo\":\"10\",\"id_marcas\":3,\"contenido_neto\":\"269gr\",\"codigo_interno\":\"EXA-003\",\"precio_costo\":\"5.00\",\"tipo_producto\":\"Cosmetico\",\"id_tenants\":1,\"precio_venta\":\"10.00\",\"id_productos\":\"4\",\"stock_actual\":\"36\",\"estado\":\"1\",\"img_url\":\"/uploads/1d61723730904a9f8a391fa15ef004c7.jpg\",\"visible_storefront\":\"1\",\"hibernateLazyInitializer\":\"org.hibernate.proxy.pojo.bytebuddy.ByteBuddyInterceptor@81ffb9a\"}', '{\"etiqueta_especial\":null,\"id_categorias_productos\":4,\"margen_ganancia\":\"100.00\",\"nombre_producto\":\"Test\",\"stock_critico\":\"5\",\"presentacion\":\"Caja\",\"codigo_barras\":\"7501234560007\",\"descripcion\":\"holis\",\"stock_minimo\":\"10\",\"id_marcas\":3,\"contenido_neto\":\"269gr\",\"codigo_interno\":\"EXA-003\",\"precio_costo\":\"5.00\",\"tipo_producto\":\"Cosmetico\",\"id_tenants\":1,\"precio_venta\":\"10.00\",\"id_productos\":\"4\",\"stock_actual\":\"36\",\"estado\":\"1\",\"img_url\":\"/uploads/1d61723730904a9f8a391fa15ef004c7.jpg\",\"visible_storefront\":\"1\",\"hibernateLazyInitializer\":\"org.hibernate.proxy.pojo.bytebuddy.ByteBuddyInterceptor@81ffb9a\"}', '0:0:0:0:0:0:0:1', '2026-07-10 03:38:24'),
(23, 1, NULL, 'INSERT', 'pedidos', NULL, NULL, '{\"costo_envio\":\"0\",\"modalidad\":\"delivery\",\"id_clientes\":4,\"subtotal\":\"101.69491525423729\",\"impuesto\":\"18.305084745762713\",\"fecha_pedido\":\"2026-07-09T22:56:24.321775100\",\"id_pedidos\":null,\"numero_pedido\":\"PED-1783655784322\",\"fecha_entrega_real\":null,\"fecha_entrega_estimada\":null,\"direccion_entrega\":\"Jr. Sinchi Roca Cdra. 2\",\"id_zonas_delivery\":null,\"estado\":\"1\",\"total\":\"120.0\",\"id_tenants\":1,\"id_usuarios\":null}', '0:0:0:0:0:0:0:1', '2026-07-10 03:56:24'),
(24, 1, NULL, 'INSERT', 'citas', NULL, NULL, '{\"observaciones\":\"\",\"id_usuarios_usuario_creacion\":null,\"id_usuarios_especialista\":null,\"hora_fin\":\"19:30\",\"hora_inicio\":\"18:00\",\"id_sedes\":1,\"id_clientes\":4,\"duracion_minutos\":\"90\",\"id_ventas\":null,\"id_citas\":null,\"fecha_cita\":\"2026-07-13\",\"estado\":\"1\",\"id_tenants\":1}', '0:0:0:0:0:0:0:1', '2026-07-10 03:56:24'),
(25, 1, NULL, 'INSERT', 'servicio_citas', NULL, NULL, '{\"fecha_registro\":\"2026-07-09T22:56:24.424222900\",\"observaciones\":\"Reservado online\",\"id_servicios_belleza\":3,\"id_servicios_cita\":null,\"id_citas\":44,\"precio\":\"120.00\"}', '0:0:0:0:0:0:0:1', '2026-07-10 03:56:24'),
(26, 1, NULL, 'UPDATE', 'orden_compras', 3, '{\"id_proveedores\":3,\"monto_total\":\"210\",\"numero_orden\":\"OC-2026-003\",\"fecha_orden\":\"2026-06-01\",\"forma_pago\":\"transferencia\",\"id_ordenes_compra\":\"3\",\"fecha_entrega_estimada\":\"2026-06-08\",\"condiciones_entrega\":null,\"estado\":\"2\",\"notas\":\"Esmaltes y accesorios nail art\",\"id_tenants\":1}', '{\"id_proveedores\":3,\"monto_total\":\"210\",\"numero_orden\":\"OC-2026-003\",\"fecha_orden\":\"2026-06-01\",\"forma_pago\":\"transferencia\",\"id_ordenes_compra\":\"3\",\"fecha_entrega_estimada\":\"2026-06-08\",\"condiciones_entrega\":null,\"estado\":\"2\",\"notas\":\"Esmaltes y accesorios nail art\",\"id_tenants\":1}', '0:0:0:0:0:0:0:1', '2026-07-10 04:14:29'),
(28, 1, NULL, 'INSERT', 'ventas', NULL, NULL, '{\"estado_sunat\":\"aceptada\",\"descuento\":\"0\",\"fecha_venta\":\"2026-07-09T23:52:16.198824600\",\"id_sedes\":1,\"id_clientes\":4,\"subtotal\":\"150\",\"impuesto\":\"27\",\"tipo_comprobante\":\"boleta\",\"id_sesiones_caja\":1,\"comprobante_numero\":\"B001-00000028\",\"numero_ticket\":\"TKT-00000028\",\"id_ventas\":null,\"estado\":\"1\",\"total\":\"177\",\"id_tenants\":1,\"id_usuarios\":1}', '0:0:0:0:0:0:0:1', '2026-07-10 04:52:16'),
(29, 1, NULL, 'INSERT', 'ventas', NULL, NULL, '{\"fecha_venta\":\"2026-07-10T00:03:11.979568\",\"estado\":\"1\",\"tipo_comprobante\":\"boleta\",\"id_clientes\":4,\"subtotal\":\"175.0\",\"impuesto\":\"31.5\",\"comprobante_numero\":\"B001-00000029\",\"id_sedes\":1,\"id_ventas\":null,\"numero_ticket\":\"TKT-00000029\",\"descuento\":\"0.0\",\"estado_sunat\":\"pendiente\",\"id_sesiones_caja\":31,\"id_usuarios\":1,\"id_tenants\":1,\"total\":\"206.5\"}', '0:0:0:0:0:0:0:1', '2026-07-10 05:03:12'),
(30, 1, NULL, 'INSERT', 'detalle_ventas', NULL, NULL, '{\"id_productos\":1,\"cantidad\":\"5\",\"id_detalle_venta\":null,\"subtotal\":\"175.0\",\"id_lotes_inventario\":null,\"precio_unitario\":\"35.0\",\"id_ventas\":55,\"descuento\":\"0\"}', '0:0:0:0:0:0:0:1', '2026-07-10 05:03:12'),
(31, 1, NULL, 'UPDATE', 'lote_inventarios', 1, '{\"estado\":\"1\",\"id_productos\":1,\"numero_lote\":\"LOT-2026-001\",\"cantidad\":\"50\",\"id_proveedores\":1,\"id_almacenes\":1,\"cantidad_disponible\":\"33\",\"id_lotes_inventario\":\"1\",\"precio_unitario\":\"18.00\",\"observaciones\":null,\"fecha_ingreso\":\"2026-05-27\",\"fecha_vencimiento\":\"2027-12-31\"}', '{\"estado\":\"1\",\"id_productos\":1,\"numero_lote\":\"LOT-2026-001\",\"cantidad\":\"50\",\"id_proveedores\":1,\"id_almacenes\":1,\"cantidad_disponible\":\"33\",\"id_lotes_inventario\":\"1\",\"precio_unitario\":\"18.00\",\"observaciones\":null,\"fecha_ingreso\":\"2026-05-27\",\"fecha_vencimiento\":\"2027-12-31\"}', '0:0:0:0:0:0:0:1', '2026-07-10 05:03:12'),
(32, 1, NULL, 'INSERT', 'movimiento_inventarios', NULL, NULL, '{\"motivo\":\"Venta directa\",\"cantidad\":\"5\",\"id_lotes_inventario\":1,\"tipo_movimiento\":\"salida\",\"referencia_documento\":\"B001-00000029\",\"fecha_movimiento\":\"2026-07-10T00:03:12.198178200\",\"id_usuarios\":null,\"id_movimientos_inventario\":null}', '0:0:0:0:0:0:0:1', '2026-07-10 05:03:12'),
(33, 1, NULL, 'UPDATE', 'productos', 1, '{\"id_categorias_productos\":1,\"etiqueta_especial\":null,\"tipo_producto\":\"tinte\",\"descripcion\":\"Tinte permanente rubio medio con cobertura 100%\",\"presentacion\":\"tubo\",\"precio_costo\":\"20.00\",\"contenido_neto\":\"50ml\",\"stock_minimo\":\"10\",\"stock_critico\":\"5\",\"stock_actual\":\"33\",\"id_marcas\":1,\"estado\":\"1\",\"id_productos\":\"1\",\"visible_storefront\":\"1\",\"precio_venta\":\"35.00\",\"nombre_producto\":\"Tinte L\'Oréal Majirel N°7\",\"codigo_interno\":\"TIN-001\",\"codigo_barras\":\"7501234560001\",\"margen_ganancia\":\"75.00\",\"id_tenants\":1,\"img_url\":\"/uploads/383bcfd951ce46ceb2d53f851a336527.jpeg\"}', '{\"id_categorias_productos\":1,\"etiqueta_especial\":null,\"tipo_producto\":\"tinte\",\"descripcion\":\"Tinte permanente rubio medio con cobertura 100%\",\"presentacion\":\"tubo\",\"precio_costo\":\"20.00\",\"contenido_neto\":\"50ml\",\"stock_minimo\":\"10\",\"stock_critico\":\"5\",\"stock_actual\":\"33\",\"id_marcas\":1,\"estado\":\"1\",\"id_productos\":\"1\",\"visible_storefront\":\"1\",\"precio_venta\":\"35.00\",\"nombre_producto\":\"Tinte L\'Oréal Majirel N°7\",\"codigo_interno\":\"TIN-001\",\"codigo_barras\":\"7501234560001\",\"margen_ganancia\":\"75.00\",\"id_tenants\":1,\"img_url\":\"/uploads/383bcfd951ce46ceb2d53f851a336527.jpeg\"}', '0:0:0:0:0:0:0:1', '2026-07-10 05:03:12'),
(34, 1, NULL, 'UPDATE', 'ventas', 55, '{\"fecha_venta\":\"2026-07-10T00:03:54.787999600\",\"estado\":\"1\",\"tipo_comprobante\":\"boleta\",\"id_clientes\":4,\"subtotal\":\"175.0\",\"impuesto\":\"31.5\",\"comprobante_numero\":\"B001-00000029\",\"id_sedes\":1,\"id_ventas\":\"55\",\"numero_ticket\":\"TKT-00000029\",\"descuento\":\"0.0\",\"estado_sunat\":\"aceptada\",\"id_sesiones_caja\":31,\"id_usuarios\":1,\"id_tenants\":1,\"total\":\"206.5\"}', '{\"fecha_venta\":\"2026-07-10T00:03:54.787999600\",\"estado\":\"1\",\"tipo_comprobante\":\"boleta\",\"id_clientes\":4,\"subtotal\":\"175.0\",\"impuesto\":\"31.5\",\"comprobante_numero\":\"B001-00000029\",\"id_sedes\":1,\"id_ventas\":\"55\",\"numero_ticket\":\"TKT-00000029\",\"descuento\":\"0.0\",\"estado_sunat\":\"aceptada\",\"id_sesiones_caja\":31,\"id_usuarios\":1,\"id_tenants\":1,\"total\":\"206.5\"}', '0:0:0:0:0:0:0:1', '2026-07-10 05:03:54'),
(35, 1, NULL, 'DELETE', 'detalle_ventas', 50, NULL, NULL, '0:0:0:0:0:0:0:1', '2026-07-10 05:03:54'),
(36, 1, NULL, 'INSERT', 'detalle_ventas', NULL, NULL, '{\"id_productos\":1,\"cantidad\":\"5\",\"id_detalle_venta\":null,\"subtotal\":\"175.0\",\"id_lotes_inventario\":null,\"precio_unitario\":\"35.0\",\"id_ventas\":55,\"descuento\":\"0\"}', '0:0:0:0:0:0:0:1', '2026-07-10 05:03:54'),
(37, 1, NULL, 'INSERT', 'ventas', NULL, NULL, '{\"fecha_venta\":\"2026-07-10T00:04:40.147270700\",\"estado\":\"1\",\"tipo_comprobante\":\"boleta\",\"id_clientes\":4,\"subtotal\":\"105.0\",\"impuesto\":\"18.9\",\"comprobante_numero\":\"B001-00000030\",\"id_sedes\":1,\"id_ventas\":null,\"numero_ticket\":\"TKT-00000030\",\"descuento\":\"0.0\",\"estado_sunat\":\"aceptada\",\"id_sesiones_caja\":33,\"id_usuarios\":1,\"id_tenants\":1,\"total\":\"123.9\"}', '0:0:0:0:0:0:0:1', '2026-07-10 05:04:40'),
(38, 1, NULL, 'INSERT', 'detalle_ventas', NULL, NULL, '{\"id_productos\":1,\"cantidad\":\"3\",\"id_detalle_venta\":null,\"subtotal\":\"105.0\",\"id_lotes_inventario\":null,\"precio_unitario\":\"35.0\",\"id_ventas\":56,\"descuento\":\"0\"}', '0:0:0:0:0:0:0:1', '2026-07-10 05:04:40'),
(39, 1, NULL, 'UPDATE', 'lote_inventarios', 1, '{\"estado\":\"1\",\"id_productos\":1,\"numero_lote\":\"LOT-2026-001\",\"cantidad\":\"50\",\"id_proveedores\":1,\"id_almacenes\":1,\"cantidad_disponible\":\"30\",\"id_lotes_inventario\":\"1\",\"precio_unitario\":\"18.00\",\"observaciones\":null,\"fecha_ingreso\":\"2026-05-27\",\"fecha_vencimiento\":\"2027-12-31\"}', '{\"estado\":\"1\",\"id_productos\":1,\"numero_lote\":\"LOT-2026-001\",\"cantidad\":\"50\",\"id_proveedores\":1,\"id_almacenes\":1,\"cantidad_disponible\":\"30\",\"id_lotes_inventario\":\"1\",\"precio_unitario\":\"18.00\",\"observaciones\":null,\"fecha_ingreso\":\"2026-05-27\",\"fecha_vencimiento\":\"2027-12-31\"}', '0:0:0:0:0:0:0:1', '2026-07-10 05:04:40'),
(40, 1, NULL, 'INSERT', 'movimiento_inventarios', NULL, NULL, '{\"motivo\":\"Venta directa\",\"cantidad\":\"3\",\"id_lotes_inventario\":1,\"tipo_movimiento\":\"salida\",\"referencia_documento\":\"B001-00000030\",\"fecha_movimiento\":\"2026-07-10T00:04:40.183039100\",\"id_usuarios\":null,\"id_movimientos_inventario\":null}', '0:0:0:0:0:0:0:1', '2026-07-10 05:04:40'),
(41, 1, NULL, 'UPDATE', 'productos', 1, '{\"id_categorias_productos\":1,\"etiqueta_especial\":null,\"tipo_producto\":\"tinte\",\"descripcion\":\"Tinte permanente rubio medio con cobertura 100%\",\"presentacion\":\"tubo\",\"precio_costo\":\"20.00\",\"contenido_neto\":\"50ml\",\"stock_minimo\":\"10\",\"stock_critico\":\"5\",\"stock_actual\":\"30\",\"id_marcas\":1,\"estado\":\"1\",\"id_productos\":\"1\",\"visible_storefront\":\"1\",\"precio_venta\":\"35.00\",\"nombre_producto\":\"Tinte L\'Oréal Majirel N°7\",\"codigo_interno\":\"TIN-001\",\"codigo_barras\":\"7501234560001\",\"margen_ganancia\":\"75.00\",\"id_tenants\":1,\"img_url\":\"/uploads/383bcfd951ce46ceb2d53f851a336527.jpeg\"}', '{\"id_categorias_productos\":1,\"etiqueta_especial\":null,\"tipo_producto\":\"tinte\",\"descripcion\":\"Tinte permanente rubio medio con cobertura 100%\",\"presentacion\":\"tubo\",\"precio_costo\":\"20.00\",\"contenido_neto\":\"50ml\",\"stock_minimo\":\"10\",\"stock_critico\":\"5\",\"stock_actual\":\"30\",\"id_marcas\":1,\"estado\":\"1\",\"id_productos\":\"1\",\"visible_storefront\":\"1\",\"precio_venta\":\"35.00\",\"nombre_producto\":\"Tinte L\'Oréal Majirel N°7\",\"codigo_interno\":\"TIN-001\",\"codigo_barras\":\"7501234560001\",\"margen_ganancia\":\"75.00\",\"id_tenants\":1,\"img_url\":\"/uploads/383bcfd951ce46ceb2d53f851a336527.jpeg\"}', '0:0:0:0:0:0:0:1', '2026-07-10 05:04:40'),
(42, 1, NULL, 'INSERT', 'ventas', NULL, NULL, '{\"fecha_venta\":\"2026-07-10T00:11:04.079275700\",\"estado\":\"1\",\"tipo_comprobante\":\"boleta\",\"id_clientes\":4,\"subtotal\":\"175.0\",\"impuesto\":\"31.5\",\"comprobante_numero\":\"B001-00000031\",\"id_sedes\":1,\"id_ventas\":null,\"numero_ticket\":\"TKT-00000031\",\"descuento\":\"0.0\",\"estado_sunat\":\"aceptada\",\"id_sesiones_caja\":36,\"id_usuarios\":1,\"id_tenants\":1,\"total\":\"206.5\"}', '0:0:0:0:0:0:0:1', '2026-07-10 05:11:04'),
(43, 1, NULL, 'UPDATE', 'lote_inventarios', 1, '{\"estado\":\"1\",\"id_productos\":1,\"numero_lote\":\"LOT-2026-001\",\"cantidad\":\"50\",\"id_proveedores\":1,\"id_almacenes\":1,\"cantidad_disponible\":\"25\",\"id_lotes_inventario\":\"1\",\"precio_unitario\":\"18.00\",\"observaciones\":null,\"fecha_ingreso\":\"2026-05-27\",\"fecha_vencimiento\":\"2027-12-31\"}', '{\"estado\":\"1\",\"id_productos\":1,\"numero_lote\":\"LOT-2026-001\",\"cantidad\":\"50\",\"id_proveedores\":1,\"id_almacenes\":1,\"cantidad_disponible\":\"25\",\"id_lotes_inventario\":\"1\",\"precio_unitario\":\"18.00\",\"observaciones\":null,\"fecha_ingreso\":\"2026-05-27\",\"fecha_vencimiento\":\"2027-12-31\"}', '0:0:0:0:0:0:0:1', '2026-07-10 05:11:04'),
(44, 1, NULL, 'INSERT', 'movimiento_inventarios', NULL, NULL, '{\"motivo\":\"Venta directa\",\"cantidad\":\"5\",\"id_lotes_inventario\":1,\"tipo_movimiento\":\"salida\",\"referencia_documento\":\"B001-00000031\",\"fecha_movimiento\":\"2026-07-10T00:11:04.167295500\",\"id_usuarios\":null,\"id_movimientos_inventario\":null}', '0:0:0:0:0:0:0:1', '2026-07-10 05:11:04'),
(45, 1, NULL, 'INSERT', 'detalle_ventas', NULL, NULL, '{\"id_productos\":1,\"cantidad\":\"5\",\"id_detalle_venta\":null,\"subtotal\":\"175.0\",\"id_lotes_inventario\":1,\"precio_unitario\":\"35.0\",\"id_ventas\":57,\"descuento\":\"0\"}', '0:0:0:0:0:0:0:1', '2026-07-10 05:11:04'),
(46, 1, NULL, 'UPDATE', 'productos', 1, '{\"id_categorias_productos\":1,\"etiqueta_especial\":null,\"tipo_producto\":\"tinte\",\"descripcion\":\"Tinte permanente rubio medio con cobertura 100%\",\"presentacion\":\"tubo\",\"precio_costo\":\"20.00\",\"contenido_neto\":\"50ml\",\"stock_minimo\":\"10\",\"stock_critico\":\"5\",\"stock_actual\":\"25\",\"id_marcas\":1,\"estado\":\"1\",\"id_productos\":\"1\",\"visible_storefront\":\"1\",\"precio_venta\":\"35.00\",\"nombre_producto\":\"Tinte L\'Oréal Majirel N°7\",\"codigo_interno\":\"TIN-001\",\"codigo_barras\":\"7501234560001\",\"margen_ganancia\":\"75.00\",\"id_tenants\":1,\"img_url\":\"/uploads/383bcfd951ce46ceb2d53f851a336527.jpeg\"}', '{\"id_categorias_productos\":1,\"etiqueta_especial\":null,\"tipo_producto\":\"tinte\",\"descripcion\":\"Tinte permanente rubio medio con cobertura 100%\",\"presentacion\":\"tubo\",\"precio_costo\":\"20.00\",\"contenido_neto\":\"50ml\",\"stock_minimo\":\"10\",\"stock_critico\":\"5\",\"stock_actual\":\"25\",\"id_marcas\":1,\"estado\":\"1\",\"id_productos\":\"1\",\"visible_storefront\":\"1\",\"precio_venta\":\"35.00\",\"nombre_producto\":\"Tinte L\'Oréal Majirel N°7\",\"codigo_interno\":\"TIN-001\",\"codigo_barras\":\"7501234560001\",\"margen_ganancia\":\"75.00\",\"id_tenants\":1,\"img_url\":\"/uploads/383bcfd951ce46ceb2d53f851a336527.jpeg\"}', '0:0:0:0:0:0:0:1', '2026-07-10 05:11:04'),
(47, 1, NULL, 'UPDATE', 'productos', 1, '{\"estado\":\"1\",\"id_tenants\":1,\"id_productos\":\"1\",\"codigo_interno\":\"TIN-001\",\"codigo_barras\":\"7501234560001\",\"precio_venta\":\"35\",\"nombre_producto\":\"Tinte L\'Oréal Majirel N°7\",\"visible_storefront\":\"1\",\"id_categorias_productos\":1,\"id_marcas\":1,\"descripcion\":\"Tinte permanente rubio medio con cobertura 100%\",\"tipo_producto\":\"tinte\",\"presentacion\":\"tubo\",\"contenido_neto\":\"50ml\",\"img_url\":\"/uploads/9235b42fe1f94a1fbb22e50875dad834.jpg\",\"precio_costo\":\"20\",\"stock_minimo\":\"10\",\"stock_critico\":\"5\",\"stock_actual\":\"25\",\"etiqueta_especial\":null,\"margen_ganancia\":null}', '{\"estado\":\"1\",\"id_tenants\":1,\"id_productos\":\"1\",\"codigo_interno\":\"TIN-001\",\"codigo_barras\":\"7501234560001\",\"precio_venta\":\"35\",\"nombre_producto\":\"Tinte L\'Oréal Majirel N°7\",\"visible_storefront\":\"1\",\"id_categorias_productos\":1,\"id_marcas\":1,\"descripcion\":\"Tinte permanente rubio medio con cobertura 100%\",\"tipo_producto\":\"tinte\",\"presentacion\":\"tubo\",\"contenido_neto\":\"50ml\",\"img_url\":\"/uploads/9235b42fe1f94a1fbb22e50875dad834.jpg\",\"precio_costo\":\"20\",\"stock_minimo\":\"10\",\"stock_critico\":\"5\",\"stock_actual\":\"25\",\"etiqueta_especial\":null,\"margen_ganancia\":null}', '38.250.151.163', '2026-07-10 07:08:48');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `branding_negocio`
--

CREATE TABLE `branding_negocio` (
  `id_branding_negocio` int(11) NOT NULL,
  `id_tenants` int(11) NOT NULL,
  `logo_url` varchar(255) DEFAULT NULL,
  `color_primario` varchar(7) DEFAULT NULL,
  `color_secundario` varchar(7) DEFAULT NULL,
  `nombre_visible` varchar(255) DEFAULT NULL,
  `redes_sociales` longtext CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL CHECK (json_valid(`redes_sociales`))
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `branding_negocio`
--

INSERT INTO `branding_negocio` (`id_branding_negocio`, `id_tenants`, `logo_url`, `color_primario`, `color_secundario`, `nombre_visible`, `redes_sociales`) VALUES
(1, 1, 'https://cdn.bellarista.pe/logos/bellarista.png', '#FF6B9D', '#FFFFFF', 'Bellarista', '{\"instagram\":\"@bellarista\",\"facebook\":\"BellaristaPe\"}'),
(16, 2, 'https://picsum.photos/200/200', '#FF5733', '#C70039', 'Negocio Tenant 2', NULL),
(17, 3, 'https://picsum.photos/200/200', '#FF5733', '#C70039', 'Negocio Tenant 3', NULL),
(18, 4, 'https://picsum.photos/200/200', '#FF5733', '#C70039', 'Negocio Tenant 4', NULL),
(19, 5, 'https://picsum.photos/200/200', '#FF5733', '#C70039', 'Negocio Tenant 5', NULL);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `caja_chica`
--

CREATE TABLE `caja_chica` (
  `id_caja_chica` int(11) NOT NULL,
  `id_sesiones_caja` int(11) NOT NULL,
  `concepto` varchar(255) DEFAULT NULL,
  `monto` decimal(10,2) DEFAULT NULL,
  `descripcion` text DEFAULT NULL,
  `comprobante_url` varchar(255) DEFAULT NULL,
  `id_usuarios` int(11) DEFAULT NULL,
  `fecha_registro` timestamp NULL DEFAULT current_timestamp()
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `caja_chica`
--

INSERT INTO `caja_chica` (`id_caja_chica`, `id_sesiones_caja`, `concepto`, `monto`, `descripcion`, `comprobante_url`, `id_usuarios`, `fecha_registro`) VALUES
(1, 1, 'Compra de útiles de limpieza', 35.00, 'Escoba, recogedor y lejía para el local', NULL, 2, '2026-06-02 02:52:12'),
(2, 1, 'Pasajes para trámite', 15.00, 'Movilidad para renovar licencia municipal', NULL, 1, '2026-06-02 02:52:12'),
(3, 1, 'Impresión de facturas', 12.50, 'Resma de papel bond A4 y tóner para impresora', NULL, 2, '2026-06-02 02:52:12');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `categorias_productos`
--

CREATE TABLE `categorias_productos` (
  `id_categorias_productos` int(11) NOT NULL,
  `id_tenants` int(11) NOT NULL,
  `nombre_categoria_producto` varchar(100) NOT NULL,
  `descripcion` text DEFAULT NULL,
  `imagen_url` varchar(255) DEFAULT NULL,
  `orden` int(11) DEFAULT NULL,
  `estado` tinyint(1) DEFAULT 1
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `categorias_productos`
--

INSERT INTO `categorias_productos` (`id_categorias_productos`, `id_tenants`, `nombre_categoria_producto`, `descripcion`, `imagen_url`, `orden`, `estado`) VALUES
(1, 1, 'Tintes y Coloración', 'Tintes, decolorantes y accesorios de color', NULL, 1, 1),
(2, 1, 'Cuidado Capilar', 'Shampoos, acondicionadores y tratamientos', NULL, 2, 1),
(3, 1, 'Nail Art', 'Esmaltes, gel UV y accesorios para uñas', NULL, 3, 1),
(4, 1, 'Holaaa', 'Hermanito lindo\n', NULL, 4, 1),
(41, 2, 'Categoría Prod 1 Tenant 2', 'Descripción de categoría de producto 1', NULL, 1, 1),
(42, 2, 'Categoría Prod 2 Tenant 2', 'Descripción de categoría de producto 2', NULL, 2, 1),
(43, 2, 'Categoría Prod 3 Tenant 2', 'Descripción de categoría de producto 3', NULL, 3, 1),
(44, 3, 'Categoría Prod 1 Tenant 3', 'Descripción de categoría de producto 1', NULL, 1, 1),
(45, 3, 'Categoría Prod 2 Tenant 3', 'Descripción de categoría de producto 2', NULL, 2, 1),
(46, 3, 'Categoría Prod 3 Tenant 3', 'Descripción de categoría de producto 3', NULL, 3, 1),
(47, 4, 'Categoría Prod 1 Tenant 4', 'Descripción de categoría de producto 1', NULL, 1, 1),
(48, 4, 'Categoría Prod 2 Tenant 4', 'Descripción de categoría de producto 2', NULL, 2, 1),
(49, 4, 'Categoría Prod 3 Tenant 4', 'Descripción de categoría de producto 3', NULL, 3, 1),
(50, 5, 'Categoría Prod 1 Tenant 5', 'Descripción de categoría de producto 1', NULL, 1, 1),
(51, 5, 'Categoría Prod 2 Tenant 5', 'Descripción de categoría de producto 2', NULL, 2, 1),
(52, 5, 'Categoría Prod 3 Tenant 5', 'Descripción de categoría de producto 3', NULL, 3, 1);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `categorias_servicios`
--

CREATE TABLE `categorias_servicios` (
  `id_categorias_servicios` int(11) NOT NULL,
  `id_tenants` int(11) NOT NULL,
  `nombre_categoria_servicio` varchar(100) NOT NULL,
  `descripcion` text DEFAULT NULL,
  `estado` tinyint(1) DEFAULT 1
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `categorias_servicios`
--

INSERT INTO `categorias_servicios` (`id_categorias_servicios`, `id_tenants`, `nombre_categoria_servicio`, `descripcion`, `estado`) VALUES
(1, 1, 'Corte y Peinado', 'Servicios de corte, lavado y peinado', 1),
(2, 1, 'Colorimetría', 'Tintes, mechas, balayage y decoloración', 1),
(3, 1, 'Tratamientos', 'Keratina, hidratación profunda y botox capilar', 1),
(40, 2, 'Categoría Serv 1 Tenant 2', 'Descripción de categoría de servicio 1', 1),
(41, 2, 'Categoría Serv 2 Tenant 2', 'Descripción de categoría de servicio 2', 1),
(42, 2, 'Categoría Serv 3 Tenant 2', 'Descripción de categoría de servicio 3', 1),
(43, 3, 'Categoría Serv 1 Tenant 3', 'Descripción de categoría de servicio 1', 1),
(44, 3, 'Categoría Serv 2 Tenant 3', 'Descripción de categoría de servicio 2', 1),
(45, 3, 'Categoría Serv 3 Tenant 3', 'Descripción de categoría de servicio 3', 1),
(46, 4, 'Categoría Serv 1 Tenant 4', 'Descripción de categoría de servicio 1', 1),
(47, 4, 'Categoría Serv 2 Tenant 4', 'Descripción de categoría de servicio 2', 1),
(48, 4, 'Categoría Serv 3 Tenant 4', 'Descripción de categoría de servicio 3', 1),
(49, 5, 'Categoría Serv 1 Tenant 5', 'Descripción de categoría de servicio 1', 1),
(50, 5, 'Categoría Serv 2 Tenant 5', 'Descripción de categoría de servicio 2', 1),
(51, 5, 'Categoría Serv 3 Tenant 5', 'Descripción de categoría de servicio 3', 1);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `citas`
--

CREATE TABLE `citas` (
  `id_citas` int(11) NOT NULL,
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
  `id_ventas` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `citas`
--

INSERT INTO `citas` (`id_citas`, `id_tenants`, `id_sedes`, `id_clientes`, `id_usuarios_especialista`, `fecha_cita`, `hora_inicio`, `hora_fin`, `duracion_minutos`, `estado`, `observaciones`, `id_usuarios_usuario_creacion`, `id_ventas`) VALUES
(1, 1, 1, 1, 1, '2026-06-05', '10:00:00', '11:00:00', 60, 1, 'Cliente con cabello teñido previamente', 2, NULL),
(2, 1, 1, 2, 1, '2026-06-05', '11:30:00', '14:00:00', 150, 1, 'Mechas californianas + hidratación', 1, NULL),
(3, 1, 2, 3, 1, '2026-06-06', '09:00:00', '10:30:00', 90, 1, NULL, 1, NULL),
(28, 2, 28, 29, 28, '2026-07-05', '10:00:00', '11:00:00', 60, 1, 'Reserva generada para tratamiento capilar.', 28, NULL),
(29, 2, 29, 30, 29, '2026-07-06', '10:00:00', '11:00:00', 60, 1, 'Reserva generada para tratamiento capilar.', 29, NULL),
(30, 2, 30, 31, 30, '2026-07-07', '10:00:00', '11:00:00', 60, 1, 'Reserva generada para tratamiento capilar.', 30, NULL),
(31, 3, 31, 32, 31, '2026-07-05', '10:00:00', '11:00:00', 60, 1, 'Reserva generada para tratamiento capilar.', 31, NULL),
(32, 3, 32, 33, 32, '2026-07-06', '10:00:00', '11:00:00', 60, 1, 'Reserva generada para tratamiento capilar.', 32, NULL),
(33, 3, 33, 34, 33, '2026-07-07', '10:00:00', '11:00:00', 60, 1, 'Reserva generada para tratamiento capilar.', 33, NULL),
(34, 4, 34, 35, 34, '2026-07-05', '10:00:00', '11:00:00', 60, 1, 'Reserva generada para tratamiento capilar.', 34, NULL),
(35, 4, 35, 36, 35, '2026-07-06', '10:00:00', '11:00:00', 60, 1, 'Reserva generada para tratamiento capilar.', 35, NULL),
(36, 4, 36, 37, 36, '2026-07-07', '10:00:00', '11:00:00', 60, 1, 'Reserva generada para tratamiento capilar.', 36, NULL),
(37, 5, 37, 38, 37, '2026-07-05', '10:00:00', '11:00:00', 60, 1, 'Reserva generada para tratamiento capilar.', 37, NULL),
(38, 5, 38, 39, 38, '2026-07-06', '10:00:00', '11:00:00', 60, 1, 'Reserva generada para tratamiento capilar.', 38, NULL),
(39, 5, 39, 40, 39, '2026-07-07', '10:00:00', '11:00:00', 60, 1, 'Reserva generada para tratamiento capilar.', 39, NULL),
(40, 1, 1, 4, NULL, '2026-07-12', '10:30:00', '11:15:00', 45, 1, '', NULL, 48),
(43, 1, 1, 4, NULL, '2026-09-13', '12:00:00', '14:00:00', 120, 1, '', NULL, 51),
(44, 1, 1, 4, NULL, '2026-07-13', '18:00:00', '19:30:00', 90, 1, '', NULL, NULL);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `clientes`
--

CREATE TABLE `clientes` (
  `id_clientes` int(11) NOT NULL,
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
  `fecha_registro` timestamp NULL DEFAULT current_timestamp()
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `clientes`
--

INSERT INTO `clientes` (`id_clientes`, `id_tenants`, `nombre_cliente`, `apellidos_clientes`, `tipo_documento`, `numero_documento`, `telefono`, `correo`, `direccion`, `distrito`, `tipo_cliente`, `estado`, `fecha_registro`) VALUES
(1, 1, 'Lucía', 'Paredes Ruiz', 'DNI', '48231456', '987654321', 'lucia.paredes@gmail.com', 'Av. Brasil 123', 'Pueblo Libre', 'frecuente', 1, '2026-06-02 02:52:12'),
(2, 1, 'Martina', 'Díaz Sánchez', 'DNI', '49876543', '976543210', 'martina.diaz@hotmail.com', 'Calle Los Robles 45', 'Miraflores', 'vip', 1, '2026-06-02 02:52:12'),
(3, 1, 'Carmen', 'Huanca Flores', 'DNI', '47321098', '965432109', NULL, 'Jr. Los Pinos 67', 'San Borja', 'regular', 1, '2026-06-02 02:52:12'),
(4, 1, 'Brayam', 'Arista Fernández', 'DNI', '71490956', '71490956', 'brayamaristafrndz@gmail.com', 'Jr. Sinchi Roca Cdra. 2', 'Morales', 'regular', 1, '2026-07-03 05:42:33'),
(29, 2, 'Cliente 1 Tenant 2', 'Apellidos 1', 'DNI', '12345621', '912345671', 'cliente1.t2@example.com', 'Dirección Cliente 1', 'Distrito 1', 'regular', 1, '2026-07-05 01:39:13'),
(30, 2, 'Cliente 2 Tenant 2', 'Apellidos 2', 'DNI', '12345622', '912345672', 'cliente2.t2@example.com', 'Dirección Cliente 2', 'Distrito 2', 'frecuente', 1, '2026-07-05 01:39:13'),
(31, 2, 'Cliente 3 Tenant 2', 'Apellidos 3', 'DNI', '12345623', '912345673', 'cliente3.t2@example.com', 'Dirección Cliente 3', 'Distrito 3', 'vip', 1, '2026-07-05 01:39:13'),
(32, 3, 'Cliente 1 Tenant 3', 'Apellidos 1', 'DNI', '12345631', '912345671', 'cliente1.t3@example.com', 'Dirección Cliente 1', 'Distrito 1', 'regular', 1, '2026-07-05 01:39:13'),
(33, 3, 'Cliente 2 Tenant 3', 'Apellidos 2', 'DNI', '12345632', '912345672', 'cliente2.t3@example.com', 'Dirección Cliente 2', 'Distrito 2', 'frecuente', 1, '2026-07-05 01:39:13'),
(34, 3, 'Cliente 3 Tenant 3', 'Apellidos 3', 'DNI', '12345633', '912345673', 'cliente3.t3@example.com', 'Dirección Cliente 3', 'Distrito 3', 'vip', 1, '2026-07-05 01:39:13'),
(35, 4, 'Cliente 1 Tenant 4', 'Apellidos 1', 'DNI', '12345641', '912345671', 'cliente1.t4@example.com', 'Dirección Cliente 1', 'Distrito 1', 'regular', 1, '2026-07-05 01:39:14'),
(36, 4, 'Cliente 2 Tenant 4', 'Apellidos 2', 'DNI', '12345642', '912345672', 'cliente2.t4@example.com', 'Dirección Cliente 2', 'Distrito 2', 'frecuente', 1, '2026-07-05 01:39:14'),
(37, 4, 'Cliente 3 Tenant 4', 'Apellidos 3', 'DNI', '12345643', '912345673', 'cliente3.t4@example.com', 'Dirección Cliente 3', 'Distrito 3', 'vip', 1, '2026-07-05 01:39:14'),
(38, 5, 'Cliente 1 Tenant 5', 'Apellidos 1', 'DNI', '12345651', '912345671', 'cliente1.t5@example.com', 'Dirección Cliente 1', 'Distrito 1', 'regular', 1, '2026-07-05 01:39:14'),
(39, 5, 'Cliente 2 Tenant 5', 'Apellidos 2', 'DNI', '12345652', '912345672', 'cliente2.t5@example.com', 'Dirección Cliente 2', 'Distrito 2', 'frecuente', 1, '2026-07-05 01:39:14'),
(40, 5, 'Cliente 3 Tenant 5', 'Apellidos 3', 'DNI', '12345653', '912345673', 'cliente3.t5@example.com', 'Dirección Cliente 3', 'Distrito 3', 'vip', 1, '2026-07-05 01:39:14');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `combos_promocionales`
--

CREATE TABLE `combos_promocionales` (
  `id_combos_promocionales` int(11) NOT NULL,
  `id_tenants` int(11) NOT NULL,
  `nombre_promocion` varchar(255) NOT NULL,
  `descripcion` text DEFAULT NULL,
  `precio_combo` decimal(10,2) DEFAULT NULL,
  `precio_original` decimal(10,2) DEFAULT NULL,
  `fecha_inicio` date DEFAULT NULL,
  `fecha_fin` date DEFAULT NULL,
  `visible_storefront` tinyint(1) DEFAULT 1,
  `estado` tinyint(1) DEFAULT 1
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `combos_promocionales`
--

INSERT INTO `combos_promocionales` (`id_combos_promocionales`, `id_tenants`, `nombre_promocion`, `descripcion`, `precio_combo`, `precio_original`, `fecha_inicio`, `fecha_fin`, `visible_storefront`, `estado`) VALUES
(1, 1, 'Kit Coloración Completo Test', 'Tinte + Oxigenada + Shampoo neutro', 89.90, 115.00, '2026-06-01', '2026-06-30', 1, 0),
(2, 1, 'Pack Cuidado Capilar', 'Shampoo + Acondicionador + Mascarilla', 75.00, 99.00, '2026-06-01', '2026-06-30', 1, 0),
(3, 1, 'Combo Nail Art Starter', 'Esmalte gel + lámpara UV + lima profesional', 65.00, 85.00, '2026-06-01', '2026-06-30', 1, 0),
(28, 2, 'Combo Promocional 1 T2', 'Gran descuento en pack de productos 1', 45.00, 60.00, '2026-07-05', '2026-08-05', 1, 1),
(29, 2, 'Combo Promocional 2 T2', 'Gran descuento en pack de productos 2', 90.00, 120.00, '2026-07-05', '2026-08-05', 1, 1),
(30, 2, 'Combo Promocional 3 T2', 'Gran descuento en pack de productos 3', 135.00, 180.00, '2026-07-05', '2026-08-05', 1, 1),
(31, 3, 'Combo Promocional 1 T3', 'Gran descuento en pack de productos 1', 45.00, 60.00, '2026-07-05', '2026-08-05', 1, 1),
(32, 3, 'Combo Promocional 2 T3', 'Gran descuento en pack de productos 2', 90.00, 120.00, '2026-07-05', '2026-08-05', 1, 1),
(33, 3, 'Combo Promocional 3 T3', 'Gran descuento en pack de productos 3', 135.00, 180.00, '2026-07-05', '2026-08-05', 1, 1),
(34, 4, 'Combo Promocional 1 T4', 'Gran descuento en pack de productos 1', 45.00, 60.00, '2026-07-05', '2026-08-05', 1, 1),
(35, 4, 'Combo Promocional 2 T4', 'Gran descuento en pack de productos 2', 90.00, 120.00, '2026-07-05', '2026-08-05', 1, 1),
(36, 4, 'Combo Promocional 3 T4', 'Gran descuento en pack de productos 3', 135.00, 180.00, '2026-07-05', '2026-08-05', 1, 1),
(37, 5, 'Combo Promocional 1 T5', 'Gran descuento en pack de productos 1', 45.00, 60.00, '2026-07-05', '2026-08-05', 1, 1),
(38, 5, 'Combo Promocional 2 T5', 'Gran descuento en pack de productos 2', 90.00, 120.00, '2026-07-05', '2026-08-05', 1, 1),
(39, 5, 'Combo Promocional 3 T5', 'Gran descuento en pack de productos 3', 135.00, 180.00, '2026-07-05', '2026-08-05', 1, 1),
(41, 1, 'Test combo', 'Hola ', 30.00, 45.00, '2026-07-08', '2026-07-15', 1, 1);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `composicion_combo`
--

CREATE TABLE `composicion_combo` (
  `id_composicion_combo` int(11) NOT NULL,
  `id_combos_promocionales` int(11) NOT NULL,
  `id_productos` int(11) NOT NULL,
  `cantidad` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `composicion_combo`
--

INSERT INTO `composicion_combo` (`id_composicion_combo`, `id_combos_promocionales`, `id_productos`, `cantidad`) VALUES
(1, 1, 1, 1),
(2, 2, 2, 1),
(3, 3, 3, 1),
(4, 41, 1, 1),
(5, 41, 4, 1);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `comprobantes_electronicos`
--

CREATE TABLE `comprobantes_electronicos` (
  `id_comprobantes_electronicos` int(11) NOT NULL,
  `id_tenants` int(11) NOT NULL,
  `tipo_comprobante` enum('boleta','factura','nota_credito','nota_debito') NOT NULL,
  `numero_serie` varchar(10) DEFAULT NULL,
  `numero_comprobante` varchar(10) DEFAULT NULL,
  `id_ventas` int(11) DEFAULT NULL,
  `estado_sunat` enum('aceptada','observada','rechazada','pendiente_envio') DEFAULT 'pendiente_envio',
  `respuesta_sunat` text DEFAULT NULL,
  `fecha_envio_sunat` timestamp NULL DEFAULT NULL,
  `xml_generado` longtext DEFAULT NULL,
  `cdr_recibida` longtext DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `comprobantes_electronicos`
--

INSERT INTO `comprobantes_electronicos` (`id_comprobantes_electronicos`, `id_tenants`, `tipo_comprobante`, `numero_serie`, `numero_comprobante`, `id_ventas`, `estado_sunat`, `respuesta_sunat`, `fecha_envio_sunat`, `xml_generado`, `cdr_recibida`) VALUES
(1, 1, 'boleta', 'B001', '00001', 1, 'aceptada', NULL, NULL, NULL, NULL),
(2, 1, 'boleta', 'B001', '00002', 2, 'pendiente_envio', NULL, NULL, NULL, NULL),
(3, 1, 'factura', 'F001', '00001', 3, 'pendiente_envio', NULL, NULL, NULL, NULL),
(4, 1, 'boleta', 'B001', '00003', 1, 'pendiente_envio', NULL, NULL, NULL, NULL),
(29, 2, 'boleta', 'S002', '00001', 36, 'aceptada', NULL, NULL, NULL, NULL),
(30, 2, 'factura', 'S002', '00002', 37, 'aceptada', NULL, NULL, NULL, NULL),
(31, 2, 'boleta', 'S002', '00003', 38, 'aceptada', NULL, NULL, NULL, NULL),
(32, 3, 'boleta', 'S003', '00001', 39, 'aceptada', NULL, NULL, NULL, NULL),
(33, 3, 'factura', 'S003', '00002', 40, 'aceptada', NULL, NULL, NULL, NULL),
(34, 3, 'boleta', 'S003', '00003', 41, 'aceptada', NULL, NULL, NULL, NULL),
(35, 4, 'boleta', 'S004', '00001', 42, 'aceptada', NULL, NULL, NULL, NULL),
(36, 4, 'factura', 'S004', '00002', 43, 'aceptada', NULL, NULL, NULL, NULL),
(37, 4, 'boleta', 'S004', '00003', 44, 'aceptada', NULL, NULL, NULL, NULL),
(38, 5, 'boleta', 'S005', '00001', 45, 'aceptada', NULL, NULL, NULL, NULL),
(39, 5, 'factura', 'S005', '00002', 46, 'aceptada', NULL, NULL, NULL, NULL),
(40, 5, 'boleta', 'S005', '00003', 47, 'aceptada', NULL, NULL, NULL, NULL);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `configuracion_global`
--

CREATE TABLE `configuracion_global` (
  `id_configuracion_global` int(11) NOT NULL,
  `id_tenants` int(11) NOT NULL,
  `clave` varchar(100) NOT NULL,
  `valor` text DEFAULT NULL,
  `tipo_valor` enum('string','numerico','booleano','json') DEFAULT 'string',
  `descripcion` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `configuracion_global`
--

INSERT INTO `configuracion_global` (`id_configuracion_global`, `id_tenants`, `clave`, `valor`, `tipo_valor`, `descripcion`) VALUES
(1, 1, 'igv_porcentaje', '18', 'numerico', 'Porcentaje de IGV aplicado en ventas'),
(2, 1, 'moneda', 'PEN', 'string', 'Moneda principal del sistema'),
(3, 1, 'max_descuento_cajero', '10', 'numerico', 'Descuento máximo que puede aplicar un cajero (%)'),
(40, 2, 'MONEDA', 'PEN', 'string', 'Moneda por defecto del sistema'),
(41, 2, 'IGV', '18.00', 'numerico', 'Porcentaje de impuesto general a las ventas'),
(42, 2, 'MODO_PRUEBAS', 'true', 'booleano', 'Define si la integración de SUNAT está en pruebas'),
(43, 3, 'MONEDA', 'PEN', 'string', 'Moneda por defecto del sistema'),
(44, 3, 'IGV', '18.00', 'numerico', 'Porcentaje de impuesto general a las ventas'),
(45, 3, 'MODO_PRUEBAS', 'true', 'booleano', 'Define si la integración de SUNAT está en pruebas'),
(46, 4, 'MONEDA', 'PEN', 'string', 'Moneda por defecto del sistema'),
(47, 4, 'IGV', '18.00', 'numerico', 'Porcentaje de impuesto general a las ventas'),
(48, 4, 'MODO_PRUEBAS', 'true', 'booleano', 'Define si la integración de SUNAT está en pruebas'),
(49, 5, 'MONEDA', 'PEN', 'string', 'Moneda por defecto del sistema'),
(50, 5, 'IGV', '18.00', 'numerico', 'Porcentaje de impuesto general a las ventas'),
(51, 5, 'MODO_PRUEBAS', 'true', 'booleano', 'Define si la integración de SUNAT está en pruebas');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `cuentas_por_pagar`
--

CREATE TABLE `cuentas_por_pagar` (
  `id_cuentas_por_pagar` int(11) NOT NULL,
  `id_tenants` int(11) NOT NULL,
  `id_proveedores` int(11) NOT NULL,
  `documento_numero` varchar(50) DEFAULT NULL,
  `tipo_documento` enum('factura','boleta','orden_compra') DEFAULT 'factura',
  `monto` decimal(10,2) DEFAULT NULL,
  `fecha_documento` date DEFAULT NULL,
  `fecha_vencimiento` date DEFAULT NULL,
  `estado_pago` enum('pendiente','pagada') DEFAULT 'pendiente'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `cuentas_por_pagar`
--

INSERT INTO `cuentas_por_pagar` (`id_cuentas_por_pagar`, `id_tenants`, `id_proveedores`, `documento_numero`, `tipo_documento`, `monto`, `fecha_documento`, `fecha_vencimiento`, `estado_pago`) VALUES
(1, 1, 1, 'FAC-00341', 'factura', 540.00, '2026-05-27', '2026-06-27', 'pendiente'),
(2, 1, 2, 'BOL-00892', 'boleta', 320.00, '2026-05-29', '2026-06-29', 'pendiente'),
(3, 1, 3, 'FAC-00115', 'factura', 210.00, '2026-06-08', '2026-07-08', 'pendiente'),
(28, 2, 28, 'DOC-CPP-2-0', 'factura', 300.00, '2026-07-05', '2026-08-04', 'pendiente'),
(29, 2, 29, 'DOC-CPP-2-1', 'factura', 600.00, '2026-07-05', '2026-08-04', 'pendiente'),
(30, 2, 30, 'DOC-CPP-2-2', 'factura', 900.00, '2026-07-05', '2026-08-04', 'pendiente'),
(31, 3, 31, 'DOC-CPP-3-0', 'factura', 300.00, '2026-07-05', '2026-08-04', 'pendiente'),
(32, 3, 32, 'DOC-CPP-3-1', 'factura', 600.00, '2026-07-05', '2026-08-04', 'pendiente'),
(33, 3, 33, 'DOC-CPP-3-2', 'factura', 900.00, '2026-07-05', '2026-08-04', 'pendiente'),
(34, 4, 34, 'DOC-CPP-4-0', 'factura', 300.00, '2026-07-05', '2026-08-04', 'pendiente'),
(35, 4, 35, 'DOC-CPP-4-1', 'factura', 600.00, '2026-07-05', '2026-08-04', 'pendiente'),
(36, 4, 36, 'DOC-CPP-4-2', 'factura', 900.00, '2026-07-05', '2026-08-04', 'pendiente'),
(37, 5, 37, 'DOC-CPP-5-0', 'factura', 300.00, '2026-07-05', '2026-08-04', 'pendiente'),
(38, 5, 38, 'DOC-CPP-5-1', 'factura', 600.00, '2026-07-05', '2026-08-04', 'pendiente'),
(39, 5, 39, 'DOC-CPP-5-2', 'factura', 900.00, '2026-07-05', '2026-08-04', 'pendiente');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `detalle_devolucion_proveedor`
--

CREATE TABLE `detalle_devolucion_proveedor` (
  `id_detalle_devolucion_proveedor` int(11) NOT NULL,
  `id_devoluciones_proveedor` int(11) NOT NULL,
  `id_productos` int(11) NOT NULL,
  `id_lotes_inventario` int(11) DEFAULT NULL,
  `cantidad` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `detalle_devolucion_proveedor`
--

INSERT INTO `detalle_devolucion_proveedor` (`id_detalle_devolucion_proveedor`, `id_devoluciones_proveedor`, `id_productos`, `id_lotes_inventario`, `cantidad`) VALUES
(1, 1, 1, 1, 3),
(2, 2, 2, 2, 1),
(3, 3, 3, 3, 2);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `detalle_devolucion_venta`
--

CREATE TABLE `detalle_devolucion_venta` (
  `id_detalle_devolucion_venta` int(11) NOT NULL,
  `id_devoluciones_venta` int(11) NOT NULL,
  `id_productos` int(11) NOT NULL,
  `cantidad` int(11) DEFAULT NULL,
  `precio_unitario` decimal(10,2) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `detalle_devolucion_venta`
--

INSERT INTO `detalle_devolucion_venta` (`id_detalle_devolucion_venta`, `id_devoluciones_venta`, `id_productos`, `cantidad`, `precio_unitario`) VALUES
(1, 1, 1, 1, 35.00),
(2, 2, 2, 1, 42.00),
(3, 3, 3, 1, 28.00);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `detalle_orden_compra`
--

CREATE TABLE `detalle_orden_compra` (
  `id_detalle_orden_compra` int(11) NOT NULL,
  `id_ordenes_compra` int(11) NOT NULL,
  `id_productos` int(11) NOT NULL,
  `cantidad_solicitada` int(11) DEFAULT NULL,
  `cantidad_recibida` int(11) DEFAULT 0,
  `precio_unitario` decimal(10,2) DEFAULT NULL,
  `subtotal` decimal(10,2) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `detalle_orden_compra`
--

INSERT INTO `detalle_orden_compra` (`id_detalle_orden_compra`, `id_ordenes_compra`, `id_productos`, `cantidad_solicitada`, `cantidad_recibida`, `precio_unitario`, `subtotal`) VALUES
(1, 1, 1, 30, 30, 18.00, 540.00),
(2, 2, 2, 20, 20, 16.00, 320.00),
(3, 3, 3, 30, 30, 7.00, 210.00);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `detalle_pedido`
--

CREATE TABLE `detalle_pedido` (
  `id_detalle_pedido` int(11) NOT NULL,
  `id_pedidos` int(11) NOT NULL,
  `id_productos` int(11) NOT NULL,
  `cantidad` int(11) DEFAULT NULL,
  `precio_unitario` decimal(10,2) DEFAULT NULL,
  `descuento` decimal(10,2) DEFAULT 0.00,
  `subtotal` decimal(10,2) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `detalle_pedido`
--

INSERT INTO `detalle_pedido` (`id_detalle_pedido`, `id_pedidos`, `id_productos`, `cantidad`, `precio_unitario`, `descuento`, `subtotal`) VALUES
(1, 1, 1, 1, 35.00, 0.00, 35.00),
(2, 2, 2, 1, 42.00, 0.00, 42.00),
(3, 3, 3, 1, 28.00, 0.00, 28.00);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `detalle_venta`
--

CREATE TABLE `detalle_venta` (
  `id_detalle_venta` int(11) NOT NULL,
  `id_ventas` int(11) NOT NULL,
  `id_productos` int(11) DEFAULT NULL,
  `cantidad` int(11) DEFAULT NULL,
  `precio_unitario` decimal(10,2) DEFAULT NULL,
  `descuento` decimal(10,2) DEFAULT 0.00,
  `subtotal` decimal(10,2) DEFAULT NULL,
  `id_lotes_inventario` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `detalle_venta`
--

INSERT INTO `detalle_venta` (`id_detalle_venta`, `id_ventas`, `id_productos`, `cantidad`, `precio_unitario`, `descuento`, `subtotal`, `id_lotes_inventario`) VALUES
(1, 1, 1, 1, 35.00, 0.00, 35.00, 1),
(2, 2, 2, 1, 42.00, 0.00, 42.00, 2),
(3, 3, 3, 1, 28.00, 0.00, 28.00, 3),
(4, 5, 2, 15, 42.00, 0.00, 630.00, NULL),
(5, 6, 4, 4, 10.00, 0.00, 40.00, NULL),
(6, 7, 4, 4, 10.00, 0.00, 40.00, NULL),
(7, 8, 3, 2, 28.00, 0.00, 56.00, NULL),
(8, 9, 1, 5, 35.00, 0.00, 175.00, NULL),
(9, 10, 4, 1, 10.00, 0.00, 10.00, NULL),
(10, 11, 1, 1, 35.00, 0.00, 35.00, NULL),
(35, 36, 29, 2, 15.00, 0.00, 30.00, NULL),
(36, 37, 30, 2, 30.00, 0.00, 60.00, NULL),
(37, 38, 31, 2, 45.00, 0.00, 90.00, NULL),
(38, 39, 32, 2, 15.00, 0.00, 30.00, NULL),
(39, 40, 33, 2, 30.00, 0.00, 60.00, NULL),
(40, 41, 34, 2, 45.00, 0.00, 90.00, NULL),
(41, 42, 35, 2, 15.00, 0.00, 30.00, NULL),
(42, 43, 36, 2, 30.00, 0.00, 60.00, NULL),
(43, 44, 37, 2, 45.00, 0.00, 90.00, NULL),
(44, 45, 38, 2, 15.00, 0.00, 30.00, NULL),
(45, 46, 39, 2, 30.00, 0.00, 60.00, NULL),
(46, 47, 40, 2, 45.00, 0.00, 90.00, NULL),
(47, 52, 1, 5, 35.00, 0.00, 175.00, NULL),
(48, 53, 1, 3, 23.33, 0.00, 70.00, NULL),
(49, 53, 4, 3, 6.67, 0.00, 20.00, NULL),
(51, 55, 1, 5, 35.00, 0.00, 175.00, NULL),
(52, 56, 1, 3, 35.00, 0.00, 105.00, NULL),
(53, 57, 1, 5, 35.00, 0.00, 175.00, 1);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `devoluciones_proveedor`
--

CREATE TABLE `devoluciones_proveedor` (
  `id_devoluciones_proveedor` int(11) NOT NULL,
  `id_tenants` int(11) NOT NULL,
  `id_proveedores` int(11) NOT NULL,
  `numero_devolucion` varchar(50) DEFAULT NULL,
  `fecha_devolucion` date DEFAULT NULL,
  `motivo` varchar(255) DEFAULT NULL,
  `observaciones` text DEFAULT NULL,
  `estado` tinyint(1) DEFAULT 1
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `devoluciones_proveedor`
--

INSERT INTO `devoluciones_proveedor` (`id_devoluciones_proveedor`, `id_tenants`, `id_proveedores`, `numero_devolucion`, `fecha_devolucion`, `motivo`, `observaciones`, `estado`) VALUES
(1, 1, 1, 'DEV-P-001', '2026-05-15', 'Producto vencido', '3 tubos de tinte con fecha vencida al ingreso', 1),
(2, 1, 2, 'DEV-P-002', '2026-05-20', 'Envase dañado', 'Frasco roto en el embalaje, derrame interno', 1),
(3, 1, 3, 'DEV-P-003', '2026-06-01', 'Producto incorrecto', 'Se recibió código distinto al pedido', 1),
(28, 2, 28, 'DEV-P-2-0', '2026-07-05', 'Lote de tintes vencido antes de fecha estipulada.', NULL, 1),
(29, 2, 29, 'DEV-P-2-1', '2026-07-05', 'Lote de tintes vencido antes de fecha estipulada.', NULL, 1),
(30, 2, 30, 'DEV-P-2-2', '2026-07-05', 'Lote de tintes vencido antes de fecha estipulada.', NULL, 1),
(31, 3, 31, 'DEV-P-3-0', '2026-07-05', 'Lote de tintes vencido antes de fecha estipulada.', NULL, 1),
(32, 3, 32, 'DEV-P-3-1', '2026-07-05', 'Lote de tintes vencido antes de fecha estipulada.', NULL, 1),
(33, 3, 33, 'DEV-P-3-2', '2026-07-05', 'Lote de tintes vencido antes de fecha estipulada.', NULL, 1),
(34, 4, 34, 'DEV-P-4-0', '2026-07-05', 'Lote de tintes vencido antes de fecha estipulada.', NULL, 1),
(35, 4, 35, 'DEV-P-4-1', '2026-07-05', 'Lote de tintes vencido antes de fecha estipulada.', NULL, 1),
(36, 4, 36, 'DEV-P-4-2', '2026-07-05', 'Lote de tintes vencido antes de fecha estipulada.', NULL, 1),
(37, 5, 37, 'DEV-P-5-0', '2026-07-05', 'Lote de tintes vencido antes de fecha estipulada.', NULL, 1),
(38, 5, 38, 'DEV-P-5-1', '2026-07-05', 'Lote de tintes vencido antes de fecha estipulada.', NULL, 1),
(39, 5, 39, 'DEV-P-5-2', '2026-07-05', 'Lote de tintes vencido antes de fecha estipulada.', NULL, 1);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `devoluciones_venta`
--

CREATE TABLE `devoluciones_venta` (
  `id_devoluciones_venta` int(11) NOT NULL,
  `id_tenants` int(11) NOT NULL,
  `id_ventas` int(11) NOT NULL,
  `numero_devolucion` varchar(50) DEFAULT NULL,
  `motivo` varchar(255) DEFAULT NULL,
  `tipo_resolucion` enum('reembolso','cambio_producto','nota_credito') DEFAULT 'reembolso',
  `estado_devolucion` enum('pendiente','aprobada','rechazada','completada') DEFAULT 'pendiente',
  `monto_reembolso` decimal(10,2) DEFAULT NULL,
  `id_usuarios` int(11) DEFAULT NULL,
  `fecha_devolucion` timestamp NULL DEFAULT current_timestamp()
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `devoluciones_venta`
--

INSERT INTO `devoluciones_venta` (`id_devoluciones_venta`, `id_tenants`, `id_ventas`, `numero_devolucion`, `motivo`, `tipo_resolucion`, `estado_devolucion`, `monto_reembolso`, `id_usuarios`, `fecha_devolucion`) VALUES
(1, 1, 1, 'DEV-V-001', 'Producto en mal estado', 'reembolso', 'completada', 35.00, 1, '2026-06-02 02:52:12'),
(2, 1, 2, 'DEV-V-002', 'Talla/presentación incorrecta', 'cambio_producto', 'aprobada', 42.00, 1, '2026-06-02 02:52:12'),
(3, 1, 3, 'DEV-V-003', 'Cliente insatisfecho', 'nota_credito', 'pendiente', 28.00, 1, '2026-06-02 02:52:12'),
(28, 2, 36, 'DEV-V-2-1', 'Producto con falla de empaque', 'cambio_producto', 'aprobada', 10.00, 28, '2026-07-05 01:39:13'),
(29, 2, 37, 'DEV-V-2-2', 'Producto con falla de empaque', 'cambio_producto', 'aprobada', 20.00, 29, '2026-07-05 01:39:13'),
(30, 2, 38, 'DEV-V-2-3', 'Producto con falla de empaque', 'cambio_producto', 'aprobada', 30.00, 30, '2026-07-05 01:39:13'),
(31, 3, 39, 'DEV-V-3-1', 'Producto con falla de empaque', 'cambio_producto', 'aprobada', 10.00, 31, '2026-07-05 01:39:14'),
(32, 3, 40, 'DEV-V-3-2', 'Producto con falla de empaque', 'cambio_producto', 'aprobada', 20.00, 32, '2026-07-05 01:39:14'),
(33, 3, 41, 'DEV-V-3-3', 'Producto con falla de empaque', 'cambio_producto', 'aprobada', 30.00, 33, '2026-07-05 01:39:14'),
(34, 4, 42, 'DEV-V-4-1', 'Producto con falla de empaque', 'cambio_producto', 'aprobada', 10.00, 34, '2026-07-05 01:39:14'),
(35, 4, 43, 'DEV-V-4-2', 'Producto con falla de empaque', 'cambio_producto', 'aprobada', 20.00, 35, '2026-07-05 01:39:14'),
(36, 4, 44, 'DEV-V-4-3', 'Producto con falla de empaque', 'cambio_producto', 'aprobada', 30.00, 36, '2026-07-05 01:39:14'),
(37, 5, 45, 'DEV-V-5-1', 'Producto con falla de empaque', 'cambio_producto', 'aprobada', 10.00, 37, '2026-07-05 01:39:14'),
(38, 5, 46, 'DEV-V-5-2', 'Producto con falla de empaque', 'cambio_producto', 'aprobada', 20.00, 38, '2026-07-05 01:39:14'),
(39, 5, 47, 'DEV-V-5-3', 'Producto con falla de empaque', 'cambio_producto', 'aprobada', 30.00, 39, '2026-07-05 01:39:14');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `facturas_suscripcion`
--

CREATE TABLE `facturas_suscripcion` (
  `id_facturas_suscripcion` int(11) NOT NULL,
  `id_suscripciones` int(11) NOT NULL,
  `numero_factura` varchar(50) DEFAULT NULL,
  `monto` decimal(10,2) DEFAULT NULL,
  `fecha_emision` date DEFAULT NULL,
  `fecha_vencimiento` date DEFAULT NULL,
  `estado_pago` enum('pendiente','pagada','vencida','cancelada') DEFAULT 'pendiente',
  `metodo_pago` varchar(50) DEFAULT NULL,
  `fecha_pago` timestamp NULL DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `facturas_suscripcion`
--

INSERT INTO `facturas_suscripcion` (`id_facturas_suscripcion`, `id_suscripciones`, `numero_factura`, `monto`, `fecha_emision`, `fecha_vencimiento`, `estado_pago`, `metodo_pago`, `fecha_pago`) VALUES
(1, 1, 'FS-2026-0001', 129.90, '2026-01-01', '2026-01-10', 'pagada', 'transferencia', NULL),
(2, 2, 'FS-2026-0002', 49.90, '2026-03-01', '2026-03-10', 'pagada', 'efectivo', NULL),
(3, 3, 'FS-2026-0003', 899.90, '2025-12-01', '2025-12-10', 'pagada', 'transferencia', NULL);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `formas_pago_venta`
--

CREATE TABLE `formas_pago_venta` (
  `id_formas_pago_venta` int(11) NOT NULL,
  `id_ventas` int(11) NOT NULL,
  `tipo_pago` enum('efectivo','tarjeta_credito','tarjeta_debito','transferencia','billetera_digital','credito_tienda') DEFAULT 'efectivo',
  `monto` decimal(10,2) DEFAULT NULL,
  `referencia_transaccion` varchar(100) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `formas_pago_venta`
--

INSERT INTO `formas_pago_venta` (`id_formas_pago_venta`, `id_ventas`, `tipo_pago`, `monto`, `referencia_transaccion`) VALUES
(1, 1, 'efectivo', 35.00, NULL),
(2, 2, 'tarjeta_debito', 42.00, 'TXN-20260601-001'),
(3, 3, 'billetera_digital', 28.00, 'YAPE-20260601-002');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `gastos_operativos`
--

CREATE TABLE `gastos_operativos` (
  `id_gastos_operativos` int(11) NOT NULL,
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
  `fecha_gasto` date DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `gastos_operativos`
--

INSERT INTO `gastos_operativos` (`id_gastos_operativos`, `id_tenants`, `id_sedes`, `concepto`, `categoria`, `monto`, `descripcion`, `id_proveedores`, `comprobante_numero`, `archivo_evidencia`, `estado`, `id_usuarios_usuario_creacion`, `id_usuarios_usuario_aprobacion`, `fecha_gasto`) VALUES
(1, 1, 1, 'Reposición de toallas', 'insumos', 180.00, '12 toallas de microfibra para clientes', NULL, NULL, NULL, 1, 1, NULL, '2026-06-01'),
(2, 1, 1, 'Publicidad en Instagram', 'publicidad', 200.00, 'Campaña pagada semana del 01 al 07 de junio', NULL, NULL, NULL, 1, 1, NULL, '2026-06-01'),
(3, 1, 1, 'Mantenimiento aire acondicionado', 'mantenimiento', 95.00, 'Limpieza y recarga de gas equipo sala principal', NULL, NULL, NULL, 1, 1, NULL, '2026-06-01'),
(28, 2, 28, 'Compra de insumos de estilismo', 'insumos', 150.00, 'Adquisición urgente de tintes y shampoos.', 28, 'FACT-G-0', NULL, 1, 28, 28, '2026-07-05'),
(29, 2, 29, 'Compra de insumos de estilismo', 'insumos', 300.00, 'Adquisición urgente de tintes y shampoos.', 29, 'FACT-G-1', NULL, 1, 29, 29, '2026-07-05'),
(30, 2, 30, 'Compra de insumos de estilismo', 'insumos', 450.00, 'Adquisición urgente de tintes y shampoos.', 30, 'FACT-G-2', NULL, 1, 30, 30, '2026-07-05'),
(31, 3, 31, 'Compra de insumos de estilismo', 'insumos', 150.00, 'Adquisición urgente de tintes y shampoos.', 31, 'FACT-G-0', NULL, 1, 31, 31, '2026-07-05'),
(32, 3, 32, 'Compra de insumos de estilismo', 'insumos', 300.00, 'Adquisición urgente de tintes y shampoos.', 32, 'FACT-G-1', NULL, 1, 32, 32, '2026-07-05'),
(33, 3, 33, 'Compra de insumos de estilismo', 'insumos', 450.00, 'Adquisición urgente de tintes y shampoos.', 33, 'FACT-G-2', NULL, 1, 33, 33, '2026-07-05'),
(34, 4, 34, 'Compra de insumos de estilismo', 'insumos', 150.00, 'Adquisición urgente de tintes y shampoos.', 34, 'FACT-G-0', NULL, 1, 34, 34, '2026-07-05'),
(35, 4, 35, 'Compra de insumos de estilismo', 'insumos', 300.00, 'Adquisición urgente de tintes y shampoos.', 35, 'FACT-G-1', NULL, 1, 35, 35, '2026-07-05'),
(36, 4, 36, 'Compra de insumos de estilismo', 'insumos', 450.00, 'Adquisición urgente de tintes y shampoos.', 36, 'FACT-G-2', NULL, 1, 36, 36, '2026-07-05'),
(37, 5, 37, 'Compra de insumos de estilismo', 'insumos', 150.00, 'Adquisición urgente de tintes y shampoos.', 37, 'FACT-G-0', NULL, 1, 37, 37, '2026-07-05'),
(38, 5, 38, 'Compra de insumos de estilismo', 'insumos', 300.00, 'Adquisición urgente de tintes y shampoos.', 38, 'FACT-G-1', NULL, 1, 38, 38, '2026-07-05'),
(39, 5, 39, 'Compra de insumos de estilismo', 'insumos', 450.00, 'Adquisición urgente de tintes y shampoos.', 39, 'FACT-G-2', NULL, 1, 39, 39, '2026-07-05');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `gastos_recurrentes`
--

CREATE TABLE `gastos_recurrentes` (
  `id_gastos_recurrentes` int(11) NOT NULL,
  `id_tenants` int(11) NOT NULL,
  `id_sedes` int(11) DEFAULT NULL,
  `concepto` varchar(255) DEFAULT NULL,
  `monto` decimal(10,2) DEFAULT NULL,
  `frecuencia` enum('diaria','semanal','quincenal','mensual','trimestral','anual') DEFAULT 'mensual',
  `fecha_proximo_registro` date DEFAULT NULL,
  `estado` tinyint(1) DEFAULT 1
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `gastos_recurrentes`
--

INSERT INTO `gastos_recurrentes` (`id_gastos_recurrentes`, `id_tenants`, `id_sedes`, `concepto`, `monto`, `frecuencia`, `fecha_proximo_registro`, `estado`) VALUES
(1, 1, 1, 'Alquiler local sede central', 3500.00, 'mensual', '2026-07-01', 1),
(2, 1, 2, 'Servicio de electricidad', 420.00, 'mensual', '2026-07-01', 1),
(3, 1, 3, 'Servicio de limpieza', 250.00, 'quincenal', '2026-06-15', 1),
(28, 2, 28, 'Servicio de Internet y Telefonía Sede 1', 120.00, 'mensual', NULL, 1),
(29, 2, 29, 'Servicio de Internet y Telefonía Sede 2', 240.00, 'mensual', NULL, 1),
(30, 2, 30, 'Servicio de Internet y Telefonía Sede 3', 360.00, 'mensual', NULL, 1),
(31, 3, 31, 'Servicio de Internet y Telefonía Sede 1', 120.00, 'mensual', NULL, 1),
(32, 3, 32, 'Servicio de Internet y Telefonía Sede 2', 240.00, 'mensual', NULL, 1),
(33, 3, 33, 'Servicio de Internet y Telefonía Sede 3', 360.00, 'mensual', NULL, 1),
(34, 4, 34, 'Servicio de Internet y Telefonía Sede 1', 120.00, 'mensual', NULL, 1),
(35, 4, 35, 'Servicio de Internet y Telefonía Sede 2', 240.00, 'mensual', NULL, 1),
(36, 4, 36, 'Servicio de Internet y Telefonía Sede 3', 360.00, 'mensual', NULL, 1),
(37, 5, 37, 'Servicio de Internet y Telefonía Sede 1', 120.00, 'mensual', NULL, 1),
(38, 5, 38, 'Servicio de Internet y Telefonía Sede 2', 240.00, 'mensual', NULL, 1),
(39, 5, 39, 'Servicio de Internet y Telefonía Sede 3', 360.00, 'mensual', NULL, 1);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `horarios_operacion`
--

CREATE TABLE `horarios_operacion` (
  `id_horarios_operacion` int(11) NOT NULL,
  `id_sedes` int(11) NOT NULL,
  `dia_semana` enum('lunes','martes','miercoles','jueves','viernes','sabado','domingo') DEFAULT NULL,
  `hora_apertura` time DEFAULT NULL,
  `hora_cierre` time DEFAULT NULL,
  `estado` tinyint(1) DEFAULT 1
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `horarios_operacion`
--

INSERT INTO `horarios_operacion` (`id_horarios_operacion`, `id_sedes`, `dia_semana`, `hora_apertura`, `hora_cierre`, `estado`) VALUES
(1, 1, 'lunes', '09:00:00', '20:00:00', 1),
(2, 1, 'sabado', '09:00:00', '18:00:00', 1),
(3, 1, 'domingo', '10:00:00', '15:00:00', 1);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `lotes_inventario`
--

CREATE TABLE `lotes_inventario` (
  `id_lotes_inventario` int(11) NOT NULL,
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
  `estado` tinyint(1) DEFAULT 1
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `lotes_inventario`
--

INSERT INTO `lotes_inventario` (`id_lotes_inventario`, `id_productos`, `id_almacenes`, `numero_lote`, `cantidad`, `cantidad_disponible`, `precio_unitario`, `fecha_vencimiento`, `id_proveedores`, `fecha_ingreso`, `observaciones`, `estado`) VALUES
(1, 1, 1, 'LOT-2026-001', 50, 25, 18.00, '2027-12-31', 1, '2026-05-27', NULL, 1),
(2, 2, 1, 'LOT-2026-002', 30, 30, 22.00, '2028-06-30', 2, '2026-05-29', NULL, 1),
(3, 3, 1, 'LOT-2026-003', 40, 40, 12.00, '2027-08-31', 3, '2026-06-08', NULL, 1);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `marcas`
--

CREATE TABLE `marcas` (
  `id_marcas` int(11) NOT NULL,
  `id_tenants` int(11) NOT NULL,
  `nombre_marca` varchar(100) NOT NULL,
  `pais_origen` varchar(100) DEFAULT NULL,
  `logo_url` varchar(255) DEFAULT NULL,
  `descripcion` text DEFAULT NULL,
  `estado` tinyint(1) DEFAULT 1
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `marcas`
--

INSERT INTO `marcas` (`id_marcas`, `id_tenants`, `nombre_marca`, `pais_origen`, `logo_url`, `descripcion`, `estado`) VALUES
(1, 1, 'L\'Oréal Professionnel XD', 'Peru', '/uploads/6314de883c0d49d2a656735f4ee3c7e0.jpg', 'Marca líder en productos capilares profesionales', 1),
(2, 1, 'Wella Professionals', 'Alemania', '/uploads/457a0fde9f21406bb1867009d5835772.jpg', 'Coloración y cuidado capilar de alta gama', 1),
(3, 1, 'OPI', 'Estados Unidos', '/uploads/a1f78ed67f6947d391b8156c96d62625.jpg', 'Referente mundial en nail art y esmaltes', 1),
(28, 2, 'Marca 1 Tenant 2', 'Perú', NULL, 'Descripción de marca 1', 1),
(29, 2, 'Marca 2 Tenant 2', 'Perú', NULL, 'Descripción de marca 2', 1),
(30, 2, 'Marca 3 Tenant 2', 'Perú', NULL, 'Descripción de marca 3', 1),
(31, 3, 'Marca 1 Tenant 3', 'Perú', NULL, 'Descripción de marca 1', 1),
(32, 3, 'Marca 2 Tenant 3', 'Perú', NULL, 'Descripción de marca 2', 1),
(33, 3, 'Marca 3 Tenant 3', 'Perú', NULL, 'Descripción de marca 3', 1),
(34, 4, 'Marca 1 Tenant 4', 'Perú', NULL, 'Descripción de marca 1', 1),
(35, 4, 'Marca 2 Tenant 4', 'Perú', NULL, 'Descripción de marca 2', 1),
(36, 4, 'Marca 3 Tenant 4', 'Perú', NULL, 'Descripción de marca 3', 1),
(37, 5, 'Marca 1 Tenant 5', 'Perú', NULL, 'Descripción de marca 1', 1),
(38, 5, 'Marca 2 Tenant 5', 'Perú', NULL, 'Descripción de marca 2', 1),
(39, 5, 'Marca 3 Tenant 5', 'Perú', NULL, 'Descripción de marca 3', 1);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `metodos_pago`
--

CREATE TABLE `metodos_pago` (
  `id_metodos_pago` int(11) NOT NULL,
  `id_tenants` int(11) NOT NULL,
  `nombre_metodo_de_pago` varchar(100) NOT NULL,
  `tipo` enum('efectivo','tarjeta_credito','tarjeta_debito','transferencia','billetera_digital','otro') DEFAULT NULL,
  `descripcion` text DEFAULT NULL,
  `estado` tinyint(1) DEFAULT 1
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `metodos_pago`
--

INSERT INTO `metodos_pago` (`id_metodos_pago`, `id_tenants`, `nombre_metodo_de_pago`, `tipo`, `descripcion`, `estado`) VALUES
(1, 1, 'Efectivo', 'efectivo', 'Pago en efectivo en tienda', 1),
(2, 1, 'Visa / MC', 'tarjeta_credito', 'Tarjetas de crédito y débito con POS', 1),
(3, 1, 'Yape / Plin', 'billetera_digital', 'Billeteras digitales móviles', 1),
(31, 2, 'Efectivo Soles', 'efectivo', 'Pago en efectivo moneda local', 1),
(32, 2, 'Tarjeta Visa/Mastercard', 'tarjeta_credito', 'Pago con tarjeta vía pasarela POS', 1),
(33, 2, 'Billetera Digital Yape/Plin', 'billetera_digital', 'Pago rápido móvil', 1),
(34, 3, 'Efectivo Soles', 'efectivo', 'Pago en efectivo moneda local', 1),
(35, 3, 'Tarjeta Visa/Mastercard', 'tarjeta_credito', 'Pago con tarjeta vía pasarela POS', 1),
(36, 3, 'Billetera Digital Yape/Plin', 'billetera_digital', 'Pago rápido móvil', 1),
(37, 4, 'Efectivo Soles', 'efectivo', 'Pago en efectivo moneda local', 1),
(38, 4, 'Tarjeta Visa/Mastercard', 'tarjeta_credito', 'Pago con tarjeta vía pasarela POS', 1),
(39, 4, 'Billetera Digital Yape/Plin', 'billetera_digital', 'Pago rápido móvil', 1),
(40, 5, 'Efectivo Soles', 'efectivo', 'Pago en efectivo moneda local', 1),
(41, 5, 'Tarjeta Visa/Mastercard', 'tarjeta_credito', 'Pago con tarjeta vía pasarela POS', 1),
(42, 5, 'Billetera Digital Yape/Plin', 'billetera_digital', 'Pago rápido móvil', 1);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `movimientos_inventario`
--

CREATE TABLE `movimientos_inventario` (
  `id_movimientos_inventario` int(11) NOT NULL,
  `id_lotes_inventario` int(11) NOT NULL,
  `tipo_movimiento` enum('entrada','salida','ajuste','transferencia','merma','devolucion') NOT NULL,
  `cantidad` int(11) DEFAULT NULL,
  `id_usuarios` int(11) DEFAULT NULL,
  `motivo` varchar(255) DEFAULT NULL,
  `referencia_documento` varchar(100) DEFAULT NULL,
  `fecha_movimiento` timestamp NULL DEFAULT current_timestamp()
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `movimientos_inventario`
--

INSERT INTO `movimientos_inventario` (`id_movimientos_inventario`, `id_lotes_inventario`, `tipo_movimiento`, `cantidad`, `id_usuarios`, `motivo`, `referencia_documento`, `fecha_movimiento`) VALUES
(1, 1, 'entrada', 50, 1, 'Ingreso por orden de compra', 'OC-2026-001', '2026-06-02 02:52:12'),
(2, 2, 'entrada', 30, 1, 'Ingreso por orden de compra', 'OC-2026-002', '2026-06-02 02:52:12'),
(3, 3, 'entrada', 40, 1, 'Ingreso por orden de compra', 'OC-2026-003', '2026-06-02 02:52:12'),
(4, 1, 'salida', 5, NULL, 'Venta directa', 'B001-00000029', '2026-07-10 05:03:12'),
(5, 1, 'salida', 3, NULL, 'Venta directa', 'B001-00000030', '2026-07-10 05:04:40'),
(6, 1, 'salida', 5, NULL, 'Venta directa', 'B001-00000031', '2026-07-10 05:11:04');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `notificaciones`
--

CREATE TABLE `notificaciones` (
  `id_notificaciones` int(11) NOT NULL,
  `id_tenants` int(11) NOT NULL,
  `id_usuarios` int(11) DEFAULT NULL,
  `tipo` enum('stock_bajo','producto_vencer','cita_pendiente','cancelacion','acceso_sospechoso','pago_vencido','otro') DEFAULT 'otro',
  `titulo` varchar(255) DEFAULT NULL,
  `mensaje` text DEFAULT NULL,
  `canal_envio` enum('correo','sms','sistema','whatsapp') DEFAULT 'sistema',
  `estado_lectura` enum('no_leido','leido') DEFAULT 'no_leido',
  `fecha_lectura` timestamp NULL DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `notificaciones`
--

INSERT INTO `notificaciones` (`id_notificaciones`, `id_tenants`, `id_usuarios`, `tipo`, `titulo`, `mensaje`, `canal_envio`, `estado_lectura`, `fecha_lectura`) VALUES
(1, 1, 1, 'stock_bajo', 'Stock bajo: Tinte Majirel N°7', 'Solo quedan 2 unidades del tinte Majirel N°7. Se recomienda reabastecer.', 'sistema', 'no_leido', NULL),
(2, 1, 2, 'cita_pendiente', 'Cita mañana: Lucía Paredes', 'Mañana a las 10:00 tienes una cita de corte con Lucía Paredes.', 'sistema', 'no_leido', NULL),
(3, 1, 1, 'pago_vencido', 'Factura próxima a vencer', 'La factura FAC-00341 vence el 27/06/2026. Coordina el pago.', 'correo', 'no_leido', NULL),
(28, 2, 28, 'stock_bajo', 'Alerta de Stock Bajo', 'El producto 1 está por debajo del mínimo.', 'sistema', 'no_leido', NULL),
(29, 2, 29, 'stock_bajo', 'Alerta de Stock Bajo', 'El producto 2 está por debajo del mínimo.', 'sistema', 'no_leido', NULL),
(30, 2, 30, 'stock_bajo', 'Alerta de Stock Bajo', 'El producto 3 está por debajo del mínimo.', 'sistema', 'no_leido', NULL),
(31, 3, 31, 'stock_bajo', 'Alerta de Stock Bajo', 'El producto 1 está por debajo del mínimo.', 'sistema', 'no_leido', NULL),
(32, 3, 32, 'stock_bajo', 'Alerta de Stock Bajo', 'El producto 2 está por debajo del mínimo.', 'sistema', 'no_leido', NULL),
(33, 3, 33, 'stock_bajo', 'Alerta de Stock Bajo', 'El producto 3 está por debajo del mínimo.', 'sistema', 'no_leido', NULL),
(34, 4, 34, 'stock_bajo', 'Alerta de Stock Bajo', 'El producto 1 está por debajo del mínimo.', 'sistema', 'no_leido', NULL),
(35, 4, 35, 'stock_bajo', 'Alerta de Stock Bajo', 'El producto 2 está por debajo del mínimo.', 'sistema', 'no_leido', NULL),
(36, 4, 36, 'stock_bajo', 'Alerta de Stock Bajo', 'El producto 3 está por debajo del mínimo.', 'sistema', 'no_leido', NULL),
(37, 5, 37, 'stock_bajo', 'Alerta de Stock Bajo', 'El producto 1 está por debajo del mínimo.', 'sistema', 'no_leido', NULL),
(38, 5, 38, 'stock_bajo', 'Alerta de Stock Bajo', 'El producto 2 está por debajo del mínimo.', 'sistema', 'no_leido', NULL),
(39, 5, 39, 'stock_bajo', 'Alerta de Stock Bajo', 'El producto 3 está por debajo del mínimo.', 'sistema', 'no_leido', NULL);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `ordenes_compra`
--

CREATE TABLE `ordenes_compra` (
  `id_ordenes_compra` int(11) NOT NULL,
  `id_tenants` int(11) NOT NULL,
  `id_proveedores` int(11) NOT NULL,
  `numero_orden` varchar(50) DEFAULT NULL,
  `fecha_orden` date DEFAULT NULL,
  `fecha_entrega_estimada` date DEFAULT NULL,
  `estado` tinyint(1) DEFAULT 1,
  `monto_total` decimal(10,2) DEFAULT NULL,
  `condiciones_entrega` text DEFAULT NULL,
  `forma_pago` varchar(100) DEFAULT NULL,
  `notas` text DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `ordenes_compra`
--

INSERT INTO `ordenes_compra` (`id_ordenes_compra`, `id_tenants`, `id_proveedores`, `numero_orden`, `fecha_orden`, `fecha_entrega_estimada`, `estado`, `monto_total`, `condiciones_entrega`, `forma_pago`, `notas`) VALUES
(1, 1, 1, 'OC-2026-001', '2026-05-20', '2026-05-27', 0, 540.00, NULL, 'transferencia', 'Pedido mensual de tintes'),
(2, 1, 2, 'OC-2026-002', '2026-05-22', '2026-05-29', 0, 320.00, NULL, 'efectivo', 'Reposición de shampoos y acondicionadores'),
(3, 1, 3, 'OC-2026-003', '2026-06-01', '2026-06-08', 2, 210.00, NULL, 'transferencia', 'Esmaltes y accesorios nail art'),
(28, 2, 28, 'OC-2-0-1783215553', '2026-07-05', '2026-07-12', 1, 500.00, NULL, NULL, NULL),
(29, 2, 29, 'OC-2-1-1783215553', '2026-07-05', '2026-07-12', 1, 1000.00, NULL, NULL, NULL),
(30, 2, 30, 'OC-2-2-1783215553', '2026-07-05', '2026-07-12', 1, 1500.00, NULL, NULL, NULL),
(31, 3, 31, 'OC-3-0-1783215554', '2026-07-05', '2026-07-12', 1, 500.00, NULL, NULL, NULL),
(32, 3, 32, 'OC-3-1-1783215554', '2026-07-05', '2026-07-12', 1, 1000.00, NULL, NULL, NULL),
(33, 3, 33, 'OC-3-2-1783215554', '2026-07-05', '2026-07-12', 1, 1500.00, NULL, NULL, NULL),
(34, 4, 34, 'OC-4-0-1783215554', '2026-07-05', '2026-07-12', 1, 500.00, NULL, NULL, NULL),
(35, 4, 35, 'OC-4-1-1783215554', '2026-07-05', '2026-07-12', 1, 1000.00, NULL, NULL, NULL),
(36, 4, 36, 'OC-4-2-1783215554', '2026-07-05', '2026-07-12', 1, 1500.00, NULL, NULL, NULL),
(37, 5, 37, 'OC-5-0-1783215554', '2026-07-05', '2026-07-12', 1, 500.00, NULL, NULL, NULL),
(38, 5, 38, 'OC-5-1-1783215554', '2026-07-05', '2026-07-12', 1, 1000.00, NULL, NULL, NULL),
(39, 5, 39, 'OC-5-2-1783215554', '2026-07-05', '2026-07-12', 1, 1500.00, NULL, NULL, NULL);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `pagos_proveedor`
--

CREATE TABLE `pagos_proveedor` (
  `id_pagos_proveedor` int(11) NOT NULL,
  `id_cuentas_por_pagar` int(11) NOT NULL,
  `monto_pagado` decimal(10,2) DEFAULT NULL,
  `forma_pago` enum('efectivo','transferencia','tarjeta','otro') DEFAULT 'efectivo',
  `numero_comprobante` varchar(50) DEFAULT NULL,
  `fecha_pago` date DEFAULT NULL,
  `id_usuarios` int(11) DEFAULT NULL,
  `observaciones` text DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `pagos_proveedor`
--

INSERT INTO `pagos_proveedor` (`id_pagos_proveedor`, `id_cuentas_por_pagar`, `monto_pagado`, `forma_pago`, `numero_comprobante`, `fecha_pago`, `id_usuarios`, `observaciones`) VALUES
(1, 1, 540.00, 'transferencia', 'TRANSF-20260601-001', '2026-06-01', 1, 'Pago total factura FAC-00341'),
(2, 2, 200.00, 'efectivo', NULL, '2026-06-01', 1, 'Pago parcial BOL-00892'),
(3, 3, 210.00, 'transferencia', 'TRANSF-20260601-002', '2026-06-01', 1, 'Pago total FAC-00115');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `pedidos`
--

CREATE TABLE `pedidos` (
  `id_pedidos` int(11) NOT NULL,
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
  `fecha_entrega_real` datetime DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `pedidos`
--

INSERT INTO `pedidos` (`id_pedidos`, `id_tenants`, `id_clientes`, `numero_pedido`, `modalidad`, `estado`, `subtotal`, `costo_envio`, `impuesto`, `total`, `direccion_entrega`, `id_zonas_delivery`, `id_usuarios`, `fecha_pedido`, `fecha_entrega_estimada`, `fecha_entrega_real`) VALUES
(1, 1, 1, 'PED-2026-001', 'delivery', 1, 29.66, 8.00, 5.34, 43.00, 'Av. Brasil 123, Pueblo Libre', 1, 3, '2026-06-02 02:52:12', '2026-06-02 14:00:00', NULL),
(2, 1, 2, 'PED-2026-002', 'delivery', 1, 35.59, 10.00, 6.41, 52.00, 'Calle Los Robles 45, Miraflores', 2, 3, '2026-06-02 02:52:12', '2026-06-02 15:30:00', NULL),
(3, 1, 3, 'PED-2026-003', 'presencial', 0, 23.73, 0.00, 4.27, 28.00, NULL, NULL, 2, '2026-06-02 02:52:12', NULL, NULL),
(28, 2, 29, 'PED-2-1-1783215553', 'delivery', 1, 40.00, 10.00, NULL, 50.00, 'Dirección Entrega Pedido 1', 28, 28, '2026-07-05 01:39:13', NULL, NULL),
(29, 2, 30, 'PED-2-2-1783215553', 'delivery', 1, 80.00, 10.00, NULL, 90.00, 'Dirección Entrega Pedido 2', 29, 29, '2026-07-05 01:39:13', NULL, NULL),
(30, 2, 31, 'PED-2-3-1783215553', 'delivery', 1, 120.00, 10.00, NULL, 130.00, 'Dirección Entrega Pedido 3', 30, 30, '2026-07-05 01:39:13', NULL, NULL),
(31, 3, 32, 'PED-3-1-1783215554', 'delivery', 1, 40.00, 10.00, NULL, 50.00, 'Dirección Entrega Pedido 1', 31, 31, '2026-07-05 01:39:14', NULL, NULL),
(32, 3, 33, 'PED-3-2-1783215554', 'delivery', 1, 80.00, 10.00, NULL, 90.00, 'Dirección Entrega Pedido 2', 32, 32, '2026-07-05 01:39:14', NULL, NULL),
(33, 3, 34, 'PED-3-3-1783215554', 'delivery', 1, 120.00, 10.00, NULL, 130.00, 'Dirección Entrega Pedido 3', 33, 33, '2026-07-05 01:39:14', NULL, NULL),
(34, 4, 35, 'PED-4-1-1783215554', 'delivery', 1, 40.00, 10.00, NULL, 50.00, 'Dirección Entrega Pedido 1', 34, 34, '2026-07-05 01:39:14', NULL, NULL),
(35, 4, 36, 'PED-4-2-1783215554', 'delivery', 1, 80.00, 10.00, NULL, 90.00, 'Dirección Entrega Pedido 2', 35, 35, '2026-07-05 01:39:14', NULL, NULL),
(36, 4, 37, 'PED-4-3-1783215554', 'delivery', 1, 120.00, 10.00, NULL, 130.00, 'Dirección Entrega Pedido 3', 36, 36, '2026-07-05 01:39:14', NULL, NULL),
(37, 5, 38, 'PED-5-1-1783215554', 'delivery', 1, 40.00, 10.00, NULL, 50.00, 'Dirección Entrega Pedido 1', 37, 37, '2026-07-05 01:39:14', NULL, NULL),
(38, 5, 39, 'PED-5-2-1783215554', 'delivery', 1, 80.00, 10.00, NULL, 90.00, 'Dirección Entrega Pedido 2', 38, 38, '2026-07-05 01:39:14', NULL, NULL),
(39, 5, 40, 'PED-5-3-1783215554', 'delivery', 1, 120.00, 10.00, NULL, 130.00, 'Dirección Entrega Pedido 3', 39, 39, '2026-07-05 01:39:14', NULL, NULL),
(40, 1, 4, 'PED-1783655784322', 'delivery', 1, 101.69, 0.00, 18.31, 120.00, 'Jr. Sinchi Roca Cdra. 2', NULL, NULL, '2026-07-10 03:56:24', NULL, NULL);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `permisos_rol`
--

CREATE TABLE `permisos_rol` (
  `id_permisos_rol` int(11) NOT NULL,
  `id_roles_personalizados` int(11) NOT NULL,
  `modulo` varchar(100) DEFAULT NULL,
  `accion` enum('crear','editar','eliminar','visualizar') NOT NULL,
  `recurso` varchar(100) DEFAULT NULL,
  `estado` tinyint(1) DEFAULT 1
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `permisos_rol`
--

INSERT INTO `permisos_rol` (`id_permisos_rol`, `id_roles_personalizados`, `modulo`, `accion`, `recurso`, `estado`) VALUES
(5, 41, 'ventas', 'visualizar', 'modulo', 1),
(6, 41, 'pedidos', 'visualizar', 'modulo', 1),
(7, 41, 'formas-pago-venta', 'visualizar', 'modulo', 1),
(8, 41, 'comprobantes-electronicos', 'visualizar', 'modulo', 1),
(9, 41, 'series-comprobantes', 'visualizar', 'modulo', 1),
(10, 41, 'caja-chica', 'visualizar', 'modulo', 1),
(11, 41, 'sesiones-caja', 'visualizar', 'modulo', 1),
(12, 41, 'metodos-pago', 'visualizar', 'modulo', 1),
(13, 41, 'clientes', 'visualizar', 'modulo', 1),
(14, 42, 'citas', 'visualizar', 'modulo', 1),
(15, 42, 'clientes', 'visualizar', 'modulo', 1),
(16, 42, 'productos', 'visualizar', 'modulo', 1),
(17, 42, 'categorias', 'visualizar', 'modulo', 1),
(18, 42, 'marcas', 'visualizar', 'modulo', 1),
(19, 40, 'pedidos', 'visualizar', 'modulo', 1),
(20, 40, 'clientes', 'visualizar', 'modulo', 1),
(21, 40, 'zonas-delivery', 'visualizar', 'modulo', 1),
(22, 43, 'citas', 'visualizar', 'modulo', 1),
(23, 43, 'clientes', 'visualizar', 'modulo', 1),
(24, 43, 'notificaciones', 'visualizar', 'modulo', 1),
(25, 43, 'horarios-operacion', 'visualizar', 'modulo', 1),
(26, 44, 'ventas', 'visualizar', 'modulo', 1),
(27, 44, 'pedidos', 'visualizar', 'modulo', 1),
(28, 44, 'formas-pago-venta', 'visualizar', 'modulo', 1),
(29, 44, 'comprobantes-electronicos', 'visualizar', 'modulo', 1),
(30, 44, 'series-comprobantes', 'visualizar', 'modulo', 1),
(31, 44, 'caja-chica', 'visualizar', 'modulo', 1),
(32, 44, 'sesiones-caja', 'visualizar', 'modulo', 1),
(33, 44, 'metodos-pago', 'visualizar', 'modulo', 1),
(34, 44, 'clientes', 'visualizar', 'modulo', 1),
(35, 45, 'citas', 'visualizar', 'modulo', 1),
(36, 45, 'clientes', 'visualizar', 'modulo', 1),
(37, 45, 'productos', 'visualizar', 'modulo', 1),
(38, 45, 'categorias', 'visualizar', 'modulo', 1),
(39, 45, 'marcas', 'visualizar', 'modulo', 1),
(40, 46, 'pedidos', 'visualizar', 'modulo', 1),
(41, 46, 'clientes', 'visualizar', 'modulo', 1),
(42, 46, 'zonas-delivery', 'visualizar', 'modulo', 1),
(43, 47, 'citas', 'visualizar', 'modulo', 1),
(44, 47, 'clientes', 'visualizar', 'modulo', 1),
(45, 47, 'notificaciones', 'visualizar', 'modulo', 1),
(46, 47, 'horarios-operacion', 'visualizar', 'modulo', 1),
(47, 48, 'ventas', 'visualizar', 'modulo', 1),
(48, 48, 'pedidos', 'visualizar', 'modulo', 1),
(49, 48, 'formas-pago-venta', 'visualizar', 'modulo', 1),
(50, 48, 'comprobantes-electronicos', 'visualizar', 'modulo', 1),
(51, 48, 'series-comprobantes', 'visualizar', 'modulo', 1),
(52, 48, 'caja-chica', 'visualizar', 'modulo', 1),
(53, 48, 'sesiones-caja', 'visualizar', 'modulo', 1),
(54, 48, 'metodos-pago', 'visualizar', 'modulo', 1),
(55, 48, 'clientes', 'visualizar', 'modulo', 1),
(56, 49, 'citas', 'visualizar', 'modulo', 1),
(57, 49, 'clientes', 'visualizar', 'modulo', 1),
(58, 49, 'productos', 'visualizar', 'modulo', 1),
(59, 49, 'categorias', 'visualizar', 'modulo', 1),
(60, 49, 'marcas', 'visualizar', 'modulo', 1),
(61, 50, 'pedidos', 'visualizar', 'modulo', 1),
(62, 50, 'clientes', 'visualizar', 'modulo', 1),
(63, 50, 'zonas-delivery', 'visualizar', 'modulo', 1),
(64, 51, 'citas', 'visualizar', 'modulo', 1),
(65, 51, 'clientes', 'visualizar', 'modulo', 1),
(66, 51, 'notificaciones', 'visualizar', 'modulo', 1),
(67, 51, 'horarios-operacion', 'visualizar', 'modulo', 1),
(68, 52, 'ventas', 'visualizar', 'modulo', 1),
(69, 52, 'pedidos', 'visualizar', 'modulo', 1),
(70, 52, 'formas-pago-venta', 'visualizar', 'modulo', 1),
(71, 52, 'comprobantes-electronicos', 'visualizar', 'modulo', 1),
(72, 52, 'series-comprobantes', 'visualizar', 'modulo', 1),
(73, 52, 'caja-chica', 'visualizar', 'modulo', 1),
(74, 52, 'sesiones-caja', 'visualizar', 'modulo', 1),
(75, 52, 'metodos-pago', 'visualizar', 'modulo', 1),
(76, 52, 'clientes', 'visualizar', 'modulo', 1),
(77, 53, 'citas', 'visualizar', 'modulo', 1),
(78, 53, 'clientes', 'visualizar', 'modulo', 1),
(79, 53, 'productos', 'visualizar', 'modulo', 1),
(80, 53, 'categorias', 'visualizar', 'modulo', 1),
(81, 53, 'marcas', 'visualizar', 'modulo', 1),
(82, 54, 'pedidos', 'visualizar', 'modulo', 1),
(83, 54, 'clientes', 'visualizar', 'modulo', 1),
(84, 54, 'zonas-delivery', 'visualizar', 'modulo', 1),
(85, 55, 'citas', 'visualizar', 'modulo', 1),
(86, 55, 'clientes', 'visualizar', 'modulo', 1),
(87, 55, 'notificaciones', 'visualizar', 'modulo', 1),
(88, 55, 'horarios-operacion', 'visualizar', 'modulo', 1),
(89, 56, 'ventas', 'visualizar', 'modulo', 1),
(90, 56, 'pedidos', 'visualizar', 'modulo', 1),
(91, 56, 'formas-pago-venta', 'visualizar', 'modulo', 1),
(92, 56, 'comprobantes-electronicos', 'visualizar', 'modulo', 1),
(93, 56, 'series-comprobantes', 'visualizar', 'modulo', 1),
(94, 56, 'caja-chica', 'visualizar', 'modulo', 1),
(95, 56, 'sesiones-caja', 'visualizar', 'modulo', 1),
(96, 56, 'metodos-pago', 'visualizar', 'modulo', 1),
(97, 56, 'clientes', 'visualizar', 'modulo', 1),
(98, 57, 'citas', 'visualizar', 'modulo', 1),
(99, 57, 'clientes', 'visualizar', 'modulo', 1),
(100, 57, 'productos', 'visualizar', 'modulo', 1),
(101, 57, 'categorias', 'visualizar', 'modulo', 1),
(102, 57, 'marcas', 'visualizar', 'modulo', 1),
(103, 58, 'pedidos', 'visualizar', 'modulo', 1),
(104, 58, 'clientes', 'visualizar', 'modulo', 1),
(105, 58, 'zonas-delivery', 'visualizar', 'modulo', 1),
(106, 59, 'citas', 'visualizar', 'modulo', 1),
(107, 59, 'clientes', 'visualizar', 'modulo', 1),
(108, 59, 'notificaciones', 'visualizar', 'modulo', 1),
(109, 59, 'horarios-operacion', 'visualizar', 'modulo', 1);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `planes_suscripcion`
--

CREATE TABLE `planes_suscripcion` (
  `id_planes_suscripcion` int(11) NOT NULL,
  `nombre_plan_suscripcion` varchar(100) NOT NULL,
  `descripcion` text DEFAULT NULL,
  `estado` tinyint(1) DEFAULT 1
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `planes_suscripcion`
--

INSERT INTO `planes_suscripcion` (`id_planes_suscripcion`, `nombre_plan_suscripcion`, `descripcion`, `estado`) VALUES
(1, 'Plan Básico', 'Acceso a funciones esenciales del sistema', 1),
(2, 'Plan Profesional', 'Mayor capacidad en todo. pero no tiene accceso a sistema de reportes', 1),
(3, 'Plan Enterprise', 'Acceso completo y sistema de reportes', 1);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `precios_plan`
--

CREATE TABLE `precios_plan` (
  `id_precios_plan` int(11) NOT NULL,
  `id_planes_suscripcion` int(11) NOT NULL,
  `periodo` enum('mensual','trimestral','anual') NOT NULL,
  `precio` decimal(10,2) NOT NULL,
  `estado` tinyint(1) DEFAULT 1
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `precios_plan`
--

INSERT INTO `precios_plan` (`id_precios_plan`, `id_planes_suscripcion`, `periodo`, `precio`, `estado`) VALUES
(1, 1, 'mensual', 49.90, 1),
(2, 2, 'trimestral', 129.90, 1),
(3, 3, 'anual', 899.90, 1);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `preferencias_usuario`
--

CREATE TABLE `preferencias_usuario` (
  `id_preferencias_usuario` int(11) NOT NULL,
  `id_usuarios` int(11) NOT NULL,
  `idioma` varchar(10) DEFAULT 'es',
  `formato_fecha` varchar(20) DEFAULT 'DD/MM/YYYY',
  `zona_horaria` varchar(50) DEFAULT NULL,
  `notificaciones_activas` tinyint(1) DEFAULT 1,
  `vista_inicial` varchar(100) DEFAULT NULL,
  `tema_interfaz` enum('claro','oscuro') DEFAULT 'claro'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `preferencias_usuario`
--

INSERT INTO `preferencias_usuario` (`id_preferencias_usuario`, `id_usuarios`, `idioma`, `formato_fecha`, `zona_horaria`, `notificaciones_activas`, `vista_inicial`, `tema_interfaz`) VALUES
(1, 1, 'es', 'DD/MM/YYYY', 'America/Lima', 1, 'dashboard', 'claro'),
(2, 2, 'es', 'DD/MM/YYYY', 'America/Lima', 1, 'ventas', 'claro'),
(3, 3, 'es', 'DD/MM/YYYY', 'America/Lima', 0, 'pedidos', 'oscuro');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `productos`
--

CREATE TABLE `productos` (
  `id_productos` int(11) NOT NULL,
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
  `margen_ganancia` decimal(5,2) GENERATED ALWAYS AS (case when `precio_costo` > 0 then round((`precio_venta` - `precio_costo`) / `precio_costo` * 100,2) else NULL end) VIRTUAL,
  `stock_minimo` int(11) DEFAULT 10,
  `stock_critico` int(11) DEFAULT 5,
  `stock_actual` int(11) DEFAULT 0,
  `visible_storefront` tinyint(1) DEFAULT 1,
  `etiqueta_especial` varchar(100) DEFAULT NULL,
  `estado` tinyint(1) DEFAULT 1
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `productos`
--

INSERT INTO `productos` (`id_productos`, `id_tenants`, `id_categorias_productos`, `id_marcas`, `codigo_interno`, `codigo_barras`, `nombre_producto`, `descripcion`, `tipo_producto`, `presentacion`, `contenido_neto`, `img_url`, `precio_costo`, `precio_venta`, `stock_minimo`, `stock_critico`, `stock_actual`, `visible_storefront`, `etiqueta_especial`, `estado`) VALUES
(1, 1, 1, 1, 'TIN-001', '7501234560001', 'Tinte L\'Oréal Majirel N°7', 'Tinte permanente rubio medio con cobertura 100%', 'tinte', 'tubo', '50ml', '/uploads/9235b42fe1f94a1fbb22e50875dad834.jpg', 20.00, 35.00, 10, 5, 25, 1, NULL, 1),
(2, 1, 2, 2, 'SHA-002', '7501234560002', 'Shampoo Wella SP Balance', 'Shampoo equilibrante para cuero cabelludo sensible', 'shampoo', 'frasco', '250ml', '/uploads/08f324985a924e12b422c11b28598d2f.jpg', 22.00, 42.00, 10, 5, 20, 1, NULL, 1),
(3, 1, 3, 3, 'ESM-003', '7501234560003', 'Esmalte OPI Gel Color Rojo', 'Esmalte semipermanente gel color rojo pasión', 'esmalte', 'frasco', '15ml', '/uploads/9bc1450498ec4790b7186f1d89edb4e3.jpg', 12.00, 28.00, 15, 5, 15, 1, NULL, 1),
(4, 1, 4, 3, 'EXA-003', '7501234560007', 'Test', 'holis', 'Cosmetico', 'Caja', '269gr', '/uploads/1d61723730904a9f8a391fa15ef004c7.jpg', 5.00, 10.00, 10, 5, 36, 1, NULL, 1),
(29, 2, 41, 28, 'INT-2-1', '7501232189', 'Producto 1 Tenant 2', 'Descripción del producto premium 1 para el tenant 2', 'Cosmético', 'Frasco', '250ml', NULL, 15.00, 30.00, 5, 2, 50, 1, NULL, 1),
(30, 2, 42, 29, 'INT-2-2', '7501232289', 'Producto 2 Tenant 2', 'Descripción del producto premium 2 para el tenant 2', 'Cosmético', 'Frasco', '250ml', NULL, 30.00, 60.00, 5, 2, 50, 1, NULL, 1),
(31, 2, 43, 30, 'INT-2-3', '7501232389', 'Producto 3 Tenant 2', 'Descripción del producto premium 3 para el tenant 2', 'Cosmético', 'Frasco', '250ml', NULL, 45.00, 90.00, 5, 2, 50, 1, NULL, 1),
(32, 3, 44, 31, 'INT-3-1', '7501233189', 'Producto 1 Tenant 3', 'Descripción del producto premium 1 para el tenant 3', 'Cosmético', 'Frasco', '250ml', NULL, 15.00, 30.00, 5, 2, 50, 1, NULL, 1),
(33, 3, 45, 32, 'INT-3-2', '7501233289', 'Producto 2 Tenant 3', 'Descripción del producto premium 2 para el tenant 3', 'Cosmético', 'Frasco', '250ml', NULL, 30.00, 60.00, 5, 2, 50, 1, NULL, 1),
(34, 3, 46, 33, 'INT-3-3', '7501233389', 'Producto 3 Tenant 3', 'Descripción del producto premium 3 para el tenant 3', 'Cosmético', 'Frasco', '250ml', NULL, 45.00, 90.00, 5, 2, 50, 1, NULL, 1),
(35, 4, 47, 34, 'INT-4-1', '7501234189', 'Producto 1 Tenant 4', 'Descripción del producto premium 1 para el tenant 4', 'Cosmético', 'Frasco', '250ml', NULL, 15.00, 30.00, 5, 2, 50, 1, NULL, 1),
(36, 4, 48, 35, 'INT-4-2', '7501234289', 'Producto 2 Tenant 4', 'Descripción del producto premium 2 para el tenant 4', 'Cosmético', 'Frasco', '250ml', NULL, 30.00, 60.00, 5, 2, 50, 1, NULL, 1),
(37, 4, 49, 36, 'INT-4-3', '7501234389', 'Producto 3 Tenant 4', 'Descripción del producto premium 3 para el tenant 4', 'Cosmético', 'Frasco', '250ml', NULL, 45.00, 90.00, 5, 2, 50, 1, NULL, 1),
(38, 5, 50, 37, 'INT-5-1', '7501235189', 'Producto 1 Tenant 5', 'Descripción del producto premium 1 para el tenant 5', 'Cosmético', 'Frasco', '250ml', NULL, 15.00, 30.00, 5, 2, 50, 1, NULL, 1),
(39, 5, 51, 38, 'INT-5-2', '7501235289', 'Producto 2 Tenant 5', 'Descripción del producto premium 2 para el tenant 5', 'Cosmético', 'Frasco', '250ml', NULL, 30.00, 60.00, 5, 2, 50, 1, NULL, 1),
(40, 5, 52, 39, 'INT-5-3', '7501235389', 'Producto 3 Tenant 5', 'Descripción del producto premium 3 para el tenant 5', 'Cosmético', 'Frasco', '250ml', NULL, 45.00, 90.00, 5, 2, 50, 1, NULL, 1);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `promociones`
--

CREATE TABLE `promociones` (
  `id_promociones` int(11) NOT NULL,
  `id_tenants` int(11) NOT NULL,
  `nombre_promocion` varchar(255) NOT NULL,
  `tipo_descuento` enum('fijo','porcentual','2x1','3x2','escalonado','regalo') DEFAULT NULL,
  `valor_descuento` decimal(10,2) DEFAULT NULL,
  `compra_minima` decimal(10,2) DEFAULT NULL,
  `fecha_inicio` date DEFAULT NULL,
  `fecha_fin` date DEFAULT NULL,
  `id_categorias_productos` int(11) DEFAULT NULL,
  `estado` tinyint(1) DEFAULT 1
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `promociones`
--

INSERT INTO `promociones` (`id_promociones`, `id_tenants`, `nombre_promocion`, `tipo_descuento`, `valor_descuento`, `compra_minima`, `fecha_inicio`, `fecha_fin`, `id_categorias_productos`, `estado`) VALUES
(1, 1, 'Descuento Coloración Junio', 'porcentual', 15.00, 50.00, '2026-06-01', '2026-06-30', 1, 0),
(2, 1, '2x1 en Shampoos', '2x1', 0.00, 35.00, '2026-06-01', '2026-06-15', 2, 0),
(3, 1, 'Oferta Nail Art', 'fijo', 10.00, 25.00, '2026-06-01', '2026-06-30', 3, 0),
(28, 2, 'Promoción 1 T2', 'porcentual', 10.00, 100.00, '2026-07-05', '2026-07-20', 41, 1),
(29, 2, 'Promoción 2 T2', 'porcentual', 20.00, 100.00, '2026-07-05', '2026-07-20', 42, 1),
(30, 2, 'Promoción 3 T2', 'porcentual', 30.00, 100.00, '2026-07-05', '2026-07-20', 43, 1),
(31, 3, 'Promoción 1 T3', 'porcentual', 10.00, 100.00, '2026-07-05', '2026-07-20', 44, 1),
(32, 3, 'Promoción 2 T3', 'porcentual', 20.00, 100.00, '2026-07-05', '2026-07-20', 45, 1),
(33, 3, 'Promoción 3 T3', 'porcentual', 30.00, 100.00, '2026-07-05', '2026-07-20', 46, 1),
(34, 4, 'Promoción 1 T4', 'porcentual', 10.00, 100.00, '2026-07-05', '2026-07-20', 47, 1),
(35, 4, 'Promoción 2 T4', 'porcentual', 20.00, 100.00, '2026-07-05', '2026-07-20', 48, 1),
(36, 4, 'Promoción 3 T4', 'porcentual', 30.00, 100.00, '2026-07-05', '2026-07-20', 49, 1),
(37, 5, 'Promoción 1 T5', 'porcentual', 10.00, 100.00, '2026-07-05', '2026-07-20', 50, 1),
(38, 5, 'Promoción 2 T5', 'porcentual', 20.00, 100.00, '2026-07-05', '2026-07-20', 51, 1),
(39, 5, 'Promoción 3 T5', 'porcentual', 30.00, 100.00, '2026-07-05', '2026-07-20', 52, 1),
(40, 1, 'TEST', 'porcentual', 20.00, 10.00, '2026-07-08', '2026-07-15', 1, 1);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `proveedores`
--

CREATE TABLE `proveedores` (
  `id_proveedores` int(11) NOT NULL,
  `id_tenants` int(11) NOT NULL,
  `nombre_proveedor` varchar(255) NOT NULL,
  `apellido_proveedor` varchar(255) NOT NULL,
  `razon_social` varchar(255) NOT NULL,
  `ruc` varchar(20) DEFAULT NULL,
  `direccion` varchar(255) DEFAULT NULL,
  `telefono` varchar(15) DEFAULT NULL,
  `correo` varchar(100) DEFAULT NULL,
  `estado` tinyint(1) DEFAULT 1
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `proveedores`
--

INSERT INTO `proveedores` (`id_proveedores`, `id_tenants`, `nombre_proveedor`, `apellido_proveedor`, `razon_social`, `ruc`, `direccion`, `telefono`, `correo`, `estado`) VALUES
(1, 1, 'Jorge', 'Mamani Quispe', 'Distribuidora Cosmética JM S.A.C.', '20501234571', 'Av. Argentina 123, Lima', '01-2345678', 'ventas@jm-cosmetica.pe', 1),
(2, 1, 'Claudia', 'Flores Ramos', 'Beauty Supply Peru E.I.R.L.', '20501234572', 'Jr. Cusco 456, Lima', '01-3456780', 'pedidos@beautysupply.pe', 1),
(3, 1, 'Roberto', 'Chávez Villena', 'Importadora Profesional S.A.', '20501234573', 'Calle Los Cedros 789, Lima', '01-4567891', 'compras@importpro.pe', 1),
(28, 2, 'Proveedor 1', 'Apellido 1', 'Proveedor 1 T2 S.A.C.', '2069992101', 'Dirección Prov 1', '944556621', 'proveedor1.t2@example.com', 1),
(29, 2, 'Proveedor 2', 'Apellido 2', 'Proveedor 2 T2 S.A.C.', '2069992201', 'Dirección Prov 2', '944556622', 'proveedor2.t2@example.com', 1),
(30, 2, 'Proveedor 3', 'Apellido 3', 'Proveedor 3 T2 S.A.C.', '2069992301', 'Dirección Prov 3', '944556623', 'proveedor3.t2@example.com', 1),
(31, 3, 'Proveedor 1', 'Apellido 1', 'Proveedor 1 T3 S.A.C.', '2069993101', 'Dirección Prov 1', '944556621', 'proveedor1.t3@example.com', 1),
(32, 3, 'Proveedor 2', 'Apellido 2', 'Proveedor 2 T3 S.A.C.', '2069993201', 'Dirección Prov 2', '944556622', 'proveedor2.t3@example.com', 1),
(33, 3, 'Proveedor 3', 'Apellido 3', 'Proveedor 3 T3 S.A.C.', '2069993301', 'Dirección Prov 3', '944556623', 'proveedor3.t3@example.com', 1),
(34, 4, 'Proveedor 1', 'Apellido 1', 'Proveedor 1 T4 S.A.C.', '2069994101', 'Dirección Prov 1', '944556621', 'proveedor1.t4@example.com', 1),
(35, 4, 'Proveedor 2', 'Apellido 2', 'Proveedor 2 T4 S.A.C.', '2069994201', 'Dirección Prov 2', '944556622', 'proveedor2.t4@example.com', 1),
(36, 4, 'Proveedor 3', 'Apellido 3', 'Proveedor 3 T4 S.A.C.', '2069994301', 'Dirección Prov 3', '944556623', 'proveedor3.t4@example.com', 1),
(37, 5, 'Proveedor 1', 'Apellido 1', 'Proveedor 1 T5 S.A.C.', '2069995101', 'Dirección Prov 1', '944556621', 'proveedor1.t5@example.com', 1),
(38, 5, 'Proveedor 2', 'Apellido 2', 'Proveedor 2 T5 S.A.C.', '2069995201', 'Dirección Prov 2', '944556622', 'proveedor2.t5@example.com', 1),
(39, 5, 'Proveedor 3', 'Apellido 3', 'Proveedor 3 T5 S.A.C.', '2069995301', 'Dirección Prov 3', '944556623', 'proveedor3.t5@example.com', 1);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `proveedor_categorias`
--

CREATE TABLE `proveedor_categorias` (
  `id_proveedor_categorias` int(11) NOT NULL,
  `id_proveedores` int(11) NOT NULL,
  `id_categorias_productos` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `proveedor_categorias`
--

INSERT INTO `proveedor_categorias` (`id_proveedor_categorias`, `id_proveedores`, `id_categorias_productos`) VALUES
(1, 1, 1),
(2, 2, 2),
(3, 3, 3);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `reclamos`
--

CREATE TABLE `reclamos` (
  `id_reclamos` int(11) NOT NULL,
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
  `fecha_cierre` timestamp NULL DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `reclamos`
--

INSERT INTO `reclamos` (`id_reclamos`, `id_tenants`, `id_clientes`, `id_ventas`, `numero_reclamo`, `canal_ingreso`, `tipo_incidencia`, `descripcion`, `estado`, `id_usuarios_responsable`, `sla_horas`, `fecha_vencimiento_sla`, `solucion_aplicada`, `id_usuarios_usuario_creacion`, `fecha_cierre`) VALUES
(1, 1, 1, 1, 'REC-2026-001', 'presencial', 'Producto defectuoso', 'El tinte comprado presentó mal olor y no cubrió adecuadamente', 1, 1, 48, '2026-06-03 10:00:00', NULL, 2, NULL),
(2, 1, 2, 2, 'REC-2026-002', 'whatsapp', 'Demora en atención', 'La clienta esperó más de 30 minutos sin ser atendida en caja', 1, 1, 24, '2026-06-03 11:30:00', NULL, 1, NULL),
(3, 1, 3, 3, 'REC-2026-003', 'web', 'Cobro incorrecto', 'Se cobró un precio distinto al mostrado en el catálogo online', 1, 1, 24, '2026-06-03 09:00:00', NULL, 1, NULL),
(28, 2, 29, 36, 'REC-2-1', 'whatsapp', 'Demora en entrega', 'El delivery tardó más de una hora en llegar a la dirección indicada.', 1, 28, NULL, NULL, NULL, 28, NULL),
(29, 2, 30, 37, 'REC-2-2', 'whatsapp', 'Demora en entrega', 'El delivery tardó más de una hora en llegar a la dirección indicada.', 1, 29, NULL, NULL, NULL, 29, NULL),
(30, 2, 31, 38, 'REC-2-3', 'whatsapp', 'Demora en entrega', 'El delivery tardó más de una hora en llegar a la dirección indicada.', 1, 30, NULL, NULL, NULL, 30, NULL),
(31, 3, 32, 39, 'REC-3-1', 'whatsapp', 'Demora en entrega', 'El delivery tardó más de una hora en llegar a la dirección indicada.', 1, 31, NULL, NULL, NULL, 31, NULL),
(32, 3, 33, 40, 'REC-3-2', 'whatsapp', 'Demora en entrega', 'El delivery tardó más de una hora en llegar a la dirección indicada.', 1, 32, NULL, NULL, NULL, 32, NULL),
(33, 3, 34, 41, 'REC-3-3', 'whatsapp', 'Demora en entrega', 'El delivery tardó más de una hora en llegar a la dirección indicada.', 1, 33, NULL, NULL, NULL, 33, NULL),
(34, 4, 35, 42, 'REC-4-1', 'whatsapp', 'Demora en entrega', 'El delivery tardó más de una hora en llegar a la dirección indicada.', 1, 34, NULL, NULL, NULL, 34, NULL),
(35, 4, 36, 43, 'REC-4-2', 'whatsapp', 'Demora en entrega', 'El delivery tardó más de una hora en llegar a la dirección indicada.', 1, 35, NULL, NULL, NULL, 35, NULL),
(36, 4, 37, 44, 'REC-4-3', 'whatsapp', 'Demora en entrega', 'El delivery tardó más de una hora en llegar a la dirección indicada.', 1, 36, NULL, NULL, NULL, 36, NULL),
(37, 5, 38, 45, 'REC-5-1', 'whatsapp', 'Demora en entrega', 'El delivery tardó más de una hora en llegar a la dirección indicada.', 1, 37, NULL, NULL, NULL, 37, NULL),
(38, 5, 39, 46, 'REC-5-2', 'whatsapp', 'Demora en entrega', 'El delivery tardó más de una hora en llegar a la dirección indicada.', 1, 38, NULL, NULL, NULL, 38, NULL),
(39, 5, 40, 47, 'REC-5-3', 'whatsapp', 'Demora en entrega', 'El delivery tardó más de una hora en llegar a la dirección indicada.', 1, 39, NULL, NULL, NULL, 39, NULL);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `registros`
--

CREATE TABLE `registros` (
  `idregistro` int(11) NOT NULL,
  `nombres` varchar(255) NOT NULL,
  `apellidos` varchar(255) NOT NULL,
  `email` varchar(255) NOT NULL,
  `cliente_id` varchar(255) NOT NULL,
  `llave_secreta` varchar(255) NOT NULL,
  `access_token` varchar(255) NOT NULL,
  `estado` tinyint(1) NOT NULL DEFAULT 1
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `registros`
--

INSERT INTO `registros` (`idregistro`, `nombres`, `apellidos`, `email`, `cliente_id`, `llave_secreta`, `access_token`, `estado`) VALUES
(1, 'Brayam', 'Arista Fernández', 'brayamaristafrndz@gmail.com', 'f6723fcfecab4c538184dd23283b424e8fe4448958c1a7ae9bfea9ecefac2c6c', '$2a$10$HgN3dfUUqu5QhfBHajFm9OfhSud6qAHui7Cl8bERUTyf//dld6V7e', 'eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJmNjcyM2ZjZmVjYWI0YzUzODE4NGRkMjMyODNiNDI0ZThmZTQ0NDg5NThjMWE3YWU5YmZlYTllY2VmYWMyYzZjIiwiaWF0IjoxNzgwMzcwMTE3LCJleHAiOjQ5MzM5NzAxMTd9.HhRW9Fi1YZeft7Fn5lGLkEGerR6MzbLaVYSnGAv6ydIqeYFw1WKMPv21bFWkJDRBsq02qnYjJo9jZLVSbgs2tA', 1),
(2, 'Brayam', 'Arista Fernández', 'brayamaristafrndz@gmail.com', 'f6723fcfecab4c538184dd23283b424e8fe4448958c1a7ae9bfea9ecefac2c6c', '$2a$10$gLYHm2IajLkzoI0XqseBeuAKqvelR.MdCU4n5cCqKnxHaE4Z6peiy', '', 1),
(3, 'Brayam', 'Arista Fernández', 'brayamaristafrndz@gmail.com', 'f6723fcfecab4c538184dd23283b424e8fe4448958c1a7ae9bfea9ecefac2c6c', '$2a$10$GsUA1nnhftlr1/ekK4ljye7bynqUui7DuAgH8gOg9sl1DSwizhUlO', '', 1),
(4, 'Luis', 'Fernandez', 'Luis@gmail.com', 'dd7b85c4f4bea75882d588cb1fd133b3b89dfaa20013a22bf9d223cbf1cba9bd', '$2a$10$6AC5.y5tgNCu7OC71aOqluw6D03GRpzIbmXLpX0HcsBPKQrJJD85G', 'eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJkZDdiODVjNGY0YmVhNzU4ODJkNTg4Y2IxZmQxMzNiM2I4OWRmYWEyMDAxM2EyMmJmOWQyMjNjYmYxY2JhOWJkIiwiaWF0IjoxNzgwMzcwMTQzLCJleHAiOjQ5MzM5NzAxNDN9.ep3uRPCM9RIkKvXVroDyOE06rexYlqSl9vMnzVPUQL-OMr4DN3aseJZvBbFBAMkaFLCFAp-6FjckTpIjPcPtcg', 1),
(5, 'Marco', 'Reategui Bojorquez', 'marcoreabojo@gmail.com', '96d7977ab062e64a217e31be4260696317d95e6f89daf7bd188c9918caff6147', '$2a$10$EOXBF.vm0EI3eBPTr.tKYud7yfqb1a4B5HocTKE0MCMhkD51rgb3m', 'eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiI5NmQ3OTc3YWIwNjJlNjRhMjE3ZTMxYmU0MjYwNjk2MzE3ZDk1ZTZmODlkYWY3YmQxODhjOTkxOGNhZmY2MTQ3IiwiaWF0IjoxNzgwNDMzNDc5LCJleHAiOjQ5MzQwMzM0Nzl9.H9GWlmciY3eRU1KOz3XzqQNH1Ph_b9p1DoWSGh5WOgaOgj0Zl0VNFZRxEcJCYERp_K0ghv0bJGvKb8EdTLU9cg', 1);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `repartidores`
--

CREATE TABLE `repartidores` (
  `id_repartidores` int(11) NOT NULL,
  `id_tenants` int(11) NOT NULL,
  `id_usuarios` int(11) NOT NULL,
  `tipo_vehiculo` varchar(100) DEFAULT NULL,
  `placa_vehiculo` varchar(20) DEFAULT NULL,
  `numero_licencia` varchar(50) DEFAULT NULL,
  `estado` tinyint(1) DEFAULT 1
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `repartidores`
--

INSERT INTO `repartidores` (`id_repartidores`, `id_tenants`, `id_usuarios`, `tipo_vehiculo`, `placa_vehiculo`, `numero_licencia`, `estado`) VALUES
(1, 1, 3, 'Moto lineal', 'M3G-456', 'LIC-2024-00123', 1),
(26, 2, 28, 'Motocicleta', 'AB-2099', 'LIC-2077', 1),
(27, 2, 29, 'Motocicleta', 'AB-2199', 'LIC-2177', 1),
(28, 2, 30, 'Motocicleta', 'AB-2299', 'LIC-2277', 1),
(29, 3, 31, 'Motocicleta', 'AB-3099', 'LIC-3077', 1),
(30, 3, 32, 'Motocicleta', 'AB-3199', 'LIC-3177', 1),
(31, 3, 33, 'Motocicleta', 'AB-3299', 'LIC-3277', 1),
(32, 4, 34, 'Motocicleta', 'AB-4099', 'LIC-4077', 1),
(33, 4, 35, 'Motocicleta', 'AB-4199', 'LIC-4177', 1),
(34, 4, 36, 'Motocicleta', 'AB-4299', 'LIC-4277', 1),
(35, 5, 37, 'Motocicleta', 'AB-5099', 'LIC-5077', 1),
(36, 5, 38, 'Motocicleta', 'AB-5199', 'LIC-5177', 1),
(37, 5, 39, 'Motocicleta', 'AB-5299', 'LIC-5277', 1);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `roles_personalizados`
--

CREATE TABLE `roles_personalizados` (
  `id_roles_personalizados` int(11) NOT NULL,
  `id_tenants` int(11) NOT NULL,
  `nombre_rol_personalizado` varchar(100) NOT NULL,
  `descripcion` text DEFAULT NULL,
  `estado` tinyint(1) DEFAULT 1
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `roles_personalizados`
--

INSERT INTO `roles_personalizados` (`id_roles_personalizados`, `id_tenants`, `nombre_rol_personalizado`, `descripcion`, `estado`) VALUES
(1, 1, 'Supervisor de Caja', 'Controla sesiones de caja y aprueba descuentos', 0),
(2, 1, 'Colorista Senior', 'Especialista autorizado en técnicas de color', 0),
(3, 1, 'Encargado de Almacén', 'Gestiona el inventario y órdenes de compra', 0),
(28, 2, 'Rol Personalizado 1 Tenant 2', 'Descripción de rol personalizado 1', 1),
(29, 2, 'Rol Personalizado 2 Tenant 2', 'Descripción de rol personalizado 2', 1),
(30, 2, 'Rol Personalizado 3 Tenant 2', 'Descripción de rol personalizado 3', 1),
(31, 3, 'Rol Personalizado 1 Tenant 3', 'Descripción de rol personalizado 1', 1),
(32, 3, 'Rol Personalizado 2 Tenant 3', 'Descripción de rol personalizado 2', 1),
(33, 3, 'Rol Personalizado 3 Tenant 3', 'Descripción de rol personalizado 3', 1),
(34, 4, 'Rol Personalizado 1 Tenant 4', 'Descripción de rol personalizado 1', 1),
(35, 4, 'Rol Personalizado 2 Tenant 4', 'Descripción de rol personalizado 2', 1),
(36, 4, 'Rol Personalizado 3 Tenant 4', 'Descripción de rol personalizado 3', 1),
(37, 5, 'Rol Personalizado 1 Tenant 5', 'Descripción de rol personalizado 1', 1),
(38, 5, 'Rol Personalizado 2 Tenant 5', 'Descripción de rol personalizado 2', 1),
(39, 5, 'Rol Personalizado 3 Tenant 5', 'Descripción de rol personalizado 3', 1),
(40, 1, 'Repartidor', 'Gestiona pedidos', 1),
(41, 1, 'cajero', 'Acceso a caja, cobros y ventas del negocio', 1),
(42, 1, 'estilista', 'Especialista autorizado en estilismo y citas', 1),
(43, 1, 'recepcionista', 'Administración de citas y recepción de clientes', 1),
(44, 2, 'cajero', 'Acceso a caja, cobros y ventas del negocio', 1),
(45, 2, 'estilista', 'Especialista autorizado en estilismo y citas', 1),
(46, 2, 'repartidor', 'Encargado de la entrega de pedidos a domicilio', 1),
(47, 2, 'recepcionista', 'Administración de citas y recepción de clientes', 1),
(48, 3, 'cajero', 'Acceso a caja, cobros y ventas del negocio', 1),
(49, 3, 'estilista', 'Especialista autorizado en estilismo y citas', 1),
(50, 3, 'repartidor', 'Encargado de la entrega de pedidos a domicilio', 1),
(51, 3, 'recepcionista', 'Administración de citas y recepción de clientes', 1),
(52, 4, 'cajero', 'Acceso a caja, cobros y ventas del negocio', 1),
(53, 4, 'estilista', 'Especialista autorizado en estilismo y citas', 1),
(54, 4, 'repartidor', 'Encargado de la entrega de pedidos a domicilio', 1),
(55, 4, 'recepcionista', 'Administración de citas y recepción de clientes', 1),
(56, 5, 'cajero', 'Acceso a caja, cobros y ventas del negocio', 1),
(57, 5, 'estilista', 'Especialista autorizado en estilismo y citas', 1),
(58, 5, 'repartidor', 'Encargado de la entrega de pedidos a domicilio', 1),
(59, 5, 'recepcionista', 'Administración de citas y recepción de clientes', 1);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `sedes`
--

CREATE TABLE `sedes` (
  `id_sedes` int(11) NOT NULL,
  `id_tenants` int(11) NOT NULL,
  `nombre_sede` varchar(255) NOT NULL,
  `direccion` varchar(255) DEFAULT NULL,
  `distrito` varchar(100) DEFAULT NULL,
  `telefono` varchar(15) DEFAULT NULL,
  `responsable` varchar(255) DEFAULT NULL,
  `estado` tinyint(1) DEFAULT 1
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `sedes`
--

INSERT INTO `sedes` (`id_sedes`, `id_tenants`, `nombre_sede`, `direccion`, `distrito`, `telefono`, `responsable`, `estado`) VALUES
(1, 1, 'Sede Central', 'Av. La Marina 123', 'San Miguel', '01-3456789', 'Ana Torres', 1),
(2, 1, 'Sede Miraflores', 'Calle Berlín 456', 'Miraflores', '01-4567890', 'Luis Vargas', 1),
(3, 1, 'Sede San Borja', 'Av. San Luis 789', 'San Borja', '01-5678901', 'María Quispe', 1),
(28, 2, 'Sede 1 Tenant 2', 'Dirección de Sede 1', 'Distrito 1', '987654321', 'Responsable Sede 1', 1),
(29, 2, 'Sede 2 Tenant 2', 'Dirección de Sede 2', 'Distrito 2', '987654322', 'Responsable Sede 2', 1),
(30, 2, 'Sede 3 Tenant 2', 'Dirección de Sede 3', 'Distrito 3', '987654323', 'Responsable Sede 3', 1),
(31, 3, 'Sede 1 Tenant 3', 'Dirección de Sede 1', 'Distrito 1', '987654321', 'Responsable Sede 1', 1),
(32, 3, 'Sede 2 Tenant 3', 'Dirección de Sede 2', 'Distrito 2', '987654322', 'Responsable Sede 2', 1),
(33, 3, 'Sede 3 Tenant 3', 'Dirección de Sede 3', 'Distrito 3', '987654323', 'Responsable Sede 3', 1),
(34, 4, 'Sede 1 Tenant 4', 'Dirección de Sede 1', 'Distrito 1', '987654321', 'Responsable Sede 1', 1),
(35, 4, 'Sede 2 Tenant 4', 'Dirección de Sede 2', 'Distrito 2', '987654322', 'Responsable Sede 2', 1),
(36, 4, 'Sede 3 Tenant 4', 'Dirección de Sede 3', 'Distrito 3', '987654323', 'Responsable Sede 3', 1),
(37, 5, 'Sede 1 Tenant 5', 'Dirección de Sede 1', 'Distrito 1', '987654321', 'Responsable Sede 1', 1),
(38, 5, 'Sede 2 Tenant 5', 'Dirección de Sede 2', 'Distrito 2', '987654322', 'Responsable Sede 2', 1),
(39, 5, 'Sede 3 Tenant 5', 'Dirección de Sede 3', 'Distrito 3', '987654323', 'Responsable Sede 3', 1);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `series_comprobantes`
--

CREATE TABLE `series_comprobantes` (
  `id_series_comprobantes` int(11) NOT NULL,
  `id_tenants` int(11) NOT NULL,
  `tipo_comprobante` enum('boleta','factura','nota_credito','nota_debito') DEFAULT NULL,
  `punto_emision` int(11) DEFAULT NULL,
  `numero_serie` varchar(10) DEFAULT NULL,
  `numero_proximo` int(11) DEFAULT 1,
  `fecha_autorizacion` date DEFAULT NULL,
  `fecha_vencimiento` date DEFAULT NULL,
  `estado` tinyint(1) DEFAULT 1
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `series_comprobantes`
--

INSERT INTO `series_comprobantes` (`id_series_comprobantes`, `id_tenants`, `tipo_comprobante`, `punto_emision`, `numero_serie`, `numero_proximo`, `fecha_autorizacion`, `fecha_vencimiento`, `estado`) VALUES
(1, 1, 'boleta', 1, 'B001', 1, '2026-01-01', NULL, 1),
(2, 1, 'factura', 1, 'F001', 1, '2026-01-01', NULL, 1),
(3, 1, 'boleta', 2, 'B002', 1, '2026-01-01', NULL, 1),
(28, 2, 'boleta', 1, 'B002', 1, NULL, NULL, 1),
(29, 2, 'factura', 1, 'F002', 1, NULL, NULL, 1),
(30, 2, 'nota_credito', 1, 'C002', 1, NULL, NULL, 1),
(31, 3, 'boleta', 1, 'B003', 1, NULL, NULL, 1),
(32, 3, 'factura', 1, 'F003', 1, NULL, NULL, 1),
(33, 3, 'nota_credito', 1, 'C003', 1, NULL, NULL, 1),
(34, 4, 'boleta', 1, 'B004', 1, NULL, NULL, 1),
(35, 4, 'factura', 1, 'F004', 1, NULL, NULL, 1),
(36, 4, 'nota_credito', 1, 'C004', 1, NULL, NULL, 1),
(37, 5, 'boleta', 1, 'B005', 1, NULL, NULL, 1),
(38, 5, 'factura', 1, 'F005', 1, NULL, NULL, 1),
(39, 5, 'nota_credito', 1, 'C005', 1, NULL, NULL, 1);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `servicios_belleza`
--

CREATE TABLE `servicios_belleza` (
  `id_servicios_belleza` int(11) NOT NULL,
  `id_tenants` int(11) NOT NULL,
  `id_categorias_servicios` int(11) DEFAULT NULL,
  `nombre_servicio_belleza` varchar(255) NOT NULL,
  `descripcion` text DEFAULT NULL,
  `duracion_minima` int(11) DEFAULT NULL,
  `duracion_maxima` int(11) DEFAULT NULL,
  `precio_base` decimal(10,2) DEFAULT NULL,
  `precio_editable` tinyint(1) DEFAULT 0,
  `estado` tinyint(1) DEFAULT 1
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `servicios_belleza`
--

INSERT INTO `servicios_belleza` (`id_servicios_belleza`, `id_tenants`, `id_categorias_servicios`, `nombre_servicio_belleza`, `descripcion`, `duracion_minima`, `duracion_maxima`, `precio_base`, `precio_editable`, `estado`) VALUES
(1, 1, 1, 'Corte de Cabello Dama', 'Corte personalizado con lavado y secado', 45, 60, 45.00, 1, 1),
(2, 1, 2, 'Mechas Californianas', 'Técnica de iluminación degradada', 120, 180, 180.00, 1, 1),
(3, 1, 3, 'Keratina Brasileña', 'Tratamiento alisador con keratina orgánica', 90, 150, 120.00, 0, 1),
(28, 2, 40, 'Servicio 1 Tenant 2', 'Descripción del servicio exclusivo 1 para el tenant 2', 30, 60, 50.00, 0, 1),
(29, 2, 41, 'Servicio 2 Tenant 2', 'Descripción del servicio exclusivo 2 para el tenant 2', 60, 120, 100.00, 0, 1),
(30, 2, 42, 'Servicio 3 Tenant 2', 'Descripción del servicio exclusivo 3 para el tenant 2', 90, 180, 150.00, 0, 1),
(31, 3, 43, 'Servicio 1 Tenant 3', 'Descripción del servicio exclusivo 1 para el tenant 3', 30, 60, 50.00, 0, 1),
(32, 3, 44, 'Servicio 2 Tenant 3', 'Descripción del servicio exclusivo 2 para el tenant 3', 60, 120, 100.00, 0, 1),
(33, 3, 45, 'Servicio 3 Tenant 3', 'Descripción del servicio exclusivo 3 para el tenant 3', 90, 180, 150.00, 0, 1),
(34, 4, 46, 'Servicio 1 Tenant 4', 'Descripción del servicio exclusivo 1 para el tenant 4', 30, 60, 50.00, 0, 1),
(35, 4, 47, 'Servicio 2 Tenant 4', 'Descripción del servicio exclusivo 2 para el tenant 4', 60, 120, 100.00, 0, 1),
(36, 4, 48, 'Servicio 3 Tenant 4', 'Descripción del servicio exclusivo 3 para el tenant 4', 90, 180, 150.00, 0, 1),
(37, 5, 49, 'Servicio 1 Tenant 5', 'Descripción del servicio exclusivo 1 para el tenant 5', 30, 60, 50.00, 0, 1),
(38, 5, 50, 'Servicio 2 Tenant 5', 'Descripción del servicio exclusivo 2 para el tenant 5', 60, 120, 100.00, 0, 1),
(39, 5, 51, 'Servicio 3 Tenant 5', 'Descripción del servicio exclusivo 3 para el tenant 5', 90, 180, 150.00, 0, 1);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `servicios_cita`
--

CREATE TABLE `servicios_cita` (
  `id_servicios_cita` int(11) NOT NULL,
  `id_citas` int(11) NOT NULL,
  `id_servicios_belleza` int(11) NOT NULL,
  `precio` decimal(10,2) DEFAULT NULL,
  `observaciones` text DEFAULT NULL,
  `fecha_registro` timestamp NULL DEFAULT current_timestamp()
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `servicios_cita`
--

INSERT INTO `servicios_cita` (`id_servicios_cita`, `id_citas`, `id_servicios_belleza`, `precio`, `observaciones`, `fecha_registro`) VALUES
(1, 1, 1, 45.00, 'Corte + lavado', '2026-06-02 02:52:12'),
(2, 2, 2, 200.00, 'Mechas + tratamiento post-color', '2026-06-02 02:52:12'),
(3, 3, 3, 130.00, 'Keratina express 90 min', '2026-06-02 02:52:12'),
(28, 28, 28, NULL, NULL, '2026-07-05 01:39:13'),
(29, 29, 29, NULL, NULL, '2026-07-05 01:39:13'),
(30, 30, 30, NULL, NULL, '2026-07-05 01:39:13'),
(31, 31, 31, NULL, NULL, '2026-07-05 01:39:14'),
(32, 32, 32, NULL, NULL, '2026-07-05 01:39:14'),
(33, 33, 33, NULL, NULL, '2026-07-05 01:39:14'),
(34, 34, 34, NULL, NULL, '2026-07-05 01:39:14'),
(35, 35, 35, NULL, NULL, '2026-07-05 01:39:14'),
(36, 36, 36, NULL, NULL, '2026-07-05 01:39:14'),
(37, 37, 37, NULL, NULL, '2026-07-05 01:39:14'),
(38, 38, 38, NULL, NULL, '2026-07-05 01:39:14'),
(39, 39, 39, NULL, NULL, '2026-07-05 01:39:14'),
(40, 43, 2, 180.00, 'Reservado online', '2026-07-09 22:08:31'),
(41, 44, 3, 120.00, 'Reservado online', '2026-07-10 03:56:24');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `sesiones_caja`
--

CREATE TABLE `sesiones_caja` (
  `id_sesiones_caja` int(11) NOT NULL,
  `id_sedes` int(11) NOT NULL,
  `id_usuarios` int(11) NOT NULL,
  `fecha_apertura` timestamp NULL DEFAULT current_timestamp(),
  `fecha_cierre` timestamp NULL DEFAULT NULL,
  `monto_inicial` decimal(10,2) DEFAULT NULL,
  `monto_final` decimal(10,2) DEFAULT NULL,
  `diferencia` decimal(10,2) DEFAULT NULL,
  `estado` tinyint(1) DEFAULT 1,
  `observaciones` text DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `sesiones_caja`
--

INSERT INTO `sesiones_caja` (`id_sesiones_caja`, `id_sedes`, `id_usuarios`, `fecha_apertura`, `fecha_cierre`, `monto_inicial`, `monto_final`, `diferencia`, `estado`, `observaciones`) VALUES
(1, 1, 2, '2026-06-01 14:00:00', NULL, 200.00, NULL, NULL, 1, NULL),
(2, 1, 2, '2026-05-31 14:00:00', NULL, 200.00, NULL, NULL, 1, NULL),
(3, 1, 2, '2026-06-02 14:00:00', NULL, 200.00, NULL, NULL, 1, NULL),
(28, 28, 28, '2026-07-05 01:39:13', NULL, 100.00, NULL, NULL, 1, NULL),
(29, 29, 29, '2026-07-05 01:39:13', NULL, 100.00, NULL, NULL, 1, NULL),
(30, 30, 30, '2026-07-05 01:39:13', NULL, 100.00, NULL, NULL, 1, NULL),
(31, 31, 31, '2026-07-05 01:39:14', NULL, 100.00, NULL, NULL, 1, NULL),
(32, 32, 32, '2026-07-05 01:39:14', NULL, 100.00, NULL, NULL, 1, NULL),
(33, 33, 33, '2026-07-05 01:39:14', NULL, 100.00, NULL, NULL, 1, NULL),
(34, 34, 34, '2026-07-05 01:39:14', NULL, 100.00, NULL, NULL, 1, NULL),
(35, 35, 35, '2026-07-05 01:39:14', NULL, 100.00, NULL, NULL, 1, NULL),
(36, 36, 36, '2026-07-05 01:39:14', NULL, 100.00, NULL, NULL, 1, NULL),
(37, 37, 37, '2026-07-05 01:39:14', NULL, 100.00, NULL, NULL, 1, NULL),
(38, 38, 38, '2026-07-05 01:39:14', NULL, 100.00, NULL, NULL, 1, NULL),
(39, 39, 39, '2026-07-05 01:39:14', NULL, 100.00, NULL, NULL, 1, NULL);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `suscripciones`
--

CREATE TABLE `suscripciones` (
  `id_suscripciones` int(11) NOT NULL,
  `id_tenants` int(11) NOT NULL,
  `id_planes_suscripcion` int(11) NOT NULL,
  `fecha_inicio` date NOT NULL,
  `fecha_proximo_pago` date NOT NULL,
  `estado` tinyint(1) DEFAULT 1,
  `precio_contratado` decimal(10,2) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `suscripciones`
--

INSERT INTO `suscripciones` (`id_suscripciones`, `id_tenants`, `id_planes_suscripcion`, `fecha_inicio`, `fecha_proximo_pago`, `estado`, `precio_contratado`) VALUES
(1, 1, 3, '2026-01-01', '2026-07-01', 1, 129.90),
(28, 2, 1, '2026-07-05', '2026-08-05', 1, 99.90),
(29, 2, 2, '2026-07-05', '2026-08-05', 1, 199.80),
(30, 2, 3, '2026-07-05', '2026-08-05', 1, 299.70),
(31, 3, 1, '2026-07-05', '2026-08-05', 1, 99.90),
(32, 3, 2, '2026-07-05', '2026-08-05', 1, 199.80),
(33, 3, 3, '2026-07-05', '2026-08-05', 1, 299.70),
(34, 4, 1, '2026-07-05', '2026-08-05', 1, 99.90),
(35, 4, 2, '2026-07-05', '2026-08-05', 1, 199.80),
(36, 4, 3, '2026-07-05', '2026-08-05', 1, 299.70),
(37, 5, 1, '2026-07-05', '2026-08-05', 1, 99.90),
(38, 5, 2, '2026-07-05', '2026-08-05', 1, 199.80),
(39, 5, 3, '2026-07-05', '2026-08-05', 1, 299.70);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `tenants`
--

CREATE TABLE `tenants` (
  `id_tenants` int(11) NOT NULL,
  `razon_social` varchar(255) NOT NULL,
  `ruc` varchar(20) NOT NULL,
  `direccion_fiscal` varchar(255) DEFAULT NULL,
  `correo` varchar(100) NOT NULL,
  `telefono` varchar(15) DEFAULT NULL,
  `nombre_comercial` varchar(255) DEFAULT NULL,
  `tipo_negocio` varchar(100) DEFAULT NULL,
  `estado` tinyint(1) DEFAULT 1,
  `fecha_registro` timestamp NULL DEFAULT current_timestamp()
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `tenants`
--

INSERT INTO `tenants` (`id_tenants`, `razon_social`, `ruc`, `direccion_fiscal`, `correo`, `telefono`, `nombre_comercial`, `tipo_negocio`, `estado`, `fecha_registro`) VALUES
(1, 'Salon Bellarista S.A.C.', '20601234561', 'Av. La Marina 123, Lima', 'contacto@bellarista.pe', '01-3456789', 'Bellarista', 'Salón de belleza', 1, '2026-06-02 02:52:12'),
(2, 'Glamour Studio E.I.R.L.', '20601234562', 'Jr. Miraflores 456, Tarapoto', 'glamour@studio.pe', '042-123456', 'Glamour Studio V2', 'Spa y estética', 1, '2026-06-02 02:52:12'),
(3, 'Beauty Plus S.A.C.', '20601234563', 'Calle Los Pinos 789, Trujillo', 'info@beautyplus.pe', '044-654321', 'Beauty Plus', 'Centro de belleza', 1, '2026-06-02 02:52:12'),
(4, 'Aura Medicina Estetica S.A.C.', '20609999003', 'Av. Primavera 1010, Santiago de Surco, Lima', 'citas@auramedicina.pe', '987654323', 'Aura Estética', 'Clínica de Estética', 1, '2026-07-05 01:35:15'),
(5, 'Bellarista Express S.A.', '20609999004', 'Jr. de la Union 450, Cercado de Lima, Lima', 'express@bellarista.pe', '987654324', 'Bellarista Express', 'Venta de Cosméticos', 1, '2026-07-05 01:35:15');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `usuarios`
--

CREATE TABLE `usuarios` (
  `id_usuarios` int(11) NOT NULL,
  `id_tenants` int(11) NOT NULL,
  `nombre_usuario` varchar(255) NOT NULL,
  `apellidos_usuario` varchar(255) NOT NULL,
  `correo` varchar(100) NOT NULL,
  `numero_documento` varchar(20) DEFAULT NULL,
  `contraseña` varchar(255) NOT NULL,
  `tipo_usuario` enum('superadmin','admin','cajero','recepcionista','especialista','estilista','gerente','repartidor','otro') DEFAULT 'otro',
  `estado` tinyint(1) DEFAULT 1
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `usuarios`
--

INSERT INTO `usuarios` (`id_usuarios`, `id_tenants`, `nombre_usuario`, `apellidos_usuario`, `correo`, `numero_documento`, `contraseña`, `tipo_usuario`, `estado`) VALUES
(1, 1, 'Ana', 'Torres Mendoza', 'ana.torres@bellarista.pe', '45123456', '123', 'admin', 1),
(2, 1, 'Carlos', 'Ríos Huanca', 'carlos.rios@bellarista.pe', '47896541', '123', 'cajero', 1),
(3, 1, 'Pedro', 'Salinas López', 'pedro.salinas@bellarista.pe', '46321478', '123', 'repartidor', 1),
(28, 2, 'User1T2', 'Apellidos User 1', 'user1.t2@bellarista.pe', '77665521', '123', 'admin', 1),
(29, 2, 'User2T2', 'Apellidos User 2', 'user2.t2@bellarista.pe', '77665522', '123', 'cajero', 1),
(30, 2, 'User3T2', 'Apellidos User 3', 'user3.t2@bellarista.pe', '77665523', '123', 'estilista', 1),
(31, 3, 'User1T3', 'Apellidos User 1', 'user1.t3@bellarista.pe', '77665531', '123', 'admin', 1),
(32, 3, 'User2T3', 'Apellidos User 2', 'user2.t3@bellarista.pe', '77665532', '123', 'cajero', 1),
(33, 3, 'User3T3', 'Apellidos User 3', 'user3.t3@bellarista.pe', '77665533', '123', 'estilista', 1),
(34, 4, 'User1T4', 'Apellidos User 1', 'user1.t4@bellarista.pe', '77665541', '123', 'admin', 1),
(35, 4, 'User2T4', 'Apellidos User 2', 'user2.t4@bellarista.pe', '77665542', '123', 'cajero', 1),
(36, 4, 'User3T4', 'Apellidos User 3', 'user3.t4@bellarista.pe', '77665543', '123', 'estilista', 1),
(37, 5, 'User1T5', 'Apellidos User 1', 'user1.t5@bellarista.pe', '77665551', '123', 'admin', 1),
(38, 5, 'User2T5', 'Apellidos User 2', 'user2.t5@bellarista.pe', '77665552', '123', 'cajero', 1),
(39, 5, 'User3T5', 'Apellidos User 3', 'user3.t5@bellarista.pe', '77665553', '123', 'estilista', 1);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `usuario_sedes`
--

CREATE TABLE `usuario_sedes` (
  `id_usuario_sedes` int(11) NOT NULL,
  `id_usuarios` int(11) NOT NULL,
  `id_sedes` int(11) NOT NULL,
  `fecha_asignacion` timestamp NULL DEFAULT current_timestamp()
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `usuario_sedes`
--

INSERT INTO `usuario_sedes` (`id_usuario_sedes`, `id_usuarios`, `id_sedes`, `fecha_asignacion`) VALUES
(1, 1, 1, '2026-06-02 02:52:12'),
(2, 2, 1, '2026-06-02 02:52:12'),
(3, 3, 1, '2026-06-02 02:52:12');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `ventas`
--

CREATE TABLE `ventas` (
  `id_ventas` int(11) NOT NULL,
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
  `fecha_venta` timestamp NULL DEFAULT current_timestamp()
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `ventas`
--

INSERT INTO `ventas` (`id_ventas`, `id_tenants`, `id_sedes`, `id_sesiones_caja`, `id_clientes`, `numero_ticket`, `comprobante_numero`, `tipo_comprobante`, `subtotal`, `descuento`, `impuesto`, `total`, `estado`, `estado_sunat`, `id_usuarios`, `fecha_venta`) VALUES
(1, 1, 1, 1, 1, 'T-0001', 'B001-00001', 'boleta', 29.66, 0.00, 5.34, 35.00, 1, 'pendiente', 2, '2026-06-02 02:52:12'),
(2, 1, 1, 1, 2, 'T-0002', 'B001-00002', 'boleta', 35.59, 0.00, 6.41, 42.00, 1, 'pendiente', 2, '2026-06-02 02:52:12'),
(3, 1, 1, 1, 3, 'T-0003', 'F001-00001', 'factura', 23.73, 0.00, 4.27, 28.00, 1, 'pendiente', 2, '2026-06-02 02:52:12'),
(4, 1, 1, 1, 4, 'TK-1783057852154', 'C-1783057852154', 'boleta', 533.90, 0.00, 96.10, 630.00, 1, 'aceptada', NULL, '2026-07-03 05:50:52'),
(5, 1, 1, 1, 4, 'TK-1783058052999', 'C-1783058052999', 'boleta', 533.90, 0.00, 96.10, 630.00, 1, 'aceptada', NULL, '2026-07-03 05:54:12'),
(6, 1, 1, 1, 4, 'TK-1783060231042', 'C-1783060231042', 'boleta', 33.90, 0.00, 6.10, 40.00, 1, 'aceptada', NULL, '2026-07-03 06:30:31'),
(7, 1, 1, 1, 4, 'TK-1783060748520', 'C-1783060748520', 'boleta', 33.90, 0.00, 6.10, 40.00, 1, 'aceptada', NULL, '2026-07-03 06:39:08'),
(8, 1, 1, 1, 4, 'TK-1783060946957', 'C-1783060946957', 'boleta', 47.46, 0.00, 8.54, 56.00, 1, 'aceptada', NULL, '2026-07-03 06:42:26'),
(9, 1, 1, 1, 4, 'TK-1783061234842', 'C-1783061234842', 'boleta', 148.31, 0.00, 26.69, 175.00, 1, 'aceptada', NULL, '2026-07-03 06:47:14'),
(10, 1, 1, 1, 4, 'TK-1783061335966', 'C-1783061335966', 'boleta', 8.47, 0.00, 1.53, 10.00, 1, 'aceptada', NULL, '2026-07-03 06:48:55'),
(11, 1, 1, 1, 4, 'TK-1783061652910', 'C-1783061652910', 'boleta', 29.66, 0.00, 5.34, 35.00, 1, 'aceptada', NULL, '2026-07-03 06:54:12'),
(36, 2, 28, 28, 29, 'TKT-2-1-1783215553', 'FAC-2-1-1783215553', 'boleta', 50.85, 0.00, 9.15, 60.00, 1, 'aceptada', 28, '2026-07-05 01:39:13'),
(37, 2, 29, 29, 30, 'TKT-2-2-1783215553', 'FAC-2-2-1783215553', 'factura', 101.70, 0.00, 18.31, 120.01, 1, 'aceptada', 29, '2026-07-05 01:39:13'),
(38, 2, 30, 30, 31, 'TKT-2-3-1783215553', 'FAC-2-3-1783215553', 'boleta', 152.55, 0.00, 27.46, 180.01, 1, 'aceptada', 30, '2026-07-05 01:39:13'),
(39, 3, 31, 31, 32, 'TKT-3-1-1783215554', 'FAC-3-1-1783215554', 'boleta', 50.85, 0.00, 9.15, 60.00, 1, 'aceptada', 31, '2026-07-05 01:39:14'),
(40, 3, 32, 32, 33, 'TKT-3-2-1783215554', 'FAC-3-2-1783215554', 'factura', 101.70, 0.00, 18.31, 120.01, 1, 'aceptada', 32, '2026-07-05 01:39:14'),
(41, 3, 33, 33, 34, 'TKT-3-3-1783215554', 'FAC-3-3-1783215554', 'boleta', 152.55, 0.00, 27.46, 180.01, 1, 'aceptada', 33, '2026-07-05 01:39:14'),
(42, 4, 34, 34, 35, 'TKT-4-1-1783215554', 'FAC-4-1-1783215554', 'boleta', 50.85, 0.00, 9.15, 60.00, 1, 'aceptada', 34, '2026-07-05 01:39:14'),
(43, 4, 35, 35, 36, 'TKT-4-2-1783215554', 'FAC-4-2-1783215554', 'factura', 101.70, 0.00, 18.31, 120.01, 1, 'aceptada', 35, '2026-07-05 01:39:14'),
(44, 4, 36, 36, 37, 'TKT-4-3-1783215554', 'FAC-4-3-1783215554', 'boleta', 152.55, 0.00, 27.46, 180.01, 1, 'aceptada', 36, '2026-07-05 01:39:14'),
(45, 5, 37, 37, 38, 'TKT-5-1-1783215554', 'FAC-5-1-1783215554', 'boleta', 50.85, 0.00, 9.15, 60.00, 1, 'aceptada', 37, '2026-07-05 01:39:14'),
(46, 5, 38, 38, 39, 'TKT-5-2-1783215554', 'FAC-5-2-1783215554', 'factura', 101.70, 0.00, 18.31, 120.01, 1, 'aceptada', 38, '2026-07-05 01:39:14'),
(47, 5, 39, 39, 40, 'TKT-5-3-1783215554', 'FAC-5-3-1783215554', 'boleta', 152.55, 0.00, 27.46, 180.01, 1, 'aceptada', 39, '2026-07-05 01:39:14'),
(48, 1, 1, 1, 4, 'TK-1783634066743', 'C-1783634066744', 'boleta', 38.14, 0.00, 6.86, 45.00, 1, 'aceptada', NULL, '2026-07-09 21:54:26'),
(51, 1, 1, 1, 4, 'TK-1783634911749', 'C-1783634911750', 'boleta', 152.54, 0.00, 27.46, 180.00, 1, 'aceptada', NULL, '2026-07-09 22:08:31'),
(52, 1, 1, 1, 4, 'TK-1783648351113', 'C-1783648351113', 'boleta', 148.31, 0.00, 26.69, 175.00, 1, 'aceptada', NULL, '2026-07-10 01:52:31'),
(53, 1, 1, 1, 4, 'TK-1783654704508', 'C-1783654704508', 'boleta', 76.27, 0.00, 13.73, 90.00, 1, 'aceptada', NULL, '2026-07-10 03:38:24'),
(54, 1, 1, 1, 4, 'TKT-00000028', 'B001-00000028', 'boleta', 150.00, 0.00, 27.00, 177.00, 1, 'aceptada', 1, '2026-07-10 04:52:16'),
(55, 1, 1, 31, 4, 'TKT-00000029', 'B001-00000029', 'boleta', 175.00, 0.00, 31.50, 206.50, 1, 'aceptada', 1, '2026-07-10 05:03:54'),
(56, 1, 1, 33, 4, 'TKT-00000030', 'B001-00000030', 'boleta', 105.00, 0.00, 18.90, 123.90, 1, 'aceptada', 1, '2026-07-10 05:04:40'),
(57, 1, 1, 36, 4, 'TKT-00000031', 'B001-00000031', 'boleta', 175.00, 0.00, 31.50, 206.50, 1, 'aceptada', 1, '2026-07-10 05:11:04');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `zonas_delivery`
--

CREATE TABLE `zonas_delivery` (
  `id_zonas_delivery` int(11) NOT NULL,
  `id_sedes` int(11) NOT NULL,
  `nombre_zona` varchar(100) DEFAULT NULL,
  `distritos` varchar(500) DEFAULT NULL,
  `costo_fijo` decimal(10,2) DEFAULT NULL,
  `monto_minimo_compra` decimal(10,2) DEFAULT NULL,
  `tiempo_estimado_minutos` int(11) DEFAULT NULL,
  `estado` tinyint(1) DEFAULT 1
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `zonas_delivery`
--

INSERT INTO `zonas_delivery` (`id_zonas_delivery`, `id_sedes`, `nombre_zona`, `distritos`, `costo_fijo`, `monto_minimo_compra`, `tiempo_estimado_minutos`, `estado`) VALUES
(1, 1, 'Zona Centro', 'San Miguel, Pueblo Libre, Magdalena', 8.00, 50.00, 30, 1),
(2, 1, 'Zona Moderna', 'Miraflores, San Isidro, Surco', 10.00, 60.00, 45, 1),
(3, 1, 'Zona Periférica', 'Callao, Los Olivos, Independencia', 15.00, 80.00, 60, 1),
(28, 28, 'Zona Delivery Sede 1', 'Distrito A, Distrito B', 10.00, 50.00, 45, 1),
(29, 29, 'Zona Delivery Sede 2', 'Distrito A, Distrito B', 10.00, 50.00, 45, 1),
(30, 30, 'Zona Delivery Sede 3', 'Distrito A, Distrito B', 10.00, 50.00, 45, 1),
(31, 31, 'Zona Delivery Sede 1', 'Distrito A, Distrito B', 10.00, 50.00, 45, 1),
(32, 32, 'Zona Delivery Sede 2', 'Distrito A, Distrito B', 10.00, 50.00, 45, 1),
(33, 33, 'Zona Delivery Sede 3', 'Distrito A, Distrito B', 10.00, 50.00, 45, 1),
(34, 34, 'Zona Delivery Sede 1', 'Distrito A, Distrito B', 10.00, 50.00, 45, 1),
(35, 35, 'Zona Delivery Sede 2', 'Distrito A, Distrito B', 10.00, 50.00, 45, 1),
(36, 36, 'Zona Delivery Sede 3', 'Distrito A, Distrito B', 10.00, 50.00, 45, 1),
(37, 37, 'Zona Delivery Sede 1', 'Distrito A, Distrito B', 10.00, 50.00, 45, 1),
(38, 38, 'Zona Delivery Sede 2', 'Distrito A, Distrito B', 10.00, 50.00, 45, 1),
(39, 39, 'Zona Delivery Sede 3', 'Distrito A, Distrito B', 10.00, 50.00, 45, 1);

--
-- Índices para tablas volcadas
--

--
-- Indices de la tabla `almacenes`
--
ALTER TABLE `almacenes`
  ADD PRIMARY KEY (`id_almacenes`),
  ADD KEY `idx_sede_id` (`id_sedes`),
  ADD KEY `idx_tenant_id` (`id_tenants`);

--
-- Indices de la tabla `auditoria`
--
ALTER TABLE `auditoria`
  ADD PRIMARY KEY (`id_auditoria`),
  ADD KEY `idx_tenant_id` (`id_tenants`),
  ADD KEY `idx_usuario_id` (`id_usuarios`),
  ADD KEY `idx_fecha_hora` (`fecha_hora`),
  ADD KEY `idx_accion` (`accion`);

--
-- Indices de la tabla `branding_negocio`
--
ALTER TABLE `branding_negocio`
  ADD PRIMARY KEY (`id_branding_negocio`),
  ADD UNIQUE KEY `unique_tenant` (`id_tenants`),
  ADD KEY `idx_tenant_id` (`id_tenants`);

--
-- Indices de la tabla `caja_chica`
--
ALTER TABLE `caja_chica`
  ADD PRIMARY KEY (`id_caja_chica`),
  ADD KEY `id_usuarios` (`id_usuarios`),
  ADD KEY `idx_sesion_caja_id` (`id_sesiones_caja`);

--
-- Indices de la tabla `categorias_productos`
--
ALTER TABLE `categorias_productos`
  ADD PRIMARY KEY (`id_categorias_productos`),
  ADD KEY `idx_tenant_id` (`id_tenants`),
  ADD KEY `idx_estado` (`estado`);

--
-- Indices de la tabla `categorias_servicios`
--
ALTER TABLE `categorias_servicios`
  ADD PRIMARY KEY (`id_categorias_servicios`),
  ADD KEY `idx_tenant_id` (`id_tenants`),
  ADD KEY `idx_estado` (`estado`);

--
-- Indices de la tabla `citas`
--
ALTER TABLE `citas`
  ADD PRIMARY KEY (`id_citas`),
  ADD KEY `id_sedes` (`id_sedes`),
  ADD KEY `id_usuarios_usuario_creacion` (`id_usuarios_usuario_creacion`),
  ADD KEY `idx_tenant_id` (`id_tenants`),
  ADD KEY `idx_fecha_cita` (`fecha_cita`),
  ADD KEY `idx_estado` (`estado`),
  ADD KEY `idx_cita_cliente` (`id_clientes`),
  ADD KEY `idx_cita_especialista` (`id_usuarios_especialista`),
  ADD KEY `fk_citas_ventas` (`id_ventas`);

--
-- Indices de la tabla `clientes`
--
ALTER TABLE `clientes`
  ADD PRIMARY KEY (`id_clientes`),
  ADD KEY `idx_tenant_id` (`id_tenants`),
  ADD KEY `idx_numero_documento` (`numero_documento`),
  ADD KEY `idx_estado` (`estado`);

--
-- Indices de la tabla `combos_promocionales`
--
ALTER TABLE `combos_promocionales`
  ADD PRIMARY KEY (`id_combos_promocionales`),
  ADD KEY `idx_tenant_id` (`id_tenants`),
  ADD KEY `idx_estado` (`estado`);

--
-- Indices de la tabla `composicion_combo`
--
ALTER TABLE `composicion_combo`
  ADD PRIMARY KEY (`id_composicion_combo`),
  ADD KEY `id_productos` (`id_productos`),
  ADD KEY `idx_combo_id` (`id_combos_promocionales`);

--
-- Indices de la tabla `comprobantes_electronicos`
--
ALTER TABLE `comprobantes_electronicos`
  ADD PRIMARY KEY (`id_comprobantes_electronicos`),
  ADD UNIQUE KEY `unique_comprobante` (`id_tenants`,`numero_serie`,`numero_comprobante`),
  ADD KEY `id_ventas` (`id_ventas`),
  ADD KEY `idx_tenant_id` (`id_tenants`),
  ADD KEY `idx_estado_sunat` (`estado_sunat`);

--
-- Indices de la tabla `configuracion_global`
--
ALTER TABLE `configuracion_global`
  ADD PRIMARY KEY (`id_configuracion_global`),
  ADD UNIQUE KEY `unique_tenant_clave` (`id_tenants`,`clave`),
  ADD KEY `idx_tenant_id` (`id_tenants`);

--
-- Indices de la tabla `cuentas_por_pagar`
--
ALTER TABLE `cuentas_por_pagar`
  ADD PRIMARY KEY (`id_cuentas_por_pagar`),
  ADD KEY `id_proveedores` (`id_proveedores`),
  ADD KEY `idx_tenant_id` (`id_tenants`),
  ADD KEY `idx_estado_pago` (`estado_pago`),
  ADD KEY `idx_fecha_vencimiento` (`fecha_vencimiento`);

--
-- Indices de la tabla `detalle_devolucion_proveedor`
--
ALTER TABLE `detalle_devolucion_proveedor`
  ADD PRIMARY KEY (`id_detalle_devolucion_proveedor`),
  ADD KEY `id_productos` (`id_productos`),
  ADD KEY `id_lotes_inventario` (`id_lotes_inventario`),
  ADD KEY `idx_devolucion_id` (`id_devoluciones_proveedor`);

--
-- Indices de la tabla `detalle_devolucion_venta`
--
ALTER TABLE `detalle_devolucion_venta`
  ADD PRIMARY KEY (`id_detalle_devolucion_venta`),
  ADD KEY `id_productos` (`id_productos`),
  ADD KEY `idx_devolucion_id` (`id_devoluciones_venta`);

--
-- Indices de la tabla `detalle_orden_compra`
--
ALTER TABLE `detalle_orden_compra`
  ADD PRIMARY KEY (`id_detalle_orden_compra`),
  ADD KEY `id_productos` (`id_productos`),
  ADD KEY `idx_orden_compra_id` (`id_ordenes_compra`);

--
-- Indices de la tabla `detalle_pedido`
--
ALTER TABLE `detalle_pedido`
  ADD PRIMARY KEY (`id_detalle_pedido`),
  ADD KEY `id_productos` (`id_productos`),
  ADD KEY `idx_pedido_id` (`id_pedidos`);

--
-- Indices de la tabla `detalle_venta`
--
ALTER TABLE `detalle_venta`
  ADD PRIMARY KEY (`id_detalle_venta`),
  ADD KEY `id_productos` (`id_productos`),
  ADD KEY `id_lotes_inventario` (`id_lotes_inventario`),
  ADD KEY `idx_venta_id` (`id_ventas`);

--
-- Indices de la tabla `devoluciones_proveedor`
--
ALTER TABLE `devoluciones_proveedor`
  ADD PRIMARY KEY (`id_devoluciones_proveedor`),
  ADD UNIQUE KEY `numero_devolucion` (`numero_devolucion`),
  ADD KEY `id_proveedores` (`id_proveedores`),
  ADD KEY `idx_tenant_id` (`id_tenants`),
  ADD KEY `idx_estado` (`estado`);

--
-- Indices de la tabla `devoluciones_venta`
--
ALTER TABLE `devoluciones_venta`
  ADD PRIMARY KEY (`id_devoluciones_venta`),
  ADD UNIQUE KEY `numero_devolucion` (`numero_devolucion`),
  ADD KEY `id_ventas` (`id_ventas`),
  ADD KEY `id_usuarios` (`id_usuarios`),
  ADD KEY `idx_tenant_id` (`id_tenants`),
  ADD KEY `idx_estado_devolucion` (`estado_devolucion`);

--
-- Indices de la tabla `facturas_suscripcion`
--
ALTER TABLE `facturas_suscripcion`
  ADD PRIMARY KEY (`id_facturas_suscripcion`),
  ADD UNIQUE KEY `numero_factura` (`numero_factura`),
  ADD KEY `id_suscripciones` (`id_suscripciones`),
  ADD KEY `idx_estado_pago` (`estado_pago`),
  ADD KEY `idx_fecha_vencimiento` (`fecha_vencimiento`);

--
-- Indices de la tabla `formas_pago_venta`
--
ALTER TABLE `formas_pago_venta`
  ADD PRIMARY KEY (`id_formas_pago_venta`),
  ADD KEY `idx_venta_id` (`id_ventas`);

--
-- Indices de la tabla `gastos_operativos`
--
ALTER TABLE `gastos_operativos`
  ADD PRIMARY KEY (`id_gastos_operativos`),
  ADD KEY `id_sedes` (`id_sedes`),
  ADD KEY `id_proveedores` (`id_proveedores`),
  ADD KEY `id_usuarios_usuario_creacion` (`id_usuarios_usuario_creacion`),
  ADD KEY `id_usuarios_usuario_aprobacion` (`id_usuarios_usuario_aprobacion`),
  ADD KEY `idx_tenant_id` (`id_tenants`),
  ADD KEY `idx_estado` (`estado`),
  ADD KEY `idx_fecha_gasto` (`fecha_gasto`);

--
-- Indices de la tabla `gastos_recurrentes`
--
ALTER TABLE `gastos_recurrentes`
  ADD PRIMARY KEY (`id_gastos_recurrentes`),
  ADD KEY `id_sedes` (`id_sedes`),
  ADD KEY `idx_tenant_id` (`id_tenants`);

--
-- Indices de la tabla `horarios_operacion`
--
ALTER TABLE `horarios_operacion`
  ADD PRIMARY KEY (`id_horarios_operacion`),
  ADD UNIQUE KEY `unique_sede_dia` (`id_sedes`,`dia_semana`),
  ADD KEY `idx_sede_id` (`id_sedes`);

--
-- Indices de la tabla `lotes_inventario`
--
ALTER TABLE `lotes_inventario`
  ADD PRIMARY KEY (`id_lotes_inventario`),
  ADD KEY `idx_producto_id` (`id_productos`),
  ADD KEY `idx_almacen_id` (`id_almacenes`),
  ADD KEY `idx_proveedor_id` (`id_proveedores`),
  ADD KEY `idx_fecha_vencimiento` (`fecha_vencimiento`),
  ADD KEY `idx_estado` (`estado`);

--
-- Indices de la tabla `marcas`
--
ALTER TABLE `marcas`
  ADD PRIMARY KEY (`id_marcas`),
  ADD KEY `idx_tenant_id` (`id_tenants`),
  ADD KEY `idx_estado` (`estado`);

--
-- Indices de la tabla `metodos_pago`
--
ALTER TABLE `metodos_pago`
  ADD PRIMARY KEY (`id_metodos_pago`),
  ADD KEY `idx_tenant_id` (`id_tenants`);

--
-- Indices de la tabla `movimientos_inventario`
--
ALTER TABLE `movimientos_inventario`
  ADD PRIMARY KEY (`id_movimientos_inventario`),
  ADD KEY `id_usuarios` (`id_usuarios`),
  ADD KEY `idx_lote_id` (`id_lotes_inventario`),
  ADD KEY `idx_tipo_movimiento` (`tipo_movimiento`),
  ADD KEY `idx_fecha_movimiento` (`fecha_movimiento`);

--
-- Indices de la tabla `notificaciones`
--
ALTER TABLE `notificaciones`
  ADD PRIMARY KEY (`id_notificaciones`),
  ADD KEY `id_usuarios` (`id_usuarios`),
  ADD KEY `idx_tenant_id` (`id_tenants`),
  ADD KEY `idx_estado_lectura` (`estado_lectura`);

--
-- Indices de la tabla `ordenes_compra`
--
ALTER TABLE `ordenes_compra`
  ADD PRIMARY KEY (`id_ordenes_compra`),
  ADD UNIQUE KEY `numero_orden` (`numero_orden`),
  ADD KEY `idx_tenant_id` (`id_tenants`),
  ADD KEY `idx_proveedor_id` (`id_proveedores`),
  ADD KEY `idx_estado` (`estado`);

--
-- Indices de la tabla `pagos_proveedor`
--
ALTER TABLE `pagos_proveedor`
  ADD PRIMARY KEY (`id_pagos_proveedor`),
  ADD KEY `id_usuarios` (`id_usuarios`),
  ADD KEY `idx_cuenta_id` (`id_cuentas_por_pagar`),
  ADD KEY `idx_fecha_pago` (`fecha_pago`);

--
-- Indices de la tabla `pedidos`
--
ALTER TABLE `pedidos`
  ADD PRIMARY KEY (`id_pedidos`),
  ADD UNIQUE KEY `numero_pedido` (`numero_pedido`),
  ADD KEY `id_usuarios` (`id_usuarios`),
  ADD KEY `id_zonas_delivery` (`id_zonas_delivery`),
  ADD KEY `idx_tenant_id` (`id_tenants`),
  ADD KEY `idx_estado` (`estado`),
  ADD KEY `idx_fecha_pedido` (`fecha_pedido`),
  ADD KEY `idx_pedido_cliente` (`id_clientes`);

--
-- Indices de la tabla `permisos_rol`
--
ALTER TABLE `permisos_rol`
  ADD PRIMARY KEY (`id_permisos_rol`),
  ADD KEY `idx_rol_id` (`id_roles_personalizados`);

--
-- Indices de la tabla `planes_suscripcion`
--
ALTER TABLE `planes_suscripcion`
  ADD PRIMARY KEY (`id_planes_suscripcion`),
  ADD KEY `idx_estado` (`estado`);

--
-- Indices de la tabla `precios_plan`
--
ALTER TABLE `precios_plan`
  ADD PRIMARY KEY (`id_precios_plan`),
  ADD UNIQUE KEY `unique_plan_periodo` (`id_planes_suscripcion`,`periodo`),
  ADD KEY `idx_plan_id` (`id_planes_suscripcion`);

--
-- Indices de la tabla `preferencias_usuario`
--
ALTER TABLE `preferencias_usuario`
  ADD PRIMARY KEY (`id_preferencias_usuario`),
  ADD UNIQUE KEY `unique_usuario` (`id_usuarios`),
  ADD KEY `idx_usuario_id` (`id_usuarios`);

--
-- Indices de la tabla `productos`
--
ALTER TABLE `productos`
  ADD PRIMARY KEY (`id_productos`),
  ADD UNIQUE KEY `codigo_barras` (`codigo_barras`),
  ADD KEY `idx_tenant_id` (`id_tenants`),
  ADD KEY `idx_categoria_id` (`id_categorias_productos`),
  ADD KEY `idx_marca_id` (`id_marcas`),
  ADD KEY `idx_codigo_barras` (`codigo_barras`),
  ADD KEY `idx_estado` (`estado`);

--
-- Indices de la tabla `promociones`
--
ALTER TABLE `promociones`
  ADD PRIMARY KEY (`id_promociones`),
  ADD KEY `id_categorias_productos` (`id_categorias_productos`),
  ADD KEY `idx_tenant_id` (`id_tenants`),
  ADD KEY `idx_estado` (`estado`);

--
-- Indices de la tabla `proveedores`
--
ALTER TABLE `proveedores`
  ADD PRIMARY KEY (`id_proveedores`),
  ADD KEY `idx_tenant_id` (`id_tenants`),
  ADD KEY `idx_estado` (`estado`);

--
-- Indices de la tabla `proveedor_categorias`
--
ALTER TABLE `proveedor_categorias`
  ADD PRIMARY KEY (`id_proveedor_categorias`),
  ADD UNIQUE KEY `unique_proveedor_categoria` (`id_proveedores`,`id_categorias_productos`),
  ADD KEY `idx_proveedor_id` (`id_proveedores`),
  ADD KEY `idx_categoria_id` (`id_categorias_productos`);

--
-- Indices de la tabla `reclamos`
--
ALTER TABLE `reclamos`
  ADD PRIMARY KEY (`id_reclamos`),
  ADD UNIQUE KEY `numero_reclamo` (`numero_reclamo`),
  ADD KEY `id_ventas` (`id_ventas`),
  ADD KEY `id_usuarios_responsable` (`id_usuarios_responsable`),
  ADD KEY `id_usuarios_usuario_creacion` (`id_usuarios_usuario_creacion`),
  ADD KEY `idx_tenant_id` (`id_tenants`),
  ADD KEY `idx_estado` (`estado`),
  ADD KEY `idx_reclamo_cliente` (`id_clientes`);

--
-- Indices de la tabla `registros`
--
ALTER TABLE `registros`
  ADD PRIMARY KEY (`idregistro`);

--
-- Indices de la tabla `repartidores`
--
ALTER TABLE `repartidores`
  ADD PRIMARY KEY (`id_repartidores`),
  ADD UNIQUE KEY `unique_usuario_repartidor` (`id_usuarios`),
  ADD KEY `idx_tenant_id` (`id_tenants`),
  ADD KEY `idx_usuario_id` (`id_usuarios`),
  ADD KEY `idx_estado` (`estado`);

--
-- Indices de la tabla `roles_personalizados`
--
ALTER TABLE `roles_personalizados`
  ADD PRIMARY KEY (`id_roles_personalizados`),
  ADD KEY `idx_tenant_id` (`id_tenants`);

--
-- Indices de la tabla `sedes`
--
ALTER TABLE `sedes`
  ADD PRIMARY KEY (`id_sedes`),
  ADD KEY `idx_tenant_id` (`id_tenants`),
  ADD KEY `idx_estado` (`estado`);

--
-- Indices de la tabla `series_comprobantes`
--
ALTER TABLE `series_comprobantes`
  ADD PRIMARY KEY (`id_series_comprobantes`),
  ADD UNIQUE KEY `unique_serie` (`id_tenants`,`tipo_comprobante`,`punto_emision`,`numero_serie`),
  ADD KEY `idx_tenant_id` (`id_tenants`);

--
-- Indices de la tabla `servicios_belleza`
--
ALTER TABLE `servicios_belleza`
  ADD PRIMARY KEY (`id_servicios_belleza`),
  ADD KEY `idx_tenant_id` (`id_tenants`),
  ADD KEY `idx_categoria_id` (`id_categorias_servicios`),
  ADD KEY `idx_estado` (`estado`);

--
-- Indices de la tabla `servicios_cita`
--
ALTER TABLE `servicios_cita`
  ADD PRIMARY KEY (`id_servicios_cita`),
  ADD KEY `id_servicios_belleza` (`id_servicios_belleza`),
  ADD KEY `idx_cita_id` (`id_citas`);

--
-- Indices de la tabla `sesiones_caja`
--
ALTER TABLE `sesiones_caja`
  ADD PRIMARY KEY (`id_sesiones_caja`),
  ADD KEY `idx_sede_id` (`id_sedes`),
  ADD KEY `idx_usuario_id` (`id_usuarios`),
  ADD KEY `idx_estado` (`estado`);

--
-- Indices de la tabla `suscripciones`
--
ALTER TABLE `suscripciones`
  ADD PRIMARY KEY (`id_suscripciones`),
  ADD KEY `id_planes_suscripcion` (`id_planes_suscripcion`),
  ADD KEY `idx_tenant_id` (`id_tenants`),
  ADD KEY `idx_estado` (`estado`),
  ADD KEY `idx_fecha_proximo_pago` (`fecha_proximo_pago`);

--
-- Indices de la tabla `tenants`
--
ALTER TABLE `tenants`
  ADD PRIMARY KEY (`id_tenants`),
  ADD UNIQUE KEY `ruc` (`ruc`),
  ADD UNIQUE KEY `correo` (`correo`),
  ADD KEY `idx_ruc` (`ruc`),
  ADD KEY `idx_estado` (`estado`);

--
-- Indices de la tabla `usuarios`
--
ALTER TABLE `usuarios`
  ADD PRIMARY KEY (`id_usuarios`),
  ADD UNIQUE KEY `unique_correo_tenant` (`id_tenants`,`correo`),
  ADD KEY `idx_tenant_id` (`id_tenants`),
  ADD KEY `idx_correo` (`correo`),
  ADD KEY `idx_estado` (`estado`);

--
-- Indices de la tabla `usuario_sedes`
--
ALTER TABLE `usuario_sedes`
  ADD PRIMARY KEY (`id_usuario_sedes`),
  ADD UNIQUE KEY `unique_usuario_sede` (`id_usuarios`,`id_sedes`),
  ADD KEY `idx_usuario_id` (`id_usuarios`),
  ADD KEY `idx_sede_id` (`id_sedes`);

--
-- Indices de la tabla `ventas`
--
ALTER TABLE `ventas`
  ADD PRIMARY KEY (`id_ventas`),
  ADD UNIQUE KEY `comprobante_numero` (`comprobante_numero`),
  ADD KEY `id_sesiones_caja` (`id_sesiones_caja`),
  ADD KEY `idx_tenant_id` (`id_tenants`),
  ADD KEY `idx_sede_id` (`id_sedes`),
  ADD KEY `idx_fecha_venta` (`fecha_venta`),
  ADD KEY `idx_estado` (`estado`),
  ADD KEY `idx_venta_cliente` (`id_clientes`),
  ADD KEY `idx_venta_usuario` (`id_usuarios`);

--
-- Indices de la tabla `zonas_delivery`
--
ALTER TABLE `zonas_delivery`
  ADD PRIMARY KEY (`id_zonas_delivery`),
  ADD KEY `idx_sede_id` (`id_sedes`);

--
-- AUTO_INCREMENT de las tablas volcadas
--

--
-- AUTO_INCREMENT de la tabla `almacenes`
--
ALTER TABLE `almacenes`
  MODIFY `id_almacenes` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=40;

--
-- AUTO_INCREMENT de la tabla `auditoria`
--
ALTER TABLE `auditoria`
  MODIFY `id_auditoria` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=48;

--
-- AUTO_INCREMENT de la tabla `branding_negocio`
--
ALTER TABLE `branding_negocio`
  MODIFY `id_branding_negocio` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=20;

--
-- AUTO_INCREMENT de la tabla `caja_chica`
--
ALTER TABLE `caja_chica`
  MODIFY `id_caja_chica` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT de la tabla `categorias_productos`
--
ALTER TABLE `categorias_productos`
  MODIFY `id_categorias_productos` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=53;

--
-- AUTO_INCREMENT de la tabla `categorias_servicios`
--
ALTER TABLE `categorias_servicios`
  MODIFY `id_categorias_servicios` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=52;

--
-- AUTO_INCREMENT de la tabla `citas`
--
ALTER TABLE `citas`
  MODIFY `id_citas` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=45;

--
-- AUTO_INCREMENT de la tabla `clientes`
--
ALTER TABLE `clientes`
  MODIFY `id_clientes` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=41;

--
-- AUTO_INCREMENT de la tabla `combos_promocionales`
--
ALTER TABLE `combos_promocionales`
  MODIFY `id_combos_promocionales` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=42;

--
-- AUTO_INCREMENT de la tabla `composicion_combo`
--
ALTER TABLE `composicion_combo`
  MODIFY `id_composicion_combo` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;

--
-- AUTO_INCREMENT de la tabla `comprobantes_electronicos`
--
ALTER TABLE `comprobantes_electronicos`
  MODIFY `id_comprobantes_electronicos` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=41;

--
-- AUTO_INCREMENT de la tabla `configuracion_global`
--
ALTER TABLE `configuracion_global`
  MODIFY `id_configuracion_global` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=52;

--
-- AUTO_INCREMENT de la tabla `cuentas_por_pagar`
--
ALTER TABLE `cuentas_por_pagar`
  MODIFY `id_cuentas_por_pagar` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=40;

--
-- AUTO_INCREMENT de la tabla `detalle_devolucion_proveedor`
--
ALTER TABLE `detalle_devolucion_proveedor`
  MODIFY `id_detalle_devolucion_proveedor` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT de la tabla `detalle_devolucion_venta`
--
ALTER TABLE `detalle_devolucion_venta`
  MODIFY `id_detalle_devolucion_venta` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT de la tabla `detalle_orden_compra`
--
ALTER TABLE `detalle_orden_compra`
  MODIFY `id_detalle_orden_compra` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT de la tabla `detalle_pedido`
--
ALTER TABLE `detalle_pedido`
  MODIFY `id_detalle_pedido` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT de la tabla `detalle_venta`
--
ALTER TABLE `detalle_venta`
  MODIFY `id_detalle_venta` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=54;

--
-- AUTO_INCREMENT de la tabla `devoluciones_proveedor`
--
ALTER TABLE `devoluciones_proveedor`
  MODIFY `id_devoluciones_proveedor` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=40;

--
-- AUTO_INCREMENT de la tabla `devoluciones_venta`
--
ALTER TABLE `devoluciones_venta`
  MODIFY `id_devoluciones_venta` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=40;

--
-- AUTO_INCREMENT de la tabla `facturas_suscripcion`
--
ALTER TABLE `facturas_suscripcion`
  MODIFY `id_facturas_suscripcion` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT de la tabla `formas_pago_venta`
--
ALTER TABLE `formas_pago_venta`
  MODIFY `id_formas_pago_venta` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT de la tabla `gastos_operativos`
--
ALTER TABLE `gastos_operativos`
  MODIFY `id_gastos_operativos` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=40;

--
-- AUTO_INCREMENT de la tabla `gastos_recurrentes`
--
ALTER TABLE `gastos_recurrentes`
  MODIFY `id_gastos_recurrentes` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=40;

--
-- AUTO_INCREMENT de la tabla `horarios_operacion`
--
ALTER TABLE `horarios_operacion`
  MODIFY `id_horarios_operacion` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT de la tabla `lotes_inventario`
--
ALTER TABLE `lotes_inventario`
  MODIFY `id_lotes_inventario` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT de la tabla `marcas`
--
ALTER TABLE `marcas`
  MODIFY `id_marcas` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=40;

--
-- AUTO_INCREMENT de la tabla `metodos_pago`
--
ALTER TABLE `metodos_pago`
  MODIFY `id_metodos_pago` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=43;

--
-- AUTO_INCREMENT de la tabla `movimientos_inventario`
--
ALTER TABLE `movimientos_inventario`
  MODIFY `id_movimientos_inventario` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7;

--
-- AUTO_INCREMENT de la tabla `notificaciones`
--
ALTER TABLE `notificaciones`
  MODIFY `id_notificaciones` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=40;

--
-- AUTO_INCREMENT de la tabla `ordenes_compra`
--
ALTER TABLE `ordenes_compra`
  MODIFY `id_ordenes_compra` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=40;

--
-- AUTO_INCREMENT de la tabla `pagos_proveedor`
--
ALTER TABLE `pagos_proveedor`
  MODIFY `id_pagos_proveedor` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT de la tabla `pedidos`
--
ALTER TABLE `pedidos`
  MODIFY `id_pedidos` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=41;

--
-- AUTO_INCREMENT de la tabla `permisos_rol`
--
ALTER TABLE `permisos_rol`
  MODIFY `id_permisos_rol` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=110;

--
-- AUTO_INCREMENT de la tabla `planes_suscripcion`
--
ALTER TABLE `planes_suscripcion`
  MODIFY `id_planes_suscripcion` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT de la tabla `precios_plan`
--
ALTER TABLE `precios_plan`
  MODIFY `id_precios_plan` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT de la tabla `preferencias_usuario`
--
ALTER TABLE `preferencias_usuario`
  MODIFY `id_preferencias_usuario` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT de la tabla `productos`
--
ALTER TABLE `productos`
  MODIFY `id_productos` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=41;

--
-- AUTO_INCREMENT de la tabla `promociones`
--
ALTER TABLE `promociones`
  MODIFY `id_promociones` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=41;

--
-- AUTO_INCREMENT de la tabla `proveedores`
--
ALTER TABLE `proveedores`
  MODIFY `id_proveedores` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=40;

--
-- AUTO_INCREMENT de la tabla `proveedor_categorias`
--
ALTER TABLE `proveedor_categorias`
  MODIFY `id_proveedor_categorias` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT de la tabla `reclamos`
--
ALTER TABLE `reclamos`
  MODIFY `id_reclamos` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=40;

--
-- AUTO_INCREMENT de la tabla `registros`
--
ALTER TABLE `registros`
  MODIFY `idregistro` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;

--
-- AUTO_INCREMENT de la tabla `repartidores`
--
ALTER TABLE `repartidores`
  MODIFY `id_repartidores` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=38;

--
-- AUTO_INCREMENT de la tabla `roles_personalizados`
--
ALTER TABLE `roles_personalizados`
  MODIFY `id_roles_personalizados` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=60;

--
-- AUTO_INCREMENT de la tabla `sedes`
--
ALTER TABLE `sedes`
  MODIFY `id_sedes` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=40;

--
-- AUTO_INCREMENT de la tabla `series_comprobantes`
--
ALTER TABLE `series_comprobantes`
  MODIFY `id_series_comprobantes` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=40;

--
-- AUTO_INCREMENT de la tabla `servicios_belleza`
--
ALTER TABLE `servicios_belleza`
  MODIFY `id_servicios_belleza` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=40;

--
-- AUTO_INCREMENT de la tabla `servicios_cita`
--
ALTER TABLE `servicios_cita`
  MODIFY `id_servicios_cita` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=42;

--
-- AUTO_INCREMENT de la tabla `sesiones_caja`
--
ALTER TABLE `sesiones_caja`
  MODIFY `id_sesiones_caja` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=40;

--
-- AUTO_INCREMENT de la tabla `suscripciones`
--
ALTER TABLE `suscripciones`
  MODIFY `id_suscripciones` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=40;

--
-- AUTO_INCREMENT de la tabla `tenants`
--
ALTER TABLE `tenants`
  MODIFY `id_tenants` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=12;

--
-- AUTO_INCREMENT de la tabla `usuarios`
--
ALTER TABLE `usuarios`
  MODIFY `id_usuarios` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=40;

--
-- AUTO_INCREMENT de la tabla `usuario_sedes`
--
ALTER TABLE `usuario_sedes`
  MODIFY `id_usuario_sedes` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT de la tabla `ventas`
--
ALTER TABLE `ventas`
  MODIFY `id_ventas` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=58;

--
-- AUTO_INCREMENT de la tabla `zonas_delivery`
--
ALTER TABLE `zonas_delivery`
  MODIFY `id_zonas_delivery` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=40;

--
-- Restricciones para tablas volcadas
--

--
-- Filtros para la tabla `almacenes`
--
ALTER TABLE `almacenes`
  ADD CONSTRAINT `fk_almacenes_1` FOREIGN KEY (`id_sedes`) REFERENCES `sedes` (`id_sedes`) ON DELETE CASCADE,
  ADD CONSTRAINT `fk_almacenes_tenant` FOREIGN KEY (`id_tenants`) REFERENCES `tenants` (`id_tenants`) ON DELETE CASCADE;

--
-- Filtros para la tabla `auditoria`
--
ALTER TABLE `auditoria`
  ADD CONSTRAINT `fk_auditoria_1` FOREIGN KEY (`id_tenants`) REFERENCES `tenants` (`id_tenants`) ON DELETE CASCADE,
  ADD CONSTRAINT `fk_auditoria_2` FOREIGN KEY (`id_usuarios`) REFERENCES `usuarios` (`id_usuarios`) ON DELETE SET NULL;

--
-- Filtros para la tabla `branding_negocio`
--
ALTER TABLE `branding_negocio`
  ADD CONSTRAINT `fk_branding_negocio_1` FOREIGN KEY (`id_tenants`) REFERENCES `tenants` (`id_tenants`) ON DELETE CASCADE;

--
-- Filtros para la tabla `caja_chica`
--
ALTER TABLE `caja_chica`
  ADD CONSTRAINT `fk_caja_chica_1` FOREIGN KEY (`id_sesiones_caja`) REFERENCES `sesiones_caja` (`id_sesiones_caja`),
  ADD CONSTRAINT `fk_caja_chica_2` FOREIGN KEY (`id_usuarios`) REFERENCES `usuarios` (`id_usuarios`);

--
-- Filtros para la tabla `categorias_productos`
--
ALTER TABLE `categorias_productos`
  ADD CONSTRAINT `fk_categorias_productos_1` FOREIGN KEY (`id_tenants`) REFERENCES `tenants` (`id_tenants`) ON DELETE CASCADE;

--
-- Filtros para la tabla `categorias_servicios`
--
ALTER TABLE `categorias_servicios`
  ADD CONSTRAINT `fk_categorias_servicios_1` FOREIGN KEY (`id_tenants`) REFERENCES `tenants` (`id_tenants`) ON DELETE CASCADE;

--
-- Filtros para la tabla `citas`
--
ALTER TABLE `citas`
  ADD CONSTRAINT `fk_citas_1` FOREIGN KEY (`id_tenants`) REFERENCES `tenants` (`id_tenants`) ON DELETE CASCADE,
  ADD CONSTRAINT `fk_citas_2` FOREIGN KEY (`id_sedes`) REFERENCES `sedes` (`id_sedes`),
  ADD CONSTRAINT `fk_citas_3` FOREIGN KEY (`id_clientes`) REFERENCES `clientes` (`id_clientes`),
  ADD CONSTRAINT `fk_citas_4` FOREIGN KEY (`id_usuarios_especialista`) REFERENCES `usuarios` (`id_usuarios`),
  ADD CONSTRAINT `fk_citas_5` FOREIGN KEY (`id_usuarios_usuario_creacion`) REFERENCES `usuarios` (`id_usuarios`),
  ADD CONSTRAINT `fk_citas_ventas` FOREIGN KEY (`id_ventas`) REFERENCES `ventas` (`id_ventas`);

--
-- Filtros para la tabla `clientes`
--
ALTER TABLE `clientes`
  ADD CONSTRAINT `fk_clientes_1` FOREIGN KEY (`id_tenants`) REFERENCES `tenants` (`id_tenants`) ON DELETE CASCADE;

--
-- Filtros para la tabla `combos_promocionales`
--
ALTER TABLE `combos_promocionales`
  ADD CONSTRAINT `fk_combos_promocionales_1` FOREIGN KEY (`id_tenants`) REFERENCES `tenants` (`id_tenants`) ON DELETE CASCADE;

--
-- Filtros para la tabla `composicion_combo`
--
ALTER TABLE `composicion_combo`
  ADD CONSTRAINT `fk_composicion_combo_1` FOREIGN KEY (`id_combos_promocionales`) REFERENCES `combos_promocionales` (`id_combos_promocionales`) ON DELETE CASCADE,
  ADD CONSTRAINT `fk_composicion_combo_2` FOREIGN KEY (`id_productos`) REFERENCES `productos` (`id_productos`);

--
-- Filtros para la tabla `comprobantes_electronicos`
--
ALTER TABLE `comprobantes_electronicos`
  ADD CONSTRAINT `fk_comprobantes_electronicos_1` FOREIGN KEY (`id_tenants`) REFERENCES `tenants` (`id_tenants`) ON DELETE CASCADE,
  ADD CONSTRAINT `fk_comprobantes_electronicos_2` FOREIGN KEY (`id_ventas`) REFERENCES `ventas` (`id_ventas`);

--
-- Filtros para la tabla `configuracion_global`
--
ALTER TABLE `configuracion_global`
  ADD CONSTRAINT `fk_configuracion_global_1` FOREIGN KEY (`id_tenants`) REFERENCES `tenants` (`id_tenants`) ON DELETE CASCADE;

--
-- Filtros para la tabla `cuentas_por_pagar`
--
ALTER TABLE `cuentas_por_pagar`
  ADD CONSTRAINT `fk_cuentas_por_pagar_1` FOREIGN KEY (`id_tenants`) REFERENCES `tenants` (`id_tenants`) ON DELETE CASCADE,
  ADD CONSTRAINT `fk_cuentas_por_pagar_2` FOREIGN KEY (`id_proveedores`) REFERENCES `proveedores` (`id_proveedores`);

--
-- Filtros para la tabla `detalle_devolucion_proveedor`
--
ALTER TABLE `detalle_devolucion_proveedor`
  ADD CONSTRAINT `fk_detalle_devolucion_proveedor_1` FOREIGN KEY (`id_devoluciones_proveedor`) REFERENCES `devoluciones_proveedor` (`id_devoluciones_proveedor`) ON DELETE CASCADE,
  ADD CONSTRAINT `fk_detalle_devolucion_proveedor_2` FOREIGN KEY (`id_productos`) REFERENCES `productos` (`id_productos`),
  ADD CONSTRAINT `fk_detalle_devolucion_proveedor_3` FOREIGN KEY (`id_lotes_inventario`) REFERENCES `lotes_inventario` (`id_lotes_inventario`);

--
-- Filtros para la tabla `detalle_devolucion_venta`
--
ALTER TABLE `detalle_devolucion_venta`
  ADD CONSTRAINT `fk_detalle_devolucion_venta_1` FOREIGN KEY (`id_devoluciones_venta`) REFERENCES `devoluciones_venta` (`id_devoluciones_venta`) ON DELETE CASCADE,
  ADD CONSTRAINT `fk_detalle_devolucion_venta_2` FOREIGN KEY (`id_productos`) REFERENCES `productos` (`id_productos`);

--
-- Filtros para la tabla `detalle_orden_compra`
--
ALTER TABLE `detalle_orden_compra`
  ADD CONSTRAINT `fk_detalle_orden_compra_1` FOREIGN KEY (`id_ordenes_compra`) REFERENCES `ordenes_compra` (`id_ordenes_compra`) ON DELETE CASCADE,
  ADD CONSTRAINT `fk_detalle_orden_compra_2` FOREIGN KEY (`id_productos`) REFERENCES `productos` (`id_productos`);

--
-- Filtros para la tabla `detalle_pedido`
--
ALTER TABLE `detalle_pedido`
  ADD CONSTRAINT `fk_detalle_pedido_1` FOREIGN KEY (`id_pedidos`) REFERENCES `pedidos` (`id_pedidos`) ON DELETE CASCADE,
  ADD CONSTRAINT `fk_detalle_pedido_2` FOREIGN KEY (`id_productos`) REFERENCES `productos` (`id_productos`);

--
-- Filtros para la tabla `detalle_venta`
--
ALTER TABLE `detalle_venta`
  ADD CONSTRAINT `fk_detalle_venta_1` FOREIGN KEY (`id_ventas`) REFERENCES `ventas` (`id_ventas`) ON DELETE CASCADE,
  ADD CONSTRAINT `fk_detalle_venta_2` FOREIGN KEY (`id_productos`) REFERENCES `productos` (`id_productos`),
  ADD CONSTRAINT `fk_detalle_venta_3` FOREIGN KEY (`id_lotes_inventario`) REFERENCES `lotes_inventario` (`id_lotes_inventario`);

--
-- Filtros para la tabla `devoluciones_proveedor`
--
ALTER TABLE `devoluciones_proveedor`
  ADD CONSTRAINT `fk_devoluciones_proveedor_1` FOREIGN KEY (`id_tenants`) REFERENCES `tenants` (`id_tenants`) ON DELETE CASCADE,
  ADD CONSTRAINT `fk_devoluciones_proveedor_2` FOREIGN KEY (`id_proveedores`) REFERENCES `proveedores` (`id_proveedores`);

--
-- Filtros para la tabla `devoluciones_venta`
--
ALTER TABLE `devoluciones_venta`
  ADD CONSTRAINT `fk_devoluciones_venta_1` FOREIGN KEY (`id_tenants`) REFERENCES `tenants` (`id_tenants`) ON DELETE CASCADE,
  ADD CONSTRAINT `fk_devoluciones_venta_2` FOREIGN KEY (`id_ventas`) REFERENCES `ventas` (`id_ventas`),
  ADD CONSTRAINT `fk_devoluciones_venta_3` FOREIGN KEY (`id_usuarios`) REFERENCES `usuarios` (`id_usuarios`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
