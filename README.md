# FXStatic-BK — Express API Scaffold

Quick start:

1. Install dependencies:

```bash
cd f:/project/ptMedia/FXStatic-BK
npm install
```

2. Run in development (requires `nodemon`):

```bash
npm run dev
```

3. Start normally:

```bash
npm start
```

Endpoints:

- `GET /health` — health check
- `GET /api/example` — example payload

Project structure (added):

- `src/`
	- `controllers/` — request handlers
	- `services/` — business logic
	- `models/` — data access placeholders
  - `repository/` — DB repositories (pg)
  - `routes/` — route definitions (index.js)
  - `middleware/` — express middleware (error handler)
  - `config/` — configuration
  - `utils/` — helpers (logger)
- `tests/` — test files
- `.env.example` — example env vars

Database support:

- `src/repository/db.js` — Postgres `pg` pool helper
- `src/repository/itemsRepository.js` — SQL-based CRUD for `items` table
- `itemsService` will use the DB repository when `DATABASE_URL` is set, otherwise it falls back to the in-memory model.

To try with Postgres, set `DATABASE_URL` in `.env` and run migrations (create `items` table).
