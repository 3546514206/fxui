package cn.lichenfei.fxui.controls;

import cn.lichenfei.fxui.common.FxUtil;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.stage.FileChooser;
import org.kordamp.ikonli.antdesignicons.AntDesignIconsOutlined;
import org.kordamp.ikonli.javafx.FontIcon;

import java.io.File;

public final class CFFileChooser extends CFTextField {

    private FileChooser fileChooser = new FileChooser();
    private ObjectProperty<File> fileProperty = new SimpleObjectProperty<>();

    public CFFileChooser() {
        super(Type.TEXT, FontIcon.of(AntDesignIconsOutlined.FILE));
        textField.setEditable(false); //不可编辑
        setPromptText("请选择文件");
        textField.setOnMouseClicked(event -> {
            File file = fileChooser.showOpenDialog(FxUtil.getWindow(this));
            if (file != null) {
                fileProperty.set(file);
            } else {
                fileProperty.set(fileProperty.get());
            }
        });
        fileProperty.addListener((observableValue, file, t1) -> setText(t1 != null ? t1.getPath() : ""));
    }

    @Override
    protected void clearText() {
        super.clearText();
        fileProperty.set(null);
    }
}
