export interface User {
  id: number;
  email: string;
  name?: string;
  password: string;
  createdAt?: string;
}

const users: User[] = [];
let nextId = 1;

export function list(): User[] {
  return users.slice();
}

export function getById(id: number | string): User | null {
  return users.find((user) => user.id === Number(id)) || null;
}

export function create(data: { email: string; name?: string; password: string }): User {
  const user: User = {
    id: nextId++,
    email: data.email,
    name: data.name,
    password: data.password,
    createdAt: new Date().toISOString(),
  };
  users.push(user);
  return user;
}

export function update(id: number | string, data: { email?: string; name?: string; password?: string }): User | null {
  const user = getById(id);
  if (!user) return null;
  if (data.email) user.email = data.email;
  if (data.name !== undefined) user.name = data.name;
  if (data.password) user.password = data.password;
  return user;
}

export function remove(id: number | string): boolean {
  const idx = users.findIndex((user) => user.id === Number(id));
  if (idx === -1) return false;
  users.splice(idx, 1);
  return true;
}
