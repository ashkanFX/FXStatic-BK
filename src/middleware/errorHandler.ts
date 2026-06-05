import { Request, Response, NextFunction } from 'express';
import { ZodError } from 'zod';
import { Prisma } from '@prisma/client';
import * as logger from '../utils/logger';

interface ErrorResponse {
  error: string;
  details?: unknown;
}

export default function errorHandler(err: unknown, _req: Request, res: Response, _next: NextFunction) {
  let statusCode = 500;
  const body: ErrorResponse = { error: 'Internal Server Error' };

  if (err instanceof ZodError) {
    statusCode = 400;
    body.error = 'Validation failed';
    body.details = err.issues;
  } else if (err instanceof Prisma.PrismaClientKnownRequestError) {
    switch (err.code) {
      case 'P2002':
        statusCode = 409;
        body.error = 'Resource already exists';
        break;
      case 'P2025':
        statusCode = 404;
        body.error = 'Resource not found';
        break;
      case 'P2003':
        statusCode = 400;
        body.error = 'Foreign key constraint failed';
        break;
      default:
        statusCode = 400;
        body.error = 'Database request error';
    }
  } else if (err instanceof Prisma.PrismaClientValidationError) {
    statusCode = 400;
    body.error = 'Invalid data provided';
  } else if (err instanceof Prisma.PrismaClientInitializationError) {
    statusCode = 503;
    body.error = 'Database unavailable';
  } else if (isJWTError(err)) {
    statusCode = 401;
    body.error = err.message;
  } else if (isMulterError(err)) {
    statusCode = 400;
    body.error = err.message;
  } else if (err instanceof SyntaxError && 'body' in err) {
    statusCode = 400;
    body.error = 'Malformed JSON in request body';
  } else if (isPostgresError(err)) {
    switch (err.code) {
      case '23505':
        statusCode = 409;
        body.error = 'Resource already exists';
        break;
      case '23503':
        statusCode = 400;
        body.error = 'Foreign key constraint failed';
        break;
      case '42P01':
        statusCode = 500;
        body.error = 'Database schema error';
        break;
      default:
        statusCode = 400;
        body.error = 'Database error';
    }
  } else if (err instanceof Error) {
    const e = err as Error & { status?: number; statusCode?: number };
    statusCode = e.status || e.statusCode || 500;
    body.error = e.message || 'Internal Server Error';
  }

  if (statusCode >= 500) {
    logger.error(err);
  }

  res.status(statusCode).json(body);
}

function isJWTError(err: unknown): err is Error & { message: string } {
  return err instanceof Error && (err.constructor.name === 'JsonWebTokenError' || err.constructor.name === 'TokenExpiredError' || err.constructor.name === 'NotBeforeError');
}

function isMulterError(err: unknown): err is Error & { message: string; code: string } {
  return err instanceof Error && err.constructor.name === 'MulterError';
}

function isPostgresError(err: unknown): err is { code: string; message: string } {
  return err instanceof Error && 'code' in err && typeof (err as any).code === 'string' && err.constructor.name === 'DatabaseError';
}
