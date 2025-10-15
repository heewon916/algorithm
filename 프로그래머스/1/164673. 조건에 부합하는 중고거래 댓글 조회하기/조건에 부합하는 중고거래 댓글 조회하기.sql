-- 코드를 입력하세요
-- 게시글 제목, 게시글 ID, 댓글ID, 댓글 작성자ID, 댓글내용, 댓글작성일 
SELECT ugb.title, ugb.board_id, ugr.reply_id, ugr.writer_id, ugr.contents, date_format(ugr.created_date, '%Y-%m-%d') as created_date
from used_goods_board as ugb
inner join used_goods_reply as ugr on ugb.board_id = ugr.board_id 
where ugb.created_date like '2022-10-%'
order by ugr.created_date asc, ugb.title asc;