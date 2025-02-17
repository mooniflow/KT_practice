-- 외래 키 제약 조건 제거
ALTER TABLE code_detail DROP CONSTRAINT IF EXISTS code_detail_group_id_fkey;

-- 테이블 삭제
DROP TABLE IF EXISTS users CASCADE;
DROP TABLE IF EXISTS pets CASCADE;
DROP TABLE IF EXISTS code_detail CASCADE;
DROP TABLE IF EXISTS code_group CASCADE;
DROP TABLE IF EXISTS product CASCADE;
DROP TABLE IF EXISTS cart CASCADE;
DROP TABLE IF EXISTS schedule CASCADE;
DROP TABLE IF EXISTS booking CASCADE;
DROP TABLE IF EXISTS pet_sitter CASCADE;
DROP TABLE IF EXISTS petsitter_certifications CASCADE;
DROP TABLE IF EXISTS petsitter_available_times CASCADE;

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

-- 펫시터 테이블 생성
CREATE TABLE IF NOT EXISTS pet_sitter (
    id SERIAL PRIMARY KEY,
    user_id BIGINT NOT NULL,
    name VARCHAR(255) NOT NULL,
    location VARCHAR(255),
    experience VARCHAR(255),
    services VARCHAR(255),
    pet_size VARCHAR(20),
    price INT,
    is_active BOOLEAN DEFAULT true,
    introduction TEXT,
    phone VARCHAR(20),
    FOREIGN KEY (user_id) REFERENCES users(id)
);

-- 펫시터 인증서 테이블 생성
CREATE TABLE IF NOT EXISTS petsitter_certifications (
    pet_sitter_id BIGINT,
    certifications VARCHAR(255),
    FOREIGN KEY (pet_sitter_id) REFERENCES pet_sitter(id)
);

-- 펫시터 가능 시간 테이블 생성
CREATE TABLE IF NOT EXISTS petsitter_available_times (
    id SERIAL PRIMARY KEY,
    pet_sitter_id BIGINT,
    start_time TIMESTAMP NOT NULL,
    end_time TIMESTAMP NOT NULL,
    FOREIGN KEY (pet_sitter_id) REFERENCES pet_sitter(id)
);

-- 스케줄 테이블 생성
CREATE TABLE IF NOT EXISTS schedule (
    id SERIAL PRIMARY KEY,
    sitter_id BIGINT NOT NULL,
    start_time TIMESTAMP NOT NULL,
    end_time TIMESTAMP NOT NULL,
    FOREIGN KEY (sitter_id) REFERENCES pet_sitter(id)
);

-- 예약 테이블 생성
CREATE TABLE IF NOT EXISTS booking (
    id SERIAL PRIMARY KEY,
    user_id BIGINT NOT NULL,
    pet_id BIGINT NOT NULL,
    sitter_id BIGINT NOT NULL,
    start_time TIMESTAMP NOT NULL,
    end_time TIMESTAMP NOT NULL,
    service VARCHAR(255),
    status VARCHAR(20),
    location VARCHAR(255),
    price INT,
    FOREIGN KEY (user_id) REFERENCES users(id),
    FOREIGN KEY (pet_id) REFERENCES pets(id),
    FOREIGN KEY (sitter_id) REFERENCES pet_sitter(id)
);

-- 펫시터 예시 데이터
INSERT INTO pet_sitter (user_id, name, location, experience, services, pet_size, price, is_active, introduction, phone)
VALUES
    (2, '김펫시터', '서울시 강남구', '3년', '산책,돌봄', 'MEDIUM', 30000, true, '안녕하세요. 반려동물을 사랑하는 펫시터입니다.', '010-1234-5678'),
    (3, '이펫시터', '서울시 서초구', '2년', '산책,돌봄', 'SMALL', 25000, true, '안녕하세요. 반려동물을 사랑하는 펫시터입니다.', '010-1234-5678');

-- 펫시터 인증서 예시 데이터
INSERT INTO petsitter_certifications (pet_sitter_id, certifications)
VALUES
    (1, '반려동물관리사 자격증'),
    (1, '반려동물행동교정사 자격증'),
    (2, '반려동물관리사 자격증');

-- 펫시터 가능 시간 예시 데이터
INSERT INTO petsitter_available_times (pet_sitter_id, start_time, end_time)
VALUES
    (1, '2024-03-25 09:00:00', '2024-03-25 18:00:00'),
    (1, '2024-03-26 09:00:00', '2024-03-26 18:00:00'),
    (2, '2024-03-25 10:00:00', '2024-03-25 19:00:00');

-- 예약 예시 데이터 추가
INSERT INTO booking (user_id, pet_id, sitter_id, start_time, end_time, service, status, location, price)
VALUES
    (1, 1, 2, '2024-03-25 10:00:00', '2024-03-25 12:00:00', '산책', 'PENDING', '서울특별시 강남구 역삼동', 30000),
    (2, 2, 2, '2024-03-26 14:00:00', '2024-03-26 16:00:00', '돌봄', 'APPROVED', '서울특별시 강남구 역삼동', 30000);
