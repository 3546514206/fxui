package cn.lichenfei.fxui.controls;

import javafx.animation.FadeTransition;
import javafx.geometry.Bounds;
import javafx.geometry.Rectangle2D;
import javafx.scene.Node;
import javafx.stage.Screen;

import java.util.HashMap;
import java.util.Map;

/**
 * 气泡卡片
 */
public class CFPopover extends CFPopup {

    // 位置
    private Position position = Position.BOTTOM_LEFT;
    // 偏移量：距离所属节点的长度
    private double offset = 10;

    public CFPopover(Node main) {
        super(main);
        setAutoFix(false);
    }

    public void setPosition(Position position) {
        this.position = position;
    }

    @Override
    protected void setShow() {
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
    protected void setLocation(Bounds ownerNodeBounds) {
        resetPosition(ownerNodeBounds);
        /**
         * 因为存在DropShadow，所以要计算偏移量（请勿修改父类的DropShadow：offsetX、offsetY属性，可能会导致位置计算出现偏差，如需修改请自行实现）
         */
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
            case RIGHT_TOP -> {
                setX(ownerNodeBounds.getMaxX() - w + offset);
                setY(ownerNodeBounds.getMinY() - h);
            }
            case RIGHT_CENTER -> {
                setX(ownerNodeBounds.getMaxX() - w + offset);
                setY(ownerNodeBounds.getMinY() - h - container.getHeight() / 2 + ownerNodeBounds.getHeight() / 2);
            }
            case RIGHT_BOTTOM -> {
                setX(ownerNodeBounds.getMaxX() - w + offset);
                setY(ownerNodeBounds.getMinY() - h - container.getHeight() + ownerNodeBounds.getHeight());
            }
            case LEFT_TOP -> {
                setX(ownerNodeBounds.getMinX() - w - offset - container.getWidth());
                setY(ownerNodeBounds.getMinY() - h);
            }
            case LEFT_CENTER -> {
                setX(ownerNodeBounds.getMinX() - w - offset - container.getWidth());
                setY(ownerNodeBounds.getMinY() - h - container.getHeight() / 2 + ownerNodeBounds.getHeight() / 2);
            }
            case LEFT_BOTTOM -> {
                setX(ownerNodeBounds.getMinX() - w - offset - container.getWidth());
                setY(ownerNodeBounds.getMinY() - h - container.getHeight() + ownerNodeBounds.getHeight());
            }
            default -> {
            }
        }
    }

    /****************************************************************/

    private static Map<Position, Position> opposite = new HashMap<>();

    static {
        //初始化相反位置
        opposite.put(Position.BOTTOM_LEFT, Position.TOP_LEFT);
        opposite.put(Position.BOTTOM_CENTER, Position.TOP_CENTER);
        opposite.put(Position.BOTTOM_RIGHT, Position.TOP_RIGHT);
        opposite.put(Position.LEFT_TOP, Position.RIGHT_TOP);
        opposite.put(Position.LEFT_CENTER, Position.RIGHT_CENTER);
        opposite.put(Position.LEFT_BOTTOM, Position.RIGHT_BOTTOM);
        //相反方向遍历重新加一遍
        Map<Position, Position> putMap = new HashMap<>();
        opposite.forEach((k, v) -> putMap.put(v, k));
        opposite.putAll(putMap);
    }

    /**
     * 重置位置：（可能因为当前位置不能完全显示气泡卡片，所以需要向相反方向显示）
     */
    private void resetPosition(Bounds ownerNodeBounds) {
        Rectangle2D visualBounds = Screen.getPrimary().getVisualBounds();//获取主屏幕的可视边界
        boolean reset = false;
        switch (this.position) {
            //主要检测下侧是否能完全展示组件
            case BOTTOM_LEFT, BOTTOM_CENTER, BOTTOM_RIGHT -> reset = visualBounds.getMaxY() < getHeight() + ownerNodeBounds.getMaxY();
            //主要检测上侧是否能完全展示组件
            case TOP_LEFT, TOP_CENTER, TOP_RIGHT -> reset = getHeight() > ownerNodeBounds.getMinY();
            //主要检测右侧是否能完全展示组件
            case RIGHT_TOP, RIGHT_CENTER, RIGHT_BOTTOM -> reset = visualBounds.getMaxX() < getWidth() + ownerNodeBounds.getMaxX();
            //主要检测左侧是否能完全展示组件
            case LEFT_TOP, LEFT_CENTER, LEFT_BOTTOM -> reset = getWidth() > ownerNodeBounds.getMinX();
            default -> {
            }
        }
        if (reset) {
            this.position = opposite.get(this.position);
        }
    }

    @Override
    protected void popupMove() {
        // 不支持拖动
    }

    public enum Position {
        BOTTOM_LEFT, BOTTOM_CENTER, BOTTOM_RIGHT,
        TOP_LEFT, TOP_CENTER, TOP_RIGHT,
        RIGHT_TOP, RIGHT_CENTER, RIGHT_BOTTOM,
        LEFT_TOP, LEFT_CENTER, LEFT_BOTTOM
    }

}