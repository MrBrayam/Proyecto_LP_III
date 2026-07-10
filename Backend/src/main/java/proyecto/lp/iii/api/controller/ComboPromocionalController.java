package proyecto.lp.iii.api.controller;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import proyecto.lp.iii.api.entity.ComboPromocional;
import proyecto.lp.iii.api.entity.ComposicionCombo;
import proyecto.lp.iii.api.entity.Producto;
import proyecto.lp.iii.api.service.IComboPromocionalService;
import proyecto.lp.iii.api.service.IComposicionComboService;
import proyecto.lp.iii.api.service.IProductoService;

@RestController
@RequestMapping("/api")
public class ComboPromocionalController {
	@Autowired
	private IComboPromocionalService serviceComboPromocional;

	@Autowired
	private IComposicionComboService serviceComposicionCombo;

	@Autowired
	private IProductoService serviceProducto;

	@GetMapping("/combos_promocionales")
	public List<ComboPromocional> buscarTodos() {
		return serviceComboPromocional.buscarTodos();
	}

	@PostMapping("/combos_promocionales")
	public ComboPromocional guardar(@RequestBody ComboPromocional registro) {
		ComboPromocional saved = serviceComboPromocional.guardar(registro);
		return saved;
	}

	@PutMapping("/combos_promocionales/{id}")
	public ComboPromocional modificar(@PathVariable Integer id, @RequestBody ComboPromocional registro) {
		registro.setId_combos_promocionales(id);
        serviceComboPromocional.modificar(registro);
		return registro;
	}

	@GetMapping("/combos_promocionales/{id}")
	public Optional<ComboPromocional> buscarId(@PathVariable("id") Integer id) {
		return serviceComboPromocional.buscarId(id);
	}

	@DeleteMapping("/combos_promocionales/{id}")
	public String elminar(@PathVariable Integer id) {
		serviceComboPromocional.eliminar(id);
		return "Registro Eliminado";
	}

	// ─── Endpoints adicionales para composición de productos ────────────────────

	/**
	 * Lista los productos de la composición de un combo.
	 * GET /api/combos_promocionales/{id}/productos
	 */
	@GetMapping("/combos_promocionales/{id}/productos")
	public List<ComposicionCombo> listarProductosCombo(@PathVariable Integer id) {
		return serviceComposicionCombo.buscarPorCombo(id);
	}

	/**
	 * Crea un combo con sus productos en una sola petición atómica.
	 * POST /api/combos_promocionales/con-productos
	 * Body: { "combo": {...}, "productos": [{"id_productos": 1, "cantidad": 2}, ...] }
	 */
	@PostMapping("/combos_promocionales/con-productos")
	@Transactional
	public ComboPromocional guardarConProductos(@RequestBody Map<String, Object> body) {
		// 1. Guardar el combo base y capturar la entidad persistida (con ID)
		@SuppressWarnings("unchecked")
		Map<String, Object> comboMap = (Map<String, Object>) body.get("combo");
		ComboPromocional combo = mapToCombo(comboMap);
		ComboPromocional savedCombo = serviceComboPromocional.guardar(combo);

		// 2. Insertar cada producto en composicion_combo usando la entidad gestionada
		@SuppressWarnings("unchecked")
		List<Map<String, Object>> productosRaw = (List<Map<String, Object>>) body.get("productos");
		if (productosRaw != null) {
			for (Map<String, Object> item : productosRaw) {
				Integer productoId = (Integer) item.get("id_productos");
				Integer cantidad = item.get("cantidad") != null ? (Integer) item.get("cantidad") : 1;
				if (productoId == null) continue;
				serviceProducto.buscarId(productoId).ifPresent(prod -> {
					ComposicionCombo cc = new ComposicionCombo();
					cc.setId_combos_promocionales(savedCombo);
					cc.setId_productos(prod);
					cc.setCantidad(cantidad);
					serviceComposicionCombo.guardar(cc);
				});
			}
		}
		return savedCombo;
	}

	/**
	 * Actualiza un combo reemplazando su composición de productos.
	 * PUT /api/combos_promocionales/{id}/con-productos
	 */
	@PutMapping("/combos_promocionales/{id}/con-productos")
	@Transactional
	public ComboPromocional modificarConProductos(@PathVariable Integer id,
			@RequestBody Map<String, Object> body) {
		// 1. Actualizar el combo base
		@SuppressWarnings("unchecked")
		Map<String, Object> comboMap = (Map<String, Object>) body.get("combo");
		ComboPromocional combo = mapToCombo(comboMap);
		combo.setId_combos_promocionales(id);
		serviceComboPromocional.modificar(combo);

		// 2. Borrar composición anterior y reinsertarla
		serviceComposicionCombo.eliminarPorCombo(id);

		@SuppressWarnings("unchecked")
		List<Map<String, Object>> productosRaw = (List<Map<String, Object>>) body.get("productos");
		Optional<ComboPromocional> comboRef = serviceComboPromocional.buscarId(id);
		if (productosRaw != null && comboRef.isPresent()) {
			for (Map<String, Object> item : productosRaw) {
				Integer productoId = (Integer) item.get("id_productos");
				Integer cantidad = item.get("cantidad") != null ? (Integer) item.get("cantidad") : 1;
				if (productoId == null) continue;
				serviceProducto.buscarId(productoId).ifPresent(prod -> {
					ComposicionCombo cc = new ComposicionCombo();
					cc.setId_combos_promocionales(comboRef.get());
					cc.setId_productos(prod);
					cc.setCantidad(cantidad);
					serviceComposicionCombo.guardar(cc);
				});
			}
		}
		return combo;
	}

	// ─── Mapping helper ──────────────────────────────────────────────────────────
	private ComboPromocional mapToCombo(Map<String, Object> map) {
		ComboPromocional c = new ComboPromocional();
		if (map.containsKey("nombre_promocion")) c.setNombre_promocion((String) map.get("nombre_promocion"));
		if (map.containsKey("descripcion")) c.setDescripcion((String) map.get("descripcion"));
		if (map.containsKey("precio_combo") && map.get("precio_combo") != null)
			c.setPrecio_combo(new java.math.BigDecimal(map.get("precio_combo").toString()));
		if (map.containsKey("precio_original") && map.get("precio_original") != null)
			c.setPrecio_original(new java.math.BigDecimal(map.get("precio_original").toString()));
		if (map.containsKey("fecha_inicio") && map.get("fecha_inicio") != null)
			c.setFecha_inicio(java.time.LocalDate.parse((String) map.get("fecha_inicio")));
		if (map.containsKey("fecha_fin") && map.get("fecha_fin") != null)
			c.setFecha_fin(java.time.LocalDate.parse((String) map.get("fecha_fin")));
		if (map.containsKey("estado") && map.get("estado") != null)
			c.setEstado((Integer) map.get("estado"));
		// id_tenants se toma del campo hidden del formulario via FK object
		if (map.containsKey("id_tenants") && map.get("id_tenants") != null) {
			proyecto.lp.iii.api.entity.Tenants t = new proyecto.lp.iii.api.entity.Tenants();
			Object tenantRaw = map.get("id_tenants");
			if (tenantRaw instanceof Map) {
				t.setId_tenants((Integer) ((Map<?,?>) tenantRaw).get("id_tenants"));
			} else {
				t.setId_tenants(Integer.parseInt(tenantRaw.toString()));
			}
			c.setId_tenants(t);
		}
		return c;
	}
}
