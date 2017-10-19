package sample.dao;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import sample.model.Course;
import sample.model.Person;
import sample.model.University;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class Load {

    private FileReader fileReader;
    private BufferedReader bufferedReader;
    private String line;
    private File file;

    private ObservableList<Person> dataPerson;
    private ObservableList<University> dataUniversity;
    private ObservableList<Course> dataCourse;

    public Load() throws IOException {
        dataLoad();
    }

    private void openFile() throws IOException {
        FileChooser fileChooser = new FileChooser();

        FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("TXT files (*.txt)", "*.txt");
        fileChooser.getExtensionFilters().add(extFilter);

        file = fileChooser.showOpenDialog(new Stage());
        fileReader = new FileReader(file);
        bufferedReader = new BufferedReader(fileReader);
    }

    private void closeReader() throws IOException {
        bufferedReader.close();
        fileReader.close();
    }

    private void dataLoad() throws IOException {
        dataPerson = FXCollections.observableArrayList();
        dataUniversity = FXCollections.observableArrayList();
        dataCourse = FXCollections.observableArrayList();
        openFile();

        line = bufferedReader.readLine();
        if (line.equals("## Person ##")) {
            while (!line.equals("## End Person ##")) {
                line = bufferedReader.readLine();
                if (line != null && !line.equals("## End Person ##") && !line.equals(" ")) {
                    Person person = new Person();
                    String result[] = line.split(";");

                    person.setName(result[0]);
                    person.setAddress(result[1]);
                    person.setPhone(result[2]);
                    person.setCpf(result[3]);
                    person.setContract(result[4]);
                    person.setDate(result[5]);
                    person.setUniversity(result[6]);
                    person.setCourse(result[7]);
                    person.setComment(result[8]);
                    dataPerson.add(person);
                }
            }
        }
        line = bufferedReader.readLine();
        if (line.equals("## University ##")) {
            while (!line.equals("## End University ##")) {
                line = bufferedReader.readLine();
                if (line != null && !line.equals("## End University ##") && !line.equals(" ")) {
                    University university = new University();
                    String result[] = line.split(";");

                    university.setName(result[0]);
                    university.setAddress(result[1]);
                    university.setPhone(result[2]);
                    university.setComment(result[3]);
                    dataUniversity.add(university);
                }
            }
        }
        line = bufferedReader.readLine();
        if (line.equals("## Course ##")) {
            while (!line.equals("## End Course ##")) {
                line = bufferedReader.readLine();
                if (line != null && !line.equals("## End Course ##") && !line.equals(" ")) {
                    Course course = new Course();
                    String result[] = line.split(";");

                    course.setName(result[0]);
                    course.setComment(result[1]);
                    dataCourse.add(course);
                }
            }
        }

        closeReader();
    }

    public ObservableList<Person> getDataPerson() {
        return dataPerson;
    }

    public ObservableList<University> getDataUniversity() {
        return dataUniversity;
    }

    public ObservableList<Course> getDataCourse() {
        return dataCourse;
    }

    public File getFile() {
        return file;
    }

    public void setFile(File file) {
        this.file = file;
    }
}
