package model;

/**
 * Created by ASUS on 17-Sep-23.
 */
public class User {
    private  int id ;
    private  String name,email,country,gender,address,password,type,confirmation_token;
    boolean isConfirmed;
    //the new user documented//
    public User(String name,String email,String gender,String password,String userType,String address,String confirmation_token){
        this.name = name;
        this.email = email;
        this.gender=gender;
        this.password=password;
        this.type=userType;
        this.address=address;
        this.confirmation_token=confirmation_token;
    }
    public User( int id,String name,String email,String gender,String address,
                 String password,String type,
    String confirmation_token,boolean isConfirmed){
        this.id=id;
        this.name = name;
        this.email = email;
        this.gender=gender;
        this.address=address;
        this.password=password;
        this.type=type;
        this.confirmation_token=confirmation_token;
        this.isConfirmed=isConfirmed;

    }

    ////////////

    public User(int   id, String name, String email,String gender, String country) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.gender=gender;
        this.country = country;
    }

    public User(String name, String email,String gender, String country) {
        this.name = name;
        this.email = email;
        this.gender=gender;

        this.country = country;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getType() { //teacher or student
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getConfirmation_token() {
        return confirmation_token;
    }

    public void setConfirmation_token(String confirmation_token) {
        this.confirmation_token = confirmation_token;
    }

    public boolean isConfirmed() {
        return isConfirmed;
    }

    public void setConfirmed(boolean confirmed) {
        isConfirmed = confirmed;
    }

    public int  getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    public void setGender(String gender){
        this.gender=gender;
    }
    public String getGender(){
        return gender;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }
}
