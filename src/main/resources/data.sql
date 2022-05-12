DROP TABLE IF EXISTS USER_ROLES;
DROP TABLE IF EXISTS VOTE;
DROP TABLE IF EXISTS USERS CASCADE;
DROP TABLE IF EXISTS MENU_IREM;
DROP TABLE IF EXISTS RESTAURANT CASCADE;
DROP TABLE IF EXISTS MENU CASCADE;

CREATE TABLE RESTAURANT
(
    id   INTEGER IDENTITY PRIMARY KEY,
    name VARCHAR(255) NOT NULL
);
CREATE UNIQUE INDEX restaurant_unique_name_idx ON RESTAURANT (NAME);

CREATE TABLE USERS
(
    id         INTEGER  IDENTITY       PRIMARY KEY,
    name       VARCHAR(255)            NOT NULL,
    email      VARCHAR(255)            NOT NULL,
    password   VARCHAR(255)            NOT NULL,
    registered TIMESTAMP DEFAULT now() NOT NULL,
    enabled    BOOLEAN   DEFAULT TRUE  NOT NULL
);
CREATE UNIQUE INDEX users_unique_email_idx ON USERS (email);

CREATE TABLE USER_ROLE
(
    user_id INTEGER NOT NULL,
    role    VARCHAR(255),
    CONSTRAINT user_role_idx UNIQUE (user_id, role),
    FOREIGN KEY (user_id) REFERENCES USERS (id) ON DELETE CASCADE
);

CREATE TABLE VOTE
(
    id            INTEGER IDENTITY PRIMARY KEY,
    user_id       INTEGER NOT NULL,
    restaurant_id INTEGER NOT NULL,
    created_on    DATE DEFAULT now(),
    CONSTRAINT votes_user_created_on_idx UNIQUE (user_id, created_on),
    FOREIGN KEY (user_id) REFERENCES USERS (id) ON DELETE CASCADE,
    FOREIGN KEY (restaurant_id) REFERENCES RESTAURANT (id) ON DELETE CASCADE
);

CREATE TABLE MENU
(
    id            INTEGER IDENTITY PRIMARY KEY,
    restaurant_id INTEGER NOT NULL,
    created_on    DATE DEFAULT now(),
    CONSTRAINT menu_restaurant_id_created_on_idx UNIQUE (restaurant_id, created_on),
    FOREIGN KEY (restaurant_id) REFERENCES RESTAURANT (id) ON DELETE CASCADE
);

CREATE TABLE MENU_ITEM
(
    id      INTEGER IDENTITY PRIMARY KEY,
    name    VARCHAR(255) NOT NULL,
    price   INTEGER      NOT NULL,
    menu_id INTEGER      NOT NULL,
    FOREIGN KEY (menu_id) REFERENCES MENU (id) ON DELETE CASCADE
);

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