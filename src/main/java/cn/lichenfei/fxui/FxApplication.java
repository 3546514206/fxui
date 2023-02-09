package cn.lichenfei.fxui;

import cn.lichenfei.fxui.common.FxUtils;
import cn.lichenfei.fxui.controls.CFStage;
import cn.lichenfei.fxui.ui.Login;
import javafx.application.Application;

import javafx.stage.Stage;

/**
 * @author ChenFei
 * @date 2022/12/9
 */
public class FxApplication extends Application {
    @Override
    public void start(Stage stage) {
        //基础尺寸
        stage = new CFStage(new Login());
        stage.setTitle("chenfei-fx");// 标题
        stage.getIcons().add(FxUtils.getImage("/img/logo.jpg"));// icon
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}