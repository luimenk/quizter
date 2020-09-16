package com.adpmx.quizter.utils;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class EncryptedPasswordUtils {

    //Encriptación de password con BCryptPasswordEncoder
    public String encryte(String password) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(4);
        return encoder.encode(password);
    }

    /*public void main(String[] args) {
        String password = "123";
        String encryted = encryte(password);
    }*/
}
