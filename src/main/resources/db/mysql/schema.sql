CREATE DATABASE IF NOT EXISTS WondaCabinetIncDb;
create user 'user'@'%' identified by 'pwd';
GRANT ALL PRIVILEGES ON WondaCabinetIncDb.* TO 'user'@'%';

USE WondacabinetIncDb;

CREATE TABLE IF NOT EXISTS orders (
    order_id INT UNSIGNED NOT NULL AUTO_INCREMENT PRIMARY KEY,
    order_status VARCHAR(45),
    tracking_no LONG,
    design VARCHAR(80)
    ) ENGINE=InnoDb;