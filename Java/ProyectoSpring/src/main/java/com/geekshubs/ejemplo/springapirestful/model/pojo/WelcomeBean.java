package com.geekshubs.ejemplo.springapirestful.model.pojo;

public class WelcomeBean {
    private static final String WELCOME_MSG = "Hello world!";
    private String welcomeMsg;

    public WelcomeBean() {
        this.welcomeMsg = WELCOME_MSG;
    }

    public WelcomeBean(String msg) {
        this.welcomeMsg = msg;
    }

    public String getWelcomeMsg() {
        return welcomeMsg;
    }

    public void setWelcomeMsg(String welcomeMsg) {
        this.welcomeMsg = welcomeMsg;
    }
}
