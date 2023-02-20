package cn.lichenfei.fxui.project;

import cn.lichenfei.fxui.common.FxUtil;
import cn.lichenfei.fxui.common.SimpleControl;
import cn.lichenfei.fxui.controls.CFAvatar;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import org.kordamp.ikonli.antdesignicons.AntDesignIconsFilled;
import org.kordamp.ikonli.antdesignicons.AntDesignIconsOutlined;
import org.kordamp.ikonli.javafx.FontIcon;

import java.util.Optional;

public class Home extends HBox {

    private static final String STYLE_SHEET = FxUtil.getResource("/css/project/home.css");

    private Aside aside = new Aside();
    private StackPane main = new StackPane();

    public Home() {
        initialize();
    }

    private void initialize() {
        getChildren().addAll(this.aside, this.main);
        getStylesheets().add(STYLE_SHEET);// 加载css
        HBox.setHgrow(this.main, Priority.ALWAYS);
    }

    /**
     * 左侧导航栏
     */
    public class Aside extends StackPane {

        // 动态导航栏
        private ListView<NavItem> menu = new ListView<>();

        // 固定导航栏
        private NavItem notification = new NavItem(new FontIcon(AntDesignIconsFilled.NOTIFICATION), "通知");
        private NavItem setting = new NavItem(new FontIcon(AntDesignIconsFilled.SETTING), "设置");
        private NavItem help = new NavItem(new FontIcon(AntDesignIconsFilled.QUESTION_CIRCLE), "帮助");
        private VBox fixedNav = new VBox(notification, setting, help);

        // 底部用户栏
        private CFAvatar avatar = new CFAvatar(FxUtil.getImage("/img/logo.jpg"), 36, 36); // 用户头像
        private Label userLabel = SimpleControl.getLabel("ChenFei");// 用户名
        private Label userType = SimpleControl.getLabel("admin", SimpleControl.LabelEnum.TEXT_SMALL); // 用户类型
        private VBox userInfoBox = new VBox(userLabel, userType);
        private Label moreLabel = new Label();
        private HBox userBox = new HBox(avatar, userInfoBox, moreLabel);

        private VBox vBox = new VBox(menu, fixedNav, userBox);

        public Aside() {
            initialize();
            // 模拟数据
            menu.getItems().addAll(
                    new NavItem(new FontIcon(AntDesignIconsFilled.HOME), "首页"),
                    new NavItem(new FontIcon(AntDesignIconsFilled.PIE_CHART), "统计图")
            );
        }

        private void initialize() {
            getChildren().addAll(this.vBox);
            VBox.setVgrow(menu,Priority.ALWAYS);
            HBox.setHgrow(userInfoBox, Priority.ALWAYS);
            this.moreLabel.setGraphic(new FontIcon(AntDesignIconsOutlined.MORE));
            //styleClass
            getStyleClass().add("aside");
            this.menu.getStyleClass().addAll("scroll-bar-style", "menu");
            this.userBox.getStyleClass().add("user-box");
            this.userInfoBox.getStyleClass().add("user-info-box");
            this.moreLabel.getStyleClass().add("more-label");
            //菜单选中事件监听
            menu.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
                Optional.ofNullable(newValue).ifPresent(menuItem -> {
                    //main.getChildren().set(0, menuItem.getContent());
                });
            });
        }


        public class NavItem extends HBox {
            private Label iconLabel = new Label();
            private Label nameLabel = new Label();

            public NavItem(FontIcon fontIcon, String name) {
                this.iconLabel.setGraphic(fontIcon);
                this.nameLabel.setText(name);
                initialize();
            }

            private void initialize() {
                getChildren().addAll(iconLabel, nameLabel);
                //
                getStyleClass().add("nav-item");
                iconLabel.getStyleClass().add("icon-label");
                nameLabel.getStyleClass().add("name-label");
            }
        }
    }
}
