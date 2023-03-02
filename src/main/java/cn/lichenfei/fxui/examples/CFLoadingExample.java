package cn.lichenfei.fxui.examples;

import cn.lichenfei.fxui.common.Level;
import cn.lichenfei.fxui.common.LoadingUtils;
import cn.lichenfei.fxui.common.SimpleButton;
import cn.lichenfei.fxui.controls.CFLoading;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;

import java.util.concurrent.TimeUnit;

public class CFLoadingExample extends StackPane {

    Button button1 = SimpleButton.get("按钮加载", Level.PRIMARY);
    Button button2 = SimpleButton.get("指定容器", Level.SUCCESS);
    Button button3 = SimpleButton.get("指定大小", Level.INFO);

    HBox hBox = new HBox(button1, button2, button3);

    public CFLoadingExample() {
        getChildren().add(hBox);
        hBox.setMaxSize(USE_PREF_SIZE, USE_PREF_SIZE);
        hBox.setAlignment(Pos.CENTER);
        hBox.setSpacing(50);

        //
        button1.setOnMouseClicked(event ->
                LoadingUtils.buttonLoad(button1, () -> {
                    // 在这个里边可以执行业务逻辑
                    try {
                        TimeUnit.SECONDS.sleep(5);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    return true;
                }));

        //
        button2.setOnMouseClicked(event ->
                LoadingUtils.nodeLoad(this, () -> {
                    // 在这个里边可以执行业务逻辑
                    try {
                        TimeUnit.SECONDS.sleep(5);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    return true;
                }));
        //
        button3.setOnMouseClicked(event ->
                LoadingUtils.nodeLoad(this, new CFLoading(CFLoading.Size.LARGE).setMessage("稍等片刻!"), () -> {
                    // 在这个里边可以执行业务逻辑
                    try {
                        TimeUnit.SECONDS.sleep(5);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    return true;
                }));

    }

}
