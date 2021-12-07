CREATE DATABASE IF NOT EXISTS WondaCabinetIncDb;
create user 'user'@'%' identified by 'pwd';
GRANT ALL PRIVILEGES ON WondaCabinetIncDb.* TO 'user'@'%';

USE WondacabinetIncDb;

CREATE TABLE IF NOT EXISTS orders (
    orderId INT UNSIGNED NOT NULL AUTO_INCREMENT PRIMARY KEY,
    rderStatus VARCHAR(45),
    trackingNo LONG,
    design VARCHAR(80)
    ) ENGINE=InnoDb;