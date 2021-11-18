package ar.edu.unlam.tallerweb1.modelo;

import javax.persistence.*;

@Entity
public class Notificacion {

   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String Descripcion;

    @Column
    private String Color;

    @ManyToOne
    private Usuario Usuario;

    public void setId(Long id) {
        this.id = id;
    }
    public Long getId() {
        return id;
    }

    public String getDescripcion() {
        return Descripcion;
    }

    public void setDescripcion(String descripcion) {
        Descripcion = descripcion;
    }

    public String getColor() {
        return Color;
    }

    public void setColor(String color) {
        Color = color;
    }

    public ar.edu.unlam.tallerweb1.modelo.Usuario getUsuario() {
        return Usuario;
    }

    public void setUsuario(ar.edu.unlam.tallerweb1.modelo.Usuario usuario) {
        Usuario = usuario;
    }
}
