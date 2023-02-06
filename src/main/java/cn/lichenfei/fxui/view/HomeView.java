package cn.lichenfei.fxui.view;

import cn.lichenfei.fxui.common.FxUtils;
import cn.lichenfei.fxui.common.model.MenuInfo;
import cn.lichenfei.fxui.controls.CFCarousel;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import org.kordamp.ikonli.antdesignicons.AntDesignIconsFilled;
import org.kordamp.ikonli.antdesignicons.AntDesignIconsOutlined;
import org.kordamp.ikonli.javafx.FontIcon;

import java.util.Optional;

/**
 * @author ChenFei
 * @date 2022/12/9
 */
public class HomeView extends HBox {

    //顶部
    private Header header = new Header();
    //左侧导航
    private Aside aside = new Aside();
    private VBox container = new VBox();
    //主要区域
    private StackPane main = new StackPane();

    {
        //css
        this.getStylesheets().add(FxUtils.getCss("/css/home.css"));
        //布局
        this.getChildren().addAll(aside, container);
        HBox.setHgrow(container, Priority.ALWAYS);
        container.getChildren().addAll(header, main);
        VBox.setVgrow(main, Priority.ALWAYS);
        //class
        this.getStyleClass().add("home");
        aside.getStyleClass().add("aside");
        main.getStyleClass().add("main");
        header.getStyleClass().add("header");
    }

    public HomeView() {
        ListView<Aside.MenuItem> menu = aside.getMenu();
        //初始化菜单数据
        MenuInfo[] menuInfo = {
                new MenuInfo(new FontIcon(AntDesignIconsFilled.HOME), "首页", new StackPane()),
                new MenuInfo(new FontIcon(AntDesignIconsOutlined.TABLE), "表格/数据", new DataView()),
                new MenuInfo(new FontIcon(AntDesignIconsOutlined.AREA_CHART), "统计图", new ChartView()),
                new MenuInfo(new FontIcon(AntDesignIconsOutlined.CREDIT_CARD), "卡片", new CardView()),
                new MenuInfo(new FontIcon(AntDesignIconsOutlined.COMMENT), "轮播", CFCarousel.create(null, 500, 300, true))
        };
        aside.setMenuInfo(menuInfo);
        //菜单选中事件监听
        menu.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            Optional.ofNullable(newValue).ifPresent(menuItem -> {
                header.getTitleLabel().setText(menuItem.getNameLabel().getText());
                main.getChildren().set(0, menuItem.getContent());
            });
        });
        //选中
        main.getChildren().add(menuInfo[4].getContent());
        menu.getSelectionModel().select(4);
    }

    /**
     * 顶栏
     */
    public class Header extends HBox {
        private Label titleLabel = new Label();
        private Label msgLabel = new Label("这是一条消息提示");

        {
            this.getChildren().addAll(titleLabel, msgLabel);
            HBox.setHgrow(msgLabel, Priority.ALWAYS);
            msgLabel.setMaxWidth(Double.MAX_VALUE);
            msgLabel.setGraphic(new FontIcon(AntDesignIconsFilled.MESSAGE));
            titleLabel.getStyleClass().add("title-label");
            msgLabel.getStyleClass().add("msg-label");
        }

        public Label getTitleLabel() {
            return titleLabel;
        }
    }

    /**
     * 左侧导航
     */
    public class Aside extends VBox {
        //上侧
        private StackPane top = new StackPane();
        private Pane userLogo = new Pane();
        private ImageView logoImg = new ImageView();
        private Label userLabel = new Label("你好！小羊人");
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
        }

        public void setMenuInfo(MenuInfo[] menuInfo) {
            for (int i = 0; i < menuInfo.length; i++) {
                MenuInfo info = menuInfo[i];
                MenuItem menuItem = new MenuItem(info.getIcon(), info.getName(), info.getContent());
                menu.getItems().addAll(menuItem);
            }
        }

        public ListView<MenuItem> getMenu() {
            return menu;
        }

        public class MenuItem extends HBox {
            private Label iconLabel = new Label();
            private Label nameLabel = new Label();
            private Node content;

            {
                this.getChildren().addAll(iconLabel, nameLabel);
                //styleClass
                this.getStyleClass().add("menu-item");
                iconLabel.getStyleClass().add("icon-label");
                nameLabel.getStyleClass().add("name-label");
                this.prefWidthProperty().bind(menu.widthProperty().subtract(3));
            }

            public MenuItem(Node icon, String name, Node content) {
                this.iconLabel.setGraphic(icon);
                this.nameLabel.setText(name);
                this.content = content;
            }

            public Label getNameLabel() {
                return nameLabel;
            }

            public Node getContent() {
                return content;
            }
        }
    }
}
