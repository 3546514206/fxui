package cn.lichenfei.fxui.common.model;

import javafx.scene.Node;

/**
 * @author ChenFei
 * @date 2022/12/12
 */
public class MenuInfo {

    private Node icon;
    private String name;
    private Node content;

    public MenuInfo() {
    }

    public MenuInfo(Node icon, String name, Node content) {
        this.icon = icon;
        this.name = name;
        this.content = content;
    }

    public Node getIcon() {
        return icon;
    }

    public void setIcon(Node icon) {
        this.icon = icon;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "CFMenuInfo{" +
                "icon=" + icon +
                ", name='" + name + '\'' +
                '}';
    }

    public void setContent(Node content) {
        this.content = content;
    }

    public Node getContent() {
        return content;
    }
}
