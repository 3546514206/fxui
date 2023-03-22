package cn.lichenfei.fxui.controls;

import cn.lichenfei.fxui.common.FxUtil;
import cn.lichenfei.fxui.common.Level;
import cn.lichenfei.fxui.common.SimpleButton;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.stage.FileChooser;
import org.kordamp.ikonli.antdesignicons.AntDesignIconsOutlined;
import org.kordamp.ikonli.javafx.FontIcon;

import java.io.File;

public final class CFFileChooser extends HBox {

    private FileChooser fileChooser = new FileChooser();
    private ObjectProperty<File> fileProperty = new SimpleObjectProperty<>();
    //
    private CFTextField cfTextField = new CFTextField(CFTextField.Type.TEXT, FontIcon.of(AntDesignIconsOutlined.FILE));
    private Button button = SimpleButton.get("选择文件", Level.PRIMARY);

    public CFFileChooser() {
        initialize();
    }

    private void initialize() {
        getChildren().addAll(cfTextField, button);
        setSpacing(5);
        setMaxSize(USE_PREF_SIZE, USE_PREF_SIZE);
        cfTextField.setEditable(false); //不可编辑
        cfTextField.setPromptText("请选择文件");
        fileChooser.setTitle("选择文件");
        //
        initEvent();
    }

    private void initEvent(){
        cfTextField.getTextProperty().addListener((observableValue, s, t1) -> {
            if (t1 == null || "".equals(t1)){
                fileProperty.set(null);
            }
        });
        button.setOnMouseClicked(event -> {
            File file = fileChooser.showOpenDialog(FxUtil.getWindow(this));
            if (file != null) {
                cfTextField.setText(file.getPath());
                fileProperty.setValue(file);
            }
        });
    }

    public FileChooser getFileChooser() {
        return fileChooser;
    }

    public File getFile() {
        return fileProperty.get();
    }

    /*    public void showOpenMultipleDialog(Consumer<List<File>> consumer) {
        List<File> files = fileChooser.showOpenMultipleDialog(FxUtil.getWindow(this));
        if (files != null && !files.isEmpty()) {
            String filesStr = files.stream().map(v -> v.getPath()).collect(Collectors.joining(";"));
            cfTextField.setText(filesStr);
            filesProperty.set(files);
        }
        consumer.accept(files);
    }

    public void showSaveDialog(Consumer<File> consumer) {
        File file = fileChooser.showSaveDialog(FxUtil.getWindow(this));
        if (file != null) {
            cfTextField.setText(file.getPath());
            filesProperty.set(Arrays.asList(file));
        }
        consumer.accept(file);
    }

*/

}
