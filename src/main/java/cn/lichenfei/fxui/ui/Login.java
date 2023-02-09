package cn.lichenfei.fxui.ui;

import cn.lichenfei.fxui.controls.CFButton;
import cn.lichenfei.fxui.controls.CFTextField;
import javafx.beans.binding.DoubleBinding;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.geometry.Pos;
import javafx.scene.control.Control;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import org.kordamp.ikonli.antdesignicons.AntDesignIconsOutlined;
import org.kordamp.ikonli.javafx.FontIcon;

public class Login extends StackPane {

    // 基本属性
    private DoubleBinding DEFAULT_WIDTH = widthProperty().subtract(100);
    private DoubleBinding DEFAULT_HEIGHT = new SimpleDoubleProperty(40).subtract(0);
    // 登录，注册容器
    private SignInBox signInBox = new SignInBox();
    private SignUpBox signUpBox = new SignUpBox();

    public Login() {
        getChildren().addAll(signInBox, signUpBox);
        signInBox.translateYProperty().bind(heightProperty());
        //signUpBox.translateYProperty().bind(heightProperty());
    }

    /**
     * 绑定宽度和高度
     *
     * @param node
     */
    private void bindWidthHeight(Region... node) {
        for (int i = 0; i < node.length; i++) {
            node[i].prefWidthProperty().bind(DEFAULT_WIDTH);
            node[i].prefHeightProperty().bind(DEFAULT_HEIGHT);
        }
    }

    /**
     * 设置背景图片
     *
     * @param node
     * @param image
     */
    public void setBackdropImage(Control node, Image image) {
        BackgroundSize backgroundSize = new BackgroundSize(-1, -1, false, false, false, true);
        BackgroundImage backgroundImage = new BackgroundImage(image, null, null, BackgroundPosition.CENTER, backgroundSize);
        Background background = new Background(backgroundImage);
        node.setBackground(background);
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
            bindWidthHeight(email, password, signIn);
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
            bindWidthHeight(user, email, password, signUp);
        }
    }

}
