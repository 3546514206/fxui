package cn.lichenfei.fxui.demo;

import javafx.animation.Interpolator;
import javafx.animation.ParallelTransition;
import javafx.animation.RotateTransition;
import javafx.animation.TranslateTransition;
import javafx.application.Application;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Arc;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.util.Duration;


public class LoadingDemo extends Application {

    double width = 100;

    Button button = new Button();
    TranslateTransition t = new TranslateTransition();
    RotateTransition r = new RotateTransition();
    ParallelTransition pt = new ParallelTransition(t, r);
    //
    private Arc arc1 = getArc(width, 360, Color.valueOf("#dedfe0"));
    private Arc arc2 = getArc(width, 50, Color.valueOf("#409EFF"));

    private Arc getArc(double size, double arcLength, Color color) {
        Arc arc = new Arc(size / 2, size / 2, size / 2, size / 2, 0, arcLength);
        arc.setFill(null);
        arc.setStrokeWidth(4);
        arc.setStroke(color);
        return arc;
    }

    public Parent createContent() {
        Pane root = new Pane();
        root.setMaxHeight(Double.NEGATIVE_INFINITY);
        root.setMaxWidth(Double.NEGATIVE_INFINITY);
        root.setPrefWidth(width);
        root.setPrefHeight(width);
        root.getChildren().addAll(arc1, arc2);

        //移动动画
        t.setNode(button);
        t.setFromX(200);
        t.setToX(30);
        t.setDuration(Duration.millis(1000));
        t.setAutoReverse(true);
        t.setCycleCount(-1);
        //
        r.setNode(button);
        r.setFromAngle(0);
        r.setToAngle(360);
        r.setDuration(Duration.millis(700));
        r.setInterpolator(Interpolator.LINEAR);
        r.setCycleCount(-1);
        //
        pt.play();

        arc2.lengthProperty().bind(button.translateXProperty());
        arc2.startAngleProperty().bind(button.rotateProperty());

        StackPane stackPane = new StackPane(root);
        Label message = new Label("Loading...");// 提示文字
        message.setFont(new Font(14));
        message.setTextFill(Color.valueOf("#409EFF"));
        stackPane.getChildren().add(message);
        return stackPane;
    }

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setScene(new Scene(createContent()));
        primaryStage.setWidth(500);
        primaryStage.setHeight(400);
        primaryStage.show();
    }

    /**
     * Java main for when running without JavaFX launcher
     */
    public static void main(String[] args) {
        launch(args);
    }

}