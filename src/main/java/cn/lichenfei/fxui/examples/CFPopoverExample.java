package cn.lichenfei.fxui.examples;

import cn.lichenfei.fxui.common.Level;
import cn.lichenfei.fxui.common.SimpleButton;
import cn.lichenfei.fxui.controls.CFPopover;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;

import java.util.concurrent.atomic.AtomicInteger;

//提示案例
public class CFPopoverExample extends StackPane {

    SimpleButton simpleButton = new SimpleButton("点我试试");
    SimpleButton nextButton = SimpleButton.get(CFPopover.Position.BOTTOM_LEFT.name().replace("_", "-"), Level.SUCCESS);
    //
    HBox hBox = new HBox(nextButton, simpleButton);
    //
    StackPane content = new StackPane();
    CFPopover cfPopover = new CFPopover(content);

    public CFPopoverExample() {
        getChildren().addAll(hBox);
        //
        hBox.setMaxSize(USE_PREF_SIZE, USE_PREF_SIZE);
        hBox.setSpacing(20);
        content.setPrefSize(200, 200);

        AtomicInteger i = new AtomicInteger();
        CFPopover.Position[] positionArr = {
                CFPopover.Position.BOTTOM_LEFT, CFPopover.Position.BOTTOM_CENTER, CFPopover.Position.BOTTOM_RIGHT,
                CFPopover.Position.TOP_LEFT, CFPopover.Position.TOP_CENTER, CFPopover.Position.TOP_RIGHT
        };
        //
        cfPopover.setPosition(CFPopover.Position.BOTTOM_RIGHT);
        simpleButton.setOnMouseClicked(event -> {
            cfPopover.setPosition(positionArr[i.get()]);// 设置显示的位置
            cfPopover.show(simpleButton);
        });
        //切换显示的位置
        nextButton.setOnMouseClicked(event -> {
            i.getAndIncrement();
            if (i.get() >= positionArr.length) {
                i.set(0);
            }
            nextButton.setText(positionArr[i.get()].name().replace("_", "-"));
        });
    }
}
