package cn.lichenfei.fxui.ui;

import cn.lichenfei.fxui.common.FxUtils;
import cn.lichenfei.fxui.controls.CFButton;
import cn.lichenfei.fxui.controls.CFTextField;
import javafx.animation.TranslateTransition;
import javafx.geometry.Pos;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;
import org.kordamp.ikonli.antdesignicons.AntDesignIconsOutlined;
import org.kordamp.ikonli.javafx.FontIcon;

public class IndexUI extends StackPane {

    private Rectangle TRANSITION_NODE = new Rectangle();
    private TranslateTransition TT = new TranslateTransition();

    private Backdrop backdrop1 = new Backdrop(FxUtils.getImage("/img/backdrop.png")); // 底层背景容器
    private HBox main = new HBox(); // 登录,注册容器
    private Backdrop backdrop2 = new Backdrop(FxUtils.getImage("/img/backdrop.png")); // 上层背景容器

    //
    private SignInBox signInBox = new SignInBox();
    private SignUpBox signUpBox = new SignUpBox();

    public IndexUI() {
        getChildren().addAll(backdrop1, main, backdrop2);
        main.setStyle("-fx-background-color: rgba(255,255,255,0.5);");
        main.getChildren().addAll(signInBox, signUpBox);
        signInBox.prefWidthProperty().bind(widthProperty().divide(2));
        signUpBox.prefWidthProperty().bind(widthProperty().divide(2));
        //
        Rectangle rectangle = new Rectangle();
        rectangle.widthProperty().bind(this.widthProperty().divide(2));
        rectangle.heightProperty().bind(this.heightProperty());
        backdrop2.setClip(rectangle);
        // 动画
        rectangle.layoutXProperty().bind(TRANSITION_NODE.translateXProperty());

        backdrop2.setOnMouseClicked(event -> translatePlay(!(TRANSITION_NODE.getTranslateX() > 0)));
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

    public class SignInBox extends VBox {
        private CFTextField email = new CFTextField(CFTextField.Type.TEXT, new FontIcon(AntDesignIconsOutlined.MAIL));
        private CFTextField password = new CFTextField(CFTextField.Type.PASSWORD, new FontIcon(AntDesignIconsOutlined.KEY));
        private CFButton signIn = new CFButton("登录");

        public SignInBox() {
            getChildren().addAll(email, password, signIn);
            signIn.prefWidthProperty().bind(password.widthProperty());
            setAlignment(Pos.CENTER);
            setSpacing(20);
        }
    }

    public class SignUpBox extends VBox {
        private CFTextField user = new CFTextField(CFTextField.Type.TEXT, new FontIcon(AntDesignIconsOutlined.USER));
        private CFTextField email = new CFTextField(CFTextField.Type.TEXT, new FontIcon(AntDesignIconsOutlined.MAIL));
        private CFTextField password = new CFTextField(CFTextField.Type.PASSWORD, new FontIcon(AntDesignIconsOutlined.KEY));
        private CFButton signUp = new CFButton("注册");

        public SignUpBox() {
            getChildren().addAll(user, email, password, signUp);
            signUp.prefWidthProperty().bind(password.widthProperty());
            setAlignment(Pos.CENTER);
            setSpacing(20);
        }
    }

}
