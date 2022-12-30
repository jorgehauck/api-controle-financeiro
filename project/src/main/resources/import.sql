INSERT INTO receitas(descricao, valor, data) VALUES ('Salario', 4570.00, '2022-12-07');
INSERT INTO receitas(descricao, valor, data) VALUES ('Dividendos', 1570.00, '2022-10-15');

INSERT INTO despesas(descricao, valor, data, categoria, receitas_id) VALUES('Restaurante', 250.0, '2022-12-07', 'ALIMENTACAO', 1);
INSERT INTO despesas(descricao, valor, data, categoria, receitas_id) VALUES('Cinema', 57.90, '2022-10-17', 'LAZER', 2);