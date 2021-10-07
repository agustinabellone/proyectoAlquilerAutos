package ar.edu.unlam.tallerweb1.repositorios;

import ar.edu.unlam.tallerweb1.modelo.Suscripcion;

import java.util.List;

public interface RepositorioSuscripcion {
    Suscripcion guardar(Suscripcion suscripcion);

    Suscripcion buscarPorId(Long id);

    List<Suscripcion> buscarPorTipo(Long id_tipo);

    Suscripcion buscarPorCliente(Long id);
}
