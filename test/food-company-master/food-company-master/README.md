# 온라인 매장 음료 리스트

본 문서는 온라인 매장에서 **음료 리스트(`DrinkList.jsx`)** 데이터를 불러오지 못하는 문제를 해결하기 위한 가이드입니다.  
이 문서를 참고하여 **프로젝트를 실행하고**, **백엔드 API가 정상적으로 동작하는지 확인**한 후, **발생하는 문제를 분석**하세요.


## ✅ 데이터 추가 삽입

### `drinks` 테이블 데이터 추가
```sql
INSERT INTO drinks (
    drink_name, drink_brand, drink_volume_ml, drink_price, drink_stock, 
    drink_category, drink_sugar_free, drink_caffeine, drink_expiration_date
) VALUES
('스타벅스 카페라떼', '스타벅스', 250, 3300.00, 60, '커피', FALSE, TRUE, '2025-07-15'),
('옥수수 수염차', '웅진식품', 500, 1500.00, 90, '차', TRUE, FALSE, '2025-10-10'),
('포카리 스웨트', '동아오츠카', 620, 2100.00, 75, '스포츠음료', FALSE, FALSE, '2025-09-05');
```

### `snacks` 테이블 데이터 추가
```sql
INSERT INTO snacks (
    snack_name, snack_brand, snack_weight_g, snack_price, snack_stock, 
    snack_category, snack_spicy, snack_allergy_info, snack_expiration_date
) VALUES
('츄파춥스 딸기맛', '페레로', 15, 500.00, 200, '사탕', FALSE, '설탕, 합성향료', '2026-01-10'),
('허니버터칩', '해태', 60, 1700.00, 95, '과자', FALSE, '밀, 감자, 우유', '2025-10-05'),
('땅콩강정', '국내중소기업', 200, 5000.00, 50, '견과류', FALSE, '땅콩, 설탕', '2026-03-12'),
('불닭맛 스낵', '삼양', 100, 2200.00, 70, '과자', TRUE, '밀, 우유, 매운 양념', '2025-09-20');
```



## 리액트 프로젝트 실행 방법

### 1. 프로젝트 클론
```sh
git clone https://github.com/kht241023/food-company.git
```

### 2. 프로젝트 폴더로 이동
```sh
cd food-company
```

### 3. 패키지 설치
```sh
npm install
```

### 4. 프로젝트 실행
```sh
npm start
```

✔️ 실행 후 브라우저에서 `http://localhost:3000`으로 접속하여 화면을 확인하세요.

---

## 🔎 백엔드 API 정상 작동 여부 확인 (Postman 사용)

리액트가 데이터를 불러오지 못할 경우, 백엔드 API가 정상적으로 동작하는지 확인해야 합니다.  
Postman을 사용하여 직접 API 요청을 보내고 응답을 확인하세요.

### 1. Postman 실행
Postman을 실행하여 Food-Company 전용 폴더를 생성합니다.

### 2️⃣ 백엔드 서버 실행

### 3️⃣ Postman에서 API 요청 보내기

1. Postman을 실행합니다.
2. **새 요청(New Request)**을 생성합니다.
3. 응답을 확인합니다.

### 4. API 응답 확인
✔️ 정상적인 경우:
```json
[
    {
        "drink_id": 1,
        "drink_name": "코카콜라",
        "drink_brand": "코카콜라",
        "drink_volume_ml": 500,
        "drink_price": 1800.00,
        "drink_stock": 100,
        "drink_category": "탄산음료",
        "drink_sugar_free": false,
        "drink_caffeine": true,
        "drink_expiration_date": "2025-12-31"
    }
]
```
✔️ 오류 발생 시:
- **500 Internal Server Error** → 백엔드 로직 문제 (로그 확인 필요)
- **404 Not Found** → API 경로 오류 (프론트엔드에서 잘못된 URL 호출 가능)
- **401 Unauthorized** → 인증 문제 (토큰 필요 여부 확인)
