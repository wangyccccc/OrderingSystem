package com.orderingsystem.ui.home;

import com.orderingsystem.dao.UserDao;
import com.orderingsystem.entity.User;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.sql.SQLException;

public class Register extends Application {
    @Override
    public void start(Stage stage) throws Exception {
        VBox root = new VBox();
        root.setAlignment(Pos.TOP_CENTER);
        root.setSpacing(20);
        root.setPadding(new Insets(20));

        Label title = new Label("用户注册");
        title.setFont(Font.font("Arial", FontWeight.BOLD, 20));
        root.getChildren().add(title);

        GridPane formGrid = new GridPane();
        formGrid.setHgap(10);
        formGrid.setVgap(10);
        formGrid.setAlignment(Pos.CENTER);

        Label accountLabel = new Label("账号：");
        TextField accountField = new TextField();
        formGrid.addRow(0, accountLabel, accountField);

        Label passwordLabel = new Label("密码：");
        PasswordField passwordField = new PasswordField();
        formGrid.addRow(1, passwordLabel, passwordField);

        Label nameLabel = new Label("姓名：");
        TextField nameField = new TextField();
        formGrid.addRow(2, nameLabel, nameField);

        Label genderLabel = new Label("性别：");
        ToggleGroup genderGroup = new ToggleGroup();
        RadioButton maleRadioButton = new RadioButton("男");
        maleRadioButton.setSelected(true);
        maleRadioButton.setToggleGroup(genderGroup);
        RadioButton femaleRadioButton = new RadioButton("女");
        femaleRadioButton.setToggleGroup(genderGroup);
        HBox genderBox = new HBox(10, maleRadioButton, femaleRadioButton);
        formGrid.addRow(3, genderLabel, genderBox);

        Label ageLabel = new Label("年龄：");
        TextField ageField = new TextField();
        formGrid.addRow(4, ageLabel, ageField);

        Label phoneLabel = new Label("电话：");
        TextField phoneField = new TextField();
        formGrid.addRow(5, phoneLabel, phoneField);

        Label addressLabel = new Label("地址：");
        TextField addressField = new TextField();
        formGrid.addRow(6, addressLabel, addressField);

        Label hobbyLabel = new Label("爱好：");
        CheckBox eatingCheckBox = new CheckBox("吃饭");
        CheckBox drinkingCheckBox = new CheckBox("喝水");
        CheckBox sleepingCheckBox = new CheckBox("睡觉");
        HBox hobbyBox = new HBox(10, eatingCheckBox, drinkingCheckBox, sleepingCheckBox);
        eatingCheckBox.setSelected(true);
        formGrid.addRow(7, hobbyLabel, hobbyBox);

        Label imageLabel = new Label("图片：");
        Button imageButton = new Button("选择图片");
        FileChooser fGoodsImage = new FileChooser();
        TextField tGoodsImage = new TextField();
        tGoodsImage.setPromptText("请选择图片");
        formGrid.addRow(8, imageLabel,tGoodsImage);

        Label infoLabel = new Label("信息：");
        TextArea infoArea = new TextArea();
        infoArea.setPrefWidth(50);
        infoArea.setPrefHeight(30);
        formGrid.addRow(9, infoLabel, infoArea);

        Button yes = new Button("确定",new ImageView(new Image("com\\orderingsystem\\icon\\确定.png")));
        Button refresh = new Button("重置",new ImageView(new Image("com\\orderingsystem\\icon\\重置.png")));
        HBox hButton = new HBox(yes,refresh);
        hButton.setAlignment(Pos.CENTER);
        hButton.setPrefHeight(35);
        hButton.setSpacing(30);

        root.getChildren().add(formGrid);
        root.getChildren().add(hButton);

        tGoodsImage.setOnMouseClicked(e->{
            File file = fGoodsImage.showOpenDialog(null);
            String filePath = null;
            if(null != file){
                tGoodsImage.setText(file.getPath());
                tGoodsImage.setDisable(true);
                filePath = file.getPath();
            }else{
                new Alert(Alert.AlertType.INFORMATION,"取消选择!").show();
            }
        });
        yes.setOnAction(e->{
            String account = accountField.getText();
            String password = passwordField.getText();
            String name = nameField.getText();
            String gender = ((RadioButton) genderGroup.getSelectedToggle()).getText();
            int age = Integer.parseInt(ageField.getText());
            String phone = phoneField.getText();
            String address = addressField.getText();
            String hobbies = "";
            if (eatingCheckBox.isSelected()) {
                hobbies += "吃饭 ";
            }
            if (drinkingCheckBox.isSelected()) {
                hobbies += "喝水 ";
            }
            if (sleepingCheckBox.isSelected()) {
                hobbies += "睡觉";
            }
            String imagePath = tGoodsImage.getText();
            String info = infoArea.getText();
            User user = new User();
            user.setAccount(account);
            user.setPassword(password);
            user.setName(name);
            user.setSex(gender);
            user.setAge(age);
            user.setTel(phone);
            user.setAddress(address);
            user.setHobby(hobbies);
            user.setImagepath(imagePath);
            user.setInfo(info);
            try {
                boolean flag = new UserDao().register(user);
                if(flag){
                    new Alert(Alert.AlertType.INFORMATION,"注册成功!").show();
                }else{
                    new Alert(Alert.AlertType.INFORMATION,"增加失败").show();
                }
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
        });
        refresh.setOnAction(e->{
            accountField.setText("");
            passwordField.setText("");
            nameField.setText("");
            maleRadioButton.setSelected(true);
            ageField.setText("");
            phoneField.setText("");
            addressField.setText("");
            eatingCheckBox.setSelected(true);
            drinkingCheckBox.setSelected(false);
            sleepingCheckBox.setSelected(false);
            tGoodsImage.setText("");
            infoArea.setText("");
        });

        Scene scene = new Scene(root, 300, 450);
        stage.setScene(scene);
        stage.setTitle("用户注册");
        stage.show();
    }

    public static void main(String[] args) {
        Application.launch();
    }
}
