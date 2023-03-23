package cn.lichenfei.fxui.controls.skin;

import cn.lichenfei.fxui.controls.CFRadioButton;
import javafx.scene.control.skin.RadioButtonSkin;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;

public class CFRadioButtonSkin extends RadioButtonSkin {

    private StackPane stackPane;

    public CFRadioButtonSkin(CFRadioButton control) {
        super(control);
    }

    @Override
    protected void updateChildren() {
        super.updateChildren();
        stackPane = new StackPane();
        stackPane.setBackground(new Background(new BackgroundFill(Color.RED, null, null)));
        stackPane.getStyleClass().add("cf-radio");
        stackPane.setPrefSize(10, 10);
        stackPane.setMaxSize(10, 10);
        getChildren().setAll(stackPane);
    }

    public StackPane getStackPane() {
        return stackPane;
    }
}
