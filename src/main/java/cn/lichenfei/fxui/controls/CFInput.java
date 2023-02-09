package cn.lichenfei.fxui.controls;

import cn.lichenfei.fxui.common.FxUtils;
import javafx.css.PseudoClass;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.*;

public class CFInput extends HBox {

    private static final String STYLE_SHEET = "/css/cf-input.css";

    public PseudoClass CF_FOCUSED = PseudoClass.getPseudoClass("cf-focused");

    private Label leftIcon = new Label();// 左侧图标

    private TextField textField = new TextField();
    private PasswordField passwordField = new PasswordField();

    private Label rightIcon = new Label(); // 右侧图标

    public CFInput() {
        //
        this.getStyleClass().add("cf-input");
        this.getChildren().add(textField);
        this.textField.focusedProperty().addListener((observable, oldValue, newValue) -> this.pseudoClassStateChanged(CF_FOCUSED, newValue));
    }

    @Override
    public String getUserAgentStylesheet() {
        return FxUtils.getCss(STYLE_SHEET);
    }
}
