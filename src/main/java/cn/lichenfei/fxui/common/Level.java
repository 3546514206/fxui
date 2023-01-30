package cn.lichenfei.fxui.common;

public enum Level {

    PRIMARY,

    SUCCESS,

    INFO,

    WARN,

    DANGER;

    public String getStyleClass() {
        return name().toLowerCase();
    }

}
