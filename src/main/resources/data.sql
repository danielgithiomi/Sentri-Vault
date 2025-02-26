SET search_path TO sentri_vault_schema;

INSERT INTO users (user_id, first_name, last_name, username, email, password)
VALUES ('USR001', 'SUPER', 'USER', 'SUPER', 'super@sentrivault.com', 'super@123');

INSERT INTO users (user_id, first_name, last_name, username, email, password)
VALUES ('USR002', 'ADMIN', 'MAIN', 'ADMIN', 'admin@sentrivault.com', 'admin@123');

INSERT INTO roles (role_name, role_description)
VALUES ('SUPER_USER', 'Full Access Rights'),
       ('ADMIN', 'Medium Access Rights'),
       ('USER', 'Minimal Access Rights');

INSERT INTO user_roles (user_id, role_id)
VALUES ('USR001', 1),
       ('USR002', 2);
