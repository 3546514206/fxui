package cn.lichenfei.fxui.demo;

import cn.lichenfei.fxui.common.FxUtils;
import cn.lichenfei.fxui.common.Level;
import cn.lichenfei.fxui.common.SimpleButton;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class ButtonDemo extends Application {

    @Override
    public void start(Stage primaryStage) {

        GridPane gridPane = new GridPane();
        StackPane root = new StackPane(gridPane);
        gridPane.setMaxHeight(Double.NEGATIVE_INFINITY);
        gridPane.setMaxWidth(Double.NEGATIVE_INFINITY);
        gridPane.setVgap(20);
        gridPane.setHgap(20);
        //普通按钮
        gridPane.add(SimpleButton.get("普通按钮", Level.PRIMARY), 0, 0);
        gridPane.add(SimpleButton.get("普通按钮", Level.SUCCESS), 1, 0);
        gridPane.add(SimpleButton.get("普通按钮", Level.INFO), 2, 0);
        gridPane.add(SimpleButton.get("普通按钮", Level.WARN), 3, 0);
        gridPane.add(SimpleButton.get("普通按钮", Level.DANGER), 4, 0);
        //圆角按钮
        gridPane.add(SimpleButton.get("圆角按钮", Level.PRIMARY, true), 0, 1);
        gridPane.add(SimpleButton.get("圆角按钮", Level.SUCCESS, true), 1, 1);
        gridPane.add(SimpleButton.get("圆角按钮", Level.INFO, true), 2, 1);
        gridPane.add(SimpleButton.get("圆角按钮", Level.WARN, true), 3, 1);
        gridPane.add(SimpleButton.get("圆角按钮", Level.DANGER, true), 4, 1);
        //朴素按钮
        gridPane.add(SimpleButton.get("朴素按钮", Level.PRIMARY, false, true), 0, 2);
        gridPane.add(SimpleButton.get("朴素按钮", Level.SUCCESS, false, true), 1, 2);
        gridPane.add(SimpleButton.get("朴素按钮", Level.INFO, true, true), 2, 2);
        gridPane.add(SimpleButton.get("朴素按钮", Level.WARN, false, true), 3, 2);
        gridPane.add(SimpleButton.get("朴素按钮", Level.DANGER, false, true), 4, 2);
        //
        Scene scene = new Scene(root);
        scene.getStylesheets().add(FxUtils.getCss("/css/core.css"));// 加载css
        primaryStage.setScene(scene);
        primaryStage.setWidth(1200);
        primaryStage.setHeight(700);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
