package cn.lichenfei.fxui.demo;

import cn.lichenfei.fxui.controls.CFTextField;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import org.kordamp.ikonli.antdesignicons.AntDesignIconsFilled;
import org.kordamp.ikonli.javafx.FontIcon;

public class CFTextFieldDemo extends Application {

    @Override
    public void start(Stage primaryStage) {

        GridPane gridPane = new GridPane();
        StackPane root = new StackPane(gridPane);
        gridPane.setMaxHeight(Double.NEGATIVE_INFINITY);
        gridPane.setMaxWidth(Double.NEGATIVE_INFINITY);
        gridPane.setVgap(20);
        gridPane.setHgap(20);

        // 文本框
        gridPane.add(new CFTextField(), 0, 0);
        gridPane.add(new CFTextField(CFTextField.Type.TEXT, new FontIcon(AntDesignIconsFilled.SETTING)), 1, 0);

        // 密码框
        CFTextField cfTextField = new CFTextField(CFTextField.Type.PASSWORD);
        cfTextField.setPromptText("请输入密码");
        gridPane.add(cfTextField, 0, 1);
        gridPane.add(new CFTextField(CFTextField.Type.PASSWORD, new FontIcon(AntDesignIconsFilled.LOCK)), 1, 1);

        //
        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.setWidth(900);
        primaryStage.setHeight(500);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }

}
