package ar.edu.uade.server.DTO;

import ar.edu.uade.server.model.RedSocial;
import ar.edu.uade.server.model.enums.TipoRedSocialEnum;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RedSocialDTO {

    private String link;
    private String redSocial;

    public RedSocial toModel() {
        RedSocial red = new RedSocial();

        red.setLink(this.link);
        red.setRedSocial(TipoRedSocialEnum.getEnum(this.redSocial));

        return red;
    }
}
