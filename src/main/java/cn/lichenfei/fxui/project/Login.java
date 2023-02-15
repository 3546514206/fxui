package cn.lichenfei.fxui.project;

import cn.lichenfei.fxui.common.FxUtil;
import cn.lichenfei.fxui.common.SimpleButton;
import cn.lichenfei.fxui.common.SimpleControl;
import cn.lichenfei.fxui.controls.CFAvatar;
import cn.lichenfei.fxui.controls.CFTextField;
import javafx.animation.*;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.geometry.Pos;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.util.Duration;
import org.kordamp.ikonli.antdesignicons.AntDesignIconsOutlined;
import org.kordamp.ikonli.javafx.FontIcon;

import java.util.function.Consumer;

public class Login extends StackPane {

    private DoubleProperty DEFAULT_HEIGHT = new SimpleDoubleProperty(40);
    private DoubleProperty DEFAULT_WIDTH = new SimpleDoubleProperty(250);
    // 登录，注册容器
    private SignInBox signInBox = new SignInBox();
    private SignUpBox signUpBox = new SignUpBox();

    //动画
    private TranslateTransition TT1 = new TranslateTransition(Duration.millis(500));
    private RotateTransition RT = new RotateTransition(Duration.millis(500));
    private ParallelTransition PT = new ParallelTransition(TT1, RT);
    private TranslateTransition TT2 = new TranslateTransition(Duration.millis(500));
    private SequentialTransition SEQ_T = new SequentialTransition(PT, TT2);
    private double translateX = -400;
    private double rotateAngle = 5;

    public Login() {
        setBackdropImage(FxUtil.getImage("/img/backdrop.png"));
        getChildren().addAll(signUpBox, signInBox);
        //动画
        signUpBox.setRotate(rotateAngle);
        signInBox.toSignUpClicked(event -> play(signInBox, signUpBox));
        signUpBox.toSignInClicked(event -> play(signUpBox, signInBox));
        //
        signUpBox.setMaxWidth(USE_PREF_SIZE);
        signInBox.setMaxWidth(USE_PREF_SIZE);
        StackPane.setAlignment(signInBox, Pos.CENTER_RIGHT);
        StackPane.setAlignment(signUpBox, Pos.CENTER_RIGHT);
        // styleClass
        getStyleClass().add("login");
        signUpBox.getStyleClass().add("sign-up-box");
        signInBox.getStyleClass().add("sign-in-box");
    }

    // 启动动画
    private void play(Pane out, Pane in) {
        TT1.setNode(out);
        TT1.setFromX(0);
        TT1.setToX(translateX);
        RT.setNode(out);
        RT.setFromAngle(0);
        RT.setToAngle(rotateAngle);
        out.rotateProperty()
                // 监听旋转角度恢复要显示的内容
                .addListener((observable, oldValue, newValue) -> in.setRotate(rotateAngle - newValue.doubleValue()));
        TT2.setNode(out);
        TT2.setFromX(translateX);
        TT2.setToX(0);
        PT.setOnFinished(event -> {
            // 动画完成设置Z值（深度）
            in.setTranslateZ(-1);
            out.setTranslateZ(0);
        });
        SEQ_T.play();
    }

    private void setBackdropImage(Image image) {
        BackgroundSize backgroundSize = new BackgroundSize(-1, -1, false, false, false, true);
        BackgroundImage backgroundImage = new BackgroundImage(image, null, null, BackgroundPosition.DEFAULT, backgroundSize);
        Background background = new Background(backgroundImage);
        setBackground(background);
    }

    private void bindWidthHeight(Region... node) {
        for (int i = 0; i < node.length; i++) {
            node[i].prefWidthProperty().bind(DEFAULT_WIDTH);
            node[i].prefHeightProperty().bind(DEFAULT_HEIGHT);
        }
    }

    /**
     * 登录
     */
    public class SignInBox extends VBox {

        private Label titleLabel = SimpleControl.getLabel("登录", SimpleControl.LabelEnum.H1);
        private CFAvatar avatar = new CFAvatar(64, 10);
        private StackPane titlePane = new StackPane(titleLabel, avatar);
        private CFTextField email = new CFTextField(CFTextField.Type.TEXT, new FontIcon(AntDesignIconsOutlined.MAIL));
        private CFTextField password = new CFTextField(CFTextField.Type.PASSWORD, new FontIcon(AntDesignIconsOutlined.KEY));
        private SimpleButton signIn = new SimpleButton("登录");
        private Hyperlink toSignUp = SimpleControl.getHyperlink("没有账户？去注册", SimpleControl.Level.PRIMARY);

        public SignInBox() {
            getChildren().addAll(titlePane, email, password, signIn, toSignUp);
            StackPane.setAlignment(titleLabel, Pos.BOTTOM_LEFT);
            avatar.setImage(FxUtil.getImage("/img/logo.jpg"));
            StackPane.setAlignment(avatar, Pos.BOTTOM_RIGHT);
            bindWidthHeight(email, password, signIn);
            signIn.prefWidthProperty().bind(password.widthProperty());
            //
            email.setPromptText("邮箱");
            password.setPromptText("密码");
        }

        public void toSignUpClicked(Consumer<MouseEvent> consumer) {
            this.toSignUp.setOnMouseClicked(event -> consumer.accept(event));
        }

    }

    /**
     * 注册
     */
    public class SignUpBox extends VBox {

        private Label titleLabel = SimpleControl.getLabel("注册", SimpleControl.LabelEnum.H1);
        private StackPane titlePane = new StackPane(titleLabel);
        private CFTextField user = new CFTextField(CFTextField.Type.TEXT, new FontIcon(AntDesignIconsOutlined.USER));
        private CFTextField email = new CFTextField(CFTextField.Type.TEXT, new FontIcon(AntDesignIconsOutlined.MAIL));
        private CFTextField password = new CFTextField(CFTextField.Type.PASSWORD, new FontIcon(AntDesignIconsOutlined.KEY));
        private SimpleButton signUp = new SimpleButton("注册");
        private Hyperlink toSignIn = SimpleControl.getHyperlink("已有账户，去登录", SimpleControl.Level.PRIMARY);

        public SignUpBox() {
            getChildren().addAll(titlePane, user, email, password, signUp, toSignIn);
            bindWidthHeight(user, email, password, signUp);
            StackPane.setAlignment(titleLabel, Pos.CENTER_LEFT);
            signUp.prefWidthProperty().bind(password.widthProperty());
            //
            user.setPromptText("用户名");
            email.setPromptText("邮箱");
            password.setPromptText("密码");
        }

        public void toSignInClicked(Consumer<MouseEvent> consumer) {
            this.toSignIn.setOnMouseClicked(event -> consumer.accept(event));
        }
    }

    @Override
    public String getUserAgentStylesheet() {
        return FxUtil.getResource("/css/project/login.css");
    }
}
