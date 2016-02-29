SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";

DROP SCHEMA IF EXISTS userTest;
CREATE SCHEMA userTest;
USE userTest;


CREATE TABLE userLogin (uid INT UNSIGNED NOT NULL AUTO_INCREMENT, email VARCHAR(50) NOT NULL , password VARCHAR(20) NOT NULL,  PRIMARY KEY  (uid));
insert into userLogin (email,password) values ('umesh936@gmail.com','12345678');

-- ------------------------------------------------------------------------------------

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";

DROP SCHEMA IF EXISTS eventTest;
CREATE SCHEMA eventTest;
USE eventTest;


CREATE TABLE event (eid INT UNSIGNED NOT NULL AUTO_INCREMENT, eventName VARCHAR(50) NOT NULL , eventPlace VARCHAR(20) NOT NULL,  PRIMARY KEY  (eid));
insert into event (eventName,eventPlace) values ('Salsa Dancing','CP, New Dehli');

-- ------------------------------------------------------------------------------------