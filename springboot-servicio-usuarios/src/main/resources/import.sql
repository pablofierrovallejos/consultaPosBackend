INSERT INTO usuarios (username, password, enabled, nombre, apellido, email) VALUES('Andres','12345', 1 , 'Andres', 'Guzman' , 'alo@alo2.cl');
INSERT INTO usuarios (username, password, enabled, nombre, apellido, email) VALUES('Jose','6789', 2 , 'Jorge', 'Perez' , 'jp@alo2.cl');
INSERT INTO roles (nombre) VALUES('ROLE_USER');
INSERT INTO roles (nombre) VALUES('ROLE_ADMIN');
INSERT INTO usuarios_roles (usuario_id, role_id) VALUES(1,1);
INSERT INTO usuarios_roles (usuario_id, role_id) VALUES(2,2);
INSERT INTO usuarios_roles (usuario_id, role_id) VALUES(2,1);