package cn.lichenfei.fxui;

import cn.lichenfei.fxui.common.FxUtil;
import cn.lichenfei.fxui.controls.CFHeader;
import cn.lichenfei.fxui.controls.CFStage;
import cn.lichenfei.fxui.project.Index;
import com.sun.javafx.application.HostServicesDelegate;
import javafx.application.Application;
import javafx.stage.Stage;

/**
 * @author ChenFei
 * @date 2022/12/9
 */
public class FxApplication extends Application {
    @Override
    public void start(Stage primaryStage) {

        //基础尺寸
        final CFStage stage = new CFStage(new Index(), 800, 500)
                .setHeaderStyle(CFHeader.HeaderStyle.CLOSE)
                .setBackdropImage(FxUtil.getImage("/img/backdrop.png"));
        stage.setTitle("chenfei-fxui");// 标题
        stage.getIcons().add(FxUtil.getImage("/img/logo.jpg"));// icon
        stage.show();

        //HostServicesDelegate instance = HostServicesDelegate.getInstance(this);
    }

    public static void main(String[] args) {
        launch();
    }
}