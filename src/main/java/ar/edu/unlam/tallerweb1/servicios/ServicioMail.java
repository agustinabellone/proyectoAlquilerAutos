package ar.edu.unlam.tallerweb1.servicios;


import java.time.LocalDate;

public interface ServicioMail {



    void enviarMailRegistro(String email,String hash);

    void enviarMailSuscripcion(String email, String nombrePlan);

    void enviarMailMejorarSuscripcion(String email, String nombrePlan);

    void enviarMailAlquiler(String email, String lugarRetiro, String lugarDevolucion, LocalDate salida, LocalDate ingreso);

    void enviarMailActivarCuenta(String email);

    void verificarHash(String mail, String hash);
}

