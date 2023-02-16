package cn.lichenfei.fxui.demo;

import javafx.animation.TranslateTransition;
import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.EventHandler;
import javafx.geometry.Bounds;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Region;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.Duration;

public class Demo1 extends Application {

    private Button bb = new Button();

    @Override
    public void start(Stage primaryStage) {

        VBox vBox = new VBox(new Label("1111111111"), new Label("2222222222222"), new Label("33333333333333333"));
        ScrollPane scrollPane = new ScrollPane(vBox);
        scrollPane.setMaxSize(Region.USE_PREF_SIZE, Region.USE_PREF_SIZE);

        bb.translateXProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
                System.out.println(newValue);
                scrollPane.setPrefHeight(newValue.doubleValue());
            }
        });



        //vBox.proper


        TranslateTransition TT = new TranslateTransition(Duration.millis(1000), bb);
        TT.setAutoReverse(true);
        TT.setCycleCount(-1);
        TT.setToX(0);

        scrollPane.boundsInLocalProperty().addListener(new ChangeListener<Bounds>() {
            @Override
            public void changed(ObservableValue<? extends Bounds> observable, Bounds oldValue, Bounds newValue) {
                TT.setFromX(newValue.getHeight());
            }
        });


        // 不显示滚动条
        scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        scrollPane.setMinSize(0, 0);
        StackPane.setAlignment(scrollPane, Pos.TOP_CENTER);

        //
        Button button = new Button("开始缩放");
        button.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                //TT.setFromX(vBox.getHeight());
                TT.play();
            }
        });

        VBox root = new VBox(scrollPane, button);
        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.setWidth(500);
        primaryStage.setHeight(300);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }

}
