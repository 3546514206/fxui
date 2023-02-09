package cn.lichenfei.fxui.common;

import javafx.scene.control.Button;
import org.kordamp.ikonli.javafx.FontIcon;

public class SimpleButton extends Button {

    private static final String BUT_CLASS = "cf-but";
    private static final String PLAIN_CLASS = "plain";
    private static final String ROUND_CLASS = "round";

    public static SimpleButton get(String text, Level level) {
        return new SimpleButton(text, level);
    }

    public SimpleButton(String text) {
        this.setText(text);
        setStyleClass(BUT_CLASS, Level.PRIMARY.getStyleClass());
    }

    public SimpleButton(String text, Level level) {
        this.setText(text);
        setStyleClass(BUT_CLASS, level.getStyleClass());
    }

    public SimpleButton round() {
        setStyleClass(ROUND_CLASS);
        return this;
    }

    public SimpleButton plain() {
        setStyleClass(PLAIN_CLASS);
        return this;
    }

    public SimpleButton setFontIcon(FontIcon fontIcon) {
        this.setGraphic(fontIcon);
        return this;
    }

    private void setStyleClass(String... styleClass) {
        this.getStyleClass().addAll(styleClass);
    }
}
