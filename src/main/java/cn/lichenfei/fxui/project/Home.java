package cn.lichenfei.fxui.project;

import cn.lichenfei.fxui.common.FxUtil;
import cn.lichenfei.fxui.common.SimpleControl;
import cn.lichenfei.fxui.controls.CFAvatar;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import org.kordamp.ikonli.antdesignicons.AntDesignIconsOutlined;
import org.kordamp.ikonli.javafx.FontIcon;

public class Home extends HBox {

    private Aside aside = new Aside();
    private StackPane main = new StackPane();

    public Home() {
        initialize();
    }

    private void initialize() {
        getChildren().addAll(this.aside, this.main);
        HBox.setHgrow(this.main, Priority.ALWAYS);
    }

    @Override
    public String getUserAgentStylesheet() {
        return FxUtil.getResource("/css/project/home.css");
    }

    /**
     * 左侧导航栏
     */
    public class Aside extends StackPane {
        private VBox vBox = new VBox();

        //底部用户栏
        private CFAvatar avatar = new CFAvatar(FxUtil.getImage("/img/logo.jpg"), 36, 36); // 用户头像
        private Label userLabel = SimpleControl.getLabel("ChenFei");// 用户名
        private Label userType = SimpleControl.getLabel("admin", SimpleControl.LabelEnum.TEXT_SMALL); // 用户类型
        private VBox userInfoBox = new VBox(userLabel, userType);
        private Label moreLabel = new Label();
        private HBox userBox = new HBox(avatar, userInfoBox, moreLabel);

        public Aside() {
            initialize();
        }

        private void initialize() {
            getChildren().addAll(this.vBox);
            this.vBox.getChildren().addAll(this.userBox);
            HBox.setHgrow(userInfoBox, Priority.ALWAYS);
            this.moreLabel.setGraphic(new FontIcon(AntDesignIconsOutlined.MORE));
            //styleClass
            getStyleClass().add("aside");
            this.userBox.getStyleClass().add("user-box");
            this.userInfoBox.getStyleClass().add("user-info-box");
            this.moreLabel.getStyleClass().add("more-label");
        }
    }
}
