package com.geekshubs.ejemplo.springcdi.model.service;

import org.springframework.stereotype.Component;

@Component("MiServicioNuevo")
public class MiServicioNuevo implements IServicio {
    public String operacion() {
        return "Ejecutando una operación desde MiServicioNuevo...";
    }
}