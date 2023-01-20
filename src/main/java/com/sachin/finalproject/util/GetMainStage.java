package com.sachin.finalproject.util;

import javafx.stage.Stage;

public class GetMainStage {
    private static Stage mainStage;

    public static Stage getMainStage() {
        return mainStage;
    }

    public static void setMainStage(Stage stage) {
        mainStage = stage;
    }
}
