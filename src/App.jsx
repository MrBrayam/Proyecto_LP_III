import React, { useState, useEffect } from 'react';
import { api, API_BASE_URL } from './api';
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
  Scissors, 
  FileText, 
  CheckCircle, 
  Calendar, 
  ArrowLeft,
  Briefcase,
  AlertCircle,
  Settings,
  MapPin,
  TrendingUp,
  Tag,
  Users,
  ShieldAlert
} from 'lucide-react';

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
      
      setProducts(prods);
      setFilteredProducts(prods);
      setCategories(cats);
      setServices(servs);
      setBrands(brnds);
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
    return `/api/${mod}`;
  };

  // Load Crud data from backend
  const loadCrudModule = async (modName) => {
    try {
      setLoadingAdminCrud(true);
      const data = await api.crudList(getModuleEndpoint(modName));
      setAdminCrudData(data || []);
    } catch (err) {
      console.error(err);
      throw err;
    } finally {
      setLoadingAdminCrud(false);
    }
  };

  useEffect(() => {
    if (route === '#/dashboard' && isAdminLoggedIn) {
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
      if (crudModal.mode === 'create') {
        await api.crudCreate(endpoint, dynamicFormFields);
        setSuccessMsg('Registro creado con éxito.');
      } else {
        const id = crudModal.data[config.primaryKey];
        await api.crudUpdate(endpoint, id, dynamicFormFields);
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
    setIsAdminLoggedIn(false);
    window.location.hash = '#/admin/login';
  };

  // Download Sunat invoice inside App (re-implemented)
  const downloadReceipt = (ventaId) => {
    downloadReceiptGlobal(ventaId);
  };

  const downloadReceiptGlobal = async (ventaId) => {
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
      alert('Error al generar el PDF de la boleta.');
    }
  };


  // --- MAIN RENDER ROUTING FLOW ---
  
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
              <h1 style={{ color: 'white' }}>Bella<span>rista</span></h1>
              <p>Panel de SuperAdministración</p>
            </div>

            {errorMsg && <div className="alert-error">{errorMsg}</div>}

            <form onSubmit={handleSuperadminLogin} autoComplete="off">
              <div className="form-group">
                <label style={{ color: 'rgba(255,255,255,0.7)' }}>Correo Electrónico</label>
                <input 
                  type="email" 
                  required 
                  placeholder="ejemplo@correo.com"
                  value={superadminEmail}
                  onChange={(e) => setSuperadminEmail(e.target.value)}
                  style={{ background: 'rgba(255,255,255,0.07)', color: 'white', borderColor: 'rgba(255,255,255,0.12)' }}
                />
              </div>
              <div className="form-group">
                <label style={{ color: 'rgba(255,255,255,0.7)' }}>Token de Acceso</label>
                <input 
                  type="password" 
                  required 
                  placeholder="Ingrese su access token"
                  value={superadminToken}
                  onChange={(e) => setSuperadminToken(e.target.value)}
                  style={{ background: 'rgba(255,255,255,0.07)', color: 'white', borderColor: 'rgba(255,255,255,0.12)' }}
                />
              </div>
              <button type="submit" className="btn-login" style={{ background: 'linear-gradient(135deg, #667eea 0%, #764ba2 100%)', width: '100%', border: 'none', borderRadius: '12px', padding: '14px', color: 'white', fontWeight: '600', cursor: 'pointer' }}>
                Ingresar al Panel
              </button>
            </form>

            <div style={{ marginTop: '24px', display: 'flex', flexDirection: 'column', gap: '8px', fontSize: '12px', textAlign: 'center' }}>
              <a href="#/admin/login" style={{ color: '#8b9cf7', textDecoration: 'underline' }}>
                Acceder al Portal de Administración de Sede (Locales)
              </a>
              <a href="#/tienda/1" style={{ color: '#c5a880', textDecoration: 'underline' }}>
                Ver Tienda Online (Prueba - Sede 1)
              </a>
            </div>
            <p className="footer-text" style={{ color: 'rgba(255,255,255,0.3)', marginTop: '24px', fontSize: '11px', textAlign: 'center' }}>Acceso exclusivo para administradores de plataforma</p>
          </div>
        </div>
      </div>
    );
  }

  // 2. ROUTE: SuperAdmin Dashboard (`#/superadmin/dashboard`)
  if (route === '#/superadmin/dashboard') {
    return (
      <div className="sa-layout" style={{ background: '#0f1117', color: '#e8e8f0', minHeight: '100vh', fontFamily: 'sans-serif' }}>
        {/* SA Navbar */}
        <div className="sa-navbar" style={{ background: '#1a1d27', display: 'flex', justifyContent: 'space-between', padding: '16px 32px', borderBottom: '1px solid rgba(255,255,255,0.08)', alignItems: 'center' }}>
          <div style={{ fontSize: '20px', fontWeight: 'bold' }}>Bellarista <span>SuperAdmin</span></div>
          <div style={{ display: 'flex', gap: '16px', alignItems: 'center' }}>
            <button className={`sa-nav-tab ${saTab === 'tenants' ? 'active' : ''}`} onClick={() => setSaTab('tenants')} style={{ background: 'none', border: 'none', color: saTab === 'tenants' ? '#667eea' : 'gray', cursor: 'pointer', fontWeight: 'bold' }}>
              Tenants
            </button>
            <button className={`sa-nav-tab ${saTab === 'users' ? 'active' : ''}`} onClick={() => setSaTab('users')} style={{ background: 'none', border: 'none', color: saTab === 'users' ? '#667eea' : 'gray', cursor: 'pointer', fontWeight: 'bold' }}>
              Usuarios
            </button>
            <button onClick={handleSuperadminLogoutClick} style={{ background: 'red', border: 'none', padding: '8px 16px', color: 'white', borderRadius: '8px', cursor: 'pointer' }}>
              Cerrar Sesión
            </button>
          </div>
        </div>

        {/* SA Container */}
        <div className="sa-main" style={{ padding: '32px' }}>
          {successMsg && <div style={{ background: '#064e3b', color: '#34d399', padding: '12px', borderRadius: '8px', marginBottom: '16px' }}>{successMsg}</div>}
          {errorMsg && <div style={{ background: '#7f1d1d', color: '#f87171', padding: '12px', borderRadius: '8px', marginBottom: '16px' }}>{errorMsg}</div>}

          {saTab === 'tenants' ? (
            <div>
              <div style={{ display: 'flex', justifyContent: 'space-between', marginBottom: '16px', alignItems: 'center' }}>
                <h2>Listado de Tenants (Boutiques / Salones)</h2>
                <button 
                  onClick={() => {
                    setTenantForm({ razon_social: '', ruc: '', direccion_fiscal: '', correo: '', telefono: '', nombre_comercial: '', tipo_negocio: 'estetica' });
                    setSaModal({ open: true, mode: 'create', type: 'sa-tenant', data: null });
                  }}
                  style={{ background: '#667eea', color: 'white', border: 'none', padding: '10px 20px', borderRadius: '8px', cursor: 'pointer' }}
                >
                  + Crear Tenant
                </button>
              </div>

              <div style={{ display: 'grid', gridTemplateColumns: 'repeat(auto-fill, minmax(300px, 1fr))', gap: '20px' }}>
                {tenants.map(t => (
                  <div 
                    key={t.id_tenants} 
                    onClick={() => { window.location.hash = `#/tienda/${t.id_tenants}`; }}
                    style={{ 
                      background: '#1a1d27', 
                      border: '1px solid rgba(255,255,255,0.08)', 
                      borderRadius: '12px', 
                      padding: '20px',
                      cursor: 'pointer',
                      transition: 'all 0.2s ease',
                    }}
                    className="sa-tenant-card-interactive"
                  >
                    <div style={{ display: 'flex', alignItems: 'center', gap: '12px', marginBottom: '12px' }}>
                      <div style={{ width: '40px', height: '40px', background: '#667eea', borderRadius: '8px', display: 'flex', alignItems: 'center', justifyContent: 'center', fontWeight: 'bold' }}>{t.id_tenants}</div>
                      <div>
                        <h4 style={{ margin: 0 }}>{t.nombre_comercial}</h4>
                        <span style={{ fontSize: '11px', color: 'gray' }}>{t.razon_social}</span>
                      </div>
                    </div>
                    <div style={{ fontSize: '12px', display: 'flex', flexDirection: 'column', gap: '4px', color: '#a0aec0' }}>
                      <div><strong>RUC:</strong> {t.ruc}</div>
                      <div><strong>Correo:</strong> {t.correo}</div>
                      <div><strong>Teléfono:</strong> {t.telefono}</div>
                      <div><strong>Dirección:</strong> {t.direccion_fiscal}</div>
                    </div>
                    <div style={{ marginTop: '16px', display: 'flex', justifyContent: 'space-between', gap: '10px' }}>
                      <span style={{ color: '#8b9cf7', fontSize: '11px', textDecoration: 'underline', alignSelf: 'center' }}>
                        Ver Tienda →
                      </span>
                      <button 
                        onClick={(e) => { e.stopPropagation(); handleSAEditClick('tenant', t); }} 
                        style={{ background: '#667eea', color: 'white', border: 'none', padding: '6px 12px', borderRadius: '6px', fontSize: '11px', cursor: 'pointer' }}
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
              <table style={{ width: '100%', borderCollapse: 'collapse', marginTop: '16px', background: '#1a1d27', borderRadius: '8px', overflow: 'hidden' }}>
                <thead>
                  <tr style={{ background: '#22263a', textAlign: 'left', fontSize: '13px' }}>
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
                    <tr key={u.id_usuarios} style={{ borderBottom: '1px solid rgba(255,255,255,0.05)', fontSize: '13px' }}>
                      <td style={{ padding: '12px' }}>{u.id_usuarios}</td>
                      <td style={{ padding: '12px' }}>{u.nombre_usuario} {u.apellidos_usuario}</td>
                      <td style={{ padding: '12px' }}>{u.correo}</td>
                      <td style={{ padding: '12px' }}>{u.id_tenants ? u.id_tenants.nombre_comercial : 'SuperAdmin'}</td>
                      <td style={{ padding: '12px' }}><span style={{ background: u.tipo_usuario === 'admin' ? '#1e3a8a' : '#14532d', padding: '2px 8px', borderRadius: '4px', fontSize: '11px' }}>{u.tipo_usuario}</span></td>
                      <td style={{ padding: '12px' }}>{u.estado === 1 ? 'Activo' : 'Inactivo'}</td>
                      <td style={{ padding: '12px' }}>
                        <button onClick={() => handleSAEditClick('user', u)} style={{ background: '#667eea', color: 'white', border: 'none', padding: '4px 8px', borderRadius: '4px', cursor: 'pointer' }}>Editar</button>
                      </td>
                    </tr>
                  ))}
                </tbody>
              </table>
            </div>
          )}
        </div>

        {/* SA Modal Dialog */}
        {saModal.open && (
          <div style={{ position: 'fixed', top: 0, left: 0, width: '100%', height: '100%', background: 'rgba(0,0,0,0.7)', display: 'flex', justifyContent: 'center', alignItems: 'center', zIndex: 1000 }}>
            <div style={{ background: '#1a1d27', padding: '32px', borderRadius: '12px', width: '90%', maxWidth: '500px', border: '1px solid rgba(255,255,255,0.08)' }}>
              <div style={{ display: 'flex', justifyContent: 'space-between', marginBottom: '20px' }}>
                <h3>{saModal.mode === 'create' ? 'Crear' : 'Editar'} {saModal.type === 'tenant' ? 'Tenant' : 'Usuario'}</h3>
                <button onClick={() => setSaModal({ open: false })} style={{ background: 'none', border: 'none', color: 'white', fontSize: '20px', cursor: 'pointer' }}>&times;</button>
              </div>

              {saModal.type === 'tenant' ? (
                <form onSubmit={saModal.mode === 'create' ? handleCreateTenant : handleEditTenant}>
                  <div className="form-group">
                    <label>Nombre Comercial</label>
                    <input type="text" required value={tenantForm.nombre_comercial} onChange={(e) => setTenantForm({ ...tenantForm, nombre_comercial: e.target.value })} style={{ background: '#22263a', color: 'white', border: '1px solid rgba(255,255,255,0.08)' }} />
                  </div>
                  <div className="form-group">
                    <label>Razón Social</label>
                    <input type="text" required value={tenantForm.razon_social} onChange={(e) => setTenantForm({ ...tenantForm, razon_social: e.target.value })} style={{ background: '#22263a', color: 'white', border: '1px solid rgba(255,255,255,0.08)' }} />
                  </div>
                  <div className="form-group">
                    <label>RUC</label>
                    <input type="text" required value={tenantForm.ruc} onChange={(e) => setTenantForm({ ...tenantForm, ruc: e.target.value })} style={{ background: '#22263a', color: 'white', border: '1px solid rgba(255,255,255,0.08)' }} />
                  </div>
                  <div className="form-group">
                    <label>Dirección Fiscal</label>
                    <input type="text" required value={tenantForm.direccion_fiscal} onChange={(e) => setTenantForm({ ...tenantForm, direccion_fiscal: e.target.value })} style={{ background: '#22263a', color: 'white', border: '1px solid rgba(255,255,255,0.08)' }} />
                  </div>
                  <div style={{ display: 'grid', gridTemplateColumns: '1fr 1fr', gap: '12px' }}>
                    <div className="form-group">
                      <label>Correo</label>
                      <input type="email" required value={tenantForm.correo} onChange={(e) => setTenantForm({ ...tenantForm, correo: e.target.value })} style={{ background: '#22263a', color: 'white', border: '1px solid rgba(255,255,255,0.08)' }} />
                    </div>
                    <div className="form-group">
                      <label>Teléfono</label>
                      <input type="text" value={tenantForm.telefono} onChange={(e) => setTenantForm({ ...tenantForm, telefono: e.target.value })} style={{ background: '#22263a', color: 'white', border: '1px solid rgba(255,255,255,0.08)' }} />
                    </div>
                  </div>
                  <button type="submit" style={{ background: '#667eea', width: '100%', padding: '12px', border: 'none', color: 'white', borderRadius: '8px', cursor: 'pointer', fontWeight: 'bold' }}>Guardar</button>
                </form>
              ) : (
                <form onSubmit={handleEditsaUser}>
                  <div style={{ display: 'grid', gridTemplateColumns: '1fr 1fr', gap: '12px' }}>
                    <div className="form-group">
                      <label>Nombres</label>
                      <input type="text" required value={saUserForm.nombre_usuario} onChange={(e) => setSaUserForm({ ...saUserForm, nombre_usuario: e.target.value })} style={{ background: '#22263a', color: 'white', border: '1px solid rgba(255,255,255,0.08)' }} />
                    </div>
                    <div className="form-group">
                      <label>Apellidos</label>
                      <input type="text" required value={saUserForm.apellidos_usuario} onChange={(e) => setSaUserForm({ ...saUserForm, apellidos_usuario: e.target.value })} style={{ background: '#22263a', color: 'white', border: '1px solid rgba(255,255,255,0.08)' }} />
                    </div>
                  </div>
                  <div className="form-group">
                    <label>Correo</label>
                    <input type="email" required value={saUserForm.correo} onChange={(e) => setSaUserForm({ ...saUserForm, correo: e.target.value })} style={{ background: '#22263a', color: 'white', border: '1px solid rgba(255,255,255,0.08)' }} />
                  </div>
                  <div className="form-group">
                    <label>Contraseña (Opcional - dejar vacío para conservar)</label>
                    <input type="password" value={saUserForm.contrasenia} onChange={(e) => setSaUserForm({ ...saUserForm, contrasenia: e.target.value })} style={{ background: '#22263a', color: 'white', border: '1px solid rgba(255,255,255,0.08)' }} />
                  </div>
                  <div style={{ display: 'grid', gridTemplateColumns: '1fr 1fr', gap: '12px' }}>
                    <div className="form-group">
                      <label>Rol</label>
                      <select value={saUserForm.tipo_usuario} onChange={(e) => setSaUserForm({ ...saUserForm, tipo_usuario: e.target.value })} style={{ background: '#22263a', color: 'white', border: '1px solid rgba(255,255,255,0.08)' }}>
                        <option value="admin">Administrador Sede</option>
                        <option value="empleado">Empleado Estilista</option>
                      </select>
                    </div>
                    <div className="form-group">
                      <label>Estado</label>
                      <select value={saUserForm.estado} onChange={(e) => setSaUserForm({ ...saUserForm, estado: Number(e.target.value) })} style={{ background: '#22263a', color: 'white', border: '1px solid rgba(255,255,255,0.08)' }}>
                        <option value={1}>Activo</option>
                        <option value={0}>Inactivo</option>
                      </select>
                    </div>
                  </div>
                  <button type="submit" style={{ background: '#667eea', width: '100%', padding: '12px', border: 'none', color: 'white', borderRadius: '8px', cursor: 'pointer', fontWeight: 'bold' }}>Guardar Cambios</button>
                </form>
              )}
            </div>
          </div>
        )}
      </div>
    );
  }

  // 3. ROUTE: Tenant Admin Login (`#/admin/login`)
  if (route === '#/admin/login') {
    return (
      <div className="login-page-bg" style={{ background: 'linear-gradient(135deg, #1e1e24 0%, #302d27 100%)' }}>
        <div className="login-container">
          <div className="login-card-dark">
            <div className="logo-section">
              <div className="logo-icon" style={{ background: 'linear-gradient(135deg, #c5a880 0%, #b09168 100%)' }}>
                <User size={32} color="white" />
              </div>
              <h1 style={{ color: 'white' }}>Bella<span>rista</span></h1>
              <p>Portal de Administración de Sede</p>
            </div>

            {errorMsg && <div className="alert-error">{errorMsg}</div>}

            <form onSubmit={handleAdminLogin} autoComplete="off">
              <div className="form-group">
                <label style={{ color: 'rgba(255,255,255,0.7)' }}>Correo Electrónico</label>
                <input 
                  type="email" 
                  required 
                  placeholder="admin@correo.com"
                  value={adminCorreo}
                  onChange={(e) => setAdminCorreo(e.target.value)}
                  style={{ background: 'rgba(255,255,255,0.07)', color: 'white', borderColor: 'rgba(255,255,255,0.12)' }}
                />
              </div>
              <div className="form-group">
                <label style={{ color: 'rgba(255,255,255,0.7)' }}>Contraseña</label>
                <input 
                  type="password" 
                  required 
                  placeholder="Ingrese su contraseña"
                  value={adminPassword}
                  onChange={(e) => setAdminPassword(e.target.value)}
                  style={{ background: 'rgba(255,255,255,0.07)', color: 'white', borderColor: 'rgba(255,255,255,0.12)' }}
                />
              </div>
              <button type="submit" className="btn-login" style={{ background: 'linear-gradient(135deg, #c5a880 0%, #b09168 100%)', width: '100%', border: 'none', borderRadius: '12px', padding: '14px', color: 'white', fontWeight: '600', cursor: 'pointer' }}>
                Ingresar al Portal
              </button>
            </form>

            <div style={{ marginTop: '24px', display: 'flex', flexDirection: 'column', gap: '8px', fontSize: '12px', textAlign: 'center' }}>
              <a href="#/" style={{ color: '#c5a880', textDecoration: 'underline' }}>
                Regresar a Acceso SuperAdmin
              </a>
            </div>
            <p className="footer-text" style={{ color: 'rgba(255,255,255,0.3)', marginTop: '24px', fontSize: '11px', textAlign: 'center' }}>Acceso restringido para personal autorizado</p>
          </div>
        </div>
      </div>
    );
  }

  // 4. ROUTE: Tenant Admin Dashboard (`#/dashboard`)
  if (route === '#/dashboard') {
    const config = getModuleConfig(adminActiveModule);
    return (
      <div className="admin-dashboard-layout" style={{ display: 'grid', gridTemplateColumns: '260px 1fr', minHeight: '100vh', background: '#f5f5f7' }}>
        {/* Admin Sidebar */}
        <aside style={{ background: '#1e1e24', color: '#e6e6e9', padding: '24px', display: 'flex', flexDirection: 'column', justifyContent: 'space-between' }}>
          <div>
            <div style={{ fontSize: '22px', fontWeight: 'bold', fontFamily: "'Playfair Display', serif", marginBottom: '32px', color: 'white' }}>
              Bellarista <span style={{ color: '#c5a880' }}>Admin</span>
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
            <a href="#/tienda/1" target="_blank" rel="noreferrer" style={{ textDecoration: 'none', color: '#c5a880', fontSize: '13px', textAlign: 'center', fontWeight: 'bold' }}>
              Ir a Tienda Cliente
            </a>
            <button onClick={handleAdminLogoutClick} style={{ background: 'rgba(255,255,255,0.05)', border: '1px solid rgba(255,255,255,0.1)', color: '#f87171', padding: '10px', borderRadius: '8px', cursor: 'pointer', display: 'flex', alignItems: 'center', gap: '8px', justifyContent: 'center' }}>
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

          {successMsg && <div style={{ background: '#d1fae5', color: '#065f46', padding: '12px 16px', borderRadius: '8px', marginBottom: '20px', fontSize: '13px' }}>{successMsg}</div>}
          {errorMsg && <div className="error-alert">{errorMsg}</div>}

          {loadingAdminCrud ? (
            <div style={{ textAlign: 'center', padding: '80px' }}>
              <div className="loading-spinner"></div>
              <p style={{ marginTop: '16px', color: 'var(--text-muted)' }}>Cargando registros desde cPanel...</p>
            </div>
          ) : (
            <div style={{ background: 'white', borderRadius: '16px', border: '1px solid var(--border)', overflow: 'hidden', boxShadow: 'var(--shadow-soft)' }}>
              <table style={{ width: '100%', borderCollapse: 'collapse', textAlign: 'left', fontSize: '13px' }}>
                <thead>
                  <tr style={{ background: '#fafafc', borderBottom: '1px solid var(--border)' }}>
                    <th style={{ padding: '16px' }}>Código ID</th>
                    {config.fields.slice(0, 4).map(f => (
                      <th key={f.name} style={{ padding: '16px' }}>{f.label}</th>
                    ))}
                    <th style={{ padding: '16px', textAlign: 'right' }}>Acciones</th>
                  </tr>
                </thead>
                <tbody>
                  {adminCrudData.length === 0 ? (
                    <tr>
                      <td colSpan={6} style={{ padding: '32px', textAlign: 'center', color: 'var(--text-muted)' }}>No hay registros disponibles.</td>
                    </tr>
                  ) : (
                    adminCrudData.map(item => {
                      const id = item[config.primaryKey];
                      return (
                        <tr key={id} style={{ borderBottom: '1px solid rgba(0,0,0,0.03)' }}>
                          <td style={{ padding: '16px', fontWeight: 'bold' }}>{id}</td>
                          {config.fields.slice(0, 4).map(f => {
                            let value = item[f.name];
                            if (typeof value === 'object' && value !== null) {
                              value = value.nombre_marca || value.nombre_categoria_producto || JSON.stringify(value);
                            }
                            return <td key={f.name} style={{ padding: '16px' }}>{value ?? '-'}</td>;
                          })}
                          <td style={{ padding: '16px', textAlign: 'right', display: 'flex', gap: '8px', justifyContent: 'flex-end' }}>
                            <button onClick={() => handleAdminEditClick(item)} style={{ background: '#e6e6e9', border: 'none', padding: '6px 12px', borderRadius: '6px', cursor: 'pointer', fontSize: '11px', fontWeight: '600' }}>
                              Editar
                            </button>
                            <button onClick={() => handleAdminCrudDelete(id)} style={{ background: '#fee2e2', color: '#991b1b', border: 'none', padding: '6px 12px', borderRadius: '6px', cursor: 'pointer', fontSize: '11px', fontWeight: '600' }}>
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
          <div style={{ position: 'fixed', top: 0, left: 0, width: '100%', height: '100%', background: 'rgba(0,0,0,0.4)', display: 'flex', justifyContent: 'center', alignItems: 'center', zIndex: 1000 }}>
            <div style={{ background: 'white', padding: '32px', borderRadius: '16px', width: '90%', maxWidth: '500px', boxShadow: 'var(--shadow-soft)' }}>
              <div style={{ display: 'flex', justifyContent: 'space-between', marginBottom: '20px', alignItems: 'center' }}>
                <h3>{crudModal.mode === 'create' ? 'Nuevo' : 'Editar'} {config.title}</h3>
                <button onClick={() => setCrudModal({ open: false })} style={{ background: 'none', border: 'none', fontSize: '22px', cursor: 'pointer', color: 'var(--text-muted)' }}>&times;</button>
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
                <button type="submit" className="btn-checkout" style={{ width: '100%' }}>
                  Guardar Registro
                </button>
              </form>
            </div>
          </div>
        )}
      </div>
    );
  }

  // 5. ROUTE: Customer Storefront (`#/tienda/:tenantId`)
  const { tenantId } = getRouteParams();
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
                                  <button onClick={() => downloadReceipt(v.id_ventas)} className="btn-pdf-receipt">
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
                <button onClick={() => downloadReceipt(lastVentaId)} className="btn-filled" style={{ padding: '12px 24px', fontSize: '14px', display: 'inline-flex', alignItems: 'center', gap: '8px' }}>
                  Descargar Boleta PDF
                </button>
                <button onClick={() => { setStoreTab('store'); setStoreView('catalog'); }} className="btn-outline" style={{ padding: '12px 24px', fontSize: '14px' }}>
                  Seguir Comprando
                </button>
              </div>
            </main>
          )}
        </div>
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

export default App;
