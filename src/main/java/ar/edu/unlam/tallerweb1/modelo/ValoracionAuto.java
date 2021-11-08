package ar.edu.unlam.tallerweb1.modelo;

import javax.persistence.*;

@Entity
public class ValoracionAuto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private int valoracion;

    @Column
    private String comentarios;

    @OneToOne
    private Alquiler alquiler;

    @ManyToOne
    private Auto auto;


    public ValoracionAuto(int valoracion,String comentarios, Auto auto,Alquiler alquiler){
        this.valoracion=valoracion;
        this.comentarios=comentarios;
        this.auto=auto;
        this.alquiler=alquiler;
    }

    public ValoracionAuto() {

    }

    public Long getId() {

        return this.id;
    }

    public void setId(Long id) {

        this.id = id;
    }

    public Auto getAuto() {
        return auto;
    }

    public void setAuto(Auto auto) {
        this.auto = auto;
    }


    public int getValoracion() {
        return valoracion;
    }

    public void setValoracion(int valoracion) {
        this.valoracion = valoracion;
    }

    public String getComentarios() {
        return comentarios;
    }

    public void setComentarios(String comentarios) {
        this.comentarios = comentarios;
    }

    public Alquiler getAlquiler() {
        return alquiler;
    }

    public void setAlquiler(Alquiler alquiler) {
        this.alquiler = alquiler;
    }
}
