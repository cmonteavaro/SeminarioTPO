package ar.edu.uade.server.model;
import ar.edu.uade.server.model.enums.TipoRedSocialEnum;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RedSocial {

    private String link;
    private TipoRedSocialEnum redSocial;

    public RedSocial(String link, TipoRedSocialEnum red) {
        this.link = link;
        this.redSocial = red;
    }



}