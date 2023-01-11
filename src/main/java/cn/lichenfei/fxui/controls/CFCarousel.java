package cn.lichenfei.fxui.controls;

import cn.lichenfei.fxui.common.FxUtils;
import javafx.animation.Interpolator;
import javafx.animation.ParallelTransition;
import javafx.animation.PauseTransition;
import javafx.animation.TranslateTransition;
import javafx.scene.Node;
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

    private Duration interval = Duration.millis(3000);// 自动切换的时间间隔，单位为秒
    private Duration duration = Duration.millis(1000); // 动画时间
    private List<StackPane> spList;
    private Integer currentIndex = 0;

    // 动画
    private TranslateTransition t1 = new TranslateTransition();
    private TranslateTransition t2 = new TranslateTransition();
    private ParallelTransition pt = new ParallelTransition(t1, t2, new PauseTransition(interval));

    public CFCarousel(List<StackPane> spList) {
        StackPane sp1 = new StackPane();
        sp1.setStyle("-fx-background-color:red;");
        StackPane sp2 = new StackPane();
        sp2.setStyle("-fx-background-color:blue;");
        //
        this.spList = Arrays.asList(sp1, sp2);
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
        //
        pt.setInterpolator(Interpolator.LINEAR);
        pt.setCycleCount(-1);
        pt.play();

        t1.setOnFinished(event -> new Thread(() -> {
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
