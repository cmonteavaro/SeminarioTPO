package ar.edu.uade.server.service;

import ar.edu.uade.server.DTO.FormularioDTO;
import ar.edu.uade.server.exceptions.AdopcionException;
import ar.edu.uade.server.exceptions.AnimalException;
import ar.edu.uade.server.model.Adopcion;
import ar.edu.uade.server.model.Animal;
import ar.edu.uade.server.model.PublicacionVoluntariado;
import ar.edu.uade.server.model.Transito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.internet.MimeMessage;
import java.util.Optional;

@Service
public class EmailServiceImpl {
    @Autowired private JavaMailSender javaMailSender;
    @Autowired private AdopcionService adopcionService;
    @Value("${spring.mail.username}") private String sender;
    public Boolean sendSimpleMail(String recipient, String subject, String msgBody)
    {
        try {
            // ---- Con Mime y helper
            MimeMessage mailMessage = javaMailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(mailMessage, true);

            mailMessage.setFrom(sender);
            mailMessage.setRecipients(MimeMessage.RecipientType.TO,recipient);
            mailMessage.setSubject(subject);

            helper.setText(msgBody,true);

            // ----- Con simple mail (No permite HTML)
//            SimpleMailMessage mailMessage = new SimpleMailMessage();
//
//            mailMessage.setFrom(sender);
//            mailMessage.setTo(recipient);
//            mailMessage.setText(msgBody);
//            mailMessage.setSubject(subject);

            javaMailSender.send(mailMessage);
            return true;
        }catch (Exception e) {
            return false;
        }
    }

    public Boolean sendMailDTO(FormularioDTO formularioDTO, Adopcion adopcion){
        String subject = "Solicitud de adopcion: "+adopcion.getAnimal().getNombre();
        String msg = "<h1 style='color: red'> Hola! </h1> "+adopcion.getAnimal().getNombre()+" ha recibido una solicitud de adopcion por parte de "+formularioDTO.getNombrePostulante()+". \nContacto: "+formularioDTO.getTelefonoPostulante();
        return this.sendSimpleMail(adopcion.getRefugio().getUsuario(),subject,msg);
    }

    public Boolean sendMailDTO(FormularioDTO formularioDTO, Transito transito){
        String subject = "Solicitud de transito: "+transito.getAnimal().getNombre();
        String msg = transito.getAnimal().getNombre()+" ha recibido una solicitud de transito por parte de "+formularioDTO.getNombrePostulante()+". \nContacto: "+formularioDTO.getTelefonoPostulante();
        return this.sendSimpleMail(transito.getRefugio().getUsuario(),subject,msg);
    }

    public Boolean sendMailDTO(FormularioDTO formularioDTO, PublicacionVoluntariado voluntariado){
        String subject = "Solicitud de voluntariado: "+voluntariado.getTitulo();
        String msg = " Ha recibido una solicitud de voluntariado por parte de "+formularioDTO.getNombrePostulante()+". \nContacto: "+formularioDTO.getTelefonoPostulante();
        return this.sendSimpleMail(voluntariado.getRefugio().getUsuario(),subject,msg);
    }
}
