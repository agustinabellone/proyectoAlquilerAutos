package ar.edu.unlam.tallerweb1.servicios;

import ar.edu.unlam.tallerweb1.Exceptions.AutoNoExistente;
import ar.edu.unlam.tallerweb1.Exceptions.NoEnviaAutoAMantenimiento;
import ar.edu.unlam.tallerweb1.Exceptions.NoHayAutosEnMantenientoException;
import ar.edu.unlam.tallerweb1.Exceptions.NoHayAutosParaRevision;
import ar.edu.unlam.tallerweb1.modelo.Auto;
import ar.edu.unlam.tallerweb1.modelo.Situacion;
import ar.edu.unlam.tallerweb1.repositorios.RepositorioAuto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.List;

@Service
@Transactional
public class ServicioDeAutoImpl implements ServicioDeAuto {

    private RepositorioAuto repositorioAuto;
    private LocalDate localDate = LocalDate.now();

    @Autowired
    public ServicioDeAutoImpl(RepositorioAuto repositorioAuto) {
        this.repositorioAuto = repositorioAuto;
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
            Auto actualizado = repositorioAuto.enviarAMantenimiento(obtenido.getId(),Situacion.EN_MANTENIMIENTO);
            return actualizado;
        }
        throw new NoEnviaAutoAMantenimiento();
    }

    @Override
    public Auto buscarAutoPorPatente(String patente) {
        return null;
    }

    @Override
    public void enviarARevision(String patente, Long id_mecanico) {

    }

    @Override
    public List<Auto> obtenerAutosEnRevision() throws NoHayAutosParaRevision {
        throw new NoHayAutosParaRevision();
    }
}
