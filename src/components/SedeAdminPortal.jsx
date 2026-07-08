import React from 'react';
import { 
  Tag, 
  Settings, 
  MapPin, 
  Scissors, 
  Users, 
  Calendar, 
  TrendingUp, 
  LogOut, 
  Plus, 
  User 
} from 'lucide-react';

// Helper function to render complex objects inside table cells safely and clearly
const renderCellValue = (value) => {
  if (value === null || value === undefined) return '-';
  
  if (typeof value === 'object') {
    // If it's a Tenant
    if (value.nombre_comercial) return value.nombre_comercial;
    if (value.razon_social) return value.razon_social;
    
    // If it's a Category
    if (value.nombre_categoria_producto) return value.nombre_categoria_producto;
    if (value.nombre_categoria_servicio) return value.nombre_categoria_servicio;
    
    // If it's a Brand
    if (value.nombre_marca) return value.nombre_marca;
    
    // If it's a Sede
    if (value.nombre_sede) return value.nombre_sede;
    
    // If it's a Cliente
    if (value.nombre_cliente !== undefined) {
      return `${value.nombre_cliente} ${value.apellidos_clientes || ''}`.trim();
    }
    
    // If it's a User
    if (value.nombre_usuario) return value.nombre_usuario;
    
    // Fallback labels for relational references
    if (value.id_tenants) return `Tenant #${value.id_tenants}`;
    if (value.id_sedes) return `Sede #${value.id_sedes}`;
    if (value.id_clientes) return `Cliente #${value.id_clientes}`;
    if (value.id_usuarios) return `Usuario #${value.id_usuarios}`;
    
    try {
      return JSON.stringify(value);
    } catch (e) {
      return '[Objeto Complejo]';
    }
  }
  
  return String(value);
};

export function SedeAdminPortal({
  route,
  errorMsg,
  successMsg,
  loadingAdminCrud,
  // Login State
  adminCorreo,
  setAdminCorreo,
  adminPassword,
  setAdminPassword,
  handleAdminLogin,
  // Dashboard state
  adminActiveModule,
  setAdminActiveModule,
  getModuleConfig,
  adminCrudData,
  handleAdminCreateClick,
  handleAdminEditClick,
  handleAdminCrudDelete,
  handleAdminLogoutClick,
  // CRUD Modals
  crudModal,
  setCrudModal,
  handleAdminCrudSave,
  dynamicFormFields,
  setDynamicFormFields
}) {
  // 3. ROUTE: Tenant Admin Login (`#/admin/login`)
  if (route === '#/admin/login') {
    return (
      <div className="login-page-bg">
        <div className="login-container">
          <div className="login-card-dark">
            <div className="logo-section">
              <div className="logo-icon">
                <User size={32} color="white" />
              </div>
              <h1>Bella<span>rista</span></h1>
              <p>Portal de Administración de Sede</p>
            </div>

            {errorMsg && <div className="alert-error">{errorMsg}</div>}

            <form onSubmit={handleAdminLogin} autoComplete="off">
              <div className="form-group">
                <label>Correo Electrónico</label>
                <input 
                  type="email" 
                  required 
                  placeholder="admin@correo.com"
                  value={adminCorreo}
                  onChange={(e) => setAdminCorreo(e.target.value)}
                />
              </div>
              <div className="form-group">
                <label>Contraseña</label>
                <input 
                  type="password" 
                  required 
                  placeholder="Ingrese su contraseña"
                  value={adminPassword}
                  onChange={(e) => setAdminPassword(e.target.value)}
                />
              </div>
              <button type="submit" className="btn-login">
                Ingresar al Portal
              </button>
            </form>

            <div style={{ marginTop: '24px', display: 'flex', flexDirection: 'column', gap: '8px', fontSize: '12px', textAlign: 'center' }}>
              <a href="#/" style={{ color: 'var(--primary-dark)', textDecoration: 'underline' }}>
                Regresar a Acceso SuperAdmin
              </a>
            </div>
            <p className="footer-text" style={{ color: 'var(--text-muted)', marginTop: '24px', fontSize: '11px', textAlign: 'center' }}>Acceso exclusivo para administradores de sede</p>
          </div>
        </div>
      </div>
    );
  }

  // 4. ROUTE: Tenant Admin Dashboard (`#/dashboard`)
  if (route === '#/dashboard') {
    const config = getModuleConfig(adminActiveModule);
    return (
      <div className="admin-dashboard-layout">
        {/* Admin Sidebar */}
        <aside>
          <div>
            <div style={{ fontSize: '22px', fontWeight: 'bold', fontFamily: "'Playfair Display', serif", marginBottom: '32px', color: 'white' }}>
              Bellarista <span style={{ color: 'var(--primary)' }}>Admin</span>
            </div>
            
            <nav style={{ display: 'flex', flexDirection: 'column', gap: '8px' }}>
              <button className={`aside-nav-btn ${adminActiveModule === 'productos' ? 'active' : ''}`} onClick={() => setAdminActiveModule('productos')}>
                <Tag size={16} /> Productos
              </button>
              <button className={`aside-nav-btn ${adminActiveModule === 'categorias' ? 'active' : ''}`} onClick={() => setAdminActiveModule('categorias')}>
                <Settings size={16} /> Categorías
              </button>
              <button className={`aside-nav-btn ${adminActiveModule === 'marcas' ? 'active' : ''}`} onClick={() => setAdminActiveModule('marcas')}>
                <Settings size={16} /> Marcas
              </button>
              <button className={`aside-nav-btn ${adminActiveModule === 'sedes' ? 'active' : ''}`} onClick={() => setAdminActiveModule('sedes')}>
                <MapPin size={16} /> Sedes
              </button>
              <button className={`aside-nav-btn ${adminActiveModule === 'servicios-belleza' ? 'active' : ''}`} onClick={() => setAdminActiveModule('servicios-belleza')}>
                <Scissors size={16} /> Servicios
              </button>
              <button className={`aside-nav-btn ${adminActiveModule === 'clientes' ? 'active' : ''}`} onClick={() => setAdminActiveModule('clientes')}>
                <Users size={16} /> Clientes
              </button>
              <button className={`aside-nav-btn ${adminActiveModule === 'citas' ? 'active' : ''}`} onClick={() => setAdminActiveModule('citas')}>
                <Calendar size={16} /> Reservas/Citas
              </button>
              <button className={`aside-nav-btn ${adminActiveModule === 'ventas' ? 'active' : ''}`} onClick={() => setAdminActiveModule('ventas')}>
                <TrendingUp size={16} /> Ventas SUNAT
              </button>
            </nav>
          </div>

          <div style={{ display: 'flex', flexDirection: 'column', gap: '12px' }}>
            <a href="#/tienda/1" target="_blank" rel="noreferrer" style={{ textDecoration: 'none', color: 'var(--primary)', fontSize: '13px', textAlign: 'center', fontWeight: 'bold' }}>
              Ir a Tienda Cliente
            </a>
            <button onClick={handleAdminLogoutClick} className="aside-logout-btn">
              <LogOut size={16} /> Cerrar Sesión
            </button>
          </div>
        </aside>

        {/* Admin Content */}
        <main style={{ padding: '40px', overflowY: 'auto' }}>
          <div style={{ display: 'flex', justifyContent: 'space-between', marginBottom: '24px', alignItems: 'center' }}>
            <h2>Panel de {config.title}</h2>
            <button onClick={handleAdminCreateClick} className="btn-filled" style={{ border: 'none', padding: '10px 20px', display: 'flex', alignItems: 'center', gap: '6px', cursor: 'pointer' }}>
              <Plus size={16} /> Nuevo Registro
            </button>
          </div>

          {successMsg && <div style={{ background: '#d1fae5', color: '#065f46', padding: '12px 16px', borderRadius: '8px', marginBottom: '20px', fontSize: '13px', border: '1px solid #a7f3d0' }}>{successMsg}</div>}
          {errorMsg && <div className="error-alert">{errorMsg}</div>}

          {loadingAdminCrud ? (
            <div style={{ textAlign: 'center', padding: '80px' }}>
              <div className="loading-spinner"></div>
              <p style={{ marginTop: '16px', color: 'var(--text-muted)' }}>Cargando registros desde cPanel...</p>
            </div>
          ) : (
            <div className="table-card">
              <table>
                <thead>
                  <tr>
                    <th>Código ID</th>
                    {config.fields.slice(0, 4).map(f => (
                      <th key={f.name}>{f.label}</th>
                    ))}
                    <th style={{ textAlign: 'right' }}>Acciones</th>
                  </tr>
                </thead>
                <tbody>
                  {adminCrudData.length === 0 ? (
                    <tr>
                      <td colSpan={6} style={{ padding: '32px', textAlign: 'center', color: 'var(--text-muted)' }}>No hay registros disponibles.</td>
                    </tr>
                  ) : (
                    adminCrudData.map((item, index) => {
                      const id = item[config.primaryKey];
                      const rowKey = id && typeof id !== 'object' ? id : `${config.primaryKey}_${index}`;
                      return (
                        <tr key={rowKey}>
                          <td style={{ fontWeight: 'bold' }}>
                            {id && typeof id === 'object' ? (
                              id.id_categorias_productos || id.id_marcas || id.id_sedes || id.id_clientes || id.id_ventas || id.id_productos || id.id_servicios_belleza || 'Object'
                            ) : (id ?? '-')}
                          </td>
                          {config.fields.slice(0, 4).map(f => {
                            let value = item[f.name];
                            return <td key={f.name}>{renderCellValue(value)}</td>;
                          })}
                          <td style={{ textAlign: 'right', display: 'flex', gap: '8px', justifyContent: 'flex-end' }}>
                            <button onClick={() => handleAdminEditClick(item)} className="btn-sa-action">
                              Editar
                            </button>
                            <button onClick={() => handleAdminCrudDelete(id)} className="btn-sa-delete">
                              Eliminar
                            </button>
                          </td>
                        </tr>
                      );
                    })
                  )}
                </tbody>
              </table>
            </div>
          )}
        </main>

        {/* Admin CRUD Modal */}
        {crudModal.open && (
          <div className="modal-overlay">
            <div className="modal-content">
              <div className="modal-header">
                <h3>{crudModal.mode === 'create' ? 'Nuevo' : 'Editar'} {config.title}</h3>
                <button onClick={() => setCrudModal({ open: false })} className="modal-close-btn">&times;</button>
              </div>

              <form onSubmit={handleAdminCrudSave}>
                <div style={{ maxHeight: '400px', overflowY: 'auto', paddingRight: '6px', marginBottom: '20px' }}>
                  {config.fields.map(f => (
                    <div className="form-group" key={f.name}>
                      <label>{f.label}</label>
                      <input 
                        type={f.type} 
                        required={f.required}
                        value={dynamicFormFields[f.name] ?? ''}
                        onChange={(e) => setDynamicFormFields({ ...dynamicFormFields, [f.name]: e.target.value })}
                      />
                    </div>
                  ))}
                </div>
                <button type="submit">
                  Guardar Registro
                </button>
              </form>
            </div>
          </div>
        )}
      </div>
    );
  }

  return null;
}
