package cn.lichenfei.fxui.controls;

import cn.lichenfei.fxui.common.FxUtil;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.StackPane;
import org.kordamp.ikonli.antdesignicons.AntDesignIconsOutlined;
import org.kordamp.ikonli.javafx.FontIcon;

import java.util.function.Consumer;

public class CFHeader extends HBox {

    private static final String STYLE_SHEET = FxUtil.getResource("/css/cf-header.css");
    private ObjectProperty<HeaderStyle> headerStylePro = new SimpleObjectProperty<>(HeaderStyle.ALL);

    private StackPane titleBox = new StackPane();
    private Button iconifyBut = new Button();
    private Button maximizeBut = new Button();
    private Button closeBut = new Button();

    public CFHeader() {
        initialize();
    }

    public void setMaximizeMouseClicked(Consumer<MouseEvent> consumer) {
        maximizeBut.setOnMouseClicked(event -> consumer.accept(event));
    }

    public void setIconifyMouseClicked(Consumer<MouseEvent> consumer) {
        iconifyBut.setOnMouseClicked(event -> consumer.accept(event));
    }

    public void setCloseMouseClicked(Consumer<MouseEvent> consumer) {
        closeBut.setOnMouseClicked(event -> consumer.accept(event));
    }

    public void setHeaderStyle(HeaderStyle headerStyle) {
        this.headerStylePro.set(headerStyle);
    }

    private void initialize() {
        setMaxHeight(USE_PREF_SIZE);
        getChildren().addAll(titleBox, iconifyBut, maximizeBut, closeBut);
        HBox.setHgrow(titleBox, Priority.ALWAYS);
        //图标
        iconifyBut.setGraphic(new FontIcon(AntDesignIconsOutlined.MINUS));
        maximizeBut.setGraphic(new FontIcon(AntDesignIconsOutlined.PLUS));
        closeBut.setGraphic(new FontIcon(AntDesignIconsOutlined.CLOSE));
        //styleClass
        getStyleClass().add("cf-header");
        iconifyBut.getStyleClass().add("iconified-but");
        maximizeBut.getStyleClass().add("maximize-but");
        closeBut.getStyleClass().add("close-but");
        //
        headerStylePro.addListener((observable, oldValue, newValue) -> {
            switch (newValue) {
                case ICONIFY_CLOSE:
                    showNode(false, maximizeBut);
                    showNode(true, iconifyBut, closeBut);
                    break;
                case CLOSE:
                    showNode(false, iconifyBut, maximizeBut);
                    showNode(true, closeBut);
                    break;
                case ALL:
                default:
                    showNode(true, iconifyBut, maximizeBut, closeBut);
                    break;
            }
        });
    }

    private void showNode(boolean value, Node... node) {
        for (int i = 0; i < node.length; i++) {
            node[i].setVisible(value ? true : false);
            node[i].setManaged(value ? true : false);
        }
    }

    @Override
    public String getUserAgentStylesheet() {
        return STYLE_SHEET;
    }

    public enum HeaderStyle {
        ALL, ICONIFY_CLOSE, CLOSE
    }
}
