export interface Item { id: number; name: string; createdAt?: string }

const items: Item[] = [];
let nextId = 1;

export function list(): Item[] { return items.slice(); }
export function getById(id: number | string): Item | null { return items.find(i => i.id === Number(id)) || null; }
export function create(data: { name: string }): Item { const item = { id: nextId++, name: data.name, createdAt: new Date().toISOString() }; items.push(item); return item; }
export function update(id: number | string, data: { name?: string }): Item | null { const item = getById(id); if (!item) return null; if (data.name) item.name = data.name; return item; }
export function remove(id: number | string): boolean { const idx = items.findIndex(i => i.id === Number(id)); if (idx === -1) return false; items.splice(idx,1); return true; }
