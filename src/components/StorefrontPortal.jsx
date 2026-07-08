import React from 'react';
import { 
  X, 
  ShoppingBag, 
  Plus, 
  Scissors, 
  LogOut, 
  ArrowLeft, 
  CheckCircle, 
  AlertCircle, 
  Search 
} from 'lucide-react';
import { downloadReceiptGlobal } from '../utils/pdfGenerator';

export function StorefrontPortal({
  route,
  tenantId,
  loading,
  errorMsg,
  // Catalog tabs and controls
  storeTab,
  setStoreTab,
  storeView,
  setStoreView,
  categories,
  activeCategory,
  setActiveCategory,
  searchQuery,
  setSearchQuery,
  filteredProducts,
  services,
  // Cart operations
  cart,
  isCartOpen,
  setIsCartOpen,
  handleAddToCart,
  removeFromCart,
  updateCartQuantity,
  getCartTotal,
  // User Session
  storeClient,
  handleStoreClientLogout,
  // Login / Register forms and actions
  storeLoginForm,
  setStoreLoginForm,
  handleStoreClientLogin,
  storeRegisterForm,
  setStoreRegisterForm,
  handleStoreClientRegister,
  // Checkout & Success
  checkoutForm,
  setCheckoutForm,
  handleStoreCheckout,
  lastVentaId,
  // History tab
  loadingHistory,
  historyVentas,
  historyCitas
}) {

  const handleDownloadReceipt = (ventaId) => {
    downloadReceiptGlobal(ventaId);
  };

  return (
    <div className="app-layout">
      {/* Header / Navbar */}
      <header>
        <div className="nav-container">
          <button 
            className="logo" 
            style={{ background: 'none', border: 'none', cursor: 'pointer', textAlign: 'left' }}
            onClick={() => { setStoreTab('store'); setStoreView('catalog'); }}
          >
            Bellarista<span>Salon</span>
          </button>
          
          <ul className="nav-menu">
            <li>
              <button 
                className={`nav-link-btn ${storeTab === 'store' && storeView === 'catalog' ? 'active' : ''}`}
                onClick={() => { setStoreTab('store'); setStoreView('catalog'); }}
              >
                Productos
              </button>
            </li>
            <li>
              <button 
                className={`nav-link-btn ${storeTab === 'services' && storeView === 'catalog' ? 'active' : ''}`}
                onClick={() => { setStoreTab('services'); setStoreView('catalog'); }}
              >
                Servicios
              </button>
            </li>
            {storeClient && (
              <li>
                <button 
                  className={`nav-link-btn ${storeTab === 'history' && storeView === 'catalog' ? 'active' : ''}`}
                  onClick={() => { setStoreTab('history'); setStoreView('catalog'); }}
                >
                  Mi Historial
                </button>
              </li>
            )}
            <li>
              <a href="#/admin/login" className="nav-link-btn">Portal Admin</a>
            </li>
          </ul>
          
          <div className="nav-actions">
            <div className="auth-links">
              {storeClient ? (
                <div style={{ display: 'flex', alignItems: 'center', gap: '14px' }}>
                  <span style={{ fontSize: '13px', fontWeight: '500' }}>
                    Hola, <strong>{storeClient.nombre_cliente}</strong>
                  </span>
                  <button onClick={handleStoreClientLogout} className="btn-logout-icon" title="Cerrar Sesión">
                    <LogOut size={16} />
                  </button>
                </div>
              ) : (
                <div style={{ display: 'flex', alignItems: 'center', gap: '10px' }}>
                  <button onClick={() => setStoreView('store-login')} className="btn-outline">
                    Iniciar Sesión
                  </button>
                  <button onClick={() => setStoreView('store-register')} className="btn-filled">
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
      {storeView === 'catalog' && (
        <section className="hero">
          <div className="hero-content">
            <p className="hero-subtitle">Tienda Virtual - Sede {tenantId || '1'}</p>
            {storeTab === 'store' && (
              <>
                <h1>Encuentra los mejores productos para tu cuidado personal</h1>
                <p>Una cuidada selección de cremas, esmaltes, tratamientos y accesorios premium recomendados por nuestros expertos.</p>
              </>
            )}
            {storeTab === 'services' && (
              <>
                <h1>Tratamientos y Servicios de Belleza Exclusivos</h1>
                <p>Reserva cortes de cabello, colorimetría, manicura y tratamientos capilares avanzados con nuestros estilistas certificados.</p>
              </>
            )}
            {storeTab === 'history' && (
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
          {storeView === 'catalog' && storeTab === 'store' && (
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
                              onClick={() => handleAddToCart(p)}
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
          {storeView === 'catalog' && storeTab === 'services' && (
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
          {storeView === 'catalog' && storeTab === 'history' && (
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
              ) : (
                <div className="history-grid">
                  {/* Compras */}
                  <div className="sa-card" style={{ background: 'white', padding: '24px', borderRadius: '12px', border: '1px solid var(--border)' }}>
                    <h3 style={{ borderBottom: '2px solid var(--primary-light)', paddingBottom: '8px', marginBottom: '16px', fontFamily: "'Playfair Display', serif" }}>Mis Compras de Productos</h3>
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
                                  <button onClick={() => handleDownloadReceipt(v.id_ventas)} className="btn-pdf-receipt">
                                    📄 PDF
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
                  <div className="sa-card" style={{ background: 'white', padding: '24px', borderRadius: '12px', border: '1px solid var(--border)' }}>
                    <h3 style={{ borderBottom: '2px solid var(--primary-light)', paddingBottom: '8px', marginBottom: '16px', fontFamily: "'Playfair Display', serif" }}>Mis Reservas de Citas</h3>
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
        </div>
      )}

      {/* VIEW: CLIENT STORE LOGIN */}
      {storeView === 'store-login' && (
        <div className="auth-container">
          <div className="auth-card">
            <button onClick={() => setStoreView('catalog')} className="auth-close-btn">
              <X size={20} />
            </button>
            <h2>Iniciar Sesión</h2>
            <p style={{ color: 'var(--text-muted)', fontSize: '13px', marginBottom: '20px' }}>Ingresa tus datos del portal de clientes de Bellarista.</p>
            
            <form onSubmit={handleStoreClientLogin}>
              <div className="form-group">
                <label>Correo Electrónico</label>
                <input 
                  type="email" required placeholder="nombre@ejemplo.com"
                  value={storeLoginForm.correo}
                  onChange={(e) => setStoreLoginForm({ ...storeLoginForm, correo: e.target.value })}
                />
              </div>
              
              <div className="form-group">
                <label>DNI / RUC</label>
                <input 
                  type="text" required placeholder="Ingrese su documento de identidad"
                  value={storeLoginForm.documento}
                  onChange={(e) => setStoreLoginForm({ ...storeLoginForm, documento: e.target.value })}
                />
              </div>
              
              <button type="submit" className="btn-checkout" style={{ marginTop: '10px' }}>
                Entrar al Portal
              </button>
            </form>
            
            <div className="auth-footer">
              ¿No tienes cuenta?{' '}
              <button onClick={() => setStoreView('store-register')} className="auth-link-toggle">
                Regístrate aquí
              </button>
            </div>
          </div>
        </div>
      )}

      {/* VIEW: CLIENT STORE REGISTER */}
      {storeView === 'store-register' && (
        <div className="auth-container">
          <div className="auth-card" style={{ maxWidth: '500px' }}>
            <button onClick={() => setStoreView('catalog')} className="auth-close-btn">
              <X size={20} />
            </button>
            <h2>Registrar Cliente</h2>
            <p style={{ color: 'var(--text-muted)', fontSize: '13px', marginBottom: '24px' }}>Crea tu cuenta de cliente en Bellarista.</p>
            
            <form onSubmit={handleStoreClientRegister}>
              <div style={{ display: 'grid', gridTemplateColumns: '1fr 1fr', gap: '16px' }}>
                <div className="form-group">
                  <label>Nombres</label>
                  <input type="text" required value={storeRegisterForm.nombre} onChange={(e) => setStoreRegisterForm({ ...storeRegisterForm, nombre: e.target.value })} />
                </div>
                <div className="form-group">
                  <label>Apellidos</label>
                  <input type="text" required value={storeRegisterForm.apellidos} onChange={(e) => setStoreRegisterForm({ ...storeRegisterForm, apellidos: e.target.value })} />
                </div>
              </div>
              <div className="form-group">
                <label>Correo Electrónico</label>
                <input type="email" required value={storeRegisterForm.correo} onChange={(e) => setStoreRegisterForm({ ...storeRegisterForm, correo: e.target.value })} />
              </div>
              <div style={{ display: 'grid', gridTemplateColumns: '1fr 1fr', gap: '16px' }}>
                <div className="form-group">
                  <label>Teléfono</label>
                  <input type="text" required value={storeRegisterForm.telefono} onChange={(e) => setStoreRegisterForm({ ...storeRegisterForm, telefono: e.target.value })} />
                </div>
                <div className="form-group">
                  <label>Distrito</label>
                  <input type="text" required value={storeRegisterForm.distrito} onChange={(e) => setStoreRegisterForm({ ...storeRegisterForm, distrito: e.target.value })} />
                </div>
              </div>
              <div className="form-group">
                <label>Dirección</label>
                <input type="text" required value={storeRegisterForm.direccion} onChange={(e) => setStoreRegisterForm({ ...storeRegisterForm, direccion: e.target.value })} />
              </div>
              <div style={{ display: 'grid', gridTemplateColumns: '1fr 1fr', gap: '16px' }}>
                <div className="form-group">
                  <label>Tipo Documento</label>
                  <select value={storeRegisterForm.tipoDocumento} onChange={(e) => setStoreRegisterForm({ ...storeRegisterForm, tipoDocumento: e.target.value })}>
                    <option value="DNI">DNI (Persona Física)</option>
                    <option value="RUC">RUC (Factura Comercial)</option>
                    <option value="CE">C.E. (Extranjería)</option>
                    <option value="PASAPORTE">Pasaporte</option>
                  </select>
                </div>
                <div className="form-group">
                  <label>Número Documento</label>
                  <input type="text" required value={storeRegisterForm.numeroDocumento} onChange={(e) => setStoreRegisterForm({ ...storeRegisterForm, numeroDocumento: e.target.value })} />
                </div>
              </div>
              <button type="submit" className="btn-checkout" style={{ marginTop: '16px' }}>
                Registrar y Entrar
              </button>
            </form>
          </div>
        </div>
      )}

      {/* VIEW: CLIENT CHECKOUT */}
      {storeView === 'checkout' && (
        <main className="store-container">
          <button onClick={() => setStoreView('catalog')} className="btn-back">
            <ArrowLeft size={16} /> Volver a la Tienda
          </button>
          
          <div className="checkout-grid" style={{ display: 'grid', gridTemplateColumns: '1fr 400px', gap: '40px', marginTop: '20px' }}>
            <div className="checkout-form-card" style={{ background: 'white', padding: '32px', borderRadius: '16px', border: '1px solid var(--border)' }}>
              <h2>Detalles de Facturación</h2>
              <p style={{ color: 'var(--text-muted)', fontSize: '13px', marginBottom: '24px' }}>Completa los datos para generar tu comprobante.</p>
              
              <form onSubmit={handleStoreCheckout}>
                <div style={{ display: 'grid', gridTemplateColumns: '1fr 1fr', gap: '16px' }}>
                  <div className="form-group">
                    <label>Nombre</label>
                    <input type="text" required value={checkoutForm.nombre} onChange={(e) => setCheckoutForm({ ...checkoutForm, nombre: e.target.value })} />
                  </div>
                  <div className="form-group">
                    <label>Apellidos</label>
                    <input type="text" required value={checkoutForm.apellidos} onChange={(e) => setCheckoutForm({ ...checkoutForm, apellidos: e.target.value })} />
                  </div>
                </div>
                
                <div style={{ display: 'grid', gridTemplateColumns: '1fr 1fr', gap: '16px' }}>
                  <div className="form-group">
                    <label>Correo Electrónico</label>
                    <input type="email" required value={checkoutForm.correo} onChange={(e) => setCheckoutForm({ ...checkoutForm, correo: e.target.value })} />
                  </div>
                  <div className="form-group">
                    <label>Teléfono</label>
                    <input type="text" required value={checkoutForm.telefono} onChange={(e) => setCheckoutForm({ ...checkoutForm, telefono: e.target.value })} />
                  </div>
                </div>
                
                <div style={{ display: 'grid', gridTemplateColumns: '1fr 1fr', gap: '16px' }}>
                  <div className="form-group">
                    <label>Dirección</label>
                    <input type="text" required value={checkoutForm.direccion} onChange={(e) => setCheckoutForm({ ...checkoutForm, direccion: e.target.value })} />
                  </div>
                  <div className="form-group">
                    <label>Distrito</label>
                    <input type="text" required value={checkoutForm.distrito} onChange={(e) => setCheckoutForm({ ...checkoutForm, distrito: e.target.value })} />
                  </div>
                </div>

                <div style={{ display: 'grid', gridTemplateColumns: '1fr 1fr', gap: '16px' }}>
                  <div className="form-group">
                    <label>Comprobante</label>
                    <select value={checkoutForm.tipoDocumento} onChange={(e) => setCheckoutForm({ ...checkoutForm, tipoDocumento: e.target.value })}>
                      <option value="DNI">DNI (Boleta)</option>
                      <option value="RUC">RUC (Factura)</option>
                    </select>
                  </div>
                  <div className="form-group">
                    <label>Número Documento</label>
                    <input type="text" required value={checkoutForm.numeroDocumento} onChange={(e) => setCheckoutForm({ ...checkoutForm, numeroDocumento: e.target.value })} />
                  </div>
                </div>
                
                <div className="form-group" style={{ marginTop: '12px' }}>
                  <label>Método de Pago</label>
                  <div className="payment-options">
                    <label className={`payment-label ${checkoutForm.metodoPago === 'tarjeta' ? 'active' : ''}`}>
                      <input type="radio" name="metodoPago" value="tarjeta" checked={checkoutForm.metodoPago === 'tarjeta'} onChange={() => setCheckoutForm({ ...checkoutForm, metodoPago: 'tarjeta' })} />
                      <span>💳 Tarjeta</span>
                    </label>
                    <label className={`payment-label ${checkoutForm.metodoPago === 'yape_plin' ? 'active' : ''}`}>
                      <input type="radio" name="metodoPago" value="yape_plin" checked={checkoutForm.metodoPago === 'yape_plin'} onChange={() => setCheckoutForm({ ...checkoutForm, metodoPago: 'yape_plin' })} />
                      <span>📱 Yape / Plin</span>
                    </label>
                    <label className={`payment-label ${checkoutForm.metodoPago === 'transferencia' ? 'active' : ''}`}>
                      <input type="radio" name="metodoPago" value="transferencia" checked={checkoutForm.metodoPago === 'transferencia'} onChange={() => setCheckoutForm({ ...checkoutForm, metodoPago: 'transferencia' })} />
                      <span>🏦 Transf.</span>
                    </label>
                  </div>
                </div>
                
                <button type="submit" className="btn-checkout" style={{ width: '100%', marginTop: '24px' }}>
                  Confirmar Compra (S/ {getCartTotal().toFixed(2)})
                </button>
              </form>
            </div>
            
            {/* Checkout Summary Card */}
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

      {/* VIEW: SUCCESS STORE */}
      {storeView === 'success' && (
        <main className="store-container" style={{ textAlign: 'center', padding: '80px 24px', maxWidth: '600px', margin: '40px auto' }}>
          <div className="success-icon-wrap" style={{ display: 'inline-flex', background: '#e8f5e9', color: '#2e7d32', padding: '20px', borderRadius: '50%', marginBottom: '24px' }}>
            <CheckCircle size={48} />
          </div>
          <h1 style={{ fontSize: '32px', marginBottom: '12px' }}>¡Compra Procesada con Éxito!</h1>
          <p style={{ color: 'var(--text-muted)', marginBottom: '32px', fontSize: '15px' }}>
            Su comprobante ha sido registrado y aceptado por SUNAT. Ya puede descargar su boleta en PDF.
          </p>
          <div style={{ display: 'flex', justifyContent: 'center', gap: '16px' }}>
            <button onClick={() => handleDownloadReceipt(lastVentaId)} className="btn-filled" style={{ padding: '12px 24px', fontSize: '14px', display: 'inline-flex', alignItems: 'center', gap: '8px' }}>
              Descargar Boleta PDF
            </button>
            <button onClick={() => { setStoreTab('store'); setStoreView('catalog'); }} className="btn-outline" style={{ padding: '12px 24px', fontSize: '14px' }}>
              Seguir Comprando
            </button>
          </div>
        </main>
      )}

      {/* Cart Drawer */}
      <div className={`cart-overlay ${isCartOpen ? 'open' : ''}`} onClick={() => setIsCartOpen(false)}>
        <div className="cart-drawer" onClick={(e) => e.stopPropagation()}>
          <div className="cart-header">
            <h3>Mi Carrito</h3>
            <button className="close-cart-btn" onClick={() => setIsCartOpen(false)}>&times;</button>
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
                          <button onClick={() => updateCartQuantity(item.id_productos, -1)}>-</button>
                          <span>{item.cantidad}</span>
                          <button onClick={() => updateCartQuantity(item.id_productos, 1)}>+</button>
                        </div>
                        <button className="remove-item-btn" onClick={() => removeFromCart(item.id_productos)}>Quitar</button>
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
            <button className="btn-checkout" onClick={() => { setIsCartOpen(false); setStoreView('checkout'); }} disabled={cart.length === 0} style={{ opacity: cart.length === 0 ? 0.5 : 1, cursor: cart.length === 0 ? 'not-allowed' : 'pointer' }}>
              Proceder al Pago
            </button>
          </div>
        </div>
      </div>
    </div>
  );
}
