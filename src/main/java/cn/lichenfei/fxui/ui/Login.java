package cn.lichenfei.fxui.ui;

import cn.lichenfei.fxui.common.SimpleButton;
import cn.lichenfei.fxui.common.SimpleControl;
import cn.lichenfei.fxui.controls.CFTextField;
import javafx.animation.FadeTransition;
import javafx.animation.ParallelTransition;
import javafx.animation.TranslateTransition;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Control;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.util.Duration;
import org.kordamp.ikonli.antdesignicons.AntDesignIconsOutlined;
import org.kordamp.ikonli.javafx.FontIcon;

import java.util.function.Consumer;

public class Login extends StackPane {

    // 登录，注册容器
    private SignInBox signInBox = new SignInBox();
    private SignUpBox signUpBox = new SignUpBox();

    //动画
    private TranslateTransition TT = new TranslateTransition(Duration.millis(500));
    private FadeTransition RT = new FadeTransition(Duration.millis(500));
    private ParallelTransition PT = new ParallelTransition(TT, RT);

    public Login() {
        getChildren().addAll(signInBox);
        //动画监听
        signInBox.toSignUpClicked(event -> {
            play(this.signInBox, false);
        });
        signUpBox.toSignInClicked(event -> {
            play(this.signUpBox, false);
        });
        setSignInBoxSignUpStyle(this.signUpBox);
        setSignInBoxSignUpStyle(this.signInBox);
    }

    private void play(Node node, boolean in) {
        RT.setNode(node);
        RT.setFromValue(in ? 0 : 1);
        RT.setToValue(in ? 1 : 0);
        //
        TT.setNode(node);
        TT.setFromY(in ? 300 : 0);
        TT.setToY(in ? 0 : 300);
        //
        PT.setAutoReverse(true);
        PT.setCycleCount(2);
        PT.play();
    }

    // 设置登录，注册容器样式
    private void setSignInBoxSignUpStyle(VBox node) {
        node.setStyle("-fx-background-color:rgb(242,242,242);-fx-background-radius:5px;");
        node.setAlignment(Pos.BOTTOM_CENTER);
        node.setSpacing(20);
        node.setPadding(new Insets(50, 0, 50, 0));
        node.prefWidthProperty().bind(widthProperty().subtract(50));
        node.setMaxHeight(Control.USE_PREF_SIZE);
        node.setMaxWidth(Control.USE_PREF_SIZE);
        StackPane.setAlignment(node, Pos.BOTTOM_CENTER);
        StackPane.setMargin(node, new Insets(0, 0, 50, 0));
    }

    /**
     * 登录
     */
    public class SignInBox extends VBox {

        private Label title = SimpleControl.getLabel("登录", SimpleControl.LabelEnum.TEXT_DEFAULT);
        private CFTextField email = new CFTextField(CFTextField.Type.TEXT, new FontIcon(AntDesignIconsOutlined.MAIL));
        private CFTextField password = new CFTextField(CFTextField.Type.PASSWORD, new FontIcon(AntDesignIconsOutlined.KEY));
        private SimpleButton signIn = new SimpleButton("登录");
        private Hyperlink toSignUp = new Hyperlink("没有账户？去注册！");

        public SignInBox() {
            getChildren().addAll(title, email, password, signIn, toSignUp);
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

        private Label title = SimpleControl.getLabel("注册", SimpleControl.LabelEnum.H1);
        private CFTextField user = new CFTextField(CFTextField.Type.TEXT, new FontIcon(AntDesignIconsOutlined.USER));
        private CFTextField email = new CFTextField(CFTextField.Type.TEXT, new FontIcon(AntDesignIconsOutlined.MAIL));
        private CFTextField password = new CFTextField(CFTextField.Type.PASSWORD, new FontIcon(AntDesignIconsOutlined.KEY));
        private SimpleButton signUp = new SimpleButton("注册");
        private Hyperlink toSignIn = new Hyperlink("已有账户？去登录！");

        public SignUpBox() {
            getChildren().addAll(title, user, email, password, signUp, toSignIn);
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
