export const API_BASE_URL = 'http://belleza.spring.informaticapp.com:2451';
export const DEFAULT_API_TOKEN = 'eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJkZDdiODVjNGY0YmVhNzU4ODJkNTg4Y2IxZmQxMzNiM2I4OWRmYWEyMDAxM2EyMmJmOWQyMjNjYmYxY2JhOWJkIiwiaWF0IjoxNzgwMzcwMTQzLCJleHAiOjQ5MzM5NzAxNDN9.ep3uRPCM9RIkKvXVroDyOE06rexYlqSl9vMnzVPUQL-OMr4DN3aseJZvBbFBAMkaFLCFAp-6FjckTpIjPcPtcg';

// Helper to execute API requests
export async function apiFetch(url, options = {}) {
    const headers = {
        'Authorization': `Bearer ${DEFAULT_API_TOKEN}`,
        ...(options.headers || {})
    };
    
    if (!(options.body instanceof FormData)) {
        headers['Content-Type'] = 'application/json';
    }

    const response = await fetch(`${API_BASE_URL}${url}`, {
        ...options,
        headers,
        credentials: 'include' // crucial for cross-origin session cookies (HttpSession)
    });

    if (!response.ok) {
        const text = await response.text();
        throw new Error(text || 'Error en la petición');
    }

    const contentType = response.headers.get('content-type');
    if (contentType && contentType.includes('application/json')) {
        return response.json();
    }
    return response.text();
}

// API methods
export const api = {
    getProductos: () => apiFetch('/tienda/api/productos'),
    getServicios: () => apiFetch('/tienda/api/servicios'),
    getCategorias: () => apiFetch('/tienda/api/categorias'),
    getMarcas: () => apiFetch('/tienda/api/marcas'),
    getHistorial: () => apiFetch('/tienda/api/historial'),
    getVenta: (id) => apiFetch(`/tienda/api/venta/${id}`),
    getDetallesVenta: (id) => apiFetch(`/tienda/api/detalles-venta/${id}`),
    
    // Set tenant scope in backend session (ignores template resolution errors in cPanel)
    setTenantSession: async (tenantId) => {
        try {
            await fetch(`${API_BASE_URL}/tienda/${tenantId}`, {
                method: 'GET',
                headers: {
                    'Authorization': `Bearer ${DEFAULT_API_TOKEN}`
                },
                credentials: 'include'
            });
        } catch (e) {
            console.warn('View resolution bypassed:', e);
        }
        return true;
    },
    
    // Auth
    login: async (correo, documento) => {
        const formData = new URLSearchParams();
        formData.append('correo', correo);
        formData.append('documento', documento);
        
        const response = await fetch(`${API_BASE_URL}/tienda/login`, {
            method: 'POST',
            body: formData,
            headers: {
                'Authorization': `Bearer ${DEFAULT_API_TOKEN}`
            },
            credentials: 'include'
        });
        
        if (!response.ok) {
            throw new Error('Error al iniciar sesión');
        }
        return true;
    },
    
    registro: async (data) => {
        const formData = new URLSearchParams();
        Object.keys(data).forEach(key => {
            formData.append(key, data[key]);
        });
        
        const response = await fetch(`${API_BASE_URL}/tienda/registro`, {
            method: 'POST',
            body: formData,
            headers: {
                'Authorization': `Bearer ${DEFAULT_API_TOKEN}`
            },
            credentials: 'include'
        });
        
        if (!response.ok) {
            throw new Error('Error al registrarse');
        }
        return true;
    },
    
    logout: () => apiFetch('/tienda/logout'),
    
    checkout: (checkoutData) => apiFetch('/tienda/api/checkout', {
        method: 'POST',
        body: JSON.stringify(checkoutData)
    }),

    // SuperAdmin Auth & Operations
    loginGeneral: async (email, accessToken) => {
        const formData = new URLSearchParams();
        formData.append('email', email);
        formData.append('accessToken', accessToken);
        
        const response = await fetch(`${API_BASE_URL}/login-general`, {
            method: 'POST',
            body: formData,
            headers: {
                'Authorization': `Bearer ${DEFAULT_API_TOKEN}`
            },
            credentials: 'include'
        });
        
        if (!response.ok) {
            throw new Error('Error al iniciar sesión de SuperAdmin');
        }
        return true;
    },
    getSuperadminTenants: () => apiFetch('/superadmin/api/tenants'),
    getSuperadminUsuarios: () => apiFetch('/superadmin/api/usuarios'),
    crearTenant: (data) => apiFetch('/superadmin/tenants/crear', {
        method: 'POST',
        body: JSON.stringify(data)
    }),
    editarTenant: (data) => apiFetch('/superadmin/tenants/editar', {
        method: 'POST',
        body: JSON.stringify(data)
    }),
    editarUsuario: (data) => apiFetch('/superadmin/usuarios/editar', {
        method: 'POST',
        body: JSON.stringify(data)
    }),
    superadminLogout: () => apiFetch('/superadmin/logout'),

    // Admin Auth
    loginAdmin: async (correo, contrasenia) => {
        const formData = new URLSearchParams();
        formData.append('correo', correo);
        formData.append('contrasenia', contrasenia);
        
        const response = await fetch(`${API_BASE_URL}/admin/login`, {
            method: 'POST',
            body: formData,
            headers: {
                'Authorization': `Bearer ${DEFAULT_API_TOKEN}`
            },
            credentials: 'include'
        });
        
        if (!response.ok) {
            throw new Error('Credenciales de administrador incorrectas');
        }
        return true;
    },
    adminLogout: () => apiFetch('/logout'),

    // Generic REST API CRUD (replaces app.js crud actions)
    crudList: (endpoint) => apiFetch(endpoint),
    crudCreate: (endpoint, data) => apiFetch(endpoint, {
        method: 'POST',
        body: JSON.stringify(data)
    }),
    crudUpdate: (endpoint, id, data) => apiFetch(`${endpoint}/${id}`, {
        method: 'PUT',
        body: JSON.stringify(data)
    }),
    crudDelete: (endpoint, id) => apiFetch(`${endpoint}/${id}`, {
        method: 'DELETE'
    })
};
