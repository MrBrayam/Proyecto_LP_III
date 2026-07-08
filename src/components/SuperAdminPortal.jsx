import React from 'react';
import { Briefcase } from 'lucide-react';

export function SuperAdminPortal({
  route,
  // Auth state/handlers
  superadminEmail,
  setSuperadminEmail,
  superadminToken,
  setSuperadminToken,
  handleSuperadminLogin,
  handleSuperadminLogoutClick,
  // Dashboard state/handlers
  saTab,
  setSaTab,
  tenants,
  setTenants,
  saUsers,
  setSaUsers,
  saModal,
  setSaModal,
  tenantForm,
  setTenantForm,
  saUserForm,
  setSaUserForm,
  handleCreateTenant,
  handleEditTenant,
  handleEditsaUser,
  handleSAEditClick,
  // Messaging
  successMsg,
  errorMsg
}) {
  // 1. ROUTE: SuperAdmin Login (Root `/` or `#/`)
  if (route === '#/' || route === '') {
    return (
      <div className="login-page-bg">
        <div className="login-container">
          <div className="login-card-dark">
            <div className="logo-section">
              <div className="logo-icon">
                <Briefcase size={32} color="white" />
              </div>
              <h1>Bella<span>rista</span></h1>
              <p>Panel de SuperAdministración</p>
            </div>

            {errorMsg && <div className="alert-error">{errorMsg}</div>}

            <form onSubmit={handleSuperadminLogin} autoComplete="off">
              <div className="form-group">
                <label>Correo Electrónico</label>
                <input 
                  type="email" 
                  required 
                  placeholder="ejemplo@correo.com"
                  value={superadminEmail}
                  onChange={(e) => setSuperadminEmail(e.target.value)}
                />
              </div>
              <div className="form-group">
                <label>Token de Acceso</label>
                <input 
                  type="password" 
                  required 
                  placeholder="Ingrese su access token"
                  value={superadminToken}
                  onChange={(e) => setSuperadminToken(e.target.value)}
                />
              </div>
              <button type="submit" className="btn-login">
                Ingresar al Panel
              </button>
            </form>

            <div style={{ marginTop: '24px', display: 'flex', flexDirection: 'column', gap: '8px', fontSize: '12px', textAlign: 'center' }}>
              <a href="#/admin/login" style={{ color: 'var(--primary-dark)', textDecoration: 'underline' }}>
                Acceder al Portal de Administración de Sede (Locales)
              </a>
              <a href="#/tienda/1" style={{ color: 'var(--text-muted)', textDecoration: 'underline' }}>
                Ver Tienda Online (Prueba - Sede 1)
              </a>
            </div>
            <p className="footer-text" style={{ color: 'var(--text-muted)', marginTop: '24px', fontSize: '11px', textAlign: 'center' }}>Acceso exclusivo para administradores de plataforma</p>
          </div>
        </div>
      </div>
    );
  }

  // 2. ROUTE: SuperAdmin Dashboard (`#/superadmin/dashboard`)
  if (route === '#/superadmin/dashboard') {
    return (
      <div className="sa-layout">
        {/* SA Navbar */}
        <div className="sa-navbar" style={{ display: 'flex', justifyContent: 'space-between', padding: '16px 32px', alignItems: 'center' }}>
          <div style={{ fontSize: '20px', fontWeight: 'bold' }} className="logo">Bellarista <span>SuperAdmin</span></div>
          <div style={{ display: 'flex', gap: '16px', alignItems: 'center' }}>
            <button className={`sa-nav-tab ${saTab === 'tenants' ? 'active' : ''}`} onClick={() => setSaTab('tenants')}>
              Tenants
            </button>
            <button className={`sa-nav-tab ${saTab === 'users' ? 'active' : ''}`} onClick={() => setSaTab('users')}>
              Usuarios
            </button>
            <button onClick={handleSuperadminLogoutClick} className="btn-logout-elegant">
              Cerrar Sesión
            </button>
          </div>
        </div>

        {/* SA Container */}
        <div className="sa-main" style={{ padding: '32px' }}>
          {successMsg && <div style={{ background: '#d1fae5', color: '#065f46', padding: '12px', borderRadius: '8px', marginBottom: '16px', fontSize: '13px', border: '1px solid #a7f3d0' }}>{successMsg}</div>}
          {errorMsg && <div style={{ background: '#fee2e2', color: '#b85c5c', padding: '12px', borderRadius: '8px', marginBottom: '16px', fontSize: '13px', border: '1px solid #fecaca' }}>{errorMsg}</div>}

          {saTab === 'tenants' ? (
            <div>
              <div style={{ display: 'flex', justifyContent: 'space-between', marginBottom: '24px', alignItems: 'center' }}>
                <h2>Listado de Tenants (Boutiques / Salones)</h2>
                <button 
                  onClick={() => {
                    setTenantForm({ razon_social: '', ruc: '', direccion_fiscal: '', correo: '', telefono: '', nombre_comercial: '', tipo_negocio: 'estetica' });
                    setSaModal({ open: true, mode: 'create', type: 'tenant', data: null });
                  }}
                  className="btn-filled"
                  style={{ border: 'none', padding: '10px 20px', borderRadius: '8px', cursor: 'pointer' }}
                >
                  + Crear Tenant
                </button>
              </div>

              <div style={{ display: 'grid', gridTemplateColumns: 'repeat(auto-fill, minmax(300px, 1fr))', gap: '20px' }}>
                {tenants.map(t => (
                  <div 
                    key={t.id_tenants} 
                    onClick={() => { window.location.hash = `#/tienda/${t.id_tenants}`; }}
                    className="sa-tenant-card-interactive"
                    style={{ padding: '20px' }}
                  >
                    <div style={{ display: 'flex', alignItems: 'center', gap: '12px', marginBottom: '12px' }}>
                      <div style={{ width: '40px', height: '40px', background: 'var(--primary-light)', color: 'var(--primary-dark)', borderRadius: '8px', display: 'flex', alignItems: 'center', justifyContent: 'center', fontWeight: 'bold' }}>{t.id_tenants}</div>
                      <div>
                        <h4 style={{ margin: 0, fontFamily: "'Playfair Display', serif", fontWeight: '700' }}>{t.nombre_comercial}</h4>
                        <span style={{ fontSize: '11px', color: 'var(--text-muted)' }}>{t.razon_social}</span>
                      </div>
                    </div>
                    <div style={{ fontSize: '12px', display: 'flex', flexDirection: 'column', gap: '4px', color: 'var(--text-muted)' }}>
                      <div><strong>RUC:</strong> {t.ruc}</div>
                      <div><strong>Correo:</strong> {t.correo}</div>
                      <div><strong>Teléfono:</strong> {t.telefono}</div>
                      <div><strong>Dirección:</strong> {t.direccion_fiscal}</div>
                    </div>
                    <div style={{ marginTop: '16px', display: 'flex', justifyContent: 'space-between', gap: '10px' }}>
                      <span style={{ color: 'var(--primary-dark)', fontSize: '11px', textDecoration: 'underline', alignSelf: 'center' }}>
                        Ver Tienda →
                      </span>
                      <button 
                        onClick={(e) => { e.stopPropagation(); handleSAEditClick('tenant', t); }} 
                        className="btn-sa-action"
                      >
                        Editar
                      </button>
                    </div>
                  </div>
                ))}
              </div>
            </div>
          ) : (
            <div>
              <h2>Gestión de Usuarios Multi-Tenant</h2>
              <div className="table-card" style={{ marginTop: '16px' }}>
                <table>
                  <thead>
                    <tr>
                      <th style={{ padding: '12px' }}>ID</th>
                      <th style={{ padding: '12px' }}>Nombre</th>
                      <th style={{ padding: '12px' }}>Correo</th>
                      <th style={{ padding: '12px' }}>Tenant (Salon)</th>
                      <th style={{ padding: '12px' }}>Rol</th>
                      <th style={{ padding: '12px' }}>Estado</th>
                      <th style={{ padding: '12px' }}>Acciones</th>
                    </tr>
                  </thead>
                  <tbody>
                    {saUsers.map(u => (
                      <tr key={u.id_usuarios}>
                        <td>{u.id_usuarios}</td>
                        <td style={{ fontWeight: '600' }}>{u.nombre_usuario} {u.apellidos_usuario}</td>
                        <td>{u.correo}</td>
                        <td>{u.id_tenants ? u.id_tenants.nombre_comercial : 'SuperAdmin'}</td>
                        <td>
                          <span className={u.tipo_usuario === 'admin' ? 'badge-role badge-role-admin' : 'badge-role badge-role-employee'}>
                            {u.tipo_usuario}
                          </span>
                        </td>
                        <td>
                          <span className={u.estado === 1 ? 'badge-status-active' : 'badge-status-inactive'}>
                            {u.estado === 1 ? 'Activo' : 'Inactivo'}
                          </span>
                        </td>
                        <td>
                          <button onClick={() => handleSAEditClick('user', u)} className="btn-sa-action">Editar</button>
                        </td>
                      </tr>
                    ))}
                  </tbody>
                </table>
              </div>
            </div>
          )}
        </div>

        {/* SA Modal Dialog */}
        {saModal.open && (
          <div className="modal-overlay">
            <div className="modal-content">
              <div className="modal-header">
                <h3>{saModal.mode === 'create' ? 'Crear' : 'Editar'} {saModal.type === 'tenant' ? 'Tenant' : 'Usuario'}</h3>
                <button onClick={() => setSaModal({ open: false })} className="modal-close-btn">&times;</button>
              </div>

              {saModal.type === 'tenant' ? (
                <form onSubmit={saModal.mode === 'create' ? handleCreateTenant : handleEditTenant}>
                  <div className="form-group">
                    <label>Nombre Comercial</label>
                    <input type="text" required value={tenantForm.nombre_comercial} onChange={(e) => setTenantForm({ ...tenantForm, nombre_comercial: e.target.value })} />
                  </div>
                  <div className="form-group">
                    <label>Razón Social</label>
                    <input type="text" required value={tenantForm.razon_social} onChange={(e) => setTenantForm({ ...tenantForm, razon_social: e.target.value })} />
                  </div>
                  <div className="form-group">
                    <label>RUC</label>
                    <input type="text" required value={tenantForm.ruc} onChange={(e) => setTenantForm({ ...tenantForm, ruc: e.target.value })} />
                  </div>
                  <div className="form-group">
                    <label>Dirección Fiscal</label>
                    <input type="text" required value={tenantForm.direccion_fiscal} onChange={(e) => setTenantForm({ ...tenantForm, direccion_fiscal: e.target.value })} />
                  </div>
                  <div style={{ display: 'grid', gridTemplateColumns: '1fr 1fr', gap: '12px' }}>
                    <div className="form-group">
                      <label>Correo</label>
                      <input type="email" required value={tenantForm.correo} onChange={(e) => setTenantForm({ ...tenantForm, correo: e.target.value })} />
                    </div>
                    <div className="form-group">
                      <label>Teléfono</label>
                      <input type="text" value={tenantForm.telefono} onChange={(e) => setTenantForm({ ...tenantForm, telefono: e.target.value })} />
                    </div>
                  </div>
                  <button type="submit">Guardar</button>
                </form>
              ) : (
                <form onSubmit={handleEditsaUser}>
                  <div style={{ display: 'grid', gridTemplateColumns: '1fr 1fr', gap: '12px' }}>
                    <div className="form-group">
                      <label>Nombres</label>
                      <input type="text" required value={saUserForm.nombre_usuario} onChange={(e) => setSaUserForm({ ...saUserForm, nombre_usuario: e.target.value })} />
                    </div>
                    <div className="form-group">
                      <label>Apellidos</label>
                      <input type="text" required value={saUserForm.apellidos_usuario} onChange={(e) => setSaUserForm({ ...saUserForm, apellidos_usuario: e.target.value })} />
                    </div>
                  </div>
                  <div className="form-group">
                    <label>Correo</label>
                    <input type="email" required value={saUserForm.correo} onChange={(e) => setSaUserForm({ ...saUserForm, correo: e.target.value })} />
                  </div>
                  <div className="form-group">
                    <label>Contraseña (Opcional - dejar vacío para conservar)</label>
                    <input type="password" value={saUserForm.contrasenia} onChange={(e) => setSaUserForm({ ...saUserForm, contrasenia: e.target.value })} />
                  </div>
                  <div style={{ display: 'grid', gridTemplateColumns: '1fr 1fr', gap: '12px' }}>
                    <div className="form-group">
                      <label>Rol</label>
                      <select value={saUserForm.tipo_usuario} onChange={(e) => setSaUserForm({ ...saUserForm, tipo_usuario: e.target.value })}>
                        <option value="admin">Administrador Sede</option>
                        <option value="empleado">Empleado Estilista</option>
                      </select>
                    </div>
                    <div className="form-group">
                      <label>Estado</label>
                      <select value={saUserForm.estado} onChange={(e) => setSaUserForm({ ...saUserForm, estado: Number(e.target.value) })}>
                        <option value={1}>Activo</option>
                        <option value={0}>Inactivo</option>
                      </select>
                    </div>
                  </div>
                  <button type="submit">Guardar Cambios</button>
                </form>
              )}
            </div>
          </div>
        )}
      </div>
    );
  }

  return null;
}
