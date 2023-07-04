package com.orderingsystem.ui.admin;

import com.orderingsystem.dao.GoodsDao;
import com.orderingsystem.entity.Goods;
import com.orderingsystem.share.ShareValue;
import javafx.application.Application;
import javafx.application.Platform;
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
import java.util.Objects;
import java.util.Optional;

public class GoodsAdmin extends Application {
    private GoodsDao goodsDao = new GoodsDao();
    BorderPane borderPane = new BorderPane();
    TextField tSearch = new TextField();
    Button search = new Button("搜索",new ImageView(new Image("com\\orderingsystem\\icon\\搜索.png")));
    Label lsearch = new Label("菜品名称：");
    HBox hTop = new HBox(lsearch,tSearch,search);

    TableView table = new TableView();
    TableColumn c1 = new TableColumn("菜品编号");
    TableColumn c2 = new TableColumn("菜品名称");
    TableColumn c3 = new TableColumn("菜品单价");
    TableColumn c4 = new TableColumn("菜品类别");
    TableColumn c5 = new TableColumn("菜品库存");
    TableColumn c6 = new TableColumn("菜品详情");
    TableColumn c7 = new TableColumn("添加时间");
    TableColumn c8 = new TableColumn("菜品说明");
    Button insert = new Button("增加",new ImageView("com\\orderingsystem\\icon\\增加.png"));
    Button delete = new Button("删除",new ImageView("com\\orderingsystem\\icon\\删除.png"));
    Button update = new Button("修改",new ImageView("com\\orderingsystem\\icon\\修改.png"));
    Button exit = new Button("退出",new ImageView("com\\orderingsystem\\icon\\退出.png"));
    HBox hBottom = new HBox(insert,delete,update,exit);
    public void binding(){
        table.getItems().clear();
        String str = tSearch.getText();
        c1.setCellValueFactory(new PropertyValueFactory("gid"));
        c2.setCellValueFactory(new PropertyValueFactory("gname"));
        c3.setCellValueFactory(new PropertyValueFactory("gprice"));
        c4.setCellValueFactory(new PropertyValueFactory("tname"));
        c5.setCellValueFactory(new PropertyValueFactory("gkc"));
        c6.setCellValueFactory(new PropertyValueFactory("gxl"));
        c7.setCellValueFactory(new PropertyValueFactory("gtime"));
        c8.setCellValueFactory(new PropertyValueFactory("ginfo"));
        try {
            table.getItems().addAll(goodsDao.queryAll(str,null));
        } catch (SQLException e){
            e.printStackTrace();
        }
    }
    {
       insert.getStyleClass().add("button");
    }
    public void start(Stage stage) throws Exception {
        stage.getIcons().add(new Image("com\\orderingsystem\\icon\\首页.png"));
        stage.setWidth(1200);
        stage.setHeight(700);
        stage.setTitle("你好【"+ ShareValue.USER.getName() +"】");
        stage.setResizable(false);

        table.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        tSearch.setPromptText("请输入菜品名称关键字");
        search.setFont(new Font(15));
        hTop.setAlignment(Pos.CENTER);
        hTop.setPrefHeight(40);
        hTop.setSpacing(10);
        borderPane.setTop(hTop);
        table.getColumns().addAll(c1,c2,c3,c4,c5,c6,c7,c8);
        borderPane.setCenter(table);
        binding();
        hBottom.setAlignment(Pos.CENTER);
        hBottom.setSpacing(15);
        hBottom.setPrefHeight(40);
        insert.setFont(new Font(15));
        delete.setFont(new Font(15));
        update.setFont(new Font(15));
        exit.setFont(new Font(15));
        borderPane.setBottom(hBottom);
        search.setOnAction(e->{
           binding();
        });
        tSearch.setOnKeyReleased(e->{
            binding();
        });
        insert.setOnAction(e->{
            try {
                new InsertGoods(GoodsAdmin.this).start(new Stage());
            } catch (Exception ex) {
                throw new RuntimeException(ex);
            }
        });
        delete.setOnAction(e->{
             int rIndex = table.getSelectionModel().getSelectedIndex();
            Goods goods = (Goods)table.getSelectionModel().getSelectedItem();
            if (Objects.isNull(goods)) {
                Alert alert = new Alert(Alert.AlertType.ERROR, "请选择要删除的数据!!!");
                alert.showAndWait();
                return;
            }
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "确定要删除该数据?");
            Optional<ButtonType> btn = alert.showAndWait();
            if(btn.get().getText().equals("确定")){
                try {
                    boolean flag = goodsDao.deleteGoods(goods.getGid());
                    if(flag){
                        new Alert(Alert.AlertType.INFORMATION,"删除成功!").show();
                        binding();
                    }else{
                        new Alert(Alert.AlertType.INFORMATION,"删除失败").show();
                    }
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });
        update.setOnAction(e->{
            try {
                Goods goods = (Goods) table.getSelectionModel().getSelectedItem();
                if (Objects.isNull(goods)) {
                    Alert alert = new Alert(Alert.AlertType.ERROR, "请选择要修改的菜品!");
                    alert.showAndWait();
                    return;
                }
                new UpdateGoods(GoodsAdmin.this,goods).start(new Stage());
            } catch (Exception ex) {
                throw new RuntimeException(ex);
            }
        });
        exit.setOnAction(e->{
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "确定要退出?");
            Optional<ButtonType> btn = alert.showAndWait();
            if(btn.get().getText().equals("确定")){
                Platform.exit();
            }
        });
        Scene scene = new Scene(borderPane);
        scene.getStylesheets().add(getClass().getResource("/com/orderingsystem/css/admin/goodsAdmin.css").toExternalForm());
        stage.setScene(scene);
        stage.show();
    }
    public static void main(String[] args) {
        launch();
    }
}
