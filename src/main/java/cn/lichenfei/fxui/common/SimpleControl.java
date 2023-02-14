package cn.lichenfei.fxui.common;

import javafx.scene.Node;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;


/**
 * 常用简单组件
 */
public class SimpleControl {

    /********************************* Label *********************************/

    public static Label getLabel(String text) {
        return getLabel(text, LabelEnum.TEXT_DEFAULT);
    }

    public static Label getLabel(String text, LabelEnum labelEnum) {
        Label label = new Label(text);
        setStyleClass(label, labelEnum.getStyleClass());
        return label;
    }

    public enum LabelEnum {
        H1, H2, H3, H4, H5, H6, TEXT_DEFAULT, TEXT_SMALL;

        public String getStyleClass() {
            return name().toLowerCase().replaceAll("_", "-");
        }
    }

    /********************************* Hyperlink *********************************/

    private static final String HYPERLINK_STYLE_CLASS = "cf-link";

    public static Hyperlink getHyperlink(String text) {
        Hyperlink hyperlink = new Hyperlink(text);
        setStyleClass(hyperlink, HYPERLINK_STYLE_CLASS);
        return hyperlink;
    }

    public static Hyperlink getHyperlink(String text, Level level) {
        Hyperlink hyperlink = new Hyperlink(text);
        setStyleClass(hyperlink, HYPERLINK_STYLE_CLASS, level.getStyleClass());
        return hyperlink;
    }


    public enum Level {
        PRIMARY,
        SUCCESS,
        INFO,
        WARN,
        DANGER;
        public String getStyleClass() {
            return name().toLowerCase();
        }

    }


    private static void setStyleClass(Node node, String... styleClass) {
        node.getStyleClass().addAll(styleClass);
    }

}
