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

import java.time.LocalDate;
import org.springframework.transaction.annotation.Transactional;
import proyecto.lp.iii.api.entity.OrdenCompra;
import proyecto.lp.iii.api.entity.CuentaPorPagar;
import proyecto.lp.iii.api.entity.PagoProveedor;
import proyecto.lp.iii.api.service.IOrdenCompraService;
import proyecto.lp.iii.api.service.ICuentaPorPagarService;
import proyecto.lp.iii.api.service.IPagoProveedorService;

@RestController
@RequestMapping("/api")
public class OrdenCompraController {
	@Autowired
	private IOrdenCompraService serviceOrdenCompra;

	@Autowired
	private ICuentaPorPagarService serviceCuentaPorPagar;

	@Autowired
	private IPagoProveedorService servicePagoProveedor;

	private void procesarConversionCuentaPorPagar(OrdenCompra oc) {
		if (oc == null || oc.getEstado() == null || oc.getEstado() != 2) {
			return;
		}

		boolean cppExists = serviceCuentaPorPagar.buscarTodos().stream()
			.anyMatch(cpp -> oc.getNumero_orden() != null && oc.getNumero_orden().equalsIgnoreCase(cpp.getDocumento_numero()));

		if (!cppExists) {
			CuentaPorPagar cpp = new CuentaPorPagar();
			cpp.setId_tenants(oc.getId_tenants());
			cpp.setId_proveedores(oc.getId_proveedores());
			cpp.setDocumento_numero(oc.getNumero_orden());
			cpp.setTipo_documento("factura");
			cpp.setMonto(oc.getMonto_total());
			cpp.setFecha_documento(oc.getFecha_orden() != null ? oc.getFecha_orden() : LocalDate.now());
			cpp.setFecha_vencimiento(oc.getFecha_entrega_estimada() != null ? oc.getFecha_entrega_estimada() : (oc.getFecha_orden() != null ? oc.getFecha_orden().plusDays(30) : LocalDate.now().plusDays(30)));
			
			String formaPago = oc.getForma_pago();
			boolean isPaidImmediately = "contado".equalsIgnoreCase(formaPago) || "transferencia".equalsIgnoreCase(formaPago);
			cpp.setEstado_pago(isPaidImmediately ? "pagado" : "pendiente");

			CuentaPorPagar savedCpp = serviceCuentaPorPagar.guardar(cpp);

			if (isPaidImmediately) {
				PagoProveedor pago = new PagoProveedor();
				pago.setId_cuentas_por_pagar(savedCpp);
				pago.setMonto_pagado(oc.getMonto_total());
				pago.setForma_pago(formaPago != null ? formaPago : "transferencia");
				pago.setNumero_comprobante("AUTO-" + System.currentTimeMillis());
				pago.setFecha_pago(LocalDate.now());
				pago.setObservaciones("Pago automático al recibir Orden de Compra (Al Contado)");
				servicePagoProveedor.guardar(pago);
			}
		}
	}

	@GetMapping("/ordenes_compra")
	public List<OrdenCompra> buscarTodos() {
		return serviceOrdenCompra.buscarTodos();
	}

	@PostMapping("/ordenes_compra")
	public OrdenCompra guardar(@RequestBody OrdenCompra registro) {
		serviceOrdenCompra.guardar(registro);
		procesarConversionCuentaPorPagar(registro);
		return registro;
	}

	@PutMapping("/ordenes_compra/{id}")
	@Transactional
	public OrdenCompra modificar(@PathVariable Integer id, @RequestBody OrdenCompra registro) {
		registro.setId_ordenes_compra(id);
		serviceOrdenCompra.modificar(registro);
		procesarConversionCuentaPorPagar(registro);
		return registro;
	}

@GetMapping("/ordenes_compra/{id}")
public Optional<OrdenCompra> buscarId(@PathVariable("id") Integer id) {
return serviceOrdenCompra.buscarId(id);
}

@DeleteMapping("/ordenes_compra/{id}")
public String elminar(@PathVariable Integer id) {
serviceOrdenCompra.eliminar(id);
return "Registro Eliminado";
}
}
