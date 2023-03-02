package cn.lichenfei.fxui.common;

import cn.lichenfei.fxui.controls.CFStage;
import javafx.beans.property.DoubleProperty;
import javafx.geometry.Bounds;
import javafx.geometry.Rectangle2D;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Rectangle;
import javafx.stage.Screen;
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

    /**
     * 获取主屏幕的可视边界
     *
     * @return
     */
    public static Rectangle2D getVisualBounds() {
        return Screen.getPrimary().getVisualBounds();
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

}
