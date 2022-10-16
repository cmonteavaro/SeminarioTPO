package ar.edu.uade.server.model;
import ar.edu.uade.server.model.enums.TipoRedSocialEnum;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Embeddable
public class RedSocial {
    private String link;
    @Enumerated
    private TipoRedSocialEnum redSocial;

    public RedSocial(String link, TipoRedSocialEnum red) {
        this.link = link;
        this.redSocial = red;
    }

    public RedSocial() {

    }
}