import { Router } from 'express';
import * as usersController from '../controllers/usersController';
import { validateUser } from '../middleware/validate';

const router = Router();

router.get('/', usersController.listUsers);
router.get('/:id', usersController.getUser);
router.post('/', validateUser, usersController.createUser);
router.put('/:id', validateUser, usersController.updateUser);
router.delete('/:id', usersController.deleteUser);

export default router;
