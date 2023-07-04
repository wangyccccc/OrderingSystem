package com.orderingsystem.ui.admin;

import com.mysql.jdbc.StringUtils;
import com.orderingsystem.dao.UserDao;
import com.orderingsystem.entity.User;
import com.orderingsystem.share.ShareValue;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

import java.util.Objects;
import java.util.Optional;

public class Login extends Application {// 继承 Application
    public static String username;
    private String account;
    private String msg;
    private UserDao userDao = new UserDao();
    Label systemName = new Label("玩具总动员主题餐厅");
    GridPane gridPane = new GridPane();
    Label lAccount = new Label("账号：");
    TextField tAccount = new TextField();
    Label lPassword = new Label("密码：");
    PasswordField pPassword = new PasswordField();
    ImageView login = new ImageView(new Image("com/orderingsystem/images/other/登陆.png"));
    Hyperlink register = new Hyperlink("没有账号?");
    Hyperlink wjmm = new Hyperlink("忘记密码");
    Hyperlink exit = new Hyperlink("退出");
    HBox hBottom = new HBox(register,wjmm,exit);
    AnchorPane anchorPane = new AnchorPane(systemName,gridPane,login,hBottom);//
    public void start(Stage stage) throws Exception {
        stage.setTitle("管理员登录");
        stage.setWidth(500);
        stage.setHeight(400);
        systemName.setId("systemName");
        AnchorPane.setLeftAnchor(systemName,100d);
        AnchorPane.setTopAnchor(systemName,30d);
        tAccount.setPromptText("请输入账号");
        gridPane.add(lAccount,0,0);
        gridPane.add(tAccount,1,0);
        pPassword.setPromptText("请输入密码");
        gridPane.add(lPassword,0,1);
        gridPane.add(pPassword,1,1);
        gridPane.setAlignment(Pos.CENTER);
        gridPane.setVgap(20);
        anchorPane.setId("anchorPane");
        AnchorPane.setLeftAnchor(gridPane,130d);
        AnchorPane.setTopAnchor(gridPane,125d);
        AnchorPane.setLeftAnchor(login,135d);
        AnchorPane.setTopAnchor(login,250d);
        hBottom.setSpacing(10);
        AnchorPane.setLeftAnchor(hBottom,163d);
        AnchorPane.setTopAnchor(hBottom,330d);
        login.setOnMouseClicked(e->{
            account = tAccount.getText();
            String password = pPassword.getText();
            if(StringUtils.isNullOrEmpty(account)) {
                msg = "账号不能为空!";
                new Alert(Alert.AlertType.INFORMATION,msg).show();
                return;
            }
            if(StringUtils.isNullOrEmpty(password)) {
                msg = "密码不能为空!";
                new Alert(Alert.AlertType.INFORMATION,msg).show();
                return;
            }
            try {
                User user = new User();
                user.setAccount(account);
                user.setPassword(password);
                ShareValue.USER = userDao.login(user);
                if(Objects.nonNull(ShareValue.USER)){
                    new Admin().start(new Stage());
                    stage.close();
                }else{
                    Alert alert = new Alert(Alert.AlertType.ERROR,"账号或密码有误!");
                    alert.setTitle("警告");
                    alert.showAndWait();
                                        alert.getDialogPane().setStyle(
                            "-fx-background-color: #FF0000; " +
                                    "-fx-border-color: #FFFFFF; " +
                                    "-fx-border-width: 5px; " +
                                    "-fx-text-fill: #FFFFFF; " +
                                    "-fx-font-size: 16px; " +
                                    "-fx-font-weight: bold;");
                }
            } catch (Exception ex) {
                throw new RuntimeException(ex);
            }
        });

        register.setOnAction(e ->{
            try {
                new Register().start(new Stage());
            } catch (Exception ex) {
                throw new RuntimeException(ex);
            }
            stage.close();
        });
        exit.setOnAction(e->{
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "确定退出?");
            Optional<ButtonType> btn = alert.showAndWait();
            if(btn.get().getText().equals("确定")){
                Platform.exit();
            }
        });
        register.setOnAction(e->{
            try {
                new Register().start(new Stage());
            } catch (Exception ex) {
                throw new RuntimeException(ex);
            }
        });
        Scene scene = new Scene(anchorPane);
        scene.getStylesheets().add(getClass().getResource("/com/orderingsystem/css/admin/login.css").toExternalForm());
        stage.setScene(scene);
        stage.show();
    }

    public void stop() throws Exception {
    }

    public static void main(String[] args) {
        Application.launch();
    }
}
