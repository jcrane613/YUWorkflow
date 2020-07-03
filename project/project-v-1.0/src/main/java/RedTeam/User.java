package main.java.RedTeam;

import java.util.Queue;
import java.util.UUID;

public abstract class User {

    String firstName;
    String middleName;
    String lastName;
    String email;
    String password;
    Queue<Form> requestQueue;

    public User(String fullName, String email, String password) {
        String[] names = fullName.split(" ");
        this.firstName = names[0];
        this.middleName = (names.length > 2) ? names[1] : "";
        this.lastName = names[names.length-1];
        this.requestQueue = new Queue<Form>();
    }

    public Queue<Form> getAllForms() {
        return this.requestQueue;
    }

}
