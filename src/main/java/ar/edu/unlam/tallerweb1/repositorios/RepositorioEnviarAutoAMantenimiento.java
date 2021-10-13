package ar.edu.unlam.tallerweb1.repositorios;

import ar.edu.unlam.tallerweb1.modelo.Auto;

public interface RepositorioEnviarAutoAMantenimiento {

    Auto buscarPor(String patente);

    void guardar(Auto enviado);
}
