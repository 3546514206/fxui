package cn.lichenfei.fxui.controls;

import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import org.kordamp.ikonli.antdesignicons.AntDesignIconsFilled;
import org.kordamp.ikonli.javafx.FontIcon;

public class CFInput extends StackPane {

    private Label leftIcon = new Label();
    private Label rightIcon = new Label();
    private TextField textField = new TextField();
    private PasswordField passwordField = new PasswordField();

    public CFInput() {
        setLayout();
        setEvent();
    }

    public CFInput(FontIcon icon) {
        setLayout();
        setEvent();
        setLeftIcon(icon);
    }

    public CFInput setLeftIcon(FontIcon fontIcon) {
        this.getChildren().add(leftIcon);
        StackPane.setAlignment(leftIcon, Pos.CENTER_LEFT);
        leftIcon.setGraphic(fontIcon);
        this.getStyleClass().add("left");
        return this;
    }

    protected CFInput setRightIcon(FontIcon fontIcon) {
        this.getChildren().add(rightIcon);
        StackPane.setAlignment(rightIcon, Pos.CENTER_RIGHT);
        rightIcon.setGraphic(fontIcon);
        this.getStyleClass().add("right");
        rightIcon.setOpacity(0);
        rightIcon.setOnMouseClicked(event -> textField.setText(""));// 清空输入框事件
        return this;
    }

    private void setLayout() {
        this.setMaxHeight(Double.NEGATIVE_INFINITY);
        this.setMaxWidth(Double.NEGATIVE_INFINITY);
        this.getChildren().addAll(textField);
        // class
        this.getStyleClass().add("cf-input");
        leftIcon.getStyleClass().add("left-icon");
        rightIcon.getStyleClass().add("right-icon");
        setRightIcon(new FontIcon(AntDesignIconsFilled.CLOSE_CIRCLE));
    }

    private void setEvent() {
        textField.focusedProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue) {
                this.getStyleClass().add("selected");
            } else {
                this.getStyleClass().remove("selected");
            }
        });
        textField.textProperty().addListener((observable, oldValue, newValue) -> {
            rightIcon.setOpacity("".equals(newValue) ? 0 : 1);
            passwordField.setText(newValue);
        });
    }

}
