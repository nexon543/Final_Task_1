drop procedure if exists `mydb`.get_tariffs;
delimiter //
create procedure `mydb`.get_tariffs (IN inlang varchar(2))
begin
select * from mydb.Tariffs t inner join  mydb.tTariffs tt on t.id_tariffs=tt.id_tariffs and tt.lang=inlang ORDER BY t.id_tariffs;
end//
delimiter ;

drop procedure if exists `mydb`.get_tariffs_by_id;
delimiter //
create procedure `mydb`.get_tariffs_by_id (IN id int, IN inlang varchar(2))
begin
select * from mydb.Tariffs t, mydb.tTariffs tt  where tt.id_tariffs=t.id_tariffs and tt.lang=inlang and t.id_tariffs=id  ORDER BY t.id_tariffs;
end//	
delimiter ;

drop procedure if exists `mydb`.get_tariffs_limited;
delimiter //
create procedure `mydb`.get_tariffs_limited (IN inlang varchar(2), in start INT, in end INT)
begin
select * from mydb.Tariffs t inner join  mydb.tTariffs tt on t.id_tariffs=tt.id_tariffs and tt.lang=inlang ORDER BY t.id_tariffs LIMIT start, end;
end//
delimiter ;

drop procedure if exists `mydb`.insert_tariff;
delimiter //
create procedure `mydb`.insert_tariff (in receiving_speed int, in transfer_speed int, in price INT, lang varchar(2), in tname varchar(45), in description varchar(250))
begin
INSERT INTO Tariffs (`receiving_speed`, `transfer_speed`, `price`) VALUES (receiving_speed, transfer_speed, price);
INSERT INTO tTariffs (`lang`,`id_tariffs`,`name`,`description`)VALUES(lang,(select last_insert_id()),tname,description);
end//
delimiter ;

drop procedure if exists `mydb`.update_tariff;
delimiter //
create procedure `mydb`.update_tariff (in id_tariffs int, in receiving_speed int, in transfer_speed int, in price INT, lang varchar(2), in tname varchar(45), in description varchar(250))
begin
update `Tariffs` set `Tariffs`.`receiving_speed`=receiving_speed, `Tariffs`.`transfer_speed` = transfer_speed, `Tariffs`.`price`=price where `Tariffs`.`id_tariffs`=id_tariffs;
update `tTariffs` set `tTariffs`.`name`=tname,`tTariffs`.`description`=description where `tTariffs`.`id_tariffs`=id_tariffs and `tTariffs`.`lang`=lang;
end//
delimiter ;

drop procedure if exists `mydb`.delete_tariff;
delimiter //
create procedure `mydb`.delete_tariff (in id int)
begin
DECLARE EXIT HANDLER FOR SQLEXCEPTION ROLLBACK;
DECLARE EXIT HANDLER FOR NOT FOUND ROLLBACK;
DECLARE EXIT HANDLER FOR SQLWARNING ROLLBACK;
START TRANSACTION READ WRITE;
delete from tTariffs where id_tariffs=id;
delete from Tariffs where id_tariffs=id;
COMMIT;
end//
delimiter ;

drop trigger if exists `mydb`.upd_balance;
delimiter //
CREATE TRIGGER `mydb`.upd_balance before INSERT ON Transactions
       FOR EACH ROW
       BEGIN
           IF NEW.amount > 0 THEN
               update Profiles set `balance` = `balance` +NEW.amount where id_profiles=NEW.id_profiles;
           END IF;
       END;//
delimiter ;
