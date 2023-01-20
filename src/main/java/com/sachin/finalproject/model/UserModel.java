package com.sachin.finalproject.model;

import com.sachin.finalproject.to.User;
import com.sachin.finalproject.util.CrudUtil;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.awt.*;
import java.io.*;
import java.sql.Blob;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserModel {
     static  boolean isAvailable = false;
    private static User user;
    public static boolean addUser(String username, String password, FileInputStream fileInputStream, String role, String userType, String name) throws SQLException, ClassNotFoundException {
       return CrudUtil.execute("INSERT INTO user Values(?,?,?,?,?,?)", username,password,fileInputStream,role,userType, name);
    }

    public static User getUser(String username) throws SQLException, ClassNotFoundException, IOException {
        ResultSet rst = CrudUtil.execute("SELECT * FROM user where username = ?", username);
        if(rst.next()){
            User user = new User();
            user.setUsername(rst.getString(1));
            user.setPassword(rst.getString(2));
            Blob blob = rst.getBlob(3);
            InputStream is = blob.getBinaryStream();
            Image image = new Image(is);


            user.setImg(image);
            user.setRole(rst.getString(4));
            user.setUserType(rst.getString(5));
            user.setName(rst.getString(6));
            return  user;
        }
        return null;
    }

    public static void setCurrentUser(User currentUser) {
        isAvailable = true;
        user = currentUser;
    }
    public static User getCurrentUser() {
        return user;
    }
}
