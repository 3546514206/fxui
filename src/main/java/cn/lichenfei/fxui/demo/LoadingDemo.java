package cn.lichenfei.fxui.demo;

import cn.lichenfei.fxui.common.FxUtils;
import cn.lichenfei.fxui.common.Level;
import cn.lichenfei.fxui.common.LoadingUtils;
import cn.lichenfei.fxui.controls.CFLoading;
import cn.lichenfei.fxui.common.SimpleButton;
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

import java.util.concurrent.TimeUnit;

public class LoadingDemo extends Application {

    @Override
    public void start(Stage primaryStage) {
        HBox hBox = new HBox();
        StackPane root = new StackPane(hBox);
        hBox.setMaxHeight(Double.NEGATIVE_INFINITY);
        hBox.setMaxWidth(Double.NEGATIVE_INFINITY);
        hBox.setAlignment(Pos.CENTER);
        hBox.setSpacing(50);
        //按钮加载
        Button button1 = SimpleButton.get("按钮加载", Level.PRIMARY);
        button1.setOnMouseClicked(event ->
                LoadingUtils.buttonLoad(button1, () -> {
                    // 在这个里边可以执行业务逻辑
                    try {
                        TimeUnit.SECONDS.sleep(5);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    return true;
                }));

        //指定容器
        Button button2 = SimpleButton.get("指定容器", Level.SUCCESS);
        button2.setOnMouseClicked(event ->
                LoadingUtils.nodeLoad(root, () -> {
                    // 在这个里边可以执行业务逻辑
                    try {
                        TimeUnit.SECONDS.sleep(5);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    return true;
                }));
        //指定大小
        Button button3 = SimpleButton.get("指定大小", Level.INFO);
        button3.setOnMouseClicked(event ->
                LoadingUtils.nodeLoad(root, new CFLoading(CFLoading.Size.LARGE).setMessage("稍等片刻!"), () -> {
                    // 在这个里边可以执行业务逻辑
                    try {
                        TimeUnit.SECONDS.sleep(5);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    return true;
                }));

        hBox.getChildren().addAll(button1, button2, button3);
        //
        Scene scene = new Scene(root);
        scene.getStylesheets().add(FxUtils.getCss("/css/core.css"));// 加载css
        primaryStage.setScene(scene);
        primaryStage.setWidth(700);
        primaryStage.setHeight(400);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }

}