package cn.lichenfei.fxui.demo;

import cn.lichenfei.fxui.common.FxUtils;
import cn.lichenfei.fxui.controls.CFInput;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import org.kordamp.ikonli.antdesignicons.AntDesignIconsOutlined;
import org.kordamp.ikonli.javafx.FontIcon;

public class CFInputDemo extends Application {

    @Override
    public void start(Stage primaryStage) {

        GridPane gridPane = new GridPane();
        StackPane root = new StackPane(gridPane);
        gridPane.setMaxHeight(Double.NEGATIVE_INFINITY);
        gridPane.setMaxWidth(Double.NEGATIVE_INFINITY);
        gridPane.setVgap(20);
        gridPane.setHgap(20);
        gridPane.add(new CFInput(), 0, 0);
        gridPane.add(new CFInput(FontIcon.of(AntDesignIconsOutlined.USER)), 1, 0);

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
