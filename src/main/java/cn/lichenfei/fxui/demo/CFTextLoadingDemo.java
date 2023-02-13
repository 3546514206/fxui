package cn.lichenfei.fxui.demo;

import cn.lichenfei.fxui.common.FxUtil;
import cn.lichenfei.fxui.controls.CFTextLoading;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class CFTextLoadingDemo extends Application {

    @Override
    public void start(Stage primaryStage) {

        StackPane root = new StackPane(new CFTextLoading("加载中..."));
        Scene scene = new Scene(root);
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
