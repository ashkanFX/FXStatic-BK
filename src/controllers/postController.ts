import { Request, Response, NextFunction } from 'express';
let nextPostId = 1;

export async function createPost(req: Request, res: Response, _next: NextFunction) {
  const payload = req.body || {};
  const obj = { id: nextPostId++, ...payload, createdAt: new Date().toISOString() };
  res.status(201).json(obj);
}
