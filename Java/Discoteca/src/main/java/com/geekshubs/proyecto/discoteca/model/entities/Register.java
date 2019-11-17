package com.geekshubs.proyecto.discoteca.model.entities;


import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Entity
@Table(name="registers")
public class Register {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name="name")
    @NotEmpty(message = "{name.notempty}")
    private String name;

    @Column(name="surname")
    @NotEmpty(message = "{surname.notempty}")
    private String surname;

    @Column(name="age")
    @NotNull(message = "{age.notnull}")
    private int age;

    @Column(name="phonenumber")
    @NotEmpty(message = "{phonenumber.notnull}")
    private String phoneNumber;

    @ManyToOne
    @JoinColumn(name="eventId")
    private Event event;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public Event getEvent() {
        return event;
    }

    public void setEvent(Event event) {
        this.event = event;
    }
}
