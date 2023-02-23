package cn.lichenfei.fxui.project;

import cn.lichenfei.fxui.common.FxUtil;
import cn.lichenfei.fxui.common.SimpleControl;
import cn.lichenfei.fxui.controls.CFAvatar;
import cn.lichenfei.fxui.controls.CFBadge;
import javafx.collections.ObservableList;
import javafx.scene.Node;
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
import java.util.function.Consumer;

public class Home extends HBox {

    private static final String STYLE_SHEET = FxUtil.getResource("/css/project/home.css");

    private Aside aside = new Aside();
    private StackPane main = new StackPane();

    public Home() {
        initialize();
        aside.menuSelected(node -> { // 菜单选择监听
            if (main.getChildren().isEmpty()) {
                main.getChildren().add(node);
            } else {
                main.getChildren().set(0, node);
            }
        });
        // 测试
        ObservableList<NavItem> menuItems = aside.menu.getItems();
        // 模拟数据
        menuItems.addAll(
                new NavItem(new FontIcon(AntDesignIconsFilled.CONTROL), "Effect-效果", new EffectExamples()),
                new NavItem(new FontIcon(AntDesignIconsOutlined.PLAY_CIRCLE), "动画效果", new AnimationExamples())

        );
        // 默认选择第一个
        aside.menu.getSelectionModel().select(0);
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
        private NavItem notification = new NavItem(new FontIcon(AntDesignIconsFilled.NOTIFICATION), "通知", null);
        private NavItem setting = new NavItem(new FontIcon(AntDesignIconsFilled.SETTING), "设置", null);
        private VBox fixedNav = new VBox(notification, setting);

        // 用户栏
        private CFAvatar avatar = new CFAvatar(FxUtil.getImage("/img/logo.jpg"), 36, 10); // 用户头像
        private Label userLabel = SimpleControl.getLabel("ChenFei");// 用户名
        private Label userType = SimpleControl.getLabel("admin", SimpleControl.LabelEnum.TEXT_SMALL); // 用户类型
        private VBox userInfoBox = new VBox(userLabel, userType);
        private Label moreLabel = new Label();
        private HBox userBox = new HBox(avatar, userInfoBox, moreLabel);

        private VBox vBox = new VBox(userBox, fixedNav, SimpleControl.getSeparator(), menu);

        public Aside() {
            initialize();
            notification.getCfBadge().setValue(3);
        }

        public void menuSelected(Consumer<Node> consumer) {
            //菜单选中事件监听
            menu.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
                Optional.ofNullable(newValue).ifPresent(menuItem -> {
                    consumer.accept(menuItem.content);
                });
            });
        }

        private void initialize() {
            getChildren().addAll(this.vBox);
            VBox.setVgrow(menu, Priority.ALWAYS);
            HBox.setHgrow(userInfoBox, Priority.ALWAYS);
            this.moreLabel.setGraphic(new FontIcon(AntDesignIconsOutlined.ELLIPSIS));
            //styleClass
            getStyleClass().add("aside");
            this.menu.getStyleClass().addAll("scroll-bar-style", "menu");
            this.userBox.getStyleClass().add("user-box");
            this.userInfoBox.getStyleClass().add("user-info-box");
            this.moreLabel.getStyleClass().add("more-label");
        }
    }

    public class NavItem extends HBox {
        private Label iconLabel = new Label();
        private Label nameLabel = new Label();
        private CFBadge cfBadge = new CFBadge();
        private Node content;

        public NavItem(FontIcon fontIcon, String name, Node content) {
            this.content = content;
            this.iconLabel.setGraphic(fontIcon);
            this.nameLabel.setText(name);
            initialize();
        }

        private void initialize() {
            getChildren().addAll(iconLabel, nameLabel, cfBadge);
            nameLabel.setMaxSize(Double.MAX_VALUE, USE_PREF_SIZE);
            HBox.setHgrow(nameLabel, Priority.ALWAYS);
            //
            getStyleClass().add("nav-item");
            iconLabel.getStyleClass().add("icon-label");
            nameLabel.getStyleClass().add("name-label");
            prefWidthProperty().bind(this.widthProperty());
        }

        public CFBadge getCfBadge() {
            return cfBadge;
        }
    }
}
