import 'dotenv/config';

const config = {
  port: Number(process.env.PORT) || 3000,
  env: process.env.NODE_ENV || 'development',
  corsOrigin: process.env.CORS_ORIGIN || 'http://localhost:4200',
  databaseUrl: process.env.DATABASE_URL || null
};

export default config;
