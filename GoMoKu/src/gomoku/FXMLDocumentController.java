/*
 * Author: Aadit Prabhu
 * Project Title: GoMoku (Assignment 3)
 * Subject: Advanced Programming and Data Structures
 */
package gomoku;

import java.net.URL;
import java.util.Arrays;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class FXMLDocumentController implements Initializable {

    //String[row][col]
    private String[][] result = new String[18][18];
    private Button[][] buttonList;
    private String player = "X";
    private int checkWinCounter = 0;
    private int xWins = 0;
    private int oWins = 0;
    private int draws = 0;
    private boolean resultCheck = true;
    private String PlayerWin = "";
    private Label labelTitle;
    private TextField txtXScore;
    private TextField txtOScore;
    private TextField txtDrawScore;
    Alert alert = new Alert(Alert.AlertType.INFORMATION); //This will be used to show alerts
    Alert alertConfirm = new Alert(Alert.AlertType.CONFIRMATION); //This will be used to show alerts with confirmation

    public FXMLDocumentController() {
        for (String[] array : result) {
            Arrays.fill(array, "   ");
        }
    }

    public void setAllFields(Button[][] buttonList, Label labelTitle, TextField txtXWins, TextField txtOWins, TextField txtDraws) {
        this.buttonList = buttonList;
        this.labelTitle = labelTitle;
        this.txtXScore = txtXWins;
        this.txtOScore = txtOWins;
        this.txtDrawScore = txtDraws;
    }

    public String getPlayerWin() {
        return PlayerWin;
    }

    public void setPlayerWin(String PlayerWin) {
        this.PlayerWin = PlayerWin;
    }

    private void nextPlayer() {
        labelTitle.requestFocus();
        if (player.equals("X")) {
            player = "O";
        } else {
            player = "X";
        }
    }

    public void handleButtonQuitAction() {
        alertConfirm.setTitle("Quit");
        alertConfirm.setHeaderText("Are you sure you want to quit the game?");
        if (alertConfirm.showAndWait().filter(t -> t == ButtonType.OK).isPresent()) {
            Platform.exit();
        }
    }

    /*public void handleButtonResetBoardAction() {
        alertConfirm.setTitle("Reset");
        alertConfirm.setHeaderText("Are you sure you want to reset the board?");
        if (alertConfirm.showAndWait().filter(t -> t == ButtonType.OK).isPresent()) {
            resetBoard();
        }
    }*/
    
    public void handleButtonResetGameAction() {
        alertConfirm.setTitle("Start Game Again");
        alertConfirm.setHeaderText("Are you sure you want to start the game again?");
        if (alertConfirm.showAndWait().filter(t -> t == ButtonType.OK).isPresent()) {
            resetBoard();
            xWins = 0;
            oWins = 0;
            draws = 0;
            txtDrawScore.setText("0");
            txtOScore.setText("0");
            txtXScore.setText("0");
        }
    }

    public void resetBoard() {
        for (String[] array : result) {
            Arrays.fill(array, "   ");
        }
        checkWinCounter = 0;
        player = "X";
        setPlayerWin("");
        resultCheck = true;
        for (Button[] buttonList1 : buttonList) {
            for (int j = 0; j < buttonList.length; j++) {
                buttonList1[j].setDisable(false);
                buttonList1[j].setText(" ");
            }
        }
    }

    public void handleAllButtonsAction(Button button) {
        int row = Integer.parseInt(button.getId().substring(6, 8));
        int col = Integer.parseInt(button.getId().substring(8, 10));
        result[row][col] = player;
        button.setText(player);
        if (player.equals("X")) {
            button.setStyle("-fx-text-fill: red");
        } else {
            button.setStyle("-fx-text-fill: blue");
        }
        nextPlayer();
        button.setDisable(true);
        checkWinCounter();
    }

    private void checkWinCounter() {
        checkWinCounter++;
        System.out.println("Moves: " + checkWinCounter);
        if (checkWinCounter > 8 && resultCheck) {
            checkWin();
        }
        if (checkWinCounter == 323) {
            draws++;
            txtDrawScore.setText("Draws :" + String.valueOf(draws));
            showResult();
        }
    }

    private void showResult() {
        if (getPlayerWin().equals("X") || getPlayerWin().equals("O")) {
            alert.setTitle("Congratulations..");
            alert.setHeaderText("Player "+ getPlayerWin() + " is the Winner!!!");
            alert.showAndWait();
            resetBoard();
        } 
        else {
            alert.setTitle("Opps..");
            alert.setHeaderText("It was a Draw Game!!");
            alert.showAndWait(); 
            resetBoard();
        }
    }

    private void checkWin() {
        if (checkColumns() || checkRows() || checkDiagonal()) {
            if (getPlayerWin().equals("X")) {
                xWins++;
                txtXScore.setText(String.valueOf(xWins));
                resultCheck = false;
                showResult();
            } else if (getPlayerWin().equals("O")) {
                oWins++;
                txtOScore.setText(String.valueOf(oWins));
                resultCheck = false;
                showResult();
            } else {
                resultCheck = true;
            }
        }
    }

    private boolean checkRows() {
        for (int row = 0; row < 15; row++) {
            for (int col = 0; col < 10; col++) {
                if (result[row][col].equals(result[row][col + 1]) && result[row][col + 1].equals(result[row][col + 2])
                        && result[row][col + 2].equals(result[row][col + 3]) && result[row][col + 3].equals(result[row][col + 4])) {
                    if (!result[row][col].equals("   ")) {
                        setPlayerWin(result[row][col]);
                        return true;
                    }
                }
            }
        }
        return false;
    }

    private boolean checkColumns() {
        for (int col = 0; col < 15; col++) {
            for (int row = 0; row < 10; row++) {
                if (result[row][col].equals(result[row + 1][col]) && result[row + 1][col].equals(result[row + 2][col])
                        && result[row + 2][col].equals(result[row + 3][col]) && result[row + 3][col].equals(result[row + 4][col])) {
                    if (!result[row][col].equals("   ")) {
                        setPlayerWin(result[row][col]);
                        return true;
                    }
                }
            }
        }
        return false;
    }

    private boolean checkDiagonal() {
        for (int row = 0; row < 11; row++) {
            for (int col = 0; col < 11; col++) {
                if (result[row][col].equals(result[row + 1][col + 1]) && result[row + 1][col + 1].equals(result[row + 2][col + 2])
                        && result[row + 2][col + 2].equals(result[row + 3][col + 3]) && result[row + 3][col + 3].equals(result[row + 4][col + 4])) {
                    if (!result[row][col].equals("   ")) {
                        setPlayerWin(result[row][col]);
                        return true;
                    }
                }
            }

        }
        for (int row = 0; row < 11; row++) {
            for (int col = 4; col < 15; col++) {
                if (result[row][col].equals(result[row + 1][col - 1]) && result[row + 1][col - 1].equals(result[row + 2][col - 2])
                        && result[row + 2][col - 2].equals(result[row + 3][col - 3]) && result[row + 3][col - 3].equals(result[row + 4][col - 4])) {
                    if (!result[row][col].equals("   ")) {
                        setPlayerWin(result[row][col]);
                        return true;
                    }
                }
            }

        }
        return false;
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        for (String[] array : result) {
            Arrays.fill(array, "   ");
        }
    }
}