package sample.util;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import sample.model.Course;
import sample.model.Person;
import sample.model.University;

public class Search {
	ObservableList<Person> dataPerson;
	ObservableList<Person> newDataPerson;

	ObservableList<University> dataUniversity;
	ObservableList<University> newDataUniversity;

	ObservableList<Course> dataCourse;
	ObservableList<Course> newDataCourse;

	public void searchPerson(ObservableList<Person> dataPerson, String search){
		this.dataPerson = dataPerson;
		newDataPerson = FXCollections.observableArrayList();

		for(Person person : this.dataPerson){
			if(person.getName().contains(search) || person.getAddress().contains(search) || person.getPhone().contains(search)
					|| person.getCpf().contains(search) || person.getContract().contains(search) || person.getDate().contains(search)
					|| person.getUniversity().contains(search) || person.getCourse().contains(search) || person.getComment().contains(search)){
				newDataPerson.add(person);
			}
		}
	}

	public void searchUniversity(ObservableList<University> dataUniversity, String search){
		this.dataUniversity = dataUniversity;
		newDataUniversity = FXCollections.observableArrayList();

		for(University university : this.dataUniversity){
			if(university.getName().contains(search) || university.getAddress().contains(search) || university.getPhone().contains(search)
					|| university.getComment().contains(search)){
				newDataUniversity.add(university);
			}
		}
	}

	public void searchCourse(ObservableList<Course> dataCourse, String search){
		this.dataCourse = dataCourse;
		newDataCourse = FXCollections.observableArrayList();

		for(Course course : this.dataCourse){
			if(course.getName().contains(search) || course.getComment().contains(search)){
				newDataCourse.add(course);
			}
		}
	}

	public ObservableList<Person> getDataPerson() {
		return dataPerson;
	}

	public void setDataPerson(ObservableList<Person> dataPerson) {
		this.dataPerson = dataPerson;
	}

	public ObservableList<Person> getNewDataPerson() {
		return newDataPerson;
	}

	public void setNewDataPerson(ObservableList<Person> newDataPerson) {
		this.newDataPerson = newDataPerson;
	}

	public ObservableList<University> getDataUniversity() {
		return dataUniversity;
	}

	public void setDataUniversity(ObservableList<University> dataUniversity) {
		this.dataUniversity = dataUniversity;
	}

	public ObservableList<University> getNewDataUniversity() {
		return newDataUniversity;
	}

	public void setNewDataUniversity(ObservableList<University> newDataUniversity) {
		this.newDataUniversity = newDataUniversity;
	}

	public ObservableList<Course> getDataCourse() {
		return dataCourse;
	}

	public void setDataCourse(ObservableList<Course> dataCourse) {
		this.dataCourse = dataCourse;
	}

	public ObservableList<Course> getNewDataCourse() {
		return newDataCourse;
	}

	public void setNewDataCourse(ObservableList<Course> newDataCourse) {
		this.newDataCourse = newDataCourse;
	}
}
