/* ===========================================
   tienda.js — Storefront client-side logic
   Bellarista Salon & Boutique
   =========================================== */

let allProducts = [];
let filteredProducts = [];
let categories = [];
let activeCategory = null;
let cart = [];
let allServices = [];

// Load Catalog on page ready
document.addEventListener('DOMContentLoaded', () => {
    loadCart();
    fetch('/tienda/api/categorias')
        .then(r => r.json())
        .then(cats => {
            categories = cats;
            renderCategoryFilters();
        });

    fetch('/tienda/api/productos')
        .then(r => r.json())
        .then(prods => {
            allProducts = prods;
            filteredProducts = prods;
            renderProducts();
        });

    fetch('/tienda/api/servicios')
        .then(r => r.json())
        .then(servs => {
            allServices = servs;
        })
        .catch(err => console.error('Error al cargar servicios:', err));
});

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

        const imgUrl = p.img_url || '';
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

    const existing = cart.find(item => item.id_productos === prodId);
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
            nombre_producto: prod.nombre_producto,
            precio_venta: prod.precio_venta,
            img_url: prod.img_url,
            cantidad: 1
        });
    }
    saveCart();
    toggleCart(true);
}

function updateCartQuantity(prodId, delta) {
    const item = cart.find(i => i.id_productos === prodId);
    if (!item) return;

    if (delta > 0) {
        const prod = allProducts.find(p => p.id_productos === prodId);
        const stockAct = prod && prod.stock_actual != null ? Number(prod.stock_actual) : 999;
        if (item.cantidad >= stockAct) {
            alert('No se pueden agregar más unidades. El stock máximo disponible es ' + stockAct + '.');
            return;
        }
    }

    item.cantidad += delta;
    if (item.cantidad <= 0) {
        cart = cart.filter(i => i.id_productos !== prodId);
    }
    saveCart();
}

function removeFromCart(prodId) {
    cart = cart.filter(i => i.id_productos !== prodId);
    saveCart();
}

function updateCartUI() {
    const container = document.getElementById('cartItems');
    const countBadge = document.getElementById('cartCount');

    // Guard: these elements only exist on tienda.html, not on other pages
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

        const imgUrl = item.img_url || '';
        const fallbackChar = item.nombre_producto.charAt(0).toUpperCase();
        const imageHtml = imgUrl
            ? '<img src="' + imgUrl + '" class="cart-item-img" alt="' + item.nombre_producto + '">'
            : '<div class="cart-item-img" style="display:flex;align-items:center;justify-content:center;background:var(--primary-light);font-size:24px;font-weight:700;color:var(--primary);font-family:\'Playfair Display\',serif;">' + fallbackChar + '</div>';

        div.innerHTML = imageHtml
            + '<div class="cart-item-info">'
            + '<h4>' + item.nombre_producto + '</h4>'
            + '<p>S/ ' + parseFloat(item.precio_venta).toFixed(2) + ' c/u</p>'
            + '<div class="cart-item-actions">'
            + '<div class="quantity-control">'
            + '<button onclick="updateCartQuantity(' + item.id_productos + ', -1)">-</button>'
            + '<span>' + item.cantidad + '</span>'
            + '<button onclick="updateCartQuantity(' + item.id_productos + ', 1)">+</button>'
            + '</div>'
            + '<button class="remove-item-btn" onclick="removeFromCart(' + item.id_productos + ')">Quitar</button>'
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
    window.location.href = '/tienda/checkout';
}

// Tab Switching
function switchTab(tab, element) {
    document.querySelectorAll('.nav-menu .nav-link').forEach(l => l.classList.remove('active'));
    element.classList.add('active');

    document.getElementById('storeSection').style.display = 'none';
    document.getElementById('servicesSection').style.display = 'none';
    const historySec = document.getElementById('historySection');
    if (historySec) historySec.style.display = 'none';

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
            + '<a href="https://wa.me/51987654321?text=Hola,%20quisiera%20reservar%20una%20cita%20para%20el%20servicio%20de%20' + encodeURIComponent(s.nombre_servicio_belleza) + '" target="_blank" class="btn-filled" style="padding:6px 12px;font-size:11px;border-radius:99px;display:flex;align-items:center;gap:4px;text-decoration:none;">Reservar Cita</a>'
            + '</div></div>';
        grid.appendChild(card);
    });
}

// Render History List
function renderHistory() {
    const ventasList = document.getElementById('ventasHistoryList');
    const citasList = document.getElementById('citasHistoryList');

    ventasList.innerHTML = '<div style="text-align:center;color:var(--text-muted);padding:20px;font-size:13px;">Cargando compras...</div>';
    citasList.innerHTML = '<div style="text-align:center;color:var(--text-muted);padding:20px;font-size:13px;">Cargando citas...</div>';

    fetch('/tienda/api/historial')
        .then(r => r.json())
        .then(data => {
            if (!data.success) {
                ventasList.innerHTML = '<div style="text-align:center;color:red;padding:20px;font-size:13px;">' + data.error + '</div>';
                citasList.innerHTML = '<div style="text-align:center;color:red;padding:20px;font-size:13px;">' + data.error + '</div>';
                return;
            }

            // Render Ventas
            ventasList.innerHTML = '';
            if (!data.ventas || data.ventas.length === 0) {
                ventasList.innerHTML = '<div style="text-align:center;color:var(--text-muted);padding:20px;font-weight:300;font-size:13px;">No tienes compras registradas.</div>';
            } else {
                data.ventas.forEach(v => {
                    const dateStr = v.fecha_venta ? new Date(v.fecha_venta).toLocaleDateString() : '-';
                    const ticket = v.numero_ticket || v.comprobante_numero || 'T-N/A';
                    const statusLabel = v.estado_sunat ? v.estado_sunat.toUpperCase() : 'PENDIENTE';
                    const totalVal = v.total != null ? parseFloat(v.total).toFixed(2) : '0.00';

                    let statusClass = 'status-badge-pending';
                    if (v.estado_sunat === 'aceptada') statusClass = 'status-badge-success';
                    else if (v.estado_sunat === 'rechazada') statusClass = 'status-badge-danger';

                    const itemDiv = document.createElement('div');
                    itemDiv.className = 'history-item';
                    itemDiv.innerHTML = '<div style="display:flex;justify-content:space-between;align-items:center;margin-bottom:8px;">'
                        + '<div><strong style="font-size:14px;color:var(--dark);">' + ticket + '</strong>'
                        + '<span style="font-size:12px;color:var(--text-muted);margin-left:8px;">' + dateStr + '</span></div>'
                        + '<span class="status-badge ' + statusClass + '">' + statusLabel + '</span>'
                        + '</div>'
                        + '<div style="display:flex;justify-content:space-between;align-items:center;font-size:13px;">'
                        + '<span style="color:var(--text-muted);">Comprobante: ' + (v.tipo_comprobante || 'boleta') + '</span>'
                        + '<div style="display:flex;align-items:center;gap:12px;">'
                        + '<button onclick="generarBoletaDesdeHistorial(' + v.id_ventas + ')" class="btn-filled" style="padding:4px 10px;font-size:10px;border-radius:99px;background:var(--primary);border:none;cursor:pointer;color:white;">📄 PDF</button>'
                        + '<strong style="font-size:15px;color:var(--dark);">S/ ' + totalVal + '</strong>'
                        + '</div></div>';
                    ventasList.appendChild(itemDiv);
                });
            }

            // Render Citas
            citasList.innerHTML = '';
            if (!data.citas || data.citas.length === 0) {
                citasList.innerHTML = '<div style="text-align:center;color:var(--text-muted);padding:20px;font-weight:300;font-size:13px;">No tienes reservas de citas de belleza.</div>';
            } else {
                data.citas.forEach(c => {
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
                    itemDiv.innerHTML = '<div style="display:flex;justify-content:space-between;align-items:center;margin-bottom:8px;">'
                        + '<div><strong style="font-size:14px;color:var(--dark);">' + dateStr + '</strong>'
                        + '<span style="font-size:12px;color:var(--text-muted);margin-left:8px;">' + timeStr + '</span></div>'
                        + '<span class="status-badge ' + statusClass + '">' + statusLabel + '</span>'
                        + '</div>'
                        + '<div style="display:flex;justify-content:space-between;align-items:center;font-size:13px;margin-bottom:4px;">'
                        + '<span style="color:var(--text-muted);">Sede: ' + sedeName + '</span>'
                        + '<span style="color:var(--text-muted);">' + duration + '</span>'
                        + '</div>'
                        + '<div style="font-size:12px;color:var(--text-muted);font-style:italic;">Obs: ' + (c.observaciones || 'Sin observaciones') + '</div>';
                    citasList.appendChild(itemDiv);
                });
            }
        })
        .catch(err => {
            console.error('Error al cargar historial:', err);
            ventasList.innerHTML = '<div style="text-align:center;color:red;padding:20px;font-size:13px;">Error al cargar.</div>';
            citasList.innerHTML = '<div style="text-align:center;color:red;padding:20px;font-size:13px;">Error al cargar.</div>';
        });
}

// PDF Generator — from history page
function generarBoletaDesdeHistorial(ventaId) {
    if (!ventaId) return;
    generarBoletaPDFComun(ventaId);
}

// Shared PDF generation logic
function generarBoletaPDFComun(ventaId) {
    Promise.all([
        fetch('/tienda/api/venta/' + ventaId).then(r => r.json()),
        fetch('/tienda/api/detalles-venta/' + ventaId).then(r => r.json())
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
