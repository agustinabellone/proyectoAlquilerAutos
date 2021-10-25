package ar.edu.unlam.tallerweb1.modelo;

import javax.persistence.*;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.concurrent.TimeUnit;

@Entity
public class Suscripcion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @OneToOne
    private Cliente cliente;
    @ManyToOne
    private TipoSuscripcion tipoSuscripcion;
    private Date registroSuscripcion;
    private Date ultimaRenovacion;
    private Date vencimientoRenovacion;
    private Boolean renovado;
   // private Long diasRestantes;

    public Suscripcion(Long id, Cliente cliente, TipoSuscripcion tipoSuscripcion) throws ParseException {
        registrarRegistroSuscripcion();
        this.id = id;
        this.cliente = cliente;
        this.tipoSuscripcion = tipoSuscripcion;
        this.ultimaRenovacion=registroSuscripcion;
        setVencimientoRenovacion();
    }


    public Suscripcion(Cliente cliente, TipoSuscripcion tipoSuscripcion) throws ParseException {
        registrarRegistroSuscripcion();
        this.cliente=cliente;
        this.tipoSuscripcion=tipoSuscripcion;
        this.ultimaRenovacion=registroSuscripcion;

        setVencimientoRenovacion();
    }

    public Suscripcion() throws ParseException {
        registrarRegistroSuscripcion();
        this.ultimaRenovacion=registroSuscripcion;

        setVencimientoRenovacion();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public TipoSuscripcion getTipoSuscripcion() {
        return tipoSuscripcion;
    }

    public void setTipoSuscripcion(TipoSuscripcion tipoSuscripcion) {
        this.tipoSuscripcion = tipoSuscripcion;
    }

    public Date getRegistroSuscripcion() {
        return registroSuscripcion;
    }

    public void setRegistroSuscripcion(Date registroSuscripcion) {
        this.registroSuscripcion = registroSuscripcion;
    }



    /*public Date getDiasRestantes() {
        return diasRestantes;
    }

    public void setDiasRestantes(LocalDate diasRestantes) {

    }*/


    public Date getUltimaRenovacion() {
        return ultimaRenovacion;
    }

    public void setUltimaRenovacion(Date ultimaRenovacion) {
        this.ultimaRenovacion = ultimaRenovacion;
    }

    public Date getVencimientoRenovacion() {
        return vencimientoRenovacion;
    }

    public void setVencimientoRenovacion() {
        Calendar ultimoPago = new GregorianCalendar();
        Calendar proximoPago = new GregorianCalendar();

        if(this.getUltimaRenovacion()!=null) {
        ultimoPago.setTime(getUltimaRenovacion());
        proximoPago = ultimoPago;
        proximoPago.add(Calendar.MONTH, 1);

        Calendar calendar = Calendar.getInstance();
        Date date = proximoPago.getTime();
        this.vencimientoRenovacion=date;
        }
    }

    public Boolean getRenovado() {
        return renovado;
    }

    public void setRenovado(Boolean renovado) {
        this.renovado = renovado;
    }

    /*public void setDiasRestantes(Date diasRestantes) {
        Long diff = this.vencimientoRenovacion.getTime() - this.ultimaRenovacion.getTime();
        Long dias = horas / 24;
        this.diasRestantes = diasRestantes;
    }*/


    private void registrarRegistroSuscripcion() throws ParseException {
        Date date = new Date(System.currentTimeMillis());
        DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
        String date1= df.format(date);
        Date registroSuscripcion= df.parse(date1);
        this.registroSuscripcion=registroSuscripcion;
    }
}
