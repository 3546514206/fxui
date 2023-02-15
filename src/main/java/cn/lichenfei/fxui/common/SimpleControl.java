package cn.lichenfei.fxui.common;

import javafx.scene.Node;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.PopupControl;
import javafx.scene.control.Tooltip;
import javafx.util.Duration;


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
        setStyleClass(label, getStyleClass(labelEnum.name()));
        return label;
    }

    public enum LabelEnum {
        H1, H2, H3, H4, H5, H6, TEXT_DEFAULT, TEXT_SMALL
    }

    /********************************* Hyperlink *********************************/

    private static final String HYPERLINK_STYLE_CLASS = "cf-link";

    public static Hyperlink getHyperlink(String text) {
        return getHyperlink(text, null);
    }

    public static Hyperlink getHyperlink(String text, Level level) {
        Hyperlink hyperlink = new Hyperlink(text);
        if (level == null) {
            setStyleClass(hyperlink, HYPERLINK_STYLE_CLASS);
        } else {
            setStyleClass(hyperlink, HYPERLINK_STYLE_CLASS, getStyleClass(level.name()));
        }
        return hyperlink;
    }

    /********************************* Tooltip *********************************/

    private static final String TOOLTIP_STYLE_CLASS = "cf-tooltip";

    public static Tooltip getTooltip(String text) {
        return getTooltip(text, TooltipEnum.LIGHT);
    }

    public static Tooltip getTooltip(String text, TooltipEnum tooltipEnum) {
        Tooltip tooltip = new Tooltip(text);
        tooltip.setShowDelay(Duration.millis(200));
        setStyleClass(tooltip, TOOLTIP_STYLE_CLASS, getStyleClass(tooltipEnum.name()));
        return tooltip;
    }

    public enum TooltipEnum {
        DARK, LIGHT
    }


    public enum Level {
        PRIMARY,
        SUCCESS,
        INFO,
        WARN,
        DANGER
    }

    public static String getStyleClass(String enumName) {
        return enumName.toLowerCase().replaceAll("_", "-");
    }

    private static void setStyleClass(Node node, String... styleClass) {
        node.getStyleClass().addAll(styleClass);
    }

    private static void setStyleClass(PopupControl popup, String... styleClass) {
        popup.getStyleClass().addAll(styleClass);
    }
}
