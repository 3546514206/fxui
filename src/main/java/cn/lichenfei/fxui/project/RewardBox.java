package cn.lichenfei.fxui.project;

import cn.lichenfei.fxui.common.FxUtil;
import javafx.animation.Interpolator;
import javafx.animation.RotateTransition;
import javafx.animation.Timeline;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.paint.CycleMethod;
import javafx.scene.paint.LinearGradient;
import javafx.scene.paint.Stop;
import javafx.scene.shape.Arc;
import javafx.scene.shape.ArcType;
import javafx.util.Duration;

public class RewardBox extends HBox {

    public RewardBox() {
        getChildren().addAll(
                new MyEffect(FxUtil.getIconImage("/img/reward-wx.jpg", 200), 210, 210),
                new MyEffect(FxUtil.getIconImage("/img/reward-zfb.jpg", 200), 210, 210)
        );
        setPadding(new Insets(50));
        setSpacing(50);
    }

    /**
     * 边框流动效果
     */
    public class MyEffect extends StackPane {

        private double width;
        private double height;
        private double radius;
        private double borderWidth = 2;
        private double borderRadius = 0;
        //
        private Arc arc1;
        private Arc arc2;
        private Pane pane = new Pane();
        private StackPane content = new StackPane();
        private StackPane back = new StackPane(pane, content);

        //动画
        RotateTransition RT = new RotateTransition(Duration.millis(4000), pane);

        public MyEffect(Node main, double width, double height) {
            this.width = width;
            this.height = height;
            initLayout(main);
            //
            RT.setToAngle(360);
            RT.setFromAngle(0);
            RT.setInterpolator(Interpolator.LINEAR);
            RT.setCycleCount(Timeline.INDEFINITE);
            RT.play();
        }

        private void initLayout(Node main) {
            radius = Math.sqrt(width * width + height * height) / 2;
            arc1 = new Arc(width / 2, height / 2, radius, radius, 0, 90);
            arc2 = new Arc(width / 2, height / 2, radius, radius, 180, 90);
            setArcInfo(arc1, Color.valueOf("#409EFF"), true);
            setArcInfo(arc2, Color.valueOf("#F56C6C"), false);
            pane.getChildren().addAll(arc1, arc2);
            //
            content.getChildren().add(main);// 设置内容
            getChildren().add(back);
            back.setMaxSize(USE_PREF_SIZE, USE_PREF_SIZE);
            back.setPrefSize(width, height);
            back.setBackground(new Background(new BackgroundFill(Color.rgb(0, 0, 0, 0.2), null, null)));
            FxUtil.setRectangleClip(back, new SimpleDoubleProperty(borderRadius * 2));
            //
            content.setBackground(new Background(new BackgroundFill(Color.WHITE, new CornerRadii(borderRadius), new Insets(borderWidth))));
        }

        private void setArcInfo(Arc arc, Color color, boolean flag) {
            arc.setType(ArcType.ROUND);
            Stop[] stops1 = new Stop[]{new Stop(0, Color.TRANSPARENT), new Stop(1, color)};
            LinearGradient linearGradient1 = new LinearGradient(flag ? 0 : 1, flag ? 0 : 1, flag ? 1 : 0, flag ? 0 : 1, true, CycleMethod.NO_CYCLE, stops1);
            arc.setFill(linearGradient1);
        }
    }
}
