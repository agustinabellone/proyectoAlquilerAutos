package ar.edu.unlam.tallerweb1.servicios;

import org.junit.Before;

public class TestServicioMecanico {

    private ServicioUsuarioImpl servicioUsuario;
    private ServicioDeAutoImpl servicioAuto;

    @Before
    public void init(){
        servicioAuto = new ServicioDeAutoImpl();
        servicioUsuario = new ServicioUsuarioImpl();
    }
}
