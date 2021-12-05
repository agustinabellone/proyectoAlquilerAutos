use alquiler;

-- USUARIO ADMINISTRADOR CUANDO MOSTREMOS ES EL UNICO  QUE DEBE ESTAR CARGADO.
INSERT INTO usuario (clave, email, rol, nombre, estado) VALUES ('admin', 'admin@tallerweb.com', 'admin', 'admin',1);
INSERT INTO usuario (clave, email, rol, nombre, estado) VALUES ('clave', 'mail@tallerweb.com', 'encargado', 'Encargado Av Mayo 2525', 1), ('clave', 'mail2@tallerweb.com', 'encargado', 'Encargado 9 de Julio 922', 1);

-- GARAGES
INSERT INTO garage (id, cantAutosActual, capacidad, direccion, encargado_id) VALUES (1, 36, 85, 'Av. de Mayo 2525', 2);
INSERT INTO garage (id, cantAutosActual, capacidad, direccion, encargado_id) VALUES (2, 26, 40, '9 de Julio 922', 3);

-- TIPO DE SUSCRIPCION
INSERT INTO tiposuscripcion (id, descripcion, eleccionVehiculo, limiteKilometraje, permiteReserva, valorIncumplimientoHoraLugar, valorPorKmAdicional, valorPorMalasCondiciones, duracion, precio)
VALUES (1, 'standard', 0, 50, 0, 900, 350,800, 31, 10000),
       (2, 'premium', 1, 80, 1, 500, 280, 800, 31, 15000),
       (3, 'golden', 1, 200, 1, 0, 100, 800, 31, 20000);

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
insert into auto (situacion, imagen, km, marca_id, modelo_id, patente,gama) values (0, "https://acroadtrip.blob.core.windows.net/catalogo-imagenes/m/RT_V_48e01d974fcc4d20a9178b645526b693.jpg",80000,02,1,"AAA111",0);
insert into auto (situacion, imagen, km, marca_id, modelo_id, patente,gama) values (0, "https://acroadtrip.blob.core.windows.net/catalogo-imagenes/m/RT_V_48e01d974fcc4d20a9178b645526b693.jpg",80000,02,1,"ABA121",0);
insert into auto (situacion, imagen, km, marca_id, modelo_id, patente,gama) values (0, "https://acroadtrip.blob.core.windows.net/catalogo-imagenes/m/RT_V_48e01d974fcc4d20a9178b645526b693.jpg",80000,02,1,"DZA531",0);
insert into auto (situacion, imagen, km, marca_id, modelo_id, patente,gama) values (0, "https://cdn.wheel-size.com/automobile/body/volkswagen-gol-2019-2021-1605540845.02767.jpg",80000,04,2,"BCA121",0);
insert into auto (situacion, imagen, km, marca_id, modelo_id, patente,gama) values (0, "https://cdn.wheel-size.com/automobile/body/volkswagen-gol-2019-2021-1605540845.02767.jpg",80000,04,2,"ABL771",0);


insert into auto (situacion, imagen, km, marca_id, modelo_id, patente,gama) values (0, "https://vossrentals.com.ar/30-large_default/fiat-cronos.jpg",80000,03,3,"QQA891",1);
insert into auto (situacion, imagen, km, marca_id, modelo_id, patente,gama) values (0, "https://vossrentals.com.ar/30-large_default/fiat-cronos.jpg",80000,03,3,"ATY411",1);
insert into auto (situacion, imagen, km, marca_id, modelo_id, patente,gama) values (0, "https://vossrentals.com.ar/30-large_default/fiat-cronos.jpg",80000,03,3,"SAA551",1);
insert into auto (situacion, imagen, km, marca_id, modelo_id, patente,gama) values (0, "https://www.chevrolet.com.ar/content/dam/chevrolet/mercosur/argentina/espanol/index/cars/2020-onix-premier/colorizer/01-images/julio-20/5-onix-premier-summit-white.png?imwidth=960",80000,01,4,"BCA121",1);
insert into auto (situacion, imagen, km, marca_id, modelo_id, patente,gama) values (0, "https://www.chevrolet.com.ar/content/dam/chevrolet/mercosur/argentina/espanol/index/cars/2020-onix-premier/colorizer/01-images/julio-20/5-onix-premier-summit-white.png?imwidth=960",80000,01,4,"ABL771",1);

insert into auto (situacion, imagen, km, marca_id, modelo_id, patente,gama) values (0, "https://acroadtrip.blob.core.windows.net/catalogo-imagenes/m/RT_V_306e84f8dec84ba4ad1f3372499ac72e.jpg",80000,02,5,"TTT191",2);
insert into auto (situacion, imagen, km, marca_id, modelo_id, patente,gama) values (0, "https://acroadtrip.blob.core.windows.net/catalogo-imagenes/m/RT_V_306e84f8dec84ba4ad1f3372499ac72e.jpg",80000,02,5,"ATZ119",2);
insert into auto (situacion, imagen, km, marca_id, modelo_id, patente,gama) values (0, "https://acroadtrip.blob.core.windows.net/catalogo-imagenes/m/RT_V_306e84f8dec84ba4ad1f3372499ac72e.jpg",80000,02,5,"SGA991",2);
insert into auto (situacion, imagen, km, marca_id, modelo_id, patente,gama) values (0, "https://www.chevrolet.com.ar/content/dam/chevrolet/mercosur/argentina/espanol/index/cars/2019-cruze-premier/colorizer/enero-21/colorizer-branco-summit.jpg?imwidth=960",80000,01,6,"BGC171",2);
insert into auto (situacion, imagen, km, marca_id, modelo_id, patente,gama) values (0, "https://www.chevrolet.com.ar/content/dam/chevrolet/mercosur/argentina/espanol/index/cars/2019-cruze-premier/colorizer/enero-21/colorizer-branco-summit.jpg?imwidth=960",80000,01,6,"AJJ271",2);



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




