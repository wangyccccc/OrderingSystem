package com.orderingsystem.ui.admin;

import com.orderingsystem.dao.GoodsDao;
import com.orderingsystem.dao.GoodsTypeDao;
import com.orderingsystem.entity.Goods;
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.sql.SQLException;
import java.util.Optional;

public class InsertGoods extends Application {
    private File file;
    private GoodsAdmin goodsAdmin;
    private Goods goods;
    public InsertGoods(){
    }
    public InsertGoods(GoodsAdmin goodsAdmin){
        this.goodsAdmin = goodsAdmin;
    }
    private GoodsTypeDao goodsTypeDao = new GoodsTypeDao();
    BorderPane bp = new BorderPane();
    GridPane gp = new GridPane();

    Label lGoodsName = new Label("菜品名称:");
    TextField tGoodsName = new TextField();

    Label lGoodsType = new Label("菜品类别:");
    ChoiceBox<String> cGoodsType = new ChoiceBox<>();
    Label lGoodsCount = new Label("菜品库存:");
    Spinner<Integer> cGoodsCount = new Spinner<>(0, 1000, 0);

    ImageView goodsImage = new ImageView();
    Label lGoodsImage = new Label("菜品图片:");
    FileChooser fGoodsImage = new FileChooser();
    Button choiseBtn = new Button("选择图片");
    HBox filePane = new HBox(goodsImage);

    Label lAddTime = new Label("添加时间:");
    TextField ttime = new TextField();

    Label lGoodsPrice = new Label("菜品价格:");
    TextField tGoodsPrice = new TextField();
    Label lComment = new Label("菜品信息:");
    TextArea tComment = new TextArea();

    Button yes = new Button("确定",new ImageView(new Image("com\\orderingsystem\\icon\\确定.png")));
    Button cancel = new Button("关闭",new ImageView(new Image("com\\orderingsystem\\icon\\退出.png")));
    HBox hButton = new HBox(yes,cancel);

    public void start(Stage stage) throws Exception {
        stage.setTitle("编辑菜品");
        stage.setHeight(500);
        stage.setWidth(400);
        gp.add(lGoodsName,0,0);
        gp.add(tGoodsName,1,0);

        gp.add(lGoodsType,0,2);
        gp.add(cGoodsType,1,2);
        goodsTypeDao.queryAll().forEach(e->{
            cGoodsType.getItems().add(e.getTname());
        });
        cGoodsType.setValue(cGoodsType.getItems().get(0));

        gp.add(lGoodsPrice,0,4);
        gp.add(tGoodsPrice,1,4);
        gp.add(lGoodsCount,0,5);
        gp.add(cGoodsCount,1,5);
        goodsImage.setFitWidth(200);
        goodsImage.setFitHeight(200);
        goodsImage.setPreserveRatio(true);
        goodsImage.setPreserveRatio(true);
        goodsImage.setSmooth(true);
        goodsImage.setCache(true);
        goodsImage.setImage(new Image("com/orderingsystem/images/other/暂无图片1.jpg"));
        gp.add(lGoodsImage,0,6);
        gp.add(filePane,1,6);
        filePane.setSpacing(5);
        gp.add(lComment,0,7);
        gp.add(tComment,1,7);
        tComment.setPromptText("请输入备注");
        tComment.setPrefWidth(50);
        tComment.setPrefHeight(50);
        gp.setAlignment(Pos.CENTER);
        gp.setHgap(10);
        gp.setVgap(10);
        bp.setCenter(gp);
        hButton.setAlignment(Pos.CENTER);
        hButton.setPrefHeight(35);
        hButton.setSpacing(30);
        bp.setBottom(hButton);
        goodsImage.setOnMouseClicked(e->{
            file = fGoodsImage.showOpenDialog(null);
            if(null != file){
                goodsImage.setFitWidth(200);
                goodsImage.setFitHeight(200);
                goodsImage.setPreserveRatio(true);
                goodsImage.setPreserveRatio(true);
                goodsImage.setSmooth(true);
                goodsImage.setCache(true);
                goodsImage.setImage(new Image("file:\\"+file.getPath()));
            }else{
                new Alert(Alert.AlertType.INFORMATION,"取消选择!").show();
            }
        });
        yes.setOnAction(e->{
            String goodsName = tGoodsName.getText();
            String goodsType = cGoodsType.getValue();
            Integer goodsPrice = Integer.valueOf(tGoodsPrice.getText());
            Integer goodsKc = cGoodsCount.getValue();
            String goodsImagePath = file.getPath();
            String goodsInfo= tComment.getText();
            Goods goods = new Goods();
            goods.setGname(goodsName);
            goods.setTname(goodsType);
            goods.setGprice(goodsPrice);
            goods.setGkc(goodsKc);
            goods.setGpath(goodsImagePath);
            goods.setGinfo(goodsInfo);
            try {
                boolean flag = new GoodsDao().insertGoods(goods);
                if(flag){
                    new Alert(Alert.AlertType.INFORMATION,"添加成功!").show();
                    goodsAdmin.binding();// 调用 Index页面 binging方法刷新菜品数据
                }else{
                    new Alert(Alert.AlertType.INFORMATION,"添加失败").show();
                }
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
        });
        cancel.setOnAction(e->{
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "确定要关闭?");
            Optional<ButtonType> btn = alert.showAndWait();
            if(btn.get().getText().equals("确定")){
                stage.close();
            }
        });

        stage.setScene(new Scene(bp));
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}
