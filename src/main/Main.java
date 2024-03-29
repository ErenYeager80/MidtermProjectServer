package main;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import pages.InitServer;
import pages.PrepareBoard;

public class Main extends Application{

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setScene(new Scene(new InitServer()));
        primaryStage.setTitle("Prepare Game");
        primaryStage.show();
    }
}
