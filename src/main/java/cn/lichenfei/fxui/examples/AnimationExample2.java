package cn.lichenfei.fxui.examples;

import cn.lichenfei.fxui.common.FxUtil;
import cn.lichenfei.fxui.controls.CFClock;
import javafx.scene.PerspectiveCamera;
import javafx.scene.SubScene;
import javafx.scene.layout.StackPane;

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
