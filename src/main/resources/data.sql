CREATE TABLE IF NOT EXIST menu(
    id BIGINT PRIMARY KEY,
    itemName varchar(255) NOT NULL,
    price BIGINT,
    typeOfFood varchar(10),
    selfPickUp BOOLEAN NOT NULL
);