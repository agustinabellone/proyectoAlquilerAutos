use alquiler;

INSERT INTO usuario (clave, email, rol, nombre) VALUES ('clave', 'mail@mail', 'cliente', 'Sheni');
INSERT INTO auto (id, patente, km) VALUES (1, 'AA111BB', 1000);
INSERT INTO garage (id, cantAutosActual, capacidad, direccion) VALUES (1, 36, 85, 'Av. de Mayo 2525');
INSERT INTO garage (id, cantAutosActual, capacidad, direccion) VALUES (2, 26, 40, '9 de Julio 922');
INSERT INTO tiposuscripcion (id, descripcion) VALUES (1, 'standard'), (2, 'premium'), (3, 'golden');
INSERT INTO suscripcion (Usuario_id, tipoSuscripcion_id) VALUES (1, 2); 
INSERT INTO alquiler (auto_id, usuario_id, estado, garageLlegadaEst_id, garagePartida_id, adicionalCambioLugarFecha, adicionalInfraccionesOtro, f_egreso, f_ingreso, garageLlegada_id) VALUES (1, 1, 0, 2, 1, '0.0', '0.0', '20211027', '20211031', 2);



select * from alquiler;
select * from suscripcion;
select * from usuario;
select * from tiposuscripcion;
select * from auto;
select * from valoracionauto;


