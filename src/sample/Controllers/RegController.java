package sample;


import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import org.sqlite.SQLiteDataSource;
import sample.Animation.Shake;

import java.io.IOException;
import java.sql.*;


public class RegController {



    @FXML
    private AnchorPane register;

    @FXML
    private PasswordField regPass;

    @FXML
    private Button regButton;

    @FXML
    private TextField regLogin;

    @FXML
    private TextField regName;

    @FXML
    private PasswordField regConfirmPass;

    @FXML
    private TextField regSurname;

    @FXML
    void initialize(){
        regButton.setOnAction(actionEvent -> {
            String url = "jdbc:postgresql:DIARY";
            String dbHandler = "postgres";
            String dbPass = "avtushko";

            try {
                Class.forName("org.postgresql.Driver");
            } catch (ClassNotFoundException e) {
                System.out.println("Driver not found");
                e.printStackTrace();
            }

            try(Connection dbConnection = DriverManager.getConnection(url, dbHandler, dbPass)) {
                String insertUsers = "INSERT INTO users (NAME, SURNAME, PASSWORD, LOGIN) VALUES (?,?,?,?)";
                String insertWeek = "INSERT INTO week (login) VALUES (?)";
                try (PreparedStatement preparedStatementUsers = dbConnection.prepareStatement(insertUsers);
                    PreparedStatement preparedStatementWeek = dbConnection.prepareStatement(insertWeek) ){
                    dbConnection.setAutoCommit(false);

                    String name = regName.getText();
                    String surname = regSurname.getText();
                    String password = regPass.getText();
                    String login = regLogin.getText();
                    String confirmPassword = regConfirmPass.getText();
                    if(password.equals(confirmPassword)){
                        preparedStatementUsers.setString(1, name);
                        preparedStatementUsers.setString(2, surname);
                        preparedStatementUsers.setString(3, password);
                        preparedStatementUsers.setString(4, login);

                        preparedStatementUsers.executeUpdate();

                        preparedStatementWeek.setString(1, login);

                        preparedStatementWeek.executeUpdate();

                        dbConnection.commit();

                        regButton.getScene().getWindow().hide();

                        FXMLLoader loader = new FXMLLoader(getClass().getResource("styles/auth.fxml"));
                        try {
                            loader.load();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        Parent root = loader.getRoot();
                        Stage stage = new Stage();
                        stage.setScene(new Scene(root));
                        stage.setTitle("Ежедневник");
                        stage.show();
                        }else{
                        Shake wrongpass1 = new Shake(regPass);
                        Shake wrongpass2 = new Shake(regConfirmPass);
                        wrongpass1.playAnim();
                        wrongpass2.playAnim();
                    }
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        });
    }
}
