package ar.edu.unlam.tallerweb1.servicios;



public interface ServicioMail {



    void enviarMailRegistro(String email,String hash);

    void verificarHash(String mail, String hash);
}

