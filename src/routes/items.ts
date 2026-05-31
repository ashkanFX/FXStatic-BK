import { Router } from 'express';
import * as itemsController from '../controllers/itemsController';
import { validateItem } from '../middleware/validate';

const router = Router();

router.get('/', itemsController.listItems);
router.get('/:id', itemsController.getItem);
router.post('/', validateItem, itemsController.createItem);
router.put('/:id', validateItem, itemsController.updateItem);
router.delete('/:id', itemsController.deleteItem);

export default router;
