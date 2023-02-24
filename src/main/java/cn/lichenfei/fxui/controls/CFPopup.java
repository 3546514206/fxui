package cn.lichenfei.fxui.controls;

import cn.lichenfei.fxui.common.FxUtil;
import javafx.animation.*;
import javafx.geometry.Bounds;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.effect.BlurType;
import javafx.scene.effect.DropShadow;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.Popup;
import javafx.util.Duration;
import org.kordamp.ikonli.antdesignicons.AntDesignIconsOutlined;
import org.kordamp.ikonli.javafx.FontIcon;

public class CFPopup extends Popup {

    private Label titleLabel = new Label("CF-POPUP");
    private Label closeLabel = new Label();
    private StackPane top = new StackPane(titleLabel, closeLabel);
    private BorderPane borderPane = new BorderPane(null, top, null, null, null);

    //动画
    private Button button = new Button();
    private Duration duration = Duration.millis(300);
    private FadeTransition FT = new FadeTransition(duration);
    private ScaleTransition ST = new ScaleTransition(duration);
    private TranslateTransition TT = new TranslateTransition(duration);
    private ParallelTransition PT = new ParallelTransition(button, FT, ST, TT);

    public CFPopup() {
        borderPane.setPrefSize(500, 300);
        borderPane.setBackground(new Background(new BackgroundFill(Color.WHITE, new CornerRadii(3), null)));
        borderPane.setEffect(new DropShadow(BlurType.THREE_PASS_BOX, Color.rgb(0, 0, 0, 0.6), 10, 0, 0, 0));
        getContent().add(borderPane);
        //
        closeLabel.setGraphic(new FontIcon(AntDesignIconsOutlined.CLOSE));
        StackPane.setAlignment(titleLabel, Pos.CENTER);
        StackPane.setAlignment(closeLabel, Pos.CENTER_RIGHT);
        //
        closeLabel.setOnMouseClicked(event -> {
            this.hide();
        });

        this.opacityProperty().bind(button.opacityProperty());
        //button.setOpacity(0);
        button.translateXProperty().addListener((observableValue, number, t1) -> {
            this.setAnchorX(t1.doubleValue());
        });
        button.translateYProperty().addListener((observableValue, number, t1) -> {
            this.setAnchorY(t1.doubleValue());
        });
        borderPane.scaleXProperty().bind(button.scaleXProperty());
        borderPane.scaleYProperty().bind(button.scaleYProperty());
        //窗口显示之后
        setOnShowing(windowEvent -> {
            /*FT.setFromValue(0);
            FT.setToValue(1);
            ST.setFromX(0);
            ST.setFromY(0);
            ST.setToX(1);
            ST.setToY(1);
            //
            TT.setToX(300);
            TT.setFromX(getAnchorX());
            TT.setToY(300);
            TT.setFromY(getAnchorY());
            //
            PT.play();
            PT.setCycleCount(Timeline.INDEFINITE);
            PT.setOnFinished(actionEvent -> {
                //阴影处理


            });*/
        });
    }

    @Override
    public void show(Node ownerNode, double anchorX, double anchorY) {
        Bounds bounds = FxUtil.localToScreen(ownerNode);
        super.show(ownerNode, bounds.getCenterX(), bounds.getCenterY());
    }
}
