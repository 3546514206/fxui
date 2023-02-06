package cn.lichenfei.fxui.demo;

import cn.lichenfei.fxui.common.FxUtils;
import cn.lichenfei.fxui.controls.CFCarousel;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class CarouselDemo extends Application {
    @Override
    public void start(Stage primaryStage) {
        List<StackPane> paneList = new ArrayList<>();
        for (int i = 0; i < 4; i++) {
            StackPane sp = new StackPane(getLabel(i + 1));
            sp.setBackground(new Background(new BackgroundFill(Color.rgb(
                    new Random().nextInt(255),
                    new Random().nextInt(255),
                    new Random().nextInt(255)), null, null)));
            paneList.add(sp);
        }
        CFCarousel carousel = CFCarousel.create(paneList, 500, 300, true, CFCarousel.Direction.LEFT);
        //
        StackPane root = new StackPane(carousel);
        Scene scene = new Scene(root);
        scene.getStylesheets().add(FxUtils.getCss("/css/core.css"));// 加载css
        primaryStage.setScene(scene);
        primaryStage.setWidth(1200);
        primaryStage.setHeight(700);
        primaryStage.show();
    }

    public Label getLabel(int i) {
        Label label = new Label(i + "");
        label.setFont(new Font(50));
        label.setTextFill(Color.WHITE);
        return label;
    }

    public static void main(String[] args) {
        launch(args);
    }
}
