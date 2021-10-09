package ar.edu.unlam.tallerweb1.servicios;

import ar.edu.unlam.tallerweb1.Exceptions.AutoNoExistente;
import ar.edu.unlam.tallerweb1.Exceptions.AutoYaExistente;
import ar.edu.unlam.tallerweb1.modelo.Auto;
import ar.edu.unlam.tallerweb1.repositorio.RepositorioEnviarAutoAMantenimiento;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@Transactional
public class ServicioMantenimientoImpl implements ServicioMantenimiento {

    private RepositorioEnviarAutoAMantenimiento repositorioEnviarAutoAMantenimiento;

    @Autowired
    public ServicioMantenimientoImpl(RepositorioEnviarAutoAMantenimiento repositorioEnviarAutoAMantenimientoImpl) {
        this.repositorioEnviarAutoAMantenimiento = repositorioEnviarAutoAMantenimientoImpl;
    }

    @Override
    public Auto enviar(Auto queNecesitaMantenimiento, String fecha_que_se_envia) throws AutoYaExistente {
        if (existeElAuto(queNecesitaMantenimiento)) {
            throw new AutoYaExistente();
        }
        repositorioEnviarAutoAMantenimiento.guardar(queNecesitaMantenimiento);
        return queNecesitaMantenimiento;
    }

    private boolean existeElAuto(Auto queNecesitaMantenimiento) {
        return this.repositorioEnviarAutoAMantenimiento.buscarPor(queNecesitaMantenimiento.getPatente()) != null;
    }

    @Override
    public Auto obtenerPor(String patente) throws AutoNoExistente {
        if (this.repositorioEnviarAutoAMantenimiento.buscarPor(patente) == null){
            throw new AutoNoExistente();
        }
        return new Auto();
    }
}
