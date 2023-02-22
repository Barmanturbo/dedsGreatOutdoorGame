package com.example;

import java.io.IOException;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Rectangle2D;
import javafx.scene.control.Button;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.*;

public class Mscreencontroller {
    @FXML
    private Button startButton;
    private Button uitlegButton;

    public void startSpel() throws IOException{
        Stage stage = (Stage) startButton.getScene().getWindow();
        FXMLLoader loader = new FXMLLoader(App.class.getResource("/spelscherm.fxml"));
        Rectangle2D primaryScreenBounds = Screen.getPrimary().getVisualBounds();

        stage.setScene(new Scene(loader.load(), 0.5 * primaryScreenBounds.getWidth(), 0.5 * primaryScreenBounds.getHeight()));
        stage.setX(primaryScreenBounds.getMinX());
        stage.setY(primaryScreenBounds.getMinY());
        stage.setFullScreen(true);
        stage.setMinHeight(800);
        stage.setMinWidth(1000);
        stage.show();
    }
}
