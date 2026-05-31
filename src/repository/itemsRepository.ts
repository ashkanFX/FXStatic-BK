import db from './db';

export async function list() { const res = await db.query('SELECT id, name, created_at FROM items ORDER BY id'); return res.rows; }
export async function getById(id: number | string) { const res = await db.query('SELECT id, name, created_at FROM items WHERE id = $1', [id]); return res.rows[0] || null; }
export async function create(data: { name: string }) { const res = await db.query('INSERT INTO items(name, created_at) VALUES($1, NOW()) RETURNING id, name, created_at', [data.name]); return res.rows[0]; }
export async function update(id: number | string, data: { name?: string }) { const res = await db.query('UPDATE items SET name = $1 WHERE id = $2 RETURNING id, name, created_at', [data.name, id]); return res.rows[0] || null; }
export async function remove(id: number | string) { const res = await db.query('DELETE FROM items WHERE id = $1', [id]); return (res.rowCount ?? 0) > 0; }
