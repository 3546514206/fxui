package cn.lichenfei.fxui.common;

import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Rectangle;

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
