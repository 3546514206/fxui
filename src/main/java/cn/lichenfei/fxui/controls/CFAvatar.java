package cn.lichenfei.fxui.controls;

import javafx.animation.ScaleTransition;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;

public class CFAvatar extends StackPane {

    private ImageView imageView = new ImageView();
    //
    private DoubleProperty arcPro = new SimpleDoubleProperty(48); // 圆角
    private double width = 48; // 圆角
    private ScaleTransition ST = new ScaleTransition(Duration.millis(300), imageView);

    public CFAvatar() {
        initialize();
    }

    public CFAvatar(double width, double arc) {
        this.width = width;
        arcPro.set(arc);
        initialize();
    }

    private void initialize() {
        setMaxWidth(USE_PREF_SIZE);
        setMaxHeight(USE_PREF_SIZE);
        getChildren().add(imageView);
        //
        setPrefHeight(width);
        setPrefWidth(width);
        imageView.setFitWidth(width);
        imageView.setFitHeight(width);
        //
        Rectangle rectangle = new Rectangle();
        rectangle.widthProperty().bind(widthProperty());
        rectangle.heightProperty().bind(heightProperty());
        rectangle.arcHeightProperty().bind(arcPro);
        rectangle.arcWidthProperty().bind(arcPro);
        setClip(rectangle);
        //
        hoverProperty().addListener((observableValue, aBoolean, t1) -> {
            ST.stop();
            ST.setToX(t1 ? 1.2 : 1);
            ST.setToY(t1 ? 1.2 : 1);
            ST.play();
        });

    }

    public void setImage(Image image) {
        this.imageView.setImage(image);
    }
}
