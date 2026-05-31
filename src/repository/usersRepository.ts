import db from './db';

export async function list() {
  const res = await db.query('SELECT id, email, name, password, created_at FROM users ORDER BY id');
  return res.rows;
}

export async function getById(id: number | string) {
  const res = await db.query('SELECT id, email, name, password, created_at FROM users WHERE id = $1', [id]);
  return res.rows[0] || null;
}

export async function create(data: { email: string; name?: string; password: string }) {
  const res = await db.query(
    'INSERT INTO users(email, name, password, created_at) VALUES($1, $2, $3, NOW()) RETURNING id, email, name, password, created_at',
    [data.email, data.name || null, data.password]
  );
  return res.rows[0];
}

export async function update(id: number | string, data: { email?: string; name?: string; password?: string }) {
  const res = await db.query(
    'UPDATE users SET email = COALESCE($1, email), name = COALESCE($2, name), password = COALESCE($3, password) WHERE id = $4 RETURNING id, email, name, password, created_at',
    [data.email || null, data.name || null, data.password || null, id]
  );
  return res.rows[0] || null;
}

export async function remove(id: number | string) {
  const res = await db.query('DELETE FROM users WHERE id = $1', [id]);
  return (res.rowCount ?? 0) > 0;
}
