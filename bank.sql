--
-- PostgreSQL database dump
--

-- Dumped from database version 12.9 (Ubuntu 12.9-0ubuntu0.20.04.1)
-- Dumped by pg_dump version 12.9 (Ubuntu 12.9-0ubuntu0.20.04.1)

SET statement_timeout = 0;
SET lock_timeout = 0;
SET idle_in_transaction_session_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SELECT pg_catalog.set_config('search_path', '', false);
SET check_function_bodies = false;
SET xmloption = content;
SET client_min_messages = warning;
SET row_security = off;

SET default_tablespace = '';

SET default_table_access_method = heap;

--
-- Name: bank_account; Type: TABLE; Schema: public; Owner: ynovuser
--

CREATE TABLE public.bank_account (
    id integer NOT NULL,
    id_usr integer NOT NULL,
    account_type character varying,
    amount double precision
);


ALTER TABLE public.bank_account OWNER TO ynovuser;

--
-- Name: bank_user; Type: TABLE; Schema: public; Owner: ynovuser
--

CREATE TABLE public.bank_user (
    id integer NOT NULL,
    nom character varying,
    password character varying
);


ALTER TABLE public.bank_user OWNER TO ynovuser;

--
-- Data for Name: bank_account; Type: TABLE DATA; Schema: public; Owner: ynovuser
--

COPY public.bank_account (id, id_usr, account_type, amount) FROM stdin;
\.


--
-- Data for Name: bank_user; Type: TABLE DATA; Schema: public; Owner: ynovuser
--

COPY public.bank_user (id, nom, password) FROM stdin;
\.


--
-- Name: bank_account bank_account_pkey; Type: CONSTRAINT; Schema: public; Owner: ynovuser
--

ALTER TABLE ONLY public.bank_account
    ADD CONSTRAINT bank_account_pkey PRIMARY KEY (id);


--
-- Name: bank_user bank_user_pkey; Type: CONSTRAINT; Schema: public; Owner: ynovuser
--

ALTER TABLE ONLY public.bank_user
    ADD CONSTRAINT bank_user_pkey PRIMARY KEY (id);


--
-- Name: bank_account bank_account_id_usr_fkey; Type: FK CONSTRAINT; Schema: public; Owner: ynovuser
--

ALTER TABLE ONLY public.bank_account
    ADD CONSTRAINT bank_account_id_usr_fkey FOREIGN KEY (id_usr) REFERENCES public.bank_user(id);


--
-- PostgreSQL database dump complete
--

