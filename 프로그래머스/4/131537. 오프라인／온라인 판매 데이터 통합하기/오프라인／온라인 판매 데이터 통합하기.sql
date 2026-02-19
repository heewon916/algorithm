-- 2022년 3월 오프라인/온라인 
-- 판매날짜 상품Id 유저Id 판매량 
-- 오프라인 유저Id는 null 처리 
-- 판매일 > 상품Id > 유저Id 오름차순 정렬 
(SELECT DATE_FORMAT(SALES_DATE, '%Y-%m-%d') as SALES_DATE, PRODUCT_ID, USER_ID, SALES_AMOUNT
FROM ONLINE_SALE ONL
where month(sales_date) = 3 and year(sales_date) = 2022
)
UNION 
(SELECT DATE_FORMAT(SALES_DATE, '%Y-%m-%d') as SALES_DATE, PRODUCT_ID, NULL, SALES_AMOUNT
FROM OFFLINE_SALE OFF
where month(sales_date) = 3 and year(sales_date) = 2022)
ORDER BY SALES_DATE ASC, PRODUCT_ID ASC, USER_ID ASC; 