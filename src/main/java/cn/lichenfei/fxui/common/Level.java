package cn.lichenfei.fxui.common;

import org.kordamp.ikonli.Ikon;
import org.kordamp.ikonli.antdesignicons.AntDesignIconsFilled;

public enum Level {
    PRIMARY(AntDesignIconsFilled.MESSAGE),
    SUCCESS(AntDesignIconsFilled.CHECK_CIRCLE),
    INFO(AntDesignIconsFilled.INFO_CIRCLE),
    WARN(AntDesignIconsFilled.EXCLAMATION_CIRCLE),
    DANGER(AntDesignIconsFilled.CLOSE_CIRCLE);

    Level(Ikon ikon) {
        this.ikon = ikon;
    }

    private Ikon ikon;

    public Ikon getIkon() {
        return ikon;
    }

    public String getStyleClass(){
        return name().toLowerCase();
    }
}
