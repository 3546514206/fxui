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
        Scene scene = new Scene(new HomeView(), 1100, 650);
        scene.getStylesheets().addAll(FxUtils.getCss("/css/base.css"));
        stage.setTitle("Hello!");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}