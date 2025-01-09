INSERT INTO users (user_id, first_name, last_name, username, email, password)
           VALUES ('USR001', 'Andy', 'Chiffonee', 'ANDCHI', 'andy.chif@mcb.mu', 'jaysen123');

INSERT INTO roles (name, description)
           VALUES ('Manager', 'Top Level'),
                  ('Assistant', 'Low Level');

INSERT INTO user_roles (user_id, role_id)
                VALUES ('USR001', 1);