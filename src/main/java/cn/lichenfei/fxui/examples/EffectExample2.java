package cn.lichenfei.fxui.examples;

import javafx.scene.Cursor;
import javafx.scene.control.Label;
import javafx.scene.effect.BlurType;
import javafx.scene.effect.InnerShadow;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

/**
 * @author ChenFei
 * @date 2023/2/23
 */
class EffectExample2 extends StackPane {

    private Label label = new Label("InnerShadow");

    public EffectExample2() {
        label.setFont(Font.font(16));
        getChildren().add(label);
        setStyle("-fx-background-radius: 5px;" +
                "-fx-background-color: rgb(255,255,255);" +
                "-fx-border-radius: 5px;" +
                "-fx-border-color: -cf-border-color;");
        setPrefSize(150, 150);
        setMaxSize(USE_PREF_SIZE, USE_PREF_SIZE);
        //阴影效果绑定
        InnerShadow dropShadow = new InnerShadow(BlurType.THREE_PASS_BOX, Color.rgb(0, 0, 0, 0.6), 20, 0, 0, 0);
        setEffect(dropShadow);
        setCursor(Cursor.HAND);
    }
}
