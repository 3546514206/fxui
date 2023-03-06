package cn.lichenfei.fxui.project;

import cn.lichenfei.fxui.common.FxUtil;
import javafx.geometry.Insets;
import javafx.scene.layout.HBox;

public class RewardBox extends HBox {

    public RewardBox() {
        getChildren().addAll(
                FxUtil.getIconImage("/img/reward-wx.jpg", 200),
                FxUtil.getIconImage("/img/reward-zfb.jpg", 200)
        );
        setPadding(new Insets(50));
        setSpacing(50);
    }
}
