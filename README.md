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

- `POST /api/post` — echo posted object (returns id + createdAt)

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

ORM (Prisma):

- A Prisma schema is added at `prisma/schema.prisma` with an `Item` model.
- Run the following to set up Prisma client and migrations (with `DATABASE_URL` in `.env`):

```bash
npm run prisma:generate
npm run prisma:migrate:dev
npm run prisma:studio
```

After running migrations, the app will use Prisma repository automatically when `DATABASE_URL` is present.

To try with Postgres, set `DATABASE_URL` in `.env` and run migrations (create `items` table).
