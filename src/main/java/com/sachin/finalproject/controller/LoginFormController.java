package com.sachin.finalproject.controller;

import com.sachin.finalproject.model.UserModel;
import com.sachin.finalproject.to.User;
import com.sachin.finalproject.controller.CashierFormController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;
import java.sql.SQLException;

public class LoginFormController {

    private double x, y;
    @FXML
    public Button loginBtn;
    public ImageView loginImage;
    @FXML
    private TextField txtUsername;
    @FXML
    private PasswordField txtPassword;


    public void exitBtnOnAction(ActionEvent actionEvent) {
        System.exit(0);
    }


    private void close(ActionEvent actionEvent) {
        Node source = (Node) actionEvent.getSource();
        Stage stagee = (Stage) source.getScene().getWindow();
        stagee.close();
    }

    private void show(Stage stage, Parent root) {
        root.setOnMousePressed(event -> {
            x = event.getSceneX();
            y = event.getSceneY();
        });
        root.setOnMouseDragged(event -> {

            stage.setX(event.getScreenX() - x);
            stage.setY(event.getScreenY() - y);

        });

        stage.initStyle(StageStyle.UNDECORATED);
        stage.setResizable(false);
        stage.show();
    }

    public void loginBtnOnAction(ActionEvent actionEvent) throws IOException {
        try {


            String username = txtUsername.getText();

            String password = txtPassword.getText();

            User user = UserModel.getUser(username);
            if(user == null){
                new Alert(Alert.AlertType.ERROR, "Wrong Login").show();
                return;
            }
            if(user.getUsername().equals(username) && user.getPassword().equals(password)){
                if(user.getUserType().equals("cashier")){

                    close(actionEvent);

                    Stage stage = new Stage();
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("/com.sachin.finalproject/view/CashierForm.fxml"));
                    Parent root = loader.load();
                    CashierFormController cf = loader.getController();
                    cf.setUser(user.getName(), user.getImg(), user);
                    stage.setScene(new Scene(root));
                    show(stage, root);

                    new Alert(Alert.AlertType.INFORMATION, "Cashier Login Success\n WELCOME").show();
                }
                if(user.getUserType().equals("admin")){

                    close(actionEvent);

                    Stage stage = new Stage();
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("/com.sachin.finalproject/view/AdminForm.fxml"));
                    System.out.println("Eroor here");
                    Parent root = loader.load();
                    stage.setScene(new Scene(root));
                    stage.show();
                    new Alert(Alert.AlertType.INFORMATION, "Admin Login Success").show();
                }
            }else{
                new Alert(Alert.AlertType.ERROR, "Wrong Login").show();
            }

        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

}
