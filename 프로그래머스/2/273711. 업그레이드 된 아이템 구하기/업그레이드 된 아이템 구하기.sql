# a->b 업그레이드
# parent: a/ child: b 
# rare 아이템의 -> 다음 업그레이드 아이템 id, name, rarity 
select item_id, item_name, rarity
from item_info
where item_id in (
        select t.item_id
        from item_info i join item_tree t on i.item_id = t.parent_item_id
        where rarity = "RARE"
    )
order by item_id desc; 
