-- Init first User, password: admin
INSERT INTO users (id, email, first_name, last_name, password)
VALUES (1, 'szebartek@gmail.com', 'Bartosz', 'Szeliga', '$2a$10$CvYqRZG4FMJjM1o84Ozqy.FOYrvXuC/X2D.hHWfOiu.kPyYL241U6');

INSERT INTO roles (id, rolename, user_id)
VALUES (1, 'Admin', 1);