package cn.lichenfei.fxui.view;

import cn.lichenfei.fxui.common.model.User;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Priority;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;

import java.time.LocalDate;
import java.util.ArrayList;
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
    private TableView tableView = new TableView();
    //分页
    private TableColumn nameCol = new TableColumn("用户名");
    private TableColumn ageCol = new TableColumn("年龄");
    private TableColumn addressCol = new TableColumn("地址");
    private TableColumn birthdayCol = new TableColumn("生日");
    private TableColumn operateCol = new TableColumn("操作");

    {
        this.getChildren().addAll(vBox);
        vBox.getChildren().add(tableView);
        VBox.setVgrow(tableView, Priority.ALWAYS);
        tableView.getStyleClass().addAll("cf-table-view", "scroll-bar-style");//class
        tableView.setColumnResizePolicy(javafx.scene.control.TableView.CONSTRAINED_RESIZE_POLICY);
        //绑定TableColumn，TableView
        tableView.getColumns().addAll(nameCol, ageCol, addressCol, birthdayCol, operateCol);
        nameCol.setCellValueFactory(new PropertyValueFactory<User, String>("name"));
        ageCol.setCellValueFactory(new PropertyValueFactory<User, Integer>("age"));
        addressCol.setCellValueFactory(new PropertyValueFactory<User, String>("address"));
        birthdayCol.setCellValueFactory(new PropertyValueFactory<User, LocalDate>("birthday"));
        operateCol.setCellValueFactory(new PropertyValueFactory<User, Node>("operateBox"));
        operateCol.setMaxWidth(100);
        operateCol.setMinWidth(100);
        setTableViewInfo();
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
        tableView.setItems(observableList);
    }

}
