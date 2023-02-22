package com.example;


import javafx.fxml.FXMLLoader;
import javafx.application.Application;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class App extends Application {
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws IOException{
        Parent root = FXMLLoader.load(App.class.getResource("/mainscreen.fxml"));
        primaryStage.setTitle("The Great Outdoors Wint");
        primaryStage.setScene(new Scene(root,300,300));
        primaryStage.show();
    }
}