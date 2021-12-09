package ar.edu.unlam.tallerweb1.servicios;

import ar.edu.unlam.tallerweb1.Exceptions.*;
import ar.edu.unlam.tallerweb1.modelo.*;
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
        }
        throw new AutoNoExistente();
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
    public Revision enviarARevision(Auto enMantenimiento, Usuario mecanico, LocalDate fecha_de_envio) throws AutoNoExistente, UsuarioNoExistente, NoSeEnviaARevision {
        Auto aEnviar = repositorioAuto.buscarPor(enMantenimiento.getId());
        Usuario deLaRevision = repositorioUsuario.buscarPorId(mecanico.getId());
        if (aEnviar == null) {
            throw new AutoNoExistente();
        }
        if (deLaRevision == null) {
            throw new UsuarioNoExistente();
        }
        if (!aEnviar.getSituacion().equals(Situacion.EN_MANTENIMIENTO)){
            throw new NoSeEnviaARevision();
        }
        Revision nueva = repositorioAuto.enviarARevision(aEnviar, deLaRevision, fecha_de_envio);
        return nueva;
    }

    @Override
    public List<Auto> obtenerAutosEnRevision() throws NoHayAutosParaRevision {
        List<Auto> paraRevision = repositorioAuto.obtenerAutosEnRevision();
        if (paraRevision.size() == 0) {
            throw new NoHayAutosParaRevision();
        }
        return paraRevision;
    }

    @Override
    public Revision finalizarRevision(Auto queVienePorRequestParam, LocalDate fecha_fin_revision, String comentario) throws AutoNoExistente, RevisionNoExistente {
        Auto deLaRevision = repositorioAuto.buscarPor(queVienePorRequestParam.getId());
        Revision revision = repositorioAuto.obtenerRevisionPorAuto(deLaRevision);
        if (deLaRevision == null) {
            throw new AutoNoExistente();
        }
        if (revision == null) {
            throw new RevisionNoExistente();
        }
        deLaRevision.setSituacion(Situacion.DISPONIBLE);
        revision.setAuto(deLaRevision);
        revision.setFechaFinRevision(fecha_fin_revision);
        revision.setComentario(comentario);
        revision.setEstadoRevision(EstadoRevision.FINALIZADA);
        Revision actualizada = repositorioAuto.actualizarRevision(revision);
        return actualizada;
    }

}
