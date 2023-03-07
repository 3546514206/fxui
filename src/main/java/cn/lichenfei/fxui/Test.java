package cn.lichenfei.fxui;

import cn.lichenfei.fxui.common.FxUtil;
import javafx.animation.Interpolator;
import javafx.animation.RotateTransition;
import javafx.animation.Timeline;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.geometry.Insets;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.paint.CycleMethod;
import javafx.scene.paint.LinearGradient;
import javafx.scene.paint.Stop;
import javafx.scene.shape.Arc;
import javafx.scene.shape.ArcType;
import javafx.util.Duration;

public class Test extends StackPane {

    private double width = 200;
    private double height = 200;
    double radius = Math.sqrt(width * width + height * height) / 2;
    //
    private Arc arc1 = new Arc(width / 2, height / 2, radius, radius, 0, 90);
    private Arc arc2 = new Arc(width / 2, height / 2, radius, radius, 180, 90);
    private Pane pane = new Pane(arc1, arc2);
    private StackPane content = new StackPane();
    private StackPane back = new StackPane(pane, content);

    //动画
    RotateTransition RT = new RotateTransition(Duration.millis(4000), pane);

    public Test() {
        getChildren().add(back);
        back.setMaxSize(USE_PREF_SIZE, USE_PREF_SIZE);
        back.setPrefSize(width, height);
        back.setBackground(new Background(new BackgroundFill(Color.rgb(0, 0, 0, 0.2), null, null)));
        FxUtil.setRectangleClip(back, new SimpleDoubleProperty(0));
        //
        arc1.setType(ArcType.ROUND);
        Stop[] stops1 = new Stop[]{new Stop(0, Color.TRANSPARENT), new Stop(1, Color.BLUE)};
        LinearGradient linearGradient1 = new LinearGradient(0, 0, 1, 0, true, CycleMethod.NO_CYCLE, stops1);
        arc1.setFill(linearGradient1);
        //
        arc2.setType(ArcType.ROUND);
        Stop[] stops2 = new Stop[]{new Stop(0, Color.TRANSPARENT), new Stop(1, Color.RED)};
        LinearGradient linearGradient2 = new LinearGradient(1, 1, 0, 1, true, CycleMethod.NO_CYCLE, stops2);
        arc2.setFill(linearGradient2);

        //
        content.setBackground(new Background(new BackgroundFill(Color.WHITE, new CornerRadii(0), new Insets(2))));

        //
        RT.setToAngle(360);
        RT.setFromAngle(0);
        RT.setInterpolator(Interpolator.LINEAR);
        RT.setCycleCount(Timeline.INDEFINITE);
        RT.play();
    }

}
