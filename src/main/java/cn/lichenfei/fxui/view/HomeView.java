package cn.lichenfei.fxui.view;

import cn.lichenfei.fxui.common.FxUtils;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Control;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import org.kordamp.ikonli.antdesignicons.AntDesignIconsOutlined;
import org.kordamp.ikonli.javafx.FontIcon;

/**
 * @author ChenFei
 * @date 2022/12/9
 */
public class HomeView extends HBox {

    //左侧导航
    private Aside aside = new Aside();
    //右侧内容
    private StackPane main = new StackPane();

    {
        //css
        this.getStylesheets().add(FxUtils.getCss("/css/home.css"));
        //布局
        this.getChildren().addAll(aside, main);
        HBox.setHgrow(main, Priority.ALWAYS);
        //class
        this.getStyleClass().add("home");
        aside.getStyleClass().add("aside");
        main.getStyleClass().add("main");
    }

    /**
     * 左侧导航
     */
    public class Aside extends VBox {
        //上侧
        private StackPane top = new StackPane();
        private Pane userLogo = new Pane();
        private ImageView logoImg = new ImageView();
        private Label userLabel = new Label("你好！ChenFei Li");
        private Label bellLabel = new Label();
        private Label userMenu = new Label();
        //菜单栏
        private ListView<MenuItem> menu = new ListView();
        //下侧
        private StackPane bottom = new StackPane();
        private Label logoutLabel = new Label("退出登录");

        {
            this.getChildren().addAll(top, menu, bottom);
            VBox.setVgrow(menu, Priority.ALWAYS);
            //top
            top.getChildren().addAll(userLogo, userLabel, bellLabel, userMenu);
            StackPane.setAlignment(userLogo, Pos.TOP_CENTER);
            StackPane.setAlignment(userLabel, Pos.BOTTOM_CENTER);
            StackPane.setAlignment(bellLabel, Pos.TOP_RIGHT);
            StackPane.setAlignment(userMenu, Pos.TOP_LEFT);
            userLogo.getChildren().add(logoImg);
            userLogo.setMaxWidth(Control.USE_PREF_SIZE);
            userLogo.setMaxHeight(Control.USE_PREF_SIZE);
            logoImg.setImage(FxUtils.getImage("/img/logo.jpg"));
            logoImg.setFitHeight(60);
            logoImg.setFitWidth(60);
            FxUtils.setClip(userLogo, 60);
            bellLabel.setGraphic(new FontIcon(AntDesignIconsOutlined.BELL));
            userMenu.setGraphic(new FontIcon(AntDesignIconsOutlined.MENU));
            //bottom
            bottom.getChildren().addAll(logoutLabel);
            logoutLabel.setGraphic(new FontIcon(AntDesignIconsOutlined.LOGOUT));
            //class
            top.getStyleClass().add("top");
            menu.getStyleClass().addAll("scroll-bar-style", "menu");
            bottom.getStyleClass().add("bottom");
            userLabel.getStyleClass().add("user-label");
            userLogo.getStyleClass().add("user-logo");
            bellLabel.getStyleClass().add("bell-label");
            userMenu.getStyleClass().add("user-menu");
            logoutLabel.getStyleClass().add("logout-label");
            //初始化菜单数据
            for (int i = 0; i < 30; i++) {
                menu.getItems().add(new MenuItem("表格"));
            }
        }

    }

    public class MenuItem extends HBox {
        private Label iLabel = new Label();
        private Label textLabel = new Label();

        {
            this.getChildren().addAll(iLabel, textLabel);
            HBox.setHgrow(textLabel, Priority.ALWAYS);
            //class
            this.getStyleClass().add("menu-item");
            iLabel.getStyleClass().add("i-label");
            textLabel.getStyleClass().add("text-label");
        }

        public MenuItem(String text) {
            this.textLabel.setText(text);
        }

    }
}
