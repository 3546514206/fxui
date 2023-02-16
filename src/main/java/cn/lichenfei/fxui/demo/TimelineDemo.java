package cn.lichenfei.fxui.demo;

import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.PerspectiveCamera;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.transform.Rotate;
import javafx.scene.transform.Scale;
import javafx.stage.Stage;
import javafx.util.Duration;

public class TimelineDemo extends Application {

    @Override
    public void start(Stage primaryStage) {
        /***************************************** 案例1 *****************************************/
        Timeline timeline = new Timeline();
        Rectangle rectangle1 = new Rectangle();
        rectangle1.setFill(Color.BLUE);
        rectangle1.setWidth(100);
        rectangle1.setHeight(100);
        StackPane.setAlignment(rectangle1, Pos.TOP_LEFT);
        // 开始位置
        KeyValue tx1 = new KeyValue(rectangle1.translateXProperty(), 0);
        KeyValue ty1 = new KeyValue(rectangle1.translateYProperty(), 0);
        KeyValue o1 = new KeyValue(rectangle1.opacityProperty(), 0);
        KeyFrame kf1 = new KeyFrame(Duration.ZERO, tx1, ty1, o1);
        // 中间位置
        KeyValue tx2 = new KeyValue(rectangle1.translateXProperty(), 200);
        KeyValue ty2 = new KeyValue(rectangle1.translateYProperty(), 200);
        KeyValue o2 = new KeyValue(rectangle1.opacityProperty(), 1);
        KeyValue r2 = new KeyValue(rectangle1.rotateProperty(), 0);
        KeyFrame kf2 = new KeyFrame(Duration.seconds(1), tx2, ty2, o2, r2);
        // 结束位置
        KeyValue tx3 = new KeyValue(rectangle1.translateXProperty(), 400);
        KeyValue ty3 = new KeyValue(rectangle1.translateYProperty(), 0);
        KeyValue r3 = new KeyValue(rectangle1.rotateProperty(), 360);
        KeyFrame kf3 = new KeyFrame(Duration.seconds(2), tx3, ty3, r3);

        timeline.getKeyFrames().addAll(kf1, kf2, kf3);
        //
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.setAutoReverse(true);
        timeline.play();

        /***************************************** 案例2 *****************************************/
        Timeline timeline2 = new Timeline();
        Label label = new Label("缩放");
        label.setFont(Font.font(100));
        StackPane scalePane = new StackPane(label);
        scalePane.setBackground(new Background(new BackgroundFill(Color.BLUE, null, null)));
        scalePane.setPrefWidth(200);
        scalePane.setPrefWidth(200);
        scalePane.setLayoutX(400);
        scalePane.setLayoutY(200);
        // 缩放
        //Scale scale = new Scale(1, 1, 0, 0);// 左上角固定缩放
/*        Scale scale = new Scale(1, 1, 100, 100);// 右下角固定缩放
        scalePane.getTransforms().add(scale);
        //
        KeyValue sx1 = new KeyValue(scale.xProperty(), 0);
        KeyValue sy1 = new KeyValue(scale.yProperty(), 0);
        KeyFrame kfs1 = new KeyFrame(Duration.seconds(0), sx1, sy1);
        //
        KeyValue sx2 = new KeyValue(scale.xProperty(), 1);
        KeyValue sy2 = new KeyValue(scale.yProperty(), 1);
        KeyFrame kfs2 = new KeyFrame(Duration.seconds(1), sx2, sy2);*/


        Scale scale = new Scale(1, 1);// 左侧固定缩放
        scale.setPivotX(0);//
        scalePane.getTransforms().add(scale);
        KeyValue sx1 = new KeyValue(scale.xProperty(), 0);
        KeyFrame kfs1 = new KeyFrame(Duration.seconds(0), sx1);
        //
        KeyValue sx2 = new KeyValue(scale.xProperty(), 1);
        KeyFrame kfs2 = new KeyFrame(Duration.seconds(1), sx2);


        //
        timeline2.getKeyFrames().addAll(kfs1, kfs2);
        timeline2.setCycleCount(Timeline.INDEFINITE);
        timeline2.setAutoReverse(true);
        timeline2.play();

        /***************************************** 案例3 *****************************************/

        Timeline timeline3 = new Timeline();
        Label label1 = new Label("6");
        label1.setTextFill(Color.WHITE);
        label1.setFont(new Font(50));
        label1.setBackground(new Background(new BackgroundFill(Color.BLACK, null, null)));
        label1.setLayoutX(400);
        label1.setLayoutY(200);
        label1.setPrefWidth(300);
        label1.setPrefHeight(300);
        label1.setAlignment(Pos.CENTER);
        // 旋转
        //Rotate rotate = new Rotate(0, 0, 100);//左下角旋转
        Rotate rotate = new Rotate(0, Rotate.Y_AXIS);//3D Y 旋转
        rotate.setPivotX(300);
        label1.getTransforms().add(rotate);
        //
        KeyValue px1 = new KeyValue(rotate.angleProperty(), 0);
        KeyFrame kfpx1 = new KeyFrame(Duration.seconds(0), px1);
        //
        KeyValue px2 = new KeyValue(rotate.angleProperty(), 360);
        KeyFrame kfpx2 = new KeyFrame(Duration.seconds(1), px2);
        //
        timeline3.getKeyFrames().addAll(kfpx1, kfpx2);
        timeline3.setCycleCount(Timeline.INDEFINITE);
        //timeline3.setAutoReverse(true);
        timeline3.play();

        //
        Pane root = new Pane();
        root.getChildren().addAll(rectangle1, scalePane, label1);
        Scene scene = new Scene(root);
        PerspectiveCamera perspectiveCamera = new PerspectiveCamera();
        scene.setCamera(perspectiveCamera);// 立体相机
        primaryStage.setScene(scene);
        primaryStage.setWidth(1200);
        primaryStage.setHeight(700);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
