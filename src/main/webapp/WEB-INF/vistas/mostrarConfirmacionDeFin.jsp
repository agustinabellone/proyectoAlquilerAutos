<!doctype html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Document</title>
</head>
<body>
<h1>${cliente.nombre} CONFIRMA LOS DATOS PARA DAR FIN AL ALQUILER EN CURSO</h1>

        <h5>Inició: ${alquiler.f_egreso.toString()}</h5>
        <h5>Desde: ${garagePartida.direccion}</h5>

        <h5>Entrega el vehiculo en: ${garageLlegadaEst.direccion}</h5>
        <p>En caso de no ser asi debe <a href='modificar-garage-llegada?alquilerID=${alquiler.id}'>MODIFICAR GARAGE DE LLEGADA</a></p>
        <b>Recordar que se cobrara una tarifa por la modificacion</b>

        <a href='confirmacion-fin-alquiler?alquilerID=${alquiler.id}'>CONFIRMAR FINALIZACION DE ALQUILER</a>
</body>
</html>