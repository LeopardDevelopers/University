package sample.model;

import javafx.beans.property.StringProperty;

public class Person {
    private String name;
    private String phone;
    private String address;
    private String cpf;
    private String contract;
    private String date;
    private String university;
    private String course;
    private String comment;

    public Person(String name, String phone, String address, String cpf, String contract, String date, String university, String course, String comment) {
        this.name = name;
        this.phone = phone;
        this.address = address;
        this.cpf = cpf;
        this.contract = contract;
        this.date = date;
        this.university = university;
        this.course = course;
        this.comment = comment;
    }

    public Person(){
        this("", "", "", "", "", "", "", "", "");
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getContract() {
        return contract;
    }

    public void setContract(String contract) {
        this.contract = contract;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getUniversity() {
        return university;
    }

    public void setUniversity(String university) {
        this.university = university;
    }

    public String getCourse() {
        return course;
    }

    public void setCourse(String course) {
        this.course = course;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
}
