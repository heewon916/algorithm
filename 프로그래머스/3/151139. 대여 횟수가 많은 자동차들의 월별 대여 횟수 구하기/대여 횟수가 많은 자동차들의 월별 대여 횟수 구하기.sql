-- 코드를 입력하세요
-- 대여 시작일을 기준으로 2022년 8월부터 2022년 10월까지 총 대여 횟수가 5회 이상인 자동차들
-- 해당 기간 동안의 월별 자동차 ID 별 총 대여 횟수(컬럼명: RECORDS) 리스트
-- 특정 월의 총 대여 횟수가 0인 경우에는 결과에서 제외
-- 대여 시작일을 기준으로 총 대여 횟수가 5회 이상인 자동차 -> 월마다 자동차의 대여 횟수가 5회 이상이 아니라, 8~10월 사이 전체 대여 횟수를 구해야 됨 

# 아 그러면 월별로 먼저 구해둘 게 아니라 
# 그 사이에 있던 모든 대여 횟수를 알아야 되네 

-- 자동차별로, 8월~10월 사이에 대여 횟수가 5회 이상인 CAR_ID 
-- 이런 자동차 ID들에 대해서 월별 대여 횟수를 또 구해야 되는 거네 

SELECT MONTH(START_DATE) AS MONTH, CAR_ID, COUNT(HISTORY_ID) AS RECORDS 
FROM CAR_RENTAL_COMPANY_RENTAL_HISTORY
WHERE YEAR(START_DATE) = '2022' AND MONTH(START_DATE) BETWEEN 8 AND 10 
AND CAR_ID IN (SELECT CAR_ID
                FROM CAR_RENTAL_COMPANY_RENTAL_HISTORY 
                WHERE YEAR(START_DATE) = '2022' AND MONTH(START_DATE) BETWEEN 8 AND 10 
                GROUP BY CAR_ID
                HAVING COUNT(HISTORY_ID) >= 5)
GROUP BY MONTH, CAR_ID
HAVING RECORDS > 0 
ORDER BY MONTH ASC, CAR_ID DESC;
