package com.geekshubs.ejemplo.springcdi.model.service;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

@Component("MiServicio")
public class MiServicio implements IServicio {
    public String operacion() {
        return "Ejecutando operación desde MiServicio...";
    }
}