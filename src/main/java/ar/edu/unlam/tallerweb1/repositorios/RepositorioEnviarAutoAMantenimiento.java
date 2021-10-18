package ar.edu.unlam.tallerweb1.repositorios;

import ar.edu.unlam.tallerweb1.modelo.Auto;

import java.util.List;

public interface RepositorioEnviarAutoAMantenimiento {

    Auto buscarPorPatente(String patente);

    void guardar(Auto enviado);

    List<Auto> buscarPorModelo(String modelo);

    List<Auto> buscarPorMarca(String marca);

    Auto buscarPorId(Long idDelAuto);

    List<Auto> buscarPorAnioDeFabricacion(int anioDeFabricacion);
}
