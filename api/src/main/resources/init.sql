create table books
(
    id           serial primary key,
    author       text not null,
    title        text not null,
    status       text not null,
    created_at   timestamp default now(),
    last_updated timestamp default now()
);

create table book_tags
(
    id   serial primary key,
    name text not null
);

create table books_tags
(
    book_id integer references books (id),
    tag_id  integer references book_tags (id)
);

-- Post

create table posts
(
    id      serial primary key,
    title   text      not null,
    date    timestamp not null default now(),
    content text      not null,
    summary text      not null
);