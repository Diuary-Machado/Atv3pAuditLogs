

CREATE TABLE consultas (
		id BIGINT AUTO_INCREMENT PRIMARY KEY,
		data_hora TIMESTAMP NOT NULL,
		paciente_id BIGINT NOT NULL,
		CONSTRAINT fk_consulta_paciente
		FOREIGN KEY (paciente_id)
		REFERENCES pacientes (id)
		ON DELETE CASCADE

		);
