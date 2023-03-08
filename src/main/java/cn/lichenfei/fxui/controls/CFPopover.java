package cn.lichenfei.fxui.controls;

import javafx.animation.FadeTransition;
import javafx.geometry.Bounds;
import javafx.scene.Node;

/**
 * 气泡卡片
 */
public class CFPopover extends CFPopup {

    private double offset = 8;

    public CFPopover(Node main) {
        super(main);
    }

    @Override
    protected void setShow(Bounds ownerNodeBounds) {
        double height = getHeight();
        double width = getWidth();
        Bounds layoutBounds = container.getLayoutBounds();
        double w = (layoutBounds.getWidth() - width) / 2;
        double h = (layoutBounds.getHeight() - height) / 2;
        // 设置初始位置
        setAnchorX(ownerNodeBounds.getMinX() + w);
        setAnchorY(ownerNodeBounds.getMaxY() + h);
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
}