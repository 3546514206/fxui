package cn.lichenfei.fxui.controls;

import cn.lichenfei.fxui.common.Level;
import javafx.animation.*;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.layout.StackPane;
import javafx.util.Duration;

public class CFMessage extends CFAlert {

    private double maxWidth = 300;
    // 动画
    private TranslateTransition TT = new TranslateTransition(Duration.millis(300), this);
    private FadeTransition FT = new FadeTransition(Duration.millis(300), this);
    private ParallelTransition PPT = new ParallelTransition(TT, FT);
    private PauseTransition PT = new PauseTransition(Duration.seconds(1.3));// 停顿时间1.5相当三秒
    private SequentialTransition ST = new SequentialTransition(PPT, PT);

    private CFMessage(String message) {
        super(message, Level.PRIMARY);
    }

    private CFMessage(String message, Level level) {
        super(message, level);
    }

    public static CFMessage init(String message) {
        CFMessage cfMessage = new CFMessage(message);
        cfMessage.setMaxSize();
        cfMessage.setOpacity(0);// 初始化不显示
        cfMessage.setAnimationInfo();
        return cfMessage;
    }

    public CFMessage setLevel(Level level) {
        this.levelProperty.set(level);
        return this;
    }

    /**
     * 播放动画；默认存放到StackPane进行展示，展示位置：Pos.TOP_CENTER
     *
     * @param node
     */
    public void show(Node node) {
        show(node, true);
    }

    public void show(Node node, boolean showClose) {
        try {
            CFStage cfStage = (CFStage) node.getParent().getScene().getWindow();
            StackPane backdrop = cfStage.getBackdrop();
            backdrop.getChildren().add(this);
            StackPane.setAlignment(this, Pos.TOP_CENTER);// 默认消息存放位置
            this.showClose(showClose);
            this.play();// 开始动画
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void setPadding() {
        setPadding(new Insets(15, 15, 15, 15));
    }

    private void setMaxSize() {
        this.setMaxSize(USE_PREF_SIZE, USE_PREF_SIZE);
        this.messLabel.setMaxSize(maxWidth, USE_PREF_SIZE);
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
        ST.setOnFinished(event -> destroy());
        // 终止动画
        closeLabel.setOnMouseClicked(event -> {
            event.consume();
            ST.stop();
            destroy();
        });
    }
}
