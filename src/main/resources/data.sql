SET search_path TO sentri_vault_schema;

INSERT INTO users (user_id, first_name, last_name, username, email, password)
           VALUES ('USR001', 'ASDWSDF', 'DQFQWQ', 'sgeweer', 'test@gmail.com', 'r3454er'

INSERT INTO roles (role_name, role_description)
           VALUES ('USER', 'Minimal Access Rights'),
                  ('ADMIN', 'Medium Access Rights'),
                  ('SUPER_USER', 'Full Access Rights');

INSERT INTO user_roles (user_id, role_id)
                VALUES ('USR001', 1);
