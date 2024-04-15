create table if not exists pizzas (
                                      id          bigserial,
                                      name        varchar not null,
                                      description varchar not null,
                                      constraint pizzas_pk primary key (id)
);
insert into pizzas(name, description) values ('Маргарита', 'Увеличенная порция моцареллы, томаты, итальянские травы, фирменный томатный соус'),
                                             ('Четыре сыра', 'Сыр дорблю, сыр сулугуни, моцарелла, песто, сыр пармезан'),
                                             ('Пепперони', 'Пикантная пепперони, увеличенная порция моцареллы, фирменный томатный соус'),
                                             ('Ветчина и грибы', 'Ветчина, шампиньоны, увеличенная порция моцареллы, фирменный томатный соус');
