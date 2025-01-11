CREATE SCHEMA IF NOT EXISTS sentri_vault_schema;

SET TIMEZONE = '+4:00';

SET search_path TO sentri_vault_schema;

DROP TABLE IF EXISTS users, roles, user_roles;

CREATE TABLE IF NOT EXISTS users
(
    user_id VARCHAR(10) NOT NULL,
    first_name VARCHAR(50) NOT NULL,
    last_name VARCHAR(50) NOT NULL,
    username VARCHAR(50) NOT NULL,
    email VARCHAR(100) NOT NULL,
    password VARCHAR(20) NOT NULL,
    age INTEGER DEFAULT NULL,
    phone BIGINT DEFAULT NULL,
    is_verified BOOLEAN NOT NULL DEFAULT false,
    is_locked BOOLEAN NOT NULL DEFAULT false,
    image_url VARCHAR(255) DEFAULT 'https://www.strasys.uk/wp-content/uploads/2022/02/Depositphotos_484354208_S.jpg',
    created_at TIMESTAMP NOT NULL DEFAULT now(),
    last_updated TIMESTAMP DEFAULT NULL,
    PRIMARY KEY (user_id),
    CONSTRAINT UQ_USER_USERNAME UNIQUE (username),
    CONSTRAINT UQ_USER_EMAIL UNIQUE (email)
);

CREATE TABLE IF NOT EXISTS roles
(
    role_id SERIAL NOT NULL,
    role_name VARCHAR(10) NOT NULL,
    role_description VARCHAR(100) NOT NULL,
    PRIMARY KEY (role_id)
);

CREATE TABLE IF NOT EXISTS user_roles
(
    id SERIAL NOT NULL,
    user_id VARCHAR(10) NOT NULL,
    role_id INTEGER NOT NULL,
    PRIMARY KEY (id),
    CONSTRAINT FK_USER_ROLE
        FOREIGN KEY (user_id)
        REFERENCES users (user_id)
        ON UPDATE CASCADE
        ON DELETE CASCADE,
    CONSTRAINT FK_ROLE_USER
        FOREIGN KEY (role_id)
        REFERENCES roles (role_id)
        ON UPDATE CASCADE
        ON DELETE RESTRICT
);