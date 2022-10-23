package ar.edu.uade.server.service;

import ar.edu.uade.server.DTO.FormularioDTO;
import ar.edu.uade.server.exceptions.AdopcionException;
import ar.edu.uade.server.exceptions.AnimalException;
import ar.edu.uade.server.model.Adopcion;
import ar.edu.uade.server.model.Animal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class EmailServiceImpl {
    @Autowired private JavaMailSender javaMailSender;
    @Autowired private AdopcionService adopcionService;
    @Value("${spring.mail.username}") private String sender;
    public Boolean sendSimpleMail(String recipient, String subject, String msgBody)
    {
        try {
            SimpleMailMessage mailMessage = new SimpleMailMessage();

            mailMessage.setFrom(sender);
            mailMessage.setTo(recipient);
            mailMessage.setText(msgBody);
            mailMessage.setSubject(subject);

            javaMailSender.send(mailMessage);
            return true;
        }catch (Exception e) {
            return false;
        }
    }

    public Boolean sendMailDTO(FormularioDTO formularioDTO, Adopcion adopcion){
        String subject = "Solicitud de adopcion: "+adopcion.getAnimal().getNombre();
        String msg = adopcion.getAnimal().getNombre()+" ha recibido una solicitud de adopcion por parte de "+formularioDTO.getNombrePostulante()+". \nContacto: "+formularioDTO.getTelefonoPostulante();
        return this.sendSimpleMail(adopcion.getRefugio().getUsuario(),subject,msg);
    }
}
