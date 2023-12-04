package com.example.entidades;

public class Usuario {

    private int idUsuario;
    private String nombre;
    private String apellidos;
    private String email;
    private String contraseña;
    private byte[] imagen;
    private String rol;
    private String dni;

    public Usuario() {
    }

    public Usuario(int idUsuario, String nombre, String apellidos, String email, String contraseña, byte[] imagen, String rol, String dni) {
        this.idUsuario = idUsuario;
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.email = email;
        this.contraseña = contraseña;
        this.imagen = imagen;
        this.rol = rol;
        this.dni = dni;
    }


    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getContraseña() {
        return contraseña;
    }

    public void setContraseña(String contraseña) {
        this.contraseña = contraseña;
    }

    public byte[] getImagen() {
        return imagen;
    }

    public void setImagen(byte[] imagen) {
        this.imagen = imagen;
    }

    public String getRol() {
        return rol;
    }

    public void setRol(String rol) {
        this.rol = rol;
    }
   public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }
}