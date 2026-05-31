import prisma from './prismaClient';

export async function list() {
  return prisma.user.findMany({ orderBy: { id: 'asc' } });
}

export async function getById(id: number | string) {
  return prisma.user.findUnique({ where: { id: Number(id) } });
}

export async function create(data: { email: string; name?: string; password: string }) {
  return prisma.user.create({ data: { email: data.email, name: data.name, password: data.password } });
}

export async function update(id: number | string, data: { email?: string; name?: string; password?: string }) {
  try {
    return await prisma.user.update({
      where: { id: Number(id) },
      data: { email: data.email, name: data.name, password: data.password },
    });
  } catch {
    return null;
  }
}

export async function remove(id: number | string) {
  try {
    await prisma.user.delete({ where: { id: Number(id) } });
    return true;
  } catch {
    return false;
  }
}
