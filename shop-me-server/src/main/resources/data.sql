-- create default admin
INSERT INTO `shopme`.`users` (`username`, `password`) VALUES ('admin', '$2a$10$tVWrchqlsoGsiCSjJ1EZseNC7QGCzWYXTONgLQldoRsb4gy1MqUU2');
INSERT INTO `shopme`.`users` (`username`, `password`) VALUES ('thilina', '$2a$10$tVWrchqlsoGsiCSjJ1EZseNC7QGCzWYXTONgLQldoRsb4gy1MqUU2');

INSERT INTO `shopme`.`authorities` (`authority`) VALUES ('admin');

INSERT INTO `shopme`.`users_authorities` (`username`, `authority`) VALUES ('admin', 'admin');


INSERT INTO `shopme`.`category` (`category`) VALUES ('Backery');
INSERT INTO `shopme`.`category` (`category`) VALUES ('Fruits');


INSERT INTO `shopme`.`product` (`title`, `price`, `category_id`, `image_url`) VALUES ('Bread', '73', '1', 'http://localhost:8787/images/bread.jpg');
INSERT INTO `shopme`.`product` (`title`, `price`, `category_id`, `image_url`) VALUES ('Sausage Bun', '65', '1', 'http://localhost:8787/images/sousage_bun.jpg');
INSERT INTO `shopme`.`product` (`title`, `price`, `category_id`, `image_url`) VALUES ('Chease Burger', '165', '1', 'http://localhost:8787/images/burger.jpg');
INSERT INTO `shopme`.`product` (`title`, `price`, `category_id`, `image_url`) VALUES ('Sini Sumble Bun', '65', '1', 'http://localhost:8787/images/sini_sumbal_bun.jpg');

INSERT INTO `shopme`.`product` (`title`, `price`, `category_id`, `image_url`) VALUES ('Apple', '55', '2', 'http://localhost:8787/images/apple.jpg');
INSERT INTO `shopme`.`product` (`title`, `price`, `category_id`, `image_url`) VALUES ('Orange', '65', '2', 'http://localhost:8787/images/download.jpg');
INSERT INTO `shopme`.`product` (`title`, `price`, `category_id`, `image_url`) VALUES ('Banana', '55', '2', 'http://localhost:8787/images/banana.jpg');