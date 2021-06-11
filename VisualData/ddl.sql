create table if not exists product
(
	id bigserial not null
		constraint product_pk
			primary key,
	name varchar(500) not null,
	price numeric(10,3) not null
);

alter table product owner to postgres;

create unique index if not exists product_name_uindex
	on product (name);

create table if not exists client
(
	id bigserial not null
		constraint client_pk
			primary key,
	name varchar(250) not null,
	middle_name varchar(250) not null,
	last_name varchar(250) not null,
	address varchar(500) not null,
	phone varchar(10) not null
);

alter table client owner to postgres;

create table if not exists "order"
(
	id bigserial not null
		constraint order_pk
			primary key,
	date timestamp default now() not null,
	client_id bigint not null
		constraint order_client_fk
			references client,
	sum numeric(10,3) default 0.0 not null
);

alter table "order" owner to postgres;

create table if not exists order_details
(
	product_id bigint not null
		constraint order_details_product_fk
			references product,
	order_id bigint not null
		constraint order_details_order_fk
			references "order",
	quantity integer not null,
	constraint order_details_pkey
		primary key (product_id, order_id)
);

alter table order_details owner to postgres;


create or replace function order_sum() returns trigger
	language plpgsql
as $$
DECLARE
    PRICE_SUM numeric(10,3);
BEGIN
    SELECT o.sum
    INTO PRICE_SUM
    FROM "order" o
    WHERE NEW.order_id = o.id;

    raise notice 'PRICE_SUM IS: % ', PRICE_SUM;

    SELECT PRICE_SUM + p.price*NEW.quantity
    INTO PRICE_SUM
    FROM product p WHERE NEW.product_id=p.id;

    raise notice 'PRICE_SUM IS: % ', PRICE_SUM;

    UPDATE "order" SET sum=PRICE_SUM WHERE id=NEW.order_id;

    RETURN NEW;
END;
$$;

alter function order_sum() owner to postgres;

create trigger order_sum_trigger
	before insert
	on order_details
	for each row
	execute procedure order_sum();

create or replace view order_products_view(id, name, quantity) as
	SELECT o.id,
       p.name,
       od.quantity
FROM "order" o
         JOIN order_details od ON o.id = od.order_id
         JOIN product p ON od.product_id = p.id;

alter table order_products_view owner to postgres;

create or replace view client_order_view(id, name, middle_name, last_name, address, phone, date, sum) as
SELECT o.id,
       c.name,
       c.middle_name,
       c.last_name,
       c.address,
       c.phone,
       o.date,
       o.sum
FROM client c
         JOIN "order" o ON o.client_id = c.id;

alter table client_order_view
    owner to postgres;
