package ar.edu.unlam.tallerweb1.servicios;

import ar.edu.unlam.tallerweb1.Exceptions.ClienteNoExisteException;
import ar.edu.unlam.tallerweb1.controladores.DatosLogin;
import ar.edu.unlam.tallerweb1.repositorios.RepositorioUsuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import javax.transaction.Transactional;

@Service("ServicioLogin")
@Transactional
public class ServicioLoginImpl implements ServicioLogin {

    private RepositorioUsuario repositorioUsuario;

    @Autowired
    public ServicioLoginImpl(RepositorioUsuario repositorioUsuario) {
        this.repositorioUsuario = repositorioUsuario;
    }

    @Override
    public boolean ingresar(DatosLogin datosLogin) {
        if(repositorioUsuario.buscarPorEmail(datosLogin.getEmail()) == null){
            throw new ClienteNoExisteException();
        }
        return true;
    }
}
