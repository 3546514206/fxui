package cn.lichenfei.fxui.controls;

import cn.lichenfei.fxui.common.FxUtil;
import cn.lichenfei.fxui.controls.skin.CFRadioButtonSkin;
import javafx.scene.control.RadioButton;

public class CFRadioButton extends RadioButton {

    private static final String STYLE_SHEET = FxUtil.getResource("/css/cf-radio-button.css");

    public CFRadioButton() {
        setMaxSize(USE_PREF_SIZE, USE_PREF_SIZE);
        getStyleClass().setAll("cf-radio-button");
    }

    public CFRadioButton(String text) {
        setText(text);
        setMaxSize(USE_PREF_SIZE, USE_PREF_SIZE);
        getStyleClass().setAll("cf-radio-button");
    }

    @Override
    protected CFRadioButtonSkin createDefaultSkin() {
        return new CFRadioButtonSkin(this);
    }

    @Override
    public String getUserAgentStylesheet() {
        return STYLE_SHEET;
    }
}
