# Java Web Services — Secured Blog API

Individual project for the Java Web Services course: a REST API for a blog system, secured end-to-end with Keycloak for authentication and authorization.

**What it does:**
- CRUD for blog posts — list all posts and a specific post (open to any authenticated user), create, update, and delete (restricted by role and ownership)
- Role-based access control: regular users can manage their own posts, admins can access additional endpoints (e.g. post count) regardless of ownership

**Security:**
- Keycloak as the identity provider (OAuth2 resource server)
- Access controlled via `@PreAuthorize` / `SecurityFilterChain` based on role and resource ownership
- Verified end-to-end with Postman: requesting a token from Keycloak, calling protected endpoints with it, and confirming unauthorized calls are correctly blocked

**Tech stack:** Java 21, Spring Boot 3.5.5, Spring Security, Spring Data JPA, Spring OAuth2 Resource Server, Keycloak, H2.

**Grade:** Väl Godkänt (VG), with written reflection on security and design submitted as part of the assignment.

**Known improvement:** `BlogPost` currently stores the author's name directly as well as their id. The cleaner design — and the one planned — is to link posts to authors by `authorId` only, looking up the name when needed, so a name change doesn't require updating every post that author has written.
