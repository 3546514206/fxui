package cn.lichenfei.fxui.examples;

import cn.lichenfei.fxui.common.FxUtil;
import cn.lichenfei.fxui.common.Level;
import cn.lichenfei.fxui.common.SimpleButton;
import cn.lichenfei.fxui.controls.CFDrawer;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;

//消息提示案例
public class CFMessageExample extends StackPane {

    SimpleButton p = new SimpleButton("PRIMARY");
    SimpleButton s = new SimpleButton("SUCCESS");
    SimpleButton i = new SimpleButton("INFO");
    SimpleButton w = new SimpleButton("WARN");
    SimpleButton d = new SimpleButton("DANGER");

    HBox hBox = new HBox(p, s, i, w, d);

    private CFDrawer cfDrawer = new CFDrawer(400, this);

    public CFMessageExample() {
        getChildren().add(hBox);
        //
        hBox.setMaxSize(USE_PREF_SIZE, USE_PREF_SIZE);
        hBox.setSpacing(20);
        p.setOnMouseClicked(event -> FxUtil.showMessage(this, "默认消息"));
        s.setOnMouseClicked(event -> FxUtil.showMessage(this, "成功消息", Level.SUCCESS));
        i.setOnMouseClicked(event -> FxUtil.showMessage(this, "这是一串比较长的消息提示！", Level.INFO));
        w.setOnMouseClicked(event -> FxUtil.showMessage(this, "警告消息", Level.WARN));
        d.setOnMouseClicked(event -> FxUtil.showMessage(this, "危险消息", Level.DANGER));
    }

}
