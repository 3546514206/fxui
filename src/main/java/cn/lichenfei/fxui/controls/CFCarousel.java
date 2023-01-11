package cn.lichenfei.fxui.controls;

import cn.lichenfei.fxui.common.FxUtils;
import javafx.animation.*;
import javafx.scene.Node;
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

    private Duration interval = Duration.millis(3000);// 自动切换的时间间隔
    private Duration duration = Duration.millis(1000); // 动画移动时间
    private List<StackPane> spList;
    private Integer currentIndex = 0;

    // 动画
    private TranslateTransition t1 = new TranslateTransition();// 移动动画
    private TranslateTransition t2 = new TranslateTransition();
    private ParallelTransition pt = new ParallelTransition(t1, t2);// 平行动画
    private SequentialTransition st = new SequentialTransition(
            pt,
            new PauseTransition(interval)// 停顿时间
    );//顺序动画

    public CFCarousel(List<StackPane> spList) {
        StackPane sp1 = new StackPane();
        sp1.getChildren().add(new ImageView(FxUtils.getImage("/img/img1.png")));
        StackPane sp2 = new StackPane();
        sp2.getChildren().add(new ImageView(FxUtils.getImage("/img/img2.png")));
        StackPane sp3 = new StackPane();
        sp3.getChildren().add(new ImageView(FxUtils.getImage("/img/img3.png")));
        //
        this.spList = Arrays.asList(sp1, sp2, sp3);
        FxUtils.setClip(this, 10);
        this.setPrefWidth(500);
        this.setPrefHeight(300);
        this.setMaxWidth(Double.NEGATIVE_INFINITY);
        this.setMaxHeight(Double.NEGATIVE_INFINITY);
        setEvent();
    }

    private void setEvent() {
        this.getChildren().addAll(spList.get(0), spList.get(1));
        setLeftNode(this.getChildren().get(1));
        setRightNode(this.getChildren().get(0));
        //开始动画
        st.setCycleCount(-1);
        st.play();
        pt.setOnFinished(event -> new Thread(() -> {// 一次轮播执行完毕
            System.out.println("动画完成。。。");
            /*try {
                TimeUnit.SECONDS.sleep(interval);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            Platform.runLater(() -> {
                currentIndex++;
                if (currentIndex + 1 == spList.size()) {
                    StackPane lp = spList.get(currentIndex);
                    setLeftNode(lp);
                    this.getChildren().set(1, lp);
                    setRightNode(spList.get(0));
                    currentIndex = 0;
                } else {
                    StackPane lp = spList.get(currentIndex);
                    setLeftNode(lp);
                    this.getChildren().set(0, lp);
                    setRightNode(spList.get(currentIndex + 1));
                }
                pt.play();
            });*/
        }).start());

    }

    /**
     * 设置左侧内容
     *
     * @param sp
     */
    private void setLeftNode(Node sp) {
        sp.setTranslateX(-500);
        t1.setFromX(sp.getTranslateX());
        t1.setToX(0);
        t1.setNode(sp);
        t1.setDuration(duration);
    }

    /**
     * 设置右侧内容
     *
     * @param sp
     */
    private void setRightNode(Node sp) {
        t2.setFromX(0);
        t2.setToX(500);
        t2.setNode(sp);
        t2.setDuration(duration);
    }

}