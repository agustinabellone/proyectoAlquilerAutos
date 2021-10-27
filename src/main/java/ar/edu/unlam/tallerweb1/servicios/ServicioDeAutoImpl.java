package ar.edu.unlam.tallerweb1.servicios;

import ar.edu.unlam.tallerweb1.modelo.Auto;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
public class ServicioDeAutoImpl implements ServicioDeAuto{


    @Override
    public Auto buscarAutoPorId(Long idDelAuto) {
        return null;
    }

    @Override
    public Boolean enviarAutoMantenimiento(Auto aEnviar) {
        return true;
    }
}
