package cn.lichenfei.fxui.project;

import cn.lichenfei.fxui.common.FxUtil;
import cn.lichenfei.fxui.common.SimpleButton;
import cn.lichenfei.fxui.common.SimpleControl;
import cn.lichenfei.fxui.controls.CFAvatar;
import cn.lichenfei.fxui.controls.CFTextField;
import javafx.animation.*;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.SceneAntialiasing;
import javafx.scene.SubScene;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Polyline;
import javafx.util.Duration;
import org.kordamp.ikonli.antdesignicons.AntDesignIconsOutlined;
import org.kordamp.ikonli.javafx.FontIcon;

import java.util.function.Consumer;

public class Index extends StackPane {
    private DoubleProperty DEFAULT_HEIGHT = new SimpleDoubleProperty(40);
    private DoubleProperty DEFAULT_WIDTH = new SimpleDoubleProperty(250);
    //
    private StackPane subSceneRoot = new StackPane();
    private SubScene subScene = new SubScene(subSceneRoot, 0, 0, true, SceneAntialiasing.BALANCED);
    // 登录，注册容器
    private SignInBox signInBox = new SignInBox();
    private SignUpBox signUpBox = new SignUpBox();
    //关闭按钮
    private Button closeBut = new Button();
    //动画
    private TranslateTransition TT1 = new TranslateTransition(Duration.millis(500));
    private RotateTransition RT = new RotateTransition(Duration.millis(500));
    private ParallelTransition PT = new ParallelTransition(TT1, RT);
    private TranslateTransition TT2 = new TranslateTransition(Duration.millis(500));
    private SequentialTransition SEQ_T = new SequentialTransition(PT, TT2);
    private double translateX = -400;
    private double rotateAngle = 5;

    public Index() {
        //背景
        setBackdropImage(FxUtil.getImage("/img/backdrop.png"));
        //布局
        getChildren().addAll(subScene, closeBut);
        subScene.widthProperty().bind(widthProperty());
        subScene.heightProperty().bind(heightProperty());
        subSceneRoot.getChildren().addAll(signUpBox, signInBox);
        signUpBox.setMaxWidth(USE_PREF_SIZE);
        signInBox.setMaxWidth(USE_PREF_SIZE);
        signUpBox.setMaxHeight(USE_PREF_SIZE);
        signInBox.setMaxHeight(USE_PREF_SIZE);
        StackPane.setAlignment(signInBox, Pos.CENTER_RIGHT);
        StackPane.setAlignment(signUpBox, Pos.CENTER_RIGHT);
        StackPane.setMargin(signInBox, new Insets(50));
        StackPane.setMargin(signUpBox, new Insets(50));
        StackPane.setAlignment(closeBut, Pos.TOP_RIGHT);
        closeBut.setGraphic(new FontIcon(AntDesignIconsOutlined.CLOSE));
        closeBut.setOnMouseClicked(event -> closeBut.getParent().getScene().getWindow().hide());
        //动画
        signUpBox.setRotate(rotateAngle);
        signInBox.toSignUpClicked(event -> play(signInBox, signUpBox));
        signUpBox.toSignInClicked(event -> play(signUpBox, signInBox));
        // styleClass
        getStyleClass().add("index");
        closeBut.getStyleClass().add("close-but");
        subSceneRoot.getStylesheets().add(FxUtil.getResource("/css/project/index.css"));
        getStylesheets().add(FxUtil.getResource("/css/project/index.css"));
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
        this.subSceneRoot.setBackground(background);
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
    public class SignInBox extends StackPane {

        private VBox vBox = new VBox();
        private Label titleLabel = SimpleControl.getLabel("登录", SimpleControl.LabelEnum.H1);
        private CFAvatar avatar = new CFAvatar(64, 10);
        private StackPane titlePane = new StackPane(titleLabel, avatar);
        private CFTextField email = new CFTextField(CFTextField.Type.TEXT, new FontIcon(AntDesignIconsOutlined.MAIL));
        private CFTextField password = new CFTextField(CFTextField.Type.PASSWORD, new FontIcon(AntDesignIconsOutlined.KEY));
        private SimpleButton signIn = new SimpleButton("登录");
        private Hyperlink forget = SimpleControl.getHyperlink("忘记密码", SimpleControl.Level.PRIMARY);
        private StackPane bottomPane = new StackPane(forget);
        private Hyperlink toSignUp = SimpleControl.getHyperlink("没有账户？去注册", SimpleControl.Level.PRIMARY);
        //二维码登录
        private Label qrCodeLabel = new Label();

        public SignInBox() {
            //布局
            getChildren().addAll(vBox, qrCodeLabel);
            StackPane.setAlignment(qrCodeLabel, Pos.TOP_LEFT);
            vBox.getChildren().addAll(titlePane, email, password, signIn, bottomPane, toSignUp);
            StackPane.setAlignment(titleLabel, Pos.BOTTOM_LEFT);
            StackPane.setAlignment(avatar, Pos.BOTTOM_RIGHT);
            StackPane.setAlignment(forget, Pos.CENTER_RIGHT);
            bindWidthHeight(email, password, signIn);
            signIn.prefWidthProperty().bind(password.widthProperty());
            //属性
            qrCodeLabel.setGraphic(new FontIcon(AntDesignIconsOutlined.QRCODE));
            qrCodeLabel.setTooltip(SimpleControl.getTooltip("扫描登录"));
            avatar.setImage(FxUtil.getImage("/img/logo.jpg"));
            email.setPromptText("邮箱");
            password.setPromptText("密码");
            //styleClass
            vBox.getStyleClass().add("sign-in-box");
            qrCodeLabel.getStyleClass().add("qr-code-label");
            //裁剪二维码
            qrCodeLabel.widthProperty().addListener((observable, oldValue, newValue) -> {
                double value = newValue.doubleValue();
                Polyline polyline = new Polyline(0, 0, value, 0, 0, value, 0, 0);
                polyline.setFill(Color.WHITE);
                qrCodeLabel.setClip(polyline);
            });
        }

        public void toSignUpClicked(Consumer<MouseEvent> consumer) {
            this.toSignUp.setOnMouseClicked(event -> consumer.accept(event));
        }

    }

    /**
     * 注册
     */
    public class SignUpBox extends StackPane {

        private VBox vBox = new VBox();
        private Label titleLabel = SimpleControl.getLabel("注册", SimpleControl.LabelEnum.H1);
        private StackPane titlePane = new StackPane(titleLabel);
        private CFTextField user = new CFTextField(CFTextField.Type.TEXT, new FontIcon(AntDesignIconsOutlined.USER));
        private CFTextField email = new CFTextField(CFTextField.Type.TEXT, new FontIcon(AntDesignIconsOutlined.MAIL));
        private CFTextField password = new CFTextField(CFTextField.Type.PASSWORD, new FontIcon(AntDesignIconsOutlined.KEY));
        private SimpleButton signUp = new SimpleButton("注册");
        private Hyperlink toSignIn = SimpleControl.getHyperlink("已有账户，去登录", SimpleControl.Level.PRIMARY);

        public SignUpBox() {
            //布局
            getChildren().add(vBox);
            vBox.getChildren().addAll(titlePane, user, email, password, signUp, toSignIn);
            bindWidthHeight(user, email, password, signUp);
            StackPane.setAlignment(titleLabel, Pos.CENTER_LEFT);
            signUp.prefWidthProperty().bind(password.widthProperty());
            //属性
            user.setPromptText("用户名");
            email.setPromptText("邮箱");
            password.setPromptText("密码");
            //styleClass
            vBox.getStyleClass().add("sign-up-box");
        }

        public void toSignInClicked(Consumer<MouseEvent> consumer) {
            this.toSignIn.setOnMouseClicked(event -> consumer.accept(event));
        }
    }
}
