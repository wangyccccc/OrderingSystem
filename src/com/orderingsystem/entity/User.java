package com.orderingsystem.entity;


public class User {
    private Integer uuid;
    private String account;
    private String password;
    private String name;
    private String sex;
    private Integer age;
    private String tel;
    private String address;
    private String hobby;
    private String imagepath;
    private Integer power;
    private String registertime;
    private String info;

    public Integer getUuid() {
        return uuid;
    }

    public void setUuid(Integer uuid, int anInt) {
        this.uuid = uuid;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getHobby() {
        return hobby;
    }

    public void setHobby(String hobby) {
        this.hobby = hobby;
    }

    public String getImagepath() {
        return imagepath;
    }

    public void setImagepath(String imagepath) {
        this.imagepath = imagepath;
    }

    public Integer getPower() {
        return power;
    }

    public void setPower(Integer power) {
        this.power = power;
    }

    public String getRegistertime() {
        return registertime;
    }

    public void setRegistertime(String registertime) {
        this.registertime = registertime;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public User() {
    }
    public User(String account) {
        this.account = account;
    }

    public User(Integer uuid, String account, String password, String name, String sex, Integer age, String tel, String address, String hobby, String imagepath, Integer power, String registertime, String info) {
        this.uuid = uuid;
        this.account = account;
        this.password = password;
        this.name = name;
        this.sex = sex;
        this.age = age;
        this.tel = tel;
        this.address = address;
        this.hobby = hobby;
        this.imagepath = imagepath;
        this.power = power;
        this.registertime = registertime;
        this.info = info;
    }
    public User(String account, String password, String name, String sex, Integer age, String tel, String address, String hobby, String imagepath, Integer power, String registertime, String info) {
        this.account = account;
        this.password = password;
        this.name = name;
        this.sex = sex;
        this.age = age;
        this.tel = tel;
        this.address = address;
        this.hobby = hobby;
        this.imagepath = imagepath;
        this.power = power;
        this.registertime = registertime;
        this.info = info;
    }
    public User(String account, String password, String name, String sex, Integer age, String tel, String address, String hobby, String imagepath, Integer power, String info) {
        this.account = account;
        this.password = password;
        this.name = name;
        this.sex = sex;
        this.age = age;
        this.tel = tel;
        this.address = address;
        this.hobby = hobby;
        this.imagepath = imagepath;
        this.power = power;
        this.info = info;
    }

    @Override
    public String toString() {
        return "User{" +
                "uuid=" + uuid +
                ", account='" + account + '\'' +
                ", password='" + password + '\'' +
                ", name='" + name + '\'' +
                ", sex='" + sex + '\'' +
                ", age=" + age +
                ", tel='" + tel + '\'' +
                ", address='" + address + '\'' +
                ", hobby='" + hobby + '\'' +
                ", imagepath='" + imagepath + '\'' +
                ", power=" + power +
                ", registertime='" + registertime + '\'' +
                ", info='" + info + '\'' +
                '}';
    }
}
