-- skillcodes 테이블: name, category, code 
-- developers 테이블: id, first_name, last_name, email, skill_code 
-- python 또는 c# 스킬을 가진 
-- 개발자의 id, 이메일, 이름, 성을 조회 
-- id 기준으로 오름차순 정렬 
select d.id, d.email, d.first_name, d.last_name 
from developers d 
join (select sum(sc.code) as mask 
     from skillcodes sc
     where name in ('Python', 'C#')) as s 
where (d.skill_code & s.mask) > 0
order by d.id;