DROP DATABASE IF EXISTS shopme;
CREATE DATABASE IF NOT EXISTS shopme ;
USE shopme;

CREATE TABLE IF NOT EXISTS users(
	id INT NOT NULL ,
	username VARCHAR(150) PRIMARY KEY NOT NULL,
    `password` VARCHAR(255) NOT NULl,
    `role` VARCHAR(50) NOT NULL
);

CREATE TABLE category (
	category_id INT PRIMARY KEY NOT NULL AUTO_INCREMENT,
	category  VARCHAR(50) NOT NULL
);

CREATE TABLE product(
	product_id INT PRIMARY KEY NOT NULL AUTO_INCREMENT,
    title VARCHAR(50) NOT NULL,
    price FLOAT NOT NULL,
    category_id INT NOT NULL,
    image_url VARCHAR(255) NOT NULL,
    FOREIGN KEY fk_product_category (category_id)
		REFERENCES category (category_id)
);