package com.adpmx.quizter.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;

@Service
public class MailService {

    private static final Logger LOGGER = LoggerFactory.getLogger(MailService.class);

    @Autowired
    private JavaMailSender mailSender;

    @Async
    public void userRegistered(String username, String password){
        SimpleMailMessage message = new SimpleMailMessage();

        message.setFrom("cecim.sistema@gmail.com"); //Se indica de donde (quién) saldrá el Correo
        message.setTo(username); //Se indica el destinatario
        message.setSubject("Detalles de su cuenta de la aplicación"); //Se indica el asunto del Correo
        String cuerpoMensaje = "A continuación se describe el detalle de su cuenta: "
                + "\n\nUsuario: " + username + "\n\nContraseña: " + password + ""
                + "\n\nEs un placer atenderlo.";
        message.setText(cuerpoMensaje); //Se indica el detalle del mensaje

        try {
            mailSender.send(message);
            LOGGER.info("Mensaje Enviado");
            //return appUser;
        } catch (Exception e) {
            e.printStackTrace();
            LOGGER.info("Error al enviar el mensaje");
            //return "Error al enviar el mensaje";
        }
    }
}
