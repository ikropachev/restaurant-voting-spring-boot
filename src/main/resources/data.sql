INSERT INTO USERS (NAME, EMAIL, PASSWORD, REGISTERED)
VALUES ('Admin', 'admin@gmail.com', '{noop}admin', '2021-12-02 18:00:00'),
       ('User', 'user@gmail.com', '{noop}password', '2021-12-02 18:01:00'),
       ('second_user', 'second@gmail.com', '{noop}second_pass', '2021-12-01 18:02:00'),
       ('third_user', 'third@gmail.com', '{noop}third_pass', '2021-12-01 18:03:00');

INSERT INTO USER_ROLE (ROLE, USER_ID)
VALUES ('USER', 1),
       ('ADMIN', 1),
       ('USER', 2),
       ('USER', 3),
       ('USER', 4);

INSERT INTO RESTAURANT (NAME)
VALUES ('BarZero'),
       ('Burgers'),
       ('HappyShaverma'),
       ('Suluguni');

INSERT INTO MENU (RESTAURANT_ID, CREATED_ON)
VALUES (1, '2021-12-03'),
       (3, '2021-12-03'),
       (4, '2021-12-03'),
       (2, '2021-12-03');

INSERT INTO MENU (RESTAURANT_ID)
VALUES (3),
       (4);

INSERT INTO MENU_ITEM (NAME, PRICE, MENU_ID)
VALUES ('Hachapuri po imeretinski', 550, 3),
       ('Vegan Burger', 150, 1),
       ('Chicken Burger', 50, 1),
       ('Meat Shaverma', 100, 2),
       ('Vegan Shaverma', 150, 2),
       ('Chicken Shaverma', 50, 5),
       ('Hachapuri po adzharski', 350, 3),
       ('Hachapuri po megrelski', 450, 6),
       ('Meat Burger', 100, 4);

INSERT INTO VOTE (USER_ID, RESTAURANT_ID, CREATED_ON)
VALUES (2, 2, '2021-12-02'),
       (2, 3, '2021-12-03'),
       (3, 1, '2021-11-16'),
       (3, 4, '2021-11-17'),
       (4, 3, '2021-11-17');

INSERT INTO VOTE (USER_ID, RESTAURANT_ID)
VALUES (3, 2),
       (4, 3);