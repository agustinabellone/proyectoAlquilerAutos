package ar.edu.unlam.tallerweb1.servicios;

import ar.edu.unlam.tallerweb1.Exceptions.ClienteNoExisteException;
import ar.edu.unlam.tallerweb1.controladores.DatosLogin;
import ar.edu.unlam.tallerweb1.repositorio.TablaCliente;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service("ServicioLogin")
@Transactional
public class ServicioLoginImpl implements ServicioLogin {

    private TablaCliente tablaCliente = TablaCliente.getInstance();

    @Override
    public boolean ingresar(DatosLogin datosLogin) {
        if(!tablaCliente.existeClienteCon(datosLogin.getEmail())){
            throw new ClienteNoExisteException();
        }
        return true;
    }
}
