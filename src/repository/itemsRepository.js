const db = require('./db');

async function list() {
  const res = await db.query('SELECT id, name, created_at FROM items ORDER BY id');
  return res.rows;
}

async function getById(id) {
  const res = await db.query('SELECT id, name, created_at FROM items WHERE id = $1', [id]);
  return res.rows[0] || null;
}

async function create(data) {
  const res = await db.query(
    'INSERT INTO items(name, created_at) VALUES($1, NOW()) RETURNING id, name, created_at',
    [data.name]
  );
  return res.rows[0];
}

async function update(id, data) {
  const res = await db.query(
    'UPDATE items SET name = $1 WHERE id = $2 RETURNING id, name, created_at',
    [data.name, id]
  );
  return res.rows[0] || null;
}

async function remove(id) {
  const res = await db.query('DELETE FROM items WHERE id = $1', [id]);
  return res.rowCount > 0;
}

module.exports = { list, getById, create, update, remove };