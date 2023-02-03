package cn.lichenfei.fxui.demo;

import cn.lichenfei.fxui.common.FxUtils;
import cn.lichenfei.fxui.controls.CFCarousel;
import javafx.application.Application;
import javafx.css.Size;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import java.util.Arrays;

public class CarouselDemo extends Application {
    @Override
    public void start(Stage primaryStage) {

        //轮播图内容
        StackPane sp1 = new StackPane();
        sp1.getChildren().addAll(new ImageView(FxUtils.getImage("/img/img1.png")), getLabel(1));
        StackPane sp2 = new StackPane();
        sp2.getChildren().addAll(new ImageView(FxUtils.getImage("/img/img2.png")), getLabel(2));
        StackPane sp3 = new StackPane();
        sp3.getChildren().addAll(new ImageView(FxUtils.getImage("/img/img3.png")), getLabel(3));
        //轮播图
        CFCarousel cfCarousel = new CFCarousel(Arrays.asList(sp1, sp2, sp3), 500, 300);
        //
        StackPane root = new StackPane(cfCarousel);
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
