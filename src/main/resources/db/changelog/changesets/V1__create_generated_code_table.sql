create table generated_code
(
    id            bigserial
        primary key,
    created_on    timestamp(6),
    updated_on    timestamp(6),
    last_code     varchar(255)
        constraint last_code_index
            unique,
    previous_code varchar(255)
        constraint previous_code_index
            unique
);

insert into generated_code(last_code) VALUES ('a0a0');