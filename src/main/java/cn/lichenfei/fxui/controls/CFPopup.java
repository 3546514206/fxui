package cn.lichenfei.fxui.controls;

import cn.lichenfei.fxui.common.FxUtil;
import cn.lichenfei.fxui.common.SimpleControl;
import javafx.animation.*;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.effect.BlurType;
import javafx.scene.effect.DropShadow;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.Popup;
import javafx.stage.Window;
import javafx.util.Duration;
import org.kordamp.ikonli.antdesignicons.AntDesignIconsOutlined;
import org.kordamp.ikonli.javafx.FontIcon;

public class CFPopup extends Popup {

    private double centerX = 0;
    private double centerY = 0;
    private DropShadow dropShadow = new DropShadow(BlurType.THREE_PASS_BOX, Color.rgb(0, 0, 0, 0.3), 10, 0, 0, 0);

    private Label titleLabel = SimpleControl.getLabel("标题", SimpleControl.LabelEnum.H4);
    private Label closeLabel = new Label();
    private StackPane top = new StackPane(titleLabel, closeLabel);
    private BorderPane borderPane = new BorderPane(null, top, null, null, null);

    //动画
    private Button TRANSITION_NODE = new Button();
    private Duration duration = Duration.millis(250);
    private FadeTransition FT = new FadeTransition(duration, TRANSITION_NODE);

    public CFPopup(Node main) {
        borderPane.setCenter(main);
        initialize();
    }

    private void initialize() {
        getContent().add(borderPane);
        borderPane.setMinSize(400, 200);
        borderPane.setBackground(new Background(new BackgroundFill(Color.WHITE, new CornerRadii(3), null)));
        borderPane.setPadding(new Insets(20));
        borderPane.setEffect(dropShadow);
        //
        FontIcon fontIcon = new FontIcon(AntDesignIconsOutlined.CLOSE);
        fontIcon.setIconSize(16);
        fontIcon.iconColorProperty().bind(titleLabel.textFillProperty());// 颜色和标题颜色一致
        closeLabel.setGraphic(fontIcon);
        closeLabel.setCursor(Cursor.HAND);
        StackPane.setAlignment(titleLabel, Pos.CENTER_LEFT);
        StackPane.setAlignment(closeLabel, Pos.CENTER_RIGHT);
        //
        setEvent();
        popupMove();
    }

    private void setEvent() {
        setAutoHide(true);

        this.opacityProperty().bind(TRANSITION_NODE.opacityProperty());// 绑定透明度
        TRANSITION_NODE.setOpacity(0);
        this.opacityProperty().addListener((observableValue, number, t1) -> {// 监听到透明度为0，执行hide();方法
            double v = t1.doubleValue();
            if (v <= 0 && isShowing()) {
                hide();
            }
        });
        //关闭
        closeLabel.setOnMouseClicked(event -> {
            FT.setFromValue(1);
            FT.setToValue(0);
            FT.play();
        });
        //窗口显示之后
        setOnShown(windowEvent -> {
            setAnchorX(centerX - getWidth() / 2);
            setAnchorY(centerY - getHeight() / 2);
            FT.setFromValue(0);
            FT.setToValue(1);
            FT.play();
        });
    }

    /**
     * 显示Popup
     *
     * @param owner
     */
    public void show(Node owner) {
        Window window = FxUtil.getWindow(owner);
        centerX = window.getX() + (window.getWidth() / 2);
        centerY = window.getY() + (window.getHeight() / 2);
        if (!isShowing()) {
            show(window);
        }
    }

    /**************************************************** 窗口拖动 ****************************************************/

    private double xOffset;
    private double yOffset;

    private void popupMove() {
        this.borderPane.setOnMousePressed(event -> {
            event.consume();
            this.xOffset = this.getX() - event.getScreenX();
            this.yOffset = this.getY() - event.getScreenY();
        });
        this.borderPane.setOnMouseDragged(event -> {
            event.consume();
            this.setX(event.getScreenX() + this.xOffset);
            this.setY(event.getScreenY() + this.yOffset);
        });
    }
}
