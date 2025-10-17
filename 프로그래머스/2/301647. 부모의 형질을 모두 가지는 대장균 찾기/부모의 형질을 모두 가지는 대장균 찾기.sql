-- 코드를 작성해주세요
-- 부모가 있는 개체에 대해서 
-- 그 부모의 형질을 구해서
-- & 연산해서 그게 부모의 형질과 같거나 크다면 
-- 부모의 형질을 모두 보유한 개체라고 할 수 있다.

# select a.id, a.genotype, b.genotype
# from ecoli_data a, (select id, genotype from ecoli_data where parent_id = id) as b 
# where a.parent_id is not null
# and a.genotype & b.genotype >= b.genotype; 

select b.id, b.genotype, a.genotype as parent_genotype
from ecoli_data a, ecoli_data b 
where a.id = b.parent_id
and a.genotype & b.genotype = a.genotype
order by b.id; 