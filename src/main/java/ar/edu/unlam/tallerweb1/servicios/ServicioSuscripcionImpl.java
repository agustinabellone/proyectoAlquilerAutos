package ar.edu.unlam.tallerweb1.servicios;

import ar.edu.unlam.tallerweb1.Exceptions.ClienteYaSuscriptoException;
import ar.edu.unlam.tallerweb1.Exceptions.NivelDeSuscripcionActualMejorOIgualQueElNuevoException;
import ar.edu.unlam.tallerweb1.Exceptions.SuscripcionYaRenovadaException;
import ar.edu.unlam.tallerweb1.modelo.Usuario;
import ar.edu.unlam.tallerweb1.modelo.Suscripcion;
import ar.edu.unlam.tallerweb1.modelo.TipoSuscripcion;
import ar.edu.unlam.tallerweb1.repositorios.RepositorioSuscripcion;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

@Service("servicioSuscripcion")
@Transactional
public class ServicioSuscripcionImpl implements ServicioSuscripcion{


    @Override
    public Suscripcion suscribir(Usuario usuario, TipoSuscripcion tipoSuscripcion) {
        return null;
    }

    @Override
    public Boolean existeSuscripcionPorUsuario(Usuario usuario) {
        return null;
    }

    @Override
    public void renovarAutomaticamenteSuscripcion(Long id) {

    }

    @Override
    public void mejorarNivelSuscripcion(Suscripcion suscripcion, TipoSuscripcion nuevo_tipo) {

    }

    @Override
    public Suscripcion buscarPorIdUsuario(Long id) {
        return null;
    }

    @Override
    public void revisionDeSuscripciones() {

    }
}


