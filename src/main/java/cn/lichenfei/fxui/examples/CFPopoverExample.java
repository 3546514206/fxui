package cn.lichenfei.fxui.examples;

import cn.lichenfei.fxui.common.SimpleButton;
import cn.lichenfei.fxui.controls.CFPopover;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;


//提示案例
public class CFPopoverExample extends StackPane {

    SimpleButton bottomStart = new SimpleButton("bottom-start");

    HBox hBox = new HBox(bottomStart);

    //
    StackPane content = new StackPane();
    CFPopover cfPopover = new CFPopover(content);

    public CFPopoverExample() {
        getChildren().add(hBox);
        //
        hBox.setMaxSize(USE_PREF_SIZE, USE_PREF_SIZE);
        hBox.setSpacing(20);
        content.setPrefSize(200, 200);
        //
        bottomStart.setOnMouseClicked(event -> cfPopover.show(bottomStart));
    }
}
