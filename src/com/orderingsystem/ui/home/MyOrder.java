package com.orderingsystem.ui.home;

import com.orderingsystem.dao.CartDao;
import com.orderingsystem.dao.GoodsDao;
import com.orderingsystem.dao.OrderDao;
import com.orderingsystem.dao.OrderItemDao;
import com.orderingsystem.entity.Order;
import com.orderingsystem.entity.User;
import com.orderingsystem.share.ShareValue;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.stage.Stage;

import java.util.List;

public class MyOrder extends Application {
    private User user = ShareValue.USER;
    private CartDao cartDao = new CartDao();
    private GoodsDao goodsDao = new GoodsDao();
    private OrderDao orderDao = new OrderDao();
    private OrderItemDao orderItemDao = new OrderItemDao();
    TableView tableView = new TableView();
    TableColumn c1 = new TableColumn("订单编号");
    TableColumn c2 = new TableColumn("下单用户");
    TableColumn c3 = new TableColumn("收货地址");
    TableColumn c4 = new TableColumn("订单总价");
    TableColumn c5 = new TableColumn("下单时间");
    public void binding(List<Order> orderList){
        tableView.getItems().clear();
        c1.setCellValueFactory(new PropertyValueFactory("ooid"));
        c2.setCellValueFactory(new PropertyValueFactory("name"));
        c3.setCellValueFactory(new PropertyValueFactory("address"));
        c4.setCellValueFactory(new PropertyValueFactory("orderprice"));
        c5.setCellValueFactory(new PropertyValueFactory("otime"));
        tableView.getItems().addAll(orderList);
    }

    public void start(Stage stage) throws Exception {
        stage.getIcons().add(new Image("/com/orderingsystem/icon/商店.png"));
        stage.setTitle("我的购物车");

        c1.setPrefWidth(200);
        c1.setStyle("-fx-font-size: 16px;");
        c2.setPrefWidth(100);
        c2.setStyle("-fx-font-size: 16px;");
        c3.setPrefWidth(100);
        c3.setStyle("-fx-font-size: 16px;");
        c4.setPrefWidth(100);
        c4.setStyle("-fx-font-size: 16px;");
        c5.setPrefWidth(200);
        c5.setStyle("-fx-font-size: 16px;");
        tableView.getColumns().addAll(c1,c2,c3,c4,c5);

        binding(orderDao.queryOrderByUuid(ShareValue.USER));

        Button gw = new Button("继续购物");
        gw.setStyle("-fx-padding: 5");
        gw.setStyle("-fx-background-color: #00BFFF; -fx-text-fill: white; -fx-font-size: 16px; -fx-font-weight: bold; -fx-padding: 10px 20px; -fx-border-radius: 20px;");
        Button sc = new Button("删除订单");
        sc.setStyle("-fx-background-color: #FF6347; -fx-text-fill: white; -fx-font-size: 16px; -fx-font-weight: bold; -fx-padding: 10px 20px; -fx-border-radius: 20px;");
        HBox hBotton = new HBox(10,gw);
        hBotton.setAlignment(Pos.CENTER_RIGHT);
        hBotton.setSpacing(10);

        VBox root = new VBox(10,tableView,hBotton);
        root.setPadding(new Insets(10));
        root.setAlignment(Pos.CENTER);
        gw.setOnAction(e->{
            try {
                stage.close();
                new Index().start(new Stage());
            } catch (Exception ex) {
                throw new RuntimeException(ex);
            }
        });
        Scene scene = new Scene(root, 730, 400);
        stage.setScene(scene);
        stage.show();
    }
    public static void main(String[] args) {
        launch();
    }
}
