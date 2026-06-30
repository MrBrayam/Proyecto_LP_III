const API = '';

const API_TOKEN_KEY = 'api_token';
const DEFAULT_API_TOKEN = 'eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiI5NmQ3OTc3YWIwNjJlNjRhMjE3ZTMxYmU0MjYwNjk2MzE3ZDk1ZTZmODlkYWY3YmQxODhjOTkxOGNhZmY2MTQ3IiwiaWF0IjoxNzgwNDMzNDc5LCJleHAiOjQ5MzQwMzM0Nzl9.H9GWlmciY3eRU1KOz3XzqQNH1Ph_b9p1DoWSGh5WOgaOgj0Zl0VNFZRxEcJCYERp_K0ghv0bJGvKb8EdTLU9cg';

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

const crud = {
    apiUrl: '',
    columns: [],
    pkField: '',
    formConfig: [],
    fkLoads: [],
    editId: null,

    init(apiUrl, columns, formConfig, fkLoads) {
        this.apiUrl = apiUrl;
        this.columns = columns;
        this.pkField = columns.find(c => c.pk)?.field;
        this.formConfig = formConfig;
        this.fkLoads = fkLoads || [];
        this.load();
    },

    load() {
        document.querySelector('.loading').style.display = 'block';
        document.querySelector('.empty-state').style.display = 'none';
        apiFetch(this.apiUrl).then(data => renderTable(data, this.columns)).catch(() => {});
    },

    openNew() {
        this.editId = null;
        document.getElementById('modalTitle').textContent = 'Nuevo Registro';
        document.getElementById('entityForm').reset();
        this.loadFkOptions(() => this.openModal());
    },

    edit(id) {
        this.editId = id;
        document.getElementById('modalTitle').textContent = 'Editar Registro';
        apiFetch(this.apiUrl + '/' + id).then(data => {
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
                this.openModal();
            });
        }).catch(err => { console.error(err); alert(err.message); });
    },

    save() {
        const form = document.getElementById('entityForm');
        const data = {};
        for (const field of this.formConfig) {
            const el = form.elements[field.name];
            if (!el) continue;
            let v = el.value;
            if (v === '' || v === null || v === undefined) continue;
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

        apiFetch(url, { method, body: JSON.stringify(data) }).then(() => {
            this.closeModal();
            this.load();
        }).catch(err => { console.error(err); alert(err.message); });
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
});
