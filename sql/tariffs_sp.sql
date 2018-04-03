delimiter //
create procedure get_tariffs (IN inlang varchar(2))
begin
select * from mydb.tariffs t, mydb.ttariffs tt  where tt.id_tariffs=t.id_tariffs and tt.lang=inlang  ORDER BY t.id_tariffs;
end//
delimiter ;

delimiter //
create procedure get_tariffs_limited (IN inlang varchar(2), in start INT, in end INT)
begin
select * from mydb.tariffs t, mydb.ttariffs tt  where tt.id_tariffs=t.id_tariffs and tt.lang=inlang  ORDER BY t.id_tariffs LIMIT start, end;
end//
delimiter ;

delimiter //
create procedure insert_tariff (in recieving_speed int, in transfering_speed int, in price INT, in isUnlim tinyint, lang varchar(2), in tname varchar(45), in description varchar(250))
begin
INSERT INTO tariffs (`recieving_speed`, `transfering_speed`, `price`,`isUnlim`) VALUES (recieving_speed, transfering_speed, price, isUnlim);
INSERT INTO ttariffs (`lang`,`id_tariffs`,`name`,`description`)VALUES(lang,(select last_insert_id()),tname,description);
end//
delimiter ;