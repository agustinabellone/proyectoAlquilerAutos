use alquiler;

-- USUARIO ADMINISTRADOR CUANDO MOSTREMOS ES EL UNICO  QUE DEBE ESTAR CARGADO.
INSERT INTO usuario (clave, email, rol, nombre, estado) VALUES ('admin', 'admin@tallerweb.com', 'admin', 'admin',1);
INSERT INTO usuario (clave, email, rol, nombre, estado) VALUES ('clave', 'enca1@tallerweb.com', 'encargado', 'Encargado Liniers', 1), ('clave', 'enca2@tallerweb.com', 'encargado', 'Encargado Morón', 1), ('clave', 'enca3@tallerweb.com', 'encargado', 'Encargado La Matanza', 1);

-- USUARIO CLIENTE
insert into usuario (email, clave, estado, rol, puntaje, nombre)
values ("cliente@gmail.com", "12345678", 1, "cliente", 0, "Juan");

-- GARAGES
INSERT INTO garage (id, cantAutosActual, capacidad, direccion, encargado_id) VALUES (1, 36, 85, 'Sede Liniers', 2);
INSERT INTO garage (id, cantAutosActual, capacidad, direccion, encargado_id) VALUES (2, 26, 40, 'Sede Morón', 3);
INSERT INTO garage (id, cantAutosActual, capacidad, direccion, encargado_id) VALUES (3, 12, 100, 'Sede La Matanza', 4);

-- TIPO DE SUSCRIPCION
INSERT INTO tiposuscripcion (id, nombre, descripcion, eleccionVehiculo, limiteKilometraje, permiteReserva, valorIncumplimientoHoraLugar, valorPorKmAdicional, valorPorMalasCondiciones, duracion, precio)
VALUES (1, 'básico', 'standard', 0, 50, 0, 900, 350,800, 31, 10000),
       (2,  'oro', 'premium', 1, 80, 1, 500, 280, 800, 31, 15000),
       (3, 'diamante', 'golden', 1, 200, 1, 0, 100, 800, 31, 20000);

-- MARCAS
insert into marca(id,descripcion) values(01,'chevrolet');
insert into marca(id,descripcion) values(02,'toyota');
insert into marca(id,descripcion) values(03,'fiat');
insert into marca(id,descripcion) values(04,'volkswagen');

-- MODELOS
insert into modelo(id,descripcion,marca_id) values(1,'etios',02);
insert into modelo(id,descripcion,marca_id) values(2,'gol',04);

insert into modelo(id,descripcion,marca_id) values(3,'cronos',03);
insert into modelo(id,descripcion,marca_id) values(4,'onix',01);

insert into modelo(id,descripcion,marca_id) values(5,'hilux',02);
insert into modelo(id,descripcion,marca_id) values(6,'cruze',01);

-- AUTOS
insert into auto (situacion, imagen, km, marca_id, modelo_id, patente,gama,limiteKm) values (0, "https://acroadtrip.blob.core.windows.net/catalogo-imagenes/m/RT_V_48e01d974fcc4d20a9178b645526b693.jpg",80000,02,1,"AAA111",0, 1000);
insert into auto (situacion, imagen, km, marca_id, modelo_id, patente,gama,limiteKm) values (0, "https://acroadtrip.blob.core.windows.net/catalogo-imagenes/m/RT_V_48e01d974fcc4d20a9178b645526b693.jpg",80000,02,1,"ABA121",0, 1000);
insert into auto (situacion, imagen, km, marca_id, modelo_id, patente,gama,limiteKm) values (0, "https://acroadtrip.blob.core.windows.net/catalogo-imagenes/m/RT_V_48e01d974fcc4d20a9178b645526b693.jpg",80000,02,1,"DZA531",0, 1000);
insert into auto (situacion, imagen, km, marca_id, modelo_id, patente,gama,limiteKm) values (0, "https://cdn.wheel-size.com/automobile/body/volkswagen-gol-2019-2021-1605540845.02767.jpg",80000,04,2,"BCA121",0, 1000);
insert into auto (situacion, imagen, km, marca_id, modelo_id, patente,gama,limiteKm) values (0, "https://cdn.wheel-size.com/automobile/body/volkswagen-gol-2019-2021-1605540845.02767.jpg",80000,04,2,"ABL771",0, 1000);
insert into auto (situacion, imagen, km, marca_id, modelo_id, patente,gama,limiteKm) values (0, "https://vossrentals.com.ar/30-large_default/fiat-cronos.jpg",80000,03,3,"QQA891",1, 1000);
insert into auto (situacion, imagen, km, marca_id, modelo_id, patente,gama,limiteKm) values (0, "https://vossrentals.com.ar/30-large_default/fiat-cronos.jpg",80000,03,3,"ATY411",1, 1000);
insert into auto (situacion, imagen, km, marca_id, modelo_id, patente,gama,limiteKm) values (0, "https://vossrentals.com.ar/30-large_default/fiat-cronos.jpg",80000,03,3,"SAA551",1, 1000);
insert into auto (situacion, imagen, km, marca_id, modelo_id, patente,gama,limiteKm) values (0, "https://www.chevrolet.com.ar/content/dam/chevrolet/mercosur/argentina/espanol/index/cars/2020-onix-premier/colorizer/01-images/julio-20/5-onix-premier-summit-white.png?imwidth=960",80000,01,4,"BCA121",1,1000);
insert into auto (situacion, imagen, km, marca_id, modelo_id, patente,gama,limiteKm) values (0, "https://www.chevrolet.com.ar/content/dam/chevrolet/mercosur/argentina/espanol/index/cars/2020-onix-premier/colorizer/01-images/julio-20/5-onix-premier-summit-white.png?imwidth=960",80000,01,4,"ABL771",1,1000);

insert into auto (situacion, imagen, km, marca_id, modelo_id, patente,gama,limiteKm) values (0, "https://acroadtrip.blob.core.windows.net/catalogo-imagenes/m/RT_V_306e84f8dec84ba4ad1f3372499ac72e.jpg",80000,02,5,"TTT191",2,1000);
insert into auto (situacion, imagen, km, marca_id, modelo_id, patente,gama,limiteKm) values (0, "https://acroadtrip.blob.core.windows.net/catalogo-imagenes/m/RT_V_306e84f8dec84ba4ad1f3372499ac72e.jpg",80000,02,5,"ATZ119",2,1000);
insert into auto (situacion, imagen, km, marca_id, modelo_id, patente,gama,limiteKm) values (0, "https://acroadtrip.blob.core.windows.net/catalogo-imagenes/m/RT_V_306e84f8dec84ba4ad1f3372499ac72e.jpg",80000,02,5,"SGA991",2,1000);
insert into auto (situacion, imagen, km, marca_id, modelo_id, patente,gama,limiteKm) values (0, "https://www.chevrolet.com.ar/content/dam/chevrolet/mercosur/argentina/espanol/index/cars/2019-cruze-premier/colorizer/enero-21/colorizer-branco-summit.jpg?imwidth=960",80000,01,6,"BGC171",2,1000);
insert into auto (situacion, imagen, km, marca_id, modelo_id, patente,gama,limiteKm) values (0, "https://www.chevrolet.com.ar/content/dam/chevrolet/mercosur/argentina/espanol/index/cars/2019-cruze-premier/colorizer/enero-21/colorizer-branco-summit.jpg?imwidth=960",80000,01,6,"AJJ271",2,1000);


-- NOTIFICACIONES
INSERT INTO `notificacion` (`id`, `Color`, `Descripcion`, `Usuario_id`) VALUES (NULL, 'danger', 'Su suscripcion sera cancelada dentro de 5 dias', '1'), (NULL, 'primary', 'Quedan 3 dias para que aproveche nuestras promociones!', '1'), (NULL, 'success', 'Su suscripcion sera renovada dentro de 10 dias', '1');

-- OTROS
/*INSERT INTO alquiler (id,auto_id, usuario_id, estado, garageLlegadaEst_id, garagePartida_id, adicionalCambioLugarFecha, adicionalInfraccionesOtro, f_egreso, f_ingreso, garageLlegada_id, encargado_id) VALUES (8, 1, 1, 0, 2, 1, '0.0', '0.0', '20211027', '20211031', 2, 3);*/
/*INSERT INTO suscripcion (Usuario_id, TipoSuscripcion_id) VALUES (1, 2); */
-- script de eze no tocar pliss por ahora, luz si no te anda proba cambiando la 'U' por 'u'
/*INSERT INTO Usuario (clave, email, rol, nombre) VALUES ('admin', 'admin@admin', 'admin', 'admin');*/
SET SQL_SAFE_UPDATES = 0;
select * from alquiler;
select * from suscripcion;
select * from usuario;
select * from tiposuscripcion;
select * from auto;
select * from valoracionauto;
select * from solicitud;
