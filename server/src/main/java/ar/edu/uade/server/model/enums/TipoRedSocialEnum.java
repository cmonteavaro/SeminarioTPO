package ar.edu.uade.server.model.enums;

import lombok.Getter;

import java.util.Arrays;

public enum TipoRedSocialEnum {
    INSTAGRAM("Instagram"),
    TWITTER("Twitter"),
    WHATSAPP("Whatsapp"),
    FACEBOOK("Facebook"),
    TELEGRAM("Telegram");

    @Getter
    private String nombre;

    TipoRedSocialEnum(String nombre) {
        this.nombre = nombre;
    }

    public static TipoRedSocialEnum getEnum(String nombre) {
        return Arrays.stream(TipoRedSocialEnum.values()).filter(e -> e.getNombre().equals(nombre)).toList().get(0);
    }
}