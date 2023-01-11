package cn.lichenfei.fxui.controls;

import cn.lichenfei.fxui.common.FxUtils;
import javafx.animation.Animation;
import javafx.animation.Interpolator;
import javafx.animation.Timeline;
import javafx.animation.TranslateTransition;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.layout.StackPane;
import javafx.util.Duration;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

/**
 * @author ChenFei
 * @date 2022/12/18
 * <p>
 * 走马灯、轮播图
 */
public class CFCarousel extends StackPane {

    private List<StackPane> spList;

    TranslateTransition t1 = new TranslateTransition();
    TranslateTransition t2 = new TranslateTransition();

    public CFCarousel(List<StackPane> spList) {
        StackPane sp1 = new StackPane();
        sp1.setStyle("-fx-background-color:red;");
        StackPane sp2 = new StackPane();
        sp2.setStyle("-fx-background-color:blue;");
        //
        this.setStyle("-fx-border-color:green;");
        this.spList = Arrays.asList(sp1, sp2);
        FxUtils.setClip(this, 0);
        this.setWidth(500);
        setEvent();
    }

    private void setEvent() {


        spList.forEach(sp -> {
            sp.prefWidthProperty().bind(this.widthProperty());
            sp.prefHeightProperty().bind(this.heightProperty());
        });
        StackPane left = spList.get(0);
        StackPane right = spList.get(1);
        this.getChildren().addAll(left, right);
        left.translateXProperty().bind(this.widthProperty().multiply(-1));
        t2.setFromX(0);
        t2.setToX(this.getWidth());
        t2.setNode(right);
        t2.setDuration(Duration.seconds(2));
        t2.setCycleCount(-1);//次数：无数次
        t2.setInterpolator(Interpolator.LINEAR);//：每次旋转完的过渡:均匀过渡
        t2.play();


    }

}
