-- 부모에서 타고 내려가는 방식
-- parent_item_id -> 업그레이드 -> item_id 
-- rare인 아이템에 대해서 
-- 부모 아이템의 id, name, rarity를 출력하기

select t.item_id, i.item_name, i.rarity
from item_tree t join item_info i on t.item_id = i.item_id
where t.parent_item_id in (
    select t.item_id
    from item_info i join item_tree t on i.item_id = t.item_id 
    where i.rarity = 'RARE'
)
order by t.item_id desc;
