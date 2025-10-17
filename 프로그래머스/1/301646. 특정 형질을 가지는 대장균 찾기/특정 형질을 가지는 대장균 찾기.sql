-- 코드를 작성해주세요
select count(*) as 'count'
from ecoli_data 
where (genotype & 2) = 0 and (genotype & 5) > 0;

-- 2번 보유할 때: 0010 
-- 3번 보유할 때: 0100 
-- 1번 보유할 때: 0001