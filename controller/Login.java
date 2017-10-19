package sample.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import sample.Main;

import java.io.IOException;

public class Login {
    @FXML
    private Label label;

    @FXML
    private TextField login;

    @FXML
    private PasswordField password;

    @FXML
    private Button button;

    @FXML
    private void doLogin() throws IOException {
        Main main = new Main();
        main.principal();
    }
}
