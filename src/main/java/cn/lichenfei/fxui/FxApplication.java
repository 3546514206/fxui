package cn.lichenfei.fxui;

import cn.lichenfei.fxui.common.FxUtils;
import cn.lichenfei.fxui.view.HomeView;
import javafx.application.Application;

import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * @author ChenFei
 * @date 2022/12/9
 */
public class FxApplication extends Application {
    @Override
    public void start(Stage stage) {
        //基础尺寸
        stage.setWidth(1100);
        stage.setHeight(650);
        stage.setMinWidth(1100);
        stage.setMinHeight(650);
        Scene scene = new Scene(new HomeView());
        scene.getStylesheets().addAll(FxUtils.getCss("/css/base.css"));
        stage.setTitle("Hello!");// 标题
        stage.getIcons().add(FxUtils.getImage("/img/logo.jpg"));// icon
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}