use alquiler;

INSERT INTO usuario (clave, email, rol, suscripcion_id) VALUES ('clave', 'mail@mail', 'cliente', null);
INSERT INTO auto (id, patente, km) VALUES (1, 'AA111BB', 1000);




INSERT INTO alquiler (id, auto_id, usuario_id, estado) VALUES (1, 1, 1, 0);

select * from suscripcion;
select * from usuario;


