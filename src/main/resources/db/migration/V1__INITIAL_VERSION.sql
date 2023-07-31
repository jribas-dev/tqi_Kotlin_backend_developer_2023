CREATE TABLE IF NOT EXISTS public.category
(
    category_id SERIAL NOT NULL ,
    category_parent integer,
    category_name character varying(255) COLLATE pg_catalog."default" NOT NULL,
    CONSTRAINT category_pkey PRIMARY KEY (category_id)
)
    TABLESPACE pg_default;

ALTER TABLE IF EXISTS public.category
    OWNER to "user";


CREATE TABLE IF NOT EXISTS public.product
(
    category_id integer,
    unit_price numeric(38,2) NOT NULL,
    product_id BIGSERIAL NOT NULL,
    product_name character varying(255) COLLATE pg_catalog."default" NOT NULL,
    unit character varying(255) COLLATE pg_catalog."default" NOT NULL,
    CONSTRAINT product_pkey PRIMARY KEY (product_id),
    CONSTRAINT fk1mtsbur82frn64de7balymq9s FOREIGN KEY (category_id)
        REFERENCES public.category (category_id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
)
    TABLESPACE pg_default;

ALTER TABLE IF EXISTS public.product
    OWNER to "user";


CREATE TABLE IF NOT EXISTS public.cart
(
    cart_status smallint,
    cart_code uuid NOT NULL,
    CONSTRAINT cart_pkey PRIMARY KEY (cart_code),
    CONSTRAINT cart_cart_status_check CHECK (cart_status >= 0 AND cart_status <= 1)
)
    TABLESPACE pg_default;
ALTER TABLE IF EXISTS public.cart
    OWNER to "user";


CREATE TABLE IF NOT EXISTS public.cart_items
(
    sale_price double precision NOT NULL,
    sale_qtd double precision NOT NULL,
    item_id BIGSERIAL NOT NULL,
    product_id bigint NOT NULL,
    cart_code uuid NOT NULL,
    CONSTRAINT cart_items_pkey PRIMARY KEY (item_id),
    CONSTRAINT fkaabi0pf9vqx926896h0umtp0f FOREIGN KEY (cart_code)
        REFERENCES public.cart (cart_code) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION,
    CONSTRAINT fkl7je3auqyq1raj52qmwrgih8x FOREIGN KEY (product_id)
        REFERENCES public.product (product_id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
)
    TABLESPACE pg_default;

ALTER TABLE IF EXISTS public.cart_items
    OWNER to "user";


CREATE TABLE IF NOT EXISTS public.sale
(
    sale_status smallint,
    total_paid double precision NOT NULL,
    total_sale double precision NOT NULL,
    sale_date timestamp(6) without time zone NOT NULL,
    sale_id BIGSERIAL NOT NULL,
    cart_code uuid NOT NULL,
    CONSTRAINT sale_pkey PRIMARY KEY (sale_id),
    CONSTRAINT sale_cart_code_key UNIQUE (cart_code),
    CONSTRAINT fkr5g8ur5bllqv0n55mxmmjeboo FOREIGN KEY (cart_code)
        REFERENCES public.cart (cart_code) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION,
    CONSTRAINT sale_sale_status_check CHECK (sale_status >= 0 AND sale_status <= 2)
)
    TABLESPACE pg_default;

ALTER TABLE IF EXISTS public.sale
    OWNER to "user";


INSERT INTO public.category(
    category_parent, category_name)
VALUES
    (NULL, 'PADARIA'),
    (NULL, 'AÇOUGUE'),
    (NULL, 'MERCEARIA'),
    (NULL, 'HORTIFRUTI');


INSERT INTO public.product(
    category_id, unit_price, product_name, unit)
VALUES
    (2, 89.90, 'PICANHA ARGENTINA', 'KG'),
    (2, 59.90, 'MAMINHA', 'KG'),
    (1, 29.90, 'ROSCA COM LEITE CONDENSADO', 'KG'),
    (1, 8.90, 'BISCOITO DE CHOCOLATE', 'KG');
