use annetteprojekt;

DELIMITER $$
drop procedure if exists getMeat$$
create procedure getMeat()
begin
select * from meat, category 
where meat.c_category_id = category.c_id;
end$$
DELIMITER ;

DELIMITER $$
drop procedure if exists getAccompaniment$$
create procedure getAccompaniment()
begin
select * from accompaniment, category 
where accompaniment.c_category_id = category.c_id;
end$$
DELIMITER ;

DELIMITER $$
drop procedure if exists getCategories$$
create procedure getCategories()
begin
select * from category;
end$$
DELIMITER ;