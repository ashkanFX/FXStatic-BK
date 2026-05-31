import { Request, Response, NextFunction, RequestHandler } from 'express';
import { ZodSchema, ZodError, z } from 'zod';

export function validate(schema: ZodSchema, property: 'body' | 'query' | 'params' = 'body'): RequestHandler {
  return (req: Request, res: Response, next: NextFunction) => {
    try {
      const result = schema.parse(req[property]);
      // assign the parsed value back (useful for transforms/defaults)
      (req as any)[property] = result;
      next();
    } catch (err) {
      if (err instanceof ZodError) {
        return res.status(400).json({ error: 'Validation failed', details: err.issues });
      }
      next(err);
    }
  };
}

// Backwards-compatible validator for items used in routes
const itemSchema = z.object({
  name: z.string().min(1, { message: 'Field "name" is required and must be a non-empty string' }),
});

export const validateItem = validate(itemSchema, 'body');
