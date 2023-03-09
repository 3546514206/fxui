package cn.lichenfei.fxui.project;

import cn.lichenfei.fxui.common.FxUtil;
import cn.lichenfei.fxui.common.SimpleControl;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class RewardBox extends VBox {

    private ImageView wxImage = FxUtil.getIconImage("/img/reward-wx.jpg", 180);
    private Label wxLabel = SimpleControl.getLabel("微信", SimpleControl.LabelEnum.H5);
    private VBox wxBox = new VBox(wxImage, wxLabel);
    //
    private ImageView zfbImage = FxUtil.getIconImage("/img/reward-zfb.jpg", 180);
    private Label zfbLabel = SimpleControl.getLabel("支付宝", SimpleControl.LabelEnum.H5);
    private VBox zfbBox = new VBox(zfbImage, zfbLabel);
    //
    private HBox hBox = new HBox(wxBox, zfbBox);
    //
    private Label title = SimpleControl.getLabel("打赏作者", SimpleControl.LabelEnum.H4);

    private Label message = SimpleControl.getLabel("支持一下作者，请作者吃碗油泼面吧！");

    public RewardBox() {
        setPadding(new Insets(10));
        getChildren().addAll(title, hBox, message);
        setAlignment(Pos.CENTER);
        hBox.setPadding(new Insets(10));
        hBox.setSpacing(30);
        wxBox.setSpacing(10);
        wxBox.setAlignment(Pos.CENTER);
        zfbBox.setSpacing(10);
        zfbBox.setAlignment(Pos.CENTER);
        //
        wxBox.setStyle("-fx-border-color:-cf-border-color;-fx-border-radius: 3px;-fx-padding:10px;");
        zfbBox.setStyle("-fx-border-color:-cf-border-color;-fx-border-radius: 3px;fx-padding:10px;");
    }
}