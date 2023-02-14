package cn.lichenfei.fxui.controls;

import cn.lichenfei.fxui.common.FxUtil;
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

    private StackPane titleBox = new StackPane();
    private Button iconifyBut = new Button();
    private Button maximizeBut = new Button();
    private Button closeBut = new Button();

    private CFStage stage;

    public CFHeader(CFStage stage) {
        this.stage = stage;
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
        iconifyBut.setOnMouseClicked(event -> stage.setIconified(true));
        closeBut.setOnMouseClicked(event -> stage.close());
    }

    @Override
    public String getUserAgentStylesheet() {
        return STYLE_SHEET;
    }
}
