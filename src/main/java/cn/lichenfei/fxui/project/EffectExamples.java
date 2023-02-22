package cn.lichenfei.fxui.project;

import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.effect.BlurType;
import javafx.scene.effect.DropShadow;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

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
        DropShadow dropShadow = new DropShadow(BlurType.THREE_PASS_BOX, Color.rgb(0, 0, 0, 0.6), 20, 0, 0, 8);
        setEffect(dropShadow);
        /*
        setOnMouseMoved(event -> {
            double x = event.getX();
            double y = event.getY();
            dropShadow.setOffsetX(x > getWidth() / 2 ? 8 : -8);
            dropShadow.setOffsetY(y > getHeight() / 2 ? 8 : -8);
        });*/
    }

}