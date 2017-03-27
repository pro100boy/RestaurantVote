DELETE FROM user_roles;
DELETE FROM votes;
DELETE FROM dishes;
DELETE FROM users;
DELETE FROM restaurants;

ALTER SEQUENCE global_seq RESTART WITH 100000;

INSERT INTO users (name, email, password) VALUES
  ('Admin', 'admin@gmail.com', '$2a$10$WejOLxVuXRpOgr4IlzQJ.eT4UcukNqHlAiOVZj1P/nmc8WbpMkiju'),
  ('User', 'user@ya.ru', '$2a$10$Sh0ZD2NFrzRRJJEKEWn8l.92ROEuzlVyzB9SV1AM8fdluPR0aC1ni'),
  ('User 2', 'user2@ya.ru', '$2a$10$Sh0ZD2NFrzRRJJEKEWn8l.92ROEuzlVyzB9SV1AM8fdluPR0aC1ni'),
  ('User 3', 'user3@ya.ru', '$2a$10$Sh0ZD2NFrzRRJJEKEWn8l.92ROEuzlVyzB9SV1AM8fdluPR0aC1ni');

INSERT INTO user_roles (role, user_id) VALUES
  ('ROLE_USER', 100000),
  ('ROLE_ADMIN', 100000),
  ('ROLE_USER', 100001),
  ('ROLE_USER', 100002),
  ('ROLE_USER', 100003);

INSERT INTO restaurants (name, description) VALUES
  ('Restaurant 1', 'Description of restaurant 1'),
  ('Restaurant 2', 'Description of restaurant 2'),
  ('Restaurant 3', 'Description of restaurant 3');

INSERT INTO dishes (rest_id, date, name, price) VALUES
  (100004, '2017-01-30', 'Dish 1', 1250),
  (100005, '2017-01-30', 'Dish 2', 1350),
  (100006, '2017-01-30', 'Dish 3', 1450),
  (100004, '2017-02-20', 'Dish 4', 12050),
  (100005, '2017-02-20', 'Dish 5', 13050),
  (100006, '2017-02-20', 'Dish 6', 14050),
  (100005, now(), 'Dish 7', 13999),
  (100005, now(), 'Dish 8', 14099);

INSERT INTO votes (user_id, rest_id, date) VALUES
  (100000, 100004, '2017-01-30'),
  (100001, 100005, '2017-01-30'),
  (100002, 100006, '2017-01-30'),
  (100003, 100006, '2017-01-30'),
  (100000, 100004, '2017-02-20'),
  (100001, 100004, '2017-02-20'),
  (100002, 100005, now()),
  (100003, 100004, now());