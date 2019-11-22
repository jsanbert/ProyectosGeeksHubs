package com.geekshubs.proyecto.discoteca.model.entities;


import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Conditional;
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

    @Column(name="user_id")
    @NotNull
    private Long userId;

    @Column(name="event_id")
    @NotNull
    private Long eventId;

    @Column(name="token")
    private String token;

    public Register(@NotNull Long userId, @NotNull Long eventId, String token) {
        this.userId = userId;
        this.eventId = eventId;
        this.token = token;
    }

    public Register() {}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getEventId() {
        return eventId;
    }

    public void setEventId(Long eventId) {
        this.eventId = eventId;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
