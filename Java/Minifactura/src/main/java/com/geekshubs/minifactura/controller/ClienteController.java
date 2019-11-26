package com.geekshubs.minifactura.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;
import com.geekshubs.minifactura.entity.Cliente;
import com.geekshubs.minifactura.entity.ClienteSimplificado;
import com.geekshubs.minifactura.repository.ClienteRepository;
import com.geekshubs.minifactura.service.ClienteErrorException;
import com.geekshubs.minifactura.service.ClienteException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.List;

@ControllerAdvice
@RestController
@RequestMapping("/${minifactura.version}")
public class ClienteController extends ResponseEntityExceptionHandler {

    @ExceptionHandler({ClienteErrorException.class,NumberFormatException.class})
    public final ResponseEntity<ClienteException>
    clienteError(Exception ex) throws JsonProcessingException {
        ClienteException exceptionResponse=null;
        if(ex.getClass()==NumberFormatException.class){
            exceptionResponse =
                    new ClienteException("id no numerico",
                            "Los detalles que queramos devolver");
        }else {
            exceptionResponse =
                    new ClienteException(ex.getMessage(),
                            "Los detalles que queramos devolver");
        }

        return new ResponseEntity<ClienteException>
                (exceptionResponse, new HttpHeaders(),
                        HttpStatus.NOT_FOUND);
    }


    @Autowired
    ClienteRepository clienteR;

    @GetMapping("/clientes")
    public List<Cliente> listar(){
        System.out.println("PETICION DE LISTADO DE CLIENTES");
        return clienteR.findAll();
    }

    @GetMapping("/clientesSolo")
    public List<ClienteSimplificado> listarSolo(){
        System.out.println("PETICION DE SOLO LISTADO DE CLIENTES");
        return clienteR.findClienteSimplificado();
    }

    @GetMapping("/clientesDetalle")
    public Cliente detalle() {
        throw new ClienteErrorException("id No se ha enviado");
    }

    @GetMapping("/clientesDetalle/{id}")
    public Cliente detalle(@PathVariable String id){
        System.out.println("PETICION DE DETALLE DE CLIENTE");
        if(Integer.parseInt(id)<0) throw new ClienteErrorException("id negativo");
        return clienteR.findById(Long.parseLong(id)).orElse(null);
    }

    @GetMapping("/clientes/error")
    public String errorService() {
        throw new RuntimeException("Un error ha sucedido");
    }

}
