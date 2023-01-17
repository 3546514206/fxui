package cn.lichenfei.fxui.controls;

import cn.lichenfei.fxui.common.FxUtils;
import javafx.animation.ParallelTransition;
import javafx.animation.TranslateTransition;
import javafx.geometry.Insets;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.effect.BlurType;
import javafx.scene.effect.DropShadow;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;
import org.kordamp.ikonli.antdesignicons.AntDesignIconsOutlined;
import org.kordamp.ikonli.javafx.FontIcon;
import org.silentsoft.ui.util.StageDragResizer;

import java.util.function.Consumer;

public class CFStage extends Stage {

    private static final double DEFAULT_WIDTH = 1100;
    private static final double DEFAULT_HEIGHT = 650;
    private DropShadow dropShadow = new DropShadow(BlurType.THREE_PASS_BOX, Color.rgb(0, 0, 0, 0.8), 10, 0, 1.5, 1.5);
    private Background background = new Background(new BackgroundFill(Color.WHITE, null, new Insets(0, 0, 0, 0)));
    //
    private StackPane root = new StackPane();
    private StackPane shadow = new StackPane();
    private Scene scene = new Scene(root);
    private HBox backgroundBox = new HBox();
    private StackPane aside = new StackPane();// 侧边栏容器
    private VBox container = new VBox();// 右侧
    private Header header = new Header(this);// 顶栏容器
    private StackPane content = new StackPane();

    public CFStage() {
        initializeStyle();
        stageMove();
        StageDragResizer.makeResizable(this, this.root, 10, 5);// 窗口拖动缩放
    }

    public StackPane getRoot() {
        return root;
    }

    public HBox getBackgroundBox() {
        return backgroundBox;
    }

    /**
     * 初始化样式
     */
    private void initializeStyle() {
        this.initStyle(StageStyle.TRANSPARENT);// 定义具有透明背景且没有装饰的舞台样式
        this.setScene(scene);
        this.scene.setFill(null);// 设置场景背景
        this.root.setPadding(new Insets(10, 10, 10, 10));
        this.root.setBackground(null);
        this.root.setPrefWidth(DEFAULT_WIDTH);
        this.root.setPrefHeight(DEFAULT_HEIGHT);
        this.root.getChildren().add(this.shadow);
        this.root.setMaxWidth(Double.NEGATIVE_INFINITY);
        this.root.setMaxHeight(Double.NEGATIVE_INFINITY);
        this.shadow.getChildren().add(backgroundBox);
        this.shadow.setEffect(dropShadow); // 窗口阴影
        // 内容
        this.backgroundBox.setBackground(background);
        FxUtils.setClip(this.backgroundBox, 8);
        //
        this.backgroundBox.getChildren().addAll(aside, container);
        HBox.setHgrow(container, Priority.ALWAYS);
        container.getChildren().addAll(header, content);
        VBox.setVgrow(content, Priority.ALWAYS);
        // class
        root.getStyleClass().add("cf-stage");
        // css
        this.scene.getStylesheets().add(FxUtils.getCss("/css/chenfei-fxui.css"));
    }

    /**************************************************** 窗口拖动 ****************************************************/

    private double xOffset;
    private double yOffset;

    private void stageMove() {
        this.backgroundBox.setOnMousePressed(event -> {
            event.consume();
            this.xOffset = this.getX() - event.getScreenX();
            this.yOffset = this.getY() - event.getScreenY();
        });
        this.backgroundBox.setOnMouseDragged(event -> {
            event.consume();
            this.setX(event.getScreenX() + this.xOffset);
            this.setY(event.getScreenY() + this.yOffset);
        });
    }

    /**************************************************** 顶栏容器 ****************************************************/

    public class Header extends HBox {
        private CFStage stage;
        //
        private StackPane title = new StackPane();
        private HBox buttonBox = new HBox();
        //
        private Button iconifiedButton = new Button();
        private Button fullScreenButton = new Button();
        private Button closeButton = new Button();

        public Header(CFStage stage) {
            this.stage = stage;
            initializeStyle();
            initializeEvent();
        }

        public void initializeStyle() {
            this.getChildren().addAll(title, buttonBox);
            HBox.setHgrow(title, Priority.ALWAYS);
            buttonBox.getChildren().addAll(iconifiedButton, fullScreenButton, closeButton);
            // class
            this.getStyleClass().add("header");
            buttonBox.getStyleClass().add("button-box");
            iconifiedButton.getStyleClass().add("iconified-button");
            fullScreenButton.getStyleClass().add("full-screen-button");
            closeButton.getStyleClass().add("close-button");
            // icon
            iconifiedButton.setGraphic(new FontIcon(AntDesignIconsOutlined.MINUS));
            fullScreenButton.setGraphic(new FontIcon(AntDesignIconsOutlined.PLUS));
            closeButton.setGraphic(new FontIcon(AntDesignIconsOutlined.CLOSE));
        }

        public void initializeEvent() {
            /*this.onTopButton.selectedProperty().addListener((observable, oldValue, newValue) -> this.stage.setAlwaysOnTop(newValue));*/
            this.closeButton.setOnMouseClicked(event -> {
                event.consume();
                this.stage.close();
            });
            this.iconifiedButton.setOnMouseClicked(event -> this.stage.setIconified(true));
            this.fullScreenButton.setOnMouseClicked(event -> {
                Rectangle2D visualBounds = Screen.getPrimary().getVisualBounds();
                double height = visualBounds.getHeight();
                double width = visualBounds.getWidth();
                double minX = visualBounds.getMinX();
                double minY = visualBounds.getMinY();
                this.stage.setX(minX);
                this.stage.setY(minY);
                this.stage.setWidth(width);
                this.stage.setHeight(height);
                this.stage.getRoot().setPadding(new Insets(0, 0, 0, 0));
                this.stage.getBackgroundBox().setClip(null);
            });
        }
    }
}
