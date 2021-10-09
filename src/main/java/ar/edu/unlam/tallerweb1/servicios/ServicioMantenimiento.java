package ar.edu.unlam.tallerweb1.servicios;

import ar.edu.unlam.tallerweb1.modelo.Auto;


public interface ServicioMantenimiento {

    Auto enviar(Auto queNecesitaMantenimiento, String fecha_que_se_envia) throws Exception;;

    Auto obtenerPor(String patente);
}
