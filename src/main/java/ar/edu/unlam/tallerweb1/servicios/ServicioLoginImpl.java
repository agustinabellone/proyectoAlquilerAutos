package ar.edu.unlam.tallerweb1.servicios;

import ar.edu.unlam.tallerweb1.Exceptions.ClienteNoExisteException;
import ar.edu.unlam.tallerweb1.controladores.DatosLogin;
import ar.edu.unlam.tallerweb1.repositorios.RepositorioCliente;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import javax.transaction.Transactional;

@Service("ServicioLogin")
@Transactional
public class ServicioLoginImpl implements ServicioLogin {

    private RepositorioCliente repositorioCliente;

    @Autowired
    public ServicioLoginImpl(RepositorioCliente repositorioCliente) {
        this.repositorioCliente = repositorioCliente;
    }

    @Override
    public boolean ingresar(DatosLogin datosLogin) {
        if(repositorioCliente.buscarPorEmail(datosLogin.getEmail()) == null){
            throw new ClienteNoExisteException();
        }
        return true;
    }
}
