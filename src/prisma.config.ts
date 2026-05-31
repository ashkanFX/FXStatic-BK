import 'dotenv/config';

// Prisma 7: move datasource URL out of schema and provide client config here.
// Adjust `adapter` (direct DB) or `accelerateUrl` (Prisma Accelerate) as needed.

const prismaConfig = {
  // For a direct PostgreSQL connection use `adapter` with the connection URL
  adapter: {
    provider: 'postgresql',
    url: process.env.DATABASE_URL || undefined,
  },

  // If you use Prisma Accelerate, set `accelerateUrl` instead:
  // accelerateUrl: process.env.PRISMA_ACCELERATE_URL || undefined,
};

export default prismaConfig;
