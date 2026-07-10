const API = '';

const API_TOKEN_KEY = 'api_token';
const DEFAULT_API_TOKEN = 'eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiI5NmQ3OTc3YWIwNjJlNjRhMjE3ZTMxYmU0MjYwNjk2MzE3ZDk1ZTZmODlkYWY3YmQxODhjOTkxOGNhZmY2MTQ3IiwiaWF0IjoxNzgwNDMzNDc5LCJleHAiOjQ5MzQwMzM0Nzl9.H9GWlmciY3eRU1KOz3XzqQNH1Ph_b9p1DoWSGh5WOgaOgj0Zl0VNFZRxEcJCYERp_K0ghv0bJGvKb8EdTLU9cg';

window.__crudDebug = window.__crudDebug || [];

function crudLog(level, message, details) {
    const entry = {
        time: new Date().toISOString(),
        level,
        message,
        details
    };

    window.__crudDebug.push(entry);

    const method = console[level] || console.log;
    if (details !== undefined) {
        method.call(console, `[CRUD] ${message}`, details);
    } else {
        method.call(console, `[CRUD] ${message}`);
    }
}

function getApiToken() {
    return DEFAULT_API_TOKEN
        || localStorage.getItem(API_TOKEN_KEY)
        || sessionStorage.getItem(API_TOKEN_KEY)
        || '';
}

function setApiToken(token, persist = true) {
    const clean = (token || '').trim();
    if (!clean) return;
    if (persist) {
        localStorage.setItem(API_TOKEN_KEY, clean);
        sessionStorage.removeItem(API_TOKEN_KEY);
    } else {
        sessionStorage.setItem(API_TOKEN_KEY, clean);
        localStorage.removeItem(API_TOKEN_KEY);
    }
}

function clearApiToken() {
    localStorage.removeItem(API_TOKEN_KEY);
    sessionStorage.removeItem(API_TOKEN_KEY);
}

window.setApiToken = setApiToken;
window.clearApiToken = clearApiToken;

// Sidebar
function toggleSidebar() {
    document.getElementById('sidebar').classList.toggle('open');
    document.getElementById('sidebarOverlay').classList.toggle('open');
}

function closeSidebar() {
    document.getElementById('sidebar').classList.remove('open');
    document.getElementById('sidebarOverlay').classList.remove('open');
}

function toggleMenuGroup(header) {
    header.classList.toggle('active');
    header.nextElementSibling.classList.toggle('open');
}

function setActiveMenu() {
    const path = window.location.pathname;
    document.querySelectorAll('.menu-items a').forEach(a => {
        a.classList.toggle('active', a.getAttribute('href') === path);
    });
    document.querySelectorAll('.menu-header').forEach(h => {
        const items = h.nextElementSibling;
        if (items && items.querySelector('a.active')) {
            h.classList.add('active');
            items.classList.add('open');
        }
    });
}

function apiFetch(url, options) {
    const headers = { ...(options?.headers || {}) };
    const token = getApiToken();

    if (token && !headers.Authorization) {
        headers.Authorization = `Bearer ${token}`;
    }

    if (!(options?.body instanceof FormData)) {
        headers['Content-Type'] = 'application/json';
    }
    return fetch(API + url, { ...options, headers }).then(res => {
        if (res.status === 401 || res.status === 403) {
            throw new Error('No autorizado. Configure un token JWT con setApiToken("TU_TOKEN").');
        }
        if (!res.ok) {
            return res.text().then(t => { try { const j = JSON.parse(t); throw new Error(j.message || j.error || 'Error'); } catch (e) { throw new Error(t || 'Error'); } });
        }
        const ct = res.headers.get('content-type');
        return ct && ct.includes('application/json') ? res.json() : res.text();
    });
}

function val(obj, path, fallback) {
    const parts = path.split('.');
    let cur = obj;
    for (const p of parts) {
        if (cur == null || cur[p] === undefined) return fallback ?? '-';
        cur = cur[p];
    }
    return cur ?? fallback ?? '-';
}

function escapeHtml(value) {
    return String(value ?? '')
        .replace(/&/g, '&amp;')
        .replace(/</g, '&lt;')
        .replace(/>/g, '&gt;')
        .replace(/"/g, '&quot;')
        .replace(/'/g, '&#39;');
}

function resolveCardImageUrl(rawUrl, label, fieldName) {
    const value = String(rawUrl || '').trim();
    if (!value) {
        return '';
    }

    if (value.startsWith('data:') || value.startsWith('blob:') || value.startsWith('/')) {
        return value;
    }

    try {
        const parsed = new URL(value, window.location.origin);
        const allowedHosts = new Set([window.location.hostname, 'localhost', '127.0.0.1']);
        if (parsed.protocol === 'http:' || parsed.protocol === 'https:') {
            if (allowedHosts.has(parsed.hostname)) {
                return parsed.toString();
            }

            console.warn(`Imagen omitida para ${fieldName || 'card'} (${label || 'sin nombre'}): host externo no permitido`, parsed.toString());
            return '';
        }
    } catch (error) {
        console.warn(`Imagen omitida para ${fieldName || 'card'} (${label || 'sin nombre'}): URL invalida`, value);
        return '';
    }

    console.warn(`Imagen omitida para ${fieldName || 'card'} (${label || 'sin nombre'}): esquema no soportado`, value);
    return '';
}

function renderTable(data, columns) {
    const tbody = document.querySelector('#data-table tbody');
    const emptyRow = document.querySelector('.empty-state');
    const loading = document.querySelector('.loading');
    if (!tbody) return;
    if (loading) loading.style.display = 'none';

    if (!data || data.length === 0) {
        tbody.innerHTML = '';
        if (emptyRow) emptyRow.style.display = 'block';
        return;
    }
    if (emptyRow) emptyRow.style.display = 'none';

    const pk = columns.find(c => c.pk)?.field || Object.keys(data[0])[0];

    tbody.innerHTML = data.map(row => {
        const cells = columns.map(col => {
            if (col.actions) return '';
            let v = val(row, col.field);
            if (col.render) v = col.render(v, row);
            else if (col.field === 'estado') {
                v = v === 1 || v === true
                    ? '<span class="badge badge-active">Activo</span>'
                    : '<span class="badge badge-inactive">Inactivo</span>';
            }
            return `<td>${v}</td>`;
        }).join('');

        const id = val(row, pk);
        const actions = `
            <button class="btn btn-sm" onclick="crud.edit(${id})">Editar</button>
            <button class="btn btn-sm btn-danger" onclick="crud.del(${id})">Eliminar</button>`;

        return `<tr>${cells}<td>${actions}</td></tr>`;
    }).join('');
}

function renderCards(data, columns, options = {}) {
    const container = document.querySelector('#data-cards');
    const emptyRow = document.querySelector('.empty-state');
    const loading = document.querySelector('.loading');
    if (!container) return;
    if (loading) loading.style.display = 'none';

    if (!data || data.length === 0) {
        container.innerHTML = '';
        if (emptyRow) emptyRow.style.display = 'block';
        return;
    }

    if (emptyRow) emptyRow.style.display = 'none';

    const pk = columns.find(c => c.pk)?.field || Object.keys(data[0])[0];
    const renderer = typeof options.cardRenderer === 'function' ? options.cardRenderer : null;

    container.innerHTML = data.map(row => {
        if (renderer) {
            return renderer(row, pk, columns);
        }

        const id = val(row, pk);
        const title = escapeHtml(val(row, options.titleField || pk));
        const subtitle = options.subtitleField ? escapeHtml(val(row, options.subtitleField)) : '';
        const image = resolveCardImageUrl(val(row, options.imageField || 'img_url', ''), title, options.imageField || 'img_url');
        const initials = title ? title.slice(0, 2).toUpperCase() : '--';
        const imageBlock = image
            ? `<img class="entity-card__image" src="${escapeHtml(image)}" alt="${title}" loading="lazy" onerror="this.style.display='none';this.nextElementSibling.style.display='flex';"><div class="entity-card__fallback" style="display:none;">${initials}</div>`
            : `<div class="entity-card__fallback">${initials}</div>`;

        const meta = (options.metaFields || []).map(field => {
            const value = val(row, field.field);
            const formatted = field.render ? field.render(value, row) : escapeHtml(value);
            return `<div class="entity-card__meta-item"><span>${escapeHtml(field.label || field.field)}</span><strong>${formatted}</strong></div>`;
        }).join('');

        const badge = options.badgeField ? val(row, options.badgeField) : null;
        const badgeHtml = badge !== null && badge !== undefined
            ? (badge === 1 || badge === true
                ? '<span class="badge badge-active">Activo</span>'
                : '<span class="badge badge-inactive">Inactivo</span>')
            : '';

        return `
            <article class="entity-card">
                <div class="entity-card__media">${imageBlock}</div>
                <div class="entity-card__body">
                    <div class="entity-card__header">
                        <div>
                            <h4>${title}</h4>
                            ${subtitle ? `<p>${subtitle}</p>` : ''}
                        </div>
                        ${badgeHtml}
                    </div>
                    ${options.descriptionField ? `<p class="entity-card__description">${escapeHtml(val(row, options.descriptionField))}</p>` : ''}
                    ${meta ? `<div class="entity-card__meta">${meta}</div>` : ''}
                    <div class="entity-card__actions">
                        <button class="btn btn-sm" onclick="crud.edit(${id})">Editar</button>
                        <button class="btn btn-sm btn-danger" onclick="crud.del(${id})">Eliminar</button>
                    </div>
                </div>
            </article>`;
    }).join('');
}

const crud = {
    apiUrl: '',
    columns: [],
    pkField: '',
    formConfig: [],
    fkLoads: [],
    fileFields: [],
    pendingUploadPromises: {},
    options: {},
    editId: null,

    init(apiUrl, columns, formConfig, fkLoads, options = {}) {
        this.apiUrl = apiUrl;
        this.columns = columns;
        this.pkField = columns.find(c => c.pk)?.field;
        this.formConfig = formConfig;
        this.fkLoads = fkLoads || [];
        this.fileFields = options.fileFields || [];
        this.options = options || {};
        this.load();
        this.bindFileInputs();
    },

    bindFileInputs() {
        this.fileFields.forEach(field => {
            const input = document.querySelector(`input[data-file-target="${field.target}"]`);
            if (!input || input.dataset.bound === 'true') return;
            input.dataset.bound = 'true';
            input.addEventListener('change', async () => {
                const file = input.files && input.files[0];
                const hidden = document.querySelector(`input[name="${field.target}"]`);
                const preview = document.querySelector(field.previewSelector);
                if (!file || !hidden) return;
                crudLog('log', `Archivo seleccionado para ${field.target}`, {
                    name: file.name,
                    size: file.size,
                    type: file.type
                });

                this.uploadFileField(field, file, hidden, preview);
            });
        });
    },

    uploadFileField(field, file, hidden, preview) {
        const tempUrl = URL.createObjectURL(file);
        if (preview) {
            preview.src = tempUrl;
            preview.style.display = 'block';
        }

        const formData = new FormData();
        formData.append('file', file);

        crudLog('log', `Iniciando upload para ${field.target}`, {
            uploadUrl: field.uploadUrl,
            fileName: file.name,
            fileSize: file.size,
            fileType: file.type
        });

        const uploadPromise = apiFetch(field.uploadUrl, { method: 'POST', body: formData })
            .then(response => {
                crudLog('log', `Respuesta del upload para ${field.target}`, response);
                hidden.value = response.url || response.path || response;
                crudLog('log', `Valor guardado en ${field.target}`, hidden.value);
                if (preview && hidden.value) {
                    preview.src = hidden.value;
                    preview.style.display = 'block';
                }
                return hidden.value;
            })
            .catch(error => {
                crudLog('error', `Error subiendo archivo para ${field.target}`, error);
                throw error;
            })
            .finally(() => {
                delete this.pendingUploadPromises[field.target];
                URL.revokeObjectURL(tempUrl);
            });

        this.pendingUploadPromises[field.target] = uploadPromise;
        return uploadPromise;
    },

    syncFilePreviews() {
        this.fileFields.forEach(field => {
            const hidden = document.querySelector(`input[name="${field.target}"]`);
            const preview = document.querySelector(field.previewSelector);
            const input = document.querySelector(`input[data-file-target="${field.target}"]`);
            if (input) input.value = '';
            crudLog('log', `Sincronizando preview para ${field.target}`, {
                hiddenValue: hidden ? hidden.value : null
            });
            if (preview && hidden && hidden.value) {
                preview.src = hidden.value;
                preview.style.display = 'block';
            } else if (preview) {
                preview.removeAttribute('src');
                preview.style.display = 'none';
            }
        });
    },

    load() {
        document.querySelector('.loading').style.display = 'block';
        document.querySelector('.empty-state').style.display = 'none';
        apiFetch(this.apiUrl).then(data => {
            if (this.options.view === 'cards') {
                renderCards(data, this.columns, this.options);
            } else {
                renderTable(data, this.columns);
            }
        }).catch(() => {});
    },

    openNew() {
        this.editId = null;
        document.getElementById('modalTitle').textContent = 'Nuevo Registro';
        document.getElementById('entityForm').reset();
        this.loadFkOptions(() => {
            this.syncFilePreviews();
            this.fileFields.forEach(field => {
                const hidden = document.querySelector(`input[name="${field.target}"]`);
                crudLog('log', `Formulario nuevo listo para ${field.target}`, {
                    hiddenValue: hidden ? hidden.value : null
                });
            });
            this.openModal();
        });
    },

    edit(id) {
        this.editId = id;
        document.getElementById('modalTitle').textContent = 'Editar Registro';
        apiFetch(this.apiUrl + '/' + id).then(data => {
            crudLog('log', `Cargando registro para edicion ${this.apiUrl}/${id}`, data);
            const form = document.getElementById('entityForm');
            const fkValues = {};
            for (const field of this.formConfig) {
                const el = form.elements[field.name];
                if (!el) continue;
                if (field.type === 'fk') {
                    let value = data[field.name] ?? '';
                    if (typeof value === 'object' && value !== null) {
                        value = value[field.name] ?? '';
                    }
                    fkValues[field.name] = value;
                } else {
                    el.value = field.fromNested ? val(data, field.name) : (data[field.name] ?? '');
                }
            }
            this.loadFkOptions(() => {
                for (const [name, value] of Object.entries(fkValues)) {
                    if (value) form.elements[name].value = value;
                }
                this.syncFilePreviews();
                this.fileFields.forEach(field => {
                    const hidden = document.querySelector(`input[name="${field.target}"]`);
                    crudLog('log', `Formulario de edicion listo para ${field.target}`, {
                        hiddenValue: hidden ? hidden.value : null
                    });
                });
                this.openModal();
            });
        }).catch(err => { console.error(err); alert(err.message); });
    },

    save() {
        const form = document.getElementById('entityForm');

        const uploadRuns = this.fileFields.map(field => {
            const hidden = document.querySelector(`input[name="${field.target}"]`);
            const input = document.querySelector(`input[data-file-target="${field.target}"]`);
            const preview = document.querySelector(field.previewSelector);
            const file = input && input.files && input.files[0];

            if (!hidden) {
                return Promise.resolve();
            }

            if (file) {
                crudLog('log', `El campo ${field.target} tiene archivo seleccionado y se va a subir ahora mismo o esperar a que termine`, {
                    name: file.name,
                    size: file.size,
                    type: file.type
                });
                return this.pendingUploadPromises[field.target] || this.uploadFileField(field, file, hidden, preview);
            }

            if (hidden.value) {
                crudLog('log', `Campo ${field.target} conserva su valor anterior`, hidden.value);
                return Promise.resolve(hidden.value);
            }

            crudLog('warn', `No hay archivo seleccionado para ${field.target} y el hidden sigue vacio`, {
                editId: this.editId
            });
            return Promise.resolve();
        });

        const waitForUploads = Promise.all([...Object.values(this.pendingUploadPromises), ...uploadRuns]);

        waitForUploads.then(() => {
            const data = {};
            for (const field of this.formConfig) {
                const el = form.elements[field.name];
                if (!el) continue;
                let v = el.value;
                if (v === '' || v === null || v === undefined) {
                    if (field.name === 'img_url' || field.name === 'logo_url') {
                        crudLog('warn', `Campo imagen vacio al construir payload: ${field.name}`, {
                            editId: this.editId,
                            value: v
                        });
                    }
                    continue;
                }
                if (field.type === 'fk') {
                    data[field.name] = { [field.name]: Number(v) };
                } else if (field.type === 'number' || field.type === 'decimal') {
                    data[field.name] = field.type === 'decimal' ? parseFloat(v) : Number(v);
                } else {
                    data[field.name] = v;
                }
            }

            const method = this.editId ? 'PUT' : 'POST';
            const url = this.editId ? this.apiUrl + '/' + this.editId : this.apiUrl;

            crudLog('log', `Enviando ${method} a ${url}`, {
                editId: this.editId,
                pendingUploads: Object.keys(this.pendingUploadPromises),
                payload: data
            });

            apiFetch(url, { method, body: JSON.stringify(data) }).then(response => {
                crudLog('log', `Guardado exitoso en ${url}`, response);
                this.closeModal();
                this.load();
            }).catch(err => {
                crudLog('error', `Error guardando en ${url}`, err);
                alert(err.message);
            });
        }).catch(err => {
            crudLog('error', 'Pendientes de subida sin resolver al guardar', err);
            alert('Espere a que termine la subida de la imagen antes de guardar.');
        });
    },

    del(id) {
        if (!confirm('Eliminar este registro?')) return;
        apiFetch(this.apiUrl + '/' + id, { method: 'DELETE' }).then(() => this.load()).catch(err => { console.error(err); alert(err.message); });
    },

    openModal() {
        document.getElementById('entityModal').classList.add('open');
    },

    closeModal() {
        document.getElementById('entityModal').classList.remove('open');
    },

    loadFkOptions(cb) {
        const promises = this.fkLoads.map(fk =>
            apiFetch(fk.api).then(data => {
                const sel = document.querySelector(`select[name="${fk.field}"]`);
                if (!sel) return;
                const current = sel.value;
                sel.innerHTML = '<option value="">Seleccionar...</option>';
                data.forEach(item => {
                    const id = item[fk.field];
                    const label = fk.label ? val(item, fk.label) : id;
                    sel.innerHTML += `<option value="${id}">${label}</option>`;
                });
                if (current) sel.value = current;
            }).catch(() => {})
        );
        Promise.all(promises).then(() => { if (cb) cb(); });
    }
};

// Init
document.addEventListener('DOMContentLoaded', function() {
    const params = new URLSearchParams(window.location.search);
    const tokenFromUrl = params.get('token');
    if (tokenFromUrl) {
        setApiToken(tokenFromUrl, true);
        params.delete('token');
        const next = `${window.location.pathname}${params.toString() ? '?' + params.toString() : ''}${window.location.hash || ''}`;
        window.history.replaceState({}, document.title, next);
    }

    setActiveMenu();

    const hamburger = document.getElementById('hamburgerBtn');
    if (hamburger) hamburger.addEventListener('click', toggleSidebar);

    const closeBtn = document.getElementById('closeBtn');
    if (closeBtn) closeBtn.addEventListener('click', closeSidebar);

    const overlay = document.getElementById('sidebarOverlay');
    if (overlay) overlay.addEventListener('click', closeSidebar);

    const logoutBtn = document.querySelector('.logout-btn');
    if (logoutBtn) {
        logoutBtn.addEventListener('click', function() {
            clearApiToken();
        });
    }

    document.querySelectorAll('.menu-header').forEach(h => {
        h.addEventListener('click', function() { toggleMenuGroup(this); });
    });

    if (window.crud && typeof window.crud.bindFileInputs === 'function') {
        window.crud.bindFileInputs();
    }
});
