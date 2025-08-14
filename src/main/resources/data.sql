INSERT IGNORE INTO roles (name) VALUES ('ROLE_ADMIN'), ('ROLE_USER');

INSERT IGNORE INTO users (username, name, surname, age, email, password)
VALUES
    ('admin', 'Admin', 'Admin', 18, 'admin@example.com', '$2a$12$yci2zEIqUFsDowrGUKWqwuCFSRckqtgpcq6zlhGcUG03t7NW3w4Qe'),
    ('user', 'User', 'User', 18, 'user@example.com', '$2a$12$JMfnGbdKzKayLkse2oNXl.ySyJbGlHB2q0oCe8Zkm5/5AekrhML0.');

INSERT INTO users_roles (user_id, role_id)
SELECT
    (SELECT id FROM users WHERE username = 'admin'),
    (SELECT id FROM roles WHERE name = 'ROLE_ADMIN')
WHERE NOT EXISTS (
    SELECT 1 FROM users_roles
    WHERE user_id = (SELECT id FROM users WHERE username = 'admin')
    AND role_id = (SELECT id FROM roles WHERE name = 'ROLE_ADMIN')
);

INSERT INTO users_roles (user_id, role_id)
SELECT
    (SELECT id FROM users WHERE username = 'user'),
    (SELECT id FROM roles WHERE name = 'ROLE_USER')
WHERE NOT EXISTS (
    SELECT 1 FROM users_roles
    WHERE user_id = (SELECT id FROM users WHERE username = 'user')
    AND role_id = (SELECT id FROM roles WHERE name = 'ROLE_USER')
);