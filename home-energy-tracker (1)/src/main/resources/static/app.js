const API = '/api/usages';

async function fetchAll(){
  const res = await fetch(API);
  const data = await res.json();
  renderTable(data);
}

function renderTable(data){
  const tbody = document.querySelector('#usageTable tbody');
  tbody.innerHTML = '';
  data.forEach(u=>{
  const tr = document.createElement('tr');
    tr.innerHTML = `<td>${u.id}</td><td>${u.date}</td><td>${escapeHtml(u.appliance)}</td><td>${u.kwh}</td>
      <td><button data-id="${u.id}" class="del">Delete</button></td>`;
    tbody.appendChild(tr);
  });
  document.querySelectorAll('.del').forEach(btn=>{
    btn.addEventListener('click', async e=>{
      const id = e.target.dataset.id;
      await fetch(API + '/' + id, { method: 'DELETE' });
      fetchAll();
    });
  });
}

function escapeHtml(s){ return s.replaceAll('&','&amp;').replaceAll('<','&lt;').replaceAll('>','&gt;'); }

document.getElementById('usageForm').addEventListener('submit', async (e)=>{
  e.preventDefault();
  const body = {
    date: document.getElementById('date').value,
    appliance: document.getElementById('appliance').value,
    kwh: parseFloat(document.getElementById('kwh').value)
  };
  await fetch(API, {method:'POST', headers:{'Content-Type':'application/json'}, body: JSON.stringify(body)});
  e.target.reset();
  fetchAll();
});

document.getElementById('filterBtn').addEventListener('click', async ()=>{
  const start = document.getElementById('start').value;
  const end = document.getElementById('end').value;
  if(!start || !end){ alert('Select both start and end dates'); return; }
  const res = await fetch(`${API}/range?start=${start}&end=${end}`);
  const data = await res.json();
  renderTable(data);
});

document.getElementById('resetBtn').addEventListener('click', ()=>{ document.getElementById('start').value=''; document.getElementById('end').value=''; fetchAll(); });

fetchAll();
