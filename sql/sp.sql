drop procedure if exists get_tariffs;
delimiter //
create procedure get_tariffs (IN inlang varchar(2))
begin
select * from mydb.tariffs t, mydb.ttariffs tt  where tt.id_tariffs=t.id_tariffs and tt.lang=inlang  ORDER BY t.id_tariffs;
end//
delimiter ;

drop procedure if exists get_tariffs_by_id;
delimiter //
create procedure get_tariffs_by_id (IN inlang varchar(2), IN id int)
begin
select * from mydb.tariffs t, mydb.ttariffs tt  where tt.id_tariffs=t.id_tariffs and tt.lang=inlang and t.id_tariffs=id  ORDER BY t.id_tariffs;
end//	
delimiter ;

drop procedure if exists get_tariffs_limited;
delimiter //
create procedure get_tariffs_limited (IN inlang varchar(2), in start INT, in end INT)
begin
select * from mydb.tariffs t, mydb.ttariffs tt  where tt.id_tariffs=t.id_tariffs and tt.lang=inlang  ORDER BY t.id_tariffs LIMIT start, end;
end//
delimiter ;

drop procedure if exists insert_tariff;
delimiter //
create procedure insert_tariff (in recieving_speed int, in transfer_speed int, in price INT, lang varchar(2), in tname varchar(45), in description varchar(250))
begin
INSERT INTO tariffs (`recieving_speed`, `transfer_speed`, `price`) VALUES (recieving_speed, transfer_speed, price);
INSERT INTO ttariffs (`lang`,`id_tariffs`,`name`,`description`)VALUES(lang,(select last_insert_id()),tname,description);
end//
delimiter ;

drop procedure if exists update_tariff;
delimiter //
create procedure update_tariff (in id_tariffs int, in recieving_speed int, in transfer_speed int, in price INT, lang varchar(2), in tname varchar(45), in description varchar(250))
begin
update `tariffs` set `tariffs`.`recieving_speed`=recieving_speed, `tariffs`.`transfer_speed` = transfer_speed, `tariffs`.`price`=price where `tariffs`.`id_tariffs`=id_tariffs;
update `ttariffs` set `ttariffs`.`name`=tname,`ttariffs`.`description`=description where `ttariffs`.`id_tariffs`=id_tariffs and `ttariffs`.`lang`=lang;
end//
delimiter ;


drop trigger if exists upd_balance;
delimiter //
CREATE TRIGGER upd_balance before INSERT ON Transactions
       FOR EACH ROW
       BEGIN
           IF NEW.amount < 0 THEN
               SET NEW.amount = 0;
           END IF;
           update profiles set `balance` = `balance` +NEW.amount where id_profiles=NEW.id_profiles;
       END;//
delimiter ;
