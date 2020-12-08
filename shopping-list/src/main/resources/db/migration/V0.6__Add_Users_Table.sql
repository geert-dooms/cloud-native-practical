create table users(
                      username varchar(50) not null primary key,
                      password varchar(500) not null,
                      enabled boolean not null
);

create table authorities (
                             username varchar(50) not null,
                             authority varchar(50) not null,
                             constraint fk_authorities_users foreign key(username) references users(username)
);
create unique index ix_auth_username on authorities (username,authority);

INSERT INTO users (username, password, enabled) VALUES ('user', 'pass', 'true');
INSERT INTO users (username, password, enabled) VALUES ('misterg', 'pass', 'true');
INSERT INTO users (username, password, enabled) VALUES ('admin', 'pass', 'true');

INSERT INTO authorities (username, authority) VALUES ('user', 'ROLE_USER');
INSERT INTO authorities (username, authority) VALUES ('misterg', 'ROLE_USER');
INSERT INTO authorities (username, authority) VALUES ('admin', 'ROLE_ADMIN');
