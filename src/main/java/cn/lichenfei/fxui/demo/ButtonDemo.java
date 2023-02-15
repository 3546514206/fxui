package cn.lichenfei.fxui.demo;

import cn.lichenfei.fxui.common.Level;
import cn.lichenfei.fxui.common.SimpleButton;
import cn.lichenfei.fxui.common.SimpleControl;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import org.kordamp.ikonli.antdesignicons.AntDesignIconsFilled;
import org.kordamp.ikonli.javafx.FontIcon;

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
        gridPane.add(SimpleButton.get("圆角按钮", Level.PRIMARY).round(), 0, 1);
        gridPane.add(SimpleButton.get("圆角按钮", Level.SUCCESS).round(), 1, 1);
        gridPane.add(SimpleButton.get("圆角按钮", Level.INFO).round(), 2, 1);
        gridPane.add(SimpleButton.get("圆角按钮", Level.WARN).round(), 3, 1);
        gridPane.add(SimpleButton.get("圆角按钮", Level.DANGER).round(), 4, 1);
        //朴素按钮
        gridPane.add(SimpleButton.get("朴素按钮", Level.PRIMARY).plain(), 0, 2);
        gridPane.add(SimpleButton.get("朴素按钮", Level.SUCCESS).plain(), 1, 2);
        gridPane.add(SimpleButton.get("朴素按钮", Level.INFO).plain().round(), 2, 2);
        gridPane.add(SimpleButton.get("朴素按钮", Level.WARN).plain(), 3, 2);
        gridPane.add(SimpleButton.get("朴素按钮", Level.DANGER).plain(), 4, 2);
        //图标按钮
        gridPane.add(SimpleButton.get("图标按钮", Level.PRIMARY).setFontIcon(new FontIcon(AntDesignIconsFilled.LIKE)), 0, 3);
        gridPane.add(SimpleButton.get("图标按钮", Level.SUCCESS).round().setFontIcon(new FontIcon(AntDesignIconsFilled.WECHAT)), 1, 3);
        gridPane.add(SimpleButton.get("图标按钮", Level.INFO).plain().round().setFontIcon(new FontIcon(AntDesignIconsFilled.SETTING)), 2, 3);
        gridPane.add(SimpleButton.get("图标按钮", Level.WARN).plain().setFontIcon(new FontIcon(AntDesignIconsFilled.MESSAGE)), 3, 3);
        gridPane.add(SimpleButton.get("图标按钮", Level.DANGER).setFontIcon(new FontIcon(AntDesignIconsFilled.ALIPAY_CIRCLE)), 4, 3);
        //
        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.setWidth(1200);
        primaryStage.setHeight(700);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
