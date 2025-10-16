package co.edu.unicauca.degreeprojectmicroservicescommunication.entity;

import jakarta.persistence.*;


import java.io.Serializable;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public class Persona  implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombres;
    private String apellidos;
    private String celular;
    private String correo;

    @Enumerated(EnumType.STRING)
    private Rol rol;

    public Persona(){
        this.id= null;
    }

    public Persona(Long id, String nombres, String apellidos, String celular, String correo,
                   Programa programa, Rol rol) {
        this.id = id;
        this.nombres = nombres;
        this.apellidos = apellidos;
        this.celular = celular;
        this.correo = correo;
        this.rol = rol;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombres() {
        return nombres;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public String getCelular() {
        return celular;
    }

    public void setCelular(String celular) {
        this.celular = celular;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public Rol getRol() {
        return rol;
    }

    public void setRol(Rol rol) {
        this.rol = rol;
    }
}
