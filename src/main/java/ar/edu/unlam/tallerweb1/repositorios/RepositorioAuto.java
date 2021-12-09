package ar.edu.unlam.tallerweb1.repositorios;

import ar.edu.unlam.tallerweb1.modelo.*;

import java.time.LocalDate;
import java.util.List;

public interface RepositorioAuto {

    void guardar(Long id);

    Auto buscarPor(Long id);

    List<Auto> buscarAutosEnMantenimiento(Situacion enMantenimiento);

    Auto enviarAMantenimiento(Long id, Situacion enMantenimiento, LocalDate localDate);

    Revision enviarARevision(Auto aEnviar, Usuario deLaRevision, LocalDate fecha_de_envio);

    List<Auto> obtenerAutosEnRevision();

    Revision obtenerRevisionPorAuto(Auto deLaRevision);

    Revision actualizarRevision(Revision revision);

    List<Revision> obtenerRevisionesPorMecanico(Usuario id_mecanico);

    Revision obtenerRevisionEstadoYAuto(Auto deLaRevision, EstadoRevision activa);
}
