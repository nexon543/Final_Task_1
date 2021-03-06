INSERT INTO `mydb`.`Tariffs` (`id_tariffs`, `receiving_speed`, `transfer_speed`, `price`) VALUES (1, 3072, 512, 25);
INSERT INTO `mydb`.`Tariffs` (`id_tariffs`, `receiving_speed`, `transfer_speed`, `price`) VALUES (2, 4096, 512, 75);
INSERT INTO `mydb`.`Tariffs` (`id_tariffs`, `receiving_speed`, `transfer_speed`, `price`) VALUES (3, 6144, 512, 16.5);
INSERT INTO `mydb`.`Tariffs` (`id_tariffs`, `receiving_speed`, `transfer_speed`, `price`) VALUES (4, 8192, 512, 16.5);
INSERT INTO `mydb`.`Tariffs` (`id_tariffs`, `receiving_speed`, `transfer_speed`, `price`) VALUES (5, 3072, 512, 16.5);

INSERT INTO `mydb`.`Langs` (`lang`,`lname`) VALUES ('de','German');
INSERT INTO `mydb`.`Langs` (`lang`,`lname`) VALUES ('en','English');
INSERT INTO `mydb`.`Langs` (`lang`,`lname`) VALUES ('fr','French');
INSERT INTO `mydb`.`Langs` (`lang`,`lname`) VALUES ('ru','Russian');

INSERT INTO `mydb`.`tTariffs` (`lang`,`id_tariffs`,`name`,`description`)VALUES('en',1,'Home-lanced',
'"Home-launched" (with access speed determined by the technical capability of the subscriber line, but not exceeding 3072/512 Kbps for reception / transmission)');
INSERT INTO `mydb`.`tTariffs` (`lang`,`id_tariffs`,`name`,`description`)VALUES('ru',1,'Домосед-старт',
'"Домосед старт" (со скоростью доступа, определяемой технической возможностью абонентской линии, но не превышающей 3072/512 Кбит/с на прием/передачу');
INSERT INTO `mydb`.`tTariffs` (`lang`,`id_tariffs`,`name`,`description`)VALUES('en',2,'Home-grown XXL',
'"Home-grown XXL" (receiving / transmitting speed up to 4096/512 Kbps)');
INSERT INTO `mydb`.`tTariffs` (`lang`,`id_tariffs`,`name`,`description`)VALUES('ru',2,'Домосед XXL',
'"Домосед XXL" (скорость прием/передача до 4096/512 Кбит/с)');
INSERT INTO `mydb`.`tTariffs` (`lang`,`id_tariffs`,`name`,`description`)VALUES('en',3,'Super Homebody',
'"Super-Domestic" (receiving / transmitting speed up to 6144/512 Kbps)');
INSERT INTO `mydb`.`tTariffs` (`lang`,`id_tariffs`,`name`,`description`)VALUES('ru',3,'СуперДомосед',
'"Супер-Домосед" (скорость прием/передача до 6144/512 Кбит/с)');
INSERT INTO `mydb`.`tTariffs` (`lang`,`id_tariffs`,`name`,`description`)VALUES('en',4,'Ultra-Home',
'"Ultra-home" (speed transmission / reception up to 8192/512 kbit / s)');
INSERT INTO `mydb`.`tTariffs` (`lang`,`id_tariffs`,`name`,`description`)VALUES('ru',4,'Домосед Ультра',
'"Домосед Ультра" (скорость прием/передача до 8192/512 Кбит/с)');
INSERT INTO `mydb`.`tTariffs` (`lang`,`id_tariffs`,`name`,`description`)VALUES('en',5,'Social anlim 3',
'"Social anlim 3" (with access speed determined by the technical capability of the subscriber line, but not exceeding 3072/512 Kbps for reception / transmission)');
INSERT INTO `mydb`.`tTariffs` (`lang`,`id_tariffs`,`name`,`description`)VALUES('ru',5,'Социальный анлим 3',
'"Социальный анлим 3" (со скоростью доступа, определяемой технической возможностью абонентской линии, но не превышающей 3072/512 Кбит/с на прием/передачу)');

DELETE FROM `mydb`.`Profiles` where id_profiles>0;
INSERT INTO `mydb`.`Profiles` (`id_profiles`,`first_name`, `second_name`, `passport`, `id_tariffs`, `balance`, `register_date`, `login`,`pass`,`role`) 
VALUES (1, 'Ivan', 'Ivanov', 'MP347423', 1, 10, '2017-12-02','user',MD5('user'),'client');
INSERT INTO `mydb`.`Profiles` (`first_name`, `second_name`, `passport`, `id_tariffs`, `balance`, `register_date`, `login`,`pass`,`role`)
VALUES ('Kevin', 'Smnith', 'MP432634', 2, 1, '2017-12-02','user1',MD5('user'),'client');
INSERT INTO `mydb`.`Profiles` (`first_name`, `second_name`, `passport`, `id_tariffs`, `balance`, `register_date`, `login`,`pass`,`role`) 
VALUES ('Barbra', 'Okland', 'MP344522', 3, 0.5, '2017-12-02','user2',MD5('user'),'client');
INSERT INTO `mydb`.`Profiles` (`first_name`, `second_name`, `passport`, `id_tariffs`, `balance`, `register_date`, `login`,`pass`,`role`) 
VALUES ('Vasiliy', 'Boyko', 'MP453678', 4, 2, '2017-12-02','user3',MD5('user'),'client');
INSERT INTO `mydb`.`Profiles` (`first_name`, `second_name`, `passport`, `id_tariffs`, `balance`, `register_date`, `login`,`pass`,`role`) 
VALUES ('Admin', 'Admin', 'Admin', 1, 0, '2007-12-02','admin',MD5('admin'),'admin');


INSERT INTO `mydb`.`Transactions` (`amount`, `date`, `id_profiles`) VALUES (30, '2018-07-04', 1);
INSERT INTO `mydb`.`Transactions` (`amount`, `date`, `id_profiles`) VALUES (30, '2018-07-04', 1);



INSERT INTO `mydb`.`Tariffs` (`id_tariffs`, `receiving_speed`, `transfer_speed`, `price`) VALUES (1, 3072, 512, 25);
INSERT INTO `mydb`.`Tariffs` (`id_tariffs`, `receiving_speed`, `transfer_speed`, `price`) VALUES (2, 4096, 512, 75);
INSERT INTO `mydb`.`Tariffs` (`id_tariffs`, `receiving_speed`, `transfer_speed`, `price`) VALUES (3, 6144, 512, 16.5);
INSERT INTO `mydb`.`Tariffs` (`id_tariffs`, `receiving_speed`, `transfer_speed`, `price`) VALUES (4, 8192, 512, 16.5);
INSERT INTO `mydb`.`Tariffs` (`id_tariffs`, `receiving_speed`, `transfer_speed`, `price`) VALUES (5, 3072, 512, 16.5);

INSERT INTO `mydb`.`Langs` (`lang`,`lname`) VALUES ('de','German');
INSERT INTO `mydb`.`Langs` (`lang`,`lname`) VALUES ('en','English');
INSERT INTO `mydb`.`Langs` (`lang`,`lname`) VALUES ('fr','French');
INSERT INTO `mydb`.`Langs` (`lang`,`lname`) VALUES ('ru','Russian');

INSERT INTO `mydb`.`tTariffs` (`lang`,`id_tariffs`,`name`,`description`)VALUES('en',1,'Home-lanced',
'"Home-launched" (with access speed determined by the technical capability of the subscriber line, but not exceeding 3072/512 Kbps for reception / transmission)');
INSERT INTO `mydb`.`tTariffs` (`lang`,`id_tariffs`,`name`,`description`)VALUES('en',2,'Home-grown XXL',
'"Home-grown XXL" (receiving / transmitting speed up to 4096/512 Kbps)');
INSERT INTO `mydb`.`tTariffs` (`lang`,`id_tariffs`,`name`,`description`)VALUES('en',3,'Super Homebody',
'"Super-Domestic" (receiving / transmitting speed up to 6144/512 Kbps)');
INSERT INTO `mydb`.`tTariffs` (`lang`,`id_tariffs`,`name`,`description`)VALUES('en',4,'Ultra-Home',
'"Ultra-home" (speed transmission / reception up to 8192/512 kbit / s)');
INSERT INTO `mydb`.`tTariffs` (`lang`,`id_tariffs`,`name`,`description`)VALUES('en',5,'Social anlim 3',
'"Social anlim 3" (with access speed determined by the technical capability of the subscriber line, but not exceeding 3072/512 Kbps for reception / transmission)');

DELETE FROM `mydb`.`Profiles` where id_profiles>0;
INSERT INTO `mydb`.`Profiles` (`id_profiles`,`first_name`, `second_name`, `passport`, `id_tariffs`, `balance`, `register_date`, `login`,`pass`,`role`)
VALUES (1, 'Ivan', 'Ivanov', 'MP347423', 1, 10, '2017-12-02','user',MD5('user'),'client');
INSERT INTO `mydb`.`Profiles` (`first_name`, `second_name`, `passport`, `id_tariffs`, `balance`, `register_date`, `login`,`pass`,`role`)
VALUES ('Kevin', 'Smnith', 'MP432634', 2, 1, '2017-12-02','user1',MD5('user'),'client');
INSERT INTO `mydb`.`Profiles` (`first_name`, `second_name`, `passport`, `id_tariffs`, `balance`, `register_date`, `login`,`pass`,`role`)
VALUES ('Barbra', 'Okland', 'MP344522', 3, 0.5, '2017-12-02','user2',MD5('user'),'client');
INSERT INTO `mydb`.`Profiles` (`first_name`, `second_name`, `passport`, `id_tariffs`, `balance`, `register_date`, `login`,`pass`,`role`)
VALUES ('Vasiliy', 'Boyko', 'MP453678', 4, 2, '2017-12-02','user3',MD5('user'),'client');
INSERT INTO `mydb`.`Profiles` (`first_name`, `second_name`, `passport`, `id_tariffs`, `balance`, `register_date`, `login`,`pass`,`role`)
VALUES ('Admin', 'Admin', 'Admin', 1, 0, '2007-12-02','admin',MD5('admin'),'admin');


INSERT INTO `mydb`.`Transactions` (`amount`, `date`, `id_profiles`) VALUES (30, '2018-07-04', 1);
INSERT INTO `mydb`.`Transactions` (`amount`, `date`, `id_profiles`) VALUES (30, '2018-07-04', 1);