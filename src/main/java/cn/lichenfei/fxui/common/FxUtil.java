package cn.lichenfei.fxui.common;

import javafx.geometry.Rectangle2D;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Rectangle;
import javafx.stage.Screen;

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

    // 获取主屏幕的可视边界
    public static Rectangle2D getVisualBounds() {
        return Screen.getPrimary().getVisualBounds();
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
