package cn.lichenfei.fxui.examples;

import cn.lichenfei.fxui.common.SimpleButton;
import cn.lichenfei.fxui.controls.CFDrawer;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;

//抽屉案例
public class CFDrawerExample extends StackPane {

    SimpleButton top = new SimpleButton("TOP");
    SimpleButton right = new SimpleButton("RIGHT");
    SimpleButton bottom = new SimpleButton("BOTTOM");
    SimpleButton left = new SimpleButton("LEFT");

    HBox hBox = new HBox(top, right, bottom, left);

    private CFDrawer cfDrawer = new CFDrawer(400, this);

    public CFDrawerExample() {
        getChildren().add(hBox);
        //
        hBox.setMaxSize(USE_PREF_SIZE, USE_PREF_SIZE);
        hBox.setSpacing(20);
        top.setOnMouseClicked(event -> cfDrawer.show(CFDrawer.Position.TOP));
        right.setOnMouseClicked(event -> cfDrawer.show());
        bottom.setOnMouseClicked(event -> cfDrawer.show(CFDrawer.Position.BOTTOM));
        left.setOnMouseClicked(event -> cfDrawer.show(CFDrawer.Position.LEFT));
    }

}
