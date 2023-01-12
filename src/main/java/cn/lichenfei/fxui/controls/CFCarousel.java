package cn.lichenfei.fxui.controls;

import cn.lichenfei.fxui.common.FxUtils;
import javafx.animation.*;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.util.Duration;

import java.util.ArrayList;
import java.util.List;

/**
 * @author ChenFei
 * @date 2022/12/18
 * <p>
 * 走马灯、轮播图
 */
public class CFCarousel extends StackPane {

    private Duration interval = Duration.millis(3000);// 隔多少时间切换下一个
    private Duration duration = Duration.millis(1000); // 动画时间
    private List<StackPane> spList = new ArrayList<>();// 需要轮播的容器列表
    private int currentIndex = 1;

    private Button button = new Button();// 没用的button
    // 动画
    private TranslateTransition TT = new TranslateTransition();// 移动动画
    //private TranslateTransition t2 = new TranslateTransition();
    //private ParallelTransition pt = new ParallelTransition(t1, t2);// 平行动画
    private SequentialTransition ST = new SequentialTransition(// 顺序动画
            TT,
            new PauseTransition(interval)// 停顿时间
    );


    public CFCarousel(List<StackPane> spList) {
        /*StackPane sp1 = new StackPane();
        sp1.getChildren().add(new ImageView(FxUtils.getImage("/img/img1.png")));
        StackPane sp2 = new StackPane();
        sp2.getChildren().add(new ImageView(FxUtils.getImage("/img/img2.png")));
        StackPane sp3 = new StackPane();
        sp3.getChildren().add(new ImageView(FxUtils.getImage("/img/img3.png")));
        this.spList = Arrays.asList(sp1, sp2, sp3);*/
        for (int i = 0; i < 6; i++) {
            this.spList.add(getSp((i + 1) + ""));
        }
        //
        FxUtils.setClip(this, 10);
        this.setPrefWidth(500);
        this.setPrefHeight(300);
        this.setMaxWidth(Double.NEGATIVE_INFINITY);
        this.setMaxHeight(Double.NEGATIVE_INFINITY);
        setAnimation();
    }

    /**
     * 设置动画效果
     */
    private void setAnimation() {
        if (spList.size() == 1) {
            this.getChildren().add(spList.get(0));
            return;
        }
        spList.get(1).setTranslateX(-500);
        this.getChildren().addAll(spList.get(0), spList.get(1));
        // 动画效果（根据动画设置节点的偏移量）
        button.translateXProperty().addListener((observable, oldValue, newValue) -> {
            Node node1 = this.getChildren().get(0);
            Node node2 = this.getChildren().get(1);
            node1.setTranslateX(currentIndex % 2 == 0 ? newValue.doubleValue() - 500 : newValue.doubleValue());
            node2.setTranslateX(currentIndex % 2 == 1 ? newValue.doubleValue() - 500 : newValue.doubleValue());
        });
        // 每次轮播执行完毕
        TT.setOnFinished(event -> {
            System.out.println("currentIndex：" + currentIndex);
            StackPane leftNode;
            int setIndex;
            if (currentIndex + 1 == spList.size()) {
                leftNode = spList.get(0);
                setIndex = currentIndex % 2 == 0 ? 1 : 0;
                currentIndex = 0;
            } else {
                currentIndex++;
                leftNode = spList.get(currentIndex);
                setIndex = currentIndex % 2 == 1 ? 1 : 0;
            }
            leftNode.setTranslateX(-500);
            this.getChildren().set(setIndex, leftNode);
        });
        TT.setFromX(0);
        TT.setToX(500);
        TT.setNode(button);
        TT.setDuration(duration);
        //开始动画
        ST.setCycleCount(-1);
        ST.setDelay(interval);// 延迟动画的开始
        ST.play();
    }

    private StackPane getSp(String index) {
        Label label = new Label(index);
        label.setStyle("-fx-font-size: 50px;");
        StackPane stackPane = new StackPane(label);
        stackPane.setStyle("-fx-background-color:rgb(255,255,255);");
        return stackPane;
    }
}
