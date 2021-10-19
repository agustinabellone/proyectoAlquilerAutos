package ar.edu.unlam.tallerweb1.servicios;

public interface ServicioValoracion {

    Auto enviar(Auto queNecesitaMantenimiento, String fecha_que_se_envia) throws AutoYaExistente;

    Auto obtenerPor(String patente) throws AutoNoExistente;
}
