package com.geekshubs.proyecto.discoteca.application.model.entities;


import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Entity
@Table(name="users")
public class User {

    public interface RegisterValidation {
        // validation group marker interface
    }

    public interface LoginValidation {
        // validation group marker interface
    }

    public User(@NotEmpty(message = "{username.notempty}", groups = {RegisterValidation.class, LoginValidation.class}) String username, @NotEmpty(message = "{password.notempty}", groups = {RegisterValidation.class, LoginValidation.class}) String password, @NotEmpty(message = "{name.notempty}", groups = {RegisterValidation.class}) String name, @NotEmpty(message = "{surname.notempty}", groups = {RegisterValidation.class}) String surname, @NotNull(message = "{age.notnull}", groups = {RegisterValidation.class}) Integer age, @NotEmpty(message = "{phonenumber.notnull}", groups = {RegisterValidation.class}) String phoneNumber, Boolean isAdmin, Boolean isSuperadmin) {
        this.username = username;
        this.password = password;
        this.name = name;
        this.surname = surname;
        this.age = age;
        this.phoneNumber = phoneNumber;
        this.isAdmin = isAdmin;
        this.isSuperadmin = isSuperadmin;
    }

    public User() {}

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name="username")
    @NotEmpty(message = "{username.notempty}", groups = {RegisterValidation.class, LoginValidation.class})
    private String username;

    @Column(name="password")
    @NotEmpty(message = "{password.notempty}", groups = {RegisterValidation.class, LoginValidation.class})
    private String password;

    @Column(name="name")
    @NotEmpty(message = "{name.notempty}", groups = {RegisterValidation.class})
    private String name;

    @Column(name="surname")
    @NotEmpty(message = "{surname.notempty}", groups = {RegisterValidation.class})
    private String surname;

    @Column(name="age")
    @NotNull(message = "{age.notnull}", groups = {RegisterValidation.class})
    private Integer age;

    @Column(name="phonenumber")
    @NotEmpty(message = "{phonenumber.notnull}", groups = {RegisterValidation.class})
    private String phoneNumber;

    @Column(name="is_admin")
    private Boolean isAdmin;

    @Column(name="is_superadmin")
    private Boolean isSuperadmin;

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

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Boolean getAdmin() {
        return isAdmin;
    }

    public void setAdmin(Boolean admin) {
        isAdmin = admin;
    }

    public Boolean getSuperadmin() {
        return isSuperadmin;
    }

    public void setSuperadmin(Boolean superadmin) {
        isSuperadmin = superadmin;
    }
}
