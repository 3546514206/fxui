package cn.lichenfei.fxui.controls;

import cn.lichenfei.fxui.common.FxUtil;
import cn.lichenfei.fxui.common.SimpleControl;
import javafx.animation.FadeTransition;
import javafx.animation.ParallelTransition;
import javafx.animation.TranslateTransition;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;
import org.kordamp.ikonli.antdesignicons.AntDesignIconsOutlined;
import org.kordamp.ikonli.javafx.FontIcon;

/**
 * 抽屉
 */
public class CFDrawer extends StackPane {

    //基本属性
    private static final String STYLE_SHEET = FxUtil.getResource("/css/cf-drawer.css");
    private double size;
    private StackPane parent;
    private Position position = Position.RIGHT;

    //UI布局
    private StackPane modal = new StackPane(); // 遮罩层
    //
    private Label titleLabel = SimpleControl.getLabel("CFDrawer", SimpleControl.LabelEnum.H4);
    private Button close = new Button();
    private StackPane header = new StackPane(titleLabel, close); // 头部
    private StackPane content = new StackPane(); // 内容区域
    private VBox main = new VBox(header, content);// 主要区域

    /**
     * 提供一个StackPane作为父组件，会将抽屉添加到父组件，在调用show()方法进行展示
     *
     * @param size：Position位置不同 size表示的参数不通：TOP（size=height）, RIGHT（size=width）, BOTTOM（size=height）, LEFT（size=width）
     * @param parent
     */
    public CFDrawer(double size, StackPane parent) {
        this.size = size;
        this.parent = parent;
        initialize();
    }

    /**
     * 设置抽屉中的内容
     *
     * @param node
     * @return
     */
    public CFDrawer setContent(Node node) {
        ObservableList<Node> children = content.getChildren();
        if (children.isEmpty()) {
            children.add(node);
        } else {
            children.set(0, node);
        }
        return this;
    }

    private void initialize() {
        getChildren().addAll(this.modal, this.main);
        Rectangle rectangle = new Rectangle();
        rectangle.heightProperty().bind(heightProperty());
        rectangle.widthProperty().bind(widthProperty());
        setClip(rectangle);// 裁剪使抽屉移动时不会超出父组件
        // 主要区域
        header.setMaxSize(USE_COMPUTED_SIZE, USE_PREF_SIZE);
        StackPane.setAlignment(titleLabel, Pos.CENTER_LEFT);
        StackPane.setAlignment(close, Pos.CENTER_RIGHT);
        close.setGraphic(new FontIcon(AntDesignIconsOutlined.CLOSE));
        VBox.setVgrow(content, Priority.ALWAYS);
        //遮罩层
        modal.setBackground(new Background(new BackgroundFill(Color.rgb(0, 0, 0, 0.3), null, null)));
        modal.setOpacity(0);
        //点击遮罩层关闭
        modal.setOnMouseClicked(event -> hide());
        close.setOnMouseClicked(event -> hide());
        //styleClass
        main.getStyleClass().add("main");
        close.getStyleClass().add("close");
    }

    // 动画属性
    private TranslateTransition TT = new TranslateTransition(Duration.millis(250), this.main);
    private FadeTransition FT = new FadeTransition(Duration.millis(250), this.modal);
    private ParallelTransition PT = new ParallelTransition(TT, FT);


    /**
     * 从右侧展示抽屉
     */
    public void show() {
        show(Position.RIGHT);
    }

    /**
     * 指定方向展示抽屉
     *
     * @param position
     */
    public void show(Position position) {
        this.position = position;
        setShowInfo();
        parent.getChildren().add(this);// 添加抽屉
        FT.setFromValue(0);
        FT.setToValue(1);
        PT.play();
        PT.setOnFinished((event) -> {
        });
    }

    /**
     * 隐藏抽屉
     */
    public void hide() {
        setHideInfo();
        FT.setFromValue(1);
        FT.setToValue(0);
        PT.play();
        PT.setOnFinished((event) -> parent.getChildren().remove(this));// 移除抽屉
    }

    private void setHideInfo() {
        double translateSize = getTranslateSize();
        switch (position) {
            case TOP:
            case BOTTOM:
                TT.setFromY(0);
                TT.setToY(translateSize);
                TT.setToX(0);
                TT.setFromX(0);
                break;
            case RIGHT:
            case LEFT:
                TT.setFromX(0);
                TT.setToX(translateSize);
                TT.setToY(0);
                TT.setFromY(0);
                break;
            default:
                break;
        }
    }

    private void setShowInfo() {
        double translateSize = getTranslateSize();
        switch (position) {
            case TOP:
            case BOTTOM:
                main.setPrefHeight(size);
                main.setMaxSize(USE_COMPUTED_SIZE, USE_PREF_SIZE);
                StackPane.setAlignment(main, Position.BOTTOM.equals(position) ? Pos.BOTTOM_CENTER : Pos.TOP_CENTER);
                main.setTranslateY(translateSize);
                main.setTranslateX(0);
                TT.setFromY(translateSize);
                TT.setToY(0);
                TT.setToX(0);
                TT.setFromX(0);
                break;
            case RIGHT:
            case LEFT:
                main.setPrefWidth(size);
                main.setMaxSize(USE_PREF_SIZE, USE_COMPUTED_SIZE);
                StackPane.setAlignment(main, Position.RIGHT.equals(position) ? Pos.CENTER_RIGHT : Pos.CENTER_LEFT);
                main.setTranslateX(translateSize);
                main.setTranslateY(0);
                TT.setFromX(translateSize);
                TT.setToX(0);
                TT.setToY(0);
                TT.setFromY(0);
                break;
            default:
                break;
        }
    }

    @Override
    public String getUserAgentStylesheet() {
        return STYLE_SHEET;
    }

    /**
     * 获取移动量
     *
     * @return
     */
    private double getTranslateSize() {
        double translateSize = size;
        if (Position.TOP.equals(position) || Position.LEFT.equals(position)) {
            translateSize = -size;
        }
        return translateSize;
    }

    /**
     * 抽屉出现的位置
     */
    public enum Position {
        TOP, RIGHT, BOTTOM, LEFT
    }

}
