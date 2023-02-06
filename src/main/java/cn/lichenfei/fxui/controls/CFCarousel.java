package cn.lichenfei.fxui.controls;

import cn.lichenfei.fxui.common.FxUtils;
import javafx.animation.*;
import javafx.beans.property.*;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.util.Duration;
import org.kordamp.ikonli.antdesignicons.AntDesignIconsOutlined;
import org.kordamp.ikonli.javafx.FontIcon;

import java.util.List;

/**
 * @author ChenFei
 * @date 2022/12/18
 * <p>
 * 走马灯、轮播图
 */
public class CFCarousel extends StackPane {

    private static final String NEXT = "NEXT";
    private static final String CURRENT = "CURRENT";
    //
    private Duration interval = Duration.millis(3000);// 隔多少时间切换下一个
    private Duration duration = Duration.millis(500); // 动画时间
    private List<StackPane> spList;// 需要轮播的容器列表
    private IntegerProperty currentIndex = new SimpleIntegerProperty(0);
    private double width;
    private double height;
    private boolean autoplay;
    private Direction direction; // 轮播方向，默认向右
    private Direction directionCache; // 轮播方向缓存

    private Button TRANSITION_NODE = new Button();// 没用的button
    // 动画
    private TranslateTransition TT = new TranslateTransition();// 移动动画
    private PauseTransition PT = new PauseTransition(interval);// 停顿时间
    private SequentialTransition ST = new SequentialTransition(TT, PT);// 顺序动画
    // 轮播内容区域
    private StackPane content = new StackPane();
    // 轮播指示器
    private Indicator indicator;
    // 上一个
    private Button leftBut = new Button();
    // 下一个
    private Button rightBut = new Button();

    public static CFCarousel create(List<StackPane> spList, double width, double height) {
        return new CFCarousel(spList, width, height, true, Direction.RIGHT);
    }

    public static CFCarousel create(List<StackPane> spList, double width, double height, boolean autoplay) {
        return new CFCarousel(spList, width, height, autoplay, Direction.RIGHT);
    }

    public static CFCarousel create(List<StackPane> spList, double width, double height, boolean autoplay, Direction direction) {
        return new CFCarousel(spList, width, height, autoplay, direction);
    }

    private CFCarousel(List<StackPane> spList, double width, double height, boolean autoplay, Direction direction) {
        this.spList = spList;
        this.width = width;
        this.height = height;
        this.autoplay = autoplay;
        this.direction = direction;
        this.directionCache = direction;
        setLayout();
        setAnimation();
        setEvent();
    }

    // 布局
    private void setLayout() {
        FxUtils.setClip(this, 10);
        this.setMaxWidth(Double.NEGATIVE_INFINITY);
        this.setMaxHeight(Double.NEGATIVE_INFINITY);
        this.getChildren().add(content);
        this.setPrefWidth(this.width);
        this.setPrefHeight(this.height);
        setIndicator();// 指示器
        // 左 , 右 按钮
        this.getChildren().addAll(leftBut, rightBut);
        StackPane.setAlignment(leftBut, Pos.CENTER_LEFT);
        StackPane.setAlignment(rightBut, Pos.CENTER_RIGHT);
        StackPane.setMargin(leftBut, new Insets(0, 0, 0, 10));
        StackPane.setMargin(rightBut, new Insets(0, 10, 0, 0));
        leftBut.setGraphic(new FontIcon(AntDesignIconsOutlined.LEFT));
        rightBut.setGraphic(new FontIcon(AntDesignIconsOutlined.RIGHT));
        // class
        this.getStyleClass().add("cf-carousel");
        leftBut.getStyleClass().add("left-but");
        rightBut.getStyleClass().add("right-but");
        leftBut.opacityProperty().bind(rightBut.opacityProperty());
        // 鼠标移入显示按钮
        this.hoverProperty().addListener((observable, oldValue, newValue) -> rightBut.setOpacity(newValue ? 1 : 0));
        rightBut.setOpacity(0);// 初始化不显示
    }

    // 设置轮播指示器
    private void setIndicator() {
        indicator = new Indicator(this.spList.size(), currentIndex.get());
        this.getChildren().add(indicator);
        StackPane.setAlignment(indicator, Pos.BOTTOM_CENTER);
        StackPane.setMargin(indicator, new Insets(0, 0, 5, 0));
        this.currentIndex.addListener((observable, oldValue, newValue) -> indicator.setSelected(newValue.intValue()));// 更新指示器
    }

    /**
     * 设置相关事件
     */
    private void setEvent() {
        this.rightBut.setOnMouseClicked(event -> {
            if (!directionCache.equals(Direction.RIGHT)) {

            }
            directionCache = Direction.RIGHT;
            PT.setDuration(Duration.millis(0));
            ST.setDelay(Duration.millis(0));
            ST.setCycleCount(1);
            ST.play();
        });
        this.leftBut.setOnMouseClicked(event -> {
            if (!directionCache.equals(Direction.LEFT)) {

            }
            directionCache = Direction.LEFT;
            PT.setDuration(Duration.millis(0));
            ST.setDelay(Duration.millis(0));
            ST.setCycleCount(1);
            ST.play();
        });
    }

    /**
     * 下一个轮播
     */
    private void currentIndexNext() {
        if (direction.equals(directionCache)) { // 方向相同，递增
            if (currentIndex.get() + 1 >= spList.size()) {
                currentIndex.set(0);
            } else {
                currentIndex.set(currentIndex.get() + 1);
            }
        } else {// 方向相反，递减
            if (currentIndex.get() <= 0) {
                currentIndex.set(spList.size() - 1);
            } else {
                currentIndex.set(currentIndex.get() - 1);
            }
        }
        System.out.println(currentIndex);
    }

    /**
     * 设置动画效果
     */
    private void setAnimation() {
        if (spList.size() == 1) {
            content.getChildren().add(spList.get(0));
            return;
        }
        content.getChildren().addAll(spList.get(1), spList.get(0));
        setPlace(spList.get(0), spList.get(1));// 设置轮播初始位置
        // 动画效果（根据动画设置节点的偏移量）
        TRANSITION_NODE.translateXProperty().addListener((observable, oldValue, newValue) -> {
            Node node1 = content.getChildren().get(0);
            Node node2 = content.getChildren().get(1);
            boolean b = Direction.RIGHT.equals(directionCache);
            double doubleValue = newValue.doubleValue();
            if (NEXT.equals(node1.getUserData())) {
                node1.setTranslateX(b ? doubleValue - width : width - doubleValue);
                node2.setTranslateX(b ? doubleValue : -doubleValue);
            } else {
                node1.setTranslateX(b ? doubleValue : -doubleValue);
                node2.setTranslateX(b ? doubleValue - width : width - doubleValue);
            }
        });
        // 每次轮播执行完毕
        TT.setOnFinished(event -> {
            currentIndexNext();
            setPosition();
        });
        TT.setFromX(0);
        TT.setToX(width);
        TT.setNode(TRANSITION_NODE);
        TT.setDuration(duration);
        //开始动画
        ST.setCycleCount(-1);
        ST.setDelay(interval);// 延迟动画的开始
        if (autoplay) {
            ST.play();
        }
    }

    /**
     * 设置轮播图位置
     *
     * @param current
     * @param next
     */
    private void setPlace(Node current, Node next) {
        current.setUserData(CURRENT);
        next.setUserData(NEXT);
        next.setTranslateX(Direction.RIGHT.equals(directionCache) ? -width : width);
    }

    /**
     * 设置位置
     */
    private void setPosition() {
        boolean isCurrent = CURRENT.equals(content.getChildren().get(0).getUserData());
        if (spList.size() == 2) {// 轮播为2特殊判断
            setPlace(this.content.getChildren().get(isCurrent ? 1 : 0), content.getChildren().get(isCurrent ? 0 : 1));
        } else {
            int i;
            if (!direction.equals(directionCache)) {
                i = currentIndex.get() == 0 ? spList.size() - 1 : currentIndex.get() - 1;
            } else {
                i = currentIndex.get() + 1 == spList.size() ? 0 : currentIndex.get() + 1;
            }
            System.out.println(i);
            Node currentNode = spList.get(i);
            content.getChildren().set(isCurrent ? 0 : 1, currentNode);
            setPlace(content.getChildren().get(isCurrent ? 1 : 0), currentNode);
        }
    }

    /**
     * 轮播图方向
     */
    public enum Direction {
        LEFT, RIGHT
    }

    /**
     * 指示器
     */
    public class Indicator extends HBox {

        private int number;
        private ToggleGroup toggleGroup = new ToggleGroup();

        public Indicator(int number, int index) {
            this.number = number;
            setLayout();
            setSelected(index);
        }

        // 选中
        public void setSelected(int index) {
            ((ToggleButton) this.getChildren().get(index)).setSelected(true);
        }

        private void setLayout() {
            this.setMaxHeight(Double.NEGATIVE_INFINITY);
            this.setMaxWidth(Double.NEGATIVE_INFINITY);
            for (int i = 0; i < number; i++) {
                this.getChildren().add(getToggleButton(i));
            }
            this.getStyleClass().add("cf-indicator");
        }

        // 设置指示器按钮样式
        private ToggleButton getToggleButton(int index) {
            ToggleButton tb = new ToggleButton();
            tb.setToggleGroup(toggleGroup);
            tb.setUserData(index);
            return tb;
        }
    }
}

