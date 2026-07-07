import React, { useState, useEffect } from 'react';
import { api } from './api';
import { jsPDF } from 'jspdf';
import 'jspdf-autotable';
import { 
  ShoppingBag, 
  Search, 
  User, 
  LogOut, 
  X, 
  Plus, 
  Minus, 
  Trash2, 
  Scissors, 
  FileText, 
  CheckCircle, 
  Calendar, 
  ArrowLeft,
  Briefcase,
  AlertCircle
} from 'lucide-react';

function App() {
  // Navigation & View states
  const [tab, setTab] = useState('store'); // 'store' | 'services' | 'history'
  const [view, setView] = useState('catalog'); // 'catalog' | 'checkout' | 'success' | 'login' | 'register'
  
  // Data states
  const [products, setProducts] = useState([]);
  const [filteredProducts, setFilteredProducts] = useState([]);
  const [categories, setCategories] = useState([]);
  const [services, setServices] = useState([]);
  const [brands, setBrands] = useState([]);
  
  // Interaction states
  const [activeCategory, setActiveCategory] = useState(null);
  const [searchQuery, setSearchQuery] = useState('');
  const [cart, setCart] = useState([]);
  const [isCartOpen, setIsCartOpen] = useState(false);
  
  // Auth state
  const [client, setClient] = useState(null);
  
  // History states
  const [historyVentas, setHistoryVentas] = useState([]);
  const [historyCitas, setHistoryCitas] = useState([]);
  const [loadingHistory, setLoadingHistory] = useState(false);
  const [historyError, setHistoryError] = useState('');

  // Form states
  const [checkoutForm, setCheckoutForm] = useState({
    nombre: '',
    apellidos: '',
    correo: '',
    telefono: '',
    direccion: '',
    distrito: '',
    tipoDocumento: 'DNI',
    numeroDocumento: '',
    metodoPago: 'tarjeta'
  });
  
  const [loginForm, setLoginForm] = useState({
    correo: '',
    documento: ''
  });
  
  const [registerForm, setRegisterForm] = useState({
    nombre: '',
    apellidos: '',
    correo: '',
    telefono: '',
    direccion: '',
    distrito: '',
    tipoDocumento: 'DNI',
    numeroDocumento: ''
  });

  const [lastVentaId, setLastVentaId] = useState(null);
  const [loading, setLoading] = useState(true);
  const [errorMsg, setErrorMsg] = useState('');

  // Initial Load
  useEffect(() => {
    // Load local storage items
    try {
      const savedCart = localStorage.getItem('bellarista_cart');
      if (savedCart) setCart(JSON.parse(savedCart));
      
      const savedClient = localStorage.getItem('bellarista_client');
      if (savedClient) {
        const parsed = JSON.parse(savedClient);
        setClient(parsed);
        // Pre-fill checkout form with logged-in client details
        setCheckoutForm(prev => ({
          ...prev,
          nombre: parsed.nombre_cliente || '',
          apellidos: parsed.apellidos_clientes || '',
          correo: parsed.correo || '',
          telefono: parsed.telefono || '',
          direccion: parsed.direccion || '',
          distrito: parsed.distrito || '',
          tipoDocumento: parsed.tipo_documento || 'DNI',
          numeroDocumento: parsed.numero_documento || ''
        }));
      }
    } catch (e) {
      console.error('Error al cargar datos de localStorage:', e);
    }
    
    // Fetch initial API data
    const fetchInitialData = async () => {
      try {
        setLoading(true);
        const [prods, cats, servs, brnds] = await Promise.all([
          api.getProductos().catch(e => { console.error(e); return []; }),
          api.getCategorias().catch(e => { console.error(e); return []; }),
          api.getServicios().catch(e => { console.error(e); return []; }),
          api.getMarcas().catch(e => { console.error(e); return []; })
        ]);
        
        setProducts(prods);
        setFilteredProducts(prods);
        setCategories(cats);
        setServices(servs);
        setBrands(brnds);
      } catch (err) {
        console.error('Error al cargar datos del backend:', err);
        setErrorMsg('No se pudo conectar con el servidor cPanel. Inténtelo más tarde.');
      } finally {
        setLoading(false);
      }
    };
    
    fetchInitialData();
  }, []);

  // Filter products when activeCategory or searchQuery changes
  useEffect(() => {
    let result = products;
    
    if (activeCategory !== null) {
      result = result.filter(p => p.id_categorias_productos && p.id_categorias_productos.id_categorias_productos === activeCategory);
    }
    
    if (searchQuery.trim() !== '') {
      const q = searchQuery.toLowerCase().trim();
      result = result.filter(p => 
        p.nombre_producto.toLowerCase().includes(q) || 
        (p.descripcion && p.descripcion.toLowerCase().includes(q))
      );
    }
    
    setFilteredProducts(result);
  }, [activeCategory, searchQuery, products]);

  // Load client history when tab switches to 'history' or client logs in
  useEffect(() => {
    if (tab === 'history' && client) {
      loadClientHistory();
    }
  }, [tab, client]);

  const loadClientHistory = async () => {
    try {
      setLoadingHistory(true);
      setHistoryError('');
      const data = await api.getHistorial();
      if (data && data.success) {
        setHistoryVentas(data.ventas || []);
        setHistoryCitas(data.citas || []);
      } else {
        setHistoryError(data.error || 'Error al obtener el historial');
      }
    } catch (err) {
      console.error(err);
      setHistoryError('Error de red al cargar historial.');
    } finally {
      setLoadingHistory(false);
    }
  };

  // Cart operations
  const saveCart = (newCart) => {
    setCart(newCart);
    localStorage.setItem('bellarista_cart', JSON.stringify(newCart));
  };

  const addToCart = (product) => {
    const existing = cart.find(item => item.id_productos === product.id_productos);
    const stockAct = product.stock_actual != null ? Number(product.stock_actual) : 0;
    const currentQty = existing ? existing.cantidad : 0;

    if (currentQty >= stockAct) {
      alert(`Lo sentimos, no hay más unidades disponibles de ${product.nombre_producto} (Stock actual: ${stockAct}).`);
      return;
    }

    if (existing) {
      saveCart(cart.map(item => 
        item.id_productos === product.id_productos 
          ? { ...item, cantidad: item.cantidad + 1 }
          : item
      ));
    } else {
      saveCart([...cart, {
        id_productos: product.id_productos,
        nombre_producto: product.nombre_producto,
        precio_venta: product.precio_venta,
        img_url: product.img_url,
        cantidad: 1
      }]);
    }
    setIsCartOpen(true);
  };

  const updateCartQuantity = (productId, delta) => {
    const item = cart.find(i => i.id_productos === productId);
    if (!item) return;

    if (delta > 0) {
      const prod = products.find(p => p.id_productos === productId);
      const stockAct = prod && prod.stock_actual != null ? Number(prod.stock_actual) : 999;
      if (item.cantidad >= stockAct) {
        alert(`No se pueden agregar más unidades. El stock máximo disponible es ${stockAct}.`);
        return;
      }
    }

    const newQty = item.cantidad + delta;
    if (newQty <= 0) {
      saveCart(cart.filter(i => i.id_productos !== productId));
    } else {
      saveCart(cart.map(i => 
        i.id_productos === productId 
          ? { ...i, cantidad: newQty }
          : i
      ));
    }
  };

  const removeFromCart = (productId) => {
    saveCart(cart.filter(i => i.id_productos !== productId));
  };

  const getCartTotal = () => {
    return cart.reduce((sum, item) => sum + (item.cantidad * item.precio_venta), 0);
  };

  // Auth Operations
  const handleLogin = async (e) => {
    e.preventDefault();
    try {
      setErrorMsg('');
      await api.login(loginForm.correo, loginForm.documento);
      
      // Since MVC login redirects and saves to session, we simulate client retrieval
      // Fetching historial acts as verification. If successful, we save locally.
      const historyData = await api.getHistorial();
      if (historyData && historyData.success) {
        // We find the client details in the backend history response, or simulate it:
        // Here we just save the basic info
        const clientObj = {
          nombre_cliente: loginForm.correo.split('@')[0], // Fallback
          correo: loginForm.correo,
          numero_documento: loginForm.documento
        };
        
        // Let's try to query products to see if we get customer details inside sale entities
        if (historyData.ventas && historyData.ventas.length > 0) {
          const matchedCli = historyData.ventas[0].id_clientes;
          if (matchedCli) {
            clientObj.nombre_cliente = matchedCli.nombre_cliente;
            clientObj.apellidos_clientes = matchedCli.apellidos_clientes;
            clientObj.telefono = matchedCli.telefono;
            clientObj.direccion = matchedCli.direccion;
            clientObj.distrito = matchedCli.distrito;
            clientObj.tipo_documento = matchedCli.tipo_documento;
            clientObj.id_clientes = matchedCli.id_clientes;
          }
        }
        
        setClient(clientObj);
        localStorage.setItem('bellarista_client', JSON.stringify(clientObj));
        
        // Auto fill checkout form
        setCheckoutForm(prev => ({
          ...prev,
          nombre: clientObj.nombre_cliente || '',
          apellidos: clientObj.apellidos_clientes || '',
          correo: clientObj.correo || '',
          telefono: clientObj.telefono || '',
          direccion: clientObj.direccion || '',
          distrito: clientObj.distrito || '',
          tipoDocumento: clientObj.tipo_documento || 'DNI',
          numeroDocumento: clientObj.numero_documento || ''
        }));

        setView('catalog');
        setTab('store');
      } else {
        setErrorMsg('Credenciales incorrectas o cliente no encontrado en este tenant.');
      }
    } catch (err) {
      console.error(err);
      setErrorMsg('Error de red o credenciales incorrectas. Verifique DNI/RUC.');
    }
  };

  const handleRegister = async (e) => {
    e.preventDefault();
    try {
      setErrorMsg('');
      await api.registro(registerForm);
      
      // Auto login by executing normal login call
      await api.login(registerForm.correo, registerForm.numeroDocumento);
      
      const clientObj = {
        nombre_cliente: registerForm.nombre,
        apellidos_clientes: registerForm.apellidos,
        correo: registerForm.correo,
        telefono: registerForm.telefono,
        direccion: registerForm.direccion,
        distrito: registerForm.distrito,
        tipo_documento: registerForm.tipoDocumento,
        numero_documento: registerForm.numeroDocumento
      };
      
      setClient(clientObj);
      localStorage.setItem('bellarista_client', JSON.stringify(clientObj));
      
      // Pre-fill checkout form
      setCheckoutForm(prev => ({
        ...prev,
        ...registerForm
      }));

      setView('catalog');
      setTab('store');
    } catch (err) {
      console.error(err);
      setErrorMsg('Error al registrarse. Posiblemente el correo ya esté registrado.');
    }
  };

  const handleLogout = async () => {
    try {
      await api.logout();
    } catch (e) {
      console.error(e);
    }
    setClient(null);
    setHistoryVentas([]);
    setHistoryCitas([]);
    localStorage.removeItem('bellarista_client');
    setTab('store');
    setView('catalog');
  };

  // Checkout Operations
  const handleCheckoutSubmit = async (e) => {
    e.preventDefault();
    if (cart.length === 0) {
      alert('Tu carrito está vacío.');
      return;
    }
    
    const payload = {
      nombre: checkoutForm.nombre,
      apellidos: checkoutForm.apellidos,
      correo: checkoutForm.correo,
      telefono: checkoutForm.telefono,
      direccion: checkoutForm.direccion,
      distrito: checkoutForm.distrito,
      tipoDocumento: checkoutForm.tipoDocumento,
      numeroDocumento: checkoutForm.numeroDocumento,
      metodoPago: checkoutForm.metodoPago,
      items: cart.map(i => ({
        id_productos: i.id_productos,
        cantidad: i.cantidad,
        precio_venta: i.precio_venta
      }))
    };

    try {
      setErrorMsg('');
      const response = await api.checkout(payload);
      // Backend returns string "{"success": true, "ventaId": ...}"
      const res = typeof response === 'string' ? JSON.parse(response) : response;
      
      if (res && res.success) {
        setLastVentaId(res.ventaId);
        
        // Refresh client object locally if checkout registered/updated it
        if (!client) {
          const guestCliObj = {
            nombre_cliente: checkoutForm.nombre,
            apellidos_clientes: checkoutForm.apellidos,
            correo: checkoutForm.correo,
            numero_documento: checkoutForm.numeroDocumento
          };
          setClient(guestCliObj);
          localStorage.setItem('bellarista_client', JSON.stringify(guestCliObj));
        }
        
        saveCart([]); // Clear cart
        setView('success');
      } else {
        setErrorMsg(res.error || 'Ocurrió un error al procesar el pedido.');
      }
    } catch (err) {
      console.error(err);
      setErrorMsg('No se pudo procesar la compra. Inténtalo de nuevo.');
    }
  };

  // PDF Boleta generator
  const downloadReceipt = async (ventaId) => {
    if (!ventaId) return;
    try {
      const [ventaRes, detalles] = await Promise.all([
        api.getVenta(ventaId),
        api.getDetallesVenta(ventaId)
      ]);

      if (!ventaRes || !ventaRes.success || !ventaRes.venta) {
        alert('No se encontró la información del pedido.');
        return;
      }
      
      const venta = ventaRes.venta;
      const doc = new jsPDF();

      doc.setFont('helvetica', 'bold');
      doc.setFontSize(20);
      doc.setTextColor(30, 30, 36);
      doc.text('BELLARISTA SALON & BOUTIQUE', 14, 20);

      doc.setFont('helvetica', 'normal');
      doc.setFontSize(9);
      doc.setTextColor(117, 117, 119);
      doc.text('RUC: 20601234561', 14, 26);
      doc.text('Dirección: Av. La Marina 123, San Miguel, Lima', 14, 31);
      doc.text('Teléfono: 01-3456789 | contacto@bellarista.pe', 14, 36);

      doc.setDrawColor(197, 168, 128);
      doc.setFillColor(245, 239, 230);
      doc.rect(130, 12, 65, 26, 'FD');

      doc.setFont('helvetica', 'bold');
      doc.setFontSize(12);
      doc.setTextColor(176, 145, 104);
      doc.text('BOLETA DE VENTA', 135, 18);
      doc.text('ELECTRONICA', 142, 23);

      const ticketNum = venta.numero_ticket || `TK-${venta.id_ventas}`;
      doc.setFontSize(11);
      doc.setTextColor(30, 30, 36);
      doc.text(ticketNum, 142, 31);

      doc.setDrawColor(230, 230, 233);
      doc.line(14, 45, 195, 45);

      doc.setFont('helvetica', 'bold');
      doc.setFontSize(10);
      doc.text('DATOS DEL CLIENTE', 14, 52);

      doc.setFont('helvetica', 'normal');
      doc.setFontSize(9);
      const cli = venta.id_clientes || {};
      const cliNombre = cli.nombre_cliente ? `${cli.nombre_cliente} ${cli.apellidos_clientes || ''}` : 'Cliente General';
      const cliDoc = cli.numero_documento ? `${cli.tipo_documento || 'DOC'}: ${cli.numero_documento}` : 'Publico General';
      const cliDir = cli.direccion ? `${cli.direccion}, ${cli.distrito || ''}` : '-';
      const fecha = venta.fecha_venta ? new Date(venta.fecha_venta).toLocaleString() : new Date().toLocaleString();

      doc.text(`Cliente: ${cliNombre}`, 14, 58);
      doc.text(`Documento: ${cliDoc}`, 14, 63);
      doc.text(`Direccion: ${cliDir}`, 14, 68);
      doc.text(`Fecha Emision: ${fecha}`, 120, 58);
      doc.text('Metodo Pago: Pago Electronico', 120, 63);

      const headers = [['Item', 'Producto / Servicio', 'Cant.', 'Precio Unit.', 'Total']];
      const rows = detalles.map((d, index) => [
        index + 1,
        d.nombre_producto || 'Producto/Servicio',
        d.cantidad || 0,
        `S/ ${parseFloat(d.precio_unitario || 0).toFixed(2)}`,
        `S/ ${parseFloat(d.subtotal || 0).toFixed(2)}`
      ]);

      doc.autoTable({
        head: headers,
        body: rows,
        startY: 75,
        theme: 'striped',
        headStyles: { fillColor: [30, 30, 36], textColor: [255, 255, 255] },
        alternateRowStyles: { fillColor: [250, 250, 252] },
        styles: { font: 'helvetica', fontSize: 9 }
      });

      const finalY = doc.lastAutoTable.finalY + 10;

      doc.setFont('helvetica', 'normal');
      doc.setFontSize(9);
      const subVal = venta.subtotal != null ? parseFloat(venta.subtotal).toFixed(2) : '0.00';
      const igvVal = venta.impuesto != null ? parseFloat(venta.impuesto).toFixed(2) : '0.00';
      const totVal = venta.total != null ? parseFloat(venta.total).toFixed(2) : '0.00';

      doc.text('Subtotal:', 140, finalY);
      doc.text(`S/ ${subVal}`, 185, finalY, { align: 'right' });
      doc.text('IGV (18%):', 140, finalY + 5);
      doc.text(`S/ ${igvVal}`, 185, finalY + 5, { align: 'right' });

      doc.setFont('helvetica', 'bold');
      doc.setFontSize(10);
      doc.text('Total General:', 140, finalY + 12);
      doc.text(`S/ ${totVal}`, 185, finalY + 12, { align: 'right' });

      doc.setFont('helvetica', 'normal');
      doc.setFontSize(8);
      doc.setTextColor(117, 117, 119);
      doc.text('Esta es una representacion impresa de la boleta de venta electronica generada en Bellarista Storefront.', 14, finalY + 30);
      doc.text('Autorizado por SUNAT. Muchas gracias por su preferencia!', 14, finalY + 35);

      const pdfBlob = doc.output('blob');
      const pdfUrl = URL.createObjectURL(pdfBlob);
      window.open(pdfUrl, '_blank');
    } catch (err) {
      console.error(err);
      alert('Error al generar el archivo PDF de la boleta.');
    }
  };

  return (
    <div className="app-layout">
      {/* Header / Navbar */}
      <header>
        <div className="nav-container">
          <button 
            className="logo" 
            style={{ background: 'none', border: 'none', cursor: 'pointer', textAlign: 'left' }}
            onClick={() => { setTab('store'); setView('catalog'); }}
          >
            Bellarista<span>Salon</span>
          </button>
          
          <ul className="nav-menu">
            <li>
              <button 
                className={`nav-link-btn ${tab === 'store' && view === 'catalog' ? 'active' : ''}`}
                onClick={() => { setTab('store'); setView('catalog'); }}
              >
                Productos
              </button>
            </li>
            <li>
              <button 
                className={`nav-link-btn ${tab === 'services' && view === 'catalog' ? 'active' : ''}`}
                onClick={() => { setTab('services'); setView('catalog'); }}
              >
                Servicios
              </button>
            </li>
            {client && (
              <li>
                <button 
                  className={`nav-link-btn ${tab === 'history' && view === 'catalog' ? 'active' : ''}`}
                  onClick={() => { setTab('history'); setView('catalog'); }}
                >
                  Mi Historial
                </button>
              </li>
            )}
            <li>
              <a 
                href="http://belleza.spring.informaticapp.com:2451/admin/login" 
                target="_blank" 
                rel="noreferrer" 
                className="nav-link-btn"
              >
                Portal Admin
              </a>
            </li>
          </ul>
          
          <div className="nav-actions">
            <div className="auth-links">
              {client ? (
                <div style={{ display: 'flex', alignItems: 'center', gap: '14px' }}>
                  <span style={{ fontSize: '13px', fontWeight: '500' }}>
                    Hola, <strong>{client.nombre_cliente}</strong>
                  </span>
                  <button onClick={handleLogout} className="btn-logout-icon" title="Cerrar Sesión">
                    <LogOut size={16} />
                  </button>
                </div>
              ) : (
                <div style={{ display: 'flex', alignItems: 'center', gap: '10px' }}>
                  <button onClick={() => setView('login')} className="btn-outline">
                    Iniciar Sesión
                  </button>
                  <button onClick={() => setView('register')} className="btn-filled">
                    Registrarse
                  </button>
                </div>
              )}
            </div>

            <button className="cart-icon-btn" onClick={() => setIsCartOpen(true)}>
              <ShoppingBag />
              <span className="cart-badge">{cart.reduce((sum, item) => sum + item.cantidad, 0)}</span>
            </button>
          </div>
        </div>
      </header>

      {/* Hero Section */}
      {view === 'catalog' && (
        <section className="hero">
          <div className="hero-content">
            <p className="hero-subtitle">Bellarista Boutique</p>
            {tab === 'store' && (
              <>
                <h1>Encuentra los mejores productos para tu cuidado personal</h1>
                <p>Una cuidada selección de cremas, esmaltes, tratamientos y accesorios premium recomendados por nuestros expertos.</p>
              </>
            )}
            {tab === 'services' && (
              <>
                <h1>Tratamientos y Servicios de Belleza Exclusivos</h1>
                <p>Reserva cortes de cabello, colorimetría, manicura y tratamientos capilares avanzados con nuestros estilistas certificados.</p>
              </>
            )}
            {tab === 'history' && (
              <>
                <h1>Mi Actividad y Estado de Pedidos</h1>
                <p>Sigue el estado de tus compras y administra el calendario de tus próximas visitas al salón de belleza.</p>
              </>
            )}
          </div>
        </section>
      )}

      {/* Main Content Area */}
      {loading ? (
        <div style={{ textAlign: 'center', padding: '100px 24px' }}>
          <div className="loading-spinner"></div>
          <p style={{ marginTop: '16px', color: 'var(--text-muted)' }}>Cargando catálogo de belleza...</p>
        </div>
      ) : (
        <div className="container">
          {errorMsg && (
            <div className="error-alert">
              <AlertCircle size={20} />
              <span>{errorMsg}</span>
            </div>
          )}

          {/* VIEW: CATALOG -> STORE TAB */}
          {view === 'catalog' && tab === 'store' && (
            <main className="store-container">
              <div className="controls">
                <div className="filter-pills">
                  <button 
                    className={`pill ${activeCategory === null ? 'active' : ''}`}
                    onClick={() => setActiveCategory(null)}
                  >
                    Todos
                  </button>
                  {categories.map(cat => (
                    <button 
                      key={cat.id_categorias_productos}
                      className={`pill ${activeCategory === cat.id_categorias_productos ? 'active' : ''}`}
                      onClick={() => setActiveCategory(cat.id_categorias_productos)}
                    >
                      {cat.nombre_categoria_producto}
                    </button>
                  ))}
                </div>
                
                <div className="search-bar">
                  <Search size={16} />
                  <input 
                    type="text" 
                    placeholder="Buscar productos..." 
                    value={searchQuery}
                    onChange={(e) => setSearchQuery(e.target.value)}
                  />
                </div>
              </div>

              {filteredProducts.length === 0 ? (
                <div style={{ gridColumn: '1/-1', textAlign: 'center', padding: '60px 40px', color: 'var(--text-muted)' }}>
                  No se encontraron productos disponibles en esta categoría.
                </div>
              ) : (
                <div className="product-grid">
                  {filteredProducts.map(p => {
                    const stockAct = p.stock_actual != null ? Number(p.stock_actual) : 0;
                    const stockMin = p.stock_minimo != null ? Number(p.stock_minimo) : 10;
                    const brandName = p.id_marcas ? p.id_marcas.nombre_marca : 'Bellarista';
                    const price = p.precio_venta != null ? parseFloat(p.precio_venta).toFixed(2) : '0.00';
                    const initials = p.nombre_producto ? p.nombre_producto.charAt(0).toUpperCase() : 'B';
                    
                    return (
                      <article className="product-card" key={p.id_productos}>
                        <div className="product-img-wrap">
                          {p.img_url ? (
                            <img src={p.img_url} className="product-img" alt={p.nombre_producto} />
                          ) : (
                            <div className="img-fallback">{initials}</div>
                          )}
                          {p.etiqueta_especial && <span className="product-tag">{p.etiqueta_especial}</span>}
                        </div>
                        
                        <div className="product-card-body">
                          <span className="product-brand">{brandName}</span>
                          <h3 className="product-title">{p.nombre_producto}</h3>
                          <p className="product-desc">{p.descripcion || 'Sin descripción'}</p>
                          
                          {stockAct <= 0 ? (
                            <span className="stock-alert out">⚠️ Agotado</span>
                          ) : stockAct <= stockMin ? (
                            <span className="stock-alert low">⚠️ ¡Pocas unidades! (Quedan {stockAct})</span>
                          ) : (
                            <span className="stock-alert normal">Stock disponible: {stockAct}</span>
                          )}

                          <div className="product-card-footer" style={{ marginTop: '12px' }}>
                            <span className="product-price">S/ {price}</span>
                            <button 
                              className="add-to-cart-btn"
                              onClick={() => addToCart(p)}
                              disabled={stockAct <= 0}
                              style={{ opacity: stockAct <= 0 ? 0.4 : 1, cursor: stockAct <= 0 ? 'not-allowed' : 'pointer' }}
                            >
                              <Plus size={16} />
                            </button>
                          </div>
                        </div>
                      </article>
                    );
                  })}
                </div>
              )}
            </main>
          )}

          {/* VIEW: CATALOG -> SERVICES TAB */}
          {view === 'catalog' && tab === 'services' && (
            <main className="store-container">
              <div className="services-intro">
                <h2>Nuestros Servicios de Belleza</h2>
                <p>Descubre nuestra gama de servicios exclusivos. Reserva tu cita online o visítanos en local.</p>
              </div>
              
              {services.length === 0 ? (
                <div style={{ textAlign: 'center', padding: '60px 40px', color: 'var(--text-muted)' }}>
                  No hay servicios de belleza disponibles en este momento.
                </div>
              ) : (
                <div className="product-grid">
                  {services.map(s => {
                    const price = s.precio_base != null ? parseFloat(s.precio_base).toFixed(2) : '0.00';
                    const duration = s.duracion_minima ? `${s.duracion_minima} min` : '45 min';
                    const initials = s.nombre_servicio_belleza ? s.nombre_servicio_belleza.charAt(0).toUpperCase() : 'S';
                    
                    return (
                      <article className="product-card" key={s.id_servicios_belleza}>
                        <div className="product-img-wrap" style={{ background: 'linear-gradient(135deg, #fdfbf7 0%, #f5efe6 100%)', aspectRatio: '16/10' }}>
                          <div style={{ display: 'flex', flexDirection: 'column', alignItems: 'center', justifyContent: 'center', color: 'var(--primary-dark)', fontFamily: "'Playfair Display', serif" }}>
                            <span style={{ fontSize: '36px', fontWeight: '600', opacity: 0.8 }}>{initials}</span>
                            <span style={{ fontSize: '10px', textTransform: 'uppercase', letterSpacing: '2px', marginTop: '6px', fontFamily: "'Plus Jakarta Sans', sans-serif", fontWeight: '600', opacity: 0.6 }}>Estética</span>
                          </div>
                        </div>
                        
                        <div className="product-card-body" style={{ padding: '20px' }}>
                          <span className="product-brand" style={{ color: 'var(--primary-dark)' }}>Bellarista Spa</span>
                          <h3 className="product-title" style={{ minHeight: 'auto', marginBottom: '8px', fontSize: '16px' }}>{s.nombre_servicio_belleza}</h3>
                          <p className="product-desc" style={{ marginBottom: '12px' }}>{s.descripcion || 'Sin descripción'}</p>
                          
                          <div style={{ display: 'flex', alignItems: 'center', gap: '6px', fontSize: '11px', color: 'var(--text-muted)', marginBottom: '16px' }}>
                            <Scissors size={14} />
                            <span>Duración: {duration}</span>
                          </div>
                          
                          <div className="product-card-footer" style={{ marginTop: 'auto', borderTop: '1px solid rgba(0,0,0,0.04)', paddingTop: '12px' }}>
                            <div>
                              <span style={{ fontSize: '9px', textTransform: 'uppercase', color: 'var(--text-muted)', display: 'block', letterSpacing: '0.5px' }}>Precio Base</span>
                              <span className="product-price" style={{ fontSize: '16px' }}>S/ {price}</span>
                            </div>
                            <a 
                              href={`https://wa.me/51987654321?text=Hola,%20quisiera%20reservar%20una%20cita%20para%20el%20servicio%20de%20${encodeURIComponent(s.nombre_servicio_belleza)}`} 
                              target="_blank" 
                              rel="noreferrer" 
                              className="btn-filled"
                              style={{ padding: '6px 12px', fontSize: '11px', borderRadius: '99px', display: 'flex', alignItems: 'center', textDecoration: 'none' }}
                            >
                              Reservar Cita
                            </a>
                          </div>
                        </div>
                      </article>
                    );
                  })}
                </div>
              )}
            </main>
          )}

          {/* VIEW: CATALOG -> HISTORY TAB */}
          {view === 'catalog' && tab === 'history' && (
            <main className="store-container">
              <div className="services-intro">
                <h2>Mi Historial Personal</h2>
                <p>Consulta el estado de tus compras anteriores y las citas de belleza que tienes programadas.</p>
              </div>
              
              {loadingHistory ? (
                <div style={{ textAlign: 'center', padding: '40px' }}>
                  <div className="loading-spinner"></div>
                  <p style={{ marginTop: '12px', fontSize: '13px', color: 'var(--text-muted)' }}>Cargando actividad...</p>
                </div>
              ) : historyError ? (
                <div style={{ textAlign: 'center', color: '#dc2626', padding: '20px' }}>{historyError}</div>
              ) : (
                <div className="history-grid">
                  {/* Compras */}
                  <div className="card-history">
                    <h3>Mis Compras de Productos</h3>
                    {historyVentas.length === 0 ? (
                      <div className="empty-history">No tienes compras registradas.</div>
                    ) : (
                      <div className="history-list">
                        {historyVentas.map(v => {
                          const dateStr = v.fecha_venta ? new Date(v.fecha_venta).toLocaleDateString() : '-';
                          const ticket = v.numero_ticket || v.comprobante_numero || 'T-N/A';
                          const statusLabel = v.estado_sunat ? v.estado_sunat.toUpperCase() : 'PENDIENTE';
                          const totalVal = v.total != null ? parseFloat(v.total).toFixed(2) : '0.00';
                          
                          let statusClass = 'status-badge-pending';
                          if (v.estado_sunat === 'aceptada') statusClass = 'status-badge-success';
                          else if (v.estado_sunat === 'rechazada') statusClass = 'status-badge-danger';
                          
                          return (
                            <div className="history-item" key={v.id_ventas}>
                              <div style={{ display: 'flex', justifyContent: 'space-between', alignItems: 'center', marginBottom: '8px' }}>
                                <div>
                                  <strong style={{ fontSize: '14px', color: 'var(--dark)' }}>{ticket}</strong>
                                  <span style={{ fontSize: '12px', color: 'var(--text-muted)', marginLeft: '8px' }}>{dateStr}</span>
                                </div>
                                <span className={`status-badge ${statusClass}`}>{statusLabel}</span>
                              </div>
                              <div style={{ display: 'flex', justifyContent: 'space-between', alignItems: 'center', fontSize: '13px' }}>
                                <span style={{ color: 'var(--text-muted)' }}>Comprobante: {v.tipo_comprobante || 'boleta'}</span>
                                <div style={{ display: 'flex', alignItems: 'center', gap: '12px' }}>
                                  <button 
                                    onClick={() => downloadReceipt(v.id_ventas)} 
                                    className="btn-pdf-receipt"
                                  >
                                    <FileText size={12} /> PDF
                                  </button>
                                  <strong style={{ fontSize: '15px', color: 'var(--dark)' }}>S/ {totalVal}</strong>
                                </div>
                              </div>
                            </div>
                          );
                        })}
                      </div>
                    )}
                  </div>
                  
                  {/* Citas */}
                  <div className="card-history">
                    <h3>Mis Reservas de Citas</h3>
                    {historyCitas.length === 0 ? (
                      <div className="empty-history">No tienes reservas de citas de belleza.</div>
                    ) : (
                      <div className="history-list">
                        {historyCitas.map(c => {
                          const dateStr = c.fecha_cita ? new Date(c.fecha_cita + 'T00:00:00').toLocaleDateString() : '-';
                          const startStr = c.hora_inicio ? c.hora_inicio.substring(0, 5) : '';
                          const endStr = c.hora_fin ? c.hora_fin.substring(0, 5) : '';
                          const timeStr = `${startStr} - ${endStr}`;
                          const duration = c.duracion_minutos ? `${c.duracion_minutos} min` : '60 min';
                          const sedeName = c.id_sedes ? c.id_sedes.nombre_sede : 'Sede Principal';
                          
                          let statusLabel = 'CONFIRMADA';
                          let statusClass = 'status-badge-success';
                          if (c.estado === 0) {
                            statusLabel = 'CANCELADA';
                            statusClass = 'status-badge-danger';
                          }
                          
                          return (
                            <div className="history-item" key={c.id_citas}>
                              <div style={{ display: 'flex', justifyContent: 'space-between', alignItems: 'center', marginBottom: '8px' }}>
                                <div>
                                  <strong style={{ fontSize: '14px', color: 'var(--dark)' }}>{dateStr}</strong>
                                  <span style={{ fontSize: '12px', color: 'var(--text-muted)', marginLeft: '8px' }}>{timeStr}</span>
                                </div>
                                <span className={`status-badge ${statusClass}`}>{statusLabel}</span>
                              </div>
                              <div style={{ display: 'flex', justifyContent: 'space-between', alignItems: 'center', fontSize: '13px', marginBottom: '4px' }}>
                                <span style={{ color: 'var(--text-muted)' }}>Sede: {sedeName}</span>
                                <span style={{ color: 'var(--text-muted)' }}>{duration}</span>
                              </div>
                              <div style={{ fontSize: '12px', color: 'var(--text-muted)', fontStyle: 'italic' }}>
                                Obs: {c.observaciones || 'Sin observaciones'}
                              </div>
                            </div>
                          );
                        })}
                      </div>
                    )}
                  </div>
                </div>
              )}
            </main>
          )}

          {/* VIEW: LOGIN */}
          {view === 'login' && (
            <div className="auth-container">
              <div className="auth-card">
                <button onClick={() => setView('catalog')} className="auth-close-btn">
                  <X size={20} />
                </button>
                <h2>Iniciar Sesión</h2>
                <p style={{ color: 'var(--text-muted)', fontSize: '13px', marginBottom: '20px' }}>Ingresa tus datos del portal de clientes de Bellarista.</p>
                
                <form onSubmit={handleLogin}>
                  <div className="form-group">
                    <label>Correo Electrónico</label>
                    <input 
                      type="email" 
                      required
                      placeholder="nombre@ejemplo.com"
                      value={loginForm.correo}
                      onChange={(e) => setLoginForm({ ...loginForm, correo: e.target.value })}
                    />
                  </div>
                  
                  <div className="form-group">
                    <label>DNI / RUC (Número de Documento)</label>
                    <input 
                      type="text" 
                      required
                      placeholder="Ingrese su documento de identidad"
                      value={loginForm.documento}
                      onChange={(e) => setLoginForm({ ...loginForm, documento: e.target.value })}
                    />
                  </div>
                  
                  <button type="submit" className="btn-checkout" style={{ marginTop: '10px' }}>
                    Entrar al Portal
                  </button>
                </form>
                
                <div className="auth-footer">
                  ¿No tienes una cuenta?{' '}
                  <button onClick={() => setView('register')} className="auth-link-toggle">
                    Regístrate aquí
                  </button>
                </div>
              </div>
            </div>
          )}

          {/* VIEW: REGISTER */}
          {view === 'register' && (
            <div className="auth-container" style={{ padding: '40px 24px' }}>
              <div className="auth-card" style={{ maxWidth: '500px' }}>
                <button onClick={() => setView('catalog')} className="auth-close-btn">
                  <X size={20} />
                </button>
                <h2>Registrar Cliente</h2>
                <p style={{ color: 'var(--text-muted)', fontSize: '13px', marginBottom: '24px' }}>Crea tu cuenta para guardar historial y agilizar tus compras.</p>
                
                <form onSubmit={handleRegister}>
                  <div style={{ display: 'grid', gridTemplateColumns: '1fr 1fr', gap: '16px' }}>
                    <div className="form-group">
                      <label>Nombres</label>
                      <input 
                        type="text" required
                        value={registerForm.nombre}
                        onChange={(e) => setRegisterForm({ ...registerForm, nombre: e.target.value })}
                      />
                    </div>
                    <div className="form-group">
                      <label>Apellidos</label>
                      <input 
                        type="text" required
                        value={registerForm.apellidos}
                        onChange={(e) => setRegisterForm({ ...registerForm, apellidos: e.target.value })}
                      />
                    </div>
                  </div>
                  
                  <div className="form-group">
                    <label>Correo Electrónico</label>
                    <input 
                      type="email" required
                      value={registerForm.correo}
                      onChange={(e) => setRegisterForm({ ...registerForm, correo: e.target.value })}
                    />
                  </div>
                  
                  <div style={{ display: 'grid', gridTemplateColumns: '1fr 1fr', gap: '16px' }}>
                    <div className="form-group">
                      <label>Teléfono</label>
                      <input 
                        type="text" required
                        value={registerForm.telefono}
                        onChange={(e) => setRegisterForm({ ...registerForm, telefono: e.target.value })}
                      />
                    </div>
                    <div className="form-group">
                      <label>Distrito</label>
                      <input 
                        type="text" required
                        value={registerForm.distrito}
                        onChange={(e) => setRegisterForm({ ...registerForm, distrito: e.target.value })}
                      />
                    </div>
                  </div>
                  
                  <div className="form-group">
                    <label>Dirección</label>
                    <input 
                      type="text" required
                      value={registerForm.direccion}
                      onChange={(e) => setRegisterForm({ ...registerForm, direccion: e.target.value })}
                    />
                  </div>

                  <div style={{ display: 'grid', gridTemplateColumns: '1fr 1fr', gap: '16px' }}>
                    <div className="form-group">
                      <label>Tipo Documento</label>
                      <select 
                        value={registerForm.tipoDocumento}
                        onChange={(e) => setRegisterForm({ ...registerForm, tipoDocumento: e.target.value })}
                      >
                        <option value="DNI">DNI (Persona Física)</option>
                        <option value="RUC">RUC (Empresas)</option>
                        <option value="CE">C.E. (Extranjería)</option>
                        <option value="PASAPORTE">Pasaporte</option>
                      </select>
                    </div>
                    <div className="form-group">
                      <label>Número Documento</label>
                      <input 
                        type="text" required
                        value={registerForm.numeroDocumento}
                        onChange={(e) => setRegisterForm({ ...registerForm, numeroDocumento: e.target.value })}
                      />
                    </div>
                  </div>
                  
                  <button type="submit" className="btn-checkout" style={{ marginTop: '16px' }}>
                    Registrar y Entrar
                  </button>
                </form>
                
                <div className="auth-footer">
                  ¿Ya tienes una cuenta?{' '}
                  <button onClick={() => setView('login')} className="auth-link-toggle">
                    Inicia sesión aquí
                  </button>
                </div>
              </div>
            </div>
          )}

          {/* VIEW: CHECKOUT */}
          {view === 'checkout' && (
            <main className="store-container checkout-page">
              <button onClick={() => setView('catalog')} className="btn-back">
                <ArrowLeft size={16} /> Volver a la Tienda
              </button>
              
              <div className="checkout-grid" style={{ display: 'grid', gridTemplateColumns: '1fr 400px', gap: '40px', marginTop: '20px' }}>
                {/* Formulario */}
                <div className="checkout-form-card" style={{ background: 'white', padding: '32px', borderRadius: '16px', border: '1px solid var(--border)' }}>
                  <h2>Detalles de Facturación</h2>
                  <p style={{ color: 'var(--text-muted)', fontSize: '13px', marginBottom: '24px' }}>Completa los datos para generar tu comprobante SUNAT.</p>
                  
                  <form onSubmit={handleCheckoutSubmit}>
                    <div style={{ display: 'grid', gridTemplateColumns: '1fr 1fr', gap: '16px' }}>
                      <div className="form-group">
                        <label>Nombre</label>
                        <input 
                          type="text" required
                          value={checkoutForm.nombre}
                          onChange={(e) => setCheckoutForm({ ...checkoutForm, nombre: e.target.value })}
                        />
                      </div>
                      <div className="form-group">
                        <label>Apellidos</label>
                        <input 
                          type="text" required
                          value={checkoutForm.apellidos}
                          onChange={(e) => setCheckoutForm({ ...checkoutForm, apellidos: e.target.value })}
                        />
                      </div>
                    </div>
                    
                    <div style={{ display: 'grid', gridTemplateColumns: '1fr 1fr', gap: '16px' }}>
                      <div className="form-group">
                        <label>Correo Electrónico</label>
                        <input 
                          type="email" required
                          value={checkoutForm.correo}
                          onChange={(e) => setCheckoutForm({ ...checkoutForm, correo: e.target.value })}
                        />
                      </div>
                      <div className="form-group">
                        <label>Teléfono</label>
                        <input 
                          type="text" required
                          value={checkoutForm.telefono}
                          onChange={(e) => setCheckoutForm({ ...checkoutForm, telefono: e.target.value })}
                        />
                      </div>
                    </div>
                    
                    <div style={{ display: 'grid', gridTemplateColumns: '1fr 1fr', gap: '16px' }}>
                      <div className="form-group">
                        <label>Dirección de Entrega</label>
                        <input 
                          type="text" required
                          value={checkoutForm.direccion}
                          onChange={(e) => setCheckoutForm({ ...checkoutForm, direccion: e.target.value })}
                        />
                      </div>
                      <div className="form-group">
                        <label>Distrito</label>
                        <input 
                          type="text" required
                          value={checkoutForm.distrito}
                          onChange={(e) => setCheckoutForm({ ...checkoutForm, distrito: e.target.value })}
                        />
                      </div>
                    </div>

                    <div style={{ display: 'grid', gridTemplateColumns: '1fr 1fr', gap: '16px' }}>
                      <div className="form-group">
                        <label>Tipo Comprobante (Documento)</label>
                        <select 
                          value={checkoutForm.tipoDocumento}
                          onChange={(e) => setCheckoutForm({ ...checkoutForm, tipoDocumento: e.target.value })}
                        >
                          <option value="DNI">DNI (Boleta de Venta)</option>
                          <option value="RUC">RUC (Factura)</option>
                          <option value="CE">C.E. (Extranjería)</option>
                          <option value="PASAPORTE">Pasaporte</option>
                        </select>
                      </div>
                      <div className="form-group">
                        <label>Número Documento</label>
                        <input 
                          type="text" required
                          value={checkoutForm.numeroDocumento}
                          onChange={(e) => setCheckoutForm({ ...checkoutForm, numeroDocumento: e.target.value })}
                        />
                      </div>
                    </div>
                    
                    <div className="form-group" style={{ marginTop: '12px' }}>
                      <label>Método de Pago</label>
                      <div className="payment-options" style={{ display: 'grid', gridTemplateColumns: '1fr 1fr 1fr', gap: '12px', marginTop: '8px' }}>
                        <label className={`payment-label ${checkoutForm.metodoPago === 'tarjeta' ? 'active' : ''}`}>
                          <input 
                            type="radio" 
                            name="metodoPago" 
                            value="tarjeta"
                            checked={checkoutForm.metodoPago === 'tarjeta'}
                            onChange={() => setCheckoutForm({ ...checkoutForm, metodoPago: 'tarjeta' })}
                          />
                          <span>💳 Tarjeta</span>
                        </label>
                        <label className={`payment-label ${checkoutForm.metodoPago === 'yape_plin' ? 'active' : ''}`}>
                          <input 
                            type="radio" 
                            name="metodoPago" 
                            value="yape_plin"
                            checked={checkoutForm.metodoPago === 'yape_plin'}
                            onChange={() => setCheckoutForm({ ...checkoutForm, metodoPago: 'yape_plin' })}
                          />
                          <span>📱 Yape / Plin</span>
                        </label>
                        <label className={`payment-label ${checkoutForm.metodoPago === 'transferencia' ? 'active' : ''}`}>
                          <input 
                            type="radio" 
                            name="metodoPago" 
                            value="transferencia"
                            checked={checkoutForm.metodoPago === 'transferencia'}
                            onChange={() => setCheckoutForm({ ...checkoutForm, metodoPago: 'transferencia' })}
                          />
                          <span>🏦 Transf.</span>
                        </label>
                      </div>
                    </div>
                    
                    <button type="submit" className="btn-checkout" style={{ width: '100%', marginTop: '24px' }}>
                      Confirmar y Pagar Compra
                    </button>
                  </form>
                </div>
                
                {/* Resumen del Carrito */}
                <div className="checkout-summary-card" style={{ background: '#fafafc', padding: '30px', borderRadius: '16px', border: '1px solid var(--border)', height: 'fit-content' }}>
                  <h3>Resumen del Pedido</h3>
                  <div className="summary-items" style={{ margin: '20px 0', display: 'flex', flexDirection: 'column', gap: '16px' }}>
                    {cart.map(item => (
                      <div className="summary-item" key={item.id_productos} style={{ display: 'flex', justifyContent: 'space-between', fontSize: '13px' }}>
                        <span>{item.nombre_producto} <strong style={{ color: 'var(--text-muted)' }}>x{item.cantidad}</strong></span>
                        <strong>S/ {(item.precio_venta * item.cantidad).toFixed(2)}</strong>
                      </div>
                    ))}
                  </div>
                  
                  <div style={{ borderTop: '1px solid var(--border)', paddingTop: '16px' }}>
                    <div style={{ display: 'flex', justifyContent: 'space-between', marginBottom: '8px', fontSize: '13px' }}>
                      <span>Subtotal (sin IGV)</span>
                      <span>S/ {(getCartTotal() / 1.18).toFixed(2)}</span>
                    </div>
                    <div style={{ display: 'flex', justifyContent: 'space-between', marginBottom: '16px', fontSize: '13px' }}>
                      <span>IGV (18%)</span>
                      <span>S/ {(getCartTotal() - (getCartTotal() / 1.18)).toFixed(2)}</span>
                    </div>
                    <div style={{ display: 'flex', justifyContent: 'space-between', fontSize: '18px', fontWeight: '700', borderTop: '1px solid var(--border)', paddingTop: '12px' }}>
                      <span>Total</span>
                      <span style={{ color: 'var(--primary-dark)' }}>S/ {getCartTotal().toFixed(2)}</span>
                    </div>
                  </div>
                </div>
              </div>
            </main>
          )}

          {/* VIEW: SUCCESS */}
          {view === 'success' && (
            <main className="store-container" style={{ textAlign: 'center', padding: '80px 24px', maxWidth: '600px', margin: '40px auto' }}>
              <div className="success-icon-wrap" style={{ display: 'inline-flex', background: '#e8f5e9', color: '#2e7d32', padding: '20px', borderRadius: '50%', marginBottom: '24px' }}>
                <CheckCircle size={48} />
              </div>
              <h1 style={{ fontSize: '32px', marginBottom: '12px' }}>¡Compra Procesada con Éxito!</h1>
              <p style={{ color: 'var(--text-muted)', marginBottom: '32px', fontSize: '15px' }}>
                Su comprobante ha sido registrado y aceptado por SUNAT. Ya puede descargar su boleta en PDF.
              </p>
              
              <div style={{ display: 'flex', justifyContent: 'center', gap: '16px' }}>
                <button 
                  onClick={() => downloadReceipt(lastVentaId)}
                  className="btn-filled"
                  style={{ padding: '12px 24px', fontSize: '14px', display: 'inline-flex', alignItems: 'center', gap: '8px' }}
                >
                  <FileText size={16} /> Descargar Boleta PDF
                </button>
                <button 
                  onClick={() => { setTab('store'); setView('catalog'); }}
                  className="btn-outline"
                  style={{ padding: '12px 24px', fontSize: '14px' }}
                >
                  Seguir Comprando
                </button>
              </div>
            </main>
          )}
        </div>
      )}

      {/* Cart Slider Drawer Overlay */}
      <div className={`cart-overlay ${isCartOpen ? 'open' : ''}`} onClick={() => setIsCartOpen(false)}>
        <div className="cart-drawer" onClick={(e) => e.stopPropagation()}>
          <div className="cart-header">
            <h3>Mi Carrito</h3>
            <button className="close-cart-btn" onClick={() => setIsCartOpen(false)}>
              <X size={20} />
            </button>
          </div>
          
          <div className="cart-items-list">
            {cart.length === 0 ? (
              <p className="empty-cart-msg">Tu carrito está vacío</p>
            ) : (
              cart.map(item => {
                const initials = item.nombre_producto ? item.nombre_producto.charAt(0).toUpperCase() : 'P';
                return (
                  <div className="cart-item" key={item.id_productos}>
                    {item.img_url ? (
                      <img src={item.img_url} className="cart-item-img" alt={item.nombre_producto} />
                    ) : (
                      <div className="cart-item-img" style={{ display: 'flex', alignItems: 'center', justifyContent: 'center', background: 'var(--primary-light)', fontSize: '24px', fontWeight: '700', color: 'var(--primary)', fontFamily: "'Playfair Display', serif" }}>
                        {initials}
                      </div>
                    )}
                    
                    <div className="cart-item-info">
                      <h4>{item.nombre_producto}</h4>
                      <p>S/ {parseFloat(item.precio_venta).toFixed(2)} c/u</p>
                      
                      <div className="cart-item-actions">
                        <div className="quantity-control">
                          <button onClick={() => updateCartQuantity(item.id_productos, -1)}>
                            <Minus size={12} />
                          </button>
                          <span>{item.cantidad}</span>
                          <button onClick={() => updateCartQuantity(item.id_productos, 1)}>
                            <Plus size={12} />
                          </button>
                        </div>
                        <button className="remove-item-btn" onClick={() => removeFromCart(item.id_productos)}>
                          Quitar
                        </button>
                      </div>
                    </div>
                  </div>
                );
              })
            )}
          </div>
          
          <div className="cart-footer">
            <div className="cart-summary-line">
              <span>Subtotal</span>
              <span>S/ {getCartTotal().toFixed(2)}</span>
            </div>
            <div className="cart-summary-line">
              <span>Envío</span>
              <span style={{ color: 'var(--success)', fontWeight: '600' }}>Gratis</span>
            </div>
            <div className="cart-summary-line total">
              <span>Total</span>
              <span>S/ {getCartTotal().toFixed(2)}</span>
            </div>
            <button 
              className="btn-checkout" 
              onClick={() => { setIsCartOpen(false); setView('checkout'); }}
              disabled={cart.length === 0}
              style={{ opacity: cart.length === 0 ? 0.5 : 1, cursor: cart.length === 0 ? 'not-allowed' : 'pointer' }}
            >
              Proceder al Pago
            </button>
          </div>
        </div>
      </div>
    </div>
  );
}

export default App;
