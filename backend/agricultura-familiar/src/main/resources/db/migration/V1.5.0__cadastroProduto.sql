    create table produto (
       id bigint not null auto_increment,
        condicoes_especiais_de_entrega bit,
        estimava_de_producao_sazonalidade bigint,
        foto varchar(255),
        nome varchar(255),
        preco double precision not null,
        quantidade double precision not null,
        tipologia integer not null,
        unidade_de_medida varchar(255),
        produtor_id bigint,
        primary key (id)
    ) engine=InnoDB;

        alter table produto
           add constraint UK_hdot1xprktyi4sf2onvllkmkd unique (nome);

        alter table produto
           add constraint FKftdfs29fqsi5wis7tg2ruecmg
           foreign key (produtor_id)
           references produtor (id);