const express = require('express');
const router = express.Router();
const itemsController = require('../controllers/itemsController');
const { validateItem } = require('../middleware/validate');

router.get('/', itemsController.listItems);
router.get('/:id', itemsController.getItem);
router.post('/', validateItem, itemsController.createItem);
router.put('/:id', validateItem, itemsController.updateItem);
router.delete('/:id', itemsController.deleteItem);

module.exports = router;
