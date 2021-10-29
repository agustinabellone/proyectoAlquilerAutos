package ar.edu.unlam.tallerweb1.servicios;

import ar.edu.unlam.tallerweb1.modelo.Alquiler;
import ar.edu.unlam.tallerweb1.modelo.Auto;
import ar.edu.unlam.tallerweb1.modelo.Marca;
import ar.edu.unlam.tallerweb1.modelo.Modelo;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ServicioHomeImpl implements  ServicioHome{


    @Override
    public List<String> obtenerFechasEnString(List<Alquiler> alquileresUsuario) {
        List<String> fechasAlquileres = new ArrayList<String>();
        for (Alquiler alquiler: alquileresUsuario) {
            String fecha = alquiler.getF_egreso().toString();
            fechasAlquileres.add(fecha);
        }
        return fechasAlquileres;
    }

    @Override
    public List<Marca> obtenerMarcaAutoAlquiler(List<Alquiler> alquileresUsuario) {
        List<Marca> marcasAutoAlquileres = new ArrayList<>();
        for (Alquiler alquiler: alquileresUsuario) {
            Marca marcas = alquiler.getAuto().getMarca();
            marcasAutoAlquileres.add(marcas);
        }
        return marcasAutoAlquileres;
    }

    @Override
    public List<Modelo> obtenerModeloAutoAlquiler(List<Alquiler> alquileresUsuario) {
        List<Modelo> modeloAutoAlquileres = new ArrayList<>();
        for (Alquiler alquiler: alquileresUsuario) {
            Modelo modelo= alquiler.getAuto().getModelo();
            modeloAutoAlquileres.add(modelo);
        }
        return modeloAutoAlquileres;
    }



}
