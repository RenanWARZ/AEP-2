const API_USERS = '/api/usuario';
const API_OCC = '/api/occurrences';

let editingUserId = null;
let editingOccId = null;

// === USUÁRIOS ===
document.getElementById('user-form').addEventListener('submit', async e => {
  e.preventDefault();
  const user = {
    name: document.getElementById('user-name').value,
    email: document.getElementById('user-email').value,
    role: document.getElementById('user-role').value
  };

  if (editingUserId) {
    // Atualiza usuário existente
    await fetch(`${API_USERS}/${editingUserId}`, {
      method: 'PUT',
      headers: { 'Content-Type': 'application/json' },
      body: JSON.stringify(user)
    });
    editingUserId = null;
    document.querySelector('#user-form button').textContent = "Salvar Usuário";
  } else {
    // Novo cadastro
    await fetch(API_USERS, {
      method: 'POST',
      headers: { 'Content-Type': 'application/json' },
      body: JSON.stringify(user)
    });
  }

  loadUsers();
  e.target.reset();
});

async function loadUsers() {
  const res = await fetch(API_USERS);
  const users = await res.json();
  const div = document.getElementById('user-list');
  div.innerHTML = `
  <br>
    <h3>Usuários Cadastrados</h3>
    ${users.map(u => `
      <p>
        ${u.id} - ${u.name} (${u.role})
        <button onclick="editUser(${u.id})" class="btn-edit"><i class="fa-solid fa-pen"></i> Editar</button>
         <button  onclick="deleteUser(${u.id})" class="btn-delete"><i class="fa-solid fa-trash"></i> Excluir</button>
      </p>
    `).join('')}
  `;
}

async function editUser(id) {
  const res = await fetch(`${API_USERS}/${id}`);
  const user = await res.json();

  document.getElementById('user-name').value = user.name;
  document.getElementById('user-email').value = user.email;
  document.getElementById('user-role').value = user.role;

  editingUserId = user.id;
  document.querySelector('#user-form button').textContent = "Atualizar Usuário";
}

async function deleteUser(id) {
  await fetch(`${API_USERS}/${id}`, { method: 'DELETE' });
  loadUsers();
}

loadUsers();

// === OCORRÊNCIAS ===
document.getElementById('occurrence-form').addEventListener('submit', async e => {
  e.preventDefault();

  const occ = {
    title: document.getElementById('occ-title').value,
    description: document.getElementById('occ-description').value,
    category: document.getElementById('occ-category').value,
    reporterId: parseInt(document.getElementById('occ-reporter').value)
  };

  if (editingOccId) {
    await fetch(`${API_OCC}/${editingOccId}`, {
      method: 'PUT',
      headers: { 'Content-Type': 'application/json' },
      body: JSON.stringify(occ)
    });
    editingOccId = null;
    document.querySelector('#occurrence-form button').textContent = "Salvar Ocorrência";
  } else {
    await fetch(API_OCC, {
      method: 'POST',
      headers: { 'Content-Type': 'application/json' },
      body: JSON.stringify(occ)
    });
  }

  loadOccurrences();
  e.target.reset();
});

async function loadOccurrences() {
  const res = await fetch(API_OCC);
  const occs = await res.json();
  const tbody = document.querySelector('#occurrence-table tbody');

  tbody.innerHTML = occs.map(o => `
    <tr>
      <td>${o.id}</td>
      <td>${o.title}</td>
      <td>${o.description}</td>
      <td>${o.category}</td>
      <td>${o.status || 'Aberto'}</td>
      <td>
        <button onclick="editOcc(${o.id})" class="btn btn-edit">
          <i class="fa-solid fa-pen"></i> Editar
        </button>

        ${
          o.status === 'Fechado'
          ? `<button onclick="updateStatus(${o.id}, 'Aberto')" class="btn btn-info"> Abrir </button>`
          : `<button onclick="updateStatus(${o.id}, 'Fechado')" class="btn btn-close"> Fechar  </button>`
        }

        <button onclick="deleteOcc(${o.id})" class="btn btn-delete">
          <i class="fa-solid fa-trash"></i> Excluir
        </button>
      </td>
    </tr>
  `).join('');
}

// Função para editar ocorrência
async function editOcc(id) {
  const res = await fetch(`${API_OCC}/${id}`);
  const o = await res.json();

  document.getElementById('occ-title').value = o.title;
  document.getElementById('occ-description').value = o.description;
  document.getElementById('occ-category').value = o.category;
  document.getElementById('occ-reporter').value = o.reporter ? o.reporter.id : '';

  editingOccId = o.id;
  document.querySelector('#occurrence-form button').textContent = "Atualizar Ocorrência";
}

// Função para atualizar status (Fechar/Abrir)
async function updateStatus(id, status) {
  await fetch(`${API_OCC}/${id}/status`, {
    method: 'PUT',
    headers: { 'Content-Type': 'application/json' },
    body: JSON.stringify({ status })
  });
  loadOccurrences();
}

// Função para excluir ocorrência
async function deleteOcc(id) {
  await fetch(`${API_OCC}/${id}`, { method: 'DELETE' });
  loadOccurrences();
}

loadOccurrences();
