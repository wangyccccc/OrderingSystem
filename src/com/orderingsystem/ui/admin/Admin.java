package com.orderingsystem.ui.admin;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.stage.Stage;

public class Admin extends Application {
    @Override
    public void start(Stage stage) throws Exception {
        BorderPane root = new BorderPane();
        VBox vbox = new VBox();
        vbox.setPadding(new Insets(15, 12, 15, 12));
        vbox.setSpacing(10);
        Button btn1 = new Button("菜品管理");
        btn1.setGraphic(new ImageView(new Image("com/orderingsystem/images/other/菜品管理.png")));
        btn1.getStyleClass().add("menu-button");

        Button btn2 = new Button("订单管理");
        btn2.setGraphic(new ImageView(new Image("com/orderingsystem/images/other/订单管理.png")));
        btn2.getStyleClass().add("order-button");
        HBox h1 = new HBox(btn1,btn2);
        h1.setSpacing(10);

        Button btn3 = new Button("用户管理");
        btn3.setGraphic(new ImageView(new Image("com/orderingsystem/images/other/用户管理.png")));
        btn3.getStyleClass().add("user-button");

        Button btn4 = new Button("会员管理");
        btn4.setGraphic(new ImageView(new Image("com/orderingsystem/images/other/会员管理.png")));
        btn4.getStyleClass().add("member-button");
        HBox h2 = new HBox(btn3,btn4);
        h2.setSpacing(10);
        vbox.getChildren().addAll(h1,h2);
        Image backgroundImage = new Image("com/orderingsystem/images/other/动态蓝色.gif");
        BackgroundImage background = new BackgroundImage(backgroundImage,
                BackgroundRepeat.REPEAT, BackgroundRepeat.REPEAT, BackgroundPosition.DEFAULT,
                BackgroundSize.DEFAULT);
        root.setBackground(new Background(background));
        root.setTop(vbox);
        Scene scene = new Scene(root, 480, 320);
        scene.getStylesheets().add("/com/orderingsystem/css/admin/admin.css");
        stage.setTitle("后台主界面");
        btn1.setOnAction(e->{
            try {
                new GoodsAdmin().start(new Stage());
            } catch (Exception ex) {
                throw new RuntimeException(ex);
            }
        });
        btn2.setOnAction(e->{
            try {
                new Order().start(new Stage());
            } catch (Exception ex) {
                throw new RuntimeException(ex);
            }
        });
        btn3.setOnAction(e->{
            try {
                new UserAdmin().start(new Stage());
            } catch (Exception ex) {
                throw new RuntimeException(ex);
            }
        });
        btn4.setOnAction(e->{

        });
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        Application.launch();
    }
}
