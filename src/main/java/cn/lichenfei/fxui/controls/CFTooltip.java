package cn.lichenfei.fxui.controls;

import javafx.scene.Node;
import javafx.scene.control.Label;

public class CFTooltip extends CFPopover {

    private Node ownerNode;

    public static void create(String content, Node ownerNode) {
        create(content, ownerNode, Position.BOTTOM_CENTER);
    }

    public static void create(String content, Node ownerNode, Position position) {
        CFTooltip cfTooltip = new CFTooltip(content, ownerNode);
        cfTooltip.setPosition(position);
    }

    private CFTooltip(String content, Node ownerNode) {
        super(new Label(content));
        this.ownerNode = ownerNode;
        initEvent();
        setAutoHide(false);
        setHideOnEscape(false);
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
