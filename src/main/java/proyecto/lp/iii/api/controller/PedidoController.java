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
import proyecto.lp.iii.api.entity.Pedido;
import proyecto.lp.iii.api.entity.DetallePedido;
import proyecto.lp.iii.api.entity.Venta;
import proyecto.lp.iii.api.entity.DetalleVenta;
import proyecto.lp.iii.api.entity.SesionCaja;
import proyecto.lp.iii.api.entity.Sede;
import proyecto.lp.iii.api.entity.Cita;
import proyecto.lp.iii.api.service.IPedidoService;
import proyecto.lp.iii.api.service.IDetallePedidoService;
import proyecto.lp.iii.api.service.IVentaService;
import proyecto.lp.iii.api.service.IDetalleVentaService;
import proyecto.lp.iii.api.service.ISesionCajaService;
import proyecto.lp.iii.api.service.ISedeService;
import proyecto.lp.iii.api.service.ICitaService;
import org.springframework.transaction.annotation.Transactional;

@RestController
@RequestMapping("/api")
public class PedidoController {
    @Autowired
    private IPedidoService servicePedido;

    @Autowired
    private IDetallePedidoService serviceDetallePedido;

    @Autowired
    private IVentaService serviceVenta;

    @Autowired
    private IDetalleVentaService serviceDetalleVenta;

    @Autowired
    private ISesionCajaService serviceSesionCaja;

    @Autowired
    private ISedeService serviceSede;

    @Autowired
    private ICitaService serviceCita;

    @GetMapping("/pedidos")
    public List<Pedido> buscarTodos() {
        return servicePedido.buscarTodos();
    }

    @PostMapping("/pedidos")
    public Pedido guardar(@RequestBody Pedido registro) {
        servicePedido.guardar(registro);
        return registro;
    }

    @PutMapping("/pedidos/{id}")
    @Transactional
    public Pedido modificar(@PathVariable Integer id, @RequestBody Pedido registro) {
        registro.setId_pedidos(id);
        
        Pedido original = servicePedido.buscarId(id).orElse(null);
        
        if (original != null && (original.getEstado() == null || original.getEstado() != 2) && registro.getEstado() != null && registro.getEstado() == 2) {
            boolean ventaExists = serviceVenta.buscarTodos().stream()
                .anyMatch(v -> original.getNumero_pedido() != null && original.getNumero_pedido().equalsIgnoreCase(v.getNumero_ticket()));
            
            if (!ventaExists) {
                Sede sede = null;
                List<Cita> customerCitas = serviceCita.buscarTodos().stream()
                    .filter(c -> c.getId_clientes() != null && c.getId_clientes().getId_clientes().equals(original.getId_clientes().getId_clientes())
                            && c.getId_ventas() == null)
                    .collect(java.util.stream.Collectors.toList());
                
                if (!customerCitas.isEmpty()) {
                    sede = customerCitas.get(0).getId_sedes();
                }
                
                if (sede == null) {
                    sede = serviceSede.buscarTodos().stream()
                        .filter(s -> s.getId_tenants() != null && s.getId_tenants().getId_tenants().equals(original.getId_tenants().getId_tenants()))
                        .findFirst().orElse(null);
                }
                
                if (sede == null) {
                    sede = serviceSede.buscarTodos().stream().findFirst().orElse(null);
                }
                
                SesionCaja sesion = serviceSesionCaja.buscarTodos().stream()
                    .filter(s -> s.getId_sedes() != null && s.getId_sedes().getId_tenants() != null 
                            && s.getId_sedes().getId_tenants().getId_tenants().equals(original.getId_tenants().getId_tenants())
                            && (s.getEstado() == null || s.getEstado() == 1))
                    .findFirst().orElse(null);
                if (sesion == null) {
                    sesion = serviceSesionCaja.buscarTodos().stream().findFirst().orElse(null);
                }
                
                Venta venta = new Venta();
                venta.setId_tenants(original.getId_tenants());
                venta.setId_sedes(sede);
                venta.setId_sesiones_caja(sesion);
                venta.setId_clientes(original.getId_clientes());
                venta.setNumero_ticket(original.getNumero_pedido());
                venta.setComprobante_numero("C-" + System.currentTimeMillis());
                venta.setTipo_comprobante("boleta");
                venta.setEstado(1);
                venta.setEstado_sunat("aceptada");
                venta.setSubtotal(original.getSubtotal());
                venta.setImpuesto(original.getImpuesto());
                venta.setTotal(original.getTotal());
                venta.setId_usuarios(original.getId_usuarios());
                
                Venta savedVenta = serviceVenta.guardar(venta);
                
                List<DetallePedido> detalles = serviceDetallePedido.buscarTodos().stream()
                    .filter(d -> d.getId_pedidos() != null && d.getId_pedidos().getId_pedidos().equals(original.getId_pedidos()))
                    .collect(java.util.stream.Collectors.toList());
                for (DetallePedido dp : detalles) {
                    DetalleVenta dv = new DetalleVenta();
                    dv.setId_ventas(savedVenta);
                    dv.setId_productos(dp.getId_productos());
                    dv.setCantidad(dp.getCantidad());
                    dv.setPrecio_unitario(dp.getPrecio_unitario());
                    dv.setSubtotal(dp.getSubtotal());
                    serviceDetalleVenta.guardar(dv);
                }
                
                if (!customerCitas.isEmpty()) {
                    for (Cita cita : customerCitas) {
                        cita.setId_ventas(savedVenta);
                        serviceCita.modificar(cita);
                    }
                }
            }
        }
        
        servicePedido.modificar(registro);
        return registro;
    }

    @GetMapping("/pedidos/{id}")
    public Optional<Pedido> buscarId(@PathVariable("id") Integer id) {
        return servicePedido.buscarId(id);
    }

    @DeleteMapping("/pedidos/{id}")
    public String elminar(@PathVariable Integer id) {
        servicePedido.eliminar(id);
        return "Registro Eliminado";
    }
}
