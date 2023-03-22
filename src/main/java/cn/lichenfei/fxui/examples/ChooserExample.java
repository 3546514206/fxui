package cn.lichenfei.fxui.examples;

import cn.lichenfei.fxui.controls.CFDirectoryChooser;
import cn.lichenfei.fxui.controls.CFFileChooser;
import javafx.geometry.Pos;
import javafx.scene.layout.HBox;

public class ChooserExample extends HBox {

    CFFileChooser cfFileChooser = new CFFileChooser();

    public ChooserExample() {
        setAlignment(Pos.CENTER);
        setSpacing(50);
        getChildren().addAll(cfFileChooser, new CFDirectoryChooser());

    }
}
