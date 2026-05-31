const { Pool } = require('pg');
const config = require('../config');

let pool = null;
if (config.databaseUrl) {
  pool = new Pool({ connectionString: config.databaseUrl });
} else {
  // graceful: create a dummy pool-like interface that throws on use
  pool = {
    query() { throw new Error('No DATABASE_URL configured'); }
  };
}

module.exports = { pool, query: (text, params) => pool.query(text, params) };