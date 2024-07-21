drop database if exists devsu_challenge;

create database devsu_challenge;

use devsu_challenge;

create table document_types
(
    id           tinyint      not null primary key,
    description  varchar(100) not null,
    created_date timestamp    not null default current_timestamp,
    updated_date timestamp    not null default current_timestamp
);

create table persons
(
    id              integer      not null primary key auto_increment,
    name            varchar(100) not null,
    gender          varchar(100),
    document_type   tinyint,
    document_number varchar(20),
    address         varchar(500),
    phone           varchar(50),
    created_date    timestamp             default current_timestamp,
    updated_date    timestamp    not null default current_timestamp
);

create table customers
(
    id           integer   not null primary key,
    password     nvarchar(2500),
    status       bit       not null default 1,
    created_date timestamp not null default current_timestamp,
    updated_date timestamp not null default current_timestamp
);

create table account_types
(
    id           int          not null primary key auto_increment,
    name         varchar(150) not null,
    description  nvarchar(2500),
    created_date timestamp    not null default current_timestamp,
    updated_date timestamp    not null default current_timestamp
);

create table accounts
(
    id              integer        not null primary key auto_increment,
    customer_id     integer        not null,
    account_type_id integer        not null,
    number          varchar(200)   not null,
    initial_balance decimal(30, 3) not null default 0.0,
    current_balance decimal(30, 3) not null default 0.0,
    status          bit            not null default 1,
    created_date    timestamp      not null default current_timestamp,
    updated_date    timestamp      not null default current_timestamp
);

create table movement_types
(
    id              int          not null primary key auto_increment,
    name            varchar(150) not null,
    description     nvarchar(2500),
    -- 0 => subtract
    -- 1 => add
    adjustment_type bit          not null default 0,
    created_date    timestamp    not null default current_timestamp,
    updated_date    timestamp    not null default current_timestamp
);

create table movements
(
    id              bigint         not null primary key auto_increment,
    account_id      integer        not null,
    movement_date   timestamp      not null default current_timestamp,
    movement_amount decimal(30, 3) not null,
    movement_type   int            not null,
    account_balance decimal(30, 3) not null,
    created_date    timestamp      not null default current_timestamp,
    updated_date    timestamp      not null default current_timestamp
);

create table report
(
    id              bigint  not null primary key auto_increment,
    customer_id     integer not null,
    account_id      int     not null,
    movement_id     bigint  not null,
    movement_date   timestamp,
    customer        varchar(100),
    account_number  varchar(200),
    account_type    varchar(150),
    movement_status bit,
    bef_mov_balance decimal(30, 3),
    movement_amount decimal(30, 3),
    aft_mov_balance decimal(30, 3)
);

alter table customers
    add constraint fk_customer_id
        foreign key (id)
            references persons (id);

alter table persons
    add constraint fk_person_document_type
        foreign key (document_type)
            references document_types (id);

alter table accounts
    add constraint fk_account_customer
        foreign key (customer_id)
            references customers (id);

alter table accounts
    add constraint fk_account_type
        foreign key (account_type_id)
            references account_types (id);

alter table movements
    add constraint fk_movement_account
        foreign key (account_id)
            references accounts (id);

alter table movements
    add constraint fk_movement_type
        foreign key (movement_type)
            references movement_types (id);

alter table movements
    add constraint ck_movement_amount
        check (movement_amount <> 0);

create index idx_report_date
    on report (movement_date desc);

delimiter $$
create trigger tr_new_movement
    after insert
    on movements
    for each row
begin
    insert into report (customer_id,
                        account_id,
                        movement_id,
                        movement_date,
                        customer,
                        account_number,
                        account_type,
                        movement_status,
                        bef_mov_balance,
                        movement_amount,
                        aft_mov_balance)
    select a.customer_id,
           new.account_id,
           new.id,
           new.movement_date,
           p.name,
           a.number,
           at.name,
           a.status,
           new.account_balance,
           new.movement_amount,
           new.account_balance + new.movement_amount
    from accounts a,
         customers c,
         persons p,
         account_types at
    where a.customer_id = c.id
      and c.id = p.id
      and a.account_type_id = at.id
      and a.id = new.account_id;
end $$

insert into document_types (id, description) values (1, 'Documento Nacional de Identidad');
insert into document_types (id, description) values (2, 'Pasaporte');
insert into document_types (id, description) values (3, 'Carnét de extranjería');

insert into account_types (name, description)
values ('Corriente', 'Cuenta de gastos generales');
insert into account_types (name, description)
values ('Sueldo', 'Cuenta de recepción de salario dependiente');
insert into account_types (name, description)
values ('Ahorros', 'Cuenta de mayor crecimiento de dinero');

insert into movement_types (name)
values ('Retiro');
insert into movement_types (name, adjustment_type)
values ('Depósito', 1);
insert into movement_types (name)
values ('Compra');
