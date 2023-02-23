package cn.lichenfei.fxui.common;

import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Region;
import javafx.stage.Stage;
import org.silentsoft.ui.util.DragResizer;

public class MyStageDragResizer extends DragResizer {

    private boolean enable = true;

    private final Stage stage;
    private final int shadow;

    public static void makeResizable(Stage stage, Region region) {
        new MyStageDragResizer(stage, region);
    }

    public static void makeResizable(Stage stage, Region region, int margin) {
        new MyStageDragResizer(stage, region, margin);
    }

    public static void makeResizable(Stage stage, Region region, int margin, int shadow) {
        new MyStageDragResizer(stage, region, margin, shadow);
    }

    public static void makeResizable(Stage stage, Region region, int margin, int shadow, Runnable dragDoneAction) {
        new MyStageDragResizer(stage, region, margin, shadow, dragDoneAction);
    }

    public MyStageDragResizer(Stage stage, Region region) {
        this(stage, region, 0);
    }

    public MyStageDragResizer(Stage stage, Region region, int margin) {
        this(stage, region, margin, 0);
    }

    public MyStageDragResizer(Stage stage, Region region, int margin, int shadow) {
        this(stage, region, margin, shadow, null);
    }

    public MyStageDragResizer(Stage stage, Region region, int margin, int shadow, Runnable dragDoneAction) {
        super(region, margin, dragDoneAction);

        this.stage = stage;
        this.shadow = shadow;
    }

    public void setEnable(boolean enable) {
        this.enable = enable;
    }

    @Override
    protected void mouseMoved(MouseEvent event) {
        if (enable) {//开启拖拽
            super.mouseMoved(event);
        }
    }

    @Override
    protected void drag(MouseEvent mouseEvent, DragMode dragMode, double position, double size) {
        if (enable) {//开启拖拽
            super.drag(mouseEvent, dragMode, position, size);
            switch (dragMode) {
                case WIDTH:
                    stage.setX(position);
                    stage.setWidth(size + shadow);
                    break;
                case HEIGHT:
                    stage.setY(position);
                    stage.setHeight(size + shadow);
                    break;
            }
        }
    }
}
