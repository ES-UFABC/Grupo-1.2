ALTER TABLE endereco_consumidor
    DROP CONSTRAINT FK9ilxxq32r2xis065me3974882;

ALTER TABLE consumidor
    DROP COLUMN id_endereco;

ALTER TABLE endereco_consumidor
    ADD cpf VARCHAR(100);

ALTER TABLE endereco_consumidor
    ADD CONSTRAINT fk_consumidor_endereco
        FOREIGN KEY (cpf) 
    REFERENCES consumidor(cpf);
    