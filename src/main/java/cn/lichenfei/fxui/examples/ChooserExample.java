package cn.lichenfei.fxui.examples;

import cn.lichenfei.fxui.controls.CFFileChooser;
import javafx.geometry.Pos;
import javafx.scene.layout.VBox;

public class ChooserExample extends VBox {

    public ChooserExample(){
        setAlignment(Pos.CENTER);
        getChildren().add(new CFFileChooser());
    }

}
