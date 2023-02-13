package cn.lichenfei.fxui.controls;

import cn.lichenfei.fxui.common.FxUtils;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.util.List;

/**
 * @author ChenFei
 * @date 2022/12/18
 */
public class CFTableView extends TableView {

    /**
     * 快速创建表格
     *
     * @param fieldList
     * @return
     */
    public void fast(List<Field> fieldList) {
        this.getStyleClass().addAll("cf-table-view", "scroll-bar-style"); //class
        this.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        fieldList.forEach(field -> {
            TableColumn column = new TableColumn<>(field.title);
            column.setCellValueFactory(new PropertyValueFactory(field.name));
            this.getColumns().add(column);
        });
    }

    @Override
    public String getUserAgentStylesheet() {
        return FxUtils.getCss("/css/cf-button.css");
    }

    /**
     * 字段
     */
    public static class Field {
        private String title;

        private String name;

        public Field(String title, String name) {
            this.title = title;
            this.name = name;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }
}
