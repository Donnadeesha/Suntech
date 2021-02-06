package com.example.suntech_application.Laptop;

public class Laptop {


    private String id;
    private String model;
    private String quantity;
    private String username;
    private String password;



    public Laptop(){

    }

    public String getId(){
        return id;
    }

    public void setId(String id){
        this.id=id;
    }

    public String getModel(){
        return model;
    }

    public void setModel(String model){
        this.model=model;
    }

    public String getQuantity(){
        return quantity;
    }

    public void setQuantity(String quantity){
        this.quantity=quantity;
    }


    public String getUserName() {
        return username;
    }

    public void setUserName(String userName) {
        this.username = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }




}
