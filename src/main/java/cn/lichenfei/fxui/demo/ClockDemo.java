package cn.lichenfei.fxui.demo;

import cn.lichenfei.fxui.common.FxUtils;
import cn.lichenfei.fxui.controls.CFClock;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class ClockDemo extends Application {

    @Override
    public void start(Stage primaryStage) {

        StackPane root = new StackPane(new CFClock());
        Scene scene = new Scene(root);
        scene.getStylesheets().add(FxUtils.getCss("/css/core.css"));// 加载css
        primaryStage.setScene(scene);
        primaryStage.setWidth(500);
        primaryStage.setHeight(300);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }

}
