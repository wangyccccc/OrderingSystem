package com.orderingsystem.ui.admin;

import com.orderingsystem.dao.CartDao;
import com.orderingsystem.dao.GoodsDao;
import com.orderingsystem.dao.OrderDao;
import com.orderingsystem.dao.OrderItemDao;
import com.orderingsystem.entity.User;
import com.orderingsystem.share.ShareValue;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.stage.Stage;

import java.util.List;

public class Order extends Application {
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
    public void binding(List<com.orderingsystem.entity.Order> orderList){
        tableView.getItems().clear();
        c1.setCellValueFactory(new PropertyValueFactory("ooid"));
        c2.setCellValueFactory(new PropertyValueFactory("name"));
        c3.setCellValueFactory(new PropertyValueFactory("address"));
        c4.setCellValueFactory(new PropertyValueFactory("orderprice"));
        c5.setCellValueFactory(new PropertyValueFactory("otime"));
        tableView.getItems().addAll(orderList);
    }

    @Override
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
        binding(orderDao.queryAllOrder());
        VBox root = new VBox(10,tableView);
        root.setPadding(new Insets(10));
        root.setAlignment(Pos.CENTER);
        Scene scene = new Scene(root, 730, 400);
        stage.setScene(scene);
        stage.show();
    }
    public static void main(String[] args) {
        launch();
    }
}
