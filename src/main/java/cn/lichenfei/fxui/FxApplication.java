package cn.lichenfei.fxui;

import cn.lichenfei.fxui.common.FxUtil;
import cn.lichenfei.fxui.controls.CFHeader;
import cn.lichenfei.fxui.controls.CFStage;
import cn.lichenfei.fxui.project.Login;
import javafx.application.Application;

import javafx.scene.SceneAntialiasing;
import javafx.scene.SubScene;
import javafx.stage.Stage;

/**
 * @author ChenFei
 * @date 2022/12/9
 */
public class FxApplication extends Application {
    @Override
    public void start(Stage stage) {
        //基础尺寸
        SubScene subScene = new SubScene(new Login(), 800, 500, true, SceneAntialiasing.BALANCED);
        stage = new CFStage(subScene, 800, 500).setHeaderStyle(CFHeader.HeaderStyle.NONE);
        stage.setTitle("chenfei-fx");// 标题
        stage.getIcons().add(FxUtil.getImage("/img/logo.jpg"));// icon
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}