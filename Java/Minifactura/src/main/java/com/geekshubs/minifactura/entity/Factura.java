package com.geekshubs.minifactura.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.geekshubs.minifactura.controller.ClienteController;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;

import javax.persistence.*;
import java.util.Date;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Entity
@Table(name = "facturas")
public class Factura extends EntityModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String descripcion;
    private String observacion;
    @Temporal(TemporalType.DATE)
    @Column(name = "createdat")
    private Date fechacreacion;

    @JsonBackReference
    @ManyToOne(fetch = FetchType.LAZY)
    private Cliente cliente;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getObservacion() {
        return observacion;
    }

    public void setObservacion(String observacion) {
        this.observacion = observacion;
    }

    public Date getFechacreacion() {
        return fechacreacion;
    }

    public void setFechacreacion(Date fechacreacion) {
        this.fechacreacion = fechacreacion;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    @PostLoad
    public void addLink() {
        Link link1 = linkTo(methodOn(ClienteController.class).detalle(String.valueOf(this.id))).withSelfRel();
        Link link2 = linkTo(methodOn(ClienteController.class).listar()).withRel("todos");
        this.add(link1);
        this.add(link2);
    }
}
