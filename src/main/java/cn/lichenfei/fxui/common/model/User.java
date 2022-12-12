package cn.lichenfei.fxui.common.model;

import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import org.kordamp.ikonli.antdesignicons.AntDesignIconsOutlined;
import org.kordamp.ikonli.javafx.FontIcon;

import java.time.LocalDate;

/**
 * @author ChenFei
 * @date 2022/12/12
 */
public class User {

    private String name;
    private Integer age;
    private String address;
    private LocalDate birthday;
    //操作
    private HBox operateBox;

    {
        operateBox = new HBox();
        //
        Button editBut = new Button();
        editBut.getStyleClass().addAll("cf-info-but", "icon");
        Button removeBut = new Button();
        removeBut.getStyleClass().addAll("cf-danger-but", "icon");
        operateBox.getChildren().addAll(editBut, removeBut);
        operateBox.setAlignment(Pos.CENTER);
        operateBox.setSpacing(10);
        //
        editBut.setGraphic(new FontIcon(AntDesignIconsOutlined.EDIT));
        removeBut.setGraphic(new FontIcon(AntDesignIconsOutlined.DELETE));
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public LocalDate getBirthday() {
        return birthday;
    }

    public void setBirthday(LocalDate birthday) {
        this.birthday = birthday;
    }

    public void setOperateBox(HBox operateBox) {
        this.operateBox = operateBox;
    }

    public HBox getOperateBox() {
        return operateBox;
    }
}
