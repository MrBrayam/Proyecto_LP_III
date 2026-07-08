import React, { useState, useEffect } from 'react';
import { api, API_BASE_URL, apiFetch } from './api';
import { SuperAdminPortal } from './components/SuperAdminPortal';
import { SedeAdminPortal } from './components/SedeAdminPortal';
import { StorefrontPortal } from './components/StorefrontPortal';
import { downloadReceiptGlobal } from './utils/pdfGenerator';

function App() {
  // Hash-based routing state
  const [route, setRoute] = useState(window.location.hash || '#/');
  
  // Navigation & View states inside portals
  const [storeTab, setStoreTab] = useState('store'); // 'store' | 'services' | 'history'
  const [storeView, setStoreView] = useState('catalog'); // 'catalog' | 'checkout' | 'success' | 'store-login' | 'store-register'
  
  // Storefront Data states
  const [products, setProducts] = useState([]);
  const [filteredProducts, setFilteredProducts] = useState([]);
  const [categories, setCategories] = useState([]);
  const [services, setServices] = useState([]);
  const [brands, setBrands] = useState([]);
  const [activeCategory, setActiveCategory] = useState(null);
  const [searchQuery, setSearchQuery] = useState('');
  const [cart, setCart] = useState([]);
  const [isCartOpen, setIsCartOpen] = useState(false);
  const [storeClient, setStoreClient] = useState(null);
  
  // Customer Store History states
  const [historyVentas, setHistoryVentas] = useState([]);
  const [historyCitas, setHistoryCitas] = useState([]);
  const [loadingHistory, setLoadingHistory] = useState(false);
  
  // Forms & Auth data
  const [checkoutForm, setCheckoutForm] = useState({
    nombre: '', apellidos: '', correo: '', telefono: '', direccion: '', distrito: '',
    tipoDocumento: 'DNI', numeroDocumento: '', metodoPago: 'tarjeta'
  });
  const [storeLoginForm, setStoreLoginForm] = useState({ correo: '', documento: '' });
  const [storeRegisterForm, setStoreRegisterForm] = useState({
    nombre: '', apellidos: '', correo: '', telefono: '', direccion: '', distrito: '',
    tipoDocumento: 'DNI', numeroDocumento: ''
  });
  const [lastVentaId, setLastVentaId] = useState(null);

  // --- PORTAL: SUPERADMIN STATE ---
  const [superadminEmail, setSuperadminEmail] = useState('');
  const [superadminToken, setSuperadminToken] = useState('');
  const [isSuperadminLoggedIn, setIsSuperadminLoggedIn] = useState(false);
  const [tenants, setTenants] = useState([]);
  const [saUsers, setSaUsers] = useState([]);
  const [saTab, setSaTab] = useState('tenants'); // 'tenants' | 'users'
  const [saModal, setSaModal] = useState({ open: false, mode: 'create', type: 'tenant', data: null });
  // Tenant Form
  const [tenantForm, setTenantForm] = useState({
    razon_social: '', ruc: '', direccion_fiscal: '', correo: '', telefono: '', nombre_comercial: '', tipo_negocio: 'estetica'
  });
  // User Form
  const [saUserForm, setSaUserForm] = useState({
    nombre_usuario: '', apellidos_usuario: '', correo: '', tipo_usuario: 'admin', numero_documento: '', contrasenia: '', estado: 1, id_tenants: ''
  });

  // --- PORTAL: TENANT ADMIN STATE ---
  const [adminCorreo, setAdminCorreo] = useState('');
  const [adminPassword, setAdminPassword] = useState('');
  const [isAdminLoggedIn, setIsAdminLoggedIn] = useState(false);
  const [adminTenantId, setAdminTenantId] = useState(() => {
    const saved = localStorage.getItem('bellarista_admin_tenant_id');
    return saved ? Number(saved) : null;
  });
  const [adminActiveModule, setAdminActiveModule] = useState('productos');
  const [adminCrudData, setAdminCrudData] = useState([]);
  const [loadingAdminCrud, setLoadingAdminCrud] = useState(false);
  const [crudModal, setCrudModal] = useState({ open: false, mode: 'create', data: null });
  // Dynamic generic form data state for admin CRUD panel
  const [dynamicFormFields, setDynamicFormFields] = useState({});

  // Loading & Global Errors
  const [loading, setLoading] = useState(false);
  const [errorMsg, setErrorMsg] = useState('');
  const [successMsg, setSuccessMsg] = useState('');

  // Sync hash routing
  useEffect(() => {
    const handleHashChange = () => {
      setRoute(window.location.hash || '#/');
      setErrorMsg('');
      setSuccessMsg('');
    };
    window.addEventListener('hashchange', handleHashChange);
    return () => window.removeEventListener('hashchange', handleHashChange);
  }, []);

  // Parse path parameters (e.g., #/tienda/1)
  const getRouteParams = () => {
    if (route.startsWith('#/tienda/')) {
      const parts = route.split('/');
      return { tenantId: parts[parts.length - 1] };
    }
    return {};
  };

  // Route router effect
  useEffect(() => {
    const params = getRouteParams();
    if (params.tenantId) {
      loadStorefront(params.tenantId);
    } else if (route === '#/superadmin/dashboard') {
      loadSuperadminDashboard();
    } else if (route === '#/dashboard') {
      loadAdminDashboard();
    }
  }, [route]);

  // Load client details and cart from localstorage on start
  useEffect(() => {
    try {
      const savedCart = localStorage.getItem('bellarista_cart');
      if (savedCart) setCart(JSON.parse(savedCart));
      
      const savedClient = localStorage.getItem('bellarista_client');
      if (savedClient) setStoreClient(JSON.parse(savedClient));
    } catch (e) {
      console.error(e);
    }
  }, []);

  const loadStorefront = async (tenantId) => {
    try {
      setLoading(true);
      setErrorMsg('');
      
      // Tenant scope isolation check (prevent sharing login or cart across stores)
      const savedClient = localStorage.getItem('bellarista_client');
      if (savedClient) {
        const parsed = JSON.parse(savedClient);
        if (parsed.storefrontTenantId && Number(parsed.storefrontTenantId) !== Number(tenantId)) {
          localStorage.removeItem('bellarista_client');
          localStorage.removeItem('bellarista_cart');
          setStoreClient(null);
          setCart([]);
        }
      }
      
      // Set the session tenantId on cPanel backend
      await api.setTenantSession(tenantId);
      
      // Load storefront data
      const [prods, cats, servs, brnds] = await Promise.all([
        api.getProductos().catch(e => { console.error(e); return []; }),
        api.getCategorias().catch(e => { console.error(e); return []; }),
        api.getServicios().catch(e => { console.error(e); return []; }),
        api.getMarcas().catch(e => { console.error(e); return []; })
      ]);
      
      // Helper function to safely isolate tenant data
      const matchesTenant = (item) => {
        if (!item) return false;
        if (!item.id_tenants) return false;
        const itemTid = typeof item.id_tenants === 'object' ? item.id_tenants.id_tenants : item.id_tenants;
        return Number(itemTid) === Number(tenantId);
      };

      const filteredProds = prods.filter(matchesTenant);
      const filteredCats = cats.filter(matchesTenant);
      const filteredServs = servs.filter(matchesTenant);
      const filteredBrands = brnds.filter(matchesTenant);

      setProducts(filteredProds);
      setFilteredProducts(filteredProds);
      setCategories(filteredCats);
      setServices(filteredServs);
      setBrands(filteredBrands);
    } catch (err) {
      console.error(err);
      setErrorMsg('No se pudo conectar con el servidor cPanel de la tienda.');
    } finally {
      setLoading(false);
    }
  };

  // Filter storefront products
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

  // Load storefront client history
  useEffect(() => {
    if (storeTab === 'history' && storeClient && getRouteParams().tenantId) {
      (async () => {
        try {
          setLoadingHistory(true);
          const data = await api.getHistorial();
          if (data && data.success) {
            setHistoryVentas(data.ventas || []);
            setHistoryCitas(data.citas || []);
          }
        } catch (e) {
          console.error(e);
        } finally {
          setLoadingHistory(false);
        }
      })();
    }
  }, [storeTab, storeClient, route]);

  // Cart operations
  const updateCart = (newCart) => {
    setCart(newCart);
    localStorage.setItem('bellarista_cart', JSON.stringify(newCart));
  };

  const handleAddToCart = (product) => {
    const existing = cart.find(item => item.id_productos === product.id_productos);
    const stockAct = product.stock_actual != null ? Number(product.stock_actual) : 0;
    const currentQty = existing ? existing.cantidad : 0;

    if (currentQty >= stockAct) {
      alert(`Lo sentimos, no hay más unidades de ${product.nombre_producto}. (Stock actual: ${stockAct}).`);
      return;
    }

    if (existing) {
      updateCart(cart.map(item => 
        item.id_productos === product.id_productos 
          ? { ...item, cantidad: item.cantidad + 1 }
          : item
      ));
    } else {
      updateCart([...cart, {
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
      updateCart(cart.filter(i => i.id_productos !== productId));
    } else {
      updateCart(cart.map(i => 
        i.id_productos === productId 
          ? { ...i, cantidad: newQty }
          : i
      ));
    }
  };

  const removeFromCart = (productId) => {
    updateCart(cart.filter(i => i.id_productos !== productId));
  };

  const getCartTotal = () => {
    return cart.reduce((sum, item) => sum + (item.cantidad * item.precio_venta), 0);
  };

  // Client login/register
  const handleStoreClientLogin = async (e) => {
    e.preventDefault();
    try {
      setErrorMsg('');
      await api.login(storeLoginForm.correo, storeLoginForm.documento);
      const historyData = await api.getHistorial();
      if (historyData && historyData.success) {
        const clientObj = {
          nombre_cliente: storeLoginForm.correo.split('@')[0],
          correo: storeLoginForm.correo,
          numero_documento: storeLoginForm.documento,
          storefrontTenantId: Number(getRouteParams().tenantId)
        };
        if (historyData.ventas && historyData.ventas.length > 0 && historyData.ventas[0].id_clientes) {
          const c = historyData.ventas[0].id_clientes;
          clientObj.nombre_cliente = c.nombre_cliente;
          clientObj.apellidos_clientes = c.apellidos_clientes;
          clientObj.telefono = c.telefono;
          clientObj.direccion = c.direccion;
          clientObj.distrito = c.distrito;
          clientObj.tipo_documento = c.tipo_documento;
          clientObj.id_clientes = c.id_clientes;
          clientObj.id_tenants = c.id_tenants;
        }
        setStoreClient(clientObj);
        localStorage.setItem('bellarista_client', JSON.stringify(clientObj));
        
        // Fill checkout fields
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
        setStoreView('catalog');
      } else {
        setErrorMsg('No se pudo encontrar su historial. Verifique sus datos.');
      }
    } catch (err) {
      setErrorMsg('Error de inicio de sesión. Verifique sus credenciales.');
    }
  };

  const handleStoreClientRegister = async (e) => {
    e.preventDefault();
    try {
      setErrorMsg('');
      await api.registro(storeRegisterForm);
      await api.login(storeRegisterForm.correo, storeRegisterForm.numeroDocumento);
      
      const clientObj = {
        nombre_cliente: storeRegisterForm.nombre,
        apellidos_clientes: storeRegisterForm.apellidos,
        correo: storeRegisterForm.correo,
        telefono: storeRegisterForm.telefono,
        direccion: storeRegisterForm.direccion,
        distrito: storeRegisterForm.distrito,
        tipo_documento: storeRegisterForm.tipoDocumento,
        numero_documento: storeRegisterForm.numeroDocumento,
        storefrontTenantId: Number(getRouteParams().tenantId)
      };
      
      setStoreClient(clientObj);
      localStorage.setItem('bellarista_client', JSON.stringify(clientObj));
      setCheckoutForm(prev => ({ ...prev, ...storeRegisterForm }));
      setStoreView('catalog');
    } catch (err) {
      setErrorMsg('Error en el registro. Posiblemente el correo ya existe.');
    }
  };

  const handleStoreClientLogout = async () => {
    try { await api.logout(); } catch(e) {}
    setStoreClient(null);
    setHistoryVentas([]);
    setHistoryCitas([]);
    localStorage.removeItem('bellarista_client');
    setStoreTab('store');
    setStoreView('catalog');
  };

  const handleStoreCheckout = async (e) => {
    e.preventDefault();
    if (cart.length === 0) return;
    
    const payload = {
      ...checkoutForm,
      items: cart.map(i => ({
        id_productos: i.id_productos,
        cantidad: i.cantidad,
        precio_venta: i.precio_venta
      }))
    };
    
    try {
      setErrorMsg('');
      const raw = await api.checkout(payload);
      const res = typeof raw === 'string' ? JSON.parse(raw) : raw;
      if (res && res.success) {
        setLastVentaId(res.ventaId);
        updateCart([]);
        setStoreView('success');
      } else {
        setErrorMsg(res.error || 'Error al procesar la compra.');
      }
    } catch (err) {
      setErrorMsg('Error de red al procesar el checkout.');
    }
  };

  // --- SUB-FLOW: SUPERADMIN ---
  const handleSuperadminLogin = async (e) => {
    e.preventDefault();
    try {
      setErrorMsg('');
      await api.loginGeneral(superadminEmail, superadminToken);
      setIsSuperadminLoggedIn(true);
      window.location.hash = '#/superadmin/dashboard';
    } catch (err) {
      setErrorMsg('Credenciales incorrectas o error de conexión de SuperAdmin.');
    }
  };

  const loadSuperadminDashboard = async () => {
    try {
      setLoading(true);
      setErrorMsg('');
      const [tList, uList] = await Promise.all([
        api.getSuperadminTenants(),
        api.getSuperadminUsuarios()
      ]);
      setTenants(tList);
      setSaUsers(uList);
      setIsSuperadminLoggedIn(true);
    } catch (err) {
      // If unauthorized, redirect to general login
      setIsSuperadminLoggedIn(false);
      window.location.hash = '#/';
    } finally {
      setLoading(false);
    }
  };

  const handleCreateTenant = async (e) => {
    e.preventDefault();
    try {
      setErrorMsg('');
      const res = await api.crearTenant(tenantForm);
      if (res && res.success) {
        setSuccessMsg(`Tenant creado. Admin temporal creado: ${res.adminCorreo}`);
        setSaModal({ open: false, mode: 'create', type: 'tenant', data: null });
        loadSuperadminDashboard();
      } else {
        setErrorMsg(res.error || 'Error al crear el Tenant.');
      }
    } catch (err) {
      setErrorMsg('Error de red al crear Tenant.');
    }
  };

  const handleEditTenant = async (e) => {
    e.preventDefault();
    try {
      setErrorMsg('');
      const res = await api.editarTenant(tenantForm);
      if (res && res.success) {
        setSuccessMsg('Tenant modificado con éxito.');
        setSaModal({ open: false, mode: 'edit', type: 'tenant', data: null });
        loadSuperadminDashboard();
      } else {
        setErrorMsg(res.error || 'Error al editar el Tenant.');
      }
    } catch (err) {
      setErrorMsg('Error de red al modificar Tenant.');
    }
  };

  const handleEditsaUser = async (e) => {
    e.preventDefault();
    try {
      setErrorMsg('');
      // Parse tenant object
      const payload = {
        ...saUserForm,
        id_tenants: saUserForm.id_tenants ? { id_tenants: Number(saUserForm.id_tenants) } : null
      };
      const res = await api.editarUsuario(payload);
      if (res && res.success) {
        setSuccessMsg('Usuario guardado con éxito.');
        setSaModal({ open: false, mode: 'edit', type: 'user', data: null });
        loadSuperadminDashboard();
      } else {
        setErrorMsg(res.error || 'Error al editar usuario.');
      }
    } catch (err) {
      setErrorMsg('Error de red al modificar usuario.');
    }
  };

  const handleSAEditClick = (type, item) => {
    if (type === 'tenant') {
      setTenantForm(item);
      setSaModal({ open: true, mode: 'edit', type: 'tenant', data: item });
    } else {
      setSaUserForm({
        ...item,
        contrasenia: '',
        id_tenants: item.id_tenants ? item.id_tenants.id_tenants : ''
      });
      setSaModal({ open: true, mode: 'edit', type: 'user', data: item });
    }
  };

  const handleSuperadminLogoutClick = async () => {
    try { await api.superadminLogout(); } catch(e) {}
    setIsSuperadminLoggedIn(false);
    window.location.hash = '#/';
  };


  // --- SUB-FLOW: TENANT ADMIN ---
  const handleAdminLogin = async (e) => {
    e.preventDefault();
    try {
      setErrorMsg('');
      await api.loginAdmin(adminCorreo, adminPassword);
      
      // Fetch the user profile by email to extract their tenant scope
      const userProfile = await apiFetch(`/api/usuarios/correo/${adminCorreo}`).catch(() => null);
      if (userProfile && userProfile.id_tenants) {
        const tenantId = typeof userProfile.id_tenants === 'object' ? userProfile.id_tenants.id_tenants : userProfile.id_tenants;
        localStorage.setItem('bellarista_admin_tenant_id', tenantId);
        setAdminTenantId(Number(tenantId));
      }

      setIsAdminLoggedIn(true);
      window.location.hash = '#/dashboard';
    } catch (err) {
      setErrorMsg('Usuario o contraseña del local incorrectos.');
    }
  };

  const loadAdminDashboard = async () => {
    try {
      setLoading(true);
      setErrorMsg('');
      // Test credentials loading active module
      await loadCrudModule(adminActiveModule);
      setIsAdminLoggedIn(true);
    } catch (err) {
      setIsAdminLoggedIn(false);
      window.location.hash = '#/admin/login';
    } finally {
      setLoading(false);
    }
  };

  // Map modules to API endpoints
  const getModuleEndpoint = (mod) => {
    if (mod === 'categorias') return '/api/categorias_productos';
    if (mod === 'servicios-belleza') return '/api/servicios_belleza';
    return `/api/${mod}`;
  };

  // Load Crud data from backend
  const loadCrudModule = async (modName) => {
    try {
      setLoadingAdminCrud(true);
      const data = await api.crudList(getModuleEndpoint(modName));
      
      let filteredData = data || [];
      const savedTenantId = localStorage.getItem('bellarista_admin_tenant_id') || adminTenantId;
      
      if (savedTenantId) {
        filteredData = filteredData.filter(item => {
          if (!item) return false;
          // If the item doesn't have id_tenants, we show it (e.g. shared settings, if any)
          if (!item.id_tenants) return true;
          const itemTid = typeof item.id_tenants === 'object' ? item.id_tenants.id_tenants : item.id_tenants;
          return Number(itemTid) === Number(savedTenantId);
        });
      }

      setAdminCrudData(filteredData);
    } catch (err) {
      console.error(err);
      throw err;
    } finally {
      setLoadingAdminCrud(false);
    }
  };

  useEffect(() => {
    if (route === '#/dashboard' && isAdminLoggedIn) {
      setAdminCrudData([]); // Clear previous table rows synchronously to prevent object-type key mismatches
      loadCrudModule(adminActiveModule);
    }
  }, [adminActiveModule, route, isAdminLoggedIn]);

  // Get module configuration (fields, labels) for automatic CRUD forms
  const getModuleConfig = (mod) => {
    switch (mod) {
      case 'productos':
        return {
          title: 'Productos',
          primaryKey: 'id_productos',
          fields: [
            { name: 'nombre_producto', label: 'Nombre Producto', type: 'text', required: true },
            { name: 'precio_venta', label: 'Precio Venta (S/)', type: 'number', required: true },
            { name: 'stock_actual', label: 'Stock Actual', type: 'number', required: true },
            { name: 'stock_minimo', label: 'Stock Mínimo', type: 'number', required: true },
            { name: 'descripcion', label: 'Descripción', type: 'text' },
            { name: 'visible_storefront', label: 'Visible Tienda (1=Sí, 0=No)', type: 'number' },
            { name: 'etiqueta_especial', label: 'Etiqueta Especial', type: 'text' }
          ]
        };
      case 'categorias':
        return {
          title: 'Categorías',
          primaryKey: 'id_categorias_productos',
          fields: [
            { name: 'nombre_categoria_producto', label: 'Nombre Categoría', type: 'text', required: true },
            { name: 'descripcion', label: 'Descripción', type: 'text' }
          ]
        };
      case 'marcas':
        return {
          title: 'Marcas',
          primaryKey: 'id_marcas',
          fields: [
            { name: 'nombre_marca', label: 'Nombre Marca', type: 'text', required: true }
          ]
        };
      case 'sedes':
        return {
          title: 'Sedes',
          primaryKey: 'id_sedes',
          fields: [
            { name: 'nombre_sede', label: 'Nombre Sede', type: 'text', required: true },
            { name: 'direccion', label: 'Dirección', type: 'text', required: true },
            { name: 'telefono', label: 'Teléfono', type: 'text' }
          ]
        };
      case 'servicios-belleza':
        return {
          title: 'Servicios de Belleza',
          primaryKey: 'id_servicios_belleza',
          fields: [
            { name: 'nombre_servicio_belleza', label: 'Nombre Servicio', type: 'text', required: true },
            { name: 'precio_base', label: 'Precio Base (S/)', type: 'number', required: true },
            { name: 'duracion_minima', label: 'Duración (Minutos)', type: 'number', required: true },
            { name: 'descripcion', label: 'Descripción', type: 'text' }
          ]
        };
      case 'clientes':
        return {
          title: 'Clientes',
          primaryKey: 'id_clientes',
          fields: [
            { name: 'nombre_cliente', label: 'Nombres', type: 'text', required: true },
            { name: 'apellidos_clientes', label: 'Apellidos', type: 'text', required: true },
            { name: 'correo', label: 'Correo Electrónico', type: 'email', required: true },
            { name: 'telefono', label: 'Teléfono', type: 'text' },
            { name: 'direccion', label: 'Dirección', type: 'text' },
            { name: 'distrito', label: 'Distrito', type: 'text' },
            { name: 'tipo_documento', label: 'Tipo Documento (DNI/RUC)', type: 'text', required: true },
            { name: 'numero_documento', label: 'Número Documento', type: 'text', required: true }
          ]
        };
      case 'citas':
        return {
          title: 'Reservas de Citas',
          primaryKey: 'id_citas',
          fields: [
            { name: 'fecha_cita', label: 'Fecha Cita (YYYY-MM-DD)', type: 'text', required: true },
            { name: 'hora_inicio', label: 'Hora Inicio (HH:MM)', type: 'text', required: true },
            { name: 'hora_fin', label: 'Hora Fin (HH:MM)', type: 'text' },
            { name: 'observaciones', label: 'Observaciones', type: 'text' },
            { name: 'estado', label: 'Estado (1=Activa, 0=Cancelada)', type: 'number' }
          ]
        };
      case 'ventas':
        return {
          title: 'Ventas Realizadas',
          primaryKey: 'id_ventas',
          fields: [
            { name: 'numero_ticket', label: 'Número Ticket', type: 'text', required: true },
            { name: 'tipo_comprobante', label: 'Tipo Comprobante (boleta/factura)', type: 'text', required: true },
            { name: 'total', label: 'Total Cobrado (S/)', type: 'number', required: true },
            { name: 'estado_sunat', label: 'Estado SUNAT (aceptada/rechazada)', type: 'text' }
          ]
        };
      default:
        return { title: 'Módulo', primaryKey: 'id', fields: [] };
    }
  };

  const handleAdminCrudSave = async (e) => {
    e.preventDefault();
    const config = getModuleConfig(adminActiveModule);
    const endpoint = getModuleEndpoint(adminActiveModule);
    try {
      setErrorMsg('');
      const savedTenantId = localStorage.getItem('bellarista_admin_tenant_id') || adminTenantId;
      const payload = { ...dynamicFormFields };
      
      if (savedTenantId) {
        payload.id_tenants = {
          id_tenants: Number(savedTenantId)
        };
      }

      if (crudModal.mode === 'create') {
        await api.crudCreate(endpoint, payload);
        setSuccessMsg('Registro creado con éxito.');
      } else {
        const id = crudModal.data[config.primaryKey];
        await api.crudUpdate(endpoint, id, payload);
        setSuccessMsg('Registro modificado con éxito.');
      }
      setCrudModal({ open: false, mode: 'create', data: null });
      loadCrudModule(adminActiveModule);
    } catch (err) {
      setErrorMsg('No se pudo guardar el registro en cPanel.');
    }
  };

  const handleAdminCrudDelete = async (id) => {
    if (!confirm('¿Eliminar este registro permanentemente?')) return;
    const endpoint = getModuleEndpoint(adminActiveModule);
    try {
      setErrorMsg('');
      await api.crudDelete(endpoint, id);
      setSuccessMsg('Registro eliminado con éxito.');
      loadCrudModule(adminActiveModule);
    } catch (err) {
      setErrorMsg('Error al eliminar registro. Asegúrese de que no tenga relaciones activas.');
    }
  };

  const handleAdminEditClick = (item) => {
    const config = getModuleConfig(adminActiveModule);
    const formFields = {};
    config.fields.forEach(f => {
      formFields[f.name] = item[f.name] ?? '';
    });
    setDynamicFormFields(formFields);
    setCrudModal({ open: true, mode: 'edit', data: item });
  };

  const handleAdminCreateClick = () => {
    const config = getModuleConfig(adminActiveModule);
    const formFields = {};
    config.fields.forEach(f => {
      formFields[f.name] = f.type === 'number' ? 0 : '';
    });
    setDynamicFormFields(formFields);
    setCrudModal({ open: true, mode: 'create', data: null });
  };

  const handleAdminLogoutClick = async () => {
    try { await api.adminLogout(); } catch(e) {}
    localStorage.removeItem('bellarista_admin_tenant_id');
    setAdminTenantId(null);
    setIsAdminLoggedIn(false);
    window.location.hash = '#/admin/login';
  };

  const { tenantId } = getRouteParams();

  // SuperAdmin login & dashboard
  if (route === '#/' || route === '' || route === '#/superadmin/dashboard') {
    return (
      <SuperAdminPortal
        route={route}
        superadminEmail={superadminEmail}
        setSuperadminEmail={setSuperadminEmail}
        superadminToken={superadminToken}
        setSuperadminToken={setSuperadminToken}
        handleSuperadminLogin={handleSuperadminLogin}
        handleSuperadminLogoutClick={handleSuperadminLogoutClick}
        saTab={saTab}
        setSaTab={setSaTab}
        tenants={tenants}
        setTenants={setTenants}
        saUsers={saUsers}
        setSaUsers={setSaUsers}
        saModal={saModal}
        setSaModal={setSaModal}
        tenantForm={tenantForm}
        setTenantForm={setTenantForm}
        saUserForm={saUserForm}
        setSaUserForm={setSaUserForm}
        handleCreateTenant={handleCreateTenant}
        handleEditTenant={handleEditTenant}
        handleEditsaUser={handleEditsaUser}
        handleSAEditClick={handleSAEditClick}
        successMsg={successMsg}
        errorMsg={errorMsg}
      />
    );
  }

  // Tenant Admin login & dashboard
  if (route === '#/admin/login' || route === '#/dashboard') {
    return (
      <SedeAdminPortal
        route={route}
        errorMsg={errorMsg}
        successMsg={successMsg}
        loadingAdminCrud={loadingAdminCrud}
        adminCorreo={adminCorreo}
        setAdminCorreo={setAdminCorreo}
        adminPassword={adminPassword}
        setAdminPassword={setAdminPassword}
        handleAdminLogin={handleAdminLogin}
        adminActiveModule={adminActiveModule}
        setAdminActiveModule={setAdminActiveModule}
        getModuleConfig={getModuleConfig}
        adminCrudData={adminCrudData}
        handleAdminCreateClick={handleAdminCreateClick}
        handleAdminEditClick={handleAdminEditClick}
        handleAdminCrudDelete={handleAdminCrudDelete}
        handleAdminLogoutClick={handleAdminLogoutClick}
        crudModal={crudModal}
        setCrudModal={setCrudModal}
        handleAdminCrudSave={handleAdminCrudSave}
        dynamicFormFields={dynamicFormFields}
        setDynamicFormFields={setDynamicFormFields}
      />
    );
  }

  // Customer Storefront
  return (
    <StorefrontPortal
      route={route}
      tenantId={tenantId}
      loading={loading}
      errorMsg={errorMsg}
      storeTab={storeTab}
      setStoreTab={setStoreTab}
      storeView={storeView}
      setStoreView={setStoreView}
      categories={categories}
      activeCategory={activeCategory}
      setActiveCategory={setActiveCategory}
      searchQuery={searchQuery}
      setSearchQuery={setSearchQuery}
      filteredProducts={filteredProducts}
      services={services}
      cart={cart}
      isCartOpen={isCartOpen}
      setIsCartOpen={setIsCartOpen}
      handleAddToCart={handleAddToCart}
      removeFromCart={removeFromCart}
      updateCartQuantity={updateCartQuantity}
      getCartTotal={getCartTotal}
      storeClient={storeClient}
      handleStoreClientLogout={handleStoreClientLogout}
      storeLoginForm={storeLoginForm}
      setStoreLoginForm={setStoreLoginForm}
      handleStoreClientLogin={handleStoreClientLogin}
      storeRegisterForm={storeRegisterForm}
      setStoreRegisterForm={setStoreRegisterForm}
      handleStoreClientRegister={handleStoreClientRegister}
      checkoutForm={checkoutForm}
      setCheckoutForm={setCheckoutForm}
      handleStoreCheckout={handleStoreCheckout}
      lastVentaId={lastVentaId}
      loadingHistory={loadingHistory}
      historyVentas={historyVentas}
      historyCitas={historyCitas}
    />
  );
}

export default App;
