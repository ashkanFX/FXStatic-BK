import { Router } from 'express';
import itemsRouter from './items';
import postRouter from './post';
import usersRouter from './users';

const router = Router();
router.use('/items', itemsRouter);
router.use('/post', postRouter);
router.use('/users', usersRouter);

export default router;
