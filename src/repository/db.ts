import { Pool } from 'pg';
import config from '../config';

let pool: Pool | null = null;
if (config.databaseUrl) {
  pool = new Pool({ connectionString: config.databaseUrl });
} else {
  // dummy: throw when used
  pool = null;
}

export default {
  query: (text: string, params?: any[]) => {
    if (!pool) throw new Error('No DATABASE_URL configured');
    return pool.query(text, params);
  }
};
