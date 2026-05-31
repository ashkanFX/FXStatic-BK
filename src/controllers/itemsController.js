const itemsService = require('../services/itemsService');

async function listItems(req, res, next) {
  try {
    const data = await itemsService.listItems();
    res.json(data);
  } catch (err) {
    next(err);
  }
}

async function getItem(req, res, next) {
  try {
    const item = await itemsService.getItem(req.params.id);
    if (!item) return res.status(404).json({ error: 'Not found' });
    res.json(item);
  } catch (err) {
    next(err);
  }
}

async function createItem(req, res, next) {
  try {
    const created = await itemsService.createItem(req.body);
    res.status(201).json(created);
  } catch (err) {
    next(err);
  }
}

async function updateItem(req, res, next) {
  try {
    const updated = await itemsService.updateItem(req.params.id, req.body);
    if (!updated) return res.status(404).json({ error: 'Not found' });
    res.json(updated);
  } catch (err) {
    next(err);
  }
}

async function deleteItem(req, res, next) {
  try {
    const ok = await itemsService.deleteItem(req.params.id);
    if (!ok) return res.status(404).json({ error: 'Not found' });
    res.status(204).end();
  } catch (err) {
    next(err);
  }
}

module.exports = { listItems, getItem, createItem, updateItem, deleteItem };
