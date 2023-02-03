package cn.lichenfei.fxui.controls;

import cn.lichenfei.fxui.common.FxUtils;
import javafx.animation.*;
import javafx.beans.property.*;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
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

    private static final String LEFT = "L";
    private static final String RIGHT = "R";
    //
    private Duration interval = Duration.millis(3000);// 隔多少时间切换下一个
    private Duration duration = Duration.millis(500); // 动画时间
    private List<StackPane> spList;// 需要轮播的容器列表
    private IntegerProperty currentIndex = new SimpleIntegerProperty(1);
    private double width;
    private double height;

    private Button TRANSITION_NODE = new Button();// 没用的button
    // 动画
    private TranslateTransition TT = new TranslateTransition();// 移动动画
    private SequentialTransition ST = new SequentialTransition(// 顺序动画
            TT,
            new PauseTransition(interval)// 停顿时间
    );
    // 轮播内容区域
    private StackPane content = new StackPane();
    // 轮播指示器
    private Indicator indicator;
    // 上一个
    private Button leftBut = new Button();
    // 下一个
    private Button rightBut = new Button();

    public CFCarousel(List<StackPane> spList, double width, double height) {
        this.spList = spList;
        this.width = width;
        this.height = height;
        setLayout();
        setAnimation();
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
        // 鼠标移入再显示轮播图
        this.hoverProperty().addListener((observable, oldValue, newValue) -> rightBut.setOpacity(newValue ? 1 : 0));
        rightBut.setOpacity(0);// 初始化不显示
    }

    // 设置轮播指示器
    private void setIndicator() {
        indicator = new Indicator(this.spList.size(), currentIndex.get() - 1);
        this.getChildren().add(indicator);
        StackPane.setAlignment(indicator, Pos.BOTTOM_CENTER);
        indicator.setSelected(0);
        StackPane.setMargin(indicator, new Insets(0, 0, 5, 0));
    }

    /**
     * 设置动画效果
     */
    private void setAnimation() {
        if (spList.size() == 1) {
            content.getChildren().add(spList.get(0));
            return;
        }
        content.getChildren().addAll(spList.get(0), spList.get(1));
        spList.get(1).setTranslateX(-width);
        spList.get(1).setUserData(LEFT);
        spList.get(0).setUserData(RIGHT);
        // 动画效果（根据动画设置节点的偏移量）
        TRANSITION_NODE.translateXProperty().addListener((observable, oldValue, newValue) -> {
            Node node1 = content.getChildren().get(0);
            Node node2 = content.getChildren().get(1);
            if (LEFT.equals(node1.getUserData())) {
                node1.setTranslateX(newValue.doubleValue() - width);
                node2.setTranslateX(newValue.doubleValue());
            } else {
                node1.setTranslateX(newValue.doubleValue());
                node2.setTranslateX(newValue.doubleValue() - width);
            }
        });
        // 每次轮播执行完毕
        TT.setOnFinished(event -> setPosition());
        TT.setFromX(0);
        TT.setToX(width);
        TT.setNode(TRANSITION_NODE);
        TT.setDuration(duration);
        //开始动画
        ST.setCycleCount(-1);
        ST.setDelay(interval);// 延迟动画的开始
        ST.play();
    }

    /**
     * 设置位置
     */
    private void setPosition() {
        indicator.setSelected(currentIndex.get());// 轮播完毕，选中当前的指示器
        if (currentIndex.get() + 1 >= spList.size()) {
            currentIndex.set(0);
        } else {
            currentIndex.set(currentIndex.get() + 1);
        }
        Node currentNode;
        boolean rb = RIGHT.equals(content.getChildren().get(0).getUserData());
        if (spList.size() == 2) {// 轮播为2特殊判断
            currentNode = content.getChildren().get(rb ? 0 : 1);
            content.getChildren().get(rb ? 1 : 0).setUserData(RIGHT);
        } else {
            currentNode = spList.get(currentIndex.get());
            content.getChildren().set(rb ? 0 : 1, currentNode);
            content.getChildren().get(rb ? 1 : 0).setUserData(rb ? LEFT : RIGHT);
        }
        currentNode.setUserData(LEFT);
        currentNode.setTranslateX(-width);
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

