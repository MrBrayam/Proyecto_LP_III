CASOS DE USO DETALLADOS 

UC-001: Autenticar Usuario 

El sistema tiene DOS endpoints de autenticación separados según el tipo de usuario: 

POST /auth/admin/** → AdminAuthController + AdminAuthService (para SuperAdmin) 

POST /auth/escuela/** → EscuelaAuthController + EscuelaAuthService (para Escuela) 

La lógica real de EscuelaAuthService.authenticate() implementa: 

Buscar usuario por username (findByUsuario) 

Verificar estado activo (estado == 1) 

Detectar si contraseña en BD es BCrypt ($2a$) o texto plano, y migrar automáticamente a hash 

Validar suscripción vigente de la institución (SuscripcionValidator) 

Generar JWT con JwtUtil (JJWT 0.12.6, HS256, expiración: 100 años) 

Campo                    Descripción 

ID                       UC-001 

Nombre                   Autenticar Usuario (Iniciar Sesión) 

Actor Primario           SuperAdmin / Admin / Profesor / Tesorería 

Actores Secundarios      BD MySQL, JwtUtil (JJWT 0.12.6), SuscripcionValidator 

Precondición             El usuario tiene credenciales registradas y está activo (estado=1) 

Postcondición (éxito)    Token JWT generado + UsuarioEscuelaDTO con rol, sede, institución 

Postcondición (fallo)     No se genera sesión; mensaje de error HTTP 401/400 

Endpoint real             POST /auth/escuela/login | POST /auth/admin/login 

Flujo Principal           1. POST con {usuario, contraseña} 2. findByUsuario() 3. BCrypt.matches() 4. SuscripcionValidator.validar() 5. JwtUtil.generarToken() 6. Retornar EscuelaLoginResponse con token + perfil 

Flujo Alternativo A       A-1: Credenciales incorrectas → HTTP 400 Bad Request 

Flujo de Excepción        E-1: Usuario inactivo → 'Usuario inactivo' | E-2: Suscripción vencida → error suscripción 

Requerimientos            RF-MSC-01 (Autenticación), RNF-Seguridad (JWT, BCrypt) 

Prioridad                 MUST 



UC-002: Gestionar Almacenes

Campo                    Descripcion

ID                       UC-002

Nombre                   Gestionar Almacenes

Actor Primario           Admin / Operador del sistema

Actores Secundarios      API REST Spring Boot, BD MySQL

Precondicion             Sesion iniciada y permisos del modulo

Postcondicion (exito)    Cambios reflejados en la entidad correspondiente

Postcondicion (fallo)    No se persisten cambios; se retorna error de API

Endpoint real            GET|POST|PUT|DELETE /api/almacenes/**

Flujo Principal          1. GET /api/almacenes para listar 2. POST /api/almacenes para crear 3. PUT /api/almacenes/{id} para actualizar 4. GET /api/almacenes/{id} para consultar detalle 5. DELETE /api/almacenes/{id} para eliminar

Flujo Alternativo A      A-1: ID no encontrado -> respuesta vacia o error segun capa de servicio

Flujo de Excepcion       E-1: Error de validacion/persistencia en BD

Requerimientos           RF-MSC-CRUD, RNF-Integridad-Datos

Prioridad                MUST


UC-003: Gestionar Auditoria

Campo                    Descripcion

ID                       UC-003

Nombre                   Gestionar Auditoria

Actor Primario           Admin / Operador del sistema

Actores Secundarios      API REST Spring Boot, BD MySQL

Precondicion             Sesion iniciada y permisos del modulo

Postcondicion (exito)    Cambios reflejados en la entidad correspondiente

Postcondicion (fallo)    No se persisten cambios; se retorna error de API

Endpoint real            GET|POST|PUT|DELETE /api/auditoria/**

Flujo Principal          1. GET /api/auditoria para listar 2. POST /api/auditoria para crear 3. PUT /api/auditoria/{id} para actualizar 4. GET /api/auditoria/{id} para consultar detalle 5. DELETE /api/auditoria/{id} para eliminar

Flujo Alternativo A      A-1: ID no encontrado -> respuesta vacia o error segun capa de servicio

Flujo de Excepcion       E-1: Error de validacion/persistencia en BD

Requerimientos           RF-MSC-CRUD, RNF-Integridad-Datos

Prioridad                MUST


UC-004: Gestionar Branding Negocio

Campo                    Descripcion

ID                       UC-004

Nombre                   Gestionar Branding Negocio

Actor Primario           Admin / Operador del sistema

Actores Secundarios      API REST Spring Boot, BD MySQL

Precondicion             Sesion iniciada y permisos del modulo

Postcondicion (exito)    Cambios reflejados en la entidad correspondiente

Postcondicion (fallo)    No se persisten cambios; se retorna error de API

Endpoint real            GET|POST|PUT|DELETE /api/branding_negocio/**

Flujo Principal          1. GET /api/branding_negocio para listar 2. POST /api/branding_negocio para crear 3. PUT /api/branding_negocio/{id} para actualizar 4. GET /api/branding_negocio/{id} para consultar detalle 5. DELETE /api/branding_negocio/{id} para eliminar

Flujo Alternativo A      A-1: ID no encontrado -> respuesta vacia o error segun capa de servicio

Flujo de Excepcion       E-1: Error de validacion/persistencia en BD

Requerimientos           RF-MSC-CRUD, RNF-Integridad-Datos

Prioridad                MUST


UC-005: Gestionar Caja Chica

Campo                    Descripcion

ID                       UC-005

Nombre                   Gestionar Caja Chica

Actor Primario           Admin / Operador del sistema

Actores Secundarios      API REST Spring Boot, BD MySQL

Precondicion             Sesion iniciada y permisos del modulo

Postcondicion (exito)    Cambios reflejados en la entidad correspondiente

Postcondicion (fallo)    No se persisten cambios; se retorna error de API

Endpoint real            GET|POST|PUT|DELETE /api/caja_chica/**

Flujo Principal          1. GET /api/caja_chica para listar 2. POST /api/caja_chica para crear 3. PUT /api/caja_chica/{id} para actualizar 4. GET /api/caja_chica/{id} para consultar detalle 5. DELETE /api/caja_chica/{id} para eliminar

Flujo Alternativo A      A-1: ID no encontrado -> respuesta vacia o error segun capa de servicio

Flujo de Excepcion       E-1: Error de validacion/persistencia en BD

Requerimientos           RF-MSC-CRUD, RNF-Integridad-Datos

Prioridad                MUST


UC-006: Gestionar Categorias Productos

Campo                    Descripcion

ID                       UC-006

Nombre                   Gestionar Categorias Productos

Actor Primario           Admin / Operador del sistema

Actores Secundarios      API REST Spring Boot, BD MySQL

Precondicion             Sesion iniciada y permisos del modulo

Postcondicion (exito)    Cambios reflejados en la entidad correspondiente

Postcondicion (fallo)    No se persisten cambios; se retorna error de API

Endpoint real            GET|POST|PUT|DELETE /api/categorias_productos/**

Flujo Principal          1. GET /api/categorias_productos para listar 2. POST /api/categorias_productos para crear 3. PUT /api/categorias_productos/{id} para actualizar 4. GET /api/categorias_productos/{id} para consultar detalle 5. DELETE /api/categorias_productos/{id} para eliminar

Flujo Alternativo A      A-1: ID no encontrado -> respuesta vacia o error segun capa de servicio

Flujo de Excepcion       E-1: Error de validacion/persistencia en BD

Requerimientos           RF-MSC-CRUD, RNF-Integridad-Datos

Prioridad                MUST


UC-007: Gestionar Categorias Servicios

Campo                    Descripcion

ID                       UC-007

Nombre                   Gestionar Categorias Servicios

Actor Primario           Admin / Operador del sistema

Actores Secundarios      API REST Spring Boot, BD MySQL

Precondicion             Sesion iniciada y permisos del modulo

Postcondicion (exito)    Cambios reflejados en la entidad correspondiente

Postcondicion (fallo)    No se persisten cambios; se retorna error de API

Endpoint real            GET|POST|PUT|DELETE /api/categorias_servicios/**

Flujo Principal          1. GET /api/categorias_servicios para listar 2. POST /api/categorias_servicios para crear 3. PUT /api/categorias_servicios/{id} para actualizar 4. GET /api/categorias_servicios/{id} para consultar detalle 5. DELETE /api/categorias_servicios/{id} para eliminar

Flujo Alternativo A      A-1: ID no encontrado -> respuesta vacia o error segun capa de servicio

Flujo de Excepcion       E-1: Error de validacion/persistencia en BD

Requerimientos           RF-MSC-CRUD, RNF-Integridad-Datos

Prioridad                MUST


UC-008: Gestionar Citas

Campo                    Descripcion

ID                       UC-008

Nombre                   Gestionar Citas

Actor Primario           Admin / Operador del sistema

Actores Secundarios      API REST Spring Boot, BD MySQL

Precondicion             Sesion iniciada y permisos del modulo

Postcondicion (exito)    Cambios reflejados en la entidad correspondiente

Postcondicion (fallo)    No se persisten cambios; se retorna error de API

Endpoint real            GET|POST|PUT|DELETE /api/citas/**

Flujo Principal          1. GET /api/citas para listar 2. POST /api/citas para crear 3. PUT /api/citas/{id} para actualizar 4. GET /api/citas/{id} para consultar detalle 5. DELETE /api/citas/{id} para eliminar

Flujo Alternativo A      A-1: ID no encontrado -> respuesta vacia o error segun capa de servicio

Flujo de Excepcion       E-1: Error de validacion/persistencia en BD

Requerimientos           RF-MSC-CRUD, RNF-Integridad-Datos

Prioridad                MUST


UC-009: Gestionar Clientes

Campo                    Descripcion

ID                       UC-009

Nombre                   Gestionar Clientes

Actor Primario           Admin / Operador del sistema

Actores Secundarios      API REST Spring Boot, BD MySQL

Precondicion             Sesion iniciada y permisos del modulo

Postcondicion (exito)    Cambios reflejados en la entidad correspondiente

Postcondicion (fallo)    No se persisten cambios; se retorna error de API

Endpoint real            GET|POST|PUT|DELETE /api/clientes/**

Flujo Principal          1. GET /api/clientes para listar 2. POST /api/clientes para crear 3. PUT /api/clientes/{id} para actualizar 4. GET /api/clientes/{id} para consultar detalle 5. DELETE /api/clientes/{id} para eliminar

Flujo Alternativo A      A-1: ID no encontrado -> respuesta vacia o error segun capa de servicio

Flujo de Excepcion       E-1: Error de validacion/persistencia en BD

Requerimientos           RF-MSC-CRUD, RNF-Integridad-Datos

Prioridad                MUST


UC-010: Gestionar Combos Promocionales

Campo                    Descripcion

ID                       UC-010

Nombre                   Gestionar Combos Promocionales

Actor Primario           Admin / Operador del sistema

Actores Secundarios      API REST Spring Boot, BD MySQL

Precondicion             Sesion iniciada y permisos del modulo

Postcondicion (exito)    Cambios reflejados en la entidad correspondiente

Postcondicion (fallo)    No se persisten cambios; se retorna error de API

Endpoint real            GET|POST|PUT|DELETE /api/combos_promocionales/**

Flujo Principal          1. GET /api/combos_promocionales para listar 2. POST /api/combos_promocionales para crear 3. PUT /api/combos_promocionales/{id} para actualizar 4. GET /api/combos_promocionales/{id} para consultar detalle 5. DELETE /api/combos_promocionales/{id} para eliminar

Flujo Alternativo A      A-1: ID no encontrado -> respuesta vacia o error segun capa de servicio

Flujo de Excepcion       E-1: Error de validacion/persistencia en BD

Requerimientos           RF-MSC-CRUD, RNF-Integridad-Datos

Prioridad                MUST


UC-011: Gestionar Composicion Combo

Campo                    Descripcion

ID                       UC-011

Nombre                   Gestionar Composicion Combo

Actor Primario           Admin / Operador del sistema

Actores Secundarios      API REST Spring Boot, BD MySQL

Precondicion             Sesion iniciada y permisos del modulo

Postcondicion (exito)    Cambios reflejados en la entidad correspondiente

Postcondicion (fallo)    No se persisten cambios; se retorna error de API

Endpoint real            GET|POST|PUT|DELETE /api/composicion_combo/**

Flujo Principal          1. GET /api/composicion_combo para listar 2. POST /api/composicion_combo para crear 3. PUT /api/composicion_combo/{id} para actualizar 4. GET /api/composicion_combo/{id} para consultar detalle 5. DELETE /api/composicion_combo/{id} para eliminar

Flujo Alternativo A      A-1: ID no encontrado -> respuesta vacia o error segun capa de servicio

Flujo de Excepcion       E-1: Error de validacion/persistencia en BD

Requerimientos           RF-MSC-CRUD, RNF-Integridad-Datos

Prioridad                MUST


UC-012: Gestionar Comprobantes Electronicos

Campo                    Descripcion

ID                       UC-012

Nombre                   Gestionar Comprobantes Electronicos

Actor Primario           Admin / Operador del sistema

Actores Secundarios      API REST Spring Boot, BD MySQL

Precondicion             Sesion iniciada y permisos del modulo

Postcondicion (exito)    Cambios reflejados en la entidad correspondiente

Postcondicion (fallo)    No se persisten cambios; se retorna error de API

Endpoint real            GET|POST|PUT|DELETE /api/comprobantes_electronicos/**

Flujo Principal          1. GET /api/comprobantes_electronicos para listar 2. POST /api/comprobantes_electronicos para crear 3. PUT /api/comprobantes_electronicos/{id} para actualizar 4. GET /api/comprobantes_electronicos/{id} para consultar detalle 5. DELETE /api/comprobantes_electronicos/{id} para eliminar

Flujo Alternativo A      A-1: ID no encontrado -> respuesta vacia o error segun capa de servicio

Flujo de Excepcion       E-1: Error de validacion/persistencia en BD

Requerimientos           RF-MSC-CRUD, RNF-Integridad-Datos

Prioridad                MUST


UC-013: Gestionar Configuracion Global

Campo                    Descripcion

ID                       UC-013

Nombre                   Gestionar Configuracion Global

Actor Primario           Admin / Operador del sistema

Actores Secundarios      API REST Spring Boot, BD MySQL

Precondicion             Sesion iniciada y permisos del modulo

Postcondicion (exito)    Cambios reflejados en la entidad correspondiente

Postcondicion (fallo)    No se persisten cambios; se retorna error de API

Endpoint real            GET|POST|PUT|DELETE /api/configuracion_global/**

Flujo Principal          1. GET /api/configuracion_global para listar 2. POST /api/configuracion_global para crear 3. PUT /api/configuracion_global/{id} para actualizar 4. GET /api/configuracion_global/{id} para consultar detalle 5. DELETE /api/configuracion_global/{id} para eliminar

Flujo Alternativo A      A-1: ID no encontrado -> respuesta vacia o error segun capa de servicio

Flujo de Excepcion       E-1: Error de validacion/persistencia en BD

Requerimientos           RF-MSC-CRUD, RNF-Integridad-Datos

Prioridad                MUST


UC-014: Gestionar Cuentas Por Pagar

Campo                    Descripcion

ID                       UC-014

Nombre                   Gestionar Cuentas Por Pagar

Actor Primario           Admin / Operador del sistema

Actores Secundarios      API REST Spring Boot, BD MySQL

Precondicion             Sesion iniciada y permisos del modulo

Postcondicion (exito)    Cambios reflejados en la entidad correspondiente

Postcondicion (fallo)    No se persisten cambios; se retorna error de API

Endpoint real            GET|POST|PUT|DELETE /api/cuentas_por_pagar/**

Flujo Principal          1. GET /api/cuentas_por_pagar para listar 2. POST /api/cuentas_por_pagar para crear 3. PUT /api/cuentas_por_pagar/{id} para actualizar 4. GET /api/cuentas_por_pagar/{id} para consultar detalle 5. DELETE /api/cuentas_por_pagar/{id} para eliminar

Flujo Alternativo A      A-1: ID no encontrado -> respuesta vacia o error segun capa de servicio

Flujo de Excepcion       E-1: Error de validacion/persistencia en BD

Requerimientos           RF-MSC-CRUD, RNF-Integridad-Datos

Prioridad                MUST


UC-015: Gestionar Detalle Devolucion Proveedor

Campo                    Descripcion

ID                       UC-015

Nombre                   Gestionar Detalle Devolucion Proveedor

Actor Primario           Admin / Operador del sistema

Actores Secundarios      API REST Spring Boot, BD MySQL

Precondicion             Sesion iniciada y permisos del modulo

Postcondicion (exito)    Cambios reflejados en la entidad correspondiente

Postcondicion (fallo)    No se persisten cambios; se retorna error de API

Endpoint real            GET|POST|PUT|DELETE /api/detalle_devolucion_proveedor/**

Flujo Principal          1. GET /api/detalle_devolucion_proveedor para listar 2. POST /api/detalle_devolucion_proveedor para crear 3. PUT /api/detalle_devolucion_proveedor/{id} para actualizar 4. GET /api/detalle_devolucion_proveedor/{id} para consultar detalle 5. DELETE /api/detalle_devolucion_proveedor/{id} para eliminar

Flujo Alternativo A      A-1: ID no encontrado -> respuesta vacia o error segun capa de servicio

Flujo de Excepcion       E-1: Error de validacion/persistencia en BD

Requerimientos           RF-MSC-CRUD, RNF-Integridad-Datos

Prioridad                MUST


UC-016: Gestionar Detalle Devolucion Venta

Campo                    Descripcion

ID                       UC-016

Nombre                   Gestionar Detalle Devolucion Venta

Actor Primario           Admin / Operador del sistema

Actores Secundarios      API REST Spring Boot, BD MySQL

Precondicion             Sesion iniciada y permisos del modulo

Postcondicion (exito)    Cambios reflejados en la entidad correspondiente

Postcondicion (fallo)    No se persisten cambios; se retorna error de API

Endpoint real            GET|POST|PUT|DELETE /api/detalle_devolucion_venta/**

Flujo Principal          1. GET /api/detalle_devolucion_venta para listar 2. POST /api/detalle_devolucion_venta para crear 3. PUT /api/detalle_devolucion_venta/{id} para actualizar 4. GET /api/detalle_devolucion_venta/{id} para consultar detalle 5. DELETE /api/detalle_devolucion_venta/{id} para eliminar

Flujo Alternativo A      A-1: ID no encontrado -> respuesta vacia o error segun capa de servicio

Flujo de Excepcion       E-1: Error de validacion/persistencia en BD

Requerimientos           RF-MSC-CRUD, RNF-Integridad-Datos

Prioridad                MUST


UC-017: Gestionar Detalle Orden Compra

Campo                    Descripcion

ID                       UC-017

Nombre                   Gestionar Detalle Orden Compra

Actor Primario           Admin / Operador del sistema

Actores Secundarios      API REST Spring Boot, BD MySQL

Precondicion             Sesion iniciada y permisos del modulo

Postcondicion (exito)    Cambios reflejados en la entidad correspondiente

Postcondicion (fallo)    No se persisten cambios; se retorna error de API

Endpoint real            GET|POST|PUT|DELETE /api/detalle_orden_compra/**

Flujo Principal          1. GET /api/detalle_orden_compra para listar 2. POST /api/detalle_orden_compra para crear 3. PUT /api/detalle_orden_compra/{id} para actualizar 4. GET /api/detalle_orden_compra/{id} para consultar detalle 5. DELETE /api/detalle_orden_compra/{id} para eliminar

Flujo Alternativo A      A-1: ID no encontrado -> respuesta vacia o error segun capa de servicio

Flujo de Excepcion       E-1: Error de validacion/persistencia en BD

Requerimientos           RF-MSC-CRUD, RNF-Integridad-Datos

Prioridad                MUST


UC-018: Gestionar Detalle Pedido

Campo                    Descripcion

ID                       UC-018

Nombre                   Gestionar Detalle Pedido

Actor Primario           Admin / Operador del sistema

Actores Secundarios      API REST Spring Boot, BD MySQL

Precondicion             Sesion iniciada y permisos del modulo

Postcondicion (exito)    Cambios reflejados en la entidad correspondiente

Postcondicion (fallo)    No se persisten cambios; se retorna error de API

Endpoint real            GET|POST|PUT|DELETE /api/detalle_pedido/**

Flujo Principal          1. GET /api/detalle_pedido para listar 2. POST /api/detalle_pedido para crear 3. PUT /api/detalle_pedido/{id} para actualizar 4. GET /api/detalle_pedido/{id} para consultar detalle 5. DELETE /api/detalle_pedido/{id} para eliminar

Flujo Alternativo A      A-1: ID no encontrado -> respuesta vacia o error segun capa de servicio

Flujo de Excepcion       E-1: Error de validacion/persistencia en BD

Requerimientos           RF-MSC-CRUD, RNF-Integridad-Datos

Prioridad                MUST


UC-019: Gestionar Detalle Venta

Campo                    Descripcion

ID                       UC-019

Nombre                   Gestionar Detalle Venta

Actor Primario           Admin / Operador del sistema

Actores Secundarios      API REST Spring Boot, BD MySQL

Precondicion             Sesion iniciada y permisos del modulo

Postcondicion (exito)    Cambios reflejados en la entidad correspondiente

Postcondicion (fallo)    No se persisten cambios; se retorna error de API

Endpoint real            GET|POST|PUT|DELETE /api/detalle_venta/**

Flujo Principal          1. GET /api/detalle_venta para listar 2. POST /api/detalle_venta para crear 3. PUT /api/detalle_venta/{id} para actualizar 4. GET /api/detalle_venta/{id} para consultar detalle 5. DELETE /api/detalle_venta/{id} para eliminar

Flujo Alternativo A      A-1: ID no encontrado -> respuesta vacia o error segun capa de servicio

Flujo de Excepcion       E-1: Error de validacion/persistencia en BD

Requerimientos           RF-MSC-CRUD, RNF-Integridad-Datos

Prioridad                MUST


UC-020: Gestionar Devoluciones Proveedor

Campo                    Descripcion

ID                       UC-020

Nombre                   Gestionar Devoluciones Proveedor

Actor Primario           Admin / Operador del sistema

Actores Secundarios      API REST Spring Boot, BD MySQL

Precondicion             Sesion iniciada y permisos del modulo

Postcondicion (exito)    Cambios reflejados en la entidad correspondiente

Postcondicion (fallo)    No se persisten cambios; se retorna error de API

Endpoint real            GET|POST|PUT|DELETE /api/devoluciones_proveedor/**

Flujo Principal          1. GET /api/devoluciones_proveedor para listar 2. POST /api/devoluciones_proveedor para crear 3. PUT /api/devoluciones_proveedor/{id} para actualizar 4. GET /api/devoluciones_proveedor/{id} para consultar detalle 5. DELETE /api/devoluciones_proveedor/{id} para eliminar

Flujo Alternativo A      A-1: ID no encontrado -> respuesta vacia o error segun capa de servicio

Flujo de Excepcion       E-1: Error de validacion/persistencia en BD

Requerimientos           RF-MSC-CRUD, RNF-Integridad-Datos

Prioridad                MUST


UC-021: Gestionar Devoluciones Venta

Campo                    Descripcion

ID                       UC-021

Nombre                   Gestionar Devoluciones Venta

Actor Primario           Admin / Operador del sistema

Actores Secundarios      API REST Spring Boot, BD MySQL

Precondicion             Sesion iniciada y permisos del modulo

Postcondicion (exito)    Cambios reflejados en la entidad correspondiente

Postcondicion (fallo)    No se persisten cambios; se retorna error de API

Endpoint real            GET|POST|PUT|DELETE /api/devoluciones_venta/**

Flujo Principal          1. GET /api/devoluciones_venta para listar 2. POST /api/devoluciones_venta para crear 3. PUT /api/devoluciones_venta/{id} para actualizar 4. GET /api/devoluciones_venta/{id} para consultar detalle 5. DELETE /api/devoluciones_venta/{id} para eliminar

Flujo Alternativo A      A-1: ID no encontrado -> respuesta vacia o error segun capa de servicio

Flujo de Excepcion       E-1: Error de validacion/persistencia en BD

Requerimientos           RF-MSC-CRUD, RNF-Integridad-Datos

Prioridad                MUST


UC-022: Gestionar Facturas Suscripcion

Campo                    Descripcion

ID                       UC-022

Nombre                   Gestionar Facturas Suscripcion

Actor Primario           Admin / Operador del sistema

Actores Secundarios      API REST Spring Boot, BD MySQL

Precondicion             Sesion iniciada y permisos del modulo

Postcondicion (exito)    Cambios reflejados en la entidad correspondiente

Postcondicion (fallo)    No se persisten cambios; se retorna error de API

Endpoint real            GET|POST|PUT|DELETE /api/facturas_suscripcion/**

Flujo Principal          1. GET /api/facturas_suscripcion para listar 2. POST /api/facturas_suscripcion para crear 3. PUT /api/facturas_suscripcion/{id} para actualizar 4. GET /api/facturas_suscripcion/{id} para consultar detalle 5. DELETE /api/facturas_suscripcion/{id} para eliminar

Flujo Alternativo A      A-1: ID no encontrado -> respuesta vacia o error segun capa de servicio

Flujo de Excepcion       E-1: Error de validacion/persistencia en BD

Requerimientos           RF-MSC-CRUD, RNF-Integridad-Datos

Prioridad                MUST


UC-023: Gestionar Formas Pago Venta

Campo                    Descripcion

ID                       UC-023

Nombre                   Gestionar Formas Pago Venta

Actor Primario           Admin / Operador del sistema

Actores Secundarios      API REST Spring Boot, BD MySQL

Precondicion             Sesion iniciada y permisos del modulo

Postcondicion (exito)    Cambios reflejados en la entidad correspondiente

Postcondicion (fallo)    No se persisten cambios; se retorna error de API

Endpoint real            GET|POST|PUT|DELETE /api/formas_pago_venta/**

Flujo Principal          1. GET /api/formas_pago_venta para listar 2. POST /api/formas_pago_venta para crear 3. PUT /api/formas_pago_venta/{id} para actualizar 4. GET /api/formas_pago_venta/{id} para consultar detalle 5. DELETE /api/formas_pago_venta/{id} para eliminar

Flujo Alternativo A      A-1: ID no encontrado -> respuesta vacia o error segun capa de servicio

Flujo de Excepcion       E-1: Error de validacion/persistencia en BD

Requerimientos           RF-MSC-CRUD, RNF-Integridad-Datos

Prioridad                MUST


UC-024: Gestionar Gastos Operativos

Campo                    Descripcion

ID                       UC-024

Nombre                   Gestionar Gastos Operativos

Actor Primario           Admin / Operador del sistema

Actores Secundarios      API REST Spring Boot, BD MySQL

Precondicion             Sesion iniciada y permisos del modulo

Postcondicion (exito)    Cambios reflejados en la entidad correspondiente

Postcondicion (fallo)    No se persisten cambios; se retorna error de API

Endpoint real            GET|POST|PUT|DELETE /api/gastos_operativos/**

Flujo Principal          1. GET /api/gastos_operativos para listar 2. POST /api/gastos_operativos para crear 3. PUT /api/gastos_operativos/{id} para actualizar 4. GET /api/gastos_operativos/{id} para consultar detalle 5. DELETE /api/gastos_operativos/{id} para eliminar

Flujo Alternativo A      A-1: ID no encontrado -> respuesta vacia o error segun capa de servicio

Flujo de Excepcion       E-1: Error de validacion/persistencia en BD

Requerimientos           RF-MSC-CRUD, RNF-Integridad-Datos

Prioridad                MUST


UC-025: Gestionar Gastos Recurrentes

Campo                    Descripcion

ID                       UC-025

Nombre                   Gestionar Gastos Recurrentes

Actor Primario           Admin / Operador del sistema

Actores Secundarios      API REST Spring Boot, BD MySQL

Precondicion             Sesion iniciada y permisos del modulo

Postcondicion (exito)    Cambios reflejados en la entidad correspondiente

Postcondicion (fallo)    No se persisten cambios; se retorna error de API

Endpoint real            GET|POST|PUT|DELETE /api/gastos_recurrentes/**

Flujo Principal          1. GET /api/gastos_recurrentes para listar 2. POST /api/gastos_recurrentes para crear 3. PUT /api/gastos_recurrentes/{id} para actualizar 4. GET /api/gastos_recurrentes/{id} para consultar detalle 5. DELETE /api/gastos_recurrentes/{id} para eliminar

Flujo Alternativo A      A-1: ID no encontrado -> respuesta vacia o error segun capa de servicio

Flujo de Excepcion       E-1: Error de validacion/persistencia en BD

Requerimientos           RF-MSC-CRUD, RNF-Integridad-Datos

Prioridad                MUST


UC-026: Gestionar Horarios Operacion

Campo                    Descripcion

ID                       UC-026

Nombre                   Gestionar Horarios Operacion

Actor Primario           Admin / Operador del sistema

Actores Secundarios      API REST Spring Boot, BD MySQL

Precondicion             Sesion iniciada y permisos del modulo

Postcondicion (exito)    Cambios reflejados en la entidad correspondiente

Postcondicion (fallo)    No se persisten cambios; se retorna error de API

Endpoint real            GET|POST|PUT|DELETE /api/horarios_operacion/**

Flujo Principal          1. GET /api/horarios_operacion para listar 2. POST /api/horarios_operacion para crear 3. PUT /api/horarios_operacion/{id} para actualizar 4. GET /api/horarios_operacion/{id} para consultar detalle 5. DELETE /api/horarios_operacion/{id} para eliminar

Flujo Alternativo A      A-1: ID no encontrado -> respuesta vacia o error segun capa de servicio

Flujo de Excepcion       E-1: Error de validacion/persistencia en BD

Requerimientos           RF-MSC-CRUD, RNF-Integridad-Datos

Prioridad                MUST


UC-027: Gestionar Lotes Inventario

Campo                    Descripcion

ID                       UC-027

Nombre                   Gestionar Lotes Inventario

Actor Primario           Admin / Operador del sistema

Actores Secundarios      API REST Spring Boot, BD MySQL

Precondicion             Sesion iniciada y permisos del modulo

Postcondicion (exito)    Cambios reflejados en la entidad correspondiente

Postcondicion (fallo)    No se persisten cambios; se retorna error de API

Endpoint real            GET|POST|PUT|DELETE /api/lotes_inventario/**

Flujo Principal          1. GET /api/lotes_inventario para listar 2. POST /api/lotes_inventario para crear 3. PUT /api/lotes_inventario/{id} para actualizar 4. GET /api/lotes_inventario/{id} para consultar detalle 5. DELETE /api/lotes_inventario/{id} para eliminar

Flujo Alternativo A      A-1: ID no encontrado -> respuesta vacia o error segun capa de servicio

Flujo de Excepcion       E-1: Error de validacion/persistencia en BD

Requerimientos           RF-MSC-CRUD, RNF-Integridad-Datos

Prioridad                MUST


UC-028: Gestionar Marcas

Campo                    Descripcion

ID                       UC-028

Nombre                   Gestionar Marcas

Actor Primario           Admin / Operador del sistema

Actores Secundarios      API REST Spring Boot, BD MySQL

Precondicion             Sesion iniciada y permisos del modulo

Postcondicion (exito)    Cambios reflejados en la entidad correspondiente

Postcondicion (fallo)    No se persisten cambios; se retorna error de API

Endpoint real            GET|POST|PUT|DELETE /api/marcas/**

Flujo Principal          1. GET /api/marcas para listar 2. POST /api/marcas para crear 3. PUT /api/marcas/{id} para actualizar 4. GET /api/marcas/{id} para consultar detalle 5. DELETE /api/marcas/{id} para eliminar

Flujo Alternativo A      A-1: ID no encontrado -> respuesta vacia o error segun capa de servicio

Flujo de Excepcion       E-1: Error de validacion/persistencia en BD

Requerimientos           RF-MSC-CRUD, RNF-Integridad-Datos

Prioridad                MUST


UC-029: Gestionar Metodos Pago

Campo                    Descripcion

ID                       UC-029

Nombre                   Gestionar Metodos Pago

Actor Primario           Admin / Operador del sistema

Actores Secundarios      API REST Spring Boot, BD MySQL

Precondicion             Sesion iniciada y permisos del modulo

Postcondicion (exito)    Cambios reflejados en la entidad correspondiente

Postcondicion (fallo)    No se persisten cambios; se retorna error de API

Endpoint real            GET|POST|PUT|DELETE /api/metodos_pago/**

Flujo Principal          1. GET /api/metodos_pago para listar 2. POST /api/metodos_pago para crear 3. PUT /api/metodos_pago/{id} para actualizar 4. GET /api/metodos_pago/{id} para consultar detalle 5. DELETE /api/metodos_pago/{id} para eliminar

Flujo Alternativo A      A-1: ID no encontrado -> respuesta vacia o error segun capa de servicio

Flujo de Excepcion       E-1: Error de validacion/persistencia en BD

Requerimientos           RF-MSC-CRUD, RNF-Integridad-Datos

Prioridad                MUST


UC-030: Gestionar Movimientos Inventario

Campo                    Descripcion

ID                       UC-030

Nombre                   Gestionar Movimientos Inventario

Actor Primario           Admin / Operador del sistema

Actores Secundarios      API REST Spring Boot, BD MySQL

Precondicion             Sesion iniciada y permisos del modulo

Postcondicion (exito)    Cambios reflejados en la entidad correspondiente

Postcondicion (fallo)    No se persisten cambios; se retorna error de API

Endpoint real            GET|POST|PUT|DELETE /api/movimientos_inventario/**

Flujo Principal          1. GET /api/movimientos_inventario para listar 2. POST /api/movimientos_inventario para crear 3. PUT /api/movimientos_inventario/{id} para actualizar 4. GET /api/movimientos_inventario/{id} para consultar detalle 5. DELETE /api/movimientos_inventario/{id} para eliminar

Flujo Alternativo A      A-1: ID no encontrado -> respuesta vacia o error segun capa de servicio

Flujo de Excepcion       E-1: Error de validacion/persistencia en BD

Requerimientos           RF-MSC-CRUD, RNF-Integridad-Datos

Prioridad                MUST


UC-031: Gestionar Notificaciones

Campo                    Descripcion

ID                       UC-031

Nombre                   Gestionar Notificaciones

Actor Primario           Admin / Operador del sistema

Actores Secundarios      API REST Spring Boot, BD MySQL

Precondicion             Sesion iniciada y permisos del modulo

Postcondicion (exito)    Cambios reflejados en la entidad correspondiente

Postcondicion (fallo)    No se persisten cambios; se retorna error de API

Endpoint real            GET|POST|PUT|DELETE /api/notificaciones/**

Flujo Principal          1. GET /api/notificaciones para listar 2. POST /api/notificaciones para crear 3. PUT /api/notificaciones/{id} para actualizar 4. GET /api/notificaciones/{id} para consultar detalle 5. DELETE /api/notificaciones/{id} para eliminar

Flujo Alternativo A      A-1: ID no encontrado -> respuesta vacia o error segun capa de servicio

Flujo de Excepcion       E-1: Error de validacion/persistencia en BD

Requerimientos           RF-MSC-CRUD, RNF-Integridad-Datos

Prioridad                MUST


UC-032: Gestionar Ordenes Compra

Campo                    Descripcion

ID                       UC-032

Nombre                   Gestionar Ordenes Compra

Actor Primario           Admin / Operador del sistema

Actores Secundarios      API REST Spring Boot, BD MySQL

Precondicion             Sesion iniciada y permisos del modulo

Postcondicion (exito)    Cambios reflejados en la entidad correspondiente

Postcondicion (fallo)    No se persisten cambios; se retorna error de API

Endpoint real            GET|POST|PUT|DELETE /api/ordenes_compra/**

Flujo Principal          1. GET /api/ordenes_compra para listar 2. POST /api/ordenes_compra para crear 3. PUT /api/ordenes_compra/{id} para actualizar 4. GET /api/ordenes_compra/{id} para consultar detalle 5. DELETE /api/ordenes_compra/{id} para eliminar

Flujo Alternativo A      A-1: ID no encontrado -> respuesta vacia o error segun capa de servicio

Flujo de Excepcion       E-1: Error de validacion/persistencia en BD

Requerimientos           RF-MSC-CRUD, RNF-Integridad-Datos

Prioridad                MUST


UC-033: Gestionar Pagos Proveedor

Campo                    Descripcion

ID                       UC-033

Nombre                   Gestionar Pagos Proveedor

Actor Primario           Admin / Operador del sistema

Actores Secundarios      API REST Spring Boot, BD MySQL

Precondicion             Sesion iniciada y permisos del modulo

Postcondicion (exito)    Cambios reflejados en la entidad correspondiente

Postcondicion (fallo)    No se persisten cambios; se retorna error de API

Endpoint real            GET|POST|PUT|DELETE /api/pagos_proveedor/**

Flujo Principal          1. GET /api/pagos_proveedor para listar 2. POST /api/pagos_proveedor para crear 3. PUT /api/pagos_proveedor/{id} para actualizar 4. GET /api/pagos_proveedor/{id} para consultar detalle 5. DELETE /api/pagos_proveedor/{id} para eliminar

Flujo Alternativo A      A-1: ID no encontrado -> respuesta vacia o error segun capa de servicio

Flujo de Excepcion       E-1: Error de validacion/persistencia en BD

Requerimientos           RF-MSC-CRUD, RNF-Integridad-Datos

Prioridad                MUST


UC-034: Gestionar Pedidos

Campo                    Descripcion

ID                       UC-034

Nombre                   Gestionar Pedidos

Actor Primario           Admin / Operador del sistema

Actores Secundarios      API REST Spring Boot, BD MySQL

Precondicion             Sesion iniciada y permisos del modulo

Postcondicion (exito)    Cambios reflejados en la entidad correspondiente

Postcondicion (fallo)    No se persisten cambios; se retorna error de API

Endpoint real            GET|POST|PUT|DELETE /api/pedidos/**

Flujo Principal          1. GET /api/pedidos para listar 2. POST /api/pedidos para crear 3. PUT /api/pedidos/{id} para actualizar 4. GET /api/pedidos/{id} para consultar detalle 5. DELETE /api/pedidos/{id} para eliminar

Flujo Alternativo A      A-1: ID no encontrado -> respuesta vacia o error segun capa de servicio

Flujo de Excepcion       E-1: Error de validacion/persistencia en BD

Requerimientos           RF-MSC-CRUD, RNF-Integridad-Datos

Prioridad                MUST


UC-035: Gestionar Permisos Rol

Campo                    Descripcion

ID                       UC-035

Nombre                   Gestionar Permisos Rol

Actor Primario           Admin / Operador del sistema

Actores Secundarios      API REST Spring Boot, BD MySQL

Precondicion             Sesion iniciada y permisos del modulo

Postcondicion (exito)    Cambios reflejados en la entidad correspondiente

Postcondicion (fallo)    No se persisten cambios; se retorna error de API

Endpoint real            GET|POST|PUT|DELETE /api/permisos_rol/**

Flujo Principal          1. GET /api/permisos_rol para listar 2. POST /api/permisos_rol para crear 3. PUT /api/permisos_rol/{id} para actualizar 4. GET /api/permisos_rol/{id} para consultar detalle 5. DELETE /api/permisos_rol/{id} para eliminar

Flujo Alternativo A      A-1: ID no encontrado -> respuesta vacia o error segun capa de servicio

Flujo de Excepcion       E-1: Error de validacion/persistencia en BD

Requerimientos           RF-MSC-CRUD, RNF-Integridad-Datos

Prioridad                MUST


UC-036: Gestionar Planes Suscripcion

Campo                    Descripcion

ID                       UC-036

Nombre                   Gestionar Planes Suscripcion

Actor Primario           Admin / Operador del sistema

Actores Secundarios      API REST Spring Boot, BD MySQL

Precondicion             Sesion iniciada y permisos del modulo

Postcondicion (exito)    Cambios reflejados en la entidad correspondiente

Postcondicion (fallo)    No se persisten cambios; se retorna error de API

Endpoint real            GET|POST|PUT|DELETE /api/planes_suscripcion/**

Flujo Principal          1. GET /api/planes_suscripcion para listar 2. POST /api/planes_suscripcion para crear 3. PUT /api/planes_suscripcion/{id} para actualizar 4. GET /api/planes_suscripcion/{id} para consultar detalle 5. DELETE /api/planes_suscripcion/{id} para eliminar

Flujo Alternativo A      A-1: ID no encontrado -> respuesta vacia o error segun capa de servicio

Flujo de Excepcion       E-1: Error de validacion/persistencia en BD

Requerimientos           RF-MSC-CRUD, RNF-Integridad-Datos

Prioridad                MUST


UC-037: Gestionar Precios Plan

Campo                    Descripcion

ID                       UC-037

Nombre                   Gestionar Precios Plan

Actor Primario           Admin / Operador del sistema

Actores Secundarios      API REST Spring Boot, BD MySQL

Precondicion             Sesion iniciada y permisos del modulo

Postcondicion (exito)    Cambios reflejados en la entidad correspondiente

Postcondicion (fallo)    No se persisten cambios; se retorna error de API

Endpoint real            GET|POST|PUT|DELETE /api/precios_plan/**

Flujo Principal          1. GET /api/precios_plan para listar 2. POST /api/precios_plan para crear 3. PUT /api/precios_plan/{id} para actualizar 4. GET /api/precios_plan/{id} para consultar detalle 5. DELETE /api/precios_plan/{id} para eliminar

Flujo Alternativo A      A-1: ID no encontrado -> respuesta vacia o error segun capa de servicio

Flujo de Excepcion       E-1: Error de validacion/persistencia en BD

Requerimientos           RF-MSC-CRUD, RNF-Integridad-Datos

Prioridad                MUST


UC-038: Gestionar Preferencias Usuario

Campo                    Descripcion

ID                       UC-038

Nombre                   Gestionar Preferencias Usuario

Actor Primario           Admin / Operador del sistema

Actores Secundarios      API REST Spring Boot, BD MySQL

Precondicion             Sesion iniciada y permisos del modulo

Postcondicion (exito)    Cambios reflejados en la entidad correspondiente

Postcondicion (fallo)    No se persisten cambios; se retorna error de API

Endpoint real            GET|POST|PUT|DELETE /api/preferencias_usuario/**

Flujo Principal          1. GET /api/preferencias_usuario para listar 2. POST /api/preferencias_usuario para crear 3. PUT /api/preferencias_usuario/{id} para actualizar 4. GET /api/preferencias_usuario/{id} para consultar detalle 5. DELETE /api/preferencias_usuario/{id} para eliminar

Flujo Alternativo A      A-1: ID no encontrado -> respuesta vacia o error segun capa de servicio

Flujo de Excepcion       E-1: Error de validacion/persistencia en BD

Requerimientos           RF-MSC-CRUD, RNF-Integridad-Datos

Prioridad                MUST


UC-039: Gestionar Productos

Campo                    Descripcion

ID                       UC-039

Nombre                   Gestionar Productos

Actor Primario           Admin / Operador del sistema

Actores Secundarios      API REST Spring Boot, BD MySQL

Precondicion             Sesion iniciada y permisos del modulo

Postcondicion (exito)    Cambios reflejados en la entidad correspondiente

Postcondicion (fallo)    No se persisten cambios; se retorna error de API

Endpoint real            GET|POST|PUT|DELETE /api/productos/**

Flujo Principal          1. GET /api/productos para listar 2. POST /api/productos para crear 3. PUT /api/productos/{id} para actualizar 4. GET /api/productos/{id} para consultar detalle 5. DELETE /api/productos/{id} para eliminar

Flujo Alternativo A      A-1: ID no encontrado -> respuesta vacia o error segun capa de servicio

Flujo de Excepcion       E-1: Error de validacion/persistencia en BD

Requerimientos           RF-MSC-CRUD, RNF-Integridad-Datos

Prioridad                MUST


UC-040: Gestionar Promociones

Campo                    Descripcion

ID                       UC-040

Nombre                   Gestionar Promociones

Actor Primario           Admin / Operador del sistema

Actores Secundarios      API REST Spring Boot, BD MySQL

Precondicion             Sesion iniciada y permisos del modulo

Postcondicion (exito)    Cambios reflejados en la entidad correspondiente

Postcondicion (fallo)    No se persisten cambios; se retorna error de API

Endpoint real            GET|POST|PUT|DELETE /api/promociones/**

Flujo Principal          1. GET /api/promociones para listar 2. POST /api/promociones para crear 3. PUT /api/promociones/{id} para actualizar 4. GET /api/promociones/{id} para consultar detalle 5. DELETE /api/promociones/{id} para eliminar

Flujo Alternativo A      A-1: ID no encontrado -> respuesta vacia o error segun capa de servicio

Flujo de Excepcion       E-1: Error de validacion/persistencia en BD

Requerimientos           RF-MSC-CRUD, RNF-Integridad-Datos

Prioridad                MUST


UC-041: Gestionar Proveedor Categorias

Campo                    Descripcion

ID                       UC-041

Nombre                   Gestionar Proveedor Categorias

Actor Primario           Admin / Operador del sistema

Actores Secundarios      API REST Spring Boot, BD MySQL

Precondicion             Sesion iniciada y permisos del modulo

Postcondicion (exito)    Cambios reflejados en la entidad correspondiente

Postcondicion (fallo)    No se persisten cambios; se retorna error de API

Endpoint real            GET|POST|PUT|DELETE /api/proveedor_categorias/**

Flujo Principal          1. GET /api/proveedor_categorias para listar 2. POST /api/proveedor_categorias para crear 3. PUT /api/proveedor_categorias/{id} para actualizar 4. GET /api/proveedor_categorias/{id} para consultar detalle 5. DELETE /api/proveedor_categorias/{id} para eliminar

Flujo Alternativo A      A-1: ID no encontrado -> respuesta vacia o error segun capa de servicio

Flujo de Excepcion       E-1: Error de validacion/persistencia en BD

Requerimientos           RF-MSC-CRUD, RNF-Integridad-Datos

Prioridad                MUST


UC-042: Gestionar Proveedores

Campo                    Descripcion

ID                       UC-042

Nombre                   Gestionar Proveedores

Actor Primario           Admin / Operador del sistema

Actores Secundarios      API REST Spring Boot, BD MySQL

Precondicion             Sesion iniciada y permisos del modulo

Postcondicion (exito)    Cambios reflejados en la entidad correspondiente

Postcondicion (fallo)    No se persisten cambios; se retorna error de API

Endpoint real            GET|POST|PUT|DELETE /api/proveedores/**

Flujo Principal          1. GET /api/proveedores para listar 2. POST /api/proveedores para crear 3. PUT /api/proveedores/{id} para actualizar 4. GET /api/proveedores/{id} para consultar detalle 5. DELETE /api/proveedores/{id} para eliminar

Flujo Alternativo A      A-1: ID no encontrado -> respuesta vacia o error segun capa de servicio

Flujo de Excepcion       E-1: Error de validacion/persistencia en BD

Requerimientos           RF-MSC-CRUD, RNF-Integridad-Datos

Prioridad                MUST


UC-043: Gestionar Reclamos

Campo                    Descripcion

ID                       UC-043

Nombre                   Gestionar Reclamos

Actor Primario           Admin / Operador del sistema

Actores Secundarios      API REST Spring Boot, BD MySQL

Precondicion             Sesion iniciada y permisos del modulo

Postcondicion (exito)    Cambios reflejados en la entidad correspondiente

Postcondicion (fallo)    No se persisten cambios; se retorna error de API

Endpoint real            GET|POST|PUT|DELETE /api/reclamos/**

Flujo Principal          1. GET /api/reclamos para listar 2. POST /api/reclamos para crear 3. PUT /api/reclamos/{id} para actualizar 4. GET /api/reclamos/{id} para consultar detalle 5. DELETE /api/reclamos/{id} para eliminar

Flujo Alternativo A      A-1: ID no encontrado -> respuesta vacia o error segun capa de servicio

Flujo de Excepcion       E-1: Error de validacion/persistencia en BD

Requerimientos           RF-MSC-CRUD, RNF-Integridad-Datos

Prioridad                MUST


UC-044: Gestionar Registros

Campo                    Descripcion

ID                       UC-044

Nombre                   Gestionar Registros

Actor Primario           Admin / Operador del sistema

Actores Secundarios      API REST Spring Boot, BD MySQL

Precondicion             Sesion iniciada y permisos del modulo

Postcondicion (exito)    Cambios reflejados en la entidad correspondiente

Postcondicion (fallo)    No se persisten cambios; se retorna error de API

Endpoint real            GET|POST|PUT|DELETE /api/registros/**

Flujo Principal          1. GET /api/registros o GET /api/registros/{id} para consultar 2. POST /api/registros para crear registro 3. PUT /api/registros/{id} para actualizar 4. DELETE /api/registros/{id} para eliminar

Flujo Alternativo A      A-1: ID no encontrado -> respuesta vacia o error segun capa de servicio

Flujo de Excepcion       E-1: Error de validacion/persistencia en BD

Requerimientos           RF-MSC-CRUD, RNF-Integridad-Datos

Prioridad                MUST


UC-045: Gestionar Repartidores

Campo                    Descripcion

ID                       UC-045

Nombre                   Gestionar Repartidores

Actor Primario           Admin / Operador del sistema

Actores Secundarios      API REST Spring Boot, BD MySQL

Precondicion             Sesion iniciada y permisos del modulo

Postcondicion (exito)    Cambios reflejados en la entidad correspondiente

Postcondicion (fallo)    No se persisten cambios; se retorna error de API

Endpoint real            GET|POST|PUT|DELETE /api/repartidores/**

Flujo Principal          1. GET /api/repartidores para listar 2. POST /api/repartidores para crear 3. PUT /api/repartidores/{id} para actualizar 4. GET /api/repartidores/{id} para consultar detalle 5. DELETE /api/repartidores/{id} para eliminar

Flujo Alternativo A      A-1: ID no encontrado -> respuesta vacia o error segun capa de servicio

Flujo de Excepcion       E-1: Error de validacion/persistencia en BD

Requerimientos           RF-MSC-CRUD, RNF-Integridad-Datos

Prioridad                MUST


UC-046: Gestionar Roles Personalizados

Campo                    Descripcion

ID                       UC-046

Nombre                   Gestionar Roles Personalizados

Actor Primario           Admin / Operador del sistema

Actores Secundarios      API REST Spring Boot, BD MySQL

Precondicion             Sesion iniciada y permisos del modulo

Postcondicion (exito)    Cambios reflejados en la entidad correspondiente

Postcondicion (fallo)    No se persisten cambios; se retorna error de API

Endpoint real            GET|POST|PUT|DELETE /api/roles_personalizados/**

Flujo Principal          1. GET /api/roles_personalizados para listar 2. POST /api/roles_personalizados para crear 3. PUT /api/roles_personalizados/{id} para actualizar 4. GET /api/roles_personalizados/{id} para consultar detalle 5. DELETE /api/roles_personalizados/{id} para eliminar

Flujo Alternativo A      A-1: ID no encontrado -> respuesta vacia o error segun capa de servicio

Flujo de Excepcion       E-1: Error de validacion/persistencia en BD

Requerimientos           RF-MSC-CRUD, RNF-Integridad-Datos

Prioridad                MUST


UC-047: Gestionar Sedes

Campo                    Descripcion

ID                       UC-047

Nombre                   Gestionar Sedes

Actor Primario           Admin / Operador del sistema

Actores Secundarios      API REST Spring Boot, BD MySQL

Precondicion             Sesion iniciada y permisos del modulo

Postcondicion (exito)    Cambios reflejados en la entidad correspondiente

Postcondicion (fallo)    No se persisten cambios; se retorna error de API

Endpoint real            GET|POST|PUT|DELETE /api/sedes/**

Flujo Principal          1. GET /api/sedes para listar 2. POST /api/sedes para crear 3. PUT /api/sedes/{id} para actualizar 4. GET /api/sedes/{id} para consultar detalle 5. DELETE /api/sedes/{id} para eliminar

Flujo Alternativo A      A-1: ID no encontrado -> respuesta vacia o error segun capa de servicio

Flujo de Excepcion       E-1: Error de validacion/persistencia en BD

Requerimientos           RF-MSC-CRUD, RNF-Integridad-Datos

Prioridad                MUST


UC-048: Gestionar Series Comprobantes

Campo                    Descripcion

ID                       UC-048

Nombre                   Gestionar Series Comprobantes

Actor Primario           Admin / Operador del sistema

Actores Secundarios      API REST Spring Boot, BD MySQL

Precondicion             Sesion iniciada y permisos del modulo

Postcondicion (exito)    Cambios reflejados en la entidad correspondiente

Postcondicion (fallo)    No se persisten cambios; se retorna error de API

Endpoint real            GET|POST|PUT|DELETE /api/series_comprobantes/**

Flujo Principal          1. GET /api/series_comprobantes para listar 2. POST /api/series_comprobantes para crear 3. PUT /api/series_comprobantes/{id} para actualizar 4. GET /api/series_comprobantes/{id} para consultar detalle 5. DELETE /api/series_comprobantes/{id} para eliminar

Flujo Alternativo A      A-1: ID no encontrado -> respuesta vacia o error segun capa de servicio

Flujo de Excepcion       E-1: Error de validacion/persistencia en BD

Requerimientos           RF-MSC-CRUD, RNF-Integridad-Datos

Prioridad                MUST


UC-049: Gestionar Servicios Belleza

Campo                    Descripcion

ID                       UC-049

Nombre                   Gestionar Servicios Belleza

Actor Primario           Admin / Operador del sistema

Actores Secundarios      API REST Spring Boot, BD MySQL

Precondicion             Sesion iniciada y permisos del modulo

Postcondicion (exito)    Cambios reflejados en la entidad correspondiente

Postcondicion (fallo)    No se persisten cambios; se retorna error de API

Endpoint real            GET|POST|PUT|DELETE /api/servicios_belleza/**

Flujo Principal          1. GET /api/servicios_belleza para listar 2. POST /api/servicios_belleza para crear 3. PUT /api/servicios_belleza/{id} para actualizar 4. GET /api/servicios_belleza/{id} para consultar detalle 5. DELETE /api/servicios_belleza/{id} para eliminar

Flujo Alternativo A      A-1: ID no encontrado -> respuesta vacia o error segun capa de servicio

Flujo de Excepcion       E-1: Error de validacion/persistencia en BD

Requerimientos           RF-MSC-CRUD, RNF-Integridad-Datos

Prioridad                MUST


UC-050: Gestionar Servicio Cita

Campo                    Descripcion

ID                       UC-050

Nombre                   Gestionar Servicio Cita

Actor Primario           Admin / Operador del sistema

Actores Secundarios      API REST Spring Boot, BD MySQL

Precondicion             Sesion iniciada y permisos del modulo

Postcondicion (exito)    Cambios reflejados en la entidad correspondiente

Postcondicion (fallo)    No se persisten cambios; se retorna error de API

Endpoint real            GET|POST|PUT|DELETE /api/servicio_cita/**

Flujo Principal          1. GET /api/servicio_cita para listar 2. POST /api/servicio_cita para crear 3. PUT /api/servicio_cita/{id} para actualizar 4. GET /api/servicio_cita/{id} para consultar detalle 5. DELETE /api/servicio_cita/{id} para eliminar

Flujo Alternativo A      A-1: ID no encontrado -> respuesta vacia o error segun capa de servicio

Flujo de Excepcion       E-1: Error de validacion/persistencia en BD

Requerimientos           RF-MSC-CRUD, RNF-Integridad-Datos

Prioridad                MUST


UC-051: Gestionar Sesiones Caja

Campo                    Descripcion

ID                       UC-051

Nombre                   Gestionar Sesiones Caja

Actor Primario           Admin / Operador del sistema

Actores Secundarios      API REST Spring Boot, BD MySQL

Precondicion             Sesion iniciada y permisos del modulo

Postcondicion (exito)    Cambios reflejados en la entidad correspondiente

Postcondicion (fallo)    No se persisten cambios; se retorna error de API

Endpoint real            GET|POST|PUT|DELETE /api/sesiones_caja/**

Flujo Principal          1. GET /api/sesiones_caja para listar 2. POST /api/sesiones_caja para crear 3. PUT /api/sesiones_caja/{id} para actualizar 4. GET /api/sesiones_caja/{id} para consultar detalle 5. DELETE /api/sesiones_caja/{id} para eliminar

Flujo Alternativo A      A-1: ID no encontrado -> respuesta vacia o error segun capa de servicio

Flujo de Excepcion       E-1: Error de validacion/persistencia en BD

Requerimientos           RF-MSC-CRUD, RNF-Integridad-Datos

Prioridad                MUST


UC-052: Gestionar Suscripciones

Campo                    Descripcion

ID                       UC-052

Nombre                   Gestionar Suscripciones

Actor Primario           Admin / Operador del sistema

Actores Secundarios      API REST Spring Boot, BD MySQL

Precondicion             Sesion iniciada y permisos del modulo

Postcondicion (exito)    Cambios reflejados en la entidad correspondiente

Postcondicion (fallo)    No se persisten cambios; se retorna error de API

Endpoint real            GET|POST|PUT|DELETE /api/suscripciones/**

Flujo Principal          1. GET /api/suscripciones para listar 2. POST /api/suscripciones para crear 3. PUT /api/suscripciones/{id} para actualizar 4. GET /api/suscripciones/{id} para consultar detalle 5. DELETE /api/suscripciones/{id} para eliminar

Flujo Alternativo A      A-1: ID no encontrado -> respuesta vacia o error segun capa de servicio

Flujo de Excepcion       E-1: Error de validacion/persistencia en BD

Requerimientos           RF-MSC-CRUD, RNF-Integridad-Datos

Prioridad                MUST


UC-053: Gestionar Tenants

Campo                    Descripcion

ID                       UC-053

Nombre                   Gestionar Tenants

Actor Primario           Admin / Operador del sistema

Actores Secundarios      API REST Spring Boot, BD MySQL

Precondicion             Sesion iniciada y permisos del modulo

Postcondicion (exito)    Cambios reflejados en la entidad correspondiente

Postcondicion (fallo)    No se persisten cambios; se retorna error de API

Endpoint real            GET|POST|PUT|DELETE /api/tenants/**

Flujo Principal          1. GET /api/tenants para listar 2. POST /api/tenants para crear 3. PUT /api/tenants/{id} para actualizar 4. GET /api/tenants/{id} para consultar detalle 5. DELETE /api/tenants/{id} para eliminar

Flujo Alternativo A      A-1: ID no encontrado -> respuesta vacia o error segun capa de servicio

Flujo de Excepcion       E-1: Error de validacion/persistencia en BD

Requerimientos           RF-MSC-CRUD, RNF-Integridad-Datos

Prioridad                MUST


UC-054: Gestionar Usuarios

Campo                    Descripcion

ID                       UC-054

Nombre                   Gestionar Usuarios

Actor Primario           Admin / Operador del sistema

Actores Secundarios      API REST Spring Boot, BD MySQL

Precondicion             Sesion iniciada y permisos del modulo

Postcondicion (exito)    Cambios reflejados en la entidad correspondiente

Postcondicion (fallo)    No se persisten cambios; se retorna error de API

Endpoint real            GET|POST|PUT|DELETE /api/usuarios/**

Flujo Principal          1. GET /api/usuarios para listar 2. POST /api/usuarios para crear 3. PUT /api/usuarios/{id} para actualizar 4. GET /api/usuarios/{id} para consultar detalle 5. DELETE /api/usuarios/{id} para eliminar

Flujo Alternativo A      A-1: ID no encontrado -> respuesta vacia o error segun capa de servicio

Flujo de Excepcion       E-1: Error de validacion/persistencia en BD

Requerimientos           RF-MSC-CRUD, RNF-Integridad-Datos

Prioridad                MUST


UC-055: Gestionar Usuario Sedes

Campo                    Descripcion

ID                       UC-055

Nombre                   Gestionar Usuario Sedes

Actor Primario           Admin / Operador del sistema

Actores Secundarios      API REST Spring Boot, BD MySQL

Precondicion             Sesion iniciada y permisos del modulo

Postcondicion (exito)    Cambios reflejados en la entidad correspondiente

Postcondicion (fallo)    No se persisten cambios; se retorna error de API

Endpoint real            GET|POST|PUT|DELETE /api/usuario_sedes/**

Flujo Principal          1. GET /api/usuario_sedes para listar 2. POST /api/usuario_sedes para crear 3. PUT /api/usuario_sedes/{id} para actualizar 4. GET /api/usuario_sedes/{id} para consultar detalle 5. DELETE /api/usuario_sedes/{id} para eliminar

Flujo Alternativo A      A-1: ID no encontrado -> respuesta vacia o error segun capa de servicio

Flujo de Excepcion       E-1: Error de validacion/persistencia en BD

Requerimientos           RF-MSC-CRUD, RNF-Integridad-Datos

Prioridad                MUST


UC-056: Gestionar Ventas

Campo                    Descripcion

ID                       UC-056

Nombre                   Gestionar Ventas

Actor Primario           Admin / Operador del sistema

Actores Secundarios      API REST Spring Boot, BD MySQL

Precondicion             Sesion iniciada y permisos del modulo

Postcondicion (exito)    Cambios reflejados en la entidad correspondiente

Postcondicion (fallo)    No se persisten cambios; se retorna error de API

Endpoint real            GET|POST|PUT|DELETE /api/ventas/**

Flujo Principal          1. GET /api/ventas para listar 2. POST /api/ventas para crear 3. PUT /api/ventas/{id} para actualizar 4. GET /api/ventas/{id} para consultar detalle 5. DELETE /api/ventas/{id} para eliminar

Flujo Alternativo A      A-1: ID no encontrado -> respuesta vacia o error segun capa de servicio

Flujo de Excepcion       E-1: Error de validacion/persistencia en BD

Requerimientos           RF-MSC-CRUD, RNF-Integridad-Datos

Prioridad                MUST


UC-057: Gestionar Zonas Delivery

Campo                    Descripcion

ID                       UC-057

Nombre                   Gestionar Zonas Delivery

Actor Primario           Admin / Operador del sistema

Actores Secundarios      API REST Spring Boot, BD MySQL

Precondicion             Sesion iniciada y permisos del modulo

Postcondicion (exito)    Cambios reflejados en la entidad correspondiente

Postcondicion (fallo)    No se persisten cambios; se retorna error de API

Endpoint real            GET|POST /api/zonas_delivery, PUT /api/zonas_delivery, GET|DELETE /api/zonas_delivery/{id}

Flujo Principal          1. GET /api/zonas_delivery para listar 2. POST /api/zonas_delivery para crear 3. PUT /api/zonas_delivery para modificar (sin id en path) 4. GET /api/zonas_delivery/{id} para detalle 5. DELETE /api/zonas_delivery/{id} para eliminar

Flujo Alternativo A      A-1: ID no encontrado en consulta/eliminacion

Flujo de Excepcion       E-1: Error de persistencia o datos incompletos

Requerimientos           RF-MSC-CRUD, RNF-Integridad-Datos

Prioridad                MUST


UC-058: Iniciar Sesion Web

Campo                    Descripcion

ID                       UC-058

Nombre                   Iniciar Sesion Web

Actor Primario           Usuario del sistema

Actores Secundarios      PageController, HttpSession, BD MySQL

Precondicion             Usuario registrado con correo y contrasenia

Postcondicion (exito)    Se guarda atributo usuario en sesion y redirige a /dashboard

Postcondicion (fallo)    Permanece en /login con mensaje Credenciales incorrectas

Endpoint real            GET /login, POST /login

Flujo Principal          1. Usuario abre /login 2. Envia correo y contrasenia 3. Se busca en usuarios 4. Si coincide, se crea sesion 5. Redirige a /dashboard

Flujo Alternativo A      A-1: Sesion ya activa -> redireccion automatica a /dashboard

Flujo de Excepcion       E-1: Credenciales invalidas -> se retorna vista login con error

Requerimientos           RF-MSC-Acceso-Web, RNF-Sesion

Prioridad                MUST


UC-059: Cerrar Sesion Web

Campo                    Descripcion

ID                       UC-059

Nombre                   Cerrar Sesion Web

Actor Primario           Usuario autenticado

Actores Secundarios      PageController, HttpSession

Precondicion             Existe sesion activa

Postcondicion (exito)    Sesion invalidada y redireccion a /login

Postcondicion (fallo)    N/A

Endpoint real            GET /logout

Flujo Principal          1. Usuario accede a /logout 2. Se invalida HttpSession 3. Se redirige a /login

Flujo Alternativo A      A-1: Sesion inexistente -> redireccion a /login

Flujo de Excepcion       E-1: Error de sesion del servidor

Requerimientos           RF-MSC-Acceso-Web, RNF-Sesion

Prioridad                SHOULD


UC-060: Navegar Modulos Web

Campo                    Descripcion

ID                       UC-060

Nombre                   Navegar Modulos Web

Actor Primario           Usuario autenticado

Actores Secundarios      PageController, Thymeleaf templates

Precondicion             Sesion activa (atributo usuario en HttpSession)

Postcondicion (exito)    Vista base renderizada con contentTemplate del modulo

Postcondicion (fallo)    Redireccion a /login por ausencia de sesion

Endpoint real            GET /dashboard, /modulos/productos, /modulos/categorias, /modulos/marcas, /modulos/almacenes, /modulos/lotes, /modulos/movimientos, /modulos/combos, /modulos/promociones

Flujo Principal          1. Usuario autenticado accede al modulo 2. Controller valida sesion 3. Define title y contentTemplate 4. Retorna vista base

Flujo Alternativo A      A-1: Intento de acceso sin sesion -> redirect /login

Flujo de Excepcion       E-1: Plantilla inexistente o error de render

Requerimientos           RF-MSC-UI-Navegacion, RNF-Usabilidad

Prioridad                SHOULD


UC-061: Generar Token API de Registros

Campo                    Descripcion

ID                       UC-061

Nombre                   Generar Token API de Registros

Actor Primario           Cliente API externo

Actores Secundarios      RegistrosController, JwtUtil, BCryptPasswordEncoder

Precondicion             Existe registro con cliente_id y llave_secreta hash en BD

Postcondicion (exito)    JWT generado y persistido en access_token del registro

Postcondicion (fallo)    Respuesta 401 Credenciales incorrectas

Endpoint real            POST /api/token

Flujo Principal          1. Enviar cliente_id y llave_secreta 2. Buscar registro por cliente_id 3. Validar llave con BCrypt 4. Generar JWT 5. Guardar access_token 6. Retornar token

Flujo Alternativo A      A-1: cliente_id no existe

Flujo de Excepcion       E-1: llave_secreta invalida -> 401

Requerimientos           RF-MSC-Token-API, RNF-Seguridad-JWT

Prioridad                MUST

