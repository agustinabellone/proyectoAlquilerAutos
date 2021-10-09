package ar.edu.unlam.tallerweb1.servicios;

import ar.edu.unlam.tallerweb1.modelo.Auto;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@Transactional
public class ServicioMantenimientoImpl implements ServicioMantenimiento {

    @Override
    public Auto enviar(Auto queNecesitaMantenimiento, String fecha_que_se_envia) throws Exception {
        return queNecesitaMantenimiento;
    }

    @Override
    public Auto obtenerPor(String patente) {
        return new Auto();
    }
}
