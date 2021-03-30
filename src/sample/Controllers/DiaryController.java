package sample;

import javafx.fxml.FXML;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextInputDialog;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import org.sqlite.SQLiteDataSource;

import java.awt.*;
import java.io.*;
import java.sql.*;
import java.util.Optional;
import java.util.Scanner;

public class DiaryController {

    @FXML
    private AnchorPane mondayPane;

    @FXML
    private Text employmentMonday;

    @FXML
    private Button mondayBtn;

    @FXML
    private Text employmentTuesday;

    @FXML
    private Button tuesdayBtn;

    @FXML
    private Text employmentWednesday;

    @FXML
    private Button wednesdayBtn;

    @FXML
    private Text employmentFriday;

    @FXML
    private Button fridayBtn;

    @FXML
    private Text employmentThursday;

    @FXML
    private Button thursdayBtn;

    @FXML
    private Text employmentSaturday;

    @FXML
    private Button saturdayBtn;

    @FXML
    private Text employmentSunday;

    @FXML
    private Button sundayBtn;

    @FXML
    void initialize(){

        String url = "jdbc:postgresql://localhost:5432/DIARY";
        String dbHandler = "postgres";
        String dbPass = "avtushko";

        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            System.out.println("Driver not found");
            e.printStackTrace();
        }

            try (Connection con = DriverManager.getConnection(url, dbHandler, dbPass)) {
              String login = "";
                String selectWeek = "";
                try(Statement statementLog = con.createStatement()){
                con.setAutoCommit(false);
                ResultSet resultSet = null;
                File fileLog = new File("login");
                try (Scanner scanner = new Scanner(fileLog);){
                    while (scanner.hasNext()){
                        login = scanner.nextLine();
                        FileWriter fileWriterLog = new FileWriter(fileLog);
                        fileWriterLog.write("");
                        fileWriterLog.close();
                    }
                }catch (FileNotFoundException e){
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                selectWeek = "SELECT * FROM week WHERE login=" + "'" + login + "'";

                resultSet = statementLog.executeQuery(selectWeek);
                while(resultSet.next()) {
                    String monday = resultSet.getString(1);
                    String tuesday = resultSet.getString(2);
                    String wednesday = resultSet.getString(3);
                    String thursday = resultSet.getString(4);
                    String friday = resultSet.getString(5);
                    String saturday = resultSet.getString(6);
                    String sunday = resultSet.getString(7);

                    employmentMonday.setText(monday);
                    employmentTuesday.setText(tuesday);
                    employmentWednesday.setText(wednesday);
                    employmentThursday.setText(thursday);
                    employmentFriday.setText(friday);
                    employmentSaturday.setText(saturday);
                    employmentSunday.setText(sunday);
                }
                con.commit();
                con.endRequest();
            }
        }catch (SQLException e){
            e.printStackTrace();
        }


        mondayBtn.setOnAction(actionEvent -> {
            TextInputDialog dialog = new TextInputDialog("Ваше занятие");
            dialog.setTitle("Ваше занятие");
            dialog.setHeaderText("Ваше занятие");
            dialog.setContentText("Введите ваше занятие:");
            Optional<String> result = dialog.showAndWait();
            employmentMonday.setText(result.get());
            try(Connection connectionMonday = DriverManager.getConnection(url, dbHandler, dbPass)){
                String inputMonday = "UPDATE WEEK SET monday=?";
                try(PreparedStatement preparedStatementMonday = connectionMonday.prepareStatement(inputMonday)){
                    connectionMonday.setAutoCommit(false);
                    preparedStatementMonday.setString(1, result.get());
                    preparedStatementMonday.executeUpdate();
                    connectionMonday.commit();
                }
            }catch (SQLException e){
                e.printStackTrace();
            }
        });
        tuesdayBtn.setOnAction(actionEvent -> {
            TextInputDialog dialog = new TextInputDialog("Ваше занятие");
            dialog.setTitle("Ваше занятие");
            dialog.setHeaderText("Ваше занятие");
            dialog.setContentText("Введите ваше занятие:");
            Optional<String> result = dialog.showAndWait();
            employmentTuesday.setText(result.get());
            try(Connection connectionTuesday = DriverManager.getConnection(url, dbHandler, dbPass)){
                String inputTuesday = "UPDATE WEEK SET tuesday=?";
                try(PreparedStatement preparedStatementTuesday = connectionTuesday.prepareStatement(inputTuesday)){
                    connectionTuesday.setAutoCommit(false);
                    preparedStatementTuesday.setString(1, result.get());
                    preparedStatementTuesday.executeUpdate();
                    connectionTuesday.commit();
                }
            }catch (SQLException e){
                e.printStackTrace();
            }
        });
        wednesdayBtn.setOnAction(actionEvent -> {
            TextInputDialog dialog = new TextInputDialog("Ваше занятие");
            dialog.setTitle("Ваше занятие");
            dialog.setHeaderText("Ваше занятие");
            dialog.setContentText("Введите ваше занятие:");
            Optional<String> result = dialog.showAndWait();
            employmentWednesday.setText(result.get());
            try(Connection connectionWednesday = DriverManager.getConnection(url, dbHandler, dbPass)){
                String inputWednesday = "UPDATE WEEK SET wednesday=?";
                try(PreparedStatement preparedStatementWednesday = connectionWednesday.prepareStatement(inputWednesday)){
                    connectionWednesday.setAutoCommit(false);
                    preparedStatementWednesday.setString(1, result.get());
                    preparedStatementWednesday.executeUpdate();
                    connectionWednesday.commit();
                }
            }catch (SQLException e){
                e.printStackTrace();
            }
        });
        thursdayBtn.setOnAction(actionEvent -> {
            TextInputDialog dialog = new TextInputDialog("Ваше занятие");
            dialog.setTitle("Ваше занятие");
            dialog.setHeaderText("Ваше занятие");
            dialog.setContentText("Введите ваше занятие:");
            Optional<String> result = dialog.showAndWait();
            employmentThursday.setText(result.get());
            try(Connection connectionThursday = DriverManager.getConnection(url, dbHandler, dbPass)){
                String inputThursday = "UPDATE WEEK SET thursday=?";
                try(PreparedStatement preparedStatementThursday = connectionThursday.prepareStatement(inputThursday)){
                    connectionThursday.setAutoCommit(false);
                    preparedStatementThursday.setString(1, result.get());
                    preparedStatementThursday.executeUpdate();
                    connectionThursday.commit();
                }
            }catch (SQLException e){
                e.printStackTrace();
            }
        });
        fridayBtn.setOnAction(actionEvent -> {
            TextInputDialog dialog = new TextInputDialog("Ваше занятие");
            dialog.setTitle("Ваше занятие");
            dialog.setHeaderText("Ваше занятие");
            dialog.setContentText("Введите ваше занятие:");
            Optional<String> result = dialog.showAndWait();
            employmentFriday.setText(result.get());
            try(Connection connectionFriday = DriverManager.getConnection(url, dbHandler, dbPass)){
                String inputFriday = "UPDATE WEEK SET friday=?";
                try(PreparedStatement preparedStatementFriday = connectionFriday.prepareStatement(inputFriday)){
                    connectionFriday.setAutoCommit(false);
                    preparedStatementFriday.setString(1, result.get());
                    preparedStatementFriday.executeUpdate();
                    connectionFriday.commit();
                }
            }catch (SQLException e){
                e.printStackTrace();
            }
        });
        saturdayBtn.setOnAction(actionEvent -> {
            TextInputDialog dialog = new TextInputDialog("Ваше занятие");
            dialog.setTitle("Ваше занятие");
            dialog.setHeaderText("Ваше занятие");
            dialog.setContentText("Введите ваше занятие:");
            Optional<String> result = dialog.showAndWait();
            employmentSaturday.setText(result.get());
            try(Connection connectionSaturday = DriverManager.getConnection(url, dbHandler, dbPass)){
                String inputSaturday = "UPDATE WEEK SET saturday=?";
                try(PreparedStatement preparedStatementSaturday = connectionSaturday.prepareStatement(inputSaturday)){
                    connectionSaturday.setAutoCommit(false);
                    preparedStatementSaturday.setString(1, result.get());
                    preparedStatementSaturday.executeUpdate();
                    connectionSaturday.commit();
                }
            }catch (SQLException e){
                e.printStackTrace();
            }
        });
        sundayBtn.setOnAction(actionEvent -> {
            TextInputDialog dialog = new TextInputDialog("Ваше занятие");
            dialog.setTitle("Ваше занятие");
            dialog.setHeaderText("Ваше занятие");
            dialog.setContentText("Введите ваше занятие:");
            Optional<String> result = dialog.showAndWait();
            employmentSunday.setText(result.get());
            try(Connection connectionSunday = DriverManager.getConnection(url, dbHandler, dbPass)){
                String inputSunday = "UPDATE WEEK SET sunday=?";
                try(PreparedStatement preparedStatementSunday = connectionSunday.prepareStatement(inputSunday)){
                    connectionSunday.setAutoCommit(false);
                    preparedStatementSunday.setString(1, result.get());
                    preparedStatementSunday.executeUpdate();
                    connectionSunday.commit();
                }
            }catch (SQLException e){
                e.printStackTrace();
            }
        });


    }
}