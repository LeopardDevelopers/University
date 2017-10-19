package sample.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;
import sample.model.Course;
import sample.model.Person;
import sample.model.University;
import sample.util.ValidaCPF;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Registration {

    private String object;
    private Person personObj;
    private University universityObj;
    private Course courseObj;
    private ObservableList<String> courseData;
    private ObservableList<String> universityData;

    @FXML
    private TextField name;

    @FXML
    private TextField phone;

    @FXML
    private TextField address;

    @FXML
    private TextField cpf;

    @FXML
    private ComboBox contract;

    @FXML
    private TextField date;

    @FXML
    private ComboBox university;

    @FXML
    private ComboBox course;

    @FXML
    private TextField comment;

    @FXML
    private CheckBox today;

    @FXML
    private Button save;

    @FXML
    private Button cancel;

    @FXML
    private Button clear;

    @FXML
    private Label label;

    @FXML
    private Label cpflbl;

    @FXML
    public void initialize() {
        ObservableList<String> contracts = FXCollections.observableArrayList("Titular", "Adjunto");
        contract.setItems(contracts);
    }

    @FXML
    private void doSave() {
        if (isValid()) {
            if (object.equals("Person")) {
                personObj = new Person();
                personObj.setName(name.getText());
                personObj.setAddress(address.getText());
                personObj.setPhone(phone.getText());
                personObj.setCpf(cpf.getText());
                personObj.setContract(contract.getValue().toString());
                personObj.setDate(date.getText());
                personObj.setUniversity(this.university.getValue().toString());
                personObj.setCourse(course.getValue().toString());
                personObj.setComment(comment.getText());
            }
            if (object.equals("University")) {
                universityObj = new University();
                universityObj.setName(name.getText());
                universityObj.setAddress(address.getText());
                universityObj.setPhone(phone.getText());
                universityObj.setCnpj(cpf.getText());
                universityObj.setComment(comment.getText());
            }
            if (object.equals("Course")) {
                courseObj = new Course();
                courseObj.setName(name.getText());
                courseObj.setComment(comment.getText());
            }
            Stage stage = (Stage) cancel.getScene().getWindow();
            stage.close();
        }
    }

    @FXML
    private void doClear() {
        name.setText("");
        phone.setText("");
        address.setText("");
        cpf.setText("");
        contract.setValue(null);
        date.setText("");
        university.setValue(null);
        course.setValue(null);
        comment.setText("");
        today.setSelected(false);
    }

    @FXML
    private void doCancel() {
        personObj = null;
        universityObj = null;
        courseObj = null;
        Stage stage = (Stage) cancel.getScene().getWindow();
        stage.close();
    }

    @FXML
    private void doToday() {

        if (today.isSelected()) {
            Date d = new Date();
            SimpleDateFormat dt = new SimpleDateFormat("dd/MM/yyyy");
            String today = dt.format(d);
            date.setText(today);
            date.setDisable(true);
        } else date.setDisable(false);
    }

    private boolean isValid() {
        if (object.equals("Person")) {
            if (!ValidaCPF.isCPF(cpf.getText())) {
                label.setText("CPF inválid");
                return false;
            } else {

                if (name.getText().equals("") || (name == null)) {
                    label.setText("Incomplete registration");
                    return false;
                }

                if (phone.getText().equals("") || (phone == null) || phone.getText().contains("_")) {
                    label.setText("Incomplete registration");
                    return false;
                }

                if (address.getText().equals("") || (address == null)) {
                    label.setText("Incomplete registration");
                    return false;
                }

                if (date.getText().equals("") || date.getText().contains("_") || (date == null)) {
                    label.setText("Incomplete registration");
                    return false;
                }

                if (contract.getValue() == null) {
                    label.setText("Incomplete registration");
                    return false;
                }

                if (university.getValue() == null) {
                    label.setText("Incomplete registration");
                    return false;
                }

                if (course.getValue() == null) {
                    label.setText("Incomplete registration");
                    return false;
                }
                if (comment.getText().equals("") || (comment == null)) {
                    comment.setText(" ");
                }

            }
            if (object.equals("University")) {
                if (name.getText().equals("") || (name == null)) {
                    label.setText("Incomplete registration");
                    return false;
                }

                if (phone.getText().equals("") || (phone == null) || phone.getText().contains("_")) {
                    label.setText("Incomplete registration");
                    return false;
                }

                if (address.getText().equals("") || (address == null)) {
                    label.setText("Incomplete registration");
                    return false;
                }
                if (comment.getText().equals("") || (comment == null)) {
                    comment.setText(" ");
                }
            }
            if (object.equals("Course")) {
                if (name.getText().equals("") || (name == null)) {
                    label.setText("Incomplete registration");
                    return false;
                }
                if (comment.getText().equals("") || (comment == null)) {
                    comment.setText(" ");
                }
            }
        }
        return true;
    }

    public Person getPersonObj() {
        return personObj;
    }

    public void setPersonObj(Person personObj) {
        this.personObj = personObj;
        this.object = "Person";
        label.setText("Person registration");

        name.setText(this.personObj.getName());
        phone.setText(this.personObj.getPhone());
        address.setText(this.personObj.getAddress());
        cpf.setText(this.personObj.getCpf());
        contract.setValue(this.personObj.getContract());
        date.setText(this.personObj.getDate());
        university.setValue(this.personObj.getUniversity());
        course.setValue(this.personObj.getCourse());
        comment.setText(this.personObj.getComment());
    }

    public University getUniversityObj() {
        return universityObj;
    }

    public void setUniversityObj(University universityObj) {
        this.universityObj = universityObj;
        this.object = "University";
        label.setText("University registration");
        cpflbl.setText("CNPJ");

        name.setText(this.universityObj.getName());
        phone.setText(this.universityObj.getPhone());
        address.setText(this.universityObj.getAddress());
        comment.setText(this.universityObj.getComment());
        cpf.setText(this.universityObj.getCnpj());

        contract.setDisable(true);
        date.setDisable(true);
        this.university.setDisable(true);
        course.setDisable(true);
        today.setDisable(true);
    }

    public Course getCourseObj() {
        return courseObj;
    }

    public void setCourseObj(Course courseObj) {
        this.courseObj = courseObj;
        this.object = "Course";
        label.setText("Course registration");

        name.setText(this.courseObj.getName());
        comment.setText(this.courseObj.getComment());

        phone.setDisable(true);
        address.setDisable(true);
        cpf.setDisable(true);
        contract.setDisable(true);
        date.setDisable(true);
        university.setDisable(true);
        this.course.setDisable(true);
        today.setDisable(true);
    }

    public ObservableList<String> getCourseData() {
        return courseData;
    }

    public void setCourseData(ObservableList<String> courseData) {
        this.courseData = courseData;
        course.setItems(courseData);
    }

    public ObservableList<String> getUniversityData() {
        return universityData;
    }

    public void setUniversityData(ObservableList<String> universityData) {
        this.universityData = universityData;
        university.setItems(universityData);
    }
}
