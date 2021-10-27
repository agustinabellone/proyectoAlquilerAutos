package ar.edu.unlam.tallerweb1.servicios;

import ar.edu.unlam.tallerweb1.Exceptions.AutoNoExistente;
import ar.edu.unlam.tallerweb1.Exceptions.AutoYaExistente;
import ar.edu.unlam.tallerweb1.Exceptions.NoHayAutosEnMantenientoException;
import ar.edu.unlam.tallerweb1.modelo.Auto;
import ar.edu.unlam.tallerweb1.modelo.Marca;
import ar.edu.unlam.tallerweb1.modelo.Modelo;
import ar.edu.unlam.tallerweb1.repositorios.RepositorioEnviarAutoAMantenimiento;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service
@Transactional
public class ServicioMantenimientoImpl implements ServicioMantenimiento {

    private RepositorioEnviarAutoAMantenimiento repositorioEnviarAutoAMantenimiento;
    Marca marca = new Marca(1, "Ford");
    Modelo modelo = new Modelo(1, "Fiesta", marca);

    @Autowired
    public ServicioMantenimientoImpl(RepositorioEnviarAutoAMantenimiento repositorioEnviarAutoAMantenimientoImpl) {
        this.repositorioEnviarAutoAMantenimiento = repositorioEnviarAutoAMantenimientoImpl;
    }

    @Override
    public Auto enviar(Auto queNecesitaMantenimiento, String fecha_que_se_envia) throws AutoYaExistente {
        if (existeElAuto(queNecesitaMantenimiento)) {
            throw new AutoYaExistente();
        }
        queNecesitaMantenimiento.setPatente("ABC123");
        queNecesitaMantenimiento.setMarca(marca);
        queNecesitaMantenimiento.setModelo(modelo);
        repositorioEnviarAutoAMantenimiento.guardarAutoMantenimiento(queNecesitaMantenimiento);
        return queNecesitaMantenimiento;
    }

    private boolean existeElAuto(Auto queNecesitaMantenimiento) {
        return this.repositorioEnviarAutoAMantenimiento.buscarPorPatente(queNecesitaMantenimiento.getPatente()) != null;
    }

    @Override
    public Auto obtenerPor(String patente) throws AutoNoExistente {
        if (this.repositorioEnviarAutoAMantenimiento.buscarPorPatente(patente) == null){
            throw new AutoNoExistente();
        }
        return new Auto();
    }

    @Override
    public List<Auto> obtenerAutosEnMantenimiento() throws NoHayAutosEnMantenientoException {
        List<Auto> autosObtenidos = repositorioEnviarAutoAMantenimiento.obtenerAutosEnMantenimiento();
        if (autosObtenidos.size() == 0){
            throw new NoHayAutosEnMantenientoException();
        }
        else{
            return autosObtenidos;
        }
    }
}
