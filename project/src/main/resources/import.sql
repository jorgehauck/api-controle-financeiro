INSERT INTO TB_RECEITAS(descricao, valor, data) VALUES('Salário', 3500.00, '2022-06-28');
INSERT INTO TB_RECEITAS(descricao, valor, data) VALUES('Dividendos', 850.00, '2022-04-05');

INSERT INTO TB_DESPESAS(descricao, valor, data, receitas_id, categoria) VALUES('Cinema', 45.00, '2022-06-30', 1, 'LAZER');
INSERT INTO TB_DESPESAS(descricao, valor, data, receitas_id, categoria) VALUES('Medicamentos', 55.00, '2022-06-30', 2, 'SAUDE');