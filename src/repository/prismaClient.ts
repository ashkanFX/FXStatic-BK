import { PrismaClient } from '@prisma/client';
import prismaConfig from '../prisma.config';

// PrismaClient constructor in Prisma 7 accepts config for adapter/accelerate.
// Cast to `any` to avoid tight typing here — the shape depends on Prisma runtime.
const prisma = new PrismaClient(prismaConfig as any);

export default prisma;
