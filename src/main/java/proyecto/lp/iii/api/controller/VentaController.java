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

import java.math.BigDecimal;
import java.util.Map;
import java.util.stream.Collectors;
import org.springframework.transaction.annotation.Transactional;

import proyecto.lp.iii.api.entity.Venta;
import proyecto.lp.iii.api.entity.DetalleVenta;
import proyecto.lp.iii.api.entity.Producto;
import proyecto.lp.iii.api.entity.LoteInventario;
import proyecto.lp.iii.api.entity.MovimientoInventario;
import proyecto.lp.iii.api.service.IVentaService;
import proyecto.lp.iii.api.service.IDetalleVentaService;
import proyecto.lp.iii.api.service.IProductoService;
import proyecto.lp.iii.api.service.ILoteInventarioService;
import proyecto.lp.iii.api.service.IMovimientoInventarioService;
import proyecto.lp.iii.api.service.ISedeService;
import proyecto.lp.iii.api.service.ISesionCajaService;
import proyecto.lp.iii.api.service.IClienteService;
import proyecto.lp.iii.api.service.ITenantsService;
import proyecto.lp.iii.api.service.IUsuariosService;

@RestController
@RequestMapping("/api")
public class VentaController {
	@Autowired
	private IVentaService serviceVenta;

	@Autowired
	private IDetalleVentaService serviceDetalleVenta;

	@Autowired
	private IProductoService serviceProducto;

	@Autowired
	private ILoteInventarioService serviceLoteInventario;

	@Autowired
	private IMovimientoInventarioService serviceMovimientoInventario;

	@Autowired
	private ISedeService serviceSede;

	@Autowired
	private ISesionCajaService serviceSesionCaja;

	@Autowired
	private IClienteService serviceCliente;

	@Autowired
	private ITenantsService serviceTenants;

	@Autowired
	private IUsuariosService serviceUsuarios;

	private Venta mapToVenta(Map<String, Object> map) {
		Venta v = new Venta();
		if (map.get("id_ventas") != null) {
			v.setId_ventas((Integer) map.get("id_ventas"));
		}
		if (map.get("numero_ticket") != null) {
			v.setNumero_ticket((String) map.get("numero_ticket"));
		}
		if (map.get("comprobante_numero") != null) {
			v.setComprobante_numero((String) map.get("comprobante_numero"));
		}
		if (map.get("tipo_comprobante") != null) {
			v.setTipo_comprobante((String) map.get("tipo_comprobante"));
		}
		if (map.get("estado_sunat") != null) {
			v.setEstado_sunat((String) map.get("estado_sunat"));
		}
		if (map.get("subtotal") != null) {
			v.setSubtotal(BigDecimal.valueOf(((Number) map.get("subtotal")).doubleValue()));
		}
		if (map.get("descuento") != null) {
			v.setDescuento(BigDecimal.valueOf(((Number) map.get("descuento")).doubleValue()));
		}
		if (map.get("impuesto") != null) {
			v.setImpuesto(BigDecimal.valueOf(((Number) map.get("impuesto")).doubleValue()));
		}
		if (map.get("total") != null) {
			v.setTotal(BigDecimal.valueOf(((Number) map.get("total")).doubleValue()));
		}
		if (map.get("estado") != null) {
			v.setEstado((Integer) map.get("estado"));
		}

		if (map.get("id_tenants") != null) {
			Integer id = (Integer) map.get("id_tenants");
			serviceTenants.buscarId(id).ifPresent(v::setId_tenants);
		}
		if (map.get("id_sedes") != null) {
			Integer id = (Integer) map.get("id_sedes");
			serviceSede.buscarId(id).ifPresent(v::setId_sedes);
		}
		if (map.get("id_sesiones_caja") != null) {
			Integer id = (Integer) map.get("id_sesiones_caja");
			serviceSesionCaja.buscarId(id).ifPresent(v::setId_sesiones_caja);
		}
		if (map.get("id_clientes") != null) {
			Integer id = (Integer) map.get("id_clientes");
			serviceCliente.buscarId(id).ifPresent(v::setId_clientes);
		}
		if (map.get("id_usuarios") != null) {
			Integer id = (Integer) map.get("id_usuarios");
			serviceUsuarios.buscarId(id).ifPresent(v::setId_usuarios);
		}
		return v;
	}

	@GetMapping("/ventas")
	public List<Venta> buscarTodos() {
		return serviceVenta.buscarTodos();
	}

	@PostMapping("/ventas")
	public Venta guardar(@RequestBody Venta registro) {
		serviceVenta.guardar(registro);
		return registro;
	}

	@PostMapping("/ventas/con-detalles")
	@Transactional
	public Venta guardarConDetalles(@RequestBody Map<String, Object> body) {
		@SuppressWarnings("unchecked")
		Map<String, Object> ventaMap = (Map<String, Object>) body.get("venta");
		Venta venta = mapToVenta(ventaMap);
		Venta savedVenta = serviceVenta.guardar(venta);

		@SuppressWarnings("unchecked")
		List<Map<String, Object>> detallesRaw = (List<Map<String, Object>>) body.get("detalles");
		if (detallesRaw != null) {
			for (Map<String, Object> item : detallesRaw) {
				Integer productoId = (Integer) item.get("id_productos");
				Integer cantidad = item.get("cantidad") != null ? (Integer) item.get("cantidad") : 1;
				Double priceUnit = item.get("precio_unitario") != null ? ((Number) item.get("precio_unitario")).doubleValue() : 0.0;
				Double sub = item.get("subtotal") != null ? ((Number) item.get("subtotal")).doubleValue() : 0.0;

				if (productoId == null) continue;
				Producto prod = serviceProducto.buscarId(productoId).orElse(null);
				if (prod != null) {
					// Deduct stock using FIFO from LoteInventario
					List<LoteInventario> lotes = serviceLoteInventario.buscarPorProductoDisponible(prod);

					int remainingToDeduct = cantidad;
					for (LoteInventario lote : lotes) {
						if (remainingToDeduct <= 0) break;
						int available = lote.getCantidad_disponible();
						int deductedFromThisLote = 0;
						if (available >= remainingToDeduct) {
							lote.setCantidad_disponible(available - remainingToDeduct);
							serviceLoteInventario.modificar(lote);

							MovimientoInventario mov = new MovimientoInventario();
							mov.setId_lotes_inventario(lote);
							mov.setTipo_movimiento("salida");
							mov.setCantidad(remainingToDeduct);
							mov.setMotivo("Venta directa");
							mov.setReferencia_documento(savedVenta.getComprobante_numero() != null ? savedVenta.getComprobante_numero() : savedVenta.getNumero_ticket());
							serviceMovimientoInventario.guardar(mov);

							deductedFromThisLote = remainingToDeduct;
							remainingToDeduct = 0;
						} else {
							lote.setCantidad_disponible(0);
							serviceLoteInventario.modificar(lote);

							MovimientoInventario mov = new MovimientoInventario();
							mov.setId_lotes_inventario(lote);
							mov.setTipo_movimiento("salida");
							mov.setCantidad(available);
							mov.setMotivo("Venta directa");
							mov.setReferencia_documento(savedVenta.getComprobante_numero() != null ? savedVenta.getComprobante_numero() : savedVenta.getNumero_ticket());
							serviceMovimientoInventario.guardar(mov);

							deductedFromThisLote = available;
							remainingToDeduct -= available;
						}

						// Save DetalleVenta linked to this specific lot
						DetalleVenta det = new DetalleVenta();
						det.setId_ventas(savedVenta);
						det.setId_productos(prod);
						det.setCantidad(deductedFromThisLote);
						det.setPrecio_unitario(BigDecimal.valueOf(priceUnit));
						det.setSubtotal(BigDecimal.valueOf(priceUnit * deductedFromThisLote));
						det.setId_lotes_inventario(lote);
						serviceDetalleVenta.guardar(det);
					}

					// Update product stock_actual as the sum of all available lots
					int newStock = serviceLoteInventario.buscarPorProducto(prod).stream()
						.mapToInt(LoteInventario::getCantidad_disponible)
						.sum();
					prod.setStock_actual(newStock);
					serviceProducto.modificar(prod);
				}
			}
		}
		return savedVenta;
	}

	@PutMapping("/ventas/{id}")
	public Venta modificar(@PathVariable Integer id, @RequestBody Venta registro) {
		registro.setId_ventas(id);
		serviceVenta.modificar(registro);
		return registro;
	}

	@PutMapping("/ventas/{id}/con-detalles")
	@Transactional
	public Venta modificarConDetalles(@PathVariable Integer id, @RequestBody Map<String, Object> body) {
		@SuppressWarnings("unchecked")
		Map<String, Object> ventaMap = (Map<String, Object>) body.get("venta");
		Venta venta = mapToVenta(ventaMap);
		venta.setId_ventas(id);
		serviceVenta.modificar(venta);

		// Clean old details and revert stock
		List<DetalleVenta> oldDetalles = serviceDetalleVenta.buscarTodos().stream()
			.filter(d -> d.getId_ventas() != null && d.getId_ventas().getId_ventas().equals(id))
			.collect(Collectors.toList());
		for (DetalleVenta od : oldDetalles) {
			if (od.getId_lotes_inventario() != null) {
				LoteInventario lote = od.getId_lotes_inventario();
				lote.setCantidad_disponible(lote.getCantidad_disponible() + od.getCantidad());
				serviceLoteInventario.modificar(lote);

				// Delete inventory movement related to this lot/sale
				List<MovimientoInventario> movs = serviceMovimientoInventario.buscarTodos().stream()
					.filter(m -> m.getId_lotes_inventario() != null && m.getId_lotes_inventario().getId_lotes_inventario().equals(lote.getId_lotes_inventario())
							&& "salida".equals(m.getTipo_movimiento())
							&& ("Venta directa".equals(m.getMotivo()) || m.getReferencia_documento() != null))
					.collect(Collectors.toList());
				for (MovimientoInventario m : movs) {
					serviceMovimientoInventario.eliminar(m.getId_movimientos_inventario());
				}
			}
			serviceDetalleVenta.eliminar(od.getId_detalle_venta());

			if (od.getId_productos() != null) {
				int newStock = serviceLoteInventario.buscarPorProducto(od.getId_productos()).stream()
					.mapToInt(LoteInventario::getCantidad_disponible)
					.sum();
				od.getId_productos().setStock_actual(newStock);
				serviceProducto.modificar(od.getId_productos());
			}
		}

		@SuppressWarnings("unchecked")
		List<Map<String, Object>> detallesRaw = (List<Map<String, Object>>) body.get("detalles");
		if (detallesRaw != null) {
			for (Map<String, Object> item : detallesRaw) {
				Integer productoId = (Integer) item.get("id_productos");
				Integer cantidad = item.get("cantidad") != null ? (Integer) item.get("cantidad") : 1;
				Double priceUnit = item.get("precio_unitario") != null ? ((Number) item.get("precio_unitario")).doubleValue() : 0.0;
				Double sub = item.get("subtotal") != null ? ((Number) item.get("subtotal")).doubleValue() : 0.0;

				if (productoId == null) continue;
				Producto prod = serviceProducto.buscarId(productoId).orElse(null);
				if (prod != null) {
					// Deduct stock using FIFO from LoteInventario
					List<LoteInventario> lotes = serviceLoteInventario.buscarPorProductoDisponible(prod);

					int remainingToDeduct = cantidad;
					for (LoteInventario lote : lotes) {
						if (remainingToDeduct <= 0) break;
						int available = lote.getCantidad_disponible();
						int deductedFromThisLote = 0;
						if (available >= remainingToDeduct) {
							lote.setCantidad_disponible(available - remainingToDeduct);
							serviceLoteInventario.modificar(lote);

							MovimientoInventario mov = new MovimientoInventario();
							mov.setId_lotes_inventario(lote);
							mov.setTipo_movimiento("salida");
							mov.setCantidad(remainingToDeduct);
							mov.setMotivo("Venta directa");
							mov.setReferencia_documento(venta.getComprobante_numero() != null ? venta.getComprobante_numero() : venta.getNumero_ticket());
							serviceMovimientoInventario.guardar(mov);

							deductedFromThisLote = remainingToDeduct;
							remainingToDeduct = 0;
						} else {
							lote.setCantidad_disponible(0);
							serviceLoteInventario.modificar(lote);

							MovimientoInventario mov = new MovimientoInventario();
							mov.setId_lotes_inventario(lote);
							mov.setTipo_movimiento("salida");
							mov.setCantidad(available);
							mov.setMotivo("Venta directa");
							mov.setReferencia_documento(venta.getComprobante_numero() != null ? venta.getComprobante_numero() : venta.getNumero_ticket());
							serviceMovimientoInventario.guardar(mov);

							deductedFromThisLote = available;
							remainingToDeduct -= available;
						}

						// Save DetalleVenta linked to this specific lot
						DetalleVenta det = new DetalleVenta();
						det.setId_ventas(venta);
						det.setId_productos(prod);
						det.setCantidad(deductedFromThisLote);
						det.setPrecio_unitario(BigDecimal.valueOf(priceUnit));
						det.setSubtotal(BigDecimal.valueOf(priceUnit * deductedFromThisLote));
						det.setId_lotes_inventario(lote);
						serviceDetalleVenta.guardar(det);
					}

					// Update product stock_actual as the sum of all available lots
					int newStock = serviceLoteInventario.buscarPorProducto(prod).stream()
						.mapToInt(LoteInventario::getCantidad_disponible)
						.sum();
					prod.setStock_actual(newStock);
					serviceProducto.modificar(prod);
				}
			}
		}
		return venta;
	}

	@GetMapping("/ventas/{id}")
	public Venta buscarId(@PathVariable("id") Integer id) {
		return serviceVenta.buscarId(id).orElse(null);
	}

	@GetMapping("/ventas/{id}/productos")
	public List<DetalleVenta> getVentaProductos(@PathVariable Integer id) {
		return serviceDetalleVenta.buscarTodos().stream()
			.filter(d -> d.getId_ventas() != null && d.getId_ventas().getId_ventas().equals(id))
			.collect(Collectors.toList());
	}

	@DeleteMapping("/ventas/{id}")
	public String elminar(@PathVariable Integer id) {
		serviceVenta.eliminar(id);
		return "Registro Eliminado";
	}
}
