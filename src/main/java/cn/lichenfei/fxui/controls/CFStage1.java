package cn.lichenfei.fxui.controls;

import cn.lichenfei.fxui.common.FxUtil;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.effect.BlurType;
import javafx.scene.effect.DropShadow;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import org.silentsoft.ui.util.StageDragResizer;

public class CFStage1 extends Stage {

    private static final String STYLE_SHEET = FxUtil.getResource("/css/cf-stage.css");
    private double padding = 10;
    private DoubleProperty arc = new SimpleDoubleProperty(6); // 窗口圆角程度
    private double height = 650;
    private double width = 1100;

    private StackPane content = new StackPane(new StackPane()); // 内容区域
    private StackPane backdrop = new StackPane(content); // 背景区域，为了展示阴影效果
    private StackPane root = new StackPane(backdrop); // 根节点
    private Scene scene = new Scene(root);

    public CFStage1() {
        initializeStyle();
        stageMove();
        StageDragResizer.makeResizable(this, this.root, 10, 5);// 窗口拖动缩放
    }

    public CFStage1(Node node) {
        setContent(node);
        initializeStyle();
        stageMove();
        StageDragResizer.makeResizable(this, this.root, 10, 5);// 窗口拖动缩放
    }

    public CFStage1(Node node, double height, double width) {
        setContent(node);
        this.height = height;
        this.width = width;
        initializeStyle();
        stageMove();
        StageDragResizer.makeResizable(this, this.root, 10, 5);// 窗口拖动缩放
    }

    public CFStage1 setArc(double arc) {
        this.arc.set(arc);
        return this;
    }

    public CFStage1 setContent(Node content) {
        this.content.getChildren().set(0, content);
        return this;
    }

    private void initializeStyle() {
        initStyle(StageStyle.TRANSPARENT); // 修改窗口样式
        scene.setFill(null);
        scene.getStylesheets().add(STYLE_SHEET);// 加载css样式
        setScene(scene);
        root.setBackground(new Background(new BackgroundFill(Color.TRANSPARENT, null, null)));
        content.setBackground(new Background(new BackgroundFill(Color.WHITE, null, null))); // 窗口默认颜色
        //裁剪为圆角
        Rectangle rectangle = new Rectangle();
        rectangle.widthProperty().bind(content.widthProperty());
        rectangle.heightProperty().bind(content.heightProperty());
        rectangle.arcHeightProperty().bind(arc);
        rectangle.arcWidthProperty().bind(arc);
        content.setClip(rectangle);
        //显示阴影效果
        backdrop.setEffect(new DropShadow(BlurType.THREE_PASS_BOX, Color.rgb(0, 0, 0, 0.8), 10, 0, 1.5, 1.5));
        this.root.setPrefHeight(height + padding * 2);
        this.root.setPrefWidth(width + padding * 2);
        this.root.setPadding(new Insets(padding));
    }

    /**************************************************** 窗口拖动 ****************************************************/

    private double xOffset;
    private double yOffset;

    private void stageMove() {
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
