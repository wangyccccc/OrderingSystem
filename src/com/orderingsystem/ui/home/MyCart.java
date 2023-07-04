package com.orderingsystem.ui.home;


import com.orderingsystem.dao.*;
import com.orderingsystem.entity.Cart;
import com.orderingsystem.entity.Order;
import com.orderingsystem.entity.OrderItem;
import com.orderingsystem.entity.User;
import com.orderingsystem.share.ShareValue;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class MyCart extends Application {
    private User user = ShareValue.USER;
    private CartDao cartDao = new CartDao();
    private GoodsDao goodsDao = new GoodsDao();
    private OrderDao orderDao = new OrderDao();
    private OrderItemDao orderItemDao = new OrderItemDao();
    // 中
    TableView tableView = new TableView();
    TableColumn c1 = new TableColumn("菜品名称");
    TableColumn c2 = new TableColumn("菜品单价");
    TableColumn c3 = new TableColumn("菜品数量");
    TableColumn c4 = new TableColumn("价格小计");
    TableColumn c5 = new TableColumn("添加时间");
    /**
     * 表格绑定数据
     * @param cartList
     */
    public void binding(List<Cart> cartList){
        // 清除表格数据
        tableView.getItems().clear();
        // 转换表格的列名
        c1.setCellValueFactory(new PropertyValueFactory("gname"));
        c2.setCellValueFactory(new PropertyValueFactory("gprice"));
        c3.setCellValueFactory(new PropertyValueFactory("gcount"));
        c4.setCellValueFactory(new PropertyValueFactory("cprice"));
        c5.setCellValueFactory(new PropertyValueFactory("ctime"));
        // 绑定数据
        tableView.getItems().addAll(cartList);
    }

    @Override
    public void start(Stage stage) throws Exception {
        stage.getIcons().add(new Image("/com/orderingsystem/icon/商店.png"));
        // stage.setResizable(false);// 设置不能改变窗体大小
        stage.setTitle("我的购物车");

        // 设置列宽
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

        // 中
        // 给表格添加列名
        tableView.getColumns().addAll(c1,c2,c3,c4,c5);

        // 绑定表格数据
        binding(cartDao.queryCartByUuid(ShareValue.USER.getUuid()));

        RadioButton ts = new RadioButton("堂食");
        ts.setSelected(true);
        ts.setPadding(new Insets(10));
        ts.setGraphic(new ImageView(new Image("/com/orderingsystem/icon/堂食.png")));
        RadioButton db = new RadioButton("打包");
        db.setPadding(new Insets(10));
        db.setGraphic(new ImageView(new Image("/com/orderingsystem/icon/打包.png")));
        ToggleGroup toggleGroup = new ToggleGroup();
        ts.setToggleGroup(toggleGroup);
        db.setToggleGroup(toggleGroup);
        HBox hRadio = new HBox(ts,db);
        hRadio.setAlignment(Pos.CENTER_LEFT);
        hRadio.setSpacing(10);
        Button gw = new Button("继续购物");
        gw.setStyle("-fx-padding: 5");
        gw.setStyle("-fx-background-color: #00BFFF; -fx-text-fill: white; -fx-font-size: 16px; -fx-font-weight: bold; -fx-padding: 10px 20px; -fx-border-radius: 20px;");
        Button js = new Button("我要结账");
        js.setStyle("-fx-background-color: #FF6347; -fx-text-fill: white; -fx-font-size: 16px; -fx-font-weight: bold; -fx-padding: 10px 20px; -fx-border-radius: 20px;");
        HBox hBotton = new HBox(10,gw, js);
        hBotton.setAlignment(Pos.CENTER_RIGHT);
        hBotton.setSpacing(10);

        HBox hBox = new HBox(hRadio,hBotton);
        hBox.setAlignment(Pos.CENTER);
        hBox.setSpacing(275);


        VBox root = new VBox(10,tableView,hBox);
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
        js.setOnAction(e->{
            try {
                List<Cart> cartList = cartDao.queryCartByUuid(user.getUuid());
                List<OrderItem> orderItemList = new ArrayList<>();
                for(Cart cart : cartList){
                    OrderItem orderItem = new OrderItem();
                    orderItem.setGoods(cart.getGoods());
                    orderItem.setGcount(cart.getGcount());
                    orderItem.setOrderitemprice(cart.getCprice());
                    orderItemList.add(orderItem);
                }
                Order order = new Order();
                order.setUser(user);
                order.setOrderItemList(orderItemList);
                order.calcOrderPrice();

                Alert alert1 = new Alert(Alert.AlertType.CONFIRMATION, "确定结账?");
                Optional<ButtonType> btn = alert1.showAndWait();
                if(btn.get().getText().equals("确定")){

                    if(orderDao.insertOrder(order)) {

                        for(Cart cart : cartList){
                            goodsDao.updateGoodsGkc(cart.getGoods().getGid(),cart.getGcount());
                        }
                        cartDao.clearCart(user);
                        Alert alert = new Alert(Alert.AlertType.INFORMATION,"结算成功!");
                        alert.setTitle("信息");
                        alert.showAndWait();

                        stage.close();

                        new MyOrder().start(new Stage());
                    }else {
                        Alert alert = new Alert(Alert.AlertType.ERROR,"结算失败!");
                        alert.setTitle("警告");
                        alert.showAndWait();
                    }
                }
            } catch (Exception ee) {
                throw new RuntimeException(ee);
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
