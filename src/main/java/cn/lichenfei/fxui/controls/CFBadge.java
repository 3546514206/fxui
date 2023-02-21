package cn.lichenfei.fxui.controls;

import cn.lichenfei.fxui.common.FxUtil;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.scene.control.Label;

public class CFBadge extends Label {

    private static final String STYLE_SHEET = FxUtil.getResource("/css/cf-badge.css");
    private IntegerProperty valuePro = new SimpleIntegerProperty();

    public CFBadge() {
        initialize();
    }

    public void setValue(int value) {
        if (value <= 0) {
            value = -1;
        }
        this.valuePro.set(value);
    }

    private void initialize() {
        getStyleClass().add("cf-badge");
        valuePro.addListener((observable, oldValue, newValue) -> {
            int i = newValue.intValue();
            setManaged(i == -1 ? false : true);
            setVisible(i == -1 ? false : true);
            setText(i > 9 ? "9+" : i + "");
        });
        valuePro.set(-1);// 默认不展示
    }

    @Override
    public String getUserAgentStylesheet() {
        return STYLE_SHEET;
    }
}
