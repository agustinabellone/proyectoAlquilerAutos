package ar.edu.unlam.tallerweb1.repositorios;

import ar.edu.unlam.tallerweb1.modelo.TipoSuscripcion;

public interface RepositorioTipoSuscripcion {

    TipoSuscripcion buscarPorId(Long id);

    TipoSuscripcion guardar(TipoSuscripcion tipoSuscripcion);
}
