package cn.lichenfei.fxui.examples;

import javafx.scene.Cursor;
import javafx.scene.control.Label;
import javafx.scene.effect.BlurType;
import javafx.scene.effect.DropShadow;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

/**
 * @author ChenFei
 * @date 2023/2/23
 */
class EffectExample1 extends StackPane {

    private Label label = new Label("DropShadow");

    public EffectExample1() {
        label.setFont(Font.font(16));
        getChildren().add(label);
        setStyle("-fx-background-radius: 5px;" +
                "-fx-background-color: rgb(255,255,255);" +
                "-fx-border-radius: 5px;" +
                "-fx-border-color: -cf-border-color;");
        setPrefSize(150, 150);
        setMaxSize(USE_PREF_SIZE, USE_PREF_SIZE);
        //阴影效果绑定
        DropShadow dropShadow = new DropShadow(BlurType.THREE_PASS_BOX, Color.rgb(0, 0, 0, 0.6), 20, 0, 0, 0);
        setEffect(dropShadow);
        setCursor(Cursor.HAND);
        double offset = 8;
        setOnMouseMoved(event -> {// 让影子动态变化
            double x = event.getX();
            double y = event.getY();
            double ww = getWidth() / 2;
            double hh = getHeight() / 2;
            if (x < ww) { // 左侧
                double v1 = (ww - x) / ww;
                dropShadow.setOffsetX(v1 * -offset);
            } else { // 右侧
                double v1 = (x - ww) / ww;
                dropShadow.setOffsetX(v1 * offset);
            }
            if (y < hh) { // 上侧
                double v1 = (hh - y) / hh;
                dropShadow.setOffsetY(v1 * -offset);
            } else { // 下册
                double v1 = (y - hh) / hh;
                dropShadow.setOffsetY(v1 * offset);
            }
        });
    }
}
