package cn.lichenfei.fxui.controls;

import cn.lichenfei.fxui.common.FxUtil;
import javafx.beans.property.*;
import javafx.geometry.Insets;
import javafx.geometry.Rectangle2D;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.effect.BlurType;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import org.silentsoft.ui.util.StageDragResizer;

public class CFStage extends Stage {

    private static final String ROOT_STYLE_SHEET = FxUtil.getResource("/css/cf-base.css");
    private ObjectProperty<Insets> insetsPro = new SimpleObjectProperty<>(new Insets(10));
    private BooleanProperty maximizePro = new SimpleBooleanProperty(false);
    private DoubleProperty arcPro = new SimpleDoubleProperty(6); // 窗口圆角程度
    private double arc;
    private double height = 650;
    private double width = 1100;
    private Rectangle2D stageBounds;
    private StageDragResizer stageDragResizer;

    private BorderPane content = new BorderPane(); // 内容区域
    private StackPane backdrop = new StackPane(content); // 背景区域，为了展示阴影效果
    private StackPane root = new StackPane(backdrop); // 根节点
    private Scene scene = new Scene(root);

    //
    private CFHeader cfHeader = new CFHeader();

    public CFStage() {
        initialize();
    }

    public CFStage(Node node) {
        setContent(node);
        initialize();
    }

    public CFStage(Node node, double width, double height) {
        setContent(node);
        this.height = height;
        this.width = width;
        initialize();
    }

    public CFStage setArc(double arcPro) {
        this.arcPro.set(arcPro);
        return this;
    }

    public CFStage setContent(Node content) {
        this.content.setCenter(content);
        return this;
    }

    public CFStage setHeaderStyle(CFHeader.HeaderStyle headerStyle) {
        if (!CFHeader.HeaderStyle.ALL.equals(headerStyle)) {
            stageDragResizer.setMargin(0);
        }
        if (CFHeader.HeaderStyle.NONE.equals(headerStyle)) {
            content.setTop(null);
        }
        cfHeader.setHeaderStyle(headerStyle);
        return this;
    }

    public StackPane getBackdrop() {
        return backdrop;
    }

    /**
     * 设置背景图片:默认背景为白色
     *
     * @param image
     */
    public CFStage setBackdropImage(Image image) {
        BackgroundSize backgroundSize = new BackgroundSize(-1, -1, false, false, false, true);
        BackgroundImage backgroundImage = new BackgroundImage(image, null, null, BackgroundPosition.DEFAULT, backgroundSize);
        Background background = new Background(backgroundImage);
        this.content.setBackground(background);
        return this;
    }

    private void initialize() {
        initStyle(StageStyle.TRANSPARENT); // 修改窗口样式
        scene.setFill(null);
        scene.getStylesheets().add(ROOT_STYLE_SHEET);// 加载基础样式
        setScene(scene);
        root.setBackground(null);
        content.setBackground(new Background(new BackgroundFill(Color.WHITE, null, null))); // 窗口默认颜色
        //裁剪为圆角
        Rectangle rectangle = new Rectangle();
        rectangle.widthProperty().bind(content.widthProperty());
        rectangle.heightProperty().bind(content.heightProperty());
        rectangle.arcHeightProperty().bind(arcPro);
        rectangle.arcWidthProperty().bind(arcPro);
        content.setClip(rectangle);
        //显示阴影效果
        backdrop.setEffect(new DropShadow(BlurType.THREE_PASS_BOX, Color.rgb(0, 0, 0, 0.5), 10, 0, 1.5, 1.5));
        this.root.setPrefHeight(height + insetsPro.get().getBottom() * 2);
        this.root.setPrefWidth(width + insetsPro.get().getBottom() * 2);
        this.root.paddingProperty().bind(insetsPro);
        // header
        content.setTop(cfHeader);
        headerEvent();
        //窗口移动和缩放
        stageMove();
        stageDragResizer = StageDragResizer.makeResizable(this, this.root, 10, 5);// 窗口拖动缩放
    }

    /**
     * 窗口事件监听
     */
    private void headerEvent() {
        cfHeader.setMaximizeMouseClicked(event -> maximizePro.set(!maximizePro.get()));
        maximizePro.addListener((observable, oldValue, newValue) -> {
            if (newValue) {
                stageBounds = new Rectangle2D(getX(), getY(), getWidth(), getHeight());
                arc = arcPro.get();
                Rectangle2D visualBounds = FxUtil.getVisualBounds();
                insetsPro.set(new Insets(0));
                arcPro.set(0);
                setWidth(visualBounds.getWidth());
                setHeight(visualBounds.getHeight());
                setX(visualBounds.getMinX());
                setY(visualBounds.getMinY());
                stageDragResizer.setMargin(0);
            } else {
                insetsPro.set(new Insets(10));
                arcPro.set(arc);
                setWidth(stageBounds.getWidth());
                setHeight(stageBounds.getHeight());
                setX(stageBounds.getMinX());
                setY(stageBounds.getMinY());
                stageDragResizer.setMargin(10);
            }
            cfHeader.setMaximizeTooltip(newValue ? "向下还原" : "最大化");
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
