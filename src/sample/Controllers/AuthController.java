package sample;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import org.sqlite.SQLiteDataSource;
import sample.Animation.Shake;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.*;

public class AuthController {

    @FXML
    private Button regButton;

    @FXML
    private Text startCompanyName;

    @FXML
    private Button authButton;

    @FXML
    private PasswordField passField;

    @FXML
    private TextField loginField;

    @FXML
    void initialize(){
        authButton.setOnAction(actionEvent -> {
            String login = loginField.getText();
            String password = passField.getText();

            ResultSet resultSetAuth = null;

            String url = "jdbc:postgresql://localhost:5432/DIARY";
            String dbHandler = "postgres";
            String dbPass = "avtushko";

            try {
                Class.forName("org.postgresql.Driver");
            } catch (ClassNotFoundException e) {
                System.out.println("Driver not found");
                e.printStackTrace();
            }

            try(Connection dbConnection = DriverManager.getConnection(url, dbHandler, dbPass)) {
                String selectAuth = "SELECT login,password FROM users WHERE login=? AND password=?";
                try (PreparedStatement preparedStatementAuth = dbConnection.prepareStatement(selectAuth)){
                    dbConnection.setAutoCommit(false);

                        preparedStatementAuth.setString(1, login);
                        preparedStatementAuth.setString(2, password);

                    resultSetAuth = preparedStatementAuth.executeQuery();


                    int counter = 0;
                    while (resultSetAuth.next()){
                        counter++;
                    }
                    if (counter == 1) {
                        File fileLog = new File("login");
                        try {
                            fileLog.createNewFile();
                            FileWriter fileWriterLog = new FileWriter(fileLog);
                            fileWriterLog.write(login);
                            fileWriterLog.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }

                        authButton.getScene().getWindow().hide();

                        FXMLLoader loader = new FXMLLoader();
                        loader.setLocation(getClass().getResource("styles/diary.fxml"));
                        try {
                            loader.load();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        Parent root = loader.getRoot();
                        Stage stage = new Stage();
                        stage.setScene(new Scene(root));
                        stage.setResizable(false);
                        stage.setTitle("Ежедневник");
                        stage.showAndWait();
                        dbConnection.commit();
                        dbConnection.close();
                        resultSetAuth.close();
                    }else{
                        Shake wrongAuth1 = new Shake(loginField);
                        Shake wrongAuth2 = new Shake(passField);
                        wrongAuth1.playAnim();
                        wrongAuth2.playAnim();
                    }
                }
            } catch (SQLException e) {
                Shake wrongLogin = new Shake(loginField);
                Shake wrongPass = new Shake(passField);
                wrongLogin.playAnim();
                wrongPass.playAnim();
            }
        });

        regButton.setOnAction(actionEvent -> {
            regButton.getScene().getWindow().hide();

            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("styles/reg.fxml"));
            try {
                loader.load();
            } catch (IOException e) {
                e.printStackTrace();
            }
            Parent root = loader.getRoot();
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.setResizable(false);
            stage.setTitle("Ежедневник");
            stage.show();
        });
    }

}
