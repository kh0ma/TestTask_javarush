CREATE SCHEMA `test` ;

CREATE TABLE `test`.`test` (
`id` int(8) unsigned NOT NULL AUTO_INCREMENT,
`name` varchar(25) NOT NULL,
`age` int NOT NULL,
`is_admin` BIT(8),
`create_date` timestamp NOT NULL,
PRIMARY KEY (`id`) USING BTREE) 
ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;

INSERT INTO `test`.`test` (`id`, `name`, `age`, `is_admin`, `create_date`) VALUES ('1', 'User1', '1', '1', '2015-02-28 15:18:14');
INSERT INTO `test`.`test` (`id`, `name`, `age`, `is_admin`, `create_date`) VALUES ('2', 'User2', '1', '0', '2014-02-28 15:18:14');
INSERT INTO `test`.`test` (`id`, `name`, `age`, `is_admin`, `create_date`) VALUES ('3', 'User3', '1', '1', '2013-08-28 15:18:14');
INSERT INTO `test`.`test` (`id`, `name`, `age`, `is_admin`, `create_date`) VALUES ('4', 'User4', '1', '0', '2011-01-28 15:18:14');
INSERT INTO `test`.`test` (`id`, `name`, `age`, `is_admin`, `create_date`) VALUES ('5', 'User5', '1', '1', '2016-08-28 15:18:14');
INSERT INTO `test`.`test` (`id`, `name`, `age`, `is_admin`, `create_date`) VALUES ('6', 'User6', '1', '0', '2011-01-28 15:18:14');
INSERT INTO `test`.`test` (`id`, `name`, `age`, `is_admin`, `create_date`) VALUES ('7', 'User7', '1', '1', '2012-08-28 15:18:14');
INSERT INTO `test`.`test` (`id`, `name`, `age`, `is_admin`, `create_date`) VALUES ('8', 'User8', '1', '0', '2016-02-28 15:18:14');
INSERT INTO `test`.`test` (`id`, `name`, `age`, `is_admin`, `create_date`) VALUES ('9', 'User9', '1', '1', '2016-08-28 15:18:14');
INSERT INTO `test`.`test` (`id`, `name`, `age`, `is_admin`, `create_date`) VALUES ('10', 'User10', '1', '0', '2016-08-28 15:18:14');
INSERT INTO `test`.`test` (`id`, `name`, `age`, `is_admin`, `create_date`) VALUES ('11', 'User11', '1', '1', '2016-03-28 15:18:14');
INSERT INTO `test`.`test` (`id`, `name`, `age`, `is_admin`, `create_date`) VALUES ('12', 'User12', '1', '0', '2016-04-28 15:18:14');
INSERT INTO `test`.`test` (`id`, `name`, `age`, `is_admin`, `create_date`) VALUES ('13', 'User13', '1', '1', '2016-08-28 15:18:14');
INSERT INTO `test`.`test` (`id`, `name`, `age`, `is_admin`, `create_date`) VALUES ('14', 'User14', '1', '0', '2016-11-28 15:18:14');
INSERT INTO `test`.`test` (`id`, `name`, `age`, `is_admin`, `create_date`) VALUES ('15', 'User15', '1', '1', '2006-08-28 15:18:14');
INSERT INTO `test`.`test` (`id`, `name`, `age`, `is_admin`, `create_date`) VALUES ('16', 'User16', '1', '0', '2016-01-28 15:18:14');
INSERT INTO `test`.`test` (`id`, `name`, `age`, `is_admin`, `create_date`) VALUES ('17', 'User17', '1', '1', '2016-08-28 15:18:14');
INSERT INTO `test`.`test` (`id`, `name`, `age`, `is_admin`, `create_date`) VALUES ('18', 'User18', '1', '0', '2006-10-28 15:18:14');
INSERT INTO `test`.`test` (`id`, `name`, `age`, `is_admin`, `create_date`) VALUES ('19', 'User19', '1', '1', '2016-10-28 15:18:14');
INSERT INTO `test`.`test` (`id`, `name`, `age`, `is_admin`, `create_date`) VALUES ('20', 'User20', '1', '0', '2006-08-28 15:18:14');
INSERT INTO `test`.`test` (`id`, `name`, `age`, `is_admin`, `create_date`) VALUES ('21', 'User21', '1', '1', '2016-08-28 15:18:14');
INSERT INTO `test`.`test` (`id`, `name`, `age`, `is_admin`, `create_date`) VALUES ('22', 'User22', '1', '0', '2016-08-28 15:18:14');
INSERT INTO `test`.`test` (`id`, `name`, `age`, `is_admin`, `create_date`) VALUES ('23', 'User23', '1', '1', '2006-08-28 15:18:14');
INSERT INTO `test`.`test` (`id`, `name`, `age`, `is_admin`, `create_date`) VALUES ('24', 'User24', '1', '0', '2016-08-28 15:18:14');
INSERT INTO `test`.`test` (`id`, `name`, `age`, `is_admin`, `create_date`) VALUES ('25', 'User25', '1', '1', '2006-08-28 15:18:14');
INSERT INTO `test`.`test` (`id`, `name`, `age`, `is_admin`, `create_date`) VALUES ('26', 'User26', '1', '1', '2016-08-28 15:18:14');
INSERT INTO `test`.`test` (`id`, `name`, `age`, `is_admin`, `create_date`) VALUES ('27', 'User27', '1', '1', '2016-08-28 15:18:14');
INSERT INTO `test`.`test` (`id`, `name`, `age`, `is_admin`, `create_date`) VALUES ('28', 'User28', '1', '0', '2006-08-28 15:18:14');
INSERT INTO `test`.`test` (`id`, `name`, `age`, `is_admin`, `create_date`) VALUES ('29', 'User29', '1', '1', '2016-08-28 15:18:14');
INSERT INTO `test`.`test` (`id`, `name`, `age`, `is_admin`, `create_date`) VALUES ('30', 'User30', '1', '0', '2016-08-28 15:18:14');
INSERT INTO `test`.`test` (`id`, `name`, `age`, `is_admin`, `create_date`) VALUES ('31', 'User31', '1', '0', '2016-08-28 15:18:14');
INSERT INTO `test`.`test` (`id`, `name`, `age`, `is_admin`, `create_date`) VALUES ('32', 'User32', '1', '1', '2006-08-28 15:18:14');
INSERT INTO `test`.`test` (`id`, `name`, `age`, `is_admin`, `create_date`) VALUES ('33', 'User33', '1', '0', '2016-08-28 15:18:14');
INSERT INTO `test`.`test` (`id`, `name`, `age`, `is_admin`, `create_date`) VALUES ('34', 'User34', '1', '1', '2016-08-28 15:18:14');
INSERT INTO `test`.`test` (`id`, `name`, `age`, `is_admin`, `create_date`) VALUES ('35', 'User35', '1', '0', '2014-08-28 15:18:14');
INSERT INTO `test`.`test` (`id`, `name`, `age`, `is_admin`, `create_date`) VALUES ('36', 'User36', '1', '1', '2016-08-28 15:18:14');
INSERT INTO `test`.`test` (`id`, `name`, `age`, `is_admin`, `create_date`) VALUES ('37', 'User37', '1', '0', '2016-08-28 15:18:14');
INSERT INTO `test`.`test` (`id`, `name`, `age`, `is_admin`, `create_date`) VALUES ('38', 'User38', '1', '1', '2014-08-28 15:18:14');
INSERT INTO `test`.`test` (`id`, `name`, `age`, `is_admin`, `create_date`) VALUES ('39', 'User39', '1', '0', '2016-08-28 15:18:14');
INSERT INTO `test`.`test` (`id`, `name`, `age`, `is_admin`, `create_date`) VALUES ('40', 'User40', '1', '1', '2015-08-28 15:18:14');
INSERT INTO `test`.`test` (`id`, `name`, `age`, `is_admin`, `create_date`) VALUES ('41', 'User41', '1', '1', '2016-08-28 15:18:14');
INSERT INTO `test`.`test` (`id`, `name`, `age`, `is_admin`, `create_date`) VALUES ('42', 'User42', '1', '0', '2016-08-28 15:18:14');
INSERT INTO `test`.`test` (`id`, `name`, `age`, `is_admin`, `create_date`) VALUES ('43', 'User43', '1', '1', '2016-08-28 15:18:14');
INSERT INTO `test`.`test` (`id`, `name`, `age`, `is_admin`, `create_date`) VALUES ('44', 'User44', '1', '1', '2016-08-28 15:18:14');
INSERT INTO `test`.`test` (`id`, `name`, `age`, `is_admin`, `create_date`) VALUES ('45', 'User45', '1', '1', '2015-08-28 15:18:14');
INSERT INTO `test`.`test` (`id`, `name`, `age`, `is_admin`, `create_date`) VALUES ('46', 'User46', '1', '0', '2016-08-28 15:18:14');
INSERT INTO `test`.`test` (`id`, `name`, `age`, `is_admin`, `create_date`) VALUES ('47', 'User47', '13', '0', '2016-08-28 15:18:14');
INSERT INTO `test`.`test` (`id`, `name`, `age`, `is_admin`, `create_date`) VALUES ('48', 'User48', '12', '0', '2015-08-28 15:18:14');