import * as usersModel from '../models/usersModel';
import config from '../config';

let usersRepo: any = null;
if (config.databaseUrl) {
  try {
    usersRepo = require('../repository/usersPrismaRepository');
  } catch (e) {
    try {
      usersRepo = require('../repository/usersRepository');
    } catch {
      usersRepo = null;
    }
  }
}

export async function listUsers() {
  if (usersRepo) return usersRepo.list();
  return usersModel.list();
}

export async function getUser(id: number | string) {
  if (usersRepo) return usersRepo.getById(id);
  return usersModel.getById(id);
}

export async function createUser(data: { email: string; name?: string; password: string }) {
  if (usersRepo) return usersRepo.create(data);
  return usersModel.create(data);
}

export async function updateUser(id: number | string, data: { email?: string; name?: string; password?: string }) {
  if (usersRepo) return usersRepo.update(id, data);
  return usersModel.update(id, data);
}

export async function deleteUser(id: number | string) {
  if (usersRepo) return usersRepo.remove(id);
  return usersModel.remove(id);
}
