CREATE TABLE IF NOT EXISTS consumidor_pedido (
    id bigint not null auto_increment,
    id_consumidor varchar(255) NOT NULL,
    id_produtor bigint NOT NULL,
    pedido varchar(255) NOT NULL,
    PRIMARY KEY (id)
    ) engine=InnoDB;

--ALTER TABLE consumidor_pedido
--	ADD CONSTRAINT fk_id_consumidor_pedido
--	FOREIGN KEY (id_consumidor)
--	REFERENCES consumidor(cpf);
--
--ALTER TABLE consumidor_pedido
--	ADD CONSTRAINT fk_id_produtor_pedido
--	FOREIGN KEY (id_produtor)
--	REFERENCES produtor(id);

--ALTER TABLE consumidor_pedido
--	ADD CONSTRAINT fk_pedido
--	FOREIGN KEY (pedido)
--	REFERENCES produto(nome);