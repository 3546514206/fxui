package cn.lichenfei.fxui.project;

import javafx.animation.Animation;
import javafx.animation.TranslateTransition;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.effect.BlurType;
import javafx.scene.effect.DropShadow;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.util.Duration;

public class EffectExamples extends ScrollPane {

    private VBox itemBox = new VBox();

    public EffectExamples() {
        getStyleClass().add("scroll-bar-style");
        setContent(itemBox);
        itemBox.prefWidthProperty().bind(widthProperty().subtract(2));
        itemBox.setAlignment(Pos.TOP_CENTER);
        itemBox.setSpacing(50);
        //
        Pane empty = new Pane();
        empty.setPrefHeight(50);
        //
        itemBox.getChildren().addAll(
                empty,
                new EffectExample1()
        );
    }
}

class EffectExample1 extends StackPane {

    private Label label = new Label("DropShadow");

    private Rectangle TRANSITION_NODE = new Rectangle();
    private TranslateTransition TT = new TranslateTransition(Duration.millis(300), TRANSITION_NODE);

    public EffectExample1() {
        label.setFont(Font.font(16));
        getChildren().add(label);
        setStyle("-fx-background-radius: 3px;" +
                "-fx-background-color: rgb(255,255,255);" +
                "-fx-border-radius: 3px;" +
                "-fx-border-color: -cf-border-color;");
        setPrefSize(150, 150);
        setMaxSize(USE_PREF_SIZE, USE_PREF_SIZE);
        //阴影效果绑定
        DropShadow dropShadow = new DropShadow(BlurType.THREE_PASS_BOX, Color.rgb(0, 0, 0, 0.6), 0, 0, 0, 0);
        dropShadow.radiusProperty().bind(TRANSITION_NODE.translateXProperty());
        setEffect(dropShadow);
        //鼠标移入开始动画
        hoverProperty().addListener((observable, oldValue, newValue) -> {
            if (Animation.Status.RUNNING.equals(TT.getStatus())) {
                TT.stop();
                double translateX = TRANSITION_NODE.getTranslateX();
                TT.setToX(newValue ? translateX : 0);
                TT.setFromX(newValue ? 0 : translateX);
            } else {
                TT.setToX(newValue ? 20 : 0);
                TT.setFromX(newValue ? 0 : 20);
            }
            TT.play();
        });
    }

}