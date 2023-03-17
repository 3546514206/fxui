package cn.lichenfei.fxui.controls;

import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

public class CFTooltip extends CFPopover {


    private Node ownerNode;
    private Effect effect;

    public static void create(String text, Node ownerNode) {
        create(text, ownerNode, Position.BOTTOM_CENTER);
    }

    public static void create(String text, Node ownerNode, Position position) {
        create(text, ownerNode, position, Effect.DARK);
    }

    public static void create(String text, Node ownerNode, Position position, Effect effect) {
        CFTooltip cfTooltip = new CFTooltip(text, ownerNode, effect);
        cfTooltip.setPosition(position);
    }

    private CFTooltip(String content, Node ownerNode, Effect effect) {
        super(new Label(content));
        this.ownerNode = ownerNode;
        this.effect = effect;
        initialize();
    }

    private void initialize() {
        initEvent();
        setAutoHide(false);
        setHideOnEscape(false);
        //style
        Label label = (Label) getMain();
        label.setFont(Font.font(12));
        label.setWrapText(true);
        label.setPadding(new Insets(5));
        container.setBackground(new Background(new BackgroundFill(effect.color, new CornerRadii(3), null)));
        arrow.setFill(effect.color);
        label.setTextFill(effect.textColor);
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

    public enum Effect {
        DARK(Color.valueOf("#303133"), Color.WHITE), LIGHT(Color.WHITE, Color.valueOf("#303133"));

        Effect(Color color, Color textColor) {
            this.color = color;
            this.textColor = textColor;
        }

        Color color;
        Color textColor;
    }

}
