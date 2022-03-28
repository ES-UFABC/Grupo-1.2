    create table IF NOT EXISTS endereco_consumidor (
        id_endereco bigint not null auto_increment,
        bairro varchar(255),
        cep varchar(255),
        municipio varchar(255),
        numero varchar(255),
        rua varchar(255),
        complemento integer not null,
        flag_endereco_principal varchar(1),
        primary key (id_endereco)
    ) engine=InnoDB;

    create table IF NOT EXISTS consumidor (
        cpf varchar(255),
        nome varchar(255),
        email varchar(255),
        telefone varchar(255),
        id_endereco bigint not null,
        primary key (cpf)
    ) engine=InnoDB;

    create index FK9ilxxq32r2xis065me3974882 on agricultura_familiar_db.consumidor(id_endereco);

    alter table endereco_consumidor
       add constraint FK9ilxxq32r2xis065me3974882
       foreign key (id_endereco)
       references consumidor (id_endereco);
