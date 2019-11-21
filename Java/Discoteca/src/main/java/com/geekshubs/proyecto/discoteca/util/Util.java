package com.geekshubs.proyecto.discoteca.util;

import org.springframework.web.servlet.ModelAndView;

import java.security.SecureRandom;
import java.util.Base64;

public class Util {

    private static final SecureRandom secureRandom = new SecureRandom(); //threadsafe
    private static final Base64.Encoder base64Encoder = Base64.getUrlEncoder(); //threadsafe

    public static ModelAndView errorPage(String errorTitle, String errorMessage) {
        return new ModelAndView("redirect:/error-display?errorTitle="+errorTitle+"&errorMessage="+errorMessage);
    }

    public static String generateToken() {
        byte[] randomBytes = new byte[24];
        secureRandom.nextBytes(randomBytes);
        return base64Encoder.encodeToString(randomBytes);
    }
}
