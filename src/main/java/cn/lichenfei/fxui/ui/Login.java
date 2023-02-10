package cn.lichenfei.fxui.ui;

import cn.lichenfei.fxui.common.FxUtils;
import cn.lichenfei.fxui.controls.CFButton;
import cn.lichenfei.fxui.controls.CFTextField;
import javafx.beans.binding.DoubleBinding;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Control;
import javafx.scene.effect.Lighting;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.paint.CycleMethod;
import javafx.scene.paint.LinearGradient;
import javafx.scene.paint.Stop;
import javafx.scene.shape.Rectangle;
import org.kordamp.ikonli.antdesignicons.AntDesignIconsOutlined;
import org.kordamp.ikonli.javafx.FontIcon;

public class Login extends StackPane {

    // 基本属性
    private DoubleBinding DEFAULT_WIDTH = widthProperty().subtract(100);
    private DoubleBinding DEFAULT_HEIGHT = new SimpleDoubleProperty(45).subtract(0);
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
     * 背景
     */
    public class Backdrop extends StackPane {
        private Image image;
        private ImageView imageView = new ImageView();
        private Rectangle rectangle = new Rectangle();

        public Backdrop(Image image) {
            this.image = image;
            setLayout();
        }

        private void setLayout() {
            getChildren().addAll(imageView, rectangle);
            StackPane.setAlignment(imageView, Pos.BOTTOM_CENTER);
            StackPane.setAlignment(rectangle, Pos.BOTTOM_CENTER);
            imageView.setImage(image);
            imageView.fitWidthProperty().bind(widthProperty());
            imageView.setEffect(new Lighting());
            imageView.fitHeightProperty().bind(heightProperty().divide(2));
            rectangle.heightProperty().bind(imageView.fitHeightProperty());
            rectangle.widthProperty().bind(imageView.fitWidthProperty());
            //背景渐变
            Stop[] stops = new Stop[]{new Stop(0, Color.WHITE), new Stop(1, Color.rgb(255, 255, 255, 0))};
            LinearGradient lg1 = new LinearGradient(1, 0, 1, 1, true, CycleMethod.NO_CYCLE, stops);
            rectangle.setFill(lg1);
        }
    }


    /**
     * 登录
     */
    public class SignInBox extends StackPane {

        private VBox container = new VBox();
        private CFTextField email = new CFTextField(CFTextField.Type.TEXT, new FontIcon(AntDesignIconsOutlined.MAIL));
        private CFTextField password = new CFTextField(CFTextField.Type.PASSWORD, new FontIcon(AntDesignIconsOutlined.KEY));
        private CFButton signIn = new CFButton("登录");

        public SignInBox() {
            getChildren().addAll(new Backdrop(FxUtils.getImage("/img/backdrop1.png")), container);
            container.getChildren().addAll(email, password, signIn);
            signIn.prefWidthProperty().bind(password.widthProperty());
            container.setAlignment(Pos.TOP_CENTER);
            container.setSpacing(20);
            container.setPadding(new Insets(100, 0, 0, 0));
            bindWidthHeight(email, password, signIn);
            //
            email.setPromptText("邮箱");
            password.setPromptText("密码");
        }
    }

    /**
     * 注册
     */
    public class SignUpBox extends StackPane {

        private VBox container = new VBox();
        private CFTextField user = new CFTextField(CFTextField.Type.TEXT, new FontIcon(AntDesignIconsOutlined.USER));
        private CFTextField email = new CFTextField(CFTextField.Type.TEXT, new FontIcon(AntDesignIconsOutlined.MAIL));
        private CFTextField password = new CFTextField(CFTextField.Type.PASSWORD, new FontIcon(AntDesignIconsOutlined.KEY));
        private CFButton signUp = new CFButton("注册");

        public SignUpBox() {
            getChildren().addAll(new Backdrop(FxUtils.getImage("/img/backdrop2.png")), container);
            container.getChildren().addAll(user, email, password, signUp);
            signUp.prefWidthProperty().bind(password.widthProperty());
            container.setAlignment(Pos.TOP_CENTER);
            container.setSpacing(20);
            container.setPadding(new Insets(100, 0, 0, 0));
            bindWidthHeight(user, email, password, signUp);// 绑定组件宽度和高度
            //
            user.setPromptText("用户名");
            email.setPromptText("邮箱");
            password.setPromptText("密码");
        }
    }

    public class MyVBox extends VBox {

        public MyVBox(Node... nodes) {
            super(nodes);
            setMaxHeight(Control.USE_PREF_SIZE);
            setMaxWidth(Control.USE_PREF_SIZE);
        }
    }
}
