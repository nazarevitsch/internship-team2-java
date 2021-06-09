drop type if exists user_role;
create type user_role as enum('USER', 'ADMINISTRATOR');

CREATE EXTENSION IF NOT EXISTS "uuid-ossp";

drop table if exists users cascade;
create table users(
                      id uuid default uuid_generate_v4(),
                      name varchar(40),
                      email varchar(240),
                      password varchar(40),
                      user_role user_role,

                      location_id int
);

drop table if exists vendor cascade;
create table vendor(
                       id serial primary key,
                       name varchar(40)
);

drop table if exists discount cascade;
create table discount(
                         id serial primary key,
                         name varchar(40),
                         vendor_id int,
                         image_link varchar(255),
                         small_description varchar(255),
                         full_description text,
                         start_date date,
                         end_date date,
                         is_active bool,


                         is_online bool,
                         location_id int
);

drop table if exists tag cascade;
create table tag(
                    id serial primary key,
                    tag_name varchar(40)
);

drop table if exists used_discounts_history cascade;
create table used_discounts_history(
                                       id serial primary key,
                                       discount_id int,
                                       user_id int,
                                       using_date date,
                                       is_used bool
);

drop table if exists location cascade;
create table location(
                         id serial primary key,
                         country varchar(100),
                         city varchar(100),
                         street varchar(120),
                         house_number varchar(5),
                         additional varchar(255),

                         latitude numeric(9, 7),
                         longitude numeric(9, 7)
);

drop table if exists favourite_discount cascade;
create table favourite_discount(
                                   id serial primary key,
                                   discount_id int,
                                   user_id int
);


--Dependencies

drop table if exists discounts_tag cascade;
create table discounts_tag(
                              id serial primary key,
                              discount_id int,
                              tag_id int
);