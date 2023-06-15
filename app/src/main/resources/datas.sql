insert into city (id, name)
values (1, 'Житомир');
insert into city (id, name)
values (2, 'Каунас');
insert into city (id, name)
values (3, 'Вильюс');
insert into city (id, name)
values (4, 'Волгоград');
insert into city (id, name)
values (5, 'Рига');
insert into city (id, name)
values (6, 'С.Петербург');
insert into city (id, name)
values (7, 'Москва');
insert into city (id, name)
values (8, 'Вильнюс');
insert into city (id, name)
values (9, 'Даугавпилс');
insert into city (id, name)
values (10, 'Таллинн');
insert into city (id, name)
values (11, 'Калининград');
insert into city (id, name)
values (12, 'Киев');
insert into city (id, name)
values (13, 'Витебск');
insert into city (id, name)
values (14, 'Минск');
insert into city (id, name)
values (15, 'Одесса');
insert into city (id, name)
values (16, 'Воронеж');
insert into city (id, name)
values (17, 'Кишинев');
insert into city (id, name)
values (18, 'Уфа');
insert into city (id, name)
values (19, 'Самара');
insert into city (id, name)
values (20, 'Казань');
insert into city (id, name)
values (21, 'Ярославль');
insert into city (id, name)
values (22, 'Орел');
insert into city (id, name)
values (23, 'Брест');
insert into city (id, name)
values (24, 'Харьков');
insert into city (id, name)
values (25, 'Ниж.Новгород');
insert into city (id, name)
values (26, 'Мурманск');
insert into city (id, name)
values (27, 'Донецк');
insert into city (id, name)
values (28, 'Симферополь');

insert into distance (length, ct1_id, ct2_id)
values (531, 8, 23);
insert into distance (length, ct1_id, ct2_id)
values (638, 13, 23);
insert into distance (length, ct1_id, ct2_id)
values (360, 13, 3);
insert into distance (length, ct1_id, ct2_id)
values (869, 16, 13);
insert into distance (length, ct1_id, ct2_id)
values (581, 16, 4);
insert into distance (length, ct1_id, ct2_id)
values (1455, 4, 13);
insert into distance (length, ct1_id, ct2_id)
values (911, 13, 25);
insert into distance (length, ct1_id, ct2_id)
values (211, 8, 9);
insert into distance (length, ct1_id, ct2_id)
values (699, 11, 23);
insert into distance (length, ct1_id, ct2_id)
values (333, 11, 8);
insert into distance (length, ct1_id, ct2_id)
values (734, 12, 8);
insert into distance (length, ct1_id, ct2_id)
values (131, 12, 1);
insert into distance (length, ct1_id, ct2_id)
values (863, 1, 27);
insert into distance (length, ct1_id, ct2_id)
values (1493, 1, 4);
insert into distance (length, ct1_id, ct2_id)
values (467, 17, 12);
insert into distance (length, ct1_id, ct2_id)
values (812, 17, 27);
insert into distance (length, ct1_id, ct2_id)
values (602, 6, 13);
insert into distance (length, ct1_id, ct2_id)
values (739, 6, 11);
insert into distance (length, ct1_id, ct2_id)
values (641, 6, 5);
insert into distance (length, ct1_id, ct2_id)
values (815, 7, 20);
insert into distance (length, ct1_id, ct2_id)
values (411, 7, 25);
insert into distance (length, ct1_id, ct2_id)
values (690, 7, 14);
insert into distance (length, ct1_id, ct2_id)
values (1084, 7, 27);
insert into distance (length, ct1_id, ct2_id)
values (664, 7, 6);
insert into distance (length, ct1_id, ct2_id)
values (1412, 26, 6);
insert into distance (length, ct1_id, ct2_id)
values (2238, 26, 14);
insert into distance (length, ct1_id, ct2_id)
values (522, 22, 13);
insert into distance (length, ct1_id, ct2_id)
values (709, 22, 27);
insert into distance (length, ct1_id, ct2_id)
values (368, 22, 7);
insert into distance (length, ct1_id, ct2_id)
values (487, 15, 12);
insert into distance (length, ct1_id, ct2_id)
values (267, 5, 2);
insert into distance (length, ct1_id, ct2_id)
values (308, 10, 5);
insert into distance (length, ct1_id, ct2_id)
values (471, 24, 12);
insert into distance (length, ct1_id, ct2_id)
values (639, 24, 28);
insert into distance (length, ct1_id, ct2_id)
values (739, 21, 16);
insert into distance (length, ct1_id, ct2_id)
values (940, 21, 14);
insert into distance (length, ct1_id, ct2_id)
values (525, 18, 20);
insert into distance (length, ct1_id, ct2_id)
values (461, 18, 19);


create sequence item_seq start 1;
insert into item (id, name, price)
values (nextval('item_seq'), 'apple', 100);
insert into item (id, name, price)
values (nextval('item_seq'), 'orange', 150);
insert into item (id, name, price)
values (nextval('item_seq'), 'watermelon', 120);
insert into item (id, name, price)
values (nextval('item_seq'), 'strawberry', 130);
insert into item (id, name, price)
values (nextval('item_seq'), 'blueberry', 100);
insert into item (id, name, price)
values (nextval('item_seq'), 'potato', 35);
insert into item (id, name, price)
values (nextval('item_seq'), 'tomato', 59);
insert into item (id, name, price)
values (nextval('item_seq'), 'garlic', 85);
insert into item (id, name, price)
values (nextval('item_seq'), 'pesto', 140);
insert into item (id, name, price)
values (nextval('item_seq'), 'sandwich', 250);
insert into item (id, name, price)
values (nextval('item_seq'), 'beef', 550);
insert into item (id, name, price)
values (nextval('item_seq'), 'pork', 340);
insert into item (id, name, price)
values (nextval('item_seq'), 'chicken', 290);


insert into item_cities (item_id, cities_id)
values (1, 1);
insert into item_cities (item_id, cities_id)
values (1, 2);
insert into item_cities (item_id, cities_id)
values (1, 3);
insert into item_cities (item_id, cities_id)
values (1, 4);
insert into item_cities (item_id, cities_id)
values (1, 8);
insert into item_cities (item_id, cities_id)
values (1, 9);
insert into item_cities (item_id, cities_id)
values (1, 20);
insert into item_cities (item_id, cities_id)
values (1, 21);
insert into item_cities (item_id, cities_id)
values (1, 10);
insert into item_cities (item_id, cities_id)
values (1, 11);
insert into item_cities (item_id, cities_id)
values (1, 24);
insert into item_cities (item_id, cities_id)
values (1, 25);


insert into item_cities (item_id, cities_id)
values (2, 2);
insert into item_cities (item_id, cities_id)
values (2, 4);
insert into item_cities (item_id, cities_id)
values (2, 6);
insert into item_cities (item_id, cities_id)
values (2, 8);
insert into item_cities (item_id, cities_id)
values (2, 10);
insert into item_cities (item_id, cities_id)
values (2, 12);
insert into item_cities (item_id, cities_id)
values (2, 14);
insert into item_cities (item_id, cities_id)
values (2, 16);
insert into item_cities (item_id, cities_id)
values (2, 18);
insert into item_cities (item_id, cities_id)
values (2, 20);

insert into item_cities (item_id, cities_id)
values (3, 1);
insert into item_cities (item_id, cities_id)
values (3, 4);
insert into item_cities (item_id, cities_id)
values (3, 7);
insert into item_cities (item_id, cities_id)
values (3, 10);
insert into item_cities (item_id, cities_id)
values (3, 13);
insert into item_cities (item_id, cities_id)
values (3, 17);
insert into item_cities (item_id, cities_id)
values (3, 25);
insert into item_cities (item_id, cities_id)
values (3, 28);

insert into item_cities (item_id, cities_id)
values (4, 28);
insert into item_cities (item_id, cities_id)
values (4, 27);
insert into item_cities (item_id, cities_id)
values (4, 26);
insert into item_cities (item_id, cities_id)
values (4, 25);
insert into item_cities (item_id, cities_id)
values (4, 24);
insert into item_cities (item_id, cities_id)
values (4, 23);
insert into item_cities (item_id, cities_id)
values (4, 22);
insert into item_cities (item_id, cities_id)
values (4, 21);
insert into item_cities (item_id, cities_id)
values (4, 20);

insert into item_cities (item_id, cities_id)
values (5, 1);
insert into item_cities (item_id, cities_id)
values (5, 2);
insert into item_cities (item_id, cities_id)
values (5, 3);
insert into item_cities (item_id, cities_id)
values (5, 4);
insert into item_cities (item_id, cities_id)
values (5, 5);
insert into item_cities (item_id, cities_id)
values (5, 6);
insert into item_cities (item_id, cities_id)
values (5, 7);
insert into item_cities (item_id, cities_id)
values (5, 8);
insert into item_cities (item_id, cities_id)
values (5, 9);
insert into item_cities (item_id, cities_id)
values (5, 10);

insert into item_cities (item_id, cities_id)
values (6, 12);
insert into item_cities (item_id, cities_id)
values (6, 13);
insert into item_cities (item_id, cities_id)
values (6, 14);
insert into item_cities (item_id, cities_id)
values (6, 15);
insert into item_cities (item_id, cities_id)
values (6, 16);
insert into item_cities (item_id, cities_id)
values (6, 17);
insert into item_cities (item_id, cities_id)
values (6, 18);
insert into item_cities (item_id, cities_id)
values (6, 19);
insert into item_cities (item_id, cities_id)
values (6, 20);
insert into item_cities (item_id, cities_id)
values (6, 21);
insert into item_cities (item_id, cities_id)
values (6, 22);

insert into item_cities (item_id, cities_id)
values (7, 1);
insert into item_cities (item_id, cities_id)
values (7, 9);
insert into item_cities (item_id, cities_id)
values (7, 15);
insert into item_cities (item_id, cities_id)
values (7, 23);
insert into item_cities (item_id, cities_id)
values (7, 28);

insert into item_cities (item_id, cities_id)
values (8, 12);
insert into item_cities (item_id, cities_id)
values (8, 22);
insert into item_cities (item_id, cities_id)
values (8, 27);
insert into item_cities (item_id, cities_id)
values (8, 28);

insert into item_cities (item_id, cities_id)
values (9, 1);
insert into item_cities (item_id, cities_id)
values (9, 2);
insert into item_cities (item_id, cities_id)
values (9, 3);

insert into item_cities (item_id, cities_id)
values (10, 3);
insert into item_cities (item_id, cities_id)
values (10, 9);
insert into item_cities (item_id, cities_id)
values (10, 15);
insert into item_cities (item_id, cities_id)
values (10, 21);
insert into item_cities (item_id, cities_id)
values (10, 27);
insert into item_cities (item_id, cities_id)
values (10, 28);

insert into item_cities (item_id, cities_id)
values (11, 21);
insert into item_cities (item_id, cities_id)
values (11, 22);
insert into item_cities (item_id, cities_id)
values (11, 23);
insert into item_cities (item_id, cities_id)
values (11, 24);
insert into item_cities (item_id, cities_id)
values (11, 25);
insert into item_cities (item_id, cities_id)
values (11, 26);
insert into item_cities (item_id, cities_id)
values (11, 27);
insert into item_cities (item_id, cities_id)
values (11, 28);

insert into item_cities (item_id, cities_id)
values (12, 1);

insert into item_cities (item_id, cities_id)
values (13, 2);

insert into coupon (discount, name)
values (0.1, 'DISCOUNT10');
insert into coupon (discount, name)
values (0.2, 'DISCOUNT20');
insert into coupon (discount, name)
values (0.3, 'DISCOUNT30');
insert into coupon (discount, name)
values (0.5, 'DISCOUNT50');
