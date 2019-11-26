package com.geekshubs.minifactura.service;

public class ClienteErrorException extends RuntimeException {
    public ClienteErrorException(String msg) {
        super(msg);
    }
}
