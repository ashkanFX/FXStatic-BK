import prisma from './prismaClient';

export async function list() { return prisma.item.findMany({ orderBy: { id: 'asc' } }); }
export async function getById(id: number | string) { return prisma.item.findUnique({ where: { id: Number(id) } }); }
export async function create(data: { name: string }) { return prisma.item.create({ data: { name: data.name } }); }
export async function update(id: number | string, data: { name?: string }) { try { return await prisma.item.update({ where: { id: Number(id) }, data: { name: data.name } }); } catch { return null; } }
export async function remove(id: number | string) { try { await prisma.item.delete({ where: { id: Number(id) } }); return true; } catch { return false; } }
