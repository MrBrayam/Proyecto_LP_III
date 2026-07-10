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

import org.springframework.transaction.annotation.Transactional;
import proyecto.lp.iii.api.entity.PagoProveedor;
import proyecto.lp.iii.api.entity.CuentaPorPagar;
import proyecto.lp.iii.api.service.IPagoProveedorService;
import proyecto.lp.iii.api.service.ICuentaPorPagarService;

@RestController
@RequestMapping("/api")
public class PagoProveedorController {
	@Autowired
	private IPagoProveedorService servicePagoProveedor;

	@Autowired
	private ICuentaPorPagarService serviceCuentaPorPagar;

	private void actualizarEstadoCuentaPorPagar(PagoProveedor pago) {
		if (pago == null || pago.getId_cuentas_por_pagar() == null) {
			return;
		}
		
		CuentaPorPagar cpp = serviceCuentaPorPagar.buscarId(pago.getId_cuentas_por_pagar().getId_cuentas_por_pagar()).orElse(null);
		if (cpp != null) {
			List<PagoProveedor> pagos = servicePagoProveedor.buscarTodos();
			double sumPaid = pagos.stream()
				.filter(p -> p.getId_cuentas_por_pagar() != null && p.getId_cuentas_por_pagar().getId_cuentas_por_pagar().equals(cpp.getId_cuentas_por_pagar()))
				.mapToDouble(p -> p.getMonto_pagado() != null ? p.getMonto_pagado().doubleValue() : 0.0)
				.sum();
			
			double totalAmount = cpp.getMonto() != null ? cpp.getMonto().doubleValue() : 0.0;
			
			if (sumPaid >= totalAmount) {
				cpp.setEstado_pago("pagado");
			} else {
				cpp.setEstado_pago("pendiente");
			}
			serviceCuentaPorPagar.modificar(cpp);
		}
	}

	@GetMapping("/pagos_proveedor")
	public List<PagoProveedor> buscarTodos() {
		return servicePagoProveedor.buscarTodos();
	}

	@PostMapping("/pagos_proveedor")
	@Transactional
	public PagoProveedor guardar(@RequestBody PagoProveedor registro) {
		servicePagoProveedor.guardar(registro);
		actualizarEstadoCuentaPorPagar(registro);
		return registro;
	}

	@PutMapping("/pagos_proveedor/{id}")
	@Transactional
	public PagoProveedor modificar(@PathVariable Integer id, @RequestBody PagoProveedor registro) {
		registro.setId_pagos_proveedor(id);
		servicePagoProveedor.modificar(registro);
		actualizarEstadoCuentaPorPagar(registro);
		return registro;
	}

	@GetMapping("/pagos_proveedor/{id}")
	public Optional<PagoProveedor> buscarId(@PathVariable("id") Integer id) {
		return servicePagoProveedor.buscarId(id);
	}

	@DeleteMapping("/pagos_proveedor/{id}")
	@Transactional
	public String elminar(@PathVariable Integer id) {
		Optional<PagoProveedor> opt = servicePagoProveedor.buscarId(id);
		servicePagoProveedor.eliminar(id);
		opt.ifPresent(this::actualizarEstadoCuentaPorPagar);
		return "Registro Eliminado";
	}
}
