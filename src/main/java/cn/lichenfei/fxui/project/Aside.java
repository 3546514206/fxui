package cn.lichenfei.fxui.project;

import cn.lichenfei.fxui.common.FxUtil;
import cn.lichenfei.fxui.common.SimpleControl;
import cn.lichenfei.fxui.controls.CFAvatar;
import cn.lichenfei.fxui.controls.CFBadge;
import cn.lichenfei.fxui.controls.CFPopup;
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

/**
 * 左侧导航栏
 */
public class Aside extends StackPane {

    private static final String STYLE_SHEET = FxUtil.getResource("/css/project/aside.css");

    //LOGO
    private Label titleLabel = new Label("CHENFEI-FXUI");
    // 固定导航栏
    private NavItem setting = new NavItem(new FontIcon(AntDesignIconsFilled.SETTING), "设置", null);
    private NavItem notification = new NavItem(new FontIcon(AntDesignIconsFilled.NOTIFICATION), "通知", null);
    private VBox fixedNav = new VBox(setting, notification);
    // 动态导航栏
    private ListView<NavItem> menu = new ListView<>();
    // 用户栏
    private CFAvatar avatar = new CFAvatar(FxUtil.getImage("/img/avatar.png"), 36, 36); // 用户头像
    private Label userLabel = new Label("ChenFei");
    private Label emailLabel = new Label("admin@admin.com");
    private VBox userInfoBox = new VBox(userLabel, emailLabel);
    private HBox userBox = new HBox(avatar, userInfoBox);

    private VBox asideContainer = new VBox(titleLabel, fixedNav, SimpleControl.getSeparator(), menu, SimpleControl.getSeparator(), userBox);

    public Aside() {
        initialize();
        //测试数据
        notification.getCfBadge().setValue(3);
        // 模拟数据
        menu.getItems().addAll(
                new NavItem(new FontIcon(AntDesignIconsFilled.CONTROL), "菜单一", new StackPane()),
                new NavItem(new FontIcon(AntDesignIconsOutlined.PLAY_CIRCLE), "菜单二", new StackPane())

        );
        // 默认选择第一个
        boundsInParentProperty().addListener((observableValue, bounds, t1) -> menu.getSelectionModel().select(0));
    }

    private void initialize() {
        getStylesheets().add(STYLE_SHEET);//加载css
        getChildren().addAll(asideContainer);
        VBox.setVgrow(menu, Priority.ALWAYS);
        HBox.setHgrow(userInfoBox, Priority.ALWAYS);
        //styleClass
        asideContainer.getStyleClass().add("aside");
        this.titleLabel.getStyleClass().add("title-label");
        this.menu.getStyleClass().addAll("scroll-bar-style", "menu");
        this.userBox.getStyleClass().add("user-box");
        this.userInfoBox.getStyleClass().add("user-info-box");
        this.userLabel.getStyleClass().add("user-label");
        this.emailLabel.getStyleClass().add("email-label");
        setEvent();
    }

    public void setEvent() {
        menuSelectedListener();
        //
        CFPopup label = new CFPopup(new Label("Label"));
        this.userBox.setOnMouseClicked(event -> {
            //
            label.show(this.userBox);
        });
    }


    //菜单选中事件监听
    private void menuSelectedListener() {
        menu.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            Optional.ofNullable(newValue).ifPresent(menuItem -> {
                FxUtil.getCFStage(this).setContent(menuItem.content);//设置内容
            });
        });
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
