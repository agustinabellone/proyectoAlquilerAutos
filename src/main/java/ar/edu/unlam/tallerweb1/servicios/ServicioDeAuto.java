package ar.edu.unlam.tallerweb1.servicios;

import ar.edu.unlam.tallerweb1.Exceptions.*;
import ar.edu.unlam.tallerweb1.modelo.Auto;
import ar.edu.unlam.tallerweb1.modelo.Revision;
import ar.edu.unlam.tallerweb1.modelo.Usuario;

import java.time.LocalDate;
import java.util.List;

public interface ServicioDeAuto {
    Auto buscarAutoPorId(Long idDelAuto) throws AutoNoExistente;

    List<Auto> obtenerAutosEnMantenimiento() throws NoHayAutosEnMantenientoException;

    Auto enviarAMantenimiento(Long buscado) throws NoEnviaAutoAMantenimiento, AutoNoExistente;


    Revision enviarARevision(Auto enMantenimiento, Usuario mecanico, LocalDate fecha_de_envio) throws AutoNoExistente, UsuarioNoExistente, NoSeEnviaARevision;

    List<Auto> obtenerAutosEnRevision() throws NoHayAutosParaRevision;

    Revision finalizarRevision(Auto queVienePorRequestParam, LocalDate now, String comentario) throws AutoNoExistente, RevisionNoExistente;

    List<Revision> obtenerRevisionesFinalizadas() throws NoHayAutosParaRevision;

    List<Revision> obtenerRevisionesPorMecanico(Usuario mecanico) throws NoHayAutosParaRevision;

    List<Auto> obtenerAutosAlquilados();
}
