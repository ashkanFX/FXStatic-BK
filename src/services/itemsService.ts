import * as itemsModel from '../models/itemsModel';
import config from '../config';

let itemsRepo: any = null;
if (config.databaseUrl) {
  try {
    itemsRepo = require('../repository/itemsPrismaRepository');
  } catch (e) {
    try { itemsRepo = require('../repository/itemsRepository'); } catch { itemsRepo = null; }
  }
}

export async function listItems() { if (itemsRepo) return itemsRepo.list(); return itemsModel.list(); }
export async function getItem(id: number | string) { if (itemsRepo) return itemsRepo.getById(id); return itemsModel.getById(id); }
export async function createItem(data: { name: string }) { if (itemsRepo) return itemsRepo.create(data); return itemsModel.create(data); }
export async function updateItem(id: number | string, data: { name?: string }) { if (itemsRepo) return itemsRepo.update(id, data); return itemsModel.update(id, data); }
export async function deleteItem(id: number | string) { if (itemsRepo) return itemsRepo.remove(id); return itemsModel.remove(id); }
