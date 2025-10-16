-- 코드를 입력하세요
SELECT i.rest_id, i.rest_name, i.food_type, i.favorites, i.address, 
    round(avg(v.review_score), 2) as score
from rest_info i, rest_review v
where i.rest_id = v.rest_id  
and address like "서울%"
group by rest_id 
order by score desc, i.favorites desc; 

