package sample;

import board.Board;
import figurs.*;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {

        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        primaryStage.setTitle("Chess");
        primaryStage.setScene(new Scene(root, 800, 800));
        primaryStage.getIcons().add(new Image("/blackqueen.png"));
        primaryStage.show();

        //Доска
        Board.init();
        //Фигуры
        Pawn.init();
        Rook.init();
        Knight.init();
        Bishop.init();
        Queen.init();
        King.init();
    }

    public static void main(String[] args) {
        launch(args);
    }

}