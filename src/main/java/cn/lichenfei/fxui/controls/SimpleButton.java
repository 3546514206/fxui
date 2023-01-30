package cn.lichenfei.fxui.controls;

import cn.lichenfei.fxui.common.CFStyleClass;
import cn.lichenfei.fxui.common.Level;
import javafx.scene.control.Button;
import org.kordamp.ikonli.javafx.FontIcon;

public class SimpleButton extends Button {

    public static SimpleButton get(String text, Level level){
        return new SimpleButton(text,level);
    }

    public SimpleButton(String text) {
        this.setText(text);
        setStyleClass(CFStyleClass.BUT, Level.PRIMARY.getStyleClass());
    }

    public SimpleButton(String text, Level level) {
        this.setText(text);
        setStyleClass(CFStyleClass.BUT, level.getStyleClass());
    }

    public SimpleButton round() {
        setStyleClass(CFStyleClass.ROUND_BUT);
        return this;
    }

    public SimpleButton plain() {
        setStyleClass(CFStyleClass.PLAIN_BUT);
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
