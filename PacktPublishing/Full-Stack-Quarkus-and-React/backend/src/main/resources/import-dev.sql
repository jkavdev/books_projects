INSERT INTO "users" ("id", "name", "password", "created", "version")
VALUES (0, 'admin', '$2a$10$kD/D5G7xHMhSf9dxb3CKz.sdZundzrhXOps1jgsl5S4z5WxhMAY3O', NOW(), 0)
    ON CONFLICT DO NOTHING;
INSERT INTO "user_roles" ("id", "role") VALUES (0, 'admin')
    ON CONFLICT DO NOTHING;
INSERT INTO "user_roles" ("id", "role") VALUES (0, 'user')
    ON CONFLICT DO NOTHING;
INSERT INTO "users" ("id", "name", "password", "created", "version")
VALUES (1, 'user', '$2a$10$7rGjh8aS9YT2Q4hiG3CHgerJ/S/EkaObGFvoypcK5E9g3yHtP63tG', NOW(), 0)
    ON CONFLICT DO NOTHING;
INSERT INTO "user_roles" ("id", "role") VALUES (1, 'user')
    ON CONFLICT DO NOTHING;
INSERT INTO "projects" ("id", "name", "user_id", "created", "version")
VALUES (0, 'Work', 1, NOW(), 0)
    ON CONFLICT DO NOTHING;

ALTER SEQUENCE IF EXISTS hibernate_sequence RESTART WITH 10;