package cn.lichenfei.fxui.controls;

import javafx.animation.TranslateTransition;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.util.Duration;

import java.text.DecimalFormat;

/**
 * 这个东西只是为了玩一玩
 */
public class CFTextLoading extends StackPane {

    private Rectangle TRANSITION_NODE = new Rectangle();
    private TranslateTransition TT = new TranslateTransition();

    public CFTextLoading(String text) {
        this.setMaxHeight(Double.NEGATIVE_INFINITY);
        this.setMaxWidth(Double.NEGATIVE_INFINITY);

        Label r = getLabel(text);
        r.setStyle("-fx-border-color:rgb(0,0,0,0.3);-fx-border-width:0 0 4px 0;-fx-text-fill:rgb(0,0,0,0.3);");

        Label l = getLabel(text);
        l.setStyle("-fx-border-color:primary-color;-fx-border-width:0 0 4px 0;-fx-text-fill:primary-color;");
        this.getChildren().addAll(l, r);

        TRANSITION_NODE.setWidth(2);
        TRANSITION_NODE.heightProperty().bind(this.heightProperty());
        TRANSITION_NODE.setStyle("-fx-fill:primary-color;");
        this.getChildren().add(TRANSITION_NODE);
        StackPane.setAlignment(TRANSITION_NODE, Pos.CENTER_LEFT);

        //
        Rectangle rectangle1 = new Rectangle();
        rectangle1.heightProperty().bind(l.heightProperty());
        rectangle1.widthProperty().bind(TRANSITION_NODE.translateXProperty());
        l.setClip(rectangle1);
        //
        Rectangle rectangle2 = new Rectangle();
        rectangle2.heightProperty().bind(l.heightProperty());
        rectangle2.widthProperty().bind(this.widthProperty());
        rectangle2.layoutXProperty().bind(TRANSITION_NODE.translateXProperty());
        r.setClip(rectangle2);
        //
        this.widthProperty().addListener((observable, oldValue, newValue) -> {
            setTranslate();
            TT.play();
        });
        TT.setOnFinished(event -> {
            setTranslate();
            TT.play();
        });
        // 进度显示
        Label label = new Label("0.00%");
        Font font = Font.font("Impact", 30);
        label.setFont(font);
        label.setTranslateY(50);
        label.setStyle("-fx-text-fill:primary-color;");

        this.getChildren().add(label);
        StackPane.setAlignment(label, Pos.BOTTOM_CENTER);
        TRANSITION_NODE.translateXProperty().addListener((observable, oldValue, newValue) -> {
            DoubleProperty doubleProperty = new SimpleDoubleProperty(newValue.doubleValue());
            double v = doubleProperty.divide(this.widthProperty()).get();
            label.setText(DF.format(v));
        });
    }

    DecimalFormat DF = new DecimalFormat("0.00%");

    private void setTranslate() {
        //
        TT.setNode(TRANSITION_NODE);
        TT.setCycleCount(1);
        if (TRANSITION_NODE.getTranslateX() > 0) {
            TT.setDelay(Duration.seconds(0));
            TT.setToX(0);
            TT.setFromX(this.getWidth());
            TT.setDuration(Duration.seconds(2));
        } else {
            TT.setDelay(Duration.seconds(1));
            TT.setFromX(0);
            TT.setToX(this.getWidth());
            TT.setDuration(Duration.seconds(1));
        }
    }


    public Label getLabel(String text) {
        Label label = new Label(text);
        Font font = Font.font("", FontWeight.BLACK, 60);

        label.setFont(font);
        label.setPadding(new Insets(0, 0, 0, 0));
        return label;
    }

}
