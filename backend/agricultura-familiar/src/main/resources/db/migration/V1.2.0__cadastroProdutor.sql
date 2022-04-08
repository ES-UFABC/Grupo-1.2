
    create table IF NOT EXISTS endereco (
       id bigint not null auto_increment,
        bairro varchar(255),
        cep varchar(255),
        municipio varchar(255),
        numero varchar(255),
        rua varchar(255),
        tipo_endereco integer not null,
        produtor_id bigint,
        primary key (id)
    ) engine=InnoDB;

    create table IF NOT EXISTS entidade_atendida (
       produtor_id bigint not null,
        entidades_atendidas varchar(255)
    ) engine=InnoDB;

    create table IF NOT EXISTS forma_pagamento (
       produtor_id bigint not null,
        formas_pagamento varchar(255)
    ) engine=InnoDB;

    create table IF NOT EXISTS paginas_externas (
       produtor_id bigint not null,
        paginas_externas varchar(255)
    ) engine=InnoDB;

    create table IF NOT EXISTS produtor (
       id bigint not null auto_increment,
        agroecologico bit,
        atende_no_endereco_de_producao bit,
        cadastro_entidade bit,
        certificacao_agroecologico bit,
        cpf_ou_cnpj varchar(255),
        geolocalizacao varchar(255),
        nome varchar(255),
        nome_fantasia varchar(255),
        organico varchar(255),
        regiao_de_producao varchar(255),
        registro_ou_certificacao bit,
        tipo_produtor integer not null,
        primary key (id)
    ) engine=InnoDB;

    create table IF NOT EXISTS produtor_tipo_producao (
       produtor_id bigint not null,
        tipo_producao_id integer not null,
        primary key (produtor_id, tipo_producao_id)
    ) engine=InnoDB;

    create table IF NOT EXISTS registros_certificados (
       produtor_id bigint not null,
        registros_ou_certificacoes varchar(255)
    ) engine=InnoDB;

    create table IF NOT EXISTS telefone (
       produtor_id bigint not null,
        telefones varchar(255)
    ) engine=InnoDB;

    create table IF NOT EXISTS tipo_producao (
       id integer not null auto_increment,
        nome varchar(255),
        primary key (id)
    ) engine=InnoDB;

    alter table endereco
       add constraint FK6ilxxq32r2xis065me3974881
       foreign key (produtor_id)
       references produtor (id);

    alter table entidade_atendida
       add constraint FK7y57nm9a79t2hl5avh83vn23k
       foreign key (produtor_id)
       references produtor (id);

    alter table forma_pagamento
       add constraint FKpjt16qrn6646npnxgpxc5nqnt
       foreign key (produtor_id)
       references produtor (id);

    alter table paginas_externas
       add constraint FKpcmwwqa6lnhxcwdrprpq1qe4
       foreign key (produtor_id)
       references produtor (id);

    alter table produtor_tipo_producao 
       add constraint FKnjyql1vcv8iy4kxo4bktocsmv 
       foreign key (tipo_producao_id) 
       references tipo_producao (id);

    alter table produtor_tipo_producao
       add constraint FKjm4bp8adwuu804emhb806ltuh
       foreign key (produtor_id)
       references produtor (id);

    alter table registros_certificados
       add constraint FK6v3ej6rbpq1hdluedcqfqb9ru
       foreign key (produtor_id)
       references produtor (id);

    alter table telefone
       add constraint FKtfb0jf3q2j7x842xj1uu7pqnn
       foreign key (produtor_id)
       references produtor (id);
