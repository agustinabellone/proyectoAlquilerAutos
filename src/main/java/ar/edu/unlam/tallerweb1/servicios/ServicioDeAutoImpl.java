package ar.edu.unlam.tallerweb1.servicios;

import ar.edu.unlam.tallerweb1.Exceptions.AutoNoExistente;
import ar.edu.unlam.tallerweb1.Exceptions.NoEnviaAutoAMantenimiento;
import ar.edu.unlam.tallerweb1.Exceptions.NoHayAutosEnMantenientoException;
import ar.edu.unlam.tallerweb1.modelo.Auto;
import ar.edu.unlam.tallerweb1.modelo.Revision;
import ar.edu.unlam.tallerweb1.modelo.Situacion;
import ar.edu.unlam.tallerweb1.modelo.Usuario;
import ar.edu.unlam.tallerweb1.repositorios.RepositorioAuto;
import ar.edu.unlam.tallerweb1.repositorios.RepositorioUsuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.List;

@Service
@Transactional
public class ServicioDeAutoImpl implements ServicioDeAuto {

    private RepositorioAuto repositorioAuto;
    private RepositorioUsuario repositorioUsuario;
    private LocalDate localDate = LocalDate.now();

    @Autowired
    public ServicioDeAutoImpl(RepositorioAuto repositorioAuto, RepositorioUsuario repositorioUsuario) {
        this.repositorioAuto = repositorioAuto;
        this.repositorioUsuario = repositorioUsuario;
    }

    public ServicioDeAutoImpl() {
    }

    @Override
    public Auto buscarAutoPorId(Long idDelAuto) throws AutoNoExistente {
        Auto buscado = repositorioAuto.buscarPor(idDelAuto);
        if (buscado != null) {
            return buscado;
        } else {
            throw new AutoNoExistente();
        }
    }

    @Override
    public List<Auto> obtenerAutosEnMantenimiento() throws NoHayAutosEnMantenientoException {
        List<Auto> autosEnMantenimiento = repositorioAuto.buscarAutosEnMantenimiento(Situacion.EN_MANTENIMIENTO);
        if (autosEnMantenimiento.isEmpty()) {
            throw new NoHayAutosEnMantenientoException();
        }
        return autosEnMantenimiento;
    }

    @Override
    public Auto enviarAMantenimiento(Long buscado) throws NoEnviaAutoAMantenimiento, AutoNoExistente {
        Auto obtenido = repositorioAuto.buscarPor(buscado);
        if (obtenido != null && obtenido.getSituacion().equals(Situacion.DISPONIBLE)) {
            Auto actualizado = repositorioAuto.enviarAMantenimiento(obtenido.getId(), Situacion.EN_MANTENIMIENTO, localDate);
            return actualizado;
        }
        throw new NoEnviaAutoAMantenimiento();
    }

    @Override
    public Auto enviarARevision(Auto enMantenimiento, Usuario mecanico, LocalDate fecha_de_envio) {
        return null;
    }

    @Override
    public List<Auto> obtenerAutosEnRevision() {
        return null;
    }

    @Override
    public Revision finalizarRevision(Auto queVienePorRequestParam, LocalDate now, String comentario) {
        return null;
    }

    @Override
    public String estaVacioElComentario(String comentario) {
        return null;
    }


}
