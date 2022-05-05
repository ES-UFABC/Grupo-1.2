CREATE TABLE IF NOT EXISTS consumidor_pedido (
    id INT PRIMARY KEY AUTO_INCREMENT,
    id_consumidor varchar(255) NOT NULL,
    id_produtor varchar(255) NOT NULL,
    pedido varchar(255) NOT NULL,
    PRIMARY KEY (id)
    ) engine=InnoDB;

ALTER TABLE consumidor_pedido
	ADD CONSTRAINT fk_id_consumidor
	FOREIGN KEY (id_consumidor)
	REFERENCES consumidor(cpf);

ALTER TABLE consumidor_pedido
	ADD CONSTRAINT fk_id_produtor
	FOREIGN KEY (id_produtor)
	REFERENCES produtor(cpf_ou_cnpj);

--ALTER TABLE consumidor_pedido
--	ADD CONSTRAINT fk_pedido
--	FOREIGN KEY (pedido)
--	REFERENCES produto(nome);