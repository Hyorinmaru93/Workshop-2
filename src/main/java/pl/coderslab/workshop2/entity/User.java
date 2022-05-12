package pl.coderslab.workshop2.entity;

public class User {
    private int id;
    private String username;
    private String email;
    private String password;

    public User(int id, String email, String username, String password){
        this.id=id;
        this.email=email;
        this.username=username;
        this.password=password;
    }

    public void printUser() {
        System.out.println("ID:" + this.id);
        System.out.println("username:" + this.username);
        System.out.println("email:" + this.email);
    }

    public int getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
