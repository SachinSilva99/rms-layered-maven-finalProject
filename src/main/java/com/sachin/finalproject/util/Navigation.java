package com.sachin.finalproject.util;

import com.sachin.finalproject.util.GetMainStage;
import com.sachin.finalproject.util.Routes;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;

public class Navigation {

    private static AnchorPane pane;

    public static void navigate(AnchorPane pane, Routes route) throws IOException {
        Navigation.pane = pane;
        Navigation.pane.getChildren().clear();
        Stage window = (Stage)Navigation.pane.getScene().getWindow();

        switch (route) {
            case ORDER_SUB_UI:
                initUI("DineInUI.fxml");
                break;
            case PICK_UP:
                window.setTitle("Customer Manage");
                initUI("PickUpForm.fxml");
                break;
            case DELIVERY_ORDER:
                window.setTitle("Dashboard");
                initUI("DeliveryOrder.fxml");
                break;
            case CASHIER_UI:
                window.setTitle("Cashier UI");
                initUI("CashierForm.fxml");
                break;
            case REGISTER_UI:
                initUI("RegisterEmployeeForm.fxml");
                break;
            case GUEST:
                initUI("GuestForm.fxml");
                break;
            case STOCK:
                initUI("StockForm.fxml");
                break;
            case SALES:
                initUI("SalesForm.fxml");
                break;

            case LOGIN:
                initUI("LoginForm.fxml");
                break;

            default:
                new Alert(Alert.AlertType.ERROR, "UI Not Found!").show();
        }
    }
    public static void initUI(String location) throws IOException {
        Navigation.pane.getChildren().add(FXMLLoader.load(Navigation.class.getResource("/com/sachin/com.sachin.finalproject/view/" + location)));
    }
    public static void cashierBack(AnchorPane pane) throws IOException {
        Navigation.pane.getChildren().add(FXMLLoader.load(Navigation.class.getResource("/com/sachin/com.sachin.finalproject/view/CashierForm.fxml")));
    }
    public static void logOut() throws IOException {
        GetMainStage.getMainStage().close();
        Stage stage = new Stage();
        stage.initStyle(StageStyle.UNDECORATED);
        stage.setResizable(false);
        stage.setScene(new Scene(FXMLLoader.load(Navigation.class.getResource("/com/sachin/com.sachin.finalproject/view/LoginForm.fxml"))));
        stage.show();
    }
}
