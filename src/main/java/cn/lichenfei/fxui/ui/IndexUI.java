package cn.lichenfei.fxui.ui;

import cn.lichenfei.fxui.common.FxUtils;
import cn.lichenfei.fxui.common.Level;
import cn.lichenfei.fxui.controls.SimpleButton;
import javafx.animation.TranslateTransition;
import javafx.geometry.Pos;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;

public class IndexUI extends StackPane {

    private Rectangle TRANSITION_NODE = new Rectangle();
    private TranslateTransition TT = new TranslateTransition();

    private Backdrop backdrop1 = new Backdrop(FxUtils.getImage("/img/backdrop.png")); // 底层背景容器
    private HBox main = new HBox(); // 登录,注册容器
    private Backdrop backdrop2 = new Backdrop(FxUtils.getImage("/img/backdrop.png")); // 上层背景容器

    public IndexUI() {
        this.getChildren().addAll(backdrop1, main, backdrop2);
        main.setStyle("-fx-background-color: rgba(255,255,255,0.8);");

        // 登录，注册测试按钮
        SimpleButton signIn = SimpleButton.get("登录", Level.PRIMARY);
        SimpleButton signUp = SimpleButton.get("注册", Level.PRIMARY);
        main.getChildren().addAll(signIn, signUp);
        main.setAlignment(Pos.CENTER);
        main.setSpacing(200);
        signIn.setOnMouseClicked(event -> translatePlay(false));
        signUp.setOnMouseClicked(event -> translatePlay(true));

        Rectangle rectangle = new Rectangle();
        rectangle.widthProperty().bind(this.widthProperty().divide(2));
        rectangle.heightProperty().bind(this.heightProperty());
        backdrop2.setClip(rectangle);
        // 动画
        rectangle.layoutXProperty().bind(TRANSITION_NODE.translateXProperty());
    }

    /**
     * 启动移动动画
     *
     * @param toRight；是否向右移动
     */
    private void translatePlay(boolean toRight) {
        TT.setCycleCount(1);
        TT.setNode(TRANSITION_NODE);
        TT.setDuration(Duration.millis(500));
        double v = this.widthProperty().divide(2).get();
        if (toRight) {
            TT.setFromX(0);
            TT.setToX(v);
        } else {
            TT.setFromX(v);
            TT.setToX(0);
        }
        TT.play();
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
