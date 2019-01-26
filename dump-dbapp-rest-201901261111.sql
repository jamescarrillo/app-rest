--
-- PostgreSQL database dump
--

-- Dumped from database version 9.6.11
-- Dumped by pg_dump version 9.6.11

-- Started on 2019-01-26 11:11:23

SET statement_timeout = 0;
SET lock_timeout = 0;
SET idle_in_transaction_session_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SELECT pg_catalog.set_config('search_path', '', false);
SET check_function_bodies = false;
SET client_min_messages = warning;
SET row_security = off;

SET default_tablespace = '';

SET default_with_oids = false;

--
-- TOC entry 186 (class 1259 OID 54470)
-- Name: categoria; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.categoria (
    idcategoria integer NOT NULL,
    nombre character varying(50),
    fecha date,
    fecha_hora timestamp without time zone
);


ALTER TABLE public.categoria OWNER TO postgres;

--
-- TOC entry 185 (class 1259 OID 54468)
-- Name: categoria_idcategoria_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.categoria_idcategoria_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.categoria_idcategoria_seq OWNER TO postgres;

--
-- TOC entry 2138 (class 0 OID 0)
-- Dependencies: 185
-- Name: categoria_idcategoria_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.categoria_idcategoria_seq OWNED BY public.categoria.idcategoria;


--
-- TOC entry 187 (class 1259 OID 54476)
-- Name: temp_demo; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.temp_demo (
    id smallint NOT NULL,
    nombre character varying(20) NOT NULL
);


ALTER TABLE public.temp_demo OWNER TO postgres;

--
-- TOC entry 188 (class 1259 OID 54479)
-- Name: temp_demo_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.temp_demo_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.temp_demo_id_seq OWNER TO postgres;

--
-- TOC entry 2139 (class 0 OID 0)
-- Dependencies: 188
-- Name: temp_demo_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.temp_demo_id_seq OWNED BY public.temp_demo.id;


--
-- TOC entry 2007 (class 2604 OID 54473)
-- Name: categoria idcategoria; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.categoria ALTER COLUMN idcategoria SET DEFAULT nextval('public.categoria_idcategoria_seq'::regclass);


--
-- TOC entry 2008 (class 2604 OID 54481)
-- Name: temp_demo id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.temp_demo ALTER COLUMN id SET DEFAULT nextval('public.temp_demo_id_seq'::regclass);


--
-- TOC entry 2129 (class 0 OID 54470)
-- Dependencies: 186
-- Data for Name: categoria; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO public.categoria VALUES (1, 'COMIDAS ', '2019-09-21', '2018-09-10 14:20:00');


--
-- TOC entry 2140 (class 0 OID 0)
-- Dependencies: 185
-- Name: categoria_idcategoria_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.categoria_idcategoria_seq', 1, true);


--
-- TOC entry 2130 (class 0 OID 54476)
-- Dependencies: 187
-- Data for Name: temp_demo; Type: TABLE DATA; Schema: public; Owner: postgres
--



--
-- TOC entry 2141 (class 0 OID 0)
-- Dependencies: 188
-- Name: temp_demo_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.temp_demo_id_seq', 1, false);


--
-- TOC entry 2010 (class 2606 OID 54475)
-- Name: categoria categoria_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.categoria
    ADD CONSTRAINT categoria_pkey PRIMARY KEY (idcategoria);


-- Completed on 2019-01-26 11:11:23

--
-- PostgreSQL database dump complete
--

