package sample;

import javafx.application.Application;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;
import sample.model.Course;
import sample.model.Person;
import sample.model.University;
import sample.controller.Registration;

import java.io.IOException;

public class Main extends Application {
    private Stage stage;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        this.stage = primaryStage;
        //login();
        principal();
    }

    public void login() throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(Main.class.getResource("view/Login.fxml"));
        Scene scene = new Scene(loader.load());

        stage = new Stage();
        stage.setScene(scene);
        stage.setTitle("Login");
        stage.show();
    }

    public void principal() throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(Main.class.getResource("view/Principal.fxml"));
        Scene scene = new Scene(loader.load());

        stage = new Stage();
        stage.setScene(scene);
        stage.setTitle("Principal");
        stage.show();
    }

    public Person cadastro(Person person, ObservableList<String> courses , ObservableList<String> universitys) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(Main.class.getResource("view/Registration.fxml"));
        Scene scene = new Scene(loader.load());

        Registration controller = loader.getController();
        controller.setPersonObj(person);
        controller.setCourseData(courses);
        controller.setUniversityData(universitys);

        stage = new Stage();
        stage.setScene(scene);
        stage.setTitle("Edit Person");
        stage.initModality(Modality.WINDOW_MODAL);

        stage.showAndWait();

        return controller.getPersonObj();
    }

    public University cadastro(University university) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(Main.class.getResource("view/Registration.fxml"));
        Scene scene = new Scene(loader.load());

        Registration controller = loader.getController();
        controller.setUniversityObj(university);

        stage = new Stage();
        stage.setScene(scene);
        stage.setTitle("Edit Person");
        stage.initModality(Modality.WINDOW_MODAL);

        stage.showAndWait();

        return controller.getUniversityObj();
    }

    public Course cadastro(Course course) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(Main.class.getResource("view/Registration.fxml"));
        Scene scene = new Scene(loader.load());

        Registration controller = loader.getController();
        controller.setCourseObj(course);

        stage = new Stage();
        stage.setScene(scene);
        stage.setTitle("Edit Person");
        stage.initModality(Modality.WINDOW_MODAL);

        stage.showAndWait();

        return controller.getCourseObj();
    }

    public Stage getStage() {
        return stage;
    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }
}
