**UNIVERSIDAD NACIONAL DE SAN MARTÍN**

**FACULTAD DE** **INGENIERÍA DE SISTEMAS E INFORMÁTICA**

**Escuela Profesional** INGENIERÍA DE SISTEMAS E INFORMÁTICA

**Semestre Académico** Elija un elemento.

**INFORME FINAL DE APLICACIÓN DISTRIBUIDA**

<div class="joplin-table-wrapper"><table><tbody><tr><td><p><strong>INTEGRANTES</strong></p></td><td colspan="7"><ul><li></li><li></li><li></li><li></li><li></li><li></li></ul></td></tr><tr><td><p><strong>CURSO</strong></p></td><td colspan="4"><p>LENGUAJE DE PROGRAMACIÓN III</p></td><td colspan="2"><p><strong>CICLO</strong></p></td><td><p>VII</p></td></tr><tr><td><p><strong>SEMANA</strong></p></td><td><p>16</p></td><td><p><strong>FECHA</strong></p></td><td><p>14.07.2023</p></td><td colspan="2"><p><strong>UNIDAD</strong></p></td><td colspan="2"><p>III</p></td></tr><tr><td><p><strong>DOCENTE</strong></p></td><td colspan="7"><p>Ing. Cristian Werner García Estrella</p></td></tr><tr><td><p><strong>COMPETENCIA ESPECÍFICA</strong></p></td><td colspan="7"><p>Construye aplicaciones distribuidas con el uso de servicios web para diferentes modelos de negocios empresariales y las gestiona con metodologías ágiles a través del uso de Kanban</p></td></tr><tr><td><p><strong>PRODUCTO ACADÉMICO</strong></p></td><td colspan="7"><p>Elabora presentación el informe de su aplicación distribuida para el modelo de negocio empresarial</p></td></tr></tbody></table></div>

1.  **ANÁLISIS DE REQUERIMIENTOS**

1.  **ARQUITECTURA BACKEND (CONTROLADORES - MÓDULO DE VENTAS)**

En esta sección se detalla la codificación de los controladores correspondientes al módulo de ventas de la aplicación.

### VentaController.java

```java
package proyecto.lp.iii.api.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import proyecto.lp.iii.api.entity.Venta;
import proyecto.lp.iii.api.service.IVentaService;

@RestController
@RequestMapping("/api")
public class VentaController {
	@Autowired
	private IVentaService serviceVenta;

	@GetMapping("/ventas")
	public List<Venta> buscarTodos() {
		return serviceVenta.buscarTodos();
	}

	@PostMapping("/ventas")
	public Venta guardar(@RequestBody Venta registro) {
		serviceVenta.guardar(registro);
		return registro;
	}

	@PutMapping("/ventas/{id}")
	public Venta modificar(@PathVariable Integer id, @RequestBody Venta registro) {
		registro.setId_ventas(id);
		serviceVenta.modificar(registro);
		return registro;
	}

	@GetMapping("/ventas/{id}")
	public Venta buscarId(@PathVariable("id") Integer id) {
		return serviceVenta.buscarId(id).orElse(null);
	}

	@DeleteMapping("/ventas/{id}")
	public String elminar(@PathVariable Integer id) {
		serviceVenta.eliminar(id);
		return "Registro Eliminado";
	}
}
```

### DetalleVentaController.java

```java
package proyecto.lp.iii.api.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import proyecto.lp.iii.api.entity.DetalleVenta;
import proyecto.lp.iii.api.service.IDetalleVentaService;

@RestController
@RequestMapping("/api")
public class DetalleVentaController {
	@Autowired
	private IDetalleVentaService serviceDetalleVenta;

	@GetMapping("/detalle_venta")
	public List<DetalleVenta> buscarTodos() {
		return serviceDetalleVenta.buscarTodos();
	}

	@PostMapping("/detalle_venta")
	public DetalleVenta guardar(@RequestBody DetalleVenta registro) {
		serviceDetalleVenta.guardar(registro);
		return registro;
	}

	@PutMapping("/detalle_venta/{id}")
	public DetalleVenta modificar(@PathVariable Integer id, @RequestBody DetalleVenta registro) {
		registro.setId_detalle_venta(id);
		serviceDetalleVenta.modificar(registro);
		return registro;
	}

	@GetMapping("/detalle_venta/{id}")
	public Optional<DetalleVenta> buscarId(@PathVariable("id") Integer id) {
		return serviceDetalleVenta.buscarId(id);
	}

	@DeleteMapping("/detalle_venta/{id}")
	public String elminar(@PathVariable Integer id) {
		serviceDetalleVenta.eliminar(id);
		return "Registro Eliminado";
	}
}
```

### FormaPagoVentaController.java

```java
package proyecto.lp.iii.api.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import proyecto.lp.iii.api.entity.FormaPagoVenta;
import proyecto.lp.iii.api.service.IFormaPagoVentaService;

@RestController
@RequestMapping("/api")
public class FormaPagoVentaController {
	@Autowired
	private IFormaPagoVentaService serviceFormaPagoVenta;

	@GetMapping("/formas_pago_venta")
	public List<FormaPagoVenta> buscarTodos() {
		return serviceFormaPagoVenta.buscarTodos();
	}

	@PostMapping("/formas_pago_venta")
	public FormaPagoVenta guardar(@RequestBody FormaPagoVenta registro) {
		serviceFormaPagoVenta.guardar(registro);
		return registro;
	}

	@PutMapping("/formas_pago_venta/{id}")
	public FormaPagoVenta modificar(@PathVariable Integer id, @RequestBody FormaPagoVenta registro) {
		registro.setId_formas_pago_venta(id);
		serviceFormaPagoVenta.modificar(registro);
		return registro;
	}

	@GetMapping("/formas_pago_venta/{id}")
	public Optional<FormaPagoVenta> buscarId(@PathVariable("id") Integer id) {
		return serviceFormaPagoVenta.buscarId(id);
	}

	@DeleteMapping("/formas_pago_venta/{id}")
	public String elminar(@PathVariable Integer id) {
		serviceFormaPagoVenta.eliminar(id);
		return "Registro Eliminado";
	}
}
```

### DevolucionVentaController.java

```java
package proyecto.lp.iii.api.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import proyecto.lp.iii.api.entity.DevolucionVenta;
import proyecto.lp.iii.api.service.IDevolucionVentaService;

@RestController
@RequestMapping("/api")
public class DevolucionVentaController {
	@Autowired
	private IDevolucionVentaService serviceDevolucionVenta;

	@GetMapping("/devoluciones_venta")
	public List<DevolucionVenta> buscarTodos() {
		return serviceDevolucionVenta.buscarTodos();
	}

	@PostMapping("/devoluciones_venta")
	public DevolucionVenta guardar(@RequestBody DevolucionVenta registro) {
		serviceDevolucionVenta.guardar(registro);
		return registro;
	}

	@PutMapping("/devoluciones_venta/{id}")
	public DevolucionVenta modificar(@PathVariable Integer id, @RequestBody DevolucionVenta registro) {
		registro.setId_devoluciones_venta(id);
		serviceDevolucionVenta.modificar(registro);
		return registro;
	}

	@GetMapping("/devoluciones_venta/{id}")
	public Optional<DevolucionVenta> buscarId(@PathVariable("id") Integer id) {
		return serviceDevolucionVenta.buscarId(id);
	}

	@DeleteMapping("/devoluciones_venta/{id}")
	public String elminar(@PathVariable Integer id) {
		serviceDevolucionVenta.eliminar(id);
		return "Registro Eliminado";
	}
}
```

### DetalleDevolucionVentaController.java

```java
package proyecto.lp.iii.api.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import proyecto.lp.iii.api.entity.DetalleDevolucionVenta;
import proyecto.lp.iii.api.service.IDetalleDevolucionVentaService;

@RestController
@RequestMapping("/api")
public class DetalleDevolucionVentaController {
	@Autowired
	private IDetalleDevolucionVentaService serviceDetalleDevolucionVenta;

	@GetMapping("/detalle_devolucion_venta")
	public List<DetalleDevolucionVenta> buscarTodos() {
		return serviceDetalleDevolucionVenta.buscarTodos();
	}

	@PostMapping("/detalle_devolucion_venta")
	public DetalleDevolucionVenta guardar(@RequestBody DetalleDevolucionVenta registro) {
		serviceDetalleDevolucionVenta.guardar(registro);
		return registro;
	}

	@PutMapping("/detalle_devolucion_venta/{id}")
	public DetalleDevolucionVenta modificar(@PathVariable Integer id, @RequestBody DetalleDevolucionVenta registro) {
		registro.setId_detalle_devolucion_venta(id);
		serviceDetalleDevolucionVenta.modificar(registro);
		return registro;
	}

	@GetMapping("/detalle_devolucion_venta/{id}")
	public Optional<DetalleDevolucionVenta> buscarId(@PathVariable("id") Integer id) {
		return serviceDetalleDevolucionVenta.buscarId(id);
	}

	@DeleteMapping("/detalle_devolucion_venta/{id}")
	public String elminar(@PathVariable Integer id) {
		serviceDetalleDevolucionVenta.eliminar(id);
		return "Registro Eliminado";
	}
}
```

1.  **APLICACIÓN CONSUMIDORA DE SERVICIOS (VISTAS)**


1.  **CONCLUSIONES Y RECOMENDACIONES**

**Conclusiones**

**Recomendaciones**