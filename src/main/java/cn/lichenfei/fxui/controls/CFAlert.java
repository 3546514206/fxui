package cn.lichenfei.fxui.controls;

import cn.lichenfei.fxui.common.FxUtil;
import cn.lichenfei.fxui.common.Level;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Priority;
import org.kordamp.ikonli.antdesignicons.AntDesignIconsOutlined;
import org.kordamp.ikonli.javafx.FontIcon;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class CFAlert extends HBox {

    private static final String STYLE_SHEET = FxUtil.getResource("/css/cf-alert.css");
    //
    private Label iconLabel = new Label();
    protected Label messLabel = new Label("消息提示！");
    protected Label closeLabel = new Label();
    // 级别
    protected ObjectProperty<Level> levelProperty = new SimpleObjectProperty<>(Level.PRIMARY);

    public CFAlert(String message) {
        messLabel.setText(message);
        initialize();
    }

    public CFAlert(String message, Level level) {
        levelProperty.set(level);
        messLabel.setText(message);
        initialize();
    }

    /**
     * 是否显示关闭按钮
     *
     * @param val
     */
    public CFAlert showClose(boolean val) {
        this.closeLabel.setManaged(val);
        this.closeLabel.setVisible(val);
        return this;
    }

    protected void initialize() {
        setMaxSize(USE_COMPUTED_SIZE, USE_PREF_SIZE);
        getChildren().addAll(iconLabel, messLabel, closeLabel);
        HBox.setHgrow(messLabel, Priority.ALWAYS);
        messLabel.setMaxSize(Double.MAX_VALUE, USE_PREF_SIZE);
        //
        Level level = levelProperty.get();
        iconLabel.setGraphic(new FontIcon(level.getIkon()));
        closeLabel.setGraphic(new FontIcon(AntDesignIconsOutlined.CLOSE));
        //styleClass
        getStyleClass().addAll("cf-alert", level.getStyleClass());
        iconLabel.getStyleClass().add("icon-label");
        messLabel.getStyleClass().add("mess-label");
        closeLabel.getStyleClass().add("close-label");
        //默认不显示关闭按钮
        showClose(false);
        setLevelStyleClass();
        setPadding();
    }

    //监听level：设置styleClass和icon
    protected void setLevelStyleClass() {
        levelProperty.addListener((observableValue, level1, t1) -> {
            //删除可能存在的class
            List<String> s = Arrays.stream(Level.values()).map(Level::getStyleClass).collect(Collectors.toList());
            getStyleClass().removeAll(s);
            //添加class
            getStyleClass().add(t1.getStyleClass());
            // icon
            iconLabel.setGraphic(new FontIcon(t1.getIkon()));
        });
    }

    protected void setPadding() {
        setPadding(new Insets(5, 15, 5, 15));
    }

    // 移出本身
    protected void destroy() {
        Pane parent = (Pane) this.getParent();
        if (parent != null) {
            parent.getChildren().remove(this);
        }
    }

    @Override
    public String getUserAgentStylesheet() {
        return STYLE_SHEET;
    }

}
