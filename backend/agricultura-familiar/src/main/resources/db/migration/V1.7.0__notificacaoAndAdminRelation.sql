CREATE TABLE IF NOT EXISTS administrador (
    id INT PRIMARY KEY AUTO_INCREMENT,
    nome VARCHAR(50) NOT NULL,    
    user_id INT NOT NULL,
    FOREIGN KEY (user_id) REFERENCES `user`(id)
) DEFAULT CHARSET=utf8
ENGINE InnoDB;

ALTER TABLE notificacao
    ADD administrador_id INT;

ALTER TABLE notificacao
	ADD CONSTRAINT fk_administrador_id
	FOREIGN KEY (administrador_id)
	REFERENCES administrador(id);