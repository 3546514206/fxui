package cn.lichenfei.fxui.controls;

import cn.lichenfei.fxui.common.FxUtil;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.beans.value.ChangeListener;
import javafx.css.PseudoClass;
import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.*;
import org.kordamp.ikonli.antdesignicons.AntDesignIconsFilled;
import org.kordamp.ikonli.javafx.FontIcon;

public class CFTextField extends HBox {

    private static final String STYLE_CLASS = "cf-text-field";
    private static final String STYLE_SHEET = FxUtil.getResource("/css/cf-text-field.css");
    private PseudoClass CF_FOCUSED = PseudoClass.getPseudoClass("cf-focused");// 自定义伪类
    private Type type = Type.TEXT;
    private StringProperty textPro = new SimpleStringProperty("");
    //组件
    private Label iconLabel = new Label();// 左侧图标
    private TextField textField = new TextField("");
    private PasswordField passwordField = new PasswordField();
    private Label rightIcon = new Label(); // 右侧图标

    public CFTextField() {
        getChildren().addAll(this.textField, this.rightIcon);
        initialize();
    }

    public CFTextField(Type type) {
        this.type = type;
        getChildren().addAll(Type.TEXT.equals(type) ? this.textField : this.passwordField, this.rightIcon);
        initialize();
    }

    public CFTextField(Type type, FontIcon fontIcon) {
        this.type = type;
        getChildren().addAll(this.iconLabel, Type.TEXT.equals(type) ? this.textField : this.passwordField, this.rightIcon);
        this.iconLabel.setGraphic(fontIcon);
        initialize();
    }

    public void setPromptText(String promptText) {
        this.textField.setPromptText(promptText);
        this.passwordField.setPromptText(promptText);
    }

    public String getText() {
        return textPro.get();
    }

    public StringProperty getTextProperty() {
        return textPro;
    }

    private void initialize() {
        setLayout();
        setEvent();
    }

    //布局相关
    private void setLayout() {
        setMaxHeight(USE_PREF_SIZE);
        setMaxWidth(USE_PREF_SIZE);
        getStyleClass().add(this.STYLE_CLASS);
        setAlignment(Pos.CENTER);
        setCursor(Cursor.TEXT);
        HBox.setHgrow(this.textField, Priority.ALWAYS);
        HBox.setHgrow(this.passwordField, Priority.ALWAYS);
        this.textField.prefHeightProperty().bind(heightProperty());
        this.passwordField.prefHeightProperty().bind(heightProperty());
        this.iconLabel.getStyleClass().add("icon-label");
        this.rightIcon.getStyleClass().add("right-icon");
        if (Type.TEXT.equals(this.type)) {
            this.rightIcon.setGraphic(new FontIcon(AntDesignIconsFilled.CLOSE_CIRCLE));
            this.rightIcon.setVisible(false);
        } else {
            this.rightIcon.setGraphic(new FontIcon(AntDesignIconsFilled.EYE_INVISIBLE));
        }
    }

    // 事件相关
    private void setEvent() {
        // 当选中文本输入框，给当前组件添加伪类
        ChangeListener<Boolean> tChangeListener = (observable, oldValue, newValue) -> this.pseudoClassStateChanged(this.CF_FOCUSED, newValue);
        this.textField.focusedProperty().addListener(tChangeListener);
        this.passwordField.focusedProperty().addListener(tChangeListener);
        //是否显示右侧图标
        this.textField.textProperty()
                .addListener((observable, oldValue, newValue) ->
                        this.rightIcon.setVisible(!(newValue == null || "".equals(newValue) && Type.TEXT.equals(this.type))));
        // 点击右侧图标事件
        this.rightIcon.setOnMouseClicked(event -> {
            if (Type.TEXT.equals(this.type)) {
                this.textField.setText(""); // 文本框直接清空
            } else {
                FontIcon fontIcon = (FontIcon) this.rightIcon.getGraphic();
                int i = childrenContains(this.iconLabel) ? 1 : 0;
                boolean isEyeInvisible = fontIcon.getIconCode().equals(AntDesignIconsFilled.EYE_INVISIBLE);
                TextField setField = isEyeInvisible ? this.textField : this.passwordField;
                setField.setText(isEyeInvisible ? this.passwordField.getText() : this.textField.getText());
                getChildren().set(i, setField);
                setRequestFocus(setField);
                this.rightIcon.setGraphic(new FontIcon(isEyeInvisible ? AntDesignIconsFilled.EYE : AntDesignIconsFilled.EYE_INVISIBLE));
            }
        });
        // 点击则选中输入框
        setOnMouseClicked(event -> setRequestFocus(childrenContains(this.textField) ? this.textField : this.passwordField));
        // 监听值
        ChangeListener<String> textChange = (observable, oldValue, newValue) -> this.textPro.set(newValue);
        this.textField.textProperty().addListener(textChange);
        this.passwordField.textProperty().addListener(textChange);
    }

    // 输入框选择，并且光标移动到最后
    private void setRequestFocus(TextField textField) {
        textField.requestFocus();
        textField.positionCaret(textField.getText().length());
    }

    // 当前容器是否存在此组件
    private boolean childrenContains(Node node) {
        return getChildren().contains(node);
    }

    @Override
    public String getUserAgentStylesheet() {
        return STYLE_SHEET;
    }

    public enum Type {
        TEXT, PASSWORD
    }
}
