package com.doit_well.trip_service.utils;

import org.springframework.stereotype.Component;

import java.util.Random;

@Component
public class StringGenerator {
    public String generateReference() {
        String SALTCHARS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";
        StringBuilder salt = new StringBuilder();
        Random rnd = new Random();
        while (salt.length() < 5) { // length of the random string.
            int index = (int) (rnd.nextFloat() * SALTCHARS.length());
            salt.append(SALTCHARS.charAt(index));
        }
        String saltStr = salt.toString().toUpperCase();
        return saltStr;

    }
}
