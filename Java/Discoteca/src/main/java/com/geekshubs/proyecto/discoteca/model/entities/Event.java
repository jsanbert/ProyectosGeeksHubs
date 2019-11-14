package com.geekshubs.proyecto.discoteca.model.entities;


import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Entity
@Table(name="events")
public class Event {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name="overview")
    @NotEmpty
    private String overview;

    @Column(name="description")
    @NotEmpty
    private String description;

    @Column(name="date")
    @NotNull
    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern = "dd-MM-yyyy hh:mm")
    private Date date;

    @Column(name="capacity")
    @NotNull
    private Long capacity;

    public Event(@NotEmpty String overview, @NotEmpty String description, @NotNull Date date, @NotNull Long capacity) {
        this.overview = overview;
        this.description = description;
        this.date = date;
        this.capacity = capacity;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getOverview() {
        return overview;
    }

    public void setOverview(String overview) {
        this.overview = overview;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Long getCapacity() {
        return capacity;
    }

    public void setCapacity(Long capacity) {
        this.capacity = capacity;
    }
}
