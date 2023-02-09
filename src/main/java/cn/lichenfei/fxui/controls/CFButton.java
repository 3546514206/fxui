package cn.lichenfei.fxui.controls;

import cn.lichenfei.fxui.common.FxUtils;
import cn.lichenfei.fxui.common.Level;
import javafx.scene.control.Button;
import org.kordamp.ikonli.javafx.FontIcon;

public class CFButton extends Button {

    private static final String STYLE_SHEET = "/css/cf-button.css";

    private static final String BUT_CLASS = "cf-but";
    private static final String PLAIN_CLASS = "plain";
    private static final String ROUND_CLASS = "round";

    public static CFButton get(String text, Level level) {
        return new CFButton(text, level);
    }

    public CFButton(String text) {
        this.setText(text);
        setStyleClass(BUT_CLASS, Level.PRIMARY.getStyleClass());
    }

    public CFButton(String text, Level level) {
        this.setText(text);
        setStyleClass(BUT_CLASS, level.getStyleClass());
    }

    public CFButton round() {
        setStyleClass(ROUND_CLASS);
        return this;
    }

    public CFButton plain() {
        setStyleClass(PLAIN_CLASS);
        return this;
    }

    public CFButton setFontIcon(FontIcon fontIcon) {
        this.setGraphic(fontIcon);
        return this;
    }

    private void setStyleClass(String... styleClass) {
        this.getStyleClass().addAll(styleClass);
    }

    @Override
    public String getUserAgentStylesheet() {
        return FxUtils.getCss(STYLE_SHEET);
    }
}
