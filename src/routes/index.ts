import { Router } from 'express';
import itemsRouter from './items';
import postRouter from './post';

const router = Router();
router.use('/items', itemsRouter);
router.use('/post', postRouter);

export default router;
