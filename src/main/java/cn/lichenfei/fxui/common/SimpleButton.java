package cn.lichenfei.fxui.common;

import javafx.scene.control.Button;
import org.kordamp.ikonli.javafx.FontIcon;
import cn.lichenfei.fxui.common.Level;

public class SimpleButton extends Button {

    private static final String BUTTON_STYLE_CLASS = "cf-but";
    private static final String STYLE_SHEET = FxUtil.getResource("/css/cf-button.css");
    //
    private static final String BUTTON_PLAIN_CLASS = "plain";
    private static final String BUTTON_ROUND_CLASS = "round";

    public static SimpleButton get(String text, Level level) {
        return new SimpleButton(text, level);
    }

    /**
     * @param text
     */
    public SimpleButton(String text) {
        this.setText(text);
        setStyleClass(BUTTON_STYLE_CLASS, SimpleControl.getStyleClass(Level.PRIMARY.name()));
    }

    /**
     * @param text
     * @param level
     */
    public SimpleButton(String text, Level level) {
        this.setText(text);
        setStyleClass(BUTTON_STYLE_CLASS, SimpleControl.getStyleClass(level.name()));
    }

    public SimpleButton round() {
        setStyleClass(BUTTON_ROUND_CLASS);
        return this;
    }

    public SimpleButton plain() {
        setStyleClass(BUTTON_PLAIN_CLASS);
        return this;
    }

    public SimpleButton setFontIcon(FontIcon fontIcon) {
        this.setGraphic(fontIcon);
        return this;
    }

    private void setStyleClass(String... styleClass) {
        getStyleClass().addAll(styleClass);
    }

    @Override
    public String getUserAgentStylesheet() {
        return STYLE_SHEET;
    }
}
