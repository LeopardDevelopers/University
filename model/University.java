package sample.model;

public class University{
    private String name;
    private String phone;
    private String address;
    private String cnpj;
    private String comment;

    public University(String name, String phone, String address, String cnpj, String comment) {
        this.name = name;
        this.phone = phone;
        this.address = address;
        this.cnpj = cnpj;
        this.comment = comment;
    }

    public University(){
        this("", "", "", "", "");
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

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
}
