package cn.lichenfei.fxui.examples;

import cn.lichenfei.fxui.controls.CFRadioButton;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;

// 表单组件
public class FormExample extends StackPane {

    GridPane gridPane = new GridPane();

    //
    public FormExample() {
        getChildren().add(gridPane);
        gridPane.setMaxHeight(Double.NEGATIVE_INFINITY);
        gridPane.setMaxWidth(Double.NEGATIVE_INFINITY);
        gridPane.setVgap(20);
        gridPane.setHgap(20);
        //
        ToggleGroup tg = new ToggleGroup();
        RadioButton radioButton = new RadioButton("原生单选框");
        radioButton.setToggleGroup(tg);
        CFRadioButton cfRadioButton = new CFRadioButton("自定义单选框");
        cfRadioButton.setToggleGroup(tg);

        gridPane.add(radioButton, 0, 0);
        gridPane.add(cfRadioButton, 1, 0);
    }

}
