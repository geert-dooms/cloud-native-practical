ALTER TABLE shopping_list
    ADD COLUMN username varchar(50) not null default 'user'
    constraint fk_shopping_list_users references users(username);

UPDATE shopping_list
    set username = 'misterg';
