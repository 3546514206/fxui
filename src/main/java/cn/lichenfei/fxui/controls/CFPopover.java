package cn.lichenfei.fxui.controls;

import javafx.animation.FadeTransition;
import javafx.geometry.Bounds;
import javafx.geometry.Pos;
import javafx.geometry.Rectangle2D;
import javafx.scene.Node;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Polygon;
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
    private double offset = 5;

    public CFPopover(Node main) {
        super(main);
        setAutoFix(false);
        initArrow();
    }

    public void setPosition(Position position) {
        this.position = position;
    }

    @Override
    protected void setShow() {
        // 设置动画属性
        if (FT == null) {
            FT = new FadeTransition(duration, TRANSITION_NODE);
        }
        FT.setFromValue(0);
        FT.setToValue(1);
        FT.play();
        FT.setOnFinished(actionEvent -> {
        });
    }

    @Override
    protected void setHide() {
        // 设置动画属性
        FT.setFromValue(1);
        FT.setToValue(0);
        FT.play();
        FT.setOnFinished(actionEvent -> super.close());
    }

    @Override
    protected void setLocation(Bounds ownerNodeBounds) {
        resetPosition(ownerNodeBounds);
        setArrowLocation(ownerNodeBounds);// 设置箭头位置
        /**
         * 因为存在DropShadow，所以要计算偏移量（请勿修改父类的DropShadow：offsetX、offsetY属性，可能会导致位置计算出现偏差，如需修改请自行实现）
         */
        Bounds bounds = container.localToScene(container.getLayoutBounds());
        // 根据不同的Pos设置要显示位置
        switch (this.position) {
            case BOTTOM_LEFT, BOTTOM_CENTER, BOTTOM_RIGHT -> setY(ownerNodeBounds.getMaxY() - bounds.getMinX() + offset);
            case TOP_LEFT, TOP_CENTER, TOP_RIGHT -> setY(ownerNodeBounds.getMinY() - bounds.getMaxY() - offset - arrowSize);
            case RIGHT_TOP, RIGHT_CENTER, RIGHT_BOTTOM -> setX(ownerNodeBounds.getMaxX() - bounds.getMinY() + offset);
            case LEFT_TOP, LEFT_CENTER, LEFT_BOTTOM -> setX(ownerNodeBounds.getMinX() - bounds.getMaxX() - offset - arrowSize);
            default -> {
            }
        }
        switch (this.position) {
            case BOTTOM_LEFT, TOP_LEFT -> setX(ownerNodeBounds.getMinX() - bounds.getMinX());
            case BOTTOM_CENTER, TOP_CENTER -> setX(ownerNodeBounds.getMinX() - bounds.getMinX() - container.getWidth() / 2 + ownerNodeBounds.getWidth() / 2);
            case BOTTOM_RIGHT, TOP_RIGHT -> setX(ownerNodeBounds.getMaxX() - bounds.getMaxX());
            case RIGHT_TOP, LEFT_TOP -> setY(ownerNodeBounds.getMinY() - bounds.getMinY());
            case RIGHT_CENTER, LEFT_CENTER -> setY(ownerNodeBounds.getMinY() - bounds.getMinY() - container.getHeight() / 2 + ownerNodeBounds.getHeight() / 2);
            case RIGHT_BOTTOM, LEFT_BOTTOM -> setY(ownerNodeBounds.getMaxY() - bounds.getMaxY());
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

    /**************************************************************************/

    private double arrowSize = 6;
    protected Polygon arrow = new Polygon();

    private void initArrow() {
        arrow.setFill(Color.WHITE);
        container.getChildren().add(arrow);
    }

    /**
     * 设置箭头
     *
     * @param ownerNodeBounds
     */
    private void setArrowLocation(Bounds ownerNodeBounds) {
        arrow.getPoints().clear();
        // 默认位置
        switch (this.position) {
            case BOTTOM_LEFT, BOTTOM_CENTER, BOTTOM_RIGHT -> {
                arrow.getPoints().addAll(0D, arrowSize, arrowSize, 0D, arrowSize * 2, arrowSize);
                StackPane.setAlignment(arrow, Pos.TOP_LEFT);
                arrow.setTranslateY(-arrowSize);
                if (container.getWidth() < ownerNodeBounds.getWidth()) {
                    arrow.setTranslateX(container.getWidth() / 2 - arrowSize);
                    return;
                }
            }
            case TOP_LEFT, TOP_CENTER, TOP_RIGHT -> {
                arrow.getPoints().addAll(0D, 0D, arrowSize * 2, 0D, arrowSize, arrowSize);
                StackPane.setAlignment(arrow, Pos.BOTTOM_LEFT);
                arrow.setTranslateY(+arrowSize);
                if (container.getWidth() < ownerNodeBounds.getWidth()) {
                    arrow.setTranslateX(container.getWidth() / 2 - arrowSize);
                    return;
                }
            }
            case RIGHT_TOP, RIGHT_CENTER, RIGHT_BOTTOM -> {
                arrow.getPoints().addAll(arrowSize, 0D, 0D, arrowSize, arrowSize, arrowSize * 2);
                StackPane.setAlignment(arrow, Pos.TOP_LEFT);
                arrow.setTranslateX(-arrowSize);
                if (container.getHeight() < ownerNodeBounds.getHeight()) {
                    arrow.setTranslateY(container.getHeight() / 2 - arrowSize);
                    return;
                }
            }
            case LEFT_TOP, LEFT_CENTER, LEFT_BOTTOM -> {
                arrow.getPoints().addAll(0D, 0D, arrowSize, arrowSize, 0D, arrowSize * 2);
                StackPane.setAlignment(arrow, Pos.TOP_RIGHT);
                arrow.setTranslateX(arrowSize);
                if (container.getHeight() < ownerNodeBounds.getHeight()) {
                    arrow.setTranslateY(container.getHeight() / 2 - arrowSize);
                    return;
                }
            }
            default -> {
            }
        }
        switch (this.position) {
            case BOTTOM_CENTER, TOP_CENTER -> arrow.setTranslateX(container.getWidth() / 2 - arrowSize);
            case LEFT_CENTER, RIGHT_CENTER -> arrow.setTranslateY(container.getHeight() / 2 - arrowSize);
            case BOTTOM_LEFT, TOP_LEFT -> arrow.setTranslateX(ownerNodeBounds.getWidth() / 2 - arrowSize);
            case BOTTOM_RIGHT, TOP_RIGHT -> arrow.setTranslateX(container.getWidth() - ownerNodeBounds.getWidth() / 2 - arrowSize);
            case RIGHT_TOP, LEFT_TOP -> arrow.setTranslateY(ownerNodeBounds.getHeight() / 2 - arrowSize);
            case RIGHT_BOTTOM, LEFT_BOTTOM -> arrow.setTranslateY(container.getHeight() - ownerNodeBounds.getHeight() / 2 - arrowSize);
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