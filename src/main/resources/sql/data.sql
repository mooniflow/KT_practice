DROP TABLE IF EXISTS users;

DROP TABLE IF EXISTS users;

CREATE TABLE users (
    id SERIAL PRIMARY KEY,
    username VARCHAR(255) NOT NULL,
    email VARCHAR(255) NOT NULL,
    password VARCHAR(255) NOT NULL,
    phone VARCHAR(255) NOT NULL,
    address VARCHAR(255) NOT NULL,
    role VARCHAR(255) NOT NULL
);

INSERT INTO users (username, email, password, phone, address, role)
VALUES
    ('Admin User', 'admin@example.com', 'admin_password', '010-1234-5678', '서울특별시 강남구 역삼동', 'ADMIN'),
    ('Test User', 'test@example.com', 'test_password', '010-1234-5678', '서울특별시 강남구 역삼동', 'USER');

CREATE TABLE pets (
    id SERIAL PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    type VARCHAR(255) NOT NULL,
    age INT NOT NULL,
    photo VARCHAR(255)
);