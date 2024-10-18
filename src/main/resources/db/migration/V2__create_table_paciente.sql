

CREATE TABLE pacientes(
		id BIGINT AUTO_INCREMENT PRIMARY KEY,
		nome VARCHAR(255) NOT NULL,
		cpf VARCHAR(11) NOT NULL UNIQUE,
		email VARCHAR(255),
		telefone VARCHAR(20),
		endereco_id BIGINT,
		CONSTRAINT fk_paciente_endereco
		FOREIGN KEY (endereco_id)
		REFERENCES endereco (id)  
		ON DELETE CASCADE

		);
