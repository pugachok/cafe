ALTER TABLE orders
DROP FOREIGN KEY Orders_Menu;

ALTER TABLE orders
DROP FOREIGN KEY Orders_Coworkers;

ALTER TABLE coworker
DROP FOREIGN KEY Coworker_Position;

ALTER TABLE dishes_in_order
DROP FOREIGN KEY dishes_in_order_menu;

ALTER TABLE menu
DROP FOREIGN KEY Menu_Dish;

ALTER TABLE ingredients_in_the_composition
DROP FOREIGN KEY ingredients_in_the_composition_dish;

ALTER TABLE ingredients_in_the_composition
DROP FOREIGN KEY ingredients_in_the_composition_ingredient;


ALTER TABLE purchase
DROP FOREIGN KEY purchase_ingredient;

ALTER TABLE purchase
DROP FOREIGN KEY purchase_contractor;

DROP TABLE contractor;

DROP TABLE coworker;

DROP TABLE dish;

DROP TABLE dishes_in_order;

DROP TABLE ingredient;

DROP TABLE ingredients_in_the_composition;

DROP TABLE menu;

# DROP TABLE order_with_delivery;

DROP TABLE position;

DROP TABLE purchase;

DROP TABLE orders;

CREATE TABLE position (
    id BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY
    , name_position nvarchar(20) NOT NULL
);


CREATE TABLE coworker (
    id BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY
    , fio nvarchar(40) NOT NULL
    , id_position BIGINT NOT NULL
);

CREATE TABLE contractor (
    id BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY
    , name nvarchar(40) NOT NULL
    , address nvarchar(40) NOT NULL
    , phone_number nvarchar(12) NOT NULL
);

CREATE TABLE dish (
    id BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY
    , name nvarchar(30) NOT NULL UNIQUE
    , unit nvarchar(10) NOT NULL/*Единица измерения*/
);

CREATE TABLE dishes_in_order (
    id BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY
    , id_menu BIGINT NOT NULL
#     , id_order_with_delivery BIGINT NOT NULL
    , address nvarchar(40) NOT NULL
    , phone_number nvarchar(14) NOT NULL
    , data date NOT NULL
    , sum int NOT NULL DEFAULT 3 CHECK(sum > 0)
);

CREATE TABLE ingredient (
    id BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY
    , name nvarchar(25) NOT NULL
    , unit nvarchar(10) NOT NULL
);

CREATE TABLE ingredients_in_the_composition (
    id BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY
    , id_ingredient BIGINT NOT NULL
    , id_dish BIGINT NOT NULL
    , sum int NOT NULL DEFAULT 1 CHECK(sum > 0)
);

CREATE TABLE menu (
    id BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY
    , id_dish BIGINT NOT NULL
    , price_dish DECIMAL(10, 2) NOT NULL DEFAULT 30
    , data date NOT NULL
);

CREATE TABLE purchase (
    id BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY
    , id_ingredient BIGINT NOT NULL
    , sum int NOT NULL DEFAULT 1 CHECK(sum > 0)
    , id_contractors BIGINT NOT NULL
    , data date NOT NULL
    , price DECIMAL(10, 2) NOT NULL DEFAULT 1 CHECK(price > 0)
);

CREATE TABLE orders (
    id BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY
    , id_coworker BIGINT NOT NULL
    , id_menu BIGINT NOT NULL
    , data date NOT NULL
    , time time NOT NULL
    , table_number int NOT NULL
    , sum int NOT NULL
);

ALTER TABLE orders
ADD CONSTRAINT Orders_Coworkers FOREIGN KEY (id_coworker)
REFERENCES coworker(id);

ALTER TABLE orders
ADD CONSTRAINT Orders_Menu FOREIGN KEY (id_menu)
REFERENCES menu(id);

ALTER TABLE coworker
ADD CONSTRAINT Coworker_Position FOREIGN KEY (id_position)
REFERENCES spring_cafe.position(id);

ALTER TABLE menu
ADD CONSTRAINT Menu_Dish FOREIGN KEY (id_dish)
REFERENCES dish(id);

ALTER TABLE dishes_in_order
ADD CONSTRAINT dishes_in_order_menu FOREIGN KEY (id_menu)
REFERENCES menu(id);

ALTER TABLE ingredients_in_the_composition
ADD CONSTRAINT ingredients_in_the_composition_dish FOREIGN KEY (id_dish)
REFERENCES dish(id);

ALTER TABLE ingredients_in_the_composition
ADD CONSTRAINT ingredients_in_the_composition_ingredient FOREIGN KEY (id_ingredient)
REFERENCES ingredient(id);

ALTER TABLE purchase
ADD CONSTRAINT purchase_ingredient FOREIGN KEY (id_ingredient)
REFERENCES ingredient(id);

ALTER TABLE purchase
ADD CONSTRAINT purchase_contractor FOREIGN KEY (id_contractors)
REFERENCES contractor(id);


