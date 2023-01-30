package cn.lichenfei.fxui.common;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Rectangle;

/**
 * @author ChenFei
 * @date 2022/12/9
 */
public class FxUtils {

    /**
     * 从resources文件夹下获取css
     *
     * @param resources eg：/css/core.css
     * @return
     */
    public static String getCss(String resources) {
        return FxUtils.class.getResource(resources).toExternalForm();
    }

    /**
     * 从resources文件夹下获取Image
     *
     * @param resources
     * @return
     */
    public static Image getImage(String resources) {
        return new Image(FxUtils.class.getResourceAsStream(resources));
    }

    /**
     * 获取图形，返回ImageView
     *
     * @param image
     * @param width
     * @return
     */
    public static ImageView getGraphic(Image image, double width) {
        ImageView imageView = new ImageView(image);
        imageView.setFitHeight(width);
        imageView.setFitWidth(width);
        return imageView;
    }

    /**
     * 获取图形，返回ImageView
     *
     * @param resources
     * @param width
     * @return
     */
    public static ImageView getGraphic(String resources, double width) {
        ImageView imageView = new ImageView(FxUtils.getImage(resources));
        imageView.setFitHeight(width);
        imageView.setFitWidth(width);
        return imageView;
    }

    /**
     * 设置裁剪圆角
     *
     * @param pane
     * @param arc
     */
    public static void setClip(Pane pane, double arc) {
        Rectangle rectangle = new Rectangle();
        rectangle.widthProperty().bind(pane.widthProperty());
        rectangle.heightProperty().bind(pane.heightProperty());
        rectangle.setArcWidth(arc);
        rectangle.setArcHeight(arc);
        pane.setClip(rectangle);
    }


}
