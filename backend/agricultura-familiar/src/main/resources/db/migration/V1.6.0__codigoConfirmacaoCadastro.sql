CREATE TABLE IF NOT EXISTS confirmacao_cadastro (
    id INT PRIMARY KEY AUTO_INCREMENT,
    codigo VARCHAR(20) NOT NULL,
    data_criacao TIMESTAMP NOT NULL,
    data_atualizacao TIMESTAMP NOT NULL,
    user_id INT NOT NULL    
) DEFAULT CHARSET=utf8
ENGINE InnoDB;

ALTER TABLE confirmacao_cadastro
	ADD CONSTRAINT fk_user_id
	FOREIGN KEY (user_id)
	REFERENCES `user`(id);
	
	