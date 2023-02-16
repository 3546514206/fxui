package cn.lichenfei.fxui.controls;

import javafx.animation.*;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Arc;
import javafx.scene.text.Font;
import javafx.util.Duration;

public class CFLoading extends StackPane {

    // 大小
    private Size defaultSize;

    // 动画相关
    private Button TRANSITION_NODE = new Button();
    private TranslateTransition TT = new TranslateTransition();
    private RotateTransition RT = new RotateTransition();
    private ParallelTransition PT = new ParallelTransition(TT, RT);

    // 加载圈
    private Arc arc1;
    private Arc arc2;

    // 布局
    private Pane arcPane = new Pane();
    private Label message = new Label("Loading...");

    public CFLoading(CFLoading.Size size) {
        this.defaultSize = size;
        setLayout();
        setAnimation();
    }

    public CFLoading setMessage(String message) {
        this.message.setText(message);
        return this;
    }

    public CFLoading setColor(Color color) {
        arc2.setStroke(color);
        return this;
    }

    public CFLoading setBackColor(Color color) {
        arc1.setStroke(color);
        return this;
    }

    // 布局
    private void setLayout() {
        setMaxSize(USE_PREF_SIZE, USE_PREF_SIZE);
        switch (defaultSize) {
            case SMALL:
                this.getChildren().addAll(this.arcPane);
                break;
            case NORMAL:
                VBox vBox = new VBox();
                vBox.setSpacing(10);
                vBox.setAlignment(Pos.CENTER);
                vBox.getChildren().addAll(this.arcPane, this.message);
                this.arcPane.setMaxWidth(Double.NEGATIVE_INFINITY);
                this.getChildren().addAll(vBox);
                break;
            case LARGE:
                this.getChildren().addAll(this.arcPane, this.message);
                break;
            default:
                break;
        }
        this.arc1 = getArc(360, Color.valueOf("#dedfe0"));
        this.arc2 = getArc(50, Color.valueOf("#409EFF"));
        this.arcPane.getChildren().addAll(arc1, arc2);
        this.arcPane.setPrefWidth(defaultSize.getSize());
        this.arcPane.setPrefHeight(defaultSize.getSize());
        this.message.setFont(new Font(14));
        this.message.setTextFill(Color.valueOf("#409EFF"));
    }

    private Arc getArc(double arcLength, Color color) {
        double size = this.defaultSize.getSize() / 2;
        Arc arc = new Arc(size, size, size, size, 0, arcLength);
        arc.setFill(null);
        arc.setStrokeWidth(this.defaultSize.getStrokeWidth());
        arc.setStroke(color);
        return arc;
    }

    /**
     * 动画
     */
    private void setAnimation() {
        //动画监听
        arc2.lengthProperty().bind(TRANSITION_NODE.translateXProperty());
        arc2.startAngleProperty().bind(TRANSITION_NODE.rotateProperty());

        //移动动画
        TT.setNode(TRANSITION_NODE);
        TT.setFromX(270);
        TT.setToX(0);
        TT.setDuration(Duration.millis(1000));
        TT.setInterpolator(Interpolator.LINEAR);
        TT.setAutoReverse(true);
        TT.setCycleCount(Timeline.INDEFINITE);
        //旋转动画
        RT.setNode(TRANSITION_NODE);
        RT.setFromAngle(360);
        RT.setToAngle(0);
        RT.setDuration(Duration.millis(700));
        RT.setInterpolator(Interpolator.LINEAR);
        RT.setCycleCount(Timeline.INDEFINITE);
        //
        PT.play();
    }

    // 加载容器大小
    public enum Size {
        LARGE(80, 4),
        NORMAL(50, 3),
        SMALL(15, 2);

        Size(double size, double strokeWidth) {
            this.size = size;
            this.strokeWidth = strokeWidth;
        }

        private double size;
        private double strokeWidth;

        public double getSize() {
            return size;
        }

        public double getStrokeWidth() {
            return strokeWidth;
        }
    }
}
