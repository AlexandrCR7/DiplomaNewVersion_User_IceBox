insert into icebox (list)
values ('[мясо, морковь]');
insert into users (name, products, icebox_id)
values ('Alex', '[мясо, морковь]', 1);
insert into icebox_ingredient (user_id, icebox_id)
values (1, 1);