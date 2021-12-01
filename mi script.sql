use alquiler;

INSERT INTO usuario (clave, email, rol, nombre) VALUES ('clave', 'mail@mail', 'cliente', 'Sheni'), ('clave', 'mail@enca', 'encargado', 'Encargado Av Mayo 2525'), ('clave', 'mail@enca2', 'encargado', 'Encargado 9 de Julio 922');
INSERT INTO garage (id, cantAutosActual, capacidad, direccion, encargado_id) VALUES (1, 36, 85, 'Av. de Mayo 2525', 2);
INSERT INTO garage (id, cantAutosActual, capacidad, direccion, encargado_id) VALUES (2, 26, 40, '9 de Julio 922', 3);
INSERT INTO tiposuscripcion (id, descripcion, eleccionVehiculo, limiteKilometraje, permiteReserva, valorIncumplimientoHoraLugar, valorPorKmAdicional, duracion) VALUES (1, 'standard', 0, 50, 0, 900, 350, 31), (2, 'premium', 1, 80, 1, 500, 280, 31), (3, 'golden', 1, 200, 1, 0, 100, 31);
INSERT INTO suscripcion (Usuario_id, TipoSuscripcion_id) VALUES (1, 2); 
insert into marca(id,descripcion) values(01,'chevrolet');
insert into modelo(id,descripcion,marca_id) values(001,'onix',01);
insert into auto (id, situacion, imagen, km, marca_id, modelo_id, patente) values (1, 0, "https://http2.mlstatic.com/D_NQ_NP_897110-MLU43129754655_082020-O.jpg",55820,1,1,"AAA111");
insert into auto (id,situacion, imagen, km, marca_id, modelo_id, patente)
values (2, 0, "https://3.bp.blogspot.com/-2S9cuFUrN7I/XKfwZuAAATI/AAAAAAAAdEc/TbKdrRbdwXkTCNy3-deFvJdESVCkA5VngCLcBGAs/s1600/Ficha-Tecnica-Chevrolet-Onix-2019.jpg",85220,1,1,"BBB111");
insert into auto (id, situacion, imagen, km, marca_id, modelo_id, patente)
values (3, 0, "https://acroadtrip.blob.core.windows.net/catalogo-imagenes/xl/RT_V_bde57fdcf34b4e75be144e7400b4c97a.jpg",93680,1,1,"CCC111");
/*INSERT INTO alquiler (auto_id, usuario_id, estado, garageLlegadaEst_id, garagePartida_id, adicionalCambioLugarFecha, adicionalInfraccionesOtro, f_egreso, f_ingreso, garageLlegada_id) VALUES (1, 1, 0, 2, 1, '0.0', '0.0', '20211027', '20211031', 2);*/

-- script de eze no tocar pliss por ahora, luz si no te anda proba cambiando la 'U' por 'u'
INSERT INTO Usuario (clave, email, rol, nombre) VALUES ('admin', 'admin@admin', 'admin', 'admin');


select * from alquiler;
select * from suscripcion;
select * from usuario;
select * from tiposuscripcion;
select * from auto;
select * from valoracionauto;
select * from usuario;


