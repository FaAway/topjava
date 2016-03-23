DELETE FROM user_roles;
DELETE FROM users;
DELETE FROM meals;
DELETE FROM users_meals;
ALTER SEQUENCE global_seq RESTART WITH 100000;

INSERT INTO users (name, email, password)
VALUES ('User', 'user@yandex.ru', 'password');

INSERT INTO users (name, email, password)
VALUES ('Admin', 'admin@gmail.com', 'admin');

INSERT INTO user_roles (role, user_id) VALUES
  ('ROLE_USER', 100000),
  ('ROLE_ADMIN', 100001);

INSERT INTO meals (date_time, description, calories)
VALUES 
  ('2015-05-30 10:00:00', 'Завтрак', 500),
  ('2015-05-30 13:00:00', 'Обед', 1000),
  ('2015-05-30 20:00:00', 'Ужин', 500),
  ('2015-05-31 10:00:00', 'Завтрак', 1000),
  ('2015-05-31 13:00:00', 'Обед', 500),
  ('2015-05-31 20:00:00', 'Ужин', 510);
INSERT INTO users_meals (user_id, meal_id)
VALUES 
  (100000, 100002),
  (100000, 100003),
  (100000, 100004),
  (100000, 100005),
  (100000, 100006),
  (100000, 100007);
  