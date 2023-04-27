--liquibase formatted sql

--changed elenazmeeva:1
CREATE TABLE label(
   id serial PRIMARY KEY,
   title text NOT NULL
);
CREATE TABLE user(
    id SERIAL PRIMARY KEY,
    full_name TEXT NOT NULL,
    email TEXT NOT NULL
);


CREATE TABLE task(
    id serial PRIMARY KEY,
    title text NOT NULL,
    description text NOT NULL,
    status text,
    created_at timestamp,
    updated_at timestamp,
    user_id serial references user(id),
    label_id serial references label(id)
);