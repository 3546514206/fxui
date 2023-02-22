package cn.lichenfei.fxui.project;

import cn.lichenfei.fxui.common.FxUtil;
import cn.lichenfei.fxui.controls.CFClock;
import javafx.animation.*;
import javafx.geometry.Pos;
import javafx.scene.PerspectiveCamera;
import javafx.scene.SubScene;
import javafx.scene.control.Label;

import javafx.scene.control.ScrollPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.util.Duration;

public class AnimationExamples extends ScrollPane {

    private VBox itemBox = new VBox();

    public AnimationExamples() {
        getStyleClass().add("scroll-bar-style");
        setContent(itemBox);
        itemBox.prefWidthProperty().bind(widthProperty().subtract(2));
        itemBox.setAlignment(Pos.TOP_CENTER);
        itemBox.setSpacing(50);
        //
        Pane empty = new Pane();
        empty.setPrefHeight(50);
        //
        itemBox.getChildren().addAll(
                empty,
                new AnimationExample2(),
                new AnimationExample1("ANIMATION", Color.valueOf("#909399"), Color.valueOf("#337ecc"))
        );
    }
}

/**
 * 时钟翻页效果
 */
class AnimationExample2 extends StackPane {

    private StackPane root = new StackPane(new CFClock());
    private SubScene subScene = new SubScene(root, 400, 200);

    public AnimationExample2() {
        getChildren().add(subScene);
        root.getStylesheets().add(FxUtil.getResource("/css/cf-base.css"));
        subScene.setCamera(new PerspectiveCamera());
    }
}

/**
 * 文本填充效果
 */
class AnimationExample1 extends StackPane {

    private Rectangle TRANSITION_NODE = new Rectangle();
    private TranslateTransition TT = new TranslateTransition(Duration.seconds(2), TRANSITION_NODE);

    public AnimationExample1(String text, Color backColor, Color frontColor) {
        //布局
        Label label1 = getLabel(text, backColor);
        Label label2 = getLabel(text, frontColor);
        TRANSITION_NODE.setWidth(2);
        TRANSITION_NODE.heightProperty().bind(this.heightProperty());
        TRANSITION_NODE.setFill(frontColor);
        StackPane.setAlignment(TRANSITION_NODE, Pos.CENTER_LEFT);
        setMaxSize(USE_PREF_SIZE, USE_PREF_SIZE);
        getChildren().addAll(label1, label2, TRANSITION_NODE);
        //裁剪绑定
        Rectangle rectangle = new Rectangle();
        rectangle.heightProperty().bind(label2.heightProperty());
        rectangle.widthProperty().bind(TRANSITION_NODE.translateXProperty());
        label2.setClip(rectangle);
        //动画
        TT.setCycleCount(Timeline.INDEFINITE);
        TT.setAutoReverse(true);
        TT.toXProperty().bind(widthProperty());
        TT.setFromX(0);
        //启动动画
        widthProperty().addListener((observable, oldValue, newValue) -> TT.play());
    }

    //获取文字
    public Label getLabel(String text, Color color) {
        Label label = new Label(text);
        Font font = Font.font("", FontWeight.BLACK, 60);
        label.setTextFill(color);
        label.setFont(font);
        return label;
    }

}
