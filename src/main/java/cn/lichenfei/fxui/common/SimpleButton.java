package cn.lichenfei.fxui.common;

import javafx.collections.ObservableList;
import javafx.scene.control.Button;

public class SimpleButton {

    public static Button get(String text) {
        return getButton(text, Level.PRIMARY.getStyleClass());
    }

    public static Button get(String text, Level level) {
        return getButton(text, level.getStyleClass());
    }

    public static Button get(String text, Level level, boolean round) {
        Button button = getButton(text, CFStyleClass.BUT, level.getStyleClass());
        if (round) {
            button.getStyleClass().add(CFStyleClass.ROUND_BUT);
        }
        return button;
    }

    public static Button get(String text, Level level, boolean circle, boolean plain) {
        Button button = getButton(text, CFStyleClass.BUT, level.getStyleClass());
        if (circle) {
            button.getStyleClass().add(CFStyleClass.ROUND_BUT);
        }
        if (plain) {
            button.getStyleClass().add(CFStyleClass.PLAIN_BUT);
        }
        return button;
    }

    private static Button getButton(String text, String... styleClass) {
        Button button = new Button(text);
        ObservableList<String> buttonStyleClass = button.getStyleClass();
        buttonStyleClass.add(CFStyleClass.BUT);
        buttonStyleClass.addAll(styleClass);
        return button;
    }
}
