package cn.lichenfei.fxui.controls;

import cn.lichenfei.fxui.common.FxUtil;
import com.sun.javafx.stage.FocusUngrabEvent;
import com.sun.javafx.stage.WindowEventDispatcher;
import javafx.event.Event;
import javafx.event.EventDispatchChain;
import javafx.event.EventDispatcher;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.effect.BlurType;
import javafx.scene.effect.DropShadow;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.Popup;
import javafx.stage.Window;
import javafx.stage.WindowEvent;

public class CFPopup extends Popup {

    private double centerX = 0;
    private double centerY = 0;
    private DropShadow dropShadow = new DropShadow(BlurType.THREE_PASS_BOX, Color.rgb(0, 0, 0, 0.4), 10, 0, 0, 0);
    //
    private BorderPane borderPane = new BorderPane();

    public CFPopup(Node main) {
        borderPane.setCenter(main);
        initialize();
    }

    /**
     * 显示Popup
     *
     * @param owner
     */
    public void show(Node owner) {
        Window window = FxUtil.getWindow(owner);
        centerX = window.getX() + (window.getWidth() / 2);
        centerY = window.getY() + (window.getHeight() / 2);
        if (!isShowing()) {
            super.show(window);
        }
    }

    private void initialize() {
        getContent().add(borderPane);
        borderPane.setBackground(new Background(new BackgroundFill(Color.WHITE, new CornerRadii(3), null)));
        borderPane.setPadding(new Insets(20));
        borderPane.setEffect(dropShadow);
        //窗口显示之后
        setOnShown(windowEvent -> {
            setAnchorX(centerX - getWidth() / 2);
            setAnchorY(centerY - getHeight() / 2);
        });
        popupMove();
        setAutoHide(true);
        addEventHandler(FocusUngrabEvent.FOCUS_UNGRAB, new EventHandler<Event>() {
            @Override
            public void handle(Event windowEvent) {
                //windowEvent.consume();
            }
        });
        addEventFilter(Event.ANY, new EventHandler<Event>() {
            @Override
            public void handle(Event event) {
                System.out.println(event);
                if (FocusUngrabEvent.FOCUS_UNGRAB.equals(event)){
                    //event.consume();
                }
            }
        });
        //https://qa.1r1g.com/sf/ask/3571083351/
        setEventDispatcher(new EventDispatcher() {
            @Override
            public Event dispatchEvent(Event event, EventDispatchChain eventDispatchChain) {
                //event.consume();
                //event
                return null;
            }
        });
        removeEventHandler(FocusUngrabEvent.FOCUS_UNGRAB, new EventHandler<FocusUngrabEvent>() {
            @Override
            public void handle(FocusUngrabEvent focusUngrabEvent) {
                focusUngrabEvent.consume();
            }
        });
    }

    /**************************************************** popup拖动 ****************************************************/

    private double xOffset;
    private double yOffset;

    private void popupMove() {
        this.borderPane.setOnMousePressed(event -> {
            event.consume();
            this.xOffset = this.getX() - event.getScreenX();
            this.yOffset = this.getY() - event.getScreenY();
        });
        this.borderPane.setOnMouseDragged(event -> {
            event.consume();
            this.setX(event.getScreenX() + this.xOffset);
            this.setY(event.getScreenY() + this.yOffset);
        });
    }

}
