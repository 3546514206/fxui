package cn.lichenfei.fxui.controls;

import cn.lichenfei.fxui.common.FxUtil;
import cn.lichenfei.fxui.common.Level;
import cn.lichenfei.fxui.common.SimpleButton;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.stage.DirectoryChooser;
import org.kordamp.ikonli.antdesignicons.AntDesignIconsOutlined;
import org.kordamp.ikonli.javafx.FontIcon;

import java.io.File;

public final class CFDirectoryChooser extends HBox {

    private DirectoryChooser directoryChooser = new DirectoryChooser();
    private ObjectProperty<File> fileProperty = new SimpleObjectProperty<>();
    //
    private CFTextField cfTextField = new CFTextField(CFTextField.Type.TEXT, FontIcon.of(AntDesignIconsOutlined.FOLDER));
    private Button button = SimpleButton.get("选择文件夹", Level.PRIMARY);

    public CFDirectoryChooser() {
        initialize();
    }

    private void initialize() {
        getChildren().addAll(cfTextField, button);
        setSpacing(5);
        setMaxSize(USE_PREF_SIZE, USE_PREF_SIZE);
        cfTextField.setEditable(false); //不可编辑
        cfTextField.setPromptText("请选择文件夹");
        directoryChooser.setTitle("选择文件夹");
        initEvent();
    }

    private void initEvent(){
        cfTextField.getTextProperty().addListener((observableValue, s, t1) -> {
            if (t1 == null || "".equals(t1)){
                fileProperty.set(null);
            }
        });
        button.setOnMouseClicked(event -> {
            File file = directoryChooser.showDialog(FxUtil.getWindow(this));
            if (file != null) {
                cfTextField.setText(file.getPath());
                fileProperty.setValue(file);
            }
        });
    }

    public DirectoryChooser getDirectoryChooser() {
        return directoryChooser;
    }

    public File getFile() {
        return fileProperty.get();
    }
}
