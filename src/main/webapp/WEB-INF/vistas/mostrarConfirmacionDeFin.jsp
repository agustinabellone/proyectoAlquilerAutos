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
        <h1><c:>${alquiler.f_ingreso}</c:></h1>
        <h1><c:>${alquiler.f_egreso}</c:></h1>
        <h1><c:>${auto.marca}</c:></h1>
        <h1><c:>${auto.modelo}</c:></h1>

        Usted esta entregando el auto en:
        <h1><c:>${alquiler.garageLlegadaEst}</c:></h1>
        <a href='/modificar-garage-llegada?alquilerID=${alquiler.id}'>En caso de no ser asi debe <br>
        </br>MODIFICAR GARAGE DE LLEGADA</a>
        <b>Recordar que se cobrara una tarifa por la modificacion</b>

        <a href="/confirmacion-fin-alquiler">CONFIRMAR FINALIZACION DE ALQUILER</a>
</body>
</html>