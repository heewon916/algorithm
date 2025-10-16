-- 자동차 종류가 '세단' 또는 'SUV' 인 자동차 => car_rental_company_car 테이블 
-- 2022년 11월 1일부터 2022년 11월 30일까지 대여 가능하고 => car_rental_company_rental_history에서 end_date <= 20221101 && start_date <=
-- 30일간의 대여 금액 => duration_type = 30일 이상 
-- 50만원 이상 200만원 미만인 자동차에 대해서 = daily_fee * 30 * (100 - discount_rate)*0.01
-- 자동차 ID, 자동차 종류, 대여 금액(컬럼명: FEE) 리스트를 출력하는 SQL문
# from - where - group by - having - select - order by 
SELECT c.car_id, 
    c.car_type, 
    round(c.daily_fee * 30 * (100 - p.discount_rate)/ 100) as fee 
from car_rental_company_car c, car_rental_company_discount_plan p
where c.car_type = p.car_type and p.duration_type = '30일 이상' 
and c.car_type in ('세단', 'SUV')
and c.car_id not in 
    (select car_id 
     from car_rental_company_rental_history
     where end_date >= '2022-11-01' and start_date <= '2022-11-30')
having fee >= 500000 and fee <= 2000000
order by fee desc, c.car_type asc, c.car_id desc; 
