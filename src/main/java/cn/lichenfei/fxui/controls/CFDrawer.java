package cn.lichenfei.fxui.controls;

import javafx.animation.FadeTransition;
import javafx.animation.ParallelTransition;
import javafx.animation.TranslateTransition;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;

/**
 * 抽屉
 */
public class CFDrawer extends StackPane {

    //基本属性
    private double size;
    private StackPane parent;
    private Position position = Position.RIGHT;

    //UI布局
    private StackPane modal = new StackPane(); // 遮罩层
    private StackPane main = new StackPane(); // 主要内容区域

    /**
     * 默认从右侧出现
     *
     * @param size
     * @param parent
     */
    public CFDrawer(double size, StackPane parent) {
        this.size = size;
        this.parent = parent;
        initialize();
    }

    private void initialize() {
        getChildren().addAll(this.modal, this.main);
        Rectangle rectangle = new Rectangle();
        rectangle.heightProperty().bind(heightProperty());
        rectangle.widthProperty().bind(widthProperty());
        setClip(rectangle);// 裁剪使抽屉移动时不会超出父组件
        // 内容区域
        main.setPadding(new Insets(20));
        main.setBackground(new Background(new BackgroundFill(Color.WHITE, null, null)));
        //遮罩层
        modal.setBackground(new Background(new BackgroundFill(Color.rgb(0, 0, 0, 0.5), null, null)));
        modal.setOpacity(0);
        //点击遮罩层关闭
        modal.setOnMouseClicked(event -> hide());
    }

    // 动画属性
    private TranslateTransition TT = new TranslateTransition(Duration.millis(250), this.main);
    private FadeTransition FT = new FadeTransition(Duration.millis(250), this.modal);
    private ParallelTransition PT = new ParallelTransition(TT, FT);


    public void show() {
        show(Position.RIGHT);
    }

    /**
     * 展示
     */
    public void show(Position position) {
        this.position = position;
        double fromSize = getTranslateSize();
        setLayout(fromSize);// 设置布局
        parent.getChildren().add(this);
        TT.setFromX(fromSize);
        TT.setToX(0);
        FT.setFromValue(0);
        FT.setToValue(1);
        PT.play();
        PT.setOnFinished((event) -> {
        });
    }

    /**
     * 隐藏
     */
    public void hide() {
        double toSize = getTranslateSize();
        TT.setFromX(0);
        TT.setToX(toSize);
        FT.setFromValue(1);
        FT.setToValue(0);
        PT.play();
        PT.setOnFinished((event) -> parent.getChildren().remove(this));
    }

    /**
     * 根据移动量设置布局
     *
     * @param translateSize
     */
    private void setLayout(double translateSize) {
        switch (position) {
            case TOP:
            case BOTTOM:
                /*main.setPrefHeight(size);
                main.setMaxSize(USE_COMPUTED_SIZE, USE_PREF_SIZE);
                StackPane.setAlignment(main, Position.BOTTOM.equals(position) ? Pos.BOTTOM_CENTER : Pos.TOP_CENTER);
                main.setTranslateY(translateSize);*/
                break;
            case RIGHT:
            case LEFT:
                main.setPrefWidth(size);
                main.setMaxSize(USE_PREF_SIZE, USE_COMPUTED_SIZE);
                StackPane.setAlignment(main, Position.RIGHT.equals(position) ? Pos.CENTER_RIGHT : Pos.CENTER_LEFT);
                main.setTranslateX(translateSize);
                break;
            default:
                break;
        }
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
