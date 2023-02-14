package cn.lichenfei.fxui.ui;

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
import javafx.scene.Node;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.util.Duration;
import org.kordamp.ikonli.antdesignicons.AntDesignIconsOutlined;
import org.kordamp.ikonli.javafx.FontIcon;

import java.util.function.Consumer;

public class Login extends StackPane {

    private DoubleProperty DEFAULT_HEIGHT = new SimpleDoubleProperty(40);
    // 登录，注册容器
    private SignInBox signInBox = new SignInBox();
    private SignUpBox signUpBox = new SignUpBox();

    //动画
    private TranslateTransition TT = new TranslateTransition(Duration.millis(500));
    private FadeTransition RT_OUT = new FadeTransition(Duration.millis(500));
    private ParallelTransition PT = new ParallelTransition(TT, RT_OUT);
    private FadeTransition RT_IN = new FadeTransition(Duration.millis(500));
    private SequentialTransition SEQ_T = new SequentialTransition(PT, RT_IN);

    public Login() {
        getChildren().addAll(signInBox);
        //动画监听
        signInBox.toSignUpClicked(event -> play(signInBox, signUpBox));
        signUpBox.toSignInClicked(event -> play(signUpBox, signInBox));
        setPadding(new Insets(0, 40, 0, 40));
    }

    private void play(Node out, Node in) {
        // 消失动画
        RT_OUT.setNode(out);
        RT_OUT.setFromValue(1);
        RT_OUT.setToValue(0);
        RT_OUT.setOnFinished(event -> {
            getChildren().remove(out);
            getChildren().add(in);
        });
        TT.setNode(out);
        TT.setFromY(0);
        TT.setToY(40);
        // 显现动画
        RT_IN.setFromValue(0);
        RT_IN.setToValue(1);
        RT_IN.setNode(in);
        SEQ_T.play();
    }

    private void bindWidthHeight(Region... node) {
        for (int i = 0; i < node.length; i++) {
            node[i].setMaxWidth(USE_COMPUTED_SIZE);
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
            setAlignment(Pos.BOTTOM_CENTER);
            setSpacing(20);
            setPadding(new Insets(0, 0, 50, 0));
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
        private Hyperlink toSignIn = SimpleControl.getHyperlink("已有账户？去登录", SimpleControl.Level.PRIMARY);

        public SignUpBox() {
            getChildren().addAll(titlePane, user, email, password, signUp, toSignIn);
            bindWidthHeight(user, email, password, signUp);
            StackPane.setAlignment(titleLabel, Pos.CENTER_LEFT);
            setAlignment(Pos.BOTTOM_CENTER);
            setSpacing(20);
            setPadding(new Insets(0, 0, 50, 0));
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

}
