package com.orderingsystem.ui.home;

import com.mysql.jdbc.StringUtils;
import com.orderingsystem.dao.CartDao;
import com.orderingsystem.dao.GoodsDao;
import com.orderingsystem.dao.UserDao;
import com.orderingsystem.entity.Cart;
import com.orderingsystem.entity.Goods;
import com.orderingsystem.entity.User;
import com.orderingsystem.share.ShareValue;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Line;
import javafx.stage.Stage;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public class Index extends Application {
    private UserDao userDao = new UserDao();
    private CartDao cartDao = new CartDao();
    private final GoodsDao goodsDao = new GoodsDao();
    private String goodsType = "全部";

    Line line = new Line(0,78,1265,78);// 线条
    Label all = new Label("全部");
    Label ck = new Label("Alien");
    Label fs = new Label("Lotso");
    Label ss = new Label("Buzz");
    Label zs = new Label("Woody");
    Label tk = new Label("BUCKET");
    Label fz = new Label("TOYS");
    HBox h1 = new HBox(all,ck,fs,ss,zs,tk,fz);
    HBox h2 = new HBox();
    Line lGoodsName = new Line(705,71,841,71);
    TextField goodsName = new TextField();
    Button search = new Button("菜品搜索",new ImageView(new Image("com\\orderingsystem\\icon\\搜索.png")));

    Hyperlink myCart = new Hyperlink("我的购物车");
    Hyperlink myOrder = new Hyperlink("我的订单");
    Hyperlink myInfo = new Hyperlink("个人中心");
    Hyperlink vip = new Hyperlink("加入会员?");
    CheckBox cvip = new CheckBox();
    Hyperlink about = new Hyperlink("关于");
    Hyperlink exit = new Hyperlink("退出");
    Separator s1 = new Separator(Orientation.VERTICAL);
    Separator s2 = new Separator(Orientation.VERTICAL);
    Separator s3 = new Separator(Orientation.VERTICAL);
    Separator s4 = new Separator(Orientation.VERTICAL);
    Separator s5 = new Separator(Orientation.VERTICAL);
    AnchorPane anchorPaneTop = new AnchorPane(line,h1,goodsName,search,lGoodsName,h2);
    {
        goodsName.setPromptText("请输入查询关键字");
        h1.setSpacing(30);
        h1.setAlignment(Pos.BOTTOM_CENTER);


        AnchorPane.setLeftAnchor(h1,10d);
        AnchorPane.setTopAnchor(h1,25d);
        AnchorPane.setLeftAnchor(goodsName,696d);
        AnchorPane.setTopAnchor(goodsName,41d);
        AnchorPane.setLeftAnchor(search,855d);
        AnchorPane.setTopAnchor(search,42d);
        AnchorPane.setLeftAnchor(h2,880d);
        AnchorPane.setTopAnchor(h2,2d);

        h1.getChildren().forEach(e->{
            if(e instanceof Label){
                Label label = (Label)e;
                String goodsType = label.getText();
                label.setOnMouseClicked(ee->{
                    label.setStyle("-fx-text-fill: red");
                    this.goodsType = goodsType;
                    try {
                        if(this.goodsType.equals("全部")) bindingGoods(goodsDao.queryAll("",null));
                        else bindingGoods(goodsDao.queryAll("",goodsType));
                    } catch (SQLException ex) {
                        throw new RuntimeException(ex);
                    }
                });
                label.setOnMouseEntered(ee->{
                    label.setStyle("-fx-text-fill: red");
                });
                label.setOnMouseExited(ee->{
                    label.setStyle("-fx-text-fill: #FFFFFFFF");
                });
            }
        });
        search.setOnAction(e->{
            String goodsNameKey = goodsName.getText();
            try {
                List<Goods> goodsList = null;
                if(goodsType.equals("全部")) goodsList = goodsDao.queryAll(goodsNameKey, null);
                else goodsList = goodsDao.queryAll(goodsNameKey, goodsType);
                bindingGoods(goodsList);
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
        });
        goodsName.setOnKeyReleased(e->{
            String goodsNameKey = goodsName.getText();
            try {
                List<Goods> goodsList = null;
                if(goodsType.equals("全部")) goodsList = goodsDao.queryAll(goodsNameKey, null);
                else goodsList = goodsDao.queryAll(goodsNameKey, goodsType);
                bindingGoods(goodsList);
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
        });
        exit.setOnAction(e->{
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "确定退出?");
            Optional<ButtonType> btn = alert.showAndWait();
            if(btn.get().getText().equals("确定")){
                Platform.exit();
            }
        });
    }
    Label imageLabel = null;
    Label goodsName2 = null;
    Label goodsType2 = null;
    Label goodsXl = null;
    Label goodsPrice = null;
    Button addCart = null;
    Button buy = null;
    AnchorPane anchorPane = null;
    VBox goodsVBox = null;
    FlowPane flowPane = new FlowPane();
 
    public void addButtonEvent(Button button,Integer gid,Integer gprice){
        button.setOnAction(e->{
            String buttonText = button.getText();
            if(!StringUtils.isNullOrEmpty(buttonText)){
                if(buttonText.contains("购物车")){
                    Cart cart = null;
                    try {
                        cart = cartDao.queryCartByGidUuid(gid, ShareValue.USER.getUuid());
                        if(null != cart){
                            Cart myCart  = new Cart();
                            Integer gcount = cart.getGcount() + 1;
                            Integer cartPrice = gcount * gprice;
                            boolean flag = cartDao.updateCartByGidUuid(gid,ShareValue.USER.getUuid(), gcount, Double.valueOf(gprice));
                            if(flag) new Alert(Alert.AlertType.INFORMATION,"添加成功!").show();
                            else new Alert(Alert.AlertType.ERROR,"添加失败!").show();
                            cart = null;
                        }else{
                            Cart myCart = new Cart();
                            myCart.setGoods(goodsDao.queryByGid(gid));
                            myCart.setUuid(ShareValue.USER.getUuid());
                            myCart.setGcount(1);
                            myCart.setCprice(Double.valueOf(gprice));
                            boolean flag = cartDao.insertCart(myCart);
                            if(flag) new Alert(Alert.AlertType.INFORMATION,"添加成功!").show();
                            else new Alert(Alert.AlertType.ERROR,"添加失败!").show();
                        }
                    } catch (SQLException ex) {
                        throw new RuntimeException(ex);
                    }
                }else{
                    System.out.println("立即购买--"+gid);
                }
            }
        });
    }
    public void addLabelEvent(Label label,Goods goods){
        label.setOnMouseClicked(e->{
            try {
            } catch (Exception ex) {
                throw new RuntimeException(ex);
            }
        });
    }
    public void bindingGoods(List<Goods> goodsList){
        flowPane.getChildren().clear();
        for (int i = 0; i < goodsList.size(); i++) {
            Image image = new Image("file:\\"+goodsList.get(i).getGpath(),300,180,false,false);
            imageLabel = new Label();
            imageLabel.setGraphic(new ImageView(image));
            goodsName2 = new Label("名称：" + goodsList.get(i).getGname());
            goodsName2.getStyleClass().add("lb");
            goodsType2 = new Label("类别：" + goodsList.get(i).getTname());
            goodsType2.getStyleClass().add("lb");
            goodsXl = new Label("库存：" + goodsList.get(i).getGkc());
            goodsXl.getStyleClass().add("lb");
            goodsPrice = new Label("价格：" + goodsList.get(i).getGprice());
            goodsPrice.getStyleClass().add("lb");

            addCart = new Button("加入购物车",new ImageView("com/orderingsystem/icon/加入购物车.png"));
            addCart.setTooltip(new Tooltip("加入购物车"));
            buy = new Button("立即购买",new ImageView("com/orderingsystem/icon/立即购买.png"));
            buy.setTooltip(new Tooltip("立即购买"));
            anchorPane = new AnchorPane(goodsName2,goodsType2,goodsXl,goodsPrice,addCart,buy);
            anchorPane.getStyleClass().add("anchorpane");
            AnchorPane.setTopAnchor(goodsName2,2d);// 商品名称
            AnchorPane.setLeftAnchor(goodsName2,38d);
            AnchorPane.setTopAnchor(goodsType2,2d);// 商品类别
            AnchorPane.setLeftAnchor(goodsType2,170d);
            AnchorPane.setTopAnchor(goodsXl,25d);// 商品定位
            AnchorPane.setLeftAnchor(goodsXl,38d);
            AnchorPane.setTopAnchor(goodsPrice,25d);// 商品价格
            AnchorPane.setLeftAnchor(goodsPrice,170d);
            AnchorPane.setTopAnchor(addCart,52d);// 加入购物车
            AnchorPane.setLeftAnchor(addCart,26d);
            AnchorPane.setTopAnchor(buy,52d);// 立即购买
            AnchorPane.setLeftAnchor(buy,168d);
            goodsVBox = new VBox(imageLabel,anchorPane);
            flowPane.getChildren().add(goodsVBox);
            addButtonEvent(addCart,goodsList.get(i).getGid(), (int) goodsList.get(i).getGprice());
            addButtonEvent(buy,goodsList.get(i).getGid(),(int) goodsList.get(i).getGprice());
            addLabelEvent(imageLabel,goodsList.get(i));
        };
    }
    ScrollPane scrollPane = new ScrollPane(flowPane);
    private VBox vGlobal = new VBox(anchorPaneTop,scrollPane);
    {
        h2.getChildren().addAll(myCart,s1,myOrder,s2,myInfo,s3,about,s4,vip,cvip,s5,exit);
    }
    {

        lGoodsName.setId("lHearName");
        h2.setAlignment(Pos.CENTER);
        s1.setMaxHeight(12);
        s2.setMaxHeight(12);
        s3.setMaxHeight(12);
        s4.setMaxHeight(12);
        search.setId("search");
        all.getStyleClass().add("la");
        ck.getStyleClass().add("la");
        ss.getStyleClass().add("la");
        fs.getStyleClass().add("la");
        zs.getStyleClass().add("la");
        tk.getStyleClass().add("la");
        fz.getStyleClass().add("la");
        anchorPaneTop.getStyleClass().add("anchorPaneTop");
        line.getStyleClass().add("line");

        /*
         * 2.下
         */
        scrollPane.setId("scrollPane");
        flowPane.getStyleClass().add("flowpane");
        vGlobal.getStyleClass().add("vGlobal");
    }
    @Override
    public void start(Stage stage) throws Exception {
        flowPane.setMinWidth(1258);
        flowPane.setMinHeight(518);
        myCart.setOnAction(e->{
            try {
                stage.close();
                new MyCart().start(new Stage());
            } catch (Exception ex) {
                throw new RuntimeException(ex);
            }
        });
        myOrder.setOnAction(e->{
            try {
                stage.close();
                new MyOrder().start(new Stage());
            } catch (Exception ex) {
                throw new RuntimeException(ex);
            }
        });
        cvip.setOnAction(e->{

        });
        stage.getIcons().add(new Image("/com/orderingsystem/icon/商店.png"));
        stage.setWidth(1258);
        stage.setHeight(638);
        stage.setTitle("商品商场");
        bindingGoods(goodsDao.queryAll("",null));

        Scene scene = new Scene(vGlobal);
        flowPane.prefWidthProperty().bind(scene.widthProperty());
        scene.getStylesheets().add(getClass().getResource("/com/orderingsystem/css/home/index.css").toExternalForm());
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
    }
    public static void main(String[] args) {
        launch();
    }
}
