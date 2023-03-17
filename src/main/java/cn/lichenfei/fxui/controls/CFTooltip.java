package cn.lichenfei.fxui.controls;

import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.text.Font;

public class CFTooltip extends CFPopover {

    private Node ownerNode;

    public static void create(String text, Node ownerNode) {
        create(text, ownerNode, Position.BOTTOM_CENTER);
    }

    public static void create(String text, Node ownerNode, Position position) {
        CFTooltip cfTooltip = new CFTooltip(getLabel(text), ownerNode);
        cfTooltip.setPosition(position);
    }

    private CFTooltip(Label content, Node ownerNode) {
        super(content);
        this.ownerNode = ownerNode;
        initEvent();
        setAutoHide(false);
        setHideOnEscape(false);
    }

    private static Label getLabel(String text) {
        Label label = new Label(text);
        label.setFont(Font.font(12));
        label.setPadding(new Insets(5));
        label.setWrapText(true);
        return label;
    }

    private void initEvent() {
        //鼠标移入展示，鼠标移出隐藏
        ownerNode.hoverProperty().addListener((observableValue, aBoolean, t1) -> {
            if (t1) {
                super.show(ownerNode);
            } else {
                super.hide();
            }
        });
    }


    @Override
    protected void setShow() {
        TRANSITION_NODE.setOpacity(1);
    }

    @Override
    protected void setHide() {
        super.close();
    }
}
