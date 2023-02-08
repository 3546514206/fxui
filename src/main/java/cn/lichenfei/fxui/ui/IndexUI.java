package cn.lichenfei.fxui.ui;

import cn.lichenfei.fxui.common.FxUtils;
import javafx.animation.Timeline;
import javafx.animation.TranslateTransition;
import javafx.event.EventHandler;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;

public class IndexUI extends StackPane {

    private Rectangle TRANSITION_NODE = new Rectangle();
    private TranslateTransition TT = new TranslateTransition();

    private Backdrop backdrop1 = new Backdrop(FxUtils.getImage("/img/backdrop.png")); // 底层背景容器
    private StackPane main = new StackPane(); // 登录,注册容器
    private Backdrop backdrop2 = new Backdrop(FxUtils.getImage("/img/backdrop.png")); // 上层背景容器

    public IndexUI() {
        this.getChildren().addAll(backdrop1, main, backdrop2);
        main.setStyle("-fx-background-color: rgba(255,255,255,0.5);");

        Rectangle rectangle = new Rectangle();
        rectangle.widthProperty().bind(this.widthProperty().divide(2));
        rectangle.heightProperty().bind(this.heightProperty());
        backdrop2.setClip(rectangle);
        // 动画
        rectangle.layoutXProperty().bind(TRANSITION_NODE.translateXProperty());
        TT.setFromX(0);
        TT.toXProperty().bind(this.widthProperty().divide(2));
        TT.setNode(TRANSITION_NODE);
        TT.setDuration(Duration.seconds(1));
        TT.setAutoReverse(true);
        TT.setCycleCount(Timeline.INDEFINITE);
        backdrop2.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                TT.play();
            }
        });
    }


    /**
     * 背景容器
     */
    public class Backdrop extends StackPane {

        public Backdrop(Image image) {
            setBackdropImage(image);
        }

        public void setBackdropImage(Image image) {
            BackgroundSize backgroundSize = new BackgroundSize(-1, -1, false, false, false, true);
            BackgroundImage backgroundImage = new BackgroundImage(image, null, null, BackgroundPosition.DEFAULT, backgroundSize);
            Background background = new Background(backgroundImage);
            this.setBackground(background);
        }
    }
}
