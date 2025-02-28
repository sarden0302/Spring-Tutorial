# 온라인 매장 음료 및 과자 데이터베이스 설정

이 문서는 온라인 매장에서 판매되는 **음료(`drinks`)** 및 **과자(`snacks`)** 데이터를 저장하기 위한 MySQL 데이터베이스 및 테이블을 설정하는 방법을 설명합니다.

---

## 1. MySQL 데이터베이스 및 사용자 설정

### 1.1 MySQL 접속
먼저 MySQL 서버에 접속합니다.

```sh
mysql -u root -p
```
비밀번호를 입력하여 관리자 계정으로 로그인합니다.

### 1.2 데이터베이스 생성
새로운 데이터베이스 `testdb` 를 생성합니다.

```sql
CREATE DATABASE testdb;
```
만약 이미 존재하는 경우, 오류가 발생할 수 있습니다. 기존 데이터베이스가 있는 경우 건너뛰어도 됩니다.

### 1.3 사용자 생성 및 권한 부여
새로운 MySQL 사용자 계정을 생성하고 `testdb` 데이터베이스에 대한 모든 권한을 부여합니다.

```sql
CREATE USER 'testkht'@'%' IDENTIFIED BY 'kht1234';
GRANT ALL PRIVILEGES ON testdb.* TO 'testkht'@'%';
FLUSH PRIVILEGES;
```

---

## 2. 데이터베이스 테이블 생성

### 2.1 데이터베이스 선택
```sql
USE storedb;
```

### 2.2 `drinks` (음료 테이블) 생성
```sql
CREATE TABLE drinks (
    drink_id         BIGINT AUTO_INCREMENT PRIMARY KEY COMMENT '음료 고유 ID',
    drink_name       VARCHAR(255) NOT NULL COMMENT '음료 이름',
    drink_brand      VARCHAR(255) NOT NULL COMMENT '음료 브랜드 / 제조사',
    drink_volume_ml  INT NOT NULL COMMENT '음료 용량 (ml 단위)',
    drink_price      DECIMAL(10,2) NOT NULL COMMENT '음료 가격 (원화 기준)',
    drink_stock      INT NOT NULL DEFAULT 0 COMMENT '음료 재고 수량',
    drink_category   ENUM('탄산음료', '주스', '커피', '차', '스포츠음료', '기타') NOT NULL COMMENT '음료 카테고리',
    drink_sugar_free BOOLEAN NOT NULL DEFAULT FALSE COMMENT '무가당 여부 (TRUE: 무가당, FALSE: 일반)',
    drink_caffeine   BOOLEAN NOT NULL DEFAULT FALSE COMMENT '카페인 포함 여부',
    drink_expiration_date DATE NOT NULL COMMENT '음료 유통기한',
    created_at       TIMESTAMP DEFAULT CURRENT_TIMESTAMP COMMENT '음료 데이터 생성일',
    updated_at       TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '음료 데이터 수정일'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='온라인 매장 음료 정보 테이블';
```

### 2.3 `snacks` (과자 테이블) 생성
```sql
CREATE TABLE snacks (
    snack_id         BIGINT AUTO_INCREMENT PRIMARY KEY COMMENT '과자 고유 ID',
    snack_name       VARCHAR(255) NOT NULL COMMENT '과자 이름',
    snack_brand      VARCHAR(255) NOT NULL COMMENT '과자 브랜드 / 제조사',
    snack_weight_g   INT NOT NULL COMMENT '과자 중량 (g 단위)',
    snack_price      DECIMAL(10,2) NOT NULL COMMENT '과자 가격 (원화 기준)',
    snack_stock      INT NOT NULL DEFAULT 0 COMMENT '과자 재고 수량',
    snack_category   ENUM('과자', '쿠키', '초콜릿', '사탕', '견과류', '기타') NOT NULL COMMENT '과자 카테고리',
    snack_spicy      BOOLEAN NOT NULL DEFAULT FALSE COMMENT '매운맛 여부',
    snack_allergy_info TEXT COMMENT '알러지 정보 (예: 우유, 땅콩, 밀 포함 여부 기재)',
    snack_expiration_date DATE NOT NULL COMMENT '과자 유통기한',
    created_at       TIMESTAMP DEFAULT CURRENT_TIMESTAMP COMMENT '과자 데이터 생성일',
    updated_at       TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '과자 데이터 수정일'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='온라인 매장 과자 정보 테이블';
```

---

## 3. 샘플 데이터 삽입

### 3.1 `drinks` 테이블 샘플 데이터 삽입
```sql
INSERT INTO drinks (
    drink_name, drink_brand, drink_volume_ml, drink_price, drink_stock, 
    drink_category, drink_sugar_free, drink_caffeine, drink_expiration_date
) VALUES
('코카콜라', '코카콜라', 500, 1800.00, 100, '탄산음료', FALSE, TRUE, '2025-12-31'),
('펩시제로', '펩시', 500, 2000.00, 80, '탄산음료', TRUE, TRUE, '2025-11-30'),
('제주 삼다수', '제주개발공사', 500, 1000.00, 200, '기타', TRUE, FALSE, '2026-06-01'),
('탐스 애플주스', '탐스', 350, 2200.00, 50, '주스', FALSE, FALSE, '2025-08-20');
```

### 3.2 `snacks` 테이블 샘플 데이터 삽입
```sql
INSERT INTO snacks (
    snack_name, snack_brand, snack_weight_g, snack_price, snack_stock, 
    snack_category, snack_spicy, snack_allergy_info, snack_expiration_date
) VALUES
('포카칩 오리지널', '오리온', 66, 1500.00, 120, '과자', FALSE, '감자, 소금', '2025-11-25'),
('새우깡', '농심', 90, 1200.00, 150, '과자', FALSE, '밀, 새우', '2025-10-30'),
('초코파이', '오리온', 336, 4800.00, 80, '쿠키', FALSE, '밀, 계란, 우유, 초콜릿', '2025-09-15'),
('빼빼로 오리지널', '롯데', 54, 1800.00, 100, '초콜릿', FALSE, '밀, 우유, 초콜릿', '2025-08-22');
```

---

## 4. 데이터 조회

### 4.1 `drinks` 테이블 데이터 조회
```sql
SELECT * FROM drinks;
```

### 4.2 `snacks` 테이블 데이터 조회
```sql
SELECT * FROM snacks;
```

---

## 5. MySQL 접속 방법
MySQL CLI 또는 MySQL Workbench를 사용하여 `testkht` 사용자로 `testdb` 데이터베이스에 접속하려면 다음 명령을 실행합니다.
```sh
mysql -u testkht -p -h [서버주소] testdb
```

비밀번호 입력 시 `kht1234`를 입력하면 접속할 수 있습니다.

---

## 6. 주요 컬럼 설명

### **공통 컬럼**
- `id` → 제품의 고유 식별번호 (`drink_id`, `snack_id`)
- `name` → 제품 이름 (`drink_name`, `snack_name`)
- `brand` → 제조사 (`drink_brand`, `snack_brand`)
- `price` → 가격 (`drink_price`, `snack_price`)
- `stock` → 재고 개수 (`drink_stock`, `snack_stock`)
- `expiration_date` → 유통기한 (`drink_expiration_date`)
- `created_at` / `updated_at` → 생성 및 수정 시간 (자동 저장)

### 🥤 **음료(`drinks`) 전용 컬럼**
- `drink_volume_ml` → 음료 용량 (ml)
- `drink_category` → 음료 종류 (`탄산음료`, `커피`, `차` 등)
- `drink_sugar_free` → 무가당 여부 (`TRUE`: 무가당, `FALSE`: 설탕 포함)
- `drink_caffeine` → 카페인 포함 여부 (`TRUE`: 포함, `FALSE`: 미포함)

### 🍪 **과자(`snacks`) 전용 컬럼**
- `snack_weight_g` → 중량 (g)
- `snack_category` → 종류 (`쿠키`, `초콜릿` 등)
- `snack_spicy` → 매운맛 여부
- `snack_allergy_info` → 알러지 정보
