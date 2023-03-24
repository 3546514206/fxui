package cn.lichenfei.fxui.controls.skin;

import cn.lichenfei.fxui.controls.CFRadioButton;
import com.sun.javafx.scene.control.behavior.BehaviorBase;
import com.sun.javafx.scene.control.behavior.ToggleButtonBehavior;

import javafx.animation.ScaleTransition;
import javafx.scene.control.RadioButton;
import javafx.scene.control.skin.LabeledSkinBase;
import javafx.scene.layout.StackPane;
import javafx.util.Duration;

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

    ScaleTransition scaleTransition;

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

    private StackPane createRadio() {
        StackPane radio = new StackPane();
        radio.getStyleClass().setAll("radio");
        StackPane region = new StackPane();
        region.getStyleClass().setAll("dot");
        radio.getChildren().setAll(region);

        // 测试：
        region.setScaleX(0);
        region.setScaleY(0);
        scaleTransition = new ScaleTransition(Duration.millis(200), region);
        CFRadioButton skinnable = getSkinnable();
        skinnable.selectedProperty().addListener((observableValue, aBoolean, t1) -> {
            scaleTransition.stop();
            scaleTransition.setToX(t1 ? 1 : 0);
            scaleTransition.setToY(t1 ? 1 : 0);
            scaleTransition.setFromX(t1 ? 0 : 1);
            scaleTransition.setFromY(t1 ? 0 : 1);
            scaleTransition.play();
        });
        return radio;
    }
}
