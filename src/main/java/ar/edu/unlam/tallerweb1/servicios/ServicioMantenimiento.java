package ar.edu.unlam.tallerweb1.servicios;

import ar.edu.unlam.tallerweb1.controladores.DatosEnvioAMantenimiento;
import ar.edu.unlam.tallerweb1.modelo.Auto;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


public interface ServicioMantenimiento {

    void enviar(Auto queNecesitaMantenimiento, String fecha_que_se_envia) throws Exception;;
}
