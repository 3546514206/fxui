package cn.lichenfei.fxui.examples;

import cn.lichenfei.fxui.common.Level;
import cn.lichenfei.fxui.common.SimpleButton;
import cn.lichenfei.fxui.controls.CFDrawer;
import cn.lichenfei.fxui.controls.CFMessage;
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
        p.setOnMouseClicked(event -> CFMessage.init("默认消息").show(this));// 这里传this只是为了获取到CFStage 中的组件
        s.setOnMouseClicked(event -> CFMessage.init("成功消息").setLevel(Level.SUCCESS).show(this));
        i.setOnMouseClicked(event -> CFMessage.init("这是一串比较长的消息提示！").setLevel(Level.INFO).show(this));
        w.setOnMouseClicked(event -> CFMessage.init("警告消息！").setLevel(Level.WARN).show(this));
        d.setOnMouseClicked(event -> CFMessage.init("危险消息！").setLevel(Level.DANGER).show(this));
    }

}
