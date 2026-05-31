// In-memory items model (placeholder for DB integration)
const items = [];
let nextId = 1;

function list() {
  return items.slice();
}

function getById(id) {
  return items.find(i => i.id === Number(id)) || null;
}

function create(data) {
  const item = { id: nextId++, ...data };
  items.push(item);
  return item;
}

function update(id, data) {
  const item = getById(id);
  if (!item) return null;
  Object.assign(item, data);
  return item;
}

function remove(id) {
  const idx = items.findIndex(i => i.id === Number(id));
  if (idx === -1) return false;
  items.splice(idx, 1);
  return true;
}

module.exports = { list, getById, create, update, remove };
