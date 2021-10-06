package ar.edu.unlam.tallerweb1.servicios;

import ar.edu.unlam.tallerweb1.controladores.DatosEnvioAMantenimiento;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public interface ServicioMantenimiento {

    void enviarAutoAMantenimiento(DatosEnvioAMantenimiento datosEnvioAMantenimiento) throws Exception;
}
