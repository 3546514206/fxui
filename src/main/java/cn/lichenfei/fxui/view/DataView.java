package cn.lichenfei.fxui.view;

import cn.lichenfei.fxui.common.model.User;
import cn.lichenfei.fxui.controls.CFTableView;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.layout.Priority;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author ChenFei
 * @date 2022/12/12
 */
public class DataView extends StackPane {

    //布局容器
    private VBox vBox = new VBox();
    //搜索栏
    //表格
    private CFTableView cfTableView = new CFTableView();

    {
        List<CFTableView.Field> fields = Arrays.asList(
                new CFTableView.Field("用户名", "name"),
                new CFTableView.Field("年龄", "age"),
                new CFTableView.Field("地址", "address"),
                new CFTableView.Field("生日", "birthday")
        );
        cfTableView.fast(fields);
        this.getChildren().addAll(vBox);
        vBox.getChildren().add(cfTableView);
        VBox.setVgrow(cfTableView, Priority.ALWAYS);
        setTableViewInfo();
    }

    public DataView() {

    }

    private void setTableViewInfo() {
        //模拟数据
        List<User> userList = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            User user = new User();
            user.setAddress("银河系 太阳系 地球 亚洲 。。。");
            user.setAge(18);
            user.setBirthday(LocalDate.now());
            user.setName("ChenFei");
            userList.add(user);
        }
        //给表格填充数据
        ObservableList<User> observableList = FXCollections.observableArrayList(userList);
        cfTableView.setItems(observableList);
    }

}
