package cn.lichenfei.fxui.project;

import cn.lichenfei.fxui.FxApplication;
import cn.lichenfei.fxui.common.FxUtil;
import cn.lichenfei.fxui.common.SimpleControl;
import cn.lichenfei.fxui.controls.CFAvatar;
import cn.lichenfei.fxui.controls.CFPopup;
import javafx.application.HostServices;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class UserDetail extends VBox {

    private static final String STYLE_SHEET = FxUtil.getResource("/css/project/user-detail.css");
    //
    private CFAvatar cfAvatar = new CFAvatar(FxUtil.getImage("/img/avatar.png"), 100, 100);
    private Label nameLabel = new Label("lichenfei_fei");
    private Label emailLabel = new Label("lichenfei_fei@163.com");
    private Label introductionLabel = new Label("1、3、5写bug，2、4、6改bug。");
    //
    private Button gitee = new Button();
    private Button javafx = new Button();
    private Button reward = new Button();
    private HBox buttonBox = new HBox(gitee, javafx, reward);
    //
    private CFPopup cfPopup;

    public UserDetail() {
        getChildren().addAll(cfAvatar, nameLabel, emailLabel, introductionLabel, buttonBox);
        gitee.setGraphic(FxUtil.getIconImage("/img/gitee.png", 30));
        javafx.setGraphic(FxUtil.getIconImage("/img/javafx.png", 30));
        reward.setGraphic(FxUtil.getIconImage("/img/reward.png", 30));
        gitee.setTooltip(SimpleControl.getTooltip("项目仓库地址"));
        javafx.setTooltip(SimpleControl.getTooltip("JavaFX中文官方网站"));
        reward.setTooltip(SimpleControl.getTooltip("鼓励一下作者"));
        //
        getStyleClass().add("user-detail");
        nameLabel.getStyleClass().add("name-label");
        emailLabel.getStyleClass().add("email-label");
        introductionLabel.getStyleClass().add("introduction-label");
        buttonBox.getStyleClass().add("button-box");
        //
        HostServices hostServices = FxApplication.hostServices;
        gitee.setOnMouseClicked(event -> hostServices.showDocument("https://gitee.com/lichenfei_fei/chenfei-fxui"));
        javafx.setOnMouseClicked(event -> hostServices.showDocument("https://openjfx.cn/index.html"));
        reward.setOnMouseClicked(event -> {
            cfPopup = new CFPopup(new RewardBox());
            cfPopup.show(this);
        });
    }

    @Override
    public String getUserAgentStylesheet() {
        return STYLE_SHEET;
    }
}
