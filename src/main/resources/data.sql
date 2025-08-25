insert IGNORE INTO roles (name) VALUES ('ROLE_ADMIN'), ('ROLE_USER');

insert IGNORE INTO users (username, password)
VALUES
    ('admin', '$2a$12$yci2zEIqUFsDowrGUKWqwuCFSRckqtgpcq6zlhGcUG03t7NW3w4Qe'),
    ('user', '$2a$12$JMfnGbdKzKayLkse2oNXl.ySyJbGlHB2q0oCe8Zkm5/5AekrhML0.');

insert into users_roles (user_id, role_id)
select
    (select id from users where username = 'admin'),
    (select id from roles where name = 'ROLE_ADMIN')
where not exists (
    select 1 from users_roles
    where user_id = (select id from users where username = 'admin')
    and role_id = (select id from roles where name = 'ROLE_ADMIN')
);

insert into users_roles (user_id, role_id)
select
    (select id from users where username = 'user'),
    (select id from roles where name = 'ROLE_USER')
where not exists (
    select 1 from users_roles
    where user_id = (select id from users where username = 'user')
    and role_id = (select id from roles where name = 'ROLE_USER')
);