const API = 'http://15.204.230.215:2451';
const urlParams = new URLSearchParams(window.location.search);
const tenantId = urlParams.get('tenantId') || '1';

let allProducts = [];
let filteredProducts = [];
let categories = [];
let activeCategory = null;
let cart = [];
let allServices = [];
let allCombos = [];

// Load Catalog on page ready
document.addEventListener('DOMContentLoaded', () => {
    loadCart();
    updateAuthUI();

    // Fetch tenant info
    fetch(API + '/api/tenants/' + tenantId)
        .then(r => r.json())
        .then(tenant => {
            if (tenant) {
                const logoText = document.getElementById('logoText');
                if (logoText) {
                    logoText.textContent = tenant.nombre_comercial;
                    logoText.href = `/tienda.html?tenantId=${tenantId}`;
                }
                document.title = tenant.nombre_comercial + ' - Tienda Virtual';
                const heroSubtitle = document.getElementById('heroSubtitle');
                if (heroSubtitle) {
                    heroSubtitle.textContent = tenant.nombre_comercial;
                }
            }
        })
        .catch(err => console.error('Error al cargar datos del Tenant:', err));

    fetch(API + '/tienda/api/categorias?tenantIdParam=' + tenantId)
        .then(r => r.json())
        .then(cats => {
            categories = cats;
            renderCategoryFilters();
        });

    fetch(API + '/tienda/api/productos?tenantIdParam=' + tenantId)
        .then(r => r.json())
        .then(prods => {
            allProducts = prods;
            filteredProducts = prods;
            renderProducts();
        });

    fetch(API + '/tienda/api/servicios?tenantIdParam=' + tenantId)
        .then(r => r.json())
        .then(servs => {
            allServices = servs;
        })
        .catch(err => console.error('Error al cargar servicios:', err));

    fetch(API + '/tienda/api/combos?tenantIdParam=' + tenantId)
        .then(r => r.json())
        .then(combos => {
            renderCombos(combos);
        })
        .catch(err => console.error('Error al cargar combos:', err));
});

function updateAuthUI() {
    const cliente = JSON.parse(localStorage.getItem('cliente') || 'null');
    const authLoggedIn = document.getElementById('auth-logged-in');
    const authLoggedOut = document.getElementById('auth-logged-out');
    const menuHistory = document.getElementById('menu-history');
    const loggedClientName = document.getElementById('logged-client-name');
    
    const loginLink = document.getElementById('loginBtnLink');
    const registerLink = document.getElementById('registerBtnLink');
    if (loginLink) loginLink.href = `/tienda_login.html?tenantId=${tenantId}`;
    if (registerLink) registerLink.href = `/tienda_registro.html?tenantId=${tenantId}`;
    
    const adminLink = document.getElementById('portalAdminLink');
    if (adminLink) adminLink.href = '/login.html';

    if (cliente) {
        if (authLoggedIn) authLoggedIn.style.display = 'flex';
        if (authLoggedOut) authLoggedOut.style.display = 'none';
        if (menuHistory) menuHistory.style.display = 'block';
        if (loggedClientName) loggedClientName.textContent = cliente.nombre_cliente;
    } else {
        if (authLoggedIn) authLoggedIn.style.display = 'none';
        if (authLoggedOut) authLoggedOut.style.display = 'flex';
        if (menuHistory) menuHistory.style.display = 'none';
    }
}

function logoutCliente() {
    localStorage.removeItem('cliente');
    window.location.reload();
}
window.logoutCliente = logoutCliente;

function renderCategoryFilters() {
    const container = document.getElementById('categoryFilters');
    categories.forEach(cat => {
        const btn = document.createElement('button');
        btn.className = 'pill';
        btn.textContent = cat.nombre_categoria_producto;
        btn.onclick = () => filterCategory(cat.id_categorias_productos, btn);
        container.appendChild(btn);
    });
}

function filterCategory(catId, element) {
    document.querySelectorAll('#categoryFilters .pill').forEach(b => b.classList.remove('active'));
    element.classList.add('active');
    activeCategory = catId;
    applyFilters();
}

function handleSearch() {
    applyFilters();
}

function applyFilters() {
    const search = document.getElementById('searchInput').value.toLowerCase().trim();
    filteredProducts = allProducts.filter(p => {
        const matchesCat = activeCategory === null || (p.id_categorias_productos && p.id_categorias_produtos.id_categorias_productos === activeCategory);
        const matchesCatFixed = activeCategory === null || (p.id_categorias_productos && p.id_categorias_productos.id_categorias_productos === activeCategory);
        const matchesSearch = !search || p.nombre_producto.toLowerCase().includes(search) || (p.descripcion && p.descripcion.toLowerCase().includes(search));
        return matchesCatFixed && matchesSearch;
    });
    renderProducts();
}

function renderProducts() {
    const grid = document.getElementById('productGrid');
    grid.innerHTML = '';
    if (filteredProducts.length === 0) {
        grid.innerHTML = '<div style="grid-column: 1/-1; text-align:center; padding: 40px; color: var(--text-muted);">No se encontraron productos en esta categoria.</div>';
        return;
    }

    filteredProducts.forEach(p => {
        const card = document.createElement('article');
        card.className = 'product-card';

        const imgUrlRaw = p.img_url || '';
        const imgUrl = (imgUrlRaw.startsWith('/') && !imgUrlRaw.startsWith('http')) ? (API + imgUrlRaw) : imgUrlRaw;
        const fallbackChar = p.nombre_producto.charAt(0).toUpperCase();
        const imageHtml = imgUrl
            ? '<img src="' + imgUrl + '" class="product-img" alt="' + p.nombre_producto + '">'
            : '<div style="width:100%;height:100%;background:var(--primary-light);display:flex;align-items:center;justify-content:center;font-size:40px;font-weight:700;color:var(--primary);font-family:\'Playfair Display\',serif;">' + fallbackChar + '</div>';

        const brandName = p.id_marcas ? p.id_marcas.nombre_marca : 'Bellarista';
        const tagHtml = p.etiqueta_especial ? '<span class="product-tag">' + p.etiqueta_especial + '</span>' : '';
        const desc = p.descripcion || 'Sin descripción';
        const price = p.precio_venta != null ? parseFloat(p.precio_venta).toFixed(2) : '0.00';

        const stockAct = p.stock_actual != null ? Number(p.stock_actual) : 0;
        const stockMin = p.stock_minimo != null ? Number(p.stock_minimo) : 10;

        let stockStatusHtml = '';
        let cartBtnHtml = '';

        if (stockAct <= 0) {
            stockStatusHtml = '<span style="font-size: 11px; font-weight: 700; color: #dc2626; margin-top: 4px; display: block;">⚠️ Agotado</span>';
            cartBtnHtml = '<button class="add-to-cart-btn" style="background:#d1d5db;cursor:not-allowed;" disabled title="Sin stock disponible">'
                + '<svg viewBox="0 0 24 24"><path d="M18 6L6 18M6 6l12 12" stroke="white" stroke-width="2" stroke-linecap="round"/></svg>'
                + '</button>';
        } else {
            if (stockAct <= stockMin) {
                stockStatusHtml = '<span style="font-size: 11px; font-weight: 600; color: #d97706; margin-top: 4px; display: block;">⚠️ ¡Pocas unidades! (Quedan ' + stockAct + ')</span>';
            } else {
                stockStatusHtml = '<span style="font-size: 11px; color: var(--text-muted); margin-top: 4px; display: block;">Stock disponible: ' + stockAct + '</span>';
            }
            cartBtnHtml = '<button class="add-to-cart-btn" onclick="addToCart(' + p.id_productos + ')">'
                + '<svg viewBox="0 0 24 24"><path d="M12 5v14M5 12h14" stroke-linecap="round" stroke-linejoin="round"/></svg>'
                + '</button>';
        }

        card.innerHTML = '<div class="product-img-wrap">' + imageHtml + tagHtml + '</div>'
            + '<div class="product-card-body">'
            + '<span class="product-brand">' + brandName + '</span>'
            + '<h3 class="product-title">' + p.nombre_producto + '</h3>'
            + '<p class="product-desc">' + desc + '</p>'
            + stockStatusHtml
            + '<div class="product-card-footer" style="margin-top: 12px;">'
            + '<span class="product-price">S/ ' + price + '</span>'
            + cartBtnHtml
            + '</div>'
            + '</div>';
        grid.appendChild(card);
    });
}

function renderCombos(combos) {
    allCombos = combos;
    const section = document.getElementById('combosSection');
    const grid = document.getElementById('combosGrid');
    if (!grid || !section) return;

    if (!combos || combos.length === 0) {
        section.style.display = 'none';
        return;
    }

    section.style.display = 'block';
    grid.innerHTML = '';

    combos.forEach(c => {
        const card = document.createElement('article');
        card.className = 'product-card combo-card';
        card.style.border = '2px solid var(--primary-light)';
        card.style.position = 'relative';

        const fallbackChar = c.nombre_promocion.charAt(0).toUpperCase();
        const imageHtml = '<div style="width:100%;height:100%;background:linear-gradient(135deg, var(--primary-light) 0%, #fae8ff 100%);display:flex;flex-direction:column;align-items:center;justify-content:center;font-family:\'Playfair Display\',serif;padding: 20px;text-align:center;">'
            + '<span style="font-size:32px;font-weight:700;color:var(--primary);">' + fallbackChar + '</span>'
            + '<span style="font-size:12px;text-transform:uppercase;letter-spacing:1px;color:var(--primary);margin-top:8px;font-weight:600;font-family:\'Plus Jakarta Sans\',sans-serif;">Combo Pack</span>'
            + '</div>';

        const tagHtml = '<span class="product-tag" style="background:#6366f1;">Ahorro</span>';
        const desc = c.descripcion || 'Sin descripción';
        const promoPrice = c.precio_combo != null ? parseFloat(c.precio_combo).toFixed(2) : '0.00';
        const origPrice = c.precio_original != null ? parseFloat(c.precio_original).toFixed(2) : null;

        let priceHtml = '<span class="product-price">S/ ' + promoPrice + '</span>';
        if (origPrice) {
            priceHtml = '<div style="display:flex;flex-direction:column;gap:2px;">'
                + '<span class="product-price" style="color:var(--primary);">S/ ' + promoPrice + '</span>'
                + '<span style="font-size:12px;text-decoration:line-through;color:var(--text-muted);">Antes: S/ ' + origPrice + '</span>'
                + '</div>';
        }

        const cartBtnHtml = '<button class="add-to-cart-btn" onclick="addComboToCart(' + c.id_combos_promocionales + ')" style="background:#6366f1;">'
            + '<svg viewBox="0 0 24 24"><path d="M12 5v14M5 12h14" stroke-linecap="round" stroke-linejoin="round"/></svg>'
            + '</button>';

        card.innerHTML = '<div class="product-img-wrap">' + imageHtml + tagHtml + '</div>'
            + '<div class="product-card-body">'
            + '<span class="product-brand" style="color:#6366f1;font-weight:600;">COMBO PROMOCIONAL</span>'
            + '<h3 class="product-title">' + c.nombre_promocion + '</h3>'
            + '<p class="product-desc">' + desc + '</p>'
            + '<div id="combo-details-' + c.id_combos_promocionales + '" style="font-size:11px;color:var(--text-muted);margin-top:6px;min-height:20px;">Cargando productos...</div>'
            + '<div class="product-card-footer" style="margin-top: 12px;align-items:flex-end;">'
            + priceHtml
            + cartBtnHtml
            + '</div>'
            + '</div>';
        grid.appendChild(card);

        fetch(API + '/tienda/api/combos/' + c.id_combos_promocionales + '/productos')
            .then(r => r.json())
            .then(ccList => {
                const detailsDiv = document.getElementById('combo-details-' + c.id_combos_promocionales);
                if (detailsDiv && ccList.length > 0) {
                    const text = ccList.map(cc => {
                        const prodName = cc.id_productos ? cc.id_productos.nombre_producto : 'Producto';
                        return cc.cantidad + 'x ' + prodName;
                    }).join(', ');
                    detailsDiv.innerHTML = '<strong style="color:var(--text-dark);">Incluye:</strong> ' + text;
                } else if (detailsDiv) {
                    detailsDiv.innerHTML = '';
                }
            })
            .catch(err => console.error(err));
    });
}

function addComboToCart(comboId) {
    const combo = allCombos.find(c => c.id_combos_promocionales === comboId);
    if (!combo) return;

    const existing = cart.find(item => item.id_combos_promocionales === comboId && item.tipo === 'combo');
    if (existing) {
        existing.cantidad += 1;
    } else {
        cart.push({
            id_combos_promocionales: combo.id_combos_promocionales,
            tipo: 'combo',
            nombre_producto: combo.nombre_promocion,
            precio_venta: combo.precio_combo || 0.00,
            img_url: '',
            cantidad: 1
        });
    }
    saveCart();
    toggleCart(true);
}

// Cart Drawer Operations
function toggleCart(open) {
    document.getElementById('cartOverlay').classList.toggle('open', open);
}

function loadCart() {
    try {
        cart = JSON.parse(localStorage.getItem('bellarista_cart') || '[]');
    } catch (e) {
        cart = [];
    }
    updateCartUI();
}

function saveCart() {
    localStorage.setItem('bellarista_cart', JSON.stringify(cart));
    updateCartUI();
}

function addToCart(prodId) {
    const prod = allProducts.find(p => p.id_productos === prodId);
    if (!prod) return;

    const existing = cart.find(item => item.id_productos === prodId && item.tipo !== 'servicio');
    const stockAct = prod.stock_actual != null ? Number(prod.stock_actual) : 0;
    const currentQty = existing ? existing.cantidad : 0;

    if (currentQty >= stockAct) {
        alert('Lo sentimos, no hay más unidades disponibles de ' + prod.nombre_producto + ' (Stock actual: ' + stockAct + ').');
        return;
    }

    if (existing) {
        existing.cantidad += 1;
    } else {
        cart.push({
            id_productos: prod.id_productos,
            tipo: 'producto',
            nombre_producto: prod.nombre_producto,
            precio_venta: prod.precio_venta,
            img_url: prod.img_url,
            cantidad: 1
        });
    }
    saveCart();
    toggleCart(true);
}

function addToCartService(serviceId) {
    const serv = allServices.find(s => s.id_servicios_belleza === serviceId);
    if (!serv) return;

    const existing = cart.find(item => item.id_servicios_belleza === serviceId && item.tipo === 'servicio');
    if (existing) {
        alert('Este servicio ya se encuentra en su carrito de reservas.');
        return;
    }

    cart.push({
        id_servicios_belleza: serv.id_servicios_belleza,
        tipo: 'servicio',
        nombre_producto: serv.nombre_servicio_belleza,
        precio_venta: serv.precio_base,
        img_url: '',
        cantidad: 1,
        duracion: serv.duracion_minima || 60
    });

    saveCart();
    toggleCart(true);
}

function updateCartQuantity(itemId, delta, tipo = 'producto') {
    const item = cart.find(i => {
        if (tipo === 'servicio') return i.id_servicios_belleza === itemId && i.tipo === 'servicio';
        if (tipo === 'combo') return i.id_combos_promocionales === itemId && i.tipo === 'combo';
        return i.id_productos === itemId && i.tipo === 'producto';
    });
    if (!item) return;

    if (delta > 0 && tipo === 'producto') {
        const prod = allProducts.find(p => p.id_productos === itemId);
        const stockAct = prod && prod.stock_actual != null ? Number(prod.stock_actual) : 999;
        if (item.cantidad >= stockAct) {
            alert('No se pueden agregar más unidades. El stock máximo disponible es ' + stockAct + '.');
            return;
        }
    }

    if (tipo === 'servicio' && delta > 0) {
        alert('Solo puede reservar un turno de este servicio a la vez.');
        return;
    }

    item.cantidad += delta;
    if (item.cantidad <= 0) {
        removeFromCart(itemId, tipo);
        return;
    }
    saveCart();
}

function removeFromCart(itemId, tipo = 'producto') {
    if (tipo === 'servicio') {
        cart = cart.filter(i => !(i.id_servicios_belleza === itemId && i.tipo === 'servicio'));
    } else if (tipo === 'combo') {
        cart = cart.filter(i => !(i.id_combos_promocionales === itemId && i.tipo === 'combo'));
    } else {
        cart = cart.filter(i => !(i.id_productos === itemId && i.tipo === 'producto'));
    }
    saveCart();
}

function updateCartUI() {
    const container = document.getElementById('cartItems');
    const countBadge = document.getElementById('cartCount');

    if (!container || !countBadge) return;

    const totalCount = cart.reduce((sum, item) => sum + item.cantidad, 0);
    countBadge.textContent = totalCount;

    container.innerHTML = '';
    if (cart.length === 0) {
        container.innerHTML = '<p class="empty-cart-msg">Tu carrito está vacío</p>';
        document.getElementById('cartSubtotal').textContent = 'S/ 0.00';
        document.getElementById('cartTotal').textContent = 'S/ 0.00';
        return;
    }

    let subtotal = 0.0;
    cart.forEach(item => {
        subtotal += item.cantidad * item.precio_venta;
        const div = document.createElement('div');
        div.className = 'cart-item';

        const imgUrlRaw = item.img_url || '';
        const imgUrl = (imgUrlRaw.startsWith('/') && !imgUrlRaw.startsWith('http')) ? (API + imgUrlRaw) : imgUrlRaw;
        const fallbackChar = item.nombre_producto.charAt(0).toUpperCase();
        const imageHtml = imgUrl
            ? '<img src="' + imgUrl + '" class="cart-item-img" alt="' + item.nombre_producto + '">'
            : '<div class="cart-item-img" style="display:flex;align-items:center;justify-content:center;background:var(--primary-light);font-size:24px;font-weight:700;color:var(--primary);font-family:\'Playfair Display\',serif;">' + fallbackChar + '</div>';

        const itemId = item.tipo === 'servicio' ? item.id_servicios_belleza : (item.tipo === 'combo' ? item.id_combos_promocionales : item.id_productos);
        const itemTipo = item.tipo || 'producto';

        div.innerHTML = imageHtml
            + '<div class="cart-item-info">'
            + '<h4>' + item.nombre_producto + '</h4>'
            + '<p>S/ ' + parseFloat(item.precio_venta).toFixed(2) + ' c/u ' + (item.tipo === 'servicio' ? '<span style="font-size:9px;color:var(--primary);font-weight:600;text-transform:uppercase;">[Servicio]</span>' : (item.tipo === 'combo' ? '<span style="font-size:9px;color:#6366f1;font-weight:600;text-transform:uppercase;">[Combo]</span>' : '')) + '</p>'
            + '<div class="cart-item-actions">'
            + '<div class="quantity-control">'
            + '<button onclick="updateCartQuantity(' + itemId + ', -1, \'' + itemTipo + '\')">-</button>'
            + '<span>' + item.cantidad + '</span>'
            + '<button onclick="updateCartQuantity(' + itemId + ', 1, \'' + itemTipo + '\')">+</button>'
            + '</div>'
            + '<button class="remove-item-btn" onclick="removeFromCart(' + itemId + ', \'' + itemTipo + '\')">Quitar</button>'
            + '</div>'
            + '</div>';
        container.appendChild(div);
    });

    document.getElementById('cartSubtotal').textContent = 'S/ ' + subtotal.toFixed(2);
    document.getElementById('cartTotal').textContent = 'S/ ' + subtotal.toFixed(2);
}

function goToCheckout() {
    if (cart.length === 0) {
        alert('Su carrito está vacío.');
        return;
    }
    window.location.href = `/tienda_checkout.html?tenantId=${tenantId}`;
}

// Tab Switching
function switchTab(tab, element) {
    document.querySelectorAll('.nav-menu .nav-link').forEach(l => l.classList.remove('active'));
    element.classList.add('active');

    document.getElementById('storeSection').style.display = 'none';
    document.getElementById('servicesSection').style.display = 'none';
    const historySec = document.getElementById('historySection');
    if (historySec) historySec.style.display = 'none';
    const contactSec = document.getElementById('contactSection');
    if (contactSec) contactSec.style.display = 'none';

    const heroTitle = document.getElementById('heroTitle');
    const heroDesc = document.getElementById('heroDesc');

    if (tab === 'store') {
        document.getElementById('storeSection').style.display = 'block';
        heroTitle.textContent = 'Encuentra los mejores productos para tu estilo y cuidado personal';
        heroDesc.textContent = 'Una cuidada selección de cremas, esmaltes, tratamientos y accesorios premium recomendados por nuestros expertos.';
    } else if (tab === 'services') {
        document.getElementById('servicesSection').style.display = 'block';
        heroTitle.textContent = 'Tratamientos y Servicios de Belleza Exclusivos';
        heroDesc.textContent = 'Reserva cortes de cabello, colorimetría, manicura y tratamientos capilares avanzados con nuestros estilistas certificados.';
        renderServices();
    } else if (tab === 'history') {
        if (historySec) {
            historySec.style.display = 'block';
            heroTitle.textContent = 'Mi Actividad y Estado de Pedidos';
            heroDesc.textContent = 'Sigue el estado de tus compras y administra el calendario de tus próximas visitas al salón de belleza.';
            renderHistory();
        }
    } else if (tab === 'contact') {
        if (contactSec) {
            contactSec.style.display = 'block';
            heroTitle.textContent = 'Estamos para Ayudarte';
            heroDesc.textContent = 'Ponte en contacto con nuestro equipo de atención o envíanos tus comentarios directamente.';
        }
    }
}

// Render Services List
function renderServices() {
    const grid = document.getElementById('servicesGrid');
    grid.innerHTML = '';
    if (allServices.length === 0) {
        grid.innerHTML = '<div style="grid-column: 1/-1; text-align:center; padding: 40px; color: var(--text-muted);">No hay servicios de belleza disponibles en este momento.</div>';
        return;
    }

    allServices.forEach(s => {
        const card = document.createElement('article');
        card.className = 'product-card';

        const desc = s.descripcion || 'Sin descripción';
        const price = s.precio_base != null ? parseFloat(s.precio_base).toFixed(2) : '0.00';
        const duration = s.duracion_minima ? (s.duracion_minima + ' min') : '45 min';
        const fallbackChar = s.nombre_servicio_belleza.charAt(0).toUpperCase();

        card.innerHTML = '<div class="product-img-wrap" style="background: linear-gradient(135deg, #fdfbf7 0%, #f5efe6 100%); aspect-ratio: 16/10;">'
            + '<div style="width:100%;height:100%;display:flex;flex-direction:column;align-items:center;justify-content:center;color:var(--primary-dark);font-family:\'Playfair Display\',serif;">'
            + '<span style="font-size:36px;font-weight:600;opacity:0.8;">' + fallbackChar + '</span>'
            + '<span style="font-size:10px;text-transform:uppercase;letter-spacing:2px;margin-top:6px;font-family:\'Plus Jakarta Sans\',sans-serif;font-weight:600;opacity:0.6;">Estética</span>'
            + '</div></div>'
            + '<div class="product-card-body" style="padding: 20px;">'
            + '<span class="product-brand" style="color:var(--primary-dark);">Bellarista Spa</span>'
            + '<h3 class="product-title" style="min-height:auto;margin-bottom:8px;font-size:16px;">' + s.nombre_servicio_belleza + '</h3>'
            + '<p class="product-desc" style="margin-bottom:12px;">' + desc + '</p>'
            + '<div style="display:flex;align-items:center;gap:6px;font-size:11px;color:var(--text-muted);margin-bottom:16px;">'
            + '<svg viewBox="0 0 24 24" style="width:14px;height:14px;fill:none;stroke:currentColor;stroke-width:2;stroke-linecap:round;stroke-linejoin:round;"><circle cx="12" cy="12" r="10"></circle><polyline points="12 6 12 12 16 14"></polyline></svg>'
            + '<span>Duración: ' + duration + '</span>'
            + '</div>'
            + '<div class="product-card-footer" style="margin-top:auto;border-top:1px solid rgba(0,0,0,0.04);padding-top:12px;">'
            + '<div><span style="font-size:9px;text-transform:uppercase;color:var(--text-muted);display:block;letter-spacing:0.5px;">Precio Base</span>'
            + '<span class="product-price" style="font-size:16px;">S/ ' + price + '</span></div>'
            + '<button onclick="addToCartService(' + s.id_servicios_belleza + ')" class="btn-filled" style="padding:6px 12px;font-size:11px;border-radius:99px;display:flex;align-items:center;gap:4px;border:none;cursor:pointer;">Reservar Cita</button>'
            + '</div></div>';
        grid.appendChild(card);
    });
}

// Render History List
let ventasData = [];
let citasData = [];
let currentVentasPage = 1;
let currentCitasPage = 1;
const itemsPerPage = 5;

// Render History List
function renderHistory() {
    const ventasList = document.getElementById('ventasHistoryList');
    const citasList = document.getElementById('citasHistoryList');

    ventasList.innerHTML = '<div style="text-align:center;color:var(--text-muted);padding:20px;font-size:13px;">Cargando compras...</div>';
    citasList.innerHTML = '<div style="text-align:center;color:var(--text-muted);padding:20px;font-size:13px;">Cargando citas...</div>';

    const cliente = JSON.parse(localStorage.getItem('cliente') || 'null');
    const clienteId = cliente ? cliente.id_clientes : '';
    fetch(API + '/tienda/api/historial?tenantIdParam=' + tenantId + '&clienteId=' + clienteId)
        .then(r => r.json())
        .then(data => {
            if (!data.success) {
                ventasList.innerHTML = '<div style="text-align:center;color:red;padding:20px;font-size:13px;">' + data.error + '</div>';
                citasList.innerHTML = '<div style="text-align:center;color:red;padding:20px;font-size:13px;">' + data.error + '</div>';
                return;
            }

            // Sort by date descending (recent purchases/appointments first)
            ventasData = (data.ventas || []).sort((a, b) => {
                const dateA = a.fecha_venta ? new Date(a.fecha_venta) : new Date(0);
                const dateB = b.fecha_venta ? new Date(b.fecha_venta) : new Date(0);
                return dateB - dateA;
            });

            citasData = (data.citas || []).sort((a, b) => {
                const dateA = a.fecha_cita ? new Date(a.fecha_cita) : new Date(0);
                const dateB = b.fecha_cita ? new Date(b.fecha_cita) : new Date(0);
                return dateB - dateA;
            });

            currentVentasPage = 1;
            currentCitasPage = 1;

            renderVentasHistoryPage();
            renderCitasHistoryPage();
        })
        .catch(err => {
            console.error('Error al cargar historial:', err);
            ventasList.innerHTML = '<div style="text-align:center;color:red;padding:20px;font-size:13px;">Error al cargar.</div>';
            citasList.innerHTML = '<div style="text-align:center;color:red;padding:20px;font-size:13px;">Error al cargar.</div>';
        });
}

function renderVentasHistoryPage() {
    const list = document.getElementById('ventasHistoryList');
    const pag = document.getElementById('ventasPagination');
    list.innerHTML = '';
    pag.innerHTML = '';

    if (ventasData.length === 0) {
        list.innerHTML = '<div style="text-align:center;color:var(--text-muted);padding:20px;font-weight:300;font-size:13px;">No tienes compras registradas.</div>';
        return;
    }

    const totalPages = Math.ceil(ventasData.length / itemsPerPage);
    const start = (currentVentasPage - 1) * itemsPerPage;
    const end = start + itemsPerPage;
    const pageItems = ventasData.slice(start, end);

    pageItems.forEach(v => {
        const dateStr = v.fecha_venta ? new Date(v.fecha_venta).toLocaleDateString() : '-';
        const ticket = v.numero_ticket || v.comprobante_numero || 'T-N/A';
        const statusLabel = v.estado_sunat ? v.estado_sunat.toUpperCase() : 'PENDIENTE';
        const totalVal = v.total != null ? parseFloat(v.total).toFixed(2) : '0.00';

        let statusClass = 'status-badge-pending';
        if (v.estado_sunat === 'aceptada') statusClass = 'status-badge-success';
        else if (v.estado_sunat === 'rechazada') statusClass = 'status-badge-danger';

        const itemDiv = document.createElement('div');
        itemDiv.className = 'history-item';
        itemDiv.style.background = 'var(--surface)';
        itemDiv.style.border = '1px solid #f1f5f9';
        itemDiv.style.borderRadius = '12px';
        itemDiv.style.padding = '16px';
        itemDiv.style.boxShadow = '0 2px 8px rgba(0,0,0,0.02)';
        itemDiv.style.transition = 'transform 0.2s, box-shadow 0.2s';
        itemDiv.style.cursor = 'pointer';

        itemDiv.innerHTML = `
            <div style="display:flex;justify-content:space-between;align-items:center;margin-bottom:10px;">
                <div>
                    <strong style="font-size:15px;color:var(--dark);font-weight:600;">${ticket}</strong>
                    <span style="font-size:12px;color:var(--text-muted);margin-left:8px;font-weight:300;">${dateStr}</span>
                </div>
                <span class="status-badge ${statusClass}" style="font-size:10px;padding:3px 8px;border-radius:12px;font-weight:600;">${statusLabel}</span>
            </div>
            <div style="display:flex;justify-content:space-between;align-items:center;font-size:13px;">
                <span style="color:var(--text-muted);font-weight:300;">Comprobante: <span style="font-weight:500;color:var(--dark);">${v.tipo_comprobante || 'boleta'}</span></span>
                <div style="display:flex;align-items:center;gap:12px;">
                    <button onclick="generarBoletaDesdeHistorial(${v.id_ventas})" class="btn-filled" 
                            style="padding:5px 12px;font-size:11px;border-radius:99px;background:var(--primary);border:none;cursor:pointer;color:white;font-weight:500;transition:opacity 0.2s;"
                            onmouseover="this.style.opacity=0.9" onmouseout="this.style.opacity=1">📄 PDF</button>
                    <strong style="font-size:16px;color:var(--dark);font-weight:600;">S/ ${totalVal}</strong>
                </div>
            </div>
        `;
        list.appendChild(itemDiv);
    });

    renderPaginationButtons(pag, currentVentasPage, totalPages, (page) => {
        currentVentasPage = page;
        renderVentasHistoryPage();
    });
}

function renderCitasHistoryPage() {
    const list = document.getElementById('citasHistoryList');
    const pag = document.getElementById('citasPagination');
    list.innerHTML = '';
    pag.innerHTML = '';

    if (citasData.length === 0) {
        list.innerHTML = '<div style="text-align:center;color:var(--text-muted);padding:20px;font-weight:300;font-size:13px;">No tienes reservas de citas de belleza.</div>';
        return;
    }

    const totalPages = Math.ceil(citasData.length / itemsPerPage);
    const start = (currentCitasPage - 1) * itemsPerPage;
    const end = start + itemsPerPage;
    const pageItems = citasData.slice(start, end);

    pageItems.forEach(c => {
        const dateStr = c.fecha_cita ? new Date(c.fecha_cita + 'T00:00:00').toLocaleDateString() : '-';
        const startStr = c.hora_inicio ? c.hora_inicio.substring(0, 5) : '';
        const endStr = c.hora_fin ? c.hora_fin.substring(0, 5) : '';
        const timeStr = startStr + ' - ' + endStr;
        const duration = c.duracion_minutos ? (c.duracion_minutos + ' min') : '60 min';
        const sedeName = c.id_sedes ? c.id_sedes.nombre_sede : 'Sede Principal';

        let statusLabel = 'CONFIRMADA';
        let statusClass = 'status-badge-success';
        if (c.estado === 0) {
            statusLabel = 'CANCELADA';
            statusClass = 'status-badge-danger';
        }

        const itemDiv = document.createElement('div');
        itemDiv.className = 'history-item';
        itemDiv.style.background = 'var(--surface)';
        itemDiv.style.border = '1px solid #f1f5f9';
        itemDiv.style.borderRadius = '12px';
        itemDiv.style.padding = '16px';
        itemDiv.style.boxShadow = '0 2px 8px rgba(0,0,0,0.02)';
        itemDiv.style.transition = 'transform 0.2s, box-shadow 0.2s';

        itemDiv.innerHTML = `
            <div style="display:flex;justify-content:space-between;align-items:center;margin-bottom:10px;">
                <div>
                    <strong style="font-size:15px;color:var(--dark);font-weight:600;">${dateStr}</strong>
                    <span style="font-size:12px;color:var(--text-muted);margin-left:8px;font-weight:300;">${timeStr}</span>
                </div>
                <span class="status-badge ${statusClass}" style="font-size:10px;padding:3px 8px;border-radius:12px;font-weight:600;">${statusLabel}</span>
            </div>
            <div style="display:flex;justify-content:space-between;align-items:center;font-size:13px;margin-bottom:8px;">
                <span style="color:var(--text-muted);font-weight:300;">Sede: <span style="font-weight:500;color:var(--dark);">${sedeName}</span></span>
                <span style="color:var(--text-muted);font-weight:300;">Duración: <span style="font-weight:500;color:var(--dark);">${duration}</span></span>
            </div>
            <div style="display:flex;justify-content:space-between;align-items:center;">
                <div style="font-size:12px;color:var(--text-muted);font-style:italic;font-weight:300;">Obs: ${c.observaciones || 'Sin observaciones'}</div>
                <button onclick="generarComprobanteCita(${c.id_citas})" class="btn-filled" 
                        style="padding:5px 12px;font-size:11px;border-radius:99px;background:var(--primary);border:none;cursor:pointer;color:white;font-weight:500;transition:opacity 0.2s;"
                        onmouseover="this.style.opacity=0.9" onmouseout="this.style.opacity=1">📄 PDF</button>
            </div>
        `;
        list.appendChild(itemDiv);
    });

    renderPaginationButtons(pag, currentCitasPage, totalPages, (page) => {
        currentCitasPage = page;
        renderCitasHistoryPage();
    });
}

function renderPaginationButtons(container, currentPage, totalPages, onPageClick) {
    if (totalPages <= 1) return;

    // Prev Button
    const prevBtn = document.createElement('button');
    prevBtn.textContent = '◀';
    prevBtn.disabled = currentPage === 1;
    prevBtn.style.padding = '6px 12px';
    prevBtn.style.borderRadius = '8px';
    prevBtn.style.border = '1px solid #e2e8f0';
    prevBtn.style.background = 'white';
    prevBtn.style.fontSize = '12px';
    prevBtn.style.cursor = currentPage === 1 ? 'not-allowed' : 'pointer';
    prevBtn.style.opacity = currentPage === 1 ? '0.5' : '1';
    prevBtn.style.transition = 'all 0.2s';
    if (currentPage !== 1) {
        prevBtn.addEventListener('mouseover', () => prevBtn.style.background = '#f8fafc');
        prevBtn.addEventListener('mouseout', () => prevBtn.style.background = 'white');
    }
    prevBtn.addEventListener('click', () => onPageClick(currentPage - 1));
    container.appendChild(prevBtn);

    // Page Numbers
    for (let i = 1; i <= totalPages; i++) {
        const btn = document.createElement('button');
        btn.textContent = i;
        btn.style.padding = '6px 12px';
        btn.style.borderRadius = '8px';
        btn.style.border = '1px solid #e2e8f0';
        btn.style.fontSize = '12px';
        btn.style.cursor = 'pointer';
        btn.style.fontWeight = '600';
        btn.style.transition = 'all 0.2s';
        if (i === currentPage) {
            btn.style.background = 'var(--primary)';
            btn.style.color = 'white';
            btn.style.borderColor = 'var(--primary)';
        } else {
            btn.style.background = 'white';
            btn.style.color = 'var(--dark)';
            btn.addEventListener('mouseover', () => btn.style.background = '#f8fafc');
            btn.addEventListener('mouseout', () => btn.style.background = 'white');
        }
        btn.addEventListener('click', () => onPageClick(i));
        container.appendChild(btn);
    }

    // Next Button
    const nextBtn = document.createElement('button');
    nextBtn.textContent = '▶';
    nextBtn.disabled = currentPage === totalPages;
    nextBtn.style.padding = '6px 12px';
    nextBtn.style.borderRadius = '8px';
    nextBtn.style.border = '1px solid #e2e8f0';
    nextBtn.style.background = 'white';
    nextBtn.style.fontSize = '12px';
    nextBtn.style.cursor = currentPage === totalPages ? 'not-allowed' : 'pointer';
    nextBtn.style.opacity = currentPage === totalPages ? '0.5' : '1';
    nextBtn.style.transition = 'all 0.2s';
    if (currentPage !== totalPages) {
        nextBtn.addEventListener('mouseover', () => nextBtn.style.background = '#f8fafc');
        nextBtn.addEventListener('mouseout', () => nextBtn.style.background = 'white');
    }
    nextBtn.addEventListener('click', () => onPageClick(currentPage + 1));
    container.appendChild(nextBtn);
}

// PDF Generator — from history page
function generarBoletaDesdeHistorial(ventaId) {
    if (!ventaId) return;
    generarBoletaPDFComun(ventaId);
}

// Shared PDF generation logic
function generarBoletaPDFComun(ventaId) {
    Promise.all([
        fetch(API + '/tienda/api/venta/' + ventaId).then(r => r.json()),
        fetch(API + '/tienda/api/detalles-venta/' + ventaId).then(r => r.json())
    ])
    .then(([res, detalles]) => {
        if (!res || !res.success || !res.venta) {
            alert('No se encontró la venta. Intente nuevamente.');
            return;
        }
        const venta = res.venta;

        const { jsPDF } = window.jspdf;
        const doc = new jsPDF();

        doc.setFont('Helvetica', 'bold');
        doc.setFontSize(20);
        doc.setTextColor(30, 30, 36);
        doc.text('BELLARISTA SALON & BOUTIQUE', 14, 20);

        doc.setFont('Helvetica', 'normal');
        doc.setFontSize(9);
        doc.setTextColor(117, 117, 119);
        doc.text('RUC: 20601234561', 14, 26);
        doc.text('Dirección: Av. La Marina 123, San Miguel, Lima', 14, 31);
        doc.text('Teléfono: 01-3456789 | contacto@bellarista.pe', 14, 36);

        doc.setDrawColor(197, 168, 128);
        doc.setFillColor(245, 239, 230);
        doc.rect(130, 12, 65, 26, 'FD');

        doc.setFont('Helvetica', 'bold');
        doc.setFontSize(12);
        doc.setTextColor(176, 145, 104);
        doc.text('BOLETA DE VENTA', 135, 18);
        doc.text('ELECTRONICA', 142, 23);

        const ticketNum = venta.numero_ticket || ('TK-' + venta.id_ventas);
        doc.setFontSize(11);
        doc.setTextColor(30, 30, 36);
        doc.text(ticketNum, 142, 31);

        doc.setDrawColor(230, 230, 233);
        doc.line(14, 45, 195, 45);

        doc.setFont('Helvetica', 'bold');
        doc.setFontSize(10);
        doc.text('DATOS DEL CLIENTE', 14, 52);

        doc.setFont('Helvetica', 'normal');
        doc.setFontSize(9);
        const cli = venta.id_clientes || {};
        const cliNombre = cli.nombre_cliente ? (cli.nombre_cliente + ' ' + (cli.apellidos_clientes || '')) : 'Cliente General';
        const cliDoc = cli.numero_documento ? ((cli.tipo_documento || 'DOC') + ': ' + cli.numero_documento) : 'Publico General';
        const cliDir = cli.direccion ? (cli.direccion + ', ' + (cli.distrito || '')) : '-';
        const fecha = venta.fecha_venta ? new Date(venta.fecha_venta).toLocaleString() : new Date().toLocaleString();

        doc.text('Cliente: ' + cliNombre, 14, 58);
        doc.text('Documento: ' + cliDoc, 14, 63);
        doc.text('Direccion: ' + cliDir, 14, 68);
        doc.text('Fecha Emision: ' + fecha, 120, 58);
        doc.text('Metodo Pago: Pago Electronico', 120, 63);

        const headers = [['Item', 'Producto / Servicio', 'Cant.', 'Precio Unit.', 'Total']];
        const rows = detalles.map((d, index) => [
            index + 1,
            d.nombre_producto || 'Producto/Servicio',
            d.cantidad || 0,
            'S/ ' + parseFloat(d.precio_unitario || 0).toFixed(2),
            'S/ ' + parseFloat(d.subtotal || 0).toFixed(2)
        ]);

        doc.autoTable({
            head: headers,
            body: rows,
            startY: 75,
            theme: 'striped',
            headStyles: { fillColor: [30, 30, 36], textColor: [255, 255, 255] },
            alternateRowStyles: { fillColor: [250, 250, 252] },
            styles: { font: 'Helvetica', fontSize: 9 }
        });

        const finalY = doc.lastAutoTable.finalY + 10;

        doc.setFont('Helvetica', 'normal');
        doc.setFontSize(9);
        const subVal = venta.subtotal != null ? parseFloat(venta.subtotal).toFixed(2) : '0.00';
        const igvVal = venta.impuesto != null ? parseFloat(venta.impuesto).toFixed(2) : '0.00';
        const totVal = venta.total != null ? parseFloat(venta.total).toFixed(2) : '0.00';

        doc.text('Subtotal:', 140, finalY);
        doc.text('S/ ' + subVal, 185, finalY, { align: 'right' });
        doc.text('IGV (18%):', 140, finalY + 5);
        doc.text('S/ ' + igvVal, 185, finalY + 5, { align: 'right' });

        doc.setFont('Helvetica', 'bold');
        doc.setFontSize(10);
        doc.text('Total General:', 140, finalY + 12);
        doc.text('S/ ' + totVal, 185, finalY + 12, { align: 'right' });

        doc.setFont('Helvetica', 'normal');
        doc.setFontSize(8);
        doc.setTextColor(117, 117, 119);
        doc.text('Esta es una representacion impresa de la boleta de venta electronica generada en Bellarista Storefront.', 14, finalY + 30);
        doc.text('Autorizado por SUNAT. Muchas gracias por su preferencia!', 14, finalY + 35);

        // Abrir PDF en nueva pestaña
        const pdfBlob = doc.output('blob');
        const pdfUrl = URL.createObjectURL(pdfBlob);
        window.open(pdfUrl, '_blank');
    })
    .catch(err => {
        console.error('Error al generar PDF:', err);
        alert('Ocurrio un error al obtener la boleta del servidor.');
    });
}

function submitContactForm(e) {
    e.preventDefault();
    const name = document.getElementById('contactName').value;
    const email = document.getElementById('contactEmail').value;
    const subject = document.getElementById('contactSubject').value;
    const message = document.getElementById('contactMessage').value;

    const body = {
        nombre: name,
        correo: email,
        asunto: subject,
        mensaje: message
    };

    fetch(API + '/tienda/api/contacto', {
        method: 'POST',
        headers: { 'Content-Type': 'application/json' },
        body: JSON.stringify(body)
    })
    .then(res => res.json())
    .then(data => {
        if (data.success) {
            alert('¡Gracias! Tu mensaje ha sido enviado con éxito. Nos pondremos en contacto contigo pronto.');
            document.getElementById('contactForm').reset();
        } else {
            alert('Error al enviar el mensaje: ' + (data.error || 'Intente nuevamente'));
        }
    })
    .catch(err => {
        console.error('Error:', err);
        alert('Ocurrió un error al enviar el mensaje.');
    });
}

function generarComprobanteCita(citaId) {
    if (!citaId) return;
    Promise.all([
        fetch(API + '/api/citas/' + citaId).then(r => r.json()),
        fetch(API + '/api/servicio_cita').then(r => r.json())
    ])
    .then(([cita, allServicios]) => {
        if (!cita) {
            alert('No se encontró la cita. Intente nuevamente.');
            return;
        }
        
        const detalles = (allServicios || []).filter(sc => sc.id_citas && (sc.id_citas.id_citas === citaId || sc.id_citas === citaId));

        const { jsPDF } = window.jspdf;
        const doc = new jsPDF();

        doc.setFont('Helvetica', 'bold');
        doc.setFontSize(20);
        doc.setTextColor(30, 30, 36);
        doc.text('BELLARISTA SALON & BOUTIQUE', 14, 20);

        doc.setFont('Helvetica', 'normal');
        doc.setFontSize(9);
        doc.setTextColor(117, 117, 119);
        doc.text('RUC: 20601234561', 14, 26);
        doc.text('Dirección: Av. La Marina 123, San Miguel, Lima', 14, 31);
        doc.text('Teléfono: 01-3456789 | contacto@bellarista.pe', 14, 36);

        // Gold title card for CITA
        doc.setDrawColor(197, 168, 128);
        doc.setFillColor(245, 239, 230);
        doc.rect(125, 12, 70, 26, 'FD');

        doc.setFont('Helvetica', 'bold');
        doc.setFontSize(11);
        doc.setTextColor(176, 145, 104);
        doc.text('RESERVA DE CITA', 130, 18);
        doc.text('DE BELLEZA', 130, 23);

        const ticketNum = 'RES-' + (cita.id_citas || 'N/A');
        doc.setFontSize(11);
        doc.setTextColor(30, 30, 36);
        doc.text(ticketNum, 130, 31);

        doc.setDrawColor(230, 230, 233);
        doc.line(14, 45, 195, 45);

        doc.setFont('Helvetica', 'bold');
        doc.setFontSize(10);
        doc.text('DATOS DE LA RESERVA', 14, 52);

        doc.setFont('Helvetica', 'normal');
        doc.setFontSize(9);
        const cli = cita.id_clientes || {};
        const cliNombre = cli.nombre_cliente ? (cli.nombre_cliente + ' ' + (cli.apellidos_clientes || '')) : 'Cliente';
        const cliPhone = cli.telefono || '-';
        const cliMail = cli.correo || '-';
        
        const sede = cita.id_sedes || {};
        const sedeNombre = sede.nombre_sede || 'Sede Principal';
        const sedeDir = sede.direccion || '-';

        const fechaCita = cita.fecha_cita ? new Date(cita.fecha_cita + 'T00:00:00').toLocaleDateString() : '-';
        const horaCita = `${cita.hora_inicio ? cita.hora_inicio.substring(0, 5) : ''} - ${cita.hora_fin ? cita.hora_fin.substring(0, 5) : ''}`;

        doc.text('Cliente: ' + cliNombre, 14, 58);
        doc.text('Teléfono: ' + cliPhone, 14, 63);
        doc.text('Correo: ' + cliMail, 14, 68);
        
        doc.text('Sede: ' + sedeNombre, 120, 58);
        doc.text('Dirección Sede: ' + sedeDir, 120, 63);
        doc.text('Fecha Cita: ' + fechaCita + ' (' + horaCita + ')', 120, 68);

        const headers = [['Item', 'Servicio Belleza', 'Duración', 'Precio Base']];
        const rows = detalles.map((d, index) => [
            index + 1,
            d.id_servicios_belleza ? d.id_servicios_belleza.nombre_servicio : 'Servicio',
            d.id_servicios_belleza && d.id_servicios_belleza.duracion_minima ? (d.id_servicios_belleza.duracion_minima + ' min') : '30 min',
            d.precio ? `S/. ${parseFloat(d.precio).toFixed(2)}` : 'S/. 0.00'
        ]);

        doc.autoTable({
            startY: 75,
            head: headers,
            body: rows,
            theme: 'striped',
            headStyles: { fillColor: [197, 168, 128] },
            styles: { fontSize: 9, font: 'helvetica' },
            margin: { top: 75 }
        });

        const finalY = doc.lastAutoTable.finalY || 120;

        // Total
        doc.setFont('Helvetica', 'bold');
        doc.setFontSize(10);
        doc.setTextColor(30, 30, 36);
        const totalAmount = detalles.reduce((sum, d) => sum + parseFloat(d.precio || 0), 0);
        doc.text(`Total Estimado a Pagar en Sede: S/. ${totalAmount.toFixed(2)}`, 14, finalY + 12);

        doc.setFont('Helvetica', 'normal');
        doc.setFontSize(9);
        doc.setTextColor(117, 117, 119);
        doc.text('Notas: Por favor presentarse 10 minutos antes del horario reservado.', 14, finalY + 22);
        doc.text('En caso de cancelación, avisar con al menos 24 horas de anticipación.', 14, finalY + 27);

        // Open in new window/tab
        const pdfBlob = doc.output('blob');
        const pdfUrl = URL.createObjectURL(pdfBlob);
        window.open(pdfUrl, '_blank');
    })
    .catch(err => {
        console.error('Error al generar comprobante de cita:', err);
        alert('Ocurrió un error al generar el PDF de la cita.');
    });
}

window.generarComprobanteCita = generarComprobanteCita;
