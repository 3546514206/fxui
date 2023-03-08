package cn.lichenfei.fxui.controls;

import javafx.animation.FadeTransition;
import javafx.geometry.Bounds;
import javafx.scene.Node;

/**
 * 气泡卡片
 */
public class CFPopover extends CFPopup {

    // 位置
    private Position position = Position.BOTTOM_LEFT;
    // 偏移量
    private double offset = 10;

    public CFPopover(Node main) {
        super(main);
    }

    public void setPosition(Position position) {
        this.position = position;
    }

    @Override
    protected void setShow(Bounds ownerNodeBounds) {
        setLocation(ownerNodeBounds);
        // 设置动画属性
        FT = new FadeTransition(duration, TRANSITION_NODE);
        FT.setFromValue(0);
        FT.setToValue(1);
        FT.play();
        FT.setOnFinished(actionEvent -> {
        });
    }

    @Override
    protected void setHide() {
        // 设置动画属性
        FT = new FadeTransition(duration, TRANSITION_NODE);
        FT.setFromValue(1);
        FT.setToValue(0);
        FT.play();
        FT.setOnFinished(actionEvent -> super.close());
    }

    @Override
    protected void popupMove() {
        // 不支持拖动
    }

    /**
     * 设置气泡卡片显示的位置
     */
    private void setLocation(Bounds ownerNodeBounds) {
        //因为存在DropShadow，所以要计算偏移量
        double w = (getHeight() - container.getWidth()) / 2;
        double h = (getWidth() - container.getWidth()) / 2;
        // 根据不同的Pos设置要显示位置
        switch (position) {
            case BOTTOM_LEFT -> {
                setX(ownerNodeBounds.getMinX() - w);
                setY(ownerNodeBounds.getMaxY() - h + offset);
            }
            case BOTTOM_CENTER -> {
                setX(ownerNodeBounds.getMinX() - w - container.getWidth() / 2 + ownerNodeBounds.getWidth() / 2);
                setY(ownerNodeBounds.getMaxY() - h + offset);
            }
            case BOTTOM_RIGHT -> {
                setX(ownerNodeBounds.getMinX() - w - container.getWidth() + ownerNodeBounds.getWidth());
                setY(ownerNodeBounds.getMaxY() - h + offset);
            }
            case TOP_LEFT -> {
                setX(ownerNodeBounds.getMinX() - w);
                setY(ownerNodeBounds.getMinY() - h - offset - container.getHeight());
            }
            case TOP_CENTER -> {
                setX(ownerNodeBounds.getMinX() - w - container.getWidth() / 2 + ownerNodeBounds.getWidth() / 2);
                setY(ownerNodeBounds.getMinY() - h - offset - container.getHeight());
            }
            case TOP_RIGHT -> {
                setX(ownerNodeBounds.getMinX() - w - container.getWidth() + ownerNodeBounds.getWidth());
                setY(ownerNodeBounds.getMinY() - h - offset - container.getHeight());
            }
        }
    }

    public enum Position {
        BOTTOM_LEFT, BOTTOM_CENTER, BOTTOM_RIGHT,
        TOP_LEFT, TOP_CENTER, TOP_RIGHT,
        RIGHT_TOP, RIGHT_CENTER, RIGHT_BOTTOM,
        LEFT_TOP, LEFT_CENTER, LEFT_BOTTOM
    }

}