package cn.lichenfei.fxui.controls;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.scene.effect.BlurType;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class CFAvatar extends StackPane {

    private ImageView imageView = new ImageView();
    //
    private DoubleProperty arcPro = new SimpleDoubleProperty(48); // 圆角
    private double width = 48; // 圆角

    public CFAvatar() {
        initialize();
    }

    public CFAvatar(Image image) {
        this.imageView.setImage(image);
        initialize();
    }

    public CFAvatar(Image image, double width, double arc) {
        this.imageView.setImage(image);
        this.width = width;
        arcPro.set(arc);
        initialize();
    }

    public CFAvatar(double width, double arc) {
        this.width = width;
        arcPro.set(arc);
        initialize();
    }

    private void initialize() {
        setMaxSize(USE_PREF_SIZE, USE_PREF_SIZE);
        getChildren().add(imageView);
        imageView.setFitWidth(width);
        imageView.setFitHeight(width);
        //裁剪为圆角
        Rectangle rectangle = new Rectangle();
        rectangle.widthProperty().bind(widthProperty());
        rectangle.heightProperty().bind(heightProperty());
        rectangle.arcHeightProperty().bind(arcPro);
        rectangle.arcWidthProperty().bind(arcPro);
        imageView.setClip(rectangle);
        //阴影
        setEffect(new DropShadow(BlurType.THREE_PASS_BOX, Color.rgb(0, 0, 0, 0.3), 5, 0, 0, 0));
    }

    public void setImage(Image image) {
        this.imageView.setImage(image);
    }
}
