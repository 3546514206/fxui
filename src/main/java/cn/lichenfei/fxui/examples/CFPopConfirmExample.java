package cn.lichenfei.fxui.examples;

import cn.lichenfei.fxui.common.Level;
import cn.lichenfei.fxui.common.SimpleButton;
import cn.lichenfei.fxui.controls.CFPopConfirm;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import org.kordamp.ikonli.antdesignicons.AntDesignIconsOutlined;
import org.kordamp.ikonli.javafx.FontIcon;

//气泡确认框 案例
public class CFPopConfirmExample extends StackPane {

    SimpleButton button
            = SimpleButton.get("删除", Level.DANGER).setFontIcon(FontIcon.of(AntDesignIconsOutlined.DELETE));
    //
    HBox hBox = new HBox(button);
    //

    public CFPopConfirmExample() {
        getChildren().addAll(hBox);
        //
        hBox.setMaxSize(USE_PREF_SIZE, USE_PREF_SIZE);
        hBox.setSpacing(20);
        button.setOnMouseClicked(event -> {
            CFPopConfirm.create("删除后无法恢复，是否继续？").show(button);
        });
    }
}
