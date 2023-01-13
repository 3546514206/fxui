package cn.lichenfei.fxui.controls;

import cn.lichenfei.fxui.common.FxUtils;
import javafx.animation.*;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.util.Duration;

import java.util.Arrays;
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
    private Duration duration = Duration.millis(1000); // 动画时间
    private List<StackPane> spList;// 需要轮播的容器列表
    private int currentIndex = 1;
    private double width;
    private double height;

    private Button button = new Button();// 没用的button
    // 动画
    private TranslateTransition TT = new TranslateTransition();// 移动动画
    //private TranslateTransition t2 = new TranslateTransition();
    //private ParallelTransition pt = new ParallelTransition(t1, t2);// 平行动画
    private SequentialTransition ST = new SequentialTransition(// 顺序动画
            TT,
            new PauseTransition(interval)// 停顿时间
    );


    public CFCarousel(List<StackPane> spList, double width, double height) {
        StackPane sp1 = new StackPane();
        sp1.getChildren().add(new ImageView(FxUtils.getImage("/img/img1.png")));
        StackPane sp2 = new StackPane();
        sp2.getChildren().add(new ImageView(FxUtils.getImage("/img/img2.png")));
        StackPane sp3 = new StackPane();
        sp3.getChildren().add(new ImageView(FxUtils.getImage("/img/img3.png")));
        this.spList = Arrays.asList(sp1, sp2, sp3);
        this.width = width;
        this.height = height;
        //
        FxUtils.setClip(this, 10);
        this.setMaxWidth(Double.NEGATIVE_INFINITY);
        this.setMaxHeight(Double.NEGATIVE_INFINITY);
        setAnimation();
    }

    /**
     * 设置动画效果
     */
    private void setAnimation() {
        this.setPrefWidth(this.width);
        this.setPrefHeight(this.height);
        if (spList.size() == 1) {
            this.getChildren().add(spList.get(0));
            return;
        }
        this.getChildren().addAll(spList.get(0), spList.get(1));
        spList.get(1).setTranslateX(-width);
        spList.get(1).setUserData(LEFT);
        spList.get(0).setUserData(RIGHT);
        // 动画效果（根据动画设置节点的偏移量）
        button.translateXProperty().addListener((observable, oldValue, newValue) -> {
            Node node1 = this.getChildren().get(0);
            Node node2 = this.getChildren().get(1);
            if (LEFT.equals(node1.getUserData())) {
                node1.setTranslateX(newValue.doubleValue() - width);
                node2.setTranslateX(newValue.doubleValue());
            } else {
                node1.setTranslateX(newValue.doubleValue());
                node2.setTranslateX(newValue.doubleValue() - width);
            }
        });
        // 每次轮播执行完毕
        TT.setOnFinished(event -> {
            if (currentIndex + 1 >= spList.size()) {
                currentIndex = 0;
            } else {
                currentIndex++;
            }
            StackPane currentNode = spList.get(currentIndex);
            currentNode.setUserData(LEFT);
            currentNode.setTranslateX(-width);
            if (RIGHT.equals(this.getChildren().get(0).getUserData())) {
                this.getChildren().set(0, currentNode);
                this.getChildren().get(1).setUserData(LEFT);
            } else {
                this.getChildren().set(1, currentNode);
                this.getChildren().get(0).setUserData(RIGHT);
            }
        });
        TT.setFromX(0);
        TT.setToX(width);
        TT.setNode(button);
        TT.setDuration(duration);
        //开始动画
        ST.setCycleCount(-1);
        ST.setDelay(interval);// 延迟动画的开始
        ST.play();
    }
}
