package cn.lichenfei.fxui.common;

import cn.lichenfei.fxui.controls.CFStage;
import javafx.beans.property.DoubleProperty;
import javafx.geometry.Bounds;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Rectangle;
import javafx.stage.Window;

/**
 * @author ChenFei
 * @date 2022/12/9
 */
public class FxUtil {

    public static String getResource(String resources) {
        return FxUtil.class.getResource(resources).toExternalForm();
    }

    public static Image getImage(String resources) {
        return new Image(getResource(resources));
    }

    public static ImageView getImageView(String resources, double size) {
        return getImageView(resources, size, size);
    }

    public static ImageView getImageView(String resources, double height, double width) {
        ImageView imageView = new ImageView(getResource(resources));
        imageView.setFitHeight(height);
        imageView.setFitWidth(width);
        return imageView;
    }

    /**
     * 获取组件在屏幕中的位置
     *
     * @param node
     * @return
     */
    public static Bounds localToScreen(Node node) {
        return node.localToScreen(node.getLayoutBounds());
    }

    /**
     * 获取窗口
     *
     * @param node
     * @return
     */
    public static CFStage getCFStage(Node node) {
        return (CFStage) node.getParent().getScene().getWindow();
    }

    /**
     * 获取窗口
     *
     * @param node
     * @return
     */
    public static Window getWindow(Node node) {
        return node.getParent().getScene().getWindow();
    }

    /**
     * 矩形裁剪并设置绑定裁剪的圆角
     *
     * @param pane
     * @param bindArc
     */
    public static void setRectangleClip(Pane pane, DoubleProperty bindArc) {
        Rectangle rectangle = new Rectangle();
        rectangle.widthProperty().bind(pane.widthProperty());
        rectangle.heightProperty().bind(pane.heightProperty());
        rectangle.arcWidthProperty().bind(bindArc);
        rectangle.arcHeightProperty().bind(bindArc);
        pane.setClip(rectangle);
    }

    public static void setRectangleClip(ImageView pane, DoubleProperty bindArc) {
        Rectangle rectangle = new Rectangle();
        rectangle.widthProperty().bind(pane.fitWidthProperty());
        rectangle.heightProperty().bind(pane.fitHeightProperty());
        rectangle.arcWidthProperty().bind(bindArc);
        rectangle.arcHeightProperty().bind(bindArc);
        pane.setClip(rectangle);
    }

}
