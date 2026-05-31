# FXStatic Project — Documentation

## Overview
A Spring Boot application providing user authentication (JWT), role-based admin operations, post/category/document management. Core packages: controllers, service.impl, repository, models, dto, jwt, config.

## Controllers
- `AuthController` (/api/auth)
  - POST `/public/signup` — register user
  - POST `/public/signin` — authenticate and return JWT
  - GET `/user` — get current authenticated user info
- `AdminController` (/api/admin)
  - GET `/getusers` — list all users
  - GET `/user/{id}` — get user details as `UserDTO`
  - PUT endpoints — update role, lock status, expiry, enabled, credentials expiry, password
- `PostController` (/api/post)
  - POST `/` — create post (authenticated)
  - PUT `/ {id}` — update post
  - DELETE `/{id}` — delete post
  - GET `/get/all` — list all posts (internal)
  - GET `/public/get/latest` — list latest posts (public)
  - GET `/public/get/{id}` — get post by id (public)
- `CategoryController` (/api/category)
  - POST `/` — create category
  - DELETE `/{id}` — delete category
  - GET `public/get/all` — list categories (public)
  - GET `get/{id}` — get category
  - POST `public/get/post` — get posts for a category (by `CategoryIdDto`)
- `DocumentController` (/api/document)
  - POST `/upload` — upload file for a post (multipart)
  - POST `/get` — fetch document by id

## Services (implementation classes)
Located under `service.impl`.
- `UserServiceImpl` — user operations: find by username/email, list users, update role, lock/expiry/enabled flags, update password, convert to `UserDTO`.
- `PostServiceImpl` — create/update/delete posts; maps `PostReqDto` to `Post`; uses `CategoryPostImpl` to link categories; returns `PostResDto` for responses and increments view counts.
- `CategoryImpl` — category CRUD and method to list posts for a category via `CategoryPostRepo` custom query.
- `CategoryPostImpl` — manages many-to-many mapping between posts and categories; provides `add`, `getAllPostCategories`.
- `DocumentImpl` — stores file bytes in `Document` entity and links to `Post`.
- `UserDetailsServiceImpl` & `UserDetailsImpl` — used by Spring Security for authentication principal mapping.

## Repositories
- `UserRepo`, `RoleRepo` — user and role persistence
- `PostRepo` — posts, includes `getLatest()` custom query
- `DocumentRepository` — documents, save/find
- `CategoryRepo`, `CategoryPostRepo` — categories and join table queries

## Models / Entities
- `User` — username, email, password, account flags (locked/expired/credentials), 2FA fields, `Role`, timestamps, `posts` list
- `Role` / `AppRole` — role enum and entity
- `Post` — title, context, description, timestamps, view count, `User` owner, `Document` list, category mapping
- `Document` — filename, content bytes, linked `Post`
- `Category` / `CategoryPost` — category entity and link entity for many-to-many relationship

## DTOs
- `PostReqDto` — fields required to create/update a post (title, context, description, categories)
- `PostResDto` — response DTO for posts (includes categories, document info, user)
- `UserDTO` — admin-facing user details
- `CategoryIdDto`, `CategoryOfPostDto`, `DatabaseDto` — used for category ↔ post queries and responses

## JWT / Security Flow
- `JwtUtils` — generates JWT with subject = username and claims: roles, is2faEnabled; reads `spring.app.jwtSecret` and `spring.app.jwtExpirationMs` from `application.properties`.
- `AuthTokenFilter` — reads `Authorization` header, validates token using `JwtUtils`, loads `UserDetails` via `UserDetailsServiceImpl`, sets `SecurityContext`.
- `AuthEntryPointJwt` — custom unauthorized JSON response.
- `SecurityConfig` — sets filter chain: permits endpoints under `/api/auth/public/**`, `/api/post/public/**`, `/api/category/public/**`; adds `AuthTokenFilter`; stateless sessions; `BCryptPasswordEncoder` bean; seeds roles and an `admin` user on startup.

## Notable Implementation Details & Recommendations
- JWT parsing/validation uses JJWT library calls — confirm the `pom.xml` JJWT version and test token generation/validation to avoid runtime mismatch.
- `SecurityConfig` contains `requestMatchers(HttpMethod.OPTIONS, "/api/admin/getusers").hasRole("admin")` — looks like a mistaken OPTIONS-only restriction; review intended matchers for admin endpoints.
- Many services throw `RuntimeException` for not-found cases; consider using `ResourceNotFoundException` consistently and return appropriate HTTP statuses.
- `CategoryPostImpl.add` avoids duplicates in-memory; consider DB unique constraint to prevent race-condition duplicates.
- `DocumentImpl` stores raw bytes in DB — monitor for large file sizes; consider external storage if needed.

## How to build & run (quick)
Run from project root (Maven):

```bash
mvn -DskipTests package
java -jar target/securtiy-0.0.1-SNAPSHOT.jar
```

## Migration to Node.js

- **Summary:** Strategy to migrate the backend from Spring Boot (Java) to Node.js (Express or NestJS). Keep the existing data store (Postgres/MySQL) and migrate schema and business logic incrementally.

- **High-level steps:**
  1. **Assess**: inventory controllers, services, repositories, models and DTOs (this doc already contains that).
  2. **Choose framework**: `Express` for lightweight control or `NestJS` for structured, DI-based apps.
  3. **Scaffold project**: create a Node project and install core libraries (web framework, ORM, auth, file handling).
  4. **Map layers**: controllers → routes/controllers, services → service classes, repositories → TypeORM/Prisma models, entities → ORM entities.
  5. **Auth**: port JWT flow using `jsonwebtoken` and `bcrypt`; implement JWT middleware equivalent to `AuthTokenFilter`.
  6. **File storage**: consider moving from DB blobs to filesystem or cloud storage (S3) and implement upload/download endpoints with `multer`.
  7. **Database migration**: export schema, create migrations (TypeORM migrations or Prisma Migrate), and verify relations (many-to-many category-post join table).
  8. **Testing & CI**: port/implement unit and integration tests; add linter and CI pipeline.
  9. **Incremental migration**: run Node.js API alongside Java app; migrate endpoints one-by-one (start with auth + user management), update clients gradually.
  10. **Data migration**: export existing data and import, or write transform scripts; validate with test data.

- **Quick scaffold commands (Express + TypeScript + TypeORM example):**

```bash
mkdir fxstatic-node && cd fxstatic-node
npm init -y
npm install express typeorm reflect-metadata pg bcrypt jsonwebtoken multer
npm install -D typescript ts-node-dev @types/express @types/node
npx tsc --init
```

- **Quick scaffold (NestJS):**

```bash
npm i -g @nestjs/cli
nest new fxstatic
```

- **Notes & risks:** manual rewrite required for business logic; watch for differences in transaction management, validation, and security defaults. Prefer migrating auth and user model first, then posts and documents.

## Next steps I can take
- Produce an architecture diagram (endpoints → services → repos).
- Run `mvn package` to surface compile errors and report fixes.
- Create a short Postman collection or exported endpoint list.
 - Create a detailed Node.js migration plan and scaffold project.

---
Generated by codebase scan on 2026-05-31.
