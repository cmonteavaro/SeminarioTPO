package ar.edu.uade.server.service;
import ar.edu.uade.server.DTO.FormularioDTO;
import ar.edu.uade.server.model.Adopcion;
import ar.edu.uade.server.model.PublicacionVoluntariado;
import ar.edu.uade.server.model.Transito;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import javax.mail.internet.MimeMessage;

@Service
public class EmailServiceImpl {
    @SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
    @Autowired private JavaMailSender javaMailSender;
    @Value("${spring.mail.username}") private String sender;

    String msgRefugio = """
        <div style='background-color: #e6ffe6;font-family: calibri;padding: 4px;width: fit-content;'>
           <img src='https://st4.depositphotos.com/21979866/30071/i/450/depositphotos_300713158-stock-photo-banner-four-pets-labrador-retriever.jpg'>
           <h1 style='color: green;text-align: center;'>Hola desde MiRefugio!</h1>
           <h2 style='color: grey;'>%s ha recibido una solicitud de %s!</h2>
           <ul>
               <b>- Nombre:</b> %s
               <br>
               <b>- Apellido:</b> %s
                   <br>
               <b>- Telefono:</b> %s
               <br>
               <b>- Direccion:</b> %s
               <br>
               <b>- Correo:</b> %s
               <br>
               <b>- Notas:</b> %s
           </ul>
           <p style='color:grey;font-size: 12px;text-align: center;'> Este es un correo automatico enviado por MiRefugio </p>
        </div>""";

    String msgPostulante = """
        <div style='background-color: #e6ffe6;font-family: calibri;padding: 4px;width: fit-content;'>
           <img src='https://st4.depositphotos.com/21979866/30071/i/450/depositphotos_300713158-stock-photo-banner-four-pets-labrador-retriever.jpg'>
           <h1 style='color: green;text-align: center;'>Hola desde MiRefugio!</h1>
           <h2 style='color: grey;'>Te has postulado para %s %s!</h2>
           <ul>
               <b>- Refugio:</b> %s
           </ul>
           <h3 style='color: grey;'>Te postulaste con los siguientes datos:</h3>
           <ul>
               <li><b>Nombre:</b> %s</li>
               <li><b>Apellido:</b> %s</li>
               <li><b>Telefono:</b> %s</li>
               <li><b>Direccion:</b> %s</li>
               <li><b>Correo:</b> %s</li>
               <li><b>Notas:</b> %s</li>
           </ul>
           <p style='color:grey;font-size: 12px;text-align: center;'> Este es un correo automatico enviado por MiRefugio </p>
        </div>""";

    public Boolean sendSimpleMailToRefugio(String recipient, String subject, String msgBody)
    {
        try {
            MimeMessage mailMessage = javaMailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(mailMessage, true);

            mailMessage.setFrom(sender);
            mailMessage.setRecipients(MimeMessage.RecipientType.TO,recipient);
            mailMessage.setSubject(subject);
            helper.setText(msgBody,true);

            javaMailSender.send(mailMessage);
            return true;
        }catch (Exception e) {
            System.out.println(e.getMessage());
            return false;
        }
    }

    public Boolean sendSimpleMailToPostulante(String recipient, String subject, String msgBody)
    {
        try {
            MimeMessage mailMessage = javaMailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(mailMessage, true);

            mailMessage.setFrom(sender);
            mailMessage.setRecipients(MimeMessage.RecipientType.TO,recipient);
            mailMessage.setSubject(subject);
            helper.setText(msgBody,true);

            javaMailSender.send(mailMessage);
            return true;
        }catch (Exception e) {
            System.out.println(e.getMessage());
            return false;
        }
    }

    public Boolean sendMailToRefugioDTO(FormularioDTO formularioDTO, Adopcion adopcion){
        String subject = "Solicitud de adopcion: "+adopcion.getAnimal().getNombre();
        if (formularioDTO.getNotas()==null) formularioDTO.setNotas("-");
        String body = String.format(msgRefugio,adopcion.getAnimal().getNombre(),"adopcion",formularioDTO.getNombre(),formularioDTO.getApellido(),formularioDTO.getTelefono(),formularioDTO.getDireccion(),formularioDTO.getCorreo(),formularioDTO.getNotas());
        return this.sendSimpleMailToRefugio(adopcion.getRefugio().getCorreo(),subject,body);
    }

    public Boolean sendMailToPostulanteDTO(FormularioDTO formularioDTO, Adopcion adopcion){
        String subject = "Solicitud de adopcion: "+adopcion.getAnimal().getNombre();
        if (formularioDTO.getNotas()==null) formularioDTO.setNotas("-");
        String body = String.format(msgPostulante,"adoptar a",adopcion.getAnimal().getNombre(),adopcion.getRefugio().getNombre(),formularioDTO.getNombre(),formularioDTO.getApellido(),formularioDTO.getTelefono(),formularioDTO.getDireccion(),formularioDTO.getCorreo(),formularioDTO.getNotas());
        return this.sendSimpleMailToPostulante(adopcion.getRefugio().getCorreo(),subject,body);
    }

    public Boolean sendMailToRefugioDTO(FormularioDTO formularioDTO, Transito transito){
        String subject = "Solicitud de transito: "+transito.getAnimal().getNombre();
        if (formularioDTO.getNotas()==null) formularioDTO.setNotas("-");
        String body = String.format(msgRefugio,transito.getAnimal().getNombre(),"transito",formularioDTO.getNombre(),formularioDTO.getApellido(),formularioDTO.getTelefono(),formularioDTO.getDireccion(),formularioDTO.getCorreo(),formularioDTO.getNotas());
        return this.sendSimpleMail(transito.getRefugio().getCorreo(),subject,body);
    }

    public Boolean sendMailToPostulanteDTO(FormularioDTO formularioDTO, Transito transito){
        String subject = "Solicitud de transito: "+transito.getAnimal().getNombre();
        if (formularioDTO.getNotas()==null) formularioDTO.setNotas("-");
        String body = String.format(msgPostulante,"transitar a",transito.getAnimal().getNombre(),transito.getRefugio().getNombre(),formularioDTO.getNombre(),formularioDTO.getApellido(),formularioDTO.getTelefono(),formularioDTO.getDireccion(),formularioDTO.getCorreo(),formularioDTO.getNotas());
        return this.sendSimpleMail(transito.getRefugio().getCorreo(),subject,body);
    }

    public Boolean sendMailToRefugioDTO(FormularioDTO formularioDTO, PublicacionVoluntariado voluntariado){
        String subject = "Solicitud de voluntariado: "+voluntariado.getTitulo();
        if (formularioDTO.getNotas()==null) formularioDTO.setNotas("-");
        String body = String.format(msgRefugio,"Usted","voluntariado",formularioDTO.getNombre(),formularioDTO.getApellido(),formularioDTO.getTelefono(),formularioDTO.getDireccion(),formularioDTO.getCorreo(),formularioDTO.getNotas());
        return this.sendSimpleMail(voluntariado.getRefugio().getCorreo(),subject,body);
    }

    public Boolean sendMailToPostulanteDTO(FormularioDTO formularioDTO, PublicacionVoluntariado voluntariado){
        String subject = "Solicitud de voluntariado: "+voluntariado.getTitulo();
        if (formularioDTO.getNotas()==null) formularioDTO.setNotas("-");
        String body = String.format(msgPostulante,"realizar voluntariado para",voluntariado.getRefugio().getNombre(),voluntariado.getRefugio().getNombre(),formularioDTO.getNombre(),formularioDTO.getApellido(),formularioDTO.getTelefono(),formularioDTO.getDireccion(),formularioDTO.getCorreo(),formularioDTO.getNotas());
        return this.sendSimpleMail(voluntariado.getRefugio().getCorreo(),subject,body);
    }
}
