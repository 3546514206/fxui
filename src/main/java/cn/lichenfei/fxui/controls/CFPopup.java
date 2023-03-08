package cn.lichenfei.fxui.controls;

import cn.lichenfei.fxui.common.FxUtil;
import javafx.animation.FadeTransition;
import javafx.animation.ParallelTransition;
import javafx.animation.TranslateTransition;
import javafx.event.ActionEvent;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.effect.BlurType;
import javafx.scene.effect.DropShadow;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.Popup;
import javafx.stage.Window;
import javafx.util.Duration;

import java.util.function.Consumer;

public class CFPopup extends Popup {

    private double centerX = 0;
    private double centerY = 0;
    private DropShadow dropShadow = new DropShadow(BlurType.THREE_PASS_BOX, Color.rgb(0, 0, 0, 0.4), 10, 0, 0, 0);
    //
    private StackPane content;

    public CFPopup(Node main) {
        content = new StackPane(main);
        initialize();
    }

    /**
     * 显示Popup
     *
     * @param owner
     */
    public void show(Node owner) {
        Window window = FxUtil.getWindow(owner);
        centerX = window.getX() + (window.getWidth() / 2);
        centerY = window.getY() + (window.getHeight() / 2);
        if (!isShowing()) {
            super.show(window);
        }
    }

    private void initialize() {
        getContent().add(content);
        content.setBackground(new Background(new BackgroundFill(Color.WHITE, new CornerRadii(3), null)));
        content.setEffect(dropShadow);
        //窗口显示之后
        setOnShown(windowEvent -> {
            setAnchorX(centerX - getWidth() / 2);
            setAnchorY(centerY - getHeight() / 2);
            setAnimationInfoAndPlay(true, actionEvent -> {

            });
        });
        popupMove();
        setAutoHide(true); // 自动隐藏：FocusUngrabEvent
        setHideOnEscape(true); // 按Esc关闭
        //动画属性绑定
        setOpacity(0);// 透明度
        opacityProperty().bind(TRANSITION_NODE.opacityProperty());
        TRANSITION_NODE.translateYProperty().addListener((observableValue, number, t1) -> setAnchorY(t1.doubleValue()));
    }

    /**
     * 重写hide方法，动画执行后调用hide。
     */
    @Override
    public void hide() {
        if (!getOwnerWindow().isShowing()) { // 所属窗口不存在了直接关闭
            super.hide();
            return;
        }
        if (isShowing()) {
            setAnimationInfoAndPlay(false, actionEvent -> super.hide());
        }
    }

    //动画
    private Button TRANSITION_NODE = new Button();
    private TranslateTransition TT = new TranslateTransition(Duration.millis(300));
    private FadeTransition FT = new FadeTransition(Duration.millis(300));
    private ParallelTransition PT = new ParallelTransition(TRANSITION_NODE, TT, FT);

    /**
     * 设置动画属性，并且开启动画。
     */
    private void setAnimationInfoAndPlay(boolean show, Consumer<ActionEvent> consumer) {
        double anchorY = getAnchorY();
        TT.setFromY(show ? anchorY - 100 : anchorY);
        TT.setToY(show ? anchorY : anchorY - 100);
        FT.setFromValue(show ? 0 : 1);
        FT.setToValue(show ? 1 : 0);
        PT.play();
        PT.setOnFinished(actionEvent -> consumer.accept(actionEvent));
    }

    /**************************************************** popup拖动 ****************************************************/

    private double xOffset;
    private double yOffset;

    private void popupMove() {
        this.content.setOnMousePressed(event -> {
            event.consume();
            this.xOffset = this.getX() - event.getScreenX();
            this.yOffset = this.getY() - event.getScreenY();
        });
        this.content.setOnMouseDragged(event -> {
            event.consume();
            this.setX(event.getScreenX() + this.xOffset);
            this.setY(event.getScreenY() + this.yOffset);
        });
    }

}
