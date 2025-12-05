select id, email, first_name, last_name 
from developers d join (select sum(code) as sumCode from skillcodes where name="Python" or name="C#") as s
where d.skill_code & s.sumCode > 0 
order by id asc; 