CREATE SEQUENCE public.funcionario_id_seq
    INCREMENT 1
    START 2
    MINVALUE 1
    MAXVALUE 9223372036854775807
    CACHE 1;

CREATE SEQUENCE public.recurso_id_seq
    INCREMENT 1
    START 1
    MINVALUE 1
    MAXVALUE 9223372036854775807
    CACHE 1;

CREATE SEQUENCE public.voto_id_seq
    INCREMENT 1
    START 1
    MINVALUE 1
    MAXVALUE 9223372036854775807
    CACHE 1;

CREATE EXTENSION pgcrypto
    SCHEMA public
    VERSION "1.3";

CREATE TABLE public.funcionario
(
    id bigint NOT NULL DEFAULT nextval('funcionario_id_seq'::regclass),
    email character varying(255) COLLATE pg_catalog."default" NOT NULL,
    nome character varying(255) COLLATE pg_catalog."default",
    senha character varying(255) COLLATE pg_catalog."default" NOT NULL,
    CONSTRAINT funcionario_pkey PRIMARY KEY (id)
)
WITH (
    OIDS = FALSE
)
TABLESPACE pg_default;


CREATE TABLE public.recurso
(
    id bigint NOT NULL DEFAULT nextval('recurso_id_seq'::regclass),
    descricao character varying(255) COLLATE pg_catalog."default",
    nome character varying(255) COLLATE pg_catalog."default",
    CONSTRAINT recurso_pkey PRIMARY KEY (id)
)
WITH (
    OIDS = FALSE
)
TABLESPACE pg_default;

CREATE TABLE public.voto
(
    id bigint NOT NULL DEFAULT nextval('voto_id_seq'::regclass),
    comentario character varying(255) COLLATE pg_catalog."default" NOT NULL,
    data timestamp with time zone NOT NULL,
    funcionario_id bigint,
    recurso_id bigint,
    CONSTRAINT voto_pkey PRIMARY KEY (id),
    CONSTRAINT fk6ypprtcow9m4itp3w4v6xtxpu FOREIGN KEY (recurso_id)
        REFERENCES public.recurso (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION,
    CONSTRAINT fkeoo5rk53a86m3gbabr4i5457t FOREIGN KEY (funcionario_id)
        REFERENCES public.funcionario (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
)
WITH (
    OIDS = FALSE
)
TABLESPACE pg_default;