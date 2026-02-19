-- 코드를 작성해주세요-- 코드를 작성해주세요
select parent.id, count(child.id) as child_count 
from ecoli_data parent left join ecoli_data child on parent.id = child.parent_id 
group by parent.id; 