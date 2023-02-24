package cn.lichenfei.fxui.controls;

import javafx.animation.FadeTransition;
import javafx.animation.ParallelTransition;
import javafx.animation.ScaleTransition;
import javafx.animation.TranslateTransition;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.effect.BlurType;
import javafx.scene.effect.DropShadow;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.stage.Popup;
import javafx.stage.Window;
import javafx.util.Duration;
import org.kordamp.ikonli.antdesignicons.AntDesignIconsOutlined;
import org.kordamp.ikonli.javafx.FontIcon;

public class CFPopup extends Popup {

    private Label titleLabel = new Label("CF-POPUP");
    private Label closeLabel = new Label();
    private StackPane top = new StackPane(titleLabel, closeLabel);
    private BorderPane borderPane = new BorderPane(null, top, null, null, null);

    //动画
    private Duration duration = Duration.millis(300);
    private FadeTransition FT = new FadeTransition(duration);
    private ScaleTransition ST = new ScaleTransition(duration);
    private TranslateTransition TT = new TranslateTransition(duration);
    private ParallelTransition PT = new ParallelTransition(borderPane, TT);

    public CFPopup() {
        borderPane.setPrefSize(500, 300);
        borderPane.setBackground(new Background(new BackgroundFill(Color.BLUE, null, null)));
        getContent().add(borderPane);
        //
        closeLabel.setGraphic(new FontIcon(AntDesignIconsOutlined.CLOSE));
        StackPane.setAlignment(titleLabel, Pos.CENTER);
        StackPane.setAlignment(closeLabel, Pos.CENTER_RIGHT);
        //
        closeLabel.setOnMouseClicked(event -> {
            this.hide();
        });

        //窗口显示之后
        setOnShowing(windowEvent -> {
            FT.setFromValue(0);
            FT.setToValue(1);
            ST.setFromX(0);
            ST.setFromY(0);
            ST.setToX(1);
            ST.setToY(1);
            TT.setToX(0);
            TT.setFromX(getAnchorX());
            TT.setToY(0);
            TT.setFromY(getAnchorY());
            //
            PT.play();
            PT.setOnFinished(actionEvent -> {
                //阴影处理


            });
        });
    }

    @Override
    public void show(Node ownerNode, double anchorX, double anchorY) {
        //borderPane.setOpacity(0);
        super.show(ownerNode, anchorX, anchorY);
    }
}
