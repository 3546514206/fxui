package cn.lichenfei.fxui.controls;

import cn.lichenfei.fxui.common.FxUtil;
import cn.lichenfei.fxui.common.Level;
import javafx.animation.*;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.StackPane;
import javafx.util.Duration;
import org.kordamp.ikonli.antdesignicons.AntDesignIconsFilled;
import org.kordamp.ikonli.antdesignicons.AntDesignIconsOutlined;
import org.kordamp.ikonli.javafx.FontIcon;

public class CFMessage extends HBox {

    private Label iconLabel = new Label();
    private Label messLabel = new Label("消息提示！");
    private Label closeLabel = new Label();
    // 动画
    private TranslateTransition TT = new TranslateTransition(Duration.millis(300), this);
    private FadeTransition FT = new FadeTransition(Duration.millis(300), this);
    private ParallelTransition PPT = new ParallelTransition(TT, FT);
    private PauseTransition PT = new PauseTransition(Duration.seconds(1.3));// 停顿时间1.5相当三秒
    private SequentialTransition ST = new SequentialTransition(PPT, PT);

    private Level level;

    private CFMessage(String message, Level level) {
        setOpacity(1);// 初始化不显示
        this.level = level;
        messLabel.setText(message);
        initialize();
    }

    /**
     * 播放动画；默认存放到StackPane进行展示，展示位置：Pos.TOP_CENTER
     *
     * @param node
     * @param message
     */
    public static void show(StackPane node, String message) {
        CFMessage cfMessage = new CFMessage(message, Level.PRIMARY);
        node.getChildren().add(cfMessage);
        StackPane.setAlignment(cfMessage, Pos.TOP_CENTER);// 默认消息存放位置
        cfMessage.play();
    }

    private void initialize() {
        setMaxSize(USE_PREF_SIZE, USE_PREF_SIZE);
        getChildren().addAll(iconLabel, messLabel, closeLabel);
        HBox.setHgrow(messLabel, Priority.ALWAYS);
        iconLabel.setGraphic(new FontIcon(AntDesignIconsFilled.MESSAGE));
        closeLabel.setGraphic(new FontIcon(AntDesignIconsOutlined.CLOSE));
        //styleClass
        getStyleClass().add("cf-message");
        iconLabel.getStyleClass().add("icon-label");
        messLabel.getStyleClass().add("mess-label");
        closeLabel.getStyleClass().add("close-label");
        setAnimationInfo();
    }

    private void play() {
        ST.play();
    }

    private void setAnimationInfo() {
        TT.setFromY(0);
        TT.setToY(30);
        FT.setFromValue(0);
        FT.setToValue(1);
        ST.setCycleCount(2);
        ST.setAutoReverse(true);
        //鼠标移入暂停动画，移出继续动画
        hoverProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue) {
                ST.pause();
            } else {
                ST.play();
            }
        });
        // 终止动画
        closeLabel.setOnMouseClicked(event -> {
            event.consume();
            ST.stop();
            ((StackPane) this.getParent()).getChildren().remove(this);
        });
    }

    @Override
    public String getUserAgentStylesheet() {
        return FxUtil.getResource("/css/cf-message.css");
    }
}
