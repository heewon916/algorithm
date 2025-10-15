-- dr_name, dr_id, lcns_id, hire_ymd, mcdp_cd, tlno 
-- 진료과가 CS이거나 GS인 
-- 의사이름, 의사id, 진료과, 고용일자 
-- 고용일자 기준 내림차순, 이름 기준 오름차순 정렬 
SELECT dr_name, dr_id, mcdp_cd, date_format(hire_ymd, '%Y-%m-%d')
from doctor  
where mcdp_cd = 'cs' or mcdp_cd = 'gs'
order by hire_ymd desc, dr_name asc; 