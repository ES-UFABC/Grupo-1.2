ALTER TABLE endereco
ADD estado varchar(50);

ALTER TABLE produtor
ADD UNIQUE (email);

ALTER TABLE produtor
ADD UNIQUE (cpf_ou_cnpj);