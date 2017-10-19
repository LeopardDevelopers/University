package sample.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import org.jetbrains.annotations.Contract;
import sample.Main;
import sample.dao.Load;
import sample.dao.Save;
import sample.model.Course;
import sample.model.Person;
import sample.model.University;
import sample.util.Search;

import java.io.File;
import java.io.IOException;

public class Principal {

    private TableView<Person> personTable;
    private TableView<University> universityTable;
    private TableView<Course> courseTable;
    private ObservableList<Person> personData;
    private ObservableList<University> universityData;
    private ObservableList<Course> courseData;
    private File file;
    private boolean edited;

    @FXML
    private AnchorPane table;

    @FXML
    private Button remove;

    @FXML
    private Button edit;

    @FXML
    private ComboBox tablecmb;

    @FXML
    private Button New;

    @FXML
    private TextField searchtxt;

    @FXML
    public void initialize() {
        ObservableList<String> options = FXCollections.observableArrayList("University", "Person", "Course");
        tablecmb.setItems(options);
        tablecmb.setValue("Person");
        personTable();
        universityTable();
        courseTable();

        doTablecmb();
        file = null;
        edited = false;

        remove.setDisable(true);
        edit.setDisable(true);

        personData = FXCollections.observableArrayList();
        universityData = FXCollections.observableArrayList();
        courseData = FXCollections.observableArrayList();
    }

    @FXML
    private void doTablecmb() {

        if (tablecmb.getValue().equals("Person")) {
            personTable.setVisible(true);
            universityTable.setVisible(false);
            courseTable.setVisible(false);

            universityTable.getSelectionModel().clearSelection();
            courseTable.getSelectionModel().clearSelection();

        } else if (tablecmb.getValue().equals("University")) {
            personTable.setVisible(false);
            universityTable.setVisible(true);
            courseTable.setVisible(false);

            personTable.getSelectionModel().clearSelection();
            courseTable.getSelectionModel().clearSelection();

        } else if (tablecmb.getValue().equals("Course")) {
            personTable.setVisible(false);
            universityTable.setVisible(false);
            courseTable.setVisible(true);

            personTable.getSelectionModel().clearSelection();
            universityTable.getSelectionModel().clearSelection();
        }
    }

    private void personTable() {
        TableColumn name = new TableColumn("Name");
        name.setCellValueFactory(new PropertyValueFactory<Person, String>("name"));

        TableColumn phone = new TableColumn("Phone");
        phone.setCellValueFactory(new PropertyValueFactory<Person, String>("phone"));

        TableColumn address = new TableColumn("Address");
        address.setCellValueFactory(new PropertyValueFactory<Person, String>("address"));

        TableColumn cpf = new TableColumn("CPF");
        cpf.setCellValueFactory(new PropertyValueFactory<Person, String>("cpf"));

        TableColumn contract = new TableColumn("Contract");
        contract.setCellValueFactory(new PropertyValueFactory<Person, String>("contract"));

        TableColumn date = new TableColumn("Date");
        date.setCellValueFactory(new PropertyValueFactory<Person, String>("date"));

        TableColumn university = new TableColumn("University");
        university.setCellValueFactory(new PropertyValueFactory<Person, String>("university"));

        TableColumn cource = new TableColumn("Course");
        cource.setCellValueFactory(new PropertyValueFactory<Person, String>("course"));

        TableColumn comment = new TableColumn("Comment");
        comment.setCellValueFactory(new PropertyValueFactory<Person, String>("comment"));

        personTable = new TableView<>();
        personTable.getColumns().addAll(name, phone, address, cpf, contract, date, university, cource, comment);

        table.getChildren().add(personTable);
        personTable.setPrefHeight(table.getPrefHeight());
        personTable.setPrefWidth(table.getPrefWidth());

        personTable.getSelectionModel().selectedItemProperty().addListener(
                (observable, oldValue, newValue) -> isTrue(newValue));
    }

    private void universityTable() {
        TableColumn name = new TableColumn("Name");
        name.setCellValueFactory(new PropertyValueFactory<University, String>("name"));

        TableColumn phone = new TableColumn("Phone");
        phone.setCellValueFactory(new PropertyValueFactory<University, String>("phone"));

        TableColumn address = new TableColumn("Address");
        address.setCellValueFactory(new PropertyValueFactory<University, String>("address"));

        TableColumn cnpj = new TableColumn("CNPJ");
        cnpj.setCellValueFactory(new PropertyValueFactory<University, String>("cnpj"));

        TableColumn comment = new TableColumn("Comment");
        comment.setCellValueFactory(new PropertyValueFactory<University, String>("comment"));

        universityTable = new TableView<>();
        universityTable.getColumns().addAll(name, phone, address, cnpj, comment);

        table.getChildren().add(universityTable);
        universityTable.setPrefHeight(table.getPrefHeight());
        universityTable.setPrefWidth(table.getPrefWidth());

        universityTable.getSelectionModel().selectedItemProperty().addListener(
                (observable, oldValue, newValue) -> isTrue(newValue));
    }

    private void courseTable() {
        TableColumn name = new TableColumn("Name");
        name.setCellValueFactory(new PropertyValueFactory<Course, String>("name"));

        TableColumn comment = new TableColumn("Comment");
        comment.setCellValueFactory(new PropertyValueFactory<Course, String>("comment"));

        courseTable = new TableView<>();
        courseTable.getColumns().addAll(name, comment);

        table.getChildren().add(courseTable);
        courseTable.setPrefHeight(table.getPrefHeight());
        courseTable.setPrefWidth(table.getPrefWidth());

        courseTable.getSelectionModel().selectedItemProperty().addListener(
                (observable, oldValue, newValue) -> isTrue(newValue));
    }

    private ObservableList<String> courses() {
        ObservableList<Course> courses = courseTable.getItems();
        ObservableList<String> newCourses = FXCollections.observableArrayList();
        for (Course course : courses) {
            newCourses.add(course.getName());
        }
        return newCourses;
    }

    private ObservableList<String> universitys() {
        ObservableList<University> universities = universityTable.getItems();
        ObservableList<String> newUnivesitys = FXCollections.observableArrayList();
        for (University university : universities) {
            newUnivesitys.add(university.getName());
        }
        return newUnivesitys;
    }

    private void isTrue(Object object) {
        if (object != null) {
            remove.setDisable(false);
            edit.setDisable(false);
            edited = true;
        } else {
            remove.setDisable(true);
            edit.setDisable(true);
        }
    }

    @FXML
    private void doNew() throws IOException {

        New.setDisable(true);
        remove.setDisable(true);
        edit.setDisable(true);

        Main main = new Main();

        if (tablecmb.getValue().equals("Person")) {
            Person cadastro = main.cadastro(new Person(), courses(), universitys());
            if (cadastro != null) {
                personTable.getItems().add(cadastro);
                personTable.refresh();
                personData.add(cadastro);
            }
        } else if (tablecmb.getValue().equals("University")) {
            University cadastro = main.cadastro(new University());
            if (cadastro != null) {
                universityTable.getItems().add(cadastro);
                universityTable.refresh();
                universityData.add(cadastro);
            }
        } else if (tablecmb.getValue().equals("Course")) {
            Course cadastro = main.cadastro(new Course());
            if (cadastro != null) {
                courseTable.getItems().add(cadastro);
                courseTable.refresh();
                courseData.add(cadastro);
            }
        }

        New.setDisable(false);
        remove.setDisable(false);
        edit.setDisable(false);
    }

    @FXML
    private void doRemove() {

        if (tablecmb.getValue().equals("Person")) {
            int id = personTable.getSelectionModel().getSelectedIndex();
            if (id >= 0) {
                personTable.getItems().remove(id);
                personTable.refresh();
                personData.remove(id);
            }
        } else if (tablecmb.getValue().equals("University")) {
            int id = universityTable.getSelectionModel().getSelectedIndex();
            if (id >= 0) {
                universityTable.getItems().remove(id);
                universityTable.refresh();
                universityData.remove(id);
            }

        } else if (tablecmb.getValue().equals("Course")) {
            int id = courseTable.getSelectionModel().getSelectedIndex();
            if (id >= 0) {
                courseTable.getItems().remove(id);
                courseTable.refresh();
                courseData.remove(id);
            }
        }
    }

    @FXML
    private void doEdit() throws IOException {

        Main main = new Main();

        New.setDisable(true);
        remove.setDisable(true);
        edit.setDisable(true);

        if (tablecmb.getValue().equals("Person")) {
            int id = personTable.getSelectionModel().getSelectedIndex();
            if (id >= 0) {
                Person person = main.cadastro(personTable.getSelectionModel().getSelectedItem(), courses(), universitys());
                if (person != null) {
                    personTable.getItems().add(id, person);
                    personTable.getItems().remove(id + 1);
                    personTable.refresh();
                    personData.add(id, person);
                    personData.remove(id + 1);
                }
            }

        } else if (tablecmb.getValue().equals("University")) {
            int id = universityTable.getSelectionModel().getSelectedIndex();
            if (id >= 0) {
                New.setDisable(true);
                University university = main.cadastro(universityTable.getSelectionModel().getSelectedItem());
                if (university != null) {
                    universityTable.getItems().add(id, university);
                    universityTable.getItems().remove(id + 1);
                    universityTable.refresh();
                    universityData.add(id, university);
                    universityData.remove(id + 1);
                }
            }

        } else if (tablecmb.getValue().equals("Course")) {
            int id = courseTable.getSelectionModel().getSelectedIndex();
            if (id >= 0) {
                New.setDisable(true);
                Course course = main.cadastro(courseTable.getSelectionModel().getSelectedItem());
                if (course != null) {
                    courseTable.getItems().add(id, course);
                    courseTable.getItems().remove(id + 1);
                    courseTable.refresh();
                    courseData.add(id, course);
                    courseData.remove(id + 1);
                }
            }
        }

        New.setDisable(false);
        remove.setDisable(false);
        edit.setDisable(false);
    }

    @FXML
    private void doOpen() throws IOException {
        doSave();
        Load load = new Load();

        file = load.getFile();

        personTable.getItems().removeAll();
        universityTable.getItems().removeAll();
        courseTable.getItems().removeAll();

        personData.removeAll();
        universityData.removeAll();
        courseData.removeAll();

        personTable.setItems(load.getDataPerson());
        universityTable.setItems(load.getDataUniversity());
        courseTable.setItems(load.getDataCourse());

        personData.addAll(load.getDataPerson());
        universityData.addAll(load.getDataUniversity());
        courseData.addAll(load.getDataCourse());
    }

    @FXML
    private void doSaveAs() throws IOException {
        FileChooser fileChooser = new FileChooser();

        FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("TXT files (*.txt)", "*.txt");
        fileChooser.getExtensionFilters().add(extFilter);

        file = fileChooser.showSaveDialog(new Stage());

        Save save = new Save();
        save.saveData(file, personTable.getItems(), universityTable.getItems(), courseTable.getItems());
    }

    @FXML
    private void doSave() throws IOException {
        if (file != null) {
            Save save = new Save();
            save.saveData(file, personTable.getItems(), universityTable.getItems(), courseTable.getItems());
        } else if (edited == true) doSaveAs();
    }

    @FXML
    private void doNewSave() throws IOException {
        doSave();

        personTable.getItems().removeAll();
        universityTable.getItems().removeAll();
        courseTable.getItems().removeAll();

        personData.removeAll();
        universityData.removeAll();
        courseData.removeAll();

        FileChooser fileChooser = new FileChooser();

        FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("TXT files (*.txt)", "*.txt");
        fileChooser.getExtensionFilters().add(extFilter);

        file = fileChooser.showSaveDialog(new Stage());
    }

    @Contract(" -> fail")
    @FXML
    private void doExit() {
        System.exit(0);
    }

    @FXML
    private void doSearch(){
        if (tablecmb.getValue().equals("Person")) {
            Search search = new Search();
            search.searchPerson(personTable.getItems(), searchtxt.getText());
            if(!searchtxt.getText().equals("")) {
                personTable.setItems(search.getNewDataPerson());
                personTable.refresh();
            }else {
                personTable.setItems(personData);
                personTable.refresh();
            }
        }
        else if (tablecmb.getValue().equals("University")) {
            Search search = new Search();
            search.searchUniversity(universityTable.getItems(), searchtxt.getText());
            if(!searchtxt.getText().equals("")) {
                universityTable.setItems(search.getNewDataUniversity());
                universityTable.refresh();
            }else {
                universityTable.setItems(universityData);
                universityTable.refresh();
            }
        }
        else if (tablecmb.getValue().equals("Course")) {
            Search search = new Search();
            search.searchCourse(courseTable.getItems(), searchtxt.getText());
            if(!searchtxt.getText().equals("")) {
                courseTable.setItems(search.getNewDataCourse());
                courseTable.refresh();
            }else {
                courseTable.setItems(courseData);
                courseTable.refresh();
            }
        }
    }

    public ObservableList<Person> getPersonData() {
        return personData;
    }

    public void setPersonData(ObservableList<Person> personData) {
        this.personData = personData;
        personTable.setItems(personData);
    }

    public ObservableList<University> getUniversityData() {
        return universityData;
    }

    public void setUniversityData(ObservableList<University> universityData) {
        this.universityData = universityData;
        universityTable.setItems(universityData);
    }

    public ObservableList<Course> getCourseData() {
        return courseData;
    }

    public void setCourseData(ObservableList<Course> courseData) {
        this.courseData = courseData;
        courseTable.setItems(courseData);
    }
}
