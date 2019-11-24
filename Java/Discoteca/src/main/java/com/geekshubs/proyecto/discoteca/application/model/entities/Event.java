package com.geekshubs.proyecto.discoteca.application.model.entities;


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
    @NotEmpty(message = "{overview.notempty}")
    private String overview;

    @Column(name="description")
    @NotEmpty(message = "{description.notempty}")
    private String description;

    @Column(name="date")
    @Temporal(TemporalType.TIMESTAMP)
    @DateTimeFormat(pattern = "dd-MM-yyyy HH:mm")
    @NotNull(message = "{date.notnull}")
    private Date date;

    @Column(name="capacity")
    @NotNull(message = "{capacity.notnull}")
    private Long capacity;

    public Event(@NotEmpty(message = "{overview.notempty}") String overview, @NotEmpty(message = "{description.notempty}") String description, @NotNull(message = "{date.notnull}") Date date, @NotNull(message = "{capacity.notnull}") Long capacity) {
        this.overview = overview;
        this.description = description;
        this.date = date;
        this.capacity = capacity;
    }

    public Event() {}

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
