package com.sachin.finalproject.util;

import javafx.scene.layout.AnchorPane;

public class GetMainUi {
    private static AnchorPane ui;
    public static void setMainUi(AnchorPane ui_){
        ui =  ui_;
    }
    public static AnchorPane getMainUi(){
        return ui;
    }
}
