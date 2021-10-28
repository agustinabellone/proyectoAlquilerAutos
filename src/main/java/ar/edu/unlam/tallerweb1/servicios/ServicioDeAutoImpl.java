package ar.edu.unlam.tallerweb1.servicios;

import ar.edu.unlam.tallerweb1.Exceptions.AutoNoExistente;
import ar.edu.unlam.tallerweb1.Exceptions.AutoYaExistente;
import ar.edu.unlam.tallerweb1.modelo.Auto;
import ar.edu.unlam.tallerweb1.modelo.Situacion;
import ar.edu.unlam.tallerweb1.repositorios.RepositorioAuto;
import ar.edu.unlam.tallerweb1.repositorios.RepositorioEnviarAutoAMantenimiento;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
public class ServicioDeAutoImpl implements ServicioDeAuto{

    private RepositorioAuto repositorioAuto;
    private RepositorioEnviarAutoAMantenimiento repositorioEnviarAutoAMantenimiento;
    @Autowired
    public ServicioDeAutoImpl(RepositorioAuto repositorioAuto) {
        this.repositorioAuto = repositorioAuto;
    }


    @Override
    public Auto buscarAutoPorId(Long idDelAuto) throws AutoNoExistente {
        Auto buscado = repositorioAuto.buscarPor(idDelAuto);
        if (buscado == null){
            throw new AutoNoExistente();
        }
        return buscado;
    }

    @Override
    public Auto enviarAutoMantenimiento(Auto aEnviar) throws AutoYaExistente{
        return null;
    }
}
