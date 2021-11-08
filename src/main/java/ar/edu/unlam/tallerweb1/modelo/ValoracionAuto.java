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


    @ManyToOne
    private Auto auto;


    public ValoracionAuto(int valoracion,String comentarios, Auto auto){
        this.valoracion=valoracion;
        this.comentarios=comentarios;
        this.auto=auto;

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

}
