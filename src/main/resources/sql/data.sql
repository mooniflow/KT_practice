-- 외래 키 제약 조건 제거
ALTER TABLE code_detail DROP CONSTRAINT IF EXISTS code_detail_group_id_fkey;

-- 테이블 삭제
DROP TABLE IF EXISTS users CASCADE;
DROP TABLE IF EXISTS pets CASCADE;
DROP TABLE IF EXISTS code_detail CASCADE;
DROP TABLE IF EXISTS code_group CASCADE;
DROP TABLE IF EXISTS product CASCADE;
DROP TABLE IF EXISTS cart CASCADE;

-- 테이블 재생성
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
    ('Test User', 'test@example.com', 'test_password', '010-1234-5678', '서울특별시 강남구 역삼동', 'USER'),
    ('test', 'test@example.com', 'test', '010-1234-5678', '서울특별시 강남구 역삼동', 'USER');
    

CREATE TABLE pets (
    id SERIAL PRIMARY KEY,
    user_id BIGINT NOT NULL,
    name VARCHAR(255) NOT NULL,
    type VARCHAR(255) NOT NULL,
    age INT NOT NULL,
    photo VARCHAR(255),
    FOREIGN KEY (user_id) REFERENCES users(id)
);

-- 예시 데이터 삽입
INSERT INTO pets (user_id, name, type, age, photo)
VALUES
    (1, 'Buddy', 'Dog', 3, 'buddy.jpg'),
    (2, 'Mittens', 'Cat', 2, 'mittens.jpg');

CREATE TABLE code_group (
    group_id VARCHAR(50) PRIMARY KEY,
    group_name VARCHAR(100) NOT NULL,
    description TEXT
);

INSERT INTO code_group (group_id, group_name, description)
VALUES
    ('group1', 'Group 1', 'Description for Group 1'),
    ('group2', 'Group 2', 'Description for Group 2');

CREATE TABLE code_detail (
    code_id VARCHAR(50) PRIMARY KEY,
    group_id VARCHAR(50) REFERENCES code_group(group_id) ON DELETE CASCADE,
    code_name VARCHAR(100) NOT NULL,
    code_value VARCHAR(50) NOT NULL,
    sort_order INT DEFAULT 1,
    is_active BOOLEAN DEFAULT TRUE
);

INSERT INTO code_detail (code_id, group_id, code_name, code_value, sort_order, is_active)
VALUES
    ('code1', 'group1', 'Code Name 1', 'Value 1', 1, TRUE),
    ('code2', 'group1', 'Code Name 2', 'Value 2', 2, FALSE),
    ('code3', 'group2', 'Code Name 3', 'Value 3', 1, TRUE);

CREATE TABLE product (
    product_id SERIAL PRIMARY KEY,
    code_group_id VARCHAR(50) NOT NULL,
    code_id VARCHAR(50) NOT NULL,
    product_name VARCHAR(255) NOT NULL,
    product_description TEXT,
    product_price DECIMAL(10, 2) NOT NULL,
    FOREIGN KEY (code_group_id) REFERENCES code_group(group_id),
    FOREIGN KEY (code_id) REFERENCES code_detail(code_id)
);

INSERT INTO product (code_group_id, code_id, product_name, product_description, product_price)
VALUES
    ('group1', 'code1', 'Product 1', 'Description for Product 1', 19900),
    ('group1', 'code2', 'Product 2', 'Description for Product 2', 29900),
    ('group2', 'code3', 'Product 3', 'Description for Product 3', 39900);

CREATE TABLE cart (
    cart_id SERIAL PRIMARY KEY,
    user_id BIGINT NOT NULL,
    product_id BIGINT NOT NULL,
    quantity INT NOT NULL,
    total_price DECIMAL(10, 2) NOT NULL,
    FOREIGN KEY (user_id) REFERENCES users(id),
    FOREIGN KEY (product_id) REFERENCES product(product_id)
);

-- 예시 데이터 삽입
INSERT INTO cart (user_id, product_id, quantity, total_price)
VALUES
    (1, 1, 2, 39.98),
    (2, 2, 1, 29.99);