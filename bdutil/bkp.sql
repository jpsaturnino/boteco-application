PGDMP                     
    w            botecodb    10.10    10.10 C    ?           0    0    ENCODING    ENCODING        SET client_encoding = 'UTF8';
                       false            @           0    0 
   STDSTRINGS 
   STDSTRINGS     (   SET standard_conforming_strings = 'on';
                       false            A           0    0 
   SEARCHPATH 
   SEARCHPATH     8   SELECT pg_catalog.set_config('search_path', '', false);
                       false            B           1262    17786    botecodb    DATABASE     �   CREATE DATABASE botecodb WITH TEMPLATE = template0 ENCODING = 'UTF8' LC_COLLATE = 'Portuguese_Brazil.1252' LC_CTYPE = 'Portuguese_Brazil.1252';
    DROP DATABASE botecodb;
             postgres    false                        2615    2200    public    SCHEMA        CREATE SCHEMA public;
    DROP SCHEMA public;
             postgres    false            C           0    0    SCHEMA public    COMMENT     6   COMMENT ON SCHEMA public IS 'standard public schema';
                  postgres    false    3                        3079    12924    plpgsql 	   EXTENSION     ?   CREATE EXTENSION IF NOT EXISTS plpgsql WITH SCHEMA pg_catalog;
    DROP EXTENSION plpgsql;
                  false            D           0    0    EXTENSION plpgsql    COMMENT     @   COMMENT ON EXTENSION plpgsql IS 'PL/pgSQL procedural language';
                       false    1            �            1259    17787 	   categoria    TABLE     l   CREATE TABLE public.categoria (
    cat_id integer NOT NULL,
    cat_nome character varying(15) NOT NULL
);
    DROP TABLE public.categoria;
       public         postgres    false    3            �            1259    17790    categoria_cat_id_seq    SEQUENCE     }   CREATE SEQUENCE public.categoria_cat_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 +   DROP SEQUENCE public.categoria_cat_id_seq;
       public       postgres    false    3    196            E           0    0    categoria_cat_id_seq    SEQUENCE OWNED BY     M   ALTER SEQUENCE public.categoria_cat_id_seq OWNED BY public.categoria.cat_id;
            public       postgres    false    197            �            1259    17792    comanda    TABLE     6  CREATE TABLE public.comanda (
    com_id integer NOT NULL,
    gar_id integer NOT NULL,
    com_numero numeric(15,0) NOT NULL,
    com_nome character varying(40),
    com_data timestamp without time zone,
    com_desc character varying(255) NOT NULL,
    com_valor numeric(8,2),
    com_status character(1)
);
    DROP TABLE public.comanda;
       public         postgres    false    3            �            1259    17795    comanda_com_id_seq    SEQUENCE     {   CREATE SEQUENCE public.comanda_com_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 )   DROP SEQUENCE public.comanda_com_id_seq;
       public       postgres    false    198    3            F           0    0    comanda_com_id_seq    SEQUENCE OWNED BY     I   ALTER SEQUENCE public.comanda_com_id_seq OWNED BY public.comanda.com_id;
            public       postgres    false    199            �            1259    17797    garcon    TABLE     _  CREATE TABLE public.garcon (
    gar_id integer NOT NULL,
    gar_nome character varying(40) NOT NULL,
    gar_cpf character varying(14) NOT NULL,
    gar_cep character varying(10),
    gar_endereco character varying(50),
    gar_cidade character varying(20),
    gar_uf character varying(2),
    gar_fone character varying(15),
    gar_foto bytea
);
    DROP TABLE public.garcon;
       public         postgres    false    3            �            1259    17803    garcon_gar_id_seq    SEQUENCE     z   CREATE SEQUENCE public.garcon_gar_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 (   DROP SEQUENCE public.garcon_gar_id_seq;
       public       postgres    false    3    200            G           0    0    garcon_gar_id_seq    SEQUENCE OWNED BY     G   ALTER SEQUENCE public.garcon_gar_id_seq OWNED BY public.garcon.gar_id;
            public       postgres    false    201            �            1259    17805    item    TABLE     |   CREATE TABLE public.item (
    com_id integer NOT NULL,
    prod_id integer NOT NULL,
    it_quantidade integer NOT NULL
);
    DROP TABLE public.item;
       public         postgres    false    3            �            1259    17808 	   pagamento    TABLE     �   CREATE TABLE public.pagamento (
    pag_id integer NOT NULL,
    com_id integer,
    pag_valor numeric(8,2),
    tpg_id integer
);
    DROP TABLE public.pagamento;
       public         postgres    false    3            �            1259    17811    pagamento_pag_id_seq    SEQUENCE     }   CREATE SEQUENCE public.pagamento_pag_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 +   DROP SEQUENCE public.pagamento_pag_id_seq;
       public       postgres    false    3    203            H           0    0    pagamento_pag_id_seq    SEQUENCE OWNED BY     M   ALTER SEQUENCE public.pagamento_pag_id_seq OWNED BY public.pagamento.pag_id;
            public       postgres    false    204            �            1259    17813    produto    TABLE     �   CREATE TABLE public.produto (
    prod_id integer NOT NULL,
    cat_id integer NOT NULL,
    uni_id integer NOT NULL,
    prod_nome character varying(30) NOT NULL,
    prod_preco numeric(8,2) NOT NULL,
    prod_descr character varying(100)
);
    DROP TABLE public.produto;
       public         postgres    false    3            �            1259    17816    produto_prod_id_seq    SEQUENCE     |   CREATE SEQUENCE public.produto_prod_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 *   DROP SEQUENCE public.produto_prod_id_seq;
       public       postgres    false    3    205            I           0    0    produto_prod_id_seq    SEQUENCE OWNED BY     K   ALTER SEQUENCE public.produto_prod_id_seq OWNED BY public.produto.prod_id;
            public       postgres    false    206            �            1259    17818    tipopgto    TABLE     b   CREATE TABLE public.tipopgto (
    tpg_id integer NOT NULL,
    tpg_nome character varying(15)
);
    DROP TABLE public.tipopgto;
       public         postgres    false    3            �            1259    17821    tipopgto_tpg_id_seq    SEQUENCE     |   CREATE SEQUENCE public.tipopgto_tpg_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 *   DROP SEQUENCE public.tipopgto_tpg_id_seq;
       public       postgres    false    207    3            J           0    0    tipopgto_tpg_id_seq    SEQUENCE OWNED BY     K   ALTER SEQUENCE public.tipopgto_tpg_id_seq OWNED BY public.tipopgto.tpg_id;
            public       postgres    false    208            �            1259    17823    unidade    TABLE     j   CREATE TABLE public.unidade (
    uni_id integer NOT NULL,
    uni_nome character varying(15) NOT NULL
);
    DROP TABLE public.unidade;
       public         postgres    false    3            �            1259    17826    unidade_uni_id_seq    SEQUENCE     {   CREATE SEQUENCE public.unidade_uni_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 )   DROP SEQUENCE public.unidade_uni_id_seq;
       public       postgres    false    209    3            K           0    0    unidade_uni_id_seq    SEQUENCE OWNED BY     I   ALTER SEQUENCE public.unidade_uni_id_seq OWNED BY public.unidade.uni_id;
            public       postgres    false    210            �
           2604    17828    categoria cat_id    DEFAULT     t   ALTER TABLE ONLY public.categoria ALTER COLUMN cat_id SET DEFAULT nextval('public.categoria_cat_id_seq'::regclass);
 ?   ALTER TABLE public.categoria ALTER COLUMN cat_id DROP DEFAULT;
       public       postgres    false    197    196            �
           2604    17829    comanda com_id    DEFAULT     p   ALTER TABLE ONLY public.comanda ALTER COLUMN com_id SET DEFAULT nextval('public.comanda_com_id_seq'::regclass);
 =   ALTER TABLE public.comanda ALTER COLUMN com_id DROP DEFAULT;
       public       postgres    false    199    198            �
           2604    17830    garcon gar_id    DEFAULT     n   ALTER TABLE ONLY public.garcon ALTER COLUMN gar_id SET DEFAULT nextval('public.garcon_gar_id_seq'::regclass);
 <   ALTER TABLE public.garcon ALTER COLUMN gar_id DROP DEFAULT;
       public       postgres    false    201    200            �
           2604    17831    pagamento pag_id    DEFAULT     t   ALTER TABLE ONLY public.pagamento ALTER COLUMN pag_id SET DEFAULT nextval('public.pagamento_pag_id_seq'::regclass);
 ?   ALTER TABLE public.pagamento ALTER COLUMN pag_id DROP DEFAULT;
       public       postgres    false    204    203            �
           2604    17832    produto prod_id    DEFAULT     r   ALTER TABLE ONLY public.produto ALTER COLUMN prod_id SET DEFAULT nextval('public.produto_prod_id_seq'::regclass);
 >   ALTER TABLE public.produto ALTER COLUMN prod_id DROP DEFAULT;
       public       postgres    false    206    205            �
           2604    17833    tipopgto tpg_id    DEFAULT     r   ALTER TABLE ONLY public.tipopgto ALTER COLUMN tpg_id SET DEFAULT nextval('public.tipopgto_tpg_id_seq'::regclass);
 >   ALTER TABLE public.tipopgto ALTER COLUMN tpg_id DROP DEFAULT;
       public       postgres    false    208    207            �
           2604    17834    unidade uni_id    DEFAULT     p   ALTER TABLE ONLY public.unidade ALTER COLUMN uni_id SET DEFAULT nextval('public.unidade_uni_id_seq'::regclass);
 =   ALTER TABLE public.unidade ALTER COLUMN uni_id DROP DEFAULT;
       public       postgres    false    210    209            .          0    17787 	   categoria 
   TABLE DATA               5   COPY public.categoria (cat_id, cat_nome) FROM stdin;
    public       postgres    false    196   G       0          0    17792    comanda 
   TABLE DATA               r   COPY public.comanda (com_id, gar_id, com_numero, com_nome, com_data, com_desc, com_valor, com_status) FROM stdin;
    public       postgres    false    198   �G       2          0    17797    garcon 
   TABLE DATA               z   COPY public.garcon (gar_id, gar_nome, gar_cpf, gar_cep, gar_endereco, gar_cidade, gar_uf, gar_fone, gar_foto) FROM stdin;
    public       postgres    false    200   H       4          0    17805    item 
   TABLE DATA               >   COPY public.item (com_id, prod_id, it_quantidade) FROM stdin;
    public       postgres    false    202   �H       5          0    17808 	   pagamento 
   TABLE DATA               F   COPY public.pagamento (pag_id, com_id, pag_valor, tpg_id) FROM stdin;
    public       postgres    false    203   �H       7          0    17813    produto 
   TABLE DATA               ]   COPY public.produto (prod_id, cat_id, uni_id, prod_nome, prod_preco, prod_descr) FROM stdin;
    public       postgres    false    205   �H       9          0    17818    tipopgto 
   TABLE DATA               4   COPY public.tipopgto (tpg_id, tpg_nome) FROM stdin;
    public       postgres    false    207   �I       ;          0    17823    unidade 
   TABLE DATA               3   COPY public.unidade (uni_id, uni_nome) FROM stdin;
    public       postgres    false    209   �I       L           0    0    categoria_cat_id_seq    SEQUENCE SET     B   SELECT pg_catalog.setval('public.categoria_cat_id_seq', 3, true);
            public       postgres    false    197            M           0    0    comanda_com_id_seq    SEQUENCE SET     @   SELECT pg_catalog.setval('public.comanda_com_id_seq', 1, true);
            public       postgres    false    199            N           0    0    garcon_gar_id_seq    SEQUENCE SET     ?   SELECT pg_catalog.setval('public.garcon_gar_id_seq', 1, true);
            public       postgres    false    201            O           0    0    pagamento_pag_id_seq    SEQUENCE SET     C   SELECT pg_catalog.setval('public.pagamento_pag_id_seq', 1, false);
            public       postgres    false    204            P           0    0    produto_prod_id_seq    SEQUENCE SET     A   SELECT pg_catalog.setval('public.produto_prod_id_seq', 7, true);
            public       postgres    false    206            Q           0    0    tipopgto_tpg_id_seq    SEQUENCE SET     A   SELECT pg_catalog.setval('public.tipopgto_tpg_id_seq', 1, true);
            public       postgres    false    208            R           0    0    unidade_uni_id_seq    SEQUENCE SET     @   SELECT pg_catalog.setval('public.unidade_uni_id_seq', 6, true);
            public       postgres    false    210            �
           2606    17885    item item_pkey 
   CONSTRAINT     Y   ALTER TABLE ONLY public.item
    ADD CONSTRAINT item_pkey PRIMARY KEY (com_id, prod_id);
 8   ALTER TABLE ONLY public.item DROP CONSTRAINT item_pkey;
       public         postgres    false    202    202            �
           2606    17836    categoria pk_categoria 
   CONSTRAINT     X   ALTER TABLE ONLY public.categoria
    ADD CONSTRAINT pk_categoria PRIMARY KEY (cat_id);
 @   ALTER TABLE ONLY public.categoria DROP CONSTRAINT pk_categoria;
       public         postgres    false    196            �
           2606    17838    comanda pk_comanda 
   CONSTRAINT     T   ALTER TABLE ONLY public.comanda
    ADD CONSTRAINT pk_comanda PRIMARY KEY (com_id);
 <   ALTER TABLE ONLY public.comanda DROP CONSTRAINT pk_comanda;
       public         postgres    false    198            �
           2606    17840    garcon pk_garcon 
   CONSTRAINT     R   ALTER TABLE ONLY public.garcon
    ADD CONSTRAINT pk_garcon PRIMARY KEY (gar_id);
 :   ALTER TABLE ONLY public.garcon DROP CONSTRAINT pk_garcon;
       public         postgres    false    200            �
           2606    17842    pagamento pk_pagamento 
   CONSTRAINT     X   ALTER TABLE ONLY public.pagamento
    ADD CONSTRAINT pk_pagamento PRIMARY KEY (pag_id);
 @   ALTER TABLE ONLY public.pagamento DROP CONSTRAINT pk_pagamento;
       public         postgres    false    203            �
           2606    17844    produto pk_produto 
   CONSTRAINT     U   ALTER TABLE ONLY public.produto
    ADD CONSTRAINT pk_produto PRIMARY KEY (prod_id);
 <   ALTER TABLE ONLY public.produto DROP CONSTRAINT pk_produto;
       public         postgres    false    205            �
           2606    17846    tipopgto pk_tipopgto 
   CONSTRAINT     V   ALTER TABLE ONLY public.tipopgto
    ADD CONSTRAINT pk_tipopgto PRIMARY KEY (tpg_id);
 >   ALTER TABLE ONLY public.tipopgto DROP CONSTRAINT pk_tipopgto;
       public         postgres    false    207            �
           2606    17848    unidade pk_unidade 
   CONSTRAINT     T   ALTER TABLE ONLY public.unidade
    ADD CONSTRAINT pk_unidade PRIMARY KEY (uni_id);
 <   ALTER TABLE ONLY public.unidade DROP CONSTRAINT pk_unidade;
       public         postgres    false    209            �
           2606    17849    comanda fk_comanda    FK CONSTRAINT     u   ALTER TABLE ONLY public.comanda
    ADD CONSTRAINT fk_comanda FOREIGN KEY (gar_id) REFERENCES public.garcon(gar_id);
 <   ALTER TABLE ONLY public.comanda DROP CONSTRAINT fk_comanda;
       public       postgres    false    2723    200    198            �
           2606    17854    item fk_itemcomd    FK CONSTRAINT     t   ALTER TABLE ONLY public.item
    ADD CONSTRAINT fk_itemcomd FOREIGN KEY (com_id) REFERENCES public.comanda(com_id);
 :   ALTER TABLE ONLY public.item DROP CONSTRAINT fk_itemcomd;
       public       postgres    false    198    2721    202            �
           2606    17859    item fk_itemprod    FK CONSTRAINT     v   ALTER TABLE ONLY public.item
    ADD CONSTRAINT fk_itemprod FOREIGN KEY (prod_id) REFERENCES public.produto(prod_id);
 :   ALTER TABLE ONLY public.item DROP CONSTRAINT fk_itemprod;
       public       postgres    false    2729    202    205            �
           2606    17864    pagamento fk_pgtogarcon    FK CONSTRAINT     z   ALTER TABLE ONLY public.pagamento
    ADD CONSTRAINT fk_pgtogarcon FOREIGN KEY (com_id) REFERENCES public.garcon(gar_id);
 A   ALTER TABLE ONLY public.pagamento DROP CONSTRAINT fk_pgtogarcon;
       public       postgres    false    203    2723    200            �
           2606    17869    pagamento fk_pgtotipo    FK CONSTRAINT     z   ALTER TABLE ONLY public.pagamento
    ADD CONSTRAINT fk_pgtotipo FOREIGN KEY (tpg_id) REFERENCES public.tipopgto(tpg_id);
 ?   ALTER TABLE ONLY public.pagamento DROP CONSTRAINT fk_pgtotipo;
       public       postgres    false    2731    207    203            �
           2606    17874    produto fk_prodcat    FK CONSTRAINT     x   ALTER TABLE ONLY public.produto
    ADD CONSTRAINT fk_prodcat FOREIGN KEY (cat_id) REFERENCES public.categoria(cat_id);
 <   ALTER TABLE ONLY public.produto DROP CONSTRAINT fk_prodcat;
       public       postgres    false    205    2719    196            �
           2606    17879    produto fk_produni    FK CONSTRAINT     v   ALTER TABLE ONLY public.produto
    ADD CONSTRAINT fk_produni FOREIGN KEY (uni_id) REFERENCES public.unidade(uni_id);
 <   ALTER TABLE ONLY public.produto DROP CONSTRAINT fk_produni;
       public       postgres    false    209    2733    205            .   *   x�3�tJM�LI�2��O*J�M-N�2�(J,������ ��	V      0   E   x�3�4�442�L��L�WH��|N#CK]CC R00�#����D���Ĝ�|#NC=��W� ���      2   c   x�3�t;�0)3����L���P���H�Ј�����D�Ҍ3�4Q!�� 3/17���b��C����8]2K/Lj��0�д���0�53���q��qqq Ԡ�      4      x�3�4�4�2�4�4�Ɯ�\1z\\\ !��      5      x������ � �      7   �   x�U�An�0E��S�*$aۮz�����IL�.�E��ՠJQ�7#�?O��2��i��sA�v2b��)�����Rkۖ>��1�Ws�Ѫ�t�EA�?�iv4/�r/����6y�%f�1�uW���^�⩬6˼�|�q�/�����������ޑr��*�9y����|�_��1:��O::5����RL      9      x�3�t���H�,������ '�      ;   I   x�3�L�/��2�LO,*JLKT030���2��s����R�L9sS3
�/?�8�˄�4���,����� �$     