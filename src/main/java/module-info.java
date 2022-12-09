module cn.lichenfei.chenfeifxui {
    requires javafx.controls;
    //requires javafx.fxml;

    requires org.kordamp.ikonli.core;
    requires org.kordamp.ikonli.javafx;
    // add icon pack modules
    requires org.kordamp.ikonli.antdesignicons;


    //opens cn.lichenfei.fxui to javafx.fxml;
    exports cn.lichenfei.fxui;
}