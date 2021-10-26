use alquiler;

INSERT INTO usuario (id, clave, email, rol, suscripcion_id) VALUES (1, 'clave', 'mail@mail', 'cliente', null);
INSERT INTO auto (id, patente, kilometros) VALUES (1, 'AA111BB', 1000);
INSERT INTO alquiler (id, auto_id, usuario_id) VALUES (1, 1, 1);
