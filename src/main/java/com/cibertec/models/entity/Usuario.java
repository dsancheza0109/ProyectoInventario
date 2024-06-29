package com.cibertec.models.entity;

import jakarta.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import java.io.Serializable;

@Entity
@Table(name = "usuario")
public class Usuario implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_usuario")
    private int id;

    @Column(name = "nombre", length = 100, nullable = false)
    @NotEmpty
    private String nombre;

    @Column(name = "email", length = 100, unique = true, nullable = false)
    @NotEmpty
    @Email
    private String email;

    @Column(name = "password", length = 255, nullable = false)
    @NotEmpty
    private String password;

    @Column(name = "rol", length = 50, nullable = false)
    @NotEmpty
    private String rol;

    // Constructor por defecto
    public Usuario() {
    }

    // Constructor con parámetros sin id
    public Usuario(@NotEmpty String nombre, @NotEmpty @Email String email, @NotEmpty String password, @NotEmpty String rol) {
        this.nombre = nombre;
        this.email = email;
        this.password = password;
        this.rol = rol;
    }

    // Constructor con parámetros incluyendo id
    public Usuario(int id, @NotEmpty String nombre, @NotEmpty @Email String email, @NotEmpty String password, @NotEmpty String rol) {
        this.id = id;
        this.nombre = nombre;
        this.email = email;
        this.password = password;
        this.rol = rol;
    }

    // Getters y Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRol() {
        return rol;
    }

    public void setRol(String rol) {
        this.rol = rol;
    }

    @Override
    public String toString() {
        return "Usuario [id=" + id + ", nombre=" + nombre + ", email=" + email + ", password=" + password + ", rol=" + rol + "]";
    }
}
