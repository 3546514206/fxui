package cn.lichenfei.fxui.demo;

import cn.lichenfei.fxui.common.FxUtil;
import cn.lichenfei.fxui.controls.CFClock;
import javafx.application.Application;
import javafx.scene.PerspectiveCamera;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class ClockDemo extends Application {

    @Override
    public void start(Stage primaryStage) {

        StackPane root = new StackPane(new CFClock());
        Scene scene = new Scene(root);
        scene.setCamera(new PerspectiveCamera());// 立体相机
        scene.getStylesheets().add(FxUtil.getResource("/css/cf-button.css"));// 加载css
        primaryStage.setScene(scene);
        primaryStage.setWidth(500);
        primaryStage.setHeight(300);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }

}
