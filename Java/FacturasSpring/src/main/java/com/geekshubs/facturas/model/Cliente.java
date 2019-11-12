package com.geekshubs.facturas.model;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component("Cliente")
public class Cliente {
    private String nombre;
    private String apellido;

    @Autowired
    public Cliente(@Qualifier("nombreCliente") String nombre, @Qualifier("nombreCliente") String apellido) {
        this.nombre = nombre;
        this.apellido = apellido;
    }

    @Bean(name="nombreCliente")
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    @Bean(name="apellidoCliente")
    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }
}
