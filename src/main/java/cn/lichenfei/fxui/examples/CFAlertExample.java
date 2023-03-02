package cn.lichenfei.fxui.examples;

import cn.lichenfei.fxui.common.Level;
import cn.lichenfei.fxui.controls.CFAlert;
import cn.lichenfei.fxui.controls.CFDrawer;
import javafx.geometry.Insets;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;

//提示案例
public class CFAlertExample extends StackPane {

    CFAlert cfAlert1 = new CFAlert("primary alert");
    CFAlert cfAlert2 = new CFAlert("success alert", Level.SUCCESS).showClose(true);
    CFAlert cfAlert3 = new CFAlert("info alert", Level.INFO);
    CFAlert cfAlert4 = new CFAlert("warn alert", Level.WARN);
    CFAlert cfAlert5 = new CFAlert("danger alert", Level.DANGER);
    //
    VBox hBox = new VBox(cfAlert1, cfAlert2, cfAlert3, cfAlert4, cfAlert5);

    private CFDrawer cfDrawer = new CFDrawer(400, this);

    public CFAlertExample() {
        getChildren().add(hBox);
        setPadding(new Insets(50));
        //
        hBox.setMaxSize(USE_COMPUTED_SIZE, USE_PREF_SIZE);
        hBox.setSpacing(20);
    }

}
