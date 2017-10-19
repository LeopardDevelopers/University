package sample.dao;

import javafx.collections.ObservableList;
import sample.model.Course;
import sample.model.Person;
import sample.model.University;

import java.io.File;
import java.io.IOException;
import java.util.Formatter;

public class Save {

    private Formatter output;

    private void closeFile() {
        if (output != null)
            output.close();
    }

    public void saveData(File file, ObservableList<Person> dataPerson, ObservableList<University> dataUniversity, ObservableList<Course> dataCourse) throws IOException {

        if (file != null) {

            if (!file.getPath().endsWith(".txt")) {
                file = new File(file.getPath() + ".txt");
            }
            output = new Formatter(file);

            output.format("## Person ##" + "\r\n");
            for (Person person : dataPerson) {
                output.format(person.getName() + ";" + person.getAddress() + ";" + person.getPhone() + ";" + person.getCpf()
                        + ";" + person.getContract() + ";" + person.getDate() + ";" + person.getUniversity() + ";" +
                        person.getCourse() + ";" + person.getComment() + ";" + "\r\n");
            }
            output.format("## End Person ##" + "\r\n");

            output.format("## University ##" + "\r\n");
            for (University university : dataUniversity) {
                output.format(university.getName() + ";" + university.getAddress() + ";" + university.getPhone() + ";" +
                        university.getCnpj() + ";" + university.getComment() + ";" + "\r\n");
            }
            output.format("## End University ##" + "\r\n");

            output.format("## Course ##" + "\r\n");
            for (Course course : dataCourse) {
                output.format(course.getName() + ";" + course.getComment() + ";" + "\r\n");
            }
            output.format("## End Course ##" + "\r\n");
        }
        closeFile();
    }
}
