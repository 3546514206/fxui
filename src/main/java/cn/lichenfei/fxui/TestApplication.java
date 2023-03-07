package cn.lichenfei.fxui;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * @author ChenFei
 * @date 2022/12/9
 */
public class TestApplication extends Application {


    @Override
    public void start(Stage primaryStage) {

        Scene scene = new Scene(new Test(), 700, 500);
        primaryStage.setScene(scene);
        primaryStage.show();

    }

    public static void main(String[] args) {
        launch();
    }
}