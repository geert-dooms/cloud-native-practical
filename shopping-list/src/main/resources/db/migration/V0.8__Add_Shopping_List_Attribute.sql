ALTER TABLE shopping_list
    ADD COLUMN username varchar(50) not null default 'user';
ALTER TABLE shopping_list
    ADD constraint fk_shopping_list_users FOREIGN KEY (username) references users(username);

UPDATE shopping_list
    set username = 'misterg';
