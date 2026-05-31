const itemsModel = require('../models/itemsModel');
const config = require('../config');
let itemsRepo = null;
if (config.databaseUrl) {
  try {
    itemsRepo = require('../repository/itemsRepository');
  } catch (e) {
    itemsRepo = null;
  }
}

async function listItems() {
  if (itemsRepo) return itemsRepo.list();
  return itemsModel.list();
}

async function getItem(id) {
  if (itemsRepo) return itemsRepo.getById(id);
  return itemsModel.getById(id);
}

async function createItem(data) {
  if (itemsRepo) return itemsRepo.create(data);
  return itemsModel.create(data);
}

async function updateItem(id, data) {
  if (itemsRepo) return itemsRepo.update(id, data);
  return itemsModel.update(id, data);
}

async function deleteItem(id) {
  if (itemsRepo) return itemsRepo.remove(id);
  return itemsModel.remove(id);
}

module.exports = { listItems, getItem, createItem, updateItem, deleteItem };
