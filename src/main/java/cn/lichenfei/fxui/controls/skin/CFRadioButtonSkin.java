package cn.lichenfei.fxui.controls.skin;

import cn.lichenfei.fxui.controls.CFRadioButton;
import com.sun.javafx.scene.control.behavior.BehaviorBase;
import com.sun.javafx.scene.control.behavior.ToggleButtonBehavior;

import javafx.scene.control.RadioButton;
import javafx.scene.control.skin.LabeledSkinBase;
import javafx.scene.layout.StackPane;

public class CFRadioButtonSkin extends LabeledSkinBase<CFRadioButton> {

    private StackPane radio;
    private final BehaviorBase<RadioButton> behavior;


    public CFRadioButtonSkin(CFRadioButton control) {
        super(control);
        behavior = new ToggleButtonBehavior<>(control);
        radio = createRadio();
        updateChildren();
    }

    @Override
    public void dispose() {
        super.dispose();
        if (behavior != null) {
            behavior.dispose();
        }
    }

    @Override
    protected void updateChildren() {
        super.updateChildren();
        if (radio != null) {
            getChildren().add(radio);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void layoutChildren(final double x, final double y,
                                  final double w, final double h) {
        System.out.println(x + " - " + y + " - " + w + " - " + h);
        CFRadioButton cfRadioButton = getSkinnable();
        radio.resize(w, h);
    }

    private static StackPane createRadio() {
        StackPane radio = new StackPane();
        radio.getStyleClass().setAll("radio");
        StackPane region = new StackPane();
        region.getStyleClass().setAll("dot");
        radio.getChildren().setAll(region);
        return radio;
    }
}
