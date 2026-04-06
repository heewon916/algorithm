-- 자동차종류가 suv
-- 
SELECT round(avg(daily_fee)) as average_fee
from CAR_RENTAL_COMPANY_CAR 
where car_type = "SUV";