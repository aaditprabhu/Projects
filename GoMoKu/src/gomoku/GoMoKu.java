/*
 * Author: Aadit Prabhu
 * Project Title: GoMoku (Assignment 3)
 * Subject: Advanced Programming and Data Structures
 */
package gomoku;

import java.text.DecimalFormat;
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;
import static javafx.application.Application.launch;
import javafx.scene.text.FontWeight;

public class GoMoKu extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        AnchorPane pane = new AnchorPane();
        FXMLDocumentController fdc = new FXMLDocumentController();

        Button[][] buttonList = new Button[18][18];
        for (int i = 0; i < buttonList.length; i++) {
            for (int j = 0; j < buttonList.length; j++) {
                buttonList[i][j] = new Button();
                buttonList[i][j].setPrefSize(32, 32);
                buttonList[i][j].setId("btnPos" + new DecimalFormat("00").format(i) + new DecimalFormat("00").format(j));
                buttonList[i][j].setOnAction(event -> fdc.handleAllButtonsAction((Button) event.getSource()));
            }
        }
        
        //Title of the Application
        Label lblTitle = new Label("GOMOKU (X vs O)");
        lblTitle.setId("lblTitle");
        lblTitle.setAlignment(Pos.CENTER);
        lblTitle.setContentDisplay(ContentDisplay.CENTER);
        lblTitle.setLayoutX(200);
        lblTitle.setLayoutY(15);
        lblTitle.setPrefHeight(45);
        lblTitle.setFont(new Font("Arial", 50));
        
        //Label for X Score
        Label lblXScore = new Label("Player X: ");
        lblXScore.setId("lblXScore");
        lblXScore.setLayoutX(700.0);
        lblXScore.setLayoutY(113.0);
        lblXScore.setPrefHeight(39);

        //Text Field to Display the Score of Player X
        TextField txtXWins = new TextField("0");
        txtXWins.setId("txtXWins");
        txtXWins.setLayoutX(765.0);
        txtXWins.setEditable(false);
        txtXWins.setLayoutY(113.0);
        txtXWins.setPrefHeight(39);
        txtXWins.setPrefWidth(50);
        
        //label for O score
        Label lblOScore = new Label("Player O: ");
        lblOScore.setId("lblOScore");
        lblOScore.setLayoutX(700.0);
        lblOScore.setLayoutY(167.0);
        lblOScore.setPrefHeight(39);

        //Text Field to display the Scroe of Player O
        TextField txtOWins = new TextField("0");
        txtOWins.setId("txtOWins");
        txtOWins.setLayoutX(765.0);
        txtOWins.setEditable(false);
        txtOWins.setLayoutY(167.0);
        txtOWins.setPrefHeight(39);
        txtOWins.setPrefWidth(50);
        
        //label for Draw score
        Label lblDrawScore = new Label("Draw: ");
        lblDrawScore.setId("lblDrawScore");
        lblDrawScore.setLayoutX(700.0);
        lblDrawScore.setLayoutY(221.0);
        lblDrawScore.setPrefHeight(39);

        //Text Field for Draw Score
        TextField txtDraws = new TextField("0");
        txtDraws.setId("txtDraws");
        txtDraws.setLayoutX(765.0);
        txtDraws.setEditable(false);
        txtDraws.setLayoutY(221.0);
        txtDraws.setPrefHeight(39);
        txtDraws.setPrefWidth(50);

        //Quit Button
        Button btnQuit = new Button("Quit");
        btnQuit.setId("btnQuit");
        btnQuit.setOnAction(event -> fdc.handleButtonQuitAction());
        btnQuit.setLayoutX(700.0);
        btnQuit.setLayoutY(483.0);
        btnQuit.setPrefHeight(39);
        btnQuit.setPrefWidth(84);

        /*Button btnResetBoard = new Button("Reset Board");
        btnResetBoard.setId("btnReset");
        btnResetBoard.setOnAction(action -> fdc.handleButtonResetBoardAction());
        btnResetBoard.setLayoutX(600.0);
        btnResetBoard.setLayoutY(345.0);
        btnResetBoard.setPrefHeight(39);
        btnResetBoard.setPrefWidth(144);*/

        Button btnResetGame = new Button("Start Game again");
        btnResetGame.setId("btnResetGame");
        btnResetGame.setOnAction(action -> fdc.handleButtonResetGameAction());
        btnResetGame.setLayoutX(700.0);
        btnResetGame.setLayoutY(414.0);
        btnResetGame.setPrefHeight(39);
        btnResetGame.setPrefWidth(144);

        GridPane gridPane = new GridPane();
        for (int i = 0; i < buttonList.length; i++) {
            gridPane.addRow(i, buttonList[i]);
        }
        gridPane.setLayoutX(60);
        gridPane.setLayoutY(110);

        fdc.setAllFields(buttonList, lblTitle, txtXWins, txtOWins, txtDraws);

        pane.getChildren().addAll(gridPane, btnQuit, btnResetGame,
                lblTitle, txtDraws, txtOWins, txtXWins, lblXScore, lblOScore, lblDrawScore);

        Scene scene = new Scene(pane, 900, 900);
        stage.setScene(scene);
        stage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
}
