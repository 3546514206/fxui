package cn.lichenfei.fxui.project;

/*import javafx.embed.swing.SwingFXUtils;*/
import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.SnapshotParameters;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.effect.*;
import javafx.scene.image.PixelFormat;
import javafx.scene.image.PixelWriter;
import javafx.scene.image.WritableImage;
import javafx.scene.image.WritablePixelFormat;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.nio.ByteBuffer;

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
                new EffectExample1(),
                new EffectExample2()/*,
                new EffectExample3()*/
        );
    }
}

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
/*

class EffectExample3 extends StackPane {

    public EffectExample3() {
        setPrefSize(64, 64);
        setMaxSize(USE_PREF_SIZE, USE_PREF_SIZE);
        setStyle("-fx-background-radius: 32px;" +
                "-fx-background-color: linear-gradient(from 0.0% 0.0% to 100.0% 0.0%, #fa00fdff 0.0%, #02dcddff 100.0%);");
        WritableImage writableImage = new WritableImage(64, 64);
        snapshot(new SnapshotParameters(), writableImage);

        BufferedImage bufferedImage = SwingFXUtils.fromFXImage(writableImage, null);
        try {
            ImageIO.write(bufferedImage, "png", new File("F:/123.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}*/
