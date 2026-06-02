-- ============================================================
-- Inserts de prueba: bellarista v3

SET FOREIGN_KEY_CHECKS = 0;

-- ------------------------------------------------------------
-- planes_suscripcion
-- ------------------------------------------------------------
INSERT INTO `planes_suscripcion` (`nombre_plan_suscripcion`, `descripcion`, `estado`) VALUES
('Plan Básico',    'Acceso a funciones esenciales del sistema', 1),
('Plan Profesional','Funciones avanzadas con soporte prioritario', 1),
('Plan Enterprise','Acceso completo con múltiples sedes y usuarios ilimitados', 1);

-- ------------------------------------------------------------
-- precios_plan
-- ------------------------------------------------------------
INSERT INTO `precios_plan` (`id_planes_suscripcion`, `periodo`, `precio`, `estado`) VALUES
(1, 'mensual',     49.90, 1),
(2, 'trimestral', 129.90, 1),
(3, 'anual',      899.90, 1);

-- ------------------------------------------------------------
-- tenants
-- ------------------------------------------------------------
INSERT INTO `tenants` (`razon_social`, `ruc`, `direccion_fiscal`, `correo`, `telefono`, `nombre_comercial`, `tipo_negocio`, `estado`) VALUES
('Salon Bellarista S.A.C.',  '20601234561', 'Av. La Marina 123, Lima',         'contacto@bellarista.pe',   '01-3456789', 'Bellarista',       'Salón de belleza', 1),
('Glamour Studio E.I.R.L.', '20601234562', 'Jr. Miraflores 456, Tarapoto',    'glamour@studio.pe',        '042-123456', 'Glamour Studio',   'Spa y estética',   1),
('Beauty Plus S.A.C.',       '20601234563', 'Calle Los Pinos 789, Trujillo',   'info@beautyplus.pe',       '044-654321', 'Beauty Plus',      'Centro de belleza',1);

-- ------------------------------------------------------------
-- suscripciones
-- ------------------------------------------------------------
INSERT INTO `suscripciones` (`id_tenants`, `id_planes_suscripcion`, `fecha_inicio`, `fecha_proximo_pago`, `estado`, `precio_contratado`) VALUES
(1, 2, '2026-01-01', '2026-07-01', 1, 129.90),
(2, 1, '2026-03-01', '2026-07-01', 1,  49.90),
(3, 3, '2025-12-01', '2026-12-01', 1, 899.90);

-- ------------------------------------------------------------
-- branding_negocio
-- ------------------------------------------------------------
INSERT INTO `branding_negocio` (`id_tenants`, `logo_url`, `color_primario`, `color_secundario`, `nombre_visible`, `redes_sociales`) VALUES
(1, 'https://cdn.bellarista.pe/logos/bellarista.png',    '#FF6B9D', '#FFFFFF', 'Bellarista',     '{"instagram":"@bellarista","facebook":"BellaristaPe"}'),
(2, 'https://cdn.bellarista.pe/logos/glamour.png',       '#9B59B6', '#F8F9FA', 'Glamour Studio', '{"instagram":"@glamourstudio","tiktok":"@glamourstudio"}'),
(3, 'https://cdn.bellarista.pe/logos/beautyplus.png',    '#E74C3C', '#FAFAFA', 'Beauty Plus',    '{"instagram":"@beautyplus","facebook":"BeautyPlusTrujillo"}');

-- ------------------------------------------------------------
-- categorias_productos
-- ------------------------------------------------------------
INSERT INTO `categorias_productos` (`id_tenants`, `nombre_categoria_producto`, `descripcion`, `imagen_url`, `orden`, `estado`) VALUES
(1, 'Tintes y Coloración',  'Tintes, decolorantes y accesorios de color',  NULL, 1, 1),
(1, 'Cuidado Capilar',      'Shampoos, acondicionadores y tratamientos',   NULL, 2, 1),
(1, 'Nail Art',             'Esmaltes, gel UV y accesorios para uñas',     NULL, 3, 1);

-- ------------------------------------------------------------
-- categorias_servicios
-- ------------------------------------------------------------
INSERT INTO `categorias_servicios` (`id_tenants`, `nombre_categoria_servicio`, `descripcion`, `estado`) VALUES
(1, 'Corte y Peinado',  'Servicios de corte, lavado y peinado',         1),
(1, 'Colorimetría',     'Tintes, mechas, balayage y decoloración',      1),
(1, 'Tratamientos',     'Keratina, hidratación profunda y botox capilar',1);

-- ------------------------------------------------------------
-- marcas
-- ------------------------------------------------------------
INSERT INTO `marcas` (`id_tenants`, `nombre_marca`, `pais_origen`, `logo_url`, `descripcion`, `estado`) VALUES
(1, 'L\'Oréal Professionnel', 'Francia',    NULL, 'Marca líder en productos capilares profesionales', 1),
(1, 'Wella Professionals',    'Alemania',   NULL, 'Coloración y cuidado capilar de alta gama',        1),
(1, 'OPI',                    'Estados Unidos', NULL, 'Referente mundial en nail art y esmaltes',     1);

-- ------------------------------------------------------------
-- roles_personalizados
-- ------------------------------------------------------------
INSERT INTO `roles_personalizados` (`id_tenants`, `nombre_rol_personalizado`, `descripcion`, `estado`) VALUES
(1, 'Supervisor de Caja',    'Controla sesiones de caja y aprueba descuentos', 1),
(1, 'Colorista Senior',      'Especialista autorizado en técnicas de color',   1),
(1, 'Encargado de Almacén',  'Gestiona el inventario y órdenes de compra',     1);

-- ------------------------------------------------------------
-- sedes
-- ------------------------------------------------------------
INSERT INTO `sedes` (`id_tenants`, `nombre_sede`, `direccion`, `distrito`, `telefono`, `responsable`, `estado`) VALUES
(1, 'Sede Central',    'Av. La Marina 123',      'San Miguel',  '01-3456789', 'Ana Torres',   1),
(1, 'Sede Miraflores', 'Calle Berlín 456',       'Miraflores',  '01-4567890', 'Luis Vargas',  1),
(1, 'Sede San Borja',  'Av. San Luis 789',       'San Borja',   '01-5678901', 'María Quispe', 1);

-- ------------------------------------------------------------
-- usuarios
-- ------------------------------------------------------------
INSERT INTO `usuarios` (`id_tenants`, `nombre_usuario`, `apellidos_usuario`, `correo`, `numero_documento`, `contraseña`, `tipo_usuario`, `estado`) VALUES
(1, 'Ana',    'Torres Mendoza',   'ana.torres@bellarista.pe',   '45123456', '$2b$12$hashedpassword1', 'admin',        1),
(1, 'Carlos', 'Ríos Huanca',      'carlos.rios@bellarista.pe',  '47896541', '$2b$12$hashedpassword2', 'cajero',       1),
(1, 'Pedro',  'Salinas López',    'pedro.salinas@bellarista.pe','46321478', '$2b$12$hashedpassword3', 'repartidor',   1);

-- ------------------------------------------------------------
-- proveedores
-- ------------------------------------------------------------
INSERT INTO `proveedores` (`id_tenants`, `nombre_proveedor`, `apellido_proveedor`, `razon_social`, `ruc`, `direccion`, `telefono`, `correo`, `estado`) VALUES
(1, 'Jorge',   'Mamani Quispe',  'Distribuidora Cosmética JM S.A.C.', '20501234571', 'Av. Argentina 123, Lima',     '01-2345678', 'ventas@jm-cosmetica.pe',  1),
(1, 'Claudia', 'Flores Ramos',   'Beauty Supply Peru E.I.R.L.',       '20501234572', 'Jr. Cusco 456, Lima',         '01-3456780', 'pedidos@beautysupply.pe',  1),
(1, 'Roberto', 'Chávez Villena', 'Importadora Profesional S.A.',      '20501234573', 'Calle Los Cedros 789, Lima',  '01-4567891', 'compras@importpro.pe',    1);

-- ------------------------------------------------------------
-- metodos_pago
-- ------------------------------------------------------------
INSERT INTO `metodos_pago` (`id_tenants`, `nombre_metodo_de_pago`, `tipo`, `descripcion`, `estado`) VALUES
(1, 'Efectivo',       'efectivo',           'Pago en efectivo en tienda',             1),
(1, 'Visa / MC',      'tarjeta_credito',    'Tarjetas de crédito y débito con POS',   1),
(1, 'Yape / Plin',    'billetera_digital',  'Billeteras digitales móviles',           1);

-- ------------------------------------------------------------
-- series_comprobantes
-- ------------------------------------------------------------
INSERT INTO `series_comprobantes` (`id_tenants`, `tipo_comprobante`, `punto_emision`, `numero_serie`, `numero_proximo`, `fecha_autorizacion`, `estado`) VALUES
(1, 'boleta',  1, 'B001', 1,    '2026-01-01', 1),
(1, 'factura', 1, 'F001', 1,    '2026-01-01', 1),
(1, 'boleta',  2, 'B002', 1,    '2026-01-01', 1);

-- ------------------------------------------------------------
-- servicios_belleza
-- ------------------------------------------------------------
INSERT INTO `servicios_belleza` (`id_tenants`, `id_categorias_servicios`, `nombre_servicio_belleza`, `descripcion`, `duracion_minima`, `duracion_maxima`, `precio_base`, `precio_editable`, `estado`) VALUES
(1, 1, 'Corte de Cabello Dama',  'Corte personalizado con lavado y secado',    45,  60,  45.00, 1, 1),
(1, 2, 'Mechas Californianas',   'Técnica de iluminación degradada',           120, 180, 180.00, 1, 1),
(1, 3, 'Keratina Brasileña',     'Tratamiento alisador con keratina orgánica', 90,  150, 120.00, 0, 1);

-- ------------------------------------------------------------
-- combos_promocionales
-- ------------------------------------------------------------
INSERT INTO `combos_promocionales` (`id_tenants`, `nombre_promocion`, `descripcion`, `precio_combo`, `precio_original`, `fecha_inicio`, `fecha_fin`, `visible_storefront`, `estado`) VALUES
(1, 'Kit Coloración Completo',   'Tinte + Oxigenada + Shampoo neutro',        89.90,  115.00, '2026-06-01', '2026-06-30', 1, 1),
(1, 'Pack Cuidado Capilar',      'Shampoo + Acondicionador + Mascarilla',     75.00,   99.00, '2026-06-01', '2026-06-30', 1, 1),
(1, 'Combo Nail Art Starter',    'Esmalte gel + lámpara UV + lima profesional',65.00,  85.00, '2026-06-01', '2026-06-30', 1, 1);

-- ------------------------------------------------------------
-- configuracion_global
-- ------------------------------------------------------------
INSERT INTO `configuracion_global` (`id_tenants`, `clave`, `valor`, `tipo_valor`, `descripcion`) VALUES
(1, 'igv_porcentaje',        '18',    'numerico', 'Porcentaje de IGV aplicado en ventas'),
(1, 'moneda',                'PEN',   'string',   'Moneda principal del sistema'),
(1, 'max_descuento_cajero',  '10',    'numerico', 'Descuento máximo que puede aplicar un cajero (%)');

-- ------------------------------------------------------------
-- productos
-- ------------------------------------------------------------
INSERT INTO `productos` (`id_tenants`, `id_categorias_productos`, `id_marcas`, `codigo_interno`, `codigo_barras`, `nombre_producto`, `descripcion`, `tipo_producto`, `presentacion`, `contenido_neto`, `img_url`, `precio_costo`, `precio_venta`, `stock_minimo`, `stock_critico`, `visible_storefront`, `estado`) VALUES
(1, 1, 1, 'TIN-001', '7501234560001', 'Tinte L\'Oréal Majirel N°7', 'Tinte permanente rubio medio con cobertura 100%', 'tinte', 'tubo', '50ml', 'https://cdn.bellarista.pe/productos/majirel7.jpg',  18.00, 35.00, 10, 5, 1, 1),
(1, 2, 2, 'SHA-002', '7501234560002', 'Shampoo Wella SP Balance',    'Shampoo equilibrante para cuero cabelludo sensible', 'shampoo', 'frasco', '250ml', 'https://cdn.bellarista.pe/productos/wella-sp.jpg', 22.00, 42.00, 10, 5, 1, 1),
(1, 3, 3, 'ESM-003', '7501234560003', 'Esmalte OPI Gel Color Rojo',  'Esmalte semipermanente gel color rojo pasión',       'esmalte', 'frasco', '15ml', 'https://cdn.bellarista.pe/productos/opi-rojo.jpg',  12.00, 28.00, 15, 5, 1, 1);

-- ------------------------------------------------------------
-- promociones
-- ------------------------------------------------------------
INSERT INTO `promociones` (`id_tenants`, `nombre_promocion`, `tipo_descuento`, `valor_descuento`, `compra_minima`, `fecha_inicio`, `fecha_fin`, `id_categorias_productos`, `estado`) VALUES
(1, 'Descuento Coloración Junio', 'porcentual', 15.00, 50.00, '2026-06-01', '2026-06-30', 1, 1),
(1, '2x1 en Shampoos',           '2x1',         0.00, 35.00, '2026-06-01', '2026-06-15', 2, 1),
(1, 'Oferta Nail Art',           'fijo',        10.00, 25.00, '2026-06-01', '2026-06-30', 3, 1);

-- ------------------------------------------------------------
-- permisos_rol
-- ------------------------------------------------------------
INSERT INTO `permisos_rol` (`id_roles_personalizados`, `modulo`, `accion`, `recurso`, `estado`) VALUES
(1, 'ventas',    'visualizar', 'sesiones_caja',  1),
(2, 'servicios', 'editar',     'citas',           1),
(3, 'inventario','crear',      'lotes_inventario',1);

-- ------------------------------------------------------------
-- almacenes
-- ------------------------------------------------------------
INSERT INTO `almacenes` (`id_sedes`, `nombre_almacen`, `ubicacion`, `capacidad`, `estado`) VALUES
(1, 'Almacén Principal', 'Sótano - Sede Central',   500, 1),
(2, 'Almacén Miraflores','Trastienda - Miraflores', 200, 1),
(3, 'Almacén San Borja', 'Trastienda - San Borja',  200, 1);

-- ------------------------------------------------------------
-- zonas_delivery
-- ------------------------------------------------------------
INSERT INTO `zonas_delivery` (`id_sedes`, `nombre_zona`, `distritos`, `costo_fijo`, `monto_minimo_compra`, `tiempo_estimado_minutos`, `estado`) VALUES
(1, 'Zona Centro',    'San Miguel, Pueblo Libre, Magdalena', 8.00,  50.00, 30, 1),
(1, 'Zona Moderna',   'Miraflores, San Isidro, Surco',       10.00, 60.00, 45, 1),
(1, 'Zona Periférica','Callao, Los Olivos, Independencia',   15.00, 80.00, 60, 1);

-- ------------------------------------------------------------
-- horarios_operacion
-- ------------------------------------------------------------
INSERT INTO `horarios_operacion` (`id_sedes`, `dia_semana`, `hora_apertura`, `hora_cierre`, `estado`) VALUES
(1, 'lunes',   '09:00:00', '20:00:00', 1),
(1, 'sabado',  '09:00:00', '18:00:00', 1),
(1, 'domingo', '10:00:00', '15:00:00', 1);

-- ------------------------------------------------------------
-- gastos_recurrentes
-- ------------------------------------------------------------
INSERT INTO `gastos_recurrentes` (`id_tenants`, `id_sedes`, `concepto`, `monto`, `frecuencia`, `fecha_proximo_registro`, `estado`) VALUES
(1, 1, 'Alquiler local sede central',  3500.00, 'mensual',  '2026-07-01', 1),
(1, 2, 'Servicio de electricidad',      420.00, 'mensual',  '2026-07-01', 1),
(1, 3, 'Servicio de limpieza',          250.00, 'quincenal','2026-06-15', 1);

-- ------------------------------------------------------------
-- facturas_suscripcion
-- ------------------------------------------------------------
INSERT INTO `facturas_suscripcion` (`id_suscripciones`, `numero_factura`, `monto`, `fecha_emision`, `fecha_vencimiento`, `estado_pago`, `metodo_pago`) VALUES
(1, 'FS-2026-0001', 129.90, '2026-01-01', '2026-01-10', 'pagada',    'transferencia'),
(2, 'FS-2026-0002',  49.90, '2026-03-01', '2026-03-10', 'pagada',    'efectivo'),
(3, 'FS-2026-0003', 899.90, '2025-12-01', '2025-12-10', 'pagada',    'transferencia');

-- ------------------------------------------------------------
-- clientes
-- ------------------------------------------------------------
INSERT INTO `clientes` (`id_tenants`, `nombre_cliente`, `apellidos_clientes`, `tipo_documento`, `numero_documento`, `telefono`, `correo`, `direccion`, `distrito`, `tipo_cliente`, `estado`) VALUES
(1, 'Lucía',    'Paredes Ruiz',     'DNI', '48231456', '987654321', 'lucia.paredes@gmail.com',   'Av. Brasil 123',    'Pueblo Libre', 'frecuente', 1),
(1, 'Martina',  'Díaz Sánchez',     'DNI', '49876543', '976543210', 'martina.diaz@hotmail.com',  'Calle Los Robles 45','Miraflores',  'vip',       1),
(1, 'Carmen',   'Huanca Flores',    'DNI', '47321098', '965432109', NULL,                         'Jr. Los Pinos 67',  'San Borja',   'regular',   1);

-- ------------------------------------------------------------
-- repartidores
-- ------------------------------------------------------------
INSERT INTO `repartidores` (`id_tenants`, `id_usuarios`, `tipo_vehiculo`, `placa_vehiculo`, `numero_licencia`, `estado`) VALUES
(1, 3, 'Moto lineal', 'M3G-456', 'LIC-2024-00123', 1);

-- (Solo 1 repartidor registrado porque solo hay 1 usuario con tipo='repartidor'.
--  Agrega más usuarios tipo repartidor para insertar más filas aquí.)

-- ------------------------------------------------------------
-- sesiones_caja
-- ------------------------------------------------------------
INSERT INTO `sesiones_caja` (`id_sedes`, `id_usuarios`, `fecha_apertura`, `fecha_cierre`, `monto_inicial`, `monto_final`, `diferencia`, `estado`) VALUES
(1, 2, '2026-06-01 09:00:00', '2026-06-01 20:00:00', 200.00, 1850.00, 0.00,  0),
(1, 2, '2026-05-31 09:00:00', '2026-05-31 20:00:00', 200.00, 2100.00, 0.00,  0),
(1, 2, '2026-06-02 09:00:00', NULL,                   200.00, NULL,    NULL,  1);

-- ------------------------------------------------------------
-- usuario_sedes
-- ------------------------------------------------------------
INSERT INTO `usuario_sedes` (`id_usuarios`, `id_sedes`) VALUES
(1, 1),
(2, 1),
(3, 1);

-- ------------------------------------------------------------
-- preferencias_usuario
-- ------------------------------------------------------------
INSERT INTO `preferencias_usuario` (`id_usuarios`, `idioma`, `formato_fecha`, `zona_horaria`, `notificaciones_activas`, `vista_inicial`, `tema_interfaz`) VALUES
(1, 'es', 'DD/MM/YYYY', 'America/Lima', 1, 'dashboard', 'claro'),
(2, 'es', 'DD/MM/YYYY', 'America/Lima', 1, 'ventas',    'claro'),
(3, 'es', 'DD/MM/YYYY', 'America/Lima', 0, 'pedidos',   'oscuro');

-- ------------------------------------------------------------
-- citas
-- ------------------------------------------------------------
INSERT INTO `citas` (`id_tenants`, `id_sedes`, `id_clientes`, `id_usuarios_especialista`, `fecha_cita`, `hora_inicio`, `hora_fin`, `duracion_minutos`, `estado`, `observaciones`, `id_usuarios_usuario_creacion`) VALUES
(1, 1, 1, 1, '2026-06-05', '10:00:00', '11:00:00', 60,  1, 'Cliente con cabello teñido previamente', 2),
(1, 1, 2, 1, '2026-06-05', '11:30:00', '14:00:00', 150, 1, 'Mechas californianas + hidratación',      1),
(1, 2, 3, 1, '2026-06-06', '09:00:00', '10:30:00', 90,  1, NULL,                                      1);

-- ------------------------------------------------------------
-- servicios_cita
-- ------------------------------------------------------------
INSERT INTO `servicios_cita` (`id_citas`, `id_servicios_belleza`, `precio`, `observaciones`) VALUES
(1, 1,  45.00, 'Corte + lavado'),
(2, 2, 200.00, 'Mechas + tratamiento post-color'),
(3, 3, 130.00, 'Keratina express 90 min');

-- ------------------------------------------------------------
-- ordenes_compra
-- ------------------------------------------------------------
INSERT INTO `ordenes_compra` (`id_tenants`, `id_proveedores`, `numero_orden`, `fecha_orden`, `fecha_entrega_estimada`, `estado`, `monto_total`, `forma_pago`, `notas`) VALUES
(1, 1, 'OC-2026-001', '2026-05-20', '2026-05-27', 0, 540.00, 'transferencia', 'Pedido mensual de tintes'),
(1, 2, 'OC-2026-002', '2026-05-22', '2026-05-29', 0, 320.00, 'efectivo',      'Reposición de shampoos y acondicionadores'),
(1, 3, 'OC-2026-003', '2026-06-01', '2026-06-08', 1, 210.00, 'transferencia', 'Esmaltes y accesorios nail art');

-- ------------------------------------------------------------
-- cuentas_por_pagar
-- ------------------------------------------------------------
INSERT INTO `cuentas_por_pagar` (`id_tenants`, `id_proveedores`, `documento_numero`, `tipo_documento`, `monto`, `fecha_documento`, `fecha_vencimiento`, `estado_pago`) VALUES
(1, 1, 'FAC-00341', 'factura', 540.00, '2026-05-27', '2026-06-27', 'pendiente'),
(1, 2, 'BOL-00892', 'boleta',  320.00, '2026-05-29', '2026-06-29', 'pendiente'),
(1, 3, 'FAC-00115', 'factura', 210.00, '2026-06-08', '2026-07-08', 'pendiente');

-- ------------------------------------------------------------
-- devoluciones_proveedor
-- ------------------------------------------------------------
INSERT INTO `devoluciones_proveedor` (`id_tenants`, `id_proveedores`, `numero_devolucion`, `fecha_devolucion`, `motivo`, `observaciones`, `estado`) VALUES
(1, 1, 'DEV-P-001', '2026-05-15', 'Producto vencido',           '3 tubos de tinte con fecha vencida al ingreso', 1),
(1, 2, 'DEV-P-002', '2026-05-20', 'Envase dañado',              'Frasco roto en el embalaje, derrame interno',   1),
(1, 3, 'DEV-P-003', '2026-06-01', 'Producto incorrecto',        'Se recibió código distinto al pedido',          1);

-- ------------------------------------------------------------
-- lotes_inventario
-- ------------------------------------------------------------
INSERT INTO `lotes_inventario` (`id_productos`, `id_almacenes`, `numero_lote`, `cantidad`, `cantidad_disponible`, `precio_unitario`, `fecha_vencimiento`, `id_proveedores`, `fecha_ingreso`, `estado`) VALUES
(1, 1, 'LOT-2026-001', 50, 48, 18.00, '2027-12-31', 1, '2026-05-27', 1),
(2, 1, 'LOT-2026-002', 30, 30, 22.00, '2028-06-30', 2, '2026-05-29', 1),
(3, 1, 'LOT-2026-003', 40, 40, 12.00, '2027-08-31', 3, '2026-06-08', 1);

-- ------------------------------------------------------------
-- detalle_orden_compra
-- ------------------------------------------------------------
INSERT INTO `detalle_orden_compra` (`id_ordenes_compra`, `id_productos`, `cantidad_solicitada`, `cantidad_recibida`, `precio_unitario`, `subtotal`) VALUES
(1, 1, 30, 30, 18.00, 540.00),
(2, 2, 20, 20, 16.00, 320.00),
(3, 3, 30, 30,  7.00, 210.00);

-- ------------------------------------------------------------
-- movimientos_inventario
-- ------------------------------------------------------------
INSERT INTO `movimientos_inventario` (`id_lotes_inventario`, `tipo_movimiento`, `cantidad`, `id_usuarios`, `motivo`, `referencia_documento`) VALUES
(1, 'entrada',  50, 1, 'Ingreso por orden de compra',  'OC-2026-001'),
(2, 'entrada',  30, 1, 'Ingreso por orden de compra',  'OC-2026-002'),
(3, 'entrada',  40, 1, 'Ingreso por orden de compra',  'OC-2026-003');

-- ------------------------------------------------------------
-- composicion_combo
-- ------------------------------------------------------------
INSERT INTO `composicion_combo` (`id_combos_promocionales`, `id_productos`, `cantidad`) VALUES
(1, 1, 1),
(2, 2, 1),
(3, 3, 1);

-- ------------------------------------------------------------
-- proveedor_categorias
-- ------------------------------------------------------------
INSERT INTO `proveedor_categorias` (`id_proveedores`, `id_categorias_productos`) VALUES
(1, 1),
(2, 2),
(3, 3);

-- ------------------------------------------------------------
-- sesiones_caja ya insertadas arriba (id: 1, 2, 3)
-- ventas
-- ------------------------------------------------------------
INSERT INTO `ventas` (`id_tenants`, `id_sedes`, `id_sesiones_caja`, `id_clientes`, `numero_ticket`, `comprobante_numero`, `tipo_comprobante`, `subtotal`, `descuento`, `impuesto`, `total`, `estado`, `estado_sunat`, `id_usuarios`) VALUES
(1, 1, 1, 1, 'T-0001', 'B001-00001', 'boleta',  29.66, 0.00, 5.34, 35.00, 0, 'pendiente', 2),
(1, 1, 1, 2, 'T-0002', 'B001-00002', 'boleta',  35.59, 0.00, 6.41, 42.00, 0, 'pendiente', 2),
(1, 1, 1, 3, 'T-0003', 'F001-00001', 'factura', 23.73, 0.00, 4.27, 28.00, 0, 'pendiente', 2);

-- ------------------------------------------------------------
-- detalle_venta
-- ------------------------------------------------------------
INSERT INTO `detalle_venta` (`id_ventas`, `id_productos`, `cantidad`, `precio_unitario`, `descuento`, `subtotal`, `id_lotes_inventario`) VALUES
(1, 1, 1, 35.00, 0.00, 35.00, 1),
(2, 2, 1, 42.00, 0.00, 42.00, 2),
(3, 3, 1, 28.00, 0.00, 28.00, 3);

-- ------------------------------------------------------------
-- formas_pago_venta
-- ------------------------------------------------------------
INSERT INTO `formas_pago_venta` (`id_ventas`, `tipo_pago`, `monto`, `referencia_transaccion`) VALUES
(1, 'efectivo',        35.00, NULL),
(2, 'tarjeta_debito',  42.00, 'TXN-20260601-001'),
(3, 'billetera_digital',28.00,'YAPE-20260601-002');

-- ------------------------------------------------------------
-- comprobantes_electronicos
-- ------------------------------------------------------------
INSERT INTO `comprobantes_electronicos` (`id_tenants`, `tipo_comprobante`, `numero_serie`, `numero_comprobante`, `id_ventas`, `estado_sunat`) VALUES
(1, 'boleta',  'B001', '00001', 1, 'pendiente_envio'),
(1, 'boleta',  'B001', '00002', 2, 'pendiente_envio'),
(1, 'factura', 'F001', '00001', 3, 'pendiente_envio');

-- ------------------------------------------------------------
-- pedidos
-- ------------------------------------------------------------
INSERT INTO `pedidos` (`id_tenants`, `id_clientes`, `numero_pedido`, `modalidad`, `estado`, `subtotal`, `costo_envio`, `impuesto`, `total`, `direccion_entrega`, `id_zonas_delivery`, `id_usuarios`, `fecha_entrega_estimada`) VALUES
(1, 1, 'PED-2026-001', 'delivery',   1,  29.66, 8.00, 5.34, 43.00, 'Av. Brasil 123, Pueblo Libre',    1, 3, '2026-06-02 14:00:00'),
(1, 2, 'PED-2026-002', 'delivery',   1,  35.59,10.00, 6.41, 52.00, 'Calle Los Robles 45, Miraflores', 2, 3, '2026-06-02 15:30:00'),
(1, 3, 'PED-2026-003', 'presencial', 0,  23.73, 0.00, 4.27, 28.00, NULL,                              NULL,2, NULL);

-- ------------------------------------------------------------
-- detalle_pedido
-- ------------------------------------------------------------
INSERT INTO `detalle_pedido` (`id_pedidos`, `id_productos`, `cantidad`, `precio_unitario`, `descuento`, `subtotal`) VALUES
(1, 1, 1, 35.00, 0.00, 35.00),
(2, 2, 1, 42.00, 0.00, 42.00),
(3, 3, 1, 28.00, 0.00, 28.00);

-- ------------------------------------------------------------
-- pagos_proveedor
-- ------------------------------------------------------------
INSERT INTO `pagos_proveedor` (`id_cuentas_por_pagar`, `monto_pagado`, `forma_pago`, `numero_comprobante`, `fecha_pago`, `id_usuarios`, `observaciones`) VALUES
(1, 540.00, 'transferencia', 'TRANSF-20260601-001', '2026-06-01', 1, 'Pago total factura FAC-00341'),
(2, 200.00, 'efectivo',      NULL,                   '2026-06-01', 1, 'Pago parcial BOL-00892'),
(3, 210.00, 'transferencia', 'TRANSF-20260601-002', '2026-06-01', 1, 'Pago total FAC-00115');

-- ------------------------------------------------------------
-- caja_chica
-- ------------------------------------------------------------
INSERT INTO `caja_chica` (`id_sesiones_caja`, `concepto`, `monto`, `descripcion`, `id_usuarios`) VALUES
(1, 'Compra de útiles de limpieza', 35.00, 'Escoba, recogedor y lejía para el local',       2),
(1, 'Pasajes para trámite',         15.00, 'Movilidad para renovar licencia municipal',      1),
(1, 'Impresión de facturas',        12.50, 'Resma de papel bond A4 y tóner para impresora', 2);

-- ------------------------------------------------------------
-- gastos_operativos
-- ------------------------------------------------------------
INSERT INTO `gastos_operativos` (`id_tenants`, `id_sedes`, `concepto`, `categoria`, `monto`, `descripcion`, `id_proveedores`, `fecha_gasto`, `id_usuarios_usuario_creacion`) VALUES
(1, 1, 'Reposición de toallas',       'insumos',           180.00, '12 toallas de microfibra para clientes',        NULL, '2026-06-01', 1),
(1, 1, 'Publicidad en Instagram',     'publicidad',        200.00, 'Campaña pagada semana del 01 al 07 de junio',   NULL, '2026-06-01', 1),
(1, 1, 'Mantenimiento aire acondicionado','mantenimiento',  95.00, 'Limpieza y recarga de gas equipo sala principal',NULL,'2026-06-01', 1);

-- ------------------------------------------------------------
-- notificaciones
-- ------------------------------------------------------------
INSERT INTO `notificaciones` (`id_tenants`, `id_usuarios`, `tipo`, `titulo`, `mensaje`, `canal_envio`, `estado_lectura`) VALUES
(1, 1, 'stock_bajo',      'Stock bajo: Tinte Majirel N°7', 'Solo quedan 2 unidades del tinte Majirel N°7. Se recomienda reabastecer.', 'sistema',  'no_leido'),
(1, 2, 'cita_pendiente',  'Cita mañana: Lucía Paredes',    'Mañana a las 10:00 tienes una cita de corte con Lucía Paredes.',           'sistema',  'no_leido'),
(1, 1, 'pago_vencido',    'Factura próxima a vencer',      'La factura FAC-00341 vence el 27/06/2026. Coordina el pago.',              'correo',   'no_leido');

-- ------------------------------------------------------------
-- auditoria
-- ------------------------------------------------------------
INSERT INTO `auditoria` (`id_tenants`, `id_usuarios`, `accion`, `tabla_afectada`, `id_registro`, `datos_anteriores`, `datos_nuevos`, `ip_address`) VALUES
(1, 1, 'INSERT', 'productos',  1, NULL,                                    '{"nombre_producto":"Tinte Majirel N°7"}',  '192.168.1.10'),
(1, 2, 'INSERT', 'ventas',     1, NULL,                                    '{"total":35.00,"tipo_comprobante":"boleta"}','192.168.1.11'),
(1, 1, 'UPDATE', 'suscripciones',1,'{"estado":1}',                         '{"estado":1}',                             '192.168.1.10');

-- ------------------------------------------------------------
-- devoluciones_venta
-- ------------------------------------------------------------
INSERT INTO `devoluciones_venta` (`id_tenants`, `id_ventas`, `numero_devolucion`, `motivo`, `tipo_resolucion`, `estado_devolucion`, `monto_reembolso`, `id_usuarios`) VALUES
(1, 1, 'DEV-V-001', 'Producto en mal estado',    'reembolso',       'completada', 35.00, 1),
(1, 2, 'DEV-V-002', 'Talla/presentación incorrecta','cambio_producto','aprobada',  42.00, 1),
(1, 3, 'DEV-V-003', 'Cliente insatisfecho',       'nota_credito',   'pendiente',   28.00, 1);

-- ------------------------------------------------------------
-- detalle_devolucion_venta
-- ------------------------------------------------------------
INSERT INTO `detalle_devolucion_venta` (`id_devoluciones_venta`, `id_productos`, `cantidad`, `precio_unitario`) VALUES
(1, 1, 1, 35.00),
(2, 2, 1, 42.00),
(3, 3, 1, 28.00);

-- ------------------------------------------------------------
-- detalle_devolucion_proveedor
-- ------------------------------------------------------------
INSERT INTO `detalle_devolucion_proveedor` (`id_devoluciones_proveedor`, `id_productos`, `id_lotes_inventario`, `cantidad`) VALUES
(1, 1, 1, 3),
(2, 2, 2, 1),
(3, 3, 3, 2);

-- ------------------------------------------------------------
-- reclamos
-- ------------------------------------------------------------
INSERT INTO `reclamos` (`id_tenants`, `id_clientes`, `id_ventas`, `numero_reclamo`, `canal_ingreso`, `tipo_incidencia`, `descripcion`, `estado`, `id_usuarios_responsable`, `sla_horas`, `fecha_vencimiento_sla`, `id_usuarios_usuario_creacion`) VALUES
(1, 1, 1, 'REC-2026-001', 'presencial', 'Producto defectuoso',  'El tinte comprado presentó mal olor y no cubrió adecuadamente',  1, 1, 48, '2026-06-03 10:00:00', 2),
(1, 2, 2, 'REC-2026-002', 'whatsapp',   'Demora en atención',   'La clienta esperó más de 30 minutos sin ser atendida en caja',   1, 1, 24, '2026-06-03 11:30:00', 1),
(1, 3, 3, 'REC-2026-003', 'web',        'Cobro incorrecto',     'Se cobró un precio distinto al mostrado en el catálogo online', 1, 1, 24, '2026-06-03 09:00:00', 1);

SET FOREIGN_KEY_CHECKS = 1;