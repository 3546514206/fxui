package cn.lichenfei.fxui.controls;

import cn.lichenfei.fxui.common.FxUtil;
import cn.lichenfei.fxui.common.SimpleControl;
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

    public void setMaximizeTooltip(String text) {
        maximizeBut.setTooltip(SimpleControl.getTooltip(text));
    }

    public HeaderStyle getHeaderStyle() {
        return this.headerStylePro.get();
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
                    showHideNode(new Node[]{iconifyBut, closeBut}, new Node[]{maximizeBut});
                    break;
                case CLOSE:
                    showHideNode(new Node[]{closeBut}, new Node[]{iconifyBut, maximizeBut});
                    break;
                case NONE:
                    showHideNode(new Node[]{}, new Node[]{closeBut, iconifyBut, maximizeBut});
                    break;
                case ALL:
                default:
                    showHideNode(new Node[]{closeBut, iconifyBut, maximizeBut}, new Node[]{});
                    break;
            }
        });
        iconifyBut.setTooltip(SimpleControl.getTooltip("最小化"));
        closeBut.setTooltip(SimpleControl.getTooltip("关闭"));
        maximizeBut.setTooltip(SimpleControl.getTooltip("最大化"));
    }

    private void showHideNode(Node[] show, Node[] hide) {
        for (int i = 0; i < show.length; i++) {
            show[i].setVisible(true);
            show[i].setManaged(true);
        }
        for (int i = 0; i < hide.length; i++) {
            hide[i].setVisible(false);
            hide[i].setManaged(false);
        }
    }

    @Override
    public String getUserAgentStylesheet() {
        return STYLE_SHEET;
    }

    public enum HeaderStyle {
        ALL, // 显示所有按钮
        ICONIFY_CLOSE, // 最小化和关闭按钮
        CLOSE, // 关闭按钮
        NONE // 隐藏所有按钮
    }
}
