package cn.lichenfei.fxui.controls;

import cn.lichenfei.fxui.common.FxUtil;
import cn.lichenfei.fxui.common.Level;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import org.kordamp.ikonli.javafx.FontIcon;


/**
 * 气泡确认框
 */
public class CFPopConfirm extends CFPopover {

    private Node ownerNode;

    public static CFPopConfirm create(String message) {
        return new CFPopConfirm(new ConfirmNode(message, Level.DANGER));
    }

    public static CFPopConfirm create(String message, Level level) {
        return new CFPopConfirm(new ConfirmNode(message, level));
    }

    private CFPopConfirm(ConfirmNode confirmNode) {
        super(confirmNode);
        setPosition(Position.BOTTOM_CENTER);
    }

    @Override
    protected void setShow() {
        ConfirmNode confirmNode = (ConfirmNode) getMain();
        confirmNode.getConfirmBut().requestFocus();// 选中确认按钮
        super.setShow();
    }

    public static class ConfirmNode extends VBox {

        private static final String STYLE_SHEET = FxUtil.getResource("/css/cf-pop-confirm.css");
        //
        private Label messageLabel = new Label();
        private Button cancelBut = new Button("取消");
        private Button confirmBut = new Button("确定");
        private HBox buttonBox = new HBox(cancelBut, confirmBut);

        public ConfirmNode(String message, Level level) {
            messageLabel.setText(message);
            messageLabel.setGraphic(FontIcon.of(level.getIkon()));
            getChildren().addAll(messageLabel, buttonBox);
            //styleClass
            getStyleClass().addAll("cf-pop-confirm", level.getStyleClass());
            messageLabel.getStyleClass().add("message");
            buttonBox.getStyleClass().add("button-box");
            cancelBut.getStyleClass().add("cancel-but");
            confirmBut.getStyleClass().add("confirm-but");
        }

        public Button getConfirmBut() {
            return confirmBut;
        }

        public Button getCancelBut() {
            return cancelBut;
        }

        @Override
        public String getUserAgentStylesheet() {
            return STYLE_SHEET;
        }
    }

}
