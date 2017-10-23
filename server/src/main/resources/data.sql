insert into parametro values (1, 'Test1', '1', '2017-04-27 00:00:00', '2017-04-27 00:00:00', 0);
insert into parametro values (2, 'Test2', '2', '2017-04-27 00:00:00', '2017-04-27 00:00:00', 0);
insert into parametro values (3, 'Test3', '3', '2017-04-27 00:00:00', '2017-04-27 00:00:00', 0);
insert into parametro values (4, 'Test4', '4', '2017-04-27 00:00:00', '2017-04-27 00:00:00', 0);
insert into parametro values (5, 'Test5', '5', '2017-04-27 00:00:00', '2017-04-27 00:00:00', 0);
insert into parametro values (6, 'Test6', '6', '2017-04-27 00:00:00', '2017-04-27 00:00:00', 0);

INSERT INTO pessoa (id_pessoa,id_tipo_pessoa,ds_email,ds_email_faturamento,ds_observacao,dh_cadastro,dh_ultima_alteracao,nr_versao) VALUES (1,'F','edusasse@email.com',null,'Administrador','2017-06-01 06:20:21',null,0);
INSERT INTO pessoa_fisica (id_pessoa,nm_primeiro_nome,nm_segundo_nome,cd_sexo,dt_nascimento,ds_documento,ds_tipo_documento) VALUES (1,'Admin','Root','M','2017-06-01 06:20:21','05949267931','CPF');
INSERT INTO usuario (id_usuario,ds_apelido,ds_senha,fl_ativo,id_pessoa,ds_perfil,dt_ultimo_acesso,dh_ultima_alteracao,dh_cadastro,nr_versao) VALUES (1,'admin','$2a$12$rYI9W.7CWaWTKPGWttzuburQabYs6LoV9YM6pBfjtQYn9zt0uyemW',true,1,'ADMIN',null,'2017-06-01 06:20:22','2017-06-01 06:20:21',1);

INSERT INTO pessoa (id_pessoa,id_tipo_pessoa,ds_email,ds_email_faturamento,ds_observacao,dh_cadastro,dh_ultima_alteracao,nr_versao) VALUES (2,'F','edusasse@email.com',null,'Usuario','2017-06-01 06:20:21',null,0);
INSERT INTO pessoa_fisica (id_pessoa,nm_primeiro_nome,nm_segundo_nome,cd_sexo,dt_nascimento,ds_documento,ds_tipo_documento) VALUES (2,'Usuario','User','M','2017-06-01 06:20:21','05949267931','CPF');
INSERT INTO usuario (id_usuario,ds_apelido,ds_senha,fl_ativo,id_pessoa,ds_perfil,dt_ultimo_acesso,dh_ultima_alteracao,dh_cadastro,nr_versao) VALUES (2,'user','$2a$12$JPvoEN9hnN2q6dhXbxh1BuO7WlEPufj57CpqKvY58dvjVYGSkDPwq',true,2,'USER',null,'2017-06-01 06:20:22','2017-06-01 06:20:21',1);