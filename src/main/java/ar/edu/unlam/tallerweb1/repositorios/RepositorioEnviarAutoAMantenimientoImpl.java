package ar.edu.unlam.tallerweb1.repositorios;


import ar.edu.unlam.tallerweb1.modelo.Auto;
import org.springframework.stereotype.Repository;

@Repository
public class RepositorioEnviarAutoAMantenimientoImpl implements RepositorioEnviarAutoAMantenimiento {

    @Override
    public Auto buscarPor(String patente) {
        return null;
    }

    @Override
    public void guardar(Auto enviado) {

    }
}
