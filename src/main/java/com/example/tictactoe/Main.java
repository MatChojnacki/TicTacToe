package com.example.tictactoe;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.io.IOException;

public class Main extends Application {

    private char currentPlayer = 'X';
    private Button[][] buttons = new Button[3][3];


    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        GridPane gridPane = new GridPane();
        gridPane.setAlignment(Pos.CENTER);
        gridPane.setHgap(10);
        gridPane.setVgap(10);

        Scene scene = new Scene(gridPane, 320, 320);
        primaryStage.setTitle("Kółko i Krzyżyk");
        primaryStage.setScene(scene);
        primaryStage.show();


        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 3; col++) {
                Button button = new Button();
                button.setMinSize(100, 100);
                button.setOnAction(e -> handleButtonClick(button));
                buttons[row][col] = button;
                gridPane.add(button, col, row);
            }
        }


    }

    private void handleButtonClick(Button button) {
        if (button.getText().equals("")) {
            button.setText(Character.toString(currentPlayer));
            if (checkForWinner()) {
                showWinnerAlert();
                resetGame();
            } else if (isBoardFull()) {
                showDrawAlert();
                resetGame();
            } else {
                currentPlayer = (currentPlayer == 'X') ? 'O' : 'X';
            }
        }
    }

    private boolean checkForWinner() {
        // Sprawdzanie wierszy
        for (int i = 0; i < 3; i++) {
            if (buttons[i][0].getText().equals(Character.toString(currentPlayer)) &&
                    buttons[i][1].getText().equals(Character.toString(currentPlayer)) &&
                    buttons[i][2].getText().equals(Character.toString(currentPlayer))) {
                return true;
            }
        }

        // Sprawdzanie kolumn
        for (int i = 0; i < 3; i++) {
            if (buttons[0][i].getText().equals(Character.toString(currentPlayer)) &&
                    buttons[1][i].getText().equals(Character.toString(currentPlayer)) &&
                    buttons[2][i].getText().equals(Character.toString(currentPlayer))) {
                return true;
            }
        }

        // Sprawdzanie przekątnych
        if (buttons[0][0].getText().equals(Character.toString(currentPlayer)) &&
                buttons[1][1].getText().equals(Character.toString(currentPlayer)) &&
                buttons[2][2].getText().equals(Character.toString(currentPlayer))) {
            return true;
        }

        if (buttons[0][2].getText().equals(Character.toString(currentPlayer)) &&
                buttons[1][1].getText().equals(Character.toString(currentPlayer)) &&
                buttons[2][0].getText().equals(Character.toString(currentPlayer))) {
            return true;
        }

        return false;
    }

    private boolean isBoardFull() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (buttons[i][j].getText().equals("")) {
                    return false;
                }
            }
        }
        return true;
    }

    private void showWinnerAlert() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Koniec gry");
        alert.setHeaderText(null);
        alert.setContentText("Gracz " + currentPlayer + " wygrywa!");
        alert.showAndWait();
    }

    private void showDrawAlert() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Koniec gry");
        alert.setHeaderText(null);
        alert.setContentText("Remis!");
        alert.showAndWait();
    }

    private void resetGame() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                buttons[i][j].setText("");
            }
        }
        currentPlayer = 'X';
    }
}
