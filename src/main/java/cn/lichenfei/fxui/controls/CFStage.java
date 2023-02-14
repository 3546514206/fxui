package cn.lichenfei.fxui.controls;

import javafx.beans.property.*;
import javafx.geometry.Insets;
import javafx.geometry.Rectangle2D;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.effect.BlurType;
import javafx.scene.effect.DropShadow;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import org.silentsoft.ui.util.StageDragResizer;

public class CFStage extends Stage {

    private ObjectProperty<Insets> insets = new SimpleObjectProperty<>(new Insets(10));
    private DoubleProperty arc = new SimpleDoubleProperty(6); // 窗口圆角程度
    private BooleanProperty maximize = new SimpleBooleanProperty(false);
    private double height = 650;
    private double width = 1100;
    private Rectangle2D stageBounds;

    private BorderPane content = new BorderPane(); // 内容区域
    private StackPane backdrop = new StackPane(content); // 背景区域，为了展示阴影效果
    private StackPane root = new StackPane(backdrop); // 根节点
    private Scene scene = new Scene(root);

    //
    private CFHeader cfHeader = new CFHeader(this);

    public CFStage() {
        initialize();
        stageMove();
        StageDragResizer.makeResizable(this, this.root, 10, 5);// 窗口拖动缩放
    }

    public CFStage(Node node) {
        setContent(node);
        initialize();
        stageMove();
        StageDragResizer.makeResizable(this, this.root, 10, 5);// 窗口拖动缩放
    }

    public CFStage(Node node, double height, double width) {
        setContent(node);
        this.height = height;
        this.width = width;
        initialize();
        stageMove();
        StageDragResizer.makeResizable(this, this.root, 10, 5);// 窗口拖动缩放
    }

    public CFStage setArc(double arc) {
        this.arc.set(arc);
        return this;
    }

    public CFStage setContent(Node content) {
        this.content.setCenter(content);
        return this;
    }

    private void initialize() {
        initStyle(StageStyle.TRANSPARENT); // 修改窗口样式
        scene.setFill(null);
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
        this.root.setPrefHeight(height + insets.get().getBottom() * 2);
        this.root.setPrefWidth(width + insets.get().getBottom() * 2);
        this.root.paddingProperty().bind(insets);
        // header
        content.setTop(cfHeader);
        headerEvent();
    }

    /**
     * 窗口事件监听
     */
    private void headerEvent() {
        cfHeader.setMaximizeMouseClicked(event -> maximize.set(!maximize.get()));
        maximize.addListener((observable, oldValue, newValue) -> {
            if (newValue) {
                stageBounds = new Rectangle2D(getX(), getY(), getWidth(), getHeight());
                Rectangle2D visualBounds = Screen.getPrimary().getVisualBounds();
                insets.set(new Insets(0));
                arc.set(0);
                setWidth(visualBounds.getWidth());
                setHeight(visualBounds.getHeight());
                setX(visualBounds.getMinX());
                setY(visualBounds.getMinY());
            } else {
                insets.set(new Insets(10));
                arc.set(6);
                setWidth(stageBounds.getWidth());
                setHeight(stageBounds.getHeight());
                setX(stageBounds.getMinX());
                setY(stageBounds.getMinY());
            }
        });
        cfHeader.setIconifyMouseClicked(event -> setIconified(true));
        cfHeader.setCloseMouseClicked(event -> close());
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
