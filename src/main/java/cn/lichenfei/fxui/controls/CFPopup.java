package cn.lichenfei.fxui.controls;

import cn.lichenfei.fxui.common.FxUtil;
import javafx.animation.Animation;
import javafx.animation.FadeTransition;
import javafx.animation.ParallelTransition;
import javafx.animation.TranslateTransition;
import javafx.geometry.Bounds;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.effect.BlurType;
import javafx.scene.effect.DropShadow;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.Popup;
import javafx.stage.Window;
import javafx.util.Duration;

public class CFPopup extends Popup {

    private DropShadow dropShadow = new DropShadow(BlurType.THREE_PASS_BOX, Color.rgb(0, 0, 0, 0.3), 10, 0, 0, 0);
    //
    protected StackPane container;

    public CFPopup(Node main) {
        container = new StackPane(main);
        initialize();
    }

    //所属节点
    private Node ownerNode;

    /**
     * 显示Popup
     *
     * @param ownerNode
     */
    public void show(Node ownerNode) {
        this.ownerNode = ownerNode;
        Window window = FxUtil.getWindow(ownerNode);
        if (!isShowing()) {
            super.show(window);
        }
    }

    private void initialize() {
        getContent().add(container);
        container.setBackground(new Background(new BackgroundFill(Color.WHITE, new CornerRadii(3), null)));
        container.setEffect(dropShadow);
        //窗口显示之后
        setOnShown(windowEvent -> {
            Bounds ownerNodeBounds = this.ownerNode.localToScreen(this.ownerNode.getLayoutBounds());
            setLocation(ownerNodeBounds);
            setShow();
        });
        popupMove();
        setAutoHide(true); // 自动隐藏：FocusUngrabEvent
        setHideOnEscape(true); // 按Esc关闭
        //动画属性绑定
        opacityProperty().bind(TRANSITION_NODE.opacityProperty());
        TRANSITION_NODE.translateYProperty().addListener((observableValue, number, t1) -> setY(t1.doubleValue()));
        TRANSITION_NODE.setOpacity(0);// 透明度
    }

    //动画相关
    protected Duration duration = Duration.millis(300);
    protected Button TRANSITION_NODE = new Button();
    protected TranslateTransition TT;
    protected FadeTransition FT;
    protected Animation PT;

    /**
     * 窗口展示后调用的方法
     */
    protected void setShow() {
        double anchorY = getAnchorY();
        // 设置动画属性
        TT = new TranslateTransition(duration);
        FT = new FadeTransition(duration);
        PT = new ParallelTransition(TRANSITION_NODE, TT, FT);
        TT.setFromY(anchorY - 100);
        TT.setToY(anchorY);
        FT.setFromValue(0);
        FT.setToValue(1);
        PT.play();
        PT.setOnFinished(actionEvent -> {
        });
    }

    /**
     * 窗口隐藏调用的方法
     */
    protected void setHide() {
        double anchorY = getAnchorY();
        TT.setFromY(anchorY);
        TT.setToY(anchorY + 100);
        FT.setFromValue(1);
        FT.setToValue(0);
        PT.play();
        PT.setOnFinished(actionEvent -> close());// 真正的隐藏
    }

    /**
     * 设置气泡卡片显示的位置
     */
    protected void setLocation(Bounds ownerNodeBounds) {
        Window window = getOwnerWindow();
        double centerX = window.getX() + (window.getWidth() / 2);
        double centerY = window.getY() + (window.getHeight() / 2);
        // 设置初始位置
        setX(centerX - getWidth() / 2);
        setY(centerY - getHeight() / 2);
    }

    /**
     * 重写hide方法，动画执行后调用hide。
     */
    @Override
    public void hide() {
        if (!getOwnerWindow().isShowing()) { // 所属窗口不存在了直接关闭
            close();
            return;
        }
        if (isShowing()) {
            setHide();
        }
    }

    protected void close() {
        super.hide();
    }


    /**************************************************** popup拖动 ****************************************************/

    private double xOffset;
    private double yOffset;

    protected void popupMove() {
        this.container.setOnMousePressed(event -> {
            event.consume();
            this.xOffset = this.getX() - event.getScreenX();
            this.yOffset = this.getY() - event.getScreenY();
        });
        this.container.setOnMouseDragged(event -> {
            event.consume();
            this.setX(event.getScreenX() + this.xOffset);
            this.setY(event.getScreenY() + this.yOffset);
        });
    }

}
