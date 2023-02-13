package cn.lichenfei.fxui;

import cn.lichenfei.fxui.common.FxUtil;
import cn.lichenfei.fxui.controls.CFStage;
import cn.lichenfei.fxui.view.HomeView;
import javafx.application.Application;

import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

/**
 * @author ChenFei
 * @date 2022/12/9
 */
public class FxApplication extends Application {
    @Override
    public void start(Stage stage) {
        //基础尺寸
        stage = new CFStage(new StackPane(new HomeView()));
        stage.setTitle("chenfei-fx");// 标题
        stage.getIcons().add(FxUtil.getImage("/img/logo.jpg"));// icon
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}