/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package grupoj.emailSender;

import grupoj.prentrega1.Notificacion;
import grupoj.prentrega1.Usuario;

import java.util.Properties;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;


/**
 * Esta clase es usada para enviar emails a los usuarios
 * noreply.notify.sii@gmail.com sii2sii2 01/01/1999 otro
 *
 * @author juanp
 */
public class SendEmail {

    /**
     * El metodo envia una notificacion a un usuario
     *
     * @param n Notificacion a enviar
     * @param u Usuario destinatario
     */
    public static void sendNotificacion(Notificacion n, Usuario u) {
        String contenido = n.getContenido();
        String usu = u.getEmail();
        String eve = n.getEv().getNombre();
        SendEmail.sendEmail(eve, contenido, usu);
    }

    private static void sendEmail(String eve, String contenido, String toUser) {
        final String username = "noreply.notify.sii@gmail.com";
        final String password = "sii2sii2";

        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");

        Session session = Session.getInstance(props,
                new javax.mail.Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(username, password);
            }
        });

        try {

            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(username));
            message.setRecipients(Message.RecipientType.TO,
                    InternetAddress.parse(toUser));
            message.setSubject("Nueva notificacion sobre " + eve + "!");
            message.setText(contenido + "\nPara dejar de recibir notificaciones acerca de eventos que le han interesado, por favor, dirijase a su cuenta en el sitio y desactive manualmente el envio al email");

            Transport.send(message);

            System.out.println("Done");

        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }

    }

}
