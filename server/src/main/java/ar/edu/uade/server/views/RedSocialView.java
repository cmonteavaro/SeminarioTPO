package ar.edu.uade.server.views;

import ar.edu.uade.server.model.RedSocial;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
public class RedSocialView {

    private String link;
    private String redSocial;

    public static RedSocialView toView(RedSocial red) {
        RedSocialView view = new RedSocialView();

        view.setLink(red.getLink());
        view.setRedSocial(red.getRedSocial().getNombre());

        return view;
    }

    private RedSocialView() {}
}
