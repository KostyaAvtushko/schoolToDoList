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
import java.time.LocalDate;
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
    private Text dateMonday;

    @FXML
    private Text dateTuesday;

    @FXML
    private Text dateWednesday;

    @FXML
    private Text dateThursday;


    @FXML
    private Text dateFriday;

    @FXML
    private Text dateSaturday;

    @FXML
    private Text dateSunday;


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
                    String dateMondaySQL = resultSet.getString(9);
                    String dateTuesdaySQL = resultSet.getString(10);
                    String dateWednesdaySQL = resultSet.getString(11);
                    String dateThursdaySQL = resultSet.getString(12);
                    String dateFridaySQL = resultSet.getString(13);
                    String dateSaturdaySQL = resultSet.getString(14);
                    String dateSundaySQL = resultSet.getString(15);

                    employmentMonday.setText(monday);
                    employmentTuesday.setText(tuesday);
                    employmentWednesday.setText(wednesday);
                    employmentThursday.setText(thursday);
                    employmentFriday.setText(friday);
                    employmentSaturday.setText(saturday);
                    employmentSunday.setText(sunday);
                    dateMonday.setText(dateMondaySQL);
                    dateTuesday.setText(dateTuesdaySQL);
                    dateWednesday.setText(dateWednesdaySQL);
                    dateThursday.setText(dateThursdaySQL);
                    dateFriday.setText(dateFridaySQL);
                    dateSaturday.setText(dateSaturdaySQL);
                    dateSunday.setText(dateSundaySQL);
                }
                con.commit();
                con.endRequest();
            }
        }catch (SQLException e){
            e.printStackTrace();
        }

        mondayBtn.setOnAction(actionEvent -> {
            LocalDate localDate = LocalDate.now();
            TextInputDialog dialog = new TextInputDialog("Ваше занятие");
            dialog.setTitle("Ваше занятие");
            dialog.setHeaderText("Ваше занятие");
            dialog.setContentText("Введите ваше занятие:");
            Optional<String> result = dialog.showAndWait();
            employmentMonday.setText(result.get());
            dateMonday.setText(localDate.toString());
            try(Connection connectionMonday = DriverManager.getConnection(url, dbHandler, dbPass)){
                String inputMonday = "UPDATE WEEK SET monday=?, datemonday=?";
                try(PreparedStatement preparedStatementMonday = connectionMonday.prepareStatement(inputMonday)){
                    connectionMonday.setAutoCommit(false);
                    preparedStatementMonday.setString(1, result.get());
                    preparedStatementMonday.setString(2, localDate.toString());
                    preparedStatementMonday.executeUpdate();
                    connectionMonday.commit();
                }
            }catch (SQLException e){
                e.printStackTrace();
            }
        });
        tuesdayBtn.setOnAction(actionEvent -> {
            LocalDate localDate = LocalDate.now();
            TextInputDialog dialog = new TextInputDialog("Ваше занятие");
            dialog.setTitle("Ваше занятие");
            dialog.setHeaderText("Ваше занятие");
            dialog.setContentText("Введите ваше занятие:");
            dateTuesday.setText(localDate.toString());
            Optional<String> result = dialog.showAndWait();
            employmentTuesday.setText(result.get());
            try(Connection connectionTuesday = DriverManager.getConnection(url, dbHandler, dbPass)){
                String inputTuesday = "UPDATE WEEK SET tuesday=?, datetuesday=?";
                try(PreparedStatement preparedStatementTuesday = connectionTuesday.prepareStatement(inputTuesday)){
                    connectionTuesday.setAutoCommit(false);
                    preparedStatementTuesday.setString(1, result.get());
                    preparedStatementTuesday.setString(2, localDate.toString());
                    preparedStatementTuesday.executeUpdate();
                    connectionTuesday.commit();
                }
            }catch (SQLException e){
                e.printStackTrace();
            }
        });
        wednesdayBtn.setOnAction(actionEvent -> {
            LocalDate localDate = LocalDate.now();
            TextInputDialog dialog = new TextInputDialog("Ваше занятие");
            dialog.setTitle("Ваше занятие");
            dialog.setHeaderText("Ваше занятие");
            dialog.setContentText("Введите ваше занятие:");
            Optional<String> result = dialog.showAndWait();
            employmentWednesday.setText(result.get());
            dateWednesday.setText(localDate.toString());
            try(Connection connectionWednesday = DriverManager.getConnection(url, dbHandler, dbPass)){
                String inputWednesday = "UPDATE WEEK SET wednesday=?, datewednesday=?";
                try(PreparedStatement preparedStatementWednesday = connectionWednesday.prepareStatement(inputWednesday)){
                    connectionWednesday.setAutoCommit(false);
                    preparedStatementWednesday.setString(1, result.get());
                    preparedStatementWednesday.setString(2, localDate.toString());
                    preparedStatementWednesday.executeUpdate();
                    connectionWednesday.commit();
                }
            }catch (SQLException e){
                e.printStackTrace();
            }
        });
        thursdayBtn.setOnAction(actionEvent -> {
            LocalDate localDate = LocalDate.now();
            TextInputDialog dialog = new TextInputDialog("Ваше занятие");
            dialog.setTitle("Ваше занятие");
            dialog.setHeaderText("Ваше занятие");
            dialog.setContentText("Введите ваше занятие:");
            Optional<String> result = dialog.showAndWait();
            employmentThursday.setText(result.get());
            dateThursday.setText(localDate.toString());
            try(Connection connectionThursday = DriverManager.getConnection(url, dbHandler, dbPass)){
                String inputThursday = "UPDATE WEEK SET thursday=?, datethursday=?";
                try(PreparedStatement preparedStatementThursday = connectionThursday.prepareStatement(inputThursday)){
                    connectionThursday.setAutoCommit(false);
                    preparedStatementThursday.setString(1, result.get());
                    preparedStatementThursday.setString(2, localDate.toString());
                    preparedStatementThursday.executeUpdate();
                    connectionThursday.commit();
                }
            }catch (SQLException e){
                e.printStackTrace();
            }
        });
        fridayBtn.setOnAction(actionEvent -> {
            LocalDate localDate = LocalDate.now();
            TextInputDialog dialog = new TextInputDialog("Ваше занятие");
            dialog.setTitle("Ваше занятие");
            dialog.setHeaderText("Ваше занятие");
            dialog.setContentText("Введите ваше занятие:");
            Optional<String> result = dialog.showAndWait();
            employmentFriday.setText(result.get());
            dateFriday.setText(localDate.toString());
            try(Connection connectionFriday = DriverManager.getConnection(url, dbHandler, dbPass)){
                String inputFriday = "UPDATE WEEK SET friday=?, datefriday=?";
                try(PreparedStatement preparedStatementFriday = connectionFriday.prepareStatement(inputFriday)){
                    connectionFriday.setAutoCommit(false);
                    preparedStatementFriday.setString(1, result.get());
                    preparedStatementFriday.setString(2, localDate.toString());
                    preparedStatementFriday.executeUpdate();
                    connectionFriday.commit();
                }
            }catch (SQLException e){
                e.printStackTrace();
            }
        });
        saturdayBtn.setOnAction(actionEvent -> {
            LocalDate localDate = LocalDate.now();
            TextInputDialog dialog = new TextInputDialog("Ваше занятие");
            dialog.setTitle("Ваше занятие");
            dialog.setHeaderText("Ваше занятие");
            dialog.setContentText("Введите ваше занятие:");
            Optional<String> result = dialog.showAndWait();
            employmentSaturday.setText(result.get());
            dateSaturday.setText(localDate.toString());
            try(Connection connectionSaturday = DriverManager.getConnection(url, dbHandler, dbPass)){
                String inputSaturday = "UPDATE WEEK SET saturday=?, datesaturday=?";
                try(PreparedStatement preparedStatementSaturday = connectionSaturday.prepareStatement(inputSaturday)){
                    connectionSaturday.setAutoCommit(false);
                    preparedStatementSaturday.setString(1, result.get());
                    preparedStatementSaturday.setString(2, localDate.toString());
                    preparedStatementSaturday.executeUpdate();
                    connectionSaturday.commit();
                }
            }catch (SQLException e){
                e.printStackTrace();
            }
        });
        sundayBtn.setOnAction(actionEvent -> {
            LocalDate localDate = LocalDate.now();
            TextInputDialog dialog = new TextInputDialog("Ваше занятие");
            dialog.setTitle("Ваше занятие");
            dialog.setHeaderText("Ваше занятие");
            dialog.setContentText("Введите ваше занятие:");
            Optional<String> result = dialog.showAndWait();
            employmentSunday.setText(result.get());
            dateSunday.setText(localDate.toString());
            try(Connection connectionSunday = DriverManager.getConnection(url, dbHandler, dbPass)){
                String inputSunday = "UPDATE WEEK SET sunday=?, datesunday=?";
                try(PreparedStatement preparedStatementSunday = connectionSunday.prepareStatement(inputSunday)){
                    connectionSunday.setAutoCommit(false);
                    preparedStatementSunday.setString(1, result.get());
                    preparedStatementSunday.setString(2, localDate.toString());
                    preparedStatementSunday.executeUpdate();
                    connectionSunday.commit();
                }
            }catch (SQLException e){
                e.printStackTrace();
            }
        });


    }
}
